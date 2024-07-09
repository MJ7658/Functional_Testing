package org.idp.platformToolIntegration;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.infy.commonHelpers.PipelineHelper;
import org.infy.commonHelpers.ToolsIntegrationHelper;
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
public class TC_Tools_Integration extends LoadData {

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();

	@Test(enabled = true, priority = 1)
	public void To_Validate_platform_level_Toolintegration_WEBHOOK_BITBUCKET() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		ToolsIntegrationHelper tIH = new ToolsIntegrationHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		av.openPlatformConfiguration();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String updatedWorkerName = "newWorker123";

		String pluginName = "WEBHOOK_BITBUCKETV7";
		String LowerCasepluginName = pluginName.toLowerCase();
		Boolean value = av.addPlugins(LowerCasepluginName, workerName, "Test");
		assertTrue(value);
	}

	@Test(enabled = true, priority = 2)
	public void To_Validate_platform_level_Toolintegration_Edit_WEBHOOK_BITBUCKET_() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		ToolsIntegrationHelper tIH = new ToolsIntegrationHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		av.openPlatformConfiguration();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String updatedWorkerName = "newWorker123";

		String pluginName = "WEBHOOK_BITBUCKETV7";
		String LowerCasepluginName = pluginName.toLowerCase();
		boolean value = av.editPlugins(LowerCasepluginName, updatedWorkerName);
		assertTrue(value);
	}

	@Test(enabled = true, priority = 3)
	public void To_Validate_platform_level_Toolintegration_Delete_WEBHOOK_BITBUCKET_() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		ToolsIntegrationHelper tIH = new ToolsIntegrationHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		av.openPlatformConfiguration();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String updatedWorkerName = "newWorker123";

		String pluginName = "WEBHOOK_BITBUCKETV7";
		String LowerCasepluginName = pluginName.toLowerCase();
		boolean value = av.delectPlugin(LowerCasepluginName);
		assertTrue(value);
	}

	@Test(enabled = true, priority = 4)
	public void To_Validate_platform_level_Toolintegration_environmentVar_IMP() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		ToolsIntegrationHelper tIH = new ToolsIntegrationHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		av.openPlatformConfiguration();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String updatedWorkerName = "newWorker123";

		String pluginName = "environmentVar_IMP";
		String upperCasepluginName = pluginName.toUpperCase();
		String LowerCasepluginName = pluginName.toLowerCase();

		av.addPlugins(pluginName);
		Thread.sleep(5000);
		av.inputDataforEnvironmentVar_IDP(upperCasepluginName, "https://agtest.ad.infosys.com/dashboard/v1",
				"https://agtest.ad.infosys.com/portfoliosvc", "https://idpapp.ad.infosys.com/registry/v1/qa",
				"https://agtest.ad.infosys.com", "xyz", "xyz", "xyz", "xyz", "idp11", "URL",
				"https://liveengineering-dev.ad.infosys.com");
	}

	@Test(enabled = true, priority = 5)
	public void To_Validate_platform_level_Toolintegration_EncrptionPlugin() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		ToolsIntegrationHelper tIH = new ToolsIntegrationHelper(driver);
		WorkerHelper.workerExcelWriter();

		av.openPlatformConfiguration();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String updatedWorkerName = "newWorker123";

		String pluginName = "encryption";
		String upperCasepluginName = pluginName.toUpperCase();
		av.addPlugins(pluginName);
		Thread.sleep(5000);
		av.inputDataforEncryption(upperCasepluginName, "WorkerAutomation123", "dfsafdfdsa");
		String gitURL = WorkerHelper.readparticularRowCol(1, 16);
		String token = WorkerHelper.readparticularRowCol(2, 16);
		String projectName = WorkerHelper.readparticularRowCol(1, 17);
		String projectPath = WorkerHelper.readparticularRowCol(2, 17);
		String gitToken = WorkerHelper.readparticularRowCol(3, 17);
		String language = WorkerHelper.readparticularRowCol(4, 17);

		String applicationName = WorkerHelper.excelRead(0);

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

		ph.submitPipeline();
		Thread.sleep(8000);
		driver.navigate().refresh();
		Thread.sleep(10000);
		ph.navigateToPipeline();
		Thread.sleep(5000);
		ph.filterPipelineAndclickOnEdit(pipelineName);
		Boolean value = ph.openPipelinePluginVerifyEncrpt("gitscm");
		assertTrue(value);
	}

	@Test(enabled = true, priority = 6)
	public void To_Validate_platform_level_Toolintegration_Encrption_Portfolio_Variable() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		ToolsIntegrationHelper tIH = new ToolsIntegrationHelper(driver);
		WorkerHelper.workerExcelWriter();

		String applicationName = WorkerHelper.excelRead(0);

		String portfolioName = WorkerHelper.excelRead(3);
		av.openPortfolioDashboardDetails(portfolioName);
		av.naviToVariableManagement();
		av.variableCreationWithSecure("toolEncrptTest", "Test", "QA", "NewTest");
		Boolean value = av.variableVerifySecure("toolEncrptTest");
		assertTrue(value);
		av.cancelVariablePopup();
		av.deleteVariable("toolEncrptTest");
//		av.deleteEnvIfExits("TestEnv1");
	}

	@Test(enabled = true, priority = 7)
	public void To_Validate_platform_level_Toolintegration_Encrption_Portfolio_Application_Variable() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		ToolsIntegrationHelper tIH = new ToolsIntegrationHelper(driver);
		WorkerHelper.workerExcelWriter();

		String applicationName = WorkerHelper.excelRead(0);

		String portfolioName = WorkerHelper.excelRead(3);
		av.openApplicationDashboard(applicationName);
		av.naviToVariableManagement();
		av.variableCreationWithSecure("toolEncrptTest1", "Test", "QA", "NewTest");
		Boolean value = av.variableVerifySecure("toolEncrptTest1");
		assertTrue(value);
		av.cancelVariablePopup();
		av.deleteVariable("toolEncrptTest1");
//		av.deleteEnvIfExits("TestEnv1");
	}

	@Test(enabled = true, priority = 8)
	public void Validate_Pipeline_parameter_Secure_RadioButton_Encryption() throws Exception {
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

		String variableName = "Username1";
		String Defaultname = "Test";
		String Evnname = "QA";
		String EnvValue = "1234";

		ph.navToParametersAndClickONAdd();
		ph.paramterVariableNameCreation(variableName);
		ph.paramterDefaultNameCreation(Defaultname);
		ph.parameterEnvCreation(Evnname, EnvValue);
		ph.clickOnSecureButton();
		ph.parameterSaveButtonClick();

		ph.editParameter(variableName);
		Boolean value = ph.verifyPipelineParameter();
		assertTrue(value);
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		av.openApplicationDashboard(applicationName);
		wh.deleteWorker(WorkerNames);
	}

	@Test(enabled = true, priority = 9)
	public void To_Validate_platform_level_Toolintegration_DecryptionPlugin() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		ToolsIntegrationHelper tIH = new ToolsIntegrationHelper(driver);
		WorkerHelper.workerExcelWriter();
		av.openPlatformConfiguration();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));

		String updatedWorkerName = "newWorker123";

		String pluginName = "decryption";
		String upperCasepluginName = pluginName.toUpperCase();
		av.addPlugins(pluginName);
		Thread.sleep(5000);
		av.inputDataforEncryption(upperCasepluginName, "Nagendra_APP_nagendra_APP_worker", "dfsafdfdsa");

		String gitURL = WorkerHelper.readparticularRowCol(1, 16);
		String token = WorkerHelper.readparticularRowCol(2, 16);
		String projectName = WorkerHelper.readparticularRowCol(1, 17);
		String projectPath = WorkerHelper.readparticularRowCol(2, 17);
		String gitToken = WorkerHelper.readparticularRowCol(3, 17);
		String language = WorkerHelper.readparticularRowCol(4, 17);

		String applicationName = WorkerHelper.excelRead(0);

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

		ph.addGitSCMInput("gitscm", "https://github.com/satheshkumar-m_infosys/EnableSessions.git",
				"Z2hwX3dja0lGSmNSb05QWDRMa3R1RUxtVkhZMGZiWHJQVzRJZXRFSw==");

		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(BuildMachineWorker);
		ph.triggerPipelineUsingButtonWithBranch("main");
		Thread.sleep(25000);
		driver.navigate().refresh();
		Thread.sleep(10000);
		ph.navigateToPipeline();
		Thread.sleep(25000);
		Thread.sleep(5000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);

		String foldername = applicationName + "_" + pipelineName;
		ph.navLogsDetails(foldername);
		String result = ph.getLog("Cloned Code Successfully");
		boolean flag;
		if (result.contains("Cloned Code Successfully")) {
			flag = true;
		} else {
			flag = false;
		}
		assertTrue(flag);
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
	}

	@Test(enabled = true, priority = 10)
	public void To_Validate_platform_level_Toolintegration_Armorcode_sync_reportPlugin() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		ToolsIntegrationHelper tIH = new ToolsIntegrationHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		av.openPlatformConfiguration();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String updatedWorkerName = "newWorker123";

		String pluginName = "armorcode_sync_report";
		String upperCasepluginName = pluginName.toUpperCase();
		av.addPlugins(pluginName);
		Thread.sleep(5000);
		av.inputDatafor_armorcode_sync_report(upperCasepluginName, "WorkerAutomation123", "jfrog_xray", "dfsafdfdsa",
				"https://app.armorcode.com", "gdfsdfgs", "fdgfdf", "ETA=ETA_Konnect");
	}
}
