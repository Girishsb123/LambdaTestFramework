package com.qa.lamda.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.lamda.base.BaseTest;
import com.qa.lamda.constants.AppConstants;
import com.qa.lamda.listeners.TestAllureListener;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100 : Design LambdaTest login page")
@Story("US 101 : Login page features")
@Feature("F50: Feature Loginpage")
@Listeners(TestAllureListener.class)
public class LoginPageTest extends BaseTest {

	@Description("login page title test...")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);

	}
	
	@Description("login url title test...")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void loginPageUrlTest() {
		String actURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION));
	}
	

	@Description("verifying forgot pwd link test...")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Description("verifying App logo exist test...")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 4)
	public void appLogoExistTest() {
		Assert.assertTrue(loginPage.isLogoExist());
	}
	

	@Description("verifying user is able to login with correct credentials...")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 5)
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
		

	@Description("verifying linktexts on login page...")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 6)
	public void loginPageLinkTextTest() {
		List<String> linkTexts = loginPage.getLoginPageLinkTexts();
		Assert.assertTrue(linkTexts.contains("Downloads"));

	}

}
