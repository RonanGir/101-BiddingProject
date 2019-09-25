package fr.training.BiddingProject.bo;

import java.util.Date;

public class Bid {
	private int noBid;
	private Date bidDate;
	private int bidAmount;
	private int noUser;
	private int noArticle;
	
	
	public Bid() {
		this(0, null, 0, 0, 0);
	}

	public Bid(int noBid, Date bidDate, int bidAmount, int noUser, int noArticle) {
		this.noBid        = noBid;
		this.bidDate      = bidDate;
		this.bidAmount    = bidAmount;
		this.noUser       = noUser;
		this.noArticle    = noArticle;
	}
	

	public int getNoBid() {
		return noBid;
	}

	public void setNoBid(int noBid) {
		this.noBid = noBid;
	}

	public Date getBidDate() {
		return bidDate;
	}

	public void setBidDate(Date bidDate) {
		this.bidDate = bidDate;
	}

	public int getBidAmount() {
		return bidAmount;
	}

	public void setBidAmount(int bidAmount) {
		this.bidAmount = bidAmount;
	}

	public int getNoUser() {
		return noUser;
	}

	public void setNoUser(int noUser) {
		this.noUser = noUser;
	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Enchère : ").append(noBid).append("par id user => ").append(noUser).append(" pour l'article n°").append(noArticle).append("\n");
		sb.append(" \t Date de l'enchere : ").append(bidDate).append("\n");
		sb.append(" \t Montant de l'enchere : ").append(bidAmount).append("\n");
		
		return sb.toString();
	}
	
	
	
}
