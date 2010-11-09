<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="Keywords" content="我的周边，交友"/>
<meta name="description" content="我的周边首页"/>
<title>我的周边</title>
<style type="text/css">
body{ font-family:Verdana; font-size:14px; margin:0; }

#container{margin:0 auto; width:900px; }
#header{height:100px; background:#9c6; margin-bottom:5px;}
#menu{height:30px; background:#693; margin-bottom:5px;}
#mainContent{ margin-bottom:5px; overflow:hidden;}
#sidebar{float:right; width:200px; background:#cf9; padding-bottom:100000px; margin-bottom:-100000px;}
#content{ float:left; width:695px; background:#ffa;}
#footer{clear:both;height:60px; background:#9c6;}
.box {
	width: 330px;
	height: 150px;
	float: left;
	border: 1px solid #B1C8D7;
	margin: 2px;
}

.box .title {
	height: 23px;
	line-height: 23px;
	background-color: #F0F5FB;
}

.box ul {
	list-style-type: none;
	padding-left: 10px;
}

.box ul li {
	float: left;
	color: #039;
	height: 20px;
	font-size: 14px;
	padding-right: 15px;
	padding-bottom: 5px;
	white-space:nowrap;
}

.box ul li a {
	color: #039;
	text-decoration: underline;
}

.box ul li a:hover {
	color: #cd0100;
	text-decoration: none;
}

.box .title span {
	color: #E7690C;
	font-weight: bold;
}
</style>
</head>
<body>
<div id="container">
	<div id="header">This is the Header</div>
	<div id="menu"><a href="chatIndex.do">周边聊天</a>|<a href="#">周边论坛</a></div>
	<div id="mainContent">
		<div id="sidebar">this is sidebar</div>
		<div id="content">
        <span style="color:#133db6">分类信息</span>&nbsp;&nbsp;<a href="#"><span style="color:#133db6">更多&gt;&gt;</span></a>
        <span style="color:#0000FF; float:right"><a href="<%=request.getContextPath()%>/info_add.do">+提交信息</span></span>
        <br/>
        
        <s:iterator value="classes.keySet()" id="vkey" status="stat">
            <s:if test="classes.keySet().size() > 10 && #stat.index+1 > 10">
                <s:if test="#stat.index==10">
                    <span style="float: right;padding-right: 20px;"><a href="#">更多&gt;&gt;</a></span>
                </s:if>
            </s:if>
            <s:else>
                <s:iterator value="classes.get(#vkey)">
                    <div class=box>
                    <div class=title><span><s:property value="className" /></span></div>
                    <ul>
                        <s:iterator value="subClasses">
                            <li><A href="/Article/junshicankao/14418.html" target=_blank><s:property
                                value="subclassName" /></A></li>
                        </s:iterator>
                    </ul>
                    </div>
                </s:iterator>
            </s:else>
        </s:iterator>
        </div>
	</div>
	<div id="footer" align="center">周边网版权所有</div>
</div>
</body>
</html>