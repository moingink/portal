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
	String loginName = tokenEntity.USER.getName();
	String corpName = tokenEntity.COMPANY.getCompany_name();
	String token = (String) request.getParameter("token");
	String corpId = tokenEntity.COMPANY.getCompany_id();
	String userId = tokenEntity.USER.getId();
	String selecthtml = tokenEntity.COMPANY.getSelectCompanyHtml();
	String ContextPath = request.getContextPath();
	
	String path=ContextPath+"/vendor/vehicles";
	String isShowHeadImg = request.getParameter("isShow");
	isShowHeadImg="1".equals(isShowHeadImg)?"1":"0";
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
	
	<body>
			<!--头部nav导航栏-->
			<nav>
				<div class="contain-fl">
					<img src="<%=path%>/img/logo.png"/>
				</div>
				<div class="contain-fr">
					<span class="nav-name"><%= corpName%></span>
					<span class="nav-customer"><%= loginName%></span>
					<!-- <span class="nav-img"></span> -->
					<span class="nav-set"><img src="<%=path%>/img/set.png"/></span>
					<span class="nav-close" onclick='closeBus(this)'><img src="<%=path%>/img/close.png" /></span>
				</div>				
			</nav>
			<!--顶部背景图片-->
			<div class="top-background" >
				<img src="<%=path%>/img/banners.png"/>
			</div>
				
	</body>
	<script type="text/javascript">
		
		var isShowHeadImg ='<%=isShowHeadImg%>';
		
		$(function() {
			if(isShowHeadImg=='0'){
				$("div .top-background").hide();
			};
		});
		
		function closeBus(t){
			if(confirm("是否退出系统！")){
				window.location.href ="<%=request.getContextPath()%>/login/signOut?token=<%=token%>";
			}
		};
	</script>
</html>
