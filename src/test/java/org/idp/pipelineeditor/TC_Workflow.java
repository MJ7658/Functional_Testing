package org.idp.pipelineeditor;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
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
public class TC_Workflow extends LoadData {

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();
	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(TC_Workflow.class);

	@Test(enabled = true, priority = 1)
	public void Validate_Workflow_Stage_hierarchy_AND_Logs() throws Exception {
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
		ph.addStageInPipeline("stage1", "qa", WorkerNames);
		ph.createDynamicSteps("stage1");
		ph.addStageInPipeline("stage2", "dev", WorkerNames);
		ph.createDynamicSteps("stage2");
		ph.addStageInPipelineWithoutEnv("stage3", WorkerNames);
		ph.createDynamicSteps("stage3");
		ph.addStageInPipeline("stage4", "prod", WorkerNames);
		ph.createDynamicSteps("stage4");
		ph.submitPipeline();
		ph.navworkflow();
		List<String> workflowList = Arrays.asList("stage3", "stage2 Dev", "stage1 QA", "stage4 prod");
		Boolean result = ph.workflowVerifyCation(workflowList);
		assertTrue(result);
		ph.navConfiguration();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(WorkerNames);
		ph.triggerPipelineUsingButton();
		Thread.sleep(15000);
		ph.pipelineRunningVerifycation(pipelineName, WorkerNames);
		String foldername = applicationName + "_" + pipelineName;
		ph.navLogsDetails(foldername);
		String name1 = ph.logsVerifycationDetails("stage3");
		String name2 = ph.logsVerifycationDetails("stage2 (Dev)");
		String name3 = ph.logsVerifycationDetails("stage1 (QA)");
		String name4 = ph.logsVerifycationDetails("stage4 (prod)");
		List<String> workflowList1 = Arrays.asList(name1, name2, name3, name4);
		Boolean li = ph.verfiyMethods(workflowList, workflowList1);
		assertTrue(li);
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		av.openApplicationDashboard(applicationName);
		String agentName = wh.agentNames();
		wh.finalSteps(agentName, WorkerNames, "worker");
	}

	@Test(enabled = true, priority = 2)
	public void Validate_Workflow_Without_Assign_Envi_In_Stage_hierarchy_AND_Logs() throws Exception {
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
		ph.addStageInPipelineWithoutEnv("stage2", WorkerNames);
		ph.createDynamicSteps("stage2");
		ph.addStageInPipelineWithoutEnv("stage3", WorkerNames);
		ph.createDynamicSteps("stage3");
		ph.addStageInPipelineWithoutEnv("stage4", WorkerNames);
		ph.createDynamicSteps("stage4");
		ph.submitPipeline();
		ph.navworkflow();
		List<String> workflowList = Arrays.asList("stage1", "stage2", "stage3", "stage4");
		Boolean result = ph.workflowVerifyCation(workflowList);
		assertTrue(result);
		ph.navConfiguration();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(WorkerNames);
		ph.triggerPipelineUsingButton();
		Thread.sleep(8000);
		ph.pipelineRunningVerifycation(pipelineName, WorkerNames);
		String foldername = applicationName + "_" + pipelineName;
		ph.navLogsDetails(foldername);
		String name1 = ph.logsVerifycationDetails("stage1");
		String name2 = ph.logsVerifycationDetails("stage2");
		String name3 = ph.logsVerifycationDetails("stage3");
		String name4 = ph.logsVerifycationDetails("stage4");
		List<String> workflowList1 = Arrays.asList(name1, name2, name3, name4);
		Boolean li = ph.verfiyMethods(workflowList, workflowList1);
		assertTrue(li);
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		av.openApplicationDashboard(applicationName);
		String agentName = wh.agentNames();
		wh.finalSteps(agentName, WorkerNames, "worker");
	}

	@Test(enabled = true, priority = 3)
	public void Validate_Workflow__Stage_hierarchy_AND_DropdownDetails() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		av.openApplicationDashboard(applicationName);
		wh.workerAgentNameFinder();
		wh.addWorker(workerName);
		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipeline("stage1", "qa", WorkerNames);
		ph.createDynamicSteps("stage1");
		ph.addStageInPipeline("stage2", "dev", WorkerNames);
		ph.createDynamicSteps("stage2");
		ph.addStageInPipelineWithoutEnv("stage3", WorkerNames);
		ph.createDynamicSteps("stage3");
		ph.addStageInPipeline("stage4", "prod", WorkerNames);
		ph.createDynamicSteps("stage4");
		ph.submitPipeline();
		ph.navworkflow();
		List<String> workflowList = Arrays.asList("stage3", "stage2 Dev", "stage1 QA", "stage4 prod");
		Boolean result = ph.workflowVerifyCation(workflowList);
		assertTrue(result);
		ph.addworkFlow();
		ph.removeStageDetailsInWorkflow();
		ph.removeStageDetailsInWorkflow();
		ph.removeStageDetailsInWorkflow();
		ph.removeStageDetailsInWorkflow();

		ph.addParticularStage("stage2 - Dev");
		ph.addParticularStage("stage1 - QA");
		Boolean value = ph.stagePositionPOPUPVerifyTrue("stage2 - Dev");
		assertTrue(value);
		ph.addStagePopupe();
		ph.removeStageDetailsInWorkflow();
		ph.removeStageDetailsInWorkflow();
		ph.addParticularStage("stage1 - QA");
		ph.addParticularStage("stage4 - prod");
		Boolean value1 = ph.stagePositionPOPUPVerifyTrue("stage1 - QA");
		assertTrue(value1);
		ph.addStagePopupe();
		ph.removeStageDetailsInWorkflow();
		ph.removeStageDetailsInWorkflow();

		ph.addParticularStage("stage1 - QA");
		ph.addParticularStage("stage4 - prod");
		Boolean value3 = ph.stagePositionPOPUPVerifyFalse("stage2 - Dev");
		assertFalse(value3);
		ph.cancelButton();
		ph.removeStageDetailsInWorkflow();

		ph.addParticularStage("stage3");
		ph.addParticularStage("stage1 - QA");
		ph.addasStageRadioButton();
		ph.addStagePopupe();

		List<String> workflowNewList = Arrays.asList("stage1 QA", "stage3");
		Boolean result1 = ph.workflowVerifyCation(workflowNewList);
		assertTrue(result1);
		ph.removeStageDetailsInWorkflow();
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		av.openApplicationDashboard(applicationName);
		wh.deleteWorker(WorkerNames);
	}

	@Test(enabled = true, priority = 4)
	public void Validate_Workflow__Stage_Parallel_And_Joined_Pipline() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		av.openApplicationDashboard(applicationName);
		wh.workerAgentNameFinder();
		wh.addWorker(workerName);
		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", WorkerNames);
		ph.createDynamicSteps("stage1");
		ph.addStageInPipelineWithoutEnv("stage2", WorkerNames);
		ph.createDynamicSteps("stage2");
		ph.addStageInPipelineWithoutEnv("stage3", WorkerNames);
		ph.createDynamicSteps("stage3");
		ph.addStageInPipelineWithoutEnv("stage4", WorkerNames);
		ph.createDynamicSteps("stage4");
		ph.submitPipeline();
		ph.navworkflow();
		List<String> workflowList = Arrays.asList("stage1", "stage2", "stage3", "stage4");
		Boolean result = ph.workflowVerifyCation(workflowList);
		assertTrue(result);
		ph.addworkFlow();
		ph.removeStageDetailsInWorkflow();
		ph.removeStageDetailsInWorkflow();
		ph.removeStageDetailsInWorkflow();
		ph.removeStageDetailsInWorkflow();

		ph.addParticularStage("stage2");
		ph.addParticularStage("stage1");
		Boolean value = ph.stagePositionPOPUPVerifyTrue("stage2");
		assertTrue(value);
		ph.addSequenceRadioButton();
		ph.addStagePopupe();
		List<String> workflowList1 = Arrays.asList("stage2", "stage1");
		Boolean result1 = ph.workflowVerifyCation(workflowList1);
		assertTrue(result1);
		ph.removeStageDetailsInWorkflow();
		ph.removeStageDetailsInWorkflow();

		ph.addParticularStage("stage1");
		ph.addParticularStage("stage2");
		Boolean value1 = ph.stagePositionPOPUPVerifyTrue("stage1");
		assertTrue(value1);
		ph.addParallelRadioButton();
		ph.addStagePopupe();
		List<String> workflowList2 = Arrays.asList("stage1", "stage2", "Join");
		Boolean result2 = ph.workflowVerifyCation(workflowList2);
		assertTrue(result2);
		ph.removeStageDetailsInWorkflow();
		ph.removeStageDetailsInWorkflow();

		ph.addParticularStage("stage1");
		ph.addParticularStage("stage2");
		Boolean value5 = ph.stagePositionPOPUPVerifyTrue("stage1");
		assertTrue(value5);
		ph.addParallelRadioButton();
		ph.addStagePopupe();

		ph.addParticularStage("stage3");
		Boolean value2 = ph.stagePositionPOPUPVerifyTrueMultiple("Join Node - 1");
		assertTrue(value2);
		ph.addStagePopupe();
		List<String> workflowList3 = Arrays.asList("stage1", "stage2", "Join", "stage3");
		Boolean result3 = ph.workflowVerifyCation(workflowList3);
		assertTrue(result3);
		ph.removeStageDetailsInWorkflow();
		ph.removeStageDetailsInWorkflow();
		ph.removeStageDetailsInWorkflow();

		ph.addParticularStage("stage1");
		ph.addParticularStage("stage2");
		ph.addasStageRadioButton();
		ph.addStagePopupe();
		List<String> workflowList4 = Arrays.asList("stage2", "stage1");
		Boolean result4 = ph.workflowVerifyCation(workflowList4);
		assertTrue(result4);
		ph.removeStageDetailsInWorkflow();
		ph.removeStageDetailsInWorkflow();

		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		av.openApplicationDashboard(applicationName);
		wh.deleteWorker(WorkerNames);
	}

	@Test(enabled = true, priority = 5)
	public void Validate_Workflow__Stage_ZoomIn_ZoomOut() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		av.openApplicationDashboard(applicationName);
		wh.workerAgentNameFinder();
		wh.addWorker(workerName);
		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", WorkerNames);
		ph.createDynamicSteps("stage1");
		ph.addStageInPipelineWithoutEnv("stage2", WorkerNames);
		ph.createDynamicSteps("stage2");
		ph.submitPipeline();
		ph.navworkflow();
		List<String> workflowList = Arrays.asList("stage1", "stage2");
		Boolean result = ph.workflowVerifyCation(workflowList);
		assertTrue(result);
		ph.addworkFlow();
		ph.removeStageDetailsInWorkflow();
		ph.removeStageDetailsInWorkflow();
		ph.addParticularStage("stage1");
		int height = ph.zoomINandZoomOut(-85);
		log.info(height + "its a 47");
		System.out.println("");
		boolean flag;
		if (height == 47) {
			flag = true;
		} else {
			flag = false;
		}
		assertTrue(flag);
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		av.openApplicationDashboard(applicationName);
		wh.deleteWorker(WorkerNames);
	}
}
