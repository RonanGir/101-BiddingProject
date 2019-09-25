<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String succesLogin = (String) request.getAttribute("successConnection"); %>
<% String successInscription = (String) request.getAttribute("successInscription"); %>


				
<% if (succesLogin != null && succesLogin.equals("true")) { %>
<div class="flash flash-success alert alert-dismissible fade show" role="alert">
  <span><strong><img class="poopsy" src="<%=request.getContextPath()%>/assets/img/hello.png" > Connexion r�ussie !</strong> Ravi de vous revoir !</span>
  <a data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </a>
</div>
<% } %>
<%if (successInscription != null && successInscription.equals("true")) { %>
<div class="flash flash-success alert alert-dismissible fade show" role="alert">
  <span><strong><img class="poopsy" src="<%=request.getContextPath()%>/assets/img/welcome.png" > Inscription r�ussie !</strong> Bienvenue sur notre site !</span>
  <span><strong>Un cr�dit de 100 points viens de vous �tre cr�dit�. Bonne chasse aux ench�res :)</span>
  <a data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </a>
</div>
<% } %>
