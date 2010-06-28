<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type='text/javascript'
	src='<%=request.getContextPath()%>/dwr/interface/category.js'></script>
<script type='text/javascript'
	src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript'
	src='<%=request.getContextPath()%>/dwr/util.js'></script>
<script type="text/javascript" src='../js/jquery-1.3.2.js'></script>
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
</body>
</html>