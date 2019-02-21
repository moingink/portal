
var buttonHtmlForButton ='<button type="button" class="btn btn-default" buttonToken="{buttonToken}" onclick="{fun}">{name}</button>';
var buttonHtmlForSelect ='<button type="button" class="btn btn-primary" buttonToken="{buttonToken}" onclick="{fun}">{name}</button>';

var buttonHtmlForImg='<div class="button-group {buttonClass}" buttonToken="{buttonToken}" onclick="{fun}"> <span class="img" ></span> <span>{name}</span> </div>' ;
	
var buttonHtml="";

var imgType = {
			  '查询':'sel',
			  '新增':'ad',
              '修改':'up',
              '删除':'de',
              '提交':'su',
              '审批':'au',
              '复制':'f'};

var initButtonJson =[
                     {name:'查询',fun:'queryTable(this)',buttonToken:'query'},
                     {name:'新增',fun:'tog(this)',buttonToken:'add'},
                     {name:'修改',fun:'updateRow(this)',buttonToken:'update'},
                     {name:'删除',fun:'delRows(this)',buttonToken:'delete'}
                    ];

function bulidButtonHtml($button,buHtml,dataSource){
	//修改查询按钮位置
	if ($("#isNewStyle").val() != "1") {
		buttonHtml=buttonHtmlForButton;
	}else{
		$button.attr("class","button-menu");
		buttonHtml=buttonHtmlForImg;
	}
	
	if(buHtml!=null&&buHtml.length>0){
		var html =bulidHtml(initButtonJson);
		html=html+buHtml;
		$button.append(html);
	}else{
		return findButtonHtmlByJs($button,dataSource);
	}
	
}

function findButtonHtmlByJs($button,dataSource){
	var buttonTypeVal =$button.attr("buttonType");
	var html =bulidHtml($button,buttonTypeVal,initButtonJson);
	$button.html(html);
	loadScript(findBusJsUrl(dataSource),function(){  //加载test.js,成功后，并执行回调函数
		if(buttonJson==null||buttonJson.length==0){
			buttonJson=initButtonJson;
		}
		$button.html(bulidHtml($button,buttonTypeVal,buttonJson));
		loadButtonJsEnd();
		//修改查询按钮位置
		appendBulidSelect();
		showBillSelectHtml(dataSourceCode);
	});
	
}

function loadButtonJsEnd(){
	if(typeof(loadJsFunction) == "function"){
		loadJsFunction();
	}
}

function bulidHtml($button,buttonTypeVal,jsonByJs){
	var html ='';
	
	for(var i =0;i<jsonByJs.length;i++){
		var buttonClass='xz';
		
		if(imgType[jsonByJs[i]["name"]]!=null){
			buttonClass=imgType[jsonByJs[i]["name"]];
		}
		jsonByJs[i]["buttonClass"]=buttonClass;
		
		if(buttonTypeVal!=null&&buttonTypeVal.length>0){
			if(jsonByJs[i]["buttonType"]==buttonTypeVal){
				if(isLoadSelectButton(jsonByJs[i]["name"])){
					html=html+buttonHtml.format(jsonByJs[i])+"&nbsp;";
				}
			}
		}else{
			if(isLoadSelectButton(jsonByJs[i]["name"])){
				html=html+buttonHtml.format(jsonByJs[i])+"&nbsp;";
			}
		}
		
	}
	return html;
}



function bulidHtmlBySelect(jsonByJs){
	var html ='';
	for(var i =0;i<jsonByJs.length;i++){
		if(jsonByJs[i]["name"]=='查询'){
			html=html+buttonHtmlForSelect.format(jsonByJs[i])+"&nbsp;";
		}
	}
	return html;
}


function buttonToServer(url,jsonData){
	var message;
	var jsNode;
	$.ajax({
		async: false,
		type: "post",
		url: url,
		dataType: "json",
		data:{"jsonData":jsonData},
		success: function(data){
			message = data['message'];
			jsNode =data['js'];
			},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			message ="请求失败";
			}
		});
	writeJs(jsNode);
	return message;
};

function writeJs(jsNode){
	 var myScript= document.createElement("script");
     myScript.type = "text/javascript";
     myScript.appendChild(document.createTextNode(jsNode));
     document.body.appendChild(myScript)
}

function isLoadSelectButton(name){
	var returnType=true;
	if(false){
		if(name=='查询'){
			returnType=!returnType;
		}
	}
	return returnType
}

function appendBulidSelect(){
	if(false){
		var selectButtonPage=$("#selectButtonPage").html();
		$("#selectButtonPage").html(bulidHtmlBySelect(buttonJson)+selectButtonPage);
	}
}


function showBillSelectHtml( dataSourceCode){
	$("#bill_date_and_status").append(getBillStatus(dataSourceCode));
	billDateOnclick();
	billStatusOnclick();
}

function billDateOnclick(dateField){
	var dateField = "BILL_DATE" ; 
	var dateFrom = dateField+'_FROM' ; 
	var dateTo = dateField+'_TO' ; 
	$("#bill_date_div span").each(function(index) {
		$(this).click(function() {
			$("#bill_date_div span").removeClass('badge');
			$(this).addClass('badge');
			var thisId = $(this).attr("id");
			if('bill_date_noLimit'==thisId){
				$("#queryParam  input[id*='"+dateFrom+"']").val("");
				$("#queryParam  input[id*='"+dateTo+"']").val("");
			}
			if('bill_date_today'==thisId){
				$("#queryParam  input[id*='"+dateFrom+"']").val(formatDate(new Date()));
			}
			if('bill_date_this_week'==thisId){
				$("#queryParam  input[id*='"+dateFrom+"']").val(getWeekStartDate());
			}
			if('bill_date_this_month'==thisId){
				$("#queryParam  input[id*='"+dateFrom+"']").val(getMonthStartDate());
			}
			queryTable($("#selectButtonPage button[buttontoken='query']").get(0));
		});
	});
}

function billStatusOnclick(){
	var statusField = 'BILL_STATUS';
	$("#bill_status_div span").each(function(index) {
		$(this).click(function() {
			$("#bill_status_div span").removeClass('badge');
			$(this).addClass('badge');
			$("#queryParam  [name*='"+statusField+"']").val($(this).attr("value"));
			queryTable($("#selectButtonPage button[buttontoken='query']").get(0));
		});
	});
}

function getBillStatus( dataSourceCode ){
	var message  ; 
	var url = context+'/billQuery?cmd=getBillSelect&dataSourceCode='+dataSourceCode;
	$.ajax({
		async: false,
		type: "post",
		url: url,
		dataType: "text",
		data:{"jsonData":null},
		success: function(data){
			message = data;
			},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			message ="请求失败";
			}
		});
		if(message.indexOf('>') == -1){
			return message ; 
		}
	return  JSON.parse(message)  ;
}

function moreToggle(){
	$("#queryParam").toggle();
	setParntHeigth($(document.body).height());
}
