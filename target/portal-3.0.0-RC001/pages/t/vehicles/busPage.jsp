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
	String busUrl =request.getParameter("b");
	String tns=request.getParameter("tns");
	String[] tnsArray =tns.split(",");
	if(tnsArray.length<3){
		tnsArray=new String[]{"","",""};
	}
	String mark="?";
	if(busUrl.indexOf("?")!=-1){
		mark="&";
	}
	busUrl=busUrl+mark+"token="+tokenEntity.getToken();
	String path=ContextPath+"/vendor/vehicles";
%>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title><%=tnsArray[tnsArray.length-1] %></title>
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
<link rel="stylesheet" type="text/css" href="<%=path%>/css/resert.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/header.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/index.css"/>
<link rel="stylesheet" type="text/css" href="<%=ContextPath%>/css/style.css"/>
	<body>
		
		
	
	<div id="wrapper">
	<div class="overlay"></div>

	<!-- Sidebar -->
		<nav class="navbar navbar-inverse navbar-fixed-top"
			id="sidebar-wrapper" role="navigation">
			<ul class="nav sidebar-nav">
				<li class="sidebar-brand"><a href="#"> Bootstrap 3 </a></li>
				<li><a href="#"><i class="fa fa-fw fa-home"></i> Home</a></li>
				<li><a href="#"><i class="fa fa-fw fa-folder"></i> Page one</a>
				</li>
				<li><a href="#"><i class="fa fa-fw fa-file-o"></i> Second
						page</a></li>
				<li><a href="#"><i class="fa fa-fw fa-cog"></i> Third page</a>
				</li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><i class="fa fa-fw fa-plus"></i>
						Dropdown <span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li class="dropdown-header">Dropdown heading</li>
						<li><a href="#">Action</a></li>
						<li><a href="#">Another action</a></li>
						<li><a href="#">Something else here</a></li>
						<li><a href="#">Separated link</a></li>
						<li><a href="#">One more separated link</a></li>
					</ul></li>
				<li><a href="#"><i class="fa fa-fw fa-bank"></i> Page four</a>
				</li>
				<li><a href="#"><i class="fa fa-fw fa-dropbox"></i> Page 5</a>
				</li>
				<li><a href="#"><i class="fa fa-fw fa-twitter"></i> Last
						page</a></li>
			</ul>
		</nav>
		<!-- /#sidebar-wrapper -->

	<!-- Page Content -->
	<div id="page-content-wrapper">
	  <button type="button" class="hamburger is-closed animated fadeInLeft" data-toggle="offcanvas">
		<span class="hamb-top"></span>
		<span class="hamb-middle"></span>
		<span class="hamb-bottom"></span>
	  </button>
			<div class="contain">
			<jsp:include page="headPage.jsp?isShow=1"></jsp:include>
			<div class="box-top" >
				<ol class="breadcrumb">
				  <li></li>
				  <% for(String title:tnsArray){ %>
				  	 <li><%= title%></li>
				  <% } %>
				</ol>
				<iframe id='busPage' src="<%=busUrl%>" width="100%"  scrolling="no" frameborder="0"  height='600px'></iframe>
			</div>
			</div>
	</div>
	<!-- /#page-content-wrapper -->

</div>	
	
		
	</body>
	<script src="js/commen.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		$(function(){
					var trigger = $('.hamburger'),
				  overlay = $('.overlay'),
				 isClosed = false;
		
				trigger.click(function () {
				  hamburger_cross();     
				  if(isClosed){
				  	$(".contain").width("90%");
				  }else{
				  	$(".contain").width("100%");
				  }
				});
		
				function hamburger_cross() {
		
				  if (isClosed == true) {          
					overlay.hide();
					trigger.removeClass('is-open');
					trigger.addClass('is-closed');
					isClosed = false;
				  } else {   
					overlay.show();
					trigger.removeClass('is-closed');
					trigger.addClass('is-open');
					isClosed = true;
				  }
			  }
			  
			  $('[data-toggle="offcanvas"]').click(function () {
					$('#wrapper').toggleClass('toggled');
			  }); 
		});
		function setHeigth(height){
			$("#busPage").attr("height",height);
		}
	</script>
</html>
