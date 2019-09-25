package fr.training.BiddingProject.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.training.BiddingProject.AppException;
import fr.training.BiddingProject.bll.ManagerFactory;
import fr.training.BiddingProject.bo.SoldArticle;
import fr.training.BiddingProject.bo.User;

/**
 * Servlet implementation class UpdateArticleServlet
 */
@WebServlet("/modifier-article")
public class UpdateArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentArticleId       = 0;
		SoldArticle currentArticle = null; 
		currentArticleId           = Integer.parseInt(request.getParameter("id"));
		
		if (currentArticleId > 0)
			try {
				currentArticle = ManagerFactory.getBidManager().getArticleById(currentArticleId);
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		if (currentArticle != null) request.setAttribute("currentArticle", currentArticle);
		
		request.getRequestDispatcher("WEB-INF/jsp/updateArticle.jsp").forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bidStartPrice=0, noCategory=0, currentArticleId=0, noRet=0;
		String name=null, retStreet=null, retZipCode=null, retCity=null, desc=null;
		Date startDate=null, endDate=null;
	
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		HttpSession session  = request.getSession();
		currentArticleId     = Integer.parseInt(request.getParameter("id"));
		User seller          = (User)session.getAttribute("currentUser");
	
	    try {
			if (!"".equals(request.getParameter("bidEndDate"))) endDate     = sdf.parse(request.getParameter("bidEndDate").replace("T", " "));
			if (!"".equals(request.getParameter("bidStartDate"))) startDate = sdf.parse(request.getParameter("bidStartDate").replace("T", " "));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	 
	    if (!"".equals(request.getParameter("retStreet"))) retStreet                   = request.getParameter("retStreet");
	    if (!"".equals(request.getParameter("retZipCode"))) retZipCode                 = request.getParameter("retZipCode");
	    if (!"".equals(request.getParameter("retCity"))) retCity                       = request.getParameter("retCity");
		if (!"".equals(request.getParameter("articleName"))) name                      = request.getParameter("articleName");
		if (!"".equals(request.getParameter("description"))) desc                      = request.getParameter("description");
		if (!"".equals(request.getParameter("bidStartPrice"))) bidStartPrice           = Integer.parseInt(request.getParameter("bidStartPrice"));
		if (Integer.parseInt(request.getParameter("noCategory")) > 0) noCategory       = Integer.parseInt(request.getParameter("noCategory"));
		if (Integer.parseInt(request.getParameter("noRet")) > 0) noRet                 = Integer.parseInt(request.getParameter("noRet"));
		
		
		ManagerFactory.getBidManager().updateArticle(currentArticleId, seller.getNoUser(), name, desc, startDate, endDate, bidStartPrice,
				noCategory, noRet, retStreet, retZipCode, retCity);
		
		
		response.sendRedirect(request.getContextPath() + "/details-article?id=" + currentArticleId);
	}

}
