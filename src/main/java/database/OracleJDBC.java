package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class OracleJDBC {
	final static Logger logger = Logger.getLogger(OracleJDBC.class);

	public static Connection getDBConnection() {
		logger.debug("-------- Oracle JDBC Connection Testing ------");

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {

			logger.debug("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return null;

		}

		logger.debug("Oracle JDBC Driver Registered!");

		Connection connection = null;

		try {

			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "System",
					"stacy1s");
			return connection;

		} catch (SQLException e) {

			logger.debug("Connection Failed! Check output console :"+e.getMessage());
			e.printStackTrace();
			return connection;

		}
	}

}