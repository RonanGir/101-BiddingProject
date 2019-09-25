package fr.training.BiddingProject.dal;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import fr.training.BiddingProject.AppException;
import fr.training.BiddingProject.bo.Category;
import fr.training.BiddingProject.bo.Retirement;
import fr.training.BiddingProject.bo.SoldArticle;
import fr.training.BiddingProject.bo.User;

public interface BidDAO {

	/**
	 * @author rgirault2019
	 * @return the list of all article whom have opened bids
	 */
	public List<SoldArticle> selectAllBidsStarted() throws AppException;

	/**
	 * @author rgirault2019
	 * @return the list of all article whom have opened bids
	 */
	public List<SoldArticle> selectAllBidsStarted(Connection cnx) throws AppException;

	/**
	 * @author rgirault2019
	 * 
	 */
	public List<SoldArticle> selectAllSoldBids(Connection cnx) throws AppException;

	/**
	 * @author rgirault2019
	 * @return the created article
	 */

	public List<Category> selectAllCategory() throws AppException;

	public void insertArticle(String articleName, String description, Date bidStartedDate, Date bidEndDate,
			int bidStartPrice, int soldPrice, int noUser, int noCategory, String retStreet, String retZipCode,
			String retCity, boolean soldState, boolean archive) throws AppException;

	/**
	 * @author blegoubi2019
	 * @return
	 */
	public List<SoldArticle> filterArticleBySearch(String input) throws AppException;

	/**
	 * @author blegoubi2019
	 * @return
	 */
	public List<SoldArticle> selectArticleByCategory(int noCategory) throws AppException;

	/**
	 * @author blegoubi2019
	 * @return
	 */
	public List<SoldArticle> filterArticleByCategoryAndArticleName(String input, int noCategory) throws AppException;

	/**
	 * @author rgirault2019
	 */
	public List<SoldArticle> filterArticleByWonBids(int noUser) throws AppException;

	/**
	 * @author blegoubi2019
	 */
	public List<SoldArticle> filterArticleByAchats(String input, int noCategory, String openBids, int noUser, String winBids) throws AppException;

	/**
	 * @author blegoubi2019
	 */
	public List<SoldArticle> filterArticleBySales(String input, int noCategory, int noUser, String pending, String end) throws AppException;

	/**
	 * @author rgirault2019 the method select an article by his id into database
	 */
	public SoldArticle selectArticleById(int noArticle) throws AppException;

	/**
	 * @author rgirault2019
	 */
	public void addNewBid(int amount, int noArticle, User bider, Connection cnx) throws AppException;

	/**
	 * @author rgirault2019
	 */

	public void updateArticle(int noArticle, int seller, String articleName, String description, Date bidStartedDate, Date bidEndDate,
			int bidStartPrice, int noCategory, int noRet, String retStreet, String retZipCode,
			String retCity);
	
	public void updateArticleArchive(int noArticle, boolean archive, Connection cnx);
	
	/**
	 * @author rgirault2019
	 * @param noArticle
	 */
	public void deleteArticle(int noArticle);

}
