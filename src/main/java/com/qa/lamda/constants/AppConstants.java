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
	
	public static final List<String> ACCOUNTS_PAGE_HEADERS_LIST =
			Arrays.asList("My Account","My Orders","My Affiliate Account");

	public static final CharSequence USER_REGISTER_SUCCESS_MESSG = " Your Account Has Been Created!";

	public static final String REGISTER_DATA_SHEET_NAME = "register";
	public static final String PRODUCT_DATA_SHEET_NAME = "register";
	
	public static final int IMPLICIT_WAIT = 5;
	public static final int EXPLICIT_WAIT = 15;
	public static final int PAGE_LOAD = 15;
	public static final int MESSAGE_WAIT = 40;
	

}
