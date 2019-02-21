package org.apache.jsp.pages.t.vehicles;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.yonyou.business.entity.TokenEntity;
import com.yonyou.business.entity.TokenUtil;
import com.yonyou.util.wsystem.service.ORG;
import java.util.Date;

public final class busPage_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");

response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", -10); 
	TokenEntity tokenEntity = TokenUtil.initTokenEntity(request);
	String ContextPath = request.getContextPath();
	String busUrl =request.getParameter("b");
	String tns=request.getParameter("tns");
	String[] tnsArray =tns.split(",");
	if(tnsArray.length<3){
		tnsArray=new String[]{"","",""};
	}
	String mark="?";
	if(busUrl.indexOf("?")!=-1){
		mark="&";
	}
	busUrl=busUrl+mark+"token="+tokenEntity.getToken();
	String path=ContextPath+"/vendor/vehicles";

      out.write("\r\n");
      out.write("<html lang=\"zh-cn\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"utf-8\">\r\n");
      out.write("<title>");
      out.print(tnsArray[tnsArray.length-1] );
      out.write("</title>\r\n");
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
      out.write("<link rel=\"stylesheet\"\r\n");
      out.write("\thref=\"");
      out.print(ContextPath);
      out.write("/pages/js/bootstrap/css/bootstrap.min.css\">\r\n");
      out.write("<link rel=\"stylesheet\"\r\n");
      out.write("\thref=\"");
      out.print(ContextPath);
      out.write("/pages/js/bootstrap-table/src/bootstrap-table.css\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(ContextPath);
      out.write("/pages/js/examples.css\">\r\n");
      out.write("<link rel=\"stylesheet\"\r\n");
      out.write("\thref=\"");
      out.print(ContextPath);
      out.write("/pages/tree/themes/default/style.min.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\r\n");
      out.write("\thref=\"");
      out.print(ContextPath);
      out.write("/js/bootTable.js\">\r\n");
      out.write("<!-- bootstrapIE8补丁 -->\r\n");
      out.write("<script src=\"");
      out.print(ContextPath);
      out.write("/vendor/bootstrap/js/html5.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(ContextPath);
      out.write("/vendor/bootstrap/js/respond.js\"></script>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(path);
      out.write("/css/resert.css\"/>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(path);
      out.write("/css/header.css\"/>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(path);
      out.write("/css/index.css\"/>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(ContextPath);
      out.write("/css/style.css\"/>\r\n");
      out.write("\t<body>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\r\n");
      out.write("\t<div id=\"wrapper\">\r\n");
      out.write("\t<div class=\"overlay\"></div>\r\n");
      out.write("\r\n");
      out.write("\t<!-- Sidebar -->\r\n");
      out.write("\t\t<nav class=\"navbar navbar-inverse navbar-fixed-top\"\r\n");
      out.write("\t\t\tid=\"sidebar-wrapper\" role=\"navigation\">\r\n");
      out.write("\t\t\t<ul class=\"nav sidebar-nav\">\r\n");
      out.write("\t\t\t\t<li class=\"sidebar-brand\"><a href=\"#\"> Bootstrap 3 </a></li>\r\n");
      out.write("\t\t\t\t<li><a href=\"#\"><i class=\"fa fa-fw fa-home\"></i> Home</a></li>\r\n");
      out.write("\t\t\t\t<li><a href=\"#\"><i class=\"fa fa-fw fa-folder\"></i> Page one</a>\r\n");
      out.write("\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t<li><a href=\"#\"><i class=\"fa fa-fw fa-file-o\"></i> Second\r\n");
      out.write("\t\t\t\t\t\tpage</a></li>\r\n");
      out.write("\t\t\t\t<li><a href=\"#\"><i class=\"fa fa-fw fa-cog\"></i> Third page</a>\r\n");
      out.write("\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t<li class=\"dropdown\"><a href=\"#\" class=\"dropdown-toggle\"\r\n");
      out.write("\t\t\t\t\tdata-toggle=\"dropdown\"><i class=\"fa fa-fw fa-plus\"></i>\r\n");
      out.write("\t\t\t\t\t\tDropdown <span class=\"caret\"></span></a>\r\n");
      out.write("\t\t\t\t\t<ul class=\"dropdown-menu\" role=\"menu\">\r\n");
      out.write("\t\t\t\t\t\t<li class=\"dropdown-header\">Dropdown heading</li>\r\n");
      out.write("\t\t\t\t\t\t<li><a href=\"#\">Action</a></li>\r\n");
      out.write("\t\t\t\t\t\t<li><a href=\"#\">Another action</a></li>\r\n");
      out.write("\t\t\t\t\t\t<li><a href=\"#\">Something else here</a></li>\r\n");
      out.write("\t\t\t\t\t\t<li><a href=\"#\">Separated link</a></li>\r\n");
      out.write("\t\t\t\t\t\t<li><a href=\"#\">One more separated link</a></li>\r\n");
      out.write("\t\t\t\t\t</ul></li>\r\n");
      out.write("\t\t\t\t<li><a href=\"#\"><i class=\"fa fa-fw fa-bank\"></i> Page four</a>\r\n");
      out.write("\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t<li><a href=\"#\"><i class=\"fa fa-fw fa-dropbox\"></i> Page 5</a>\r\n");
      out.write("\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t<li><a href=\"#\"><i class=\"fa fa-fw fa-twitter\"></i> Last\r\n");
      out.write("\t\t\t\t\t\tpage</a></li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</nav>\r\n");
      out.write("\t\t<!-- /#sidebar-wrapper -->\r\n");
      out.write("\r\n");
      out.write("\t<!-- Page Content -->\r\n");
      out.write("\t<div id=\"page-content-wrapper\">\r\n");
      out.write("\t  <button type=\"button\" class=\"hamburger is-closed animated fadeInLeft\" data-toggle=\"offcanvas\">\r\n");
      out.write("\t\t<span class=\"hamb-top\"></span>\r\n");
      out.write("\t\t<span class=\"hamb-middle\"></span>\r\n");
      out.write("\t\t<span class=\"hamb-bottom\"></span>\r\n");
      out.write("\t  </button>\r\n");
      out.write("\t\t\t<div class=\"contain\">\r\n");
      out.write("\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "headPage.jsp?isShow=1", out, false);
      out.write("\r\n");
      out.write("\t\t\t<div class=\"box-top\" >\r\n");
      out.write("\t\t\t\t<ol class=\"breadcrumb\">\r\n");
      out.write("\t\t\t\t  <li></li>\r\n");
      out.write("\t\t\t\t  ");
 for(String title:tnsArray){ 
      out.write("\r\n");
      out.write("\t\t\t\t  \t <li>");
      out.print( title);
      out.write("</li>\r\n");
      out.write("\t\t\t\t  ");
 } 
      out.write("\r\n");
      out.write("\t\t\t\t</ol>\r\n");
      out.write("\t\t\t\t<iframe id='busPage' src=\"");
      out.print(busUrl);
      out.write("\" width=\"100%\"  scrolling=\"no\" frameborder=\"0\"  height='600px'></iframe>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<!-- /#page-content-wrapper -->\r\n");
      out.write("\r\n");
      out.write("</div>\t\r\n");
      out.write("\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t</body>\r\n");
      out.write("\t<script src=\"js/commen.js\" type=\"text/javascript\" charset=\"utf-8\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t$(function(){\r\n");
      out.write("\t\t\t\t\tvar trigger = $('.hamburger'),\r\n");
      out.write("\t\t\t\t  overlay = $('.overlay'),\r\n");
      out.write("\t\t\t\t isClosed = false;\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\t\ttrigger.click(function () {\r\n");
      out.write("\t\t\t\t  hamburger_cross();     \r\n");
      out.write("\t\t\t\t  if(isClosed){\r\n");
      out.write("\t\t\t\t  \t$(\".contain\").width(\"90%\");\r\n");
      out.write("\t\t\t\t  }else{\r\n");
      out.write("\t\t\t\t  \t$(\".contain\").width(\"100%\");\r\n");
      out.write("\t\t\t\t  }\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\t\tfunction hamburger_cross() {\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\t\t  if (isClosed == true) {          \r\n");
      out.write("\t\t\t\t\toverlay.hide();\r\n");
      out.write("\t\t\t\t\ttrigger.removeClass('is-open');\r\n");
      out.write("\t\t\t\t\ttrigger.addClass('is-closed');\r\n");
      out.write("\t\t\t\t\tisClosed = false;\r\n");
      out.write("\t\t\t\t  } else {   \r\n");
      out.write("\t\t\t\t\toverlay.show();\r\n");
      out.write("\t\t\t\t\ttrigger.removeClass('is-closed');\r\n");
      out.write("\t\t\t\t\ttrigger.addClass('is-open');\r\n");
      out.write("\t\t\t\t\tisClosed = true;\r\n");
      out.write("\t\t\t\t  }\r\n");
      out.write("\t\t\t  }\r\n");
      out.write("\t\t\t  \r\n");
      out.write("\t\t\t  $('[data-toggle=\"offcanvas\"]').click(function () {\r\n");
      out.write("\t\t\t\t\t$('#wrapper').toggleClass('toggled');\r\n");
      out.write("\t\t\t  }); \r\n");
      out.write("\t\t});\r\n");
      out.write("\t\tfunction setHeigth(height){\r\n");
      out.write("\t\t\t$(\"#busPage\").attr(\"height\",height);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t</script>\r\n");
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
