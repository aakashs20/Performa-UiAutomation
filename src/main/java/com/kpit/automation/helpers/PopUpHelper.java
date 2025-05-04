package com.kpit.automation.helpers;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.kpit.automation.utilities.LoggerUtility;

public class PopUpHelper extends LoggerUtility {

	private Logger log = getLogger(PopUpHelper.class);
	
	private WebDriver driver;

	public PopUpHelper(WebDriver driver) {
		this.driver = driver;
	}

	public boolean clickOnOkButton() {
		try {
			driver.findElement(By.xpath("//button[text()='OK']//parent::button")).click();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public void clickOnCancelButton() {
		driver.findElement(By.xpath("//button[text()='Cancel']//parent::button")).click();
	}

	public void clickOnPopUpOkButton() {
		driver.findElement(By.xpath("//button[text()='OK' and @data-dismiss='modal']")).click();
	}

	public String verifyPopUp(WebElement element) {
		String strMsg = null;
		try {
			element.isDisplayed();
			Thread.sleep(2000);
			strMsg = element.getText();
			logScreenshot();
			clickOnPopUpOkButton();
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
			Assert.fail();
		}
		return strMsg;
	}

}
