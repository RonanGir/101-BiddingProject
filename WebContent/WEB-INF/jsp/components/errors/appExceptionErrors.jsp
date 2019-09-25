<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="fr.training.BiddingProject.messages.LecteurMessage"%>

<c:if test="${!empty listErrorCode}">
	<div class="flash flash-danger alert alert-dismissible fade show" role="alert">
		<strong><img class="poopsy" src="<%=request.getContextPath()%>/assets/img/poop.png" /> Poopsy ! </strong>
		<ul>
	  	<c:forEach var="code" items="${listErrorCode}">
	  		<li>${LecteurMessage.getMessageErreur(code)}</li>
	  	</c:forEach>
	  </ul>
	</div>
</c:if>

