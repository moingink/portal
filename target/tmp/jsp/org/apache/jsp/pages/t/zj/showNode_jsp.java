package org.apache.jsp.pages.t.zj;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.yonyou.util.theme.ThemePath;
import com.yonyou.util.wsystem.service.ORG;

public final class showNode_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"zh-cn\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"utf-8\">\r\n");
      out.write("<title>门户</title>\r\n");
      out.write("\r\n");

	String token = (String) request.getParameter("token");
	String totalCode = (String) request.getParameter("totalCode");
	String ContextPath =request.getContextPath();
	String urltotalcode=(String) request.getParameter("urltotalcode");

      out.write("\r\n");
      out.write("</head>\r\n");
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
      out.print(ThemePath.findPath(request, ThemePath.PORTAL_CSS));
      out.write("\">\r\n");
      out.write("<script src=\"");
      out.print(ContextPath);
      out.write("/vendor/jquery/jquery-1.11.2.min.js\"></script>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("\t<div class=\"container\" style=\"width: 90%\">\r\n");
      out.write("\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t<div id=\"node\" class=\"sidebar\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<div id=\"menuName\" class=\"header\"></div>\r\n");
      out.write("\t\t\t\t<div id=\"nodeMenu\" class=\"body\">\r\n");
      out.write("\t\t\t\t\t<div id=\"nodePage\"></div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"footer\"></div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"main\">\r\n");
      out.write("\t\t\t\t<iframe id='nodeFrame' src='");
      out.print(ContextPath);
      out.write("/pages/default.html'\r\n");
      out.write("\t\t\t\t\twidth=\"100%\" scrolling=\"no\" frameborder=\"0\" ></iframe>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("\t");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\r\n");
      out.write("\t\t var init_heigth=0;\r\n");
      out.write("\t\t $(function(){ \r\n");
      out.write("          \t\tinitNode();\r\n");
      out.write("         }); \r\n");
      out.write("         \r\n");
      out.write("         \r\n");
      out.write("        function changNode(t){\r\n");
      out.write("        \t\r\n");
      out.write("        \tchangNodeByObj($(t));\r\n");
      out.write("        \t\r\n");
      out.write("        \t                                      \r\n");
      out.write("        }\r\n");
      out.write("        \r\n");
      out.write("        function changNodeByObj($t){\r\n");
      out.write("        \t\r\n");
      out.write("        \tvar url=$t.attr(\"url\");\r\n");
      out.write("        \tvar total=$t.attr(\"totalcode\");\r\n");
      out.write("        \tvar mark=\"?\";\r\n");
      out.write("        \tif(url!=null&&url.length>0){\r\n");
      out.write("        \t\tif(url.indexOf(\"?\")!=-1){\r\n");
      out.write("        \t\t\tmark=\"&\";\r\n");
      out.write("        \t\t}\r\n");
      out.write("        \t\turl=url+mark+\"token=\"+'");
      out.print(token);
      out.write("'+'&totalCode='+total;\r\n");
      out.write("        \t\t$(\"#nodeFrame\").attr(\"src\",url);\r\n");
      out.write("        \t\tsetHeigth(1600);\r\n");
      out.write("        \t\tremoveAct($t);\r\n");
      out.write("        \t\ttransferUrl($t,url);/*传url*/\r\n");
      out.write("        \t}else{\r\n");
      out.write("        \t\tvar parentcode =$t.attr(\"totalcode\");\r\n");
      out.write("        \t\tvar spanhtml=$t.find(\"span\").html();\r\n");
      out.write("        \t\tvar repspanhtml=\"\";\r\n");
      out.write("        \t\tvar mark =\"+\";\r\n");
      out.write("        \t\tif(spanhtml.indexOf(\"&gt;\")!=-1){\r\n");
      out.write("        \t\t\trepspanhtml=spanhtml.replace(\"&gt;\",mark);\r\n");
      out.write("        \t\t}\r\n");
      out.write("        \t\tif(spanhtml.indexOf(mark)!=-1){\r\n");
      out.write("        \t\t\trepspanhtml=spanhtml.replace(mark,\"&gt;\");\r\n");
      out.write("        \t\t}\r\n");
      out.write("        \t\t$t.find(\"span\").html(repspanhtml);\r\n");
      out.write("        \t\tvar parentcodeMark=\"dd[parentcode='\"+parentcode+\"']\";\r\n");
      out.write("        \t\t$(parentcodeMark).toggle();\r\n");
      out.write("        \t\tsetMenuHeigth($(\"#node\").height());\r\n");
      out.write("        \t}\r\n");
      out.write("        \t\r\n");
      out.write("        \t\r\n");
      out.write("        }\r\n");
      out.write("        \r\n");
      out.write("        function transferUrl($t,url){\t\t\t\t/*传url*/\r\n");
      out.write("        \t  var menuName =$t.attr(\"title\");\r\n");
      out.write("        \t  var ajaxUrl =\"/portal/button?cmd=menu&token=\"+'");
      out.print(token);
      out.write("'+\"&menuName=\"+menuName;\r\n");
      out.write("\t\t\t  $.ajax({\r\n");
      out.write("\t\t\t\t            type: \"post\",\r\n");
      out.write("\t\t\t\t            url: ajaxUrl,\r\n");
      out.write("\t\t\t\t            data: {url},\r\n");
      out.write("\t\t\t\t\t\t\t});\t      \r\n");
      out.write("        }\r\n");
      out.write("        function initNodeByTotalCode(){\r\n");
      out.write("       \t\tvar urltotalCode='");
      out.print(urltotalcode);
      out.write("';\r\n");
      out.write("       \t\tvar totalcode='");
      out.print( totalCode);
      out.write("';\r\n");
      out.write("       \t\tfor(var i=totalcode.length+3;i<=urltotalCode.length;i+=3){\r\n");
      out.write("       \t\t\tvar mark =\"[totalcode=\"+urltotalCode.substr(0,i)+\"]\";\r\n");
      out.write("       \t\t\tif(!chageNodeByMark(mark)){\r\n");
      out.write("       \t\t\t\tbreak;\r\n");
      out.write("       \t\t\t}\r\n");
      out.write("       \t\t}\r\n");
      out.write("       \t\t\r\n");
      out.write("       }\r\n");
      out.write("       \r\n");
      out.write("       function chageNodeByMark(mark){\r\n");
      out.write("       \t\tvar $t =$(mark);\r\n");
      out.write("       \t\tif($t.html()!=undefined){\r\n");
      out.write("       \t\t\tchangNodeByObj($t);\r\n");
      out.write("       \t\t\treturn true;\r\n");
      out.write("       \t\t}else{\r\n");
      out.write("       \t\t\treturn false;\r\n");
      out.write("       \t\t}\r\n");
      out.write("       }\r\n");
      out.write("        \r\n");
      out.write("        function removeAct($t){\r\n");
      out.write("        \t$(\".act\").removeClass(\"act\");\r\n");
      out.write("        \t$t.attr(\"class\",\"act\");\r\n");
      out.write("        }\r\n");
      out.write("\t\r\n");
      out.write("\t\tfunction initNode(){\r\n");
      out.write("        \r\n");
      out.write("        \t$.ajax({  \r\n");
      out.write("                    url : \"/portal/base?cmd=findNode&token=\"+'");
      out.print(token);
      out.write("'+'&totalCode='+'");
      out.print(totalCode);
      out.write("'+'&ts='+new Date().getTime(),  \r\n");
      out.write("                    dataType : \"json\",  \r\n");
      out.write("                    type : \"post\",  \r\n");
      out.write("                    success : function(data) { \r\n");
      out.write("                    \tmessage = data['message'];\r\n");
      out.write("                    \t$(\"#nodePage\").html(message);\r\n");
      out.write("                    \tsetMenuHeigth($(document.body).height());\r\n");
      out.write("                    \tinitNodeByTotalCode();\r\n");
      out.write("                    } ,\r\n");
      out.write("                    error:function(XMLHttpRequest, textStatus, errorThrown){\r\n");
      out.write("\t\t\t\t\t\tmessage =\"访问超时！\";\r\n");
      out.write("\t\t\t\t\t\talert(message);\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("             });  \r\n");
      out.write("             \r\n");
      out.write("       }\r\n");
      out.write("       \r\n");
      out.write("        //window.onresize=function(){  \r\n");
      out.write("\t         //changeFrameHeight(\"nodeFrame\");  \r\n");
      out.write("\t    // }\r\n");
      out.write("       \r\n");
      out.write("       \tvar page_heigth=500;\r\n");
      out.write("       \tfunction changeFrameHeight(iframeId){\r\n");
      out.write("\t        ifm.height=page_heigth;\r\n");
      out.write("\t    }\r\n");
      out.write("\t    \r\n");
      out.write("\t    function setHeigth(heigth){\r\n");
      out.write("          \tvar div_height = heigth;//使iframe高度等于子网页高度\r\n");
      out.write("          \tif(div_height>=init_heigth){\r\n");
      out.write("          \t\tpage_heigth=div_height;\r\n");
      out.write("          \t\tchangIframeHeigth(page_heigth);\r\n");
      out.write("          \t}else{\r\n");
      out.write("          \t\tchangIframeHeigth(init_heigth);\r\n");
      out.write("          \t}\r\n");
      out.write(" \t\t\t\r\n");
      out.write("\t    }\r\n");
      out.write("\t    \r\n");
      out.write("\t    function setMenuHeigth(heigth){\r\n");
      out.write("\t    \tvar div_height = heigth;\r\n");
      out.write("\t    \tinit_heigth=heigth;\r\n");
      out.write("\t    \tif(div_height>=page_heigth){\r\n");
      out.write("\t    \t\tchangIframeHeigth(init_heigth);\r\n");
      out.write("\t    \t}\r\n");
      out.write("\t    \t\r\n");
      out.write("\t    }\r\n");
      out.write("\t    \r\n");
      out.write("\t    function changIframeHeigth(parent_heigth){\r\n");
      out.write(" \t\t\t$(\"iframe\").height(parent_heigth);\r\n");
      out.write(" \t\t\twindow.parent.setHeigth(parent_heigth);\r\n");
      out.write("\t    }\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
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
