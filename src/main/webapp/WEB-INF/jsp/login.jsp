<%@ include file="/WEB-INF/jsp/common/include.jsp"%>
<div id="body">
	<div class="container-fluid">
		<div class="loginForm">
			<c:url var="loginUrl" value="/auth-login" />
			<form action="login" method="post" class="form-signin">			
			<h4>Alexandra Login</h4>
			<c:if test="${not empty msg}"><div class="alert alert-warning">${msg}</div></c:if>
			
			<div class="input-group mb-2">
				<label class="input-group-addon" for="username"></label> <input type="text" class="form-control"
					id="username" name="username" placeholder="Enter e-mail address" autocomplete="off" required>
			</div>
			<div class="input-group mb-2">
				<label class="input-group-addon" for="password"></label> <input type="password" class="form-control"
					id="password" name="password" placeholder="Enter Password" required autocomplete="off">
			</div>
				<div class="input-group">
					<input type="submit" id="loginbtn" class="btn btn-primary btn-sm" value="Log in">
				</div>
			</form>
		</div>
	</div>
</div>

<script type="text/javascript">

	$(document).keyup(function(event) {
		if (event.keyCode === 13) {
			$('#loginbtn').click();
		}
	});

</script>