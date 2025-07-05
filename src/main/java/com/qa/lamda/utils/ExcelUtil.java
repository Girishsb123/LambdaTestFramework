package com.qa.lamda.utils;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.qa.lamda.constants.AppConstants;
import com.qa.lamda.enums.ConfigProperties;
import com.qa.lamda.factory.DriverFactory;

public class ExcelUtil {

	private ExcelUtil() {

	}

	private static Workbook book;
	private static Sheet sheet;
	private static Properties prop;
	private static DriverFactory df;

	public static Object[][] getTestData(String sheetName) {

		System.out.println("reading test data from sheet : " + sheetName);

		Object data[][] = null;

		try {
			FileInputStream ip = new FileInputStream(AppConstants.TEST_DATA_SHEET_PATH);
			book = WorkbookFactory.create(ip);
			sheet = book.getSheet(sheetName);

			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
					data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}

	public static void getTestDetails(String filePath, String sheetName) {
		List<Map<String, String>> list = null;

		try (FileInputStream fs = new FileInputStream(filePath)) {

			String location = df.getValue(ConfigProperties.LOCATION);

			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet(sheetName);

			int lastrownum = sheet.getLastRowNum();
			int lastCOLnum = sheet.getRow(0).getLastCellNum();

			Map<String, String> map = null;
			list = new ArrayList<>();

			for (int row = 1; row <= lastrownum; row++) {
				String tdLoction = getCellValueAsString(sheet.getRow(row).getCell(2)).trim();
				String execute = getCellValueAsString(sheet.getRow(row).getCell(0)).trim();
				if(location.equalsIgnoreCase(tdLoction) || location.length() == 0 || location.equalsIgnoreCase("ALL")) {
					if(execute.length() > 0 && (!(execute.equalsIgnoreCase("No")))) {
						map = new HashMap<>();
						int lastcolumn=0;
						for(int col =0 ; col < lastcolumn; col++) {
							String key = getCellValueAsString(sheet.getRow(0).getCell(col));
							String value = getCellValueAsString(sheet.getRow(row).getCell(col));
						}
						list.add(map);
					}
				}
			}
			
			((Closeable) workbook).close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String getCellValueAsString(Cell cell) {
		DataFormatter formatter = new DataFormatter();
		String cellValue = null;
		cellValue = formatter.formatCellValue(cell);
		return cellValue;
	}

	public static String getCellValueAsString(Cell cell, String key) {
		String cellValue = null;
	
		switch (cell.getCellType()) {
		case BOOLEAN:
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		case NUMERIC:
			if (key.toLowerCase().contains("date")) {
				cellValue = DateTimeUtil.getStringFromDate(cell.getDateCellValue());
			} else {
				cellValue = String.valueOf(cell.getNumericCellValue());
			}

			break;
		case STRING:
			cellValue = cell.getStringCellValue();
		case BLANK:
			cellValue = "";
		default:
			cellValue = "";
			break;
		}
	}

	public static List<String> getColumnData(String filePath, String sheetName, String columnName) {

		List<String> list = null;

		try (FileInputStream fs = new FileInputStream(filePath)) {

			String location = df.getValue(ConfigProperties.LOCATION);

			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet(sheetName);

			int lastrownum = sheet.getLastRowNum();
			int lastCOLnum = sheet.getRow(0).getLastCellNum();

			Map<String, String> map = null;
			list = new ArrayList<>();

			XSSFRow xrow = sheet.getRow(0);
			Integer columnNo = null;

			for (Cell cell : xrow) {
				// column header names.
				// System.out.println(cell.toString());
				if (cell.getRichStringCellValue().equals(columnName)) {
					columnNo = cell.getColumnIndex();
				}

				for (int row = 1; row <= lastrownum; row++) {
					String value = getCellValueAsString(sheet.getRow(row).getCell(columnNo));
					list.add(value);
				}

				((Closeable) workbook).close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;

	}

}
