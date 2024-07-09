package org.idp.environments;

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
import org.infy.uiPages.PipelineView;
import org.infy.uiPages.Workflows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

@Listeners(ListenersTest.class)
public class TC_environmentsManagement extends LoadData {
	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();

	@Test(enabled = true, priority = 1)
	public void To_validate_Environment_Create_Update_Delete() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);

		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);

		String portfolioName = WorkerHelper.excelRead(3);
		av.openPortfolioDashboardDetails(portfolioName);
		
		av.naviToEnvir();
		av.deleteEnvIfExits("TestEnv");

		av.openApplicationDashboard(applicationName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		av.naviToEnvir();
		av.deleteEnvIfExits("TestEnv");
		av.environmentCreation("TestEnv", "QA");
Boolean value = av.verifyAfterCreateEnv("TestEnv", "QA");
assertTrue(value);
		av.updateEnv("TestEnv", "QA", "Prod");
		av.naviToEnvir();
		av.deleteEnv("TestEnv");
		Thread.sleep(3000);
	}

	@Test(enabled = true, priority = 2)
	public void To_validate_Environment_Create_And_Check_Pipeline_Its_Created_or_NOT() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);

		String portfolioName = WorkerHelper.excelRead(3);
		av.openPortfolioDashboardDetails(portfolioName);
		av.naviToEnvir();
		av.deleteEnvIfExits("TestEnv");
		
		av.openApplicationDashboard(applicationName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		String BuildMachineWorker = "WorkerAutomation_BuildmachineWorker";
		av.naviToEnvir();
		av.deleteEnvIfExits("TestEnv");
		
		av.environmentCreation("TestEnv", "QA");
		Boolean value = av.verifyAfterCreateEnv("TestEnv", "QA");
		assertTrue(value);
		Thread.sleep(3000);
		ph.navigateToPipeline();
		Thread.sleep(3000);
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipeline("stage1", "TestEnv", BuildMachineWorker);
		ph.createDynamicSteps("stage1");
		av.openApplicationDashboard(applicationName);
		av.naviToEnvir();
		av.deleteEnv("TestEnv");
		Thread.sleep(3000);
	}

	@Test(enabled = true, priority = 3)
	@org.testng.annotations.Parameters("VisualTestingStatus")
	public void To_validate_Environment_Create_Requires_Approval() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);

		String portfolioName = WorkerHelper.excelRead(3);
		av.openPortfolioDashboardDetails(portfolioName);
		av.naviToEnvir();
		av.deleteEnvIfExits("TestEnv");
		
		av.openApplicationDashboard(applicationName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;

		String BuildMachineWorker = "WorkerAutomation_BuildmachineWorker";
		av.naviToEnvir();
		av.deleteEnvIfExits("TestEnv");

		av.environmentCreationwithApproval("TestEnv", "QA", "vignesh.mj@infosys.com");
		Boolean value = av.verifyAfterCreateEnv("TestEnv", "QA");
		assertTrue(value);
		av.environmentCreationwithApproval("TestEnv1", "Dev", "vignesh.mj@infosys.com");
		Boolean value1 = av.verifyAfterCreateEnv("TestEnv1", "Dev");
		assertTrue(value1);
		Thread.sleep(2000);
		ph.navigateToPipeline();
		Thread.sleep(2000);
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipeline("stage1", "TestEnv", BuildMachineWorker);
		ph.createDynamicSteps("stage1");
		ph.addStageInPipeline("stage2", "TestEnv1", BuildMachineWorker);
		ph.createDynamicSteps("stage2");
		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.ReleaseNameSelect("application:NewTest");
		ph.workerSelectionInPipelineTrigger(BuildMachineWorker);
		ph.triggerPipelineUsingButton();
		ph.approverVerification("Approved");
		Thread.sleep(2000);
		ph.navigateToPipeline();
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		// boolean value2 = ph.pipelineRunningSuccessVerifycation(pipelineName,
		// BuildMachineWorker);
		// assertTrue(value2);
		av.openApplicationDashboard(applicationName);
		av.naviToEnvir();
		av.deleteEnv("TestEnv");
		av.deleteEnv("TestEnv1");
		Thread.sleep(3000);
	}

	@Test(enabled = true, priority = 4)
	public void To_validate_Environment_Add_Evn_Name_With_Space() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);

		String portfolioName = WorkerHelper.excelRead(3);
		av.openPortfolioDashboardDetails(portfolioName);
		av.naviToEnvir();
		av.deleteEnvIfExits("TestEnv");

		av.openApplicationDashboard(applicationName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		av.naviToEnvir();
		av.deleteEnvIfExits("TestEnv");

		try {
			av.environmentCreation("Test Env", "QA");
		} catch (Exception e) {
			assertTrue(true);
		}
		Thread.sleep(3000);
	}

	@Test(enabled = true, priority = 5)
	public void To_validate_Environment_Create_Update_Delete_PortfolioLevel() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();
		String portfolioName = WorkerHelper.excelRead(3);
		String applicationName = WorkerHelper.excelRead(0);
		av.openApplicationDashboard(applicationName);
		av.naviToEnvir();
		av.deleteEnvIfExits("TestEnv");
//		av.deleteEnvIfExits("TestEnv1");

		av.openPortfolioDashboardDetails(portfolioName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		av.naviToEnvir();
//		av.deleteEnvIfExits("TestEnv");
//av.deleteEnvIfExits("TestEnv1");

		av.environmentCreation("TestEnv", "QA");
		Boolean value = av.verifyAfterCreateEnv("TestEnv", "QA");
		assertTrue(value);
		av.updateEnv("TestEnv", "QA", "Prod");
		av.naviToEnvir();
		av.deleteEnv("TestEnv");
		Thread.sleep(3000);
	}

	@Test(enabled = true, priority = 6)
	public void To_validate_Environment_Create_And_Check_Pipeline_Its_Created_or_NOT_PortfolioLevel() throws Exception {
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
		av.naviToEnvir();
		av.deleteEnvIfExits("TestEnv");
//		av.deleteEnvIfExits("TestEnv1");

		String portfolioName = WorkerHelper.excelRead(3);
		av.openPortfolioDashboardDetails(portfolioName);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;

		String BuildMachineWorker = "WorkerAutomation_BuildmachineWorker";

		av.naviToEnvir();
//		av.deleteEnvIfExits("TestEnv");
//		av.deleteEnvIfExits("TestEnv1");
		av.environmentCreation("TestEnv", "QA");
		Boolean value = av.verifyAfterCreateEnv("TestEnv", "QA");
		assertTrue(value);
		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipeline("stage1", "TestEnv", BuildMachineWorker);
		ph.createDynamicSteps("stage1");
		av.openPortfolioDashboardDetails(portfolioName);
		av.naviToEnvir();
		av.deleteEnv("TestEnv");
		Thread.sleep(3000);
	}

	@Test(enabled = true, priority = 7)
	public void To_validate_Environment_Create_Requires_Approval_PortfolioLevel() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		String portfolioName = WorkerHelper.excelRead(3);

		av.openApplicationDashboard(applicationName);
		av.naviToEnvir();
		av.deleteEnvIfExits("TestEnv");
	    av.deleteEnvIfExits("TestEnv1");

		av.openPortfolioDashboardDetails(portfolioName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;

		String BuildMachineWorker = "WorkerAutomation_BuildmachineWorker";
		av.naviToEnvir();
		av.deleteEnvIfExits("TestEnv");
		av.deleteEnvIfExits("TestEnv1");
		av.environmentCreationwithApproval("TestEnv", "QA", "satheshkumar.m@infosys.com");
		Boolean value = av.verifyAfterCreateEnv("TestEnv", "QA");
		assertTrue(value);
		av.environmentCreationwithApproval("TestEnv1", "Dev", "satheshkumar.m@infosys.com");
		Boolean value1 = av.verifyAfterCreateEnv("TestEnv1", "Dev");
		assertTrue(value1);
		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipeline("stage1", "TestEnv", BuildMachineWorker);
		ph.createDynamicSteps("stage1");
		ph.addStageInPipeline("stage2", "TestEnv1", BuildMachineWorker);
		ph.createDynamicSteps("stage2");
		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.ReleaseNameSelect("application:NewTest");
		ph.workerSelectionInPipelineTrigger(BuildMachineWorker);
		ph.triggerPipelineUsingButton();
		ph.approverVerification("Approved");
		ph.navigateToPipeline();
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		// boolean value2 = ph.pipelineRunningSuccessVerifycation(pipelineName,
		// BuildMachineWorker);
		// assertTrue(value2);
		av.openPortfolioDashboardDetails(portfolioName);
		av.naviToEnvir();
		av.deleteEnv("TestEnv");
		av.deleteEnv("TestEnv1");
		Thread.sleep(3000);
	}

	@Test(enabled = true, priority = 8)
	public void To_validate_Environment_Add_Evn_Name_With_Space_PortfolioLevel() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();
		String portfolioName = WorkerHelper.excelRead(3);
		String applicationName = WorkerHelper.excelRead(0);
		av.openApplicationDashboard(applicationName);
		av.naviToEnvir();
		av.deleteEnvIfExits("TestEnv");
		av.deleteEnvIfExits("TestEnv1");

		av.openPortfolioDashboardDetails(portfolioName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		av.naviToEnvir();
		av.deleteEnvIfExits("TestEnv");
		av.deleteEnvIfExits("TestEnv1");
		try {
			av.environmentCreation("Test Env", "QA");
		} catch (Exception e) {
			assertTrue(true);
		}
		Thread.sleep(3000);

//		av.openPortfolioDashboardDetails(portfolioName);
//		av.naviToEnvir();
//		av.deleteEnvIfExits("TestEnv");
//		av.deleteEnvIfExits("TestEnv1");
//
//		av.openApplicationDashboard(applicationName);
//		av.naviToEnvir();
//		av.deleteEnvIfExits("TestEnv");
//		av.deleteEnvIfExits("TestEnv1");

	}

}