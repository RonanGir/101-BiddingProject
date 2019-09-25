<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% Boolean mustLogIn = (Boolean) request.getAttribute("MustBeConnected"); %>

				
<% if (mustLogIn != null && mustLogIn) { %>
<div class="flash flash-danger alert alert-dismissible fade show" role="alert">
  <span><strong>Oups !</strong> Vous devez être connecté pour accéder à cette page !</span>
  <a data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </a>
</div>
<% } %>