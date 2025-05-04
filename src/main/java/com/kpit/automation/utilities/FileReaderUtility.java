/**
 * This class provides methods for reading properties from a file.
 * It extends {@link ExcelReaderUtility} and provides methods to load and retrieve data from a properties file.
 *
 * @author Aakash Saxena
 * @version 1.0
 * @since 2023-12-01
 */
package com.kpit.automation.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Utility class for reading properties from a properties file.
 * It extends {@link ExcelReaderUtility} and provides methods to load and retrieve data from a properties file.
 *
 * @author Aakash Saxena
 * @version 1.0
 * @since 2023-12-01
 */
public class FileReaderUtility extends ExcelReaderUtility {

	/**
	 * This field represents the logger object that will be used to log messages.
	 */
	private Logger log = getLogger(FileReaderUtility.class);

	private static Properties properties; // Properties object to store key-value pairs
	private static File file; // File object to represent the property file
	private static FileInputStream fileInputStream; // FileInputStream object to read from the property file

	private static String propertyFilePath = "//src//main//resources//configurations//"; // Path to the property file
	private static String testConfigFile = "testConfig.properties"; // Name of the property file

	/**
	 * This method loads the properties file and initializes the properties object with its data.
	 * It throws {@link IOException} and {@link FileNotFoundException} if there is an error reading the file.
	 *
	 * @throws IOException if there is an error reading the file
	 * @throws FileNotFoundException if the file is not found
	 */
	public void loadPropertyFile() throws IOException, FileNotFoundException {

		try {
			properties = new Properties(); // Creating a new Properties object
			file = new File(System.getProperty("user.dir") + propertyFilePath + testConfigFile); // Creating a File object representing the property file
			fileInputStream = new FileInputStream(file); // Creating a FileInputStream object to read from the property file
			properties.load(fileInputStream); // Loading properties from the file

		} catch (Exception ex) {
			ex.printStackTrace(); // Printing stack trace if an exception occurs
			log.error(ex); // Logging the exception
			log.info("================= [ Property file " + testConfigFile + " not found ] ================="); // Logging an info message

		} finally {
			fileInputStream.close(); // Closing FileInputStream in the finally block to ensure it's always closed
		}
	}

	/**
	 * This method retrieves the value of a property from the properties file.
	 * It returns the value as a string, or null if the property is not found.
	 *
	 * @param property the name of the property to retrieve
	 * @return the value of the property, or null if the property is not found
	 */
	public String getConfigData(String property) {
		String dataFromPropFile = null;
		try {
			dataFromPropFile = properties.getProperty(property).trim();
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
		}
		return dataFromPropFile;
	}

	public String getInputFilePath(String fileName) {
		try {
			File file = new File(System.getProperty("user.dir") + "/src/test/resources/testData/" + fileName);
			String absolutePath = file.getAbsolutePath();
			return absolutePath;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
			return "Error: Unable to read the file.";
		}
	}
}