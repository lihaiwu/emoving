<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="net.zhoubian.app.model.User" %>
<%	User user = (User)session.getAttribute("user");
	String loginName = "";
	if(null != user){
		loginName = user.getLoginName();
	}
%>
<script language="javascript">
var appbase = "<%=request.getContextPath()%>";
var loginName = "<%=loginName%>";
</script>