package com.qa.lamda.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.qa.lamda.factory.DriverFactory;

public final class ScreenshotUtil {

	private ScreenshotUtil() {}
	
	public static String getBase64Image() {
		return ((TakesScreenshot)DriverFactory.getDriver()).getScreenshotAs(OutputType.BASE64);
	}
}
