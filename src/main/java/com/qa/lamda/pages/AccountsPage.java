package com.qa.lamda.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.lamda.constants.AppConstants;
import com.qa.lamda.utils.ElementUtil;

import io.qameta.allure.Step;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// By locators: OR
	
	private By logoutLink = By.linkText("Logout");
	private By search = By.name("search");
	private By searchBtn = By.xpath("//button[text()='Search']");
	private By accHeaders = By.cssSelector("div#content h2");

	

	// page const...

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);

	}
	
	//page actions/methods...
	
	@Step("getting login page title")
	public String getAccountsPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.ACCOUNTS_PAGE_TITLE, AppConstants.SHORT_DEFAUTT_WAIT);
		System.out.println("Acc page title is: " + title);
		return title;
	}

	@Step("getting login page url")
	public String getAccountsPageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.ACC_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAUTT_WAIT);
		System.out.println("Acc page url is: " + url);
		return url;
	}
	
	@Step("checking logout link exist")
	public boolean isLogoutLinkExist() {
		return eleUtil.waitForVisibilityOfElement(logoutLink, AppConstants.SHORT_DEFAUTT_WAIT).isDisplayed();
	}
	
	
	public void logOut() {
		if(isLogoutLinkExist()) {
			eleUtil.doClick(logoutLink);
		}
	}
	
	@Step("checking searchfield exist")
	public boolean isSearchFieldExist() {
		return eleUtil.waitForVisibilityOfElement(search, AppConstants.SHORT_DEFAUTT_WAIT).isDisplayed();
	}
	
	@Step("getting account page headers")
	public List<String> getAccountHeaders() {
		List<WebElement> headersList = eleUtil.waitForVisibilityOfElements(accHeaders, AppConstants.MEDIUM_DEFAUTT_WAIT);
		List<String> headersValList = new ArrayList();
		for(WebElement e : headersList) {
			String text = e.getText();
			headersValList.add(text);
		}
		
		return headersValList;
	}
	
	@Step("searching product by entering text")
	public SearchResultsPage doSearch(String searchKey) {
		eleUtil.waitForVisibilityOfElement(search, AppConstants.MEDIUM_DEFAUTT_WAIT).clear();
		eleUtil.waitForVisibilityOfElement(search, AppConstants.MEDIUM_DEFAUTT_WAIT).sendKeys(searchKey);
		eleUtil.doClick(searchBtn);
		return new SearchResultsPage(driver);//TDD
		
	}

}
