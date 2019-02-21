<!DOCTYPE html>
<%@page import="com.yonyou.util.theme.ThemePath"%>
<%@page import="com.yonyou.business.entity.TokenEntity"%>
<%@page import="com.yonyou.business.entity.TokenUtil"%>
<%@page import="com.yonyou.util.wsystem.service.ORG"%>
<%@page import="java.util.Date"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", -10); 
	TokenEntity tokenEntity = TokenUtil.initTokenEntity(request);
	String ContextPath = request.getContextPath();
	String totalname=(String)request.getParameter("totalname");
	String totalcode=(String)request.getParameter("totalcode");
	String path=ContextPath+"/vendor/vehicles";
%>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title><%= totalname%>_菜单信息</title>
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
<link rel="stylesheet" href="<%=ContextPath%>/pages/js/examples.css">
<link rel="stylesheet"
	href="<%=ContextPath%>/pages/tree/themes/default/style.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=ContextPath%>/js/bootTable.js">
<!-- bootstrapIE8补丁 -->
<script src="<%=ContextPath%>/vendor/bootstrap/js/html5.js"></script>
<script src="<%=ContextPath%>/vendor/bootstrap/js/respond.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= path%>/css/resert.css"/>
	<link rel="stylesheet" type="text/css" href="<%= path%>/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="<%= path%>/css/customer.css"/>
	<body>
		<div class="contain">
			<!--头部nav导航栏-->
			<jsp:include page="headPage.jsp?isShow=1"></jsp:include>
			<!--中间内容部分-->
			<div id ='c' class="box">
				<!--客户CRM-->
				<ol class="breadcrumb">
				  <li></li>
				  <li class="active"><%= totalname%></li>
				</ol>
				<div id="nodePage">
				</div>
				
			</div>
		</div>
	</body>
	
	<script type="text/javascript">
		
		$(function() { 
            	initNode();
            	if(isShowHeadImg==0){
            		$("#c").css("margin-top","1px");
            	}
         }); 
		
		var showTotalCode ="";
		var $lastShow;
		function initNode(){
	        
	        	$.ajax({  
	                    url : "/portal/base?cmd=findNode&type=1&token="+'<%=tokenEntity.getToken()%>'+'&totalCode='+'<%=totalcode%>'+'&ts='+new Date().getTime(),  
	                    dataType : "json",  
	                    type : "post",  
	                    success : function(data) { 
	                    	message = data['message'];
	                    	$("#nodePage").html(message);
	                    	
	                    	$(function () { 
	                    	
		                    	$("[data-toggle='popover']").popover({
		                    					 html : true ,
		                    			         animation:true,placement: 'right',
		                    					 delay:{ show: 200, hide: 100 }
		                    	                 });
	                    	
		                    	$("img").on("mouseover mouseout",function(event){
									 if(event.type == "mouseover"){
									  //鼠标悬浮
									    var isPopover =$(this).attr("isPopover");
										  if(isPopover==1){
											  var showPopCode=$(this).parent().parent().attr("totalcode");
											  if(showTotalCode==""){
											  	  $(this).popover("show");
											  }else if(showTotalCode==showPopCode){
											  	  $(this).popover("toggle");
											  }else {
											  	  $(this).popover("show");
											  	  $lastShow.popover("hide");
											  }
											  showTotalCode=showPopCode;
										  	  $lastShow=$(this);
										  }else{
										  	  if($lastShow!=null){
										  	  	$lastShow.popover("hide");
										  	  }
										   }
									 }else if(event.type == "mouseout"){
									   //鼠标离开
									   //$(this).popover("hide");
									 }
								});
							})
	                    } ,
	                    error:function(XMLHttpRequest, textStatus, errorThrown){
							message ="访问超时！";
							alert(message);
						}
	             });  
	             
	     }
	     
	     
	     function changNode(t){
			var url=$(t).attr("url");
			var totalcode=$(t).attr('totalcode');
			var totalname=$(t).attr('totalname');
			var parentcode=$(t).attr('parentcode');
			var parentname=$('span[totalcode='+parentcode+']').attr("totalname");
			var tns='<%=totalname%>'+","+parentname+","+totalname;
			var isPopover =$(t).attr("isPopover");
			if(isPopover!="1"){
				openUrlByMenuPage(url,tns);
			}
			
		}
		
		function changNodeFour(t){
			var url=$(t).attr("url");
			var tc=$(t).attr('totalcode');
			var tn=$(t).attr('totalname');
			var ptc=$(t).attr('parentcode');
			var ptn=$('li[totalcode='+ptc+']').attr("totalname");
			var pptc=$('li[totalcode='+ptc+']').attr("parentcode");
			var pptn=$('span[totalcode='+pptc+']').attr("totalname");
			var tns='<%=totalname%>'+","+pptn+","+ptn+","+tn;
			openUrlByMenuPage(url,tns)
		}
		
		function openUrlByMenuPage(url,tns){
			if(url!=''){
					url=escape(url);
					tns=escape(tns);
					var mywin=window.open('<%=ThemePath.findRoute(request)%>busPage.jsp?token=<%=tokenEntity.getToken()%>&tns='+tns+'&b='+url); //将新打的窗口对象，存储在变量mywin中
			}else{
					alert("没有配置访问路径！");
			}
		}
	
	
	</script>
	
	
</html>
