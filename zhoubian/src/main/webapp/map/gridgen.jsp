<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="net.zhoubian.app.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>中国地图网格生成</title>
<style type="text/css">
body{font-size:14px; margin:0;}
#btn{width:100%; background-color:#FFFFCC;}
#result{width:100%; height:100px; background-color:#CCFFCC;}
#gridshow{width:100%; height:800px; background-color:#99CCCC;}
.grid{margin:0; height:30px; float:left; border:1px solid #0000CC;}
#gridshow br{clear:left;}
</style>
</head>
<body>
<div id="btn">
<form action="gridgen.jsp" method="get" name="form1">
经度：<input type="text" name="longitude" value="<%=request.getParameter("longitude")%>"/>
纬度：<input type="text" name="latitude" value="<%=request.getParameter("latitude")%>"/>
<input type="submit" value="查找编码"/>
</form>
</div>
<div id="result">
<%
	String longitude = request.getParameter("longitude");
	String latitude = request.getParameter("latitude");
	double lngX;
	double latY;
	if(longitude!=null && !longitude.equals("")){
		lngX = Double.parseDouble(longitude);
		latY = Double.parseDouble(latitude);
		out.print(GridUtil.getOwnGridCode(latY,lngX));
	}
	
%>
</div>
<div id="gridshow">
<% 
for(int i=GridUtil.latStrips.size()-1;i>=0;i--){
	GridUtil.LatStrip latStrip = GridUtil.latStrips.get(i);
	int width = (int)Math.round(latStrip.getLng256()*15);
	for(int j=0;j<latStrip.getGrids().size();j++){
		GridUtil.Grid256 grid256 = latStrip.getGrids().get(j);
		out.print("<div class=\"grid\" style=\"width:"+width+"px;\">"+grid256.getCode()+"</div>");
	}
	out.print("<br/>");
}
%>

</div>
</body>
</html>