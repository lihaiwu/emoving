<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>周边网</title>
<script type="text/javascript">
function addMessage(message){
	$("#rt_talk").append('<p><span style="color:green">'+message.sender+': </span>'+message.date+'</p><p style="padding:1px 1px 10px 5px">'+message.text+'</p>');
	var d = $("#rt_talk")[0].scrollHeight-$("#rt_talk").innerHeight();
	$("#rt_talk").scrollTop(d);
}
</script>
</head>
<body>
    <div style="overflow:hidden;">
    	<div id="left" class="grid_3">
        	<div id="online_user" style="min-height:200px; background-color:#FFF; margin-bottom:10px;">周边在线用户</div>
            <div id="category" style="min-height:400px; background-color:#FFF; margin-bottom:10px;">分类信息目录列表</div>
            <div id="new_information" style="min-height:200px; background-color:#FFF; padding-bottom:10000px; margin-bottom:-10000px">最新提交信息</div>
        </div>
        <div id="center" class="grid_6">
        	<div id="rt_talk" style="min-height:200px; max-height:400px; overflow:hidden; background-color:#FFF; margin-bottom:10px;">实时聊天信息<a href="javascript:top.openChatWindow()">>></a>
            	
            </div>
            <div id="new_post" style="min-height:600px; background-color:#FFF; padding-bottom:10000px; margin-bottom:-10000px">最新帖子</div>
        </div>
        <div id="right" class="grid_3">
        	<div id="current_location" style="background-color:#FFF; min-height:60px; margin-bottom:10px;">
            >><span style="font-weight:bold">您的当前位置</span><br/>
            &nbsp;&nbsp;&nbsp;&nbsp;<s:property value="#session.location.locationName"/>
            </div>
            <div id="local_resident" style="min-height:300px; background-color:#FFF; margin-bottom:10px;">本地居民</div>
            <div id="friends_list" style="min-height:300px; background-color:#FFF; margin-bottom:10px;">好友列表</div>
            <div id="named_location" style="min-height:200px; background-color:#FFF; padding-bottom:10000px; margin-bottom:-10000px">周边命名位置列表</div>
        </div>
    </div>
    <div class="clear"></div>
</body>
</html>