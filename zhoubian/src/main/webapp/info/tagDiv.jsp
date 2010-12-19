<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test='#request.tagList!=null && #request.tagList.size()>0'>
<label>标签：</label>
<s:iterator value="#request.tagList" status="tagstatus" id="tag">
<div style="margin-left:80px;">
<span style="color:blue;"><s:property value="tag.tagName"/></span>&nbsp;&nbsp;
<s:iterator value="tag.items.keySet()" id="item" status="itemstatus">
<input type="radio" name="<s:property value="tag.fieldName"/>" value="<s:property value='item.value'/>"/><s:property value='item.name'/>&nbsp;&nbsp;
</s:iterator><br/>
</div>
</s:iterator>
</s:if>
<!--
<div style="margin-left:80px;">
<span style="color:blue;">菜系：</span>&nbsp;&nbsp;
<input type="radio" name="tag1" value="1" />闽菜&nbsp;&nbsp;
<input type="radio" name="tag1" value="2" />粤菜&nbsp;&nbsp;
<input type="radio" name="tag1" value="2" />川菜&nbsp;&nbsp;
<input type="radio" name="tag1" value="2" />徽菜&nbsp;&nbsp;
<input type="radio" name="tag1" value="2" />湘菜&nbsp;&nbsp;
<input type="radio" name="tag1" value="2" />鲁菜&nbsp;&nbsp;
<input type="radio" name="tag1" value="2" />浙菜&nbsp;&nbsp;
<input type="radio" name="tag1" value="2" />苏菜<br/>
<span style="color:blue;">口味：</span>&nbsp;&nbsp;
<input type="radio" name="tag2" value="1"/>麻辣&nbsp;&nbsp;
<input type="radio" name="tag2" value="2"/>酸辣<br/>
</div>
-->