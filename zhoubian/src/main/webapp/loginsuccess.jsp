<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>login success</title>
</head>
<body>
<ul><li><a href="myspace.jsp" target="_top">进入个人空间</a></li><li><a href="<%=request.getContextPath()%>/user_goDefaultLocation.do" target="_top">进入默认位置</a></li>
<li><a href="<%=request.getContextPath()%>/user_logout.do?flag=true">退出登录</a></li>
</ul>
</body>
</html>