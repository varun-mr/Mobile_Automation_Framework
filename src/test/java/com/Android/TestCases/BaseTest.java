package com.Android.TestCases;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;

import com.Android.Base.BaseClass;

public class BaseTest {

	@BeforeTest
	public void KillNodeProcesses() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);
	}
	
	@AfterSuite
	public void end() {
		BaseClass.Service.stop();
	}

}
