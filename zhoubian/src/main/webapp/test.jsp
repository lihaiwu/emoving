<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 
<!DOCTYPE html> 
 
<html> 
 
<!--
	This is a jQuery Tools standalone demo. Feel free to copy/paste.
	                                                         
	http://flowplayer.org/tools/demos/
	
	Do *not* reference CSS files and images from flowplayer.org when in production  
 
	Enjoy!
--> 
 
<head> 
	<title>jQuery Tools standalone demo</title> 
 
<script src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/jquery.tools.overlay.min.js" type="text/javascript"></script>
 
 
	<style> 
	
	
	.modal {
		background-color:#fff;
		display:none;
		width:350px;
		padding:15px;
		text-align:left;
		border:2px solid #333;
	
		opacity:0.8;
		-moz-border-radius:6px;
		-webkit-border-radius:6px;
		-moz-box-shadow: 0 0 50px #ccc;
		-webkit-box-shadow: 0 0 50px #ccc;
	}
	
	.modal h2 {
		background:url(/img/global/info.png) 0 50% no-repeat;
		margin:0px;
		padding:10px 0 10px 45px;
		border-bottom:1px solid #333;
		font-size:20px;
	}
	</style> 
</head> 
 
<body> 
 
 
 
 
<!-- the triggers --> 
<p> 
	<button class="modalInput" rel="#yesno">Yes or no?</button> 
	<button class="modalInput" rel="#prompt">User input</button> 
</p> 
 
<form id="form1" method="post" action="#">
<label title="姓名：" for="name"/>
<input type="text" name="name"/>
</form>
<!-- yes/no dialog --> 
<div class="modal" id="yesno"> 
	<h2>This is a modal dialog</h2> 
 
	<p> 
		You can only interact with elements that are inside this dialog.
		To close it click a button or use the ESC key.
	</p> 
 
	<!-- yes/no buttons --> 
	<p> 
		<button class="close"> Yes </button> 
		<button class="close"> No </button> 
	</p> 
</div> 
 
<!-- user input dialog --> 
<div class="modal" id="prompt"> 
	<h2>This is a modal dialog</h2> 
 
	<p> 
		You can only interact with elements that are inside this dialog.
		To close it click a button or use the ESC key.
	</p> 
 
	<!-- input form. you can press enter too --> 
	<form> 
		<input /> 
		<button type="submit"> OK </button> 
		<button type="button" class="close"> Cancel </button> 
	</form> 
	<br /> 
 
</div> 
 
 
<script> 
// What is $(document).ready ? See: http://flowplayer.org/tools/documentation/basics.html#document_ready
$(document).ready(function() {
 
var triggers = $(".modalInput").overlay({
 
	// some mask tweaks suitable for modal dialogs
	mask: {
		color: '#ebecff',
		loadSpeed: 200,
		opacity: 0.9
	},
 
	closeOnClick: false
});
 
 
var buttons = $("#yesno button").click(function(e) {
	
	// get user input
	var yes = buttons.index(this) === 0;
 
	// do something with the answer
	triggers.eq(0).html("You clicked " + (yes ? "yes" : "no"));
});
 
 
$("#prompt form").submit(function(e) {
 
	// close the overlay
	triggers.eq(1).overlay().close();
 
	// get user input
	var input = $("input", this).val();
 
	// do something with the answer
	triggers.eq(1).html(input);
 
	// do not submit the form
	return e.preventDefault();
});
 
});
</script> 
 
</body> 
 
</html> 