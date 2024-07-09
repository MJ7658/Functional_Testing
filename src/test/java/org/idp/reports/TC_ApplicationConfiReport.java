package org.idp.reports;

import java.awt.HeadlessException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.infy.init.ExcelReader;
import org.infy.init.ExcelWriter;
import org.infy.init.ListenersTest;
import org.infy.reportHelper.ReportCompare;
import org.infy.reportHelper.ReportHelper;
import org.infy.uiPages.ApplicationView;
import org.infy.uiPages.CommonMethods;
import org.infy.uiPages.Parameters;
import org.infy.uiPages.PipelineView;
import org.infy.uiPages.Workflows;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

@Listeners(ListenersTest.class)
public class TC_ApplicationConfiReport {

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();

	@Test(enabled = true, priority = 1)
	public void validate_ApplicationConfiReport() throws InterruptedException, FileNotFoundException, IOException,
			ParseException, HeadlessException, UnsupportedFlavorException {

		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ReportHelper rp = new ReportHelper(driver);
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		ExcelWriter write = new ExcelWriter();
		ExcelReader read = new ExcelReader();
		ReportCompare comp = new ReportCompare();
		String appName = read.excelRead(9);
		String newValue = appName.replace(";", "");
		String[] appNames = newValue.split("_");
		String appFirstName = appNames[0];
		String appLastName = appNames[1];
		String pipelineNameis = "pipeline" + appLastName;
		write.ExcelWriter();
		comp.delectExcel();
		rp.applicationNav();
		rp.policyCreation("QATest");
		rp.environmentCreation();
		rp.variableCreation();
		rp.releaseCreation();
		rp.workerCreation();
		rp.pipelineCreation();
		rp.workfloCcreation();
		String applicationNames = read.excelRead(0);
		rp.openReportPage("Application Config Report", applicationNames, pipelineNameis);
		comp.comparitionSheet1Result();
		comp.comparitionSheet2Result();
	}
}
