<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="fr.training.BiddingProject.bo.User"%>
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
		<title>Vendre un article</title>
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
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Timestamp startBid   = new Timestamp(new Date().getTime());
				Timestamp EndBid     = new Timestamp(new Date().getTime());
				Calendar calStart    = Calendar.getInstance();
				Calendar calEnd      = Calendar.getInstance();
				calStart.setTimeInMillis(startBid.getTime());
				calStart.add(Calendar.DAY_OF_MONTH, 1);
				calEnd.setTimeInMillis(startBid.getTime());
				calEnd.add(Calendar.DAY_OF_MONTH, 7);
			    startBid = new Timestamp(calStart.getTime().getTime());
			    EndBid   = new Timestamp(calEnd.getTime().getTime());
			    
			%>
			<jsp:include page="./components/navbar.jsp"/>
		</header>
		<section class="container minHeightOneHundred minMarginTop">
			<div class="row">
				<div class="col-sm-12">
					<h1 class="text-center minMarginTop">Vendre un nouvel article</h1>
				</div>
			</div>
			
			<div class="row">
				<div class="col-sm-12">
					<form action="" method="post" class="minMarginTop">
					  <div class="form-row">
					    <div class="form-group col-md-4">
					      <label for="articleName">Nom de l'article</label>
					      <input type="text" class="form-control" id="articleName" name="articleName" placeholder="Nom de l'article" required>
					    </div>
					    <div class="form-group col-md-4">
					      <label for="bidStartPrice">Valeur de l'article</label>
					      <input type="number" class="form-control" id="bidStartPrice" name="bidStartPrice" placeholder="Points initiaux" required>
					    </div>
					    <div class="form-group col-md-4">
					      <label for="category">Catégorie</label>
					      <select name="noCategory" class="custom-select mr-sm-2" id="category" required>
					        <option value="0" selected>Choisissez...</option>
					        <option value="1">Informatique</option>
					        <option value="2">Ameublement</option>
					        <option value="3">Vêtement</option>
					        <option value="4">Sport &amp Loisirs</option>
					      </select>
					    </div>
					    <div class="form-group col-md-4">
					    	<label for="retStreet">Adresse de retrait</label>
					    	<input type="text" class="form-control" id="retStreet" name="retStreet" value="${currentUser.street}" placeholder="Rue" required>
					     </div>
					    <div class="form-group col-md-4">
					   		 <label for="retZipCode">Code postal</label>
					    	<input type="text" class="form-control" id="retZipCode" name="retZipCode" value="${currentUser.zipCode}" placeholder="Code Postal" required>
					     </div>
					    <div class="form-group col-md-4">
					    	<label for="retCity">Ville</label>
					    	<input type="text" class="form-control" id="retCity" name="retCity" value="${currentUser.city}" placeholder="Ville" required>
					     </div>
					    <div class="form-group col-md-6">
					      <label for="bidStartDate">Début de l'enchère</label>
					      <input type="datetime-local" class="form-control" id="bidStartDate" name="bidStartDate" value="<%= sdf.format(startBid).replace(" ", "T") %>" placeholder="Début de l'enchère" required>
					    </div>
					    <div class="form-group col-md-6">
					      <label for="bidEndDate">Fin de l'enchère</label>
					      <input type="datetime-local" class="form-control" id="bidEndDate" name="bidEndDate" value="<%= sdf.format(EndBid).replace(" ", "T") %>" placeholder="Fin de l'enchère" required>
					    </div>
					    
					    <div class="form-group col-md-12">
					      <label for="descriptionArticle">Description</label>
					      <textarea id="descriptionArticle" name="description" class="form-control" placeholder="Saisissez la description de l'article"></textarea>
					    </div>
					  </div>
					  <button type="submit" class="btn btn-darkOrange">Vendre</button>
					</form>
				</div>
			</div>
		</section>
		<jsp:include page="./components/footer.jsp"/>
	</body>
</html>