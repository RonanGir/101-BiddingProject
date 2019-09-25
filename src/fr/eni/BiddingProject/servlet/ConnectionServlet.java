package fr.eni.BiddingProject.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.BiddingProject.AppException;
import fr.eni.BiddingProject.bll.ManagerFactory;
import fr.eni.BiddingProject.bo.User;

/**
 * Connection Servlet
 * 
 * Servlet implementation class Connection
 * 
 */
@WebServlet("/connexion")
public class ConnectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * @author Benoit
	 * 
	 *         Do Get : Forward to connection.jsp
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/connection.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * @author Benoit
	 * 
	 *         Do Post : SendRedirect to index.jsp
	 * 
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final int TIMEOUT_REMEMBER_ME  = 2628000;
		final int TIMEOUT_SUCCESS_CONNECTION  = 2;
		boolean checkedRememberMe      = false;
		HttpSession session            = request.getSession();
		
		List<Integer> listErrorCode    = new ArrayList<>();
		
		StringBuffer connectionInfos   = new StringBuffer();
		Cookie rememberMe              = null;
		Cookie succesConnection        = null;
		User currentUser               = new User();
		
		String userSurname             = request.getParameter("identifier").replaceAll(" ", "");
		String userPwd                 = request.getParameter("pwd").replaceAll(" ", "");
		
		if (request.getParameter("rememberMe") != null) checkedRememberMe = Boolean.valueOf(request.getParameter("rememberMe"));
		
		// On initie deux booléens, un pour le check id et un autre pour le mdp
		Boolean checkId = checkId(userSurname, listErrorCode);

		if (listErrorCode.size() > 0) {
			request.setAttribute("listErrorCode", listErrorCode);
			request.getRequestDispatcher("/WEB-INF/jsp/connection.jsp").forward(request, response);
		} else {
			try {
				// On vérifie que l'id a été validé
				if (checkId == true) {
					// si id validé on récupère le user via la méthode getUser
					currentUser = checkConnection(request, userSurname, userPwd, listErrorCode);

					if (currentUser.getNoUser() == 0)
						listErrorCode.add(CodesResultatServlet.CONNECTION_WRONG_CONNECTION);

					if (listErrorCode.size() > 0) {
						request.setAttribute("listErrorCode", listErrorCode);
						request.getRequestDispatcher("/WEB-INF/jsp/connection.jsp").forward(request, response);
					} else {
						// Lors de la connection on ajoute l'utilisateur dans la session
						if (currentUser.isLoggedIn()) session.setAttribute("currentUser", currentUser);
						
						if (currentUser.isLoggedIn() && checkedRememberMe) {
							connectionInfos.append(currentUser.getSurname()).append("_");
							connectionInfos.append(currentUser.getEmail()).append("_");
							connectionInfos.append(currentUser.getPassword());

							rememberMe = new Cookie("rememberMe", connectionInfos.toString());
							rememberMe.setMaxAge(TIMEOUT_REMEMBER_ME);

						}

						if (rememberMe != null)
							response.addCookie(rememberMe);
						
						if (currentUser.isLoggedIn()) {
							succesConnection = new Cookie("success", "true");
							succesConnection.setMaxAge(TIMEOUT_SUCCESS_CONNECTION);
						}
						
						if (succesConnection != null)
							response.addCookie(succesConnection);
						
						response.sendRedirect(request.getContextPath() + "/index");
					}
				}
			} catch (AppException ae) {
				ae.printStackTrace();
				request.setAttribute("listErrorCode", listErrorCode);
				request.getRequestDispatcher("/WEB-INF/jsp/connection.jsp").forward(request, response);
			}
		}
	}

	private Boolean checkId(String id, List<Integer> listErrorCode) {
		Boolean check = false;

		try {
			check = ManagerFactory.getUserManager().checkId(id);
		} catch (AppException ae) {
			ae.printStackTrace();
			listErrorCode.add(CodesResultatServlet.CONNECTION_WRONG_ID);
		}

		if (check == true) {
			check = true;
		} else {
			listErrorCode.add(CodesResultatServlet.CONNECTION_WRONG_ID);
			check = false;
		}

		return check;
	}

	private User checkConnection(HttpServletRequest request, String id, String pwd, List<Integer> listErrorCode)
			throws AppException {
		User user = new User();

		try {
			user = ManagerFactory.getUserManager().getUserBySurname(id, pwd);
		} catch (Exception e) {
			e.printStackTrace();
			listErrorCode.add(CodesResultatServlet.CONNECTION_WRONG_CONNECTION);
		}
		return user;
	}

}
