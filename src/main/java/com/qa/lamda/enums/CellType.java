package com.qa.lamda.enums;

import org.apache.*;

public enum CellType {
	/**
	 * Unknow type,used to represent a state prior to initialization or the lack of
	 * a concreate type
	 */
	_NONE(-1), 
	
	/**
	 * Numeric cell type (whole numbers,fractional numbers, dates)
	 */
	
	NUMERIC(0), 
	
	/**
	 * String (text) cell type
	 */
	
	STRING(1), 
	
	/**
	 * Formula cell type
	 *@see FormulaType
	
	*/
	FORMULA(2), 
	
	/**
	 * Blank crll type
	 */
	
	BLANK(3),
	
	/**
	 * Boolean cell type
	 */
	
	
    BOOLEAN(4),
	
    /**
     * Error cell type
     * @see FormulaError
     */
	
	ERROR(5);

	private final int code;

	private CellType(int code) {
		this.code = code;
	}

	public static CellType forInt(int code) {
		for (CellType type : values()) {
			if (type.code == code) {
				return type;
			}
		}
		throw new IllegalArgumentException("Invalid CellType code : " + code);
	}

	public int getCode() {
		return code;
	}

}
