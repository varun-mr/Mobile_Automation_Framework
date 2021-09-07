package com.Android.PageObjects;

import com.Android.Base.BaseClass;

public class ApiDemosHomePage extends BaseClass {

	public void preference(String val) {
		click("preference_Menu_XPATH");
		click("preferenceDepenedencies_Menu_XPATH");
		click("wifi_CheckBx_ID");
		click("wifisettings_Text_XPATH");
		type("wifisettings_EditBox_XPATH", val);
		ClickFindElements("Ok_Btn_XPATH", 1);
		click("wifi_CheckBx_ID");
		for (int i = 0; i < 2; i++) {
			clickBackButton();
		}

	}
}
