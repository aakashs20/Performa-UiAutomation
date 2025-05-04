package com.kpit.automation.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.kpit.automation.utilities.LoggerUtility;

public abstract class Page extends LoggerUtility {

	public WebDriver driver;

	public Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public abstract String getPageTitle();

	public abstract String getPageHeader(By locator);

	public abstract WebElement getElement(By locator);

	public abstract List<WebElement> getElements(By locator);

//	public <TPage extends BasePage> TPage getInstance(Class<TPage> pageClass) {
//		try {
//			return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			return null;
//		}
//	}
}
