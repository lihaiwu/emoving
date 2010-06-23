<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery.autocomplete.js"></script>
<script type="text/javascript" src="../js/jquery-1.3.2.js"></script>
<script type="text/javascript" src="../js/thickbox-compressed.js"></script>
<script type="text/javascript" src="../js/localdata.js"></script>
<link rel="stylesheet" type="text/css" href="../css/main.css">
<link rel="stylesheet" type="text/css" href="../css/thickbox.css">

<script type="text/javascript">
$().ready(function() {
	$("#suggest1").focus().autocomplete(cities);

});
</script>

</head>
<body>
<h1 id="banner"><a
	href="http://bassistance.de/jquery-plugins/jquery-plugin-autocomplete/">jQuery
Autocomplete Plugin</a> Demo</h1>

<div id="content">

<form autocomplete="off">
<p><label>Keyword:</label> <input class="ac_input"
	autocomplete="off" id="suggest1" type="text"></p>
</form>
</div>

</body>
</html>