<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="Keywords" content="城市首页，周边，交友" />
<meta name="description" content="城市首页" />
<title>城市首页</title>
<style type="text/css">
body {
	font-family: Verdana;
	font-size: 14px;
	margin: 0;
}

#container {
	margin: 0 auto;
	width: 900px;
}

#header {
	height: 100px;
	background: #9c6;
	margin-bottom: 5px;
}

#menu {
	height: 30px;
	background: #693;
	margin-bottom: 5px;
}

#mainContent {
	height: 1000px;
	margin-bottom: 5px;
}

#sidebar {
	float: right;
	width: 200px;
	height: 200px;
	background: #cf9;
}
#login,#hot_user,#hot_info {
	float: right;
	width: 200px;
	height: 200px;
	border: 1px solid #B1C8D7;
	margin-bottom: 10px;
}
#sidebar_other{
	float: right;
	width: 200px;
	height: 350px;
	border: 1px solid #B1C8D7;
	margin-bottom: 10px;
}
#search_area {
	float: left;
	width: 695px;
	height: 50px;
	background-color: #E9E9E9;
}

#adv {
	float: left;
	width: 695px;
	height: 150px;
	background-color: #E8EFF7;
}

#content {
	float: left;
	width: 695px;
	height: 800px;
	/*background: #ffa;*/
}

#footer {
	height: 30px;
	background: #9c6;
	margin-bottom: 10px;
}
</style>
</head>
<body>
<div id="container">
<div id="header">This is the Header</div>
<div id="menu">This is the Menu</div>
<div id="mainContent">
<div id="sidebar">
	<div id="login"><iframe src="<%=request.getContextPath()%>/user_preLogin.do" frameborder="0"></iframe></div>
	<div id="hot_info">热门信息排行</div>
	<div id="hot_user">活跃用户排行</div>
	<div id="sidebar_other">其他信息区域</div>
</div>

<!-- 内容搜索区域 -->
<div id="search_area">内容搜索区域</div>
<!-- 广告展示区域 -->
<div id="adv">广告展示区域</div>
<!-- 分类信息显示区域 -->
<div id="content"><iframe style="width: 100%; height: 100%;"
	src="<%=request.getContextPath()%>/category_list.do" frameborder="0"></iframe></div>
</div>
<div id="footer" align="center">周边网版权所有</div>
</div>
</body>
</html>