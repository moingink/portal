package org.apache.jsp.pages.t.vehicles;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class menuManage_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>审批工作台</title>\r\n");
      out.write("\r\n");
      out.write("<script src=\"/portal/vendor/jquery/jquery-1.11.2.min.js\"></script>\r\n");
      out.write("<script src=\"/portal/pages/js/bootstrap/js/bootstrap.min.js\"></script>\r\n");
      out.write("<script src=\"/portal/pages/tree/jstree.min.js\"></script>\r\n");
      out.write("<script src=\"/portal/pages/js/tinyselect.js\"></script>\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/portal/pages/js/tinyselect.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"/portal/css/style.css\">\r\n");
      out.write("<link rel=\"stylesheet prefetch\"\r\n");
      out.write("\thref=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\">\r\n");
      out.write("<link rel=\"stylesheet\"\r\n");
      out.write("\thref=\"/portal/pages/js/bootstrap-table/src/bootstrap-table.css\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/portal/pages/js/examples.css\">\r\n");
      out.write("<link rel=\"stylesheet\"\r\n");
      out.write("\thref=\"/portal/pages/tree/themes/default/style.min.css\">\r\n");
      out.write("<script src=\"/portal/vendor/bootstrap/js/html5.js\"></script>\r\n");
      out.write("<script src=\"/portal/vendor/bootstrap/js/respond.js\"></script>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\r\n");
      out.write("\thref=\"/portal/vendor/vehicles/css/resert.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\r\n");
      out.write("\thref=\"/portal/vendor/vehicles/css/header.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\r\n");
      out.write("\thref=\"/portal/vendor/vehicles/css/customer.css\">\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<form>\r\n");
      out.write("\r\n");
      out.write("\t\t<div class=\"tabbable\" id=\"tabs-146746\">\r\n");
      out.write("\t\t\t\t<div class=\"tab-content\">\r\n");
      out.write("\t\t\t\t\t<div class=\"tab-pane active\" id=\"panel-847015\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"col-md-2\" style=\"    width: 10%;\">\r\n");
      out.write("\t\t\t\t\t\t\t<ul id=\"myTab1\" class=\"nav nav-pills nav-stacked\">\r\n");
      out.write("\t\t\t\t\t          <li >\r\n");
      out.write("\t\t\t\t\t          \t  <div id=\"jstree_div\">\r\n");
      out.write("\t\t\t\t\t          \t  </div>\r\n");
      out.write("\t\t\t\t\t          </li>\r\n");
      out.write("\t\t       \t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<div class=\"col-md-10\"  style=\"    width: 90%;\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"tab-content\">\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"tab-pane fade in active\" id='message'>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<iframe id=\"iframe\" src=\"/workflow/pages/singleTableModify.jsp?pageCode=VIEW_WF_NEED_HANDLE_COMMIT&pageName=发起列表&ParentPKField=NEED_USER_ID&ParentPKValue=");
      out.print( request.getParameter("userid") );
      out.write("\" scrolling=\"yes\" frameborder=\"0\"  width=\"99%\" height=\"800\"></iframe>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t\t  \r\n");
      out.write("    <script type=\"text/javascript\">\r\n");
      out.write("         $(function() { \r\n");
      out.write("            $('#jstree_div').jstree({\r\n");
      out.write("                'core' : {\r\n");
      out.write("                    'check_callback': true,  \r\n");
      out.write("                    \"data\" : [    \r\n");
      out.write("                       { \"id\" : \"menu0\", \"parent\" : \"#\", \"text\" : \"审批工作台\"  },    \r\n");
      out.write("                       { \"id\" : \"menu1\", \"parent\" : \"menu0\", \"text\" : \"我的发起\",\"url\":\"/workflow/pages/singleTableModify.jsp?pageCode=VIEW_WF_NEED_HANDLE_COMMIT&pageName=发起列表&ParentPKField=NEED_USER_ID&ParentPKValue=");
      out.print( request.getParameter("userid") );
      out.write("\" },    \r\n");
      out.write("                       { \"id\" : \"menu2\", \"parent\" : \"menu0\", \"text\" : \"我的待办\",\"url\":\"/workflow/pages/singleTableModify.jsp?pageCode=VIEW_WF_NEED_HANDLE&pageName=待办列表&ParentPKField=NEED_USER_ID&ParentPKValue=");
      out.print( request.getParameter("userid") );
      out.write("\" },   \r\n");
      out.write("                       { \"id\" : \"menu3\", \"parent\" : \"menu0\", \"text\" : \"我的已办\",\"url\":\"/workflow/pages/singleTableModify.jsp?pageCode=VIEW_WF_NEED_HANDLE_DONE&pageName=已办列表&ParentPKField=NEED_USER_ID&ParentPKValue=");
      out.print( request.getParameter("userid") );
      out.write("\" },   \r\n");
      out.write("                       { \"id\" : \"menu4\", \"parent\" : \"menu0\", \"text\" : \"我的办结\",\"url\":\"/workflow/pages/singleTableModify.jsp?pageCode=VIEW_WF_NEED_HANDLE_FINISH&pageName=办结列表&ParentPKField=NEED_USER_ID&ParentPKValue=");
      out.print( request.getParameter("userid") );
      out.write("\" },   \r\n");
      out.write("                       { \"id\" : \"menu5\", \"parent\" : \"menu0\", \"text\" : \"委托代办\",\"url\":\"/workflow/pages/childTableModifyForProcessEntrust.jsp?user_id=");
      out.print( request.getParameter("userid") );
      out.write("\" }\r\n");
      out.write("                      ]\r\n");
      out.write("\r\n");
      out.write("                }\r\n");
      out.write("            }).bind(\"select_node.jstree\", function(event, data) {  \r\n");
      out.write("                var inst = data.instance;  \r\n");
      out.write("                var selectedNode = inst.get_node(data.selected);\r\n");
      out.write("                $(\"#iframe\").attr(\"src\",selectedNode.original.url);\r\n");
      out.write("            }).bind(\"loaded.jstree\", function (event, data) {\r\n");
      out.write("\t\t\t\t$(\"#menu0_anchor\").dblclick();\r\n");
      out.write("\t\t\t});\r\n");
      out.write("        });  \r\n");
      out.write("      \r\n");
      out.write("    </script>  \r\n");
      out.write("    \r\n");
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
