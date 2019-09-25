<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Connexion</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link href="./assets/css/style.css" type="text/css" rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
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
		<jsp:include page="./components/navbar.jsp" />
	</header>
	<section class="container">
		<%
			String surname = (String)request.getAttribute("surname");
			String email   = (String)request.getAttribute("email");
			String pwd     = (String)request.getAttribute("password");
			
		%>
		<div class="row">
			<div class="col-sm-12">
				<div class="formBodyContainer">
					<h1 class="text-center">Connexion</h1>
					<form method="post" action="<%= request.getContextPath()%>/connexion" class="formContainer">
					  <div class="form-row">
					    <div class="form-group col-md-4 offset-md-4">
					      <label for="identifier">Identifiant</label>
					      <% if (surname != null) { %> 
					      	<input type="text" name="identifier" class="form-control" id="identifier" placeholder="Email ou Pseudo" value="<%= surname %>" autofocus required>
					      <% } else if (surname == null && email != null) { %>
					     	 <input type="text" name="identifier" class="form-control" id="identifier" placeholder="Email ou Pseudo" value="<%= email %>" autofocus required>
					      <% } else { %>
					     	 <input type="text" name="identifier" class="form-control" id="identifier" placeholder="Email ou Pseudo" autofocus required>
					      <% } %>
					    </div>
					    <div class="form-group col-md-4 offset-md-4">
					      <label for="pwd">Mot de passe</label>
					      <input type="password" class="form-control" name="pwd" id="pwd" placeholder="mot de passe" value="<%= pwd != null ? pwd : "" %>" maxlength="30" required>
					    </div>
					  </div>
					  <div class="form-group col-md-4 offset-md-4">
					    <div class="form-check">
					      <input class="form-check-input" name="rememberMe" value="true" type="checkbox" id="gridCheck">
					      <label class="form-check-label" for="gridCheck">
					        Se souvenir de moi
					      </label>
					    </div>
					  </div>
					  <div class="form-group col-md-4 offset-md-4">
					  	<a href="forgotpwd">Mot de passe oublié</a>
					  </div>
					  <div class="form-row">
						  <div class="form-group col-md-4 offset-md-4">
						  	<a href="inscription" class="btn btn-orange">Créer un compte</a>
						  	<button type="submit" class="btn btn-darkOrange">Connexion</button>
						  </div>
					  </div>
					</form>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="./components/footer.jsp" />
</body>
</html>