<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Alexandra">
<%@ include file="/WEB-INF/jsp/common/include.jsp"%>
<%@ include file="/WEB-INF/jsp/common/includeScripts.jsp"%>

<script type="text/javascript">


</script>

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->
  
<sec:csrfMetaTags />
<title><tiles:insertAttribute name="title" flush="true" /></title>
</head>
<body>
	<div id="wrap" class="">
		<tiles:insertAttribute name="navbar" flush="true" />
		<div id="content">
			<tiles:insertAttribute name="body" flush="true" />
		</div>
	</div>
	<tiles:insertAttribute name="footer" flush="true" />
	
	<form id="invalidSessionForm" action="invalid-session" method="post"></form>
	<div id="alexandraConfirmDialog" style="display: none;"><p id="alexandraConfirmDialogMsg"></p></div>
	<div id="alexandraAlertDialog" style="display: none;"><p id="alexandraAlertDialogMsg"></p></div>
	<input type="hidden" id="base_url" value="${properties.getString('com.prestonsproductions.alexandra.BASE_URL')}" />
</body>

<sec:authorize access="hasAnyRole('ROLE_USER,'ROLE_ADMIN')">
	<script type="text/javascript">
		calcClientTimeOffset();
		alexandraSessionTimer = setTimeout('checkSessionTimeOut()', 5000);
	</script>
</sec:authorize>
</html>