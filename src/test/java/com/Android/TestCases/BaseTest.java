package com.Android.TestCases;

import org.testng.annotations.AfterSuite;

import com.Android.Base.BaseClass;

public class BaseTest {
	
	@AfterSuite
	public void end() {
		BaseClass.Service.stop();
	}

}
