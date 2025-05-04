package com.kpit.automation.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.kpit.automation.helpers.AlertHelper;
import com.kpit.automation.helpers.BrowserHelper;
import com.kpit.automation.helpers.CheckBoxHelper;
import com.kpit.automation.helpers.DatePickerHelper;
import com.kpit.automation.helpers.DropDownHelper;
import com.kpit.automation.helpers.FileUploadHelper;
import com.kpit.automation.helpers.GridHelper;
import com.kpit.automation.helpers.JavaScriptHelper;
import com.kpit.automation.helpers.MouseActionHelper;
import com.kpit.automation.helpers.PopUpHelper;
import com.kpit.automation.helpers.VerificationHelper;
import com.kpit.automation.helpers.WaitHelper;
import com.kpit.automation.helpers.WebTableUtility;

public class BasePage extends Page {

	private Logger log = getLogger(BasePage.class);

	AlertHelper alertHelper = new AlertHelper(driver);
	BrowserHelper browserHelper = new BrowserHelper(driver);
	CheckBoxHelper checkBoxHelper = new CheckBoxHelper();
	DatePickerHelper datePickerHelper= new DatePickerHelper(driver);
	DropDownHelper dropDownHelper = new DropDownHelper(driver);
	FileUploadHelper fileUploadHelper = new FileUploadHelper();
	GridHelper gridHelper = new GridHelper();
	JavaScriptHelper javaScriptHelper = new JavaScriptHelper(driver);
	MouseActionHelper mouseActionHelper = new MouseActionHelper(driver);
	PopUpHelper popUpHelper = new PopUpHelper(driver);
	VerificationHelper verificationHelper = new VerificationHelper();
	WaitHelper waitHelper = new WaitHelper(driver);
	WebTableUtility webTableUtility = new WebTableUtility();

	public BasePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//jqxgrid/div[@role='grid']//div[@role='columnheader']")
    private List<WebElement> columnHeaders;

    @FindBy(xpath = "//jqxgrid/div[@role='grid']//div[@role='row']/div[@role='gridcell']")
    private List<WebElement> gridCells;

    public List<WebElement> getColumnHeaders() {
        return columnHeaders;
    }

    public List<WebElement> getGridCells() {
        return gridCells;
    }

	@Override
	public String getPageTitle() {
		return driver.getTitle();
	}

	@Override
	public String getPageHeader(By locator) {
		return getElement(locator).getText();

	}

	@Override
	public WebElement getElement(By locator) {
		WebElement element = null;
		try {
			waitHelper.waitForPresenceOfElementLocated(locator);
			element = driver.findElement(locator);
		} catch (Exception ex) {
			log.info("Some error occured while creation of element : " + locator.toString());
			ex.printStackTrace();
		}
		return element;
	}

	@Override
	public List<WebElement> getElements(By locator) {
		List<WebElement> elements = null;
		try {
			waitHelper.waitForPresenceOfElementLocated(locator);
			elements = driver.findElements(locator);
		} catch (Exception ex) {
			log.info("Some error occured while creation of element : " + locator.toString());
			ex.printStackTrace();
		}
		return elements;
	}
}