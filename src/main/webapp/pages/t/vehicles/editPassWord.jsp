<%@page import="com.yonyou.business.entity.TokenEntity"%>
<%@page import="com.yonyou.business.entity.TokenUtil"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html style="overflow-y:hidden">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style>
	.passwordline{
		width:100%;
		height:35px;
		margin-bottom:10px;
	}
	.passwordtips{
		display:block;
		width:100%;
		height:35px;
		line-height:35px;
		font-size:14px;
		text-align:right;
		
	}
	.col-md-3{
		height: 35px;
		padding: 0 !important;
	}
	.col-md-8{
		height: 35px;		
	}
	.passwordbtnbig{
		height:35px;
	}
	.passwordbtn{
		display:inline-block;
		width:auto;
		height:35px;
		margin-left:75px;
	}
	
</style>
<title>修改密码</title>

<script src="/portal/vendor/jquery/jquery-1.11.2.min.js"></script>
<script src="/portal/pages/js/bootstrap/js/bootstrap.min.js"></script>
<script src="/portal/pages/tree/jstree.min.js"></script>
<script src="/portal/pages/js/tinyselect.js"></script>
<link rel="stylesheet" href="/portal/pages/js/tinyselect.css">
<link rel="stylesheet" type="text/css" href="/portal/css/style.css">
<link rel="stylesheet" href="/portal/pages/js/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="/portal/pages/js/bootstrap-table/src/bootstrap-table.css">
<link rel="stylesheet" href="/portal/pages/js/examples.css">
<link rel="stylesheet" href="/portal/pages/tree/themes/default/style.min.css">
<script src="/portal/vendor/bootstrap/js/html5.js"></script>
<script src="/portal/vendor/bootstrap/js/respond.js"></script>
<link rel="stylesheet" type="text/css" href="/portal/vendor/vehicles/css/resert.css">
<link rel="stylesheet" type="text/css" href="/portal/vendor/vehicles/css/header.css">
<link rel="stylesheet" type="text/css" href="/portal/vendor/vehicles/css/customer.css">
	
<%
	TokenEntity tokenEntity = TokenUtil.initTokenEntity(request);
	String loginName = tokenEntity.USER.getName();
	String corpName = tokenEntity.COMPANY.getCompany_name();
	String token = (String) request.getParameter("token");
	String corpId = tokenEntity.COMPANY.getCompany_id();
	String userId = tokenEntity.USER.getId();
	String adminType = tokenEntity.USER.getAdminType();
	String IS_UPD_PASSWORD = request.getParameter("IS_UPD_PASSWORD");
%>
</head>
<body style="background:#fff;">
<form id="form">

		<div class="tabbable" id="tabs-146746">
				<div class="tab-content">
					<div class="tab-pane active" id="panel-847015" style="width:300px;margin:0 auto;">
						<input type="hidden" name="userId" id="userId" value="<%= userId%>"/>
											
						<div class="passwordline">
							<div class="col-md-3 col-xs-3 col-sm-3">
								<span class="passwordtips">用户名</span>
							</div>
							<div class="col-md-8 col-xs-8 col-sm-8" style="line-height:35px;">
								<%= loginName%>
							</div>							
						</div>
												
						<div class="passwordline">
							<div class="col-md-3 col-xs-3 col-sm-3">
								<span class="passwordtips">旧密码</span>
							</div>
							<div class="col-md-8 col-xs-8 col-sm-8">
								<input type="password" id="oldPWD" name="oldPWD" value=""  class="form-control" maxlength="30"/>
							</div>							
						</div>	
						
						<div class="passwordline">
							<div class="col-md-3 col-xs-3 col-sm-3">
								<span class="passwordtips">新密码</span>
							</div>
							<div class="col-md-8 col-xs-8 col-sm-8">
								<input type="password" id="newPWD" name="newPWD" value=""  class="form-control" maxlength="30"/>
							</div>							
						</div>
						
						<div class="passwordline">
							<div class="col-md-3 col-xs-3 col-sm-3">
								<span class="passwordtips">重输密码</span>
							</div>
							<div class="col-md-8 col-xs-8 col-sm-8">
								<input type="password" id="repeatNewPWD" name="repeatNewPWD" value=""  class="form-control" maxlength="30"/>
							</div>							
						</div>
						
						<div class="passwordbtnbig">
							<span class="passwordbtn">
								<button type="button" class="btn btn-success" onclick="editPWD();">
								<span class="glyphicon glyphicon-saved" aria-hidden="true"></span>
								确认</button>
								
							</span>	
						
						</div>

							
					</div>
				</div>
			</div>
		</form>
		  
<script type="text/javascript">
$(function() {
	var html = '<button type="button" class="btn btn-inverse"  id="closeBut" onclick="">';
	html+='<span class="glyphicon glyphicon-ban-circle" aria-hidden="true"></span>';
	html+='关闭</button>';
    $(".passwordbtn").append(html);
    $('.close',window.parent.document).hide();
    
	if(<%=IS_UPD_PASSWORD%>==null){
		$("#closeBut").attr("onclick","$('.close',window.parent.document).click();");
	}else{
		$("#closeBut").attr("onclick","firstLogin();");
	}
       
   
});

function firstLogin(){
	if(confirm("首次登陆若不修改密码将回到登陆页，您确定吗？")){
		window.parent.document.location.href = path();
	}
}

function path(){
	var adminType = '<%=adminType%>';
	var loginPath = "<%=request.getContextPath()%>/";
	if(adminType=='2'){
		return loginPath+="managerLogin/signOut?token=<%=token%>&uname=<%=tokenEntity.USER.getLoginId()%>";
	}else{
		return loginPath+="login/signOut?token=<%=token%>&uname=<%=tokenEntity.USER.getLoginId()%>";
	}
}
        
function editPWD(){
	if($("#userId").val()==null||$("#userId").val().length==0){
		alert("用户信息查询失败，请联系管理员");
		return;
	}
	if($("#oldPWD").val()==null||$("#oldPWD").val().length==0){
		alert("请填写旧密码");
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
			url :  "/portal/passWord?cmd=editPWD",
		    contentType: false,
			data:param,
			async : false,
			cache : false,
			processData : false,
			success : function(data) {
				if(data!=null&&data.state=="success"){
					if(confirm("修改成功，是否重新登录？")){
						window.parent.document.location.href = path();
					}
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