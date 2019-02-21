<%@page import="com.yonyou.util.wsystem.service.ORG"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/pages/js/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/pages/js/bootstrap-table/src/bootstrap-table.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/pages/js/examples.css">
<script src="<%=request.getContextPath()%>/pages/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/pages/js/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/pages/js/bootstrap-table/src/bootstrap-table.js"></script>
<script src="<%=request.getContextPath()%>/pages/js/reference.js"></script>

<script type="text/javascript">
var context = '<%=request.getContextPath()%>';
var dataSourceCode ='<%=request.getParameter("pageCode")%>';
var pageName ='<%=request.getParameter("pageName")%>';
var menuCode ='<%=request.getParameter("menuCode")!=null?request.getParameter("menuCode"):""%>';
var rolecode='<%=request.getAttribute("rolecode")%>';
var corpcode='<%=request.getAttribute("corpcode")%>';
var token='<%=request.getAttribute("token")%>';
var loginUser = '<%=request.getAttribute("loginUser")%>';
</script>
<script src="<%=request.getContextPath()%>/js/public.js"></script>
<script src="<%=request.getContextPath()%>/js/properties.js"></script>
<script src="<%=request.getContextPath()%>/js/crud.js"></script>
<script src="<%=request.getContextPath()%>/js/bootTable.js"></script>
<script src="<%=request.getContextPath()%>/js/common.js"></script>
<script src="<%=request.getContextPath()%>/js/initPage.js"></script>
<script src="<%=request.getContextPath()%>/js/buttonJs.js"></script>
<script src="<%=request.getContextPath()%>/vendor/bootstrap/js/bootstrap-treeview.js"></script>

<!-- bootstrapIE8 -->
<script src="<%=request.getContextPath()%>/vendor/bootstrap/js/html5.js"></script>
<script src="<%=request.getContextPath()%>/vendor/bootstrap/js/respond.js"></script>
