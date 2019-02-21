package org.apache.jsp.pages.vehicles;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.yonyou.business.entity.TokenEntity;
import com.yonyou.business.entity.TokenUtil;
import com.yonyou.util.wsystem.service.ORG;
import java.util.Date;

public final class head_jsp extends org.apache.jasper.runtime.HttpJspBase
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
	String loginName = tokenEntity.USER.getName();
	String corpName = tokenEntity.COMPANY.getCompany_name();
	String token = (String) request.getParameter("token");
	String corpId = tokenEntity.COMPANY.getCompany_id();
	String userId = tokenEntity.USER.getId();
	String selecthtml = tokenEntity.COMPANY.getSelectCompanyHtml();
	String ContextPath = request.getContextPath();
	
	String path=ContextPath+"/vendor/vehicles";

      out.write("\r\n");
      out.write("<html lang=\"zh-cn\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"utf-8\">\r\n");
      out.write("<title>业务管理系统</title>\r\n");
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
      out.write("\t\r\n");
      out.write("\t<body>\r\n");
      out.write("\t\t\t<!--头部nav导航栏-->\r\n");
      out.write("\t\t\t<nav>\r\n");
      out.write("\t\t\t\t<div class=\"contain-fl\">\r\n");
      out.write("\t\t\t\t\t<img src=\"");
      out.print(path);
      out.write("/img/logo.png\"/>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"contain-fr\">\r\n");
      out.write("\t\t\t\t\t<span class=\"nav-name\">");
      out.print( corpName);
      out.write("</span>\r\n");
      out.write("\t\t\t\t\t<span class=\"nav-customer\">");
      out.print( loginName);
      out.write("</span>\r\n");
      out.write("\t\t\t\t\t<!-- <span class=\"nav-img\"></span> -->\r\n");
      out.write("\t\t\t\t\t<span class=\"nav-set\"><img src=\"");
      out.print(path);
      out.write("/img/set.png\"/></span>\r\n");
      out.write("\t\t\t\t\t<span class=\"nav-close\" onclick='closeBus(this)'><img src=\"");
      out.print(path);
      out.write("/img/close.png\" /></span>\r\n");
      out.write("\t\t\t\t</div>\t\t\t\t\r\n");
      out.write("\t\t\t</nav>\r\n");
      out.write("\t\t\t<!--顶部背景图片-->\r\n");
      out.write("\t\t\t<div class=\"top-background\">\r\n");
      out.write("\t\t\t\t<img src=\"");
      out.print(path);
      out.write("/img/banners.png\"/>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t</body>\r\n");
      out.write("\t<script src=\"js/commen.js\" type=\"text/javascript\" charset=\"utf-8\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfunction openMenu(t){\r\n");
      out.write("\t\t\tvar mywin=window.open('");
      out.print(request.getContextPath());
      out.write("/pages/vehicles/menu.jsp?token=");
      out.print(token);
      out.write("'); //将新打的窗口对象，存储在变量mywin中\r\n");
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
