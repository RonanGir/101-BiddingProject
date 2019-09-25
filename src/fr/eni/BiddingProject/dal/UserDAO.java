package fr.eni.BiddingProject.dal;

import java.sql.Connection;

import fr.eni.BiddingProject.AppException;
import fr.eni.BiddingProject.bo.User;

public interface UserDAO {
	
	public User selectUserBySurname(String input, String password) throws AppException;
	
	public User selectUserById(int noUser);
	
	public User selectUserById(int noUser, Connection cnx);
	
	public User insertUser(String pseudo, String lastname, String firstname, String mail, String phone, String street, String zipcode, String city, String pwd, String nbPoints) throws AppException;

	public void updateUser(String pseudo, String lastname, String firstname, String mail, String phone, String street, String zipcode, String city, String pwd, int noUser) throws AppException;
	
	public void updateUserCredit(int noUser, int credits, Connection cnx) throws AppException;
	
	public void deleteUser(int noUser);
}
