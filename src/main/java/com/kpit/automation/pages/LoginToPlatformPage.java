package com.kpit.automation.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class LoginToPlatformPage extends BasePage {

	private Logger log = getLogger(LoginToPlatformPage.class);

	public LoginToPlatformPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@value='Continue']")
	WebElement continueButton;

	@FindBy(xpath = "//div[@class='modal-content background-customizable modal-content-mobile visible-md visible-lg']//input[@name='username']")
	WebElement userName;

	@FindBy(xpath = "//div[@class='modal-content background-customizable modal-content-mobile visible-md visible-lg']//input[@name='password']")
	WebElement passWord;

	@FindBy(xpath = "//div[@class='modal-content background-customizable modal-content-mobile visible-md visible-lg']//input[@value='Sign in']")
	WebElement signInButton;
	
	@FindBy(xpath = "//p[contains(text(),'Initializing')]")
	WebElement initializing;

	public void continueButton() {
		try {
			log.info("Clicking on Continue Button");
			Thread.sleep(2000);
			// driver.switchTo()
			// Assert.assertEquals(true,
			// verificationHelper.verifyElementPresent(continueButton));
			continueButton.click();
			Thread.sleep(5000);

		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
			Assert.fail();
		}
	}

	public void login(String strUsername, String strPassword) {
		try {
			log.info("Login execution start");
			logReport("Login to the application");
			logReport("<b>Login test is started with valid Username and valid Password");
			logScreenshot();
//			Thread.sleep(2000);
			waitHelper.waitForVisibilityOf(userName);
			// Assert.assertEquals(true, verificationHelper.verifyElementPresent(userName));
			userName.sendKeys(strUsername);
			// Assert.assertEquals(true, verificationHelper.verifyElementPresent(passWord));
			passWord.sendKeys(strPassword);
			logScreenshot();
			// waitHelper.waitForElementToBeClickable(signInButton);
			signInButton.click();
//			Thread.sleep(2000);
			logReport("user logged in successfully.");
			waitHelper.waitForVisibilityOf(initializing);
			logScreenshot();
			waitHelper.waitForInvisibilityOf(initializing);
			log.info("Login execution end");
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
			Assert.fail();
		}
	}

}
