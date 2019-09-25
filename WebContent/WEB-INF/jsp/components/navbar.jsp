<%@page import="fr.training.BiddingProject.bo.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-expand-sm bg-orange navbar-light navbar-lewagon">
<%--    <a class="navbar-brand" href="<%= request.getContextPath() %>/index"><img src="<%=request.getContextPath()%>/assets/img/auction.png" alt="logo-encheres-org"/></a> --%>
   <a class="navbar-brand" href="<%= request.getContextPath() %>/index"><jsp:include page="./logo.jsp" /></a>
   <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    	<span class="navbar-toggler-icon"></span>
   </button>
   
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
   <ul class="navbar-nav mr-auto">
			<%
				User currentUser = (User) session.getAttribute("currentUser");
			%>
			<c:choose>
				<c:when test="${currentUser.loggedIn}">
					<li class="nav-item">
						<a href="<%= request.getContextPath() %>/vendre-un-article" class="nav-link">Vendre un article</a>
					</li>
				      <li class="nav-item dropdown">
				      	
				      		<img class="avatar dropdown-toggle " id="navbarDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" src="https://picsum.photos/id/237/300/300" />
				        	<span class="creditUserProfil badge badge-pill <%= currentUser.getCredit() < 50 ? "badge-danger" : "badge-dark" %>">${ currentUser.credit }</span>
				      
				        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
				       		<a href="<%= request.getContextPath() %>/profil" class="nav-link">Mon profil</a>
				          	<a href="<%= request.getContextPath() %>/deconnexion" class="nav-link">Déconnexion</a>
				        </div>
				      </li>
				</c:when>
				<c:otherwise>
					<li class="nav-item"><a href="<%= request.getContextPath() %>/connexion" class="nav-link">Se connecter</a></li>
				</c:otherwise>
			</c:choose>
    </ul>
  </div>
</nav>
<jsp:include page="./errors/globalError.jsp" />
<jsp:include page="./errors/appExceptionErrors.jsp" />
<jsp:include page="./success/success.jsp" />
