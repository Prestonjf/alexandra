<%@ include file="/WEB-INF/jsp/common/include.jsp"%>
<div id="body">
	<div class="container-fluid">
		<div class="loginForm">
			<form action="login" method="POST" class="form-signin">			
			<h4>Alexandra Login</h4>
			<c:if test="${not empty param.failure and param.failure eq '1'}"><div class="alert alert-warning">Invalid Username or Password!</div></c:if>
			<c:if test="${not empty param.logout and param.logout eq 'true'}"><div class="alert alert-success">Logout Successful!</div></c:if>
			<c:if test="${not empty warningmsg}"><div class="alert alert-warning">${warningmsg}</div></c:if>
			<c:if test="${not empty msg}"><div class="alert alert-success">${msg}</div></c:if>
			<div class="input-group mb-2">
			  <div class="input-group-prepend">
			    <span class="input-group-text"><i class="material-icons">account_circle</i></span>
			  </div>
				<label class="input-group-addon" for="username"></label> <input type="text" class="form-control"
					id="username" name="username" placeholder="Enter e-mail address" autocomplete="off" required>
			</div>
			<div class="input-group mb-2">
			  <div class="input-group-prepend">
			    <span class="input-group-text"><i class="material-icons">lock</i></span>
			  </div>
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

</script>