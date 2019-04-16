<%@page import="com.yonyou.util.PropertyFileUtil"%>
<%@page import="com.yonyou.util.theme.ThemePath"%>
<%@page import="com.yonyou.business.entity.TokenEntity"%>
<%@page import="com.yonyou.business.entity.TokenUtil"%>
<%@page import="com.yonyou.util.wsystem.service.ORG"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", -10); 
	TokenEntity tokenEntity = TokenUtil.initTokenEntity(request);
	String ContextPath = request.getContextPath();
	String path=ContextPath+"/vendor/vehicles";
	String workFlowContext =PropertyFileUtil.getValue("WORK_FLOW_CONTEXT");
%>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>业务管理系统</title>
<!-- 设置浏览器默认渲染模式 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="renderer" content="webkit">
</head>
<script src="<%=ContextPath%>/vendor/jquery/jquery-1.11.2.min.js"></script>
<script src="<%=ContextPath%>/pages/js/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=ContextPath%>/pages/tree/jstree.min.js"></script>
<script src="<%=ContextPath%>/pages/js/tinyselect.js"></script>
<link rel="stylesheet" href="<%=ContextPath%>/pages/js/tinyselect.css">
<link rel="stylesheet"
	href="<%=ContextPath%>/pages/js/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=ContextPath%>/pages/js/bootstrap-table/src/bootstrap-table.css">
<link rel="stylesheet"
	href="<%=ContextPath%>/pages/js/bootstrap/css/calendar.css">
<link rel="stylesheet" href="<%=ContextPath%>/pages/js/examples.css">
<link rel="stylesheet"
	href="<%=ContextPath%>/pages/tree/themes/default/style.min.css" />
<script src="<%=ContextPath%>/vendor/bootstrap/js/respond.js"></script>
<!-- bootstrapIE8补丁 -->
<script src="<%=ContextPath%>/vendor/bootstrap/js/html5.js"></script>
<script src="<%=ContextPath%>/vendor/bootstrap/js/respond.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/resert.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/header.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/index.css" />
<body>
	<div class="contain">
		<jsp:include page="headPage.jsp?isShow=1"></jsp:include>
		<!--内容tab栏-->

		<div id="First_level_Menu"></div>

		<div class="modal fade" id="myModal999" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true"
			data-backdrop="static" data-keyboard="false">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h5 class="modal-title" id="myModalLabel999" style="color: red">
							初次登陆，请先修改密码！</h5>
					</div>
					<div class="modal-body" style="height: 300px">
						<font id="loginMessage" color="red"></font>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>

		<!--详细事项-->
		<div class="my-detail">

			<div class="my-detailtop">
				<!--我的代办-->
				<div class="detail1">
					<div class="detail1-top" style="width: 400px">
						<div class="top-name">预算执行分析</div>

						<!-- <div class="topul">
							
							<ul class="top-change" id='notity_need_handle_count' style="display: none">
								<li  ><span  class="active" onclick="updateNeedHandleCount(this)" bus_type="#CODE#">#NAME#&nbsp;<span class="badge" style="">#BUS_SIZE#</span></span></li>
							</ul>
						</div>
						<span id="topulspan" class="glyphicon glyphicon-chevron-right" style="color:#08c;margin-top:18px;margin-left:10px;display:none;"></span>
						<span id="topulspanl" class="glyphicon glyphicon-chevron-left" style="color:#08c;margin-top:18px;margin-left:10px;display:none;"></span> -->
						<div class="top-audio">
							<span onclick="openNotityNeed()" class="moreand">更多</span>
						</div>
					</div>
					<div class="detail1-contain" style="width: 400px">
						<!--div1-->
						<!--全部对应内容-->
						<div class="active">
							<%-- <div id="notity_need_dealt" style="display: none" >
								<div class="contain1"  onclick="plistOnClick('notityNeed',#INDEX#,'审批',this)" >
									<p class="contain1-point" property='SHOW_NEED_TYPE'><img src="<%=path%>/img/needType#SHOW_NEED_TYPE#.png"/></p>
									<p class="contain1-article" property='PAGE_MESSAGE' data-toggle="tooltip"  title='#PAGE_MESSAGE#' >#PAGE_MESSAGE#</p>
									<p class="contain1-name" property='PREVIOUS_USER_NAME' >#PREVIOUS_USER_NAME#</p>
									<p class="contain1-time" property='NEED_START_TIME' >#NEED_START_TIME#</p>					
								</div>
							</div> --%>

							<!--<div class="contain1">
								<p class="contain1-point"><img src="<%=path%>/img/list1.png"/></p>
								<p class="contain1-article">上合青岛峰会日程公布:10日上午将举行正式会谈正式会谈正式会谈正式会谈正式会谈正式会谈正式会谈</p>
								<p class="contain1-name">刘晓红</p>
								<p class="contain1-time">2018-06-09 23:07:56</p>					
							</div>-->
						</div>
					</div>
				</div>
				<!--公告-->
				<div class="detail2">
					<div class="detail1-top">
						<div class="top-name">经营分析支撑</div>
						<ul class="top-change">
							<li><span class="active">全部</span></li>
							<!-- <li><span>近期</span></li>
							<li><span>一个月</span></li> -->
						</ul>
						<div class="top-audio" id="noticeAll">
							<span class="moreand">更多</span>
						</div>
					</div>

					<div class="detail1-contain">
						<!--div1-->
						<!--全部内容-->
						<div class="active">
							<!-- <a href="notice.jsp" id="noticeAll"> -->
							<div id="notice" style="display: none"
								style="height:100px;width:400px;">

								<div class="contain1"
									onclick="plistOnClick('notice',#INDEX#,'公告',this)" />
								<p class="contain1-point">
									<img src="<%=path%>/img/list3.png" />
								</p>
								<p class="contain1-name-p1" property='TITLE'>#TITLE#</p>
								<p class="contain1-name-p2" property='NOTICE_MESSAGE'
									data-toggle="tooltip" data-placement="top"
									title='#NOTICE_MESSAGE#'>#NOTICE_MESSAGE#</p>
								<p class="contain1-name-p2" property='STATE_DATE'>#STATE_DATE#</p>
							</div>

						</div>
						<!-- </a> -->
					</div>
				</div>

			</div>

			<div
				style="width: 33%; float: left; margin-left: 1%; height: 240px; background-color: #fff;">
				<!-- Nav tabs -->
				<ul class="nav nav-tabs">
					<li class="active"><a href="#home" aria-controls="home"
						role="tab" data-toggle="tab" style="color: #000">代办流程</a></li>
					<li><a href="#profile" aria-controls="profile" role="tab"
						data-toggle="tab" style="color: #000">我的申请</a></li>
				</ul>
				<!-- Tab panes -->
				<div class="tab-content">
					<div class="tab-pane active" id="home"></div>
					<div class="tab-pane" id="profile"></div>
				</div>
			</div>

			<div class="my-detailbottom">
				<!--我的待阅-->
				<div class="detail3">
					<div class="detail1-top">
						<div class="top-name">客商管理</div>
						<ul class="top-change" id='notity_need_pending_count'
							style="display: none">
							<li><span class="active"
								onclick="updateNeedPendingCount(this)" bus_type="#CODE#">#NAME#&nbsp;<span
									class="badge" style="">#BUS_SIZE#</span></span></li>
						</ul>
						<div class="top-audio">
							<span class="moreand">更多</span>
						</div>
					</div>
					<div class="detail1-contain">
						<!--div1-->
						<!--全部对应内容-->
						<div class="active">
							<%-- <div id="notity_need_pending" style="display: none">
								<div class="contain1"  onclick="plistOnClick('notityNeedPending',#INDEX#,'待阅',this)" >
										<p class="contain1-point"><img src="<%=path%>/img/list3.png"/></p>
									<p class="contain1-article" property='BUS_MESSAGE' data-toggle="tooltip"   title='#BUS_MESSAGE#'>#BUS_MESSAGE#</p >
									<p class="contain1-name" property='PREVIOUS_USER_NAME'>#PREVIOUS_USER_NAME#</p >
									<p class="contain1-time" property='NEED_START_TIME'>#NEED_START_TIME#</p >					
								</div>
							</div>
						 --%>

						</div>


					</div>
				</div>
				<!--常用模块-->
				<div class="detail4">
					<div class="detail4-top">
						<p class="top-name">主题访问</p>
						<p class="top-img">
							<span class="moreand">更多</span>
						</p>
					</div>
					<div class="detail4-contain">
						<ul class="contain-lists" id="hh">
							<%-- 	<li>
								<p><img src="<%=path%>/img/customer.png"/></p>
								<p>客户基本资料</p>								
							</li>
							<li>
								<p><img src="<%=path%>/img/task.png"/></p>
								<p>拜访任务</p>								
							</li>
							<li>
								<p><img src="<%=path%>/img/visit.png"/></p>
								<p>拜访规则</p>								
							</li>
							<li>
								<p><img src="<%=path%>/img/summary.png"/></p>
								<p>拜访小结</p>								
							</li>
							<li>
								<p><img src="<%=path%>/img/data1.png"/></p>
								<p>客户基本资料</p>								
							</li>
							<li>
								<p><img src="<%=path%>/img/data2.png"/></p>
								<p>客户基本资料</p>								
							</li>
							<li>
								<p><img src="<%=path%>/img/data3.png"/></p>
								<p>客户基本资料</p>								
							</li>
							<li>
								<p><img src="<%=path%>/img/more.png"/></p>
								<p>更多</p>								
							</li> --%>
						</ul>
					</div>
				</div>

				<div class="detail5" style="margin-left: 1%; width: 33%;">
					<div class="detail5-top">
						<div class="top-name">日历</div>
					</div>
					<div class="detail5-contain">
						<!--div1-->
						<!--全部对应内容-->
						<div class="active">
							<div id="calendar" class="calendar"></div>
						</div>


					</div>
				</div>

			</div>

		</div>
	</div>

	<div
		style="height: 50px; position: absolute; z-index: 1; float: left; top: 250px; right: 800px;"
		id="roleList"></div>
</body>
<script src="<%=ContextPath%>/pages/js/bootstrap/js/calendar.js"
	type="text/javascript"></script>
<script src="<%=ContextPath%>/js/public.js"></script>
<script src="<%=ContextPath%>/js/bootTable.js"></script>
<script src="<%=ContextPath%>/js/initPage.js"></script>
<script src="<%=ContextPath%>/js/bulidPlistPage.js"></script>
<script src="<%=ContextPath%>/js/bulidNeedTypeCount.js"></script>
<script type="text/javascript">
		
		 //系统管理
		 var visit_context ='<%=workFlowContext%>';
		 var work_flow_context='<%=workFlowContext%>';
		 var context='<%=request.getContextPath()%>';
		 var token ='<%=tokenEntity.getToken()%>';
		 var showTotalCode ="";
		 var roleId = '<%=tokenEntity.ROLE.getRoleId()%>';
		 var $lastShow;
         var company_id ='<%=tokenEntity.COMPANY.getCompany_id()%>';
         var companyname='<%=tokenEntity.COMPANY.getCompany_name()%>';
         var user_id='<%=tokenEntity.USER.getId()%>';
		 function sure (){
		 	if(roleId==""||roleId=="null"){
		 		roleId = $("input[name='role']:checked").val();
		 	}
		 	//获取当前部门id
		 	var comid = $("input[name='company']:checked").val();
		 	$("#role_type option[value='0']").remove();
		 	$("#company_type option[value='0']").remove();
		 	init(roleId,comid,0);
		 	//隐藏切换角色
		 	$('#roleList').hide();
		 	hidePopover();
		 }
		 function init(roleId,cid,tab){
		 	var ccid = "";
		 	if(typeof(cid) == "undefined"){
		 		 ccid = $("input[name='company']:checked").val();;
		 		 company_id = ccid;
		 	}
		 	if(cid == null){
			 	cid  = $("#company_type").find("option:selected").val();
			 	company_id = cid;
		 	}else if(cid==""){
		 		cid  = $("input[name='company']:checked").val();
			 	company_id = cid;
		 	}else{
		 		company_id = cid;
		 	}
		 	//取消所有select 对当前cid进行选择属性 
		 
		 	$("#company_type").find("option[value!='"+company_id+"']").removeAttr('selected');
		 	$("#company_type").find("option[value = '"+company_id+"']").attr("selected","selected");
		 	var company_name =$("#company_type").find("option:selected").text();;
		 	companyname = company_name;
		 	console.log("------cid="+company_id+"cname="+companyname+"roleid="+roleId);
		 	initMenu(company_id,companyname,roleId,tab);
		 	//调用父页面的方法
		 	loadIndexRole(roleId,cid);
		 }
		 
		 $(function() {
			 	//$.noConflict();
			 	
            	var roleJson = JSON.parse('<%=tokenEntity.roleJsonString%>');
            	var companyJson = JSON.parse('<%=tokenEntity.deptJsonString%>');
            	var html ="<lable>部门： <div>";
            	for(var i=0;i<companyJson.length;i++){
            		if(i==0){
            			html=html+'<input type="radio" name="company" checked="checked" value="'+companyJson[i].ID+'"/>'+companyJson[i].NAME;
            		}else{
            			html=html+'<input type="radio" name="company"  value="'+companyJson[i].ID+'"/>'+companyJson[i].NAME;
            		}
        		}
        		html=html+"</div></lable><lable>角色</lable><div>";
            	for(var i=0;i<roleJson.length;i++){
            		if(i==0){
            			html=html+'<input type="radio" name="role" checked="checked" value="'+roleJson[i].roleId+'"/>'+roleJson[i].roleName;
            		}else{
	            		html=html+'<input type="radio" name="role" value="'+roleJson[i].roleId+'"/>'+roleJson[i].roleName;
            		}
            	}
            	html=html+"</div>";
            	html+="<div class='modal-footer'><button type='button' class='close'  onclick='sure()'  data-dismiss='modal' >确定</button></div>";
            	//$('#roleList').html(html);
            	console.log(html);
            	if(roleJson.length>1){
	            	if(roleId==""||roleId=="null"){
		            	oTable.showModal('请选择部门和角色', html);
		            	
	            	}
            	}else{
            		if(roleJson.length>0){
            			roleId = roleJson[0].roleId;
            		}
            	}
           		if(roleId!=""){
           			sure();
           		}
           		
           		if(roleJson.length<=1){
           			updPwd();
           		}else{
	           		$('#modal').on("hide.bs.modal",function(e){
		           		updPwd();
	           		 });
           		}
           		//查询待办
	            bulidNeedHandleCountType("notityNeedCount","ALL",$("#notity_need_handle_count"),true);
	            //查询待阅
	            bulidNeedHandleCountType("notityNeedPendingCount","ALL",$("#notity_need_pending_count"),true);
           		initNotity();
           		//flushCustMenu();
           		updateHandle();
           		
           		//$("#First_level_Menu").attr("mouseleave","hidePopover()");
           		
           		
           		//alert($("#notity_need_handle_count").children("li").length);
           		if($("#notity_need_handle_count").children("li").length>7){
           			//alert(111);
           			$("#topulspan").show();
           		}else{
           		};
           			$("#topulspan").click(function(){           				
					 	$("#notity_need_handle_count").css({'right':'0'});
					 	$("#notity_need_handle_count").css({'left':''});
					 	//$("#notity_need_handle_count").removeAttr({'right':'0'});
					 	$("#topulspan").hide();	
					 	$("#topulspanl").show();
					 		
					});
					$("#topulspanl").click(function(){           				
					 	$("#notity_need_handle_count").css({'left':'0'});
					 	$("#notity_need_handle_count").css({'right':''});
					 	$("#topulspanl").hide();	
					 	$("#topulspan").show();	
					});
           		
         }); 
		//初始化待办
		function initNeedHandle(){
			bulidPlist('notityNeed',$("#notity_need_dealt"),'');
		}
		
		function updateNeedHandleCount(t){
			var bus_type =$(t).attr("bus_type");
			bulidNeedHandleCountType("notityNeedCount",bus_type,$("#notity_need_handle_count"),false);
		}
		
		function updateNeedPendingCount(t){
			var bus_type =$(t).attr("bus_type");
			bulidNeedHandleCountType("notityNeedPendingCount",bus_type,$("#notity_need_pending_count"),false);
		}
		
		function loadNeedHandle(t,type){
			var bus_type =$(t).attr("bus_type");
			if(type=='notityNeedCount'){
				bulidPlist('notityNeed',$("#notity_need_dealt"),'&bus_type='+bus_type);
				$("#notity_need_handle_count").children().find(".active").removeClass("active");
			
			}else if(type=='notityNeedPendingCount'){
				bulidPlist('notityNeedPending',$("#notity_need_pending"),'&bus_type='+bus_type);
				$("#notity_need_pending_count").children().find(".active").removeClass("active");
			}
			$(t).addClass("active");
			
		}
		
		//初始化待阅
		function initHeedPending(){
			bulidPlist('notityNeedPending',$("#notity_need_pending"),'');
		}
		
		//初始化公告
		function initNotity(){
			bulidPlistByUrl('notice',$("#notice"),'/system/base?cmd=noticeJSON&dataSourceCode=NF_NOTICE_SHOW&token='+token,'');
		}
		
		var winOpenObjArray=[];
		function openMenu(t){
			var totalcode=$(t).attr("totalcode");
			var totalname=$(t).attr("totalname");
			var url=$(t).attr("url");
			if(url.length>0){
				var pageUrl='<%=ThemePath.findRoute(request)%>menuPage.jsp?token=<%=tokenEntity.getToken()%>&totalcode='+totalcode+'&totalname='+totalname+'&url='+url;
				var containFlag=false;
				var containObj=null;
				for(var i=0;i<winOpenObjArray.length;i++){
					if(winOpenObjArray[i].url==pageUrl){
						containFlag=true;
						containObj=winOpenObjArray[i].obj;
					}
				}
				if(containObj==null||containObj.closed){
					var mywin=window.open('<%=ThemePath.findRoute(request)%>menuPage.jsp?token=<%=tokenEntity.getToken()%>&totalcode='+totalcode+'&totalname='+totalname+'&url='+url); //将新打的窗口对象，存储在变量mywin中
					winOpenObjArray.push({url:pageUrl,state:'1',obj:mywin});
					
				}else{
					containObj.focus();
				}
			}
			hidePopover();
		}
		
		function openMenuPage(t){
			var locationCode=$(t).attr("totalcode");
			var totalname=$(t).attr("totalname");
			var totalcode=$(t).attr("parentcode");
			var parentcode=$(t).attr("parentcode");
			if(parentcode!=100){
				totalname=$("li[totalcode="+parentcode+"]").attr("totalname");
			}
			var url=$(t).attr("url");
			var pageUrl='<%=ThemePath.findRoute(request)%>menuPage.jsp?token=<%=tokenEntity.getToken()%>&totalcode='+totalcode+'&totalname='+totalname+'&url='+url+'&locationCode='+locationCode;
			var containFlag=false;
			var containObj=null;
			for(var i=0;i<winOpenObjArray.length;i++){
				if(winOpenObjArray[i].url==pageUrl){
					containFlag=true;
					containObj=winOpenObjArray[i].obj;
				}
			}
			if(containObj==null|| containObj.closed){
				var mywin=window.open('<%=ThemePath.findRoute(request)%>menuPage.jsp?token=<%=tokenEntity.getToken()%>&totalcode='+totalcode+'&totalname='+totalname+'&url='+url+'&locationCode='+locationCode); //将新打的窗口对象，存储在变量mywin中
				winOpenObjArray.push({url:pageUrl,state:'1',obj:mywin});
				var a=winOpenObjArray[0];
			}else{
				containObj.focus();
			}
			
			hidePopover();
		}
		
		function initMenu(companyid,companyname,roleId,tab){
	        	loadingMenu();
	        	hidePopover();
	        	var paramJson = {
           		 'companyid':companyid,
            	 'companyname':companyname
        		};
        		//弹出角色选择窗口
	        	$.ajax({  
	                    url : "/portal/base?cmd=findMenu&type=1&token="+'<%=tokenEntity.getToken()%>'+'&ts='+new Date().getTime()+"&roleid="+roleId+"&tab="+tab,  
	                    dataType : "json",  
	                    type : "post",
	                    data : paramJson,
	                    success : function(data) { 
	                    	message = data['message'];
	                    	$("#First_level_Menu").html(message);
	                    	$(function () { 
		                    	$("[data-toggle='popover']").popover({
		                    					 html : true ,
		                    			         animation:true,placement: 'right',
		                    					 delay:{ show: 200, hide: 100 }
		                    	                 });
		                    	$("[data-toggle='popover']").on("mouseenter mouseleave",function(event){
									 if(event.type == "mouseenter"){
									 	$('.popover').popover("hide");
									    //鼠标悬浮
										$(this).popover("show");
										
									 }
									 else if(event.type == "mouseleave"){
									   //鼠标离开
									   var _this = this;
									   
									   //$(_this).popover("hide");
									   //setTimeout(function () {            	      
											//if (!$(".popover:hover").length) {            	          
												//$(_this).popover("hide")            	                 
											//}            	                    
										//}, 200); 
									 }
								});
								
							})

	                    	$('.li-hover').hover(function(){
					    		$(this).find('ul').hide();
					  			},
							    function(){
							      $(this).find('ul').show();
							    });
	                    } ,
	                    error:function(XMLHttpRequest, textStatus, errorThrown){
							message ="请求失败";
							alert(message);
						}
	             });
	               
	     }
	     
	     
	     
	     
	    function flushCustMenu(){
			  	$.ajax({  
			        url:"<%=ContextPath%>/menu/show?token=<%=tokenEntity.getToken()%>",  
			        type:"post", 
			        data:"",  
			        dataType:"json",  
			        success:function(data){
			        	
			        	$("#hh").text("");
			        	var imgs =[];
			        	imgs[0]='customer.png';
			        	imgs[1]='task.png';
			        	imgs[2]='visit.png';
			        	imgs[3]='summary.png';
			        	imgs[4]='data1.png';
			        	imgs[5]='data2.png';
			        	imgs[6]='data3.png';
			        	for (var i=0; i < data.length; i++) {
			        			 $("#hh").append("<li><p totalcode='"+data[i].TOTAL_CODE+"' onclick='menuManager(this)'>"+"<img src='<%=path%>/img/"+imgs[i%6]+"'+ alt='' style='cursor:pointer'></p>"+
			        		 	"<p><a href='javascript:void(0)' totalcode="+data[i].TOTAL_CODE+" onclick='menuManager(this)' title='"+data[i].NAME+"'>"+data[i].NAME.substring(0,4)+"</a></p></li>");
			        	};
			        },  
			        error:function(XMLHttpRequest, textStatus, errorThrown){
						alert(errorThrown);
			        }  
		     	});  
	     }
function loadingMenu(){
	$("#First_level_Menu").html("<li><font color='white'>loading...</font><li>");
}
	        
	        
function  bulidShowPage(type,json,jsonData,$title,$width){
	hidePopover();
	if(type=='notityNeed'){
		var showNeedType =json["SHOW_NEED_TYPE"];
		if(showNeedType=='0'){
			$title.html(json["PAGE_MESSAGE"])
			$width.width("90%");
			var viewUrl= work_flow_context+'/pages/auditPage.jsp?&token='+token+'&ts=' + Math.random(1000);
			$.get(viewUrl, {"jsonData":jsonData}, function(data) {
				$('#ViewModal .modal-body').html(data);
				return ;
			});
		}else if(showNeedType=='1'){
			$title.html(json["PAGE_MESSAGE"])
			$width.width("90%");
			var type =json["TYPE"];
			var url =json["VISIT_BUS_URL"];
			var params=json["URL_PARAMS"];
			//&REF_COLUMN=BUSINESS_NUMBER&REF_SELECT_PARAMS=SEARCH-BUSINESS_ID&REF_SELECT_VALUES=Z9FCUI201808190003
			if(type=='MENU_JUMP_PAGE'){
				if(confirm("是否跳到业务页面！")){
					var mywin=window.open('<%=ThemePath.findRoute(request)%>menuPage.jsp?token=<%=tokenEntity.getToken()%>'+params); //将新打的窗口对象，存储在变量mywin中
					return false;
				}else{
					return false;
				}
			}else{
				var viewUrl= work_flow_context+'/pages/busFlowPage.jsp?&token=<%=tokenEntity.getToken()%>'+params+'&ts=' + Math.random(1000);
				$.get(viewUrl, {"jsonData":jsonData}, function(data) {
					$('#ViewModal .modal-body').html(data);
					return ;
				});
			}
			
		}
	}else if(type=='notityNeedPending'){
		var message =json["BUS_MESSAGE"];
		transToServerForNeedPending(work_flow_context+'/buttonBase?cmd=button&buttonToken=notityNeedPendingHandle',jsonData);
		var visit_bus_url =json["VISIT_BUS_URL"];
		var type =json["TYPE"];
		var isText =false;
		var viewUrl='';
		
		if(type=='AUDIT_PAGE'){ //是否审批页面
			viewUrl= work_flow_context+'/pages/flowPendingPageForAudit.jsp?&token=<%=tokenEntity.getToken()%>'+params+'&ts=' + Math.random(1000);
		}else{
			if(type=='ON_CLICK_PAGE'){ //是否默认双击页面
				visit_bus_url="/system/pages/busDetailMessage.jsp?pageCode="+json["MOVE_UP_LINK"]+"&busId="+json["BUS_ID"]+"&token=<%=tokenEntity.getToken()%>";
				json["VISIT_BUS_URL"]=visit_bus_url;
				jsonData=JSON.stringify(json);
			}
			if(visit_bus_url!=null&&visit_bus_url.length>0){ //是否维护页面或详情页面
				viewUrl= work_flow_context+'/pages/flowPendingPageForDetail.jsp?&token=<%=tokenEntity.getToken()%>'+params+'&ts=' + Math.random(1000);
			}else{
				isText=true;  //文本信息
			}
		}
		
		if(isText){
			$title.html("待阅");
			$width.width("50%");
			$width.height("500px");
			$('#ViewModal .modal-body').html(message);
		}else{
			$title.html("待阅:"+message);
		    $width.width("90%");
		    $.get(viewUrl, {"jsonData":jsonData}, function(data) {
				$('#ViewModal .modal-body').html(data);
				return ;
		    });
		}
	}else if(type=='notice'){
		var message =json["NOTICE_MESSAGE"];
		$title.html("<font color='blue' >"+json["TITLE"]+"</font>"+"&emsp;<font  size='2px' color='gray'>"+json["STATE_DATE"]+"</font>");
		$width.width("50%");
		$('#ViewModal .modal-body').html(message);
	}
	return true;
}




function transToServerForNeedPending(url,jsonData){
		var message;
		$.ajax({
    		async: false,
    		type: "post",
			url: url,
			dataType: "json",
			data:{"jsonData":jsonData},
			success: function(data){
				message = data['message'];
				bulidNeedHandleCountType("notityNeedPendingCount","",null,true);
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				//登录超时
		    	if(XMLHttpRequest.getResponseHeader("TIMEOUTURL")!=null){
		    		window.top.location.href = XMLHttpRequest.getResponseHeader("TIMEOUTURL");
		    	}
				message ="请求失败";
			}
		});
    	return message;
    };
  	
  	
    
    function hidePopover(){
    	$('.popover').popover("hide");
    }

	function updateHandle(){
		setTimeout(function(){
			bulidNeedHandleCountType("notityNeedCount",'',$("#notity_need_handle_count"),true);
			bulidNeedHandleCountType("notityNeedPendingCount",'',$("#notity_need_pending_count"),true);
			updateHandle();
		},1000*60*5);
	}
	
	//function updatePopover(){
		//setTimeout(function(){
			//hidePopover();
			//updatePopover();
		//},2000);
	//}
	
	
	function notice(id){
	    $.ajax({
			type : 'get',
			url : "/system/base?cmd=updateState",
			data : {"ID":id},
			success : function(data) {
				//alert("ajax请求成功！"+id);
			},
			error : function() {
				alert('updateState请求失败 请联系管理员！ ');
			}
		});
			
      	window.open("/portal/pagestice.jsp?"+"ID="+id+"&State=1",+"_blank",
		     "top=100,left=400,width=700,height=500,menubar=yes,scrollbars=no,toolbar=no,status=yes");
     }
			
	noticeAll.onclick=function(){
		     window.open("/system/pages/singleTableModify.jsp?pageCode=NF_NOTICE_SHOW&pageName=公告展示&token=<%=tokenEntity.getToken()%>","_blank",
		     "top=100,left=300,width=1000,height=600,menubar=yes,scrollbars=no,toolbar=no,status=yes");
			}
	function openNotityNeed(){
		window.open('../pages/t/vehicles/menuManage.jsp?userid=<%=tokenEntity.USER.getId()%>');
	}
	
	function updPwd(){
		$.ajax({
			type : "POST",
			url :  "/portal/base?cmd=selectUpdPwd&user_id="+user_id,
			dataType : "json",
			success : function(data) {
				if(data.IS_UPD_PASSWORD!=1){
 					$("#loginMessage").html('<iframe src=\'./t/vehicles/editPassWord.jsp?token='+token+'&IS_UPD_PASSWORD=0\' width=\'100%\' height=\'100%\' frameborder=\'0\'>您的浏览器不支持iframe，请升级<\/iframe>');
 					$("#myModal999").modal('show'); 
 				}
			},
			error : function(data) {
				alert( "查询失败");
			}
		});
	}
	
	
	more2.onclick=function(){
		     <%--        var pageWidth = window.innerWidth ; 
				var pageHeight = window.innerHeight; 
				
				if (typeof pageWidth != "number"){ 
				    if (document.compatMode == "CSS1Compat"){ 
				        pageWidth = document.documentElement.clientWidth; 
				        pageHeight = document.documentElement.clientHeight; 
				    } else { 
				        pageWidth = document.body.clientWidth; 
				        pageHeight = document.body.clientHeight; 
				    } 
				} 
				
				var newWidth = (Number(pageWidth)/3) < 600 ? 600 : (Number(pageWidth)/3); 
				var newHeight = (Number(pageHeight)/2) < 400 ? 400 : (Number(pageHeight)/2); 
				var newLeft = pageWidth - newWidth - 50; 
				var newTop = pageHeight - newHeight - 5 ; 
				winObj = window.open('/portal/pages/nodeCommonly.jsp?token=<%=tokenEntity.getToken()%>','_blank','width='+newWidth+',height='+newHeight+',top='+newTop+'px,left='+newLeft+'px,toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no');
				flag=true; --%>
				buildShowPageNodeCommonly('needCommonly','常用功能配置','90%');
	}	
	
	
	function changeFrameHeight(){
	}
	
	
	
	</script>
<style type="text/css">
.tooltip-inner {
	max-width: 400px;
	padding: 3px 8px;
	color: #000;
	text-align: center;
	text-decoration: none;
	background-color: rgb(240, 240, 242);
	border-radius: 4px;
}
</style>
</html>
