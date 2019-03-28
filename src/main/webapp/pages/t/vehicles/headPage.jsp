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
	
	String adminType = tokenEntity.USER.getAdminType();
	
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
					<%-- <img src="<%=path%>/img/logo.png"/> --%>
					<span class="title" style="font-family: '华文楷体'">天翼电子商务有限公司</span>
				</div>
				
				<div class="contain-fr">
					<div class="contain-role"><select id="role_type" class="form-control" name="INPUT_TYPE" data-bv-field="INPUT_TYPE"></select></div>
					 <div class="contain-role"><select id="company_type" class="form-control" name="INPUT_TYPE" data-bv-field="INPUT_TYPE"></select></div>
					<span class="nav-customer">欢迎您，<%= loginName%></span>
 					<%-- <span class="nav-customer" onclick="hidePopover();oTable.showModal('修改密码', '<iframe src=\'../pages/t/vehicles/editPassWord.jsp?token=<%= token %>\' width=\'100%\' height=\'250px\' frameborder=\'0\'>您的浏览器不支持iframe，请升级<\/iframe>');" ><img src="<%=path%>/img/set.png"/></span>  --%>
					<%-- <span class="nav-phone" onclick='openAppImages()'><img src="<%=path%>/img/phoneicon.png" /></span> --%>
					<span id="closeframe" class="nav-close" onclick='closeBus(this)'><img src="<%=path%>/img/close.png" title="退出"/></span>
					<!-- 下拉切换角色 -->
				</div>				
			</nav>
			<!--顶部背景图片-->
			<%-- <div class="top-background" >
				<img src="<%=path%>/img/banners.png"/>
			</div> --%>
				
	</body>
	<script type="text/javascript">
	
		/*获取url中的键值对*/
		function getUrlVars() {
		  var vars = [], hash;
		  var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
		  for (var i = 0; i < hashes.length; i++) {
		    hash = hashes[i].split('=');
		    vars.push(hash[0]);
		    vars[hash[0]] = hash[1];
		  }
		  return vars;
		}
		
		var code = getUrlVars()["totalcode"] + "";	
		var dom = document.getElementById("closeframe");
		if(code != "undefined"){
			dom.style.display = "none";
		}else{
			dom.style.display = "block";
		}
	
		var exitRoleId="";
		//获取角色json 组装成下拉列表
		var roleJson = JSON.parse('<%=tokenEntity.roleJsonString%>');
		var companyJson = JSON.parse('<%=tokenEntity.deptJsonString%>');
		var roleId = '<%=tokenEntity.ROLE.getRoleId()%>';
        var html ='<select class="" id="role_type" name="INPUT_TYPE" data-bv-field="INPUT_TYPE">';
        var company_html ='<select class="" id="company_type" name="INPUT_TYPE" data-bv-field="INPUT_TYPE">';
		if(roleId=="" && roleJson.length>1){
	        html=html+'<option  value="0">请选择</option>';
		}       
		if(roleId=="" && companyJson.length>1){
		 	company_html=company_html+'<option  value="0">请选择</option>';
		}  
        for(var i=0;i<roleJson.length;i++){
        	html=html+'<option  value="'+roleJson[i].roleId+'">'+roleJson[i].roleName+'</option>';
        }
        for(var i=0;i<companyJson.length;i++){
        	company_html=company_html+'<option  value="'+companyJson[i].ID+'">'+companyJson[i].NAME+'</option>';
        }
        if(roleId ==""){
        	 $('#role_type').val('0');
        	 $('#company_type').val('0');
        }
        $('#role_type').html(html);
       	$('#company_type').html(company_html);
        
		//设置选择角色
		var companyId = '<%=tokenEntity.COMPANY.getCompany_id()%>';
		
		if(companyId!=null||companyId!=""){
       		$("#company_type").find("option[value = '"+companyId+"']").attr("selected","selected");
		}
		function loadIndexRole(rid,cid){
			if(rid!=""){
				exitRoleId = rid;
			}else{
				exitRoleId =roleId;
			}
			$("#role_type").find("option[value = '"+exitRoleId+"']").attr("selected","selected");
			$("#company_type").find("option[value = '"+cid+"']").attr("selected","selected");
			//设置后台当前companyid
		}
		
		var isShowHeadImg ='<%=isShowHeadImg%>';
		
		$(function() {
			if(isShowHeadImg=='0'){
				$("div .top-background").hide();
			};
		});
		
		function closeBus(t){
			if(window.location.href.indexOf("portal/pages/mainPortal.jsp") == -1){
				alert("请到首页退出登陆");
				return;
			}
			if(confirm("是否退出系统！")){
				for(var i=0;i<winOpenObjArray.length;i++){
					if(winOpenObjArray[i].state=='1'){
						winOpenObjArray[i].obj.close();
					}
				}
				var loginPath = "<%=request.getContextPath()%>/";
				/*$.ajax({
					type: "GET",
					url: "/portal/base/getLoginPath",
					data: {"userId":"<%=userId%>"},
					dataType: "text",
					async: false,
					success: function(data) {
						loginPath += data+"/signOut?token=<%=token%>";
					}
				});*/
				
				var adminType = '<%=adminType%>';
				if(adminType=='2'){
					loginPath+="managerLogin/signOut?token=<%=token%>&uname=<%=tokenEntity.USER.getLoginId()%>";
				}else{
					loginPath+="login/signOut?token=<%=token%>&uname=<%=tokenEntity.USER.getLoginId()%>";
				}
				window.location.href = loginPath;
			}
		};
		
		//绑定更换事件
         $("#role_type").bind("change",function () {
             //重新更换页面
             //移除请选择  避免用户选择  
             
             $("#role_type option[value='0']").remove();
             //获取公司id传递
             var cid = $("#company_type").find("option:selected").val();
             var cname =$("#company_type").find("option:selected").text();;
             initMenu(cid,cname,this.value,1);
         });
          $("#company_type").bind("change",function () {
             //重新更换页面
             $("#company_type option[value='0']").remove();
             var rid = $("#role_type").find("option:selected").val();
             var cname =$("#company_type").find("option:selected").text();;
             initMenu(this.value,cname,rid,1);
         });
          function initMenu(companyid,companyname,roleId,tab){
	        	var paramJson = {'companyid':companyid, 'companyname':companyname};
      		//弹出角色选择窗口
	        	$.ajax({  
	                    url : "/portal/base?cmd=findMenu&type=1&token="+'<%=tokenEntity.getToken()%>'+'&ts='+new Date().getTime()+"&roleid="+roleId+"&tab="+tab,  
	                    dataType : "json",  
	                    type : "post",
	                    data : paramJson,
	                    success : function(data) { 
	                    	message = data['message'];
	                    	$("#First_level_Menu").html(message);
	                    	$(function () { 
		                    	$("[data-toggle='popover']").popover({
		                    					 html : true ,
		                    			         animation:true,placement: 'right',
		                    					 delay:{ show: 200, hide: 100 }
		                    	                 });
		                    	$("[data-toggle='popover']").on("mouseenter mouseleave",function(event){
									 if(event.type == "mouseenter"){
									 	$('.popover').popover("hide");
									    //鼠标悬浮
										$(this).popover("show");
										
									 }
									 else if(event.type == "mouseleave"){
									   //鼠标离开
									   var _this = this;
									   
									   //$(_this).popover("hide");
									   //setTimeout(function () {            	      
											//if (!$(".popover:hover").length) {            	          
												//$(_this).popover("hide")            	                 
											//}            	                    
										//}, 200); 
									 }
								});
								
							})

	                    	$('.li-hover').hover(function(){
					    		$(this).find('ul').hide();
					  			},
							    function(){
							      $(this).find('ul').show();
							    });
	                    } ,
	                    error:function(XMLHttpRequest, textStatus, errorThrown){
							message ="请求失败";
							alert(message);
						}
	             });
	               
	     }
          
          
          
		//打开APP二维码
		function openAppImages(){
			var host = window.location.host;//ip
			var port = window.location.port;//端口
			var html = '';
			if(port != '' && port != null){
				var androidImagesUrl = '/cuscapp/appimages/Android'+port+'.png';
				var iosImagesUrl = '/cuscapp/appimages/IOS'+port+'.png';
				var androidImagesHtml = '<img src="'+androidImagesUrl+'"/><center><p><b>Android</b></p></center>';
				var iosImagesHtml = '<img src="'+iosImagesUrl+'"/><center><p><b>IOS</b></p></center>';
				if(!isEffective(androidImagesUrl)){
					androidImagesHtml = '<center><p><b>不存在Android二维码</b></p></center>';
				}
				if(!isEffective(iosImagesUrl)){
					iosImagesHtml = '<center><p><b>不存在IOS二维码</b></p></center>';
				}
				html += '<div style="height:300px;" align="center">'+
							'<ul style="width:540px">'+
								'<li style="float:left;">'+
									androidImagesHtml+
								'</li>'+
								'<li style="float:left;margin-left:20px;">'+
									iosImagesHtml+
								'</li>'+
							'</ul>'+
						'</div>';
			}else{
				html += '不存在二维码';
			}
			oTable.showModal('<p>APP二维码</p>', html);
		}
		
		//判断URL是否有效
		function isEffective(url){
			var flag = false;
			$.ajax({
				type: 'GET',
				url: url,
				async: false,
				complete: function(response) {
					if(response.status == 200) {
						flag = true;
					} else {
						flag = false;
					}
				}
			});
			return flag;
		}
	</script>
</html>
