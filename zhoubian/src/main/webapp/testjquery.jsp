<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>testjquery</title>
<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
<style type="text/css">
body{
margin:0;
}
</style>
</head>
<body>
<script type="text/javascript">
var scrollbarWidth = 0;
var scrollbarHeight = 0;
function addStr(){
	document.body.appendChild(document.createTextNode("hello1hello1hello1hello1hello1hello1hello1hello1hello1hello1hello1hello1hello1hello1hello1"));
	showSize();
}
function addLine(){
	document.body.appendChild(document.createTextNode("hello3"));
	document.body.appendChild(document.createElement("br"));
	showSize();
}
function showSize(){
	document.getElementById("message").innerHTML = 
		"scrollbarWidth = "+scrollbarWidth
		+"<br/>"
		+"scrollbarHeight = "+scrollbarHeight
		+"<br/>" 
		+"$(window).height() = "+$(window).height() /*浏览器时下窗口可视区域高度*/
		+"<br/>"
		+"$(document).height() = "+$(document).height() /*浏览器时下窗口文档的高度*/
		+"<br/>"
		+"$(document.body).height() = "+$(document.body).height()/*浏览器时下窗口文档body的高度*/
		+"<br/>"
		+"$(document.body).outerHeight(true) = "+$(document.body).outerHeight(true)/*浏览器时下窗口文档body的总高度 包括border padding margin*/
		+"<br/>"
		+"$(window).width() = "+$(window).width() /*浏览器时下窗口可视区域宽度*/
		+"<br/>"
		+"$(document).width() = "+$(document).width()/*浏览器时下窗口文档对于象宽度*/
		+"<br/>"
		+"$(document.body).width() = "+$(document.body).width()/*浏览器时下窗口文档body的高度*/
		+"<br/>"
		+"$(document.body).outerWidth(true) = "+$(document.body).outerWidth(true)/*浏览器时下窗口文档body的总宽度 包括border padding margin*/
		+"<br/>"
		+"$(document).scrollTop() = "+$(document).scrollTop() /*获取滚动条到顶部的垂直高度*/
		+"<br/>"
		+"$(document).scrollLeft() = "+$(document).scrollLeft() /*获取滚动条到左边的垂直宽度*/
		+"<br/><br/><br/><br/>";
}
$(document).ready(function(){
	(function(){ 
		 
		/* 
		This function calculates window.scrollbarWidth and window.scrollbarHeight 
		 
		This must be called "onload" to work correctly (or on "DOM ready", if you're using 
		a framework that provides such an event) 
		*/ 
		$("body").height(400); 
		var i = document.createElement('p'); 
		i.style.width = '100%'; 
		i.style.height = '200px'; 
		 
		var o = document.createElement('div'); 
		o.style.position = 'absolute'; 
		o.style.top = '0px'; 
		o.style.left = '0px'; 
		o.style.visibility = 'hidden'; 
		o.style.width = '200px'; 
		o.style.height = '150px'; 
		o.style.overflow = 'hidden'; 
		o.appendChild(i); 
		 
		document.body.appendChild(o); 
		var w1 = i.offsetWidth; 
		var h1 = i.offsetHeight; 
		o.style.overflow = 'scroll'; 
		var w2 = i.offsetWidth; 
		var h2 = i.offsetHeight; 
		if (w1 == w2) w2 = o.clientWidth; 
		if (h1 == h2) h2 = o.clientWidth; 
		 
		document.body.removeChild(o); 
		 
		scrollbarWidth = w1-w2; 
		scrollbarHeight = h1-h2; 
		document.getElementById("message").innerHTML = 
			"scrollbarWidth = "+scrollbarWidth
			+"<br/>"
			+"scrollbarHeight = "+scrollbarHeight
			+"<br/>" 
			+"$(window).height() = "+$(window).height() /*浏览器时下窗口可视区域高度*/
			+"<br/>"
			+"$(document).height() = "+$(document).height() /*浏览器时下窗口文档的高度*/
			+"<br/>"
			+"$(document.body).height() = "+$(document.body).height()/*浏览器时下窗口文档body的高度*/
			+"<br/>"
			+"$(document.body).outerHeight(true) = "+$(document.body).outerHeight(true)/*浏览器时下窗口文档body的总高度 包括border padding margin*/
			+"<br/>"
			+"$(window).width() = "+$(window).width() /*浏览器时下窗口可视区域宽度*/
			+"<br/>"
			+"$(document).width() = "+$(document).width()/*浏览器时下窗口文档对于象宽度*/
			+"<br/>"
			+"$(document.body).width() = "+$(document.body).width()/*浏览器时下窗口文档body的高度*/
			+"<br/>"
			+"$(document.body).outerWidth(true) = "+$(document.body).outerWidth(true)/*浏览器时下窗口文档body的总宽度 包括border padding margin*/
			+"<br/>"
			+"$(document).scrollTop() = "+$(document).scrollTop() /*获取滚动条到顶部的垂直高度*/
			+"<br/>"
			+"$(document).scrollLeft() = "+$(document).scrollLeft() /*获取滚动条到左边的垂直宽度*/
			+"<br/><br/><br/><br/>";
				
	})(); 
});
</script>
<a href="javascript:addStr()">hello</a><br/>
<a href="javascript:showSize()">hello2</a><br/>
<a href="javascript:addLine()">hello3</a><br/>
<div id="message">
</div>
</body>
</html>