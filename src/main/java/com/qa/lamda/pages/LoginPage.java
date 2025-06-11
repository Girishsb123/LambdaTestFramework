package com.qa.lamda.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.lamda.constants.AppConstants;
import com.qa.lamda.utils.ElementUtil;
import com.qa.lamda.utils.JavaScriptUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private JavaScriptUtil jsUtil;

	// By locators: OR

	private By userName = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.cssSelector("input[value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By logo = By.cssSelector("img[alt='Poco Electro']");
	private By links = By.cssSelector(".list-group-item");
	private By registerLink = By.linkText("Register");
	private By allCategoriesOptions = By
			.xpath("//button[text()='All Categories']/following-sibling::div[@x-placement='bottom-start']/a");
	
	private static final Logger log = LogManager.getLogger(LoginPage.class);

	// page const...

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
		jsUtil = new JavaScriptUtil(this.driver);

	}

	// page actions/methods..

	@Step("getting login page title")
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_DEFAUTT_WAIT);
		//System.out.println("login page title is: " + title);
		log.info("login page title is: " + title);
		return title;
	}

	@Step("getting login url")
	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAUTT_WAIT);
		//System.out.println("login page url is: " + url);
		log.info("login page url is: " + url);
		return url;
	}
	
	@Step("checking for forgot pwd link exist")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.waitForVisibilityOfElement(forgotPwdLink, AppConstants.SHORT_DEFAUTT_WAIT).isDisplayed();
	}

	@Step("checking logo exist")
	public boolean isLogoExist() {
		return eleUtil.waitForVisibilityOfElement(logo, AppConstants.SHORT_DEFAUTT_WAIT).isDisplayed();
	}

	@Step("username is : {0} and password {1}")
	public AccountsPage doLogin(String username, String pwd) {
		//System.out.println("creds are " + username + " : " + pwd);
		log.info("creds are " + username + " : " + pwd);
		eleUtil.waitForVisibilityOfElement(userName, AppConstants.LONG_DEFAUTT_WAIT).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}

	@Step("checking linktext on loginpage")
	public List<String> getLoginPageLinkTexts() {
		List<String> linksListTexts = eleUtil.getElementsTextList(links);
		//System.out.println(linksListTexts);
		log.info(linksListTexts);
		return linksListTexts;
	}

	@Step("navigating to registration page")
	public RegisterPage navigateToRegisterPage() {
		eleUtil.waitForVisibilityOfElement(registerLink, AppConstants.LONG_DEFAUTT_WAIT).click();
		return new RegisterPage(driver);
	}

}
