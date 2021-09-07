package com.Android.Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private static ExtentSparkReporter reporter;
	private static ExtentReports extent;

	public static ExtentReports createExtentReport() {
		if (extent == null) {
			reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\target\\html\\Extent.html");
			reporter.config().setDocumentTitle("Mobile Automation Report");
			reporter.config().setReportName("APIDemos apk Automation");
			reporter.config().setTheme(Theme.DARK);
			reporter.config().setEncoding("UTF-8");
			reporter.config().setCss(".r-img {width: 200; height: 200}");

			extent = new ExtentReports();
			extent.attachReporter(reporter);
			extent.setSystemInfo("Platform", "Mobile");
			extent.setSystemInfo("DeviceType", "Android");
			extent.setSystemInfo("APPType", "Native Mobile App");
			extent.setSystemInfo("Tester", "Varun");
		}
		return extent;
	}
}
