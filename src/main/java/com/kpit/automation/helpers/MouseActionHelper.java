/**
 * This class provides methods to perform mouse actions such as hover, click, double click, and right click on web elements.
 *
 * @author Aakash Saxena
 * @version 1.0
 * @since 2023-12-01
 */
package com.kpit.automation.helpers;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.kpit.automation.utilities.LoggerUtility;

/**
 * This class extends {@link LoggerUtility} and provides methods to perform mouse actions.
 *
 * @author Aakash Saxena
 * @version 1.0
 * @since 2023-12-01
 */
public class MouseActionHelper extends LoggerUtility {

	/**
	 * The Logger instance for this class.
	 */
	private Logger log = getLogger(MouseActionHelper.class);

	/**
	 * The WebDriver instance used for performing mouse actions.
	 */
	private WebDriver driver;

	/**
	 * Constructor to initialize the WebDriver instance.
	 *
	 * @param driver The WebDriver instance used for performing mouse actions.
	 */
	public MouseActionHelper(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Method to perform a mouse hover action on an element and retrieve its tooltip
	 * text.
	 *
	 * @param element1 The first web element to hover over.
	 * @param element2 The second web element whose tooltip text is to be retrieved.
	 * @return The tooltip text of the second element.
	 * @throws Exception If any exception occurs during the mouse hover action.
	 */
	public String mouseHover(WebElement element1, WebElement element2) throws Exception {
		try {
			Actions builder = new Actions(driver);
			builder.moveToElement(element1).build().perform(); // Performing mouse hover action
			String tooltipText = element2.getText(); // Retrieving tooltip text
			log.info("Mouse hover and get tooltip text: " + tooltipText); // Logging success
			return tooltipText;
		} catch (Exception ex) {
			ex.printStackTrace(); // Printing stack trace if an exception occurs
			log.error(ex); // Logging the exception
			return null;
		}
	}
	


	public String mouseHover(WebElement element) throws Exception {
		try {
			Actions builder = new Actions(driver);
			builder.moveToElement(element).build().perform(); // Performing mouse hover action
			String tooltipText = element.getAttribute("title"); // Retrieving tooltip text
			log.info("Mouse hover and get tooltip text: " + tooltipText); // Logging success
			return tooltipText;
		} catch (Exception ex) {
			ex.printStackTrace(); // Printing stack trace if an exception occurs
			log.error(ex); // Logging the exception
			return null;
		}
	}


	/**
	 * Method to perform a mouse hover action on an element and click on another
	 * element.
	 *
	 * @param element1 The first web element to hover over.
	 * @param element2 The second web element to click on.
	 * @throws Exception If any exception occurs during the mouse hover and click
	 *                   action.
	 */
	public void mouseHoverAndClick(WebElement element1, WebElement element2) throws Exception {
		try {
			Actions builder = new Actions(driver);
			builder.moveToElement(element1).build().perform(); // Performing mouse hover action
			element2.click(); // Clicking on the second element
			log.info("Mouse hover and click on element is successful"); // Logging success
		} catch (Exception ex) {
			ex.printStackTrace(); // Printing stack trace if an exception occurs
			log.error(ex); // Logging the exception
		}
	}

	/**
	 * Method to perform a click action on an element after hovering over it.
	 *
	 * @param element1 The web element to hover over before clicking.
	 * @throws Exception If any exception occurs during the mouse hover and click action.
	 */
	public void moveToElementAndClick(WebElement element1) throws Exception {
		try {
			Actions builder = new Actions(driver);
			builder.moveToElement(element1).click().perform(); // Performing mouse hover and click action
			log.info("Click on element is successful"); // Logging success
		} catch (Exception ex) {
			ex.printStackTrace(); // Printing stack trace if an exception occurs
			log.error(ex); // Logging the exception
		}
	}

	/**
	 * Method to perform a double click action on a web element.
	 *
	 * @param element The web element to perform a double click action on.
	 * @throws Exception If any exception occurs during the double click action.
	 */
	public void doubleClick(WebElement element) throws Exception {
		try {
			Actions builder = new Actions(driver);
			Thread.sleep(2000);
			builder.doubleClick(element).build().perform(); // Performing double click action
			log.info("Double click on element is successful"); // Logging success
		} catch (Exception ex) {
			ex.printStackTrace(); // Printing stack trace if an exception occurs
			log.error(ex); // Logging the exception
		}
	}

	/**
	 * Method to perform a right click action on a web element.
	 *
	 * @param element The web element to perform a right click action on.
	 * @throws Exception If any exception occurs during the right click action.
	 */
	public void rightClick(WebElement element) throws Exception {
		try {
			Actions builder = new Actions(driver);
			builder.contextClick(element).build().perform(); // Performing right click action
			log.info("Right click on element is successful"); // Logging success
		} catch (Exception ex) {
			ex.printStackTrace(); // Printing stack trace if an exception occurs
			log.error(ex); // Logging the exception
		}
	}
}