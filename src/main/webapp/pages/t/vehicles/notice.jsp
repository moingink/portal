<%@page import="com.yonyou.business.entity.TokenEntity"%>
<%@page import="com.yonyou.business.entity.TokenUtil"%>
<%@page import="com.yonyou.util.wsystem.service.ORG"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String ContextPath = request.getContextPath();
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
//取url参数
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");
	var r = window.location.search.substr(1).match(reg);
	if (r!=null) return unescape(r[2]); return null;
}
var State=GetQueryString("State")
var id=GetQueryString("ID");

if(State==1){
$.ajax({  
    type:'get',
    url:"/system/base?cmd=noticeJSON&dataSourceCode=NF_NOTICE_SHOW",  //全部数据
    dataType:'json', 
    data : {"ID":id},
    success:function(json){  
    //alert("JSON数据获取成功！"); 
    // 根据url中的参数ID取对应JSON数组内的值
    for(var i=0;i<=json.length;i++){
   		if(json[i]["ID"]==id)
   		{
   		var	TITLE=json[i]["TITLE"];
   		var	STATE_DATE=json[i]["STATE_DATE"];
   		var	NOTICE_MESSAGE=json[i]["NOTICE_MESSAGE"];
   		var ACCESSORY=json[i]["ACCESSORY"];
   		$("#noticeTitle").append(TITLE);
   		$("#noticeTime").append(STATE_DATE);
   		$("#noticeText").append(NOTICE_MESSAGE);
   		
   		
   		}
			}
        },  
        error:function(data){  
            alert("allNoticeJSON数据获取失败，请联系管理员！");  
        }  
    });

}else{
$.ajax({  
    type:'get',
    url:"/system/base?cmd=allNoticeJSON&dataSourceCode=NF_NOTICE_SHOW",  //全部数据
    dataType:'json', 
    data : {"ID":id},
    success:function(json){  
    //alert("JSON数据获取成功！"); 
    // 根据url中的参数ID取对应JSON数组内的值
    for(var i=0;i<=json.length;i++){
   		if(json[i]["ID"]==id)
   		{
   		var	TITLE=json[i]["TITLE"];
   		var	STATE_DATE=json[i]["STATE_DATE"];
   		var	NOTICE_MESSAGE=json[i]["NOTICE_MESSAGE"];
   		var ACCESSORY=json[i]["ACCESSORY"];
   		$("#noticeTitle").append(TITLE);
   		$("#noticeTime").append(STATE_DATE);
   		$("#noticeText").append(NOTICE_MESSAGE);
   		$("#fileList_ACCESSORY").append(ACCESSORY);
   		}
			}
        },  
        error:function(data){  
            alert("allNoticeJSON数据获取失败，请联系管理员！");  
        }  
    });
}

 $(function(){
	
    }); 
</script>
</html>