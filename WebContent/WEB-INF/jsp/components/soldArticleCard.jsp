<%@page import="fr.eni.BiddingProject.bo.SoldArticle"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<%@page import="java.text.SimpleDateFormat"%>

<c:if test="${!empty ArticlesInProgress}">
	<div class="row">
		<c:forEach var="article" items="${ArticlesInProgress}">
		  <div class="col-sm-6">
			  <div class="cardContainer">
				  <div class="card-product">
					  <img src="https://picsum.photos/300/300" />
					  <div class="card-product-infos">
					    <h2 class="card-title">${article.articleName}</h2>
					     <p class="card-text">Prix : ${article.bidStartPrice}</p>
					        <p class="card-text">Fin de l'enchère : 
					        	<fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${article.bidEndDate}" />
					        </p>
					        <p class="card-text">Vendeur : ${article.seller.surname}</p>
					        <a href="<%= request.getContextPath() %>/details-article?id=${ article.noArticle }" class="btn btn-darkOrange">Voir l'article</a>
					  </div>
				  </div>
				</div>
			</div>
		</c:forEach>
	</div>
</c:if>

