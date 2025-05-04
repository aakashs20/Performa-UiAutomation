package com.kpit.automation.utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.kpit.automation.base.BaseTest;
import com.kpit.automation.reporter.Listeners;

public class ReporterUtility {

	public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy\\yyyy-MM-dd_HH-mm-ss");

	public void logScreenshot() {
		try {
			Listeners.extentTest.get()
					.info(MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64Image()).build());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logReport(String message) {
		try {
			Listeners.extentTest.get().info(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logListOfReport(List<String> list) {
		List<String> formattedStrings = new ArrayList<>();

		int count = 1;
		for (String rowString : list) {
			String[] keyValues = rowString.split("\n");

			if (keyValues.length > 0 && !keyValues[0].trim().isEmpty()) {
				formattedStrings.add(count + ". " + keyValues[0].trim());
			}

			for (int i = 1; i < keyValues.length; i++) {
				formattedStrings.add("   " + keyValues[i].trim());
			}

			formattedStrings.add("--------------------------------------------------"); // Add an empty line between
			// rows
			count++;
		}

		Listeners.extentTest.get().info(MarkupHelper.toTable(formattedStrings));
	}

	public void logMapOfReport(Map<String, String> map) {
		try {
			Listeners.extentTest.get().info(MarkupHelper.createOrderedList(map));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logListOfMapReport(List<HashMap<String, String>> listMap) {
		try {
			Listeners.extentTest.get().info(MarkupHelper.createOrderedList(listMap));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getBase64Image() {
		return ((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.BASE64);
	}

	public static String screenShot(WebDriver driver) {
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			String target = "./src/test/resources/reports/" + dtf.format(LocalDateTime.now()) + ".png";
			File destination = new File(target);
			String target1 = destination.getAbsolutePath();
			FileHandler.copy(source, destination);
			return target1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

}
