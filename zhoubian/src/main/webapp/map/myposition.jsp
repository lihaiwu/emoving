<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
#container{margin:auto 0; padding:0px; border:0px; width:100%; height:100%;}
#menu {height:30px; background:#693; width:100%; }
#mainContent{margin:0px; width:100%;}
#sidebar{float:left; width:250px; background:#cf9; height:100%}
#mapcontent{margin-left:250px !important; background:#ffa; position:relative; height:100%}
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

.li3 {
	background: url(<%=request.getContextPath()%>/images/draw_line.gif) no-repeat center center;
	height: 40px;
	width: 80%;
}

.li4 {
	background: url(<%=request.getContextPath()%>/images/draw_screen.gif) no-repeat center center;
	height: 40px;
	width: 80%;
}

.li5 {
	background: url(<%=request.getContextPath()%>/images/draw_dot2.gif) no-repeat center center;
	height: 40px;
	width: 80%;
}

.li6 {
	background: url(<%=request.getContextPath()%>/images/much_out.gif) no-repeat center center;
	height: 40px;
	width: 80%;
}

.li7 {
	background: url(<%=request.getContextPath()%>/images/gps_bdd.gif) no-repeat center center;
	height: 40px;
	width: 80%;
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

.li3_hover {
	background: url(<%=request.getContextPath()%>/images/draw_line_hover.gif) no-repeat center center;
	height: 40px;
	width: 80%;
}

.li4_hover {
	background: url(<%=request.getContextPath()%>/images/draw_screen_hover.gif) no-repeat center center
		;
	height: 40px;
	width: 80%;
}

.li5_hover {
	background: url(<%=request.getContextPath()%>/images/draw_dot2_hover.gif) no-repeat center center;
	height: 40px;
	width: 80%;
}

.li6_hover {
	background: url(<%=request.getContextPath()%>/images/much_out_hover.gif) no-repeat center center;
	height: 40px;
	width: 80%;
}
.li7_hover {
	background: url(<%=request.getContextPath()%>/images/gps_bdd_hover.gif) no-repeat center center;
	height: 40px;
	border-bottom: 1px dotted #d3d3d3;
	width: 80%;
}

#tabs-1 form{margin:0px}
#tabs-1 label{float:left; width:80px;}
#tabs-1 input{width:100px; border:1px solid #808080}
#tabs-1 form br{clear:left}
#sbutton{margin-left:20px; margin-top:5px;}
#rbutton{margin-top:5px; }
.pagelink{
	text-decoration:underline;
	color:blue;
	cursor:pointer;
}
</style>
<link type="text/css" href="<%=request.getContextPath()%>/css/jquery-ui-1.8.18.custom.css" rel="stylesheet"/>
<!--<link type="text/css" href="<%=request.getContextPath()%>/css/ui.jqgrid.css" rel="stylesheet"/>-->
<script src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="http://app.mapabc.com/apis?&t=flashmap&v=2.3.4&key=<%=net.zhoubian.app.util.SystemProperties.getProperty("mapkey")%>"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.core.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.widget.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.mouse.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.draggable.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.position.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.resizable.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.tabs.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.dialog.min.js"></script>

<!--<script type="text/javascript" src="<%=request.getContextPath()%>/js/grid-locale-cn.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.jqGrid.min.js"></script>-->
<script type="text/javascript">
<!--
var mapObj = null;
$(document).ready(function(){
	$('#mainContent').height(getViewportHeight()-115);
	$('#sidebar').height(getViewportHeight()-115);
	$('#mapcontent').height(getViewportHeight()-115);
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
	$("#tabs").tabs({
		event: 'mouseover'
	});
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
function around_search(id,name,x,y){//显示周边搜索页面
}
function mapAddChk(id,name,desc,x,y,stf){
	if(stf){
		var markerOption = new MMarkerOptions();
		markerOption.imageUrl = "<%=request.getContextPath()%>/images/list_03.png"
		markerOption.picAgent = false;
		markerOption.imageAlign = 5;
		markerOption.canShowTip = false;
		var ll = new MLngLat(x,y);
		var Mmarker = new MMarker(ll,markerOption);
		Mmarker.id = id;
		var tipOption = new MTipOptions();
		tipOption.title="位置信息";
		tipOption.tipType=DIVTIP;
		var sContent = "<font color='#000000'>名称： "+name+"<br/>";
		sContent += "描述： "+desc+"<br/>";
		sContent += "<a href=\"javascript:around_search('"+id+"','"+name+"','"+x+"','"+y+"');\">周边搜索</a></font>";
		tipOption.content=sContent;
		markerOption.tipOption = tipOption;
		markerOption.canShowTip = true;
		mapObj.addOverlay(Mmarker,true);
	}else{
		mapObj.removeOverlayById(id);
	}
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
function getViewportHeight(){
	        return jQuery.browser.msie ? 
	        	   (jQuery.boxModel ? document.documentElement.clientHeight : document.body.clientHeight) :
	        	   self.innerHeight;
        }

       function getViewportWidth() {
	        return !jQuery.boxModel && !jQuery.browser.opera? document.body.clientWidth :
	        	   jQuery.browser.msie ? document.documentElement.clientWidth : self.innerWidth;
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
   function isnull(str){
   		var i;
		if(str==null || str==undefined)
			return true;
		if(str.length == 0)
			return true;
		for(i=0;i<str.length;i++){
			if(str.charAt(i)!=' ')
				return false;
		}
		return true;
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
   		if(isnull($('#locationName').val())){
			error=error + "请输入位置名称！\n";
		}
		if(error == ""){
			<s:if test="#request.locations!=null">
			return true;
			</s:if>
			<s:else>
			window.opener.document.getElementById("locationName").value = $('#locationName').val();
			window.opener.document.getElementById("subLocType").value = $('#subLocType').val();
			window.opener.document.getElementById("locationDesc").value = $('#locationDesc').val();
			window.opener.document.getElementById("lngX").value = $('#lngX').val();
			window.opener.document.getElementById("latY").value = $('#latY').val();
			window.close();
			return false;
			</s:else>
		}else{
			alert(error);
			return false;
		}
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
   function setCurrentCity(cityName){
	   $("#cityName").val(cityName);
   }
   var currentPage = 0;
   var maxPage = Math.floor(<s:property value="#request.locations.size()"/>/5);
   function nextPage(){
	   if(currentPage == maxPage)
	     return;
	   if(currentPage == 0){
		   $("#prePage").addClass("pagelink");
	   }
	   currentPage = currentPage + 1;
	   if(currentPage == maxPage){
		   $("#nextPage").removeClass("pagelink");
	   }
	   for(var i=0;i<maxPage;i++){
		   if(i != currentPage){
			   $("#locationPage"+i).hide();
		   }
	   }
	   $("#locationPage"+currentPage).show();
   }
   function prePage(){
	   if(currentPage == 0)
	     return;
	   if(currentPage == maxPage){
		   $("#nextPage").addClass("pagelink");
	   }
	   currentPage = currentPage - 1;
	   if(currentPage == 0){
		   $("#prePage").removeClass("pagelink");
	   }
	   for(var i=0;i<maxPage;i++){
		   if(i != currentPage){
			   $("#locationPage"+i).hide();
		   }
	   }
	   $("#locationPage"+currentPage).show();
	   
   }
// -->
</script>
</head>
<body>
<%@ include file="citylist.jsp"%>
<div id="container" class="grid_12">
	<div id="menu">
    	<div id="searchDiv" style="float:left">
        <form name="searchForm" method="post" action="#" onsubmit="searchPoiBase();return false;">
    	<label for="cityName">当前城市：</label><input type="text" name="cityName" id="cityName" value="<%=session.getAttribute("cityName")%>" size="8" style="vertical-align:bottom"/>
        <a href="javascript:citylistBox()">切换城市</a>&nbsp;&nbsp;<input type="text" name="searchValue" id="searchValue" style="vertical-align:bottom" size="20"/><img style="vertical-align:bottom;" src="<%=request.getContextPath()%>/images/lb_ss.gif" alt="搜索定位" onclick="searchPoiBase();" />&nbsp;&nbsp;<a href="javascript:clearSearch();">清除搜索</a>
        &nbsp;&nbsp;
        <s:if test="#request.locations!=null">
		<a href="<%=request.getContextPath()%>/user_logout.do" style="color:red;">退出登录</a>
        </s:if>
        </form>
        </div>
	</div>
	<div id="mainContent">
		<div id="sidebar">
		<div id="tabs" style="height:100%;">
		<ul>
			<li><a href="#tabs-1">添加位置</a></li>
			<s:if test="#request.locations!=null">
			<li><a href="#tabs-2">位置列表</a></li>
			</s:if>
		</ul>
		<div id="tabs-1">
		<form method="post" name="propertyForm" action="<%=request.getContextPath()%>/map_addUserLocation.do" onsubmit="return checkForm();">
	<input id="lngX" type="hidden" name="lngX"/>
	<input id="latY" type="hidden" name="latY"/>
	<input type="hidden" name="iconUrl" id="iconUrl" value="<%=request.getContextPath()%>/images/10.gif"/>
	<label for="locationName">位置名称：</label><input style="width:120px" id="locationName" type="text" name="locationName"/><br/>
	<label for="subLocType">位置类型：</label><s:if test="#request.locations!=null"><select id="subLocType" name="subLocType"><option value="1">居住地</option>
	<option value="2">办公地</option><option value="3">旅游地</option><option value="4">出差地</option><option value="5">其他</option>
	</select></s:if><s:else><select id="subLocType" name="subLocType"><option value="1" selected="selected">居住地</option></select></s:else><br/>
	<label for="locationDesc">位置说明：</label><textarea id="locationDesc" name="locationDesc" rows="10" cols="32"></textarea><br/>
	<input id="sbutton" type="submit" value="保存" style="width:40px"/>&nbsp;&nbsp;<input id="rbutton" type="button" value="重置" style="width:40px"/>
	</form>
	</div>
	<s:if test="#request.locations!=null">
	<div id="tabs-2" style="height:85%; overflow:auto;">
	<div>&nbsp;&nbsp;共<%=((java.util.List)request.getAttribute("locations")).size()%>条结果</div>
	<s:iterator value="#request.locations" status="rowstatus">
    <s:if test="#rowstatus.first">
    <div id="locationPage0">
    </s:if>
    <s:elseif test="#rowstatus.index % 5 == 0">
    </div>
    <div id="locationPage<s:property value='#rowstatus.count / 5'/>" style="display:none">
    </s:elseif>
	<s:if test="#rowstatus.odd==true">
		<div style="background-color:#FFFFE5;">
	</s:if>
	<s:else>
		<div style="background-color:#F2FFE5;">
	</s:else>
		<table width="90%" border="0" cellpadding="0" cellspacing="0">
		<tr><td width="32%"><input type="checkbox" value="<s:property value='id'/>" onclick="mapAddChk(this.value,'<s:property value="locationName"/>','<s:property value="locationDesc"/>','<s:property value="longitude"/>','<s:property value="latitude"/>',this.checked);"/></td><td><s:property value='locationName'/></td></tr>
		<tr><td>类型：</td><td>
		<s:if test="subLocType==1">居住地</s:if>
		<s:if test="subLocType==2">办公地</s:if>
		<s:if test="subLocType==3">旅游地</s:if>
		<s:if test="subLocType==4">出差地</s:if>
		<s:if test="subLocType==5">其他</s:if>
		<s:property value="subLocType"/></td></tr>
		<tr><td>经度：</td><td><s:property value="longitude"/></td></tr>
		<tr><td>纬度：</td><td><s:property value="latitude"/></td></tr>
		<tr><td>创建时间：</td><td><s:property value="createTime"/></td></tr>
		<tr><td>描述：</td><td><s:property value="locationDesc"/></td></tr>
		</table>
	</div>
    <s:if test="#rowstatus.last">
    </div>
    </s:if>
	</s:iterator>
	<div>&nbsp;&nbsp;<span id="prePage" onclick="prePage()">上一页</span>&nbsp;&nbsp;<span id="nextPage" onclick="nextPage()" <s:if test="#request.locations.size()>5">class="pagelink"</s:if>>下一页</span></div>
	</div>
	</s:if>
	</div>
		</div>
		<div id="mapcontent">
		<div id="map_canvas" style="width:100%;height:100%"></div>
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
	</div>
</div>
</body>
</html>