/**
 * AlertHelper class provides methods to interact with alert pop-ups in a web application.
 *
 * @author Aakash Saxena
 * @version 1.0
 * @since 2023-12-01
 */
package com.kpit.automation.helpers;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import com.kpit.automation.utilities.LoggerUtility;

/**
 * AlertHelper class extends LoggerUtility and provides methods to interact with alert pop-ups in a web application.
 *
 * @author Aakash Saxena
 * @version 1.0
 * @since 2023-12-01
 */
public class AlertHelper extends LoggerUtility {

	/**
	 * Logger instance for logging messages.
	 */
	private Logger log = getLogger(AlertHelper.class);

	/**
	 * WebDriver instance used to interact with the web application.
	 */
	private WebDriver driver;

	/**
	 * Constructor for AlertHelper class.
	 *
	 * @param driver WebDriver instance used to interact with the web application.
	 */
	public AlertHelper(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Switches to the alert pop-up and returns the Alert instance.
	 *
	 * @return Alert instance representing the alert popup.
	 * @throws Exception If an exception occurs while switching to the alert popup.
	 */
	public Alert getAlert() {
		try {
			log.info("Switch to alert popup is successful");
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
		}
		return driver.switchTo().alert();
	}

	/**
	 * Accepts the alert pop-up.
	 *
	 * @throws Exception If an exception occurs while accepting the alert popup.
	 */
	public void acceptAlert() {
		try {
			getAlert().accept(); // Accepting the alert
			log.info("Alert popup accept is successful"); // Logging success
		} catch (Exception ex) {
			ex.printStackTrace(); // Printing stack trace if an exception occurs
			log.error(ex); // Logging the exception
		}
	}

	/**
	 * Dismisses the alert popup.
	 *
	 * @throws Exception If an exception occurs while dismissing the alert popup.
	 */
	public void dismissAlert() {
		try {
			getAlert().dismiss(); // Dismissing the alert
			log.info("Alert popup dismiss is successful"); // Logging success
		} catch (Exception ex) {
			ex.printStackTrace(); // Printing stack trace if an exception occurs
			log.error(ex); // Logging the exception
		}
	}

	/**
	 * Retrieves the text of the alert popup.
	 *
	 * @return String representing the text of the alert popup.
	 * @throws Exception If an exception occurs while retrieving the text of the
	 *                   alert popup.
	 */
	public String getAlertText() {
		String text = null;
		try {
			text = getAlert().getText(); // Retrieving text of the alert
			log.info("Alert popup text is : " + text); // Logging the text
		} catch (Exception ex) {
			ex.printStackTrace(); // Printing stack trace if an exception occurs
			log.error(ex); // Logging the exception
		}
		return text;
	}

	/**
	 * Checks if an alert popup is present.
	 *
	 * @return boolean indicating whether an alert popup is present.
	 * @throws Exception If an exception occurs while checking if an alert popup is
	 *                   present.
	 */
	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert(); // Switching to alert to check its presence
			log.info("Alert popup is present : " + true); // Logging presence of alert
			return true; // Returning true if alert is present
		} catch (NoAlertPresentException ex) {
			log.info("Alert popup is not present : " + false); // Logging absence of alert
			log.error(ex); // Logging the exception
			return false; // Returning false if alert is not present
		}
	}

	/**
	 * Accepts the alert popup if it is present.
	 *
	 * @throws Exception If an exception occurs while accepting the alert popup.
	 */
	public void acceptAlertIfPresent() {
		try {
			if (!isAlertPresent()) { // Checking if alert is present
				return; // Returning if alert is not present
			}
			log.info("Alert present - alert popup accept is successful"); // Logging success
			getAlertText(); // Retrieving alert text
			acceptAlert(); // Accepting alert
		} catch (Exception ex) {
			ex.printStackTrace(); // Printing stack trace if an exception occurs
			log.error(ex); // Logging the exception
		}
	}

	/**
	 * Dismisses the alert popup if it is present.
	 *
	 * @throws Exception If an exception occurs while dismissing the alert popup.
	 */
	public void dismissAlertIfPresent() {
		try {
			if (!isAlertPresent()) { // Checking if alert is present
				return; // Returning if alert is not present
			}
			log.info("Alert present - alert popup dismiss is successful"); // Logging success
			dismissAlert(); // Dismissing alert
		} catch (Exception ex) {
			ex.printStackTrace(); // Printing stack trace if an exception occurs
			log.error(ex); // Logging the exception
		}
	}

	/**
	 * Accepts the alert popup with a specified text.
	 *
	 * @param text String representing the text to be entered in the alert popup.
	 * @throws Exception If an exception occurs while accepting the alert popup.
	 */
	public void acceptPrompt(String text) {
		try {
			if (!isAlertPresent()) { // Checking if alert is present
				return; // Returning if alert is not present
			}
			Alert alert = getAlert(); // Getting alert instance
			alert.sendKeys(text); // Entering text in the prompt
			alert.accept(); // Accepting the alert
			log.info("Alert present - alert prompt popup accept is successful"); // Logging success
		} catch (Exception ex) {
			ex.printStackTrace(); // Printing stack trace if an exception occurs
			log.error(ex); // Logging the exception
		}
	}
}