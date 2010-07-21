<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="Keywords" content="地图 搜索，位置管理，周边旅游，周边购物，周边交友"/>
<meta name="description" content="我的位置管理"/>
<meta name="viewport" content="initial-scale=1.0,user-scalable=no"/>
<title>我的位置管理</title>
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
#container{margin:auto; width:100%;}
#header{height:100px; background:#9c6; margin-bottom:5px;}
#menu {height:30px; background:#693; margin-bottom:5px;}
#mainContent{height:500px; margin-bottom:5px;}
#sidebar{float:left; width:300px; height:500px; background:#cf9}
#content{margin-left:205px !important; margin-left:202px; height:500px; background:#ffa; position:relative;}
#poiTools{
	width:52px;
	position:absolute;
	right:10px;
	top:20px;
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
	background: url(../images/gj_bg.png) no-repeat bottom center;
}
.gjx_p_end{
	background: url(../images/gj_bg_b.png) top center no-repeat;
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
	background: url(../images/mover.gif) no-repeat center center;
	height: 40px;
	width: 100%;
}

.li2 {
	background: url(../images/put_dot.gif) no-repeat center center;
	height: 40px;
	width: 100%;
}

.li3 {
	background: url(../images/draw_line.gif) no-repeat center center;
	height: 40px;
	width: 80%;
}

.li4 {
	background: url(../images/draw_screen.gif) no-repeat center center;
	height: 40px;
	width: 80%;
}

.li5 {
	background: url(../images/draw_dot2.gif) no-repeat center center;
	height: 40px;
	width: 80%;
}

.li6 {
	background: url(../images/much_out.gif) no-repeat center center;
	height: 40px;
	width: 80%;
}

.li7 {
	background: url(../images/gps_bdd.gif) no-repeat center center;
	height: 40px;
	width: 80%;
}


.li1_hover {
	background: url(../images/mover_hover.gif) no-repeat center center;
	height: 40px;
	width: 80%;
}

.li2_hover {
	background: url(../images/put_dot_hover.gif) no-repeat center center;
	height: 40px;
	width: 80%;
}

.li3_hover {
	background: url(../images/draw_line_hover.gif) no-repeat center center;
	height: 40px;
	width: 80%;
}

.li4_hover {
	background: url(../images/draw_screen_hover.gif) no-repeat center center
		;
	height: 40px;
	width: 80%;
}

.li5_hover {
	background: url(../images/draw_dot2_hover.gif) no-repeat center center;
	height: 40px;
	width: 80%;
}

.li6_hover {
	background: url(../images/much_out_hover.gif) no-repeat center center;
	height: 40px;
	width: 80%;
}
.li7_hover {
	background: url(../images/gps_bdd_hover.gif) no-repeat center center;
	height: 40px;
	border-bottom: 1px dotted #d3d3d3;
	width: 80%;
}

#sidebar form{margin:0px}
#sidebar label{float:left; width:60px;}
#sidebar input{width:100px; border:1px solid #808080}
#sidebar form br{clear:left}
#sbutton{margin-left:20px; margin-top:5px;}
#rbutton{margin-top:5px; }
</style>
<script src="../js/jquery-1.3.2.js" type="text/javascript"></script>
<script type="text/javascript" src="http://app.mapabc.com/apis?&t=flashmap&v=2.3.3&key=<%=net.zhoubian.app.util.SystemProperties.getProperty("mapkey")%>"></script>
<script type="text/javascript">
<!--
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

function addCookie(objName,objValue)//增加cookie值
{
  var str = objName + "=" + escape(objValue);
  str += ";";
   document.cookie = str;
  

}
 function getCookie(objName){//获取cookie

    var arrStr = document.cookie.split(";");
    var as='';
  
    for(var i = 0;i < arrStr.length;i ++){
	
     var temp = arrStr[i].split("=");
	
	  
     if(temp[0].indexOf(objName)!=-1) 
		{
     as=unescape(temp[1]);
     return as;
		}
    } 
	return  as
   }
   function checkForm(){
   		var error = "";
   		var overlay = mapObj.getOverlayById("point1");
		if(overlay==null){
			error = error + "请在地图上进行标注！\n";
		}else{
			$('#lngX').val(overlay.lnglat.lngX);
			$('#latY').val(overlay.lnglat.latY);
		}
   		
   		return false;
   }
// -->
</script>
</head>
<body>
<div id="container">
	<div id="header">This is the Header</div>
	<div id="menu">
	
	</div>
	<div id="mainContent">
		<div id="sidebar">
		<form method="post" name="propertyForm" action="<%=request.getContextPath()%>/mapAction_addUserLocation.do" onclick="return checkForm();">
	<input id="lngX" type="hidden" name="lngX"/>
	<input id="latY" type="hidden" name="latY"/>
	<input type="hidden" name="iconUrl" id="iconUrl" value="../images/10.gif"/>
	<label for="locationName">位置名称：</label><input type="text" name="locationName"/><br/>
	<label for="subLocType">位置类型：</label><select name="subLocType"><option value="1">居住地</option>
	<option value="2">办公地</option><option value="3">旅游地</option><option value="4">出差地</option><option value="5">其他</option>
	</select><br/>
	<label for="locationDesc">位置说明：</label><textarea name="locationDesc" rows="10" cols="20"></textarea><br/>
	<input id="sbutton" type="submit" value="保存" style="width:40px"/>&nbsp;&nbsp;<input id="rbutton" type="button" value="重置" style="width:40px"/>
	</form>
		</div>
		<div id="content">
		<div id="map_canvas" style="width:100%;height:100%"></div>
		<div id="poiTools">
			<p class="gjx_pl" style="font-weight:normal">
				工具箱
			</p>
			<div class="tool_div" style="background:url(../images/gj_bg_m.png) repeat-y center center;">
				<p class="li1" id="t1" onmouseover="pic_before(this)" onmouseout="pic_yihou(this)" onclick="mapObj.setCurrentMouseTool(PAN_WHEELZOOM);" title="移动"></p>
				<p class="li2" id="t2" onmouseover="pic_before(this)" onmouseout="pic_yihou(this)" onclick="beginDrawPointOnMap()" title="标点"></p>
			</div>
			<p class="gjx_p_end"></p>
		</div>
		</div>
	</div>
</div>
</body>
</html>