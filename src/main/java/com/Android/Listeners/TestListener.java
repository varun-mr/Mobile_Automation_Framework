package com.Android.Listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.Android.Base.BaseClass;
import com.Android.Utilities.TestUtility;
import com.aventstack.extentreports.Status;

public class TestListener extends BaseClass implements ITestListener {

	public void onTestStart(ITestResult result) {
		test = report.createTest(result.getName().toUpperCase());
	}

	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Case Passed");
		test.addScreenCaptureFromPath(TestUtility.captureScreenshot(result.getName().toUpperCase()), result.getName().toUpperCase());
	}

	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, "Test Case Failed");
		test.log(Status.FAIL, result.getThrowable());
		test.addScreenCaptureFromPath(TestUtility.captureScreenshot(result.getName().toUpperCase()), result.getName().toUpperCase());
	}

	public void onTestSkipped(ITestResult result) {
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	public void onStart(ITestContext context) {
		
	}

	public void onFinish(ITestContext context) {
		report.flush();
	}

}
