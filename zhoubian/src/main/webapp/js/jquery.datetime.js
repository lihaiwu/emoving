(function ($) {
	$.extend({
		formatDate: function (format, date, options) {
			
		},
		dateToTime: function(m_date){
			var h = m_date.getHours();
			var m = m_date.getMinutes();
			var s = m_date.getSeconds();
			var hh = h<9?'0'+h:''+h;
			var mm = m<9?'0'+m:''+m;
			var ss = s<9?'0'+s:''+s;
			return hh + ':' + mm + ':' + ss;
		}
	})
}) (jQuery);