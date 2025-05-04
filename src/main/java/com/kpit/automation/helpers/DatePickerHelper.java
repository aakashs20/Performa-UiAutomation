/**
 * A helper class for handling date picker elements.
 *
 * @author Aakash Saxena
 * @version 1.0
 * @since 2023-12-01
 */
package com.kpit.automation.helpers;

import java.time.Duration;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.kpit.automation.utilities.LoggerUtility;

/**
 * A class for handling date picker elements.
 *
 * @author Aakash Saxena
 * @version 1.0
 * @since 2023-12-01
 */
public class DatePickerHelper extends LoggerUtility {

	/**
	 * The logger instance.
	 */
	private Logger log = Logger.getLogger(DatePickerHelper.class);
	private WebDriverWait wait;

	/**
	 * The WebDriver instance.
	 */
	private WebDriver driver;

	/**
	 * Constructor for DatePickerHelper.
	 *
	 * @param driver The WebDriver instance.
	 */
	public DatePickerHelper(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(20));
	}

	public WebElement testDatePicker(WebElement selectDate, String date) {
		WebElement eleDate = null;
		try {
			log.info("Wait for Date picker is clickable or not");
			wait.until(ExpectedConditions.elementToBeClickable(selectDate));
			log.info("Date picker clicked");
			selectDate.click();

			log.info("Waiting for calendar to be visible");
			wait.until(ExpectedConditions
					.presenceOfAllElementsLocatedBy(By.xpath("//tr//div[contains(@class,'arrow-right')]")));

			log.info("Finds the button to move to the next month in the calendar.");
			List<WebElement> nextLink = driver.findElements(By.xpath("//tr//div[contains(@class,'arrow-right')]"));
			log.info("Finds the button to click in the center of the calendar header.");
			List<WebElement> midLink = driver.findElements(By.xpath("//td[contains(@id,'calendarTitleHeader')]/div"));
			log.info("Finds the button to move to the previous month in the calendar.");
			List<WebElement> previousLink = driver.findElements(By.xpath("//tr//div[contains(@class,'arrow-left')]"));

			String[] dateComponents = date.split(" ");
			String[] dayMonthYear = dateComponents[0].split("/");
			int day = Integer.parseInt(dayMonthYear[0]);
			log.info("day is: " + day);
			int month = Integer.parseInt(dayMonthYear[1]) - 1;
			log.info("month is: " + month);
			int year = Integer.parseInt(dayMonthYear[2]);
			log.info("year is: " + year);

			int currentYear = Calendar.getInstance().get(Calendar.YEAR);
			log.info("current year is: " + currentYear);
			int yearDiff = year - currentYear;
			log.info("yearDiff is: " + yearDiff);

			for (WebElement elementMid : midLink) {
				Thread.sleep(2000);
				if (elementMid.isDisplayed()) {
					log.info("Wait for Year element is clickable or not");
					wait.until(ExpectedConditions.elementToBeClickable(elementMid));
					elementMid.getText();
					log.info("Year to be clicked is: " + elementMid.getText());
					elementMid.click();
					log.info("Year element clicked");
					break;
				}
			}

			if (yearDiff != 0) {
				if (yearDiff > 0) {
					for (int i = 0; i < yearDiff; i++) {
						clickVisibleElement(nextLink);
						log.info("Next element clicked");
					}
				} else {
					for (int i = 0; i < (-yearDiff); i++) {
						clickVisibleElement(previousLink);
						log.info("Previous element clicked");
					}
				}
			}

			log.info("Wait for Finds all the months in the calendar.");
			wait.until(ExpectedConditions
					.presenceOfAllElementsLocatedBy(By.xpath("//table//tbody//td[contains(@class,'cell-year')]")));

			Thread.sleep(2000);

			log.info("Finds all the months in the calendar.");
			List<WebElement> list_AllMonthToBook = driver
					.findElements(By.xpath("//table//tbody//td[contains(@class,'cell-year')]"));

			log.info("Month to be clicked is: " + list_AllMonthToBook.get(month).getText());

			Thread.sleep(2000);

			log.info("Clicks the month corresponding to the date's month.");
			list_AllMonthToBook.get(month).click();

			log.info("Wait for Finds all the dates in the calendar.");
			List<WebElement> list_AllDateToBook = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
					By.xpath("//table[contains(@id,'innerCalendar')]//tbody//td[not(contains(@class,'othermonth'))]")));

			for (WebElement element : list_AllDateToBook) {
				log.info("Finds all the dates in the calendar.");
				Thread.sleep(2000);
				if (element.isDisplayed()) {
					log.info("Expected date is displayed");
					eleDate = list_AllDateToBook.get(day - 1);
					log.info("Date to be clicked is: " + eleDate.getText());
					break;
				}
			}
			log.info("Returns the WebElement representing the selected date.");
			return eleDate;
		} catch (Exception ex) {
			log.error("An error occurred while selecting a date in the date picker.", ex);
			return null;
		}
	}

	private void clickVisibleElement(List<WebElement> elements) {
		for (WebElement element : elements) {
			if (element.isDisplayed()) {
				try {
					element.click();
					break;
				} catch (StaleElementReferenceException e) {
					log.error("StaleElementReferenceException caught, retrying...", e);
					continue;
				}
			}
		}
	}

	public void clearDate(WebElement selectDate) {
		try {
			log.info("Wait for Date picker is clickable or not");
			wait.until(ExpectedConditions.elementToBeClickable(selectDate));
			Thread.sleep(2000);
			selectDate.click();
			log.info("Date picker clicked to clear the selected date");
			log.info("Wait for clear date button.");
			WebElement clearButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Clear')]")));
			Thread.sleep(2000);
			clearButton.click();
			log.info("Selected date Cleared");
		} catch (Exception ex) {
			log.error("An error occurred while clearing the date in the date picker.", ex);
		}
	}

}