package org.idp.reports;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.infy.commonHelpers.WorkerHelper;
import org.infy.init.ListenersTest;
import org.infy.reportHelper.ExecutionReportHelper;
import org.infy.reportHelper.ReportCompare;
import org.infy.reportHelper.ReportHelper;
import org.infy.uiPages.ApplicationView;
import org.infy.uiPages.CommonMethods;
import org.infy.uiPages.Parameters;
import org.infy.uiPages.PipelineView;
import org.infy.uiPages.Workflows;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

@Listeners(ListenersTest.class)
public class TC_ExecutionReport {

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();

	@Test(priority = 1)
	public void executionReportVerification() throws InterruptedException, FileNotFoundException, IOException,
			ParseException, HeadlessException, UnsupportedFlavorException, AWTException {

		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		ReportHelper rp = new ReportHelper(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ApplicationView av = new ApplicationView(driver);
		ExecutionReportHelper eh = new ExecutionReportHelper(driver);
		ReportCompare comp = new ReportCompare();
		ReportHelper reportH = PageFactory.initElements(driver, ReportHelper.class);
		eh.executionReportExpectedResultwriter();
		String appName = eh.excelRead(0);
		String applicationName = appName;
		String protAndApplicationName = eh.excelRead(9);
		String workerName1 = eh.excelRead(1);
		String workerName = workerName1.replace("pipeline", "");
		av.openApplicationDashboard(applicationName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String nameOftheApplication = workerName;
		String WorkerNamess = applicationName + "_" + nameOftheApplication;
		wh.workerAgentNameFinder();
		comp.FirstStepToDeleteExcel("Execution_Report");
		comp.FirstStepToDeleteExcel("IDP_report");
		wh.addWorker(nameOftheApplication);
		wh.downloadWorker();
		wh.agentRunning(protAndApplicationName);
		wh.workerPageRefresh();
		wh.instanceVerifycation("IdleInstance", WorkerNamess, "0", "1");
		eh.navigateToPipeline();
		eh.pipelineCreation();
		eh.triggerPipeline();
		Thread.sleep(5000);
		eh.approverVerification();
		eh.navigateToPipelineExeHistory();
		eh.reRun();
		Thread.sleep(5000);
		String ApproveDetails = eh.approverVerification();
		eh.navigateToPipelineExeHistory();
		String[] IDs = eh.buildAndReRunIdStore();
		String appFirstName = eh.excelRead(0);
		wh.ExcelRenames("Execution_Report.xlsx");
		eh.ExcelCompartions();
		eh.rebuildIDAndApproverComparsion(IDs, ApproveDetails);
		comp.delectExcel();
		comp.FirstStepToDeleteExcel("IDP_report");
		comp.FirstStepToDeleteExcel("Execution_Report");
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		av.openApplicationDashboard(applicationName);
		String agentName = wh.agentNames();
		String pipelineNameis = "pipeline" + nameOftheApplication;
		String foldername = applicationName + "_" + pipelineNameis;
		wh.finalSteps(agentName, WorkerNamess, foldername);
	}
}
