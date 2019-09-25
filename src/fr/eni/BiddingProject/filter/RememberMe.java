package fr.eni.BiddingProject.filter;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class RememberMe
 */
@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD, 
				DispatcherType.INCLUDE, 
				DispatcherType.ERROR
		}
					, urlPatterns = { "/*" })

public class RememberMe implements Filter {

    /**
     * Default constructor. 
     */
    public RememberMe() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest rqt  = (HttpServletRequest) request;
		HttpServletResponse rep = (HttpServletResponse) response;
		
		String infos             = "";
		Cookie[] cookies         = rqt.getCookies();
		String[] connectionInfos = null;

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("rememberMe")) {
					infos = cookie.getValue();
				}
			}
		}

		if (!"".equals(infos))
			connectionInfos = infos.split("_");
		if (connectionInfos != null && connectionInfos.length > 0) {

			request.setAttribute("surname", connectionInfos[0]);
			request.setAttribute("email", connectionInfos[1]);
			request.setAttribute("password", connectionInfos[2]);
		}
		chain.doFilter(rqt, rep);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
