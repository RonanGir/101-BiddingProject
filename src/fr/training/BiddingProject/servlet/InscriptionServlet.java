package fr.training.BiddingProject.servlet;

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

import fr.training.BiddingProject.AppException;
import fr.training.BiddingProject.bll.ManagerFactory;
import fr.training.BiddingProject.bll.UserManager;
import fr.training.BiddingProject.bo.User;

/**
 * @author Benoit Servlet implementation class InscriptionServlet
 */
@WebServlet("/inscription")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/inscription.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Integer> listErrorCode = new ArrayList<>();
		HttpSession session = request.getSession();
		Boolean checkInscription = false;
		
		Cookie inscriptionSuccess = null;
		final int TIMEOUT_SUCCESS_INSCRIPTION  = 2;
		
		String nbPoints = this.getServletContext().getInitParameter("POINTS");
		User cUser = new User();
		String cUserSurname = request.getParameter("pseudo");
		String cUserLastname = request.getParameter("lastname");
		String cUserFirstname = request.getParameter("firstname");
		String cUserMail = request.getParameter("mail");
		String cUserPhone = request.getParameter("phone");
		String cUserStreet = request.getParameter("street");
		String cUserZipcode = request.getParameter("zipcode");
		String cUserCity = request.getParameter("city");
		String cUserPwd = request.getParameter("pwd");

		UserManager usrMan = new ManagerFactory().getUserManager();
		
		try {
			checkInscription = checkInscription(cUserSurname, cUserLastname, cUserFirstname, cUserMail, cUserPhone, cUserStreet,
				cUserZipcode, cUserCity, cUserPwd, listErrorCode);
		} catch (AppException ae) {
			request.setAttribute("listErrorCode", listErrorCode);
			request.getRequestDispatcher("/WEB-INF/jsp/inscription.jsp").forward(request, response);
		}
		
		if (listErrorCode.size() > 0) {
			request.setAttribute("listErrorCode", listErrorCode);
			request.getRequestDispatcher("/WEB-INF/jsp/inscription.jsp").forward(request, response);
		} else {
			try {
				if (checkInscription == true) {
					cUser = usrMan.insertUser(cUserSurname, cUserLastname, cUserFirstname, cUserMail, cUserPhone, cUserStreet,
							cUserZipcode, cUserCity, cUserPwd, nbPoints);
					
					if (cUser.getNoUser() == 0)
						listErrorCode.add(CodesResultatServlet.INSCRIPTION_FAIL);
					
					if (listErrorCode.size() > 0) {
						request.setAttribute("listErrorCode", listErrorCode);
						request.getRequestDispatcher("/WEB-INF/jsp/inscription.jsp").forward(request, response);
					} else {
						if (cUser.isLoggedIn())
							session.setAttribute("currentUser", cUser);
						if (cUser != null)
							request.setAttribute("user", cUser);
						
						if (cUser.isLoggedIn()) {
							inscriptionSuccess = new Cookie("inscriptionSuccess", "true");
							inscriptionSuccess.setMaxAge(TIMEOUT_SUCCESS_INSCRIPTION);
						}
						
						if (inscriptionSuccess != null)
							response.addCookie(inscriptionSuccess);
												
						response.sendRedirect(request.getContextPath() + "/index");
					}
				}
			} catch (AppException ae) {
				ae.printStackTrace();
				request.setAttribute("listErrorCode", listErrorCode);
				request.getRequestDispatcher("/WEB-INF/jsp/inscription.jsp").forward(request, response);
			}
		}
	}
	
	private Boolean checkInscription(String surname, String lastName, String firstName, String mail, String phone, String street, String zipCode, String city, String pwd, List<Integer> listErrorCode) throws AppException {
		Boolean check = false;
		UserManager usrMan = new ManagerFactory().getUserManager();
		
		try {
			check = usrMan.inscriptionVerification(surname, lastName, firstName, mail, phone, street, zipCode, city, pwd);
		} catch (AppException e) {
			e.printStackTrace();
			listErrorCode.add(CodesResultatServlet.CONNECTION_WRONG_CONNECTION);
		}
		
		return check;
	}

}
