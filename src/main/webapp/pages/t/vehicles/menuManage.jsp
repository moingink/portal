<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>审批工作台</title>

<!-- <script src="/portal/vendor/jquery/jquery-1.11.2.min.js"></script>
<script src="/portal/pages/js/bootstrap/js/bootstrap.min.js"></script>
<script src="/portal/pages/tree/jstree.min.js"></script>
<script src="/portal/pages/js/tinyselect.js"></script>
<link rel="stylesheet" href="/portal/pages/js/tinyselect.css">
<link rel="stylesheet" type="text/css" href="/portal/css/style.css">
<link rel="stylesheet prefetch"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="/portal/pages/js/bootstrap-table/src/bootstrap-table.css">
<link rel="stylesheet" href="/portal/pages/js/examples.css">
<link rel="stylesheet"
	href="/portal/pages/tree/themes/default/style.min.css">
<script src="/portal/vendor/bootstrap/js/html5.js"></script>
<script src="/portal/vendor/bootstrap/js/respond.js"></script>
<link rel="stylesheet" type="text/css"
	href="/portal/vendor/vehicles/css/resert.css">
<link rel="stylesheet" type="text/css"
	href="/portal/vendor/vehicles/css/header.css">
<link rel="stylesheet" type="text/css"
	href="/portal/vendor/vehicles/css/customer.css"> -->
<script src="../../js/elevator/LiftEffect.js" ></script>
<script src="../../js/elevator/jquery.min.js" ></script>	
</head>
<body>
<%-- <form>

		<div class="tabbable" id="tabs-146746">
				<div class="tab-content">
					<div class="tab-pane active" id="panel-847015">
						<div class="col-md-2 col-xs-2 col-sm-2" style="width: 10%;">
							<ul id="myTab1" class="nav nav-pills nav-stacked">
					          <li >
					          	  <div id="jstree_div">
					          	  </div>
					          </li>
		       				</ul>
						</div>
				
						<div class="col-md-10 col-xs-10 col-sm-10"  style="    width: 90%;">
						<div class="tab-content">
							<div class="tab-pane fade in active" id='message'>
									<iframe id="iframe" src="/workflow/pages/singleTableModify.jsp?pageCode=VIEW_WF_NEED_HANDLE_COMMIT&pageName=发起列表&ParentPKField=NEED_USER_ID&ParentPKValue=<%= request.getParameter("userid") %>" scrolling="yes" frameborder="0"  width="99%" height="100%"></iframe>
							</div>
						</div>
						</div>
			
					</div>
				</div>
			</div>

						
			
		</form> --%>
	
	<style>
		*{
			margin: 0;
			padding: 0;
		}
		body{
			height: 6000px;
		}
		.lift-nav{
			position: fixed;
			top: 100px;
			left: 30px;
			display: none;
		}
		.lift-nav li{
			width: 80px;
			height: 30px;
			text-align: center;
			line-height: 30px;
			color: #fff;
			padding: 10px 10px;
			margin-bottom: 10px;
			background: skyblue;
			cursor: pointer;
		}

		.lift-nav li.current{
			background: tomato;
		}

		.t1,.t2,.t3,.t4,.t5{
			width: 800px;
			height: 400px;
			text-align: center;
			line-height: 400px;
			background: skyblue;
			margin: 100px auto;
			font-size: 30px;
			color: #fff;
		}
		.t1{
			margin-top: 200px;
		}
		.t2{
			background: pink;
		}
		.t3{
			background: tomato;
		}
		.t4{
			background: grey;
		}
		.t5{
			background: yellow;
		}
		
		
	</style>
</head>
<body>

<div class="lift-nav">
	<ul class="lift">
		<li>我的发起</li>
		<li>我的待办</li>
		<li>我的已办</li>
		<li>我的办结</li>
		<li>委托代办</li>
		
	</ul>
</div>

<div class="lift-target">
	<div class="t1"><iframe width="100%" height="100%" src="/workflow/pages/singleTableModify.jsp?pageCode=VIEW_WF_NEED_HANDLE_COMMIT&pageName=发起列表&ParentPKField=NEED_USER_ID&ParentPKValue=<%= request.getParameter("userid") %>"></iframe></div>
	<div class="t2"><iframe width="100%" height="100%" src="/workflow/pages/singleTableModify.jsp?pageCode=VIEW_WF_NEED_HANDLE&pageName=待办列表&ParentPKField=NEED_USER_ID&ParentPKValue=<%= request.getParameter("userid") %>"></iframe></div>
	<div class="t3"><iframe width="100%" height="100%" src="/workflow/pages/singleTableModify.jsp?pageCode=VIEW_WF_NEED_HANDLE_DONE&pageName=已办列表&ParentPKField=NEED_USER_ID&ParentPKValue=<%= request.getParameter("userid") %>"></iframe></div>
	<div class="t4"><iframe width="100%" height="100%" src="/workflow/pages/singleTableModify.jsp?pageCode=VIEW_WF_NEED_HANDLE_FINISH&pageName=办结列表&ParentPKField=view_wf_need_handle.NEED_USER_ID&ParentPKValue=<%= request.getParameter("userid") %>"></iframe></div>
	<div class="t5"><iframe width="100%" height="100%" src="/workflow/pages/childTableModifyForProcessEntrust.jsp?user_id=<%= request.getParameter("userid") %>"></iframe></div>
	
</div>


<script>

	$(function(){
		LiftEffect({
			"control1": ".lift-nav", 						  //侧栏电梯的容器
			"control2": ".lift",                           //需要遍历的电梯的父元素
			"target": [".t1",".t2",".t3",".t4",".t5"], //监听的内容，注意一定要从小到大输入
			"current": "current" 						  //选中的样式
		});

		
	})
</script>
		  
    <script type="text/javascript">
         $(function() { 
            $('#jstree_div').jstree({
                'core' : {
                    'check_callback': true,  
                    "data" : [    
                       { "id" : "menu0", "parent" : "#", "text" : "审批工作台"  },    
                       { "id" : "menu1", "parent" : "menu0", "text" : "我的发起","url":"/workflow/pages/singleTableModify.jsp?pageCode=VIEW_WF_NEED_HANDLE_COMMIT&pageName=发起列表&ParentPKField=NEED_USER_ID&ParentPKValue=<%= request.getParameter("userid") %>" },    
                       { "id" : "menu2", "parent" : "menu0", "text" : "我的待办","url":"/workflow/pages/singleTableModify.jsp?pageCode=VIEW_WF_NEED_HANDLE&pageName=待办列表&ParentPKField=NEED_USER_ID&ParentPKValue=<%= request.getParameter("userid") %>" },   
                       { "id" : "menu3", "parent" : "menu0", "text" : "我的已办","url":"/workflow/pages/singleTableModify.jsp?pageCode=VIEW_WF_NEED_HANDLE_DONE&pageName=已办列表&ParentPKField=NEED_USER_ID&ParentPKValue=<%= request.getParameter("userid") %>" },   
                       { "id" : "menu4", "parent" : "menu0", "text" : "我的办结","url":"/workflow/pages/singleTableModify.jsp?pageCode=VIEW_WF_NEED_HANDLE_FINISH&pageName=办结列表&ParentPKField=view_wf_need_handle.NEED_USER_ID&ParentPKValue=<%= request.getParameter("userid") %>" },   
                       { "id" : "menu5", "parent" : "menu0", "text" : "委托代办","url":"/workflow/pages/childTableModifyForProcessEntrust.jsp?user_id=<%= request.getParameter("userid") %>" }
                      ]

                }
            }).bind("select_node.jstree", function(event, data) {  
                var inst = data.instance;  
                var selectedNode = inst.get_node(data.selected);
                $("#iframe").attr("src",selectedNode.original.url);
            }).bind("loaded.jstree", function (event, data) {
				$("#menu0_anchor").dblclick();
			});
        });  
      
    </script>  
    
</body>
</html>