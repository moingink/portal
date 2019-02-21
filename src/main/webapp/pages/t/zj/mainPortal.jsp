<%@page import="com.yonyou.util.theme.ThemePath"%>
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
%>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>用友广信资金管理系统</title>
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
	href="<%=ThemePath.findPath(request,ThemePath.PORTAL_CSS)%>">
<link rel="stylesheet" type="text/css"
	href="<%=ContextPath%>/js/bootTable.js">
<!-- bootstrapIE8补丁 -->
<script src="<%=ContextPath%>/vendor/bootstrap/js/html5.js"></script>
<script src="<%=ContextPath%>/vendor/bootstrap/js/respond.js"></script>
<body id="body_div">

	<div class="index_topBox">
		<div class="container">
			<div class="index_top">
				<div class="row">
					<div class="col-xs-4 hidden-xs">
						<span class="glyphicon glyphicon-star txt_red"></span>收藏用友广信资金管理系统
					</div>
					<div class="col-xs-8">
						<ul class="clearfix pull-right">
							<li style="color: red; width: 140px;"><%=loginName%></li>
							<li class="hidden-md hidden-xs">您好，欢迎来到用友广信资金系统</li>
							<li id="companyLi" class="hidden-xs"><%=corpName%></li>
							<li style="width: 50px"><a
								href="<%=request.getContextPath()%>/login/signOut?token=<%=token%>">退出</a></li>
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
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="logo_img" href="#"><img
							src="<%=ThemePath.findPath(request, ThemePath.PORTAL_LOGO)%>"></a>
					</div>

					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<form class="navbar-form navbar-left" role="search">
							<div class="form-group">
								<input type="text" id="search" class="form-control"
									placeholder="Search">
							</div>
							<!-- <button type="submit" class="btn btn-default">Submit</button> -->
						</form>
						<ul class="nav navbar-nav navbar-right">
							<li>
								<dl>
									<dt>
										<span class="glyphicon glyphicon-user"></span>
									</dt>
									<dd>
										<a href="javascript:openWindow()">在线交流</a>
									</dd>
								</dl>
							</li>
							<li>
								<dl>
									<dt>
										<span class="glyphicon glyphicon-home"></span>
									</dt>
									<dd>
										<a href="#" onclick="showModal()">公司切换</a>
									</dd>
								</dl>
							</li>
							
							<li>
								<dl>
									<dt>
										<span class="glyphicon glyphicon-home"></span>
									</dt>
									<dd>
										<a href="#" onclick="showTheme()">主题切换</a>
									</dd>
								</dl>
							</li>
							<li>
								<dl>
									<dt>
										<span class="glyphicon glyphicon-pencil"></span>
									</dt>
									<dd>
										<a href="#">密码修改</a>
									</dd>
								</dl>
							</li>
							<li>
								<dl>
									<dt>
										<span class="glyphicon glyphicon-question-sign"></span>
									</dt>
									<dd>
										<a href="#">帮助</a>
									</dd>
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
					</div>
					<!-- /.navbar-collapse -->
				</div>
				<!-- /.container-fluid -->
			</nav>
		</div>
	</div>
	<div class="nav_box">
		<div id='menuDiv' class="container">
			<ul class="clearfix">
				<li id="workLi" class="li_act" onclick="changWorkbench(this)"><a
					href="#">个人工作台</a></li>
				<div id="First_level_Menu"></div>

			</ul>

		</div>
	</div>


	<iframe id="iframepage"
		src="<%=ThemePath.findPath(request, ThemePath.PORTAL_WORK)%>?token=<%=token%>&ts=<%=new Date().getTime()%>"
		width="100%" scrolling="no" frameborder="0"></iframe>
	<!-- 底部版权 -->
	<div class="footer">
		<div class="container">
			<div class="table_cell">
				<ul class="clearfix">
					<li>法律声明|站点导航|联系我们</li>
				</ul>
			</div>
			<p>用友广信版权所有</p>
		</div>
	</div>




	<div class="container modal fade" id="myModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">切换公司</h4>
				</div>
				
				<div class="modal-body">
					<div class="cell">
						<%=selecthtml%>
					</div>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-inverse" data-dismiss="modal">关闭
					</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	
	<div class="container modal fade" id="myTheme" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">切换主题</h4>
				</div>
				
				<div class="modal-body">
					<div class="cell">
						<select class='form-control' id='themeCode' onchange="updateTheme(this)">
							<option value='zw'>默认</option>
							<option value='zw'>深蓝</option>
							<option value='zj'>蓝白</option>
						
						 </select>
					</div>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-inverse" data-dismiss="modal">关闭
					</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	
	<iframe src="" id="online-talk" class="online-talk"></iframe>
</body>
<script type="text/javascript">
	$("#company_id").tinyselect();
	//{ showSearch: false }取消过滤功能

	
	
	    $('#search').bind('keypress',function(event){ 
         if(event.keyCode == 13){  
             event.preventDefault();
         	}  
     	});
     	
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
		
		  $(function() { 
            	$("#selectHtml").html("<%=selecthtml%>");
           		initMenu($("#company_id").val(),$("#company_id option:checked").text());
           }); 
           
		  function showModal(){
		  		$("#myModal").modal({show:true}); 
		  }

  		  function b64Encode(str) { return btoa(encodeURIComponent(str)); }
  
		  function changMenu(t){
		  		
		  		changMenuByObj($(t),"");
		  }
		  
		  function changMenuByObj($t,UrlParam){
		  		
		  		var totalCode =$t.attr("totalcode");
		  		var nodeUrl ='<%=ThemePath.findPath(request, ThemePath.PORTAL_NODE)%>'+'?totalCode='+totalCode+UrlParam+'&token='+'<%=token%>'+'&ts='+new Date().getTime();
		  		$("#iframepage").attr("src",nodeUrl);
		  		clearMenuLable($t);
		  }
  
		  function clearMenuLable($t){
		  		$("#menuDiv li").removeClass("li_act");
		  		var totalcode =$t.attr("totalcode");
		  		if(totalcode!=null){
		  			if(totalcode.length>6){
		  				$("a[totalcode='"+totalcode.substring(0,6)+"']").parent().addClass('li_act');
		  			}
		  			$t.parent().addClass('li_act');
		  		}else{
		  			$t.attr('class','li_act');
		  		}
		  		
		  }
  
		  function changWorkbench(t){
			  	var url='<%=ThemePath.findPath(request, ThemePath.PORTAL_WORK)%>'+'?token='+'<%=token%>';
			  	$("#iframepage").attr("src",url);
			  	page_heigth=0;
			  	clearMenuLable($(t));
		  }
  
  		  function b64Decode(str) { return decodeURIComponent(str); }
		
	      function initMenu(companyid,companyname){
	        	loadingMenu();
	        	var paramJson = {
           		 'companyid':companyid,
            	 'companyname':companyname
        		};
	        	$.ajax({  
	                    url : "/portal/base?cmd=findMenu&token="+'<%=token%>'+'&ts='+new Date().getTime(),  
	                    dataType : "json",  
	                    type : "post",
	                    data : paramJson,
	                    success : function(data) { 
	                    	message = data['message'];
	                    	$("#First_level_Menu").html(message);
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
            
         
	        function changeMenu(){
	        	$("#myModal").modal('hide'); 
	        	$("#companyLi").html($("#company_id option:checked").text());
	        	initMenu($("#company_id").val(),$("#company_id option:checked").text());
	        	var url='<%=ThemePath.findPath(request, ThemePath.PORTAL_WORK)%>'+'?token='+'<%=token%>';
	  			$("#iframepage").attr("src",url);
	  			clearMenuLable($("#workLi"));
	  			clearInput();
	        }
	        
	        function clearInput(){
	  			$(".inputText").val("-");
	  			
	        }
	        
	        function loadingMenu(){
	        	$("#First_level_Menu").html("<li><font color='white'>loading...</font><li>");
	        }
            
            
		
			var page_heigth=0;
			
			
	
		    function changeFrameHeight(iframeId){
		        var ifm= document.getElementById(iframeId); 
		        if(page_heigth==0){
		        	page_heigth=document.documentElement.clientHeight*0.70;
		        }
		        ifm.height=page_heigth;
		    }
		    
		    var page_heigth=0;
		    
		    function setHeigth(heigth){
		    	page_heigth=heigth+10;
	            $("iframe").height(page_heigth);
		    }
		    
			function openWindow(){
	            var frame = $('#online-talk');
	            if(frame.width()<5){
	             var url = "http://123.103.9.221:8003/web/cmcc/index.jsp";
	           frame.animate({width:"400px"},300);
	            frame.attr('src',url);
	            }
	            else{
	             frame.animate({width:"0"},300);
	            frame.attr('src','');
	            }
	           
	            
			}	
			$(document).on('click',function(){
				hiddenFrame();
			})
			
			function hiddenFrame(){
				var frame = $('#online-talk');
	            if(frame.width()>5){
	            	frame.animate({width:"0"},300);
	            	frame.attr('src','');
	            }
			}
			
			$("nav li").css("margin-left","10px");
			
			function updateTheme(t){
	        	var paramJson = {
           		 'themeCode':$(t).val()
        		};
	        	$.ajax({  
	                    url : "/portal/base?cmd=findTheme&token="+'<%=token%>'+'&ts='+new Date().getTime(),  
	                    dataType : "json",  
	                    type : "post",
	                    data : paramJson,
	                    success : function(data) { 
	                    	toHref();
	                    } ,
	                    error:function(XMLHttpRequest, textStatus, errorThrown){
							message ="主题切换失败";
							alert(message);
						}
	             });  
	             
	        }
	        
	        function showTheme(){
		  		$("#myTheme").modal({show:true}); 
		    }
		    
		     function toHref(){
		    	window.location.href="<%= ThemePath.findMainPath(request)%>";
		    }
  	</script>
</html>