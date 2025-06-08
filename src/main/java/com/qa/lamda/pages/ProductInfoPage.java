package com.qa.lamda.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.lamda.constants.AppConstants;
import com.qa.lamda.utils.ElementUtil;

import io.qameta.allure.Step;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productHeader = By.xpath("//h1[normalize-space()='MacBook Pro']");
	private By productImages = By.xpath("//div[@id='image-gallery-216811']//div[@class='image-thumb d-flex']//img[@title='MacBook Pro']");
	private By productMetaData = By.cssSelector("ul.list-unstyled li");
	private By productPriceData = By.xpath("//h3[text()='$2,000.00']");

	private Map<String, String> productMap = new HashMap<String, String>();
	//private Map<String, String> productMap = new LinkedHashMap<String, String>();
	//private Map<String, String> productMap = new TreeMap<String, String>();

	
	// page const...
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);

	}
	
	@Step("getting prodcut headername")
	public String getProductHeaderName() {
		String productHeaderVal = eleUtil.doElementGetText(productHeader);
		System.out.println("product header: " + productHeaderVal);
		return productHeaderVal;
	}

	@Step("getting prodcut imagescount")
	public int getProductImagesCount() {
		int imagesCount = eleUtil.waitForVisibilityOfElements(productImages, AppConstants.MEDIUM_DEFAUTT_WAIT).size();
		System.out.println("Product " + getProductHeaderName() + " images count : " + imagesCount);
		return imagesCount;
	}
	
//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stock
	
	@Step("getting prodcut data")
	private void getProductMetaData() {
		List<WebElement> metaDataList = eleUtil.waitForVisibilityOfElements(productMetaData, AppConstants.MEDIUM_DEFAUTT_WAIT);
		
		for(WebElement e : metaDataList) {
			String metaData = e.getText();//Brand: Apple
			String metaKey = metaData.split(":")[0].trim();
			String metaVal = metaData.split(":")[1].trim();
			productMap.put(metaKey, metaVal);
		}
	}
	
	@Step("getting prodcut pricedata")
	private void getProductPriceData() {
		List<WebElement> metaPriceList = eleUtil.waitForVisibilityOfElements(productPriceData, AppConstants.MEDIUM_DEFAUTT_WAIT);
		
		String productPrice = metaPriceList.get(0).getText();
		
		productMap.put("price", productPrice);
//		productMap.put("extaxprice", productExTaxPrice);
	}
	
	
	@Step("getting prodcut details")
	public Map<String, String> getProductDetails() {
		productMap.put("productname", getProductHeaderName());
		getProductMetaData();
		getProductPriceData();
		
		System.out.println("product info: " + productMap);
		return productMap;
	}
	
	

}
