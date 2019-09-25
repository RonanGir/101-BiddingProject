<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="fr.training.BiddingProject.bo.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    	<meta name="description" content="">
    	<meta name="author" content="">
		<title>Votre Profil</title>
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
			<% User currentUser = (User) session.getAttribute("currentUser"); %>
			<jsp:include page="./components/navbar.jsp"/>
		</header>
		<section class="container minHeightOneHundred minMarginTop">
			<div class="row">
				<div class="col-sm-12">
					<h1 class=text-center>Votre profil</h1>
				</div>
			</div>
			<div class="row d-flex justify-content-center minMarginTop">
				<div class="profileDataContainer">
					<div class="col-sm-12">
						<div class="mainProfilInfo">
							<img alt="avatar" class="avatar-profil-large" src="https://picsum.photos/id/237/300/300">
							<div class="profilInfosContent">
								<h2 id="nameProfil" class="userData">${currentUser.firstName} ${currentUser.lastName} (<span class="surnameProfil">${currentUser.surname}</span>)</h2>
								<p class="creditProfil"><strong><%= currentUser.getCredit() > 1 ? "Crédits disponibles" : "Crédit disponible" %> : </strong><span class="userData">${currentUser.credit} <%= currentUser.getCredit() > 1 ? "points" : "point" %></span></p>
								<p class="adressProfil"><strong>Adresse de retrait principale : </strong><span class="userData">${currentUser.street} ${currentUser.zipCode} ${currentUser.city}</span></p>
								<p class=""><strong>Téléphone : </strong><span class="userData">${currentUser.phone}</span></p>
								<a href="<%= request.getContextPath() %>/profil/modifier" class="btn btn-darkOrange minMarginTop">Mettre à jour mon profil</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<jsp:include page="./components/footer.jsp"/>
	</body>
</html>