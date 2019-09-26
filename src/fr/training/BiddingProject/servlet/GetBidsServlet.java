package fr.training.BiddingProject.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.training.BiddingProject.AppException;
import fr.training.BiddingProject.bll.BidManager;
import fr.training.BiddingProject.bll.ManagerFactory;
import fr.training.BiddingProject.bll.UserManager;
import fr.training.BiddingProject.bo.SoldArticle;
import fr.training.BiddingProject.bo.User;

/**
 * Servlet implementation class GetBidsServlet
 */
@WebServlet({ "/index", "/chercher" })
public class GetBidsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * @author Benoit
	 * 
	 *         Index Servlet, forward to index.jsp
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		List<SoldArticle> ListArticleInProgress = null;

		try {
			ListArticleInProgress = ManagerFactory.getBidManager().getArticleBidsInProgress();
			
		} catch (AppException ae) {
			
			request.setAttribute("listErrorCode", ae.getListeCodesErreur());
		}
		
		int cat = -1;

		request.setAttribute("Cat", cat);
		
		if (ListArticleInProgress != null) request.setAttribute("ArticlesInProgress", ListArticleInProgress);

		request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
	}
	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session                = request.getSession();
		String search                      = "";
		List<SoldArticle> listArticleByCat = null;
		int noUser                         = 0;
		
		int noCat                          = Integer.parseInt(request.getParameter("noCategory"));
		User user                          = (User) session.getAttribute("currentUser");
		
		// Check if the search bar is used (term of search or not) 
		Boolean checkInput = ManagerFactory.getUserManager().checkSearch(request.getParameter("search").trim());
		
		if (checkInput == true) search = request.getParameter("search").trim();

		
		// Filtres
		try {
			
			// Si le bouton radio Achats est coché :
			if (request.getParameter("radioAchats").equals("achats")) {
				String winBids = "";
				
				// Si mes enchères est coché, on récupère le numéro d'user
				if (request.getParameter("myBids") != null) noUser = user.getNoUser();
	
				// Si mes enchères remportées est coché, winBids passe à on et on affecte le numéro d'user
				if (request.getParameter("winBids") != null) {
					winBids = "on";
					noUser  = user.getNoUser();
				}
				
				listArticleByCat = ManagerFactory.getBidManager().getListArticleByAchats(search, noCat, request.getParameter("openBids"),
						noUser, winBids);
			}
			
			// Si le bouton radio Sales est coché
			if (request.getParameter("radioAchats").equals("sales")) {
				String pending = "";
				String end     = "";
				
				// Si mes ventes en cours est coché, on récupère le no d'user
				if (request.getParameter("openSales") != null) noUser = user.getNoUser();
			
				// Si les ventes non débutées est coché, on passe l'attribut pending à on
				if (request.getParameter("pendingSales") != null) pending = "on";
				
				// Si les ventes terminées est coché, on passe l'attribut end à on
				if (request.getParameter("endSales") != null) end = "on";
			
				listArticleByCat = ManagerFactory.getBidManager().getListArticleBySales(search, noCat, noUser, pending, end);
				
			}
		} catch (NullPointerException | AppException e) {
			
			try {
				
				// SI le try n'a pas fonctionné, on va chercher la méthode
				// getListArticleByCategory et on l'affecte à listArticleByCat
				listArticleByCat = ManagerFactory.getBidManager().getListArticleByCategory(search, noCat);
				
			} catch (AppException ae) {
				
				// On envoie la liste erreur si passage dans le catch
				request.setAttribute("listErrorCode", ae.getListeCodesErreur());
				ae.printStackTrace();
			}
		}
		// On affiche la liste d'article
		request.setAttribute("ArticlesInProgress", listArticleByCat);
		// On affiche le terme de la dernière recherche
		request.setAttribute("Search", request.getParameter("search"));
		// On affiche le dernier choix de catégorie
		request.setAttribute("Cat", noCat);

		
		request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
	}

}
