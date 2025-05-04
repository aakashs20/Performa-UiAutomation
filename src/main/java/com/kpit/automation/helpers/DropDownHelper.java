package com.kpit.automation.helpers;

import java.time.Duration;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.kpit.automation.utilities.LoggerUtility;

public class DropDownHelper extends LoggerUtility {

	private Logger log = getLogger(DropDownHelper.class);

	private WebDriver driver;
	private WebDriverWait wait;

	public DropDownHelper(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
	}

	@FindBy(xpath = "//span[contains(text(),'Select Filter')]")
	private WebElement filterElement;

	@FindBy(xpath = "//div[@role='option']//span[contains(@class,'listitem')]")
	private List<WebElement> allOptions;

	public void selectDropDownValue(WebElement element, List<WebElement> dropDownElements, String valueToSelect) {
		try {
			if (element.isDisplayed()) {
				element.click();
				Thread.sleep(2000);
			}
			for (WebElement dropDownElement : dropDownElements) {
				if (dropDownElement.getText().contains(valueToSelect)) {
					Thread.sleep(2000);
					dropDownElement.click();
					log.info("Selected value from dropdown: " + valueToSelect);
					break;
				} else {
					log.error("Value not found in the dropdown: " + valueToSelect);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
		}
	}

	public void enterDropDownValue(WebElement element, List<WebElement> dropDownElements, String valueToSelect) {
		try {
			if (element.isDisplayed()) {
				element.sendKeys(valueToSelect);
				Thread.sleep(2000);
			}
			for (WebElement dropDownElement : dropDownElements) {
				if (dropDownElement.getText().contains(valueToSelect)) {
					Thread.sleep(2000);
					dropDownElement.click();
					log.info("Selected value from dropdown: " + valueToSelect);
					break;
				} else {
					log.error("Value not found in the dropdown: " + valueToSelect);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
		}
	}

	public void multiSelectDropDown(String chkBoxValue) throws Exception {
		try {
			log.info("Attempting to click on filter element");
			wait.until(ExpectedConditions.elementToBeClickable(filterElement));
			filterElement.click();

			log.info("Fetching all available options");
			wait.until(ExpectedConditions.visibilityOfAllElements(allOptions));

			for (WebElement option : allOptions) {
				log.info("Checking option: " + option.getText());
				if (option.getText().contains(chkBoxValue)) {
					log.info("Found matching option: " + option.getText() + ", clicking it");
					wait.until(ExpectedConditions.elementToBeClickable(option));
					option.click();
					filterElement.click();
					break;
				}
			}
		} catch (Exception ex) {
			log.error("Error in multiSelectDropDown: ", ex);
			throw ex; // Re-throw the exception after logging it
		}
	}

}
