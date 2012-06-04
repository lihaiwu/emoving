<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<ul>
<s:iterator value="#request.onlineUsers">
<li>
<span><s:property value="loginName"/></span>
</li>
</s:iterator>
</ul>