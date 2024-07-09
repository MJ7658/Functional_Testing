package org.infy.init;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	static String Application;

	public static void main(String[] args) throws IOException {

		String appName = excelRead(11);
		String workflowName = appName.replace(";", "");

	}

	public static String excelRead(int value) throws IOException {

		String path = System.getProperty("user.dir") + "//Data//Expected.xlsx";

		FileInputStream file = new FileInputStream(path);

		XSSFWorkbook wbook = new XSSFWorkbook(file);

		XSSFSheet sheet1 = wbook.getSheetAt(0);

		int row = sheet1.getLastRowNum() - sheet1.getFirstRowNum();

		for (int i = 1; i <= row; i++) {
			Application = sheet1.getRow(i).getCell(value).getStringCellValue();
		}
		wbook.close();
		return Application;
	}
}
