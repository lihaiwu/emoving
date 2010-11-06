<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
body{
	padding:0px;
	margin:0px;
}
.content {
	width: 685px;
}

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
<div class="content">
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
</s:iterator></div>

</body>
</html>