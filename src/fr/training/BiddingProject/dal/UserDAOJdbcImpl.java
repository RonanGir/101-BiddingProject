package fr.training.BiddingProject.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.training.BiddingProject.AppException;
import fr.training.BiddingProject.bo.User;

public class UserDAOJdbcImpl implements UserDAO {
	private final String SELECT_ALL_USER = "select * from users;";
	private final String SELECT_USER_BY_SURNAME = "select * from users where password = ? and (surname = ? or email = ?);";
	private final String SELECT_USER_BY_ID = "select * from users where noUser = ?;";
	private final String INSERT_USER = "INSERT INTO USERS (surname, lastname, firstname, email, phone, street, zipcode, city, credit, administrator, password) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
	private final String UPDATE_USER_BY_ID = "UPDATE USERS SET surname = ? , lastname = ?, firstname = ?, email = ?, phone = ?, street = ?, zipcode = ?, city = ?, password = ?  WHERE noUser = ?;";
	private final String UPDATE_USER_CREDIT_BY_ID = "UPDATE USERS SET credit = ?  WHERE noUser = ?;";
	private final String DELETE_USER = "DELETE FROM USERS WHERE noUser = ?;";

	/**
	 * The method selectUserBySurname select the an existing user with his surnanme
	 * into the database
	 * 
	 * @author rgirault2019
	 * @param "surname"
	 *            comes from BLL
	 * @return User whom own the surname paramater
	 */
	@Override
	public User selectUserBySurname(String input, String password) throws AppException {
		if (input == null && password == null) {
			AppException ae = new AppException();
			ae.ajouterErreur(CodesResultatDAL.CONNECTION_NULL_ID_PWD);
			throw ae;
		}

		User currentUser = new User();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_USER_BY_SURNAME);
			pstmt.setString(1, password);
			pstmt.setString(2, input);
			pstmt.setString(3, input);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				if (rs.getInt("noUser") != currentUser.getNoUser() && password.equals(rs.getString("password"))
						&& (input.equalsIgnoreCase(rs.getString("surname")) || input.equals(rs.getString("email")))) {
					currentUser = userBuilder(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			AppException ae = new AppException();
			ae.ajouterErreur(CodesResultatDAL.CONNECTION_FAIL);
			throw ae;
		}

		return currentUser;
	}

	@Override
	public User selectUserById(int noUser) {
		User currentUser = new User();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_USER_BY_ID);
			if (noUser != 0)
				pstmt.setInt(1, noUser);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				if (rs.getInt("noUser") != currentUser.getNoUser()) {
					currentUser = userBuilder(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return currentUser;

	}
	
	@Override
	public User selectUserById(int noUser, Connection cnx) {
		User currentUser = new User();
		try  {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_USER_BY_ID);
			if (noUser != 0)
				pstmt.setInt(1, noUser);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				
				if (rs.getInt("noUser") != currentUser.getNoUser()) {
					currentUser = userBuilder(rs);
				}
			}
			
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return currentUser;
		
	}

	@Override
	public User insertUser(String pseudo, String lastname, String firstname, String mail, String phone, String street,
			String zipcode, String city, String pwd, String nbPoints) throws AppException {
		User currentUser = new User();

		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);

				PreparedStatement pstmt = cnx.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, pseudo);
				pstmt.setString(2, lastname);
				pstmt.setString(3, firstname);
				pstmt.setString(4, mail);
				pstmt.setString(5, phone);
				pstmt.setString(6, street);
				pstmt.setString(7, zipcode);
				pstmt.setString(8, city);
				pstmt.setString(9, nbPoints);
				pstmt.setString(10, "0");
				pstmt.setString(11, pwd);

				pstmt.executeUpdate();

				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next())
					currentUser.setNoUser(rs.getInt(1));
				rs.close();
				pstmt.close();

				pstmt = cnx.prepareStatement(SELECT_USER_BY_ID);
				pstmt.setInt(1, currentUser.getNoUser());
				ResultSet rset = pstmt.executeQuery();

				while (rset.next()) {
					if (rset.getInt("noUser") == currentUser.getNoUser())
						currentUser = userBuilder(rset);
				}
				cnx.commit();
			} catch (Exception e) {
				cnx.rollback();
			}
			cnx.setAutoCommit(true);
		} catch (Exception e) {
			e.printStackTrace();
			AppException ae = new AppException();
			ae.ajouterErreur(CodesResultatDAL.INSCRIPTION_FAIL);
			throw ae;
		}
		return currentUser;
	}

	@Override
	public void updateUser(String pseudo, String lastname, String firstname, String mail, String phone, String street,
			String zipcode, String city, String pwd, int noUser) throws AppException {

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_USER_BY_ID);
			pstmt.setString(1, pseudo);
			pstmt.setString(2, lastname);
			pstmt.setString(3, firstname);
			pstmt.setString(4, mail);
			pstmt.setString(5, phone);
			pstmt.setString(6, street);
			pstmt.setString(7, zipcode);
			pstmt.setString(8, city);
			pstmt.setString(9, pwd);
			pstmt.setInt(10, noUser);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @author rgirault2019
	 */
	@Override
	public void deleteUser(int noUser) {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_USER);
			pstmt.setInt(1, noUser);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @author rgirault2019
	 */
	@Override
	public void updateUserCredit(int noUser, int credits, Connection cnx) throws AppException {
		PreparedStatement pstmt = null;
		try {
			pstmt = cnx.prepareStatement(UPDATE_USER_CREDIT_BY_ID);
			pstmt.setInt(1, credits);
			pstmt.setInt(2, noUser);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * The method userBuilder build a new user with data from database
	 * 
	 * @author rgirault2019
	 * @param rs
	 * @return new User completed by User data from database
	 * @throws SQLException
	 */
	private User userBuilder(ResultSet rs) throws SQLException {
		User currentUser;
		currentUser = new User();
		currentUser.setNoUser(rs.getInt("noUser"));
		currentUser.setSurname(rs.getString("surname"));
		currentUser.setLastName(rs.getString("lastname"));
		currentUser.setFirstName(rs.getString("firstname"));
		currentUser.setEmail(rs.getString("email"));
		currentUser.setPhone(rs.getString("phone"));
		currentUser.setStreet(rs.getString("street"));
		currentUser.setZipCode(rs.getString("zipCode"));
		currentUser.setCity(rs.getString("city"));
		currentUser.setPassword(rs.getString("password"));
		currentUser.setCredit(rs.getInt("credit"));
		currentUser.setAdministrator(rs.getInt("administrator") == 1 ? true : false);
		return currentUser;
	}

}
