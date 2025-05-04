package com.kpit.automation.pages;

import java.time.Duration;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SystemMapViewPage extends BasePage {

	private Logger log = getLogger(SystemMapViewPage.class);
	
	private WebDriverWait wait;

	public SystemMapViewPage(WebDriver driver) {
		super(driver);
		this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
	}

	@FindBy(xpath = "//button[@id='zoomInButton']")
	WebElement zoomInButton;

	@FindBy(xpath = "//button[contains(text(),'Tools')]")
	WebElement dropDownTools;

	@FindBy(xpath = "//div[contains(text(),'Autom.')]")
	WebElement traceFileAnalysis;

	// @FindBy(xpath = "//div[text()='Start up Time']/following-sibling::input")
	// WebElement chkStartUpTime;

	public WebElement getPresetCheckbox(String labelText) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[text()='" + labelText + "']/following-sibling::input")));
	}

	@FindBy(xpath = "//button[text()='Browse']")
	WebElement btnBrowse;

	// @FindBy(xpath = "//div[contains(text(),'StartUpTime')]/preceding-sibling::div/input")
	// WebElement chkTraceFile;

	public WebElement getTraceFileCheckbox(String labelText1) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(),'" + labelText1 + "')]/preceding-sibling::div/input")));
	}

	@FindBy(xpath = "//button[text()='Select']")
	WebElement btnSelect;

	@FindBy(xpath = "//button[text()='Execute']")
	WebElement btnExecute;

	@FindBy(xpath = "//button[contains(@class,'image-button')]")
	WebElement btnInfo;

	@FindBy(xpath = "//button[@id='header-tasks-button']")
	WebElement btnTask;

	@FindBy(xpath = "//div[@id='task-table-body']")
	List<WebElement> lstAllTasks;

	@FindBy(xpath = "(//div[@id='task-table-body']/div[@class='task-table-body-row'])[1]")
	WebElement lstTask;

	@FindBy(xpath = "//button[contains(@title,'Details')]")
	WebElement btnDetails;

	@FindBy(xpath = "//button[contains(@title,'Performa')]")
	WebElement btnPerfromaChart;

	@FindBy(xpath = "(//app-task-progress-tab//div[@class='body'])[1]")
	WebElement lblTaskRow;

	@FindBy(xpath = "//div[text()='SUCCESS']")
	WebElement lblStatus;

	@FindBy(xpath = "//button[text()='Performa']")
	WebElement btnPerforma;

	@FindBy(xpath = "//div[contains(@class,'layout')]//div[@class='aurora']")
	WebElement lblChart;
	
	@FindBy(xpath = "//span[@id='loading']")
	List<WebElement> lblLoading;

	@FindBy(xpath = "//div[@id='Chart0']")
	List<WebElement> lblSubChart;

	@FindBy(xpath = "//button[@class='image-button']")
	WebElement btnTaskDtlClose;

	// Clicking on zoom in(+) button to view system map
	public void clickZoomInButton() {
		try {
			log.info("Clicking on Zoom in Button");
			waitHelper.waitForElementToBeClickable(zoomInButton);
			zoomInButton.click();
			waitHelper.waitForVisibilityOf(dropDownTools);
			logScreenshot();
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
			Assert.fail();
		}
	}

	// Click on Tool button to select option from drop down
	public void clickOnTools() {
		try {
			log.info("Clicking on Tools Button");
			waitHelper.waitForElementToBeClickable(dropDownTools);
			dropDownTools.click();
			waitHelper.waitForVisibilityOf(traceFileAnalysis);
			logScreenshot();
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
			Assert.fail();
		}
	}

	// Click on Trace File Analysis option in drop down
	public void clickOnTraceFileAnalysis() {
		try {
			log.info("Clicking on Trace File Analysis");
			waitHelper.waitForElementToBeClickable(traceFileAnalysis);
			traceFileAnalysis.click();
			waitHelper.waitForVisibilityOf(btnBrowse);
			logScreenshot();
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
			Assert.fail();
		}
	}

	// Select Preset checkbox on trace file analysis pop-up
	public void selectPreset(String label) {
		try {
			log.info("Selecting Start up time Preset");
			waitHelper.waitForElementToBeClickable(getPresetCheckbox(label));
			checkBoxHelper.selectCheckBox(getPresetCheckbox(label));
			logScreenshot();
			verificationHelper.verifyElementIsDisabled(btnExecute);
			btnBrowse.click();
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
			Assert.fail();
		}
	}

	// Browse & Select trace file
	public void selectTraceFile(String label1) {
		try {
			log.info("Selecting trace file");
			verificationHelper.verifyElementIsDisabled(btnSelect);
			waitHelper.waitForVisibilityOf(getTraceFileCheckbox(label1));
			logScreenshot();
			checkBoxHelper.selectCheckBox(getTraceFileCheckbox(label1));
			verificationHelper.verifyElementIsEnabled(btnSelect);
			logScreenshot();
			btnSelect.click();
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
			Assert.fail();
		}
	}

	// Browse & Select trace file
	public void executeTrace() {
		try {
			log.info("Execute trace file");
			waitHelper.waitForVisibilityOf(btnExecute);
			verificationHelper.verifyElementIsEnabled(btnExecute);
			logScreenshot();
			btnExecute.click();
			logScreenshot();
			waitHelper.waitForElementToBeClickable(btnInfo);
			logScreenshot();
			btnInfo.click();
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
			Assert.fail();
		}
	}

	// Select Task from the task details
	public void selectTaskFromList() {
		try {
			log.info("Get running task from the list");
			verificationHelper.verifyElementIsDisplayed(btnTask);
			btnTask.click();
			waitHelper.waitForVisibilityOfAllElements(lstAllTasks);
			logReport("List of all Tasks opened");
			logScreenshot();
			lstTask.click();
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
			Assert.fail();
		}
	}

	// Go to the task processing details
	public void goToTaskDetails() {
		try {
			log.info("Get details of selected task");
			waitHelper.waitForVisibilityOf(btnDetails);
			logReport("Automated Trace File Analysis pop-up open");
			logScreenshot();
			btnDetails.click();
			logReport("Task details option selected");
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
			Assert.fail();
		}
	}

	// Go to performa chart
	public void goToPerformaChart() {
		try {
			log.info("Go to performa chart");
			javaScriptHelper.scrollDownVertical();
			waitHelper.waitForVisibilityOf(lblTaskRow);
			javaScriptHelper.scrollDownVertical();
			logReport("Task progress details opened");
			logScreenshot();
			waitHelper.waitForElementToBeClickable(btnPerforma);
			logReport("Select Performa option on task progres");
			btnPerforma.click();
			waitHelper.waitForNumberOfWindowsToBe(2);
			browserHelper.SwitchToWindow(1);
			logReport("Chart opened in a new tab");
			waitHelper.waitForVisibilityOf(lblChart);
			logScreenshot();
			waitHelper.waitForVisibilityOfAllElements(lblLoading);
			logScreenshot();
			waitHelper.waitForVisibilityOfAllElements(lblSubChart);
			Thread.sleep(5000);
			logScreenshot();
			browserHelper.switchToParentWithChildClose();
			logReport("New tab closed");
			logScreenshot();
			waitHelper.waitForElementToBeClickable(btnTaskDtlClose);
			btnTaskDtlClose.click();
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
			Assert.fail();
		}
	}

	public void goToCreatedTask() {
		try {
			log.info("Go to created task");
			verificationHelper.verifyElementIsDisplayed(btnTask);
			btnTask.click();
			logReport("List of all Tasks opened");
			waitHelper.waitForVisibilityOfAllElements(lstAllTasks);
			logScreenshot();
			lstTask.click();
			waitHelper.waitForVisibilityOf(btnDetails);
			logReport("Task selected");
			logScreenshot();
			btnDetails.click();
			waitHelper.waitForVisibilityOf(lblTaskRow);
			javaScriptHelper.scrollDownVertical();
			logReport("Task progress details opened");
			logScreenshot();
			waitHelper.waitForElementToBeClickable(btnPerforma);
			javaScriptHelper.scrollDownVertical();
			logScreenshot();
			logReport("Select Performa option on task progress");
			btnPerforma.click();
			waitHelper.waitForNumberOfWindowsToBe(2);
			browserHelper.SwitchToWindow(1);
			logReport("Chart opened in a new tab");
			waitHelper.waitForVisibilityOf(lblChart);
			logScreenshot();
			browserHelper.switchToParentWithChildClose();
			logReport("New tab closed");
			logScreenshot();
			waitHelper.waitForElementToBeClickable(btnTaskDtlClose);
			btnTaskDtlClose.click();
			logScreenshot();
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
			Assert.fail();
		}
	}

}
