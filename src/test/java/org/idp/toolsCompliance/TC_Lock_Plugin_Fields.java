package org.idp.toolsCompliance;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.infy.commonHelpers.PipelineHelper;
import org.infy.commonHelpers.WorkerHelper;
import org.infy.init.ExcelReader;
import org.infy.init.ExcelWriter;
import org.infy.init.ListenersTest;
import org.infy.init.LoadData;
import org.infy.reportHelper.ReportHelper;
import org.infy.uiPages.ApplicationView;
import org.infy.uiPages.CommonMethods;
import org.infy.uiPages.Parameters;
import org.infy.uiPages.PipelineView;
import org.infy.uiPages.Workflows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

@Listeners(ListenersTest.class)
public class TC_Lock_Plugin_Fields extends LoadData {
	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();
	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(TC_Lock_Plugin_Fields.class);

	@Test(enabled = true, priority = 1)
	public void To_validate_Lock_Plugin_Fields_After_Adding_LockFiled_For_All_Plugin_AddPlugin_IN_Pipeline_And_Check()
			throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ReportHelper reportH = PageFactory.initElements(driver, ReportHelper.class);
		ReportHelper rp = new ReportHelper(driver);
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		ExcelWriter write = new ExcelWriter();
		ExcelReader read = new ExcelReader();
		WorkerHelper wh = new WorkerHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();
		String workerName = wh.excelRead(1);
		String applicationName = WorkerHelper.excelRead(0);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));

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

		String portfolioName = WorkerHelper.excelRead(3);
		av.openPortfolioDashboardDetails(portfolioName);
		av.verifyAndDeleteTool();

		av.openPortfolioDashboardDetails(portfolioName);
		av.naviToTools_Compliance();
		Thread.sleep(3000);
		av.addTool("cmdexec");
		av.navToToolMandate();
		av.clickOnSaveMandate();
		av.navTolockPlugin();
		av.navToAddLockPlugin();
		av.addLockFiledDynamicProperties("*", "*");
		av.addLockFiledInputData("2", "new");
		av.addLockFiledEnabled("1");
		av.addLockFiledInputData("3", "java -version");
		av.addLockFiledEnabled("2");
		av.addLockFiledEnabled("3");
		av.SaveLockPlugin();
		av.clickOnSaveLockPlugin();

		driver.navigate().refresh();
		Thread.sleep(3000);
		av.naviToTools_Compliance();
		av.addTool("gitscm");
		av.navToToolMandate();
		av.clickOnSaveMandate();
		av.navTolockPlugin();
		av.navToAddLockPlugin();
		av.addLockFiledDynamicProperties("*", "*");
		av.addLockFiledEnabled("1");
		av.addLockFiledInputData("2", "https://infygithub.ad.infosys.com/thanusuya-r/Python_Project");
		av.addLockFiledEnabled("2");
		av.addLockFiledInputData("3", "satheshkumar");
		av.addLockFiledEnabled("3");
		av.addLockFiledInputData("4", "dkljkljsdklkdlsj");
		av.addLockFiledEnabled("4");
		av.addLockFiledInputData("5", "main");
		av.addLockFiledEnabled("5");
		av.addLockFiledInputData("6", "Python_Project");
		av.addLockFiledEnabled("6");
		av.addLockFiledInputData("7", "76778");
		av.addLockFiledEnabled("7");
		av.addLockFiledInputData("8", "new");
		av.addLockFiledEnabled("8");
		av.addLockFiledInputData("9", "Test");
		av.addLockFiledEnabled("9");
		av.SaveLockPlugin();
		av.clickOnSaveLockPlugin();

		driver.navigate().refresh();
		Thread.sleep(3000);
		av.naviToTools_Compliance();
		Thread.sleep(3000);
		av.addTool("sonarPlugin");
		av.navToToolMandate();
		av.clickOnSaveMandate();
		av.navTolockPlugin();
		av.navToAddLockPlugin();
		av.addLockFiledDynamicProperties("*", "*");
		av.addLockFiledInputData("2", "https://infygithub.ad.infosys.com/thanusuya-r/Python_Project");
		av.addLockFiledEnabled("1");
		av.addLockFiledInputData("3", "satheshkumar");
		av.addLockFiledEnabled("2");
		av.addLockFiledInputData("4", "dkljkljsdklkdlsj");
		av.addLockFiledEnabled("3");
		av.SaveLockPlugin();
		av.clickOnSaveLockPlugin();

		driver.navigate().refresh();
		Thread.sleep(3000);
		av.naviToTools_Compliance();
		Thread.sleep(3000);
		av.addTool("Fortify-SAST");
		av.navToToolMandate();
		av.clickOnSaveMandate();
		av.navTolockPlugin();
		av.navToAddLockPlugin();
		av.addLockFiledDynamicProperties("*", "*");
		av.addLockFiledInputData("2", "Python_Project");
		av.addLockFiledEnabled("1");
		av.addLockFiledInputData("3", "new");
		av.addLockFiledEnabled("2");
		av.addLockFiledEnabled("3");
		av.addLockFiledEnabled("4");
		av.addLockFiledInputData("4", "dkljkljsdklkdlsj");
		av.addLockFiledEnabled("5");
		av.addLockFiledInputData("5", "fsfsjklkjsfkjal");
		av.addLockFiledEnabled("6");
		av.addLockFiledInputData("6", "Python_Project");
		av.addLockFiledEnabled("7");
		av.addLockFiledInputData("7", "4.7.1");
		av.addLockFiledEnabled("8");
		av.addLockFiledInputData("8", "Test");
		av.addLockFiledEnabled("9");
		av.addLockFiledEnabled("10");
		av.SaveLockPlugin();
		av.clickOnSaveLockPlugin();
		Thread.sleep(8000);

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", BuildMachineWorker);

		ph.dyanamicStepAndPlugin("stage1", "gitscm");
		Boolean value1 = ph.addLockVerifycation("gitscm", 9);
		assertTrue(value1);

		ph.dyanamicStepAndPlugin("stage1", "sonarPlugin");
		Boolean value2 = ph.addLockVerifycation("sonarPlugin", 3);
		assertTrue(value2);

		ph.dyanamicStepAndPlugin("stage1", "Fortify-SAST");
		Boolean value3 = ph.addLockVerifycation("Fortify-SAST", 9);
		assertTrue(value3);

		ph.dyanamicStepAndPlugin("stage1", "cmdexec");
		Boolean value4 = ph.addLockVerifycation("cmdexec", 2);
		assertTrue(value4);
		driver.navigate().refresh();
		av.openPortfolioDashboardDetails(portfolioName);
		av.verifyAndDeleteTool();
//		av.naviToTools_Compliance();
//		av.deleteTool();
//
//		Thread.sleep(7000);
//		driver.navigate().refresh();
//		av.naviToTools_Compliance();
//		av.deleteTool();
//
//		Thread.sleep(7000);
//		driver.navigate().refresh();
//		av.naviToTools_Compliance();
//		av.deleteTool();
//
//		Thread.sleep(7000);
//		driver.navigate().refresh();
//		av.naviToTools_Compliance();
//		av.deleteTool();
//		Thread.sleep(2000);
	}

	@Test(enabled = true, priority = 2)
	public void To_validate_Lock_Plugin_Fields_AddPlugin_IN_Pipeline_And_Add_LockFiled_For_All_Plugin_Check_The_Pipeline()
			throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ReportHelper reportH = PageFactory.initElements(driver, ReportHelper.class);
		ReportHelper rp = new ReportHelper(driver);
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		ExcelWriter write = new ExcelWriter();
		ExcelReader read = new ExcelReader();
		WorkerHelper wh = new WorkerHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();
		String workerName = wh.excelRead(1);
		String applicationName = WorkerHelper.excelRead(0);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));

		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;

		String BuildMachineWorker = "WorkerAutomation_BuildmachineWorker";
		String portfolioName = WorkerHelper.excelRead(3);

		String gitRepo = WorkerHelper.readparticularRowCol(1, 9);
		String Token = WorkerHelper.readparticularRowCol(2, 9);
		String soapUIPath = WorkerHelper.readparticularRowCol(1, 10);

		av.openPortfolioDashboardDetails(portfolioName);
		av.verifyAndDeleteTool();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", BuildMachineWorker);

		ph.dyanamicStepAndPlugin("stage1", "gitscm");
		ph.addGitSCMInput("gitscm", gitRepo, Token);

		ph.dyanamicStepAndPlugin("stage1", "SonarQube");
		ph.addSonarQubeInput("SonarQube", "new", "sdjlkds", "QAuto", "sathesh/path", gitRepo, "Satheshkumar");

		ph.dyanamicStepAndPlugin("stage1", "Fortify-SAST");
		ph.addSASTPlugin("Fortify-SAST", "ui-notification-manager", "node_modules",
				"MTlhY2YxODItYjM4Yi00NzEyLWE4ZDYtZmY1ODExN2U5ZDky");

		ph.submitPipeline();

		av.openPortfolioDashboardDetails(portfolioName);
		av.naviToTools_Compliance();
		Thread.sleep(3000);
		av.addTool("gitscm");
		av.navTolockPlugin();
		av.navToAddLockPlugin();
		av.addLockFiledDynamicProperties("*", "*");
		av.addLockFiledEnabled("1");
		av.addLockFiledInputData("2", "https://infygithub.ad.infosys.com/thanusuya-r/Python_Project");
		av.addLockFiledEnabled("2");
		av.addLockFiledInputData("3", "satheshkumar");
		av.addLockFiledEnabled("3");
		av.addLockFiledInputData("4", "dkljkljsdklkdlsj");
		av.addLockFiledEnabled("4");
		av.addLockFiledInputData("5", "main");
		av.addLockFiledEnabled("5");
		av.addLockFiledInputData("6", "Python_Project");
		av.addLockFiledEnabled("6");
		av.addLockFiledInputData("7", "76778");
		av.addLockFiledEnabled("7");
		av.addLockFiledInputData("8", "new");
		av.addLockFiledEnabled("8");
		av.addLockFiledInputData("9", "Test");
		av.addLockFiledEnabled("9");
		av.SaveLockPlugin();
		av.clickOnSaveLockPlugin();

		driver.navigate().refresh();
		Thread.sleep(3000);
		av.naviToTools_Compliance();
		Thread.sleep(3000);
		av.addTool("SonarQube");
		av.navTolockPlugin();
		av.navToAddLockPlugin();
		av.addLockFiledDynamicProperties("*", "*");
		av.addLockFiledInputData("2", "NewTest");
		av.addLockFiledEnabled("1");
		av.addLockFiledInputData("3", "sklafdjfs");
		av.addLockFiledEnabled("2");
		av.addLockFiledInputData("4", "QAutoTestAutomation");
		av.addLockFiledEnabled("3");
		av.addLockFiledInputData("5", "new/sathesh");
		av.addLockFiledEnabled("4");
		av.addLockFiledInputData("6", "https://infosys.com");
		av.addLockFiledEnabled("5");
		av.addLockFiledInputData("7", "sfdalfsdkjl");
		av.addLockFiledEnabled("6");
		av.SaveLockPlugin();
		av.clickOnSaveLockPlugin();

		driver.navigate().refresh();
		Thread.sleep(3000);
		av.naviToTools_Compliance();
		Thread.sleep(3000);
		av.addTool("Fortify-SAST");
		av.navTolockPlugin();
		av.navToAddLockPlugin();
		av.addLockFiledDynamicProperties("*", "*");
		av.addLockFiledInputData("2", "Python_Project");
		av.addLockFiledEnabled("1");
		av.addLockFiledInputData("3", "new");
		av.addLockFiledEnabled("2");
		av.addLockFiledEnabled("3");
		av.addLockFiledEnabled("4");
		av.addLockFiledInputData("4", "dkljkljsdklkdlsj");
		av.addLockFiledEnabled("5");
		av.addLockFiledInputData("5", "fsfsjklkjsfkjal");
		av.addLockFiledEnabled("6");
		av.addLockFiledInputData("6", "Python_Project");
		av.addLockFiledEnabled("7");
		av.addLockFiledInputData("7", "4.7.1");
		av.addLockFiledEnabled("8");
		av.addLockFiledInputData("8", "Test");
		av.addLockFiledEnabled("9");
		av.addLockFiledEnabled("10");
		av.SaveLockPlugin();
		av.clickOnSaveLockPlugin();
		Thread.sleep(7000);
		ph.navigateToPipeline();
		ph.filterPipelineAndclickOnEdit(pipelineName);

		Boolean value10 = ph.addLockVerifycation("gitscm", 9);
		assertTrue(value10);

		Boolean value11 = ph.addLockVerifycation("SonarQube", 6);
		assertTrue(value11);

		Boolean value12 = ph.addLockVerifycation("Fortify-SAST", 9);
		assertTrue(value12);

		av.openPortfolioDashboardDetails(portfolioName);
		av.verifyAndDeleteTool();
//		Thread.sleep(7000);
//		driver.navigate().refresh();
//		av.naviToTools_Compliance();
//		av.deleteTool();
//		Thread.sleep(7000);
//		driver.navigate().refresh();
//		av.naviToTools_Compliance();
//		av.deleteTool();
//		Thread.sleep(7000);
//		driver.navigate().refresh();
//		av.naviToTools_Compliance();
//		av.deleteTool();
//		Thread.sleep(2000);
	}

	@Test(enabled = true, priority = 3)
	public void To_validate_Lock_Plugin_Fields_AddPlugin_IN_Particular_Pipeline_And_Add_LockFiled_For_All_Plugin_Check_The_Pipeline()
			throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ReportHelper reportH = PageFactory.initElements(driver, ReportHelper.class);
		ReportHelper rp = new ReportHelper(driver);
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		ExcelWriter write = new ExcelWriter();
		ExcelReader read = new ExcelReader();
		WorkerHelper wh = new WorkerHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();
		String workerName = wh.excelRead(1);
		String applicationName = WorkerHelper.excelRead(0);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));

		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		String pipelineName1 = "pip" + workerName;

		String BuildMachineWorker = "WorkerAutomation_BuildmachineWorker";

		String gitRepo = WorkerHelper.readparticularRowCol(1, 9);
		String Token = WorkerHelper.readparticularRowCol(2, 9);
		String soapUIPath = WorkerHelper.readparticularRowCol(1, 10);
		String portfolioName = WorkerHelper.excelRead(3);
		av.openPortfolioDashboardDetails(portfolioName);
		av.verifyAndDeleteTool();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", BuildMachineWorker);

		ph.dyanamicStepAndPlugin("stage1", "gitscm");
		ph.addGitSCMInput("gitscm", gitRepo, Token);
		Thread.sleep(2000);
		ph.dyanamicStepAndPlugin("stage1", "SonarQube");
		ph.addSonarQubeInput("SonarQube", "new", "sdjlkds", "QAuto", "sathesh/path", gitRepo, "Satheshkumar");
		Thread.sleep(2000);
		ph.dyanamicStepAndPlugin("stage1", "Fortify-SAST");
		ph.addSASTPlugin("Fortify-SAST", "ui-notification-manager", "node_modules",
				"MTlhY2YxODItYjM4Yi00NzEyLWE4ZDYtZmY1ODExN2U5ZDky");
		Thread.sleep(2000);
		ph.submitPipeline();

		av.openPortfolioDashboardDetails(portfolioName);
		av.naviToTools_Compliance();
		Thread.sleep(3000);
		av.addTool("gitscm");
		av.navTolockPlugin();
		av.navToAddLockPlugin();
		av.addLockFiledDynamicProperties("WorkerAutomation", pipelineName);
		av.addLockFiledEnabled("1");
		av.addLockFiledInputData("2", "https://infygithub.ad.infosys.com/thanusuya-r/Python_Project");
		av.addLockFiledEnabled("2");
		av.addLockFiledInputData("3", "satheshkumar");
		av.addLockFiledInputData("4", "dkljkljsdklkdlsj");
		av.addLockFiledEnabled("4");
		av.addLockFiledInputData("5", "main");
		av.addLockFiledEnabled("5");
		av.addLockFiledInputData("6", "Python_Project");
		av.addLockFiledEnabled("6");
		av.addLockFiledInputData("7", "76778");
		av.addLockFiledEnabled("7");
		av.addLockFiledInputData("8", "new");
		av.addLockFiledEnabled("8");
		av.addLockFiledInputData("9", "Test");
		av.addLockFiledEnabled("9");
		av.SaveLockPlugin();
		av.clickOnSaveLockPlugin();
		Thread.sleep(4000);

		driver.navigate().refresh();
		Thread.sleep(3000);
		av.naviToTools_Compliance();
		Thread.sleep(3000);
		av.addTool("SonarQube");
		av.navTolockPlugin();
		av.navToAddLockPlugin();
		av.addLockFiledDynamicProperties("WorkerAutomation", pipelineName);
		av.addLockFiledInputData("2", "NewTest");
		av.addLockFiledEnabled("1");
		av.addLockFiledInputData("3", "sklafdjfs");
		av.addLockFiledEnabled("2");
		av.addLockFiledInputData("4", "QAutoTestAutomation");
		av.addLockFiledEnabled("3");
		av.addLockFiledInputData("5", "new/sathesh");
		av.addLockFiledEnabled("4");
		av.addLockFiledInputData("6", "https://infosys.com");
		av.addLockFiledEnabled("5");
		av.addLockFiledInputData("7", "sfdalfsdkjl");
		av.addLockFiledEnabled("6");
		av.SaveLockPlugin();
		av.clickOnSaveLockPlugin();
		Thread.sleep(4000);

		driver.navigate().refresh();
		Thread.sleep(3000);
		av.naviToTools_Compliance();
		Thread.sleep(3000);
		av.addTool("Fortify-SAST");
		av.navTolockPlugin();
		av.navToAddLockPlugin();
		av.addLockFiledDynamicProperties("WorkerAutomation", pipelineName);
		av.addLockFiledInputData("2", "Python_Project");
		av.addLockFiledEnabled("1");
		av.addLockFiledInputData("3", "new");
		av.addLockFiledEnabled("2");
		av.addLockFiledEnabled("3");
		av.addLockFiledEnabled("4");
		av.addLockFiledInputData("4", "dkljkljsdklkdlsj");
		av.addLockFiledEnabled("5");
		av.addLockFiledInputData("5", "fsfsjklkjsfkjal");
		av.addLockFiledEnabled("6");
		av.addLockFiledInputData("6", "Python_Project");
		av.addLockFiledEnabled("7");
		av.addLockFiledInputData("7", "4.7.1");
		av.addLockFiledEnabled("8");
		av.addLockFiledInputData("8", "Test");
		av.addLockFiledEnabled("9");
		av.addLockFiledEnabled("10");
		av.SaveLockPlugin();
		av.clickOnSaveLockPlugin();
		Thread.sleep(7000);

		ph.navigateToPipeline();
		ph.filterPipelineAndclickOnEdit(pipelineName);

		Boolean value10 = ph.addLockVerifycationAndEdit("gitscm", 8, "newTester");
		assertTrue(value10);
		Thread.sleep(4000);

		Boolean value11 = ph.addLockVerifycation("SonarQube", 6);
		assertTrue(value11);
		Thread.sleep(2000);

		Boolean value12 = ph.addLockVerifycation("Fortify-SAST", 9);
		assertTrue(value12);
		Thread.sleep(2000);

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName1);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", BuildMachineWorker);
		Thread.sleep(2000);

		ph.dyanamicStepAndPlugin("stage1", "gitscm");
		ph.addGitSCMInput("gitscm", gitRepo, Token);
		Thread.sleep(2000);

		ph.dyanamicStepAndPlugin("stage1", "SonarQube");
		ph.addSonarQubeInput("SonarQube", "new", "sdjlkds", "QAuto", "sathesh/path", gitRepo, "Satheshkumar");
		Thread.sleep(2000);

		ph.dyanamicStepAndPlugin("stage1", "Fortify-SAST");
		ph.addSASTPlugin("Fortify-SAST", "ui-notification-manager", "node_modules",
				"MTlhY2YxODItYjM4Yi00NzEyLWE4ZDYtZmY1ODExN2U5ZDky");
		Thread.sleep(2000);
		ph.submitPipeline();
//
//		log.info("=============&&&&&&&&&&====================Submit");
//		ph.navigateToPipeline();
//		ph.filterPipelineAndclickOnEdit(pipelineName1);
//
//		Boolean value20 = ph.addLockVerifycation("gitscm", 9);
//		assertTrue(value20);
//		Thread.sleep(2000);
//		Boolean value21 = ph.addLockVerifycation("SonarQube", 6);
//		assertTrue(value21);
//		Thread.sleep(2000);
//		Boolean value22 = ph.addLockVerifycation("Fortify-SAST", 10);
//		assertTrue(value22);
//		log.info("=============&&&&&&&&&&==========FinishLine");
		Thread.sleep(2000);
		av.openPortfolioDashboardDetails(portfolioName);
		av.verifyAndDeleteTool();
//		driver.navigate().refresh();
//		av.naviToTools_Compliance();
//		av.deleteTool();
//		Thread.sleep(7000);
//		driver.navigate().refresh();
//		av.naviToTools_Compliance();
//		av.deleteTool();
//		Thread.sleep(7000);
//		driver.navigate().refresh();
//		av.naviToTools_Compliance();
//		av.deleteTool();
//		Thread.sleep(2000);
	}
}
