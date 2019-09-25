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
 * Servlet implementation class getProfileServlet
 */
@WebServlet("/profil")
public class GetProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User usr;
		
		HttpSession session = request.getSession();
		UserManager usrMan  = new ManagerFactory().getUserManager();
		User cUser          = (User)session.getAttribute("currentUser");
		
		usr = usrMan.getUserById(cUser.getNoUser());
		session.setAttribute("currentUser", usr);
		request.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(request, response);
	}

}
