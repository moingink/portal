<%@page import="com.yonyou.util.wsystem.service.ORG"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String loginName = (String) request.getAttribute("loginName");
	String corpCode = (String) request.getAttribute("corpcode");
	String token = (String) request.getAttribute("token");
	String corpId = (String) request.getAttribute("corpid");
	String userId = (String) request.getAttribute("userid");
%>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<!-- 设置浏览器默认渲染模式 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="renderer" content="webkit">
<title>门户</title>
<jsp:include page="${ctx}/include/public.jsp"></jsp:include>
</head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/pages/js/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/pages/js/bootstrap-table/src/bootstrap-table.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/pages/js/examples.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/pages/tree/themes/default/style.min.css" />
<script
	src="<%=request.getContextPath()%>/vendor/jquery/jquery-1.11.2.min.js"></script>
<script src="<%=request.getContextPath()%>/pages/tree/jstree.min.js"></script>
<!-- bootstrapIE8补丁 -->
<script src="<%=request.getContextPath()%>/vendor/bootstrap/js/html5.js"></script>
<script src="<%=request.getContextPath()%>/vendor/bootstrap/js/respond.js"></script>
<body>
	<form>
		
		<div class="col-md-12">
			<div class="col-md-2">
				<div class="col-md-8">
				<h4>欢迎：<%=loginName%></h4> 
				</div>
				
				<div class="col-md-4">
					<h5>
					<a href="<%=request.getContextPath()%>/login/signOut?token=<%=token%>">退出</a>
					</h5>
				</div>
				</br>
				<div id="selectHtml"></div>
				
			</div>
			<div class="col-md-10">
				<h4>
			  	 <img src="<%=request.getContextPath()%>/images/logo.png"  alt="Responsive image">
			  	 <img src="<%=request.getContextPath()%>/images/logotitle1.png"  alt="Responsive image">
				</h4>
				<h2>用友广信资金管理系统门户</h2>
			</div>
		</div>
		<div class="col-md-12">

			<div class="col-md-2">
				<ul id="myTab1" class="nav nav-pills nav-stacked">
					<li>
						<div id="jstree_div"></div>
					</li>
				</ul>
			</div>
			<div class="col-md-10">
				<div class="tab-content">
					<div class="tab-pane fade in active" id='message'>
						<iframe id="menucontent" src="/portal/pages/default.html"
							scrolling="yes" frameborder="0" width="100%" height="2000"></iframe>
					</div>
				</div>
			</div>

		</div>
	</form>
</body>
<script type="text/javascript">
		

  function b64Encode(str) { return btoa(encodeURIComponent(str)); }

  function b64Decode(str) { return decodeURIComponent(str); }
		
		
    	   function  onclickmenu(str){
           
				context=str.split("/")[1];
           	 	$("#menucontent").attr("src",str);
           }
           
            $(function() { 
            	$("#selectHtml").html("<%=request.getAttribute("selecthtml")%>");
           		loadInitMenu($("#company_id").val(),$("#company_id option:checked").text());
            });  
            
         
        function changeMenu(){
        	$('#jstree_div').data('jstree', false).empty();
        	loadInitMenu($("#company_id").val(),$("#company_id option:checked").text());
        	$("#menucontent").attr("src","/portal/pages/default.html");
        }
            
            
        function loadInitMenu(companyid,companyname){
        	
        	 $('#jstree_div').jstree({  
                'core' : {  
                    'check_callback': true,  
                    "data" : function (obj, callback){  
                            $.ajax({  
                                url : "/portal/base?cmd=findMenu&token="+'<%=token%>'+'&companyid='+companyid+'&companyname='+companyname+'&ts='+new Date().getTime(),  
                                dataType : "json",  
                                type : "GET",  
                                success : function(data) {  
                                    console.info(data); 
                                    if(data) {  
                                        callback.call(this, data);  
                                    }else{  
                                        $("#jstree_div").html("暂无数据！");  
                                    }  
                                }  
                            });  
                    }  
                },  
                //"plugins" : ["sort"]  
            }).bind("select_node.jstree", function(event, data) {  
                var inst = data.instance;  
                
                var selectedNode = inst.get_node(data.selected);
                console.log(data.selected);
                console.log(selectedNode.data["url"]);
                var url = selectedNode.data["url"];
               	if(""!=url){
               		x = document.getElementById("menucontent");
               		if(url.indexOf("?")!=-1){
               			x.src = url+"&token="+'<%=token%>'+'&ts='+new Date().getTime();
               		}else{
               			x.src = url+"?token="+'<%=token%>'+'&ts='+new Date().getTime();
               		} 
               		
               		
        			
               	}
            });  
        
        }
        
      
        function toDetail(data){
        	//alert(data.id);
        	x = document.getElementById("menucontent");
        	x.src = "menuDetail.jsp?pageCode=RM_FUNCTION_NODE&menuCode=DEMO&_dataSourceCode=RM_FUNCTION_NODE&ParentPKField=&ParentPKValue="+data.id+"&isReadonly=1";
        }
        
        function loadConfig(inst, selectedNode){  
			alert(selectedNode.data);
			alert(selectedNode.text);
			alert(selectedNode.id);
            var temp = selectedNode.id;  
            //inst.open_node(selectedNode);  
            //alert(temp);  
            $.ajax({  
                url : "/system/copyOfParty/ajax?PARENT_PARTY_CODE='"+temp+"' ",  
                dataType : "json",  
                type : "GET",  
                success : function(data) {  
                    if(data) { 
                       selectedNode.children = [];  
                       $.each(data, function (i, item) {  
                                var obj = {text:item};  
                                //$('#jstree_div').jstree('create_node', selectedNode, obj, 'last');  
                                inst.create_node(selectedNode,item,"last");  
                       });  
                       inst.open_node(selectedNode);  
                    }else{  
                        $("#jstree_div").html("暂无数据！");  
                    }  
                }  
            });  
        }    		
  	</script>
</html>