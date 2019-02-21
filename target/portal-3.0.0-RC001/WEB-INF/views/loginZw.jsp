<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
	String ContextPath =request.getContextPath();
	String accounterror =(String)request.getAttribute("accounterror")!=null?(String)request.getAttribute("accounterror"):"";
	String username =(String)request.getAttribute("username")!=null?(String)request.getAttribute("username"):"";
	String password =(String)request.getAttribute("password")!=null?(String)request.getAttribute("password"):"";
	
%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>欢迎登陆，业务管理系统</title>
<link rel="stylesheet" type="text/css" href="<%=ContextPath %>/vendor/bootstrap/css/bootstrap.min.css">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
  <script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
  <script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<script type="text/javascript" src="<%=ContextPath %>/vendor/jquery/jquery-1.11.2.min.js"></script>
<script src="<%=ContextPath%>/pages/js/bootstrap/js/bootstrap.min.js"></script>
<style type="text/css">
		html,body,form{
			width: 100%;
			height: 100%;
		}
		.bg{
			width: 1280px;
			height: 100%;
			background-image: url('<%=ContextPath%>/vendor/zw/img/bgs.png');
			background-repeat: no-repeat;
			background-size: 1280px 100%;
			margin: 0 auto;
		}
		.bg .box{
			width: 270px;
			margin: 0 auto;
			padding-top: 190px;
			box-sizing: border-box;
		}
		.box .pic{
			width: 100%;
			height: 50px;
		}
		.box .pic img{
			width: 100%;
			height: 100%;
		}
		/*用户名密码*/
		.box .name{
			width: 100%;
			height: 30px;
			margin-top: 10px;
		}
		.box .name p{
			float: left;
		}
		/*左侧图标*/
		.box .name .you-name{
			width: 30px;
			background: #0274C6;
			color: white;
			height: 30px;
			line-height: 30px;
			text-align: center;
		}
		.name .you-name span{
			font-size: 20px;			
			line-height: 30px;
		}
		/*右侧输入框*/
		.box .name .write-name{
			width: 240px;
			height: 30px;
		}
		.box .write-name input{
			width: 100%;
			height: 100%;
			border: none;
			padding-left: 10px;
			box-sizing: border-box;
		}
		/*验证码*/
		.name .code{
			width: 60px;
			height: 30px;
			text-align: center;
			line-height: 30px;
			background: #0274C6;
			color: white;
		}
		.name .write-code{
			width: 210px;
			height: 30px;
		}
		.name .write-code input{
			width: 100%;
			height: 100%;
			padding-left:10px;
			box-sizing: border-box;
			border: none;
		}
		/*登录按钮*/
		.box .login{
			width: 100%;
			height: 30px;
			background: #0274C6;
			color: white;
			text-align: center;
			margin-top: 20px;
			line-height: 30px;
		}
	</style>
<!-- zw -->
<link rel="stylesheet" type="text/css" href="<%=ContextPath %>/vendor/zw/css/reset.css"/>
<link rel="stylesheet" type="text/css" href="<%=ContextPath %>/vendor/zw/lib/fontAwesome/css/font-awesome.min.css"/>
</head>
<body>

	<form method="post"  name="form" action="<%=ContextPath %>/login/formLogin">
	<div class="bg">
			<div class="box">
				<div class="pic">
					<img src="<%=ContextPath %>/vendor/zw/img/logo.png"/>
				</div>
				<div class="name">
					<p class="you-name"><span class=" fa fa-user"></p></span>
					<p class="write-name"><input type="text" name="username" maxlength="50" value="" id="username"  value="" placeholder="请输入用户名"/></p>
				</div>
				<div class="name">
					<p class="you-name"><span class="fa fa-unlock-alt"></span></p>
					<p class="write-name"><input type="password" name="password" maxlength="50" value="" id="password"   placeholder=""/></p>
				</div>
				<div class="name">
					<p class="code"><span class="code-num">8668</span></p>
					<p class="write-code"><input type="text" name=""  value="8668" placeholder="请输入验证码"/></p>
				</div>
				<div class="login" onclick="onSumbit(this)">
					<p>登录</p>
				</div>
			</div>
		</div>
	
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
								&times;
							</button>
							<h4 class="modal-title" id="myModalLabel">
								提示
							</h4>
						</div>
						<div class="modal-body" >
							 <font id="loginMessage" color="red"></font>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭
							</button>
						</div>
					</div><!-- /.modal-content -->
				</div><!-- /.modal -->
			</div>
</form>
</body>

<script type="text/javascript">
	
	$(function(){
	
		/*$(".password_input").attr({
			"oncontextmenu":"return false",	//禁止右键
			// "onpaste":"return false",	//禁止粘贴copy" :"return false",	//禁止复制
			"oncut":"return false" ,	//禁止剪切
			//"onkeypress":"return false" ,//禁止键盘录入
		});*/
		
		$("#username").on({
	    	focus:function(){$("body").css("background-color","lightgray");},  
	    	blur:function(){$("body").css("background-color","lightblue");}, 
	  	});
	  	var error ='<%=accounterror%>';
	  	var username ='<%=username%>';
	  	var password ='<%=password%>';
	  	if(!isEmpty(error)&&!isEmpty(error)){
	  		$("#username").val(username);
	  		$("#password").val(password);
	  	}
	  	if(!isEmpty(error)){
	  		showModal(error);
	  	}
	  	
	  	document.onkeydown=function(event){
            var e = event || window.event || arguments.callee.caller.arguments[0];
             if(e && e.keyCode==13){ // enter 键登录
                 onSumbit();
            }
        }; 
});

function reImg(){    
	   var timenow = new Date().getTime();
       var img = document.getElementById("Img");    
       img.src = "${pageContext.request.contextPath}/login/imageCode?d="+timenow;    
 } 
 
 function isEmpty(column){
	if(column!=null&&column.trim().length>0){
		return false;
	}else{
		return true;
	}
}
function onSumbit(t){

	var username =$("#username").val();
	var password =$("#password").val();
	var vaildateCode=$("#vaildateCode").val();
	if(isEmpty(username)||isEmpty(password)){
		$("#loginMessage").html("用户名、密码不能为空！");
		$("#myModal").modal('show'); 
	}else{
		$("#password").val(b64Encode(password));
		form.submit();
	}
}


function showModal(message){
	$("#loginMessage").html(message);
	$("#myModal").modal('show'); 
}

function b64Encode(str) { return btoa(encodeURIComponent(str)); }






</script>
</html>