<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<ul>
<s:iterator value="#request.locations">
<li>
<s:if test="#parameters['mainWindow'] != null">
<a href="<%=request.getContextPath()%>/user_mainWindow.do?target=user_goSpecificLocation.do%3FlocationId%3D<s:property value='id'/>">
</s:if>
<s:else>
<a href="<%=request.getContextPath()%>/user_goSpecificLocation.do?locationId=<s:property value='id'/>">
</s:else>
<s:property value="locationName"/>
</a>
</li>
</s:iterator>
</ul>