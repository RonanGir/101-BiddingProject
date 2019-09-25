package fr.eni.BiddingProject.bll;

public class ManagerFactory {
	public static UserManager getUserManager() {
		return new UserManagerBase();
	}
	
	public static BidManager getBidManager() {
		return new BidManagerBase();
	}
}
