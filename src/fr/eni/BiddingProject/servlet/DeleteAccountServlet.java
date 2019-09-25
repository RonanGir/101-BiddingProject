package fr.eni.BiddingProject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.BiddingProject.bll.ManagerFactory;
import fr.eni.BiddingProject.bll.UserManager;
import fr.eni.BiddingProject.bo.User;

/**
 * Servlet implementation class DeleteAccountServlet
 */
@WebServlet("/profil/delete")
public class DeleteAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @author rgirault2019
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();	
		User user           = (User)session.getAttribute("currentUser");
		ManagerFactory.getUserManager().deleteUser(user.getNoUser());
		session.invalidate();
		response.sendRedirect(request.getContextPath() + "/index");
	}

}
