<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="fr.training.BiddingProject.bo.Regex"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    	<meta name="description" content="">
    	<meta name="author" content="">
		<title>Inscription</title>
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
			<jsp:include page="./components/navbar.jsp"/>
		</header>
		<section class="container minHeightOneHundred">
			<div class="row">
				<div class="col-sm-12">
					<h1 class="text-center">Inscription</h1>
				</div>
			</div>
			
			<div class="row">
				<div class="col-sm-12">
					<form method="post" action="inscription">
					 <div class="form-row">
					    <div class="col">
							<label for="pseudo">Pseudo : </label>
							<input maxlength="30" pattern="<%=Regex.getREGEX_BASIC()%>" id="pseudo" class="form-control" name="pseudo" type="text" placeholder="Pseudo" autofocus required>
						</div>
						<div class="col">
							<label for="lastname">Nom : </label>
							<input maxlength="30" pattern="<%=Regex.getRegexBasicNoNumbers()%>" id="lastname" name="lastname" class="form-control" type="text" placeholder="Nom" required>
						</div>
						<div class="col">
							<label for="firstname">Prénom : </label>
							<input maxlength="30" pattern="<%=Regex.getRegexBasicNoNumbers()%>" id="firstname" name="firstname" class="form-control" type="text" placeholder="Prénom" required>
						</div>
					  </div>
					 <div class="form-row">
						 <div class="col">
							<label for="mail">Mail : </label>
							<input maxlength="20" pattern="<%=Regex.getREGEX_MAIL()%>" id="mail" name="mail" class="form-control" type="email" placeholder="Mail" required>
						</div>
						<div class="col">
							<label for="phone">Téléphone : </label>
							<input maxlength="15" pattern="<%=Regex.getRegexPhone() %>" id="phone" name="phone" class="form-control" type="text" placeholder="Téléphone" >
						</div>
					</div>	
					<div class="form-row">
						 <div class="col">
							<label for="street">Rue : </label>
							<input maxlength="30" id="street" name="street" class="form-control" type="text" placeholder="Rue" required>
						</div>
						<div class="col">
							<label for="zipcode">Code Postal : </label>
							<input maxlength="10" pattern="<%=Regex.getREGEX_BASIC()%>" id="zipcode" name="zipcode" class="form-control" type="text" placeholder="Code Postal" required>
						</div>
						<div class="col">
							<label for="city">Ville : </label>
							<input maxlength="30" pattern="<%=Regex.getRegexBasicNoNumbers()%>" id="city" name="city" class="form-control" type="text" placeholder="Ville" required>
						</div>
					</div>
					<div class="form-row">
						 <div class="col">
							<label for="pwd">Mot de passe : </label>
							<input maxlength="30" pattern="<%=Regex.getREGEX_PWD()%>" id="pwd" name="pwd" class="form-control" type="password" placeholder="Mot de passe" required>
						</div>
						<div class="col">
							<label for="pwdverif">Confirmation : </label>
							<input maxlength="30" id="pwdverif" name="pwdverif" class="form-control" type="password" placeholder="Confirmez le mot de passe" required>
						</div>
					</div>
					<div class="col-auto">
				       <div class="form-check mb-2">
					        <input class="form-check-input" type="checkbox" id="rememberMe">
					        <label class="form-check-label" for="rememberMe">Se souvenir de moi</label>
				      </div>
				    </div>
					<button type="submit" class="btn btn-darkOrange">Connexion</button>
				   	<a href="index" class="btn btn-light">Annuler</a>
					</form>
				</div>
			</div>
		</section>
		<jsp:include page="./components/footer.jsp"/>
</html>