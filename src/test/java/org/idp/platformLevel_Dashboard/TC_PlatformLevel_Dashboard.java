package org.idp.platformLevel_Dashboard;

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
public class TC_PlatformLevel_Dashboard extends LoadData {

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

		Thread.sleep(15000);
		driver.navigate().refresh();
		Thread.sleep(10000);
		ph.navigateToPlatformConfig();
		ph.navigateToPlatformConfig_Dashboard();
		ph.navigateToPlatformConfig_Graph_Click("Fortify SAST Vulnerabilities");
		ph.navigateToPlatformConfig_Graph_Click("DefaultApp");
		ph.navigateToPlatformConfig_Graph_Click("Demo_CICDCT");
		ph.getParentWindowSessionID();
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

//		ph.navigateToPipeline();
//		ph.filterPipelineAndclickOnEdit(pipelineName);
//		ph.triggerPipelineUsingIcon();
//		ph.triggerPageWorkflowSelection(" AllStages ");
//		ph.triggerPageBranchSelection("dev", 1);
//		ph.triggerPageBranchSelection("dev", 2);
//		ph.triggerPageBranchSelection("dev", 3);
//		ph.triggerPageBranchSelection("main", 4);
//		ph.scrollUp();
//		ph.workerSelectionInPipelineTrigger(BuildMachineWorker);
//		ph.triggerPipelineUsingButton();
//
//		Thread.sleep(15000);
		driver.navigate().refresh();
//		Thread.sleep(500000);
		ph.navigateToPipeline();
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		ph.navLogsDetails(BuildMachineWorker);
		ArrayList<String> fortify_SAST_QualityReport = ph.fetch_Fortity_SAST_Vulnerabilities();

		ph.navPipelineDetailsPage();
		ph.navPipelineDetailsPage_SecurityTab();
		Thread.sleep(5000);
		ArrayList<String> securitySASTVulnerabilities = ph.fetch_FortifySAST_Vulnerabilities_Details();

		assertTrue(ph.verifyTwoArray(fortify_SAST_QualityReport, securitySASTVulnerabilities));

		ph.navigateToPlatformConfig();
		ph.navigateToPlatformConfig_Dashboard();
		ph.navigateToPlatformConfig_Graph_Click("Fortify SAST Vulnerabilities");
		ph.navigateToPlatformConfig_Graph_Click("WorkerAutomation");
		Thread.sleep(5000);
		assertTrue(ph.verifyTwoArray(
				ph.fetch_FortifySAST_Vulnerabilities_Details_FromPlatfromlevel("PipelineLevel_Trends"),
				securitySASTVulnerabilities));
		ph.navigateToPlatformTrends();

		assertTrue(ph.verifyTwoArray(ph.fetch_Vulnerabilities_Details_FromPlatfromlevel_Trends("Fortify-SAST Trend"),
				securitySASTVulnerabilities));
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
		Thread.sleep(5000);
		ArrayList<String> securityDASTVulnerabilities = ph.fetch_Fortify_Dast_Vulnerabilities_Details();

		for (String string : securityDASTVulnerabilities) {
			System.out.println("its 2 =>" + string);
		}
		assertTrue(ph.verifyTwoArray(fortify_DAST_QualityReport, securityDASTVulnerabilities));

		ph.navigateToPlatformConfig();
		ph.navigateToPlatformConfig_Dashboard();
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
		ph.navigateToPlatformTrends();
		Thread.sleep(5000);
		assertTrue(ph.verifyTwoArray(ph.fetch_Vulnerabilities_Details_FromPlatfromlevel_Trends("FortifyDast trend"),
				securityDASTVulnerabilities));
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
		Thread.sleep(5000);
		ArrayList<String> securityTrivyVulnerabilities = ph.fetch_Trivy_Vulnerability_Details();

		for (String string : securityTrivyVulnerabilities) {
			System.out.println("its 2 =>" + string);
		}
		assertTrue(ph.verifyTwoArray(trivy_QualityReport, securityTrivyVulnerabilities));

		ph.navigateToPlatformConfig();
		ph.navigateToPlatformConfig_Dashboard();
		ph.navigateToPlatformConfig_Graph_Click("Trivy Vulnerability Scanner Vulnerabilities");
		ph.navigateToPlatformConfig_Graph_Click("WorkerAutomation");
		Thread.sleep(5000);
		assertTrue(ph.verifyTwoArray(
				ph.fetch_FortifySAST_Vulnerabilities_Details_FromPlatfromlevel("PipelineLevel_Trends"),
				securityTrivyVulnerabilities));
		ph.navigateToPlatformTrends();
		Thread.sleep(5000);
		assertTrue(ph.verifyTwoArray(ph.fetch_Vulnerabilities_Details_FromPlatfromlevel_Trends("Trivy trend"),
				securityTrivyVulnerabilities));

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

		ph.navigateToPlatformConfig();
		ph.navigateToPlatformConfig_Dashboard();
		ph.navigateToPlatformConfig_Graph_Click("PMD-Analyzer Vulnerabilities");
		ph.navigateToPlatformConfig_Graph_Click("WorkerAutomation");
		Thread.sleep(5000);
		assertTrue(ph.verifyTwoArray(
				ph.fetch_FortifySAST_Vulnerabilities_Details_FromPlatfromlevel("PipelineLevel_Trends"),
				pmd_Vulnerabilities));
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

		ph.navigateToPlatformConfig();
		ph.navigateToPlatformConfig_Dashboard();
		ph.navigateToPlatformConfig_Graph_Click("SonarQube Vulnerabilities");
		ph.navigateToPlatformConfig_Graph_Click("WorkerAutomation");
		Thread.sleep(5000);

		assertTrue(ph.verifyTwoArray(ph.fetch_Sonar_Vulnerabilities_Details_FromPlatfromlevel("PipelineLevel_Trends"),
				securitySonarQube_Vulnerabilities));
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

		ph.navigateToPlatformConfig();
		ph.navigateToPlatformConfig_Dashboard();

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

		assertTrue(ph.verify_infoIcon_Details("SonarQube Vulnerabilities",
				"SonarQube vulnerabilities reported by the tool"));

		assertTrue(ph.verify_infoIcon_Details("Jfrog_xray vulnerabilities", "vulnerabilities reported by the tool"));
		assertTrue(ph.verify_infoIcon_Details("Snyk Vulnerabilities", "Security vulnerabilities reported by the tool"));

		ph.scrollToView("SonarQube Vulnerabilities");

		assertTrue(ph.verify_infoIcon_Details("Blackduck Security Risks",
				"Security Risks reported by the Blackduck tool"));
		ph.scrollToView("Blackduck Security Risks");

		assertTrue(ph.verify_infoIcon_Details("PrismaCloud Twistlock Security Vulnerabilities",
				"Security Vulnerabilities reported by the tool"));
		assertTrue(ph.verify_infoIcon_Details("PMD-Analyzer Vulnerabilities",
				"Security vulnerabilities reported by the tool"));
		assertTrue(
				ph.verify_infoIcon_Details("Blackduck License Risks", "License Risks reported by the Blackduck tool"));
		ph.scrollToView("Blackduck License Risks");
		assertTrue(ph.verify_infoIcon_Details("PrismaCloud Twistlock Security Compliance",
				"Security Compliance reported by the tool"));
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

		ph.navigateToPlatformConfig();
		ph.navigateToPlatformConfig_Dashboard();

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

	@Test(enabled = true, priority = 9)
	public void To_Verify_User_Able_to_see_Blackduck() throws Exception {
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

		driver.navigate().refresh();
		ph.navigateToPipeline();
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		ph.navLogsDetails(BuildMachineWorker);
		ph.navPipelineDetailsPage_SecurityTab();
		ArrayList<String> Security_Risks = ph.fetch_blackduck_Vulnerabilities(4, 4);
		ArrayList<String> License_Risk = ph.fetch_blackduck_Vulnerabilities(3, 5);
		ArrayList<String> Operational_Risks = ph.fetch_blackduck_Vulnerabilities(3, 6);

		ph.navPipelineDetailsPage();
		ph.navPipelineDetailsPage_SecurityTab();
		Thread.sleep(6000);
		ArrayList<String> Security_Risks_Vulnerabilities = ph.fetch_Blackduck_Vulnerability_Details(4, 10);
		ArrayList<String> License_Risk_Vulnerabilities = ph.fetch_Blackduck_Vulnerability_Details(3, 11);
		ArrayList<String> Operational_Risks_Vulnerabilities = ph.fetch_Blackduck_Vulnerability_Details(3, 12);

		assertTrue(ph.verifyTwoArray(Security_Risks, Security_Risks_Vulnerabilities));
		assertTrue(ph.verifyTwoArray(License_Risk, License_Risk_Vulnerabilities));
		assertTrue(ph.verifyTwoArray(Operational_Risks, Operational_Risks_Vulnerabilities));

		ph.navigateToPlatformConfig();
		ph.navigateToPlatformConfig_Dashboard();
//		ph.navigateToPlatformConfig_Graph_Click("Blackduck Security Risks");
//		ph.navigateToPlatformConfig_Graph_Click("WorkerAutomation");
		Thread.sleep(5000);

		ArrayList<String> Security_Risks_Vulnerabilities_Platform = ph
				.fetch_Platformlevel_blackduck_Vulnerability_Details(4, 11);
		ArrayList<String> License_Risk_Vulnerabilities_Platform = ph
				.fetch_Platformlevel_blackduck_Vulnerability_Details(3, 12);
		ArrayList<String> Operational_Risks_Vulnerabilities_Platform = ph
				.fetch_Platformlevel_blackduck_Vulnerability_Details(3, 13);

//		assertTrue(ph.verifyTwoArray(Security_Risks, Security_Risks_Vulnerabilities_Platform));
//		assertTrue(ph.verifyTwoArray(License_Risk, License_Risk_Vulnerabilities_Platform));
//		assertTrue(ph.verifyTwoArray(Operational_Risks, Operational_Risks_Vulnerabilities_Platform));
	}

	@Test(enabled = true, priority = 10)
	public void To_Verify_User_Able_to_see_Jfrog_Trendsc() throws Exception {
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

		driver.navigate().refresh();
		ph.navigateToPipeline();
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		ph.navLogsDetails(BuildMachineWorker);
		ph.navPipelineDetailsPage_SecurityTab();

		ArrayList<String> jfrog = ph.fetch_Jfrog_Vulnerabilities(4);
		ph.navPipelineDetailsPage();
		ph.navPipelineDetailsPage_SecurityTab();
		Thread.sleep(6000);

		ArrayList<String> jfrog_Vulnerabilities = ph.fetch_jfrog_Vulnerability_Details(4);

		assertTrue(ph.verifyTwoArray(jfrog, jfrog_Vulnerabilities));

		ph.navigateToPlatformConfig();
		ph.navigateToPlatformConfig_Dashboard();
		ph.navigateToPlatformConfig_Graph_Click("Jfrog_xray vulnerabilities");
		ph.navigateToPlatformConfig_Graph_Click("WorkerAutomation");

		Thread.sleep(5000);
		assertTrue(ph.verifyTwoArray(ph.fetch_Jfrog_Details_FromPlatfromlevel("PipelineLevel_Trends"),
				jfrog_Vulnerabilities));
	}

	@Test(enabled = true, priority = 11)
	public void To_Verify_User_Able_to_see_Contrast_Vulnerabilities() throws Exception {
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

		driver.navigate().refresh();
		ph.navigateToPipeline();
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		ph.navLogsDetails(BuildMachineWorker);
		ph.navPipelineDetailsPage_SecurityTab();

		ArrayList<String> Contrest = ph.fetch_Vulnerabilities(4, " - Contrast", 8);
		for (String string : Contrest) {
			System.out.println(Contrest);
		}
		ph.navPipelineDetailsPage();
		ph.navPipelineDetailsPage_SecurityTab();
		Thread.sleep(6000);

		ArrayList<String> Contrest_Vulnerabilities = ph.fetch_Vulnerability_Details(4, " Contrast Vulnerabilities ", 4);
		for (String string : Contrest_Vulnerabilities) {
			System.out.println(Contrest);
		}
		// assertTrue(ph.verifyTwoArray(Contrest, Contrest_Vulnerabilities));

		ph.navigateToPlatformConfig();
		ph.navigateToPlatformConfig_Dashboard();
		ph.navigateToPlatformConfig_Graph_Click("Contrast Vulnerabilities");
		ph.navigateToPlatformConfig_Graph_Click("WorkerAutomation");

		Thread.sleep(5000);
		assertTrue(ph.verifyTwoArray(ph.fetch_chart_Details_FromPlatfromlevel("PipelineLevel_Trends", 1, 5),
				Contrest_Vulnerabilities));
	}

	@Test(enabled = true, priority = 12)
	public void To_Verify_User_Able_to_see_Snyk_Test() throws Exception {
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

		driver.navigate().refresh();
		ph.navigateToPipeline();
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		ph.navLogsDetails(BuildMachineWorker);
		ph.navPipelineDetailsPage_SecurityTab();

		ArrayList<String> Snyk = ph.fetch_Vulnerabilities(4, " - snyk_test", 9);
		for (String string : Snyk) {
			System.out.println(string);
		}
		ph.navPipelineDetailsPage();
		ph.navPipelineDetailsPage_SecurityTab();
		Thread.sleep(6000);

		ArrayList<String> Snyk_Test = ph.fetch_Vulnerability_Details(4, " Snyk Vulnerabilities ", 8);
		for (String string : Snyk_Test) {
			System.out.println(string);
		}
		assertTrue(ph.verifyTwoArray(Snyk, Snyk_Test));

		ph.navigateToPlatformConfig();
		ph.navigateToPlatformConfig_Dashboard();
		ph.navigateToPlatformConfig_Graph_Click("Snyk Vulnerabilities");
		ph.navigateToPlatformConfig_Graph_Click("WorkerAutomation");

		Thread.sleep(5000);
		assertTrue(
				ph.verifyTwoArray(ph.fetch_chart_Details_FromPlatfromlevel("PipelineLevel_Trends", 1, 5), Snyk_Test));
	}
}
