package com.qa.lamda.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.lamda.base.BaseTest;
import com.qa.lamda.constants.AppConstants;
import com.qa.lamda.utils.ExcelUtil;

public class ProductResultsPageTest extends BaseTest {

	@BeforeClass
	public void productInfoSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] getSearchData() {
		return new Object[][] {
			
			{"MacBook","MacBook Pro",1},
			{"MacBook","MacBook Air",1},
			{"iMac","iMac",1},
			{"Samsung" , "Samsung SyncMaster 941BW",1},
		};
				

	}
	
	@DataProvider
	public Object[][] getSearchExcelTestData() {
		return ExcelUtil.getTestData(AppConstants.PRODUCT_DATA_SHEET_NAME);
				

	}

	@Test(dataProvider = "getSearchExcelTestData")
	public void productImagesTest(String searchKey,String productName,String imageCount) {
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		Assert.assertEquals(String.valueOf(productInfoPage.getProductImagesCount()), imageCount);// TDD
	}
	

	@Test
	public void productInfoTest() {
		searchResultsPage = accPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> productDetailsMap = productInfoPage.getProductDetails();

		Assert.assertEquals(productDetailsMap.get("Brand"), "Apple");
		Assert.assertEquals(productDetailsMap.get("Product Code"), "Product 18");
		Assert.assertEquals(productDetailsMap.get("Viewed"), "68413");
		Assert.assertEquals(productDetailsMap.get("Reward Points"), "800");

		Assert.assertEquals(productDetailsMap.get("price"), "$2,000.00");
	}

}
