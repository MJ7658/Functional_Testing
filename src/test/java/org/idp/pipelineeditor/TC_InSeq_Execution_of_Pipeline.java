package org.idp.pipelineeditor;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.infy.commonHelpers.PipelineHelper;
import org.infy.commonHelpers.WorkerHelper;
import org.infy.init.ListenersTest;
import org.infy.init.LoadData;
import org.infy.reportHelper.ReportHelper;
import org.infy.uiPages.ApplicationView;
import org.infy.uiPages.CommonMethods;
import org.infy.uiPages.Parameters;
import org.infy.uiPages.PipelineView;
import org.infy.uiPages.Workflows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

@Listeners(ListenersTest.class)
public class TC_InSeq_Execution_of_Pipeline extends LoadData {

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();

	@Test(enabled = true, priority = 1)
	public void To_Verify_Approval_Notification_By_Using_inSequece_workflow() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		av.openApplicationDashboard(applicationName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		wh.workerAgentNameFinder();
		wh.addWorker(workerName);
		wh.downloadWorker();
		wh.agentRunning(WorkerNames);
		wh.workerPageRefresh();
		Thread.sleep(2000);
		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", WorkerNames);
		ph.createDynamicSteps("stage1");
		ph.addStageInPipeline("stage2", "QATestEnv", WorkerNames);
		ph.createDynamicSteps("stage2");
		ph.addStageInPipeline("stage3", "DEVTest", WorkerNames);
		ph.createDynamicSteps("stage3");
		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.ReleaseNameSelect("application:Release3");
		ph.workerSelectionInPipelineTrigger(WorkerNames);
		ph.triggerPipelineUsingButton();
		Thread.sleep(8000);
		ph.approverVerification("Approved");
		driver.navigate().refresh();
		Thread.sleep(3000);
		ph.navigateToPipeline();
		Thread.sleep(5000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		Thread.sleep(2000);
		av.openApplicationDashboard(applicationName);
		String agentName = wh.agentNames();
		String foldername = applicationName + "_" + pipelineName;
		wh.finalSteps(agentName, WorkerNames, foldername);
	}

	@Test(enabled = true, priority = 2)
	public void To_Verify_Rejectopn_Notification_By_Using_inSequece_workflow() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		av.openApplicationDashboard(applicationName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		wh.workerAgentNameFinder();
		wh.addWorker(workerName);
		wh.downloadWorker();
		wh.agentRunning(WorkerNames);
		wh.workerPageRefresh();
		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", WorkerNames);
		ph.createDynamicSteps("stage1");
		ph.addStageInPipeline("stage2", "QATestEnv", WorkerNames);
		ph.createDynamicSteps("stage2");
		ph.addStageInPipeline("stage3", "DEVTest", WorkerNames);
		ph.createDynamicSteps("stage3");
		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.ReleaseNameSelect("application:Release3");
		ph.workerSelectionInPipelineTrigger(WorkerNames);
		ph.triggerPipelineUsingButton();
		Thread.sleep(8000);
		ph.approverVerification("reject");
		driver.navigate().refresh();
		Thread.sleep(3000);
		ph.navigateToPipeline();
		Thread.sleep(5000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		String foldername = applicationName + "_" + pipelineName;
		ph.navLogsDetails(foldername);
		boolean values = ph.verifyRejectLog("reject");
		assertTrue(values);
		driver.navigate().refresh();
		av.openApplicationDashboard(applicationName);
		String agentName = wh.agentNames();
		wh.finalSteps(agentName, WorkerNames, foldername);
	}
}
