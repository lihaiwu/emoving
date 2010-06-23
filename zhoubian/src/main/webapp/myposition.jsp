<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="Keywords" content="地图 搜索，位置管理，周边旅游，周边购物，周边交友"/>
<meta name="description" content="我的位置管理"/>
<meta name="viewport" content="initial-scale=1.0,user-scalable=no"/>
<title>我的位置管理</title>
<style type="text/css">
body{
	font-family:Verdana; font-size:14px; margin:0;
}
#container{margin:auto; width:100%;}
#header{height:100px; background:#9c6; margin-bottom:5px;}
#menu {height:30px; background:#693; margin-bottom:5px;}
#mainContent{height:500px; margin-bottom:5px;}
#sidebar{float:left; width:200px; height:500px; background:#cf9}
#content{margin-left:205px !important; margin-left:202px; height:500px; background:#ffa;}
</style>
<script type="text/javascript" src="http://app.mapabc.com/apis?&t=flashmap&v=2.3.3&key=<%=net.zhoubian.app.util.SystemProperties.getProperty("mapkey")%>"></script>
<script type="text/javascript">
function initialize(){
	var mapoption = new MMapOptions();
	mapoption.zoom = 13;
	mapoption.toolbar = DEFAULT;
	mapoption.toolbarPos = new MPoint(0,0);
	mapoption.overviewMap = SHOW;
	var mapObj = new MMap("map_canvas",mapoption);
}
</script>
</head>
<body>
<div id="container">
	<div id="header">This is the Header</div>
	<div id="menu">This is the Menu</div>
	<div id="mainContent">
		<div id="sidebar">This is the sidebar</div>
		<div id="content"><div id="map_canvas" style="width:100%;height:100%"></div></div>
	</div>
</div>
<script type="text/javascript">
initialize();
</script>
</body>
</html>