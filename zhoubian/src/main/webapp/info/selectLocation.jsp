<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>选择位置</title>
<link type="text/css" href="<%=request.getContextPath()%>/css/jquery-ui-1.8.4.custom.css" rel="stylesheet"/>
<script src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.bgiframe-2.1.1.js"></script>
<script type="text/javascript" src="http://app.mapabc.com/apis?&t=flashmap&v=2.3.4&key=<%=net.zhoubian.app.util.SystemProperties.getProperty("mapkey")%>"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.core.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.widget.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.mouse.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.draggable.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.position.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.resizable.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.tabs.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.dialog.min.js"></script>
<style type="text/css">
html,body {
	font: 12px/ 22px Arial, 宋体;
	color: #545454;
	height: 100%;
	overflow:hidden;
}

html,body,ul,li,div,p,h6,h5,form,a,iframe,form{
	margin: 0px;
	padding: 0px;
}
li {
	list-style: none;
}
a {
	color: #0176AC;
	text-decoration: underline;
}

a:visited {
	color: #0176AC;
	text-decoration: underline;
}

a:hover {
	color: #0176AC;
	text-decoration: underline;
}

a:active {
	color: #0176AC;
	text-decoration: underline;
}
#content{ width:100%; background:#ffa; position:relative; height:100%; margin:0px; padding:0px;}
#header{ width:100%; height:30px; position:absolute; top:0; left:0; z-index:100; padding:2px;}
#map_canvas{width:100%;height:100%; padding-top:30px;}
#poiTools{
	width:52px;
	position:absolute;
	right:10px;
	top:50px;
	z-index:200;
	margin:0px;
	padding:0px;
	border:0px;
}
.gjx_pl{
	width:52px;
	font-weight: bold;
	text-align: center;
	line-height: 20px;
	height: 20px;
	background: url(<%=request.getContextPath()%>/images/gj_bg.png) no-repeat bottom center;
}
.gjx_p_end{
	background: url(<%=request.getContextPath()%>/images/gj_bg_b.png) top center no-repeat;
	width:52px;
	height:5px;
}
.tool_div {
	width: 52px;
	text-align: center;
}

.tool_div p {
	text-align: center;
	height: 40px;
	cursor: hand;
	cursor: pointer;
	width: 52px;
}
.tool_div img{
	cursor:hand;
	cursor:pointer;
}
.li1 {
	background: url(<%=request.getContextPath()%>/images/mover.gif) no-repeat center center;
	height: 40px;
	width: 100%;
}

.li2 {
	background: url(<%=request.getContextPath()%>/images/put_dot.gif) no-repeat center center;
	height: 40px;
	width: 100%;
}
.li1_hover {
	background: url(<%=request.getContextPath()%>/images/mover_hover.gif) no-repeat center center;
	height: 40px;
	width: 80%;
}

.li2_hover {
	background: url(<%=request.getContextPath()%>/images/put_dot_hover.gif) no-repeat center center;
	height: 40px;
	width: 80%;
}
</style>
</head>
<body>
<script type="text/javascript">
var mapObj = null;
$(document).ready(function(){
	var mapoption = new MMapOptions();
	mapoption.zoom = 13;
	mapoption.returnCoordType=COORD_TYPE_OFFSET;
	mapoption.isCongruence = true;
	mapoption.center = new MLngLat(116.397428,39.90923);
	mapoption.toolbar = DEFAULT;
	mapoption.toolbarPos = new MPoint(0,0);
	mapoption.overviewMap = SHOW;
	mapObj = new MMap("map_canvas",mapoption);
	$(".MFMP_Business").css("display","none");
});
function pic_before(obj){
	obj.className = obj.className+"_hover";
}
function pic_yihou(obj){
	obj.className = obj.className.substring(0,3);
}
function beginDrawPointOnMap(ele){
	drawPoint();
}
function drawPoint(){
	mapObj.addEventListener(mapObj,MOUSE_CLICK,drawPoint_);
}
function drawPoint_(param){
	mapObj.removeEventListener(mapObj,MOUSE_CLICK,drawPoint_);
	var markerOption = new MMarkerOptions();
	markerOption.imageUrl = "<%=request.getContextPath()%>/images/list_03.png"
	markerOption.picAgent = false;
	markerOption.imageAlign = 5;
	markerOption.canShowTip = false;
	var ll = new MLngLat(param.eventX,param.eventY);
	var Mmarker = new MMarker(ll,markerOption);
	Mmarker.id = "point1";
	mapObj.addOverlay(Mmarker,false);
	mapObj.setOverlayEditableById('point1',true);
	
}
function searchPoiBase(){
	   var mls = new MLocalSearch();
	   var mlsp = new MLocalSearchOptions();
	   mlsp.recordsPerPage = 10;
	   mls.setCallbackFunction(searchPoiR);
	   mls.poiSearchByKeywords($("#searchValue").val(),$("#cityName").val(),mlsp);
   }
   function searchPoiR(data){
	   var ss = new Array();
	   var i = 0;
	   var Mmarker = new Array();
	   for(i=0;i<data.poilist.length;i++){
		   var markerOption = new MMarkerOptions();
		   markerOption.imageUrl = "<%=request.getContextPath()%>/images/lan_"+(i+1)+".png";
		   markerOption.picAgent = false;
		   markerOption.isDraggable = false;
		   var tipOption = new MTipOptions();
		   tipOption.title = (i+1) + "." + data.poilist[i].name;
		   var tipC = TipContents(data.poilist[i].type,data.poilist[i].address,data.poilist[i].tel);
		   tipOption.content = tipC;
		   tipOption.hasShadow = true;
		   tipOption.borderStyle.thickness = 2;
		   tipOption.borderStyle.color = 0x005cb5;
		   tipOption.borderStyle.alpha = 1;
		   tipOption.titleFontStyle.name = "Arial";
		   tipOption.titleFontStyle.size = 12;
		   tipOption.titleFontStyle.color = 0xffffff;
		   tipOption.titleFontStyle.bold = true;
		   tipOption.contentFontStyle.name = "Arial";
		   tipOption.contentFontStyle.size = 13;
		   tipOption.contentFontStyle.color = 0x000000;
		   tipOption.contentFontStyle.bold = false;
		   tipOption.contentFontStyle.color = 0xFFFFFF;//填充色
		   tipOption.fillStyle.alpha = 1;
		   
		   markerOption.tipOption = tipOption;
		   markerOption.canShowTip = true;
		   markerOption.hasShadow = true;
		   var ll = new MLngLat(data.poilist[i].x,data.poilist[i].y);
		   Mmarker[i] = new MMarker(ll,markerOption);
		   Mmarker[i].id = (i);
	   }
	   if(i>1){
		   mapObj.addOverlays(Mmarker,true);
	   }else{
		   if(i==1){
			   mapObj.setCenter(new MLngLat(data.poilist[0].x,data.poilist[0].y));
			   mapObj.setZoomLevel(14);
		   }else{
			   alert("没有找到相关结果。");
		   }
	   }
   }
   function TipContents(type,address,tel){
	   if(type=="" || type=="undefined" || type==null || type==undefined || typeof type=="undefined"){
		   type = "暂无";
	   }
	   if(address=="" || address=="undefined" || address==null || address==undefined || typeof address=="undefined"){
		   address = "暂无";
	   }
	   if(tel=="" || tel=="undefined" || tel==null || tel==undefined || typeof tel=="undefined"){
		   tel = "暂无";
	   }
	   var str = "<br/>地址："+address+"<br/>电话："+tel+"<br/>类型："+type;
	   return str;
   }
   function clearSearch(){
	   mapObj.removeAllOverlays();
   }
   function selectLocation(){
	   var overlay = mapObj.getOverlayById("point1");
	   if(overlay==null){
		   alert("请在地图上进行标注！");
	   }else{
		   /*var mls = new MReGeoCodeSearch();
		   var opt = new MReGeoCodeSearchOptions();
		   opt.poiNumber = 10;
		   opt.range = NaN;
		   opt.pattern = 0;
		   opt.exKey = "";
		   mls.setCallbackFunction(reGeoCodeSearch_CallBack);
		   mls.poiToAddress(overlay.lnglat,opt);*/
		   window.opener.document.getElementById("longitude").value = overlay.lnglat.lngX;
	   	   window.opener.document.getElementById("latitude").value = overlay.lnglat.latY;
		   window.close();
	   }
   }
   function reGeoCodeSearch_CallBack(data){
	   if(data.error_message!=null){
		   alert("查询异常！");
	   }else{
		   switch(data.message){
			   case 'ok':
			   window.opener.document.getElementById("provinceId").value = data.SpatialBean.Province.code;
			   window.opener.document.getElementById("cityId").value = data.SpatialBean.City.code;
			   window.opener.document.getElementById("countyId").value = data.SpatialBean.District.code;
			   window.close();
			   break;
			   case 'error':
			   alert("查询错误！");
			   break;
			   default:
			   alert("查询出错，请稍后再试！");
		   }
	   }
	   
   }
</script>
<%@ include file="../map/citylist.jsp"%>
<div id="content">
	<div id="header">
    	<div id="search" style="float:left">
    	 <form name="searchForm" method="post" action="#" onsubmit="searchPoiBase();return false;">
    	<label for="cityName">当前城市：</label><input type="text" name="cityName" id="cityName" value="<%=session.getAttribute("cityName")%>" size="8" />
        <a href="javascript:citylistBox()">切换城市</a>&nbsp;&nbsp;<input type="text" name="searchValue" id="searchValue" size="20"/><img style="vertical-align:bottom;" src="<%=request.getContextPath()%>/images/lb_ss.gif" alt="搜索定位" onclick="searchPoiBase();" />&nbsp;&nbsp;<a href="javascript:clearSearch();">清除搜索</a>
        </form>
        </div>
        <div id="select" style="float:right; padding-right:20px;">
        <input type="button" value="确定" onclick="selectLocation()"/>
        </div>
    </div>
	<div id="map_canvas"></div>
	<div id="poiTools">
		<p class="gjx_pl" style="font-weight:normal">
			工具箱
		</p>
		<div class="tool_div" style="background:url(<%=request.getContextPath()%>/images/gj_bg_m.png) repeat-y center center;">
			<p class="li1" id="t1" onmouseover="pic_before(this)" onmouseout="pic_yihou(this)" onclick="mapObj.setCurrentMouseTool(PAN_WHEELZOOM);" title="移动"></p>
			<p class="li2" id="t2" onmouseover="pic_before(this)" onmouseout="pic_yihou(this)" onclick="beginDrawPointOnMap()" title="标点"></p>
		</div>
		<p class="gjx_p_end"></p>
	</div>
</div>
</body>
</html>