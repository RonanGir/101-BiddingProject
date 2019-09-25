<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="fr.eni.BiddingProject.bo.User"%>
<%@page import="fr.eni.BiddingProject.bo.SoldArticle"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    	<meta name="description" content="">
    	<meta name="author" content="">
		<title>Modifier votre article</title>
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
				User currentUser     = (User)session.getAttribute("curentUser");
				SoldArticle article  = (SoldArticle)request.getAttribute("currentArticle");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
			%>
			<jsp:include page="./components/navbar.jsp"/>
		</header>
		<section class="container minHeightOneHundred minMarginTop">
			<div class="row">
				<div class="col-sm-12">
					<h1 class="text-center minMarginTop">Mettre à jour l' article</h1>
					
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<form action="modifier-article?id=${currentArticle.noArticle}" method="post" class="minMarginTop">
					  <div class="form-row">
					    <div class="form-group col-md-4">
					      <label for="articleName">Nom de l'article</label>
					      <input type="text" class="form-control" id="articleName" name="articleName" placeholder="Nom de l'article" value="${ currentArticle.articleName }" required>
					    </div>
					    <div class="form-group col-md-4">
					      <label for="bidStartPrice">Valeur de l'article</label>
					      <input type="number" class="form-control" id="bidStartPrice" name="bidStartPrice" placeholder="Points initiaux" value="${ currentArticle.bidStartPrice }" required>
					    </div>
					    <div class="form-group col-md-4">
					      <label for="category">Catégorie</label>
					      <select name="noCategory" class="custom-select mr-sm-2" id="category" required>
					        <option value="0">Choisissez...</option>
					        <option value="1" <%= article.getNoCategory() == 1 ? "selected" : "" %>>Informatique</option>
					        <option value="2" <%= article.getNoCategory() == 2 ? "selected" : "" %>>Ameublement</option>
					        <option value="3" <%= article.getNoCategory() == 3 ? "selected" : "" %>>Vêtement</option>
					        <option value="4" <%= article.getNoCategory() == 4 ? "selected" : "" %>>Sport &amp Loisirs</option>
					      </select>
					    </div>
					    <div class="form-group col-md-4">
					    	<label for="retStreet">Adresse de retrait</label>
					    	<input type="text" class="form-control" id="retStreet" name="retStreet" value="${currentArticle.ret.street}" placeholder="Rue" required>
					     </div>
					    <div class="form-group col-md-4">
					   		 <label for="retZipCode">Code postal</label>
					    	<input type="text" class="form-control" id="retZipCode" name="retZipCode" value="${currentArticle.ret.zipCode}" placeholder="Code Postal" required>
					     </div>
					    <div class="form-group col-md-4">
					    	<label for="retCity">Ville</label>
					    	<input type="text" class="form-control" id="retCity" name="retCity" value="${currentArticle.ret.city}" placeholder="Ville" required>
					     </div>
					    <div class="form-group col-md-6">
					      <label for="bidStartDate">Début de l'enchère</label>
					      <input type="datetime-local" class="form-control" id="bidStartDate" name="bidStartDate" placeholder="Début de l'enchère" value="<%= sdf.format(article.getBidStartedDate()).replace(" ", "T") %>" required>
					    </div>
					    <div class="form-group col-md-6">
					      <label for="bidEndDate">Fin de l'enchère</label>
					      <input type="datetime-local" class="form-control" id="bidEndDate" name="bidEndDate" placeholder="Fin de l'enchère" value="<%= sdf.format(article.getBidEndDate()).replace(" ", "T") %>" required>
					    </div>
					    
					    <div class="form-group col-md-12">
					      <label for="descriptionArticle">Description</label>
					      <textarea id="descriptionArticle" name="description" class="form-control" placeholder="Saisissez la description de l'article">${ currentArticle.description }</textarea>
					    </div>
					  </div>
					      <input type="hidden" class="form-control" id="noRet" name="noRet" value="<%= article.getRet().getNoRetirement() %>" required>
					  <button type="submit" class="btn btn-info">Modifier</button>
					</form>
				</div>
			</div>
		</section>
		<jsp:include page="./components/footer.jsp"/>
	</body>
</html>