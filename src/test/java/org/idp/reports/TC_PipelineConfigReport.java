package org.idp.reports;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.infy.commonHelpers.PipelineHelper;
import org.infy.commonHelpers.WorkerHelper;
import org.infy.init.ListenersTest;
import org.infy.reportHelper.ExecutionReportHelper;
import org.infy.reportHelper.PipelineConfigResultHelper;
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
public class TC_PipelineConfigReport {

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();

	@Test(priority = 1)
	public void pipelineConfigReportVerification() throws InterruptedException, FileNotFoundException, IOException,
			ParseException, HeadlessException, UnsupportedFlavorException, AWTException {

		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		ReportHelper rp = new ReportHelper(driver);
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		ReportCompare comp = new ReportCompare();
		ExecutionReportHelper eh = new ExecutionReportHelper(driver);
		PipelineConfigResultHelper prh = new PipelineConfigResultHelper(driver);
		ReportHelper reportH = PageFactory.initElements(driver, ReportHelper.class);
		prh.executionReportExpectedResultwriter();
		comp.FirstStepToDeleteExcel("Pipelineconfig_Report");
		comp.FirstStepToDeleteExcel("IDP_report");
		String applicationName = prh.excelRead(1);
		av.openApplicationDashboard(applicationName);
		wh.workerAgentNameFinder();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = prh.excelRead(5);
		String Worker = workerName.replace("stage1:" + applicationName + "_", "");
		String WorkerNames = applicationName + "_" + Worker;
		String pipelineName = prh.excelRead(0);
		wh.workerAgentNameFinder();
		wh.addWorker(Worker);
		wh.downloadWorker();
		wh.agentRunning(WorkerNames);
		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipeline("stage1", "qa", WorkerNames);
		ph.createStepeForTimeout();
		ph.submitPipeline();
		String parameters = prh.excelRead(6);
		String[] parameter = parameters.split(":");
		String variablenName = parameter[0];
		String defaultName = parameter[1];
		String defaults = defaultName.replace(";", "");
		ph.createParameter(variablenName, defaults);
		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		wh.ExcelRenames("Pipelineconfig_Report.xlsx");
		prh.comparitionSheet1Result();
		String agentName = wh.agentNames();
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		av.openApplicationDashboard(applicationName);
		String foldername = applicationName + "_" + pipelineName;
		wh.finalSteps(agentName, WorkerNames, foldername);
		comp.FirstStepToDeleteExcel("Pipelineconfig_Report");
		comp.FirstStepToDeleteExcel("IDP_report");
	}
}
