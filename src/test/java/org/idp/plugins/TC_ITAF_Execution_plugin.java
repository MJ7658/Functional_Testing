package org.idp.plugins;

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
import org.infy.uiPages.Workflows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

@Listeners(ListenersTest.class)

public class TC_ITAF_Execution_plugin extends LoadData {

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();

	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(TC_ITAF_Execution_plugin.class);

	@Test(priority = 1)
	public void validate_ITAF_Execution_Plugin() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();

		String itafURL = WorkerHelper.readparticularRowCol(1, 24);
		String projectName = WorkerHelper.readparticularRowCol(2, 24);
		String testCase = WorkerHelper.readparticularRowCol(3, 24);
		String workername = WorkerHelper.readparticularRowCol(4, 24);
		String portfolioName = WorkerHelper.readparticularRowCol(5, 24);
		String emailID = WorkerHelper.readparticularRowCol(6, 24);
		String ITAFToken = WorkerHelper.readparticularRowCol(7, 24);

		String applicationName = WorkerHelper.excelRead(0);
		av.openApplicationDashboard(applicationName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		wh.delectFilescustom("\\ITAF", "worker.lock");
		wh.workerAgentNameFinder();
		String myAgentpath = "agent_0.4.5 --idpServerUrl=https://itafapp.ad.infosys.com --agentName=IDPTest_NewITAFPluginTest --token=cgij02t5j47vvcvtjlig";
		wh.agentRunnerITAFFolder(myAgentpath);
		wh.addWorker(workerName);
		wh.downloadWorker();
		wh.agentRunning(WorkerNames);
		Thread.sleep(3000);
		wh.workerPageRefresh();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", WorkerNames);

		ph.dyanamicStepAndPlugin("stage1", "ITAF-Execution");
		ph.addITAFExecutionpluginInput("ITAF-Execution", itafURL, projectName, testCase, workername, portfolioName,
				emailID, ITAFToken);

		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(WorkerNames);
		ph.triggerPipelineUsingButton();
		Thread.sleep(90000);
		driver.navigate().refresh();
		ph.navigateToPipeline();
		Thread.sleep(15000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);

		String foldername = applicationName + "_" + pipelineName;
		ph.navLogsDetails(foldername);
		String result = ph.getLog("200 OK");
		log.info(result);
		boolean flag;
		if (result.contains("200 OK")) {
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
		wh.agentStoper("agent_0.3.0");
		wh.delectFilescustom("\\ITAF", "worker.lock");
		Thread.sleep(10000);
	}

	@Test(priority = 2)
	public void validate_ITAF_Execution_Plugin_mandatory_field() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();

		String itafURL = WorkerHelper.readparticularRowCol(1, 24);
		String projectName = WorkerHelper.readparticularRowCol(2, 24);
		String testCase = WorkerHelper.readparticularRowCol(3, 24);
		String workername = WorkerHelper.readparticularRowCol(4, 24);
		String portfolioName = WorkerHelper.readparticularRowCol(5, 24);
		String emailID = WorkerHelper.readparticularRowCol(6, 24);
		String ITAFToken = WorkerHelper.readparticularRowCol(7, 24);

		String applicationName = WorkerHelper.excelRead(0);
		av.openApplicationDashboard(applicationName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		wh.delectFilescustom("\\ITAF", "worker.lock");

		wh.addWorker(workerName);
		Thread.sleep(3000);
		wh.workerPageRefresh();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", WorkerNames);

		ph.dyanamicStepAndPlugin("stage1", "ITAF-Execution");
		ph.addITAFExecutionpluginInput("ITAF-Execution", itafURL, projectName, testCase, workername, portfolioName,
				emailID, ITAFToken);

		ph.clickOnPlugin("ITAF-Execution");

		String value = "Inputs for ITAF-EXECUTION";
		boolean test = ph.verify_Plugin_Input_Fields(value, 2);
		assertTrue(test);

		boolean test1 = ph.verify_Plugin_Input_Fields(value, 3);
		assertTrue(test1);

		boolean test2 = ph.verify_Plugin_Input_Fields(value, 4);
		assertTrue(test2);
		ph.scrollToElement(value, 4);
		boolean test3 = ph.verify_Plugin_Input_Fields(value, 5);
		assertTrue(test3);

		boolean test4 = ph.verify_Plugin_Input_Fields(value, 6);
		assertTrue(test4);
		ph.scrollToElement(value, 4);
		boolean test5 = ph.verify_Plugin_Input_Fields(value, 7);
		assertTrue(test5);

		boolean test6 = ph.verify_Plugin_Input_Fields(value, 8);
		assertTrue(test6);
	}

	@Test(priority = 3)
	public void validate_ITAF_Execution_Plugin_Invalid_itafURL() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();

		String itafURL = WorkerHelper.readparticularRowCol(1, 24);
		String projectName = WorkerHelper.readparticularRowCol(2, 24);
		String testCase = WorkerHelper.readparticularRowCol(3, 24);
		String workername = WorkerHelper.readparticularRowCol(4, 24);
		String portfolioName = WorkerHelper.readparticularRowCol(5, 24);
		String emailID = WorkerHelper.readparticularRowCol(6, 24);
		String ITAFToken = WorkerHelper.readparticularRowCol(7, 24);

		String applicationName = WorkerHelper.excelRead(0);
		av.openApplicationDashboard(applicationName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		wh.delectFilescustom("\\ITAF", "worker.lock");
		wh.workerAgentNameFinder();
		String myAgentpath = "agent_0.4.5 --idpServerUrl=https://itafapp.ad.infosys.com --agentName=IDPTest_NewITAFPluginTest --token=cgij02t5j47vvcvtjlig";
		wh.agentRunnerITAFFolder(myAgentpath);
		wh.addWorker(workerName);
		wh.downloadWorker();
		wh.agentRunning(WorkerNames);
		Thread.sleep(3000);
		wh.workerPageRefresh();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", WorkerNames);

		ph.dyanamicStepAndPlugin("stage1", "ITAF-Execution");
		ph.addITAFExecutionpluginInput("ITAF-Execution", "itafURL", projectName, testCase, workername, portfolioName,
				emailID, ITAFToken);

		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(WorkerNames);
		ph.triggerPipelineUsingButton();
		Thread.sleep(90000);
		driver.navigate().refresh();
		ph.navigateToPipeline();
		Thread.sleep(15000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);

		String foldername = applicationName + "_" + pipelineName;
		ph.navLogsDetails(foldername);
		String result = ph.getLog("ERROR runtime error: invalid memory address or nil pointer dereference");
		log.info(result);
		boolean flag;
		if (result.contains("ERROR runtime error: invalid memory address or nil pointer dereference")) {
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
		wh.agentStoper("agent_0.4.5");
		wh.delectFilescustom("\\ITAF", "worker.lock");
		Thread.sleep(10000);
	}

	@Test(priority = 4)
	public void validate_ITAF_Execution_Plugin_Invalid_projectName() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();

		String itafURL = WorkerHelper.readparticularRowCol(1, 24);
		String projectName = WorkerHelper.readparticularRowCol(2, 24);
		String testCase = WorkerHelper.readparticularRowCol(3, 24);
		String workername = WorkerHelper.readparticularRowCol(4, 24);
		String portfolioName = WorkerHelper.readparticularRowCol(5, 24);
		String emailID = WorkerHelper.readparticularRowCol(6, 24);
		String ITAFToken = WorkerHelper.readparticularRowCol(7, 24);

		String applicationName = WorkerHelper.excelRead(0);
		av.openApplicationDashboard(applicationName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		wh.delectFilescustom("\\ITAF", "worker.lock");
		wh.workerAgentNameFinder();
		String myAgentpath = "agent_0.4.5 --idpServerUrl=https://itafapp.ad.infosys.com --agentName=IDPTest_NewITAFPluginTest --token=cgij02t5j47vvcvtjlig";
		wh.agentRunnerITAFFolder(myAgentpath);
		wh.addWorker(workerName);
		wh.downloadWorker();
		wh.agentRunning(WorkerNames);
		Thread.sleep(3000);
		wh.workerPageRefresh();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", WorkerNames);

		ph.dyanamicStepAndPlugin("stage1", "ITAF-Execution");
		ph.addITAFExecutionpluginInput("ITAF-Execution", itafURL, "projectName", testCase, workername, portfolioName,
				emailID, ITAFToken);

		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(WorkerNames);
		ph.triggerPipelineUsingButton();
		Thread.sleep(90000);
		driver.navigate().refresh();
		ph.navigateToPipeline();
		Thread.sleep(15000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);

		String foldername = applicationName + "_" + pipelineName;
		ph.navLogsDetails(foldername);
		String result = ph.getLog("INFO 200 OK");
		log.info(result);
		boolean flag;
		if (result.contains("INFO 200 OK")) {
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
		wh.agentStoper("agent_0.4.5");
		wh.delectFilescustom("\\ITAF", "worker.lock");
		Thread.sleep(10000);
	}

	@Test(priority = 5)
	public void validate_ITAF_Execution_Plugin_Invalid_testCase() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();

		String itafURL = WorkerHelper.readparticularRowCol(1, 24);
		String projectName = WorkerHelper.readparticularRowCol(2, 24);
		String testCase = WorkerHelper.readparticularRowCol(3, 24);
		String workername = WorkerHelper.readparticularRowCol(4, 24);
		String portfolioName = WorkerHelper.readparticularRowCol(5, 24);
		String emailID = WorkerHelper.readparticularRowCol(6, 24);
		String ITAFToken = WorkerHelper.readparticularRowCol(7, 24);

		String applicationName = WorkerHelper.excelRead(0);
		av.openApplicationDashboard(applicationName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		wh.delectFilescustom("\\ITAF", "worker.lock");
		wh.workerAgentNameFinder();
		String myAgentpath = "agent_0.4.5 --idpServerUrl=https://itafapp.ad.infosys.com --agentName=IDPTest_NewITAFPluginTest --token=cgij02t5j47vvcvtjlig";
		wh.agentRunnerITAFFolder(myAgentpath);
		wh.addWorker(workerName);
		wh.downloadWorker();
		wh.agentRunning(WorkerNames);
		Thread.sleep(3000);
		wh.workerPageRefresh();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", WorkerNames);

		ph.dyanamicStepAndPlugin("stage1", "ITAF-Execution");
		ph.addITAFExecutionpluginInput("ITAF-Execution", itafURL, projectName, "testCase", workername, portfolioName,
				emailID, ITAFToken);

		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(WorkerNames);
		ph.triggerPipelineUsingButton();
		Thread.sleep(90000);
		driver.navigate().refresh();
		ph.navigateToPipeline();
		Thread.sleep(15000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);

		String foldername = applicationName + "_" + pipelineName;
		ph.navLogsDetails(foldername);
		String result = ph.getLog("INFO 200 OK");
		log.info(result);
		boolean flag;
		if (result.contains("INFO 200 OK")) {
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
		wh.agentStoper("agent_0.4.5");
		wh.delectFilescustom("\\ITAF", "worker.lock");
		Thread.sleep(10000);
	}

	@Test(priority = 6)
	public void validate_ITAF_Execution_Plugin_Invalid_workername() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();

		String itafURL = WorkerHelper.readparticularRowCol(1, 24);
		String projectName = WorkerHelper.readparticularRowCol(2, 24);
		String testCase = WorkerHelper.readparticularRowCol(3, 24);
		String workername = WorkerHelper.readparticularRowCol(4, 24);
		String portfolioName = WorkerHelper.readparticularRowCol(5, 24);
		String emailID = WorkerHelper.readparticularRowCol(6, 24);
		String ITAFToken = WorkerHelper.readparticularRowCol(7, 24);

		String applicationName = WorkerHelper.excelRead(0);
		av.openApplicationDashboard(applicationName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		wh.delectFilescustom("\\ITAF", "worker.lock");
		wh.workerAgentNameFinder();
		String myAgentpath = "agent_0.4.5 --idpServerUrl=https://itafapp.ad.infosys.com --agentName=IDPTest_NewITAFPluginTest --token=cgij02t5j47vvcvtjlig";
		wh.agentRunnerITAFFolder(myAgentpath);
		wh.addWorker(workerName);
		wh.downloadWorker();
		wh.agentRunning(WorkerNames);
		Thread.sleep(3000);
		wh.workerPageRefresh();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", WorkerNames);

		ph.dyanamicStepAndPlugin("stage1", "ITAF-Execution");
		ph.addITAFExecutionpluginInput("ITAF-Execution", itafURL, projectName, testCase, "workername", portfolioName,
				emailID, ITAFToken);

		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(WorkerNames);
		ph.triggerPipelineUsingButton();
		Thread.sleep(90000);
		driver.navigate().refresh();
		ph.navigateToPipeline();
		Thread.sleep(15000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);

		String foldername = applicationName + "_" + pipelineName;
		ph.navLogsDetails(foldername);
		String result = ph.getLog("INFO Removing Old Result files if exists");
		log.info(result);
		boolean flag;
		if (result.contains("INFO Removing Old Result files if exists")) {
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
		wh.agentStoper("agent_0.4.5");
		wh.delectFilescustom("\\ITAF", "worker.lock");
		Thread.sleep(10000);
	}

	@Test(priority = 7)
	public void validate_ITAF_Execution_Plugin_Invalid_portfolioName() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();

		String itafURL = WorkerHelper.readparticularRowCol(1, 24);
		String projectName = WorkerHelper.readparticularRowCol(2, 24);
		String testCase = WorkerHelper.readparticularRowCol(3, 24);
		String workername = WorkerHelper.readparticularRowCol(4, 24);
		String portfolioName = WorkerHelper.readparticularRowCol(5, 24);
		String emailID = WorkerHelper.readparticularRowCol(6, 24);
		String ITAFToken = WorkerHelper.readparticularRowCol(7, 24);

		String applicationName = WorkerHelper.excelRead(0);
		av.openApplicationDashboard(applicationName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		wh.delectFilescustom("\\ITAF", "worker.lock");
		wh.workerAgentNameFinder();
		String myAgentpath = "agent_0.4.5 --idpServerUrl=https://itafapp.ad.infosys.com --agentName=IDPTest_NewITAFPluginTest --token=cgij02t5j47vvcvtjlig";
		wh.agentRunnerITAFFolder(myAgentpath);
		wh.addWorker(workerName);
		wh.downloadWorker();
		wh.agentRunning(WorkerNames);
		Thread.sleep(3000);
		wh.workerPageRefresh();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", WorkerNames);

		ph.dyanamicStepAndPlugin("stage1", "ITAF-Execution");
		ph.addITAFExecutionpluginInput("ITAF-Execution", itafURL, projectName, testCase, workername, "portfolioName",
				emailID, ITAFToken);

		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(WorkerNames);
		ph.triggerPipelineUsingButton();
		Thread.sleep(90000);
		driver.navigate().refresh();
		ph.navigateToPipeline();
		Thread.sleep(25000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);

		String foldername = applicationName + "_" + pipelineName;
		ph.navLogsDetails(foldername);
		String result = ph.getLog("ERROR Error occurred in Execution stage");
		log.info(result);
		boolean flag;
		if (result.contains("ERROR Error occurred in Execution stage")) {
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
		wh.agentStoper("agent_0.4.5");
		wh.delectFilescustom("\\ITAF", "worker.lock");
		Thread.sleep(10000);
	}

	@Test(priority = 8)
	public void validate_ITAF_Execution_Plugin_Invalid_emailID() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();

		String itafURL = WorkerHelper.readparticularRowCol(1, 24);
		String projectName = WorkerHelper.readparticularRowCol(2, 24);
		String testCase = WorkerHelper.readparticularRowCol(3, 24);
		String workername = WorkerHelper.readparticularRowCol(4, 24);
		String portfolioName = WorkerHelper.readparticularRowCol(5, 24);
		String emailID = WorkerHelper.readparticularRowCol(6, 24);
		String ITAFToken = WorkerHelper.readparticularRowCol(7, 24);

		String applicationName = WorkerHelper.excelRead(0);
		av.openApplicationDashboard(applicationName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		wh.delectFilescustom("\\ITAF", "worker.lock");
		wh.workerAgentNameFinder();
		String myAgentpath = "agent_0.4.5 --idpServerUrl=https://itafapp.ad.infosys.com --agentName=IDPTest_NewITAFPluginTest --token=cgij02t5j47vvcvtjlig";
		wh.agentRunnerITAFFolder(myAgentpath);
		wh.addWorker(workerName);
		wh.downloadWorker();
		wh.agentRunning(WorkerNames);
		Thread.sleep(3000);
		wh.workerPageRefresh();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", WorkerNames);

		ph.dyanamicStepAndPlugin("stage1", "ITAF-Execution");
		ph.addITAFExecutionpluginInput("ITAF-Execution", itafURL, projectName, testCase, workername, portfolioName,
				"emailID@g.com", ITAFToken);

		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(WorkerNames);
		ph.triggerPipelineUsingButton();
		Thread.sleep(90000);
		driver.navigate().refresh();
		ph.navigateToPipeline();
		Thread.sleep(25000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);

		String foldername = applicationName + "_" + pipelineName;
		ph.navLogsDetails(foldername);
		String result = ph.getLog("ERROR Error occurred in Execution stage");
		log.info(result);
		boolean flag;
		if (result.contains("ERROR Error occurred in Execution stage")) {
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
		wh.agentStoper("agent_0.4.5");
		wh.delectFilescustom("\\ITAF", "worker.lock");
		Thread.sleep(10000);

	}

	@Test(priority = 9)
	public void validate_ITAF_Execution_Plugin_Invalid_ITAFToken() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();

		String itafURL = WorkerHelper.readparticularRowCol(1, 24);
		String projectName = WorkerHelper.readparticularRowCol(2, 24);
		String testCase = WorkerHelper.readparticularRowCol(3, 24);
		String workername = WorkerHelper.readparticularRowCol(4, 24);
		String portfolioName = WorkerHelper.readparticularRowCol(5, 24);
		String emailID = WorkerHelper.readparticularRowCol(6, 24);
		String ITAFToken = WorkerHelper.readparticularRowCol(7, 24);

		String applicationName = WorkerHelper.excelRead(0);
		av.openApplicationDashboard(applicationName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		wh.delectFilescustom("\\ITAF", "worker.lock");
		wh.workerAgentNameFinder();
		String myAgentpath = "agent_0.4.5 --idpServerUrl=https://itafapp.ad.infosys.com --agentName=IDPTest_NewITAFPluginTest --token=cgij02t5j47vvcvtjlig";
		wh.agentRunnerITAFFolder(myAgentpath);
		wh.addWorker(workerName);
		wh.downloadWorker();
		wh.agentRunning(WorkerNames);
		Thread.sleep(3000);
		wh.workerPageRefresh();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", WorkerNames);

		ph.dyanamicStepAndPlugin("stage1", "ITAF-Execution");
		ph.addITAFExecutionpluginInput("ITAF-Execution", itafURL, projectName, testCase, workername, portfolioName,
				emailID, "ITAFToken");

		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(WorkerNames);
		ph.triggerPipelineUsingButton();
		Thread.sleep(90000);
		driver.navigate().refresh();
		ph.navigateToPipeline();
		Thread.sleep(25000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);

		String foldername = applicationName + "_" + pipelineName;
		ph.navLogsDetails(foldername);
		String result = ph.getLog("ERROR Error occurred in Execution stage");
		log.info(result);
		boolean flag;
		if (result.contains("ERROR Error occurred in Execution stage")) {
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
		wh.agentStoper("agent_0.4.5");
		wh.delectFilescustom("\\ITAF", "worker.lock");
		Thread.sleep(10000);
	}
}
