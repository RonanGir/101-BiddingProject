package fr.training.BiddingProject.dal;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import fr.training.BiddingProject.bo.User;

public class UserMOC  implements UserDAO{
	private List<User> listUser;
	private User roro;
	private User benito;
	
	
	public UserMOC() {
		// Create New Users
		roro     = new User(1, "benito", "Legoubin", "benoît", "benoit@campus-eni.fr", "0622222222", "rue lagarde", "44000", "Nantes","123456", 1000, true);
		benito   = new User(2, "roro", "Girault", "Ronan", "ronan@campus-eni.fr", "0633333333", "rue parallele", "44000", "Nantes","123456", 500, false);
		// Add users to list of users
		listUser = new ArrayList<>();
		listUser.add(roro);
		listUser.add(benito);
	}


	@Override
	public User selectUserBySurname(String input, String password) {

		return null;
	}



	@Override
	public User insertUser(String pseudo, String lastname, String firstname, String mail, String phone, String street,
			String zipcode, String city, String pwd, String nbPoints) {
	
		return null;
	}



	@Override
	public User selectUserById(int noUser) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void deleteUser(int noUser) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void updateUserCredit(int noUser, int credits, Connection cnx) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void updateUser(String pseudo, String lastname, String firstname, String mail, String phone, String street,
			String zipcode, String city, String pwd, int noUser) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public User selectUserById(int noUser, Connection cnx) {
		// TODO Auto-generated method stub
		return null;
	}





	
	
	
}
