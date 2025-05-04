/**
 * Package: com.kpit.automation.helpers
 *
 * Class: VerificationHelper
 *
 * This class extends LoggerUtility and provides a method to verify elements on web page.
 *
 * @author Aakash Saxena
 * @version 1.0
 * @since 2023-12-01
 */
package com.kpit.automation.helpers;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import com.kpit.automation.utilities.LoggerUtility;

/**
 * Class: VerificationHelper
 *
 * This class extends LoggerUtility and provides a method to verify elements on web page.
 *
 * @author Aakash Saxena
 * @version 1.0
 * @since 2023-12-01
 */
public class VerificationHelper extends LoggerUtility {

	/**
	 * Logger: log
	 *
	 * This is the logger instance for the VerificationHelper class.
	 */
	private Logger log = getLogger(VerificationHelper.class);

	/**
	 * Verifies if the given WebElement is present on the page.
	 *
	 * @param element The WebElement to verify.
	 * @return True if the element is present, false otherwise.
	 */
	public boolean verifyElementPresent(WebElement element) {
		boolean isDisplayed = false;
		try {
			isDisplayed = element.isDisplayed();	// Check if element is displayed
			log.info("Element is present on the page");
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
		}
		return isDisplayed;
	}

	/**
	 * Verifies if the given WebElement is displayed on the page.
	 *
	 * @param element The WebElement to verify.
	 * @return True if the element is displayed, false otherwise.
	 */
	public boolean verifyElementIsDisplayed(WebElement element) {
		boolean flag = false;
		try {
			element.isDisplayed();	// Check if element is displayed
			flag = true;
			log.info("Element is displayed on the page");
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
		}
		return flag;
	}

	/**
	 * Verifies if the given WebElement is enabled on the page.
	 *
	 * @param element The WebElement to verify.
	 * @return True if the element is enabled, false otherwise.
	 */
	public boolean verifyElementIsEnabled(WebElement element) {
		boolean flag = false;
		try {
			element.isEnabled();	// Check if element is enabled
			flag = true;
			log.info("Element is enabled on the page");
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
		}
		return flag;
	}

	/**
	 * Verifies if the given WebElement is disabled on the page.
	 *
	 * @param element The WebElement to verify.
	 * @return True if the element is disabled, false otherwise.
	 */
	public boolean verifyElementIsDisabled(WebElement element) {
		boolean isDisabled = false;
		try {
			isDisabled = !element.isEnabled();	// Check if element is disabled
			log.info("Element is disabled on the page");
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
		}
		return isDisabled;
	}
	
	/**
	 * Verifies if the given WebElement is selected.
	 *
	 * @param element The WebElement to verify.
	 * @return True if the element is selected, false otherwise.
	 */
	public boolean verifyElementSelected(WebElement element) {
		boolean isSelected = false;
		try {
			isSelected = element.isSelected();		// Check if element is not selected
			log.info("Element is present on the page");
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
		}
		return isSelected;
	}

	/**
	 * Verifies if the given WebElement is not selected.
	 *
	 * @param element The WebElement to verify.
	 * @return True if the element is not selected, false otherwise.
	 */
	public boolean verifyElementNotSelected(WebElement element) {
		boolean isSelected = false;
		try {
			isSelected = !element.isSelected();		// Check if element is not selected
			log.info("Element is present on the page");
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
		}
		return isSelected;
	}

	/**
	 * Verifies if the text of the given WebElement is equal to the given text.
	 *
	 * @param element The WebElement to verify.
	 * @param text    The text to compare with.
	 * @return True if the texts are equal, false otherwise.
	 */
	public boolean verifyTextEquals(WebElement element, String text) {
		boolean flag = false;
		try {
			String actualText = element.getText();	// Get text from the element
			if (actualText.equals(text)) {
				log.info("Element text and given text is equal");
				return flag = true;
			} else {
				return flag;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
		}
		return flag;
	}

	/**
	 * Reads the text value from the given WebElement.
	 *
	 * @param element The WebElement to read from.
	 * @return The text value of the element, or null if the element is not
	 *         displayed.
	 */
	public String readTextValueFromElement(WebElement element) {
		boolean displayed = false;
		String text = null;
		try {
			displayed = verifyElementIsDisplayed(element);
			if (!displayed) {
				return null;
			}
			text = element.getText();
			log.info("Element Text Is : " + text);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
		}
		return text;
	}

	/**
	 * Reads the value from the input field of the given WebElement.
	 *
	 * @param element The WebElement to read from.
	 * @return The value of the input field, or null if the element is not
	 *         displayed.
	 */
	public String readValueFromInput(WebElement element) {
		String value = null;
		try {
			if (!verifyElementIsDisplayed(element)) {
				return null;
			}
			value = element.getAttribute("value");
			log.info("Element Text Is : " + value);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
		}
		return value;
	}
}