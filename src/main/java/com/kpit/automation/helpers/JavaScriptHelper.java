package com.kpit.automation.helpers;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.kpit.automation.utilities.LoggerUtility;

public class JavaScriptHelper extends LoggerUtility {

	private Logger log = getLogger(JavaScriptHelper.class);

	private WebDriver driver;

	public JavaScriptHelper(WebDriver driver) {
		this.driver = driver;
	}

	public Object executeScript(String script) {
		try {
			log.info("Script : " + script);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			return executor.executeScript(script); // Changed from executeAsyncScript to executeScript
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public Object executeScript(String script, Object... arguments) {
		try {
			log.info("Script : " + script);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			return executor.executeAsyncScript(script, arguments);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public void elementClick(WebElement element) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			log.info("Element click using JS executor is successful");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void scrollToElement(WebElement element) {
		try {
			executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x,
					element.getLocation().y);
			log.info("Scroll to element is successful");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void scrollToElementAndClick(WebElement element) {
		try {
			scrollToElement(element);
			element.click();
			log.info("Scroll to element and click is successful");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void scrollIntoView(WebElement element) {
		try {
			executeScript("arguments[0].scrollIntoView()", element);
			log.info("Scroll to view is successful");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void scrollIntoViewAndClick(WebElement element) {
		try {
			scrollIntoView(element);
			element.click();
			log.info("Scroll to view and click is successful");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void scrollUpVertical() {
		try {
			executeScript("window.scrollTo(0, -document.body.scrollHeight)");
			log.info("Scroll up vertical is successful");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void scrollDownVertical() {
		try {
			executeScript("window.scrollTo(0, document.body.scrollHeight)");
			log.info("Scroll down vertical is successful");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void ScrollUpByPixel(String pixel) {
		try {
			executeScript("window.scrollBy(0, -'" + pixel + "')");
			log.info("Scroll up by pixel is successful");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void ScrolDownByPixel(String pixel) {
		try {
			executeScript("window.scrollBy(0, '" + pixel + "')");
			log.info("Scroll down by pixel is successful");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void zoomInByPercentage(String percent) {
		try {
			executeScript("document.body.style.zoom='" + percent + "'");
			log.info("Zoom in by percent is successful");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
