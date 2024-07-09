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

public class TC_Jmeter_plugin extends LoadData {

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();
	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(TC_Jmeter_plugin.class);

	@Test(priority = 1)
	public void validate_Jmeter_Plugin() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);

		String gitRepo = WorkerHelper.readparticularRowCol(1, 0);
		String Token = WorkerHelper.readparticularRowCol(2, 0);
		String jmeterpath = WorkerHelper.readparticularRowCol(1, 1);
		String BuildMachineWorker = "WorkerAutomation_AgTest";
		WorkerHelper.workerExcelWriter();
		String applicationName = "WorkerAutomation";
		// av.openApplicationDashboard(applicationName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
//		wh.workerAgentNameFinder();
//		wh.addWorker(workerName);
//		wh.downloadWorker();
//		wh.agentRunning(WorkerNames);
//		wh.workerPageRefresh();
		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", BuildMachineWorker);
		ph.dyanamicStepAndPlugin("stage1", "gitscm");

		ph.addGitSCMInput("gitscm", gitRepo, Token);
		ph.dyanamicStepAndPlugin("stage1", "Jmeter");

		ph.addJmeterpluginInput("Jmeter", jmeterpath);

		ph.submitPipeline();
		// ph.addProxyParameterfor480VM();

		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(BuildMachineWorker);
		ph.triggerPipelineUsingButtonWithBranch("main");
		driver.navigate().refresh();
		Thread.sleep(30000);
		ph.navigateToPipeline();
		Thread.sleep(15000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		String foldername = applicationName + "_" + pipelineName;
		ph.navLogsDetails(foldername);
		String result = ph.getLog("Plugin Executed Successfully");
		log.info(result);

		boolean flag;
		if (result.contains("Plugin Executed Successfully")) {
			flag = true;
		} else {
			flag = false;
		}
		assertTrue(flag);
		Thread.sleep(12000);
		driver.navigate().refresh();
		Thread.sleep(2000);
//		av.openApplicationDashboard(applicationName);
//		String agentName = wh.agentNames();
//		String foldernames = applicationName + "_" + pipelineName;
//		wh.finalSteps(agentName, WorkerNames, foldernames);
	}

	@Test(priority = 2)
	public void validate_Jmeter_Plugin_mandatory_field() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);

		String gitRepo = WorkerHelper.readparticularRowCol(1, 0);
		String Token = WorkerHelper.readparticularRowCol(2, 0);
		String jmeterpath = WorkerHelper.readparticularRowCol(1, 1);
		String BuildMachineWorker = "WorkerAutomation_AgTest";
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", BuildMachineWorker);
		ph.dyanamicStepAndPlugin("stage1", "gitscm");

		ph.addGitSCMInput("gitscm", gitRepo, Token);
		ph.dyanamicStepAndPlugin("stage1", "Jmeter");

		ph.addJmeterpluginInput("Jmeter", jmeterpath);

		ph.clickOnPlugin("Jmeter");

		String value = "Inputs for JMETER";
		boolean test = ph.verify_Plugin_Input_Fields(value, 2);
		assertTrue(test);
	}

	@Test(priority = 3)
	public void validate_Jmeter_Plugin_provide_Invalid_jmeterpath() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);

		String gitRepo = WorkerHelper.readparticularRowCol(1, 0);
		String Token = WorkerHelper.readparticularRowCol(2, 0);
		String jmeterpath = WorkerHelper.readparticularRowCol(1, 1);
		String BuildMachineWorker = "WorkerAutomation_AgTest";
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
		ph.dyanamicStepAndPlugin("stage1", "gitscm");

		ph.addGitSCMInput("gitscm", gitRepo, Token);
		ph.dyanamicStepAndPlugin("stage1", "Jmeter");

		ph.addJmeterpluginInput("Jmeter", "jmeterpath");

		ph.submitPipeline();
		// ph.addProxyParameterfor480VM();

		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(WorkerNames);
		ph.triggerPipelineUsingButtonWithBranch("main");
		driver.navigate().refresh();
		Thread.sleep(30000);
		ph.navigateToPipeline();
		Thread.sleep(15000);
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
		Thread.sleep(12000);
		driver.navigate().refresh();
		Thread.sleep(2000);
	}

}
