$(document).ready(function(){
	$(".main").hover(function(){
		$(this).children("ul").slideDown();
	},function(){
		$(this).children("ul").slideUp();
	});
});