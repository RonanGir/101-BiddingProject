package fr.training.BiddingProject.bll;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import fr.training.BiddingProject.AppException;
import fr.training.BiddingProject.bo.Category;
import fr.training.BiddingProject.bo.SoldArticle;
import fr.training.BiddingProject.bo.User;

public interface BidManager {

	/**
	 * @author rgirault2019
	 * @return the list of all article whom have opened bids
	 */
	public List<SoldArticle> getArticleBidsInProgress() throws AppException;

	/**
	 * The method createArticle call the inset method into DAL
	 * 
	 * @author rgirault2019
	 * @return article just created
	 */

	public List<Category> getAllCategory() throws AppException;

	/**
	 * The method createArticle call the BidDAO whom insert the current article in
	 * BDD
	 * 
	 * @author rgirault2019
	 */
	public void createArticle(String articleName, String description, Date bidStartedDate, Date bidEndDate,
			int bidStartPrice, int soldPrice, int noUser, int noCategory, String retStreet, String retZipCode,
			String retCity, boolean archive) throws AppException;

	/**
	 * @author blegoubi2019
	 * @return Article List by Category
	 * @throws AppException
	 */
	public List<SoldArticle> getListArticleByCategory(String input, int noCategory) throws AppException;

	/**
	 * @author blegoubi2019
	 * @return Article List by Achats
	 * @throws AppException
	 */
	public List<SoldArticle> getListArticleByAchats(String input, int noCategory, String openBids, int noUser, String winBids) throws AppException;

	/**
	 * @author blegoubi2019
	 * @return Article List By Sales
	 * @throws AppException
	 */
	public List<SoldArticle> getListArticleBySales(String input, int noCategory, int noUser, String pending,
			String end) throws AppException;
	
	/**
	 * @author rgirault2019
	 */
	public List<SoldArticle> getListArticleByWonBids(int noUser) throws AppException;
	

	/**
	 * @author rgirault2019
	 * @return the selected article by Id
	 */
	public SoldArticle getArticleById(int noArticle) throws AppException;

	/**
	 * @author rgirault2019
	 * @return the selected article by Id
	 */
	public void createNewBid(int amount, int noArticle, User user, User oldUserBestBider, int oldBestBid, int bestBidAmount, int bidStartPrice) throws AppException;
	
	/**
	 * @author rgirault2019
	 */
	public void updateArticle(int noArticle, int seller, String articleName, String description, Date bidStartedDate, Date bidEndDate, 
			int bidStartPrice, int noCategory, int noRet, String retStreet, String retZipCode, String retCity); // throws AppException
	
	
	public void deleteArticleById(int noArticle);


}
