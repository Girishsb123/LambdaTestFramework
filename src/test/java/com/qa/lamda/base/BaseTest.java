package com.qa.lamda.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.lamda.factory.DriverFactory;
import com.qa.lamda.pages.AccountsPage;
import com.qa.lamda.pages.LoginPage;
import com.qa.lamda.pages.ProductInfoPage;
import com.qa.lamda.pages.RegisterPage;
import com.qa.lamda.pages.SearchResultsPage;

public class BaseTest {

	protected WebDriver driver;
	protected Properties prop;
	DriverFactory df;
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	protected RegisterPage registerPage;
	
	protected SoftAssert softAssert;

	@Parameters({"browser","browserversion","testname"})
	@BeforeTest
	public void setUp(String browserName,String browserVersion,String testName) {
		df = new DriverFactory();
		prop = df.initProp();
		
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
			prop.setProperty("browserversion", browserVersion);
			prop.setProperty("testname", testName);
		}
		
		
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();

	}

	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
