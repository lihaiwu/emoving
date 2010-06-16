<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="Keywords" content="城市首页，周边，交友"/>
<meta name="description" content="城市首页"/>
<title>城市首页</title>
<style type="text/css">
body{ font-family:Verdana; font-size:14px; margin:0; }

#container{margin:0 auto; width:900px; }
#header{height:100px; background:#9c6; margin-bottom:5px;}
#menu{height:30px; background:#693; margin-bottom:5px;}
#mainContent{height:500px; margin-bottom:5px;}
#sidebar{float:right; width:200px; height:500px; background:#cf9;}
#content{ float:left; width:695px; height:500px; background:#ffa;}
#footer{height:60px; background:#9c6;}
iframe{margin:0; border:0; padding:0; width:200px;}
</style>
</head>
<body>
<div id="container">
	<div id="header">This is the Header</div>
	<div id="menu">This is the Menu</div>
	<div id="mainContent">
		<div id="sidebar">
		<iframe id="login" src="<%=request.getContextPath()%>/user_preLogin.do" frameborder="0"></iframe>
		</div>
		<div id="content">分类信息</div>
	</div>
	<div id="footer" align="center">周边网版权所有</div>
</div>
</body>
</html>