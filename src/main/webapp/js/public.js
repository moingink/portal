
function findUrl(modifyName) {
	return findUrlParam('base', modifyName, '');
}

function findUrlParam(actionName, modifyName, params) {
	var url = context + "/" + actionName + "?cmd=" + modifyName + params;
	return url;
}

String.prototype.format = function(args) {
	if (arguments.length > 0) {
		var result = this;
		if (arguments.length == 1 && typeof (args) == "object") {
			for ( var key in args) {
				var reg = new RegExp("({" + key + "})", "g");
				result = result.replace(reg, args[key]);
			}
		} else {
			for (var i = 0; i < arguments.length; i++) {
				if (arguments[i] == undefined) {
					return "";
				} else {
					var reg = new RegExp("({[" + i + "]})", "g");
					result = result.replace(reg, arguments[i]);
				}
			}
		}
		return result;
	} else {
		return this;
	}
}

function findPageParam() {
	var pageParam = "&dataSourceCode=" + dataSourceCode;
	//组装查询参数
	$("[id^=SEARCH-]").each(function() {
		if($(this).val().length > 0){
	  		pageParam += "&"+$(this).attr("id")+"="+$(this).val();
	  		}
	  	});
	return encodeURI(pageParam);
}

/**
 *由insPage块获取字段id、value，组装为json字符串 
 */
function getJson(){
	var json = "{";
	$inspage.find("[id]").each(function() {
	  json += "\"" + $(this).attr("id") + "\":" + "\"" + $(this).val() + "\",";
	});
	json = json.substring(0,json.length-1);
	json += "}";
	return json;
}

function findCheckMessage() {
	var message = "table:" + JSON.stringify(oTable.bootMethod($table, "getSelections"));
	oTable.showModal('modal', message);
}

function getSelections () {
	//注意：bootstrap-table内置getSelections方法所返回的json不能直接用于ajax传输——存在JSONNull数据
	return JSON.stringify(oTable.bootMethod($table, "getSelections"));
}

function  busEvent(t,buttonParams){
	
	var message = transToServer(findBusUrlByPublicParam(t,buttonParams),getSelections());
	oTable.showModal('modal', message);
}

function findBusUrlByPublicParam(t,buttonParams){
	var buttonToken = $(t).attr("buttonToken");
	return findBusUrlByButtonTonken(buttonToken,buttonParams);
}

function findBusUrlByButtonTonken(buttonToken,buttonParams){
	buttonToken+="_operation";
	return  findUrlParam('buttonBase','button','&buttonToken='+buttonToken+findPageParam()+buttonParams);
}

function loadJs(file) {
	var script=document.createElement("script");  
	script.type="text/javascript";  
	script.src=file;  
	document.getElementsByTagName('head')[0].appendChild(script);  
}


function loadScript(url, callback) {
	  var script = document.createElement("script");
	  script.type = "text/javascript";
	  if(typeof(callback) != "undefined"){
	    if (script.readyState) {
	      script.onreadystatechange = function () {
	        if (script.readyState == "loaded" || script.readyState == "complete") {
	          script.onreadystatechange = null;
	          callback();
	        }
	      };
	    } else {
	      script.onload = function () {
	        callback();
	      };
	    }
	  }
	  script.src = url;
	  document.body.appendChild(script);
	}

function loadJsIsFLEF(filespec){
	$.load( filespec , ''  , function(responseText, textStatus, XMLHttpRequest){
	    if(textStatus !== "success"){
	        alert("文件下载失败");//console.log("文件下载失败");
	    }
	} );
}

function loadBusJsByDataSource(dataSourceCode){
	
	loadJs(findBusJsUrl(dataSourceCode));
}

function findBusJsUrl(dataSourceCode){
	var fileName=dataSourceCode;
	if(menuCode!=''&&menuCode.length>0){
		fileName=dataSourceCode+'_'+menuCode;
	}
	
	var file ="../pages/busJs/"+fileName+".js";
	return file;
}
