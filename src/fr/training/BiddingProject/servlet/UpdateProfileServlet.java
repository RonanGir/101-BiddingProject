package fr.training.BiddingProject.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.training.BiddingProject.bll.ManagerFactory;
import fr.training.BiddingProject.bll.UserManager;
import fr.training.BiddingProject.bo.User;

/**
 * Servlet implementation class UpdateProfileServlet
 */
@WebServlet("/profil/modifier")
public class UpdateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @author rgirault2019
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/updateProfile.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String surname =null, lastname =null, firstname =null, mail =null, phone =null, street =null, zipcode =null, city =null, pwd =null, pwdverif =null;
	
		HttpSession session = request.getSession();
		User cUser          = (User)session.getAttribute("currentUser");
		
		surname   = !"".equals(request.getParameter("pseudo")) && !request.getParameter("pseudo").equals(cUser.getSurname()) ? request.getParameter("pseudo") : cUser.getSurname();
		lastname  = !"".equals(request.getParameter("lastname")) && !request.getParameter("lastname").equals(cUser.getLastName()) ? request.getParameter("lastname") : cUser.getLastName();
		firstname = !"".equals(request.getParameter("firstname")) && !request.getParameter("firstname").equals(cUser.getFirstName()) ? request.getParameter("firstname") : cUser.getFirstName();
		mail      = !"".equals(request.getParameter("mail")) && !request.getParameter("mail").equals(cUser.getEmail()) ? request.getParameter("mail") : cUser.getEmail();
		phone     = !"".equals(request.getParameter("phone")) && !request.getParameter("phone").equals(cUser.getPhone()) ? request.getParameter("phone") : cUser.getPhone();
		street    = !"".equals(request.getParameter("street")) && !request.getParameter("street").equals(cUser.getStreet()) ? request.getParameter("street") : cUser.getStreet();
		zipcode   = !"".equals(request.getParameter("zipcode")) && !request.getParameter("zipcode").equals(cUser.getZipCode()) ? request.getParameter("zipcode") : cUser.getZipCode();
		city      = !"".equals(request.getParameter("city")) && !request.getParameter("city").equals(cUser.getCity()) ? request.getParameter("city") : cUser.getCity();
		pwd       = !"".equals(request.getParameter("pwd")) && !request.getParameter("pwd").equals(cUser.getPassword()) ? request.getParameter("pwd") : cUser.getPassword();
		pwdverif  = !"".equals(request.getParameter("pwdverif")) && !request.getParameter("pwdverif").equals(cUser.getPassword()) ? request.getParameter("pwdverif") : cUser.getPassword();
		
		try {
			int cNoUser = cUser.getNoUser();
			if (pwd.equals(pwdverif)) ManagerFactory.getUserManager().updateUser(surname, lastname, firstname, mail, phone, street, zipcode, city, pwd, cNoUser);
		} catch (Exception e) {
			System.out.println("merci de compléter les mots de passe");
		}
	
		response.sendRedirect(request.getContextPath() + "/profil");
	}

}
