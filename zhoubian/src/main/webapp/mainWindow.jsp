<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>周边网</title>
<link type="text/css" href="<%=request.getContextPath()%>/css/jquery-ui-1.8.18.custom.css" rel="stylesheet"/>
<link type="text/css" href="<%=request.getContextPath()%>/css/jquery.dialogr.css" rel="stylesheet"/>
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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui.dialogr.js"></script>
<style type="text/css">
html,
body,
#container{
	height:100%;
	margin:0;
	padding:0;
	border:0;
}
#container{
	position:relative;
	overflow:hidden;
}
#toolbar{
	position:absolute;
	z-index:10;
	left:50%;
	bottom:0;
	height:23px;
	border:1px solid #959595;
	width:958px;
	margin-left:-480px;
	background-color:#A5A5A5;
}
</style>
</head>
<body>
<div id="container">
	<div style="height:100%; width:100%; overflow:hidden; z-index:3; position:absolute; left:0; top:0; margin:0; padding:0;">
	<iframe name="contentfrm" src='<%=request.getContextPath()%>/<%=java.net.URLDecoder.decode(request.getParameter("target"),"UTF-8") %>' height="100%" width="100%" frameborder="0">
	</iframe>
    </div>
    <div id="toolbar">
    <div style="float:right; font-size:12px; line-height:23px; height:23px; padding-right:4px; padding-left:4px; border-left:1px solid #959595"><a href="javascript:openChatWindow()">周边聊天</a></div>
    </div>
    <div id="chatWindow" title="周边聊天" style="display:none">
    <iframe name="chatFrm" width="100%" height="100%" id="chatFrm" frameborder="0"></iframe>
    </div>
</div>
<script type="text/javascript">
function openChatWindow(){
	if(!document.getElementById("chatFrm").src){
		var chatWindow = $("#chatWindow").dialogr({height:400,width:800,zIndex:1000,modal:false,resizable:true,draggable:true,autoOpen:true,autoResize:true,minimized:false});
		/*var widget = chatWindow.dialog("widget");
		widget.draggable("option","iframeFix",true);*/
		chatWindow.dialogr({
			resizeStart: function(event, ui){
				$('<div id="maskdiv"></div>')
				.css({
					width:"100%",height:"100%",position:"absolute",zIndex:100,left:0,top:0
				})
				.appendTo("body");
			},
			resizeStop: function(event, ui){
				$("#maskdiv").remove();
			},
			beforeclose: function(event, ui){
				//chatWindow.dialogr("close");
				return true;
			}
		});
		document.getElementById("chatFrm").src = "<%=request.getContextPath()%>/chatIndex.do";
	}else
		showChatWindow();
}
function hideChatWindow(){
	$("#chatWindow").dialogr("close");
}
function showChatWindow(){
	$("#chatWindow").dialogr("open");
}
function closeChatWindow(){
	$("#chatWindow").dialogr("destroy");
	document.getElementById("chatFrm").src = "";
}
</script>
</body>
</html>