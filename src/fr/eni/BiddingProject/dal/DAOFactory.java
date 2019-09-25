package fr.eni.BiddingProject.dal;

public class DAOFactory {
	public static UserDAO getUserDAO() {
		return new UserDAOJdbcImpl();
		
	}
	
	public static BidDAO getBidDAO() {
		return new BidDAOJdbcImpl();
	
	}
}
