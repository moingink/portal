<%@page import="com.yonyou.util.wsystem.service.ORG"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String loginName = (String) request.getAttribute("loginName");
	String corpName = (String) request.getAttribute("corpname");
	String token = (String) request.getAttribute("token");
	String corpId = (String) request.getAttribute("corpid");
	String userId = (String) request.getAttribute("userid");
	String ContextPath =request.getContextPath();
%>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>门户</title>
</head>
<link rel="stylesheet"
	href="<%=ContextPath%>/pages/js/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=ContextPath%>/pages/js/bootstrap-table/src/bootstrap-table.css">
<link rel="stylesheet"
	href="<%=ContextPath%>/pages/js/examples.css">
<link rel="stylesheet"
	href="<%=ContextPath%>/pages/tree/themes/default/style.min.css" />
<link rel="stylesheet" type="text/css" href="<%=ContextPath%>/css/css01.css">
<script
	src="<%=ContextPath%>/vendor/jquery/jquery-1.11.2.min.js"></script>
<script src="<%=ContextPath%>/pages/tree/jstree.min.js"></script>
<body>
		
		<div class="index_topBox">
			<div class="container">
				<div class="index_top">
					<div class="row">
					  <div class="col-xs-4 hidden-xs"><span class="glyphicon glyphicon-star txt_red"></span>收藏用友广信资金管理系统</div>
					  <div class="col-xs-8">
					  	<ul class="clearfix pull-right">
					  		<li class="hidden-md hidden-xs">您好，欢迎来到用友广信资金系统</li>
					  		<li style="color:red;width:60px;"><%=loginName %></li>
					  		<li class="hidden-xs"><div id="selectHtml"></div></li>
					  		<li style="width:50px"><a href="<%=request.getContextPath()%>/login/signOut?token=<%=token%>">退出</a></li>
					  	</ul>
					  </div>
					</div>
				</div>
			</div>
		</div>

			<div class="navbar_box">
				<div class="container">
					<nav class="navbar navbar-default">
					  <div class="container-fluid">
					    <!-- Brand and toggle get grouped for better mobile display -->
					    <div class="navbar-header">
					      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					        <span class="sr-only">Toggle navigation</span>
					        <span class="icon-bar"></span>
					        <span class="icon-bar"></span>
					        <span class="icon-bar"></span>
					      </button>
					      <a class="logo_img" href="#"><img src="<%=ContextPath%>/images/logo.png"></a>
					    </div>
			
					    <!-- Collect the nav links, forms, and other content for toggling -->
					    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					      <a class="navbar-brand" href="#">用友广信资金管理系统</a>
					      
					      <form class="navbar-form navbar-left" role="search">
					        <div class="form-group">
					          <input type="text" class="form-control" placeholder="Search">
					        </div>
					        <!-- <button type="submit" class="btn btn-default">Submit</button> -->
					      </form>
					      <ul class="nav navbar-nav navbar-right">
					        <li>
					        	<dl>
					        		<dt><span class="glyphicon glyphicon-user"></span></dt>
					        		<dd><a href="">在线交流</a></dd>
					        	</dl>
					        </li>
					        <li>
					        	<dl>
					        		<dt><span class="glyphicon glyphicon-home"></span></dt>
					        		<dd><a href="">公司切换</a></dd>
					        	</dl>
					        </li>
					        <li>
					        	<dl>
					        		<dt><span class="glyphicon glyphicon-pencil"></span></dt>
					        		<dd><a href="">密码修改</a></dd>
					        	</dl>
					        </li>
					        <li>
					        	<dl>
					        		<dt><span class="glyphicon glyphicon-question-sign"></span></dt>
					        		<dd><a href="">帮助</a></dd>
					        	</dl>
					        </li>
					        <!-- <li class="dropdown">
					          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
					          <ul class="dropdown-menu">
					            <li><a href="#">Action</a></li>
					            <li><a href="#">Another action</a></li>
					            <li><a href="#">Something else here</a></li>
					            <li role="separator" class="divider"></li>
					            <li><a href="#">Separated link</a></li>
					          </ul>
					        </li> -->
					      </ul>
					    </div><!-- /.navbar-collapse -->
					  </div><!-- /.container-fluid -->
					</nav>
				</div>
			</div>
			<div class="nav_box">
				<div class="container">
					<ul class="clearfix">
						<li class="li_act"><a href="">个人工作台</a></li>
						<li><a href="">账户管理</a></li>
						<li><a href="">资金挑拨</a></li>
						<li><a href="">资金支付</a></li>
						<li><a href="">银企对账</a></li>
						<li><a href="">票据管理</a></li>
						<li><a href="">营收归集</a></li>
						<li><a href="">资金预算</a></li>
					</ul>
				</div>
			</div>
			<!-- 轮播图 -->
			<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
			  <!-- Indicators -->
			  <ol class="carousel-indicators">
			    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
			    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
			    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
			  </ol>
			
			  <!-- Wrapper for slides -->
			  <div class="carousel-inner" role="listbox">
			    <div class="item active">
			      <img src="<%=ContextPath%>/images/lunbo_1.jpg" alt="...">
			      <div class="carousel-caption">
			        
			      </div>
			    </div>
			    <div class="item">
			      <img src="<%=ContextPath%>/images/lunbo_1.jpg" alt="...">
			      <div class="carousel-caption">
			        
			      </div>
			    </div>
			    <div class="item">
			      <img src="<%=ContextPath%>/images/lunbo_1.jpg" alt="...">
			      <div class="carousel-caption">
			        
			      </div>
			    </div>
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
			  			<a href="" class="pull-right">更多</a>
			  		</header>
			  		<ul>
			  			<li>
			  				<span>【待阅】</span>
			  				<span>哈哈哈哈哈哈哈醉了</span>
						</li>
						<li>
			  				<span>【待阅】</span>
			  				<span>哈哈哈哈哈哈哈醉了</span>
						</li>
						
						<li>
			  				<span>【待办】</span>
			  				<span>哈哈哈哈哈哈哈醉了</span>
						</li>
						<li>
			  				<span>【待办】</span>
			  				<span>哈哈哈哈哈哈哈醉了</span>
						</li>
			  		</ul>
			  	</div>
			  	<div class="messagelog">
			  		<header>
			  			<span class="glyphicon glyphicon-volume-up"></span>
			  			<span>消息</span>
			  			<a href="" class="pull-right">更多</a>
			  		</header>
			  		<ul>
			  			<li>
			  				<span>2016-6-1</span>
			  				<span>哈哈哈哈哈哈哈醉了哈哈哈哈哈哈哈醉了哈哈哈哈哈哈哈醉了</span>
						</li>
						<li>
			  				<span>2016-6-6</span>
			  				<span>哈哈哈哈哈哈哈醉了</span>
						</li>
						<li>
			  				<span>2016-6-16</span>
			  				<span>哈哈哈哈哈哈哈醉了</span>
						</li>
						
			  		</ul>
			  	</div>
			  </div>
			  <!-- 轮播右边模块 -->
			  <div class="carousel_r">
			  	<div class="backlog">
			  		<ul>
			  			<li>
				        	<dl>
				        		<dt><img src="<%=ContextPath%>/images/indexIcon_0.png" alt=""></dt>
				        		<dd><a href="">账户查询</a></dd>
				        	</dl>
				        </li>
						<li>
				        	<dl>
				        		<dt><img src="<%=ContextPath%>/images/indexIcon_1.png" alt=""></dt>
				        		<dd><a href="">账户查询</a></dd>
				        	</dl>
				        </li>
				        <li>
				        	<dl>
				        		<dt><img src="<%=ContextPath%>/images/indexIcon_2.png" alt=""></dt>
				        		<dd><a href="">账户查询</a></dd>
				        	</dl>
				        </li>
				        <li>
				        	<dl>
				        		<dt><img src="<%=ContextPath%>/images/indexIcon_3.png" alt=""></dt>
				        		<dd><a href="">账户查询</a></dd>
				        	</dl>
				        </li>
				        <li>
				        	<dl>
				        		<dt><img src="<%=ContextPath%>/images/indexIcon_4.png" alt=""></dt>
				        		<dd><a href="">账户查询</a></dd>
				        	</dl>
				        </li>
				        <li>
				        	<dl>
				        		<dt><img src="<%=ContextPath%>/images/indexIcon_5.png" alt=""></dt>
				        		<dd><a href="">账户查询</a></dd>
				        	</dl>
				        </li>
				        <li>
				        	<dl>
				        		<dt><img src="<%=ContextPath%>/images/indexIcon_6.png" alt=""></dt>
				        		<dd><a href="">账户查询</a></dd>
				        	</dl>
				        </li>
				        <li>
				        	<dl>
				        		<dt><img src="<%=ContextPath%>/images/indexIcon_7.png" alt=""></dt>
				        		<dd><a href="">账户查询</a></dd>
				        	</dl>
				        </li>
				        <li>
				        	<dl>
				        		<dt><img src="<%=ContextPath%>/images/indexIcon_8.png" alt=""></dt>
				        		<dd><a href="">账户查询</a></dd>
				        	</dl>
				        </li>
				        <li>
				        	<dl>
				        		<dt><img src="<%=ContextPath%>/images/indexIcon_9.png" alt=""></dt>
				        		<dd><a href="">账户查询</a></dd>
				        	</dl>
				        </li>
				        <li>
				        	<dl>
				        		<dt><img src="<%=ContextPath%>/images/indexIcon_10.png" alt=""></dt>
				        		<dd><a href="">账户查询</a></dd>
				        	</dl>
				        </li>
			  		</ul>
			  	</div>
			  </div>
			</div>
			<!-- 模块展示 -->
			<div class="container">
				<div class="item_box">
					<ul class="item_scroll clearfix">
						<li>
							<img src="<%=ContextPath%>/images/item_0.png" alt="">
						</li>
						<li>
							<img src="<%=ContextPath%>/images/item_1.png" alt="">
						</li>
						<li>
							<img src="<%=ContextPath%>/images/item_2.png" alt="">
						</li>
						<li>
							<img src="<%=ContextPath%>/images/item_3.png" alt="">
						</li>
						<li>
							<img src="<%=ContextPath%>/images/item_1.png" alt="">
						</li>
						<li>
							<img src="<%=ContextPath%>/images/item_2.png" alt="">
						</li>
					</ul>
					<p class="left_btn">&lt;</p>
					<p class="right_btn">&gt;</p>
				</div>
				
			</div>
			<!-- 底部版权 -->
			<div class="footer">
				<div class="container">
					<div class="table_cell">
						<ul class="clearfix">
							<li><a href="">新闻中心</a></li>
							<li><a href="">法律声明</a></li>
							<li><a href="">诚聘英才</a></li>
							<li><a href="">采购信息</a></li>
							<li><a href="">企业合作</a></li>
							<li><a href="">联系我们</a></li>
							<li><a href="">站点导航</a></li>
							<li><a href="">用友广信研究院</a></li>
							<li><a href="">用友广信设计院</a></li>
							<li><a href="">网站地图</a></li>
							<li><a href="">友情链接</a></li>
						</ul>
					</div>
					
					<p>用友广信版权所有</p>
					
				</div>
			</div>
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
           		//loadInitMenu($("#company_id").val(),$("#company_id option:checked").text());
           		initMenu($("#company_id").val(),$("#company_id option:checked").text());
            });  
            
        function initMenu(companyid,companyname){
        
        	$.ajax({  
                                url : "/portal/base?cmd=findMenu&token="+'<%=token%>'+'&companyid='+companyid+'&companyname='+companyname+'&ts='+new Date().getTime(),  
                                dataType : "json",  
                                type : "GET",  
                                success : function(data) {  
                                    if(data) {  
                                    	alert(data);
                                    }else{  
                                    }  
                                }  
                            });  
        }
            
         
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
        
		var aLi = $('.item_scroll li').clone(true);
		$('.item_scroll').append(aLi);
		var w=0;
		$('.item_scroll li').each(function(i,item){
			w+=$(item).outerWidth(true);
			i++;
			return w;
		});
		//console.log(w);
		$(".left_btn").click(function(){
			var scroll_x= $(".item_scroll").position().left;
			if(scroll_x>-w/2){
				scroll_x-=280;
				
			}else{
				scroll_x=0;
			}
			$(".item_scroll").animate({'left':scroll_x});
		})
		$(".right_btn").click(function(){
			var scroll_rx= $(".item_scroll").position().left;
			//console.log(scroll_rx);
			if(scroll_rx<0){
				scroll_rx+=280;
				
			}else{
				scroll_rx=-w/2;
			}
			$(".item_scroll").animate({'left':scroll_rx});
		}) 		
  	</script>
</html>