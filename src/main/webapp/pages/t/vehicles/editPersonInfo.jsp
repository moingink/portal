<%@page import="com.yonyou.business.entity.TokenEntity"%>
<%@page import="com.yonyou.business.entity.TokenUtil"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改个人信息</title>

<script src="/portal/vendor/jquery/jquery-1.11.2.min.js"></script>
<script src="/portal/pages/js/bootstrap/js/bootstrap.min.js"></script>
<script src="/portal/pages/tree/jstree.min.js"></script>
<script src="/portal/pages/js/tinyselect.js"></script>
<link rel="stylesheet" href="/portal/pages/js/tinyselect.css">
<link rel="stylesheet" type="text/css" href="/portal/css/style.css">
<link rel="stylesheet prefetch"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="/portal/pages/js/bootstrap-table/src/bootstrap-table.css">
<link rel="stylesheet" href="/portal/pages/js/examples.css">
<link rel="stylesheet"
	href="/portal/pages/tree/themes/default/style.min.css">
<script src="/portal/vendor/bootstrap/js/html5.js"></script>
<script src="/portal/vendor/bootstrap/js/respond.js"></script>
<link rel="stylesheet" type="text/css"
	href="/portal/vendor/vehicles/css/resert.css">
<link rel="stylesheet" type="text/css"
	href="/portal/vendor/vehicles/css/header.css">
<link rel="stylesheet" type="text/css"
	href="/portal/vendor/vehicles/css/customer.css">
	
<%
	TokenEntity tokenEntity = TokenUtil.initTokenEntity(request);
	String loginName = tokenEntity.USER.getName();
	String corpName = tokenEntity.COMPANY.getCompany_name();
	String token = (String) request.getParameter("token");
	String corpId = tokenEntity.COMPANY.getCompany_id();
	String userId = tokenEntity.USER.getId();
%>
<style type="text/css">
	body{
		background: white;
	}
</style>
</head>
<body>
<form id="form">

		<div class="tabbable" id="tabs-146746">
				<div class="tab-content">
					<div class="tab-pane active" id="panel-847015" style="width:300px;margin-left: auto;margin-right: auto;">
							<input type="hidden" name="userId" id="userId" value="<%= userId%>"/>
							用户名：<%= loginName%><br/>
							邮箱：<input type="text" id="email" name="email" value=""  maxlength="30"   /><br/>
							手机号：<input type="text" id="phone" name="phone" value=""  maxlength="30"   /><br/>
							<button type="button" class="btn btn-success" onclick="editPersonInfo();">
							<span class="glyphicon glyphicon-saved" aria-hidden="true"></span>
							确认</button>
							<button type="button" class="btn btn-inverse"  onclick="$('.close',window.parent.document).click();">
							<span class="glyphicon glyphicon-ban-circle" aria-hidden="true"></span>
							关闭</button>
					</div>
				</div>
			</div>
		</form>
		  
    <script type="text/javascript">
         $(function() {
         $('.close',window.parent.document).hide();
			$.ajax({
				type : "POST",
				url :  "/portal/passWord?cmd=queryPersonInfo",
			    contentType: false,
				data:"{\"userId\":\"<%= userId%>\"}",
				async : false,
				cache : false,
				processData : false,
				success : function(data) {
					if(data!=null&&data.state=="success"){
						$("#email").val(data.data.EMAIL);
						$("#phone").val(data.data.CUSTOM3);
					}else if(data!=null&&data.msg!=null){
						alert(data.msg);
					}else{
						alert("个人信息查询出错，请联系管理员");
					}
				},
				error : function(data) {
					alert("个人信息查询出错，请联系管理员");
				}
			});
        });
        
function editPersonInfo(){
	if($("#userId").val()==null||$("#userId").val().length==0){
		alert("用户信息查询失败，请联系管理员");
		return;
	}
	if($("#email").val()==null||$("#email").val().length==0){
		alert("请填写邮箱");
		return;
	}
	if($("#phone").val()==null||$("#phone").val().length==0){
		alert("请填写手机号");
		return;
	}
	var param={};
	$("#form").find("input").each(function(){
		param[$(this).attr("name")	]= $(this).val();
	});
	param=JSON.stringify(param);
	$.ajax({
			type : "POST",
			url :  "/portal/passWord?cmd=editPersonInfo",
		    contentType: false,
			data:param,
			async : false,
			cache : false,
			processData : false,
			success : function(data) {
				if(data!=null&&data.state=="success"){
					alert("修改成功");
					$('.close',window.parent.document).click();
				}else if(data!=null&&data.msg!=null){
					alert(data.msg);
				}else{
					alert("修改密码出错，请联系管理员");
				}
			},
			error : function(data) {
				alert("修改密码出错，请联系管理员");
			}
		});
}
    </script>  
</body>
</html>