<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>login success</title>
<link type="text/css" href="<%=request.getContextPath()%>/css/jquery-ui-1.8.4.custom.css" rel="stylesheet"/>
<script src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.bgiframe-2.1.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.core.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.widget.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.mouse.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.draggable.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.position.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.resizable.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.tabs.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.dialog.min.js"></script>
</head>
<body>
<ul><li><a href="myspace.jsp" target="_top">进入个人空间</a></li><li><a href="<%=request.getContextPath()%>/user_goDefaultLocation.do" target="_top">进入默认位置</a></li>
<li><a href="<%=request.getContextPath()%>/user_goPreviousLocation.do" target="_top">进入上次位置</a></li>
<li><a href="javascript:top.selectLocation()">进入指定位置</a></li>
<li><a href="<%=request.getContextPath()%>/user_logout.do?flag=true">退出登录</a></li>
</ul>
</body>
</html>