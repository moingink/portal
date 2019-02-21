package org.apache.jsp.pages.t.vehicles;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.yonyou.util.theme.ThemePath;
import com.yonyou.business.entity.TokenEntity;
import com.yonyou.business.entity.TokenUtil;
import com.yonyou.util.wsystem.service.ORG;
import java.util.Date;

public final class menuPage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<!DOCTYPE html>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", -10); 
	TokenEntity tokenEntity = TokenUtil.initTokenEntity(request);
	String ContextPath = request.getContextPath();
	String totalname=(String)request.getParameter("totalname");
	String totalcode=(String)request.getParameter("totalcode");
	String locationCode=(String)request.getParameter("locationCode");
	locationCode=locationCode==null?"":locationCode;
	String url=request.getParameter("url")==""?ContextPath+"/pages/t/vehicles/index.jsp":(String)request.getParameter("url")+"?token="+tokenEntity.getToken();
	String path=ContextPath+"/vendor/vehicles";

      out.write("\r\n");
      out.write("<html lang=\"zh-cn\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"utf-8\">\r\n");
      out.write("<!-- 设置浏览器默认渲染模式 -->\r\n");
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("<meta name=\"renderer\" content=\"webkit\">\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<script src=\"");
      out.print(ContextPath);
      out.write("/vendor/jquery/jquery-1.11.2.min.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(ContextPath);
      out.write("/pages/js/bootstrap/js/bootstrap.min.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(ContextPath);
      out.write("/pages/tree/jstree.min.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(ContextPath);
      out.write("/pages/js/tinyselect.js\"></script>\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(ContextPath);
      out.write("/pages/js/tinyselect.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(ContextPath);
      out.write("/css/style.css\"/>\r\n");
      out.write("<link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(ContextPath);
      out.write("/pages/js/bootstrap-table/src/bootstrap-table.css\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(ContextPath);
      out.write("/pages/js/examples.css\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(ContextPath);
      out.write("/pages/tree/themes/default/style.min.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(ContextPath);
      out.write("/js/bootTable.js\">\r\n");
      out.write("<!-- bootstrapIE8补丁 -->\r\n");
      out.write("<script src=\"");
      out.print(ContextPath);
      out.write("/vendor/bootstrap/js/html5.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(ContextPath);
      out.write("/vendor/bootstrap/js/respond.js\"></script>\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print( path);
      out.write("/css/resert.css\"/>\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print( path);
      out.write("/css/header.css\"/>\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print( path);
      out.write("/css/customer.css\"/>\r\n");
      out.write("\t<style>\r\n");
      out.write("\t\t#busPage{height:608px;}\r\n");
      out.write("\t\t/* .fadeInLeft.is-open{\r\n");
      out.write("\t\t\tbackground:red;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t.fadeInLeft.is-close{\r\n");
      out.write("\t\t\tbackground:green;\r\n");
      out.write("\t\t} */\r\n");
      out.write("\t</style>\r\n");
      out.write("\t<body >\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<div id=\"wrapper\">\r\n");
      out.write("\t\t<!-- <div class=\"overlay\"></div> -->\r\n");
      out.write("\r\n");
      out.write("\t\t<!-- Sidebar -->\r\n");
      out.write("\t\t<nav class=\"navbar navbar-inverse navbar-fixed-top\" id=\"sidebar-wrapper\" role=\"navigation\">\r\n");
      out.write("\t\t<ul class=\"nav sidebar-nav\" id=\"nodePage\">\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t</nav>\r\n");
      out.write("\t\t<!-- /#sidebar-wrapper -->\r\n");
      out.write("\r\n");
      out.write("\t\t<!-- Page Content -->\r\n");
      out.write("\t\t<div id=\"page-content-wrapper\">\r\n");
      out.write("\t\t\t<button type=\"button\" class=\"hamburger is-closed animated fadeInLeft\"\r\n");
      out.write("\t\t\t\tdata-toggle=\"offcanvas\">\r\n");
      out.write("\t\t\t\t<span class=\"hamb-top\"></span> \r\n");
      out.write("\t\t\t\t<!-- <span class=\"hamb-middle\"></span> --> \r\n");
      out.write("\t\t\t\t<span\r\n");
      out.write("\t\t\t\t\tclass=\"hamb-bottom\"></span>\r\n");
      out.write("\t\t\t</button>\r\n");
      out.write("\t\t\t<div class=\"contain\">\r\n");
      out.write("\t\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "headPage.jsp?isShow=0", out, false);
      out.write("\r\n");
      out.write("\t\t\t\t<div class=\"box-top2\">\r\n");
      out.write("\t\t\t\t\t<ol class=\"breadcrumb\" style=\"margin-bottom: 10px\" id =\"ol_title\">\r\n");
      out.write("\t\t\t\t\t  <li></li>\r\n");
      out.write("\t\t\t\t\t  <li >");
      out.print( totalname);
      out.write("</li>\r\n");
      out.write("\t\t\t\t\t</ol>\r\n");
      out.write("\t\t\t\t\t<iframe id='busPage' src=\"");
      out.print(url);
      out.write("\" width=\"100%\" border=\"0\" marginwidth=\"0\" marginheight=\"0\"  frameborder=\"0\" height='600px'></iframe>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!-- /#page-content-wrapper -->\r\n");
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("\t</body>\r\n");
      out.write("\t\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t$(function () {\r\n");
      out.write("\t\t\tif(screen.width <= 1280){\r\n");
      out.write("\t\t\t\t$('#busPage').height(570); \r\n");
      out.write("\t\t\t}else if(screen.width >= 1360 && screen.width <= 1366){\r\n");
      out.write("\t            $('#busPage').height(505);      \r\n");
      out.write("\t        }else if(screen.width > 1366){\r\n");
      out.write("\t            $('#busPage').height(608); \r\n");
      out.write("\t        }else{\r\n");
      out.write("\t           \r\n");
      out.write("\t        }\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tvar locationCode ='';\r\n");
      out.write("\t\t$(function() { \r\n");
      out.write("\t\t\tlocationCode='");
      out.print(locationCode);
      out.write("'\r\n");
      out.write("            initNode();\r\n");
      out.write("            initTrigger();\r\n");
      out.write("            if(isShowHeadImg==0){\r\n");
      out.write("            \t$(\".box-top2\").css(\"margin-top\",\"30px\");\r\n");
      out.write("            }\r\n");
      out.write("            loadIndexRole(\"\");\r\n");
      out.write("            document.title = '");
      out.print(totalname);
      out.write("';\r\n");
      out.write("         }); \r\n");
      out.write("       \t \r\n");
      out.write("       \t function openLable(){\r\n");
      out.write("       \t \tvar li = $('#nodePage').children('li');\r\n");
      out.write("\t        for(var i=0;i<li.length;i++){\r\n");
      out.write("\t        \tvar totalcode = $(li[i]).children('a').attr('totalcode');\r\n");
      out.write("\t            $('#'+totalcode).toggle();\r\n");
      out.write("\t       \t}\r\n");
      out.write("       \t }\r\n");
      out.write("       \t \r\n");
      out.write("       \t function reload(){\r\n");
      out.write("       \t \tif('");
      out.print( totalname);
      out.write("' == '客户管理'){\r\n");
      out.write("       \t \t\twindow.location.reload();\r\n");
      out.write("       \t \t}\r\n");
      out.write("       \t }\r\n");
      out.write("       \t \r\n");
      out.write("         function changeLabel(){\r\n");
      out.write("         \t$.ajax({\r\n");
      out.write("\t        \turl : \"/portal/base?cmd=findChildNode&token=\"+'");
      out.print(tokenEntity.getToken());
      out.write("'+'&totalCode='+'");
      out.print(totalcode);
      out.write("'+'&ts='+new Date().getTime(),  \r\n");
      out.write("\t            dataType : \"json\",  \r\n");
      out.write("\t            type : \"post\",  \r\n");
      out.write("\t            success : function(data) { \r\n");
      out.write("\t            \t\r\n");
      out.write("\t            },\r\n");
      out.write("\t            error:function(XMLHttpRequest, textStatus, errorThrown){\r\n");
      out.write("\t\t\t\t\tmessage =\"访问超时！\";\r\n");
      out.write("\t\t\t\t\talert(message);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t       });  \r\n");
      out.write("        }\r\n");
      out.write("\t\tvar showTotalCode =\"\";\r\n");
      out.write("\t\tvar $lastShow;\r\n");
      out.write("\t\t//nodePage\r\n");
      out.write("\t\tfunction initNode(){\r\n");
      out.write("\t        \r\n");
      out.write("\t        \t$.ajax({  \r\n");
      out.write("\t                    url : \"/portal/base?cmd=findNode&type=1&token=\"+'");
      out.print(tokenEntity.getToken());
      out.write("'+'&totalCode='+'");
      out.print(totalcode);
      out.write("'+'&ts='+new Date().getTime(),  \r\n");
      out.write("\t                    dataType : \"json\",  \r\n");
      out.write("\t                    type : \"post\",  \r\n");
      out.write("\t                    success : function(data) { \r\n");
      out.write("\t                    \tmessage = data['message'];\r\n");
      out.write("\t                        var html_='<li class=\"sidebar-brand\"><a onclick=\"reload()\" href=\"#\">");
      out.print( totalname);
      out.write(" </a></li>';\r\n");
      out.write("\t                    \t$(\"#nodePage\").html(html_+message);\r\n");
      out.write("\t                    \topenLable();\r\n");
      out.write("\t                    \tif(locationCode.length>0){\r\n");
      out.write("\t                    \t\tvar $location =$(\"[totalcode=\"+locationCode+\"]\");\r\n");
      out.write("\t                    \t\tif($location!=null){\r\n");
      out.write("\t\t                    \t\tif($location.attr(\"url\").length>0){\r\n");
      out.write("\t\t                    \t\t\tchangNode($location);\r\n");
      out.write("\t\t                    \t\t}else{\r\n");
      out.write("\t\t                    \t\t\tchangNode($(\"[parentcode=\"+locationCode+\"]:first\"));\r\n");
      out.write("\t\t                    \t\t}\r\n");
      out.write("\t                    \t\t}\r\n");
      out.write("\t                    \t}\r\n");
      out.write("\t                    } ,\r\n");
      out.write("\t                    error:function(XMLHttpRequest, textStatus, errorThrown){\r\n");
      out.write("\t\t\t\t\t\t\tmessage =\"访问超时！\";\r\n");
      out.write("\t\t\t\t\t\t\talert(message);\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t             });  \r\n");
      out.write("\t             \r\n");
      out.write("\t     }\r\n");
      out.write("\t     \r\n");
      out.write("\t     function changNodeForB(t){\r\n");
      out.write("\t\t\tvar totalcode=$(t).attr('totalcode');\r\n");
      out.write("\t\t\tonChangHead(totalcode);\r\n");
      out.write("\t     \tif($(\"#\"+totalcode)!=''&&$(\"#\"+totalcode)!=undefined){\r\n");
      out.write("\t     \t\t$(\"#\"+totalcode).toggle();\r\n");
      out.write("\t     \t}else{\r\n");
      out.write("\t     \t\tchangNode(t);\r\n");
      out.write("\t     \t}\r\n");
      out.write("\t     }\r\n");
      out.write("\t     \r\n");
      out.write("\t     function onChangHead(totalCode){\r\n");
      out.write("\t     \t//var $t =$('a[totalcode='+totalCode+']');\r\n");
      out.write("\t     \t//$(\".act_t1\").removeClass(\"act_t1\");\r\n");
      out.write("\t\t\t//$t.addClass(\"act_t1\");\r\n");
      out.write("\t     }\r\n");
      out.write("\t     \r\n");
      out.write("\t     \r\n");
      out.write("\t     function changNode(t){\r\n");
      out.write("\t\t\tvar url=$(t).attr(\"url\");\r\n");
      out.write("\t\t\tvar totalcode=$(t).attr('totalcode');\r\n");
      out.write("\t\t\tif(url.indexOf(\"?\")!=-1&&url.indexOf(\"menuCode\")!=-1){\r\n");
      out.write("\t\t\t\t//url+=\"&menuCode=\"+totalcode;\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t}else if(url.indexOf(\"?\")!=-1){\r\n");
      out.write("\t\t\t\turl+=\"&totalcode=\"+totalcode;\r\n");
      out.write("\t\t\t}else{\r\n");
      out.write("\t\t\t\turl+=\"?totalcode=\"+totalcode;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tvar totalname=$(t).attr('totalname');\r\n");
      out.write("\t\t\tvar parentcode=$(t).attr('parentcode');\r\n");
      out.write("\t\t\tvar parentname=$('a[totalcode='+parentcode+']').attr(\"totalname\");\r\n");
      out.write("\t\t\tvar tnsArrays=[];\r\n");
      out.write("\t\t\ttnsArrays[0]='");
      out.print(totalname);
      out.write("';\r\n");
      out.write("\t\t\ttnsArrays[1]=parentname;\r\n");
      out.write("\t\t\ttnsArrays[2]=totalname;\r\n");
      out.write("\t\t\tvar isPopover =$(t).attr(\"isPopover\");\r\n");
      out.write("\t\t\tdocument.title = totalname;\r\n");
      out.write("\t\t\tif(isPopover!=\"1\"){\r\n");
      out.write("\t\t\t\topenUrlByMenuPage(t,tnsArrays);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\twindow.top.__proto__.testtotalcode = totalcode;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfunction changNodeFour(t){\r\n");
      out.write("\t\t\tvar url=$(t).attr(\"url\");\r\n");
      out.write("\t\t\tvar tc=$(t).attr('totalcode');\r\n");
      out.write("\t\t\tif(url.indexOf(\"?\")!=-1&&url.indexOf(\"menuCode\")!=-1){\r\n");
      out.write("\t\t\t\t//url+=\"&totalcode=\"+totalcode;\r\n");
      out.write("\t\t\t}else if(url.indexOf(\"?\")!=-1){\r\n");
      out.write("\t\t\t\turl+=\"&totalcode=\"+tc;\r\n");
      out.write("\t\t\t}else{\r\n");
      out.write("\t\t\t\turl+=\"?totalcode=\"+tc;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tvar tn=$(t).attr('totalname');\r\n");
      out.write("\t\t\tvar ptc=$(t).attr('parentcode');\r\n");
      out.write("\t\t\tvar ptn=$('li[totalcode='+ptc+']').attr(\"totalname\");\r\n");
      out.write("\t\t\tvar pptc=$('li[totalcode='+ptc+']').attr(\"parentcode\");\r\n");
      out.write("\t\t\tvar pptn=$('span[totalcode='+pptc+']').attr(\"totalname\");\r\n");
      out.write("\t\t\tvar tnsArrays=[];\r\n");
      out.write("\t\t\ttnsArrays[0]='");
      out.print(totalname);
      out.write("';\r\n");
      out.write("\t\t\ttnsArrays[1]=pptn;\r\n");
      out.write("\t\t\ttnsArrays[2]=ptn;\r\n");
      out.write("\t\t\ttnsArrays[3]=tn;\r\n");
      out.write("\t\t\topenUrlByMenuPage(url,tnsArrays);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfunction openUrlByMenuPage(t,tns){\r\n");
      out.write("\t\t\tvar url=$(t).attr(\"url\");\r\n");
      out.write("\t\t\tvar totalcode=$(t).attr('totalcode');\r\n");
      out.write("\t\t\tif(url!=null&&url.length>0){\r\n");
      out.write("\t\t\t\t\t$(\".act_t2\").removeClass(\"act_t2\");\r\n");
      out.write("\t\t\t\t\tif(totalcode.length>9){\r\n");
      out.write("\t\t\t\t\t\t$(t).attr(\"class\",\"act_t2\");\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\tvar mark=\"?\";\r\n");
      out.write("\t        \t\tif(url.indexOf(\"?\")!=-1){\r\n");
      out.write("\t        \t\t\tmark=\"&\";\r\n");
      out.write("\t        \t\t}\r\n");
      out.write("        \t\t\turl=url+mark+\"token=\"+'");
      out.print(tokenEntity.getToken());
      out.write("'+'&totalcode='+totalcode;\r\n");
      out.write("\t\t\t\t\t$(\"#busPage\").attr(\"src\",url);\r\n");
      out.write("\t\t\t\t\tappendOlTitle(tns);\r\n");
      out.write("\t\t\t\t\tonChangHead($(t).attr('parentcode'));\r\n");
      out.write("\t\t\t}else{\r\n");
      out.write("\t\t\t\t\talert(\"没有配置访问路径！\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfunction initTrigger(){\r\n");
      out.write("\t\t\t\tvar trigger = $('.hamburger');\r\n");
      out.write("\t\t\t\t//var overlay = $('.overlay');\r\n");
      out.write("\t\t\t\t//设置默认为开\r\n");
      out.write("\t\t\t\tisClosed = true;\r\n");
      out.write("\t\t\t\t//打开菜单\r\n");
      out.write("\t\t\t\tdefOpen(trigger);\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\ttrigger.click(function () {\r\n");
      out.write("\t\t\t\t  hamburger_cross();     \r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\t\tfunction hamburger_cross() {\r\n");
      out.write("\t\t\t\t  if (isClosed == true) {          \r\n");
      out.write("\t\t\t\t\t//overlay.hide();\r\n");
      out.write("\t\t\t\t\ttrigger.removeClass('is-open');\r\n");
      out.write("\t\t\t\t\ttrigger.addClass('is-closed');\r\n");
      out.write("\t\t\t\t\tisClosed = false;\r\n");
      out.write("\t\t\t\t\t$(\".contain\").width(\"100%\");\r\n");
      out.write("\t\t\t\t  } else {   \r\n");
      out.write("\t\t\t\t\t//overlay.show();\r\n");
      out.write("\t\t\t\t\ttrigger.removeClass('is-closed');\r\n");
      out.write("\t\t\t\t\ttrigger.addClass('is-open');\r\n");
      out.write("\t\t\t\t\tisClosed = true;\r\n");
      out.write("\t\t\t\t\t$(\".contain\").width(\"87%\");\r\n");
      out.write("\t\t\t\t  }\r\n");
      out.write("\t\t\t  }\r\n");
      out.write("\t\t\t  \r\n");
      out.write("\t\t\t  $('[data-toggle=\"offcanvas\"]').click(function () {\r\n");
      out.write("\t\t\t\t\t$('#wrapper').toggleClass('toggled');\r\n");
      out.write("\t\t\t  }); \r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfunction setHeigth(height){\r\n");
      out.write("\t\t\t$(\"#busPage\").attr(\"height\",height+30);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfunction appendOlTitle(tns){\r\n");
      out.write("\t\t\t$(\"#ol_title\").html(\"\");\r\n");
      out.write("\t\t\tvar html =\"<li></li>\";\r\n");
      out.write("\t\t\tfor(var i=0;i<tns.length;i++){\r\n");
      out.write("\t\t\t\tif(tns[i]!=null){\r\n");
      out.write("\t\t\t\t\thtml+=\"<li>\"+tns[i]+\"</li>\";\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t$(\"#ol_title\").html(html);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfunction defOpen(trigger){\r\n");
      out.write("\t\t\t$('#wrapper').toggleClass('toggled');\r\n");
      out.write("\t\t\ttrigger.removeClass('is-closed');\r\n");
      out.write("\t\t\ttrigger.addClass('is-open');\r\n");
      out.write("\t\t\t$(\".contain\").width(\"88%\");\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfunction hidePopover(){\r\n");
      out.write("    \t}\r\n");
      out.write("    \t\r\n");
      out.write("    \t//company_id ='");
      out.print(tokenEntity.COMPANY.getCompany_id());
      out.write("';\r\n");
      out.write("\t\tfunction init(roleId,cid,tab){\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t \t//if(cid == \"\"){\r\n");
      out.write("\t\t\t \t//cid  = $(\"input[name='company']:checked\").val();\r\n");
      out.write("\t\t \t//}\r\n");
      out.write("\t\t \t//从token中获取当前公司id 设置 显示公司 //  \r\n");
      out.write("\t\t \tcompany_id = cid;\r\n");
      out.write("\t\t \t$(\"#company_type\").find(\"option[value!='\"+company_id+\"']\").removeAttr('selected');\r\n");
      out.write("\t\t \t$(\"#company_type\").find(\"option[value = '\"+company_id+\"']\").attr(\"selected\",\"selected\");\r\n");
      out.write("\t\t \tvar companyname = $(\"#company_type\").find(\"option:selected\").text();\r\n");
      out.write("\t\t \tinitMenu(company_id,companyname,roleId,tab);\r\n");
      out.write("\t\t \t//调用父页面的方法\r\n");
      out.write("\t\t \tloadIndexRole(roleId,cid);\r\n");
      out.write("\t\t }\r\n");
      out.write("\t\t function initMenu(companyid,companyname,roleId,tab){\r\n");
      out.write("\t        \t//loadingMenu();\r\n");
      out.write("\t        \t//hidePopover();\r\n");
      out.write("\t        \tvar paramJson = {\r\n");
      out.write("           \t\t 'companyid':companyid,\r\n");
      out.write("            \t 'companyname':companyname\r\n");
      out.write("        \t\t};\r\n");
      out.write("        \t\t//弹出角色选择窗口\r\n");
      out.write("\t        \t$.ajax({  \r\n");
      out.write("\t                    url : \"/portal/base?cmd=findMenu&type=1&token=\"+'");
      out.print(tokenEntity.getToken());
      out.write("'+'&ts='+new Date().getTime()+\"&roleid=\"+roleId+\"&tab=\"+tab,  \r\n");
      out.write("\t                    dataType : \"json\",  \r\n");
      out.write("\t                    type : \"post\",\r\n");
      out.write("\t                    data : paramJson,\r\n");
      out.write("\t                    success : function(data) { \r\n");
      out.write("\t                    \tmessage = data['message'];\r\n");
      out.write("\t                    \t$(\"#First_level_Menu\").html(message);\r\n");
      out.write("\t                    \t$(function () { \r\n");
      out.write("\t\t                    \t$(\"[data-toggle='popover']\").popover({\r\n");
      out.write("\t\t                    \t\t\t\t\t html : true ,\r\n");
      out.write("\t\t                    \t\t\t         animation:true,placement: 'right',\r\n");
      out.write("\t\t                    \t\t\t\t\t delay:{ show: 200, hide: 100 }\r\n");
      out.write("\t\t                    \t                 });\r\n");
      out.write("\t\t                    \t$(\"[data-toggle='popover']\").on(\"mouseenter mouseleave\",function(event){\r\n");
      out.write("\t\t\t\t\t\t\t\t\t if(event.type == \"mouseenter\"){\r\n");
      out.write("\t\t\t\t\t\t\t\t\t \t$('.popover').popover(\"hide\");\r\n");
      out.write("\t\t\t\t\t\t\t\t\t    //鼠标悬浮\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t$(this).popover(\"show\");\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\t }\r\n");
      out.write("\t\t\t\t\t\t\t\t\t else if(event.type == \"mouseleave\"){\r\n");
      out.write("\t\t\t\t\t\t\t\t\t   //鼠标离开\r\n");
      out.write("\t\t\t\t\t\t\t\t\t   var _this = this;\r\n");
      out.write("\t\t\t\t\t\t\t\t\t   \r\n");
      out.write("\t\t\t\t\t\t\t\t\t   //$(_this).popover(\"hide\");\r\n");
      out.write("\t\t\t\t\t\t\t\t\t   //setTimeout(function () {            \t      \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t//if (!$(\".popover:hover\").length) {            \t          \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t//$(_this).popover(\"hide\")            \t                 \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t//}            \t                    \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t//}, 200); \r\n");
      out.write("\t\t\t\t\t\t\t\t\t }\r\n");
      out.write("\t\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t})\r\n");
      out.write("\r\n");
      out.write("\t                    \t$('.li-hover').hover(function(){\r\n");
      out.write("\t\t\t\t\t    \t\t$(this).find('ul').hide();\r\n");
      out.write("\t\t\t\t\t  \t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t    function(){\r\n");
      out.write("\t\t\t\t\t\t\t      $(this).find('ul').show();\r\n");
      out.write("\t\t\t\t\t\t\t    });\r\n");
      out.write("\t                    } ,\r\n");
      out.write("\t                    error:function(XMLHttpRequest, textStatus, errorThrown){\r\n");
      out.write("\t\t\t\t\t\t\tmessage =\"请求失败\";\r\n");
      out.write("\t\t\t\t\t\t\talert(message);\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t             });\r\n");
      out.write("\t               \r\n");
      out.write("\t     }\r\n");
      out.write("\t</script>\r\n");
      out.write("\t\r\n");
      out.write("\t<style type=\"text/css\">\r\n");
      out.write("\tbody{\r\n");
      out.write("\t\toverflow:hidden;\r\n");
      out.write("\t}\r\n");
      out.write(".dropdown-menu {\r\n");
      out.write("\tposition: absolute;\r\n");
      out.write("\ttop: 100%;\r\n");
      out.write("\tleft: 0;\r\n");
      out.write("\tz-index: 1000;\r\n");
      out.write("\tdisplay: none;\r\n");
      out.write("\tfloat: left;\r\n");
      out.write("\tmin-width: 160px;\r\n");
      out.write("\tpadding: 5px 0;\r\n");
      out.write("\tmargin: 2px 0 0;\r\n");
      out.write("\tfont-size: 12px;\r\n");
      out.write("\ttext-align: left;\r\n");
      out.write("\tlist-style: none;\r\n");
      out.write("\tbackground-color: #eee;\r\n");
      out.write("\t-webkit-background-clip: padding-box;\r\n");
      out.write("\tbackground-clip: padding-box;\r\n");
      out.write("\tborder: 1px solid #ccc;\r\n");
      out.write("\tborder: 1px solid rgba(0, 0, 0, .15);\r\n");
      out.write("\tborder-radius: 4px;\r\n");
      out.write("\t-webkit-box-shadow: 0 6px 12px rgba(0, 0, 0, .175);\r\n");
      out.write("\tbox-shadow: 0 6px 10px rgba(0, 0, 0, .175)\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".dropdown-menu.pull-right {\r\n");
      out.write("\tright: 0;\r\n");
      out.write("\tleft: auto\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".dropdown-menu .divider {\r\n");
      out.write("\theight: 1px;\r\n");
      out.write("\tmargin: 9px 0;\r\n");
      out.write("\toverflow: hidden;\r\n");
      out.write("\tbackground-color: rgb(144,144,145)\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".dropdown-menu>li>a {\r\n");
      out.write("\tdisplay: block;\r\n");
      out.write("\tpadding: 3px 20px;\r\n");
      out.write("\tclear: both;\r\n");
      out.write("\tfont-weight: 400;\r\n");
      out.write("\tline-height: 1.42857143;\r\n");
      out.write("\tcolor: rgb(240,240,241);\r\n");
      out.write("\twhite-space: nowrap\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".dropdown-menu>li>a:hover, .dropdown-menu>li>a:focus {\r\n");
      out.write("\tcolor:#FFFCF8;\r\n");
      out.write("\ttext-decoration: none;\r\n");
      out.write("\tbackground-color: #1798FE\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write(".sidebar-nav>li>a:hover, .sidebar-nav>li>a:focus {\r\n");
      out.write("\tcolor: rgb(240,240,241);\r\n");
      out.write("\ttext-decoration: none;\r\n");
      out.write("\tbackground-color:rgb(22,46,84)\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".dropdown-menu>.active>a, .dropdown-menu>.active>a:hover, .dropdown-menu>.active>a:focus\r\n");
      out.write("\t{\r\n");
      out.write("\tcolor: rgb(94,83,160);\r\n");
      out.write("\ttext-decoration: none;\r\n");
      out.write("\tbackground-color: #428bca;\r\n");
      out.write("\toutline: 0\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".act_t1,.act_t2{\r\n");
      out.write("  background-color: #1798FE;\r\n");
      out.write("  color:#FFFCF8;\r\n");
      out.write("  text-decoration: none;\r\n");
      out.write("}\r\n");
      out.write("/*样式添加修改  */\r\n");
      out.write("\r\n");
      out.write("@media  screen and  (min-width: 1200px)\r\n");
      out.write("{\r\n");
      out.write("\t.navbar{font-size:14px}\r\n");
      out.write("\t.dropdown-menu a{font-size:13px}\r\n");
      out.write("}\r\n");
      out.write("@media  screen and  (min-width: 1370px)\r\n");
      out.write("{\r\n");
      out.write("\t.navbar{font-size:16px}\r\n");
      out.write("\t.dropdown-menu a{font-size:15px}\r\n");
      out.write("}\r\n");
      out.write("@media  screen and  (max-width: 1199px)\r\n");
      out.write("{\r\n");
      out.write("\t.navbar{font-size:13px}\r\n");
      out.write("\t.dropdown-menu a{font-size:12px}\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("li a b{\r\n");
      out.write("\tfloat:left;\r\n");
      out.write("\twidth:88%\r\n");
      out.write("\t\r\n");
      out.write("}\r\n");
      out.write(" li a span{\r\n");
      out.write("\tfloat: left;\r\n");
      out.write("    width: 0;\r\n");
      out.write("    height: 0;\r\n");
      out.write("    border-width: 6px;\r\n");
      out.write("    border-style: solid;\r\n");
      out.write("    border-color: #fff transparent transparent transparent;\r\n");
      out.write("    margin-top: 6px;\r\n");
      out.write("\t\r\n");
      out.write("} \r\n");
      out.write("/* li a span {\r\n");
      out.write("\tfloat: left;\r\n");
      out.write("    width: 0;\r\n");
      out.write("    height: 0;\r\n");
      out.write("    border-width: 6px;\r\n");
      out.write("    border-style: solid;\r\n");
      out.write("    border-color: transparent transparent #fff  transparent;\r\n");
      out.write("    margin-top:0px;\r\n");
      out.write("} */\r\n");
      out.write("</style>\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}