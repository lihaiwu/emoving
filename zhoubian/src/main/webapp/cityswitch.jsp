<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
ul {
	list-style-type: none;
	padding-left: 10px;
}

ul li {
	float: left;
	color: #039;
	height: 20px;
	font-size: 13px;
	padding-right: 15px;
	padding: 5px;
}

table,table td {
	border-collapse: collapse;
	border: solid #B1C8D7;
	border-width: 1px 0 0 1px;
}

table td {
	border-width: 0 1px 1px 0;
}
</style>
</head>
<body>
<table border="0">
	<s:iterator value="cityMap.keySet()" id="vkey">
		<tr>
			<td width="30px" align="center" valign="middle" style="font-weight:bold;color: #6694E3;"><s:property value="#vkey" /></td>
			<td width="780px">
			<ul>
				<s:iterator value="cityMap.get(#vkey)">
					<li><a href=""><s:property value="cityName" /></a></li>
				</s:iterator>
			</ul>
			</td>
		</tr>
	</s:iterator>
</table>
</body>
</html>