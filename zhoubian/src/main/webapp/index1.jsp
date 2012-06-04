<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>周边网登录</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/960grid/reset.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/960grid/text.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/960grid/960.css" />
<link type="text/css" href="<%=request.getContextPath()%>/css/jquery-ui-1.8.18.custom.css" rel="stylesheet"/>
<script src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/jquery.tools.overlay.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.core.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.widget.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.mouse.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.draggable.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.position.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.resizable.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.tabs.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.dialog.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.form.js"></script>
<style type="text/css">
#loginTable td{
	padding:4px;
}
#progress{
	display:none;
	width:208;
	height:13;
	z-index:10000;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	var trigger = $("#progress").overlay({
					top: '30%',
					mask: {
						color: '#ccc',
						loadSpeed: 200,
						opacity: 0.5
					},
					closeOnClick: false
				});
	$('#loginForm').submit(function(){
		$(this).ajaxSubmit({
			beforeSubmit: function(formData, jqForm, options){
				trigger.overlay().load();
				return true;
			},
			success: function(responseText, statusText){
				trigger.overlay().close();
				$('#selectLocation').html(responseText);
				$('#selectLocation').dialog({
					autoOpen: true,
					modal: true,
					position: 'center',
					title: '请选择位置'
				});
			},
			error: function(request, textStatus, errorThrown){
				trigger.overlay().close();
				if(request.status==403){
					$('#selectLocation').html(request.responseText);
					$('#selectLocation').dialog({
						autoOpen: true,
						modal: true,
						position: 'center',
						title: '错误'
					});
				}else{
					$('#selectLocation').html("未知错误！");
					$('#selectLocation').dialog({
						autoOpen: true,
						modal: true,
						position: 'center',
						title: '错误'
					});
				}
			}
		});
		return false;	
	});
});
function selectLocation(){
	$("#selectLocation").dialog("close");
	var $locationListDiv = $("#locationList");
    if($locationListDiv.length == 0){
        $locationListDiv = $("<div id='locationList' title='请选择指定位置'></div>").load("<%=request.getContextPath()%>/user_listLocations.do?mainWindow=true", function(){
			$(this).dialog({height:400,width:400,modal:true,autoOpen:true});
		});
    }else{
    	$locationListDiv.dialog({height:400,width:600,modal:true,autoOpen:true});
    }
}
</script>
</head>
<body>
<div id="progress"><img src="<%=request.getContextPath()%>/images/loadingAnimation.gif"/></div>
<div id="selectLocation"></div>
<div id="content" class="container_12" style="background-color:#CCC">
	<div id="head" class="grid_12" style="height:60px;">
		<div id="logo" style="float:left;margin-top:20px;"></div>
        <div id="menu" style="float:right;margin-top:20px;"><a href="#">注册</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#">登录</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#">帮助</a>&nbsp;&nbsp;&nbsp;&nbsp;</div>
	</div>
    <div class="clear"></div>
    <div style="overflow:hidden">
    <div id="left" class="grid_4" style="min-height:500px">
    	<form method="post" id="loginForm" action="<%=request.getContextPath()%>/user_login.do">
        	<table width="100%" border="0" id="loginTable">
            	<tr><td width="60px"><label for="loginName">用户名：</label></td><td><input type="text" name="loginName" id="loginName"/></td></tr>
                <tr><td><label for="password">密码：</label></td><td><input type="password" name="password" id="password"/></td></tr>
                <tr><td colspan="2"><input type="checkbox" name="loginStatus" id="loginStatus"/>记住登录状态</td></tr>
                <tr><td colspan="2"><input type="submit" value="登录" id="submitBtn"/>&nbsp;&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/user_forgetPassword.do">忘记密码</a></td></tr>
            </table>
        </form>
        <table width="90%" border="0" bgcolor="#CCCCCC" height="2"></table>
        <img src="<%=request.getContextPath()%>/images/u23.gif"/>还没开通你的周边网帐户?<br/>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/user_register.do">立即注册</a>
    </div>
	<div id="right" class="grid_8 omega" style="background-color:#FFFFFF; padding-bottom:10000px; margin-bottom:-10000px;">
    	<span style="color:red; font-size:14px; font-weight:bold;">周边网</span><br/>
    	找到我的位置，关注我的周边，让信息不再遥远，让网络脚踏实地！
    	<div id="adv"></div>
    	<div id="notice"></div>
    </div>
    </div>
    <div class="clear"></div>
    <div id="foot" class="grid_12" style="height:40px;">
    	<div style="float:left">
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">关于我们</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">帮助</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">客服</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">给我们提意见</a>												        </div>
        <div style="float:right">&copy;2011 周边网 京ICP证080482号 京公网安备110000000003号&nbsp;&nbsp;&nbsp;&nbsp;</div>
    </div>
    <div class="clear"></div>
</div>
<script type='text/javascript' src='http://buildmypinnedsite.com/script/v1.0/634627120948251015.js'></script>
</body>
</html>