package fr.training.BiddingProject.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SoldArticle {
	
		// Atttributes
		private int noArticle;
		private int noUser;
		private String articleName;
		private String description;
		private Date bidStartedDate;
		private Date bidEndDate;
		private int bidStartPrice;
		private int soldPrice;
		private boolean soldState;
		List<Bid> ListBidArticle;
		private int noCategory;
		private User seller;
		private Retirement ret;
		private Category cat;
		private boolean archive = false;

		// Constructors
		/**
		 *
		 * @author: Ronan
		 */
		public SoldArticle() {
			this(0, 0, null, null, null, null, 0, 0, false, 0, null, null);
		}

		/**
		 *
		 * @author: Ronan
		 */
		public SoldArticle(int noArticle, int noUser, String articleName, String description, Date bidStartedDate, Date bidEndDate,
				int bidStartPrice, int soldPrice, boolean soldState, int noCategory, Retirement ret, Category cat) {
			this.noArticle      = noArticle;
			this.noUser         = noUser;
			this.articleName    = articleName;
			this.description    = description;
			this.bidStartedDate = bidStartedDate;
			this.bidEndDate     = bidEndDate;
			this.bidStartPrice  = bidStartPrice;
			this.soldPrice      = soldPrice;
			this.soldState      = soldState;
			this.noCategory     = noCategory;
			ListBidArticle      = new ArrayList<>();
		}
		// Getters / Setters

		public int getNoArticle() {
			return noArticle;
		}

		public void setNoArticle(int noArticle) {
			this.noArticle = noArticle;
		}

		public String getArticleName() {
			return articleName;
		}

		public void setArticleName(String articleName) {
			this.articleName = articleName;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Date getBidStartedDate() {
			return bidStartedDate;
		}

		public void setBidStartedDate(Date bidStartedDate) {
			this.bidStartedDate = bidStartedDate;
		}

		public Date getBidEndDate() {
			return bidEndDate;
		}

		public void setBidEndDate(Date bidEndDate) {
			this.bidEndDate = bidEndDate;
		}

		public int getBidStartPrice() {
			return bidStartPrice;
		}

		public void setBidStartPrice(int bidStartPrice) {
			this.bidStartPrice = bidStartPrice;
		}

		public int getSoldPrice() {
			return soldPrice;
		}

		public void setSoldPrice(int soldPrice) {
			this.soldPrice = soldPrice;
		}

		public boolean getSoldState() {
			return soldState;
		}

		public void setSoldState(boolean soldState) {
			this.soldState = soldState;
		}
	

		public int getNoCategory() {
			return noCategory;
		}

		public void setNoCategory(int noCategory) {
			this.noCategory = noCategory;
		}
		

		public int getNoUser() {
			return noUser;
		}

		public void setNoUser(int noUser) {
			this.noUser = noUser;
		}

		public User getSeller() {
			return seller;
		}
		
		public void setSeller(User seller) {
			this.seller = seller;
		}
		
		public Retirement getRet() {
			return ret;
		}

		public void setRet(Retirement ret) {
			this.ret = ret;
		}
		
		public Category getCat() {
			return cat;
		}

		public void setCat(Category cat) {
			this.cat = cat;
		}

		public boolean isArchive() {
			return archive;
		}

		public void setArchive(boolean archive) {
			this.archive = archive;
		}

		/**
		 * The method getListBidArticle : 
		 * @return the list of bids by Article from users / buyers.
		 */
		
		public List<Bid> getListBidArticle() {
			return ListBidArticle;
		}

		public void setListBidArticle(List<Bid> listBidArticle) {
			ListBidArticle = listBidArticle;
		}

		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append("Article \n \t noArticle:").append(noArticle).append("\n");
			sb.append("\t  Nom: ").append(noArticle).append("\n");
			sb.append("\t  Description: ").append(description).append("\n");
			sb.append("\t  Début de l'enchère: ").append(bidStartedDate).append("\n");
			sb.append("\t  Fin de l'enchère: ").append(bidEndDate).append("\n");
			sb.append("\t  Mise à Prix: ").append(bidStartPrice).append("\n");
			sb.append("\t  Prix de vente: ").append(soldPrice).append("\n");
			sb.append("\t  Etat de la vente: ").append(soldState).append("\n");
			sb.append("\t  Liste des enchères / user : ").append(ListBidArticle).append("\n");
			sb.append("\t  id catégorie : ").append(noCategory).append("\n");
			
			return sb.toString();
		}

		
		
	
		
}
