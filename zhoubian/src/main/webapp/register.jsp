<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="Keywords" content="注册用户，周边网"/>
<meta name="description" content="注册用户"/>
<title>注册用户</title>
<style type="text/css">
body{ font-family:Verdana; font-size:14px; margin:0; }

#container{margin:0 auto; width:900px; }
#header{height:100px; background:#9c6; margin-bottom:5px;}
#menu{height:30px; background:#693; margin-bottom:5px;}
#mainContent{height:500px; margin-bottom:5px; margin-left:0; margin-right:0; text-align:center}
#footer{height:60px; background:#9c6;}
form{margin:0 auto; width:70%; text-align:left}
label{float:left; width:80px}
input{width:160px; border:1px solid #808080}
#position{width:40px}
br{clear:left}

</style>
<script type="text/javascript">
function setup(){
	window.open("<%=request.getContextPath()%>/map_mylocation.do","我的位置","location=no;menubar=no;toolbar=no;");
}
</script>
</head>
<body>
<div id="container">
	<div id="header">This is the Header</div>
	<div id="menu">This is the Menu</div>
	<div id="mainContent">
		<form name="form1" action="<%=request.getContextPath()%>/user_submitRegister.do" method="post">
		<fieldset>
		<legend>基本信息</legend>
		<label for="loginNname">用户名：</label><input id="loginName" type="text" name="user.loginName"/><br/>
		<label for="password">密码：</label><input id="password" type="password" name="user.password"/><br/>
		<label for="email">电子邮件：</label><input id="email" type="text" name="user.email" /><br/>
		<label for="sex">性别：</label><select id="sex" name="user.sex"><option value="1">男</option><option value="2">女</option></select><br/>
		<label for="position">居住地：</label><input type="button" id="position" value="设置" onclick="javascript:setup()"/><br/>
		</fieldset>
		<fieldset>
		<legend>高级信息</legend>
		</fieldset>
		<div style="text-align:center"><input type="submit" value="提交" style="width:40px" />&nbsp;&nbsp;<input type="reset" value="重置" style="width:40px"/></div>
		</form>
	</div>
	<div id="footer" align="center">周边网版权所有</div>
</div>
</body>
</html>