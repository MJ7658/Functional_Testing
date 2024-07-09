package org.idp.policyManagement;

import static org.testng.Assert.assertTrue;

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
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

@Listeners(ListenersTest.class)
public class TC_PolicyManagement extends LoadData {
	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();

	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(TC_PolicyManagement.class);

	@Test(enabled = true, priority = 1)
	public void To_validate_Policys_Create_Edit_Delete_ApplicaitonLevel() throws Exception {
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
		write.ExcelWriter();
		String AppName = read.excelRead(0);
		String protName = read.excelRead(1);
		String ownrName = read.excelRead(2);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		String portfolioName = WorkerHelper.excelRead(3);
		av.navCreateApplication(AppName, protName, ownrName);
		av.openApplicationDashboard(AppName);
		rp.navPolicys();
		String policyName = "NewQATest";
		av.policyCreation(policyName);

		String[] Applicationpermission = { "EDIT", "EXECUTE", "VIEW" };
		av.addPolicy(policyName, "Application", Applicationpermission, "*");

		String[] Notificationsnpermission = { "DELETE" };
		av.addPolicy(policyName, "Bulk Notifications", Notificationsnpermission, "*");

		String[] Developer_Analytics = { "EXECUTIVE", "MANAGER", "USER" };
		av.addPolicy(policyName, "Developer Analytics", Developer_Analytics, "*");

		String[] Environment = { "CREATE", "DELETE", "EDIT", "EXECUTE", "VIEW" };
		av.addPolicy(policyName, "Environment", Environment, "*");

		String[] pipeline = { "CREATE", "DELETE", "EDIT", "EXECUTE", "VIEW" };
		av.addPolicy(policyName, "Pipeline", pipeline, "*");

		String[] Policy = { "CREATE", "EXECUTE", "EDIT", "VIEW" };
		av.addPolicy(policyName, "Policy", Policy, "*");

		String[] Release = { "CREATE", "EDIT", "EXECUTE", "VIEW" };
		av.addPolicy(policyName, "Release", Release, "*");

		String[] Stage = { "CREATE", "EDIT", "EXECUTE", "VIEW" };
		av.addPolicy(policyName, "Stage", Stage, "*");

		String[] Variables = { "CREATE", "DELETE", "EDIT", "EXECUTE", "VIEW" };
		av.addPolicy(policyName, "Variables", Variables, "*");

		String[] Worker = { "CREATE", "DELETE", "VIEW" };
		av.addPolicy(policyName, "Worker", Worker, "*");

		String[] Workflow = { "CREATE", "DELETE", "EDIT", "EXECUTE" };
		av.addPolicy(policyName, "Workflow(Application)", Workflow, "*");

		String[] username = { "satheshkumar.m@infosys.com" };
		av.addPolicyUser(policyName, username);

		av.clickOnSavePolicy();
		Thread.sleep(3000);
		driver.navigate().refresh();
		rp.navPolicys();
		String newEmail = "vignesh.mj@infosys.com";
		rp.UpdatePolicysPortfolio(policyName, newEmail);
		av.clickOnSavePolicy();
		Thread.sleep(5000);
		driver.navigate().refresh();
		Thread.sleep(3000);
		rp.navPolicys();
		rp.deletePolicys(policyName);
	}

	@Test(enabled = true, priority = 2)
	public void To_validate_Policys_Create_Edit_Delete_PortfolioLevel() throws Exception {
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
		write.ExcelWriter();
		String AppName = read.excelRead(0);
		String protName = read.excelRead(1);
		String ownrName = read.excelRead(2);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		String portfolioName = WorkerHelper.excelRead(3);
		av.navCreatePortfolio(AppName, ownrName);
		av.openPortfolioDashboardDetails(AppName);
		rp.navPolicys();
		String policyName = "NewQATest";
		av.policyCreation(policyName);

		String[] Applicationpermission = { "EDIT", "EXECUTE", "VIEW" };
		av.addPolicy(policyName, "Application", Applicationpermission, "*");

		String[] Environment = { "CREATE", "DELETE", "EDIT", "EXECUTE", "VIEW" };
		av.addPolicy(policyName, "Environment", Environment, "*");

		String[] pipeline = { "CREATE", "DELETE", "EDIT", "EXECUTE", "VIEW" };
		av.addPolicy(policyName, "Pipeline", pipeline, "*");

		String[] Policy = { "CREATE", "EXECUTE", "EDIT", "VIEW" };
		av.addPolicy(policyName, "Policy", Policy, "*");

		String[] Portfolio = { "DELETE", "EDIT", "VIEW" };
		av.addPolicy(policyName, "Portfolio", Portfolio, "*");

		String[] Release = { "CREATE", "EDIT", "EXECUTE", "VIEW" };
		av.addPolicy(policyName, "Release", Release, "*");

		String[] Reports = { "VIEW" };
		av.addPolicy(policyName, "Reports", Reports, "*");

		String[] Variables = { "CREATE", "DELETE", "EDIT", "EXECUTE", "VIEW" };
		av.addPolicy(policyName, "Variables", Variables, "*");

		String[] Worker = { "CREATE", "DELETE", "VIEW" };
		av.addPolicy(policyName, "Worker", Worker, "*");

		String[] username = { "satheshkumar.m@infosys.com" };
		av.addPolicyUser(policyName, username);

		av.clickOnSavePolicy(policyName);
		Thread.sleep(3000);
		driver.navigate().refresh();
		rp.navPolicys();
		String newEmail = "vignesh.mj@infosys.com";
		rp.UpdatePolicysPortfolio(policyName, newEmail);
		av.clickOnSavePolicy(policyName);
		Thread.sleep(5000);
		driver.navigate().refresh();
		Thread.sleep(3000);
		rp.navPolicys();
		rp.deletePolicys(policyName);
	}

	@Test(enabled = false, priority = 3)
	public void To_validate_All_Policys_With_NewUser__PortfolioLevel() throws Exception {
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
		write.ExcelWriter();
		String AppName = read.excelRead(0);
		String protName = read.excelRead(1);
		String ownrName = read.excelRead(2);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		String portfolioName = WorkerHelper.excelRead(3);
		av.navCreatePortfolio(AppName, ownrName);
		av.openPortfolioDashboardDetails(AppName);
		rp.navPolicys();
		String policyName = "NewQATest";
		av.policyCreation(policyName);

		String[] Applicationpermission = { "EDIT", "EXECUTE", "VIEW" };
		av.addPolicy(policyName, "Application", Applicationpermission, "*");

		String[] Environment = { "CREATE", "DELETE", "EDIT", "EXECUTE", "VIEW" };
		av.addPolicy(policyName, "Environment", Environment, "*");

		String[] pipeline = { "CREATE", "DELETE", "EDIT", "EXECUTE", "VIEW" };
		av.addPolicy(policyName, "Pipeline", pipeline, "*");

		String[] Policy = { "CREATE", "EXECUTE", "EDIT", "VIEW" };
		av.addPolicy(policyName, "Policy", Policy, "*");

		String[] Portfolio = { "DELETE", "EDIT", "VIEW" };
		av.addPolicy(policyName, "Portfolio", Portfolio, "*");

		String[] Release = { "CREATE", "EDIT", "EXECUTE", "VIEW" };
		av.addPolicy(policyName, "Release", Release, "*");

		String[] Reports = { "VIEW" };
		av.addPolicy(policyName, "Reports", Reports, "*");

		String[] Variables = { "CREATE", "DELETE", "EDIT", "EXECUTE", "VIEW" };
		av.addPolicy(policyName, "Variables", Variables, "*");

		String[] Worker = { "CREATE", "DELETE", "VIEW" };
		av.addPolicy(policyName, "Worker", Worker, "*");

		String[] username = { "satheshkumar.m@infosys.com" };
		av.addPolicyUser(policyName, username);

		av.clickOnSavePolicy(policyName);
		Thread.sleep(3000);
		driver.navigate().refresh();
		rp.navPolicys();
		String newEmail = "vignesh.mj@infosys.com";
		rp.UpdatePolicysPortfolio(policyName, newEmail);
		av.clickOnSavePolicy(policyName);
		Thread.sleep(5000);

		String ApplicationNames = AppName + "Te";

		String portfolioNames = AppName;
		av.navCreateApplication(ApplicationNames, portfolioNames, ownrName);

		WebDriver drivers = av.loginWithNewUser();
		WorkerHelper wh1 = new WorkerHelper(drivers);
		ReportHelper rp1 = new ReportHelper(drivers);
		PipelineHelper ph1 = new PipelineHelper(drivers);
		drivers.navigate().refresh();
		Thread.sleep(5000);
		ApplicationView av1 = new ApplicationView(drivers);
		av1.openApplicationDashboard(ApplicationNames);
		av1.navToApplicationGeneral();
		av1.editApplication("Automation_Functional_test");
		av1.openApplicationDashboard(ApplicationNames);
		av1.navToApplicationGeneral();
		av1.editApplication(portfolioNames);

		av1.openPortfolioDashboardDetails(AppName);
		rp1.navPolicys();
		String policyName1 = "NewQATest1";
		av1.policyCreation(policyName1);

		String[] Applicationpermission1 = { "EDIT", "EXECUTE", "VIEW" };
		av1.addPolicy(policyName1, "Application", Applicationpermission1, "*");

		String[] Environment1 = { "CREATE", "DELETE", "EDIT", "EXECUTE", "VIEW" };
		av1.addPolicy(policyName1, "Environment", Environment1, "*");

		String[] pipeline1 = { "CREATE", "DELETE", "EDIT", "EXECUTE", "VIEW" };
		av1.addPolicy(policyName1, "Pipeline", pipeline1, "*");

		String[] Policy1 = { "CREATE", "EXECUTE", "EDIT", "VIEW" };
		av1.addPolicy(policyName1, "Policy", Policy1, "*");

		String[] Portfolio1 = { "DELETE", "EDIT", "VIEW" };
		av1.addPolicy(policyName1, "Portfolio", Portfolio1, "*");

		String[] Release1 = { "CREATE", "EDIT", "EXECUTE", "VIEW" };
		av1.addPolicy(policyName1, "Release", Release1, "*");

		String[] Reports1 = { "VIEW" };
		av1.addPolicy(policyName1, "Reports", Reports1, "*");

		String[] Variables1 = { "CREATE", "DELETE", "EDIT", "EXECUTE", "VIEW" };
		av1.addPolicy(policyName1, "Variables", Variables1, "*");

		String[] Worker1 = { "CREATE", "DELETE", "VIEW" };
		av1.addPolicy(policyName1, "Worker", Worker1, "*");

		String[] username1 = { "suriya.n02@infosys.com" };
		av1.addPolicyUser(policyName1, username1);

		av1.clickOnSavePolicy(policyName1);
		Thread.sleep(3000);
		drivers.navigate().refresh();
		rp1.navPolicys();
		String newEmail1 = "bhavadeep.sopparama@infosys.com";
		rp1.UpdatePolicysPortfolio(policyName1, newEmail1);
		av1.clickOnSavePolicy(policyName1);
		Thread.sleep(5000);

		av1.scrollUp();

		av1.naviToEnvir();
		av1.environmentCreation("TestEnv", "QA");
		Boolean value = av1.verifyAfterCreateEnv("TestEnv", "QA");
		assertTrue(value);
		av1.updateEnv("TestEnv", "QA", "Prod");
		av1.naviToEnvir();
		av1.deleteEnv("TestEnv");

		av1.environmentCreation("TestEnv", "QA");
		av1.environmentCreation("TestEnv1", "Prod");

		rp1.variableCreation();

		av1.naviToRelease();

		String ReleaseName = "n";
		String envName = "TestEnv";
		String updatedenvName = "TestEnv1";

		String releaseName = av1.releaseCreation(ReleaseName, envName);
		av1.scrolldown();
		av1.itemPerpageDynamic("100");
		av1.scrolldown();
		Boolean values = av1.verifyAfterCreateRelease(releaseName, envName);
		assertTrue(values);
		av1.updateReleaseDetails(releaseName, updatedenvName);
		av1.itemPerpageDynamic("100");
		av1.scrolldown();
//		Boolean value1 = av1.verifyAfterCreateRelease(releaseName, updatedenvName);
//		assertTrue(value1);

		av1.naviToWorker();
		String workerName = wh.excelRead(1) + 2;
		String WorkerNames = portfolioNames + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		wh1.workerAgentNameFinder();
		wh1.addWorker(workerName);
		wh1.tokenRefresh(WorkerNames);
		wh1.deleteWorker(WorkerNames);
		Thread.sleep(5000);
		drivers.navigate().refresh();

		av1.naviToWorker();
		wh1.addWorker(workerName);
		wh1.downloadWorker();
		wh1.agentRunning(WorkerNames);
		wh1.workerPageRefresh();
		av1.naviToWorker();
		wh1.instanceVerifycation("IdleInstance", WorkerNames, "0", "1");

		ph1.navigateToPipeline();
		ph1.navigateToVisualEditor();
		ph1.createPipelineName(ApplicationNames, pipelineName);
		Thread.sleep(2000);
		ph1.addStageInPipelineWithoutEnv_Worker("stage1");
		ph1.dyanamicStepAndPlugin("stage1", "sleep");
		ph1.addSleepInput("sleep", "100");
		ph1.submitPipeline();
		PipelineHelper reportH1 = PageFactory.initElements(drivers, PipelineHelper.class);
		reportH1.triggerPipelineUsingIcon();
		ph1.workerSelectionInPipelineTrigger(WorkerNames);
		ph1.triggerPipelineUsingButton();

		Thread.sleep(10000);
		String agentName = wh1.agentNames();
		String foldername = ApplicationNames + "_" + pipelineName;

//		av1.clickOnSavePolicy(policyName1);
//
//		av1.openApplicationDashboard(ApplicationNames);
//		av1.navToApplicationGeneral();
//		rp1.navPolicys();
//		String policyName1 = "NewQATest1";
//		av1.policyCreation(policyName1);
//
//		String[] Applicationpermission = { "EDIT", "EXECUTE", "VIEW" };
//		av1.addPolicy(policyName1, "Application", Applicationpermission, "*");
//
//		String[] BulkNotifications = { "DELETE", };
//		av1.addPolicy(policyName1, "Bulk Notifications", BulkNotifications, "*");
//
//		String[] DeveloperAnalytics = { "EXECUTIVE", "MANAGER", "USER" };
//		av1.addPolicy(policyName1, "Developer Analytics", DeveloperAnalytics, "*");
//
//		String[] Environment = { "CREATE", "DELETE", "EDIT", "EXECUTE", "VIEW" };
//		av1.addPolicy(policyName1, "Environment", Environment, "*");
//
//		String[] pipeline = { "CREATE", "DELETE", "EDIT", "EXECUTE", "VIEW" };
//		av1.addPolicy(policyName1, "Pipeline", pipeline, "*");
//
//		String[] Policy = { "CREATE", "EXECUTE", "EDIT", "VIEW" };
//		av1.addPolicy(policyName1, "Policy", Policy, "*");
//
//		String[] Release = { "CREATE", "EDIT", "EXECUTE", "VIEW" };
//		av1.addPolicy(policyName1, "Release", Release, "*");
//
//		String[] stage = { "CREATE", "EXECUTE", "EDIT", "VIEW" };
//		av1.addPolicy(policyName1, "Stage", stage, "*");
//
//		String[] Variables = { "CREATE", "DELETE", "EDIT", "EXECUTE", "VIEW" };
//		av1.addPolicy(policyName1, "Variables", Variables, "*");
//
//		String[] Worker = { "CREATE", "DELETE", "VIEW" };
//		av1.addPolicy(policyName1, "Worker", Worker, "*");
//
//		String[] Workflow_Application = { "CREATE", "DELETE", "EDIT", "EXECUTE" };
//		av1.addPolicy(policyName1, "Workflow(Application)", Workflow_Application, "*");
//
//		String[] username = { "vignesh.mj@infosys.com" };
//		av1.addPolicyUser(policyName1, username);
//
//		av1.clickOnSavePolicy(policyName1);
//
//		rp1.environmentCreation();
//		rp1.variableCreation();
//		rp1.releaseCreation();
//		rp1.workerCreation();
//		rp1.pipelineCreation();
//		rp1.workfloCcreation();

//		rp.navPolicys(drivers);
//		rp.deletePolicys(policyName);
	}

//	@Test(enabled = false, priority = 11)
//	public void validate_Application_Level_AuditLogs_EachModule_Create_Scenario()
//			throws Exception {
//		driver = ListenersTest.driver;
//		logger = ListenersTest.logger;
//		ReportHelper reportH = PageFactory.initElements(driver, ReportHelper.class);
//		ReportHelper rp = new ReportHelper(driver);
//		ApplicationView av = new ApplicationView(driver);
//		PipelineView pv = new PipelineView(driver);
//		VisualTestingHelper helper = new VisualTestingHelper();
//		helper.eyesstichMode(VisualTestingStatus);
//		helper.eyeopenApplitool(driver, "validate_Application_Level_AuditLogs_EachModule_Create_Scenario",
//				VisualTestingStatus);
//		ExcelWriter write = new ExcelWriter();
//		ExcelReader read = new ExcelReader();
//		WorkerHelper wh = new WorkerHelper(driver);
//		PipelineHelper ph = new PipelineHelper(driver);
//		write.ExcelWriter();
//		rp.applicationNav();
//		reportH.newPolicyCreation("QATest");
//		rp.environmentCreation();
//		rp.variableCreation();
//		rp.releaseCreation();
//		rp.workerCreation();
//		rp.pipelineCreation();
//		rp.workfloCcreation();
//
//		driver.navigate().refresh();
//		String portfolioName = wh.excelRead(3);
//		av.openPortfolioDashboard(portfolioName);
//		ph.scrollDownToAutilogs();
//
//		String[] creations = { "CREATE_APPLICATION", "CREATE_POLICY", "CREATE_ENVIRONMENTS", "CREATE_VARIABLES",
//				"CREATE_RELEASES", "CREATE_WORKER", "CREATE_PIPELINE", "CREATE_WORKFLOW" };
//
//		int num = 0;
//		for (int i = 8; i >= 1; i--) {
//			String s = String.valueOf(i);
//			Boolean Value = ph.VerifyActionRowsForAll(s, creations[num]);
//			assertTrue(Value);
//			log.info(s + "and value is ==> " + creations[num]);
//			num++;
//		}
//
//		String AppName = read.excelRead(0);
//		String workerValue = read.excelRead(9);
//		String newValue = workerValue.replace(";", "");
//
//		String appName1 = read.excelRead(9);
//		String newValue1 = appName1.replace(";", "");
//		String appNames = newValue1.replace("_", "/");
//		String[] v = appNames.split("/");
//		String appFirstName = v[0];
//		String appLastName = v[1];
//		String appNameworkFlow = read.excelRead(11);
//		String workflowName = appNameworkFlow.replace(";", "");
//		log.info(appFirstName + "/pipeline" + appLastName);
//
//		ph.applicationVerifyViews(8, "CREATE_APPLICATION", AppName, "name", AppName);
//		ph.VerifyViews(7, "CREATE_POLICY", AppName, "policyName", "QATest");
//		ph.VerifyViews(6, "CREATE_ENVIRONMENTS", AppName, "name", "QATest");
//		ph.VerifyViews(5, "CREATE_VARIABLES", AppName, "name", "UserName");
//		ph.VerifyViews(4, "CREATE_RELEASES", AppName, "relNo", "NewOne");
//		ph.VerifyViews(3, "CREATE_WORKER", AppName, "name", newValue);
//		ph.VerifyViews(2, "CREATE_PIPELINE", appFirstName + "/pipeline" + appLastName, "name",
//				"pipeline" + appLastName);
//		ph.VerifyViews(1, "CREATE_WORKFLOW", AppName, "workflowName", workflowName);
//		helper.eyesTargetWindow("validate_Application_Level_AuditLogs_EachModule_Create_Scenario", VisualTestingStatus);
//
//		String agentName = wh.agentNames();
//		driver.navigate().refresh();
//		av.openApplicationDashboard(AppName);
//		String[] newenvironmentValue = newValue.split("_");
//		String workerNewName = newenvironmentValue[1];
//		wh.deleteWorker(workerNewName);
//		helper.eyeclose(VisualTestingStatus);
//	}

}
