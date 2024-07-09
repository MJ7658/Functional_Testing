package org.infy.reportHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReportCompare {
	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(PipelineConfigResultHelper.class);
	static String Application;
	static MultiValuedMap<Integer, Integer> errorMap = new ArrayListValuedHashMap<>();

	public static void main(String[] args) throws IOException, InterruptedException {
		delectExcel();

	}

	public static void comparitionSheet1Result() throws IOException, InterruptedException {
		if (Compare() == true) {
			log.info("Excel Sheet1 is working Fine");
		} else {
			log.info("Excel Sheet1 is NOT working Fine");
		}
	}

	public static void comparitionSheet2Result() throws IOException, InterruptedException {
		if (compareSheet2() == true) {
			log.info("Excel Sheet2 is working Fine");
		} else {
			log.info("Excel Sheet2 is NOT working Fine");
		}
	}

	public static Boolean Compare() throws IOException, InterruptedException {
		String Expected = System.getProperty("user.dir") + "\\Data\\" + "Expected.xlsx";
		String Result = waitForTheExcelFileToDownload("IDP_report");
		Boolean flag = true;
		for (int i = 0; i < 12; i++) {
			String Expecteds = readRows(Expected, 1, i);
			String Results = readRows(Result, 1, i);

			String newExpect = Expecteds.replaceAll(" ", "");
			String newResult = Results.replaceAll(" ", "");

			if (newExpect.equalsIgnoreCase(newResult)) {
				String Title = readRows(Expected, 0, i);
			} else {
				String Title = readRows(Expected, 0, i);
				log.info(Title + " " + "Not Working fine ");
				flag = false;
			}
		}
		return flag;
	}

	public static Boolean compareSheet2() throws IOException, InterruptedException {
		String Expected = System.getProperty("user.dir") + "\\Data\\" + "Expected.xlsx";
		String Result = waitForTheExcelFileToDownload("IDP_report");
		Boolean flag = true;
		for (int i = 0; i < 4; i++) {
			String Expecteds = readSheet2Rows(Expected, 1, i);
			String Results = readSheet2Rows(Result, 1, i);

			String newExpect = Expecteds.replaceAll(" ", "");
			String newResult = Results.replaceAll(" ", "");

			if (newExpect.equalsIgnoreCase(newResult)) {
				String Title = readSheet2Rows(Expected, 0, i);
			} else {
				String Title = readSheet2Rows(Expected, 0, i);
				log.info(Title + " " + "Not Working fine ");
				flag = false;
			}
		}
		return flag;
	}

	public static void delectExcel() throws IOException, InterruptedException {
		File[] file_Details = new File(System.getProperty("user.dir") + "\\Data\\").listFiles();
		for (File file : file_Details) {
			log.info(file.getName());
			if (file.getName().contains("Execution_Report")) {
				log.info("File name is..." + file.getName());
				file.deleteOnExit();
				if (file.getName().contains("Execution_Report")) {
					log.info("File is successfully deleted!");
				} else {
					log.info("Sorry, the file was not deleted.");
				}
			}

		}
	}

	public static void FirstStepToDeleteExcel(String ExcelName) throws IOException, InterruptedException {
		try {
			File[] file_Details = new File(System.getProperty("user.dir") + "\\Data\\").listFiles();
			for (File file : file_Details) {
				log.info(file.getName());
				if (file.getName().contains(ExcelName)) {
					log.info("File name is..." + file.getName());
					file.deleteOnExit();
					if (file.getName().contains(ExcelName)) {
						log.info("File is successfully deleted!");
					} else {
						log.info("Sorry, the file was not deleted.");
					}
				}

			}
		} catch (Exception e) {
			log.info("No File Available");
		}
	}

	public static String readRows(String path, int rowIndex, int value) throws IOException {
		String particlurRows = null;
		FileInputStream file = new FileInputStream(path);
		XSSFWorkbook wbook = new XSSFWorkbook(file);
		XSSFSheet sheet1 = wbook.getSheetAt(0);
		XSSFRow row = sheet1.getRow(rowIndex);

		for (int i = 1; i <= 13; i++) {
			Application = row.getCell(value).getStringCellValue();
		}
		wbook.close();
		return Application;
	}

	public static String readSheet2Rows(String path, int rowIndex, int value) throws IOException {
		String particlurRows = null;
		FileInputStream file = new FileInputStream(path);
		XSSFWorkbook wbook = new XSSFWorkbook(file);
		XSSFSheet sheet2 = wbook.getSheetAt(1);
		XSSFRow row = sheet2.getRow(rowIndex);

		for (int i = 1; i <= 4; i++) {
			Application = row.getCell(value).getStringCellValue();
		}
		wbook.close();
		return Application;
	}

	public static String waitForTheExcelFileToDownload(String fileName) throws IOException, InterruptedException {
		File[] file_Detail = new File(System.getProperty("user.dir") + "\\Data\\").listFiles();
		for (File file : file_Detail) {
			if (file.getName().contains(fileName)) {
				log.info("File Found");
				String newo = file.getAbsolutePath();
				return newo;
			} else {

			}
		}
		return fileName;
	}

}
