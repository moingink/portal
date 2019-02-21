<%@page import="com.yonyou.business.entity.TokenEntity"%>
<%@page import="com.yonyou.business.entity.TokenUtil"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String ContextPath =request.getContextPath();
	
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>找回密码</title>

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
<link rel="stylesheet" type="text/css" href="<%=ContextPath %>/vendor/vehicles/css/login.css"/>
<style type="text/css">
	body{
		background: none;
	}
	html{
		background: none;
	}
	.name-input{
		color:black;
	}
</style>
</head>
<body >
<form id="form">

		<div class="tabbable" id="tabs-146746">
				<div class="tab-content">
					<div class="login-info" id="panel-847015" style="width:400px;margin-left: auto;margin-right: auto;margin-top: 100px;">
						<div class="login-list" style="margin-left: auto;  margin-right: auto;">
							<p id="name-num">用户名</p>
							<p class="name-input" style="border:0;" id="num-input"><input type="text" placeholder="请输入用户名" style="border: 1px solid #8690A3;" id="loginID" name="loginID" maxlength="50" value="<%= request.getParameter("loginId") %>" disabled="disabled" ></p>
						</div> 
						<div class="login-list" style="margin-left: auto;  margin-right: auto;">
							<p id="name-num">邮箱</p>
							<p class="name-input" style="border:0;" id="num-input"><input type="text" placeholder="请输入邮箱" style="border: 1px solid #8690A3;" id="email" name="email" maxlength="50" value="<%= request.getParameter("email") %>" disabled="disabled" ></p>
						</div> 
						<div class="login-list" style="margin-left: auto;  margin-right: auto;">
							<p id="name-num">验证码</p>
							<p class="name-input" style="border:0;" id="num-input"><input type="text" placeholder="请输入验证码" style="border: 1px solid #8690A3;" id="code" name="code" maxlength="30" value=""></p>
						</div> 
						<div class="login-list" style="margin-left: auto;  margin-right: auto;">
							<p id="name-num">新密码</p>
							<p class="name-input" style="border:0;" id="num-input"><input type="password" placeholder="请输入新密码" style="border: 1px solid #8690A3;" id="newPWD" name="newPWD" maxlength="30" value=""></p>
						</div> 
						<div class="login-list" style="margin-left: auto;  margin-right: auto;">
							<p id="name-num">重输密码</p>
							<p class="name-input" style="border:0;" id="num-input"><input type="password" placeholder="请重输新密码" style="border: 1px solid #8690A3;" id="repeatNewPWD" name="repeatNewPWD" maxlength="30" value=""></p>
						</div> 
						<div class="login-list" style="margin-left: auto;  margin-right: auto;">
							<div class="login-btn"  onclick="retrievePWD();">
								<p style="color: #fff; width: 360px;">确定</p>
							</div>
						</div> 
						</div>
					</div>
				</div>
			</div>
		</form>
		  
    <script type="text/javascript">
         $(function() {
        });
        
function retrievePWD(){
	if($("#email").val()==null||$("#email").val().length==0){
		alert("邮箱错误，请联系管理员");
		return;
	}
	if($("#code").val()==null||$("#code").val().length==0){
		alert("请填写验证码");
		return;
	}
	if($("#code").val().length!=4){
		alert("验证码长度错误");
		return;
	}
	if($("#newPWD").val()==null||$("#newPWD").val().length==0){
		alert("请填写新密码");
		return;
	}
	if($("#repeatNewPWD").val()==null||$("#repeatNewPWD").val().length==0){
		alert("请重复填写新密码");
		return;
	}
	var pwdVerifyResult=verifyPWD($("#newPWD").val());
	if(!pwdVerifyResult){
		return;
	}
	if($("#repeatNewPWD").val()!=$("#newPWD").val()){
		alert("重输密码与新密码不一致");
		return;
	}
	
	var param={};
	$("#form").find("input").each(function(){
		param[$(this).attr("name")	]= $(this).val();
	});
	param=JSON.stringify(param);
	$.ajax({
			type : "POST",
			url :  "/portal/passWord?cmd=retrievePWD",
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
function verifyPWD(newPwd){
	var num = 0;
    var rule1 = /\d+/;
    var rule2 = /[a-z]+/;
    var rule3 = /[A-Z]+/;
    var rule4 = /[~!@#\$%^&*\{\};,.\?\/'"]/;
    var rule5 = /^.{8,16}$/;
    var flag1 = rule1.test(newPwd);
    var flag2 = rule2.test(newPwd);
    var flag3 = rule3.test(newPwd);
    var flag4 = rule4.test(newPwd);

    var flag5 = rule5.test(newPwd);
    if (flag1){
		num = num + 1;
    }
    if (flag2){
    	num = num + 1;
    }
    if (flag3){
    	num = num + 1;
    }
    if (flag4){
		num = num + 1;
    }

    if(!(num >2&&flag5)){
		alert('密码必须是8-16位数字、大、小写字母、特殊符号中的三种及三种以上的组合！');
		return false;
	}
	return true;
}
    </script>  
</body>
</html>