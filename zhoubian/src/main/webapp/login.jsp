<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>login</title>
</head>
<style type="text/css">
form{margin:0px}
label{float:left; width:60px;}
input{width:100px; border:1px solid #808080}
#sbutton{margin-left:20px; margin-top:5px; width:40px;}
form br{clear:left}
</style>
<body>
<form action="<%=request.getContextPath()%>/user_login.do" method="post">
<label for="user">姓名：</label><input type="text" id="user" name="user"/><br/>
<label for="password">密码：</label><input type="password" id="password"/><br/>
<input type="submit" id="sbutton" value="登录"/>&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/user_register.do" target="_top">没有注册</a>
</form>
</body>
</html>