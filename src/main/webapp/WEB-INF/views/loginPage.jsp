<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- 设置浏览器默认渲染模式 -->
		<meta name="renderer" content="webkit">
<title>登录</title>

<%
	String ContextPath =request.getContextPath();
	String accounterror =(String)request.getAttribute("accounterror")!=null?(String)request.getAttribute("accounterror"):"";
	String username =(String)request.getAttribute("username")!=null?(String)request.getAttribute("username"):"";
	String password =(String)request.getAttribute("password")!=null?(String)request.getAttribute("password"):"";
	
%>
<link href="<%=ContextPath %>/vendor/init.css" rel="stylesheet">
<link href="<%=ContextPath %>/vendor/login.css" rel="stylesheet">
<link href="<%=ContextPath %>/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- bootstrapIE8补丁 -->
		<script src="<%=ContextPath%>/vendor/bootstrap/js/html5.js"></script>
		<script src="<%=ContextPath%>/vendor/bootstrap/js/respond.js"></script>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
  <script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
  <script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<script type="text/javascript" src="<%=ContextPath %>/vendor/jquery/jquery-1.11.2.min.js"></script>
<script src="<%=ContextPath%>/pages/js/bootstrap/js/bootstrap.min.js"></script>

<!-- keyboard -->

<script src="<%=ContextPath%>/vendor/keyboard/jquery.keyboard.extension-autocomplete.js"></script>
<script src="<%=ContextPath%>/vendor/keyboard/jquery.keyboard.extension-typing.js"></script>
<script src="<%=ContextPath%>/vendor/keyboard/jquery.keyboard.js"></script>
<script src="<%=ContextPath%>/vendor/keyboard/jquery.mousewheel.js"></script>
<script src="<%=ContextPath%>/vendor/keyboard/jquery-ui.js"></script>

<link href="<%=ContextPath %>/vendor/keyboard/keyboard.css" rel="stylesheet">
<link href="<%=ContextPath %>/vendor/keyboard/jquery-ui.css" rel="stylesheet">

</head>
<body>
<form method="post" id="formlogin" name="form" action="<%=ContextPath %>/login/formLogin">
<div class="txt_0 txt">
	<div class="container">
    	<div class="row row_0">
		  <div class="col-xs-6">
			<h4>
			   <img src="<%=ContextPath %>/images/yd_logo.png" class="img-responsive" alt="Responsive image">
			</h4>
		  </div>
		  <div class="col-xs-6">
		  	 <h4></h4>
		  </div>
		</div>
    </div>
</div>
<div class="txt_1 txt">
	<div class="container">
    	<div class="row">
		  <div class="col-xs-6 row_l">
		  	<img src="<%=ContextPath %>/images/login_bg.png" class="img-responsive" alt="Responsive image">
		  </div>
		  <div class="col-xs-6 row_r">
		  	<div class="login_box">
		  		<h6>欢迎登录</h6>
		  		<div class="login_name form-group has-feedback">
		  			<label for="userName" class="label_user">用户名</label>
		  			<span class="glyphicon glyphicon-user form-control-feedback"></span>
					<input type="text" name="username" maxlength="50" value="" id="username" class="user_input form-control"  />
		  		</div>
		  		<div class="login_password form-group has-feedback">
		  			<label for="password" class="label_password">密码</label>
					<span class="glyphicon glyphicon-lock form-control-feedback"></span>
					<input type="password" name="password" maxlength="50" value="" id="password" class="password_input form-control qwerty" />
		  		</div>
		  		<div class="login_securityCode row">
		  			<div class="col-md-5">
	
		  				<input type="text" id="vaildateCode" name= "vaildateCode" class=" form-control">
		  			</div>
  					<div class="col-md-5 padding_cur0">
						<img id= "Img" src="${pageContext.request.contextPath}/login/imageCode">
  					</div>
  					<div class="col-md-2 padding_cur1">
  						 <a href="#" onclick="reImg();" style=" color:#0175e2;font-size:16px;">换一张</a>
  					
  					</div>
		  		</div>
		  		<div class="login_auto">
		  			 <div class="checkbox">
					    <label>
					      <input type="checkbox">自动登录
					    </label>
					 </div>
		  		</div>
		  		<div class="login_success">		  			
		  				<button type="button" onclick="onSumbit(this)" class="btn btn-success btn-primary">登录</button>
		  		</div>
		  		<div>
		  			<a href="#" class="pull-left lightblue">忘记密码</a>
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
</div>
</form> 

<script type="text/javascript">
$(function(){
		$(".password_input").attr({
			"oncontextmenu":"return false",	//禁止右键
			"onpaste":"return false",	//禁止粘贴
			"oncopy" :"return false",	//禁止复制
			"oncut":"return false" ,	//禁止剪切
			"onkeypress":"return false" ,//禁止键盘录入
		});
		
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
	}else if (isEmpty(vaildateCode)) {
		$("#loginMessage").html("验证码不能为空！");
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

$('.qwerty').keyboard({
		openOn : null,
		stayOpen : true,
		layout : 'qwerty'
	}).addTyping();

$('#password').focus(function(){
	var kb = $('.qwerty').getkeyboard();
	// close the keyboard if the keyboard is visible and the button is clicked a second time
	if ( kb.isOpen ) {
		kb.close();
	} else {
		kb.reveal();
	}
});

</script>   
</body>
</html>