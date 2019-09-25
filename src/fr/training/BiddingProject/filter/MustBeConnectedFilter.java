package fr.training.BiddingProject.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.training.BiddingProject.bo.User;

/**
 * Servlet Filter implementation class MustBeConnectedFilter
 */
@WebFilter(
		dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD, 
				DispatcherType.INCLUDE, 
				DispatcherType.ERROR
		}
					, 
		urlPatterns = { "/MustBeConnectedFilter",
						"/details-article",
						"/profil",
						"/deconnexion",
						"/profil/delete",
						"/vendre-un-article",
						"/modifier-article",
						
					}, 
		servletNames = { 
				"UpdateProfileServlet", 
				"DeconnectionServlet", 
				"GetArticleDetailsServlet", 
				"GetBidsServlet", 
				"GetProfileServlet", 
				"NewBidServlet"
		})
public class MustBeConnectedFilter implements Filter {
	public static final String ACCES_PUBLIC     = "/connexion";
    public static final String ATT_SESSION_USER = "currentUser";

	@Override
	public void init(FilterConfig arg0) throws ServletException {}
 

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		HttpServletRequest rqt  = (HttpServletRequest)request;
		HttpServletResponse rep = (HttpServletResponse)response;
		HttpSession session     = rqt.getSession();
		User user               = (User)session.getAttribute(ATT_SESSION_USER);
		
		if (user == null) {
			rqt.setAttribute("MustBeConnected", true);
			rqt.getRequestDispatcher("/WEB-INF/jsp/connection.jsp").forward(rqt, rep);
		} else {
			chain.doFilter(rqt, rep);
		}
		
	}

	
	@Override
	public void destroy() {}
	

}
