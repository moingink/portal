<%@page import="java.util.Date"%>
<%@page import="com.yonyou.util.theme.ThemePath"%>
<%@page import="com.yonyou.business.entity.TokenEntity"%>
<%@page import="com.yonyou.business.entity.TokenUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	TokenEntity tokenEntity = TokenUtil.initTokenEntity(request);
	String token = (String) request.getParameter("token");
	String ContextPath = request.getContextPath();
	String pages="/pages/";
	String pagePath =ThemePath.findPath(request, ThemePath.PORTAL_MAIN).replace(ContextPath+pages, "");
	pagePath=pagePath+"?token="+token+"&ts="+(new Date()).getTime();
%>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<META HTTP-EQUIV="pragma" CONTENT="no-cache"> 
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate"> 
<META HTTP-EQUIV="expires" CONTENT="0">
<title>用友广信资金管理系统</title>
<!-- 设置浏览器默认渲染模式 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="renderer" content="webkit">
<script src="<%=ContextPath%>/vendor/jquery/jquery-1.11.2.min.js"></script>
</head>

<body >
		
<jsp:forward page="<%=pagePath %>"/> 
		
</body>
</html>