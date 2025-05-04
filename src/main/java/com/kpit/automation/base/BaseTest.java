/**
 * Base class for all test cases.
 * This class initializes the WebDriver, sets up the test environment, and provides common methods for all test cases.
 *
 * @author Aakash Saxena
 * @version 1.0
 * @since 2023-12-01
 */
package com.kpit.automation.base;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.kpit.automation.pages.LoginToPlatformPage;
import com.kpit.automation.pages.SystemMapViewPage;
import com.kpit.automation.utilities.DatabaseUtility;
import com.kpit.automation.utilities.FileReaderUtility;

/**
 * Base class for all test cases. This class initializes the WebDriver, sets up
 * the test environment, and provides common methods for all test cases.
 *
 * @author Aakash Saxena
 * @version 1.0
 * @since 2023-12-01
 */
public class BaseTest extends FileReaderUtility {

	/**
	 * The WebDriver instance used for test execution.
	 */
	public static WebDriver driver;

	protected LoginToPlatformPage loginToFuntaPage;
	protected SystemMapViewPage systemMapViewPage;

	DatabaseUtility databaseUtils = new DatabaseUtility();

	/**
	 * The Logger instance used for logging messages.
	 */
	private Logger log = getLogger(BaseTest.class);

	/**
	 * Initializes the test environment by loading the property file and setting up
	 * the test environment.
	 *
	 * @throws IOException if the property file cannot be loaded
	 */
	@BeforeSuite(alwaysRun = true)
	public void init() throws IOException {
		loadPropertyFile();
		log.info("======================== [ Property File Load Successful ] ========================");
	}

	/**
	 * Sets up the test environment by launching the specified browser and
	 * navigating to the application URL.
	 *
	 * @param browser the name of the browser to be launched
	 * @throws IOException if the browser cannot be launched
	 */
	@BeforeTest(alwaysRun = true)
	@Parameters(value = { "browser" })
	public void setUpTest(String browser) throws IOException {
		if (System.getProperty("os.name").contains(getConfigData("operating.system"))) {
			if (browser.equalsIgnoreCase(getConfigData("browser.chrome"))) {
				log.info(
						"======================== [ Launching " + browser + " Browser] ==============================");

				// Set up ChromeDriver service
				ChromeOptions options = new ChromeOptions();
				// options.addArguments("--remote-allow-origins=*");
				// options.addArguments("--disable-dev-shm-usage");
				// options.addArguments("--no-sandbox");
				// options.addArguments("--disk-cache-size=0");
				// options.addArguments("--disable-cookies");

				driver = new ChromeDriver(options);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
				// driver.manage().deleteAllCookies();
				driver.manage().window().maximize();
			} else if (browser.equalsIgnoreCase(getConfigData("browser.firefox"))) {
				log.info(
						"======================== [ Launching " + browser + " Browser] ==============================");
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
			} else if (browser.equalsIgnoreCase(getConfigData("browser.edge"))) {
				log.info(
						"======================== [ Launching " + browser + " Browser] ==============================");
				driver = new EdgeDriver();
				driver.manage().deleteAllCookies();
				driver.manage().window().maximize();
			} else {
				log.info("No Browser Is Defined In XML File");
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
			driver.get(getConfigData("app.url"));
		} else {
			log.info("======================== [ The Operating System Is Not WINDOWS ] ==============================");
			Assert.fail();
		}
	}

	/**
	 * Sets up the test environment by launching the specified browser and
	 * navigating to the application URL.
	 *
	 * @param browser the name of the browser to be launched
	 * @throws IOException if the browser cannot be launched
	 */
	@BeforeClass(alwaysRun = true)
	public void loginApplication() {

		// Manually initialize the ExtentTest object
		// ExtentTest test = Listeners.extent.createTest("Class Setup", "Setup for the
		// test class");
		// Listeners.extentTest.set(test);

		// Log the setup information
		// Listeners.extentTest.get().log(Status.INFO,
		// MarkupHelper.createLabel("Setting up the class", ExtentColor.YELLOW));

		loginToFuntaPage = new LoginToPlatformPage(driver);
		systemMapViewPage = new SystemMapViewPage(driver);

		// loginPage.login(getConfigData("app.username"),
		// getConfigData("app.password"));
	}

	/**
	 * Logs out of the application.
	 * 
	 * @AfterClass(alwaysRun = true) public void logoutApplication() {
	 *                       loginPage.logout(); }
	 * 
	 *                       /** Closes the WebDriver instance.
	 */
	@AfterTest(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}

	/**
	 * Quits the WebDriver instance.
	 */
	@AfterSuite(alwaysRun = true)
	public void exit() {
		driver.quit();
	}

	/**
	 * Retrieves data from the database using the specified SQL query.
	 *
	 * @param sql the SQL query to be executed
	 * @return a HashMap containing the retrieved data
	 * @throws Exception if an error occurs during database operations
	 */
	public HashMap<String, String> getDataDB(String sql) throws Exception {
		HashMap<String, String> getData = databaseUtils.getDataFromDB(sql);
		return getData;
	}
}