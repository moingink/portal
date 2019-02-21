<%@page import="com.yonyou.util.wsystem.service.ORG"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<meta charset="utf-8">
		<title>门户</title>
		<jsp:include page="${ctx}/include/public.jsp"></jsp:include>
	</head>
<script src="<%=request.getContextPath()%>/vendor/bootstrap/js/tree.js"></script>    
	<body>
		<form>
			<div class="page-header">
				<h1>门户<small>,登录人:<%=request.getAttribute("loginUser")%></small></h1>
			</div>					
			<div class="col-md-2">
				<ul id="myTab1" class="nav nav-pills nav-stacked">
			          <li >
			          	<div id="treeview1" ></div>
			          </li>
		         </ul>
			</div>
			<div class="col-md-10">
				<div class="tab-content">
					<div class="tab-pane fade in active" id='message'>
							<iframe id="menucontent" src="/portal/pages/default.html" scrolling="yes" frameborder="0"  width="100%" height="2000"></iframe>
					</div>
				</div>
			</div>			
		</form>
	</body>
		<script type="text/javascript">
		

  function b64Encode(str) { return btoa(encodeURIComponent(str)); }

  function b64Decode(str) { return decodeURIComponent(str); }
		
		
           function  onclickmenu(str){
           
            //var url = 'http://127.0.0.1:8088/account/pages/singleTableModify.jsp?pageCode=AM_ACTUAL_ACCNTINFO&pageName=银行账户查询';  
			//var bigData = '1111';  
			//var html = '<form action="'+str+'" method="post" target="_self" id="postData_form">'+  
          	//	 '<input id="bigData" name="bigData" type="hidden" value="'+bigData+'"/>'+  
          	//	 '<input id="rolecode" name="rolecode" type="hidden" value="'+rolecode+'"/>'+  
          	//	 '<input id="corpcode" name="corpcode" type="hidden" value="'+corpcode+'"/>'+  
          	//	 '<input id="token" name="token" type="hidden" value="'+token+'"/>'+  
           	//	'</form>';  
  //alert(b64Decode('UEsDBBQACAgIAKZTb0oAAAAAAAAAAAAAAAAEAAAAZGF0YUsrzUsuyczPAwBQSwcIPRauygoAAAAIAAAAUEsBAhQAFAAICAgAplNvSj0WrsoKAAAACAAAAAQAAAAAAAAAAAAAAAAAAAAAAGRhdGFQSwUGAAAAAAEAAQAyAAAAPAAAAAAA'));
    //alert(decryptedString());     
			//document.getElementById('menucontent').contentWindow.document.write(html);  
			//document.getElementById('menucontent').contentWindow.document.getElementById('postData_form').submit(); 
			context=str.split("/")[1];
            $("#menucontent").attr("src",str);
           }  		
  	</script>  	
</html>