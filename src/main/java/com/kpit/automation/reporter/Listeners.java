package com.kpit.automation.reporter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.kpit.automation.base.BaseTest;

public class Listeners extends BaseTest implements ITestListener {

	public static ExtentTest test;
	public static ExtentReports extent;
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); // Thread safe

	@Override
	public void onStart(ITestContext context) {
		try {
			extent = ExtentReporter.getReportObject();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName(),
				"<b> Description for test:</b> " + result.getMethod().getDescription());
		extentTest.set(test); // unique thread id(ErrorValidationTest)->test
		test.assignCategory(result.getTestClass().getRealClass().getSimpleName());
		test.assignCategory(result.getMethod().getGroups());
		extentTest.get().log(Status.INFO,
				MarkupHelper.createLabel(result.getMethod().getMethodName() + " " + "is started.", ExtentColor.YELLOW));
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		try {
			extentTest.get().log(Status.PASS, MarkupHelper
					.createLabel(result.getMethod().getMethodName() + " " + "is successfull!", ExtentColor.GREEN));
			Reporter.log(result.getMethod().getMethodName() + " test passed");
			if (result.isSuccess()) {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
				String methodName = result.getName();
				File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
						+ "/src/test/resources/";
				File dest = new File((String) reportDirectory + "/screenshots/success/" + methodName + "_"
						+ simpleDateFormat.format(calendar.getTime()) + ".png");
				FileUtils.copyFile(src, dest);
				test.addScreenCaptureFromPath(dest.getAbsolutePath());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			extentTest.get().log(Status.FAIL, MarkupHelper
					.createLabel(result.getMethod().getMethodName() + " " + "is failed!!", ExtentColor.RED));
			extentTest.get().fail(result.getThrowable());
			if (!result.isSuccess()) {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
				String methodName = result.getName();
				File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
						+ "/src/test/resources/";
				File dest = new File((String) reportDirectory + "/screenshots/failure/" + methodName + "_"
						+ simpleDateFormat.format(calendar.getTime()) + ".png");
				FileUtils.copyFile(src, dest);
				test.addScreenCaptureFromPath(dest.getAbsolutePath());
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void onFinish(ITestContext context) {
		if (extent != null) {
			extent.flush();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}
}
