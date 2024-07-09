package org.idp.pipelineeditor;

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
public class TC_Pipeline_Parameters extends LoadData {

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();
	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(TC_Pipeline_Parameters.class);

	@Test(enabled = true, priority = 1)
	public void Validate_Pipeline_parameter_SecureRadioButton_Encryption() throws Exception {
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
		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipeline("stage1", "qa", WorkerNames);
		ph.createDynamicSteps("stage1");
		ph.submitPipeline();
		ph.navToParametersAndClickONAdd();

		String variableName = "Username1";
		String Defaultname = "Test";
		String Evnname = "QA";
		String EnvValue = "1234";

		ph.paramterVariableNameCreation(variableName);
		ph.paramterDefaultNameCreation(Defaultname);
		ph.parameterSaveButtonClick();
		boolean value = ph.verifyAddedParameters(variableName, Defaultname);
		assertTrue(value);
		ph.editParameter(variableName);
		Boolean withoutSecure = ph.parameterEditpopupDefaultVerfy();
		assertFalse(withoutSecure);
		ph.parameterSaveButtonClick();
		ph.deleteParameter(variableName);

		ph.navToParametersAndClickONAdd();
		ph.paramterVariableNameCreation(variableName);
		ph.paramterDefaultNameCreation(Defaultname);
		ph.clickOnSecureButton();
		ph.parameterSaveButtonClick();
		boolean value1 = ph.verifyAddedParameters(variableName, Defaultname);
		assertFalse(value1);
		ph.editParameter(variableName);
		Boolean withSecure = ph.parameterEditpopupDefaultVerfy();
		assertTrue(withSecure);
		ph.parameterSaveButtonClick();
		ph.deleteParameter(variableName);
		ph.navToParametersAndClickONAdd();
		ph.paramterVariableNameCreation(variableName);
		ph.paramterDefaultNameCreation(Defaultname);
		ph.parameterEnvCreation(Evnname, EnvValue);
	ph.clickOnSecureButton();
		ph.parameterSaveButtonClick();

		boolean enverify = ph.verifyAddedParameters(variableName, Defaultname);
		assertFalse(enverify);
		ph.editParameter(variableName);

		Boolean withSecure1 = ph.parameterEditpopupDefaultVerfy();
		assertTrue(withSecure1);
		Boolean withSecure2 = ph.parameterEditpopupEnveVerfy();
		assertTrue(withSecure2);
		ph.parameterSaveButtonClick();
		ph.deleteParameter(variableName);
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		av.openApplicationDashboard(applicationName);
		wh.deleteWorker(WorkerNames);
	}

//	@Test(enabled = true, priority = 2)
//	public void Validate_Pipeline_parameter_DefaultValueIn_Final_TriggerPagen() throws Exception {
//		driver = ListenersTest.driver;
//		logger = ListenersTest.logger;
//		ApplicationView av = new ApplicationView(driver);
//		PipelineView pv = new PipelineView(driver);
//		WorkerHelper wh = new WorkerHelper(driver);
//		ReportHelper rp = new ReportHelper(driver);
//		PipelineHelper ph = new PipelineHelper(driver);
//		WorkerHelper.workerExcelWriter();
//		String applicationName = WorkerHelper.excelRead(0);
//		av.openApplicationDashboard(applicationName);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
//		String workerName = wh.excelRead(1);
//		String WorkerNames = applicationName + "_" + workerName;
//		String pipelineName = "pipeline" + workerName;
//		wh.workerAgentNameFinder();
//		wh.addWorker(workerName);
//		wh.downloadWorker();
//		wh.agentRunning(WorkerNames);
//		wh.workerPageRefresh();
//		ph.navigateToPipeline();
//		ph.navigateToVisualEditor();
//		ph.createPipelineName(applicationName, pipelineName);
//		Thread.sleep(2000);
//		ph.addStageInPipeline("stage1", "qa", WorkerNames);
//		ph.createDynamicSteps("stage1");
//		ph.submitPipeline();
//		ph.navToParametersAndClickONAdd();
//
//		String variableName = "Username1";
//		String Defaultname = "Test";
//		String Evnname = "QA";
//		String EnvValue = "1234";
//
//		ph.paramterVariableNameCreation(variableName);
//		ph.paramterDefaultNameCreation(Defaultname);
//		ph.parameterSaveButtonClick();
//		ph.parametertoConfigurationNav();
//		ph.submitPipeline();
//		//ph.clickOnTriggerButton();
//		ph.triggerPipelineUsingIcon();
//		ph.workerSelectionInPipelineTrigger(WorkerNames);
//		Boolean defaultTriigerpage = ph.triggerPageParameterVerfiy(Defaultname);
//		assertTrue(defaultTriigerpage);
//
//		Thread.sleep(2000);
//		driver.navigate().refresh();
//		Thread.sleep(2000);
//		av.openApplicationDashboard(applicationName);
//		wh.deleteWorker(WorkerNames);
//	}

	@Test(enabled = true, priority = 3)
	public void Validate_In_Parameters_Give_defaultValue_And_Evn_TriggerButton_Should_Disable() throws Exception {
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
		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipeline("stage1", "qa", WorkerNames);
		ph.createDynamicSteps("stage1");
		ph.submitPipeline();
		ph.navToParametersAndClickONAdd();

		String variableName = "Username1";
		String Defaultname = "Test";
		String Evnname = "QA";
		String EnvValue = "1234";

		ph.paramterVariableNameCreation(variableName);
		ph.paramterDefaultNameCreation(Defaultname);
		ph.parameterEnvCreation(Evnname, EnvValue);
		Boolean values = ph.triggerButtonVerifyCation();
		assertTrue(values);
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		av.openApplicationDashboard(applicationName);
		wh.deleteWorker(WorkerNames);
	}


	@Test(enabled = true, priority = 4)
	public void Validate_In_Parameters_AfterGave_QAEnv_U_Can_See_Value() throws Exception {
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
		ph.submitPipeline();

		String variableName = "Username1";
		String Defaultname = "Test";
		String Evnname = "QA";
		String EnvValue = "1234";
		
		ph.navToParametersAndClickONAdd();
		ph.paramterVariableNameCreation(variableName);
		ph.paramterDefaultNameCreation(Defaultname);
		ph.parameterEnvCreation(Evnname, EnvValue);
		ph.parameterSaveButtonClick();
		ph.parametertoConfigurationNav();
		ph.removeStage();
		ph.addStageInPipeline("stage1", "qa", WorkerNames);
		ph.CreateDynamicParameterTestStep("stage1");
		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(WorkerNames);
		ph.triggerPipelineUsingButton();
		Thread.sleep(8000);
		driver.navigate().refresh();
		Thread.sleep(3000);
		ph.navigateToPipeline();
		Thread.sleep(8000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		String foldername = applicationName + "_" + pipelineName;
		ph.navLogsDetails(foldername);
		String values = ph.getLog(EnvValue);
		log.info(values);
		boolean flag;
		if (values.contains(EnvValue)) {
			flag = true;
		} else {
			flag = false;
		}
		assertTrue(flag);
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		av.openApplicationDashboard(applicationName);
		String agentName = wh.agentNames();
		String foldernames = applicationName + "_" + pipelineName;
		wh.finalSteps(agentName, WorkerNames, foldernames);
	}
	
	

	@Test(enabled = true, priority = 5)
	public void Validate_In_Parameters_AfterGave_OnlyDefaultValue_U_Can_See_Value() throws Exception {
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
		ph.submitPipeline();

		String variableName = "Username1";
		String Defaultname = "Test";

		ph.navToParametersAndClickONAdd();
		ph.paramterVariableNameCreation(variableName);
		ph.paramterDefaultNameCreation(Defaultname);
		ph.parameterSaveButtonClick();
		ph.parametertoConfigurationNav();
		ph.removeStage();
		ph.addStageInPipeline("stage1", "qa", WorkerNames);
		ph.CreateDynamicParameterTestStep("stage1");
		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(WorkerNames);
		ph.triggerPipelineUsingButton();
		Thread.sleep(4000);
		driver.navigate().refresh();
		Thread.sleep(3000);
		ph.navigateToPipeline();
		Thread.sleep(10000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		String foldername = applicationName + "_" + pipelineName;
		Thread.sleep(3000);
		ph.navLogsDetails(foldername);
		String values = ph.getLog(Defaultname);

		boolean flag;
		if (values.contains(Defaultname)) {
			flag = true;
		} else {
			flag = false;
		}
		assertTrue(flag);
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		av.openApplicationDashboard(applicationName);
		String agentName = wh.agentNames();
		String foldernames = applicationName + "_" + pipelineName;
		wh.finalSteps(agentName, WorkerNames, foldernames);
	}


	@Test(enabled = true, priority = 6)
	public void Validate_cloneParameter_With_Rerun_Scheduling() throws Exception {
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
		ph.submitPipeline();
		Thread.sleep(2000);
		ph.navigateToPipeline();
		ph.filterPipelineAndClickClonePiepline(pipelineName);
		String NewpipelineName = "pip" + workerName;
		ph.editPipelineName(NewpipelineName);
		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(WorkerNames);
		ph.triggerPipelineUsingButton();
		Thread.sleep(3000);
		ph.navigateToPipeline();
		Thread.sleep(8000);
		ph.filterPipelineAndNavToExeHistory(NewpipelineName);
		String foldername = applicationName + "_" + NewpipelineName;
		ph.navLogsDetails(foldername);
		String result = ph.getLog("openjdk");
		log.info(result);
		boolean flag;
		if (result.contains("openjdk")) {
			flag = true;
		} else {
			flag = false;
		}
		assertTrue(flag);
		ph.navigateToPipeline();
		driver.navigate().refresh();
		ph.filterPipelineAndNavToExeHistory(NewpipelineName);
		ph.reRun();
		driver.navigate().refresh();
		ph.navigateToPipeline();
		ph.filterPipelineAndNavToExeHistory(NewpipelineName);
		ph.navLogsDetails(foldername);
		String result2 = ph.getLog("openjdk");
		log.info(result2);
		boolean flag2;
		if (result.contains("openjdk")) {
			flag2 = true;
		} else {
			flag2 = false;
		}
		driver.navigate().refresh();
		ph.navigateToPipeline();
		ph.filterPipelineAndClickViewSchedulee(NewpipelineName);
		ph.schedulePipeline(NewpipelineName, WorkerNames, "1");
		ph.navigateToPipeline();
		Thread.sleep(8000);
		ph.filterPipelineAndNavToExeHistory(NewpipelineName);
		Thread.sleep(5000);
		int numberofSchedules = ph.scheduleVerfycation();
		boolean flag3;
		if (2 > numberofSchedules) {
			flag3 = false;
		} else {
			flag3 = true;
		}
		assertTrue(flag3);
		driver.navigate().refresh();
		Thread.sleep(10000);
		av.openApplicationDashboard(applicationName);
		Thread.sleep(2000);
		String agentName = wh.agentNames();
		wh.finalSteps(agentName, WorkerNames, foldername);
	}

	@Test(enabled = true, priority = 7)
	public void Validate_Schedule_update_Delete_Pipeline() throws Exception {
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
		String UpdatescheduleName = applicationName + "_" + pipelineName;
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
		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(WorkerNames);
		ph.triggerPipelineUsingButton();
		Thread.sleep(3000);
		ph.navigateToPipeline();
		ph.filterPipelineAndClickViewSchedulee(pipelineName);
		ph.schedulePipeline(pipelineName, WorkerNames, "1");
		ph.navigateToPipeline();
		Thread.sleep(8000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		Thread.sleep(5000);
		int numberofSchedules = ph.scheduleVerfycation();
		boolean flag3;
		if (2 > numberofSchedules) {
			flag3 = false;
		} else {
			flag3 = true;
		}
		assertTrue(flag3);

		ph.navigateToPipeline();
		ph.filterPipelineAndClickViewSchedulee(pipelineName);
		ph.updateSchedulePipeline(UpdatescheduleName, WorkerNames, "2");
		Thread.sleep(100000);
		ph.navigateToPipeline();
		Thread.sleep(8000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		Thread.sleep(5000);
		int numberofSchedules1 = ph.scheduleVerfycation();
		boolean flag4;
		if (2 > numberofSchedules1) {
			flag4 = false;
		} else {
			flag4 = true;
		}
		assertTrue(flag4);
		ph.navigateToPipeline();
		ph.filterPipelineAndClickViewSchedulee(pipelineName);

		ph.deleteSchedulePipeline(UpdatescheduleName);

		driver.navigate().refresh();
		Thread.sleep(10000);
		av.openApplicationDashboard(applicationName);
		Thread.sleep(2000);
		String agentName = wh.agentNames();
		String foldername = applicationName + "_" + pipelineName;
		wh.agentStoper(agentName);
		wh.deleteWorker(WorkerNames);
		wh.delectFilescustom("\\Data", "agent");
		wh.delectFilescustom("\\Data", "worker.lock");
	}

	@Test(enabled = true, priority = 8)
	public void Validate_Pipeline_parameter_Multiplechoice() throws Exception {
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
		ph.submitPipeline();
		ph.navToParametersAndClickONAdd();

		String variableName = "Username1";
		String Defaultname = "Test1,Test2,Test3,Test4";
		String expectedValue = "Test2";

		ph.paramterVariableNameCreation(variableName);
		ph.MulitiChoiceeClick();
		ph.paramterDefaultNameCreation(Defaultname);
		ph.parameterSaveButtonClick();
		ph.parametertoConfigurationNav();
		Thread.sleep(3000);
		ph.removeStage();
		Thread.sleep(3000);
		ph.addStageInPipeline("stage1", "qa", WorkerNames);
		Thread.sleep(3000);
		ph.CreateDynamicParameterTestStep("stage1");
		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(WorkerNames);
		ph.triggerPipelineUsingButtonWithMultichoiceParameter(expectedValue);
		Thread.sleep(4000);
		ph.navigateToPipeline();
		Thread.sleep(10000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		String foldername = applicationName + "_" + pipelineName;
		Thread.sleep(3000);
		ph.navLogsDetails(foldername);
		String values = ph.getLog(expectedValue);

		boolean flag;
		if (values.contains(expectedValue)) {
			flag = true;
		} else {
			flag = false;
		}
		assertTrue(flag);
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		av.openApplicationDashboard(applicationName);
		String agentName = wh.agentNames();
		String foldernames = applicationName + "_" + pipelineName;
		wh.finalSteps(agentName, WorkerNames, foldernames);
	}

	@Test(enabled = true, priority = 9)
	public void Validate_Pipeline_parameter_triggerable_Mandatory() throws Exception {
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
		ph.submitPipeline();

		String variableName1 = "Username1";
		String Defaultname1 = "Test1";

		String variableName2 = "Username2";
		String Defaultname2 = "Test2";

		String variableName3 = "Username3";
		String Defaultname3 = "Test3";

		String variableName4 = "Username4";
		String Defaultname4 = "Test4";

		ph.navToParametersAndClickONAdd();
		ph.paramterVariableNameCreation(variableName1);
		ph.triggerableClick();
		ph.paramterDefaultNameCreation(Defaultname1);
		ph.parameterSaveButtonClick();
		ph.parametertoConfigurationNav();

		ph.navToParametersAndClickONAdd();
		ph.paramterVariableNameCreation(variableName2);
		ph.triggerableClick();
		ph.paramterDefaultNameCreation(Defaultname2);
		ph.parameterSaveButtonClick();
		ph.parametertoConfigurationNav();

		ph.navToParametersAndClickONAdd();
		ph.paramterVariableNameCreation(variableName3);
		ph.triggerableClick();
		ph.paramterDefaultNameCreation(Defaultname3);
		ph.parameterSaveButtonClick();
		ph.parametertoConfigurationNav();

		ph.navToParametersAndClickONAdd();
		ph.paramterVariableNameCreation(variableName4);
		ph.triggerableClick();
		ph.paramterDefaultNameCreation(Defaultname4);
		ph.parameterSaveButtonClick();
		ph.parametertoConfigurationNav();

		ph.removeStage();
		ph.addStageInPipeline("stage1", "qa", WorkerNames);
		ph.CreateDynamicParameterTestStep("stage1");
		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(WorkerNames);
		Boolean value = ph.triggerPipelinePageTriggerableVerifycationParameter();
		assertTrue(value);
		ph.triggerPipelinePageEnterTriggerablenParameter(Defaultname1);
		ph.triggerPipelineUsingButton();

		Thread.sleep(4000);
		ph.navigateToPipeline();
		Thread.sleep(10000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		String foldername = applicationName + "_" + pipelineName;
		Thread.sleep(3000);
		ph.navLogsDetails(foldername);
		String values = ph.getLog(Defaultname1);

		boolean flag;
		if (values.contains(Defaultname1)) {
			flag = true;
		} else {
			flag = false;
		}
		assertTrue(flag);
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		av.openApplicationDashboard(applicationName);
		String agentName = wh.agentNames();
		String foldernames = applicationName + "_" + pipelineName;
		wh.finalSteps(agentName, WorkerNames, foldernames);
	}
}
