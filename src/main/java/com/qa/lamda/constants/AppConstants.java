package com.qa.lamda.constants;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class AppConstants {

	public static final String LOGIN_PAGE_TITLE = "Account Login";

	public static final String ACCOUNTS_PAGE_TITLE = "My Account";

	public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login";
	public static final String ACC_PAGE_URL_FRACTION = "route=account/account";
	public static final int ACCOUNTS_PAGE_HEADERS_COUNT = 3;

	public static final int SHORT_DEFAUTT_WAIT = 5;
	public static final int MEDIUM_DEFAUTT_WAIT = 15;
	public static final int LONG_DEFAUTT_WAIT = 20;

	public static final int POLLING_DEFAUTT_WAIT = 2;

	public static final List<String> ACCOUNTS_PAGE_HEADERS_LIST = Arrays.asList("My Account", "My Orders",
			"My Affiliate Account");

	public static final CharSequence USER_REGISTER_SUCCESS_MESSG = " Your Account Has Been Created!";

	public static final String REGISTER_DATA_SHEET_NAME = "register";
	public static final String PRODUCT_DATA_SHEET_NAME = "register";

	public static final int IMPLICIT_WAIT = 5;
	public static final int EXPLICIT_WAIT = 15;
	public static final int PAGE_LOAD = 15;
	public static final int MESSAGE_WAIT = 40;

	private static final int DATA_PROVIDER_THREAD_COUNT_DEFAULT = 1;

	private static final String RESOURCES_TEST_PATH = "/src/test/resources";

	private static final String USER_DIR = "user.dir";

	public static final String CONFIG_PROPERTY_FILE_QA = System.getProperty(USER_DIR) + RESOURCES_TEST_PATH
			+ "/config/config.qa.properties";

	public static final String CONFIG_PROPERTY_FILE_DEV = System.getProperty(USER_DIR) + RESOURCES_TEST_PATH
			+ "/config/config.dev.properties";

	public static final String CONFIG_PROPERTY_FILE_STAGE = System.getProperty(USER_DIR) + RESOURCES_TEST_PATH
			+ "/config/config.stage.properties";

	public static final String CONFIG_PROPERTY_FILE_UAT = System.getProperty(USER_DIR) + RESOURCES_TEST_PATH
			+ "/config/config.uat.properties";

	public static final String CONFIG_PROPERTY_FILE_PROD = System.getProperty(USER_DIR) + RESOURCES_TEST_PATH
			+ "/config/config.prod.properties";

	public static final String EXCEL_TESTDATA_FILE = System.getProperty(USER_DIR) + RESOURCES_TEST_PATH
			+ "/excel/LambdaAppTestData.xlsx";

	public static final String TEST_DATA_SHEET_PATH = System.getProperty(USER_DIR) + RESOURCES_TEST_PATH
			+ "/testdata/TestData.xlsx";

}
