<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test='#request.tagList!=null && #request.tagList.size()>0'>
<label>标签：</label>
<s:iterator value="#request.tagList" status="tagstatus" id="tag">
<div style="margin-left:80px;">
<span style="color:blue;"><s:property value="#tag.tagName"/>：</span>&nbsp;&nbsp;
<s:iterator value="#tag.items.entrySet()" id="tagItem" status="itemstatus">
<input type="radio" name="info.<s:property value='#tag.fieldName'/>" value="<s:property value='#tagItem.key'/>"/><s:property value='#tagItem.value'/>&nbsp;&nbsp;
</s:iterator><br/>
</div>
<script type="text/javascript">
$("input[name='info.<s:property value="#tag.fieldName"/>'][value='<s:property value="#request.tagValues.get(#tagstatus.index)"/>']").attr('checked','true');
</script>
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