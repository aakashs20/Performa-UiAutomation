/**
 * LoggerUtility class provides a method to get a Logger instance.
 * It also initializes the log4j configuration.
 *
 * @author Aakash Saxena
 * @version 1.0
 * @since 2023-12-01
 */
package com.kpit.automation.utilities;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * LoggerUtility class extends ReporterUtility and provides a method to get a Logger instance.
 * It also initializes the log4j configuration.
 *
 * @author Aakash Saxena
 * @version 1.0
 * @since 2023-12-01
 */
public class LoggerUtility extends ReporterUtility {

	private static boolean root = false; // Flag to check if logger configuration has been set
	private static String propertyFilePath = "//src//main//resources//configurations//"; // Path to the log4j properties file
	private static String logConfigFile = "log4j.properties"; // Name of the log4j properties file

	/**
	 * GetLogger method provides a Logger instance for the given class.
	 * It also initializes the log4j configuration if it hasn't been initialized yet.
	 *
	 * @param cls The class for which a Logger instance is required.
	 * @return A Logger instance for the given class.
	 */
	public Logger getLogger(Class<?> cls) {
		
		Logger logger = null; // Initializing Logger variable
		try {
			if (root) { // Checking if logger configuration has already been set
				logger = Logger.getLogger(cls); // Returning the Logger instance
			} else {
				PropertyConfigurator.configure(System.getProperty("user.dir") + propertyFilePath + logConfigFile); // Configuring Logger using log4j properties file
				root = true; // Setting the root flag to true indicating that logger configuration has been set
				logger = Logger.getLogger(cls); // Returning the Logger instance
			}
			
		} catch (Exception ex) { // Catching any exceptions
			ex.printStackTrace(); // Printing stack trace if an exception occurs
		}
		
		return logger; // Returning the Logger instance
	}
}