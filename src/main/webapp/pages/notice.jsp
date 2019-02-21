<%@page import="com.yonyou.business.entity.TokenEntity"%>
<%@page import="com.yonyou.business.entity.TokenUtil"%>
<%@page import="com.yonyou.util.wsystem.service.ORG"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String ContextPath = request.getContextPath();
TokenEntity tokenEntity = TokenUtil.initTokenEntity(request);
%>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>公告展示</title>
<!--公告弹出页面 -->
<!-- 设置浏览器默认渲染模式 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="renderer" content="webkit">
<script src="<%=ContextPath%>/vendor/jquery/jquery-1.11.2.min.js"></script>
<script src="<%=ContextPath%>/pages/js/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="<%=ContextPath%>/pages/js/bootstrap/css/bootstrap.min.css">
<!-- bootstrapIE8补丁 -->
<script src="<%=ContextPath%>/vendor/bootstrap/js/html5.js"></script>
<script src="<%=ContextPath%>/vendor/bootstrap/js/respond.js"></script>
</head>
<body>
	<div class="container">
		<div class="panel panel-primary" style="margin-top: 10%;">
			<div class="panel-heading">
				<h3 class="panel-title" style="text-align: center;">公告展示</h3>
			</div>
			<div>
				<table class="table" style="border-top: 2px #c0c0c0 solid">
					<tr>
						<th>公告标题:<span id="noticeTitle"></span></th>
						<th class="pull-right">公告时间:<span id="noticeTime"></span></th>
					</tr>
				</table>
			</div>
			<br>

			<div>
				<table class="table" style="border-top: 2px #c0c0c0 solid">
					<th>公告内容:<span id=noticeText></span></th>
				</table>
				<br>
				<div class="class="
					panel-title"" style="border-top: 2px #c0c0c0 solid"
					id="fileList_ACCESSORY">
					<a href="#" class="list-group-item active">已上传文件列表</a>
					<li class="list-group-item" id="1000999900000000013">fastDFS.txt<a href="/document/base?cmd=downloadAffix&amp;fid=1000999900000000013" class="btn green">下载</a></li>
				    <li class="list-group-item" id="1000999900000000014">linux命令.txt<a href="/document/base?cmd=downloadAffix&amp;fid=1000999900000000014" class="btn green">下载</a></li>
				    <li class="list-group-item" id="1000999900000000013">fastDFS.txt<a href="/document/base?cmd=downloadAffix&amp;fid=1000999900000000013" class="btn green">下载</a></li>
				    <li class="list-group-item" id="1000999900000000014">linux命令.txt<a href="/document/base?cmd=downloadAffix&amp;fid=1000999900000000014" class="btn green">下载</a></li>
				</div>
			</div>
			<br> <br>
		</div>
	</div>
</body>

<script type="text/javascript">
 var token ='<%=tokenEntity.getToken()%>';
//取url参数
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");
	var r = window.location.search.substr(1).match(reg);
	if (r!=null) return unescape(r[2]); return null;
}
var State=GetQueryString("State")
var id=GetQueryString("ID");

if(State==1){


$(function(){
 	    $.ajax({  
        type:'post',
        url:"/system/base?cmd=noticeJSON&dataSourceCode=NF_NOTICE_SHOW&token=<%=tokenEntity.getToken()%>,
        dataType:'json', 
        success:function(json){  
    	//alert("JSON数据获取成功！"); 
     	for(var i=0;i<json.length;i++){
			var ID=json[i]["ID"];
      		var NOTICE_MESSAGE=json[i]["NOTICE_MESSAGE"];
      		var STATE_DATE=json[i]["STATE_DATE"].split(' ')[0];
      		$("#notice").append("<li id=\'"+ID+"\'><a onclick=\"javascript:notice(\'"+ID+"\');\"><span>"+STATE_DATE+"</span><span style='color:red;' title='"+ NOTICE_MESSAGE+"'>"+NOTICE_MESSAGE+"</span></a><>");
			}
        },  
        error:function(data){  
            alert("noticeJSON数据获取失败，请联系管理员！");  
        }  
    }); 
	});




			
	
</script>
</html>