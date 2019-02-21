<%@page import="com.yonyou.util.theme.ThemePath"%>
<%@page import="com.yonyou.business.entity.TokenUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.yonyou.util.wsystem.service.ORG"%>
<%@page import="com.yonyou.iuap.system.web.login.RoleForTheme"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<!-- 获取参数userId、companyId，调用方法-->
<%
	String ContextPath =request.getContextPath();
	String token = (String) request.getParameter("token");
	String userId = TokenUtil.initTokenEntity(request).USER.getId();
	String companyId = TokenUtil.initTokenEntity(request).COMPANY.getCompany_id();
	RoleForTheme roleForTheme = new RoleForTheme();
    List<String> list= roleForTheme.getWorkbenchPic(userId,companyId);
%>

<!-- 清缓存-->
<%response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
response.flushBuffer();%>

<html lang="zh-cn">
<head>
<meta charset="utf-8">
<!-- 设置浏览器默认渲染模式 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="renderer" content="webkit">
<title>门户</title>
</head>
<link rel="stylesheet" href="<%=ContextPath%>/pages/js/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=ContextPath%>/pages/js/bootstrap-table/src/bootstrap-table.css">
<link rel="stylesheet" href="<%=ContextPath%>/pages/js/examples.css">
<link rel="stylesheet" href="<%=ContextPath%>/pages/tree/themes/default/style.min.css" />
<link rel="stylesheet" type="text/css" href="<%=ThemePath.findPath(request, ThemePath.PORTAL_CSS)%>">
<script src="<%=ContextPath%>/vendor/jquery/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="<%=ContextPath%>/pages/js/bootstrap/js/bootstrap.min.js"></script>
<!-- bootstrapIE8补丁 -->
<script src="<%=ContextPath%>/vendor/bootstrap/js/html5.js"></script>
<script src="<%=ContextPath%>/vendor/bootstrap/js/respond.js"></script>
<body >	
	<!-- 轮播图 -->
	<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
	  <!-- Indicators -->
	
		<!-- 遍历轮播图下的切换圈 -->
	  <ol class="carousel-indicators">
	  	<% 
	  	int i=0;
	  	for (String str:list){%>
	    <li data-target="#carousel-example-generic" data-slide-to="<%=i%>" 
       	    <%if(i==0){%>
       	    	class="active"
       	    <%}%>
	    ></li>
	    <%
	     i++;
	    }%>
	  </ol>
	  
	  <!-- 遍历轮播图 -->
	  <div class="carousel-inner" role="listbox">
	  	<% 
	  	i=0;
	  	for (String str1:list){%>
	  		<div class="item 
	  		<%if(0==i){%>
       	    	 active
       	    <%}%>">
			      <img src="/portal<%=str1%>" alt="...">
			      <div class="carousel-caption">	
				  </div>
		  	</div>
	    <%
	     i++;
	    }%>
	  </div>

	  <!-- Controls -->
	  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
	    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
	    <span class="sr-only">Previous</span>
	  </a>
	  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
	    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
	    <span class="sr-only">Next</span>
	  </a>
	  <!-- 轮播左边模块 -->
	  <div class="carousel_l">
	  	<div class="backlog">
	  		<header>
	  			<span class="glyphicon glyphicon-hourglass"></span>
	  			<span>待办事项</span>
	  			<a href="" class="pull-right">更多></a>
	  		</header>
	  		<ul>
	  			<li>
	  				<span>【待阅】</span>
	  				<span>借款单据审批</span>
				</li>
				<li>
	  				<span>【待阅】</span>
	  				<span>采购订单网络维护报账单</span>
				</li>
	
				<li>
	  				<span>【待办】</span>
	  				<span>差旅费报账单审批</span>
				</li>
				<li>
	  				<span>【待办】</span>
	  				<span>待摊费用报账单审批</span>
				</li>
				
	  		</ul>
	  	</div>
	  	<div class="messagelog">
	  		<header>
	  			<span class="glyphicon glyphicon-volume-up"></span>
	  			<span>消息</span>
	  			<a href="" class="pull-right" id="noticeAll">更多></a>
	  		</header>
	  		<ul id="notice"></ul>
	  	</div>
	  </div>
  		<!-- 轮播右边模块 -->
		  <div class="carousel_r">
		  	<div class="backlog">
		  		<ul   id="hh"></ul>
				<ul>
		  		
					<li>
			        	<dl>
			        		<dt><img src="<%=ContextPath%>/images/02.jpg" alt="" id="more" style="cursor:pointer"></dt>
			        		 <dd style="cursor:pointer;font-family:'微软雅黑';" id="more2"><a>更多</a></dd> 
			        	</dl>
			        </li>	
				</ul>	
			        
			        
		  		
		  	</div>
		  </div>
		</div>
<!-- 模块展示 -->
		<div class="container item-wrap">
			<div class="item_box">
				<ul class="item_scroll clearfix" id="pr" >
					<li>
						<img src="<%=ThemePath.findRoute(request) %>img/41.png" alt="系统建设中" >
					</li>
					<li>
						<img src="<%=ThemePath.findRoute(request) %>img/42.png" alt="系统建设中" >
					</li>
					<li>
						<img src="<%=ThemePath.findRoute(request) %>img/43.png" alt="系统建设中" >
					</li>
					<li>
						<img src="<%=ThemePath.findRoute(request) %>img/44.png" alt="系统建设中" >
					</li>
					<li>
						<img src="<%=ThemePath.findRoute(request) %>img/51.png" alt="系统建设中" >
					</li>
					<li>
						<img src="<%=ThemePath.findRoute(request) %>img/52.png" alt="系统建设中" >
					</li>
				</ul>
				<p class="left_btn">&lt;</p>
				<p class="right_btn">&gt;</p>
		</div>

	</div>
	<body>
	
	<script type="text/javascript">
	$(function(){
 	    $.ajax({  
        type:'post',
        url:"/system/base?cmd=noticeJSON&dataSourceCode=NF_NOTICE_SHOW&token=<%=token%>",  
        dataType:'json', 
        success:function(json){  
    	//alert("JSON数据获取成功！"); 
     	for(var i=0;i<=json.length;i++){
			var ID=json[i]["ID"];
      		var NOTICE_MESSAGE=json[i]["NOTICE_MESSAGE"];
      		var STATE_DATE=json[i]["STATE_DATE"].split(' ')[0];
      		$("#notice").append("<li id=\'"+ID+"\'><a onclick=\"javascript:notice(\'"+ID+"\');\"><span>"+STATE_DATE+"</span><span style='color:red;' title='"+ NOTICE_MESSAGE+"'>"+NOTICE_MESSAGE+"</span></a></li>");
			}
        },  
        error:function(data){  
            alert("noticeJSON数据获取失败，请联系管理员！");  
        }  
    }); 
	});
	
	function notice(id){
	
	    $.ajax({
			type : 'get',
			url : "/system/base?cmd=updateState",
			data : {"ID":id},
			success : function(data) {
				//alert("ajax请求成功！"+id);
			},
			error : function() {
				alert('updateState请求失败 请联系管理员！ ');
			}
		});
			
      	window.open("/portal/pages/notice.jsp?"+"ID="+id+"&State=1",+"_blank",
		     "top=100,left=400,width=700,height=500,menubar=yes,scrollbars=no,toolbar=no,status=yes");
     }
			
	noticeAll.onclick=function(){
		     window.open("/system/pages/singleTableModify.jsp?pageCode=NF_NOTICE_SHOW&pageName=公告展示&token=<%=token%>","_blank",
		     "top=100,left=300,width=1000,height=600,menubar=yes,scrollbars=no,toolbar=no,status=yes");
			}
	
	
	    var winObj;
	    var flag=false;
		more.onclick=function(){
		            var pageWidth = window.innerWidth ; 
				var pageHeight = window.innerHeight; 
				
				if (typeof pageWidth != "number"){ 
				    if (document.compatMode == "CSS1Compat"){ 
				        pageWidth = document.documentElement.clientWidth; 
				        pageHeight = document.documentElement.clientHeight; 
				    } else { 
				        pageWidth = document.body.clientWidth; 
				        pageHeight = document.body.clientHeight; 
				    } 
				} 
				
				var newWidth = (Number(pageWidth)/3) < 600 ? 600 : (Number(pageWidth)/3); 
				var newHeight = (Number(pageHeight)/2) < 400 ? 400 : (Number(pageHeight)/2); 
				var newLeft = pageWidth - newWidth - 50; 
				var newTop = pageHeight - newHeight - 5 ; 
				winObj = window.open('/system/pages/singleTableModify.jsp?pageCode=RM_FUNCTION_NODE&pageName=菜单节点&token=<%=token%>','_blank','width='+newWidth+',height='+newHeight+',top='+newTop+'px,left='+newLeft+'px,toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no');
				flag=true;
			}	
			
			
			more2.onclick=function(){
		            var pageWidth = window.innerWidth ; 
				var pageHeight = window.innerHeight; 
				
				if (typeof pageWidth != "number"){ 
				    if (document.compatMode == "CSS1Compat"){ 
				        pageWidth = document.documentElement.clientWidth; 
				        pageHeight = document.documentElement.clientHeight; 
				    } else { 
				        pageWidth = document.body.clientWidth; 
				        pageHeight = document.body.clientHeight; 
				    } 
				} 
				
				var newWidth = (Number(pageWidth)/3) < 600 ? 600 : (Number(pageWidth)/3); 
				var newHeight = (Number(pageHeight)/2) < 400 ? 400 : (Number(pageHeight)/2); 
				var newLeft = pageWidth - newWidth - 50; 
				var newTop = pageHeight - newHeight - 5 ; 
				winObj = window.open('/system/pages/singleTableModify.jsp?pageCode=RM_FUNCTION_NODE&pageName=菜单节点&token=<%=token%>','_blank','width='+newWidth+',height='+newHeight+',top='+newTop+'px,left='+newLeft+'px,toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no');
				flag=true;
			}	
			

		pr.onclick=function(){
			alert('系统建设中');
		}
		

	  function flushCustMenu(){
	  	$.ajax({  
	        url:"<%=ContextPath%>/menu/show?token=<%=token%>",  
	        type:"post", 
	        data:"",  
	        dataType:"json",  
	        success:function(data){
	        	//alert(data);
	        	$("#hh").text("");
	        	for (var i=0; i < data.length; i++) {
	        			 $("#hh").append("<li><dl totalcode='"+data[i].TOTAL_CODE+"' onclick='menuManager(this)'>"+"<dt><img src='<%=ContextPath%>/images/indexIcon_"+i+".png' alt='' style='cursor:pointer'></dt>"+
	        		 	"<dd><a href='javascript:void(0)' totalcode="+data[i].TOTAL_CODE+" onclick='menuManager(this)' title='"+data[i].NAME+"'>"+data[i].NAME.substring(0,4)+"</a></dd></dl></li>");
	        	};
	        },  
	        error:function(){
	        	alert("error")
	        }  
     	});  
	  }
	  	 
  flushCustMenu();
		
		
		//底部轮播
		var aLi = $('.item_scroll li').clone(true);
		$('.item_scroll').append(aLi);
		//轮播元素个数
		var x = $('.item_scroll li').length;
		//轮播元素宽度
		var w = $('.item_scroll li:first').outerWidth(true);
		//单击一次平移元素个数
		var i = 4;
		$(".right_btn").click(function(){
			var scroll_x= $(".item_scroll").position().left;
			//console.log('right_btn'+scroll_x);
			if(scroll_x > -(x - 4) * w){
				scroll_x -= w*i;
			}else{
				scroll_x = 0;
			}
			$(".item_scroll").animate({'left':scroll_x});
		});
		$(".left_btn").click(function(){
			var scroll_rx= $(".item_scroll").position().left;
			console.log('left_btn'+scroll_rx);
			if(scroll_rx < 0){
				scroll_rx += w*i;
			}else{
				scroll_rx = -(x - 4) * w;
			}
			$(".item_scroll").animate({'left':scroll_rx});
		});
		
	
		$(function() {
				window.parent.setHeigth($(document.body).height());
		});
	function menuManager (t) {
	   
	   var totalcode =$(t).attr("totalcode");
	   var urlParam= "&urltotalcode="+totalcode;
	   if(totalcode.length>=6){
	   		 
	   		var mark="a[totalcode="+totalcode.substr(0,6)+"]";
	   		//alert(mark);
	   		chageMenu(mark,urlParam);
	   }
	  
	   
	}
	
	function chageMenu(mark,urlParam){
		 parent.changMenuByObj($(mark, window.parent.document),urlParam);
	}
	

        var loopfun = function() {  
 			   
			    if(flag  && winObj.closed) {  
			        flushCustMenu();  
			        flag = false ; 
			    }  
			};
	   setInterval(loopfun, 10000);

	</script>
</html>