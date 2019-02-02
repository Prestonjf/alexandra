$(document).ready(function() {

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		if (token && header)
			xhr.setRequestHeader(header, token);
	});
	$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
	
	$('form').submit(function(event) {
		$('form').append('<input type="hidden"  name="_csrf"   value="'+$("meta[name='_csrf']").attr("content")+'"/>');
	});
	

});