package com.kpit.automation.utilities;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryUtility extends LoggerUtility implements IRetryAnalyzer {

	private int retryCount = 0;
	private int maxRetryCount = 2;

	Logger log = getLogger(RetryUtility.class);

	@Override
	public boolean retry(ITestResult test) {
		try {
			if (retryCount < maxRetryCount) {
				log.info("Retring Test " + test.getName() + " with status " + getResultStatusName(test.getStatus())
						+ " for the " + (retryCount + 1) + " time.");
				retryCount++;
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public String getResultStatusName(int status) {
		String resultName = null;
		try {
			if (status == 1) {
				resultName = "SUCCESS";
			} else if (status == 2) {
				resultName = "FAILURE";
			} else if (status == 3) {
				resultName = "SKIP";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resultName;
	}

}
