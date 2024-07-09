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

public class TC_Newman_postman_plugin extends LoadData {

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();

	@Test(priority = 1)
	public void validate_Newman_postman_Plugin() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);

		String gitRepo = WorkerHelper.readparticularRowCol(1, 3);
		String Token = WorkerHelper.readparticularRowCol(2, 3);
		String jsonPath = WorkerHelper.readparticularRowCol(1, 4);
		String path = WorkerHelper.readparticularRowCol(2, 4);
		String evnPath = WorkerHelper.readparticularRowCol(3, 4);

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
		ph.dyanamicStepAndPlugin("stage1", "NEWMAN POSTMAN");

		ph.addNewmanpluginInput("NEWMAN", jsonPath, path, evnPath);
		ph.submitPipeline();
		// ph.addProxyParameterfor480VM();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(VMMachineWorker);
		ph.triggerPipelineUsingButtonWithBranch("main");
		Thread.sleep(30000);
		driver.navigate().refresh();
		Thread.sleep(10000);
		ph.navigateToPipeline();
		Thread.sleep(5000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		String foldername = applicationName + "_" + pipelineName;
		ph.navLogsDetails(foldername);
		String result = ph.getLog("Uploading Report");
		boolean flag;
		if (result.contains("Uploading Report")) {
			flag = true;
		} else {
			flag = false;
		}
		assertTrue(flag);
		Thread.sleep(2000);
		driver.navigate().refresh();
	}

	@Test(priority = 2)
	public void validate_Newman_postman_Plugin_mandatory_field() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);

		String gitRepo = WorkerHelper.readparticularRowCol(1, 3);
		String Token = WorkerHelper.readparticularRowCol(2, 3);
		String jsonPath = WorkerHelper.readparticularRowCol(1, 4);
		String path = WorkerHelper.readparticularRowCol(2, 4);
		String evnPath = WorkerHelper.readparticularRowCol(3, 4);

		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;

		String VMMachineWorker = "WorkerAutomation_Agtest480VM";

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", VMMachineWorker);
		ph.dyanamicStepAndPlugin("stage1", "gitscm");
		ph.addGitSCMInput("gitscm", gitRepo, Token);
		ph.dyanamicStepAndPlugin("stage1", "NEWMAN POSTMAN");

		ph.addNewmanpluginInput("NEWMAN", jsonPath, path, evnPath);

		ph.clickOnPlugin("NEWMAN");

		String value = "Inputs for NEWMAN POSTMAN";
		boolean test = ph.verify_Plugin_Input_Fields(value, 2);
		assertTrue(test);

		boolean test1 = ph.verify_Plugin_Input_Fields(value, 3);
		assertTrue(test1);
	}

	@Test(priority = 3)
	public void validate_Newman_postman_Plugin_provide_Invalid_jsonPath() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);

		String gitRepo = WorkerHelper.readparticularRowCol(1, 3);
		String Token = WorkerHelper.readparticularRowCol(2, 3);
		String jsonPath = WorkerHelper.readparticularRowCol(1, 4);
		String path = WorkerHelper.readparticularRowCol(2, 4);
		String evnPath = WorkerHelper.readparticularRowCol(3, 4);

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
		ph.dyanamicStepAndPlugin("stage1", "NEWMAN POSTMAN");

		ph.addNewmanpluginInput("NEWMAN", "InvalidJsonpath", path, evnPath);
		ph.submitPipeline();
		// ph.addProxyParameterfor480VM();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(VMMachineWorker);
		ph.triggerPipelineUsingButtonWithBranch("main");
		Thread.sleep(30000);
		driver.navigate().refresh();
		Thread.sleep(10000);
		ph.navigateToPipeline();
		Thread.sleep(5000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);

		String foldername = applicationName + "_" + pipelineName;
		ph.navLogsDetails(foldername);
		String result = ph.getLog("unable to read data from file");
		boolean flag;
		if (result.contains("unable to read data from file")) {
			flag = true;
		} else {
			flag = false;
		}
		assertTrue(flag);
		Thread.sleep(2000);
		driver.navigate().refresh();
	}

	@Test(priority = 4)
	public void validate_Newman_postman_Plugin_provide_Invalid_Path() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);

		String gitRepo = WorkerHelper.readparticularRowCol(1, 3);
		String Token = WorkerHelper.readparticularRowCol(2, 3);
		String jsonPath = WorkerHelper.readparticularRowCol(1, 4);
		String path = WorkerHelper.readparticularRowCol(2, 4);
		String evnPath = WorkerHelper.readparticularRowCol(3, 4);

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
		ph.dyanamicStepAndPlugin("stage1", "NEWMAN POSTMAN");

		ph.addNewmanpluginInput("NEWMAN", jsonPath, "invalidPath", evnPath);
		ph.submitPipeline();
		// ph.addProxyParameterfor480VM();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(VMMachineWorker);
		ph.triggerPipelineUsingButtonWithBranch("main");
		Thread.sleep(30000);
		driver.navigate().refresh();
		Thread.sleep(10000);
		ph.navigateToPipeline();
		Thread.sleep(5000);
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
		Thread.sleep(2000);
		driver.navigate().refresh();

	}
}
