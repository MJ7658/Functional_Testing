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

public class TC_k8s_CustomDeploy_plugin extends LoadData {

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();

	@Test(priority = 1)
	public void validate_k8s_CustomDeploy_Plugin() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);

		String gitURL = WorkerHelper.readparticularRowCol(1, 19);
		String token = WorkerHelper.readparticularRowCol(2, 19);
		String kubeConfigPath = WorkerHelper.readparticularRowCol(1, 20);
		String kubeConfigfileName = WorkerHelper.readparticularRowCol(2, 20);
		String YMLFile = WorkerHelper.readparticularRowCol(3, 20);
		String numberspace = WorkerHelper.readparticularRowCol(4, 20);
		String Timeout = WorkerHelper.readparticularRowCol(5, 20);

		WorkerHelper.workerExcelWriter();
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

		ph.dyanamicStepAndPlugin("stage1", "k8s_CustomDeploy");
		ph.addk8s_CustomDeploypluginInput("k8s_CustomDeploy", kubeConfigPath, kubeConfigfileName, YMLFile, numberspace,
				Timeout);

		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(BuildMachineWorker);
		ph.triggerPipelineUsingButtonWithBranch("main");
		driver.navigate().refresh();
		Thread.sleep(15000);
		ph.navigateToPipeline();
		Thread.sleep(15000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);

		String foldername = applicationName + "_" + pipelineName;
		ph.navLogsDetails(foldername);
		String result = ph.getLog("INFO deployment");
		boolean flag;
		if (result.contains("INFO deployment")) {
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
	public void validate_k8s_CustomDeploy_Plugin_mandatory_field() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);

		String gitURL = WorkerHelper.readparticularRowCol(1, 19);
		String token = WorkerHelper.readparticularRowCol(2, 19);
		String kubeConfigPath = WorkerHelper.readparticularRowCol(1, 20);
		String kubeConfigfileName = WorkerHelper.readparticularRowCol(2, 20);
		String YMLFile = WorkerHelper.readparticularRowCol(3, 20);
		String numberspace = WorkerHelper.readparticularRowCol(4, 20);
		String Timeout = WorkerHelper.readparticularRowCol(5, 20);

		WorkerHelper.workerExcelWriter();
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

		ph.dyanamicStepAndPlugin("stage1", "k8s_CustomDeploy");
		ph.addk8s_CustomDeploypluginInput("k8s_CustomDeploy", kubeConfigPath, kubeConfigfileName, YMLFile, numberspace,
				Timeout);

		ph.clickOnPlugin("k8s_CustomDeploy");

		String value = "Inputs for K8S_CUSTOMDEPLOY";
		boolean test = ph.verify_Plugin_Input_Fields(value, 2);
		assertTrue(test);

		boolean tests = ph.verify_Plugin_Input_Fields(value, 3);
		assertTrue(tests);

		boolean test1 = ph.verify_Plugin_Input_Fields(value, 4);
		assertTrue(test1);

		ph.scrollToElement(value, 5);

		boolean test2 = ph.verify_Plugin_Input_Fields(value, 5);
		assertTrue(test2);
	}

	@Test(priority = 3)
	public void validate_k8s_CustomDeploy_Plugin_Invalid_kubeConfigPathn() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);

		String gitURL = WorkerHelper.readparticularRowCol(1, 19);
		String token = WorkerHelper.readparticularRowCol(2, 19);
		String kubeConfigPath = WorkerHelper.readparticularRowCol(1, 20);
		String kubeConfigfileName = WorkerHelper.readparticularRowCol(2, 20);
		String YMLFile = WorkerHelper.readparticularRowCol(3, 20);
		String numberspace = WorkerHelper.readparticularRowCol(4, 20);
		String Timeout = WorkerHelper.readparticularRowCol(5, 20);

		WorkerHelper.workerExcelWriter();
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

		ph.dyanamicStepAndPlugin("stage1", "k8s_CustomDeploy");
		ph.addk8s_CustomDeploypluginInput("k8s_CustomDeploy", "kubeConfigPath", kubeConfigfileName, YMLFile,
				numberspace, Timeout);

		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(BuildMachineWorker);
		ph.triggerPipelineUsingButtonWithBranch("main");
		driver.navigate().refresh();
		Thread.sleep(15000);
		ph.navigateToPipeline();
		Thread.sleep(15000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);

		String foldername = applicationName + "_" + pipelineName;
		ph.navLogsDetails(foldername);
		String result = ph.getLog("ERROR Error occurred in Pre-requisites Check stage");
		boolean flag;
		if (result.contains("ERROR Error occurred in Pre-requisites Check stage")) {
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
	public void validate_k8s_CustomDeploy_Plugin__Invalid_kubeConfigfileName() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);

		String gitURL = WorkerHelper.readparticularRowCol(1, 19);
		String token = WorkerHelper.readparticularRowCol(2, 19);
		String kubeConfigPath = WorkerHelper.readparticularRowCol(1, 20);
		String kubeConfigfileName = WorkerHelper.readparticularRowCol(2, 20);
		String YMLFile = WorkerHelper.readparticularRowCol(3, 20);
		String numberspace = WorkerHelper.readparticularRowCol(4, 20);
		String Timeout = WorkerHelper.readparticularRowCol(5, 20);

		WorkerHelper.workerExcelWriter();
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

		ph.dyanamicStepAndPlugin("stage1", "k8s_CustomDeploy");
		ph.addk8s_CustomDeploypluginInput("k8s_CustomDeploy", kubeConfigPath, "kubeConfigfileName", YMLFile,
				numberspace, Timeout);

		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(BuildMachineWorker);
		ph.triggerPipelineUsingButtonWithBranch("main");
		driver.navigate().refresh();
		Thread.sleep(15000);
		ph.navigateToPipeline();
		Thread.sleep(15000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);

		String foldername = applicationName + "_" + pipelineName;
		ph.navLogsDetails(foldername);
		String result = ph.getLog("ERROR Error occurred in Pre-requisites Check stage");
		boolean flag;
		if (result.contains("ERROR Error occurred in Pre-requisites Check stage")) {
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
	public void validate_k8s_CustomDeploy_Plugin__Invalid_YMLFile() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);

		String gitURL = WorkerHelper.readparticularRowCol(1, 19);
		String token = WorkerHelper.readparticularRowCol(2, 19);
		String kubeConfigPath = WorkerHelper.readparticularRowCol(1, 20);
		String kubeConfigfileName = WorkerHelper.readparticularRowCol(2, 20);
		String YMLFile = WorkerHelper.readparticularRowCol(3, 20);
		String numberspace = WorkerHelper.readparticularRowCol(4, 20);
		String Timeout = WorkerHelper.readparticularRowCol(5, 20);

		WorkerHelper.workerExcelWriter();
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

		ph.dyanamicStepAndPlugin("stage1", "k8s_CustomDeploy");
		ph.addk8s_CustomDeploypluginInput("k8s_CustomDeploy", kubeConfigPath, kubeConfigfileName, "YMLFile",
				numberspace, Timeout);

		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(BuildMachineWorker);
		ph.triggerPipelineUsingButtonWithBranch("main");
		driver.navigate().refresh();
		Thread.sleep(15000);
		ph.navigateToPipeline();
		Thread.sleep(15000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);

		String foldername = applicationName + "_" + pipelineName;
		ph.navLogsDetails(foldername);
		String result = ph.getLog("ERROR Error while replacing image tag with trigger number");
		boolean flag;
		if (result.contains("ERROR Error while replacing image tag with trigger number")) {
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
	public void validate_k8s_CustomDeploy_Plugin__Invalid_numberspace() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);

		String gitURL = WorkerHelper.readparticularRowCol(1, 19);
		String token = WorkerHelper.readparticularRowCol(2, 19);
		String kubeConfigPath = WorkerHelper.readparticularRowCol(1, 20);
		String kubeConfigfileName = WorkerHelper.readparticularRowCol(2, 20);
		String YMLFile = WorkerHelper.readparticularRowCol(3, 20);
		String numberspace = WorkerHelper.readparticularRowCol(4, 20);
		String Timeout = WorkerHelper.readparticularRowCol(5, 20);

		WorkerHelper.workerExcelWriter();
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

		ph.dyanamicStepAndPlugin("stage1", "k8s_CustomDeploy");
		ph.addk8s_CustomDeploypluginInput("k8s_CustomDeploy", kubeConfigPath, kubeConfigfileName, YMLFile,
				"numberspace", Timeout);

		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(BuildMachineWorker);
		ph.triggerPipelineUsingButtonWithBranch("main");
		driver.navigate().refresh();
		Thread.sleep(15000);
		ph.navigateToPipeline();
		Thread.sleep(15000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);

		String foldername = applicationName + "_" + pipelineName;
		ph.navLogsDetails(foldername);
		String result = ph.getLog("WARN Error while creating namespace");
		boolean flag;
		if (result.contains("WARN Error while creating namespace")) {
			flag = true;
		} else {
			flag = false;
		}
		assertTrue(flag);
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
	}
}
