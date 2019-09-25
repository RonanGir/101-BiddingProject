package fr.training.BiddingProject.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.training.BiddingProject.AppException;
import fr.training.BiddingProject.bll.ManagerFactory;
import fr.training.BiddingProject.bo.User;

/**
 * Servlet implementation class sellArticleServlet
 */
@WebServlet("/vendre-un-article")
public class SellArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @author rgirault2019
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setHeader("Expires", "0");
		request.getRequestDispatcher("/WEB-INF/jsp/sellArticle.jsp").forward(request,response);
	}

	/**
	 * @author rgirault2019
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String retStreet=null, retZipCode=null, retCity=null;
		List<Integer> listErrorCode = new ArrayList<>();
		
		Date bidEnd      = checkEndDate(request, listErrorCode);
		Date bidStart    = checkStartDate(request, listErrorCode);
		String name      = checkArticleName(request, listErrorCode);
		int bidInitPrice = checkArticleStartPrice(request, listErrorCode);
		int noCategory   = checkArticleCategory(request, listErrorCode);
		boolean archive  = false;
	 
	    if (!"".equals(request.getParameter("retStreet"))) retStreet        = request.getParameter("retStreet");
	    if (!"".equals(request.getParameter("retZipCode"))) retZipCode      = request.getParameter("retZipCode");
	    if (!"".equals(request.getParameter("retCity"))) retCity            = request.getParameter("retCity");

		HttpSession session = request.getSession();
		User seller         = (User)session.getAttribute("currentUser");
				
		// TODO : Enlever l'article retourné par la méthode createArticle
		if (listErrorCode.size() > 0) {
			request.setAttribute("listErrorCode", listErrorCode);
			request.getRequestDispatcher("/WEB-INF/jsp/sellArticle.jsp").forward(request, response);
		} else {
			try {
				ManagerFactory.getBidManager().createArticle(name, request.getParameter("description").trim(), bidStart, bidEnd, bidInitPrice, 0, seller.getNoUser(), noCategory, retStreet, retZipCode, retCity, archive);
				response.sendRedirect(request.getContextPath() + "/index");
			} catch (AppException ae) {
				ae.printStackTrace();
				request.setAttribute("listErrorCode", ae.getListeCodesErreur());
			
				request.getRequestDispatcher("/WEB-INF/jsp/sellArticle.jsp").forward(request, response);
			}
		}
		
	}
	
	
	private Date checkEndDate(HttpServletRequest request, List<Integer> listErrorCode) {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		try {
			if (!"".equals(request.getParameter("bidEndDate"))) date     = sdf.parse(request.getParameter("bidEndDate").replace("T", " "));
		} catch (Exception e) {
			e.printStackTrace();
			listErrorCode.add(CodesResultatServlet.SELL_ARTICLE_WRONG_END_DATE);
		}
		
		return date;
	}
	
	private Date checkStartDate(HttpServletRequest request, List<Integer> listErrorCode) {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		try {
			if (!"".equals(request.getParameter("bidStartDate"))) date = sdf.parse(request.getParameter("bidStartDate").replace("T", " "));			
		} catch (Exception e) {
			e.printStackTrace();
			listErrorCode.add(CodesResultatServlet.SELL_ARTICLE_WRONG_START_DATE);
		}
		return date;
	}
	
	private String checkArticleName(HttpServletRequest request, List<Integer> listErrorCode) {
		String name = "";
		
		if (!"".equals(request.getParameter("articleName"))) {
			name = request.getParameter("articleName");
		} else {
			listErrorCode.add(CodesResultatServlet.SELL_ARTICLE_WRONG_NAME);
		}
		return name;
	}
	
	private int checkArticleStartPrice(HttpServletRequest request, List<Integer> listErrorCode) {
		int price = 0;
		
		if (!"".equals(request.getParameter("bidStartPrice")) && Integer.parseInt(request.getParameter("bidStartPrice")) > 0) {
			price = Integer.parseInt(request.getParameter("bidStartPrice"));
		} else {
			listErrorCode.add(CodesResultatServlet.SELL_ARTICLE_WRONG_PRICE);
		}
		return price;
	}
	
	private int checkArticleCategory(HttpServletRequest request, List<Integer> listErrorCode) {
		int category = 0;
		if (!request.getParameter("noCategory").equals("0")) {
			category = Integer.parseInt(request.getParameter("noCategory"));
		} else {
			System.out.println("check cate - passage dans else");
			listErrorCode.add(CodesResultatServlet.SELL_ARTICLE_WRONG_CATEGORY);
		}
		return category;
	}

}
