package com.qa.lamda.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;

	public OptionsManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) co.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) co.addArguments("--incognito");
		
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			co.setCapability("browserName", "chrome");
			co.setBrowserVersion(prop.getProperty("browserversion").trim());
			
			Map<String, Object> selenoidOptions = new HashMap<>();
			selenoidOptions.put("screenResolution", "1280x1024x24");
			selenoidOptions.put("enableVNC", true);
			selenoidOptions.put("name", prop.getProperty("testname"));
			co.setCapability("selenoid:options", selenoidOptions);
		}

		return co;

	}
	
	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) fo.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) fo.addArguments("--incognito");

		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
//			fo.setCapability("browserName", "firefox");
//			fo.setBrowserVersion(prop.getProperty("browserversion").trim());
//			
//			Map<String, Object> selenoidOptions = new HashMap<>();
//			selenoidOptions.put("screenResolution", "1280x1024x24");
//			selenoidOptions.put("enableVNC", true);
//			selenoidOptions.put("name", prop.getProperty("testname"));
//			fo.setCapability("selenoid:options", selenoidOptions);
		
		}
		return fo;
//	}
//	

	}
	
	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();
		eo.setCapability("platform", Platform.LINUX);
		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) eo.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) eo.addArguments("--inPrivate");

		return eo;

	}

}
