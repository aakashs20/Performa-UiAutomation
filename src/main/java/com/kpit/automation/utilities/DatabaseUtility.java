/**
 * This class provides methods to interact with a database.
 *
 * @author Aakash Saxena
 * @version 1.0
 * @since   2023-12-01
 */
package com.kpit.automation.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.HashMap;

import org.apache.log4j.Logger;

/**
 * Utility class to interact with a database. 
 * It extends the {@link FileReaderUtility} class.
 *
 * @author Aakash Saxena
 * @version 1.0
 * @since 2023-12-01
 */
public class DatabaseUtility extends FileReaderUtility {

	/**
	 * The Logger instance for logging database operations.
	 */
	private Logger log = getLogger(DatabaseUtility.class);

	/**
	 * This method retrieves data from the database using the provided SQL query.
	 *
	 * @param sql The SQL query to execute.
	 * @return A HashMap containing the retrieved data.
	 * @throws Exception If any error occurs during database interaction.
	 */
	public HashMap<String, String> getDataFromDB(String sql) {
		
		HashMap<String, String> data_map = new HashMap<>();
		try {
			log.info("Connecting to the Database"); // Establishing connection to the database
			Connection con = DriverManager.getConnection(getConfigData("db.url"), getConfigData("db.username"),
					getConfigData("db.password")); // Creating connection object
			
			Statement stmt = con.createStatement(); // Creating statement object
			ResultSet rs = stmt.executeQuery(sql); // Executing query and getting result set
			ResultSetMetaData md = rs.getMetaData(); // Getting metadata of result set
			
			while (rs.next()) { // Loop through result set
				for (int i = 1; i <= md.getColumnCount(); i++) { // Loop through columns
					data_map.put(md.getColumnName(i), rs.getString(i)); // Putting data into HashMap
				}
			}
			
			con.close(); // Closing the connection to the database
			log.info("Connection to the Database closed"); // Logging successful DB closure
			
		} catch (Exception ex) { // Catching any exceptions
			ex.printStackTrace(); // Printing stack trace
			log.error(ex); // Logging the exception
		}
		
		return data_map;
	}
}