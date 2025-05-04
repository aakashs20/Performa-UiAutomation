/**
 * WaitHelper class extends LoggerUtility and provides various methods for handling WebDriver waits.
 *
 * @author Aakash Saxena
 * @version 1.0
 * @since 2023-12-01
 */
package com.kpit.automation.helpers;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.kpit.automation.utilities.LoggerUtility;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Class for handling WebDriver waits.
 *
 * @author Aakash Saxena
 * @version 1.0
 * @since 2023-12-01
 */
public class WaitHelper extends LoggerUtility {

	/**
	 * Logger: log
	 *
	 * This is the logger instance for the WaitHelper class.
	 */
	private Logger log = getLogger(WaitHelper.class);

	private WebDriver driver;
	private WebDriverWait wait;

	/**
	 * Constructor for WaitHelper.
	 *
	 * @param driver the WebDriver instance
	 */
	public WaitHelper(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(1200)); // Default timeout of 10 seconds
	}

	// Explicit Waits

	/**
	 * Method for for waiting until an alert is present.
	 */
	public void waitForAlertIsPresent() {
		wait.until(ExpectedConditions.alertIsPresent());
	}

	/**
	 * Method for waiting until an element's selection state is true.
	 *
	 * @param element    the WebElement instance
	 * @param isSelected the boolean value indicating the desired selection state
	 */
	public void waitForElementSelectionStateToBe(WebElement element, boolean isSelected) {
		try {
			log.info("Wait for the element : " + element);
			wait.until(ExpectedConditions.elementSelectionStateToBe(element, isSelected));
		} catch (Exception ex) {
			log.info("Some error occured while wait for element : " + element);
			ex.printStackTrace();
			log.error(ex);
		}
	}

	/**
	 * Method for waiting until an element is clickable.
	 *
	 * @param element the WebElement instance
	 */
	public void waitForElementToBeClickable(WebElement element) {
		try {
			log.info("Wait for the element : " + element);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception ex) {
			log.info("Some error occured while wait for element : " + element);
			ex.printStackTrace();
			log.error(ex);
		}
	}

	/**
	 * Method for waiting until an element is selected.
	 *
	 * @param element the WebElement instance
	 */
	public void waitForElementToBeSelected(WebElement element) {
		try {
			log.info("Wait for the element : " + element);
			wait.until(ExpectedConditions.elementToBeSelected(element));
		} catch (Exception ex) {
			log.info("Some error occured while wait for element : " + element);
			ex.printStackTrace();
			log.error(ex);
		}
	}

	/**
	 * Method for waiting until a frame is available and switching to it.
	 *
	 * @param frameLocator the By instance representing the frame locator
	 */
	public void waitForFrameToBeAvailableAndSwitchToIt(By frameLocator) {
		try {
			log.info("Wait for the element : " + frameLocator);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
		} catch (Exception ex) {
			log.info("Some error occured while wait for element : " + frameLocator);
			ex.printStackTrace();
			log.error(ex);
		}
	}

	/**
	 * Method for waiting until an element is invisible.
	 *
	 * @param element the WebElement instance
	 */
	public void waitForInvisibilityOf(WebElement element) {
		try {
			log.info("Wait for the element : " + element);
			wait.until(ExpectedConditions.invisibilityOf(element));
		} catch (Exception ex) {
			log.info("Some error occured while wait for element : " + element);
			ex.printStackTrace();
			log.error(ex);
		}
	}

	/**
	 * Method for waiting until an element is invisible.
	 *
	 * @param by the By instance representing the element locator
	 */
	public void waitForInvisibilityOfTheElementLocated(By by) {
		try {
			log.info("Wait for the element : " + by);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		} catch (Exception ex) {
			log.info("Some error occured while wait for element : " + by);
			ex.printStackTrace();
			log.error(ex);
		}
	}

	/**
	 * Method for waiting until an element with specific text is invisible.
	 *
	 * @param by   the By instance representing the element locator
	 * @param text the String instance representing the text to be matched
	 */
	public void waitForInvisibilityOfElementWithText(By by, String text) {
		try {
			log.info("Wait for the element : " + by);
			wait.until(ExpectedConditions.invisibilityOfElementWithText(by, text));
		} catch (Exception ex) {
			log.info("Some error occured while wait for element : " + by);
			ex.printStackTrace();
			log.error(ex);
		}
	}

	/**
	 * Method for waiting until all elements are present.
	 *
	 * @param by the By instance representing the element locator
	 */
	public void waitForPresenceOfAllElementsLocatedBy(By by) {
		try {
			log.info("Wait for the element : " + by);
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
		} catch (Exception ex) {
			log.info("Some error occured while wait for element : " + by);
			ex.printStackTrace();
			log.error(ex);
		}
	}

	/**
	 * Method for waiting until an element is present.
	 *
	 * @param by the By instance representing the element locator
	 */
	public void waitForPresenceOfElementLocated(By by) {
		try {
			log.info("Wait for the element : " + by);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
		} catch (Exception ex) {
			log.info("Some error occured while wait for element : " + by);
			ex.printStackTrace();
			log.error(ex);
		}
	}

	/**
	 * Method for waiting until a text is present in an element.
	 *
	 * @param element the WebElement instance
	 * @param text    the String instance representing the text to be matched
	 */
	public void waitForTextToBePresentInElement(WebElement element, String text) {
		try {
			log.info("Wait for the element : " + element);
			wait.until(ExpectedConditions.textToBePresentInElement(element, text));
		} catch (Exception ex) {
			log.info("Some error occured while wait for element : " + element);
			ex.printStackTrace();
			log.error(ex);
		}
	}

	/**
	 * Waits for the specified text to be present in the element located by the
	 * given By object.
	 *
	 * @param by   the By object representing the locator strategy and target value
	 *             of the WebElement
	 * @param text the text to be expected in the WebElement
	 * @throws Exception if an error occurs while waiting for the text
	 */
	public void waitForTextToBePresentInElementLocated(By by, String text) {
		try {
			log.info("Wait for the element : " + by);
			wait.until(ExpectedConditions.textToBePresentInElementLocated(by, text));
		} catch (Exception ex) {
			log.info("Some error occured while wait for element : " + by);
			ex.printStackTrace();
			log.error(ex);
		}
	}

	/**
	 * Waits for the specified text to be present in the value of the given
	 * WebElement.
	 *
	 * @param element the WebElement whose value is to be checked
	 * @param text    the text to be expected in the WebElement's value
	 * @throws Exception if an error occurs while waiting for the text
	 */
	public void waitForTextToBePresentInElementValue(WebElement element, String text) {
		try {
			log.info("Wait for the element : " + element);
			wait.until(ExpectedConditions.textToBePresentInElementValue(element, text));
		} catch (Exception ex) {
			log.info("Some error occured while wait for element : " + element);
			ex.printStackTrace();
			log.error(ex);
		}
	}

	/**
	 * Waits for the specified title to be present in the browser's title.
	 *
	 * @param title the title to be expected
	 * @throws Exception if an error occurs while waiting for the title
	 */
	public void waitForTitleIs(String title) {
		try {
			log.info("Wait for the title : " + title);
			wait.until(ExpectedConditions.titleIs(title));
		} catch (Exception ex) {
			log.info("Some error occured while wait for title : " + title);
			ex.printStackTrace();
			log.error(ex);
		}
	}

	/**
	 * Waits for the specified title to be present in the browser's title.
	 *
	 * @param title the title to be expected
	 * @throws Exception if an error occurs while waiting for the title
	 */
	public void waitForTitleContains(String title) {
		try {
			log.info("Wait for the title : " + title);
			wait.until(ExpectedConditions.titleContains(title));
		} catch (Exception ex) {
			log.info("Some error occured while wait for title : " + title);
			ex.printStackTrace();
			log.error(ex);
		}
	}

	/**
	 * Waits for the specified WebElement to be visible.
	 *
	 * @param element the WebElement to be checked for visibility
	 * @throws Exception if an error occurs while waiting for the element
	 */
	public void waitForVisibilityOf(WebElement element) {
		try {
			log.info("Wait for the element : " + element);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception ex) {
			log.info("Some error occured while wait for element : " + element);
			ex.printStackTrace();
			log.error(ex);
		}
	}

	/**
	 * Waits for all the specified WebElements to be visible.
	 *
	 * @param elements the list of WebElements to be checked for visibility
	 * @throws Exception if an error occurs while waiting for the elements
	 */
	public void waitForVisibilityOfAllElements(List<WebElement> elements) {
		try {
			log.info("Wait for the element : " + elements);
			wait.until(ExpectedConditions.visibilityOfAllElements(elements));
		} catch (Exception ex) {
			log.info("Some error occured while wait for element : " + elements);
			ex.printStackTrace();
			log.error(ex);
		}
	}

	/**
	 * Waits for all the WebElements located by the given By object to be visible.
	 *
	 * @param by the By object representing the locator strategy and target value of
	 *           the WebElement
	 * @throws Exception if an error occurs while waiting for the elements
	 */
	public void waitForVisibilityOfAllElementsLocatedBy(By by) {
		try {
			log.info("Wait for the element : " + by);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
		} catch (Exception ex) {
			log.info("Some error occured while wait for element : " + by);
			ex.printStackTrace();
			log.error(ex);
		}
	}

	/**
	 * Waits for the specified WebElement located by the given By object to be
	 * visible.
	 *
	 * @param by the By object representing the locator strategy and target value of
	 *           the WebElement
	 * @throws Exception if an error occurs while waiting for the element
	 */
	public void waitForVisibilityOfElementLocated(By by) {
		try {
			log.info("Wait for the element : " + by);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception ex) {
			log.info("Some error occured while wait for element : " + by);
			ex.printStackTrace();
			log.error(ex);
		}
	}

	/**
	 * Waits for the specified URL to be present in the browser's URL.
	 *
	 * @param partialUrl the partial URL to be expected
	 * @throws Exception if an error occurs while waiting for the URL
	 */
	public void waitForUrlContains(String partialUrl) {
		try {
			log.info("Wait for the url : " + partialUrl);
			wait.until(ExpectedConditions.urlContains(partialUrl));
		} catch (Exception ex) {
			log.info("Some error occured while wait for url : " + partialUrl);
			ex.printStackTrace();
			log.error(ex);
		}
	}

	/**
	 * Waits for the specified number of windows to be available.
	 *
	 * @param numberOfWindows the expected number of windows
	 * @throws Exception if an error occurs while waiting for the windows
	 */
	public void waitForNumberOfWindowsToBe(int numberOfWindows) {
		try {
			log.info("Wait for the number of windows to be : " + numberOfWindows);
			wait.until(ExpectedConditions.numberOfWindowsToBe(numberOfWindows));
		} catch (Exception ex) {
			log.info("Some error occurred while waiting for the number of windows : " + numberOfWindows);
			ex.printStackTrace();
			log.error(ex);
		}
	}

	// Implicit Wait
	/**
	 * Sets an implicit wait for the specified number of seconds.
	 *
	 * @param seconds the number of seconds to wait implicitly
	 * @throws Exception if an error occurs while setting the implicit wait
	 */
	public void setImplicitWait(int seconds) {
		try {
			log.info("Wait for the : " + seconds + " seconds");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
		} catch (Exception ex) {
			log.info("Some error occured while wait for the : " + seconds + " seconds");
			ex.printStackTrace();
			log.error(ex);
		}
	}

	// Thread Sleep
	/**
	 * Stops the program execution for the specified number of seconds.
	 *
	 * @param seconds the number of seconds to wait
	 * @throws InterruptedException if the thread is interrupted while waiting
	 */
	public void holdScript(int seconds) {
		try {
			Thread.sleep(1000 * seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Fluent Wait
	/**
	 * Sets a fluent wait for the specified WebElement, with the specified timeout
	 * and polling interval.
	 *
	 * @param element                the WebElement to be checked for visibility
	 * @param timeoutSeconds         the number of seconds to wait for the element
	 *                               to become visible
	 * @param pollingIntervalSeconds the number of milliseconds to wait between
	 *                               checks for the element's visibility
	 */
	public void setFluentWait(final WebElement element, int timeoutSeconds, int pollingIntervalSeconds) {
		try {
			FluentWait<WebDriver> fluentWait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeoutSeconds))
					.pollingEvery(Duration.ofSeconds(pollingIntervalSeconds)).ignoring(NoSuchElementException.class);
			fluentWait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}