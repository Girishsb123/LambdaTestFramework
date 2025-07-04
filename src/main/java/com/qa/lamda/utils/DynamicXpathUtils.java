package com.qa.lamda.utils;

public final class DynamicXpathUtils {
	
	private DynamicXpathUtils() {
		
	}

	public static String getXpath(String xpath,String value) {
		return String.format(xpath, value);//a[text()='%s']
	}
	
	public static String getXpath(String xpath, Object... orgs) {
		for(int i=0; i<orgs.length;i++) {
			xpath = xpath.replace("{" + i + "}", (CharSequence) orgs[i]);
		}
		
		return xpath;
	}
	
	
}
