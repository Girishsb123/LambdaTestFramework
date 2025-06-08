package com.qa.lamda.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.lamda.constants.AppConstants;
import com.qa.lamda.utils.ElementUtil;
import com.qa.lamda.utils.JavaScriptUtil;

import io.qameta.allure.Step;

public class SearchResultsPage {
	
    private WebDriver driver;
	private ElementUtil eleUtil;
	private JavaScriptUtil jsUtil;

	// By locators: OR
	
	
	// page const...

		public SearchResultsPage(WebDriver driver) {
			this.driver = driver;
			eleUtil = new ElementUtil(this.driver);
			jsUtil = new JavaScriptUtil(this.driver);

		}
		
		//page actions/methods...
		
		@Step("search prodcut name : {0}")
		public ProductInfoPage selectProduct(String productName) {
			By productname = By.xpath("(//a/parent::h4)[position()=3]");
			eleUtil.waitForVisibilityOfElement(productname, AppConstants.MEDIUM_DEFAUTT_WAIT).click();
			return new ProductInfoPage(driver);
		}

}
