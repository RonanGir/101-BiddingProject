package fr.training.BiddingProject.bll;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.training.BiddingProject.AppException;
import fr.training.BiddingProject.bo.Bid;
import fr.training.BiddingProject.bo.Category;
import fr.training.BiddingProject.bo.SoldArticle;
import fr.training.BiddingProject.bo.User;
import fr.training.BiddingProject.dal.ConnectionProvider;
import fr.training.BiddingProject.dal.DAOFactory;

public class BidManagerBase implements BidManager {


    @Override
    public List<SoldArticle> getArticleBidsInProgress() {

    	List<SoldArticle> allbidsStarted = null;
    	List<SoldArticle> allSoldArticle = null;
    	User seller                      = null;
        
        try (Connection cnx = ConnectionProvider.getConnection()){
            
            try {
                cnx.setAutoCommit(false);
                
                
                // Go get all opened bids in the database
                try {
                    allbidsStarted = DAOFactory.getBidDAO().selectAllBidsStarted(cnx);
                    
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                
             // Go get all recent sold article in the database
                try {
                    
                    allSoldArticle = DAOFactory.getBidDAO().selectAllSoldBids(cnx);
       
                    if (allSoldArticle != null) {
                        
                        int max    = 0;
                        int credit = 0;
                        int noUser = 0;
                        
                        for (SoldArticle sa : allSoldArticle) {
                            
                            for (Bid b : sa.getListBidArticle()) {
                            	
                                if (b.getBidAmount() > max) {
                                	max    = b.getBidAmount();
                                }
                                
                                noUser = sa.getNoUser();
                            }
                            
                            // set sold articles status archive to true  
                            sa.setArchive(true);
                            
                            // update them in the database 
                            DAOFactory.getBidDAO().updateArticleArchive(sa.getNoArticle(),sa.isArchive(),cnx);
                            											
  
                        }
                        
                        // credit the seller of the best bid amount of the recent sold article : 
                        
                        // get the sold article's seller
                        if(noUser > 0) seller =  ManagerFactory.getUserManager().getUserById(noUser, cnx);
                        
                        if (seller != null && max > 0) {
                    
                        	seller.setCredit(seller.getCredit() + max);
                        	credit = seller.getCredit();
                    
                        }
                        // update his credit amount in the database
                        if (credit > 0) ManagerFactory.getUserManager().updateUserCredit(seller.getNoUser(), credit, cnx);
                }
                    
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                
                
                cnx.commit();
            } catch (Exception e3) {
                cnx.rollback();
            }
            
            cnx.setAutoCommit(true);
        } catch (Exception e) {
            
        }
        return allbidsStarted;
    }
    
    

	@Override
	public void createArticle(String articleName, String description, Date bidStartedDate, Date bidEndDate,
			int bidStartPrice, int soldPrice, int noUser, int noCategory, String retStreet, String retZipCode,
			String retCity, boolean archive) throws AppException {
		String artName = null, desc = null;
		Date startDate = null, endDate = null;
		
		int noCat         = 0;
		boolean soldState = false;
		
		AppException ae = new AppException();
		
		if (!ae.hasErreurs()) {
			try {
				
	
				// check articleName != null and less than 31 chars
				if (articleName != null && !"".equals(articleName) && articleName.length() <= 30)
					artName = articleName;
				// check description != null and less than 301 chars
				if (description != null && articleName.length() <= 300)
					desc = description;
				// check !bidStartedDate < today
				if (bidStartedDate != null && bidStartedDate.getTime() > new Date().getTime())
					startDate = bidStartedDate;
				// check !bidEndDate < today && bidEndDate > bidStartedDate
				if (bidEndDate != null && bidEndDate.getTime() >= startDate.getTime())
					endDate = bidEndDate;
				// check noCategory > 0
				if (noCategory > 0)
					noCat = noCategory;
	
				DAOFactory.getBidDAO().insertArticle(artName, desc, startDate, endDate, bidStartPrice, soldPrice, noUser,
						noCat, retStreet, retZipCode, retCity, soldState, archive);
	
			} catch (AppException e) {
				
				throw e;
			}
		} else {
		
			throw ae;
		}

	}
	
	
	/**
	 * return all categories present in the database
	 */
	@Override
	public List<Category> getAllCategory() throws AppException {

		return DAOFactory.getBidDAO().selectAllCategory();

	}
	
	

	@Override
	public List<SoldArticle> getListArticleByCategory(String input, int noCategory) throws AppException {
		List<SoldArticle> listArticle = new ArrayList<>();
		AppException ae = new AppException();

		// Vérification si les champs sont null ou égaux à 0
		if (!ae.hasErreurs()) {
			// Si catégory = Toutes et le champs de recherche différent de null
			if (noCategory == 0 && input.equals("")) {
				listArticle = DAOFactory.getBidDAO().filterArticleBySearch(input);
			// Si une catégorie est sélectionnée et champs de recherche vide
			} else if (noCategory > 0 && input.equals("")) {
				listArticle = DAOFactory.getBidDAO().selectArticleByCategory(noCategory);
			// Si une catégorie est seléctionnée et champs de recherche rempli
			} else if (noCategory > 0 && input.equals("")) {
				listArticle = DAOFactory.getBidDAO().filterArticleByCategoryAndArticleName(input, noCategory);
			// Si champs de recherche vide et catégorie = Toutes
			} else {
				listArticle = DAOFactory.getBidDAO().selectAllBidsStarted();
			}
		} else {
			throw ae;
		}

		return listArticle;
	}

	@Override
	public List<SoldArticle> getListArticleByAchats(String input, int noCategory, String openBids, int noUser, String winBids) throws AppException {
		
		List<SoldArticle> listArticle = new ArrayList<>();
		AppException ae               = new AppException();

		try {
			listArticle = DAOFactory.getBidDAO().filterArticleByAchats(input, noCategory, openBids, noUser, winBids);
		} catch (Exception e) {
			throw ae;
		}

		return listArticle;
	}
	
	

	@Override
	public List<SoldArticle> getListArticleBySales(String input, int noCategory, int noUser, String pending, String end) throws AppException {
		
		List<SoldArticle> listArticle = new ArrayList<>();
		AppException appError         = new AppException();
		
		try {
			listArticle = DAOFactory.getBidDAO().filterArticleBySales(input, noCategory, noUser, pending, end);
		} catch (Exception e) {
			throw appError;
		}

		return listArticle;
	}
	
	

	/**
	 * @author rgirault2019
	 */
	@Override
	public List<SoldArticle> getListArticleByWonBids(int noUser) throws AppException {
		List<SoldArticle> listArticle = new ArrayList<>();
		
		listArticle = DAOFactory.getBidDAO().filterArticleByWonBids(noUser);
		
		return listArticle;
	}
	
	
	
	
	/**
	 * @author rgirault2019
	 */
	
	@Override
	public SoldArticle getArticleById(int noArticle) throws AppException {
		SoldArticle selectedArticle = null;

		if (noArticle != 0) selectedArticle = DAOFactory.getBidDAO().selectArticleById(noArticle);

		return selectedArticle;
	}
	
	
	/**
	 * @author rgirault2019
	 */
	
	@Override
	public void createNewBid(int amount, int noArticle, User bider, User oldUserBestBider, int oldBestBid,
			int bestBidAmount, int bidStartPrice) throws AppException {
		AppException ae = new AppException();
		
		if (!ae.hasErreurs()) {
			try (Connection cnx = ConnectionProvider.getConnection()) {
				try {
					cnx.setAutoCommit(false);
					// case first bid : check if oldUserBestBider doesn't exist AND current bider (bider) is a connected user
					if  (oldUserBestBider == null && bider.getNoUser() > 0 && bestBidAmount == 0 && amount > bidStartPrice) {
						// Next update credit amount of the current bider with his new bid
						bider.setCredit(bider.getCredit() - amount);
						// call the Manager to update the new credit amout in the database for the
						// current bider
						ManagerFactory.getUserManager().updateUserCredit(bider.getNoUser(), bider.getCredit(), cnx);
					}
	
					// case not the first bid : check if oldUserBestBider is not null AND he is
					// different than bider(the current bider)
					if (oldUserBestBider != null && oldUserBestBider.getNoUser() != bider.getNoUser()
							&& (bestBidAmount > 0 && amount > bestBidAmount)) {
						// Next update credit amount of the oldUserBestBider with his bid amount
						// (oldBestBid)
						oldUserBestBider.setCredit(oldUserBestBider.getCredit() + oldBestBid);
						// Next update credit amount of the current bider with his new bid
	
						bider.setCredit(bider.getCredit() - amount);
						// call the Manager to update the new credit amout in the database for the
						// current bider and the oldUserBestBider one
						ManagerFactory.getUserManager().updateUserCredit(oldUserBestBider.getNoUser(),oldUserBestBider.getCredit(), cnx);
						
						ManagerFactory.getUserManager().updateUserCredit(bider.getNoUser(), bider.getCredit(), cnx);
					}
	
					
					if (bider != null && amount > 0 && noArticle > 0) 
						DAOFactory.getBidDAO().addNewBid(amount, noArticle, bider, cnx);
					
					cnx.commit();
					
				} catch (Exception e1) {
					e1.printStackTrace();
					cnx.rollback();
				}
	
			} catch (Exception e2) {
				e2.printStackTrace();
			}
	
			} else {
				throw ae;
			}
	}
	
	
	/**
	 * @author rgirault2019
	 */
	@Override
	public void updateArticle(int noArticle, int seller, String articleName, String description, Date bidStartedDate, Date bidEndDate, 
			int bidStartPrice, int noCategory, int noRet, String retStreet, String retZipCode, String retCity) {

		String artName = null, desc = null;
		Date startDate = null, endDate = null;
		int noCat = 0;
		

		try {

			// check articleName != null and less than 31 chars
			if (articleName != null && !"".equals(articleName) && articleName.length() <= 30)
				artName = articleName;
			// check description != null and less than 301 chars
			if (description != null && articleName.length() <= 300)
				desc = description;
			// check !bidStartedDate < today
			if (bidStartedDate != null && bidStartedDate.getTime() > new Date().getTime())
				startDate = bidStartedDate;
			// check !bidEndDate < today && bidEndDate > bidStartedDate
			if (bidEndDate != null && startDate != null && bidEndDate.getTime() > startDate.getTime())
				endDate = bidEndDate;
			// check noCategory > 0
			if (noCategory > 0)
				noCat = noCategory;
			

			DAOFactory.getBidDAO().updateArticle(noArticle ,seller, artName, desc, startDate, endDate, bidStartPrice,
					noCat, noRet, retStreet, retZipCode, retCity);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	/**
	 * @author rgirault2019
	 */
	@Override
	public void deleteArticleById(int noArticle) {
		if (noArticle > 0)
			DAOFactory.getBidDAO().deleteArticle(noArticle);
		
	}


}
