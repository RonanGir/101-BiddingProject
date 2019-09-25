<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="fr.eni.BiddingProject.bo.User"%>
<%@page import="fr.eni.BiddingProject.bo.Category"%>
<%@page import="fr.eni.BiddingProject.bo.Regex"%>
<%@page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%	
	int cat = (int) request.getAttribute("Cat");
	String input = (String) request.getAttribute("Search");
	List<Category> listCat = (List) application.getAttribute("Category");
	User currentUser = (User) session.getAttribute("currentUser");
%>

<div class="row">
	<div class="col-sm-12">
		<div class="searchContainer">
			<h2>Filtres</h2>
			<form action="chercher" method="post">
				<div class="form-row">
					<div class="col-auto">
						<input type="text" pattern="<%=Regex.getREGEX_BASIC()%>" value='<%=  input != null && !"".equals(input) ? input : "" %>' name="search" class="form-control" placeholder="Le nom de l'article contient">
					</div>
					<label class="col-auto" for="category_select">Catégorie :</label>
					<div class="col-auto">
						<select name="noCategory" id="category_select" class="form-control">
							<%
								for (Category cate : listCat) {							
							%>
							<option <%= cat == cate.getNoCategory() ? "selected" : "" %> value="<%= cate.getNoCategory() %>"><%= cate.getName() %></option>
							<%
								}
							%>
						</select>
					</div>
					
				
					<div class="col-auto">
						<button class="btn btn-darkOrange">Rechercher</button>
					</div>
					<div class="col-sm-12">
						<c:if test="${currentUser.loggedIn}">
							<div class="AdvancedFilters col-sm-12">
								<div id="selectedAchatsBlock" class="achatsBlock col-sm-3">
									<div class="col-sm-12">
										<input type="radio" id="achats" name="radioAchats" value="achats"> <label for="achats">Achats</label>
									</div>
									<div class="col-sm-12">
										<input type="checkbox" id="openBids" name="openBids"> <label for="openBids">enchères ouvertes</label>
									</div>
									<div class="col-sm-12">	
										<input type="checkbox" id="myBids" name="myBids"> <label for="myBids">mes enchères</label>
									</div>
									<div class="col-sm-12">	
										<input type="checkbox" id="winBids" name="winBids"> <label for="winBids">mes enchères remportées</label>
									</div>
								</div>
								<div id="selectedSalesBlock" class="salesBlock col-sm-3">
									<div class="col-sm-12">
										<input type="radio" id="sales" name="radioAchats" value="sales"> <label for="sales">Mes Ventes</label>
									</div>
									<div class="col-sm-12">
										<input type="checkbox" id="openSales" name="openSales" > <label for="openSales">mes ventes en cours</label>
									</div>
									<div class="col-sm-12">	
										<input type="checkbox" id="pendingSales" name="pendingSales"> <label for="pendingSales">ventes non débutées</label>
									</div>
									<div class="col-sm-12">	
										<input type="checkbox" id="endSales" name="endSales"> <label for="endSales">ventes terminées</label>
									</div>
								</div>
							</div>
						</c:if>
					</div>
					</div>
				</form>
			</div>
		</div>
	</div>