/**
 * BrowserHelper class provides methods to interact with the browser window and frames.
 *
 * @author Aakash Saxena
 * @version 1.0
 * @since 2023-12-01
 */
package com.kpit.automation.helpers;

import java.util.LinkedList;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.kpit.automation.utilities.LoggerUtility;

/**
 * BrowserHelper class extends LoggerUtility and provides methods to interact with the browser window and frames.
 *
 * @author Aakash Saxena
 * @version 1.0
 * @since 2023-12-01
 */
public class BrowserHelper extends LoggerUtility {

	/**
	 * Logger instance for logging messages.
	 */
	private Logger log = getLogger(BrowserHelper.class);
	
	/**
	 * WebDriver instance used to interact with the web application.
	 */
	private WebDriver driver;

	/**
	 * Constructor to initialize the BrowserHelper class with a WebDriver instance.
	 *
	 * @param driver WebDriver instance for interacting with the browser.
	 */
	public BrowserHelper(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Get the current page URL of the browser.
	 *
	 * @return The current page URL as a String.
	 */
	public String getCurrentPageUrl() {
		String url = null;
		try {
			url = driver.getCurrentUrl(); // Getting current page URL
			log.info("Current page url : " + url); // Logging success
		} catch (Exception ex) {
			ex.printStackTrace(); // Printing stack trace if an exception occurs
			log.error(ex); // Logging the exception
		}
		return url;
	}

	/**
	 * Navigate the browser to the previous page.
	 */
	public void goBack() {
		try {
			driver.navigate().back(); // Navigating back on browser
			log.info("Browser navigate to previous page"); // Logging success
		} catch (Exception ex) {
			ex.printStackTrace(); // Printing stack trace if an exception occurs
			log.error(ex); // Logging the exception
		}
	}

	/**
	 * Navigate the browser to the next page.
	 */
	public void goForward() {
		try {
			driver.navigate().forward(); // Navigating forward on browser
			log.info("Browser navigate to front page"); // Logging success
		} catch (Exception ex) {
			ex.printStackTrace(); // Printing stack trace if an exception occurs
			log.error(ex); // Logging the exception
		}
	}

	/**
	 * Refresh the current page of the browser.
	 */
	public void refresh() {
		try {
			driver.navigate().refresh(); // Refreshing the web page
			log.info("Browser refresh the current page"); // Logging success
		} catch (Exception ex) {
			ex.printStackTrace(); // Printing stack trace if an exception occurs
			log.error(ex); // Logging the exception
		}
	}
	
	/**
	 * Close the current page of the browser.
	 */
	public void close() {
		try {
			driver.close(); // Closing the current web page
			log.info("Browser close the current page"); // Logging success
		} catch (Exception ex) {
			ex.printStackTrace(); // Printing stack trace if an exception occurs
			log.error(ex); // Logging the exception
		}
	}

	/**
	 * Get the set of window handles (unique alphanumeric ids) of the browser
	 * windows.
	 *
	 * @return The set of window handles as a Set of Strings.
	 */
	public Set<String> getWindowHandles() {
		try {
			log.info("Capturing windows unique alphanumeric ids"); // Logging
		} catch (Exception ex) {
			ex.printStackTrace(); // Printing stack trace if an exception occurs
			log.error(ex); // Logging the exception
		}
		return driver.getWindowHandles();
	}

	/**
	 * Switch to a specific window by its index.
	 *
	 * @param index The index of the window to switch to.
	 */
	public void SwitchToWindow(int index) {
		try {
			LinkedList<String> windowsId = new LinkedList<String>(getWindowHandles());
			if (index < 0 || index > windowsId.size()) {
				throw new IllegalArgumentException("Window handle has invalid index : " + index); // Throwing exception for invalid index
			}
			driver.switchTo().window(windowsId.get(index)); // Switching to window by index
			log.info("Switch to window with index : " + index); // Logging success
		} catch (Exception ex) {
			ex.printStackTrace(); // Printing stack trace if an exception occurs
			log.error(ex); // Logging the exception
		}
	}

	/**
	 * Switch to the parent window of the browser.
	 */
	public void switchToParentWindow() {
		try {
			LinkedList<String> windowsid = new LinkedList<String>(getWindowHandles());
			driver.switchTo().window(windowsid.get(0)); // Switching to parent window
			log.info("Switch to parent window"); // Logging success
		} catch (Exception ex) {
			ex.printStackTrace(); // Printing stack trace if an exception occurs
			log.error(ex); // Logging the exception
		}
	}

	/**
	 * Switch to the parent window of the browser and close all child windows.
	 */
	public void switchToParentWithChildClose() {
		try {
			LinkedList<String> windowsid = new LinkedList<String>(getWindowHandles());
			for (int i = 1; i < windowsid.size(); i++) { // Iterating through child windows
				log.info("Child window id : " + windowsid.get(i)); // Logging child window id
				driver.switchTo().window(windowsid.get(i)); // Switching to child window
				driver.close(); // Closing child window
			}
			switchToParentWindow(); // Switching back to parent window
		} catch (Exception ex) {
			ex.printStackTrace(); // Printing stack trace if an exception occurs
			log.error(ex); // Logging the exception
		}
	}

	/**
	 * Switch to a specific frame by its name or id.
	 *
	 * @param nameOrid The name or id of the frame to switch to.
	 */
	public void switchToFrame(String nameOrid) {
		try {
			driver.switchTo().frame(nameOrid); // Switching to frame by name or id
			log.info("Switch to frame with name or id : " + nameOrid); // Logging success
		} catch (Exception ex) {
			ex.printStackTrace(); // Printing stack trace if an exception occurs
			log.error(ex); // Logging the exception
		}
	}
}