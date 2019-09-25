<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="fr.training.BiddingProject.bo.SoldArticle"%>
<%@page import="fr.training.BiddingProject.bo.User"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    	<meta name="description" content="">
    	<meta name="author" content="">
		<title>détails article</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link href="./assets/css/style.css" type="text/css" rel="stylesheet" >
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<!--[if gte mso 9]><xml>
			<mso:CustomDocumentProperties>
			<mso:_dlc_DocId msdt:dt="string">Z5HNVW24N33T-678105430-3968</mso:_dlc_DocId>
			<mso:_dlc_DocIdItemGuid msdt:dt="string">2611dc1f-c46b-4e32-86ca-c9014f69baff</mso:_dlc_DocIdItemGuid>
			<mso:_dlc_DocIdUrl msdt:dt="string">http://inet/sites/projets/EcoleNumerique/_layouts/15/DocIdRedir.aspx?ID=Z5HNVW24N33T-678105430-3968, Z5HNVW24N33T-678105430-3968</mso:_dlc_DocIdUrl>
			<mso:xd_Signature msdt:dt="string"></mso:xd_Signature>
			<mso:TemplateUrl msdt:dt="string"></mso:TemplateUrl>
			<mso:xd_ProgID msdt:dt="string"></mso:xd_ProgID>
			<mso:_dlc_DocIdPersistId msdt:dt="string"></mso:_dlc_DocIdPersistId>
			<mso:Order msdt:dt="string">396800.000000000</mso:Order>
			<mso:ContentTypeId msdt:dt="string">0x010100486201FEAA9EBE4FA7C434936B46E6EC</mso:ContentTypeId>
			<mso:_SourceUrl msdt:dt="string"></mso:_SourceUrl>
			<mso:_SharedFileIndex msdt:dt="string"></mso:_SharedFileIndex>
			</mso:CustomDocumentProperties>
		</xml><![endif]-->
	</head>
	<body>
		<header>
			<% 
				SoldArticle currentArticle  = (SoldArticle)request.getAttribute("currentArticle"); 
				User bestBider              = (User)request.getAttribute("highestBider");
				SimpleDateFormat sdf1       = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat sdf2       = new SimpleDateFormat("HH:mm");
				User currentUser            = (User) session.getAttribute("currentUser");
			%>
			<jsp:include page="./components/navbar.jsp"/>
			<jsp:include page="./components/errors/appExceptionErrors.jsp" />
		</header>
		<section class="container minHeightOneHundred">
			<div class="row">
				<div class="col-sm-12">
					<h1 class="text-center">Détails de la vente</h1>
				</div>
			</div>	
			<div class="row minMarginTop">
				<div class="col-sm-12 col-md-6">
					<img alt="placeholder" src="https://picsum.photos/500/500">
				</div>
				<div class="col-sm-12 col-md-6">
					<div class="articleInfos">
						<div class="blockTitle">
							<h2 class="articleTitle">${ currentArticle.articleName }</h2>
							<span class="badge badge-darkOrange">${ currentArticle.cat.name }</span>
							<% if (currentUser.getNoUser() == currentArticle.getSeller().getNoUser() && currentArticle.getBidStartedDate().getTime() >= new Date().getTime()) { %>
								<a href="<%= request.getContextPath() %>/modifier-article?id=${ currentArticle.noArticle}" class="badge badge-info">Modifier</a>
								<a href="" data-toggle="modal" data-target="#deleteArticle" class="badge badge-danger">Annuler la vente</a>
							<% } %>
						</div>
						<div class="blockInfoDate">
							<div class="articleStartBid">
								<span class="prefixDateBid">L'enchère a commencé le</span>
								<span class="bidInfo"><%= sdf1.format(currentArticle.getBidStartedDate()) %> à <%= sdf2.format(currentArticle.getBidStartedDate()) %></span>
							</div>
							<div class="articleEndBid">
								<span class="prefixDateBid">et se termine le</span>
								<span class="bidInfo"><%= sdf1.format(currentArticle.getBidEndDate()) %> à <%= sdf2.format(currentArticle.getBidEndDate()) %></span>
							</div>
						</div>
						<div class="blockBid">	
							<div class="row">
							
								<div class="col-xs-12 col-sm-6 col-sm-6">
									<div class="blockInfos">
										<h3 class="titleInfo">Description</h3>
										<p class="contentInfo">${ currentArticle.description }</p>
									</div>	
								</div>
								
								<div class="col-xs-12 col-sm-6 col-sm-6">
									<div class="blockBidContent">
										<div class="blockInfosBids">
											<div class="blockInfos startBidBlock">
												<h3 class="titleInfo">Mise à prix</h3>
												<p class="contentInfo">${ currentArticle.bidStartPrice } <%= currentArticle.getBidStartPrice() > 1 ? "points" : "point" %></p>
											</div>
											<% if ( bestBider != null && (Integer)request.getAttribute("bestBidAmount") > 0) { %>
												<div class="blockInfos bestBidBlock">
													<h3 class="titleInfo">Meilleure Offre</h3>
													<p class="contentInfo"> ${ bestBidAmount } ${ bestBidAmount > 1 ? "points" : "point" } par <%= bestBider.getSurname() %></p>
												</div>					
											 <% } %>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12 col-sm-6">
									<form action="<%= request.getContextPath() %>/newBidServlet" method="post" class="form-inline minMarginTop">
										 <div class="form-group mx-sm-3 mb-2">
										 	<label for="bidProp" class="sr-only">Votre proposition</label>
											<input type="number" name="newBid" class="form-control" id="bidProp" placeholder="Proposition">
											<input type="hidden" name="noArticle" value="${ currentArticle.noArticle }">
											<input type="hidden" name="bidStartPrice" value="${ currentArticle.bidStartPrice }">
											<c:if test="${ bestBider != null && bestBidAmount > 0 }">
												<input type="hidden" name="bestBidAmount" value="${ bestBidAmount > 0 ? bestBidAmount : 0 }">
												<input type="hidden" name="actualBestBider" value="<%= bestBider.getNoUser()%>">
											</c:if>
										 </div>
										 <button type="submit" class="btn btn-orange mb-2" <%= currentUser.getNoUser() == currentArticle.getNoUser() ? "disabled" : ""%>>Enchérir</button>
									</form>
								</div>
							</div>
						</div>	
						
						<div class="blockSeller minMarginTop">
							<div class="blockInfos">
								<h3 class="titleInfo">Adresse de retrait</h3>
								<p class="contentInfo">${ currentArticle.ret }</p>
							</div>					
							<div class="blockInfos">
								<h3 class="titleInfo">Vendeur</h3>
								<p class="contentInfo">${ currentArticle.seller.surname }</p>
							</div>
						</div>						
					</div>
				</div>
			</div>
		</section>
		
		
		<!-- Modal -->
		<div class="modal fade" id="deleteArticle" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="alert alert-danger">
		       	<h5 class="modal-title" id="exampleModalLabel">Vous souhaitez supprimer votre article ?</h5>
		      </div>
		      <div class="modal-body">	 		
		 		<div class="deleteAccountMessageContainer">
			        <p>Vous vous apprêtez à supprimer votre article, cette action est irrévocable et toutes les informations concenants cette article seront supprimées de l'application</p>
			        <p>Si vous avez des enchères en cours, elles seront annulées.</p>
			        <p class="importantMessage">Êtes vous sûr de vouloir supprimer votre article ?</p>
		 		</div>
		       
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-danger" data-dismiss="modal">Annuler</button>
		        <a href="<%= request.getContextPath()%>/deleteArticle?id=<%= currentArticle.getNoArticle() %>" class="btn btn-light">Oui, je supprime mon article</a>
		      </div>
		    </div>
		  </div>
		</div>
		<jsp:include page="./components/footer.jsp"/>
	</body>
</html>