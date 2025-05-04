package com.kpit.automation.tests;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.kpit.automation.base.BaseTest;

public class SystemMapViewTest extends BaseTest {

	private Logger log = getLogger(SystemMapViewTest.class);

	@DataProvider(name = "testData")
	public Object[][] getExcelData() throws IOException {
		return testData(System.getProperty("user.dir") + getConfigData("excel.path") + "TestData.xlsx", "testdata");
	}

	@Test(priority = 1, description = "Startup Timing Chart", groups = { "Valid" }, enabled = true)
	public void startupTimingChart() {
		try {
			log.info("execution start");
			systemMapViewPage.clickZoomInButton();
			systemMapViewPage.clickOnTools();
			systemMapViewPage.clickOnTraceFileAnalysis();
			systemMapViewPage.selectPreset("Start up Time");
			systemMapViewPage.selectTraceFile("StartUpTime");
			systemMapViewPage.executeTrace();
			systemMapViewPage.selectTaskFromList();
			systemMapViewPage.goToTaskDetails();
			systemMapViewPage.goToPerformaChart();

			// systemMapViewPage.goToCreatedTask();

			log.info("execution end");
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
			Assert.fail("Test Failed", ex);
		}
	}

	@Test(priority = 2, description = "Routing Analysis Chart", groups = { "Valid" }, enabled = true)
	public void routingAnalysisChart() {
		try {
			log.info("execution start");
			systemMapViewPage.clickOnTools();
			systemMapViewPage.clickOnTraceFileAnalysis();
			systemMapViewPage.selectPreset("Routing");
			systemMapViewPage.selectTraceFile("Wireshark_without");
			systemMapViewPage.executeTrace();
			systemMapViewPage.selectTaskFromList();
			systemMapViewPage.goToTaskDetails();
			systemMapViewPage.goToPerformaChart();

			// systemMapViewPage.goToCreatedTask();

			log.info("execution end");
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
			Assert.fail("Test Failed", ex);
		}
	}

	@Test(priority = 3, description = "Event Analyzer Chart", groups = { "Valid" }, enabled = true)
	public void eventAnalysisChart() {
		try {
			log.info("execution start");
			systemMapViewPage.clickOnTools();
			systemMapViewPage.clickOnTraceFileAnalysis();
			systemMapViewPage.selectPreset("XIL");
			systemMapViewPage.selectTraceFile("fleet_car");
			systemMapViewPage.executeTrace();
			systemMapViewPage.selectTaskFromList();
			systemMapViewPage.goToTaskDetails();
			systemMapViewPage.goToPerformaChart();

			// systemMapViewPage.goToCreatedTask();

			log.info("execution end");
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
			Assert.fail("Test Failed", ex);
		}
	}

	@Test(priority = 4, description = "Throughput Analysis Chart", groups = { "Valid" }, enabled = true)
	public void throughputAnalysisChart() {
		try {
			log.info("execution start");
			systemMapViewPage.clickOnTools();
			systemMapViewPage.clickOnTraceFileAnalysis();
			systemMapViewPage.selectPreset("Buses Load");
			systemMapViewPage.selectTraceFile("Startup_multi");
			systemMapViewPage.executeTrace();
			systemMapViewPage.selectTaskFromList();
			systemMapViewPage.goToTaskDetails();
			systemMapViewPage.goToPerformaChart();

			// systemMapViewPage.goToCreatedTask();

			log.info("execution end");
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
			Assert.fail("Test Failed", ex);
		}
	}

	@Test(priority = 5, description = "Sanity check Pass", groups = { "Valid" }, enabled = true)
	public void sanityCheckPass() {
		try {
			log.info("execution start");
			systemMapViewPage.clickOnTools();
			systemMapViewPage.clickOnTraceFileAnalysis();
			systemMapViewPage.selectPreset("TECMP");
			systemMapViewPage.selectTraceFile("ACC_Logs");
			systemMapViewPage.executeTrace();
			systemMapViewPage.selectTaskFromList();
			systemMapViewPage.goToTaskDetails();
			systemMapViewPage.goToPerformaChart();

			// systemMapViewPage.goToCreatedTask();

			log.info("execution end");
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
			Assert.fail("Test Failed", ex);
		}
	}

	@Test(priority = 6, description = "Sanity check Fail", groups = { "Valid" }, enabled = true)
	public void sanityCheckFail() {
		try {
			log.info("execution start");
			systemMapViewPage.clickOnTools();
			systemMapViewPage.clickOnTraceFileAnalysis();
			systemMapViewPage.selectPreset("TECMP");
			systemMapViewPage.selectTraceFile("StartUpTimeTest");
			systemMapViewPage.executeTrace();
			systemMapViewPage.selectTaskFromList();
			systemMapViewPage.goToTaskDetails();
			systemMapViewPage.goToPerformaChart();

			// systemMapViewPage.goToCreatedTask();

			log.info("execution end");
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
			Assert.fail("Test Failed", ex);
		}
	}

}
