<%@ include file="/WEB-INF/jsp/common/include.jsp"%>
 <div class="header">
<nav class="navbar navbar-expand-lg navbar-light">
<a class="navbar-brand" href="home">Alexandra</a>

  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
     <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Use Cases
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#">SO Registration Parser</a>
          <!--<div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#"></a>  -->
        </div>
      </li>
    </ul>
   	<c:url var="logout" value="/logout" />
	<form class="form-inline my-2 my-lg-0" action="${logout}" method="post">
		<input type="submit" class="btn btn-outline-danger my-2 my-sm-0"" value="Log out" />
	</form>
	</sec:authorize>
  </div>
</nav>
</div>