package fr.eni.BiddingProject.dal;

public abstract class CodesResultatDAL {
	
	/**
	 * Echec général quand tentative d'ajouter un objet null
	 */
	public static final int INSERT_OBJET_NULL=10000;
	
	/**
	 * Echec général quand erreur non gérée à l'insertion 
	 */
	public static final int INSERT_OBJECT_FAIL=10001;

	
	/**
	 * Echec de la lecture des listes d'enchères
	 */
	public static final int SELECT_ALL_BIDS_FAIL = 10002;
	/**
	 * Echec de l'insertion du nom d'article
	 */
	public static final int INSERT_ARTICLENAME_FAIL = 10003;
	/**
	 * Echec de l'insertion de la description d'article
	 */
	public static final int INSERT_ARTICLE_DESCRIPTION_FAIL = 10004;
	/**
	 * Echec de l'insertion de la date de début
	 */
	public static final int INSERT_START_DATE_FAIL = 10005;
	/**
	 * Echec de l'insertion de la date de fin
	 */
	public static final int INSERT_START_END_FAIL = 10006;
	/**
	 * Echec de l'insertion du prix de départ
	 */
	public static final int INSERT_START_PRICE_FAIL = 10007;
	/**
	 * Echec de la lecture des catégories
	 */
	public static final int SELECT_ALL_CAT_FAIL = 10008;
	
	
	
	
	// ARTICLES
	/**
	 * Echec de la récupération des articles en fonction des recherches
	 */
	public static final int FILTER_BY_SEARCH = 13000;
	/**
	 * Echec de la récupération d'un article
	 */
	public static final int SELECT_ARTICLE_BY_ID = 13001;
	
	
	
	// ENCHERES
	/**
	 * Montant d'enchère vide
	 */
	public static final int INSERT_BID_AMOUNT_FAIL = 11000;
	/**
	 * Echec de l'insertion d'une enchère
	 */
	public static final int INSERT_BID_FAIL = 11001;
	
	
	// USERS
	/**
	 * Identifiant et mot de passe vide
	 */
	public static final int CONNECTION_NULL_ID_PWD = 12000;
	/**
	 * Echec de la connexion
	 */
	public static final int CONNECTION_FAIL = 12001;
	/**
	 * Echec de la connexion
	 */
	public static final int INSCRIPTION_FAIL = 12002;

}
