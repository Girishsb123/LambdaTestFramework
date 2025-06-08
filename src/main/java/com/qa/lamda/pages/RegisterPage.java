package com.qa.lamda.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.lamda.constants.AppConstants;
import com.qa.lamda.utils.ElementUtil;
import com.qa.lamda.utils.JavaScriptUtil;

import io.qameta.allure.Step;

public class RegisterPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private JavaScriptUtil jsUtil;

	// By locators: OR

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By subscribeYes = By.xpath("(//input[@class='custom-control-input']/following-sibling::label)[1]");
	private By subscribeNo = By.xpath("(//input[@checked='checked']/following-sibling::label)[1]");

	private By privacyCheckBox = By.cssSelector("label[for='input-agree']");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");

	private By successMessg = By.cssSelector("div#container h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	// page const...

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
		jsUtil = new JavaScriptUtil(this.driver);

	}

	// page actions/methods...

	@Step("user registration with fistname : {0} , lastname{1} , email{2}, telephone{3}, password{4}, subscribe{5}")
	public boolean userRegistration(String firstName, String lastName, String email, String telephone, String password,
			String subscribe) {

		eleUtil.waitForVisibilityOfElement(this.firstName, AppConstants.LONG_DEFAUTT_WAIT).sendKeys(firstName);

		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmpassword, password);

		if (subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		} else {
			eleUtil.doClick(subscribeNo);
		}

		eleUtil.doClick(privacyCheckBox);
		eleUtil.doClick(continueButton);

		String successMesg = eleUtil.waitForVisibilityOfElement(successMessg, AppConstants.LONG_DEFAUTT_WAIT).getText();
		System.out.println(successMesg);

		if (successMesg.contains(AppConstants.USER_REGISTER_SUCCESS_MESSG)) {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		} else {
			return false;
		}

	}
}
