<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>增加信息</title>
<script src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/dwr/interface/category.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.3.2.js"></script>
<script type="text/javascript" defer="defer">
	if (typeof window['DWRUtil'] == 'undefined') window.DWRUtil = dwr.util;

	function loadClasses(){
	   	category.getClasses(function(data){
			fillSelect('clazz', data);
			var classId = "<s:property value='info.categoryId'/>".substr(0,3);
			if(classId!=undefined && classId!=null && classId!=""){
				$("#clazz").val(classId);
			}
			getSubclass(classId);
		});
	}
	loadClasses();
	function getSubclass(classId){
		var clazzId = classId;
		if(classId == undefined){
			clazzId = document.getElementById('clazz').value;
		}
		if(clazzId == undefined)
			clearSelect('subclass');
		else{
			category.getSubclassByParent(clazzId, function(data){
				fillSelect('subclass',data);
				var subclassId = "<s:property value='info.categoryId'/>";
				if(subclassId!=undefined && subclassId!=null && subclassId!=""){
					$("#subclass").val(subclassId);
				}
				getTagDiv();
			});
			
		}
	}
	function getTagDiv(){
		var subClassId = document.getElementById("subclass").value;
		if("<s:property value='info.id'/>"!=""){
			$("#tagDiv").load("<%=request.getContextPath()%>/info_getTagDiv.do?infoId=<s:property value='info.id'/>",{categoryId:subClassId});
		}else{
			$("#tagDiv").load("<%=request.getContextPath()%>/info_getTagDiv.do",{categoryId:subClassId});
		}
	}
	function fillSelect(selectId, data){
	    clearSelect(selectId);
	    DWRUtil.addOptions(selectId, data);
	}
	function clearSelect(selectId){
	    DWRUtil.removeAllOptions(selectId);
	    DWRUtil.addOptions(selectId, {'':'--请选择--'});
	}
	function setLocation(){
		if("<s:property value='info.id'/>"!=""){
			window.open("<%=request.getContextPath()%>/info_selectLocation.do?lngX=<s:property value='info.longitude'/>&latY=<s:property value='info.latitude'/>","选择位置","menubar=no,toolbar=no,resizable=yes,location=no");
		}else{
			window.open("<%=request.getContextPath()%>/info_selectLocation.do","选择位置","menubar=no,toolbar=no,resizable=yes,location=no");
		}
	}
	$(function(){
		if("<s:property value='info.id'/>"!=""){
			$("#title").val("<s:property value='info.title'/>");
			$("#locationDesc").val("<s:property value='info.locationDesc'/>");
			$("#longitude").val("<s:property value='info.longitude'/>");
			$("#latitude").val("<s:property value='info.latitude'/>");
			$("#provinceId").val("<s:property value='info.provinceId'/>");
			$("#cityId").val("<s:property value='info.cityId'/>");
			$("#countyId").val("<s:property value='info.countyId'/>");
			$("#telephone").val("<s:property value='info.telephone'/>");
			$("#site").val("<s:property value='info.site'/>");
			$("#content").val("<s:property value='info.content'/>");
		}
	});
</script>
<style type="text/css">
form{margin:0 auto; width:70%; text-align:left}
label{float:left; width:80px}
br{clear:left}
#btn{margin-left:80px; margin-top:20px;}
</style>
</head>
<body>
<form method="post" action="<%=request.getContextPath()%>/info_save.do">
<label for="clazz">信息类别：</label>
<select id="clazz" name="clazz" onchange="getSubclass()">
	<option value="">--请选择--</option>
</select><br/>
<label for="subclass">信息子类：</label>
<select id="subclass" name="info.categoryId" onchange="getTagDiv();">
	<option value="">--请选择--</option>
</select><br/>
<div id="tagDiv">
<!--
<label>标签：</label><div style="margin-left:80px;">
<span style="color:blue;">菜系：</span>&nbsp;&nbsp;<input type="radio" name="tag1" value="1" />闽菜&nbsp;&nbsp;<input type="radio" name="tag1" value="2" />粤菜&nbsp;&nbsp;<input type="radio" name="tag1" value="2" />川菜&nbsp;&nbsp;<input type="radio" name="tag1" value="2" />徽菜&nbsp;&nbsp;<input type="radio" name="tag1" value="2" />湘菜&nbsp;&nbsp;<input type="radio" name="tag1" value="2" />鲁菜&nbsp;&nbsp;<input type="radio" name="tag1" value="2" />浙菜&nbsp;&nbsp;<input type="radio" name="tag1" value="2" />苏菜<br/>
<span style="color:blue;">口味：</span>&nbsp;&nbsp;<input type="radio" name="tag2" value="1"/>麻辣&nbsp;&nbsp;<input type="radio" name="tag2" value="2"/>酸辣<br/>
</div>--></div>
<label for="title">标题：</label><input id="title" name="info.title" type="text" maxlength="30" /><br/>
<label for="locationDesc">地址：</label><input id="locationDesc" name="info.locationDesc" type="text" maxlength="200"/>
<input type="button" value="标注" onclick="setLocation()"/><br/>
<input type="hidden" name="info.longitude" id="longitude"/>
<input type="hidden" name="info.latitude" id="latitude"/>
<input type="hidden" name="info.provinceId" id="provinceId"/>
<input type="hidden" name="info.cityId" id="cityId"/>
<input type="hidden" name="info.countyId" id="countyId"/>
<input type="hidden" name="info.streetId" id="streetId"/>
<label for="telephone">电话：</label><input id="telephone" name="info.telephone" type="text" maxlength="20"/><br/>
<label for="site">网址：</label><input id="site" name="info.site" type="text" maxlength="40"/>	<br/>
<label for="content">信息内容：</label><textarea name="info.content" id="content" cols="80" rows="10"></textarea><br/>
<div id="btn">
<input type="submit" value="提交"/>&nbsp;&nbsp;<input type="reset" value="重置"/></div>
</form>
</body>
</html>