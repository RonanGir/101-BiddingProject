package fr.training.BiddingProject;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import fr.training.BiddingProject.bll.BidManager;
import fr.training.BiddingProject.bll.ManagerFactory;
import fr.training.BiddingProject.bo.Category;

/**
 * Application Lifecycle Listener implementation class Listener
 *
 */
@WebListener
public class Listener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public Listener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) { 
    	try {
    		List<Category> ListCategory = ManagerFactory.getBidManager().getAllCategory();
    		arg0.getServletContext().setAttribute("Category", ListCategory);
    	} catch (Exception e) {
    		
    	}	
    }
	
}
