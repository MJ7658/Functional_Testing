package org.idp.auditlogs;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.ArrayList;

import org.infy.commonHelpers.PipelineHelper;
import org.infy.commonHelpers.ToolsIntegrationHelper;
import org.infy.commonHelpers.WorkerHelper;
import org.infy.init.ExcelReader;
import org.infy.init.ExcelWriter;
import org.infy.init.ListenersTest;
import org.infy.init.LoadData;
import org.infy.mongodbRead.MongoReadData;
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
public class TC_AuditLogs extends LoadData {
	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();

////	@Test(enabled = true, priority = 1)
////	@org.testng.annotations.Parameters("VisualTestingStatus")
////	public void validate_the_create_pipeline() throws Exception {
////		driver = ListenersTest.driver;
////		logger = ListenersTest.logger;
////		ApplicationView av = new ApplicationView(driver);
////		PipelineView pv = new PipelineView(driver);
////		WorkerHelper wh = new WorkerHelper(driver);
////		ReportHelper rp = new ReportHelper(driver);
////		PipelineHelper ph = new PipelineHelper(driver);
////		VisualTestingHelper helper = new VisualTestingHelper();
////		helper.eyesstichMode(VisualTestingStatus);
////		helper.eyeopenApplitool(driver, "validate_the_create_pipeline", VisualTestingStatus);
////		WorkerHelper.workerExcelWriter();
////		String applicationName = WorkerHelper.excelRead(0);
////		av.openApplicationDashboard(applicationName);
////		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
////		String workerName = wh.excelRead(1);
////		String WorkerNames = applicationName + "_" + workerName;
////		wh.workerAgentNameFinder();
////		wh.addWorker(workerName);
////		wh.downloadWorker();
////		wh.agentRunning(WorkerNames);
////		String pipelineName = "pipeline" + workerName;
////		wh.workerPageRefresh();
////		ph.navigateToPipeline();
////		ph.navigateToVisualEditor();
////		ph.createPipelineName(applicationName, pipelineName);
////		Thread.sleep(2000);
////		ph.addStageInPipeline("stage1", "qa", WorkerNames);
////		ph.createStepeForlessTimeout();
////		ph.submitPipeline();
////		ph.triggerPipelineUsingIcon();
////		ph.workerSelectionInPipelineTrigger(WorkerNames);
////		ph.triggerPipelineUsingButton();
////		// ph.pipelineTriggerVerificaton();
////		ph.pipelineRunningVerifycation(pipelineName, WorkerNames);
////		String foldername = applicationName + "_" + pipelineName;
////		ph.pipelineAuditPageNav(foldername);
////		Boolean value = ph.VerifyActionRows("2", "CREATE_PIPELINE");
////		assertTrue(value);
////		helper.eyesTargetWindow("Validate create Pipeline pipeline Level", VisualTestingStatus);
////		String viewValues = ph.VerifyView("CREATE_PIPELINE", pipelineName);
////		String path = System.getProperty("user.dir") + "//Data//testout.txt";
////		ph.WriteNote(path, viewValues);
////		ph.newone(path, "name", pipelineName);
////		ph.clickOnOK();
////		wh.delectFilescustom("\\Data", "testout.txt");
////
////		driver.navigate().refresh();
////		String portfolioName = wh.excelRead(3);
////		av.openPortfolioDashboard(portfolioName);
////		Boolean portfolioValue = ph.VerifyActionRows("1", "CREATE_PIPELINE");
////		assertTrue(portfolioValue);
////		helper.eyesTargetWindow("Validate create Pipeline portfolio Level", VisualTestingStatus);
////		String viewValues1 = ph.VerifyView("CREATE_PIPELINE", foldername);
////		ph.WriteNote(path, viewValues1);
////		ph.newone(path, "name", pipelineName);
////		ph.clickOnOK();
////		wh.delectFilescustom("\\Data", "testout.txt");
////
////		driver.navigate().refresh();
////		av.openApplicationDashboardAudi(applicationName);
////		Boolean appValue = ph.VerifyActionRows("1", "CREATE_PIPELINE");
////		assertTrue(appValue);
////		String viewValues2 = ph.VerifyView("CREATE_PIPELINE", foldername);
////		helper.eyesTargetWindow("Validate create Pipeline application Level", VisualTestingStatus);
////		ph.WriteNote(path, viewValues2);
////		ph.newone(path, "name", pipelineName);
////		ph.clickOnOK();
////		wh.delectFilescustom("\\Data", "testout.txt");
////		String agentName = wh.agentNames();
////		driver.navigate().refresh();
////		ApplicationView avPagefactory = PageFactory.initElements(driver, ApplicationView.class);
////		avPagefactory.openApplicationDashboardNew(applicationName);
////		// av.openApplicationDashboard(applicationName);
////		wh.finalSteps(agentName, WorkerNames, foldername);
////		helper.eyeclose(VisualTestingStatus);
////	}
////
////	@Test(enabled = true, priority = 2)
////	@org.testng.annotations.Parameters("VisualTestingStatus")
////	public void validate_the_update_pipeline() throws Exception {
////		driver = ListenersTest.driver;
////		logger = ListenersTest.logger;
////		ApplicationView av = new ApplicationView(driver);
////		PipelineView pv = new PipelineView(driver);
////		WorkerHelper wh = new WorkerHelper(driver);
////		ReportHelper rp = new ReportHelper(driver);
////		PipelineHelper ph = new PipelineHelper(driver);
////		VisualTestingHelper helper = new VisualTestingHelper();
////		helper.eyesstichMode(VisualTestingStatus);
////		helper.eyeopenApplitool(driver, "validate_the_update_pipeline", VisualTestingStatus);
////		WorkerHelper.workerExcelWriter();
////		String applicationName = WorkerHelper.excelRead(0);
////		av.openApplicationDashboard(applicationName);
////		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
////		String workerName = wh.excelRead(1);
////		String WorkerNames = applicationName + "_" + workerName;
////		wh.workerAgentNameFinder();
////		wh.addWorker(workerName);
////		wh.downloadWorker();
////		wh.agentRunning(WorkerNames);
////		String pipelineName = "pipeline" + workerName;
////		wh.workerPageRefresh();
////		ph.navigateToPipeline();
////		ph.navigateToVisualEditor();
////		ph.createPipelineName(applicationName, pipelineName);
////		Thread.sleep(2000);
////		ph.addStageInPipeline("stage1", "qa", WorkerNames);
////		ph.createStepeForlessTimeout();
////		ph.submitPipeline();
////		Thread.sleep(2000);
////		PipelineHelper reportH = PageFactory.initElements(driver, PipelineHelper.class);
////		reportH.triggerPipelineUsingIcon();
////		ph.workerSelectionInPipelineTrigger(WorkerNames);
////		ph.triggerPipelineUsingButton();
////		ph.pipelineTriggerVerificaton();
////		ph.pipelineRunningVerifycation(pipelineName, WorkerNames);
////		String foldername = applicationName + "_" + pipelineName;
////		ph.navLogsDetails(foldername);
////		String details = "java -version";
////		pv.editPlugin("cmdexec", details);
////		Thread.sleep(5000);
////		ph.submitPipeline();
////		Thread.sleep(5000);
////		reportH.triggerPipelineUsingIcon();
////		ph.workerSelectionInPipelineTrigger(WorkerNames);
////		ph.triggerPipelineUsingButton();
////		ph.pipelineTriggerVerificaton();
////		Thread.sleep(5000);
////		ph.pipelineAuditPageNav(foldername);
////		Boolean value = ph.VerifyActionRows("2", "UPDATE_PIPELINE");
////		assertTrue(value);
////		helper.eyesTargetWindow("Validate UPDATE_PIPELINE pipeline Level", VisualTestingStatus);
////		String viewValues = ph.VerifyView("UPDATE_PIPELINE", foldername);
////		String path = System.getProperty("user.dir") + "//Data//testout.txt";
////		ph.WriteNote(path, viewValues);
////		ph.newone(path, "0", details);
////		ph.clickOnOK();
////		wh.delectFilescustom("\\Data", "testout.txt");
////		Thread.sleep(5000);
////		driver.navigate().refresh();
////		String portfolioName = wh.excelRead(3);
////		av.openPortfolioDashboard(portfolioName);
////		Boolean portfolioValue = ph.VerifyActionRows("1", "UPDATE_PIPELINE");
////		assertTrue(portfolioValue);
////		helper.eyesTargetWindow("Validate UPDATE_PIPELINE portfolio Level", VisualTestingStatus);
////		String viewValues1 = ph.VerifyView("UPDATE_PIPELINE", foldername);
////		ph.WriteNote(path, viewValues1);
////		ph.newone(path, "0", details);
////		ph.clickOnOK();
////		wh.delectFilescustom("\\Data", "testout.txt");
////		Thread.sleep(3000);
////		driver.navigate().refresh();
////		av.openApplicationDashboardAudi(applicationName);
////		Boolean appValue = ph.VerifyActionRows("1", "UPDATE_PIPELINE");
////		assertTrue(appValue);
////		helper.eyesTargetWindow("Validate UPDATE_PIPELINE Application Level", VisualTestingStatus);
////		String viewValues2 = ph.VerifyView("UPDATE_PIPELINE", foldername);
////		ph.WriteNote(path, viewValues2);
////		ph.newone(path, "0", details);
////		ph.clickOnOK();
////		wh.delectFilescustom("\\Data", "testout.txt");
////		String agentName = wh.agentNames();
////		driver.navigate().refresh();
////		av.openApplicationDashboard(applicationName);
////		wh.finalSteps(agentName, WorkerNames, foldername);
////		helper.eyeclose(VisualTestingStatus);
////	}
////
////	@Test(enabled = true, priority = 3)
////	@org.testng.annotations.Parameters("VisualTestingStatus")
////	public void validate_without_Create_pipeline_details_Auditlogs_should_not_update()
////			throws Exception {
////		driver = ListenersTest.driver;
////		logger = ListenersTest.logger;
////		ApplicationView av = new ApplicationView(driver);
////		PipelineView pv = new PipelineView(driver);
////		WorkerHelper wh = new WorkerHelper(driver);
////		ReportHelper rp = new ReportHelper(driver);
////		PipelineHelper ph = new PipelineHelper(driver);
////		VisualTestingHelper helper = new VisualTestingHelper();
////		helper.eyesstichMode(VisualTestingStatus);
////		helper.eyeopenApplitool(driver, "validate_without_Create_pipeline_details_Auditlogs_should_not_update",
////				VisualTestingStatus);
////		WorkerHelper.workerExcelWriter();
////		String applicationName = WorkerHelper.excelRead(0);
////		av.openApplicationDashboard(applicationName);
////		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
////		String workerName = wh.excelRead(1);
////		String WorkerNames = applicationName + "_" + workerName;
////		wh.workerAgentNameFinder();
////		wh.addWorker(workerName);
////		wh.downloadWorker();
////		wh.agentRunning(WorkerNames);
////		String pipelineName = "pipeline" + workerName;
////		wh.workerPageRefresh();
////		ph.navigateToPipeline();
////		ph.navigateToVisualEditor();
////		ph.createPipelineName(applicationName, pipelineName);
////		Thread.sleep(2000);
////		ph.addStageInPipeline("stage1", "qa", WorkerNames);
////		ph.createStepeForlessTimeout();
////		ph.submitCancelPipeline();
////		Thread.sleep(5000);
////		String foldername = applicationName + "_" + pipelineName;
////
////		driver.navigate().refresh();
////		Thread.sleep(3000);
////		String portfolioName = wh.excelRead(3);
////		av.openPortfolioDashboard(portfolioName);
////		Boolean portfolioValue = ph.VerifyActionRowsWithoutAddPipline("1", foldername);
////		assertFalse(portfolioValue);
////		Thread.sleep(2000);
////		driver.navigate().refresh();
////		Thread.sleep(3000);
////		av.openApplicationDashboardAudi(applicationName);
////		Boolean appValue = ph.VerifyActionRowsWithoutAddPipline("1", foldername);
////		assertFalse(appValue);
////		helper.eyesTargetWindow("VerifyActionRowsWithoutAddPipline", VisualTestingStatus);
////		String agentName = wh.agentNames();
////		driver.navigate().refresh();
////		av.openApplicationDashboard(applicationName);
////		wh.finalSteps(agentName, WorkerNames, foldername);
////		helper.eyeclose(VisualTestingStatus);
////	}
////
////	@Test(enabled = true, priority = 4)
////	@org.testng.annotations.Parameters("VisualTestingStatus")
////	public void validate_the_Without_update_pipeline() throws Exception {
////		driver = ListenersTest.driver;
////		logger = ListenersTest.logger;
////		ApplicationView av = new ApplicationView(driver);
////		PipelineView pv = new PipelineView(driver);
////		WorkerHelper wh = new WorkerHelper(driver);
////		ReportHelper rp = new ReportHelper(driver);
////		PipelineHelper ph = new PipelineHelper(driver);
////		VisualTestingHelper helper = new VisualTestingHelper();
////		helper.eyesstichMode(VisualTestingStatus);
////		helper.eyeopenApplitool(driver, "validate_the_Without_update_pipeline", VisualTestingStatus);
////		WorkerHelper.workerExcelWriter();
////		String applicationName = WorkerHelper.excelRead(0);
////		av.openApplicationDashboard(applicationName);
////		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
////		String workerName = wh.excelRead(1);
////		String WorkerNames = applicationName + "_" + workerName;
////		wh.workerAgentNameFinder();
////		wh.addWorker(workerName);
////		wh.downloadWorker();
////		wh.agentRunning(WorkerNames);
////		String pipelineName = "pipeline" + workerName;
////		wh.workerPageRefresh();
////		ph.navigateToPipeline();
////		ph.navigateToVisualEditor();
////		ph.createPipelineName(applicationName, pipelineName);
////		Thread.sleep(2000);
////		ph.addStageInPipeline("stage1", "qa", WorkerNames);
////		ph.createStepeForlessTimeout();
////		ph.submitPipeline();
////		PipelineHelper reportH = PageFactory.initElements(driver, PipelineHelper.class);
////		reportH.triggerPipelineUsingIcon();
////		ph.workerSelectionInPipelineTrigger(WorkerNames);
////		ph.triggerPipelineUsingButton();
////		ph.pipelineTriggerVerificaton();
////		ph.pipelineRunningVerifycation(pipelineName, WorkerNames);
////		String foldername = applicationName + "_" + pipelineName;
////		ph.navLogsDetails(foldername);
////		String details = "java -version";
////		pv.editPlugin("cmdexec", details);
////
////		ph.submitCancelPipeline();
////		reportH.triggerPipelineUsingIcon();
////		ph.workerSelectionInPipelineTrigger(WorkerNames);
////		ph.triggerPipelineUsingButton();
////		ph.pipelineTriggerVerificaton();
////		ph.pipelineAuditPageNav(foldername);
////		Boolean value = ph.VerifyActionRows("2", "UPDATE_PIPELINE");
////		assertFalse(value);
////		helper.eyesTargetWindow("Validate without update Pipeline in pipeline Level", VisualTestingStatus);
////		driver.navigate().refresh();
////		String portfolioName = wh.excelRead(3);
////		av.openPortfolioDashboard(portfolioName);
////		Boolean portfolioValue = ph.VerifyActionRows("1", "UPDATE_PIPELINE");
////		assertFalse(portfolioValue);
////		helper.eyesTargetWindow("Validate without update Pipeline in Portfolio Level", VisualTestingStatus);
////
////		driver.navigate().refresh();
////		av.openApplicationDashboardAudi(applicationName);
////		Boolean appValue = ph.VerifyActionRows("1", "UPDATE_PIPELINE");
////		assertFalse(appValue);
////		helper.eyesTargetWindow("Validate without update Pipeline in Application Level", VisualTestingStatus);
////		String agentName = wh.agentNames();
////		driver.navigate().refresh();
////		av.openApplicationDashboard(applicationName);
////		wh.finalSteps(agentName, WorkerNames, foldername);
////		helper.eyeclose(VisualTestingStatus);
////	}
//
////	@Test(enabled = true, priority = 5) // QAUTOIDP-22968
////	public void To_Verify_newly_created_pipeline_getting_executed_successfully_data_regarding_same_getting_saved_DB()
////			throws Exception {
////		driver = ListenersTest.driver;
////		logger = ListenersTest.logger;
////		ApplicationView av = new ApplicationView(driver);
////		PipelineView pv = new PipelineView(driver);
////		WorkerHelper wh = new WorkerHelper(driver);
////		ReportHelper rp = new ReportHelper(driver);
////		PipelineHelper ph = new PipelineHelper(driver);
////		WorkerHelper.workerExcelWriter();
////		String applicationName = WorkerHelper.excelRead(0);
////		av.openApplicationDashboard(applicationName);
////		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
////		String workerName = wh.excelRead(1);
////		String WorkerNames = applicationName + "_" + workerName;
////		wh.workerAgentNameFinder();
////		wh.addWorker(workerName);
////		wh.downloadWorker();
////		wh.agentRunning(WorkerNames);
////		String pipelineName = "pipeline" + workerName;
////		wh.workerPageRefresh();
////		ph.navigateToPipeline();
////		ph.navigateToVisualEditor();
////		ph.createPipelineName(applicationName, pipelineName);
////		Thread.sleep(2000);
////		ph.addStageInPipeline("stage1", "qa", WorkerNames);
////		ph.cmdPluglessTimeoutJavaVersion();
////		ph.submitPipeline();
////		ph.triggerPipelineUsingIcon();
////		ph.workerSelectionInPipelineTrigger(WorkerNames);
////		ph.triggerPipelineUsingButton();
////		Thread.sleep(15000);
////		driver.navigate().refresh();
////
////		String[] duration = { "{$unwind:'$edata.data'}",
////				"{$match :{'object.identifiers.pipelinename':'" + pipelineName + "'}}",
////				"{$match :{'object.identifiers.applicationname':'" + applicationName + "'}}",
////				"{$project : {'edata.data.duration':1}}" };
////
////		String[] pathstatus = { "{$unwind:'$object.identifiers'}",
////				"{$match :{'object.identifiers.pipelinename':'" + pipelineName + "'}}",
////				"{$match :{'object.identifiers.applicationname':'" + applicationName + "'}}",
////				"{$project : {'eid':'PIPELINE_EXECUTION_STATUS'}}" };
////
////		MongoReadData MDB = new MongoReadData();
////		ArrayList<String> value = MDB.particularDataFetchFromMongoDBUsingQuery("platform_telemetry", duration);
////		String val = MDB.mongoDBDataVerification1(value, "edata", 5);
////		Boolean flag = true;
////
////		if (!val.equals(null)) {
////			flag = true;
////		} else {
////			flag = false;
////		}
////		assertTrue(flag);
////
////		ArrayList<String> value1 = MDB.particularDataFetchFromMongoDBUsingQuery("platform_telemetry", pathstatus);
////
////		String vals = MDB.mongoDBDataVerification(value1, "eid", "PIPELINE_EXECUTION_STATUS");
////		if (vals.equalsIgnoreCase("PIPELINE_EXECUTION_STATUS")) {
////			flag = true;
////		} else {
////			flag = false;
////		}
////		assertTrue(flag);
////		String agentName = wh.agentNames();
////		driver.navigate().refresh();
////		av.openApplicationDashboard(applicationName);
////		String foldername = applicationName + "_" + pipelineName;
////		wh.finalSteps(agentName, WorkerNames, foldername);
////	}
////
////	@Test(enabled = true, priority = 6) // QAUTOIDP-22970
////	public void To_Verify_data_not_getting_saved_DB_until_successful_execution() throws Exception {
////		driver = ListenersTest.driver;
////		logger = ListenersTest.logger;
////		ApplicationView av = new ApplicationView(driver);
////		PipelineView pv = new PipelineView(driver);
////		WorkerHelper wh = new WorkerHelper(driver);
////		ReportHelper rp = new ReportHelper(driver);
////		PipelineHelper ph = new PipelineHelper(driver);
////		WorkerHelper.workerExcelWriter();
////		String applicationName = WorkerHelper.excelRead(0);
////		av.openApplicationDashboard(applicationName);
////		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
////		String workerName = wh.excelRead(1);
////		String WorkerNames = applicationName + "_" + workerName;
////		wh.workerAgentNameFinder();
////		wh.addWorker(workerName);
////		wh.downloadWorker();
////		wh.agentRunning(WorkerNames);
////		String pipelineName = "pipeline" + workerName;
////		wh.workerPageRefresh();
////		ph.navigateToPipeline();
////		ph.navigateToVisualEditor();
////		ph.createPipelineName(applicationName, pipelineName);
////		Thread.sleep(2000);
////		ph.addStageInPipeline("stage1", "qa", WorkerNames);
////		ph.createFaildStepeForlessTimeout();
////		ph.submitPipeline();
////		ph.triggerPipelineUsingIcon();
////		ph.workerSelectionInPipelineTrigger(WorkerNames);
////		ph.triggerPipelineUsingButton();
////		Thread.sleep(10000);
////		driver.navigate().refresh();
////
////		String[] duration = { "{$unwind:'$edata.data'}",
////				"{$match :{'object.identifiers.pipelinename':'" + pipelineName + "'}}",
////				"{$match :{'object.identifiers.applicationname':'" + applicationName + "'}}",
////				"{$project : {'edata.data.duration':1}}" };
////
////		Boolean flag = true;
////		try {
////			MongoReadData MDB = new MongoReadData();
////			ArrayList<String> value = MDB.particularDataFetchFromMongoDBUsingQuery("platform_telemetry", duration);
////			String val = MDB.mongoDBDataVerification1(value, "edata", 5);
////			flag = true;
////		} catch (Exception e) {
////			flag = false;
////		}
////		assertFalse(flag);
////		String agentName = wh.agentNames();
////		driver.navigate().refresh();
////		av.openApplicationDashboard(applicationName);
////		String foldername = applicationName + "_" + pipelineName;
////		wh.finalSteps(agentName, WorkerNames, foldername);
////	}
////
////	@Test(enabled = true, priority = 7) // QAUTOIDP-22968
////	public void To_Verify_already_successfully_triggered_pipelines_data_DB_not_getting_updated() throws Exception {
////		driver = ListenersTest.driver;
////		logger = ListenersTest.logger;
////		ApplicationView av = new ApplicationView(driver);
////		PipelineView pv = new PipelineView(driver);
////		WorkerHelper wh = new WorkerHelper(driver);
////		ReportHelper rp = new ReportHelper(driver);
////		PipelineHelper ph = new PipelineHelper(driver);
////		WorkerHelper.workerExcelWriter();
////		String applicationName = WorkerHelper.excelRead(0);
////		av.openApplicationDashboard(applicationName);
////		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
////		String workerName = wh.excelRead(1);
////		String WorkerNames = applicationName + "_" + workerName;
////		wh.workerAgentNameFinder();
////		wh.addWorker(workerName);
////		wh.downloadWorker();
////		wh.agentRunning(WorkerNames);
////		String pipelineName = "pipeline" + workerName;
////		wh.workerPageRefresh();
////		ph.navigateToPipeline();
////		ph.navigateToVisualEditor();
////		ph.createPipelineName(applicationName, pipelineName);
////		Thread.sleep(2000);
////		ph.addStageInPipeline("stage1", "qa", WorkerNames);
////		ph.cmdPluglessTimeoutJavaVersion();
////		ph.submitPipeline();
////		ph.triggerPipelineUsingIcon();
////		ph.workerSelectionInPipelineTrigger(WorkerNames);
////		ph.triggerPipelineUsingButton();
////		Thread.sleep(10000);
////		driver.navigate().refresh();
////
////		String[] duration = { "{$unwind:'$edata.data'}",
////				"{$match :{'object.identifiers.pipelinename':'" + pipelineName + "'}}",
////				"{$match :{'object.identifiers.applicationname':'" + applicationName + "'}}",
////				"{$project : {'edata.data.duration':1}}" };
////
////		String[] pathstatus = { "{$unwind:'$object.identifiers'}",
////				"{$match :{'object.identifiers.pipelinename':'" + pipelineName + "'}}",
////				"{$match :{'object.identifiers.applicationname':'" + applicationName + "'}}",
////				"{$project : {'eid':'PIPELINE_EXECUTION_STATUS'}}" };
////
////		MongoReadData MDB = new MongoReadData();
////		ArrayList<String> value = MDB.particularDataFetchFromMongoDBUsingQuery("platform_telemetry", duration);
////		String val = MDB.mongoDBDataVerification1(value, "edata", 5);
////		Boolean flag = true;
////
////		if (!val.equals(null)) {
////			flag = true;
////		} else {
////			flag = false;
////		}
////		assertTrue(flag);
////
////		ArrayList<String> value1 = MDB.particularDataFetchFromMongoDBUsingQuery("platform_telemetry", pathstatus);
////
////		String vals = MDB.mongoDBDataVerification(value1, "eid", "PIPELINE_EXECUTION_STATUS");
////		if (vals.equalsIgnoreCase("PIPELINE_EXECUTION_STATUS")) {
////			flag = true;
////		} else {
////			flag = false;
////		}
////		assertTrue(flag);
////
////		driver.navigate().refresh();
////		String WorkerNames1 = applicationName + "_" + workerName;
////		String pipelineName1 = "pipeline" + workerName + 1;
////		ph.navigateToPipeline();
////		ph.navigateToVisualEditor();
////		ph.createPipelineName(applicationName, pipelineName1);
////		Thread.sleep(2000);
////		ph.addStageInPipeline("stage1", "qa", WorkerNames1);
////		ph.cmdPluglessTimeoutJavaVersion();
////		ph.submitPipeline();
////		ph.triggerPipelineUsingIcon();
////		ph.workerSelectionInPipelineTrigger(WorkerNames1);
////		ph.triggerPipelineUsingButton();
////		Thread.sleep(10000);
////		driver.navigate().refresh();
////
////		ArrayList<String> newvalue = MDB.particularDataFetchFromMongoDBUsingQuery("platform_telemetry", duration);
////		String newval = MDB.mongoDBDataVerification1(newvalue, "edata", 5);
////		Boolean newflag = true;
////
////		if (!newval.equals(null)) {
////			newflag = true;
////		} else {
////			newflag = false;
////		}
////		assertTrue(newflag);
////
////		ArrayList<String> newvalue1 = MDB.particularDataFetchFromMongoDBUsingQuery("platform_telemetry", pathstatus);
////
////		String newvals = MDB.mongoDBDataVerification(newvalue1, "eid", "PIPELINE_EXECUTION_STATUS");
////
////		if (newvals.equalsIgnoreCase("PIPELINE_EXECUTION_STATUS")) {
////			newflag = true;
////		} else {
////			newflag = false;
////		}
////		assertTrue(newflag);
////
////		String agentName = wh.agentNames();
////		driver.navigate().refresh();
////		av.openApplicationDashboard(applicationName);
////		String foldername = applicationName + "_" + pipelineName;
////		wh.finalSteps(agentName, WorkerNames, foldername);
////	}
////
	@Test(enabled = true, priority = 8) // QAUTOIDP-20809
	public void To_Verify_changes_made_Tools_Compliance_Dashboard_getting_audited_Portfolio_Audit_Tab()
			throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		String portfolioName = wh.excelRead(3);
		av.openPortfolioDashboardDetails(portfolioName);
		av.navToToolsComp();
		av.deleteTool();
		av.addTool("cmdexec");
		av.navToGating();
		av.navToAddGating();
		av.fileGateDetails();
		av.clickOnSave();
		driver.navigate().refresh();
		av.openPortfolioDashboard(portfolioName);
		Boolean portfolioValue = ph.VerifyActionRows("1", "CREATE GATES");
		assertFalse(portfolioValue);
		driver.navigate().refresh();
		av.openPortfolioDashboardDetails(portfolioName);
		av.navToToolsComp();
		av.navToGating();
		av.clickOnDELETE();
		// av.deleteTools();
	}
//
////	@Test(enabled = true, priority = 9)
////	@org.testng.annotations.Parameters("VisualTestingStatus")
////	public void validate_Pipeline_parameter_piplevel_prot_appLevel() throws Exception {
////		driver = ListenersTest.driver;
////		logger = ListenersTest.logger;
////		ApplicationView av = new ApplicationView(driver);
////		PipelineView pv = new PipelineView(driver);
////		WorkerHelper wh = new WorkerHelper(driver);
////		ReportHelper rp = new ReportHelper(driver);
////		PipelineHelper ph = new PipelineHelper(driver);
////		VisualTestingHelper helper = new VisualTestingHelper();
////		helper.eyesstichMode(VisualTestingStatus);
////		helper.eyeopenApplitool(driver, "validate_Pipeline_parameter_piplevel_prot_appLevel", VisualTestingStatus);
////		WorkerHelper.workerExcelWriter();
////		String applicationName = WorkerHelper.excelRead(0);
////		av.openApplicationDashboard(applicationName);
////		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
////		String workerName = wh.excelRead(1);
////		String WorkerNames = applicationName + "_" + workerName;
////		wh.workerAgentNameFinder();
////		wh.addWorker(workerName);
////		wh.downloadWorker();
////		wh.agentRunning(WorkerNames);
////		String pipelineName = "pipeline" + workerName;
////		wh.workerPageRefresh();
////		ph.navigateToPipeline();
////		ph.navigateToVisualEditor();
////		ph.createPipelineName(applicationName, pipelineName);
////		Thread.sleep(2000);
////		ph.addStageInPipeline("stage1", "qa", WorkerNames);
////		ph.createStepeForlessTimeout();
////		ph.submitPipeline();
////		ph.scrollUP();
////		PipelineConfigResultHelper prh = new PipelineConfigResultHelper(driver);
////		String parameters = prh.excelRead(6);
////		String[] parameter = parameters.split(":");
////		String variablenName = parameter[0];
////		String defaultName = parameter[1];
////		String defaults = defaultName.replace(";", "");
////		ph.createParameter(variablenName, defaults);
////
////		ph.submitPipeline();
////		ph.triggerPipelineUsingIcon();
////		ph.workerSelectionInPipelineTrigger(WorkerNames);
////		ph.triggerPipelineUsingButton();
////		ph.pipelineTriggerVerificaton();
////
////		String foldername = applicationName + "_" + pipelineName;
////		ph.pipelineAuditPageNav(foldername);
////		Boolean value = ph.VerifyActionRows("3", "CREATE_PIPELINE");
////		assertTrue(value);
////
////		String viewValues = ph.VerifyView("CREATE_PIPELINE", foldername);
////		helper.eyesTargetWindow("validate_Pipeline_parameter_pipeline level", VisualTestingStatus);
////		String path = System.getProperty("user.dir") + "//Data//testout.txt";
////		ph.WriteNote(path, viewValues);
////		ph.newone(path, "key", "UserName");
////		ph.clickOnOK();
////		wh.delectFilescustom("\\Data", "testout.txt");
////
////		driver.navigate().refresh();
////		String portfolioName = wh.excelRead(3);
////		av.openPortfolioDashboard(portfolioName);
////		Boolean portfolioValue = ph.VerifyActionRows("2", "CREATE_PIPELINE");
////		assertTrue(portfolioValue);
////		helper.eyesTargetWindow("validate_Pipeline_parameter_portfolio level", VisualTestingStatus);
////		String viewValues1 = ph.VerifyView("CREATE_PIPELINE", foldername);
////		ph.WriteNote(path, viewValues1);
////		ph.newone(path, "key", "UserName");
////		ph.clickOnOK();
////		wh.delectFilescustom("\\Data", "testout.txt");
////
////		driver.navigate().refresh();
////		av.openApplicationDashboardAudi(applicationName);
////		Boolean appValue = ph.VerifyActionRows("2", "CREATE_PIPELINE");
////		assertTrue(appValue);
////		helper.eyesTargetWindow("validate_Pipeline_parameter_Application level", VisualTestingStatus);
////		String viewValues2 = ph.VerifyView("CREATE_PIPELINE", foldername);
////		ph.WriteNote(path, viewValues2);
////		ph.newone(path, "key", "UserName");
////		ph.clickOnOK();
////		wh.delectFilescustom("\\Data", "testout.txt");
////
////		String agentName = wh.agentNames();
////		driver.navigate().refresh();
////		av.openApplicationDashboard(applicationName);
////		wh.finalSteps(agentName, WorkerNames, foldername);
////		helper.eyeclose(VisualTestingStatus);
////	}
////
////	@Test(enabled = true, priority = 10)
////	@org.testng.annotations.Parameters("VisualTestingStatus")
////	public void validate_Pipeline_updated_parameter_piplevel_prot_appLevel()
////			throws Exception {
////		driver = ListenersTest.driver;
////		logger = ListenersTest.logger;
////		ApplicationView av = new ApplicationView(driver);
////		PipelineView pv = new PipelineView(driver);
////		WorkerHelper wh = new WorkerHelper(driver);
////		ReportHelper rp = new ReportHelper(driver);
////		PipelineHelper ph = new PipelineHelper(driver);
////		VisualTestingHelper helper = new VisualTestingHelper();
////		helper.eyesstichMode(VisualTestingStatus);
////		helper.eyeopenApplitool(driver, "validate_Pipeline_updated_parameter_piplevel_prot_appLevel",
////				VisualTestingStatus);
////		WorkerHelper.workerExcelWriter();
////		String applicationName = WorkerHelper.excelRead(0);
////		av.openApplicationDashboard(applicationName);
////		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
////		String workerName = wh.excelRead(1);
////		String WorkerNames = applicationName + "_" + workerName;
////		wh.workerAgentNameFinder();
////		wh.addWorker(workerName);
////		wh.downloadWorker();
////		wh.agentRunning(WorkerNames);
////		String pipelineName = "pipeline" + workerName;
////		wh.workerPageRefresh();
////		ph.navigateToPipeline();
////		ph.navigateToVisualEditor();
////		ph.createPipelineName(applicationName, pipelineName);
////		Thread.sleep(2000);
////		ph.addStageInPipeline("stage1", "qa", WorkerNames);
////		ph.createStepeForlessTimeout();
////		ph.submitPipeline();
////		ph.scrollUP();
////		PipelineConfigResultHelper prh = new PipelineConfigResultHelper(driver);
////
////		String parameters = prh.excelRead(6);
////		String[] parameter = parameters.split(":");
////		String variablenName = parameter[0];
////		String defaultName = parameter[1];
////		String defaults = defaultName.replace(";", "");
////		ph.createParameter(variablenName, defaults);
////
////		ph.scrollUP();
////		String defaults1 = "Test1";
////		ph.editParameter(variablenName, defaults1);
////		ph.submitPipeline();
////
////		ph.triggerPipelineUsingIcon();
////		ph.workerSelectionInPipelineTrigger(WorkerNames);
////		ph.triggerPipelineUsingButton();
////		ph.pipelineTriggerVerificaton();
////
////		String foldername = applicationName + "_" + pipelineName;
////		ph.pipelineAuditPageNav(foldername);
////		Boolean value = ph.VerifyActionRows("3", "UPDATE_PIPELINE");
////		assertTrue(value);
////		helper.eyesTargetWindow("validate_Pipeline_parameter_UPDATE_PIPELINE_Pipeline level", VisualTestingStatus);
////		String viewValues = ph.VerifyDynamicRowView(3, "UPDATE_PIPELINE", foldername);
////		String path = System.getProperty("user.dir") + "//Data//testout.txt";
////		ph.WriteNote(path, viewValues);
////		ph.newone(path, "value", defaults1);
////		ph.clickOnOK();
////		wh.delectFilescustom("\\Data", "testout.txt");
////
////		driver.navigate().refresh();
////		String portfolioName = wh.excelRead(3);
////		av.openPortfolioDashboard(portfolioName);
////		Boolean portfolioValue = ph.VerifyActionRows("2", "UPDATE_PIPELINE");
////		assertTrue(portfolioValue);
////		helper.eyesTargetWindow("validate_Pipeline_parameter_UPDATE_PIPELINE_Portfolio level", VisualTestingStatus);
////		String viewValues1 = ph.VerifyDynamicRowView(2, "UPDATE_PIPELINE", foldername);
////		ph.WriteNote(path, viewValues1);
////		ph.newone(path, "value", defaults1);
////		ph.clickOnOK();
////		wh.delectFilescustom("\\Data", "testout.txt");
////
////		driver.navigate().refresh();
////		av.openApplicationDashboardAudi(applicationName);
////		Boolean appValue = ph.VerifyActionRows("2", "UPDATE_PIPELINE");
////		assertTrue(appValue);
////		helper.eyesTargetWindow("validate_Pipeline_parameter_UPDATE_PIPELINE_Application level", VisualTestingStatus);
////		String viewValues2 = ph.VerifyDynamicRowView(2, "UPDATE_PIPELINE", foldername);
////		ph.WriteNote(path, viewValues2);
////		ph.newone(path, "value", defaults1);
////		ph.clickOnOK();
////		wh.delectFilescustom("\\Data", "testout.txt");
////
////		String agentName = wh.agentNames();
////		driver.navigate().refresh();
////		av.openApplicationDashboard(applicationName);
////		wh.finalSteps(agentName, WorkerNames, foldername);
////		helper.eyeclose(VisualTestingStatus);
////	}
////
////	@Test(enabled = false, priority = 11)
////	@org.testng.annotations.Parameters("VisualTestingStatus")
////	public void validate_Application_Level_AuditLogs_EachModule_Create_Scenario()
////			throws Exception {
////		driver = ListenersTest.driver;
////		logger = ListenersTest.logger;
////		ReportHelper reportH = PageFactory.initElements(driver, ReportHelper.class);
////		ReportHelper rp = new ReportHelper(driver);
////		ApplicationView av = new ApplicationView(driver);
////		PipelineView pv = new PipelineView(driver);
////		VisualTestingHelper helper = new VisualTestingHelper();
////		helper.eyesstichMode(VisualTestingStatus);
////		helper.eyeopenApplitool(driver, "validate_Application_Level_AuditLogs_EachModule_Create_Scenario",
////				VisualTestingStatus);
////		ExcelWriter write = new ExcelWriter();
////		ExcelReader read = new ExcelReader();
////		WorkerHelper wh = new WorkerHelper(driver);
////		PipelineHelper ph = new PipelineHelper(driver);
////		write.ExcelWriter();
////		rp.applicationNav();
////		reportH.newPolicyCreation("QATest");
////		rp.environmentCreation();
////		rp.variableCreation();
////		rp.releaseCreation();
////		rp.workerCreation();
////		rp.pipelineCreation();
////		rp.workfloCcreation();
////
////		driver.navigate().refresh();
////		String portfolioName = wh.excelRead(3);
////		av.openPortfolioDashboard(portfolioName);
////		ph.scrollDownToAutilogs();
////
////		String[] creations = { "CREATE_APPLICATION", "CREATE_POLICY", "CREATE_ENVIRONMENTS", "CREATE_VARIABLES",
////				"CREATE_RELEASES", "CREATE_WORKER", "CREATE_PIPELINE", "CREATE_WORKFLOW" };
////
////		int num = 0;
////		for (int i = 8; i >= 1; i--) {
////			String s = String.valueOf(i);
////			Boolean Value = ph.VerifyActionRowsForAll(s, creations[num]);
////			assertTrue(Value);
////			log.info(s + "and value is ==> " + creations[num]);
////			num++;
////		}
////
////		String AppName = read.excelRead(0);
////		String workerValue = read.excelRead(9);
////		String newValue = workerValue.replace(";", "");
////
////		String appName1 = read.excelRead(9);
////		String newValue1 = appName1.replace(";", "");
////		String appNames = newValue1.replace("_", "/");
////		String[] v = appNames.split("/");
////		String appFirstName = v[0];
////		String appLastName = v[1];
////		String appNameworkFlow = read.excelRead(11);
////		String workflowName = appNameworkFlow.replace(";", "");
////		log.info(appFirstName + "/pipeline" + appLastName);
////
////		ph.applicationVerifyViews(8, "CREATE_APPLICATION", AppName, "name", AppName);
////		ph.VerifyViews(7, "CREATE_POLICY", AppName, "policyName", "QATest");
////		ph.VerifyViews(6, "CREATE_ENVIRONMENTS", AppName, "name", "QATest");
////		ph.VerifyViews(5, "CREATE_VARIABLES", AppName, "name", "UserName");
////		ph.VerifyViews(4, "CREATE_RELEASES", AppName, "relNo", "NewOne");
////		ph.VerifyViews(3, "CREATE_WORKER", AppName, "name", newValue);
////		ph.VerifyViews(2, "CREATE_PIPELINE", appFirstName + "/pipeline" + appLastName, "name",
////				"pipeline" + appLastName);
////		ph.VerifyViews(1, "CREATE_WORKFLOW", AppName, "workflowName", workflowName);
////		helper.eyesTargetWindow("validate_Application_Level_AuditLogs_EachModule_Create_Scenario", VisualTestingStatus);
////
////		String agentName = wh.agentNames();
////		driver.navigate().refresh();
////		av.openApplicationDashboard(AppName);
////		String[] newenvironmentValue = newValue.split("_");
////		String workerNewName = newenvironmentValue[1];
////		wh.deleteWorker(workerNewName);
////		helper.eyeclose(VisualTestingStatus);
////	}
//
//	@Test(enabled = true, priority = 12)
//	public void To_validate_Environment_Create_Update_Delete() throws Exception {
//		driver = ListenersTest.driver;
//		logger = ListenersTest.logger;
//		ApplicationView av = new ApplicationView(driver);
//		PipelineView pv = new PipelineView(driver);
//		WorkerHelper wh = new WorkerHelper(driver);
//		ReportHelper rp = new ReportHelper(driver);
//		PipelineHelper ph = new PipelineHelper(driver);
//		WorkerHelper.workerExcelWriter();
//		String applicationName = WorkerHelper.excelRead(0);
//		av.openApplicationDashboard(applicationName);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
//		String workerName = wh.excelRead(1);
//		String WorkerNames = applicationName + "_" + workerName;
//		av.naviToEnvir();
//		av.deleteEnv("TestEnv");
//		av.environmentCreation("TestEnv", "QA");
//		Boolean value = av.verifyAfterCreateEnv("TestEnv", "QA");
//		assertTrue(value);
//		av.updateEnv("TestEnv", "QA", "Prod");
//
//		Thread.sleep(6000);
//		driver.navigate().refresh();
//		String path = System.getProperty("user.dir") + "//Data//testout.txt";
//		av.openApplicationDashboardAudi(applicationName);
//		Boolean appValue = ph.VerifyActionRows("1", "UPDATE_ENVIRONMENTS");
//
//		Boolean appValue11 = null;
//		if (appValue == false) {
//			Thread.sleep(6000);
//			driver.navigate().refresh();
//			Thread.sleep(6000);
//			av.openApplicationDashboardAudi(applicationName);
//			appValue11 = ph.VerifyActionRows("1", "UPDATE_ENVIRONMENTS");
//			assertTrue(appValue11);
//		} else {
//			assertTrue(appValue);
//		}
//
//		String viewValues2 = ph.VerifyDynamicRowView(1, "UPDATE_ENVIRONMENTS", applicationName);
//		ph.WriteNote(path, viewValues2);
//		Boolean values11 = ph.newone(path, "level", "3");
//		assertTrue(values11);
//		ph.clickOnOK();
//		wh.delectFilescustom("\\Data", "testout.txt");
//
//		driver.navigate().refresh();
//		av.openApplicationDashboardAudi(applicationName);
//		Boolean appValue1 = ph.VerifyActionRows("2", "CREATE_ENVIRONMENTS");
//		assertTrue(appValue1);
//		String viewValues1 = ph.VerifyDynamicRowView(2, "CREATE_ENVIRONMENTS", applicationName);
//		ph.WriteNote(path, viewValues1);
//		Boolean values = ph.newone(path, "level", "2");
//		assertTrue(values);
//		ph.clickOnOK();
//		wh.delectFilescustom("\\Data", "testout.txt");
//
//		Thread.sleep(6000);
//		driver.navigate().refresh();
//		av.openApplicationDashboard(applicationName);
//		av.naviToEnvir();
//		av.deleteEnv("TestEnv");
//		Thread.sleep(3000);
//		driver.navigate().refresh();
//		Thread.sleep(3000);
//		av.openApplicationDashboardAudi(applicationName);
//		Boolean appValue122 = ph.VerifyActionRows("1", "DELETE_ENVIRONMENTS");
//		assertTrue(appValue122);
//	}
//
//	@Test(enabled = true, priority = 13)
//	public void To_validate_VariableManagement_Create_Update_Delete() throws Exception {
//		driver = ListenersTest.driver;
//		logger = ListenersTest.logger;
//		ApplicationView av = new ApplicationView(driver);
//		PipelineView pv = new PipelineView(driver);
//		WorkerHelper wh = new WorkerHelper(driver);
//		ReportHelper rp = new ReportHelper(driver);
//		PipelineHelper ph = new PipelineHelper(driver);
//		String applicationName = WorkerHelper.excelRead(0);
//
//		// av.openApplicationDashboard(applicationName);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
//		String workerName = wh.excelRead(1);
//		String WorkerNames = applicationName + "_" + workerName;
//
//		String variableName = "User";
//		String defaultVariable = "Test";
//		String updatedefaultVariable = "Test";
//		String envName = "QA";
//		String envValue = "1234";
//
//		av.openApplicationDashboard(applicationName);
//		av.naviToVariableManagement();
//		av.deleteAllVariable(variableName);
//
//		av.openApplicationDashboard(applicationName);
//		av.naviToVariableManagement();
//		av.variableCreation(variableName, defaultVariable, envName, envValue);
//		Boolean value = av.verifyAfterCreateVariable(variableName);
//		assertTrue(value);
//
//		av.updateVariable(variableName, updatedefaultVariable);
//		av.variableSecretEncryptUpdate(variableName);
//		Thread.sleep(5000);
//		driver.navigate().refresh();
//
//		Thread.sleep(5000);
//		String path = System.getProperty("user.dir") + "//Data//testout.txt";
//		av.openApplicationDashboardAudi(applicationName);
//		Thread.sleep(5000);
//		Boolean appValue = ph.VerifyActionRows("1", "UPDATE_VARIABLES");
//
//		assertTrue(appValue);
//		Thread.sleep(5000);
//		String viewValues2 = ph.VerifyDynamicRowView(1, "UPDATE_VARIABLES", applicationName);
//		ph.WriteNote(path, viewValues2);
//		Boolean values111 = ph.newone(path, "secret", "true");
//		assertTrue(values111);
//		ph.clickOnOK();
//		wh.delectFilescustom("\\Data", "testout.txt");
//		Thread.sleep(5000);
//		driver.navigate().refresh();
//		av.openApplicationDashboardAudi(applicationName);
//		Thread.sleep(5000);
//		Boolean appValue1 = ph.VerifyActionRows("2", "UPDATE_VARIABLES");
//
//		assertTrue(appValue1);
//		String viewValues1 = ph.VerifyDynamicRowView(2, "UPDATE_VARIABLES", applicationName);
//		ph.WriteNote(path, viewValues1);
//		Boolean values = ph.newoneWithout(path, "default", updatedefaultVariable);
//		assertTrue(values);
//		ph.clickOnOK();
//		wh.delectFilescustom("\\Data", "testout.txt");
//
//		driver.navigate().refresh();
//		av.openApplicationDashboardAudi(applicationName);
//		Thread.sleep(3000);
//		Boolean appValue12 = ph.VerifyActionRows("3", "CREATE_VARIABLES");
//
//		assertTrue(appValue12);
//		String viewValues12 = ph.VerifyDynamicRowView(3, "CREATE_VARIABLES", applicationName);
//		ph.WriteNote(path, viewValues12);
//		Boolean values22 = ph.newone(path, "default", defaultVariable);
//		assertTrue(values22);
//		ph.clickOnOK();
//		wh.delectFilescustom("\\Data", "testout.txt");
//
//		driver.navigate().refresh();
//		av.openApplicationDashboard(applicationName);
//		av.naviToVariableManagement();
//		av.deleteVariable(variableName);
//		Thread.sleep(3000);
//		driver.navigate().refresh();
//		Thread.sleep(3000);
//		av.openApplicationDashboardAudi(applicationName);
//		Boolean appValue1111 = ph.VerifyActionRows("1", "DELETE_VARIABLES");
//
//		assertTrue(appValue1111);
//		driver.navigate().refresh();
//		av.openApplicationDashboard(applicationName);
//		av.naviToVariableManagement();
//		av.deleteAllVariable(variableName);
//	}
//
////	@Test(enabled = true, priority = 14)
////	@org.testng.annotations.Parameters("VisualTestingStatus")
////	public void To_validate_Policys_Create_Update_Delete() throws Exception {
////		driver = ListenersTest.driver;
////		logger = ListenersTest.logger;
////		ReportHelper reportH = PageFactory.initElements(driver, ReportHelper.class);
////		ReportHelper rp = new ReportHelper(driver);
////		ApplicationView av = new ApplicationView(driver);
////		PipelineView pv = new PipelineView(driver);
////		VisualTestingHelper helper = new VisualTestingHelper();
////		helper.eyesstichMode(VisualTestingStatus);
////		helper.eyeopenApplitool(driver, "To_validate_Policys_Create_Update_Delete", VisualTestingStatus);
////		ExcelWriter write = new ExcelWriter();
////		ExcelReader read = new ExcelReader();
////		WorkerHelper wh = new WorkerHelper(driver);
////		PipelineHelper ph = new PipelineHelper(driver);
////		write.ExcelWriter();
////		String AppName = read.excelRead(0);
////		String protName = read.excelRead(1);
////		String ownrName = read.excelRead(2);
////		rp.applicationNav();
////		reportH.newPolicyCreation("QATest");
////		driver.navigate().refresh();
////		rp.navPolicys();
////		String newEmail = "vignesh.mj@infosys.com";
////		rp.UpdatePolicys("QATest", newEmail);
////
////		driver.navigate().refresh();
////		Thread.sleep(3000);
////		rp.navPolicys();
////		rp.deletePolicys("QATest");
////
////		Thread.sleep(3000);
////		driver.navigate().refresh();
////		Thread.sleep(3000);
////
////		String path = System.getProperty("user.dir") + "//Data//testout.txt";
////		av.openApplicationDashboardAudi1(AppName);
////		Boolean appValue = ph.VerifyActionRows("1", "DELETE_POLICY");
////		helper.eyesTargetWindow("validate_DELETE_POLICY Application Level", VisualTestingStatus);
////		assertTrue(appValue);
////
////		Boolean appValues = ph.VerifyActionRows("2", "UPDATE_POLICY");
////		assertTrue(appValues);
////		String viewValues2 = ph.VerifyDynamicRowView(2, "UPDATE_POLICY", AppName);
////		ph.WriteNote(path, viewValues2);
////		Boolean values111 = ph.newone(path, "1", newEmail);
////		helper.eyesTargetWindow("validate_UPDATE_POLICY Application Level", VisualTestingStatus);
////		assertTrue(values111);
////		ph.clickOnOK();
////		wh.delectFilescustom("\\Data", "testout.txt");
////
////		Boolean appValue1 = ph.VerifyActionRows("3", "CREATE_POLICY");
////		assertTrue(appValue1);
////		String viewValues3 = ph.VerifyDynamicRowView(3, "CREATE_POLICY", AppName);
////		ph.WriteNote(path, viewValues3);
////		Boolean values1113 = ph.newone(path, "policyName", "QATest");
////		helper.eyesTargetWindow("validate_CREATE_POLICY Application Level", VisualTestingStatus);
////		assertTrue(values1113);
////		ph.clickOnOK();
////		wh.delectFilescustom("\\Data", "testout.txt");
////		helper.eyeclose(VisualTestingStatus);
////	}
////
////	@Test(enabled = true, priority = 15)
////	@org.testng.annotations.Parameters("VisualTestingStatus")
////	public void To_validate_Release_Create_Update_Delete() throws Exception {
////		driver = ListenersTest.driver;
////		logger = ListenersTest.logger;
////		ApplicationView av = new ApplicationView(driver);
////		PipelineView pv = new PipelineView(driver);
////		WorkerHelper wh = new WorkerHelper(driver);
////		ReportHelper rp = new ReportHelper(driver);
////		PipelineHelper ph = new PipelineHelper(driver);
////		VisualTestingHelper helper = new VisualTestingHelper();
////		helper.eyesstichMode(VisualTestingStatus);
////		helper.eyeopenApplitool(driver, "To_validate_Release_Create_Update_Delete", VisualTestingStatus);
////		WorkerHelper.workerExcelWriter();
////		String applicationName = WorkerHelper.excelRead(0);
////		av.openApplicationDashboard(applicationName);
////		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
////		String workerName = wh.excelRead(1);
////		String WorkerNames = applicationName + "_" + workerName;
////		av.naviToRelease();
////
////		String ReleaseName = "n";
////		String envName = "QA";
////		String updatedenvName = "Dev";
////
////		String releaseName = av.releaseCreation(ReleaseName, envName);
////		av.scrolldown();
////		av.itemPerpageDynamic("100");
////		av.scrolldown();
////		Boolean value = av.verifyAfterCreateRelease(releaseName);
////		assertTrue(value);
////		av.updateReleaseDetails(releaseName, updatedenvName);
////
////		Thread.sleep(3000);
////		driver.navigate().refresh();
////		Thread.sleep(3000);
////		String path = System.getProperty("user.dir") + "//Data//testout.txt";
////		av.openApplicationDashboardAudi(applicationName);
////		Boolean appValue = ph.VerifyActionRows("1", "UPDATE_RELEASES");
////		helper.eyesTargetWindow("validate_UPDATE_RELEASES Application Level", VisualTestingStatus);
////		assertTrue(appValue);
////
////		String viewValues2 = ph.VerifyDynamicRowView(1, "UPDATE_RELEASES", applicationName);
////		ph.WriteNote(path, viewValues2);
////		Boolean values111 = ph.newone(path, "0", "Dev");
////		assertTrue(values111);
////		ph.clickOnOK();
////		wh.delectFilescustom("\\Data", "testout.txt");
////
////		driver.navigate().refresh();
////		av.openApplicationDashboardAudi(applicationName);
////		Boolean appValue12 = ph.VerifyActionRows("2", "CREATE_RELEASES");
////		helper.eyesTargetWindow("validate_CREATE_RELEASES Application Level", VisualTestingStatus);
////		assertTrue(appValue12);
////		helper.eyeclose(VisualTestingStatus);
////	}
////
//	@Test(enabled = true, priority = 16)
//	public void To_validate_Workers_Create_Update_Delete() throws Exception {
//		driver = ListenersTest.driver;
//		logger = ListenersTest.logger;
//		ApplicationView av = new ApplicationView(driver);
//		PipelineView pv = new PipelineView(driver);
//		WorkerHelper wh = new WorkerHelper(driver);
//		ReportHelper rp = new ReportHelper(driver);
//		PipelineHelper ph = new PipelineHelper(driver);
//
//		WorkerHelper.workerExcelWriter();
//		String applicationName = WorkerHelper.excelRead(0);
//		av.openApplicationDashboard(applicationName);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
//		String workerName = wh.excelRead(1);
//		String WorkerNames = applicationName + "_" + workerName;
//		wh.workerAgentNameFinder();
//		wh.addWorker(workerName);
//		wh.tokenRefresh(WorkerNames);
//		wh.deleteWorker(WorkerNames);
//
//		Thread.sleep(3000);
//		driver.navigate().refresh();
//		Thread.sleep(3000);
//		String path = System.getProperty("user.dir") + "//Data//testout.txt";
//		av.openApplicationDashboardAudi(applicationName);
//		Boolean appValue = ph.VerifyActionRows("1", "DELETE_WORKER");
//
//		assertTrue(appValue);
//
//		Boolean appValue1 = ph.VerifyActionRowsWithoutItemperPage("2", "UPDATE_WORKER");
//		assertTrue(appValue1);
//
//		Boolean appValue12 = ph.VerifyActionRowsWithoutItemperPage("3", "CREATE_WORKER");
//		assertTrue(appValue12);
//		String viewValues2 = ph.VerifyDynamicRowView(3, "CREATE_WORKER", applicationName);
//		ph.WriteNote(path, viewValues2);
//		Boolean values111 = ph.newone(path, "name", WorkerNames);
//
//		assertTrue(values111);
//		ph.clickOnOK();
//		wh.delectFilescustom("\\Data", "testout.txt");
//
//	}
//
//	@Test(enabled = true, priority = 17) // Once bug is closed then please enable the asserts
//	public void To_validate_Tools_Integration_AddPluginConfiguration_Create_Update_Delete() throws Exception {
//		driver = ListenersTest.driver;
//		logger = ListenersTest.logger;
//		ApplicationView av = new ApplicationView(driver);
//		PipelineView pv = new PipelineView(driver);
//		WorkerHelper wh = new WorkerHelper(driver);
//		ReportHelper rp = new ReportHelper(driver);
//		PipelineHelper ph = new PipelineHelper(driver);
//
//		ToolsIntegrationHelper tIH = new ToolsIntegrationHelper(driver);
////		WorkerHelper.workerExcelWriter();
//		String applicationName = WorkerHelper.excelRead(0);
////		av.openApplicationDashboard(applicationName);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
//		String workerName = wh.excelRead(1);
//		String WorkerNames = applicationName + "_" + workerName;
//		String updatedWorkerName = "newWorker";
////		tIH.navTotoolIntergration();
////		tIH.addPlugins("msteams_status", workerName,
////				"https://infosystechnologies.webhook.office.com/webhookb2/d98f5e01-a710-4946-a84b-fe9117e57932@63ce7d59-2f3e-42cd-a8cc-be764cff5eb6/IncomingWebhook/861fdf2087e2425695f0429647096f5b/66b17976-8350-4522-9bb6-a2629decc4c9");
////		Thread.sleep(3000);
////		tIH.editPlugins("msteams_status", updatedWorkerName);
////		Thread.sleep(3000);
////		tIH.delectPlugin("msteams_status");
//
//		Thread.sleep(3000);
//		driver.navigate().refresh();
//		// Enable this part
////		Thread.sleep(3000);
////		String path = System.getProperty("user.dir") + "//Data//testout.txt";
////		av.openApplicationDashboardAudi(applicationName);
////		Boolean appValue = ph.VerifyActionRows("1", "DELETE_PLUGIN");
////		helper.eyesTargetWindow("validate_DELETE_PLUGIN Application Level", VisualTestingStatus);
////		assertTrue(appValue);
////
////		Boolean appValue1 = ph.VerifyActionRowsWithoutItemperPage("2", "UPDATE_PLUGIN");
////		helper.eyesTargetWindow("validate_UPDATE_PLUGIN Application Level", VisualTestingStatus);
////		assertTrue(appValue1);
////
////		Boolean appValue12 = ph.VerifyActionRowsWithoutItemperPage("3", "CREATE_PLUGIN");
////		helper.eyesTargetWindow("validate_CREATE_PLUGIN Application Level", VisualTestingStatus);
////		assertTrue(appValue12);
//	}
//
//	@Test(enabled = true, priority = 18)
//	public void To_validate_CreateWorkflow_Create_Update_Delete() throws Exception {
//		driver = ListenersTest.driver;
//		logger = ListenersTest.logger;
//		ApplicationView av = new ApplicationView(driver);
//		PipelineView pv = new PipelineView(driver);
//		WorkerHelper wh = new WorkerHelper(driver);
//		ReportHelper rp = new ReportHelper(driver);
//		PipelineHelper ph = new PipelineHelper(driver);
//
//		ToolsIntegrationHelper tIH = new ToolsIntegrationHelper(driver);
//		WorkerHelper.workerExcelWriter();
//		String applicationName = WorkerHelper.excelRead(0);
//		av.openApplicationDashboard(applicationName);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
//		String workerName = wh.excelRead(1);
//		String WorkerNames = applicationName + "_" + workerName;
//		av.openApplicationDashboard(applicationName);
//		String workflowName = "workflowName";
//		av.workfloCcreation("WorkerAutomation_NewPipeline", workflowName);
//		av.editWorkfloCreation(workflowName);
//		av.deleteWorkflow(workflowName);
//
//		Thread.sleep(3000);
//		driver.navigate().refresh();
//		Thread.sleep(3000);
//		String path = System.getProperty("user.dir") + "//Data//testout.txt";
//		av.openApplicationDashboardAudi(applicationName);
//		Boolean appValue = ph.VerifyActionRows("1", "DELETE_WORKFLOW");
//
//		assertTrue(appValue);
//
//		Boolean appValue1 = ph.VerifyActionRowsWithoutItemperPage("2", "UPDATE_WORKFLOW");
//
//		assertTrue(appValue1);
//
//		Boolean appValue12 = ph.VerifyActionRowsWithoutItemperPage("3", "CREATE_WORKFLOW");
//
//		assertTrue(appValue12);
//
//		String viewValues2 = ph.VerifyDynamicRowView(3, "CREATE_WORKFLOW", applicationName);
//		ph.WriteNote(path, viewValues2);
//		Boolean values111 = ph.newone(path, "workflowName", workflowName);
//		assertTrue(values111);
//		ph.clickOnOK();
//		wh.delectFilescustom("\\Data", "testout.txt");
//
//	}
//
//	@Test(enabled = true, priority = 19)
//	public void To_validate_Environment_Create_Update_Delete_PortfolioLevel() throws Exception {
//		driver = ListenersTest.driver;
//		logger = ListenersTest.logger;
//		ApplicationView av = new ApplicationView(driver);
//		PipelineView pv = new PipelineView(driver);
//		WorkerHelper wh = new WorkerHelper(driver);
//		ReportHelper rp = new ReportHelper(driver);
//		PipelineHelper ph = new PipelineHelper(driver);
//
//		WorkerHelper.workerExcelWriter();
//		String portfolioName = WorkerHelper.excelRead(3);
//		av.openPortfolioDashboardDetails(portfolioName);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
//		String workerName = wh.excelRead(1);
//		String WorkerNames = portfolioName + "_" + workerName;
//		av.naviToEnvir();
//		av.environmentCreation("TestEnv", "QA");
//		Boolean value = av.verifyAfterCreateEnv("TestEnv", "QA");
//		assertTrue(value);
//		av.updateEnv("TestEnv", "QA", "Prod");
//		Thread.sleep(5000);
//		driver.navigate().refresh();
//		String path = System.getProperty("user.dir") + "//Data//testout.txt";
//		av.openPortfolioDashboard(portfolioName);
//		Boolean appValue = ph.VerifyActionRows("1", "UPDATE_ENVIRONMENTS");
//
//		assertTrue(appValue);
//		String viewValues2 = ph.VerifyDynamicRowViewPortfolio(1, "UPDATE_ENVIRONMENTS", portfolioName);
//		ph.WriteNote(path, viewValues2);
//		Boolean values11 = ph.newone(path, "level", "3");
//		assertTrue(values11);
//		ph.clickOnOK();
//		wh.delectFilescustom("\\Data", "testout.txt");
//
//		driver.navigate().refresh();
//		av.openPortfolioDashboard(portfolioName);
//		Boolean appValue1 = ph.VerifyActionRows("2", "CREATE_ENVIRONMENTS");
//
//		assertTrue(appValue1);
//		String viewValues1 = ph.VerifyDynamicRowViewPortfolio(2, "CREATE_ENVIRONMENTS", portfolioName);
//		ph.WriteNote(path, viewValues1);
//		Boolean values = ph.newone(path, "level", "2");
//		assertTrue(values);
//		ph.clickOnOK();
//		wh.delectFilescustom("\\Data", "testout.txt");
//
//		driver.navigate().refresh();
//		av.openPortfolioDashboardDetails(portfolioName);
//		av.naviToEnvir();
//		av.deleteEnv("TestEnv");
//		Thread.sleep(3000);
//		driver.navigate().refresh();
//		Thread.sleep(3000);
//		av.openPortfolioDashboard(portfolioName);
//		Boolean appValue122 = ph.VerifyActionRows("1", "DELETE_ENVIRONMENTS");
//
//		assertTrue(appValue122);
//
//	}
//
//	@Test(enabled = true, priority = 20)
//	public void To_validate_VariableManagement_Create_Update_Delete_PortfolioLevel() throws Exception {
//		driver = ListenersTest.driver;
//		logger = ListenersTest.logger;
//		ApplicationView av = new ApplicationView(driver);
//		PipelineView pv = new PipelineView(driver);
//		WorkerHelper wh = new WorkerHelper(driver);
//		ReportHelper rp = new ReportHelper(driver);
//		PipelineHelper ph = new PipelineHelper(driver);
//
//		WorkerHelper.workerExcelWriter();
//		String applicationName = WorkerHelper.excelRead(0);
//		String portfolioName = WorkerHelper.excelRead(3);
//		av.openPortfolioDashboardDetails(portfolioName);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
//		String workerName = wh.excelRead(1);
//		String WorkerNames = applicationName + "_" + workerName;
//		av.naviToVariableManagement();
//
//		String variableName = "User11";
//		String defaultVariable = "Test11";
//		String updatedefaultVariable = "NewTest";
//		String envName = "QA";
//		String envValue = "1234";
//
//		av.variableCreation(variableName, defaultVariable, envName, envValue);
//		Boolean value = av.verifyAfterCreateVariable(variableName);
//		assertTrue(value);
//		av.updateVariable(variableName, updatedefaultVariable);
//		av.variableSecretEncryptUpdate(variableName);
//		Thread.sleep(5000);
//		driver.navigate().refresh();
//		Thread.sleep(3000);
//		String path = System.getProperty("user.dir") + "//Data//testout.txt";
//		av.openPortfolioDashboardAudit(portfolioName);
//		Boolean appValue = ph.VerifyActionRows("1", "UPDATE_VARIABLES");
//
//		assertTrue(appValue);
//
//		String viewValues2 = ph.VerifyDynamicRowViewPortfolio(1, "UPDATE_VARIABLES", portfolioName);
//		ph.WriteNote(path, viewValues2);
//		Boolean values111 = ph.newone(path, "secret", "true");
//		assertTrue(values111);
//		ph.clickOnOK();
//		wh.delectFilescustom("\\Data", "testout.txt");
//
//		driver.navigate().refresh();
//		av.openPortfolioDashboardAudit(portfolioName);
//		Boolean appValue1 = ph.VerifyActionRows("2", "UPDATE_VARIABLES");
//		assertTrue(appValue1);
//		String viewValues1 = ph.VerifyDynamicRowViewPortfolio(2, "UPDATE_VARIABLES", portfolioName);
//		ph.WriteNote(path, viewValues1);
//		Boolean values = ph.newone(path, "default", updatedefaultVariable);
//		assertTrue(values);
//		ph.clickOnOK();
//		wh.delectFilescustom("\\Data", "testout.txt");
//
//		driver.navigate().refresh();
//		av.openPortfolioDashboardAudit(portfolioName);
//		Boolean appValue12 = ph.VerifyActionRows("3", "CREATE_VARIABLES");
//		assertTrue(appValue12);
//		String viewValues12 = ph.VerifyDynamicRowViewPortfolio(3, "CREATE_VARIABLES", portfolioName);
//		ph.WriteNote(path, viewValues12);
//		Boolean values22 = ph.newone(path, "default", defaultVariable);
//
//		assertTrue(values22);
//		ph.clickOnOK();
//		wh.delectFilescustom("\\Data", "testout.txt");
//
//		driver.navigate().refresh();
//		av.openPortfolioDashboardDetails(portfolioName);
//		av.naviToVariableManagement();
//		av.deleteVariable(variableName);
//		Thread.sleep(3000);
//		driver.navigate().refresh();
//		Thread.sleep(3000);
//		av.openPortfolioDashboardAudit(portfolioName);
//		Boolean appValue1111 = ph.VerifyActionRows("1", "DELETE_VARIABLES");
//		assertTrue(appValue1111);
//
//	}
//
////	@Test(enabled = true, priority = 21)
////	@org.testng.annotations.Parameters("VisualTestingStatus")
////	public void To_validate_Policys_Create_Update_Delete_PortfolioLevel() throws Exception {
////		driver = ListenersTest.driver;
////		logger = ListenersTest.logger;
////		ReportHelper reportH = PageFactory.initElements(driver, ReportHelper.class);
////		ReportHelper rp = new ReportHelper(driver);
////		ApplicationView av = new ApplicationView(driver);
////		PipelineView pv = new PipelineView(driver);
////		VisualTestingHelper helper = new VisualTestingHelper();
////		helper.eyesstichMode(VisualTestingStatus);
////		helper.eyeopenApplitool(driver, "To_validate_Policys_Create_Update_Delete_PortfolioLevel", VisualTestingStatus);
////		ExcelWriter write = new ExcelWriter();
////		ExcelReader read = new ExcelReader();
////		WorkerHelper wh = new WorkerHelper(driver);
////		PipelineHelper ph = new PipelineHelper(driver);
////		write.ExcelWriter();
////		String AppName = read.excelRead(0);
////		String protName = read.excelRead(1);
////		String ownrName = read.excelRead(2);
////
////		WorkerHelper.workerExcelWriter();
////		String applicationName = WorkerHelper.excelRead(0);
////		String portfolioName = WorkerHelper.excelRead(3);
////		av.openPortfolioDashboardDetails(portfolioName);
////		rp.navPolicys();
////		reportH.newPolicyCreationPortfolio("NewQATest");
////		driver.navigate().refresh();
////		rp.navPolicys();
////		String newEmail = "vignesh.mj@infosys.com";
////		rp.UpdatePolicysPortfolio("NewQATest", newEmail);
////		Thread.sleep(5000);
////		driver.navigate().refresh();
////		Thread.sleep(3000);
////		rp.navPolicys();
////		rp.deletePolicys("NewQATest");
////
////		Thread.sleep(5000);
////		driver.navigate().refresh();
////		Thread.sleep(3000);
////
////		String path = System.getProperty("user.dir") + "//Data//testout.txt";
////		av.openPortfolioDashboardAudit(portfolioName);
////		Boolean appValue = ph.VerifyActionRows("1", "DELETE_POLICY");
////		helper.eyesTargetWindow("validate_DELETE_POLICY portfolio Level", VisualTestingStatus);
////		assertTrue(appValue);
////
////		Boolean appValues = ph.VerifyActionRowsWithoutItemperPage("2", "UPDATE_POLICY");
////		helper.eyesTargetWindow("validate_UPDATE_POLICY portfolio Level", VisualTestingStatus);
////		assertTrue(appValues);
////		String viewValues2 = ph.VerifyDynamicRowViewPortfolio(2, "UPDATE_POLICY", portfolioName);
////		ph.WriteNote(path, viewValues2);
////		Boolean values111 = ph.newone(path, "1", newEmail);
////		assertTrue(values111);
////		ph.clickOnOK();
////		wh.delectFilescustom("\\Data", "testout.txt");
////
////		Boolean appValue1 = ph.VerifyActionRowsWithoutItemperPage("3", "CREATE_POLICY");
////		helper.eyesTargetWindow("validate_CREATE_POLICY portfolio Level", VisualTestingStatus);
////		assertTrue(appValue1);
////		String viewValues3 = ph.VerifyDynamicRowViewPortfolio(3, "CREATE_POLICY", portfolioName);
////		ph.WriteNote(path, viewValues3);
////		Boolean values1113 = ph.newone(path, "policyName", "NewQATest");
////		assertTrue(values1113);
////		ph.clickOnOK();
////		wh.delectFilescustom("\\Data", "testout.txt");
////		helper.eyeclose(VisualTestingStatus);
////	}
////
////	@Test(enabled = true, priority = 22)
////	@org.testng.annotations.Parameters("VisualTestingStatus")
////	public void To_validate_Release_Create_Update_Delete_PortfolioLevel() throws Exception {
////		driver = ListenersTest.driver;
////		logger = ListenersTest.logger;
////		ApplicationView av = new ApplicationView(driver);
////		PipelineView pv = new PipelineView(driver);
////		WorkerHelper wh = new WorkerHelper(driver);
////		ReportHelper rp = new ReportHelper(driver);
////		PipelineHelper ph = new PipelineHelper(driver);
////		VisualTestingHelper helper = new VisualTestingHelper();
////		helper.eyesstichMode(VisualTestingStatus);
////		helper.eyeopenApplitool(driver, "To_validate_Release_Create_Update_Delete_PortfolioLevel", VisualTestingStatus);
////		WorkerHelper.workerExcelWriter();
////		String applicationName = WorkerHelper.excelRead(0);
////		String portfolioName = WorkerHelper.excelRead(3);
////		av.openPortfolioDashboardDetails(portfolioName);
////		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
////		String workerName = wh.excelRead(1);
////		String WorkerNames = applicationName + "_" + workerName;
////		av.naviToRelease();
////
////		String ReleaseName = "n";
////		String envName = "QA";
////		String updatedenvName = "Dev";
////
////		String releaseName = av.releaseCreation(ReleaseName, envName);
////		av.scrolldown();
////		av.itemPerpageDynamic("100");
////		av.scrolldown();
////		Boolean value = av.verifyAfterCreateRelease(releaseName);
////		assertTrue(value);
////		av.updateReleaseDetails(releaseName, updatedenvName);
////
////		Thread.sleep(5000);
////		driver.navigate().refresh();
////		Thread.sleep(3000);
////		String path = System.getProperty("user.dir") + "//Data//testout.txt";
////		av.openPortfolioDashboardAudit(portfolioName);
////		Boolean appValue = ph.VerifyActionRows("1", "UPDATE_RELEASES");
////		helper.eyesTargetWindow("validate_UPDATE_RELEASES portfolio Level", VisualTestingStatus);
////		assertTrue(appValue);
////
////		String viewValues2 = ph.VerifyDynamicRowViewPortfolio(1, "UPDATE_RELEASES", portfolioName);
////		ph.WriteNote(path, viewValues2);
////		Boolean values111 = ph.newone(path, "0", "Dev");
////		assertTrue(values111);
////		ph.clickOnOK();
////		wh.delectFilescustom("\\Data", "testout.txt");
////
////		driver.navigate().refresh();
////		av.openPortfolioDashboardAudit(portfolioName);
////		Boolean appValue12 = ph.VerifyActionRowsWithoutItemperPage("2", "CREATE_RELEASES");
////		helper.eyesTargetWindow("validate_CREATE_RELEASES portfolio Level", VisualTestingStatus);
////		assertTrue(appValue12);
////		helper.eyeclose(VisualTestingStatus);
////	}
//
//	@Test(enabled = true, priority = 23)
//
//	public void To_validate_Workers_Create_Update_Delete_PortfolioLevel() throws Exception {
//		driver = ListenersTest.driver;
//		logger = ListenersTest.logger;
//		ApplicationView av = new ApplicationView(driver);
//		PipelineView pv = new PipelineView(driver);
//		WorkerHelper wh = new WorkerHelper(driver);
//		ReportHelper rp = new ReportHelper(driver);
//		PipelineHelper ph = new PipelineHelper(driver);
//
//		WorkerHelper.workerExcelWriter();
//		String applicationName = WorkerHelper.excelRead(0);
//		String portfolioName = WorkerHelper.excelRead(3);
//		av.openPortfolioDashboardDetails(portfolioName);
//		av.naviToWorker();
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
//		String workerName = wh.excelRead(1);
//		String WorkerNames = portfolioName + "_" + workerName;
//		wh.workerAgentNameFinder();
//		wh.addWorker(workerName);
//		wh.tokenRefresh(WorkerNames);
//		wh.deleteWorker(WorkerNames);
//
//		Thread.sleep(3000);
//		driver.navigate().refresh();
//		Thread.sleep(3000);
//		String path = System.getProperty("user.dir") + "//Data//testout.txt";
//		av.openPortfolioDashboardAudit(portfolioName);
//		Boolean appValue = ph.VerifyActionRows("1", "DELETE_WORKER");
//
//		assertTrue(appValue);
//
//		Boolean appValue1 = ph.VerifyActionRowsWithoutItemperPage("2", "UPDATE_WORKER");
//
//		assertTrue(appValue1);
//
//		Boolean appValue12 = ph.VerifyActionRowsWithoutItemperPage("3", "CREATE_WORKER");
//
//		assertTrue(appValue12);
//		String viewValues2 = ph.VerifyDynamicRowViewPortfolio(3, "CREATE_WORKER", portfolioName);
//		ph.WriteNote(path, viewValues2);
//		Boolean values111 = ph.newone(path, "name", WorkerNames);
//		assertTrue(values111);
//		ph.clickOnOK();
//		wh.delectFilescustom("\\Data", "testout.txt");
//
//	}
//
//	@Test(enabled = true, priority = 24)
//	public void To_Validate_Tools_Compliance_Tools_Mandate_Create_Delete() throws Exception {
//		driver = ListenersTest.driver;
//		logger = ListenersTest.logger;
//		ApplicationView av = new ApplicationView(driver);
//		PipelineView pv = new PipelineView(driver);
//		WorkerHelper wh = new WorkerHelper(driver);
//		ReportHelper rp = new ReportHelper(driver);
//		PipelineHelper ph = new PipelineHelper(driver);
//
//		WorkerHelper.workerExcelWriter();
//		String applicationName = WorkerHelper.excelRead(0);
//		String portfolioName = WorkerHelper.excelRead(3);
//		av.openPortfolioDashboardDetails(portfolioName);
//		av.naviToTools_Compliance();
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
//		String workerName = wh.excelRead(1);
//		String WorkerNames = portfolioName + "_" + workerName;
//		Thread.sleep(3000);
//		av.deleteTool();
//		av.addTool("cmdexec");
//		av.addOverRide();
//		av.saveButton();
//		av.cancelButton();
//		av.saveButton();
//		av.deleteTool();
//		Thread.sleep(3000);
//		driver.navigate().refresh();
//		Thread.sleep(3000);
//		String path = System.getProperty("user.dir") + "//Data//testout.txt";
//		av.openPortfolioDashboardAudit(portfolioName);
//		Boolean appValue = ph.VerifyActionRows("1", "DELETE_MANDATORYPLUGINS");
//
//		assertTrue(appValue);
//		Boolean appValue1 = ph.VerifyActionRowsWithoutItemperPage("2", "UPDATE_TOOLSMANDATE");
//
//		assertTrue(appValue1);
//		Boolean appValue2 = ph.VerifyActionRowsWithoutItemperPage("4", "CREATE_MANDATORYPLUGINS");
//
//		assertTrue(appValue2);
//
//	}
//
//	@Test(enabled = false, priority = 25)
//	public void To_Validate_Tools_Compliance_Run_Frequency_Create_Update_Delete() throws Exception {
//		driver = ListenersTest.driver;
//		logger = ListenersTest.logger;
//		ApplicationView av = new ApplicationView(driver);
//		PipelineView pv = new PipelineView(driver);
//		WorkerHelper wh = new WorkerHelper(driver);
//		ReportHelper rp = new ReportHelper(driver);
//		PipelineHelper ph = new PipelineHelper(driver);
//
//		WorkerHelper.workerExcelWriter();
//		String applicationName = WorkerHelper.excelRead(0);
//		String portfolioName = WorkerHelper.excelRead(3);
//		av.openPortfolioDashboardDetails(portfolioName);
//		av.naviToTools_Compliance();
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
//		String workerName = wh.excelRead(1);
//		String WorkerNames = portfolioName + "_" + workerName;
//		Thread.sleep(3000);
//		av.deleteTool();
//		av.addTool("cmdexec");
//		av.nav_AddRun_Frequency();
//		av.addRun_Frequency("10");
//		av.saveButton();
//		av.freqDeleteButton();
//		av.deleteTool();
//		Thread.sleep(3000);
//		driver.navigate().refresh();
//		Thread.sleep(3000);
//		String path = System.getProperty("user.dir") + "//Data//testout.txt";
//		av.openPortfolioDashboardAudit(portfolioName);
//		Boolean appValue = ph.VerifyActionRows("1", "DELETE_MANDATORYPLUGINS");
//
//		assertTrue(appValue);
//		Boolean appValue1 = ph.VerifyActionRowsWithoutItemperPage("2", "UPDATE_RUNFREQUENCY");
//
//		assertTrue(appValue1);
//		Boolean appValue2 = ph.VerifyActionRowsWithoutItemperPage("3", "CREATE_MANDATORYPLUGINS");
//
//		assertTrue(appValue2);
//
//	}

	@Test(enabled = true, priority = 26)
	public void To_Validate_Tools_Compliance_Gating_Create_Update_Delete() throws Exception {
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
		
		av.naviToTools_Compliance();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = portfolioName + "_" + workerName;
		Thread.sleep(3000);
		
		av.deleteTool();
		av.addTool("cmdexec");
		av.navToGating();
		av.navToAddGating();
		av.fileGateDetails();
		av.clickOnSave();
		av.clickOnDELETE();
		Thread.sleep(3000);
		//av.clickOnSave();
		av.deleteTool();
		Thread.sleep(3000);
		driver.navigate().refresh();
		Thread.sleep(3000);
		String path = System.getProperty("user.dir") + "//Data//testout.txt";
		//av.openPortfolioDashboardAudit(portfolioName);
		av.openPortfolioDashboard(portfolioName);
		Boolean appValue = ph.VerifyActionRows("1", "DELETE_MANDATORYPLUGINS");

		assertTrue(appValue);
		Boolean appValue1 = ph.VerifyActionRowsWithoutItemperPage("2", "UPDATE_GATING");

		assertTrue(appValue1);
		Boolean appValue2 = ph.VerifyActionRowsWithoutItemperPage("4", "CREATE_MANDATORYPLUGINS");

		assertTrue(appValue2);

	}

//===================================================================================================================== we need to check below
//	@Test(enabled = true, priority = 27)
//	@org.testng.annotations.Parameters("VisualTestingStatus") // it is not update in auditlogs
//	public void To_Validate_Tools_Compliance_Lock_Plugin_Fields_Create_Update_Delete()
//			throws Exception {
//		driver = ListenersTest.driver;
//		logger = ListenersTest.logger;
//		ApplicationView av = new ApplicationView(driver);
//		PipelineView pv = new PipelineView(driver);
//		WorkerHelper wh = new WorkerHelper(driver);
//		ReportHelper rp = new ReportHelper(driver);
//		PipelineHelper ph = new PipelineHelper(driver);
//		VisualTestingHelper helper = new VisualTestingHelper();
//		helper.eyesstichMode(VisualTestingStatus);
//		helper.eyeopenApplitool(driver, "To_Validate_Tools_Compliance_Lock_Plugin_Fields_Create_Update_Delete",
//				VisualTestingStatus);
//		WorkerHelper.workerExcelWriter();
//		String applicationName = WorkerHelper.excelRead(0);
//		String portfolioName = WorkerHelper.excelRead(3);
//		av.openPortfolioDashboardDetails(portfolioName);
//		av.naviToTools_Compliance();
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
//		String workerName = wh.excelRead(1);
//		String WorkerNames = portfolioName + "_" + workerName;
//		Thread.sleep(3000);
//		av.deleteTool();
//		av.addTool("cmdexec");
//		av.navTolockPlugin();
//		av.navToAddLockPlugin();
//		av.addLockFiledProperties();
//		av.clickOnSaveLockPlugin();
//		av.clickOnDeleteLockPlugin();
//		Thread.sleep(2000);
//		av.clickOnSaveLockPlugin();
//		av.deleteTool();
//		Thread.sleep(3000);
//		driver.navigate().refresh();
//		Thread.sleep(3000);
//		String path = System.getProperty("user.dir") + "//Data//testout.txt";
//		av.openPortfolioDashboardAudit(portfolioName);
//		Boolean appValue = ph.VerifyActionRows("1", "DELETE_MANDATORYPLUGINS");
//		helper.eyesTargetWindow("validate_DELETE_MANDATORYPLUGINS portfolio Level", VisualTestingStatus);
//		assertTrue(appValue);
//		Boolean appValue1 = ph.VerifyActionRowsWithoutItemperPage("2", "UPDATE_LOCKPLUGINFIELDS");
//		helper.eyesTargetWindow("validate_UPDATE_LOCKPLUGINFIELDS portfolio Level", VisualTestingStatus);
//		assertTrue(appValue1);
//		Boolean appValue2 = ph.VerifyActionRowsWithoutItemperPage("4", "CREATE_MANDATORYPLUGINS");
//		helper.eyesTargetWindow("validate_CREATE_MANDATORYPLUGINS portfolio Level", VisualTestingStatus);
//		assertTrue(appValue2);
//		helper.eyeclose(VisualTestingStatus);
//	}
//
//	@Test(enabled = true, priority = 28)
//	@org.testng.annotations.Parameters("VisualTestingStatus") // it is not update in auditlogs
//	public void To_validate_PipelineTemplates_Create_Update_PortfolioLevel()
//			throws Exception {
//		driver = ListenersTest.driver;
//		logger = ListenersTest.logger;
//		ApplicationView av = new ApplicationView(driver);
//		PipelineView pv = new PipelineView(driver);
//		WorkerHelper wh = new WorkerHelper(driver);
//		ReportHelper rp = new ReportHelper(driver);
//		PipelineHelper ph = new PipelineHelper(driver);
//		VisualTestingHelper helper = new VisualTestingHelper();
//		helper.eyesstichMode(VisualTestingStatus);
//		helper.eyeopenApplitool(driver, "To_validate_PipelineTemplates_Create_Update_PortfolioLevel",
//				VisualTestingStatus);
//		WorkerHelper.workerExcelWriter();
//		String applicationName = WorkerHelper.excelRead(0);
//		String portfolioName = WorkerHelper.excelRead(3);
//		String workerName = wh.excelRead(1);
//		String WorkerNames = applicationName + "_" + workerName;
//		String pipelineName = "pipe" + workerName;
//		String pipelineName1 = "pipeTe" + workerName;
//
//		av.openPortfolioDashboardDetails(portfolioName);
//		ph.navToPipelineTemplates();
//		ph.createNewTemplate();
//		ph.createPipelineName(applicationName, pipelineName);
//		ph.addStageInPipeline1("Stage1", "qa");
//		ph.cmdPluglessTimeoutJavaVersion();
//		String templateName = workerName + "tle";
//		ph.saveTemplate(templateName);
//		ph.submitPipeline();
//		Thread.sleep(3000);
//		driver.navigate().refresh();
//		av.openPortfolioDashboardDetails(portfolioName);
//		ph.navToPipelineTemplates();
//		ph.editPipelineTemplates(templateName, "Stage1");
//
//		Thread.sleep(5000);
//		driver.navigate().refresh();
//		Thread.sleep(3000);
//		String path = System.getProperty("user.dir") + "//Data//testout.txt";
//		av.openPortfolioDashboard(portfolioName);
//		Boolean appValue = ph.VerifyActionRows("1", "UPDATE_TEMPLATES");
//		helper.eyesTargetWindow("validate_UPDATE_TEMPLATES portfolio Level", VisualTestingStatus);
//		assertTrue(appValue);
//		Boolean appValue2 = ph.VerifyActionRowsWithoutItemperPage("2", "CREATE_TEMPLATES");
//		helper.eyesTargetWindow("validate_CREATE_TEMPLATES portfolio Level", VisualTestingStatus);
//		assertTrue(appValue2);
//		helper.eyeclose(VisualTestingStatus);
//	}


	@Test(enabled = true, priority = 29)
	public void Change_profolio_Name_in_application_edit_page() throws Exception {
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
		String AppName = read.excelRead(0);
		write.ExcelWriter();
		rp.applicationNav();
		String portfolioName = "Automation_Functional_test";
		String newPortfolioName = "IDP_Quality_Assurance";
		av.editApplicationPortfolio(newPortfolioName);
		Thread.sleep(5000);
		driver.navigate().refresh();
		Thread.sleep(3000);
		String path = System.getProperty("user.dir") + "//Data//testout.txt";
		av.openPortfolioDashboard(portfolioName);
		Boolean appValue = ph.VerifyActionRows("1", "UPDATE_GENERAL");

		assertTrue(appValue);
		Boolean appValue2 = ph.VerifyActionRowsWithoutItemperPage("2", "CREATE_APPLICATION");

		assertTrue(appValue2);
		Thread.sleep(5000);
		// driver.navigate().refresh();
		Thread.sleep(3000);
		av.openAuditLogs("applicationName", AppName);
		Boolean appValue1 = ph.VerifyActionRows("1", "UPDATE_GENERAL");

		assertTrue(appValue1);
		Boolean appValue3 = ph.VerifyActionRowsWithoutItemperPage("2", "CREATE_APPLICATION");

		assertTrue(appValue3);
		Thread.sleep(5000);

	}

	@Test(enabled = true, priority = 30)
	public void To_Validate_platform_level_Toolintegration() throws Exception {
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
		av.addPlugins(LowerCasepluginName, workerName, "Test");
		av.editPlugins(LowerCasepluginName, updatedWorkerName);
		av.delectPlugin(LowerCasepluginName);

		Thread.sleep(3000);
		// driver.navigate().refresh();
		Thread.sleep(3000);
		av.navAuditLogs();

		Boolean appValue = ph.VerifyActionRowsWithoutItemperPage("1", "DELETE_PLATFORM_PLUGINS");
		assertTrue(appValue);
		Boolean appValue1 = ph.VerifyActionRowsWithoutItemperPage("2", "UPDATE_PLATFORM_PLUGINS");
		assertTrue(appValue1);
		Boolean appValue3 = ph.VerifyActionRowsWithoutItemperPage("3", "CREATE_PLATFORM_PLUGINS");
		assertTrue(appValue3);
	}

	@Test(enabled = true, priority = 31)
	public void To_Validate_platform_level_worker_creation() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		ToolsIntegrationHelper tIH = new ToolsIntegrationHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName = "Platform_IDP";
		av.openPlatformConfiguration();
		av.navWorker();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		wh.addWorkerPlatformLevel(workerName);
		wh.tokenRefresh(WorkerNames);
		wh.deleteWorker(WorkerNames);

		Thread.sleep(5000);
		// driver.navigate().refresh();
		Thread.sleep(4000);
		String path = System.getProperty("user.dir") + "//Data//testout.txt";
		av.navAuditLogs();
		Boolean appValue = ph.VerifyActionRowsWithoutItemperPagePlatformLevel("vignesh.mj@infosys.com",
				"DELETE_WORKER");

		assertTrue(appValue);
	}

	@Test(enabled = true, priority = 32)
	public void To_Validate_Pipeline_level_Workflow() throws Exception {
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
		String portfolioName = wh.excelRead(3);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		av.openApplicationDashboard(applicationName);
		wh.workerAgentNameFinder();
		wh.addWorker(workerName);
		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipeline("stage1", "qa", WorkerNames);
		ph.createDynamicSteps("stage1");
		ph.addStageInPipeline("stage2", "dev", WorkerNames);
		ph.createDynamicSteps("stage2");
		ph.submitPipeline();
		ph.navworkflow();
		ph.addworkFlow();
		String workflowName = "Test";
		ph.clickOnSaveAs(workflowName);
		ph.editWorkflow("W2:"+workflowName);
		ph.deleteWorkflow();

		Thread.sleep(5000);
		driver.navigate().refresh();
		Thread.sleep(4000);

		String path = System.getProperty("user.dir") + "//Data//testout.txt";
		av.openApplicationDashboardAudi(applicationName);
		Boolean appValue = ph.VerifyActionRows("1", "DELETE_WORKFLOW");

		assertTrue(appValue);
		Boolean appValues = ph.VerifyActionRowsWithoutItemperPage("2", "UPDATE_WORKFLOW");

		assertTrue(appValues);
		Boolean appValue1 = ph.VerifyActionRowsWithoutItemperPage("3", "CREATE_WORKFLOW");

		assertTrue(appValue1);
		String viewValues3 = ph.VerifyDynamicRowView(3, "CREATE_WORKFLOW", applicationName);

		ph.WriteNote(path, viewValues3);
		Boolean values1113 = ph.newone(path, "workflowName", workflowName);
		assertTrue(values1113);
		ph.clickOnOK();
		wh.delectFilescustom("\\Data", "testout.txt");

		Thread.sleep(5000);
		driver.navigate().refresh();
		Thread.sleep(4000);
		av.openPortfolioDashboard(portfolioName);
		Boolean appValueportfolio = ph.VerifyActionRows("1", "DELETE_WORKFLOW");
		assertTrue(appValueportfolio);
		Boolean appValueportfolio1 = ph.VerifyActionRowsWithoutItemperPage("2", "UPDATE_WORKFLOW");
		assertTrue(appValueportfolio1);
		Boolean appValueportfolio2 = ph.VerifyActionRowsWithoutItemperPage("3", "CREATE_WORKFLOW");
		assertTrue(appValueportfolio2);
		String viewValues33 = ph.VerifyDynamicRowView(3, "CREATE_WORKFLOW", applicationName);
		ph.WriteNote(path, viewValues33);
		Boolean values11133 = ph.newone(path, "workflowName", workflowName);
		assertTrue(values11133);
		ph.clickOnOK();
		wh.delectFilescustom("\\Data", "testout.txt");

		Thread.sleep(5000);
		driver.navigate().refresh();
		Thread.sleep(3000);
		av.openAuditLogs("applicationName", applicationName);
		Boolean appValueAuditlogs = ph.VerifyActionRowsWithoutItemperPage("1", "DELETE_WORKFLOW");
		assertTrue(appValueAuditlogs);
		Boolean appValue3 = ph.VerifyActionRowsWithoutItemperPage("2", "UPDATE_WORKFLOW");
		assertTrue(appValue3);

		Boolean appValueAudit = ph.VerifyActionRowsWithoutItemperPage("3", "CREATE_WORKFLOW");
		assertTrue(appValueAudit);
		String viewValues333 = ph.VerifyDynamicRowView(3, "CREATE_WORKFLOW", applicationName);
		ph.WriteNote(path, viewValues333);
		Boolean values111333 = ph.newone(path, "workflowName", workflowName);
		assertTrue(values111333);
		ph.clickOnOK();
		wh.delectFilescustom("\\Data", "testout.txt");

		driver.navigate().refresh();
		av.openApplicationDashboard(applicationName);
		wh.deleteWorker(WorkerNames);
	}

}
