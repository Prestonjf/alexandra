var alexandraSessionTimer;
var blockUI = true;

$(document).ready(function() {

	// Submit CSRF Token on AJAX Calls
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		if (token && header)
			xhr.setRequestHeader(header, token);
	});
	
	// Block UI on AJAX
	
		$(document).ajaxStart(function() {
			if (blockUI) {
				$.blockUI();
			}		
		}).ajaxStop($.unblockUI);
	
	
	$('form').submit(function(event) {
		$('form').append('<input type="hidden"  name="_csrf"   value="'+$("meta[name='_csrf']").attr("content")+'"/>');
	});
	
});

function checkSessionTimeOut() {
	   var sessionExpiry = Math.abs(getCookie('sessionExpiry'));
	   var timeOffset = Math.abs(getCookie('clientTimeOffset'));
	   var localTime = (new Date()).getTime();
	   if ((sessionExpiry >= 0) && ((localTime + timeOffset) > sessionExpiry)) {
	          $('#invalidSessionForm').submit();
	   } else if ((sessionExpiry >= 0)
	                 && ((localTime + timeOffset + (1 * 60 * 1000)) > sessionExpiry)
	                 && ((localTime + timeOffset + (1 * 60 * 1000) - 5001) < sessionExpiry)) {
	          // console.log("Session expires in 1 Minute!");
	      showAlertDialog("Your session will expire in 1 minute. Click OK to extend your session.",extendSession, this);
	      alexandraSessionTimer = setTimeout('checkSessionTimeOut()', 5000);
	   } else {
		   alexandraSessionTimer = setTimeout('checkSessionTimeOut()', 5000);
	   }
	   //console.log("to timeout: " + (sessionExpiry - (localTime + timeOffset)))
	}
	
function calcClientTimeOffset() {
   var serverTime = getCookie('serverTime');
   serverTime = serverTime == null ? 0 : Math.abs(serverTime);
   var clientTimeOffset = (new Date()).getTime() - serverTime;
   setCookie('clientTimeOffset', clientTimeOffset);
}

function getCookie(cname) {
   var name = cname + "=";
   var decodedCookie = decodeURIComponent(document.cookie);
   var ca = decodedCookie.split(';');
   for (var i = 0; i < ca.length; i++) {
          var c = ca[i];
          while (c.charAt(0) == ' ') {
                 c = c.substring(1);
          }
          if (c.indexOf(name) == 0) {
                 return c.substring(name.length, c.length);
          }
   }
   return "";
}

function setCookie(cname, cvalue) {
	document.cookie = cname + "=" + cvalue + ";" + 1 + ";path="+ $('#base_url').val();
}

function extendSession(status, caller) {
   if (status) {
          $.post("refreshSession", null, function(response) {
                 clearTimeout(alexandraSessionTimer);
                 alexandraSessionTimer = setTimeout('checkSessionTimeOut()', 10000);
          });
   }
}

function showAlertDialog(msg, callback, caller) {
   $("#alexandraAlertDialog").dialog({
      buttons : {
         "OK" : function() {
               $(".ui-dialog-titlebar-close").show();
               $(this).dialog("close");
                   callback(true, caller);
             }
      }
   });
   $("#alexandraAlertDialog").dialog('option', 'title','Session Expiring Soon');
   $("#alexandraAlertDialog").dialog("option", "position", {
      my : "center",
      at : "center",
          of : window
   });
   $("#alexandraAlertDialog").dialog("open");
   $("#alexandraAlertDialog").html("<p class=''>" + msg + "</p>");
}

$(function() {
   $("#alexandraAlertDialog").dialog({
          title : "",
          autoOpen : false,
          modal : true,
          resizable : true,
          open : function(event, ui) {
                 $(".ui-dialog-titlebar-close").hide();
          },
          width : "auto",
          height : "auto",
   });
});