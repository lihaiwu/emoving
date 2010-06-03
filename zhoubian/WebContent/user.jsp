<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
users:<br>
<s:iterator id="u" value="users" var="u">
	id:<s:property value="id" /><br>
	name:<s:property value="name" /><br>
	password:<s:property value="password" /><br>
</s:iterator>
</body>
</html>