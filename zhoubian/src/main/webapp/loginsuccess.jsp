<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<ul><li><a href="myspace.jsp" target="_top">进入个人空间</a></li><li><a href="<%=request.getContextPath()%>/user_mainWindow.do?target=user_goDefaultLocation.do" target="_top">进入默认位置</a></li>
<li><a href="<%=request.getContextPath()%>/user_mainWindow.do?target=user_goPreviousLocation.do" target="_top">进入上次位置</a></li>
<li><a href="javascript:top.selectLocation()">进入指定位置</a></li>
<li><a href="<%=request.getContextPath()%>/user_logout.do?flag=true">退出登录</a></li>
</ul>