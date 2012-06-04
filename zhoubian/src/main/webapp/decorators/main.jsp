<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title><decorator:title default="SiteMesh的装饰器页"/></title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/960grid/reset.css"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/960grid/text.css"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/960grid/960.css" />
	<link type="text/css" href="<%=request.getContextPath()%>/css/jquery-ui-1.8.18.custom.css" rel="stylesheet"/>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css"/>
	<script src="<%=request.getContextPath()%>/js/jquery-1.7.1.js" type="text/javascript"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.bgiframe-2.1.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.core.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.mouse.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.button.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.draggable.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.position.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.resizable.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.dialog.js"></script>
    <script src="<%=request.getContextPath()%>/js/main.js" type="text/javascript"></script>
	<decorator:head/>
    <script type="text/javascript">
    function goSpecificLocation(){
    	var $locationListDiv = $("#locationList");
    	if($locationListDiv.length == 0){
        	$locationListDiv = $("<div id='locationList' title='请选择指定位置'></div>").load("<%=request.getContextPath()%>/user_listLocations.do",function(){
            	$(this).dialog({height:400,width:400,modal:true,autoOpen:true});
            });
        }else{
        	$locationListDiv.dialog({height:400,width:600,modal:true,autoOpen:true});
        }
    }
	</script>
</head>
<body>
<div id="content" class="container_12" style="background-color:#CCC; margin-bottom:25px;">
	<div id="head" class="grid_12" style="height:60px;">
		<div id="logo" style="float:left;margin-top:20px;"><img src="<%=request.getContextPath()%>/images/logo.gif"/></div>
        <div id="menu1" style="float:left; margin-top:20px; margin-left:20px;">
        	<ul>
            	<li class="main"><a href="<%=request.getContextPath()%>/user_locationIndex.do">首页</a></li>
                <li class="main">
                	<a href="#">好友</a>
                	<ul>
                		<li class="first-child"><a href="#">我的全部好友</a></li>
                    	<li><a href="#">好友管理</a></li>
                    	<li><a href="#">邀请好友加入</a></li>
                	</ul>
                </li>
                <li class="main">
                	<a href="#">导航</a>
                    <ul>
                    	<li class="first-child"><a href="<%=request.getContextPath()%>/chatIndex.do">周边聊天</a></li>
                    </ul>
                </li>
                <li class="main"><a href="#">消息</a></li>
            </ul>
        </div>
        <div id="menu2" style="float:right;margin-top:20px; margin-right:40px;">
        	<ul>
            	<li class="main">
                	<a href="#">设置</a>
                	<ul>
                    	<li class="first-child"><a href="#">账户设置</a></li>
                        <li><a href="<%=request.getContextPath()%>/map_mylocation.do">位置管理</a></li>
                    </ul>
                </li>
                <li class="main">
                	<a href="#">位置切换</a>
                    <ul>
                    	<li class="first-child"><a href="<%=request.getContextPath()%>/user_goDefaultLocation.do">默认位置</a></li>
                        <li><a href="<%=request.getContextPath()%>/user_goPreviousLocation.do">上次位置</a></li>
                        <li><a href="javascript:goSpecificLocation();">指定位置</a></li>
                        <li><a href="<%=request.getContextPath()%>/map_mylocation.do">新建位置</a></li>
                    </ul>
                </li>
                <li class="main"><a href="<%=request.getContextPath()%>/user_logout.do">退出</a></li>
            </ul>
        </div>
	</div>
    <div class="clear"></div>
	<decorator:body/>
    <div id="foot" class="grid_12" style="height:40px;">
    	<div style="float:left">
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">关于我们</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">帮助</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">客服</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">给我们提意见</a>												        </div>
        <div style="float:right">&copy;2011 周边网 京ICP证080482号 京公网安备110000000003号&nbsp;&nbsp;&nbsp;&nbsp;</div>
    </div>
    <div class="clear"></div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	top.document.getElementById("toolbar").style["left"]=$("#content").offset().left+"px";
	top.document.getElementById("toolbar").style["marginLeft"]="0";
});
</script>
</body>
</html>