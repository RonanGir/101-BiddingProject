package fr.training.BiddingProject.dal;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public abstract class ConnectionProvider {
	private static DataSource dataSource;

	/**
	 * @author Benoit Au chargement de la classe, la DataSource est recherchée dans
	 *         l'arbre JNDI
	 */
	static {
		Context ctx;
		try {
			ctx = new InitialContext();
			ConnectionProvider.dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/pool_bid_sqlite");
		} catch (NamingException e) {
			e.printStackTrace();
			//throw new RuntimeException("Impossible d'accéder à la base de données");
		}
	}

	/**
	 * Cette méthode retourne une connection opérationnelle issue du pool de
	 * connexion vers la base de données.
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return ConnectionProvider.dataSource.getConnection();
	}
}