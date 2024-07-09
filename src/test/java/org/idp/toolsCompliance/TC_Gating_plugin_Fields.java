package org.idp.toolsCompliance;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

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
public class TC_Gating_plugin_Fields extends LoadData {
	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();

	@Test(enabled = true, priority = 1)
	public void To_validate_Gating_Plugin_Before_Adding_Pipeline_Create_Gating() throws Exception {
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

		String gitRepo = "https://github.com/Infosys-idp/ui-metrics-mf.git";
		String Token = "Z2hwX21EZGdWV3JnSm8wZE05YzZIc0pVVHV3VkhEaGpxejBMckFPSQ==";

		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		String pipelineName1 = "pip" + workerName;
		String pipelineName2 = "pipTest" + workerName;

		String BuildMachineWorker = "WorkerAutomation_BuildmachineWorker";

		String portfolioName = WorkerHelper.excelRead(3);
		av.openPortfolioDashboardDetails(portfolioName);
		av.naviToTools_Compliance();
		Thread.sleep(3000);
		av.addTool("SonarQube");
		av.navToGating();
		av.navToAddGating();
		av.addGatingFiledDynamicProperties("*", "*");
		av.addGatingData("blocker", "==", "0");
		av.addGatingData("major", "==", "0");
		av.addGatingData("minor", "==", "0");
		av.SaveLockPlugin();
		av.clickOnSave();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", BuildMachineWorker);

		ph.dyanamicStepAndPlugin("stage1", "gitscm");
		ph.addGitSCMInput("gitscm", gitRepo, Token);

		ph.dyanamicStepAndPlugin("stage1", "SonarQube");
		ph.addSonarQubeInput("SonarQube", "", "", "QLTY_QAUTO_idp-mf-metrics", "ui-metrics-mf/sonar-project.properties",
				"https://infysonar.ad.infosys.com", "ZmZhYjQ4OGY2YjBhMzU4ZGZmYzZjMjI0NWY5YTAxMTgxMTNjMWY4OA==");

		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(BuildMachineWorker);
		ph.triggerPipelineUsingButtonWithBranch("dev");
		driver.navigate().refresh();
		Thread.sleep(3000);
		ph.navigateToPipeline();
		Thread.sleep(5000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		String foldername = applicationName + "_" + pipelineName;
		ph.navLogsDetails(foldername);
		String result = ph.getLog("EXECUTION SUCCESS");
		boolean flag;
		if (result.contains("EXECUTION SUCCESS")) {
			flag = true;
		} else {
			flag = false;
		}
		assertTrue(flag);

		String logresult = ph.getLogResult();
		String value = ph.filterLog(logresult, "blocker");
		Thread.sleep(2000);
		Boolean value1 = ph.verifycationFlag(value, "0");
		assertTrue(value1);

//		av.openPortfolioDashboardDetails(portfolioName);
//		av.naviToTools_Compliance();
//		av.navToGating();
//		av.clickOnDELETE();
	}

	@Test(enabled = true, priority = 2)
	public void To_validate_Gating_Plugin_After_Adding_Pipeline_Create_Gating() throws Exception {
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

		String gitRepo = WorkerHelper.readparticularRowCol(1, 9);
		String Token = WorkerHelper.readparticularRowCol(2, 9);
		String soapUIPath = WorkerHelper.readparticularRowCol(1, 10);

		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		String pipelineName1 = "pip" + workerName;
		String pipelineName2 = "pipTest" + workerName;

		String BuildMachineWorker = "WorkerAutomation_BuildmachineWorker";

		String portfolioName = WorkerHelper.excelRead(3);

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", BuildMachineWorker);

		ph.dyanamicStepAndPlugin("stage1", "gitscm");
		ph.addGitSCMInput("gitscm", gitRepo, Token);

		ph.dyanamicStepAndPlugin("stage1", "SonarQube");
		ph.addSonarQubeInput("SonarQube", "new", "sdjlkds", "QAuto", "sathesh/path", gitRepo, "Satheshkumar");

		ph.submitPipeline();

		av.openPortfolioDashboardDetails(portfolioName);
		av.naviToTools_Compliance();
		Thread.sleep(3000);
		av.addTool("SonarQube");
		av.navToGating();
		av.navToAddGating();
		av.addGatingFiledDynamicProperties("*", "*");
		av.addGatingData("blocker", "==", "0");
		av.addGatingData("major", "!=", "0");
		av.addGatingData("minor", ">=", "2");
		av.SaveLockPlugin();
		av.clickOnSave();

		av.openPortfolioDashboardDetails(portfolioName);
		av.naviToTools_Compliance();
		av.navToGating();
		av.clickOnDELETE();
	}

	@Test(enabled = true, priority = 3)
	public void To_validate_Gating_Plugin_Add_Application_PipelineName_And_Create_Gating() throws Exception {
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

		String gitRepo = WorkerHelper.readparticularRowCol(1, 9);
		String Token = WorkerHelper.readparticularRowCol(2, 9);
		String soapUIPath = WorkerHelper.readparticularRowCol(1, 10);

		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		String pipelineName1 = "pip" + workerName;
		String pipelineName2 = "pipTest" + workerName;

		String BuildMachineWorker = "WorkerAutomation_BuildmachineWorker";

		String portfolioName = WorkerHelper.excelRead(3);

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", BuildMachineWorker);

		ph.dyanamicStepAndPlugin("stage1", "gitscm");
		ph.addGitSCMInput("gitscm", gitRepo, Token);

		ph.dyanamicStepAndPlugin("stage1", "SonarQube");
		ph.addSonarQubeInput("SonarQube", "new", "sdjlkds", "QAuto", "sathesh/path", gitRepo, "Satheshkumar");

		ph.submitPipeline();

		av.openPortfolioDashboardDetails(portfolioName);
		av.naviToTools_Compliance();
		Thread.sleep(3000);
		av.addTool("SonarQube");
		av.navToGating();
		av.navToAddGating();
		av.addGatingFiledDynamicProperties(workerName, pipelineName);
		av.addGatingData("blocker", "==", "0");
		av.addGatingData("major", "!=", "0");
		av.addGatingData("minor", ">=", "2");
		av.SaveLockPlugin();
		av.clickOnSave();

		// run it and check the gating

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName1);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", BuildMachineWorker);

		ph.dyanamicStepAndPlugin("stage1", "gitscm");
		ph.addGitSCMInput("gitscm", gitRepo, Token);

		ph.dyanamicStepAndPlugin("stage1", "SonarQube");
		ph.addSonarQubeInput("SonarQube", "new", "sdjlkds", "QAuto", "sathesh/path", gitRepo, "Satheshkumar");

		ph.submitPipeline();

		// run it and check gating

		av.openPortfolioDashboardDetails(portfolioName);
		av.naviToTools_Compliance();
		av.navToGating();
		av.clickOnDELETE();
	}

	@Test(enabled = true, priority = 4)
	public void To_validate_Gating_Plugin_Create_Gating_And_Edit_Delete_Gating() throws Exception {
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

		String gitRepo = WorkerHelper.readparticularRowCol(1, 9);
		String Token = WorkerHelper.readparticularRowCol(2, 9);
		String soapUIPath = WorkerHelper.readparticularRowCol(1, 10);

		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		String pipelineName1 = "pip" + workerName;
		String pipelineName2 = "pipTest" + workerName;

		String BuildMachineWorker = "WorkerAutomation_BuildmachineWorker";

		String portfolioName = WorkerHelper.excelRead(3);

		av.openPortfolioDashboardDetails(portfolioName);
		av.naviToTools_Compliance();
		Thread.sleep(3000);
		av.addTool("SonarQube");
		av.navToGating();
		av.navToAddGating();
		av.addGatingFiledDynamicProperties(workerName, pipelineName);
		av.addGatingData("blocker", "==", "0");
		av.addGatingData("major", "!=", "0");
		av.addGatingData("minor", ">=", "2");
		av.SaveLockPlugin();
		av.clickOnSave();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", BuildMachineWorker);

		ph.dyanamicStepAndPlugin("stage1", "gitscm");
		ph.addGitSCMInput("gitscm", gitRepo, Token);

		ph.dyanamicStepAndPlugin("stage1", "SonarQube");
		ph.addSonarQubeInput("SonarQube", "new", "sdjlkds", "QAuto", "sathesh/path", gitRepo, "Satheshkumar");

		ph.submitPipeline();

		// run it and check the gating

		av.openPortfolioDashboardDetails(portfolioName);
		av.naviToTools_Compliance();
		Thread.sleep(3000);
		av.addTool("SonarQube");
		av.navToGating();
		av.clickOnEdit();
		av.deleteParticularGating("blocker");
		av.SaveLockPlugin();
		av.clickOnSave();

		ph.navigateToPipeline();
		ph.filterPipelineAndclickOnEdit(pipelineName);
		ph.workerSelectionInPipelineTrigger(WorkerNames);
		ph.triggerPipelineUsingButton();

		// run it and check gating

		av.openPortfolioDashboardDetails(portfolioName);
		av.naviToTools_Compliance();
		av.navToGating();
		av.clickOnDELETE();
	}
}
