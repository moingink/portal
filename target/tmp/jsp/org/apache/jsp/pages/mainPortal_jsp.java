package org.apache.jsp.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Date;
import com.yonyou.util.theme.ThemePath;
import com.yonyou.business.entity.TokenEntity;
import com.yonyou.business.entity.TokenUtil;

public final class mainPortal_jsp extends org.apache.jasper.runtime.HttpJspBase
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

	TokenEntity tokenEntity = TokenUtil.initTokenEntity(request);
	String token = (String) request.getParameter("token");
	String ContextPath = request.getContextPath();
	String pages="/pages/";
	String pagePath =ThemePath.findPath(request, ThemePath.PORTAL_MAIN).replace(ContextPath+pages, "");
	pagePath=pagePath+"?token="+token+"&ts="+(new Date()).getTime();

      out.write("\r\n");
      out.write("<html lang=\"zh-cn\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"utf-8\">\r\n");
      out.write("<META HTTP-EQUIV=\"pragma\" CONTENT=\"no-cache\"> \r\n");
      out.write("<META HTTP-EQUIV=\"Cache-Control\" CONTENT=\"no-cache, must-revalidate\"> \r\n");
      out.write("<META HTTP-EQUIV=\"expires\" CONTENT=\"0\">\r\n");
      out.write("<title>用友广信资金管理系统</title>\r\n");
      out.write("<!-- 设置浏览器默认渲染模式 -->\r\n");
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("<meta name=\"renderer\" content=\"webkit\">\r\n");
      out.write("<script src=\"");
      out.print(ContextPath);
      out.write("/vendor/jquery/jquery-1.11.2.min.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body >\r\n");
      out.write("\t\t\r\n");
      if (true) {
        _jspx_page_context.forward(pagePath );
        return;
      }
      out.write(" \r\n");
      out.write("\t\t\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
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
