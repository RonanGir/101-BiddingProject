<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="fr.training.BiddingProject.bo.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    	<meta name="description" content="">
    	<meta name="author" content="">
		<title>Modifier Profile</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link href="../assets/css/style.css" type="text/css" rel="stylesheet" >
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
		<section class="container minHeightOneHundred">
			<div class="row">
				<div class="col-sm-12">
					<h1 class="text-center">Mettre à jour mon profil</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<form method="post" action="modifier">
						 <div class="form-row">
						    <div class="col">
								<label for="pseudo">Pseudo : </label>
								<input maxlength="30" id="pseudo" class="form-control" name="pseudo" type="text" placeholder="Pseudo" value="${currentUser.surname}" autofocus required>
							</div>
							<div class="col">
								<label for="lastname">Nom : </label>
								<input id="lastname" name="lastname" class="form-control" type="text" placeholder="Nom" value="${currentUser.lastName}">
							</div>
							<div class="col">
								<label for="firstname">Prénom : </label>
								<input id="firstname" name="firstname" class="form-control" type="text" placeholder="Prénom" value="${currentUser.firstName}">
							</div>
						  </div>
						 <div class="form-row">
							 <div class="col">
								<label for="mail">Mail : </label>
								<input id="mail" name="mail" class="form-control" type="email" placeholder="Mail" value="${currentUser.email}">
							</div>
							<div class="col">
								<label for="phone">Téléphone : </label>
								<input id="phone" name="phone" class="form-control" type="text" placeholder="Téléphone" value="${currentUser.phone}">
							</div>
						</div>	
						<div class="form-row">
							 <div class="col">
								<label for="street">Rue : </label>
								<input id="street" name="street" class="form-control" type="text" placeholder="Rue"  value="${currentUser.street}">
							</div>
							<div class="col">
								<label for="zipcode">Code Postal : </label>
								<input id="zipcode" name="zipcode" class="form-control" type="text" placeholder="Code Postal" value="${currentUser.zipCode}">
							</div>
							<div class="col">
								<label for="city">Ville : </label>
								<input id="city" name="city" class="form-control" type="text" placeholder="Ville" value="${currentUser.city}">
							</div>
						</div>
						<div class="form-row">
							 <div class="col">
								<label for="pwd">Mot de passe : </label>
								<input id="pwd" name="pwd" class="form-control" type="password" placeholder="Mot de passe">
							</div>
							<div class="col">
								<label for="pwdverif">Confirmation : </label>
								<input id="pwdverif" name="pwdverif" class="form-control" type="password" placeholder="Confirmez le mot de passe">
							</div>
						</div>
						<button type="submit" class="btn btn-success">Modifier</button>
						<!-- Button trigger modal -->
					   	<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#exampleModal">Supprimer mon compte</button>
					   	<a href="<%=request.getContextPath()%>/profil" class="btn btn-light">Annuler</a>
					</form>
					<!-- Modal -->
					<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog" role="document">
					    <div class="modal-content">
					      <div class="alert alert-danger">
					       	<h5 class="modal-title" id="exampleModalLabel">Vous souhaitez supprimer votre compte ?</h5>
					      </div>
					      <div class="modal-body">	 		
					 		<div class="deleteAccountMessageContainer">
						        <p>Vous vous apprêtez à supprimer votre compte, cette action est irrévocable et toutes les informations vous concenants seront supprimées de l'application</p>
						        <p>Si vous avez des enchères en cours, elles seront annulées.</p>
						        <p class="importantMessage">Êtes vous sûr de vouloir supprimer votre compte ?</p>
					 		</div>
					       
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-danger" data-dismiss="modal">Annuler</button>
					        <a href="<%= request.getContextPath()%>/profil/delete" class="btn btn-light">Oui, je supprime mon compte</a>
					      </div>
					    </div>
					  </div>
					</div>
				</div>
			</div>
		</section>
		<jsp:include page="./components/footer.jsp"/>
	</body>
</html>