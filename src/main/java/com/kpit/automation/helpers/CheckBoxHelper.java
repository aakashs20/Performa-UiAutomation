/**
 * Package: com.kpit.automation.helpers
 *
 * Class: CheckBoxHelper
 *
 * This class extends LoggerUtility and provides a method to select a checkbox.
 *
 * @author Snehal Karande
 * @version 1.0
 * @since 2023-12-01
 */
package com.kpit.automation.helpers;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.kpit.automation.utilities.LoggerUtility;

/**
 * Class: CheckBoxHelper
 *
 * This class extends LoggerUtility and provides a method to select a checkbox.
 *
 * @author Snehal Karande
 * @version 1.0
 * @since 2023-12-01
 */
public class CheckBoxHelper extends LoggerUtility {

	/**
	 * Logger: log
	 *
	 * This is the logger instance for the CheckBoxHelper class.
	 */
	private Logger log = Logger.getLogger(CheckBoxHelper.class);

	/**
	 * Method: selectCheckBox
	 *
	 * This method selects a checkbox by clicking on it. It logs an info message if
	 * successful, and logs an error message if an exception occurs.
	 *
	 * @param element The WebElement representing the checkbox to be selected.
	 */
	public void selectCheckBox(WebElement element) {
		try {
			if (!element.isSelected()) {
				element.click();
				log.info("Checkbox is selected.");
			} else {
				log.info("Checkbox is already selected.");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
		}
	}
}