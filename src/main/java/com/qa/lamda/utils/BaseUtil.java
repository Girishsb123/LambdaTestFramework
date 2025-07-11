package com.qa.lamda.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.qa.lamda.enums.WaitStrategy;
import com.qa.lamda.factory.DriverFactory;
import com.qa.lamda.factory.WaitFactory;

public class BaseUtil {

	private static final Logger log = LogManager.getLogger(BaseUtil.class);

	public static void getURL(String url) {
		DriverFactory.getDriver().get(url);
	}

	protected String getPageTitle() {
		return DriverFactory.getDriver().getTitle();
	}

	protected WebElement getWebElement(By by) {
		return DriverFactory.getDriver().findElement(by);
	}

	public List<WebElement> getWebElements(By by) {
		return DriverFactory.getDriver().findElements(by);
	}

	public String getWindowHandle() {
		return DriverFactory.getDriver().getWindowHandle();
	}

	public Set<String> getWindowHandles() {
		return DriverFactory.getDriver().getWindowHandles();
	}

	public void switchToWindow(String window) {
		DriverFactory.getDriver().switchTo().window(window);
	}

	public void click(By by, WaitStrategy waitStrategy, String elementName) {
		WebElement element = WaitFactory.PerformExplicitWait(waitStrategy, by);
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
		js.executeScript("arguments[0].click;", element);
		log.info("successfully clicked on " + elementName);

	}

	public void click(WebElement element, WaitStrategy waitStrategy, String elementName) {

		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
		js.executeScript("arguments[0].click;", element);
		log.info("successfully clicked on " + elementName);

	}

	public void clickOnTab(By by) {
		WebElement element = WaitFactory.PerformExplicitWait(WaitStrategy.NONE, by);
		element.sendKeys(Keys.TAB);
	}

	public void clickOnEnter(By by) {
		WebElement element = WaitFactory.PerformExplicitWait(WaitStrategy.NONE, by);
		element.sendKeys(Keys.ENTER);
	}

	public void sendText(By by, String value, WaitStrategy waitStrategy, String elementName) {
		WebElement element = WaitFactory.PerformExplicitWait(waitStrategy, by);
		element.clear();
		element.sendKeys(value);
		// element.sendKeys(Keys.TAB);
		log.info(value + " is entered successfully in " + elementName);

	}

	/**
	 * This method is used to send text through Action class and then press Arrow
	 * down and Enter keys
	 * 
	 * @param by
	 * @param value
	 * @param waitStrategy
	 * @param elementName
	 */

	protected void sendKeys(By by, String value, WaitStrategy waitStrategy, String elementName) {
		WebElement element = WaitFactory.PerformExplicitWait(waitStrategy, by);
		element.clear();
		Actions actions = new Actions(DriverFactory.getDriver());
		String arrowDownEnterKey = Keys.chord(Keys.ARROW_DOWN, Keys.ENTER);
		actions.sendKeys(element, value).pause(Duration.ofSeconds(1)).sendKeys(arrowDownEnterKey).build().perform();
		log.info(value + " is entered successfully in " + elementName);

	}

	protected void mouseOver(By by, String value, WaitStrategy waitStrategy, String elementName) {
		WebElement element = WaitFactory.PerformExplicitWait(waitStrategy, by);
		element.clear();
		Actions actions = new Actions(DriverFactory.getDriver());
		String arrowDownEnterKey = Keys.chord(Keys.ARROW_DOWN, Keys.ENTER);
		actions.moveToElement(element).build().perform();
		log.info("Mouse is hovered over element - " + elementName);

	}

	public String getText(By by, String value, WaitStrategy waitStrategy, String elementName) {
		String getText = "";

		try {
			getText = WaitFactory.PerformExplicitWait(waitStrategy, by).getText().trim();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!elementName.isBlank()) {
			log.info("Successfully fetched the " + elementName + " text as " + getText);
		}

		return getText;
	}

	public String getAttribute(By by, WaitStrategy waitStrategy, String elementName) {

		String getText = WaitFactory.PerformExplicitWait(waitStrategy, by).getDomAttribute("value").trim();

		if (!elementName.isBlank()) {
			log.info("Successfully fetched the " + elementName + " text as " + getText);
		}

		return getText;
	}

	public String getAttribute(By by, String attributeName, WaitStrategy waitStrategy, String elementName) {

		String getText = WaitFactory.PerformExplicitWait(waitStrategy, by).getDomAttribute(attributeName).trim();

		if (!elementName.isBlank()) {
			log.info("Successfully fetched the " + elementName + " text as " + getText);
		}

		return getText;
	}

	public String getText(Function<WebElement, String> function, By by, WaitStrategy waitStrategy, String elementName) {

		WebElement element = WaitFactory.PerformExplicitWait(waitStrategy, by);
		String getText = function.apply(element);

		if (!elementName.isBlank()) {
			log.info("Successfully fetched the " + elementName + " text as " + getText);
		}

		return getText;
	}

	public String getText(By by, WaitStrategy waitStrategy, String getTextType, String attributeName,
			String elementName) {

		Function<WebElement, String> function = getFunctionOnType(getTextType, attributeName);
		WebElement element = WaitFactory.PerformExplicitWait(waitStrategy, by);
		String getText = function.apply(element);

		if (!elementName.isBlank()) {
			log.info("Successfully fetched the " + elementName + " text as " + getText);
		}

		return getText;
	}

	private static Function<WebElement, String> getFunctionOnType(String getTextType, String attributeName) {

		String GET_TEXT = "";
		String GET_ATTRIBUTE = "";

		Function<WebElement, String> function = null;
		if (GET_TEXT.equalsIgnoreCase(getTextType)) {
			function = WebElement::getText;
		} else if (GET_ATTRIBUTE.equalsIgnoreCase(getTextType))
			function = e -> e.getDomAttribute(attributeName);

		return function;
	}

	public static void selectFromDropDown(Consumer<Select> consumer, By by, String elementName) {
		WebElement element = WaitFactory.PerformExplicitWait(WaitStrategy.VISIBLE, by);
		consumer.accept(new Select(element));
		log.info("Selected value from dropdown - " + elementName);
	}

	public void verifyDisplayed(By by, String elementName) {
		WaitFactory.PerformExplicitWait(WaitStrategy.VISIBLE, by);
		log.info("Element is displayed : " + elementName);
	}

	protected void verifyNotDisplayed(By by, String elementName) {

		boolean isDisplayed = false;

		try {
			ExpectedCondition<Boolean> textEqual = args -> !isDisplayed(by);
			WaitFactory.getExplicitWait().until(textEqual);
			DriverFactory.getDriver().findElement(by).isDisplayed();
		} catch (Exception e) {

		}

		if (!isDisplayed)
			log.info("Element is not displayed : " + elementName);
		else
			log.info("Element is displayed : " + elementName);
	}

	protected void verifySelected(By by, String elementName) {
		WebElement element = WaitFactory.PerformExplicitWait(WaitStrategy.VISIBLE, by);
		if (element.isSelected())
			log.info("PASS , Element is checked/selected : " + elementName);
		else
			log.info("FAIL , Element is not checked/selected : " + elementName);
	}

	protected void verifyNotSelected(By by, String elementName) {
		WebElement element = WaitFactory.PerformExplicitWait(WaitStrategy.VISIBLE, by);
		if (!element.isSelected())
			log.info("PASS, Element is not checked/selected : " + elementName);
		else
			log.info("FAIL, Element is checked/selected : " + elementName);
	}

	protected void verifyEnabled(By by, String elementName) {
		WebElement element = WaitFactory.PerformExplicitWait(WaitStrategy.VISIBLE, by);
		if (element.isEnabled())
			log.info("PASS, Element is Enabled : " + elementName);
		else
			log.info("FAIL, Element is not Enabled : " + elementName);
	}

	public boolean isDisplayed(By by) {
		boolean isDisplayed = false;

		try {
			WebElement ele = WaitFactory.PerformExplicitWait(WaitStrategy.VISIBLE, by);
			isDisplayed = ele.isDisplayed();
		} catch (Exception e) {

		}

		return isDisplayed;

	}

	public boolean isPresent(By by) {
		boolean isPresent = false;

		try {
			WebElement ele = WaitFactory.PerformExplicitWait(WaitStrategy.PRESENT, by);
			isPresent = true;
		} catch (Exception e) {

		}

		return isPresent;

	}

	public boolean isEnabled(By by) {
		boolean isEnabled = false;

		try {
			WebElement ele = WaitFactory.PerformExplicitWait(WaitStrategy.VISIBLE, by);
			isEnabled = ele.isEnabled();
		} catch (Exception e) {

		}

		return isEnabled;

	}

	public boolean isSelected(By by) {
		boolean isChecked = false;

		try {
			WebElement ele = WaitFactory.PerformExplicitWait(WaitStrategy.VISIBLE, by);
			isChecked = ele.isSelected();
		} catch (Exception e) {

		}

		return isChecked;

	}

	protected void waitForNotDisplayed(By by) {
		ExpectedCondition<Boolean> textEqual = args -> !isDisplayed(by);
		WaitFactory.getExplicitWait().until(textEqual);
	}

	public boolean waitForCondition(ExpectedCondition<Boolean> expCondition) {
		boolean flag = false;

		try {
			WaitFactory.getExplicitWait().until(expCondition);
			flag = true;
		} catch (Exception e) {

		}

		return flag;
	}

	public boolean waitForMessages(ExpectedCondition<Boolean> expCondition) {
		boolean flag = false;

		try {
			WaitFactory.getExplicitWait().until(expCondition);
			flag = true;
		} catch (Exception e) {

		}

		return flag;
	}

	public boolean verifyText(String actualResult, String expectedResult) {

		boolean equalsFlag = false;

		if (actualResult.equalsIgnoreCase(expectedResult)) {
			equalsFlag = true;
			log.info("PASS , Text verified successfully : " + actualResult + " with " + expectedResult);
		} else {
			log.error("FAIL, Text not verified " + actualResult + " with " + expectedResult);
		}

		return equalsFlag;
	}

	public void acceptAlert() {

		try {
			WaitFactory.getExplicitWait().until(ExpectedConditions.alertIsPresent());
			DriverFactory.getDriver().switchTo().alert().accept();
			log.info("Accepted the alert");

		} catch (UnhandledAlertException ue) {
			try {
				acceptAlert();
			} catch (NoAlertPresentException e) {
				e.printStackTrace();
			}
		}
	}

	public void dismissAlert() {
		DriverFactory.getDriver().switchTo().alert().dismiss();
		log.info("Dismissed the alert");
	}

	public boolean isAlertPresent() {

		boolean alertFlag = false;

		try {
			WaitFactory.getExplicitWait().until(ExpectedConditions.alertIsPresent());
			alertFlag = true;

		} catch (UnhandledAlertException ue) {
			try {
				acceptAlert();
			} catch (NoAlertPresentException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {

		}
		return alertFlag;
	}

	public List<String> getWebElementListText(By by, String message) {

		try {
			WaitFactory.getExplicitWait().until(ExpectedConditions.visibilityOfElementLocated(by));
			return getWebElements(by).stream().map(WebElement::getText).collect(Collectors.toList());
		} catch (Exception e) {

		}

		return new ArrayList<>();
	}

	protected List<String> getWebElementListText(List<WebElement> elements, String message) {

		return elements.stream().map(e -> {
			WaitFactory.getExplicitWait().until(ExpectedConditions.visibilityOf(e));
			return e.getText();
		}).collect(Collectors.toList());

	}

	public void scrollToTheView(By by, WaitStrategy waitStrategy, String elementName) {

		WebElement element = WaitFactory.PerformExplicitWait(waitStrategy, by);
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
		js.executeScript("arguments[0].scrollIntoView();", element);
	}
	
	public By getByObject(String customXpath,String memberField) {
		return By.xpath(DynamicXpathUtils.getXpath(customXpath, memberField));
	}
	

	protected void doubleClick(By by, WaitStrategy waitStrategy, String elementName) {

		WebElement element = WaitFactory.PerformExplicitWait(waitStrategy, by);
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
		Actions actions = new Actions(DriverFactory.getDriver());
		actions.doubleClick(element).build().perform();
		log.info("PASS, Double click over element - " + elementName);
	}
	
	public void closeCurrentBrowser() {
		DriverFactory.getDriver().close();
	}

}
