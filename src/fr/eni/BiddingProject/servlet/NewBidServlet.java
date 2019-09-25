package fr.eni.BiddingProject.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.BiddingProject.bll.ManagerFactory;
import fr.eni.BiddingProject.bo.User;

/**
 * Servlet implementation class newBidServlet
 */
@WebServlet("/newBidServlet")
public class NewBidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @author rgirault2019
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentArticleId=0, bestBidAmount=0, bidStartPrice=0, amount=0, oldBestBid=0, odlBestbider=0;
		User oldUserBestBider = null;
		HttpSession session   = request.getSession();
		User bider            = (User)session.getAttribute("currentUser");
		
		if (!"".equals(request.getParameter("noArticle"))) currentArticleId   = Integer.parseInt(request.getParameter("noArticle"));
		if (!"".equals(request.getParameter("bidStartPrice"))) bidStartPrice  = Integer.parseInt(request.getParameter("bidStartPrice"));
		if (!"".equals(request.getParameter("newBid"))) amount                = Integer.parseInt(request.getParameter("newBid"));
		
		if (request.getParameter("bestBidAmount") != null && !"".equals(request.getParameter("bestBidAmount")))  {
			oldBestBid    = Integer.parseInt(request.getParameter("bestBidAmount"));
			bestBidAmount = Integer.parseInt(request.getParameter("bestBidAmount"));
			if (!"".equals(request.getParameter("actualBestBider"))) odlBestbider = Integer.parseInt(request.getParameter("actualBestBider"));
		}
		
		if (odlBestbider > 0) oldUserBestBider = ManagerFactory.getUserManager().getUserById(odlBestbider);
	
		
		try {
			if (bider != null && currentArticleId > 0 && (bestBidAmount > 0 && amount > bestBidAmount) || (bestBidAmount == 0 && amount > bidStartPrice))
				
				ManagerFactory.getBidManager().createNewBid(amount, currentArticleId, bider, oldUserBestBider, oldBestBid, bestBidAmount, bidStartPrice);
				
		} catch (Exception e){
			e.printStackTrace();
		}
		
		if (currentArticleId > 0) response.sendRedirect(request.getContextPath() + "/details-article?id=" + currentArticleId);
	}

}
