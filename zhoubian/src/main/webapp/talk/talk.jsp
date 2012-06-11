<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>本地聊天</title>
<%@ include file="/common/common.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/xheditor/xheditor_skin/default/ui.css" />
<script src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/xheditor/xheditor-1.1.12-zh-cn.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/ChatAction.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<link type="text/css" href="<%=request.getContextPath()%>/css/jquery-ui-1.8.18.custom.css" rel="stylesheet"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.datetime.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.position.js"></script>
</head>
<body>
<style type="text/css">
html,body{
	height:100%;
	padding:0;
	margin:0;
}
#container{
	height:100%;
	margin:0;
	padding:0;
	width:100%;
	overflow:hidden;
}
#left{
	height:100%;
	margin-right:240px;
	position:relative;
}
#right{
	height:100%;
	width:240px;
	position:absolute;
	top:0;
	right:0;
}
#content{
	border:1px #C5C5C5 solid;
	padding:4px;
	text-align:left;
	font-size: 12px;
	overflow-y: auto;
}
#input{
	width:100%;
	position:absolute;
	left:0;
	bottom:0;
}
#userlistbar{
	background-color:#FED18E;
	height:30px;
	text-align:left;
	line-height:30px;
}
#userlistbar span{
	font-weight:bold;
	font-style:italic;
}
#userlist{
	width:230px;
	border:1px #C5C5C5 solid;
	padding:4px;
}
#userlist ul{
	list-style:none;
	padding:2px;
	text-align: left;
}
#userlist ul li{
	padding-left:16px;
	background-image:url("images/user.png");
	background-repeat:no-repeat;
}
</style>
<div id="container">
	<div id="left">
    	<div id="content"></div>
        <div id="input">
        	<form name="form1" id="form1" action="<%=request.getContextPath()%>/chat_sendMessage.do">
            	<textarea name="message" id="message" class="xheditor {tools:'full',width:'100%',height:200}"></textarea>
                <br/>
                <div style="border:1px solid #C5C5C5; border-top:0; text-align:left;">
                	<table width="100%">
                    	<tr>
                        	<td style="color: rgb(204, 204, 204); font-size:12px;" align="left">编辑区域中按快捷键Ctrl+Enter提交表单</td>
                    		<td align="right"><input type="submit" value="提交"/><input type="reset" value="重置"/></td>
                        </tr>
                    </table>
                </div>
            </form>
        </div>
        <div id="action">
        
        </div>
    </div>
    <div id="right">
    	<div id="userlistbar">
        	<img style="height:26px;margin:2px 0;float:left;" src="<%=request.getContextPath()%>/images/persons.gif" onclick="refreshUserList()"/><span>在线用户</span><img style="margin:7px 0;float:right;" src="<%=request.getContextPath()%>/images/refresh.gif" alt="刷新用户列表" title="刷新用户列表"/>
        </div>
    	<div id="userlist"></div>
    </div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	dwr.engine.setActiveReverseAjax(true);
	$("#content").height($(window).height()-$("#input").outerHeight()-10);
	$("#userlist").height($(window).height()-40);
	$("#userlist").load("<%=request.getContextPath()%>/getOnlineUsers.do");
	$(window).resize(function(){
		$("#content").height($(window).height()-$("#input").outerHeight()-10);
		$("#userlist").height($(window).height()-40);
	});
	$("#form1").bind("submit",function(){
		//alert($("#message").val());	
		ChatAction.addChat($("#message").val(), loginName, function(){
			$("#message").val('');
		});
		return false;
	});
});
function receiveChats(chat){
	//alert(chat.text);
	$("#content").append('<p><span style="color:green">'+chat.sender+': </span>'+$.dateToTime(chat.date)+'</p><p style="padding:1px 1px 10px 5px">'+chat.text+'</p>');
	var d = $("#content")[0].scrollHeight-$("#content").innerHeight();
	$("#content").scrollTop(d);
	if(typeof top.frames[0].addMessage != "undefined"){
		var message = new Object();
		message.sender = chat.sender;
		message.date = $.dateToTime(chat.date);
		message.text = chat.text;
		top.frames[0].addMessage(message);
	}
}
function refreshUserList(){
	$("#userlist").load("<%=request.getContextPath()%>/getOnlineUsers.do");
}
</script>
</body>
</html>