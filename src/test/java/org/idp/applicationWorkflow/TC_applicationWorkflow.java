package org.idp.applicationWorkflow;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

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
public class TC_applicationWorkflow extends LoadData {
	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();
	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(TC_applicationWorkflow.class);

	@Test(enabled = true, priority = 1)
	public void To_validate_ApplicationWorkflow_Create_Delete() throws Exception {
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
		String pipelineName1 = "pip" + workerName;
		String pipelineName2 = "pipTest" + workerName;

		String workerflowpipelineName = applicationName + "_" + "pipeline" + workerName;
		String workerflowpipelineName1 = applicationName + "_" + "pip" + workerName;
		String workerflowpipelineName2 = applicationName + "_" + "pipTest" + workerName;

		String BuildMachineWorker = "WorkerAutomation_BuildmachineWorker";
		String AgTestWorker = "AgTest";
		String workFlowName = "Test" + workerName;

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipeline("stage1", "qa", BuildMachineWorker);
		ph.createDynamicSteps("stage1");
		ph.addStageInPipeline("stage2", "Dev", BuildMachineWorker);
		ph.createDynamicSteps("stage2");
		ph.submitPipeline();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName1);
		Thread.sleep(2000);
		ph.addStageInPipeline("stage1", "qa", BuildMachineWorker);
		ph.createDynamicSteps("stage1");
		ph.addStageInPipeline("stage2", "Dev", BuildMachineWorker);
		ph.createDynamicSteps("stage2");
		ph.submitPipeline();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName2);
		Thread.sleep(2000);
		ph.addStageInPipeline("stage1", "qa", BuildMachineWorker);
		ph.createDynamicSteps("stage1");
		ph.addStageInPipeline("stage2", "Dev", BuildMachineWorker);
		ph.createDynamicSteps("stage2");
		ph.submitPipeline();

		av.openApplicationDashboard(applicationName);
		av.navToWorkflow();
		ph.clickOnCreateWorkflowButton();
		ph.clickOnAddStage();
		ph.dyanamicworkflowStepAndPlugin("Stage 1");
		av.searchAndAddWorkflow(workerflowpipelineName, AgTestWorker);
		ph.dyanamicworkflowStepAndPlugin("Stage 1");
		av.searchAndAddWorkflow(workerflowpipelineName1, AgTestWorker);

		ph.clickOnAddStage();
		ph.dyanamicworkflowStepAndPlugin("Stage 2");
		av.searchAndAddWorkflow(workerflowpipelineName2, AgTestWorker);
		av.addWorkFlowName(workFlowName);
		boolean value = av.verifyWorkflowName(workFlowName);
		assertTrue(value);
		av.deleteWorkflow(workFlowName);
	}

	@Test(enabled = true, priority = 2)
	public void To_validate_ApplicationWorkflow_Update_View() throws Exception {
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
		String pipelineName1 = "pip" + workerName;
		String pipelineName2 = "pipTest" + workerName;
		String pipelineName3 = "pipTestadd" + workerName;

		String workerflowpipelineName = applicationName + "_" + "pipeline" + workerName;
		String workerflowpipelineName1 = applicationName + "_" + "pip" + workerName;
		String workerflowpipelineName2 = applicationName + "_" + "pipTest" + workerName;
		String workerflowpipelineName3 = applicationName + "_" + "pipTestadd" + workerName;

		String BuildMachineWorker = "WorkerAutomation_BuildmachineWorker";
		String AgTestWorker = "AgTest";
		String workFlowName = "Test" + workerName;

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipeline("stage1", "qa", BuildMachineWorker);
		ph.createDynamicSteps("stage1");
		ph.addStageInPipeline("stage2", "Dev", BuildMachineWorker);
		ph.createDynamicSteps("stage2");
		ph.submitPipeline();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName1);
		Thread.sleep(2000);
		ph.addStageInPipeline("stage1", "qa", BuildMachineWorker);
		ph.createDynamicSteps("stage1");
		ph.addStageInPipeline("stage2", "Dev", BuildMachineWorker);
		ph.createDynamicSteps("stage2");
		ph.submitPipeline();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName2);
		Thread.sleep(2000);
		ph.addStageInPipeline("stage1", "qa", BuildMachineWorker);
		ph.createDynamicSteps("stage1");
		ph.addStageInPipeline("stage2", "Dev", BuildMachineWorker);
		ph.createDynamicSteps("stage2");
		ph.submitPipeline();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName3);
		Thread.sleep(2000);
		ph.addStageInPipeline("stage1", "qa", BuildMachineWorker);
		ph.createDynamicSteps("stage1");
		ph.addStageInPipeline("stage2", "Dev", BuildMachineWorker);
		ph.createDynamicSteps("stage2");
		ph.submitPipeline();

		av.openApplicationDashboard(applicationName);
		av.navToWorkflow();
		ph.clickOnCreateWorkflowButton();
		ph.clickOnAddStage();
		ph.dyanamicworkflowStepAndPlugin("Stage 1");
		av.searchAndAddWorkflow(workerflowpipelineName, AgTestWorker);
		ph.dyanamicworkflowStepAndPlugin("Stage 1");
		av.searchAndAddWorkflow(workerflowpipelineName1, AgTestWorker);
		log.info("line number ===> 194");

		ph.clickOnAddStage();
		log.info("line number ===> 196");
		ph.dyanamicworkflowStepAndPlugin("Stage 2");
		av.searchAndAddWorkflow(workerflowpipelineName2, AgTestWorker);
		log.info("line number ===> 198");
		av.addWorkFlowName(workFlowName);
		log.info("line number ===> 200");
		boolean value = av.verifyWorkflowName(workFlowName);
		assertTrue(value);
		log.info("line number ===> 202");
		av.editWorkflowSteps(workFlowName, workerflowpipelineName1, "Stage 2", workerflowpipelineName3, AgTestWorker);
		log.info("line number ===> 205");
		boolean value1 = av.viewWorkflowSteps(workFlowName, workerflowpipelineName1);
		assertTrue(value1);
		log.info("line number ===> 210");
		boolean value2 = av.viewWorkflowSteps(workFlowName, workerflowpipelineName3);
		assertFalse(value2);

		av.deleteWorkflow(workFlowName);
	}

	@Test(enabled = true, priority = 3)
	public void To_validate_ApplicationWorkflow_Checking_Stage_Worker() throws Exception {
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
		String pipelineName1 = "pip" + workerName;
		String pipelineName2 = "pipTest" + workerName;
		String pipelineName3 = "pipTestadd" + workerName;

		String workerflowpipelineName = applicationName + "_" + "pipeline" + workerName;
		String workerflowpipelineName1 = applicationName + "_" + "pip" + workerName;
		String workerflowpipelineName2 = applicationName + "_" + "pipTest" + workerName;
		String workerflowpipelineName3 = applicationName + "_" + "pipTestadd" + workerName;

		String AgTestWorker = "AgTest";
		String AgTestWorker480 = "Agtest480VM";
		String workFlowName = "Test" + workerName;

		String BuildMachineWorker = "WorkerAutomation_BuildmachineWorker";
		String AGTestWorker = applicationName + "_" + AgTestWorker;
		String AGTest480VMWorker = applicationName + "_" + AgTestWorker480;

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipeline("stage1", "qa", BuildMachineWorker);
		ph.createDynamicSteps("stage1");
		ph.addStageInPipeline("stage2", "Dev", BuildMachineWorker);
		ph.createDynamicSteps("stage2");
		ph.submitPipeline();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName1);
		Thread.sleep(2000);
		ph.addStageInPipeline("stage1", "qa", BuildMachineWorker);
		ph.createDynamicSteps("stage1");
		ph.addStageInPipeline("stage2", "Dev", BuildMachineWorker);
		ph.createDynamicSteps("stage2");
		ph.submitPipeline();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName2);
		Thread.sleep(2000);
		ph.addStageInPipeline("stage1", "qa", BuildMachineWorker);
		ph.createDynamicSteps("stage1");
		ph.addStageInPipeline("stage2", "Dev", BuildMachineWorker);
		ph.createDynamicSteps("stage2");
		ph.submitPipeline();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName3);
		Thread.sleep(2000);
		ph.addStageInPipeline("stage1", "qa", BuildMachineWorker);
		ph.createDynamicSteps("stage1");
		ph.addStageInPipeline("stage2", "Dev", BuildMachineWorker);
		ph.createDynamicSteps("stage2");
		ph.submitPipeline();

		av.openApplicationDashboard(applicationName);
		av.navToWorkflow();
		ph.clickOnCreateWorkflowButton();
		ph.clickOnAddStage();
		ph.dyanamicworkflowStepAndPlugin("Stage 1");
		av.searchAndAddWorkflow(workerflowpipelineName, AgTestWorker);
		ph.dyanamicworkflowStepAndPlugin("Stage 1");
		av.searchAndAddWorkflow(workerflowpipelineName1, AgTestWorker);

		ph.clickOnAddStage();
		ph.dyanamicworkflowStepAndPlugin("Stage 2");
		av.searchAndAddWorkflow(workerflowpipelineName2, AgTestWorker);
		av.addWorkFlowName(workFlowName);
		boolean value = av.verifyWorkflowName(workFlowName);
		assertTrue(value);

		av.triggerWorkflow(workFlowName);
		ph.workerSelectionInWorkflowTrigger(BuildMachineWorker);
		ph.triggerPipelineUsingButton();
		boolean value3 = wh.workflowTriggererifycation(workFlowName, BuildMachineWorker);
		assertTrue(value3);
		av.backToeWorkflow();

		av.triggerWorkflow(workFlowName);
		ph.workerSelectionInWorkflowTrigger(AGTestWorker);
		ph.triggerPipelineUsingButton();
		boolean value4 = wh.workflowTriggererifycation(workFlowName, BuildMachineWorker);
		assertTrue(value4);
		av.backToeWorkflow();

		av.triggerWorkflow(workFlowName);
		ph.workerSelectionInWorkflowTrigger(AGTest480VMWorker);
		ph.triggerPipelineUsingButton();
		boolean value5 = wh.workflowTriggererifycation(workFlowName, BuildMachineWorker);
		assertTrue(value5);
		av.backToeWorkflow();

		av.deleteWorkflow(workFlowName);
	}

	@Test(enabled = true, priority = 4)
	public void To_validate_ApplicationWorkflow_Checking_Workflow_Worker() throws Exception {
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
		String pipelineName1 = "pip" + workerName;
		String pipelineName2 = "pipTest" + workerName;
		String pipelineName3 = "pipTestadd" + workerName;

		String workerflowpipelineName = applicationName + "_" + "pipeline" + workerName;
		String workerflowpipelineName1 = applicationName + "_" + "pip" + workerName;
		String workerflowpipelineName2 = applicationName + "_" + "pipTest" + workerName;
		String workerflowpipelineName3 = applicationName + "_" + "pipTestadd" + workerName;

		String AgTestWorker = "AgTest";
		String AgTestWorker480 = "Agtest480VM";
		String workFlowName = "Test" + workerName;

		String BuildMachineWorker = "WorkerAutomation_BuildmachineWorker";
		String AGTestWorker = applicationName + "_" + AgTestWorker;
		String AGTest480VMWorker = applicationName + "_" + AgTestWorker480;

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipeline1("stage1", "qa");
		ph.createDynamicSteps("stage1");
		ph.addStageInPipeline1("stage2", "Dev");
		ph.createDynamicSteps("stage2");
		ph.submitPipeline();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName1);
		Thread.sleep(2000);
		ph.addStageInPipeline1("stage1", "qa");
		ph.createDynamicSteps("stage1");
		ph.addStageInPipeline1("stage2", "Dev");
		ph.createDynamicSteps("stage2");
		ph.submitPipeline();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName2);
		Thread.sleep(2000);
		ph.addStageInPipeline1("stage1", "qa");
		ph.createDynamicSteps("stage1");
		ph.addStageInPipeline1("stage2", "Dev");
		ph.createDynamicSteps("stage2");
		ph.submitPipeline();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName3);
		Thread.sleep(2000);
		ph.addStageInPipeline1("stage1", "qa");
		ph.createDynamicSteps("stage1");
		ph.addStageInPipeline1("stage2", "Dev");
		ph.createDynamicSteps("stage2");
		ph.submitPipeline();

		av.openApplicationDashboard(applicationName);
		av.navToWorkflow();
		ph.clickOnCreateWorkflowButton();
		ph.clickOnAddStage();
		ph.dyanamicworkflowStepAndPlugin("Stage 1");
		av.searchAndAddWorkflow(workerflowpipelineName, AgTestWorker);
		ph.dyanamicworkflowStepAndPlugin("Stage 1");
		av.searchAndAddWorkflow(workerflowpipelineName1, AgTestWorker);
		Thread.sleep(2000);
		ph.clickOnAddStage();
		ph.dyanamicworkflowStepAndPlugin("Stage 2");
		av.searchAndAddWorkflow(workerflowpipelineName2, AgTestWorker);
		av.addWorkFlowName(workFlowName);
		boolean value = av.verifyWorkflowName(workFlowName);
		assertTrue(value);
		Thread.sleep(2000);
		av.triggerWorkflow(workFlowName);
		ph.workerSelectionInWorkflowTrigger(AGTestWorker);
		ph.triggerPipelineUsingButton();
		boolean value4 = wh.workflowTriggererifycation(workFlowName, AGTestWorker);
		assertTrue(value4);
		av.backToeWorkflow();
		Thread.sleep(2000);
		av.triggerWorkflow(workFlowName);
		ph.workerSelectionInWorkflowTrigger(AGTest480VMWorker);
		ph.triggerPipelineUsingButton();
		boolean value5 = wh.workflowTriggererifycation(workFlowName, AGTestWorker);
		assertTrue(value5);
		av.backToeWorkflow();
		av.deleteWorkflow(workFlowName);
	}

	@Test(enabled = true, priority = 5)
	public void To_validate_ApplicationWorkflow_Checking_NormalWorker() throws Exception {
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
		String pipelineName1 = "pip" + workerName;
		String pipelineName2 = "pipTest" + workerName;
		String pipelineName3 = "pipTestadd" + workerName;

		String workerflowpipelineName = applicationName + "_" + "pipeline" + workerName;
		String workerflowpipelineName1 = applicationName + "_" + "pip" + workerName;
		String workerflowpipelineName2 = applicationName + "_" + "pipTest" + workerName;
		String workerflowpipelineName3 = applicationName + "_" + "pipTestadd" + workerName;

		String AgTestWorker = "AgTest";
		String AgTestWorker480 = "Agtest480VM";
		String workFlowName = "Test" + workerName;

		String BuildMachineWorker = "WorkerAutomation_BuildmachineWorker";
		String AGTestWorker = applicationName + "_" + AgTestWorker;
		String AGTest480VMWorker = applicationName + "_" + AgTestWorker480;
		String None = "None";

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipeline1("stage1", "qa");
		ph.createDynamicSteps("stage1");
		ph.addStageInPipeline1("stage2", "Dev");
		ph.createDynamicSteps("stage2");
		ph.submitPipeline();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName1);
		Thread.sleep(2000);
		ph.addStageInPipeline1("stage1", "qa");
		ph.createDynamicSteps("stage1");
		ph.addStageInPipeline1("stage2", "Dev");
		ph.createDynamicSteps("stage2");
		ph.submitPipeline();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName2);
		Thread.sleep(2000);
		ph.addStageInPipeline1("stage1", "qa");
		ph.createDynamicSteps("stage1");
		ph.addStageInPipeline1("stage2", "Dev");
		ph.createDynamicSteps("stage2");
		ph.submitPipeline();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName3);
		Thread.sleep(2000);
		ph.addStageInPipeline1("stage1", "qa");
		ph.createDynamicSteps("stage1");
		ph.addStageInPipeline1("stage2", "Dev");
		ph.createDynamicSteps("stage2");
		ph.submitPipeline();

		av.openApplicationDashboard(applicationName);
		av.navToWorkflow();
		ph.clickOnCreateWorkflowButton();
		ph.clickOnAddStage();
		ph.dyanamicworkflowStepAndPlugin("Stage 1");
		av.searchAndAddWorkflowWithoutWorker(workerflowpipelineName);
		ph.dyanamicworkflowStepAndPlugin("Stage 1");
		av.searchAndAddWorkflowWithoutWorker(workerflowpipelineName1);

		ph.clickOnAddStage();
		ph.dyanamicworkflowStepAndPlugin("Stage 2");
		av.searchAndAddWorkflowWithoutWorker(workerflowpipelineName2);
		av.addWorkFlowName(workFlowName);
		boolean value = av.verifyWorkflowName(workFlowName);
		assertTrue(value);

		av.triggerWorkflow(workFlowName);
		ph.workerSelectionInWorkflowTrigger(AGTest480VMWorker);
		ph.triggerPipelineUsingButton();
		boolean value4 = wh.workflowTriggererifycation(workFlowName, AGTest480VMWorker);
		assertTrue(value4);
		av.backToeWorkflow();

		av.deleteWorkflow(workFlowName);
	}

	@Test(enabled = true, priority = 6)
	public void To_validate_ApplicationWorkflow_Multichoice_Checking() throws Exception {
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
		String pipelineName1 = "pip" + workerName;
		String pipelineName2 = "pipTest" + workerName;
		String pipelineName3 = "pipTestadd" + workerName;
		String pipelineName4 = "pipNew" + workerName;
		String pipelineName5 = "pipOld" + workerName;
		String pipelineName6 = "pipMe" + workerName;

		String workerflowpipelineName = applicationName + "_" + pipelineName;
		String workerflowpipelineName1 = applicationName + "_" + pipelineName1;
		String workerflowpipelineName2 = applicationName + "_" + pipelineName2;
		String workerflowpipelineName3 = applicationName + "_" + pipelineName3;
		String workerflowpipelineName4 = applicationName + "_" + pipelineName4;
		String workerflowpipelineName5 = applicationName + "_" + pipelineName5;
		String workerflowpipelineName6 = applicationName + "_" + pipelineName6;

		String AgTestWorker = "AgTest";
		String AgTestWorker480 = "Agtest480VM";
		String workFlowName = "Test" + workerName;

		String BuildMachineWorker = "WorkerAutomation_BuildmachineWorker";
		String AGTestWorker = applicationName + "_" + AgTestWorker;
		String AGTest480VMWorker = applicationName + "_" + AgTestWorker480;
		String None = "None";

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipeline1("stage1", "qa");
		ph.createDynamicSteps("stage1");
		ph.addStageInPipeline1("stage2", "Dev");
		ph.createDynamicSteps("stage2");
		ph.submitPipeline();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName1);
		Thread.sleep(2000);
		ph.addStageInPipeline1("stage1", "qa");
		ph.createDynamicSteps("stage1");
		ph.addStageInPipeline1("stage2", "Dev");
		ph.createDynamicSteps("stage2");
		ph.submitPipeline();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName2);
		Thread.sleep(2000);
		ph.addStageInPipeline1("stage1", "qa");
		ph.createDynamicSteps("stage1");
		ph.addStageInPipeline1("stage2", "Dev");
		ph.createDynamicSteps("stage2");
		ph.submitPipeline();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName3);
		Thread.sleep(2000);
		ph.addStageInPipeline1("stage1", "qa");
		ph.createDynamicSteps("stage1");
		ph.addStageInPipeline1("stage2", "Dev");
		ph.createDynamicSteps("stage2");
		ph.submitPipeline();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName4);
		Thread.sleep(2000);
		ph.addStageInPipeline1("stage1", "qa");
		ph.createDynamicSteps("stage1");
		ph.addStageInPipeline1("stage2", "Dev");
		ph.createDynamicSteps("stage2");
		ph.submitPipeline();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName5);
		Thread.sleep(2000);
		ph.addStageInPipeline1("stage1", "qa");
		ph.createDynamicSteps("stage1");
		ph.addStageInPipeline1("stage2", "Dev");
		ph.createDynamicSteps("stage2");
		ph.submitPipeline();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName6);
		Thread.sleep(2000);
		ph.addStageInPipeline1("stage1", "qa");
		ph.createDynamicSteps("stage1");
		ph.addStageInPipeline1("stage2", "Dev");
		ph.createDynamicSteps("stage2");
		ph.submitPipeline();

		av.openApplicationDashboard(applicationName);
		av.navToWorkflow();
		ph.clickOnCreateWorkflowButton();
		ph.clickOnAddStage();
		ph.dyanamicworkflowStepAndPlugin("Stage 1");
		av.searchAndAddWorkflowWithoutWorker(workerflowpipelineName);
		ph.dyanamicworkflowStepAndPlugin("Stage 1");
		av.searchAndAddWorkflowWithoutWorker(workerflowpipelineName1);
		ph.dyanamicworkflowStepAndPlugin("Stage 1");
		av.searchAndAddWorkflowWithoutWorker(workerflowpipelineName2);
		ph.dyanamicworkflowStepAndPlugin("Stage 1");
		av.searchAndAddWorkflowWithoutWorker(workerflowpipelineName3);
		ph.dyanamicworkflowStepAndPlugin("Stage 1");
		av.searchAndAddWorkflowWithoutWorker(workerflowpipelineName4);

		ph.clickOnAddStage();
		ph.dyanamicworkflowStepAndPlugin("Stage 2");
		av.searchAndAddWorkflowWithoutWorker(workerflowpipelineName5);
		ph.dyanamicworkflowStepAndPlugin("Stage 2");
		av.searchAndAddWorkflowWithoutWorker(workerflowpipelineName6);
		av.addWorkFlowName(workFlowName);
		boolean value = av.verifyWorkflowName(workFlowName);
		assertTrue(value);

		av.triggerWorkflow(workFlowName);
		ph.workerSelectionInWorkflowTrigger(AGTest480VMWorker);
		ph.triggerPipelineUsingButton();
		boolean value4 = wh.workflowTriggererifycation(workFlowName, AGTest480VMWorker);
		assertTrue(value4);
		av.backToeWorkflow();

		av.deleteWorkflow(workFlowName);

	}
}