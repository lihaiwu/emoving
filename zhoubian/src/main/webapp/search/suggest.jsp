<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery-1.3.2.js"></script>
<script type='text/javascript' src='../js/jquery.bgiframe.min.js'></script>
<script type='text/javascript' src='../js/jquery.ajaxQueue.js'></script>
<script type='text/javascript' src='../js/thickbox-compressed.js'></script>
<script type='text/javascript' src='../js/jquery.autocomplete.js'></script>
<link rel="stylesheet" type="text/css"
	href="../css/jquery.autocomplete.css" />
<link rel="stylesheet" type="text/css" href="../css/thickbox.css" />

<script type="text/javascript">
$().ready(function() {

	$("#suggest1").autocomplete("<%=request.getContextPath()%>/query_getSuggestKeyword.do",
	{
		delay : 500,	
		dataType : "json",
		parse : function(res){
			var result = [];
			for(var i = 0; i < res.length; i++){
				result[i] = {data:res[i],value:res[i],result:res[i]};
			}
			return result;
		},
	    formatItem:function(row,i,max){
		    var item="<table id='auto"+i+"' style='width:100%;'>" 
		    + "          <tr>"
		    + "                <td align='left'>"+row+"</td>"
		    + "                <td align='right' style='color:green;'>"+i+"</td>"
		    + "          </tr>"
		    + "       </table>";
		        return item;
	     },
	     //格式化结果,当选中时返回具体的值
	     formatResult:function(row,i,max){                    
	        return row;
	     }
				   
	});
});
</script>

</head>
<body>

<div id="content">
<form autocomplete="off">
<p><label>Keyword:</label> <input type="text" id="suggest1" /></p>
</form>
</div>

</body>
</html>