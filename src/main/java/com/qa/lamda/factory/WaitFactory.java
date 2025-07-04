package com.qa.lamda.factory;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.lamda.constants.AppConstants;
import com.qa.lamda.enums.WaitStrategy;

public class WaitFactory {
	
	public static WebElement PerformExplicitWait(WaitStrategy waitStrategy, By by) {
		WebElement element = null;
		
		switch (waitStrategy) {
		case CLICKABLE:
			element = getExplicitWait().until(ExpectedConditions.elementToBeClickable(by));		
			break;
		case PRESENT:
			element = getExplicitWait().until(ExpectedConditions.presenceOfElementLocated(by));		
			break;
		case VISIBLE:
			element = getExplicitWait().until(ExpectedConditions.visibilityOfElementLocated(by));		
			break;
		case NONE:
		    element = DriverFactory.getDriver().findElement(by);
			break;
		}
		
		return element;
	}
	
	public static WebDriverWait getExplicitWait() {
		return new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(AppConstants.EXPLICIT_WAIT));
	}
	
	public static WebDriverWait getMessagesWait() {
		return new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(AppConstants.MESSAGE_WAIT));
	}

}
