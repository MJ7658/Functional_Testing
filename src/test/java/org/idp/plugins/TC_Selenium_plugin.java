package org.idp.plugins;

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
import org.infy.uiPages.Workflows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

@Listeners(ListenersTest.class)

public class TC_Selenium_plugin extends LoadData {

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();

	@Test(priority = 1)
	public void validate_Selenium_Plugin() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);

		String gitRepo = WorkerHelper.readparticularRowCol(1, 6);
		String Token = WorkerHelper.readparticularRowCol(2, 6);
		String path = WorkerHelper.readparticularRowCol(1, 7);
		String testngfile = WorkerHelper.readparticularRowCol(2, 7);

		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		av.openApplicationDashboard(applicationName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		String VMMachineWorker = "WorkerAutomation_BuildmachineWorker";
		wh.workerAgentNameFinder();
		wh.addWorker(workerName);
//		wh.downloadWorker();
//		wh.agentRunning(WorkerNames);
//		wh.workerPageRefresh();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", VMMachineWorker);
		ph.dyanamicStepAndPlugin("stage1", "gitscm");
		ph.addGitSCMInput("gitscm", gitRepo, Token);
		ph.dyanamicStepAndPlugin("stage1", "Selenium");

		ph.addSeleniumpluginInput("Selenium", path, testngfile);

		ph.submitPipeline();
		// ph.addProxyParameterfor480VM();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(VMMachineWorker);
		ph.triggerPipelineUsingButtonWithBranch("main");
		Thread.sleep(30000);
		driver.navigate().refresh();
		Thread.sleep(30000);
		ph.navigateToPipeline();
		Thread.sleep(15000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);

		String foldername = applicationName + "_" + pipelineName;
		ph.navLogsDetails(foldername);
		String result = ph.getLog("Plugin executed successfully");

		boolean flag;
		if (result.contains("Plugin executed successfully")) {
			flag = true;
		} else {
			flag = false;
		}
		assertTrue(flag);
		Thread.sleep(15000);
		driver.navigate().refresh();
		Thread.sleep(20000);
		av.openApplicationDashboard(applicationName);
		String agentName = wh.agentNames();
		String foldernames = applicationName + "_" + pipelineName;

		wh.agentStoper(agentName);
		wh.deleteWorker(WorkerNames);
		wh.delectFilescustom("\\Data", "agent");
		wh.delectFilescustom("\\Data", "worker.lock");
	}

	@Test(priority = 2)
	public void validate_Selenium_Plugin_mandatory_field() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);

		String gitRepo = WorkerHelper.readparticularRowCol(1, 6);
		String Token = WorkerHelper.readparticularRowCol(2, 6);
		String path = WorkerHelper.readparticularRowCol(1, 7);
		String testngfile = WorkerHelper.readparticularRowCol(2, 7);

		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		String VMMachineWorker = "WorkerAutomation_BuildmachineWorker";

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", VMMachineWorker);
		ph.dyanamicStepAndPlugin("stage1", "gitscm");
		ph.addGitSCMInput("gitscm", gitRepo, Token);
		ph.dyanamicStepAndPlugin("stage1", "Selenium");

		ph.addSeleniumpluginInput("Selenium", path, testngfile);

		ph.clickOnPlugin("Selenium");

		String value = "Inputs for SELENIUM";
		boolean test = ph.verify_Plugin_Input_Fields(value, 2);
		assertTrue(test);

		boolean test1 = ph.verify_Plugin_Input_Fields(value, 3);
		assertTrue(test1);
	}

	@Test(priority = 3)
	public void validate_Selenium_Plugin_provide_Invalid_path() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);

		String gitRepo = WorkerHelper.readparticularRowCol(1, 6);
		String Token = WorkerHelper.readparticularRowCol(2, 6);
		String path = WorkerHelper.readparticularRowCol(1, 7);
		String testngfile = WorkerHelper.readparticularRowCol(2, 7);

		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		av.openApplicationDashboard(applicationName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		String VMMachineWorker = "WorkerAutomation_BuildmachineWorker";
		wh.workerAgentNameFinder();
//		wh.addWorker(workerName);
//		wh.downloadWorker();
//		wh.agentRunning(WorkerNames);
//		wh.workerPageRefresh();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", VMMachineWorker);
		ph.dyanamicStepAndPlugin("stage1", "gitscm");
		ph.addGitSCMInput("gitscm", gitRepo, Token);
		ph.dyanamicStepAndPlugin("stage1", "Selenium");

		ph.addSeleniumpluginInput("Selenium", "path", testngfile);

		ph.submitPipeline();
		// ph.addProxyParameterfor480VM();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(VMMachineWorker);
		ph.triggerPipelineUsingButtonWithBranch("main");
		Thread.sleep(30000);
		driver.navigate().refresh();
		Thread.sleep(30000);
		ph.navigateToPipeline();
		Thread.sleep(15000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		String foldername = applicationName + "_" + pipelineName;
		ph.navLogsDetails(foldername);
		String result = ph.getLog("ERROR Error occurred in Execution stage");

		boolean flag;
		if (result.contains("ERROR Error occurred in Execution stage")) {
			flag = true;
		} else {
			flag = false;
		}
		assertTrue(flag);
		Thread.sleep(15000);
		driver.navigate().refresh();
		Thread.sleep(20000);
//		av.openApplicationDashboard(applicationName);
//		String agentName = wh.agentNames();
//		String foldernames = applicationName + "_" + pipelineName;
//
//		wh.agentStoper(agentName);
//		wh.deleteWorker(WorkerNames);
//		wh.delectFilescustom("\\Data", "agent");
//		wh.delectFilescustom("\\Data", "worker.lock");
	}

	@Test(priority = 4)
	public void validate_Selenium_Plugin_provide_Invalid_testngfile() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);

		String gitRepo = WorkerHelper.readparticularRowCol(1, 6);
		String Token = WorkerHelper.readparticularRowCol(2, 6);
		String path = WorkerHelper.readparticularRowCol(1, 7);
		String testngfile = WorkerHelper.readparticularRowCol(2, 7);

		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		av.openApplicationDashboard(applicationName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		String VMMachineWorker = "WorkerAutomation_BuildmachineWorker";
		wh.workerAgentNameFinder();
		wh.addWorker(workerName);
		wh.downloadWorker();
		wh.agentRunning(WorkerNames);
		wh.workerPageRefresh();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", VMMachineWorker);
		ph.dyanamicStepAndPlugin("stage1", "gitscm");
		ph.addGitSCMInput("gitscm", gitRepo, Token);
		ph.dyanamicStepAndPlugin("stage1", "Selenium");

		ph.addSeleniumpluginInput("Selenium", path, "testngfile");

		ph.submitPipeline();
		// ph.addProxyParameterfor480VM();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(VMMachineWorker);
		ph.triggerPipelineUsingButtonWithBranch("main");
		Thread.sleep(30000);
		driver.navigate().refresh();
		Thread.sleep(30000);
		ph.navigateToPipeline();
		Thread.sleep(15000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);

		String foldername = applicationName + "_" + pipelineName;
		ph.navLogsDetails(foldername);
		String result = ph.getLog("ERROR Error while executing the selenium command");
		boolean flag;
		if (result.contains("ERROR Error while executing the selenium command")) {
			flag = true;
		} else {
			flag = false;
		}
		assertTrue(flag);
		Thread.sleep(15000);
		driver.navigate().refresh();
		Thread.sleep(20000);
		av.openApplicationDashboard(applicationName);
		String agentName = wh.agentNames();
		String foldernames = applicationName + "_" + pipelineName;

		wh.agentStoper(agentName);
		wh.deleteWorker(WorkerNames);
		wh.delectFilescustom("\\Data", "agent");
		wh.delectFilescustom("\\Data", "worker.lock");
	}
}
