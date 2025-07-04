package com.qa.lamda.enums;

public enum MCDFieldsEnum {

	CLUBCODE("Club Code"), MEMBERSHIPNUMBER("Number"),	STATUS("Status"), EMAILADDRESS("Email Address");

	private String value;

	MCDFieldsEnum(String s) {
		value = s;
	}

	/**
	 * 
	 * @return value
	 */

	public String getValue() {
		return value;
	}

}
