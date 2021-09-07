package com.Android.TestCases;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.Android.PageObjects.ApiDemosHomePage;
import com.Android.Utilities.TestUtility;

public class ApiDemosBasicsTest extends BaseTest {

	ApiDemosHomePage api = new ApiDemosHomePage();

	@Test(dataProviderClass = TestUtility.class, dataProvider="dp")
	public void PreferenceTest(HashMap<String, String> data) {

		api.preference(data.get("ValueToSearch"));
	}
}
