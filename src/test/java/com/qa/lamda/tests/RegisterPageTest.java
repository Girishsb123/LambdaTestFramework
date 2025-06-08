package com.qa.lamda.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.lamda.base.BaseTest;
import com.qa.lamda.constants.AppConstants;
import com.qa.lamda.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void regSetUp() {
		registerPage = loginPage.navigateToRegisterPage();
	}

	public String getRandomEmailId() {
		return "testautomation" + System.currentTimeMillis() + "@lambdatest.com";
		// testautomation1212@lambdatest.com
//		return "testautomation" + UUID.randomUUID() + "@gmail.com";
	}

//	@DataProvider
//	public Object[][] getUserRegData() {
//		return new Object[][] { { "Sushma", "M", "912499939", "test@123", "yes" },
//				{ "Sweety", "P", "992494939", "test@123", "no" }, { "Sparsha", "N", "992499937", "test@123", "yes" }, };
//	}

	@DataProvider
	public Object[][] getUserRegTestExcelData() {
		Object regData[][] = ExcelUtil.getTestData(AppConstants.REGISTER_DATA_SHEET_NAME);
		return regData;
	}

	@Test(dataProvider = "getUserRegTestExcelData")
	public void userRegisterTest(String firstName, String lastName, String phoneNumber, String password,
			String subscribe) {
		boolean isRegDone = registerPage.userRegistration(firstName, lastName, getRandomEmailId(), phoneNumber,
				password, subscribe);

		Assert.assertTrue(isRegDone);
	}

}
