package fr.training.BiddingProject.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.training.BiddingProject.AppException;
import fr.training.BiddingProject.bo.Bid;
import fr.training.BiddingProject.bo.Category;
import fr.training.BiddingProject.bo.Retirement;
import fr.training.BiddingProject.bo.SoldArticle;
import fr.training.BiddingProject.bo.User;

public class BidDAOJdbcImpl implements BidDAO {

	private final String SELECT_ALL_BID_STARTED = "select soldState, sa.noArticle, sa.articleName, sa.description, sa.bidStartPrice, sa.bidStartedDate, sa.bidEndDate, sa.soldPrice, sa.noCategory, sa.noUser, sa.noRetirement, u.noUser, u.surname, u.firstname, u.lastname, u.phone, u.email, u.credit, u.street, u.zipCode, u.city, ret.noRetirement as noRet , ret.street as streetRet, ret.zipCode as zipRet, ret.city as cityRet"
			+ " from sold_article sa " + "inner join users u on sa.noUser = u.noUser "
			+ "inner join retirement ret on sa.noRetirement = ret.noRetirement "
			+ "where bidStartedDate <= CURRENT_TIMESTAMP and bidEndDate >= CURRENT_TIMESTAMP "
			+ "ORDER BY sa.bidEndDate ASC;";

	private final String SELECT_ALL_SOLD_ARTICLE = "select archive, soldState, sa.noArticle, sa.articleName, sa.description, sa.bidStartPrice, sa.bidStartedDate, sa.bidEndDate, sa.soldPrice, sa.noCategory, sa.noUser, sa.noRetirement, u.noUser, u.surname, u.firstname, u.lastname, u.phone, u.email, u.credit, u.street, u.zipCode, u.city, ret.noRetirement as noRet , ret.street as streetRet, ret.zipCode as zipRet, ret.city as cityRet, c.noCategory, c.name, b.noBid, b.bidDate, b.bidAmount, b.noArticle as noArticleInBid, b.noUser as bider "
			+ "from sold_article sa " + "inner join bid b on b.noArticle = sa.noArticle "
			+ "inner join users u on b.noUser  = u.noUser " + "inner join category c on sa.noCategory = c.noCategory "
			+ "inner join retirement ret on sa.noRetirement = ret.noRetirement " + "where soldState = 1 and archive = 0;";

	private final String SELECT_ARTICLE_BY_ID = "select soldState, sa.noArticle, sa.articleName, sa.description, sa.bidStartPrice, sa.bidStartedDate, sa.bidEndDate, sa.soldPrice, sa.noCategory, sa.noUser, sa.noRetirement, u.noUser, u.surname, u.firstname, u.lastname, u.phone, u.email, u.credit, u.street, u.zipCode, u.city, ret.noRetirement as noRet , ret.street as streetRet, ret.zipCode as zipRet, ret.city as cityRet, c.noCategory, c.name, b.noBid, b.bidDate, b.bidAmount, b.noArticle as noArticleInBid, b.noUser as bider "
			+ "from sold_article sa " + "inner join users u on sa.noUser = u.noUser "
			+ "inner join category c on sa.noCategory = c.noCategory "
			+ "inner join retirement ret on sa.noRetirement = ret.noRetirement "
			+ "left outer join bid b on b.noArticle = sa.noArticle " + "where sa.noArticle = ? "
			+ "ORDER BY bidEndDate ASC;";

	// Requête pour récupérer les articles dont le nom d'article contien ... et
	// classés par ordre croissant
	private final String FILTER_ARTICLE_BY_SEARCH = "select soldState, sa.noArticle, sa.articleName, sa.description, sa.bidStartPrice, sa.bidStartedDate, sa.bidEndDate, sa.soldPrice, sa.noCategory, sa.noUser, sa.noRetirement, u.noUser, u.surname, u.firstname, u.lastname, u.phone, u.email, u.credit, u.street, u.zipCode, u.city, ret.noRetirement as noRet , ret.street as streetRet, ret.zipCode as zipRet, ret.city as cityRet"
			+ " from sold_article sa " + "inner join users u on sa.noUser = u.noUser "
			+ "inner join retirement ret on sa.noRetirement = ret.noRetirement "
			+ "where bidStartedDate <= CURRENT_TIMESTAMP and bidEndDate >= CURRENT_TIMESTAMP "
			+ "AND sa.articleName LIKE ? " + "ORDER BY bidEndDate ASC;";

	private final String SELECT_ARTICLE_BY_CATEGORY = "select soldState, sa.noArticle, sa.articleName, sa.description, sa.bidStartPrice, sa.bidStartedDate, sa.bidEndDate, sa.soldPrice, sa.noCategory, sa.noUser, sa.noRetirement, u.noUser, u.surname, u.firstname, u.lastname, u.phone, u.email, u.credit, u.street, u.zipCode, u.city, ret.noRetirement as noRet , ret.street as streetRet, ret.zipCode as zipRet, ret.city as cityRet"
			+ " from sold_article sa " + "inner join users u on sa.noUser = u.noUser "
			+ "inner join retirement ret on sa.noRetirement = ret.noRetirement "
			+ "where bidStartedDate <= CURRENT_TIMESTAMP and bidEndDate >= CURRENT_TIMESTAMP "
			+ "AND sa.noCategory = ? " + "ORDER BY bidEndDate ASC;";

	private final String FILTER_ARTICLE_BY_CATEGORY_AND_ARTICLENAME = "select soldState, sa.noArticle, sa.articleName, sa.description, sa.bidStartPrice, sa.bidStartedDate, sa.bidEndDate, sa.soldPrice, sa.noCategory, sa.noUser, sa.noRetirement, u.noUser, u.surname, u.firstname, u.lastname, u.phone, u.email, u.credit, u.street, u.zipCode, u.city, ret.noRetirement as noRet , ret.street as streetRet, ret.zipCode as zipRet, ret.city as cityRet"
			+ " from sold_article sa " + "inner join users u on sa.noUser = u.noUser "
			+ "inner join retirement ret on sa.noRetirement = ret.noRetirement "
			+ "where bidStartedDate <= CURRENT_TIMESTAMP and bidEndDate >= CURRENT_TIMESTAMP "
			+ "AND sa.noCategory = ? AND sa.articleName LIKE ? " + "ORDER BY bidEndDate ASC;";

	private final String FILTER_BY_WON_BIDS = "select sa.soldState, sa.noArticle, sa.articleName, sa.description, sa.bidStartPrice, sa.bidStartedDate, sa.bidEndDate, sa.soldPrice, sa.noCategory, sa.noUser, sa.noRetirement, u.noUser, u.surname, u.firstname, u.lastname, u.phone, u.email, u.credit, u.street, u.zipCode, u.city, ret.noRetirement as noRet , ret.street as streetRet, ret.zipCode as zipRet, ret.city as cityRet, c.noCategory, c.name, b.noBid, b.bidDate, b.bidAmount, b.noArticle, b.noUser as bider "
			+ "from sold_article sa " + "inner join users u on sa.noUser = u.noUser "
			+ "inner join category c on sa.noCategory = c.noCategory "
			+ "inner join retirement ret on sa.noRetirement = ret.noRetirement "
			+ "left outer join bid b on b.noArticle = sa.noArticle "
			+ "where b.noUser = ? and sa.soldState = 1 and sa.bidEndDate < CURRENT_TIMESTAMP "
			+ "order by bidEndDate desc;";

	private final String SELECT_ALL_CATEGORY = "select * from CATEGORY;";

	private final String ADD_NEW_BID_TO_ARTICLE = "insert into BID (bidDate, bidAmount, noArticle, noUser) values (CURRENT_TIMESTAMP, ?, ?, ?);";

	
	private final String INSERT_RETIREMENT      = "insert into RETIREMENT (street, zipCode, city) values (?, ?, ?);";
	
	private final String INSERT_ARTICLE         = "insert into sold_article (articleName, description, bidStartedDate, bidEndDate, bidStartPrice, soldPrice, noUser, noCategory, noRetirement, soldState, archive) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	
	private final String UPDATE_ARTICLE         = "update sold_article set articleName = ?, description = ?, bidStartedDate = ?, bidEndDate = ?, bidStartPrice = ?, noCategory = ? " + 
												"where noArticle = ?;";
	
	private final String UPDATE_ARTICLE_ARCHIVE = "update sold_article set archive = ? where noArticle = ?;";
	
	private final String UPDATE_RETIREMENT      = "update r set street = ?, zipCode = ?, city = ? from retirement r inner join sold_article sa on sa.noRetirement = r.noRetirement where r.noRetirement = ? and sa.noArticle = ?;";
	
	private final String DELETE_ARTICLE         = "DELETE FROM sold_article WHERE noArticle = ?;";
	
	
	
	
	
	
	
	 /**
     * @author rgirault2019
     */
    @Override
    public List<SoldArticle> selectAllSoldBids(Connection cnx) {
        List<SoldArticle> listArticle = new ArrayList<>();
        List<Bid> listBidByArticle    = new ArrayList<>();
            try {
                PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_SOLD_ARTICLE);
                ResultSet rs            = pstmt.executeQuery();
                
                while (rs.next()) {
                    SoldArticle art = new SoldArticle();
                    User user       = new User();
                    Retirement ret  = new Retirement();
                    Bid bid         = new Bid();
                    
                    if (rs.getInt("noArticle") != art.getNoArticle()){
                        art  = articleBuilder(rs);
                        user = userBuilder(rs);
                        ret  = retBuilder(rs);
                        
                        art.setSeller(user);
                        art.setRet(ret);
                        
                    }
                    
                    if (rs.getInt("noArticleInBid") == art.getNoArticle()) {
                        bid = bidBuilder(rs);
                        listBidByArticle.add(bid);
                    }
                    
                    art.setListBidArticle(listBidByArticle);
                    
                    listArticle.add(art);
                    
                }
                
                pstmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            return listArticle;
            
    }


	/**
	 * @author rgirault2019
	 */
	@Override
	public List<SoldArticle> selectAllBidsStarted(Connection cnx) throws AppException {
		List<SoldArticle> listArticle = new ArrayList<>();

		try {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_BID_STARTED);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				SoldArticle art = new SoldArticle();
				User user = new User();
				Retirement ret = new Retirement();

				if (rs.getInt("noArticle") != art.getNoArticle()) {
					art = articleBuilder(rs);
					user = userBuilder(rs);
					ret = retBuilder(rs);
					art.setSeller(user);
					art.setRet(ret);

				}
				listArticle.add(art);
			}

			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			AppException ae = new AppException();
			ae.ajouterErreur(CodesResultatDAL.SELECT_ALL_BIDS_FAIL);
		}

		return listArticle;

	}

	/**
	 * @author rgirault2019
	 */
	@Override
	public List<SoldArticle> selectAllBidsStarted() throws AppException {
		List<SoldArticle> listArticle = new ArrayList<>();

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_BID_STARTED);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				SoldArticle art = new SoldArticle();
				User user = new User();
				Retirement ret = new Retirement();

				if (rs.getInt("noArticle") != art.getNoArticle()) {
					art = articleBuilder(rs);
					user = userBuilder(rs);
					ret = retBuilder(rs);
					art.setSeller(user);
					art.setRet(ret);

				}
				listArticle.add(art);
			}
		} catch (Exception e) {
			e.printStackTrace();
			AppException ae = new AppException();
			ae.ajouterErreur(CodesResultatDAL.SELECT_ALL_BIDS_FAIL);
		}

		return listArticle;

	}

	/**
	 * @author rgirault2019
	 */
	@Override
	public void insertArticle(String articleName, String description, Date bidStartedDate, Date bidEndDate,
			int bidStartPrice, int soldPrice, int noUser, int noCategory, String retStreet, String retZipCode,
			String retCity, boolean soldState, boolean archive) throws AppException {
		if (articleName == null && description == null && bidStartedDate == null && bidEndDate == null && bidStartPrice == 0 && soldPrice == 0 && noUser == 0 && retStreet == null && retZipCode == null && retCity == null) {
		

			AppException ae = new AppException();
			ae.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
		}

		if (articleName == null) {

			AppException ae = new AppException();
			ae.ajouterErreur(CodesResultatDAL.INSERT_ARTICLENAME_FAIL);
		}
		if (description == null) {

			AppException ae = new AppException();
			ae.ajouterErreur(CodesResultatDAL.INSERT_ARTICLE_DESCRIPTION_FAIL);
		}
		if (bidStartedDate == null) {

			AppException ae = new AppException();
			ae.ajouterErreur(CodesResultatDAL.INSERT_START_DATE_FAIL);
		}
		if (bidEndDate == null) {

			AppException ae = new AppException();
			ae.ajouterErreur(CodesResultatDAL.INSERT_START_END_FAIL);
		}
		if (bidStartPrice == 0) {

			AppException ae = new AppException();
			ae.ajouterErreur(CodesResultatDAL.INSERT_START_PRICE_FAIL);
		}

		Retirement ret = new Retirement();
		try (Connection cnx = ConnectionProvider.getConnection()) {

			try {

				cnx.setAutoCommit(false);

				PreparedStatement pstmt = cnx.prepareStatement(INSERT_RETIREMENT,
						PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, retStreet);
				pstmt.setString(2, retZipCode);
				pstmt.setString(3, retCity);

				pstmt.executeUpdate();

				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next())
					ret.setNoRetirement(rs.getInt(1));
				rs.close();
				pstmt.close();

				PreparedStatement stmt = cnx.prepareStatement(INSERT_ARTICLE);
				stmt.setString(1, articleName);
				stmt.setString(2, description);
				stmt.setTimestamp(3, new java.sql.Timestamp(bidStartedDate.getTime()));
				stmt.setTimestamp(4, new java.sql.Timestamp(bidEndDate.getTime()));
				stmt.setInt(5, bidStartPrice);
				stmt.setInt(6, soldPrice);
				stmt.setInt(7, noUser);
				stmt.setInt(8, noCategory);
				stmt.setInt(9, ret.getNoRetirement());
				stmt.setInt(10, soldState == false ? 0 : 1);
				stmt.setInt(11, archive == true ? 1 : 0);
				stmt.executeUpdate();
				cnx.commit();
			} catch (Exception e) {

				e.printStackTrace();
				AppException ae = new AppException();
				ae.ajouterErreur(CodesResultatDAL.INSERT_OBJECT_FAIL);
				cnx.rollback();
			}

			cnx.setAutoCommit(true);
		} catch (Exception e) {

			e.printStackTrace();
			AppException ae = new AppException();
			ae.ajouterErreur(CodesResultatDAL.INSERT_OBJECT_FAIL);
		}
	}

	@Override
	public List<Category> selectAllCategory() throws AppException {
		List<Category> listCategory = new ArrayList<>();
		Category catDefault = new Category();
		catDefault.setNoCategory(0);
		catDefault.setName("--Toutes--");
		listCategory.add(catDefault);
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_CATEGORY);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Category cat = new Category();
				cat.setNoCategory(rs.getInt("noCategory"));
				cat.setName(rs.getString("name"));
				listCategory.add(cat);
			}

		} catch (Exception e) {
			e.printStackTrace();
			AppException ae = new AppException();
			ae.ajouterErreur(CodesResultatDAL.SELECT_ALL_CAT_FAIL);
		}
		return listCategory;
	}

	@Override
	public List<SoldArticle> filterArticleBySearch(String input) throws AppException {
		List<SoldArticle> listArticle = new ArrayList<>();
		// On créer un string qui va contenir le champs input, entouré de % pour la
		// requête Like
		String inputSearch = "%" + input + "%";

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(FILTER_ARTICLE_BY_SEARCH);
			pstmt.setString(1, inputSearch);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				SoldArticle article = new SoldArticle();
				// Pour chaque résultat on construit l'article
				article = articleBuilder(rs);
				// On ajoute l'article construit à notre liste d'article
				listArticle.add(article);
			}

		} catch (Exception e) {
			// Si un e exception est levée, on l'ajoute à la liste d'erreurs d'AppException
			e.printStackTrace();
			AppException ae = new AppException();
			ae.ajouterErreur(CodesResultatDAL.FILTER_BY_SEARCH);
		}

		return listArticle;
	}

	@Override
	public List<SoldArticle> selectArticleByCategory(int noCategory) throws AppException {
		List<SoldArticle> listArticle = new ArrayList<>();

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ARTICLE_BY_CATEGORY);
			pstmt.setInt(1, noCategory);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				SoldArticle article = new SoldArticle();
				// Pour chaque résultat on construit l'article
				article = articleBuilder(rs);
				// On ajoute l'article construit à notre liste d'article
				listArticle.add(article);
			}

		} catch (Exception e) {
			// Si un e exception est levée, on l'ajoute à la liste d'erreurs d'AppException
			e.printStackTrace();
			AppException ae = new AppException();
			ae.ajouterErreur(CodesResultatDAL.FILTER_BY_SEARCH);
		}

		return listArticle;
	}

	@Override
	public List<SoldArticle> filterArticleByCategoryAndArticleName(String input, int noCategory) throws AppException {
		// On créer un string qui va contenir le champs input, entouré de % pour la
		// requête Like
		String inputSearch = "%" + input + "%";
		List<SoldArticle> listArticle = new ArrayList<>();

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(FILTER_ARTICLE_BY_CATEGORY_AND_ARTICLENAME);
			pstmt.setInt(1, noCategory);
			pstmt.setString(2, inputSearch);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				SoldArticle article = new SoldArticle();
				// Pour chaque résultat on construit l'article
				article = articleBuilder(rs);
				// On ajoute l'article construit à notre liste d'article
				listArticle.add(article);
			}

		} catch (Exception e) {
			// Si un e exception est levée, on l'ajoute à la liste d'erreurs d'AppException
			e.printStackTrace();
			AppException ae = new AppException();
			ae.ajouterErreur(CodesResultatDAL.FILTER_BY_SEARCH);
		}
		return listArticle;
	}

	@Override
	public List<SoldArticle> filterArticleByAchats(String input, int noCategory, String openBids, int noUser, String winBids) throws AppException {
		
		List<SoldArticle> listArticle = new ArrayList<>();
		AppException ae              = new AppException();
		
		// Création d'une requête qui sera complétée en fonction des critères de recherches
		String rqt = "select DISTINCT soldState, sa.noArticle, sa.articleName, sa.description, sa.bidStartPrice, sa.bidStartedDate, sa.bidEndDate, sa.soldPrice, sa.noCategory, sa.noUser, sa.noRetirement, u.noUser, u.surname, u.firstname, u.lastname, u.phone, u.email, u.credit, u.street, u.zipCode, u.city, c.noCategory, c.name, ret.noRetirement as noRet , ret.street as streetRet, ret.zipCode as zipRet, ret.city as cityRet"
				+ " from sold_article sa " + "inner join users u on sa.noUser = u.noUser "
				+ "inner join category c on sa.noCategory = c.noCategory "
				+ "inner join retirement ret on sa.noRetirement = ret.noRetirement "
				+ "left outer join bid b on b.noArticle = sa.noArticle "
				+ "where ";
		
		if (!ae.hasErreurs()) {
			// Si champs de recherche rempli, on construit la requête avec le champs de recherche
			if (!input.equals("")) {
				rqt += "sa.articleName LIKE '%" + input + "%' ";
				// Sinon on fait une recherche pour tout article name non null (donc tous), permet d'avoir quelque chose après le where
			} else {
				rqt += "sa.articleName IS NOT NULL ";
			}
			
			// Si catégorie seléctionnée on ajoute le numéro de catégorie
			if (noCategory != 0) rqt += "AND sa.noCategory =" + noCategory + " ";
			
			// Si numéro d'user différent de 0 donc user qui exsite et winBids sur on
			if (noUser != 0 && !winBids.equals("")) {
				rqt += "AND b.noUser =" + noUser + "and sa.soldState = 1 and sa.bidEndDate < CURRENT_TIMESTAMP ";
			} else {
				rqt += "AND bidStartedDate <= CURRENT_TIMESTAMP and bidEndDate >= CURRENT_TIMESTAMP ";
			}
			
			// Si numéro d'user différent de 0 donc user qui exsite et winBids non coché
			if (noUser != 0 && winBids.equals("")) rqt += "AND b.noUser =" + noUser + " ";
			
			// On ajoute l'ordre d'affichage
			rqt += "ORDER BY bidEndDate ASC;";
			// On envoie la requête à la DAL
		} else {
			throw ae;
		}
			
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(rqt);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				SoldArticle art = new SoldArticle();
				// Si le numéro d'article en base est différent de l'article crée
				if (rs.getInt("noArticle") != art.getNoArticle()) {
					User user = new User();
					Retirement ret = new Retirement();
					Category cat = new Category();

					// On construit l'article, le user, le retirement et la catégorie
					art = articleBuilder(rs);
					user = userBuilder(rs);
					ret = retBuilder(rs);
					cat = catBuilder(rs);

					// On affecte le vendeur de l'article, le retirement de l'article et la
					// catégorie de l'article
					art.setSeller(user);
					art.setRet(ret);
					art.setCat(cat);
				}
				listArticle.add(art);
			}

		} catch (Exception e) {
			// Si une exception est levée, on l'ajoute à la liste d'erreurs d'AppException
			ae.ajouterErreur(CodesResultatDAL.FILTER_BY_SEARCH);
		}

		return listArticle;
	}
	
	

	@Override
	public List<SoldArticle> filterArticleBySales(String input, int noCategory, int noUser, String pending, String end) throws AppException {
		
		List<SoldArticle> listArticle = new ArrayList<>();
		AppException ae               = new AppException();
		
		// Création d'une requête qui sera complétée en fonction des critères de recherches
		String rqt = "select DISTINCT soldState, sa.noArticle, sa.articleName, sa.description, sa.bidStartPrice, sa.bidStartedDate, sa.bidEndDate, sa.soldPrice, sa.noCategory, sa.noUser, sa.noRetirement, u.noUser, u.surname, u.firstname, u.lastname, u.phone, u.email, u.credit, u.street, u.zipCode, u.city, c.noCategory, c.name, ret.noRetirement as noRet , ret.street as streetRet, ret.zipCode as zipRet, ret.city as cityRet"
				+ " from sold_article sa " + "inner join users u on sa.noUser = u.noUser "
				+ "inner join category c on sa.noCategory = c.noCategory "
				+ "inner join retirement ret on sa.noRetirement = ret.noRetirement " + "where ";
		
		if (!ae.hasErreurs()) {
			// Si champs de recherche rempli, on construit la requête avec le champs de recherche
			if (!input.equals("")) {
				rqt += "sa.articleName LIKE '%" + input + "%' ";
				// Sinon on fait une recherche pour tout article name non null (donc tous), permet d'avoir quelque chose après le where
			} else {
				rqt += "sa.articleName IS NOT NULL ";
			}
			// Si catégorie seléctionnée on ajoute le numéro de catégorie
			if (noCategory != 0) {
				rqt += "AND sa.noCategory =" + noCategory + " ";
			} 
			// Si numéro user différent de 0, donc utilisateur reconnu
			if (noUser != 0) {
				rqt += "AND sa.noUser =" + noUser + " ";
				// Sinon recherche pour tous les user
			} else {
				rqt += "AND sa.noUser IS NOT NULL ";
			}
			// Si ventes non débutées cochée, date début enchère supérieure ou égale à date actuelle
			if (!pending.equals("")) {
				rqt += "AND bidStartedDate >= CURRENT_TIMESTAMP ";
			}
			// Si ventes terminées cochée, date fin enchère inférieure à date actuelle
			if (!end.equals("")) {
				rqt += "AND bidEndDate <= CURRENT_TIMESTAMP ";
			}
			// Ajout de l'odre d'affichage
			rqt += "ORDER BY bidEndDate ASC;";
		} else {
			throw ae;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(rqt);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				SoldArticle art = new SoldArticle();
				// Si le numéro d'article en base est différent de l'article crée
				if (rs.getInt("noArticle") != art.getNoArticle()) {
					User user = new User();
					Retirement ret = new Retirement();
					Category cat = new Category();
					// On construit l'article, le user, le retirement et la catégorie
					art = articleBuilder(rs);
					user = userBuilder(rs);
					ret = retBuilder(rs);
					cat = catBuilder(rs);
					// On affecte le vendeur de l'article, le retirement de l'article et la
					// catégorie de l'article
					art.setSeller(user);
					art.setRet(ret);
					art.setCat(cat);
				}

				listArticle.add(art);
			}

		} catch (Exception e) {
			// Si une exception est levée, on l'ajoute à la liste d'erreurs d'AppException
			e.printStackTrace();
			ae.ajouterErreur(CodesResultatDAL.FILTER_BY_SEARCH);
		}
		return listArticle;
	}

	/**
	 * @author rgirault2019
	 */
	@Override
	public List<SoldArticle> filterArticleByWonBids(int noUser) throws AppException {
		List<SoldArticle> listArticle = new ArrayList<>();

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(FILTER_BY_WON_BIDS);
			pstmt.setInt(1, noUser);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				SoldArticle art = new SoldArticle();

				if (rs.getInt("noArticle") != art.getNoArticle()) {
					User user = new User();
					Retirement ret = new Retirement();
					Category cat = new Category();

					art = articleBuilder(rs);
					user = userBuilder(rs);
					ret = retBuilder(rs);
					cat = catBuilder(rs);

					art.setSeller(user);
					art.setRet(ret);
					art.setCat(cat);
				}
				listArticle.add(art);
			}

		} catch (Exception e) {
			e.printStackTrace();
			AppException ae = new AppException();
			ae.ajouterErreur(CodesResultatDAL.FILTER_BY_SEARCH);
		}

		return listArticle;
	}

	/**
	 * @author rgirault2019
	 */
	@Override
	public SoldArticle selectArticleById(int noArticle) throws AppException {
		SoldArticle art = new SoldArticle();

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ARTICLE_BY_ID);
			pstmt.setInt(1, noArticle);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Bid bid = new Bid();

				if (rs.getInt("noArticle") != art.getNoArticle()) {
					User user = new User();
					Retirement ret = new Retirement();
					Category cat = new Category();

					art = articleBuilder(rs);
					user = userBuilder(rs);
					ret = retBuilder(rs);
					cat = catBuilder(rs);

					art.setSeller(user);
					art.setRet(ret);
					art.setCat(cat);
				}

				if (rs.getInt("noBid") > 0) {
					bid = bidBuilder(rs);
					art.getListBidArticle().add(bid);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			AppException ae = new AppException();
			ae.ajouterErreur(CodesResultatDAL.SELECT_ARTICLE_BY_ID);
		}
		return art;
	}

	/**
	 * @author rgirault2019
	 */
	@Override

	public void updateArticle(int noArticle, int seller, String articleName, String description, Date bidStartedDate,
			Date bidEndDate, int bidStartPrice, int noCategory, int noRet, String retStreet, String retZipCode,
			String retCity) {

		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);

				PreparedStatement stmt = cnx.prepareStatement(UPDATE_ARTICLE);
				stmt.setString(1, articleName);
				stmt.setString(2, description);
				stmt.setTimestamp(3, new java.sql.Timestamp(bidStartedDate.getTime()));
				stmt.setTimestamp(4, new java.sql.Timestamp(bidEndDate.getTime()));
				stmt.setInt(5, bidStartPrice);
				stmt.setInt(6, noCategory);
				stmt.setInt(7, noArticle);
				stmt.executeUpdate();
				stmt.close();

				PreparedStatement pstmt = cnx.prepareStatement(UPDATE_RETIREMENT);
				pstmt.setString(1, retStreet);
				pstmt.setString(2, retZipCode);
				pstmt.setString(3, retCity);
				pstmt.setInt(4, noRet);
				pstmt.setInt(5, noArticle);

				pstmt.executeUpdate();
				cnx.commit();
			} catch (Exception e) {
				cnx.rollback();
			}
			cnx.setAutoCommit(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * @author rgirault2019
	 */
	@Override

	public void updateArticleArchive(int noArticle, boolean archive, Connection cnx) {

		try {
			try {
		
				PreparedStatement stmt = cnx.prepareStatement(UPDATE_ARTICLE_ARCHIVE);
			
				stmt.setInt(1, archive ? 1 : 0);
				stmt.setInt(2, noArticle);
				stmt.executeUpdate();
				stmt.close();
			
			} catch (Exception e) {
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	/**
	 * @author rgirault2019
	 */
	@Override
	public void deleteArticle(int noArticle) {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_ARTICLE);
			pstmt.setInt(1, noArticle);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @author rgirault2019
	 */
	public void addNewBid(int amount, int noArticle, User bider, Connection cnx) throws AppException {
		if (amount == 0) {
			AppException ae = new AppException();
			ae.ajouterErreur(CodesResultatDAL.INSERT_BID_AMOUNT_FAIL);
		}

		PreparedStatement pstmt = null;
		try {
			pstmt = cnx.prepareStatement(ADD_NEW_BID_TO_ARTICLE);
			pstmt.setInt(1, amount);
			pstmt.setInt(2, noArticle);
			pstmt.setInt(3, bider.getNoUser());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			AppException ae = new AppException();
			ae.ajouterErreur(CodesResultatDAL.INSERT_BID_FAIL);
		}

	}

	// Here are all the private methods

	private SoldArticle articleBuilder(ResultSet rs) throws SQLException {
		SoldArticle art = new SoldArticle();
		art.setNoArticle(rs.getInt("noArticle"));
		art.setNoUser(rs.getInt("noUser"));
		art.setArticleName(rs.getString("articleName"));
		art.setDescription(rs.getString("description"));
		art.setBidStartedDate(rs.getTimestamp("bidStartedDate"));
		art.setBidEndDate(rs.getTimestamp("bidEndDate"));
		art.setBidStartPrice(rs.getInt("bidStartPrice"));
		art.setSoldPrice(rs.getInt("soldPrice"));
		art.setSoldState(rs.getInt("soldState") == 0 ? false : true);
		art.setNoCategory(rs.getInt("noCategory"));
		return art;
	}

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
		currentUser.setCredit(rs.getInt("credit"));
		return currentUser;
	}

	private Retirement retBuilder(ResultSet rs) throws SQLException {
		Retirement currentRet;
		currentRet = new Retirement();
		currentRet.setNoRetirement(rs.getInt("noRet"));
		currentRet.setStreet(rs.getString("streetRet"));
		currentRet.setZipCode(rs.getString("zipRet"));
		currentRet.setCity(rs.getString("cityRet"));
		return currentRet;

	}

	private Category catBuilder(ResultSet rs) throws SQLException {
		Category cat;
		cat = new Category();
		cat.setNoCategory(rs.getInt("noCategory"));
		cat.setName(rs.getString("name"));
		return cat;

	}

	private Bid bidBuilder(ResultSet rs) throws SQLException {
		Bid bid = new Bid();
		bid.setNoBid(rs.getInt("noBid"));
		bid.setNoUser(rs.getInt("bider"));
		bid.setNoArticle(rs.getInt("noArticle"));
		bid.setBidDate(rs.getDate("bidDate"));
		bid.setBidAmount(rs.getInt("bidAmount"));
		return bid;

	}

}
