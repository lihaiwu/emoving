<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>增加信息</title>

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
	   	category.getClasses(function(data){fillSelect('clazz', data);});
	}
	loadClasses();
	function getSubclass(){
		var clazzId = document.getElementById('clazz').value;
		if(clazzId == undefined)
			clearSelect('subclass');
		else
			category.getSubclassByParent(clazzId, function(data){fillSelect('subclass',data);});
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
		window.open("<%=request.getContextPath()%>/info_selectLocation.do","选择位置","menubar=no,toolbar=no,resizable=yes,location=no");
	}
</script>
<style type="text/css">
form{margin:0 auto; width:70%; text-align:left}
label{float:left; width:80px}
br{clear:left}
</style>
</head>
<body>
<form method="post" action="<%=request.getContextPath()%>/info_save.do">
<label for="clazz">信息类别：</label>
<select id="clazz" name="clazz" onchange="getSubclass()">
	<option value="">--请选择--</option>
</select><br/>
<label for="subclass">信息子类：</label>
<select id="subclass" name="subclass">
	<option value="">--请选择--</option>
</select><br/>
<div id="tagDiv">
<label>标签：</label><div style="margin-left:80px;">
<span style="color:blue;">菜系：</span>&nbsp;&nbsp;<input type="radio" name="tag1" value="1" />闽菜&nbsp;&nbsp;<input type="radio" name="tag1" value="2" />粤菜&nbsp;&nbsp;<input type="radio" name="tag1" value="2" />川菜&nbsp;&nbsp;<input type="radio" name="tag1" value="2" />徽菜&nbsp;&nbsp;<input type="radio" name="tag1" value="2" />湘菜&nbsp;&nbsp;<input type="radio" name="tag1" value="2" />鲁菜&nbsp;&nbsp;<input type="radio" name="tag1" value="2" />浙菜&nbsp;&nbsp;<input type="radio" name="tag1" value="2" />苏菜<br/>
<span style="color:blue;">口味：</span>&nbsp;&nbsp;<input type="radio" name="tag2" value="1"/>麻辣&nbsp;&nbsp;<input type="radio" name="tag2" value="2"/>酸辣<br/>
</div></div>
<label for="title">标题：</label><input id="title" name="title" type="text" maxlength="30" /><br/>
<label for="locationDesc">地址：</label><input id="locationDesc" name="locationDesc" type="text" maxlength="200"/>
<input type="button" value="标注" onclick="setLocation()"/><br/>
<input type="hidden" name="longitude" id="longitude"/>
<input type="hidden" name="latitude" id="latitude"/>
<input type="hidden" name="provinceId" id="provinceId"/>
<input type="hidden" name="cityId" id="cityId"/>
<input type="hidden" name="countyId" id="countyId"/>
<input type="hidden" name="streetId" id="streetId"/>
<label for="telephone">电话：</label><input id="telephone" name="telephone" type="text" maxlength="20"/><br/>
<label for="site">网址：</label><input id="site" name="site" type="text" maxlength="40"/>	<br/>
<label for="content">信息内容：</label><textarea name="content" id="content" cols="80" rows="10"></textarea><br/>
</form>
</body>
</html>