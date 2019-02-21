package org.apache.jsp.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.yonyou.business.entity.TokenEntity;
import com.yonyou.business.entity.TokenUtil;
import com.yonyou.util.wsystem.service.ORG;

public final class notice_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\r\n");

	String ContextPath = request.getContextPath();

      out.write("\r\n");
      out.write("<html lang=\"zh-cn\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"utf-8\">\r\n");
      out.write("<title>公告展示</title>\r\n");
      out.write("<!--公告弹出页面 -->\r\n");
      out.write("<!-- 设置浏览器默认渲染模式 -->\r\n");
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("<meta name=\"renderer\" content=\"webkit\">\r\n");
      out.write("<script src=\"");
      out.print(ContextPath);
      out.write("/vendor/jquery/jquery-1.11.2.min.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(ContextPath);
      out.write("/pages/js/bootstrap/js/bootstrap.min.js\"></script>\r\n");
      out.write("<link rel=\"stylesheet\"\r\n");
      out.write("\thref=\"");
      out.print(ContextPath);
      out.write("/pages/js/bootstrap/css/bootstrap.min.css\">\r\n");
      out.write("<!-- bootstrapIE8补丁 -->\r\n");
      out.write("<script src=\"");
      out.print(ContextPath);
      out.write("/vendor/bootstrap/js/html5.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(ContextPath);
      out.write("/vendor/bootstrap/js/respond.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<div class=\"container\">\r\n");
      out.write("\t\t<div class=\"panel panel-primary\" style=\"margin-top: 10%;\">\r\n");
      out.write("\t\t\t<div class=\"panel-heading\">\r\n");
      out.write("\t\t\t\t<h3 class=\"panel-title\" style=\"text-align: center;\">公告展示</h3>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div>\r\n");
      out.write("\t\t\t\t<table class=\"table\" style=\"border-top: 2px #c0c0c0 solid\">\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<th>公告标题:<span id=\"noticeTitle\"></span></th>\r\n");
      out.write("\t\t\t\t\t\t<th class=\"pull-right\">公告时间:<span id=\"noticeTime\"></span></th>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t</table>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<br>\r\n");
      out.write("\r\n");
      out.write("\t\t\t<div>\r\n");
      out.write("\t\t\t\t<table class=\"table\" style=\"border-top: 2px #c0c0c0 solid\">\r\n");
      out.write("\t\t\t\t\t<th>公告内容:</th>\r\n");
      out.write("\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t<br>\r\n");
      out.write("\t\t\t\t<div class=\"well\">\r\n");
      out.write("\t\t\t\t\t<p id=\"noticeText\"></p>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<br> <br>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("//取url参数\r\n");
      out.write("function GetQueryString(name) {\r\n");
      out.write("\tvar reg = new RegExp(\"(^|&)\" + name + \"=([^&]*)(&|$)\",\"i\");\r\n");
      out.write("\tvar r = window.location.search.substr(1).match(reg);\r\n");
      out.write("\tif (r!=null) return unescape(r[2]); return null;\r\n");
      out.write("}\r\n");
      out.write("var State=GetQueryString(\"State\")\r\n");
      out.write("var id=GetQueryString(\"ID\");\r\n");
      out.write("\r\n");
      out.write("if(State==1){\r\n");
      out.write("$.ajax({  \r\n");
      out.write("    type:'get',\r\n");
      out.write("    url:\"/system/base?cmd=noticeJSON&dataSourceCode=NF_NOTICE_SHOW\",  //全部数据\r\n");
      out.write("    dataType:'json', \r\n");
      out.write("    data : {\"ID\":id},\r\n");
      out.write("    success:function(json){  \r\n");
      out.write("    //alert(\"JSON数据获取成功！\"); \r\n");
      out.write("    // 根据url中的参数ID取对应JSON数组内的值\r\n");
      out.write("    for(var i=0;i<=json.length;i++){\r\n");
      out.write("   \t\tif(json[i][\"ID\"]==id)\r\n");
      out.write("   \t\t{\r\n");
      out.write("   \t\tvar\tTITLE=json[i][\"TITLE\"];\r\n");
      out.write("   \t\tvar\tSTATE_DATE=json[i][\"STATE_DATE\"];\r\n");
      out.write("   \t\tvar\tNOTICE_MESSAGE=json[i][\"NOTICE_MESSAGE\"];\r\n");
      out.write("   \t\t$(\"#noticeTitle\").append(TITLE);\r\n");
      out.write("   \t\t$(\"#noticeTime\").append(STATE_DATE);\r\n");
      out.write("   \t\t$(\"#noticeText\").append(NOTICE_MESSAGE);\r\n");
      out.write("   \t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("        },  \r\n");
      out.write("        error:function(data){  \r\n");
      out.write("            alert(\"allNoticeJSON数据获取失败，请联系管理员！\");  \r\n");
      out.write("        }  \r\n");
      out.write("    });\r\n");
      out.write("\r\n");
      out.write("}else{\r\n");
      out.write("$.ajax({  \r\n");
      out.write("    type:'get',\r\n");
      out.write("    url:\"/system/base?cmd=allNoticeJSON&dataSourceCode=NF_NOTICE_SHOW\",  //全部数据\r\n");
      out.write("    dataType:'json', \r\n");
      out.write("    data : {\"ID\":id},\r\n");
      out.write("    success:function(json){  \r\n");
      out.write("    //alert(\"JSON数据获取成功！\"); \r\n");
      out.write("    // 根据url中的参数ID取对应JSON数组内的值\r\n");
      out.write("    for(var i=0;i<=json.length;i++){\r\n");
      out.write("   \t\tif(json[i][\"ID\"]==id)\r\n");
      out.write("   \t\t{\r\n");
      out.write("   \t\tvar\tTITLE=json[i][\"TITLE\"];\r\n");
      out.write("   \t\tvar\tSTATE_DATE=json[i][\"STATE_DATE\"];\r\n");
      out.write("   \t\tvar\tNOTICE_MESSAGE=json[i][\"NOTICE_MESSAGE\"];\r\n");
      out.write("   \t\t$(\"#noticeTitle\").append(TITLE);\r\n");
      out.write("   \t\t$(\"#noticeTime\").append(STATE_DATE);\r\n");
      out.write("   \t\t$(\"#noticeText\").append(NOTICE_MESSAGE);\r\n");
      out.write("   \t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("        },  \r\n");
      out.write("        error:function(data){  \r\n");
      out.write("            alert(\"allNoticeJSON数据获取失败，请联系管理员！\");  \r\n");
      out.write("        }  \r\n");
      out.write("    });\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(" $(function(){\r\n");
      out.write("\t\r\n");
      out.write("    }); \r\n");
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
