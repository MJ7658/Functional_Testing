package org.infy.reportHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

public class PipelineConfigResultHelper {

	static String Application;
	private WebDriver driver;
	static String applicationName = null;
	static String updatedBy = null;
	ExtentTest logger;
	private WebDriverWait wait;
	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(PipelineConfigResultHelper.class);
	public static String applicationNameToBeCreated;
	public static String applicationWorkflowNameToBeCreated;
	public static String applicationWorkflowNameCreated;
	public int count, flag;
	public static boolean statusVerify;

	public PipelineConfigResultHelper(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(8000));
	}

	public static void executionReportExpectedResultwriter() throws IOException, InterruptedException {

		String path = System.getProperty("user.dir") + "//Data//PipelineConfigReportExpected.xlsx";
		Workbook wb = new XSSFWorkbook();
		FileOutputStream fs = new FileOutputStream(path);
		Sheet sheet1 = wb.createSheet("sheet1");

		String[] data = { "Pipeline", "Application", "Stages", "Plugins", "Stages: Environment", "Stages: Worker",
				"Parameters", "Workflow", "UpdatedBy", "CreatedAt", "UpdatedAt" };

		Random ra = new Random();
		int n = ra.nextInt(9999);

		updatedBy = "satheshkumar.m@infosys.com";
		String Approved = "satheshkumar.m@infosys.com";
		String applicationName = "WorkerAutomation";
		String workerName = "AutomationDemo" + n;
		String workerNameAndAppname = applicationName + "_" + workerName;
		String PipelineName = "pipeline" + workerName;
		String Workflow = applicationName + "_" + PipelineName;
		String Stage = "stage1";

		String[] data1 = { PipelineName, applicationName, "stage1:cmdexec", "cmdexec,", "stage1:qa,",
				Stage + ":" + workerNameAndAppname, "UserName:test;", Workflow + ";", updatedBy, currentDatePicker(),
				currentDatePicker() };

		Row thirdrow = sheet1.createRow(0);
		for (int i = 0; i < data.length; i++) {
			thirdrow.createCell(i).setCellValue(data[i]);
		}
		Row thirdrow1 = sheet1.createRow(1);
		for (int i = 0; i < data1.length; i++) {
			thirdrow1.createCell(i).setCellValue(data1[i]);
		}
		wb.write(fs);
		wb.close();
		fs.close();
	}

	public static String currentDatePicker() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy,MM,d");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		String curntDate = date.replace(",", "-");
		return curntDate;
	}

	public static void main(String[] arg) throws IOException, InterruptedException {
		executionReportExpectedResultwriter();
		log.info(currentDatePicker());
	}

	public static String excelRead(int value) throws IOException {

		String path = System.getProperty("user.dir") + "//Data//PipelineConfigReportExpected.xlsx";

		FileInputStream file = new FileInputStream(path);

		XSSFWorkbook wbook = new XSSFWorkbook(file);

		XSSFSheet sheet1 = wbook.getSheetAt(0);

		int row = sheet1.getLastRowNum() - sheet1.getFirstRowNum();
		DataFormatter df = new DataFormatter();
		for (int i = 1; i <= row; i++) {
			Application = df.formatCellValue(sheet1.getRow(i).getCell(value));
		}
		wbook.close();
		return Application;
	}

	public static void comparitionSheet1Result() throws IOException, InterruptedException {
		if (Compare() == true) {
			log.info("Excel Sheet1 is working Fine");
		} else {
			log.info("Excel Sheet1 is NOT working Fine");
		}
	}

	public static Boolean Compare() throws IOException, InterruptedException {
		String Expected = System.getProperty("user.dir") + "\\Data\\" + "PipelineConfigReportExpected.xlsx";
		String Result = waitForTheExcelFileToDownload("Pipelineconfig_Report");
		Boolean flag = true;

		for (int i = 0; i < 11; i++) {
			String Expecteds = readRows(Expected, 1, i);
			String Results = readRows(Result, 1, i);

			String newExpect = Expecteds.replaceAll(" ", "");
			String newResult = Results.replaceAll(" ", "");

			String dates = null;

			if (i < 11) {
				String[] date = newResult.split("T");
				dates = date[0];
			}

			if (newExpect.equalsIgnoreCase(newResult) || newExpect.equalsIgnoreCase(dates)) {
				String Title = readRows(Expected, 0, i);
			} else {
				String Title = readRows(Expected, 0, i);
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

			if (file.getName().contains("IDP_report")) {
				log.info("File name is..." + file.getName());
				file.deleteOnExit();
				if (file.getName().contains("IDP_report")) {
					log.info("File is successfully deleted!");
				} else {
					log.info("Sorry, the file was not deleted.");
				}
			}

		}
	}

	public static String readRows(String path, int rowIndex, int value) throws IOException {
		String particlurRows = null;
		FileInputStream file = new FileInputStream(path);
		XSSFWorkbook wbook = new XSSFWorkbook(file);
		XSSFSheet sheet1 = wbook.getSheetAt(0);
		XSSFRow row = sheet1.getRow(rowIndex);
		DataFormatter df = new DataFormatter();
		for (int i = 1; i <= 13; i++) {
			Application = df.formatCellValue(row.getCell(value));
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
		DataFormatter df = new DataFormatter();
		for (int i = 1; i <= 4; i++) {
			Application = df.formatCellValue(row.getCell(value));
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
