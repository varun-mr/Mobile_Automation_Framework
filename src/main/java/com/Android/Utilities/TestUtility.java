package com.Android.Utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.Android.Base.BaseClass;

public class TestUtility extends BaseClass {

	public static String screenshotName;

	public static String captureScreenshot(String methodName) {

		TakesScreenshot ts = ((TakesScreenshot) driver);
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);

		Date d = new Date();
		screenshotName = methodName + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".png";

		String destinationPath = System.getProperty("user.dir") + "\\target\\html\\" + screenshotName;
		File destFile = new File(destinationPath);

		try {
			FileUtils.copyFile(sourceFile, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return destinationPath;

	}

	@DataProvider(name = "dp")
	public Object[][] getData(Method m) throws IOException {
		String filePath = System.getProperty("user.dir")
				+ "\\src\\test\\resources\\com\\Android\\TestData\\testData.xlsx";

		String sheetName = m.getName();
		int rows = ExcelOperations.getRowCount(filePath, sheetName);
		int cols = ExcelOperations.getCellCount(filePath, sheetName, 0);

		Object[][] data = new Object[rows][cols];

		for (int i = 0; i < rows; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			for (int j = 0; j < cols; j++) {
				map.put(ExcelOperations.getCellData(filePath, sheetName, 0, j),
						ExcelOperations.getCellData(filePath, sheetName, i + 1, j));
				data[i][0] = map;
			}
		}
		return data;

	}

}
