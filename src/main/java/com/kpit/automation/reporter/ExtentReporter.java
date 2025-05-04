package com.kpit.automation.reporter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * This class is responsible for creating and managing the ExtentReports object.
 * It also provides methods to log different types of test results such as pass,
 * fail, exception, info, start, and warning.
 *
 */
public class ExtentReporter {

	/**
	 * The static ExtentReports object that will be used to generate the test
	 * report.
	 */
	private static ExtentReports extentReports;

	/**
	 * The static DateTimeFormatter object that will be used to format the current
	 * date and time.
	 */
	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MMM_yyyy\\HH_mm_ss");

	/**
	 * This method creates and returns the ExtentReports object. It also configures
	 * the report settings such as theme, report name, document title, encoding, and
	 * system information.
	 *
	 * @return The ExtentReports object.
	 */
	public static ExtentReports getReportObject() {
		// Generate the report path with the current timestamp
		String reportPath = "./src/test/resources/reports/" + dtf.format(LocalDateTime.now());

		// Create ExtentSparkReporter instance with the report path
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(reportPath);

		// Configure the reporter with desired settings
		extentSparkReporter.config().setTheme(Theme.DARK);
		extentSparkReporter.config().setReportName("KOST Middleware API Results");
		extentSparkReporter.config().setDocumentTitle("UI Test Results");
		extentSparkReporter.config().setEncoding("utf-8");

		// Initialize the ExtentReports instance and attach the reporter
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);

		// Set system information for the report
		extentReports.setSystemInfo("Organization", "KPIT");
		extentReports.setSystemInfo("Project", "KOAST");
		extentReports.setSystemInfo("QA", "Aakash Saxena");
		extentReports.setSystemInfo("Environment", "UAT");
		extentReports.setSystemInfo("Testing type", "Functional");
		// Return the configured ExtentReports instance
		return extentReports;
	}

	/**
	 * This method logs a pass detail with a specified message.
	 *
	 * @param log The message to be logged as a pass detail.
	 */
	public static void logPassDetails(String log) {
		if (Listeners.extentTest.get() != null) {
			Listeners.extentTest.get().pass(MarkupHelper.createLabel(log, ExtentColor.GREEN));
		}
	}

	/**
	 * This method logs a failure detail with a specified message.
	 *
	 * @param log The message to be logged as a failure detail.
	 */
	public static void logFailureDetails(String log) {
		if (Listeners.extentTest.get() != null) {
			Listeners.extentTest.get().fail(MarkupHelper.createLabel(log, ExtentColor.RED));
		}
	}

	/**
	 * This method logs an exception detail with a specified message.
	 *
	 * @param log The message to be logged as an exception detail.
	 */
	public static void logExceptionDetails(String log) {
		if (Listeners.extentTest.get() != null) {
			Listeners.extentTest.get().fail(log);
		}
	}

	/**
	 * This method logs an info detail with a specified message.
	 *
	 * @param log The message to be logged as an info detail.
	 */
	public static void logInfoDetails(String log) {
		if (Listeners.extentTest.get() != null) {
			Listeners.extentTest.get().info(MarkupHelper.createLabel(log, ExtentColor.BLUE));
		}
	}

	/**
	 * This method logs a start detail with a specified message.
	 *
	 * @param log The message to be logged as a start detail.
	 */
	public static void logStartDetails(String log) {
		if (Listeners.extentTest.get() != null) {
			Listeners.extentTest.get().info(MarkupHelper.createLabel(log, ExtentColor.ORANGE));
		}
	}

	/**
	 * This method logs a warning detail with a specified message.
	 *
	 * @param log The message to be logged as a warning detail.
	 */
	public static void logWarningDetails(String log) {
		if (Listeners.extentTest.get() != null) {
			Listeners.extentTest.get().warning(MarkupHelper.createLabel(log, ExtentColor.YELLOW));
		}
	}
}
