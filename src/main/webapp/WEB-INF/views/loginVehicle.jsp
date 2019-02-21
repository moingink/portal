<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
	String ContextPath = request.getContextPath();
	String accounterror =(String)request.getAttribute("accounterror")!=null?(String)request.getAttribute("accounterror"):"";
	String username =(String)request.getAttribute("username")!=null?(String)request.getAttribute("username"):"";
	String pd =(String)request.getAttribute("pd")!=null?(String)request.getAttribute("pd"):"";
	
	String uname=request.getParameter("uname");
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
<link rel="stylesheet" type="text/css" href="<%=ContextPath %>/vendor/vehicles/css/resert.css"/>
<link rel="stylesheet" type="text/css" href="<%=ContextPath %>/vendor/vehicles/css/login.css"/>
<body>
	
	<form method="post" name="form" action="<%=ContextPath%>/login/formLogin">
		<div class="contain">			
			<div class="box">
				<!--背景-->
				<div class="bg"></div>
				<!--内容-->
				<div class="login-info">
					<div class="top-info">
						<img src="<%=ContextPath %>/vendor/vehicles/img/china.png"/>
					</div>
					<div class="login-info">
						<div class="login-list">
							<p class="name-img"></p>
							<p class="name-input"><input type="text" placeholder="请输入用户名" name='username' id="username"/></p>
						</div>
						<div class="login-list">
							<p class="name-img" id="name-img"></p>
							<p class="name-input"><input type="password" placeholder="请输入密码" autocomplete="off"  name='pd' id="pd"/></p>
						</div>
						
						<!-- <div class="login-list">
							<p  id="name-num">8668</p>
							<p class="name-input" id="num-input"><input type="password" placeholder="请输入验证码" id="" value='8668'/></p>
						</div> -->
						
						<div class="login-list" style="margin-left: auto;  margin-right: auto;">
							<p id="name-num"><img src="/portal/login/imageCode" id="image" style="width: 100%;height: 100%;" /></p>
							<p class="name-input" style="border:0;" id="num-input">
								<input type="text" placeholder="请输入验证码" style="border: 1px solid #8690A3;" id="imageCode" name="imageCode" maxlength="30" value="">
							</p>
						</div>
						
						<div class="login-btn" onclick="onSumbit(this)">
							<p>登录</p>
						</div>
						<div class="login-btn" onclick="retrievePWD()">
							<p>找回密码</p>
						</div>
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
							<button type="button" class="btn btn-inverse" data-dismiss="modal">关闭
							</button>
						</div>
					</div><!-- /.modal-content -->
				</div><!-- /.modal -->
			</div>
		</div>
		
		</form>
		
		   
			
		
</body>

<script type="text/javascript">
	
	$(function(){
		var uname ='<%=uname%>';
		if(!isEmpty(uname) && uname!='null'){
			$("#username").val(uname);
			$("#pd").val("");
		}
		$("#image").click(function(){
       		var url = $(this).attr('src')+'?random='+Math.random();
         	$(this).attr("src",url);
         });
	         
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
	  	var password ='<%=pd%>';
	  	if(!isEmpty(error)){
	  		if(error!="验证码错误!"){
	  			if(error=="用户名不存在!"){
		  			$("#username").val("");
		  			$("#pd").val("");
		  		}else{
	  				$("#username").val(username);
	  				$("#pd").val("");
	  			}
	  		}else{
	  			$("#username").val(username);
	  			$("#pd").val(password);
	  		}
	  		showModal(error);
	  	}
	  	var delHeight = $('.txt_0').height() + $('.bottom').height();
		$(window).resize(function(){
			var allHeight = window.innerHeight;
			
			var height = allHeight - delHeight;
			height = height<420?420:height;
			$('.txt_1').height(height);

		})
		$(window).trigger('resize');
	  	
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
	var password =$("#pd").val();
	
	if($("#imageCode").val()==null||$("#imageCode").val().length==0){
		$("#loginMessage").html("请填写图片验证码！");
		$("#myModal").modal('show'); 
		return;
	}
	if($("#imageCode").val().length!=4){
		$("#loginMessage").html("图片验证码长度错误！");
		$("#myModal").modal('show'); 
		return;
	}
	
	var imageCode = $("#imageCode").val();
	
	if(isEmpty(username)||isEmpty(password)){
		$("#loginMessage").html("用户名、密码不能为空！");
		$("#myModal").modal('show'); 
	}else{
		$("#pd").val(b64Encode(password));
		changeAction();
		form.submit();
	}
}

//改变action
function changeAction(){
	var url = window.document.location.href;
	if(url.indexOf('managerLogin') != -1){
		$('form[name=form]').attr('action','<%=ContextPath%>/managerLogin/formLogin');
	}
}

function showModal(message){
	$("#loginMessage").html(message);
	$("#myModal").modal('show'); 
}

function b64Encode(str) { return btoa(encodeURIComponent(str)); }

/*
$('.qwerty').keyboard({
		openOn : null,
		stayOpen : true,
		layout : 'qwerty'
	}).addTyping();
*/
/*
$('#pd').focus(function(){
	var kb = $('.qwerty').getkeyboard();
	// close the keyboard if the keyboard is visible and the button is clicked a second time
	if ( kb.isOpen ) {
		kb.close();
	} else {
		kb.reveal();
	}
});*/
function retrievePWD(){
	$("#loginMessage").html('<iframe src=\'/portal/passWord/goRetrievePWDSendCode\' width=\'100%\' height=\'500px\' frameborder=\'0\'>您的浏览器不支持iframe，请升级<\/iframe>');
	$("#myModal").modal('show'); 
}
</script>
</html>
