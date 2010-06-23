<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
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
<div class="content"><s:iterator value="classes.keySet()"
	id="vkey">
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
</s:iterator></div>

</body>
</html>