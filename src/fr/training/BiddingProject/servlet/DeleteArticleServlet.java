package fr.training.BiddingProject.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.training.BiddingProject.bll.ManagerFactory;
import fr.training.BiddingProject.bo.User;

/**
 * Servlet implementation class DeleteArticleServlet
 */
@WebServlet("/deleteArticle")
public class DeleteArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGett(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noArticle = 0;
		noArticle     = Integer.parseInt(request.getParameter("id"));
		
		if (noArticle > 0) ManagerFactory.getBidManager().deleteArticleById(noArticle);
	
		response.sendRedirect(request.getContextPath() + "/index");
	}

}
