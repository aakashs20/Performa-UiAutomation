package com.kpit.automation.tests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.kpit.automation.base.BaseTest;

public class LoginToPlatform extends BaseTest {

	private Logger log = getLogger(LoginToPlatform.class);

	@Test(priority = 1, description = "Login To Platform", groups = { "Valid" })
	public void loginToFunta() {
		try {
			log.info("Login execution start");
//			loginToFuntaPage.continueButton();
			loginToFuntaPage.login(getConfigData("app.username"), getConfigData("app.password"));
			log.info("Login execution end");
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
			Assert.fail("Test Failed", ex);
		}
	}

}
