package org.idp.reports;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.infy.commonHelpers.PipelineHelper;
import org.infy.commonHelpers.WorkerHelper;
import org.infy.init.ListenersTest;
import org.infy.init.LoadData;
import org.infy.reportHelper.ExecutionReportHelper;
import org.infy.reportHelper.ReportCompare;
import org.infy.reportHelper.ReportHelper;
import org.infy.uiPages.ApplicationView;
import org.infy.uiPages.CommonMethods;
import org.infy.uiPages.Parameters;
import org.infy.uiPages.PipelineView;
import org.infy.uiPages.Workflows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

@Listeners(ListenersTest.class)
public class TC_WorkFlow_Complieance_Execution_Report extends LoadData {

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();

	@Test(enabled = true, priority = 1)
	public void Validate_WorkFlow_Complieance_Execution_Report() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		ReportCompare comp = new ReportCompare();
		ExecutionReportHelper eh = new ExecutionReportHelper(driver);
		ReportHelper reportH = PageFactory.initElements(driver, ReportHelper.class);
		rp.workflowComplieanceExpectedResultwriter();
		comp.FirstStepToDeleteExcel("IDPReport");
		comp.FirstStepToDeleteExcel("IDP_report");
		String applicationName = rp.workflowExcelRead(1);
		String pipelineName = rp.workflowExcelRead(2);
		av.openApplicationDashboard(applicationName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String WorkerNames = rp.workflowExcelRead(7);
		String[] worker = WorkerNames.split("_");
		String workerName = worker[1];
		wh.workerAgentNameFinder();
		wh.addWorker(workerName);
		wh.downloadWorker();
		wh.agentRunning(WorkerNames);
		wh.workerPageRefresh();
		boolean value = wh.instanceVerifycation("IdleInstance", WorkerNames, "0", "1");
		assertTrue(value);
		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addPiplineTemplate();
		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(WorkerNames);
		ph.triggerPipelineUsingButton();
		ph.pipelineTriggerVerificaton();
		ph.pipelineRunningVerifycation(pipelineName, WorkerNames);
		Thread.sleep(2000);
		ph.navigateToPipelineExeHistory(pipelineName);
		ph.reRun();
		Thread.sleep(3000);
		ph.navigateToPipelineExeHistory(pipelineName);
		String[] buildAndRerunID = ph.buildAndReRunIdStore();

		String ExcbuildID = rp.workflowExcelReadwithRow(9, 1);
		String ExcreRunID = rp.workflowExcelReadwithRow(10, 1);
		String ExcbuildID1 = rp.workflowExcelReadwithRow(9, 2);

		String[] buildIDs = ExcbuildID.split("-" + pipelineName + "-");
		String[] reBuildIDs = ExcreRunID.split("-" + pipelineName + "-");
		String[] buildIDs1 = ExcbuildID1.split("-" + pipelineName + "-");

		boolean flag = true;
		if (buildIDs[1].equalsIgnoreCase(buildAndRerunID[0]) && reBuildIDs[1].equalsIgnoreCase(buildAndRerunID[1])
				&& buildIDs1[1].equalsIgnoreCase(buildAndRerunID[1])) {
			flag = true;
		} else {
			flag = false;
		}
		assertTrue(flag);

		wh.ExcelRenames("IDPReport.xlsx");
		boolean result = rp.excelCompa();
		assertTrue(result);
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		av.openApplicationDashboard(applicationName);
		String agentName = wh.agentNames();
		String foldername = applicationName + "_" + pipelineName;
		wh.finalSteps(agentName, WorkerNames, foldername);
		comp.FirstStepToDeleteExcel("IDPReport");
		comp.FirstStepToDeleteExcel("IDP_report");
	}

}
