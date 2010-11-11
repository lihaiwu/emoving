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
	function getCategory(){
		var clazzId = document.getElementById('subclass').value;
		if(clazzId == undefined)
			clearSelect('category');
		else
			category.getCategoryByParent(clazzId, function(data){fillSelect('category',data);});
	}
	function fillSelect(selectId, data){
	    clearSelect(selectId);
	    DWRUtil.addOptions(selectId, data);
	}
	function clearSelect(selectId){
	    DWRUtil.removeAllOptions(selectId);
	    DWRUtil.addOptions(selectId, {'':'--请选择--'});
	}
</script>
</head>
<body>
<form method="post" action="<%=request.getContextPath()%>/info_save.do">
信息类别
<select id="clazz" onchange="getSubclass()">
	<option value="">--请选择--</option>
</select>
信息子类
<select id="subclass" onchange="getCategory()">
	<option value="">--请选择--</option>
</select>
信息
<select id="category">
	<option value="">--请选择--</option>
</select>
</form>
</body>
</html>