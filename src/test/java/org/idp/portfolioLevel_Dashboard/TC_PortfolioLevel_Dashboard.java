package org.idp.portfolioLevel_Dashboard;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.ArrayList;

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
public class TC_PortfolioLevel_Dashboard extends LoadData {

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();

	@Test(enabled = true, priority = 1)
	public void To_Verify_User_Able_to_see_Portfolio_Level_Dashboard_Without_Having_Pipeline_Access() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();

		String applicationName = WorkerHelper.excelRead(0);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String pipelineName = "PipelineLevel_Trends";
		String BuildMachineWorker = "WorkerAutomation_BuildmachineWorker";
		String portfolioName = WorkerHelper.excelRead(3);

		Thread.sleep(15000);
		driver.navigate().refresh();
		Thread.sleep(10000);

		av.openPortfolioDashboardDetails1(portfolioName);
		ph.navigateToPlatformConfig_Graph_Click("Fortify SAST Vulnerabilities");
		ph.navigateToPlatformConfig_Graph_Click("Testing_Application");
		ph.navigateToPlatformConfig_Graph_Click("SAST");
		ph.switchToWindow();
		Boolean value = ph.navigateToPlatformVerifycation();
		assertTrue(value);
		ph.navToParentTab();
	}

	@Test(enabled = true, priority = 2)
	public void To_Verify_User_Able_to_see_DashboardLevel_FortifySAST_Vulnerabilities_Trends_Values() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();

		String applicationName = WorkerHelper.excelRead(0);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String pipelineName = "PipelineLevel_Trends";
		String BuildMachineWorker = "WorkerAutomation_BuildmachineWorker";
		String portfolioName = WorkerHelper.excelRead(3);

		Thread.sleep(15000);
		driver.navigate().refresh();
		Thread.sleep(10000);
		ph.navigateToPipeline();
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		ph.navLogsDetails(BuildMachineWorker);
		ArrayList<String> fortify_SAST_QualityReport = ph.fetch_Fortity_SAST_Vulnerabilities();

		ph.navPipelineDetailsPage();
		ph.navPipelineDetailsPage_SecurityTab();
		driver.navigate().refresh();
		Thread.sleep(5000);
		ph.navPipelineDetailsPage();
		ph.navPipelineDetailsPage_SecurityTab();

		ArrayList<String> securitySASTVulnerabilities = ph.fetch_FortifySAST_Vulnerabilities_Details();

		assertTrue(ph.verifyTwoArray(fortify_SAST_QualityReport, securitySASTVulnerabilities));
		ph.switchToWindow();
		av.openPortfolioDashboardDetails1(portfolioName);
		ph.navigateToPlatformConfig_Graph_Click("Fortify SAST Vulnerabilities");
		ph.navigateToPlatformConfig_Graph_Click("WorkerAutomation");
		ph.navigateToPlatformConfig_Graph_Click("PipelineLevel_Trends");
		Thread.sleep(5000);
		ph.navToParentTab();
	}

	@Test(enabled = true, priority = 3)
	public void To_Verify_User_Able_to_see_DashboardLevel_FortifyDAST_Vulnerabilities_Trends_Values() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();

		String applicationName = WorkerHelper.excelRead(0);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String pipelineName = "PipelineLevel_Trends";
		String BuildMachineWorker = "WorkerAutomation_BuildmachineWorker";
		String portfolioName = WorkerHelper.excelRead(3);
		ph.navigateToPipeline();
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		ph.navLogsDetails(BuildMachineWorker);
		Thread.sleep(5000);
		ArrayList<String> fortify_DAST_QualityReport = ph.fetch_Fortify_Dast_Vulnerabilities();

		for (String string : fortify_DAST_QualityReport) {
			System.out.println("its 1 =>" + string);
		}

		ph.navPipelineDetailsPage();
		ph.navPipelineDetailsPage_SecurityTab();
		driver.navigate().refresh();
		Thread.sleep(5000);
		ph.navPipelineDetailsPage();
		ph.navPipelineDetailsPage_SecurityTab();

		ArrayList<String> securityDASTVulnerabilities = ph.fetch_Fortify_Dast_Vulnerabilities_Details();

		for (String string : securityDASTVulnerabilities) {
			System.out.println("its 2 =>" + string);
		}
		assertTrue(ph.verifyTwoArray(fortify_DAST_QualityReport, securityDASTVulnerabilities));

		av.openPortfolioDashboardDetails1(portfolioName);
		ph.navigateToPlatformConfig_Graph_Click("Fortify Dast Vulnerabilities");
		ph.navigateToPlatformConfig_Graph_Click("WorkerAutomation");
		Thread.sleep(5000);

		ArrayList<String> value = ph
				.fetch_FortifySAST_Vulnerabilities_Details_FromPlatfromlevel("PipelineLevel_Trends");

		for (String string : value) {
			System.out.println("Its 3 => " + string);
		}

		assertTrue(ph.verifyTwoArray(
				ph.fetch_FortifySAST_Vulnerabilities_Details_FromPlatfromlevel("PipelineLevel_Trends"),
				securityDASTVulnerabilities));

//		ph.navigateToPlatformTrends();

	}

	@Test(enabled = true, priority = 4)
	public void To_Verify_User_Able_to_see_DashboardLevel_Trivy_Vulnerabilities_Trends_Values() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();

		String applicationName = WorkerHelper.excelRead(0);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String pipelineName = "PipelineLevel_Trends";
		String BuildMachineWorker = "WorkerAutomation_BuildmachineWorker";
		String portfolioName = WorkerHelper.excelRead(3);

		ph.navigateToPipeline();
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		ph.navLogsDetails(BuildMachineWorker);
		Thread.sleep(5000);
		ArrayList<String> trivy_QualityReport = ph.fetch_Trivy_Vulnerabilities();

		for (String string : trivy_QualityReport) {
			System.out.println("its 1 =>" + string);
		}

		ph.navPipelineDetailsPage();
		ph.navPipelineDetailsPage_SecurityTab();
		driver.navigate().refresh();
		Thread.sleep(5000);
		ph.navPipelineDetailsPage();
		ph.navPipelineDetailsPage_SecurityTab();

		ArrayList<String> securityTrivyVulnerabilities = ph.fetch_Trivy_Vulnerability_Details();

		for (String string : securityTrivyVulnerabilities) {
			System.out.println("its 2 =>" + string);
		}
		// assertTrue(ph.verifyTwoArray(trivy_QualityReport,
		// securityTrivyVulnerabilities));

		av.openPortfolioDashboardDetails1(portfolioName);
		ph.navigateToPlatformConfig_Dashboard();
		ph.navigateToPlatformConfig_Graph_Click("Trivy Vulnerability Scanner Vulnerabilities");
		ph.navigateToPlatformConfig_Graph_Click("WorkerAutomation");
		Thread.sleep(5000);
		// assertTrue(ph.verifyTwoArray(
		// ph.fetch_FortifySAST_Vulnerabilities_Details_FromPlatfromlevel("PipelineLevel_Trends"),
		// securityTrivyVulnerabilities));

	}

	@Test(enabled = true, priority = 5)
	public void To_Verify_User_Able_to_see_DashboardLevel_PMD_Trends_Values() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();

		String applicationName = WorkerHelper.excelRead(0);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String pipelineName = "PipelineLevel_Trends";
		String BuildMachineWorker = "WorkerAutomation_BuildmachineWorker";
		String portfolioName = WorkerHelper.excelRead(3);

		ph.navigateToPipeline();
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		ph.navLogsDetails(BuildMachineWorker);
		Thread.sleep(5000);
		ph.navPipelineDetailsPage();
		ph.navPipelineDetailsPage_SecurityTab();
		Thread.sleep(6000);
		ArrayList<String> pmd_Vulnerabilities = ph.fetch_PDM_Vulnerability_Details();

		for (String string : pmd_Vulnerabilities) {
			System.out.println("its 1 =>" + string);
		}

		av.openPortfolioDashboardDetails1(portfolioName);
		ph.navigateToPlatformConfig_Graph_Click("PMD-Analyzer Vulnerabilities");
		ph.navigateToPlatformConfig_Graph_Click("WorkerAutomation");
		Thread.sleep(5000);

	}

	@Test(enabled = true, priority = 6)
	public void To_Verify_User_Able_to_see_DashboardLevel_SonarQube_Trends_Values() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();

		String applicationName = WorkerHelper.excelRead(0);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String pipelineName = "PipelineLevel_Trends";
		String BuildMachineWorker = "WorkerAutomation_BuildmachineWorker";
		String portfolioName = WorkerHelper.excelRead(3);

		ph.navigateToPipeline();
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		ph.navLogsDetails(BuildMachineWorker);
		Thread.sleep(5000);
		ph.navPipelineDetailsPage();

		ph.navPipelineDetailsPage_SecurityTab();
		Thread.sleep(6000);
		ArrayList<String> securitySonarQube_Vulnerabilities = ph.fetch_Security_SonarQube_Vulnerabilities_Details();

		for (String string : securitySonarQube_Vulnerabilities) {
			System.out.println("its 1 =>" + string);
		}

		av.openPortfolioDashboardDetails1(portfolioName);
		ph.navigateToPlatformConfig_Graph_Click("SonarQube Vulnerabilities");
		ph.navigateToPlatformConfig_Graph_Click("WorkerAutomation");
		Thread.sleep(5000);
	}

	@Test(enabled = true, priority = 7)
	public void To_Verify_User_Able_to_see_DashboardLevel_InfoIcon() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();

		String applicationName = WorkerHelper.excelRead(0);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String pipelineName = "PipelineLevel_Trends";
		String BuildMachineWorker = "WorkerAutomation_BuildmachineWorker";
		String portfolioName = WorkerHelper.excelRead(3);

		av.openPortfolioDashboardDetails1(portfolioName);

		assertTrue(ph.verify_infoIcon_Details("Fortify SAST Vulnerabilities",
				"Security vulnerabilities reported by the tool"));
		assertTrue(ph.verify_infoIcon_Details("Fortify Dast Vulnerabilities",
				"Security vulnerabilities reported by the tool"));
		assertTrue(ph.verify_infoIcon_Details("Tripwire Vulnerabilities",
				"Security vulnerabilities reported by the tool"));
		ph.scrollToView("Trivy Vulnerability Scanner Vulnerabilities");
		assertTrue(ph.verify_infoIcon_Details("Trivy Vulnerability Scanner Vulnerabilities",
				"Security vulnerabilities reported by the tool"));
		assertTrue(ph.verify_infoIcon_Details("Contrast Vulnerabilities",
				"Security vulnerabilities reported by the tool"));
		Thread.sleep(6000);
		ph.scrollToView("SonarQube Vulnerabilities");
		assertTrue(ph.verify_infoIcon_Details("SonarQube Vulnerabilities",
				"SonarQube vulnerabilities reported by the tool"));

		assertTrue(ph.verify_infoIcon_Details("Jfrog_xray vulnerabilities", "vulnerabilities reported by the tool"));
		ph.scrollToView("Snyk Vulnerabilities");
		assertTrue(ph.verify_infoIcon_Details("Snyk Vulnerabilities", "Security vulnerabilities reported by the tool"));

		ph.scrollToView("Blackduck Security Risks");
		Thread.sleep(6000);
		assertTrue(ph.verify_infoIcon_Details("Blackduck Security Risks",
				"Security Risks reported by the Blackduck tool"));

		ph.scrollToView("Blackduck License Risks");
		Thread.sleep(2000);
		assertTrue(
				ph.verify_infoIcon_Details("Blackduck License Risks", "License Risks reported by the Blackduck tool"));

		ph.scrollToView("PMD-Analyzer Vulnerabilities");
		Thread.sleep(4000);
		assertTrue(ph.verify_infoIcon_Details("PMD-Analyzer Vulnerabilities",
				"Security vulnerabilities reported by the tool"));

		ph.scrollToView("Blackduck Operational Risks");
		Thread.sleep(2000);
		assertTrue(ph.verify_infoIcon_Details("Blackduck Operational Risks",
				"Operational Risks reported by the Blackduck tool"));

	}

	@Test(enabled = true, priority = 8)
	public void To_Verify_User_Able_to_see_DashboardLevel_Trends_InfoIcon() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();

		String applicationName = WorkerHelper.excelRead(0);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String pipelineName = "PipelineLevel_Trends";
		String BuildMachineWorker = "WorkerAutomation_BuildmachineWorker";
		String portfolioName = WorkerHelper.excelRead(3);

		av.openPortfolioDashboardDetails1(portfolioName);

		ph.navigateToPlatformConfig_Graph_Click("Fortify SAST Vulnerabilities");
		ph.navigateToPlatformTrends();
		assertTrue(ph.verify_infoIcon_Details("Fortify-SAST Trend", "Fortify-SAST trend for last 14 days"));
		ph.closePlatformTrends();

		ph.navigateToPlatformConfig_Graph_Click("Fortify Dast Vulnerabilities");
		ph.navigateToPlatformTrends();
		assertTrue(ph.verify_infoIcon_Details("FortifyDast trend", "fortifyDast trend for last 14 days"));
		ph.closePlatformTrends();

		ph.navigateToPlatformConfig_Graph_Click("Tripwire Vulnerabilities");
		ph.navigateToPlatformTrends();
		assertTrue(ph.verify_infoIcon_Details("Tripwire trend", "Tripwire trend for last 14 days"));
		ph.closePlatformTrends();

		ph.navigateToPlatformConfig_Graph_Click("Trivy Vulnerability Scanner Vulnerabilities");
		ph.navigateToPlatformTrends();
		assertTrue(ph.verify_infoIcon_Details("Trivy trend", "Trivy trend for last 14 days"));
		ph.closePlatformTrends();

		ph.navigateToPlatformConfig_Graph_Click("Contrast Vulnerabilities");
		ph.navigateToPlatformTrends();
		assertTrue(ph.verify_infoIcon_Details("Contrast trend", "Contrast trend for last 14 days"));
		ph.closePlatformTrends();

		ph.navigateToPlatformConfig_Graph_Click("SonarQube Vulnerabilities");
		ph.navigateToPlatformTrends();
		assertTrue(ph.verify_infoIcon_Details("SonarQube violation trend", "Violations trend for last 14 days"));
		ph.closePlatformTrends();

		ph.navigateToPlatformConfig_Graph_Click("Jfrog_xray vulnerabilities");
		ph.navigateToPlatformTrends();
		assertTrue(ph.verify_infoIcon_Details("jfrog_xray trend", "jfrog_xray trend for last 14 days"));
		ph.closePlatformTrends();

		ph.navigateToPlatformConfig_Graph_Click("Snyk Vulnerabilities");
		ph.navigateToPlatformTrends();
		assertTrue(ph.verify_infoIcon_Details("Snyk Trend", "Snyk trend for last 14 days"));
		ph.closePlatformTrends();

		ph.navigateToPlatformConfig_Graph_Click("Blackduck Security Risks");
		ph.navigateToPlatformTrends();
		assertTrue(ph.verify_infoIcon_Details("Trends for security risks",
				"Blackduck security risks trend for last 14 days"));
		ph.closePlatformTrends();

		ph.navigateToPlatformConfig_Graph_Click("PrismaCloud Twistlock Security Vulnerabilities");
		ph.navigateToPlatformTrends();
		assertTrue(ph.verify_infoIcon_Details("PrismaCloud Twistlock trend for Vulnerabilities",
				"PrismaCloud Twistlock trend of Vulnerabilities for last 14 days"));
		assertTrue(ph.verify_infoIcon_Details("PrismaCloud Twistlock trend for Compliance",
				"PrismaCloud Twistlock trend of Compliance for last 14 days"));
		ph.closePlatformTrends();

		ph.navigateToPlatformConfig_Graph_Click("Blackduck License Risks");
		ph.navigateToPlatformTrends();
		assertTrue(ph.verify_infoIcon_Details("Trends for security risks",
				"Blackduck security risks trend for last 14 days"));
		assertTrue(ph.verify_infoIcon_Details("Trends for license risks",
				"Blackduck license risks trend for last 14 days"));
		ph.closePlatformTrends();
	}
}