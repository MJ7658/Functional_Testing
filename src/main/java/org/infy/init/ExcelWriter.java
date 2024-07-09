package org.infy.init;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.infy.reportHelper.Policys;

public class ExcelWriter {
	static String applicationName = null;

	static String userNames = null;

	public static void main(String[] args) throws IOException, InterruptedException {
		ExcelWriter();
	}

	public static String currentDatePickers1() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddHHmms");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		return date;
	}

	public static void ExcelWriter() throws IOException, InterruptedException {

		String path = System.getProperty("user.dir") + "//Data//Expected.xlsx";
		Workbook wb = new XSSFWorkbook();
		FileOutputStream fs = new FileOutputStream(path);
		Sheet sheet1 = wb.createSheet("sheet1");
		Sheet sheet2 = wb.createSheet("sheet2");

		String[] data = { "Application", "Portfolio", "Application Owner", "Pipeline Administrator", "Release Manager",
				"Developer", "Environment List", "Variables", "Release (GMT)", "Worker", "Tools", "Workflow",
				"Creation Date" };

		Random ra = new Random();
		String n = currentDatePickers1();

		applicationName = "NewTest" + n;
		String worker = applicationName + "_" + "TestWorker" + n + ";";
		String workFlow = "ReportWorkflow" + n + ";";

		userNames = "satheshkumar.m@infosys.com";

		String[] data1 = { applicationName, "Automation_Functional_test", userNames, userNames, userNames, userNames,
				"  QATest:QA, ", "UserName:default=123456,", " NewOne:Not Archived;", worker, "", workFlow,
				"2022-08-09" };

		Row thirdrow = sheet1.createRow(0);
		for (int i = 0; i < data.length; i++) {
			thirdrow.createCell(i).setCellValue(data[i]);
		}
		Row thirdrow1 = sheet1.createRow(1);
		for (int i = 0; i < data1.length; i++) {
			thirdrow1.createCell(i).setCellValue(data1[i]);
		}

		String[] data2 = { "Application", "Portfolio", "Policy", "Permissions", "Users" };
		Policys po = new Policys();
		String policyDetails = po.policySelect1();

		String[] data3 = { applicationName, "Automation_Functional_test", "QAtest", policyDetails, userNames };
		Row thirdrow2 = sheet2.createRow(0);
		for (int i = 0; i < data2.length; i++) {
			thirdrow2.createCell(i).setCellValue(data2[i]);
		}
		Row thirdrow3 = sheet2.createRow(1);
		for (int i = 0; i < data3.length; i++) {

			thirdrow3.createCell(i).setCellValue(data3[i]);
		}
		wb.write(fs);
		wb.close();
		fs.close();

	}
}
