package org.idp.pipelineTrends;

import static org.testng.Assert.assertEquals;
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
public class TC_Pipeline_Trends extends LoadData {

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();

	@Test(enabled = true, priority = 1)
	public void To_Verify_User_Able_to_see_pipelineLevel_Trends_Values() throws Exception {
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
//		ph.filterPipelineAndclickOnEdit(pipelineName);
//		ph.triggerPipelineUsingIcon();
//		ph.triggerPageWorkflowSelection(" AllStage ");
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
		// Thread.sleep(10000);
		ph.navigateToPipeline();
		// Thread.sleep(400000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		ph.navLogsDetails(BuildMachineWorker);

		ArrayList<String> sonarQualityReport = ph.fetch_Sonoar_Report_Quality();

		for (String string : sonarQualityReport) {
			System.out.println("sonarQualityReport ===> " + string);
		}
		ph.navPipelineDetailsPage();
		Thread.sleep(5000);
		ArrayList<String> sonarQualityReport1 = ph.fetch_Trend_Code_violation_trend();
		for (String string : sonarQualityReport1) {
			System.out.println("sonarQualityReport1 ===> " + string);
		}

		ph.navPipelineDetailsPage_SecurityTab();
		Thread.sleep(5000);
		ArrayList<String> securitySonarQube_Vulnerabilities = ph.fetch_Security_SonarQube_Vulnerabilities_Details();

		for (String string : securitySonarQube_Vulnerabilities) {
			System.out.println("securitySonarQube_Vulnerabilities ===> " + string);
		}

		assertTrue(ph.verifyTwoArray(sonarQualityReport, sonarQualityReport1));

		assertTrue(ph.verifyTwoArray(sonarQualityReport1, securitySonarQube_Vulnerabilities));

//		ph.navToNewTab("https://infysonar.ad.infosys.com/sessions/new?return_to=%2Fprojects");
//		ArrayList<String> listFromSonar = ph.getSevDataFromSonar();
//		assertTrue(ph.verifyTwoArray(sonarQualityReport, listFromSonar));
//
//		ph.navToParentTab();
	}

	@Test(enabled = true, priority = 2)
	public void To_Verify_User_Able_to_see_Security_Fortify_SAST() throws Exception {
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
		Thread.sleep(5000);
		ArrayList<String> fortify_SAST_QualityReport = ph.fetch_Fortity_SAST_Vulnerabilities();
		for (String string : fortify_SAST_QualityReport) {
			System.out.println("fortify_SAST_QualityReport ===> " + string);
		}

		ph.navPipelineDetailsPage();
		ph.navPipelineDetailsPage_SecurityTab();
		Thread.sleep(5000);
		ArrayList<String> securitySASTVulnerabilities = ph.fetch_FortifySAST_Vulnerabilities_Details();
		for (String string : securitySASTVulnerabilities) {
			System.out.println("securitySASTVulnerabilities ===> " + string);
		}
		assertTrue(ph.verifyTwoArray(fortify_SAST_QualityReport, securitySASTVulnerabilities));
	}

	@Test(enabled = true, priority = 3)
	public void To_Verify_User_Able_to_see_Security_CodeQL_Analyze() throws Exception {
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

		ArrayList<String> CodeQL_Analyze_Report = ph.fetch_CodeQL_Analyze_Vulnerabilities();
		for (String string : CodeQL_Analyze_Report) {
			System.out.println("CodeQL_Analyze_Report ===> " + string);
		}

		ph.navPipelineDetailsPage();

		ph.navPipelineDetailsPage_SecurityTab();
		Thread.sleep(5000);
		ArrayList<String> codeqlVulnerabilities = ph.fetch_Codeql_Vulnerabilities_Details();

		for (String string : codeqlVulnerabilities) {
			System.out.println("codeqlVulnerabilities ===> " + string);
		}
		assertTrue(ph.verifyTwoArray(CodeQL_Analyze_Report, codeqlVulnerabilities));
	}

	@Test(enabled = true, priority = 4)
	public void To_Verify_User_Able_to_see_Security_Fortify_Dast() throws Exception {
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
		Thread.sleep(6000);
		ArrayList<String> DAST_Report = ph.fetch_Fortify_Dast_Vulnerabilities();
		for (String string : DAST_Report) {
			System.out.println("DAST_Report ===> " + string);
		}
		ph.navPipelineDetailsPage();

		ph.navPipelineDetailsPage_SecurityTab();
		Thread.sleep(6000);
		ArrayList<String> DASTVulnerabilities = ph.fetch_Fortify_Dast_Vulnerabilities_Details();
		for (String string : DASTVulnerabilities) {
			System.out.println("DASTVulnerabilities ===> " + string);
		}
		assertTrue(ph.verifyTwoArray(DAST_Report, DASTVulnerabilities));
	}

	@Test(enabled = true, priority = 5)
	public void To_Verify_User_Able_to_see_Trivy_Vulnerability_Scanner() throws Exception {
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
		ArrayList<String> trivy_Report = ph.fetch_Trivy_Vulnerabilities();
		for (String string : trivy_Report) {
			System.out.println("trivy_Report ===> " + string);
		}
		ph.navPipelineDetailsPage();

		ph.navPipelineDetailsPage_SecurityTab();
		Thread.sleep(6000);
		ArrayList<String> trivy_Vulnerabilities = ph.fetch_Trivy_Vulnerability_Details();
		for (String string : trivy_Vulnerabilities) {
			System.out.println("trivy_Vulnerabilities ===> " + string);
		}
		assertTrue(ph.verifyTwoArray(trivy_Report, trivy_Vulnerabilities));
	}

	@Test(enabled = true, priority = 6)
	public void To_Verify_User_Able_to_see_PMD_Vulnerability_Scanner() throws Exception {
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

		ph.navPipelineDetailsPage();

		ph.navPipelineDetailsPage_SecurityTab();
		Thread.sleep(6000);
		ArrayList<String> pmd_Vulnerabilities = ph.fetch_PDM_Vulnerability_Details();
		for (String string : pmd_Vulnerabilities) {
			System.out.println("pmd_Vulnerabilities ===> " + string);
		}
	}

	@Test(enabled = true, priority = 7)
	public void To_Verify_User_Able_to_see_Environment_Utilisation() throws Exception {
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

		ph.navPipelineDetailsPage();

		ph.navPipelineDetailsPage_MaturityTab();
		Thread.sleep(6000);
		String environmentUtilisation = ph.fetch_Environment_Utilisation_Details();
		assertEquals("0", environmentUtilisation);
	}

	@Test(enabled = true, priority = 8)
	public void To_Verify_User_Able_to_see_DevSecOps_Maturity() throws Exception {
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

		ph.navPipelineDetailsPage();

		ph.navPipelineDetailsPage_MaturityTab();
		Thread.sleep(6000);
		String devopsMaturity = ph.fetch_Maturity_Details("chart[0].__ngContext__[31][0].data[0]");
		// assertEquals("19", devopsMaturity);
	}

	@Test(enabled = true, priority = 9)
	public void To_Verify_User_Able_to_see_Execution_Efficiency() throws Exception {
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

		ph.navPipelineDetailsPage();

		ph.navPipelineDetailsPage_MaturityTab();
		Thread.sleep(6000);
		String devopsMaturity = ph.fetch_Maturity_Details("chart[1].__ngContext__[31][0].data[0]");
		System.out.println(devopsMaturity);
		// assertEquals("36.84210526315789", devopsMaturity);

	}

	@Test(enabled = true, priority = 10)
	public void To_Verify_User_Able_to_see_MTTR() throws Exception {
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

		ph.navPipelineDetailsPage();

		ph.navPipelineDetailsPage_MaturityTab();
		Thread.sleep(6000);
		String MTTR = ph.fetch_Maturity_MM_Details("MTTR");
		System.out.println(MTTR);
		// assertEquals("6.1", MTTR);
	}

	@Test(enabled = true, priority = 11)
	public void To_Verify_User_Able_to_see_MTBF() throws Exception {
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

		ph.navPipelineDetailsPage();

		ph.navPipelineDetailsPage_MaturityTab();
		Thread.sleep(6000);
		String MTBF = ph.fetch_Maturity_MM_Details("MTBF");
		System.out.println(MTBF);
		// assertEquals("1.9", MTBF);
	}

	@Test(enabled = true, priority = 12)
	public void To_Verify_User_Able_to_see_Run_Frequency() throws Exception {
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

		ph.navPipelineDetailsPage();

		ph.navPipelineDetailsPage_MaturityTab();
		Thread.sleep(10000);
		String Run_Frequency = ph.fetch_Maturity_MM_Details("Run Frequency");
		// assertEquals("19", Run_Frequency);
	}

	@Test(enabled = true, priority = 13)
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
		Thread.sleep(5000);
		ArrayList<String> Security_Risks = ph.fetch_blackduck_Vulnerabilities(4, 4);
		for (String string : Security_Risks) {
			System.out.println("Security_Risks ===> " + string);
		}

		ArrayList<String> License_Risk = ph.fetch_blackduck_Vulnerabilities(3, 5);
		for (String string : License_Risk) {
			System.out.println("License_Risk ===> " + string);
		}

		ArrayList<String> Operational_Risks = ph.fetch_blackduck_Vulnerabilities(3, 6);

		for (String string : Operational_Risks) {
			System.out.println("sonarQualityReport ===> " + string);
		}

		ph.navPipelineDetailsPage();
		ph.navPipelineDetailsPage_SecurityTab();
		Thread.sleep(6000);
		ArrayList<String> Security_Risks_Vulnerabilities = ph.fetch_Blackduck_Vulnerability_Details(4, 12);
		for (String string : Security_Risks_Vulnerabilities) {
			System.out.println("Security_Risks_Vulnerabilities ===> " + string);
		}
		ArrayList<String> License_Risk_Vulnerabilities = ph.fetch_Blackduck_Vulnerability_Details(3, 13);
		for (String string : License_Risk_Vulnerabilities) {
			System.out.println("License_Risk_Vulnerabilities ===> " + string);
		}
		ArrayList<String> Operational_Risks_Vulnerabilities = ph.fetch_Blackduck_Vulnerability_Details(3, 14);
		for (String string : Operational_Risks_Vulnerabilities) {
			System.out.println("Operational_Risks_Vulnerabilities ===> " + string);
		}

		assertTrue(ph.verifyTwoArray(Security_Risks, Security_Risks_Vulnerabilities));
		assertTrue(ph.verifyTwoArray(License_Risk, License_Risk_Vulnerabilities));
		assertTrue(ph.verifyTwoArray(Operational_Risks, Operational_Risks_Vulnerabilities));
	}

	@Test(enabled = true, priority = 14)
	public void To_Verify_User_Able_to_see_Jfrog() throws Exception {
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
		Thread.sleep(5000);
		ArrayList<String> jfrog = ph.fetch_Jfrog_Vulnerabilities(4);
		ph.navPipelineDetailsPage();
		ph.navPipelineDetailsPage_SecurityTab();
		Thread.sleep(6000);

		ArrayList<String> jfrog_Vulnerabilities = ph.fetch_jfrog_Vulnerability_Details(4);
		Thread.sleep(5000);
		assertTrue(ph.verifyTwoArray(jfrog, jfrog_Vulnerabilities));
	}
}
