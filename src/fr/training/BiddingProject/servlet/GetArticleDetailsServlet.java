package fr.training.BiddingProject.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.training.BiddingProject.AppException;
import fr.training.BiddingProject.bll.ManagerFactory;
import fr.training.BiddingProject.bo.Bid;
import fr.training.BiddingProject.bo.SoldArticle;
import fr.training.BiddingProject.bo.User;

@WebServlet("/details-article")
public class GetArticleDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @author rgirault2019
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bidMax                  = 0;
		int noBestBider             = 0;
		List<Bid> listBid           = null;
		User bestBider              = null;
		SoldArticle selectedArticle = null;
		int noArticle               = Integer.parseInt(request.getParameter("id"));
		
		if (noArticle > 0) {
			try {
				selectedArticle = ManagerFactory.getBidManager().getArticleById(noArticle);
			} catch (AppException ae) {
				request.setAttribute("listErrorCode", ae.getListeCodesErreur());
				ae.printStackTrace();
			}
		}
		
		if (selectedArticle.getListBidArticle().size() > 0) {
			listBid = selectedArticle.getListBidArticle();
		
			for (Bid bid : listBid) {
				if (bid.getBidAmount() > bidMax) {
					bidMax      = bid.getBidAmount();
					noBestBider = bid.getNoUser();
				}
			}
		}
		
		if (noBestBider > 0) bestBider = ManagerFactory.getUserManager().getUserById(noBestBider);
		if (bestBider != null) request.setAttribute("highestBider", bestBider);
		if (selectedArticle != null) request.setAttribute("currentArticle", selectedArticle);
		if (bidMax > 0) {
			request.setAttribute("bestBidAmount", bidMax);
		} else {
			request.setAttribute("bestBidAmount", 0);
		}
		
		request.getRequestDispatcher("/WEB-INF/jsp/articleDetails.jsp").forward(request,response);
	}

}
