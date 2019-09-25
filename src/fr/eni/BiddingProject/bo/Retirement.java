package fr.eni.BiddingProject.bo;

public class Retirement {

	/**
	 * @author Benoit
	 */

	// Attributs
	private int noRetirement;
	private String street;
	private String zipCode;
	private String city;

	// Constructors
	public Retirement() {
	}

	public Retirement(int noRetirement, String street, String zipCode, String city) {
		this.noRetirement = noRetirement;
		this.street       = street;
		this.zipCode      = zipCode;
		this.city         = city;
	}

	// Getters and Setters
	
	public int getNoRetirement() {
		return noRetirement;
	}
	
	public void setNoRetirement(int noRetirement) {
		this.noRetirement = noRetirement;
	}
	
	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	// To String
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(street).append(" ").append(zipCode).append(" ").append(city);
		return sb.toString();
	}

}
