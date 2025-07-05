package com.qa.lamda.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;

import com.qa.lamda.constants.AppConstants;
import com.qa.lamda.enums.ConfigProperties;
import com.qa.lamda.exception.FrameworkException;

public class DriverFactory {

	WebDriver driver;
	static Properties prop;
	OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	private static final Logger log = LogManager.getLogger(DriverFactory.class);

	public static String highlight = null;

	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser");
		// String browserName = System.getProperty("browser");

		// System.out.println("browser name is : " + browserName);
		log.info("browser name is : " + browserName);

		highlight = prop.getProperty("highlight");

		optionsManager = new OptionsManager(prop);

		switch (browserName.toLowerCase().trim()) {

		case "chrome":

			log.info("Running it on chrome");
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// run it on grid:
				log.info("Running it on remote");
				initRemoteDriver(browserName);
			} else {
				// run it on local:
				log.info("Running it on local machine");
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}

			break;

		case "firefox":
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// run it on grid:
				initRemoteDriver(browserName);
			} else {
				// run it on local:
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			}
			break;

		case "edge":
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// run it on grid:
				initRemoteDriver(browserName);
			} else {
				// run it on local:
				tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			}
			break;

		case "safari":
			// driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;

		default:
			// System.out.println("please pass the right browser name... : " + browserName);
			log.warn("please pass the right browser name... : " + browserName);
			throw new FrameworkException("NO BROWSER FOUND");

		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();
	}

	/**
	 * run tests on grid
	 * 
	 * @param browserName
	 */

	private void initRemoteDriver(String browserName) {
		// System.out.println("Running tests on GRID with browser : " + browserName);
		log.info("Running tests on GRID with browser : " + browserName);

		try {
			switch (browserName.toLowerCase().trim()) {

			case "chrome":
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));
				break;
			case "firefox":
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFirefoxOptions()));
				break;
			case "edge":
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getEdgeOptions()));
				break;

			default:
				// System.out.println("wrong browser info..can not run on grid machine....");
				log.warn("wrong browser info..can not run on grid machine....");
				break;
			}
		} catch (MalformedURLException e) {

		}

	}

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	public static void unLoad() {
		tlDriver.remove();
	}

	public Properties initProp() {

		// mvn clean install -Denv = "qa"
		FileInputStream ip = null;
		prop = new Properties();

		String envName = System.getProperty("env");
		//System.out.println("env name is : " + envName);
		log.info("env name is : " + envName);

		try {

			if (envName == null) {
				log.warn("your env is null...hence running tests on QA env...");
				//System.out.println("your env is null...hence running tests on QA env...");
				ip = new FileInputStream(AppConstants.CONFIG_PROPERTY_FILE_QA);
				log.info(ip);
			}

			else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream(AppConstants.CONFIG_PROPERTY_FILE_QA);
					break;
				case "dev":
					ip = new FileInputStream(AppConstants.CONFIG_PROPERTY_FILE_DEV);
					break;
				case "stage":
					ip = new FileInputStream(AppConstants.CONFIG_PROPERTY_FILE_STAGE);
					break;
				case "uat":
					ip = new FileInputStream(AppConstants.CONFIG_PROPERTY_FILE_UAT);
					break;
				case "prod":
					ip = new FileInputStream(AppConstants.CONFIG_PROPERTY_FILE_PROD);
					break;

				default:
					System.out.println("please pass the right env name : " + envName);
					log.error("wrong env name : " + envName);
					throw new FrameworkException("Wrong Env Name");
				}
			}
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}		
		public static String getValue(ConfigProperties key) {
			
			if(Objects.isNull(key) || Objects.isNull(prop.getProperty(key.name().toLowerCase()))) {
				Assert.assertTrue(false, "Properties file key " + key + " or value "
			
						+ prop.getProperty(key.name().toLowerCase()) + prop.getProperty(key.name().toLowerCase()) + "is null. please check");
			}
			
			return prop.getProperty(key.name().toLowerCase(null));

	}

	/**
	 * take screenshot
	 */
	public static String getScreenshot(String methodName) {

		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";

		File destination = new File(path);

		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}

}
