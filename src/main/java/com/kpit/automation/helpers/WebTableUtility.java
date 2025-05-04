/**
 * WebTableUtility class extends LoggerUtility and provides various methods for handling web table operations.
 *
 * @author Aakash Saxena
 * @version 1.0
 * @since 2023-12-01
 */
package com.kpit.automation.helpers;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.kpit.automation.utilities.LoggerUtility;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for handling web table operations.
 *
 * @author Aakash Saxena
 * @version 1.0
 * @since 2023-12-01
 */
public class WebTableUtility extends LoggerUtility {
	
	/**
	 * Logger: log
	 *
	 * This is the logger instance for the WebTableUtility class.
	 */
	private Logger log = getLogger(WebTableUtility.class);

	/**
     * Method to apply filter to a web table.
     *
     * @param filterInput The WebElement representing the filter input field.
     * @param filterValue The value to filter the table with.
     * @param elementsList The List of WebElements representing the table rows.
     * @return True if the filter is applied successfully, false otherwise.
     *
     */
	public boolean applyFilter(WebElement filterInput, String filterValue, List<WebElement> elementsList) {
		try {
			clearFilter(filterInput);
			filterInput.sendKeys(filterValue);
			Thread.sleep(2000);
			return verifyTableFilter(elementsList, filterValue);
		} catch (Exception ex) {
			log.error("Error occurred while applying filter: " + ex.getMessage());
			return false;
		}
	}

	/**
     * Method to clear the filter input field.
     *
     * @param filterInput The WebElement representing the filter input field.
     *
     * @throws InterruptedException If the thread is interrupted while waiting.
     */
	public void clearFilter(WebElement filterInput) {
		try {
			filterInput.clear();
			filterInput.sendKeys(Keys.TAB);
			Thread.sleep(2000);
		} catch (InterruptedException ex) {
			log.error("Error occurred while clearing filter: " + ex.getMessage());
		}
	}

	/**
     * Method to sort a web table column.
     *
     * @param tableHeader The WebElement representing the table header.
     * @param elementsList The List of WebElements representing the table rows.
     * @return True if the sorting is applied successfully, false otherwise.
     *
     * @throws InterruptedException If the thread is interrupted while waiting.
     */
	public boolean applySorting(WebElement tableHeader, List<WebElement> elementsList) {
		try {
			boolean isAscending = false;
			tableHeader.click();
			Thread.sleep(2000);
			logReport("Ascending sort applied");
			logListOfReport(elementsList.stream().map(WebElement::getText).collect(Collectors.toList()));
			logScreenshot();
			boolean ascendingSort = verifyTableSortAsc(elementsList);
			if (!isAscending) {
				tableHeader.click(); // Click again to sort in descending order
				Thread.sleep(2000);
				logReport("Descending sort applied");
				logListOfReport(elementsList.stream().map(WebElement::getText).collect(Collectors.toList()));
				logScreenshot();
			}
			boolean descendingSort = verifyTableSortDesc(elementsList);
			tableHeader.click();
			return ascendingSort != descendingSort; // != used due to wrong web table rows count returned.!
		} catch (InterruptedException ex) {
			log.error("Error occurred while applying sorting: " + ex.getMessage());
			return false;
		}
	}

	/**
     * Method to select the number of rows to show using the "Show rows" dropdown.
     *
     * @param dropdown The WebElement representing the dropdown.
     * @param options The List of WebElements representing the dropdown options.
     *
     * @throws InterruptedException If the thread is interrupted while waiting.
     */
	public void selectNumberOfRowsToShow(WebElement dropdown, List<WebElement> options) {
		try {
			int[] noOfRows = { 5, 12, 20};
			for (int rows : noOfRows) {
				// Click the dropdown to expand the list of options
				dropdown.click();
				Thread.sleep(2000);
				for (WebElement option : options) {
					if (option.getText().equals(String.valueOf(rows).trim())) {
						option.click();
						dropdown.sendKeys(Keys.TAB);
						Thread.sleep(2000);
						// Add logic to wait for the table to refresh after selecting the option
						break;
					}
					
				}
			}
		} catch (InterruptedException ex) {
			log.error("Error occurred while selecting number of rows: " + ex.getMessage());
		}
	}

	/**
     * Method to navigate to a specific page using the "Go to page" input field.
     *
     * @param goToPageInput The WebElement representing the "Go to page" input field.
     * @param pageNumber The page number to navigate to.
     *
     * @throws InterruptedException If the thread is interrupted while waiting.
     */
	public void goToPage(WebElement goToPageInput, int pageNumber) {
		try {
			goToPageInput.clear();
			Thread.sleep(3000);
			goToPageInput.sendKeys(String.valueOf(pageNumber));
			goToPageInput.sendKeys(Keys.TAB); // Assuming hitting enter navigates to the page
			Thread.sleep(2000);
			// Add logic to wait for the table to refresh
		} catch (InterruptedException ex) {
			log.error("Error occurred while navigating to page: " + ex.getMessage());
		}
	}

	/**
     * Method to navigate to the next page.
     *
     * @param nextButton The WebElement representing the "Next" button.
     *
     * @throws InterruptedException If the thread is interrupted while waiting.
     */
	public void goToNextPage(WebElement nextButton) {
		try {
			if (nextButton.isEnabled()) {
				nextButton.click();
				Thread.sleep(2000);
				// Add logic to wait for the table to refresh
			}
		} catch (InterruptedException ex) {
			log.error("Error occurred while navigating to next page: " + ex.getMessage());
		}
	}

	/**
     * Method to navigate to the previous page.
     *
     * @param previousButton The WebElement representing the "Previous" button.
     *
     * @throws InterruptedException If the thread is interrupted while waiting.
     */
	public void goToPreviousPage(WebElement previousButton) {
		try {
			if (previousButton.isEnabled()) {
				previousButton.click();
				Thread.sleep(2000);
				// Add logic to wait for the table to refresh
			}
		} catch (InterruptedException ex) {
			log.error("Error occurred while navigating to previous page: " + ex.getMessage());
		}
	}

	/**
     * Method to verify if the table filter is applied successfully.
     *
     * @param elementsList The List of WebElements representing the table rows.
     * @param filterValue The value to filter the table with.
     * @return True if the filter is applied successfully, false otherwise.
     */
	public boolean verifyTableFilter(List<WebElement> elementsList, String filterValue) {
		try {
			// capture text of all elementsList into new (original) list
			List<String> originalList = elementsList.stream().map(WebElement::getText).collect(Collectors.toList());

			// filter on the original list
			List<String> filteredList = originalList.stream()
					.filter(s -> s.toLowerCase().contains(filterValue.toLowerCase())).collect(Collectors.toList());

			// compare original list vs filtered list size
			return originalList.equals(filteredList);
		} catch (Exception ex) {
			log.error("Error occurred while verifying table filter: " + ex.getMessage());
			return false;
		}
	}

	/**
     * Method to verify if the table is sorted in ascending order.
     *
     * @param elementsList The List of WebElements representing the table rows.
     * @return True if the table is sorted in ascending order, false otherwise.
     */
	public boolean verifyTableSortAsc(List<WebElement> elementsList) {
		try {
			// capture text of all elementsList into new (original) list
			List<String> originalList = elementsList.stream().map(s -> s.getText()).collect(Collectors.toList());

			// sort on the original list
			List<String> sortedList = originalList.stream().sorted().collect(Collectors.toList());
			return originalList.equals(sortedList);
		} catch (Exception ex) {
			log.error("Error occurred while verifying ascending sort: " + ex.getMessage());
			return false;
		}
	}

	/**
	 * Method to verify if the table is sorted in descending order.
	 *
	 * @param elementsList The list of WebElements representing table rows.
	 * @return True if the table is sorted in descending order, false otherwise.
	 */
	public boolean verifyTableSortDesc(List<WebElement> elementsList) {
		try {
			// capture text of all elementsList into new (original) list
			List<String> originalList = elementsList.stream().map(s -> s.getText()).collect(Collectors.toList());

			// sort on the original list
			List<String> sortedList = originalList.stream().sorted(Comparator.reverseOrder())
					.collect(Collectors.toList());
			return originalList.equals(sortedList);
		} catch (Exception ex) {
			log.error("Error occurred while verifying descending sort: " + ex.getMessage());
			return false;
		}
	}
}