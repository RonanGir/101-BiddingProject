package fr.training.BiddingProject.bll;

import java.sql.Connection;

import fr.training.BiddingProject.AppException;
import fr.training.BiddingProject.bo.User;

public interface UserManager {
	
	public User getUserBySurname(String input, String password) throws AppException;
	
	public User getUserById(int noUser);
	
	public User getUserById(int noUser, Connection cnx);
	
	public User insertUser(String pseudo, String lastname, String firstname, String mail, String phone, String street, String zipcode, String city, String pwd, String nbPoints) throws AppException;
	
	public void updateUser(String pseudo, String lastname, String firstname, String mail, String phone, String street, String zipcode, String city, String pwd, int noUser) throws AppException;
	
	public void updateUserCredit(int noUser, int credits, Connection cnx) throws AppException;
	
	public void deleteUser(int noUser);
	
	public boolean UserLoggedIn(User current) throws AppException;

	public Boolean checkId (String identifier) throws AppException;

	public Boolean checkPwd (String pwd) throws AppException;
	
	public Boolean inscriptionVerification (String pseudo, String lastname, String firstname, String mail, String phone, String street, String zipcode, String city, String pwd) throws AppException;
	
	public Boolean checkSearch (String input);

}

