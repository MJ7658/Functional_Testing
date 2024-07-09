package org.idp.plugins;

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
import org.infy.uiPages.Workflows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

@Listeners(ListenersTest.class)

public class TC_CodeQL_Analyze_plugin extends LoadData {

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();
	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(TC_CodeQL_Analyze_plugin.class);

	@Test(priority = 1)
	public void validate_CodeQL_Analyze_Plugin() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();

		String gitURL = WorkerHelper.readparticularRowCol(1, 16);
		String token = WorkerHelper.readparticularRowCol(2, 16);
		String projectName = WorkerHelper.readparticularRowCol(1, 17);
		String projectPath = WorkerHelper.readparticularRowCol(2, 17);
		String gitToken = WorkerHelper.readparticularRowCol(3, 17);
		String language = WorkerHelper.readparticularRowCol(4, 17);

		String applicationName = WorkerHelper.excelRead(0);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;

		String BuildMachineWorker = "WorkerAutomation_BuildmachineWorker";

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", BuildMachineWorker);
		ph.dyanamicStepAndPlugin("stage1", "gitscm");

		ph.addGitSCMInput("gitscm", gitURL, token);

		ph.dyanamicStepAndPlugin("stage1", "CodeQL-Analyze");

		ph.addCodeQLAnalyzepluginInput("CodeQL", projectName, projectPath, gitToken, language);

		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(BuildMachineWorker);
		ph.triggerPipelineUsingButtonWithBranch("dev");
		Thread.sleep(25000);
		driver.navigate().refresh();
		Thread.sleep(10000);
		ph.navigateToPipeline();
		Thread.sleep(25000);
		Thread.sleep(5000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);

		String foldername = applicationName + "_" + pipelineName;
		ph.navLogsDetails(foldername);
		String result = ph.getLog("Uploading report");
		log.info(result);
		boolean flag;
		if (result.contains("Uploading report")) {
			flag = true;
		} else {
			flag = false;
		}
		assertTrue(flag);
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);

	}

	@Test(priority = 2)
	public void validate_CodeQL_Analyze_Plugin_mandatory_field() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();

		String gitURL = WorkerHelper.readparticularRowCol(1, 16);
		String token = WorkerHelper.readparticularRowCol(2, 16);
		String projectName = WorkerHelper.readparticularRowCol(1, 17);
		String projectPath = WorkerHelper.readparticularRowCol(2, 17);
		String gitToken = WorkerHelper.readparticularRowCol(3, 17);
		String language = WorkerHelper.readparticularRowCol(4, 17);

		String applicationName = WorkerHelper.excelRead(0);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;

		String BuildMachineWorker = "WorkerAutomation_BuildmachineWorker";

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", BuildMachineWorker);
		ph.dyanamicStepAndPlugin("stage1", "gitscm");

		ph.addGitSCMInput("gitscm", gitURL, token);

		ph.dyanamicStepAndPlugin("stage1", "CodeQL-Analyze");

		ph.addCodeQLAnalyzepluginInput("CodeQL", projectName, projectPath, gitToken, language);
		ph.clickOnPlugin("CodeQL");

		String value = "Inputs for CODEQL-ANALYZE";
		boolean test = ph.verify_Plugin_Input_Fields(value, 2);
		assertTrue(test);

		boolean test1 = ph.verify_Plugin_Input_Fields(value, 3);
		assertTrue(test1);

		boolean test2 = ph.verify_Plugin_Input_Fields(value, 4);
		assertTrue(test2);

		boolean test3 = ph.verify_Plugin_Input_Fields(value, 5);
		assertTrue(test3);
	}

	@Test(priority = 3)
	public void validate_CodeQL_Analyze_Plugin_provide_Invalid_ProjectName() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();

		String gitURL = WorkerHelper.readparticularRowCol(1, 16);
		String token = WorkerHelper.readparticularRowCol(2, 16);
		String projectName = WorkerHelper.readparticularRowCol(1, 17);
		String projectPath = WorkerHelper.readparticularRowCol(2, 17);
		String gitToken = WorkerHelper.readparticularRowCol(3, 17);
		String language = WorkerHelper.readparticularRowCol(4, 17);

		String applicationName = WorkerHelper.excelRead(0);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		String BuildMachineWorker = "WorkerAutomation_BuildmachineWorker";

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", BuildMachineWorker);
		ph.dyanamicStepAndPlugin("stage1", "gitscm");

		ph.addGitSCMInput("gitscm", gitURL, token);

		ph.dyanamicStepAndPlugin("stage1", "CodeQL-Analyze");

		ph.addCodeQLAnalyzepluginInput("CodeQL", "invalidName", projectPath, gitToken, language);

		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(BuildMachineWorker);
		ph.triggerPipelineUsingButtonWithBranch("dev");
		Thread.sleep(25000);
		driver.navigate().refresh();
		Thread.sleep(10000);
		ph.navigateToPipeline();
		Thread.sleep(25000);
		Thread.sleep(5000);
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
	}

	@Test(priority = 4)
	public void validate_CodeQL_Analyze_Plugin_provide_Invalid_ProjectPath() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();

		String gitURL = WorkerHelper.readparticularRowCol(1, 16);
		String token = WorkerHelper.readparticularRowCol(2, 16);
		String projectName = WorkerHelper.readparticularRowCol(1, 17);
		String projectPath = WorkerHelper.readparticularRowCol(2, 17);
		String gitToken = WorkerHelper.readparticularRowCol(3, 17);
		String language = WorkerHelper.readparticularRowCol(4, 17);

		String applicationName = WorkerHelper.excelRead(0);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		String BuildMachineWorker = "WorkerAutomation_BuildmachineWorker";

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", BuildMachineWorker);
		ph.dyanamicStepAndPlugin("stage1", "gitscm");

		ph.addGitSCMInput("gitscm", gitURL, token);

		ph.dyanamicStepAndPlugin("stage1", "CodeQL-Analyze");

		ph.addCodeQLAnalyzepluginInput("CodeQL", projectName, "itsprojectPath", gitToken, language);

		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(BuildMachineWorker);
		ph.triggerPipelineUsingButtonWithBranch("dev");
		Thread.sleep(25000);
		driver.navigate().refresh();
		Thread.sleep(10000);
		ph.navigateToPipeline();
		Thread.sleep(25000);
		Thread.sleep(5000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		String foldername = applicationName + "_" + pipelineName;
		ph.navLogsDetails(foldername);
		String result = ph.getLog("A fatal error occurred: Invalid repository name itsprojectPath.");
		log.info(result);
		boolean flag;
		if (result.contains("A fatal error occurred: Invalid repository name itsprojectPath.")) {
			flag = true;
		} else {
			flag = false;
		}
		assertTrue(flag);
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);

	}

	@Test(priority = 5)
	public void validate_CodeQL_Analyze_Plugin_provide_Invalid_token() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();

		String gitURL = WorkerHelper.readparticularRowCol(1, 16);
		String token = WorkerHelper.readparticularRowCol(2, 16);
		String projectName = WorkerHelper.readparticularRowCol(1, 17);
		String projectPath = WorkerHelper.readparticularRowCol(2, 17);
		String gitToken = WorkerHelper.readparticularRowCol(3, 17);
		String language = WorkerHelper.readparticularRowCol(4, 17);

		String applicationName = WorkerHelper.excelRead(0);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		String BuildMachineWorker = "WorkerAutomation_BuildmachineWorker";

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", BuildMachineWorker);
		ph.dyanamicStepAndPlugin("stage1", "gitscm");

		ph.addGitSCMInput("gitscm", gitURL, token);

		ph.dyanamicStepAndPlugin("stage1", "CodeQL-Analyze");

		ph.addCodeQLAnalyzepluginInput("CodeQL", projectName, projectPath, gitToken, language);

		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(BuildMachineWorker);
		ph.triggerPipelineUsingButtonWithBranch("dev");
		Thread.sleep(25000);
		driver.navigate().refresh();
		Thread.sleep(10000);
		ph.navigateToPipeline();
		Thread.sleep(25000);
		Thread.sleep(5000);
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

	}

	@Test(priority = 6)
	public void validate_CodeQL_Analyze_Plugin_provide_Invalid_Launguage() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();

		String gitURL = WorkerHelper.readparticularRowCol(1, 16);
		String token = WorkerHelper.readparticularRowCol(2, 16);
		String projectName = WorkerHelper.readparticularRowCol(1, 17);
		String projectPath = WorkerHelper.readparticularRowCol(2, 17);
		String gitToken = WorkerHelper.readparticularRowCol(3, 17);
		String language = WorkerHelper.readparticularRowCol(4, 17);

		String applicationName = WorkerHelper.excelRead(0);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		String BuildMachineWorker = "WorkerAutomation_BuildmachineWorker";

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", BuildMachineWorker);
		ph.dyanamicStepAndPlugin("stage1", "gitscm");

		ph.addGitSCMInput("gitscm", gitURL, token);

		ph.dyanamicStepAndPlugin("stage1", "CodeQL-Analyze");

		ph.addCodeQLAnalyzepluginInput("CodeQL", projectName, projectPath, gitToken, "invalidlanguage");

		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(BuildMachineWorker);
		ph.triggerPipelineUsingButtonWithBranch("dev");
		Thread.sleep(25000);
		driver.navigate().refresh();
		Thread.sleep(10000);
		ph.navigateToPipeline();
		Thread.sleep(25000);
		Thread.sleep(5000);
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
	}

	@Test(priority = 7)
	public void validate_CodeQL_Analyze_Plugin_If_Not_filed_mandatory_field_save_Button_Shouldnot_Enable()
			throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();

		String gitURL = WorkerHelper.readparticularRowCol(1, 16);
		String token = WorkerHelper.readparticularRowCol(2, 16);
		String projectName = WorkerHelper.readparticularRowCol(1, 17);
		String projectPath = WorkerHelper.readparticularRowCol(2, 17);
		String gitToken = WorkerHelper.readparticularRowCol(3, 17);
		String language = WorkerHelper.readparticularRowCol(4, 17);

		String applicationName = WorkerHelper.excelRead(0);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;

		String BuildMachineWorker = "WorkerAutomation_BuildmachineWorker";

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", BuildMachineWorker);
		ph.dyanamicStepAndPlugin("stage1", "gitscm");

		ph.addGitSCMInput("gitscm", gitURL, token);

		ph.dyanamicStepAndPlugin("stage1", "CodeQL-Analyze");

		ph.addCodeQLAnalyzepluginInput("CodeQL", projectName, projectPath, gitToken, language);
		ph.clickOnPlugin("CodeQL");

		String value = "Inputs for CODEQL-ANALYZE";
		boolean test = ph.verify_Plugin_Input_Fields(value, 2);
		assertTrue(test);

		boolean test1 = ph.verify_Plugin_Input_Fields(value, 3);
		assertTrue(test1);

		boolean test2 = ph.verify_Plugin_Input_Fields(value, 4);
		assertTrue(test2);

		boolean test3 = ph.verify_Plugin_Input_Fields(value, 5);
		assertTrue(test3);

		boolean test4 = ph.validateSaveButton(value, 6);
		assertFalse(test4);
	}
}
