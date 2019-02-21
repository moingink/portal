<%@page import="com.yonyou.util.theme.ThemePath"%>
<%@page import="com.yonyou.util.wsystem.service.ORG"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>门户</title>

<%
	String token = (String) request.getParameter("token");
	String totalCode = (String) request.getParameter("totalCode");
	String ContextPath =request.getContextPath();
	String urltotalcode=(String) request.getParameter("urltotalcode");
%>
</head>
<link rel="stylesheet"
	href="<%=ContextPath%>/pages/js/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=ContextPath%>/pages/js/bootstrap-table/src/bootstrap-table.css">
<link rel="stylesheet" href="<%=ContextPath%>/pages/js/examples.css">
<link rel="stylesheet"
	href="<%=ContextPath%>/pages/tree/themes/default/style.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=ThemePath.findPath(request, ThemePath.PORTAL_CSS)%>">
<script src="<%=ContextPath%>/vendor/jquery/jquery-1.11.2.min.js"></script>
<body>

	<div class="container" style="width: 90%">
		<div class="row">
			<div id="node" class="sidebar">

				<div id="menuName" class="header"></div>
				<div id="nodeMenu" class="body">
					<div id="nodePage"></div>
				</div>
				<div class="footer"></div>
			</div>
			<div class="main">
				<iframe id='nodeFrame' src='<%=ContextPath%>/pages/default.html'
					width="100%" scrolling="no" frameborder="0" ></iframe>
			</div>
		</div>
	</div>

	<%-- <div class="col-md-2">
			  
			<div id="nodePage"></div>
		</div>
		<div class='col-md-10'>
			
			<iframe id='nodeFrame' src='<%=ContextPath%>/pages/default.html' width="100%" scrolling="no" frameborder="0" height="500px"  ></iframe>
			
		</div> --%>
<body>

	<script type="text/javascript">

		 var init_heigth=0;
		 $(function(){ 
          		initNode();
         }); 
         
         
        function changNode(t){
        	
        	changNodeByObj($(t));
        	
        	                                      
        }
        
        function changNodeByObj($t){
        	
        	var url=$t.attr("url");
        	var total=$t.attr("totalcode");
        	var mark="?";
        	if(url!=null&&url.length>0){
        		if(url.indexOf("?")!=-1){
        			mark="&";
        		}
        		url=url+mark+"token="+'<%=token%>'+'&totalCode='+total;
        		$("#nodeFrame").attr("src",url);
        		setHeigth(1600);
        		removeAct($t);
        		transferUrl($t,url);/*传url*/
        	}else{
        		var parentcode =$t.attr("totalcode");
        		var spanhtml=$t.find("span").html();
        		var repspanhtml="";
        		var mark ="+";
        		if(spanhtml.indexOf("&gt;")!=-1){
        			repspanhtml=spanhtml.replace("&gt;",mark);
        		}
        		if(spanhtml.indexOf(mark)!=-1){
        			repspanhtml=spanhtml.replace(mark,"&gt;");
        		}
        		$t.find("span").html(repspanhtml);
        		var parentcodeMark="dd[parentcode='"+parentcode+"']";
        		$(parentcodeMark).toggle();
        		setMenuHeigth($("#node").height());
        	}
        	
        	
        }
        
        function transferUrl($t,url){				/*传url*/
        	  var menuName =$t.attr("title");
        	  var ajaxUrl ="/portal/button?cmd=menu&token="+'<%=token%>'+"&menuName="+menuName;
			  $.ajax({
				            type: "post",
				            url: ajaxUrl,
				            data: {url},
							});	      
        }
        function initNodeByTotalCode(){
       		var urltotalCode='<%=urltotalcode%>';
       		var totalcode='<%= totalCode%>';
       		for(var i=totalcode.length+3;i<=urltotalCode.length;i+=3){
       			var mark ="[totalcode="+urltotalCode.substr(0,i)+"]";
       			if(!chageNodeByMark(mark)){
       				break;
       			}
       		}
       		
       }
       
       function chageNodeByMark(mark){
       		var $t =$(mark);
       		if($t.html()!=undefined){
       			changNodeByObj($t);
       			return true;
       		}else{
       			return false;
       		}
       }
        
        function removeAct($t){
        	$(".act").removeClass("act");
        	$t.attr("class","act");
        }
	
		function initNode(){
        
        	$.ajax({  
                    url : "/portal/base?cmd=findNode&token="+'<%=token%>'+'&totalCode='+'<%=totalCode%>'+'&ts='+new Date().getTime(),  
                    dataType : "json",  
                    type : "post",  
                    success : function(data) { 
                    	message = data['message'];
                    	$("#nodePage").html(message);
                    	setMenuHeigth($(document.body).height());
                    	initNodeByTotalCode();
                    } ,
                    error:function(XMLHttpRequest, textStatus, errorThrown){
						message ="访问超时！";
						alert(message);
					}
             });  
             
       }
       
        //window.onresize=function(){  
	         //changeFrameHeight("nodeFrame");  
	    // }
       
       	var page_heigth=500;
       	function changeFrameHeight(iframeId){
	        ifm.height=page_heigth;
	    }
	    
	    function setHeigth(heigth){
          	var div_height = heigth;//使iframe高度等于子网页高度
          	if(div_height>=init_heigth){
          		page_heigth=div_height;
          		changIframeHeigth(page_heigth);
          	}else{
          		changIframeHeigth(init_heigth);
          	}
 			
	    }
	    
	    function setMenuHeigth(heigth){
	    	var div_height = heigth;
	    	init_heigth=heigth;
	    	if(div_height>=page_heigth){
	    		changIframeHeigth(init_heigth);
	    	}
	    	
	    }
	    
	    function changIframeHeigth(parent_heigth){
 			$("iframe").height(parent_heigth);
 			window.parent.setHeigth(parent_heigth);
	    }

</script>
</html>