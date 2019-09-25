package fr.eni.BiddingProject.bll;

import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.eni.BiddingProject.AppException;
import fr.eni.BiddingProject.bo.Regex;
import fr.eni.BiddingProject.bo.User;
import fr.eni.BiddingProject.dal.DAOFactory;

public class UserManagerBase implements UserManager {

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(Regex.getREGEX_MAIL(),
			Pattern.CASE_INSENSITIVE);
	public static final Pattern VALID_BASIC_INFO_REGEX = Pattern.compile(Regex.getREGEX_BASIC(),
			Pattern.CASE_INSENSITIVE);
	public static final Pattern VALID_PWD_REGEX = Pattern.compile(Regex.getREGEX_PWD(), Pattern.CASE_INSENSITIVE);
	public static final Pattern VALID_BASIC_WITHOUT_NUMBERS_REGEX = Pattern.compile(Regex.getRegexBasicNoNumbers(), Pattern.CASE_INSENSITIVE);
	public static final Pattern VALID_PHONE_REGEX = Pattern.compile(Regex.getRegexPhone(), Pattern.CASE_INSENSITIVE);

	/**
	 * @author rgirault2019
	 * @throws AppException 
	 */
	@Override
	public User getUserBySurname(String input, String password) throws AppException {
		AppException ae = new AppException();
		User user = null;
		
		if (input == null && password == null)
			ae.ajouterErreur(CodesResultatBLL.CONNECTION_ID_PWD_NULL);
		
		if (!ae.hasErreurs()) {
			user = DAOFactory.getUserDAO().selectUserBySurname(input, password);
		} else {
			throw ae;
		}
		
		if (user.getSurname() != null)
			user.setLoggedIn(true);
		
		return user;
	}

	/**
	 * @author rgirault2019
	 */
	@Override
	public User getUserById(int noUser) {
		User user = null;
		if (noUser != 0)
			user = DAOFactory.getUserDAO().selectUserById(noUser);
		if (user.getNoUser() != 0)
			user.setLoggedIn(true);
		return user;
	}
	
	/**
	 * @author rgirault2019
	 */
	@Override
	public User getUserById(int noUser, Connection cnx) {
		User user = null;
		if (noUser != 0)
			user = DAOFactory.getUserDAO().selectUserById(noUser, cnx);
		if (user.getNoUser() != 0)
			user.setLoggedIn(true);
		return user;
	}

	/**
	 * @author rgirault2019
	 */
	@Override
	public void updateUser(String pseudo, String lastname, String firstname, String mail, String phone, String street,
			String zipcode, String city, String pwd, int noUser) throws AppException {
		DAOFactory.getUserDAO().updateUser(pseudo, lastname, firstname, mail, phone, street, zipcode, city, pwd,
				noUser);
	}
	
	/**
	 * @author rgirault2019
	 */
	@Override
	public void updateUserCredit(int noUser, int credits, Connection cnx) throws AppException {
		DAOFactory.getUserDAO().updateUserCredit(noUser, credits, cnx);
		
	}

	/**
	 * @author rgirault2019
	 */
	@Override
	public void deleteUser(int noUser) {
		if (noUser != 0)
			DAOFactory.getUserDAO().deleteUser(noUser);
	}

	@Override
	public boolean UserLoggedIn(User current) throws AppException {
		return current.isLoggedIn() ? true : false;
	}

	/**
	 * Méthode pour l'insertion d'un user
	 */
	@Override
	public User insertUser(String pseudo, String lastname, String firstname, String mail, String phone, String street,
			String zipcode, String city, String pwd, String nbPoints) throws AppException {
		AppException ae = new AppException();
		// on crée un nouvel user
		User user = null;
		
		if (pseudo == null && lastname == null && firstname == null && mail == null && phone == null && street == null && zipcode == null && city == null && pwd == null)
			ae.ajouterErreur(CodesResultatBLL.INSCRIPTION_NULL);
		
		if (!ae.hasErreurs() ) {
			// on envoie les information à la méthode DAL et on affecte les valeurs reçues au user
			user = DAOFactory.getUserDAO().insertUser(pseudo, lastname, firstname, mail, phone, street, zipcode, city, pwd, nbPoints);
		} else {
			throw ae;
		}
		// Si un user exsite et n'est pas null on le login
		if (user.getSurname() != null)
			user.setLoggedIn(true);
		// on renvoie le user
		return user;
	}

	/**
	 * Méthode pour faire un check de l'id de connexion
	 * @author Benoit
	 * 
	 */
	public Boolean checkId(String identifier) {
		// on génére un booléen
		Boolean checked = false;
		// Vérification si l'id est un pseudo ou un mail
		int email = (identifier.indexOf('@'));

		try {
			// si ce n'est pas un email
			if (email == -1) {
				// On vérifie si l'identifiant est supérieur à 0 et inférieur à 31 caractères
				if (identifier.length() > 0 && identifier.length() <= 30) {
					try {
						// On check le pseudo avec un regex
						Boolean id = validateInfos(identifier);
						// Si le pseudo est bon on passe le booléen à vrai
						if (id == true)
							checked = true;
					} catch (Exception e) {
						System.out.println("Pseudo incorrect");
					}
				}
				// si l'identifiant est un mail
			} else {
				try {
					// vérification que le mail est inférieur à 20 caractères
					if (identifier.length() <= 20) {
						// vérification du mail via un regex
						Boolean mail = validateMail(identifier);
						// si mail validé on passe le booléen à vrai
						if (mail == true)
							checked = true;
					}
				} catch (Exception e) {
					System.out.println("Mail incorrect");
				}
			}
		} catch (Exception e) {
			System.out.println("Identifiant incorrect");
		}
		// on renvoie le booléen
		return checked;
	}

	@Override
	public Boolean checkPwd(String pwd) {
		return null;
	}

	/**
	 * Méthode pour vérifier les informations d'inscription
	 * @author Benoit
	 * 
	 */
	@Override
	public Boolean inscriptionVerification(String pseudo, String lastname, String firstname, String mail, String phone,
			String street, String zipcode, String city, String pwd) {
		AppException ae = new AppException();
		// On crée un booléen
		Boolean checked = false;
		try {
			// On teste toutes les informations suivant différents regex
			Boolean cpseudo = validateInfos(pseudo);
			Boolean clastname = validateInfosWithoutNumbers(lastname);
			Boolean cfirstname = validateInfosWithoutNumbers(firstname);
			Boolean cmail = validateMail(mail);
			Boolean cphone = validatePhone(phone);
			Boolean czipcode = validateInfos(zipcode);
			Boolean ccity = validateInfosWithoutNumbers(city);
			Boolean cpwd = validatePwd(pwd);
			
			if (cpseudo == false)
				ae.ajouterErreur(CodesResultatBLL.INSCRIPTION_WRONG_SURNAME);
			if (clastname == false)
				ae.ajouterErreur(CodesResultatBLL.INSCRIPTION_WRONG_LASTNAME);
			if (cfirstname == false)
				ae.ajouterErreur(CodesResultatBLL.INSCRIPTION_WRONG_FIRSTNAME);
			if (cmail == false)
				ae.ajouterErreur(CodesResultatBLL.INSCRIPTION_WRONG_MAIL);
			if (cphone == false)
				ae.ajouterErreur(CodesResultatBLL.INSCRIPTION_WRONG_PHONE);
			if (czipcode == false)
				ae.ajouterErreur(CodesResultatBLL.INSCRIPTION_WRONG_ZIPCODE);
			if (ccity == false)
				ae.ajouterErreur(CodesResultatBLL.INSCRIPTION_WRONG_CITY);
			if (cpwd == false)
				ae.ajouterErreur(CodesResultatBLL.INSCRIPTION_WRONG_PWD);
			
			if (!ae.hasErreurs()) {
				if (cpseudo == true && clastname == true && cfirstname == true && cmail == true && cphone == true && czipcode == true && ccity == true && cpwd == true)
					checked = true;
			} else {
				throw ae;
			}
		} catch (Exception e) {
			System.out.println("Champs d'inscriptions invalides");
			e.printStackTrace();
		}

		return checked;
	}
	
	@Override
	public Boolean checkSearch(String input) {
		Boolean checked = false;
		
		Boolean search = validateSearch(input);
		
		if (search == true)
			checked = true;
		
		return checked;
	}

	// Méthode pour valider l'email, fait appel à une constante.
	public static boolean validateMail(String email) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		return matcher.find();
	}
	
	// Méthode pour valider les infos basiques, fait appel à une constante.
	public static boolean validateInfos(String input) {
		Matcher matcher = VALID_BASIC_INFO_REGEX.matcher(input);
		return matcher.find();
	}

	// Méthode pour valider le mot de passe, fait appel à une constante.
	public static boolean validatePwd(String pwd) {
		Matcher matcher = VALID_PWD_REGEX.matcher(pwd);
		return matcher.find();
	}
	
	// Méthode pour valider les champs de lettres uniquement, fait appel à une constante.
	public static boolean validateInfosWithoutNumbers(String input) {
		Matcher matcher = VALID_BASIC_WITHOUT_NUMBERS_REGEX.matcher(input);
		return matcher.find();
	}
	
	// Méthode pour valider le téléphone, fait appel à une constante.
	public static boolean validatePhone(String phone) {
		Matcher matcher = VALID_PHONE_REGEX.matcher(phone);
		return matcher.find();
	}
	
	// Méthode pour valider le champs de recherche, fait appel à une constante.
	public static boolean validateSearch(String input) {
		Matcher matcher = VALID_BASIC_INFO_REGEX.matcher(input);
		return matcher.find();
	}


}
