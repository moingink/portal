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
<style>
	.contain nav{
		width:90% !important;
	}
	
	body{margin:0; padding:0;}
</style>
</head>
<script src="<%=ContextPath%>/vendor/jquery/jquery-1.11.2.min.js"></script>
<script src="<%=ContextPath%>/pages/js/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=ContextPath%>/pages/tree/jstree.min.js"></script>
<script src="<%=ContextPath%>/pages/js/tinyselect.js"></script>
<link rel="stylesheet" href="<%=ContextPath%>/pages/js/tinyselect.css">
<link rel="stylesheet" href="<%=ContextPath%>/pages/js/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=ContextPath%>/pages/js/bootstrap-table/src/bootstrap-table.css">
<link rel="stylesheet" href="<%=ContextPath%>/pages/js/examples.css">
<link rel="stylesheet" href="<%=ContextPath%>/pages/tree/themes/default/style.min.css" />
<script src="<%=ContextPath%>/vendor/bootstrap/js/respond.js"></script>
<!-- bootstrapIE8补丁 -->
<script src="<%=ContextPath%>/vendor/bootstrap/js/html5.js"></script>
<script src="<%=ContextPath%>/vendor/bootstrap/js/respond.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/resert.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/header.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/index.css"/>
	<body >
		<div class="contain">
			<jsp:include page="headPage.jsp?isShow=1"></jsp:include>
			<!--内容tab栏-->
			<div id="First_level_Menu" >
			</div>
			<!--详细事项-->
			<div class="my-detail">
				<!--我的代办-->
				<div class="detail1">
					<div class="detail1-top">
						<div class="top-name">我的待办</div>
						<ul class="top-change">
							<li><span  class="active">全部&nbsp;<span class="badge" style="background-color:rgb(2,141,241)">42</span></span></li>
							<li><span>客户管理<span class="badge pull-right">12</span></span></li>
							<li><span>业务管理<span class="badge pull-right">10</span></span></li>
							<li><span>财务管理<span class="badge pull-right">9</span></span></li>
							<li><span>综合管理<span class="badge pull-right">11</span></span></li>
						</ul>
						<div class="top-audio">
							<img src="<%=path%>/img/play.png"/>
						</div>
					</div>
					<div class="detail1-contain">
						<!--div1-->
						<!--全部对应内容-->
						<div class="active">
							<div id="notity_need_dealt" style="display: none">
								<div class="contain1"  onclick="plistOnClick('needHandle',#INDEX#,'审批',this)" >
									<p class="contain1-point"><img src="<%=path%>/img/list1.png"/></p>
									<p class="contain1-article" property='BUS_MESSAGE'>#BUS_MESSAGE#</p>
									<p class="contain1-name" property='NEED_USER_NAME'>#NEED_USER_NAME#</p>
									<p class="contain1-time" property='NEED_START_TIME'>#NEED_START_TIME#</p>					
								</div>
							</div>
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
						<div class="top-name">公告</div>
						<ul class="top-change">
							<li><span  class="active">全部</span></li>
							<li><span>近期</span></li>
							<li><span>一个月</span></li>
						</ul>
						<div class="top-audio">
							<img src="<%=path%>/img/play.png"/>
						</div>
					</div>
					<div class="detail1-contain">
						<!--div1-->
						<!--全部内容-->
						<div class="active">
							<div class="contain1">
								<p class="contain1-point"><img src="<%=path%>/img/list3.png"/></p>
								<p class="contain1-article">习近平主持中俄蒙元首第四次会晤</p>
								<p class="contain1-time">2分钟前</p>					
							</div>
							<div class="contain1">
								<p class="contain1-point"><img src="<%=path%>/img/list3.png"/></p>
								<p class="contain1-article">习近平主持中俄蒙元首第四次会晤</p>
								<p class="contain1-time">2分钟前</p>						
							</div>
							<div class="contain1">
								<p class="contain1-point"><img src="<%=path%>/img/list3.png"/></p>
								<p class="contain1-article">习近平主持中俄蒙元首第四次会晤</p>
								<p class="contain1-time">2分钟前</p>							
							</div>
							<div class="contain1">
								<p class="contain1-point"><img src="<%=path%>/img/list3.png"/></p>
								<p class="contain1-article">习近平主持中俄蒙元首第四次会晤</p>
								<p class="contain1-time">2分钟前</p>							
							</div>
							<div class="contain1">
								<p class="contain1-point"><img src="<%=path%>/img/list3.png"/></p>
								<p class="contain1-article">习近平主持中俄蒙元首第四次会晤</p>
								<p class="contain1-time">2分钟前</p>							
							</div>
							<!--<div class="contain1">
								<p class="contain1-point"><img src="<%=path%>/img/list1.png"/></p>
								<p class="contain1-article">上合青岛峰会日程公布:10日上午将举行正式会谈正式会谈正式会谈正式会谈正式会谈正式会谈正式会谈</p>
								<p class="contain1-name">刘晓红</p>
								<p class="contain1-time">2018-06-09 23:07:56</p>					
							</div>-->
						</div>
						
					
						
					</div>
				
				</div>
				<!--我的待阅-->
				<div class="detail3">
					<div class="detail1-top">
						<div class="top-name">我的待阅</div>
						<ul class="top-change">
							<li><span  class="active">全部&nbsp;<span class="badge" style="background-color:rgb(2,141,241)">11</span></span></li>
							<li><span>客户管理<span class="badge pull-right">6</span></span></li>
							<li><span>业务管理<span class="badge pull-right">1</span></span></li>
							<li><span>财务管理<span class="badge pull-right">2</span></span></li>
							<li><span>综合管理<span class="badge pull-right">0</span></span></li>
						</ul>
						<div class="top-audio">
							<img src="<%=path%>/img/play.png"/>
						</div>
					</div>
					<div class="detail1-contain">
						<!--div1-->
						<!--全部对应内容-->
						<div class="active">
							<div class="contain1">
								<p class="contain1-point"><img src="<%=path%>/img/list1.png"/></p>
								<p class="contain1-article">上合青岛峰会日程公布:10日上午将举行正式会谈正式会谈正式会谈正式会谈正式会谈正式会谈正式会谈</p>
								<p class="contain1-name">刘晓红</p>
								<p class="contain1-time">2018-06-09 23:07:56</p>					
							</div>
							<div class="contain1">
								<p class="contain1-point"><img src="<%=path%>/img/list1.png"/></p>
								<p class="contain1-article">上合青岛峰会日程公布:10日上午将举行正式会谈正式会谈正式会谈正式会谈正式会谈正式会谈正式会谈</p>
								<p class="contain1-name">刘晓红</p>
								<p class="contain1-time">2018-06-09 23:07:56</p>					
							</div>
							<div class="contain1">
								<p class="contain1-point"><img src="<%=path%>/img/list2.png"/></p>
								<p class="contain1-article">上合青岛峰会日程公布:10日上午将举行正式</p>
								<p class="contain1-name">刘晓红</p>
								<p class="contain1-time">2018-06-09 23:07:56</p>					
							</div>
							<div class="contain1">
								<p class="contain1-point"><img src="<%=path%>/img/list2.png"/></p>
								<p class="contain1-article">上合青岛峰会日程公布:10日上午将举行正式会谈正式会谈正式会谈正式会谈正式会谈正式会谈正式会谈</p>
								<p class="contain1-name">刘晓红</p>
								<p class="contain1-time">2018-06-09 23:07:56</p>					
							</div>
							<div class="contain1">
								<p class="contain1-point"><img src="<%=path%>/img/list1.png"/></p>
								<p class="contain1-article">上合青岛峰会日程公布:10日上午将举行正式</p>
								<p class="contain1-name">刘晓红</p>
								<p class="contain1-time">2018-06-09 23:07:56</p>					
							</div>
							<!--<div class="contain1">
								<p class="contain1-point"><img src="<%=path%>/img/list1.png"/></p>
								<p class="contain1-article">上合青岛峰会日程公布:10日上午将举行正式会谈正式会谈正式会谈正式会谈正式会谈正式会谈正式会谈</p>
								<p class="contain1-name">刘晓红</p>
								<p class="contain1-time">2018-06-09 23:07:56</p>					
							</div>-->
						</div>
						
							<!--<div class="contain1">
								<p class="contain1-point"><img src="<%=path%>/img/list1.png"/></p>
								<p class="contain1-article">上合青岛峰会日程公布:10日上午将举行正式会谈正式会谈正式会谈正式会谈正式会谈正式会谈正式会谈</p>
								<p class="contain1-name">刘晓红</p>
								<p class="contain1-time">2018-06-09 23:07:56</p>					
							</div>-->
						</div>
				</div>
				<!--常用模块-->
				<div class="detail4">
					<div class="detail4-top">
						<p class="top-name">常用模块</p>
						<p class="top-img"><img src="<%=path%>/img/play.png"/></p>
					</div>
					<div class="detail4-contain">
						<ul class="contain-lists" id="hh">
							<li>
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
							</li>
						</ul>
					</div>
				</div>
				
				
			</div>
		</div>
	</body>
	
	<script src="<%=ContextPath%>/js/public.js"></script>
	<script src="<%=ContextPath%>/js/bootTable.js"></script>
	<script src="<%=ContextPath%>/js/initPage.js"></script>
	<script src="<%=ContextPath%>/js/bulidPlistPage.js"></script>
	<script type="text/javascript">
		 //系统管理
		 var visit_context ='<%=workFlowContext%>';
		 var work_flow_context='<%=workFlowContext%>';
		 var token ='<%=tokenEntity.getToken()%>';
		 var showTotalCode ="";
		 var $lastShow;
		
		 $(function() { 
            	var company_id ='<%=tokenEntity.COMPANY.getCompany_id()%>';
            	var companyname='<%=tokenEntity.COMPANY.getCompany_name()%>';
           		initMenu(company_id,companyname);
           		bulidPlist('needHandle',$("#notity_need_dealt"),'');
           		initNeedHandle();
           		flushCustMenu();
           		
         }); 
		
		
		function initNeedHandle(){
			bulidPlist('needHandle',$("#notity_need_dealt"),'');
		}
		
		
		function openMenu(t){
			var totalcode=$(t).attr("totalcode");
			var totalname=$(t).attr("totalname");
			var mywin=window.open('<%=ThemePath.findRoute(request)%>menuPage.jsp?token=<%=tokenEntity.getToken()%>&totalcode='+totalcode+'&totalname='+totalname); //将新打的窗口对象，存储在变量mywin中
		}
		
		function initMenu(companyid,companyname){
	        	loadingMenu();
	        	var paramJson = {
           		 'companyid':companyid,
            	 'companyname':companyname
        		};
	        	$.ajax({  
	                    url : "/portal/base?cmd=findMenu&type=1&token="+'<%=tokenEntity.getToken()%>'+'&ts='+new Date().getTime(),  
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
	                    	
		                    	$("[data-toggle='popover']").on("mouseover mouseout",function(event){
									 if(event.type == "mouseover"){
									  //鼠标悬浮
										$(this).popover("show");
									 }else if(event.type == "mouseout"){
									   //鼠标离开
									    $(this).popover("hide");
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
			        error:function(){
			        	alert("error")
			        }  
		     	});  
	     }
	     
	     
	     function loadingMenu(){
	        	$("#First_level_Menu").html("<li><font color='white'>loading...</font><li>");
	        }
	</script>
</html>
