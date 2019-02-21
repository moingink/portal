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
							<p class="name-input" style="border:0;" id="num-input"><input type="text" placeholder="请输入用户名" style="border: 1px solid #8690A3;" id="loginId" name="loginId" maxlength="50" value=""></p>
						</div> 
						<div class="login-list" style="margin-left: auto;  margin-right: auto;">
							<p id="name-num">邮箱</p>
							<p class="name-input" style="border:0;" id="num-input"><input type="text" placeholder="请输入邮箱" style="border: 1px solid #8690A3;" id="email" name="email" maxlength="50" value=""></p>
						</div> 
						<div class="login-list" style="margin-left: auto;  margin-right: auto;">
							<p id="name-num"><img src="/portal/login/imageCode" id="image" style="width: 100%;height: 100%;" /></p>
							
							<p class="name-input" style="border:0;" id="num-input"><input type="text" placeholder="请输入验证码" style="border: 1px solid #8690A3;" id="imageCode" name="imageCode" maxlength="30" value=""></p>
						</div> 
						<!-- <div class="login-list" style="margin-left: auto;  margin-right: auto;">
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
						</div>  -->
						<div class="login-list" style="margin-left: auto;  margin-right: auto;">
							<div class="login-btn"  onclick="sendCode();">
								<p style="color: #fff; width: 360px;">发送验证码</p>
							</div>
						</div> 
						</div>
					</div>
				</div>
			</div>
		</form>
		  
    <script type="text/javascript">
         $(function() {
	         $("#image").click(function(){
	       		var url=$(this).attr('src')+'?random='+Math.random();
	         	$(this).attr("src",url);
	         });
        });
        
function sendCode(){
	if($("#loginId").val()==null||$("#loginId").val().length==0){
		alert("请填写用户名");
		return;
	}
	if($("#email").val()==null||$("#email").val().length==0){
		alert("请填写邮箱");
		return;
	}
 	if (/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test($("#email").val()) == false) {
		alert("邮箱格式不正确");
		return ;
     }
	if($("#imageCode").val()==null||$("#imageCode").val().length==0){
		alert("请填写图片验证码");
		return;
	}
	if($("#imageCode").val().length!=4){
		alert("图片验证码长度错误");
		return;
	}
	
	var param={};
	$("#form").find("input").each(function(){
		param[$(this).attr("name")	]= $(this).val();
	});
	param=JSON.stringify(param);
	$.ajax({
			type : "POST",
			url :  "/portal/passWord?cmd=sendEmailCode",
		    contentType: false,
			data:param,
			async : false,
			cache : false,
			processData : false,
			success : function(data) {
				if(data!=null&&data.state=="success"){
					alert("发送成功");
					window.location.href = "/portal/passWord/goRetrievePWD?email="+$("#email").val()+"&loginId="+$("#loginId").val();
					return;
				}else if(data!=null&&data.msg!=null){
					alert(data.msg);
				}else{
					alert("发送出错，请联系管理员");
				}
				$("#image").click();
			},
			error : function(data) {
				alert("发送出错，请联系管理员");
			}
		});
}
    </script>  
</body>
</html>