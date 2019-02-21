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
<link rel="stylesheet" type="text/css" href="<%=ContextPath%>/css/style.css"/>
<link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>
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
		
		<div id="wrapper">
		<!-- <div class="overlay"></div> -->

		<!-- Sidebar -->
		<nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
		<ul class="nav sidebar-nav" id="nodePage">
			
		</ul>
	</nav>
		<!-- /#sidebar-wrapper -->

		<!-- Page Content -->
		<div id="page-content-wrapper">
			<button type="button" class="hamburger is-closed animated fadeInLeft"
				data-toggle="offcanvas">
				<span class="hamb-top"></span> <span class="hamb-middle"></span> <span
					class="hamb-bottom"></span>
			</button>
			<div class="contain">
				<jsp:include page="headPage.jsp?isShow=0"></jsp:include>
				<div class="box-top2">
					<ol class="breadcrumb" style="margin-bottom: 10px" id ="ol_title">
					  <li></li>
					  <li ><%= totalname%></li>
					</ol>
					<iframe id='busPage' src="<%=ContextPath%>/pages/t/vehicles/index.jsp" width="100%" border="0" marginwidth="0" marginheight="0"   scrolling="no" frameborder="0"  height='600px'></iframe>
				</div>
			</div>
		</div>
		<!-- /#page-content-wrapper -->

	</div>
	</body>
	
	<script type="text/javascript">
		
		$(function() { 
            	initNode();
            	initTrigger();
            	if(isShowHeadImg==0){
            		$(".box-top2").css("margin-top","30px");
            	}
         }); 
         
		
		var showTotalCode ="";
		var $lastShow;
		//nodePage
		function initNode(){
	        
	        	$.ajax({  
	                    url : "/portal/base?cmd=findNode&type=1&token="+'<%=tokenEntity.getToken()%>'+'&totalCode='+'<%=totalcode%>'+'&ts='+new Date().getTime(),  
	                    dataType : "json",  
	                    type : "post",  
	                    success : function(data) { 
	                    	message = data['message'];
	                        var html_='<li class="sidebar-brand"><a href="#"><%= totalname%> </a></li>';
	                    	$("#nodePage").html(html_+message);
	                    	
	                    } ,
	                    error:function(XMLHttpRequest, textStatus, errorThrown){
							message ="访问超时！";
							alert(message);
						}
	             });  
	             
	     }
	     
	     function changNodeForB(t){
			var totalcode=$(t).attr('totalcode');
			onChangHead(totalcode);
	     	if($("#"+totalcode)!=''&&$("#"+totalcode)!=undefined){
	     		$("#"+totalcode).toggle();
	     	}else{
	     		changNode(t);
	     	}
	     }
	     
	     function onChangHead(totalCode){
	     	var $t =$('a[totalcode='+totalCode+']');
	     	$(".act_t1").removeClass("act_t1");
			$t.addClass("act_t1");
	     }
	     
	     
	     function changNode(t){
			var url=$(t).attr("url");
			var totalcode=$(t).attr('totalcode');
			var totalname=$(t).attr('totalname');
			var parentcode=$(t).attr('parentcode');
			var parentname=$('a[totalcode='+parentcode+']').attr("totalname");
			var tnsArrays=[];
			tnsArrays[0]='<%=totalname%>';
			tnsArrays[1]=parentname;
			tnsArrays[2]=totalname;
			var isPopover =$(t).attr("isPopover");
			if(isPopover!="1"){
				openUrlByMenuPage(t,tnsArrays);
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
			var tnsArrays=[];
			tnsArrays[0]='<%=totalname%>';
			tnsArrays[1]=pptn;
			tnsArrays[2]=ptn;
			tnsArrays[3]=tn;
			openUrlByMenuPage(url,tnsArrays);
		}
		
		function openUrlByMenuPage(t,tns){
			var url=$(t).attr("url");
			var totalcode=$(t).attr('totalcode');
			if(url!=null&&url.length>0){
					$(".act_t2").removeClass("act_t2");
					$(t).attr("class","act_t2");
					var mark="?";
	        		if(url.indexOf("?")!=-1){
	        			mark="&";
	        		}
        			url=url+mark+"token="+'<%=tokenEntity.getToken()%>'+'&totalCode='+totalcode;
					$("#busPage").attr("src",url);
					appendOlTitle(tns);
					onChangHead($(t).attr('parentcode'));
			}else{
					alert("没有配置访问路径！");
			}
		}
		
		function initTrigger(){
				var trigger = $('.hamburger');
				//var overlay = $('.overlay');
				//设置默认为开
				isClosed = true;
				//打开菜单
				defOpen(trigger);
				
				trigger.click(function () {
				  hamburger_cross();     
				});
		
				function hamburger_cross() {
				  if (isClosed == true) {          
					//overlay.hide();
					trigger.removeClass('is-open');
					trigger.addClass('is-closed');
					isClosed = false;
					$(".contain").width("100%");
				  } else {   
					//overlay.show();
					trigger.removeClass('is-closed');
					trigger.addClass('is-open');
					isClosed = true;
					$(".contain").width("87%");
				  }
			  }
			  
			  $('[data-toggle="offcanvas"]').click(function () {
					$('#wrapper').toggleClass('toggled');
			  }); 
		}
		
		function setHeigth(height){
			$("#busPage").attr("height",height+30);
		}
		
		function appendOlTitle(tns){
			$("#ol_title").html("");
			var html ="<li></li>";
			for(var i=0;i<tns.length;i++){
				if(tns[i]!=null){
					html+="<li>"+tns[i]+"</li>";
				}
			}
			$("#ol_title").html(html);
		}
		
		function defOpen(trigger){
			$('#wrapper').toggleClass('toggled');
			trigger.removeClass('is-closed');
			trigger.addClass('is-open');
			$(".contain").width("88%");
		}
	
	</script>
	
	<style type="text/css">
.dropdown-menu {
	position: absolute;
	top: 100%;
	left: 0;
	z-index: 1000;
	display: none;
	float: left;
	min-width: 160px;
	padding: 5px 0;
	margin: 2px 0 0;
	font-size: 12px;
	text-align: left;
	list-style: none;
	background-color: #eee;
	-webkit-background-clip: padding-box;
	background-clip: padding-box;
	border: 1px solid #ccc;
	border: 1px solid rgba(0, 0, 0, .15);
	border-radius: 4px;
	-webkit-box-shadow: 0 6px 12px rgba(0, 0, 0, .175);
	box-shadow: 0 6px 10px rgba(0, 0, 0, .175)
}

.dropdown-menu.pull-right {
	right: 0;
	left: auto
}

.dropdown-menu .divider {
	height: 1px;
	margin: 9px 0;
	overflow: hidden;
	background-color: rgb(144,144,145)
}

.dropdown-menu>li>a {
	display: block;
	padding: 3px 20px;
	clear: both;
	font-weight: 400;
	line-height: 1.42857143;
	color: rgb(240,240,241);
	white-space: nowrap
}

.dropdown-menu>li>a:hover, .dropdown-menu>li>a:focus {
	color:#FFFCF8;
	text-decoration: none;
	background-color: #1798FE
}


.sidebar-nav>li>a:hover, .sidebar-nav>li>a:focus {
	color: rgb(240,240,241);
	text-decoration: none;
	background-color:#1798FE
}

.dropdown-menu>.active>a, .dropdown-menu>.active>a:hover, .dropdown-menu>.active>a:focus
	{
	color: rgb(94,83,160);
	text-decoration: none;
	background-color: #428bca;
	outline: 0
}

.act_t1,.act_t2{
  background-color: #1798FE;
  color:#FFFCF8;
  text-decoration: none;
}
/*样式添加修改  */

@media  screen and  (min-width: 1200px)
{
	.navbar{font-size:14px}
	.dropdown-menu a{font-size:13px}
}
@media  screen and  (min-width: 1370px)
{
	.navbar{font-size:16px}
	.dropdown-menu a{font-size:15px}
}
@media  screen and  (max-width: 1199px)
{
	.navbar{font-size:13px}
	.dropdown-menu a{font-size:12px}
}

li a b{
	float:left;
	width:88%
	
}
 li a span{
	float: left;
    width: 0;
    height: 0;
    border-width: 6px;
    border-style: solid;
    border-color: #fff transparent transparent transparent;
    margin-top: 6px;
	
} 
/* li a span {
	float: left;
    width: 0;
    height: 0;
    border-width: 6px;
    border-style: solid;
    border-color: transparent transparent #fff  transparent;
    margin-top:0px;
} */
</style>
	
	
</html>
