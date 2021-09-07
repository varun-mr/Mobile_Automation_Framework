package com.Android.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.Android.Utilities.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseClass {

	public static AndroidDriver<AndroidElement> driver;
	public static WebElement element;
	public static Properties Config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static File f;
	public static File fs;
	public static List<AndroidElement> Multipleelements;
	public static DesiredCapabilities cap;
	public static AppiumDriverLocalService Service;
	public static ExtentReports report = ExtentManager.createExtentReport();
	public static ExtentTest test;
	public static Logger log = LogManager.getLogger(BaseClass.class);

	public BaseClass() {
		if (driver == null) {
			try {
				fis = new FileInputStream(System.getProperty("user.dir")
						+ "\\src\\test\\resources\\com\\Android\\Properties\\Config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			try {
				Config.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(System.getProperty("user.dir")
						+ "\\src\\test\\resources\\com\\Android\\Properties\\OR.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			try {
				OR.load(fis);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			f = new File("Apps");
			fs = new File(f, Config.getProperty("ApkName"));

			String device = Config.getProperty("device");

			if (device.contains("Emulator")) {
				startEmulator();
			}

			cap = new DesiredCapabilities();

			cap.setCapability(MobileCapabilityType.DEVICE_NAME, device);
			cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiAutomator2");
			cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
			cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 15);

			AppiumServer();

			try {
				driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.1.1:4723/wd/hub"), cap);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

			driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicitwait")),
					TimeUnit.SECONDS);
		}
	}

	public static void startEmulator() {
		try {
			Runtime.getRuntime().exec(System.getProperty("user.dir")
					+ "\\src\\test\\resources\\com\\Android\\Emulators\\Emulator_Pixel3aXLAnd9.bat");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static AppiumDriverLocalService AppiumServer() {
		boolean check = checkIfServerIsRunning(4723);
		if (!check) {
			Service = AppiumDriverLocalService.buildDefaultService();
			Service.start();
		}
		return Service;
	}

	public static boolean checkIfServerIsRunning(int port) {
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

	public static WebElement findElement(String locator) {
		if (locator.endsWith("_XPATH")) {
			element = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			element = driver.findElement(By.id(OR.getProperty(locator)));
		}
		log.debug("Finding the element: " + locator);
		test.log(Status.INFO, "Element located is: " + "'" + locator + "'");
		return element;
	}

	public static String findelement(String locator) {
		if (locator.endsWith("_XPATH")) {
			element = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			element = driver.findElement(By.id(OR.getProperty(locator)));
		}
		log.debug("Finding the element: " + locator);
		test.log(Status.INFO, "Element located is: " + "'" + locator + "'");
		return locator;
	}

	public static List<AndroidElement> FindMultipleElements(String locator) {
		if (locator.endsWith("_XPATH")) {
			Multipleelements = driver.findElementsByXPath(OR.getProperty(locator));
		} else if (locator.endsWith("_ID")) {
			Multipleelements = driver.findElementsById(OR.getProperty(locator));
		}
		log.debug("Finding the elements: " + locator);
		test.log(Status.INFO, "Elements located is: " + "'" + locator + "'");
		return Multipleelements;
	}

	public static void ClickFindElements(String locator, int num) {
		if (locator.endsWith("_XPATH")) {
			driver.findElementsByXPath(OR.getProperty(locator)).get(num).click();
			;
		} else if (locator.endsWith("_ID")) {
			driver.findElementsById(OR.getProperty(locator)).get(num).click();
			;
		}
		log.debug("Finding the elements: " + locator);
		test.log(Status.INFO, "Elements located is: " + "'" + locator + "'");
	}

	public static void click(String locator) {
		if (locator.endsWith("_XPATH")) {
			driver.findElementByXPath(OR.getProperty(locator)).click();
		} else if (locator.endsWith("_ID")) {
			driver.findElementById(OR.getProperty(locator)).click();
		}
		log.debug("Clicking on the Element : " + locator);
		test.log(Status.INFO, "Clicking on: " + "'" + locator + "'");
	}

	public static void clear(String locator) {
		if (locator.endsWith("_XPATH")) {
			driver.findElementByXPath(OR.getProperty(locator)).clear();
		} else if (locator.endsWith("_ID")) {
			driver.findElementById(OR.getProperty(locator)).clear();
		}
		log.debug("Clearing the value from the Element : " + locator);
		test.log(Status.INFO, "Clearing the value from: " + "'" + locator + "'");
	}

	public static void type(String locator, String value) {
		if (locator.endsWith("_XPATH")) {
			driver.findElementByXPath(OR.getProperty(locator)).sendKeys(value);
		} else if (locator.endsWith("_ID")) {
			driver.findElementById(OR.getProperty(locator)).sendKeys(value);
		}
		log.debug("Typing a value in the Element : " + locator);
		test.log(Status.INFO, "Typing the value of: " + "'" + value + "'" + " in the element " + "'" + locator + "'");
	}

	public static String getText(String locator) {
		String resultString = null;
		if (locator.endsWith("_XPATH")) {
			resultString = driver.findElement(By.xpath(OR.getProperty(locator))).getText();
		} else if (locator.endsWith("_ID")) {
			resultString = driver.findElement(By.id(OR.getProperty(locator))).getText();
		}
		log.debug("Fetching the text on an Element : " + locator);
		test.log(Status.INFO, "Text extracted from the locator is: " + "'" + resultString + "'");
		return resultString;
	}
	
	public static void clickBackButton() {
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
	}

}
