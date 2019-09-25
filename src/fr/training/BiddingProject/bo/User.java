package fr.training.BiddingProject.bo;

import java.util.ArrayList;
import java.util.List;

public class User {

	/**
	 * @author Benoit
	 */

	// Atrributes
	private int noUser;
	private String surname;
	private String lastName;
	private String firstName;
	private String email;
	private String phone;
	private String street;
	private String zipCode;
	private String city;
	private String password;
	private int credit;
	private boolean loggedIn;
	private boolean administrator;
	private List<SoldArticle> listArticle;
	private List<Bid> listBidUser;

	// Constructors
	public User() {
		listArticle = new ArrayList<>();
		listBidUser = new ArrayList<>();
		this.setLoggedIn(false);
	}

	public User(int noUser, String surname, String lastName, String firstName, String email, String phone,
			String street, String zipCode, String city, String password, int credit, boolean administrator) {
		super();
		this.noUser = noUser;
		this.surname = surname;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.phone = phone;
		this.street = street;
		this.zipCode = zipCode;
		this.city = city;
		this.password = password;
		this.credit = credit;
		this.administrator = administrator;

		listArticle = new ArrayList<>();
		listBidUser = new ArrayList<>();
		this.setLoggedIn(false);
	}

	// Getters and Setters
	public int getNoUser() {
		return noUser;
	}

	public void setNoUser(int noUser) {
		this.noUser = noUser;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public boolean isAdministrator() {
		return administrator;
	}

	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}

	public List<SoldArticle> getListArticle() {
		return listArticle;
	}

	public void setListArticle(List<SoldArticle> listArticle) {
		this.listArticle = listArticle;
	}
	
	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	/**
	 * 
	 * @return the list of bids of Users/Bidders
	 */

	public List<Bid> getListBidUser() {
		return listBidUser;
	}

	public void setListBid(List<Bid> listBidUser) {
		this.listBidUser = listBidUser;
	}

	
	// To String
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Utilisateurs \n \t noUtilisateur = ");
		sb.append(noUser);
		sb.append("\n \t Pseudo = ");
		sb.append(surname);
		sb.append("\n \t Nom = ");
		sb.append(lastName);
		sb.append("\n \t Prénom = ");
		sb.append(firstName);
		sb.append("\n \t Email = ");
		sb.append(email);
		sb.append("\n \t Téléphone = ");
		sb.append(phone);
		sb.append("\n \t Rue = ");
		sb.append(street);
		sb.append("\n \t Code Postal = ");
		sb.append(zipCode);
		sb.append("\n \t Ville = ");
		sb.append(city);
		sb.append("\n \t Mot de Passe = ");
		sb.append(password);
		sb.append("\n \t Crédit = ");
		sb.append(credit);
		sb.append("\n \t Administrateur = ");
		sb.append(administrator);
		sb.append("\n \t listArticle = ");
		sb.append(listArticle);
		sb.append("\n \t listBidUser = ");
		sb.append(listBidUser);
		return sb.toString();
	}

}
