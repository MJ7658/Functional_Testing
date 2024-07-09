package org.idp.workermanagement;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.infy.commonHelpers.PipelineHelper;
import org.infy.commonHelpers.WorkerHelper;
import org.infy.init.ListenersTest;
import org.infy.init.LoadData;
import org.infy.mongodbRead.MongoReadData;
import org.infy.reportHelper.ReportHelper;
import org.infy.uiPages.ApplicationView;
import org.infy.uiPages.CommonMethods;
import org.infy.uiPages.PipelineView;
import org.infy.uiPages.Workflows;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

@Listeners(ListenersTest.class)
public class TC_PlatformLevel_WorkerTestCase extends LoadData {

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(TC_PlatformLevel_WorkerTestCase.class);

	public TC_PlatformLevel_WorkerTestCase() throws FileNotFoundException, IOException, ParseException {
		super();
	}

	@Test(enabled = true, priority = 1)
	public void Validate_worker_can_be_launched_with_windows_command_plat() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName = "Platform_IDP";
		av.openPlatformConfiguration();
		av.NavigatePlatformWorker();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		wh.PlatformWorkerAgentNameFinder();
		wh.addWorkerPlatformLevel(workerName);
		wh.PlatformDownloadWorker();
		wh.agentRunning(WorkerNames);
		wh.platformWorkerPageRefresh();
		boolean value = wh.instanceVerifycation("IdleInstance", WorkerNames, "0", "1");
		assertTrue(value);
		String agentName = wh.agentNames();
		wh.finalSteps(agentName, WorkerNames, "worker");
	}

	@Test(enabled = true, priority = 2)
	public void Validate_pipeline_triggered_with_launched_worker_active_instance_count_zero_plat() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName1 = WorkerHelper.excelRead(0);
		String applicationName = "Platform_IDP";
		av.openPlatformConfiguration();
		av.NavigatePlatformWorker();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		wh.PlatformWorkerAgentNameFinder();
		wh.addWorkerPlatformLevel(workerName);
		wh.PlatformDownloadWorker();
		wh.agentRunning(WorkerNames);
		wh.platformWorkerPageRefresh();
		wh.instanceVerifycation("IdleInstance", WorkerNames, "0", "1");
		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName1, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", WorkerNames);
		ph.dyanamicStepAndPlugin("stage1", "sleep");
		ph.addSleepInput("sleep", "100");
		ph.submitPipeline();
		PipelineHelper reportH = PageFactory.initElements(driver, PipelineHelper.class);
		reportH.triggerPipelineUsingIcon();
		ph.PlatformworkerSelectionInPipelineTrigger(WorkerNames);
		ph.triggerPipelineUsingButton();
		ph.pipelineRunningVerifycation(pipelineName, WorkerNames);
		driver.navigate().refresh();
		String applicationNames = "Platform_IDP";
		// av.openApplicationDashboard(applicationName1);
		av.openPlatformConfiguration();
		av.NavigatePlatformWorker();
		boolean value = wh.instanceVerifycation("ActiveInstance", WorkerNames, "1", "0");
		assertTrue(value);
		Thread.sleep(10000);
		String agentName = wh.agentNames();
		String foldername = applicationName1 + "_" + pipelineName;
	}

	@Test(enabled = true, priority = 3)
	public void To_Validate_directory_structure_when_worker_launched_plat() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName = "Platform_IDP";
		String applicationName1 = WorkerHelper.excelRead(0);
		av.openPlatformConfiguration();
		av.NavigatePlatformWorker();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		wh.PlatformWorkerAgentNameFinder();
		wh.addWorkerPlatformLevel(workerName);
		wh.PlatformDownloadWorker();
		wh.agentRunning(WorkerNames);
		wh.platformWorkerPageRefresh();
		wh.instanceVerifycation("IdleInstance", WorkerNames, "0", "1");
		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName1, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipeline("stage1", "qa", WorkerNames);
		ph.createStepeForlessTimeout();
		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.PlatformworkerSelectionInPipelineTrigger(WorkerNames);
		ph.triggerPipelineUsingButton();
		ph.pipelineTriggerVerificaton();
		ph.pipelineRunningVerifycation(pipelineName, WorkerNames);
		driver.navigate().refresh();
		String applicationNames1 = "Platform_IDP";
		av.openPlatformConfiguration();
		av.NavigatePlatformWorker();
		wh.instanceVerifycation("ActiveInstance", WorkerNames, "1", "0");
		String agentName = wh.agentNames();
		wh.agentStoper(agentName);
		String foldername = applicationName1 + "_" + pipelineName;
		Boolean expected = wh.verifyfileExitsOrNot(foldername);
		assertTrue(expected);
		wh.finalSteps(agentName, WorkerNames, foldername);
	}

	@Test(enabled = true, priority = 4)
	public void To_Verify_that_multiple_instance_of_the_worker_getting_saved_in_DB_plat() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		String applicationName1 = "Platform_IDP";
		av.openPlatformConfiguration();
		av.NavigatePlatformWorker();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName1 + "_" + workerName;
		wh.PlatformWorkerAgentNameFinder();
		wh.addWorkerPlatformLevel(workerName);
		wh.PlatformDownloadWorker();
		String agentName = wh.agentNames();
		Thread.sleep(3000);
		wh.moveAgenttoLoc(agentName);
		wh.PlatformDownloadWorker();
		String url = wh.agentRunning(WorkerNames);
		wh.moveAndagentRunning(url);
		wh.platformWorkerPageRefresh();
		wh.instanceVerifycation("IdleInstance", WorkerNames, "0", "2");
		String paths = "{$match:{'name':'" + WorkerNames + "'}}," + "{$unwind:'$instances'}," + "{$project :{'name':'"
				+ WorkerNames + "'}}";
		MongoReadData MDB = new MongoReadData();
		ArrayList<String> value = MDB.particularDataFetchFromMongoDBUsingQuery("agents", paths);
		log.info(value);
		String status = MDB.mongoDBDataVerification(value, "name", "" + WorkerNames + "");
		assertEquals(WorkerNames, status);
		wh.finalSteps(agentName, WorkerNames, "worker");
		boolean fileExits = wh.delectFilescustom("\\Agent", agentName);
		boolean fileExits1 = wh.delectFilescustom("\\Agent", "worker.lock");
	}

	@Test(enabled = true, priority = 5)
	public void To_Verify_Correct_data_for_workingDirectory_IsActive_getting_save_DB_particular_instance_worker_plat()
			throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		String applicationName1 = "Platform_IDP";
		av.openPlatformConfiguration();
		av.NavigatePlatformWorker();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName1 + "_" + workerName;
		wh.PlatformWorkerAgentNameFinder();
		wh.addWorkerPlatformLevel(workerName);
		wh.PlatformDownloadWorker();
		String agentName = wh.agentNames();
		wh.agentRunning(WorkerNames);
		wh.platformWorkerPageRefresh();
		wh.instanceVerifycation("IdleInstance", WorkerNames, "0", "1");
		String paths = "{$match:{'name':'" + WorkerNames + "'}}," + "{$unwind:'$instances'},"
				+ "{$project : {'instances.lsActive':1}}";
		MongoReadData MDB = new MongoReadData();
		ArrayList<String> value = MDB.particularDataFetchFromMongoDBUsingQuery("agents", paths);
		String isActiveDate = MDB.mongoDBDataVerification1(value, "lsActive", 3);
		String mongoDBDate = MDB.mongoTimeStempConver(isActiveDate);
		String currnt = MDB.currentDate();

		Boolean flag;
		if (mongoDBDate.compareTo(currnt) == 0) {
			log.info("Both dates are equal");
			flag = true;

		} else {
			log.info("Both dates are Not Equal");
			flag = false;
		}
		assertTrue(flag);
		wh.finalSteps(agentName, WorkerNames, "worker");
	}

	@Test(enabled = true, priority = 6)
	public void To_Verify_token_worker_refreshed_TerminatedInstance_be_0_nodata_populate_Terminate_Instance_plat()
			throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		String applicationName1 = "Platform_IDP";
		av.openPlatformConfiguration();
		av.NavigatePlatformWorker();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName1 + "_" + workerName;
		wh.PlatformWorkerAgentNameFinder();
		wh.addWorkerPlatformLevel(workerName);
		wh.PlatformDownloadWorker();
		String agentName = wh.agentNames();
		wh.agentRunning(WorkerNames);
		wh.platformWorkerPageRefresh();
		boolean value = wh.instanceVerifycation("IdleInstance", WorkerNames, "0", "1");
		assertTrue(value);
		wh.tokenRefresh(WorkerNames);
		Thread.sleep(3000);
		wh.platformWorkerPageRefresh();
		boolean value1 = wh.instanceVerifycation("IdleInstance", WorkerNames, "0", "0");
		assertTrue(value1);
		wh.finalSteps(agentName, WorkerNames, "worker");
		boolean fileExits = wh.delectFilescustom("\\Agent", agentName);
		boolean fileExits1 = wh.delectFilescustom("\\Agent", "worker.lock");
	}

	@Test(enabled = true, priority = 7)
	public void To_Verify_WorkingDirectory_column_present_worker_details_popup_plat() throws Exception // QAUTOIDP-23635
	{
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		String applicationName1 = "Platform_IDP";
		av.openPlatformConfiguration();
		av.NavigatePlatformWorker();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName1 + "_" + workerName;
		wh.PlatformWorkerAgentNameFinder();
		wh.addWorkerPlatformLevel(workerName);
		wh.PlatformDownloadWorker();
		String agentName = wh.agentNames();
		wh.agentRunning(WorkerNames);
		wh.platformWorkerPageRefresh();
		wh.platformWorkerPageRefresh();
		wh.instanceVerifycation("IdleInstance", WorkerNames, "0", "1");
		wh.clickOnDetailsButton(WorkerNames);
		wh.verifyWorkerWorkSpaceDir();
		wh.clickOnClose();
		Thread.sleep(5000);
		wh.agentStoper(agentName);
		Thread.sleep(5000);
		wh.platformWorkerPageRefresh();
		wh.platformWorkerPageRefresh();
		Thread.sleep(5000);
		wh.clickOnDetailsButton(WorkerNames);
		wh.clickOnTerminatedInstances();
		wh.verifyWorkerWorkSpaceDir();
		wh.clickOnClose();
		wh.finalSteps(agentName, WorkerNames, "worker");
	}

	@Test(enabled = true, priority = 8)
	public void To_Verify_Worker_UpSince_column_present_worker_details_popup_plat() throws Exception // QAUTOIDP-23635
	{
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		av.openPlatformConfiguration();
		av.NavigatePlatformWorker();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String applicationName1 = "Platform_IDP";
		String WorkerNames = applicationName1 + "_" + workerName;
		wh.PlatformWorkerAgentNameFinder();
		wh.addWorkerPlatformLevel(workerName);
		wh.PlatformDownloadWorker();
		String agentName = wh.agentNames();
		wh.agentRunning(WorkerNames);
		wh.platformWorkerPageRefresh();
		wh.instanceVerifycation("IdleInstance", WorkerNames, "0", "1");
		Thread.sleep(5000);
		wh.clickOnDetailsButton(WorkerNames);
		wh.verifyWorker_Up_Since();
		wh.clickOnClose();
		wh.agentStoper(agentName);
		Thread.sleep(5000);
		wh.platformWorkerPageRefresh();
		wh.clickOnDetailsButton(WorkerNames);
		wh.clickOnTerminatedInstances();
		wh.verifyWorker_Up_Since();
		wh.clickOnClose();
		wh.finalSteps(agentName, WorkerNames, "worker");
	}

	@Test(enabled = true, priority = 9)
	public void To_Verify_active_instance_coming_Terminate_instance_tab_plat() throws Exception // QAUTOIDP-23964
	{
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		String applicationName1 = "Platform_IDP";
		av.openPlatformConfiguration();
		av.NavigatePlatformWorker();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName1 + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		wh.PlatformWorkerAgentNameFinder();
		wh.addWorkerPlatformLevel(workerName);
		wh.PlatformDownloadWorker();
		String agentName = wh.agentNames();
		wh.agentRunning(WorkerNames);
		wh.platformWorkerPageRefresh();
		wh.instanceVerifycation("IdleInstance", WorkerNames, "0", "1");
		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", WorkerNames);
		ph.dyanamicStepAndPlugin("stage1", "sleep");
		ph.addSleepInput("sleep", "260");
		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.PlatformworkerSelectionInPipelineTrigger(WorkerNames);
		ph.triggerPipelineUsingButton();
		ph.pipelineTriggerVerificaton();
		ph.pipelineRunningVerifycation(pipelineName, WorkerNames);
		driver.navigate().refresh();
		String applicationNames = wh.excelRead(0);
		av.openPlatformConfiguration();
		av.NavigatePlatformWorker();
		wh.platformWorkerPageRefresh();
		Thread.sleep(1000);
		wh.instanceVerifycation("ActiveInstance", WorkerNames, "1", "0");
		log.info("its  a active line ");
		wh.clickOnDetailsButton(WorkerNames);
		String count = wh.verifyWorkerWorkSpaceDir1();
		log.info("its a active line 2 ");
		if (count.equalsIgnoreCase("1")) {
			log.info("Active count Details are working Fine");
			assertEquals(1, 1);
			;
		} else {
			log.info("Active count Not working Fine");
			assertEquals(1, 0);
		}
		wh.clickOnClose();
		ph.navigateToPipeline();
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		String foldername = applicationName + "_" + pipelineName;
		ph.trminatePipeline(foldername);
		driver.navigate().refresh();
		av.openPlatformConfiguration();
		av.NavigatePlatformWorker();
		wh.agentStoper(agentName);
		Thread.sleep(10000);
		wh.platformWorkerPageRefresh();
		Thread.sleep(2000);
		wh.clickOnDetailsButton(WorkerNames);
		wh.clickOnTerminatedInstances();
		String Ter = wh.verifyWorkerWorkSpaceDir1();
		if (Ter.equalsIgnoreCase("1")) {
			log.info("Active count working Fine");
		} else {
			log.info("Terminated Active Count is not coming TestCase Pass");
		}
		wh.clickOnClose();
		wh.finalSteps(agentName, WorkerNames, foldername);

	}

//	@Test(enabled = true, priority = 10)
//	public void To_Verify_Working_Directory_Start_time_getting_stored_DB_particular_instance_object_plat()
//			throws Exception // QAUTOIDP-23045
//	{
//		driver = ListenersTest.driver;
//		logger = ListenersTest.logger;
//		ApplicationView av = new ApplicationView(driver);
//		PipelineView pv = new PipelineView(driver);
//		WorkerHelper wh = new WorkerHelper(driver);
//		ReportHelper rp = new ReportHelper(driver);
//		PipelineHelper ph = new PipelineHelper(driver);
//		MongoReadData MDB = new MongoReadData();
//		WorkerHelper.workerExcelWriter();
//		String applicationName = WorkerHelper.excelRead(0);
//		String applicationName1 = "Platform_IDP";
//		av.openPlatformConfiguration();
//		av.NavigatePlatformWorker();
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
//		String workerName = wh.excelRead(1);
//		String WorkerNames = applicationName1 + "_" + workerName;
//		wh.PlatformWorkerAgentNameFinder();
//		wh.addWorkerPlatformLevel(workerName);
//		wh.PlatformDownloadWorker();
//		String agentName = wh.agentNames();
//		wh.agentRunning(WorkerNames);
//		long start = System.currentTimeMillis();
//		String currentDateTime = MDB.mongoTimeStempConverters(start);
//
//		wh.platformWorkerPageRefresh();
//		wh.instanceVerifycation("IdleInstance", WorkerNames, "0", "1");
//		log.info(start);
//
//		String paths = "{$match:{'name':'" + WorkerNames + "'}}," + "{$unwind:'$instances'},"
//				+ "{$project : {'instances.workerUpSince':1}}";
//
//		ArrayList<String> value = MDB.particularDataFetchFromMongoDBUsingQuery("agents", paths);
//		String isActiveDate = MDB.mongoDBDataVerification2(value, "workerUpSince", 3);
//		String mongoDBDate = MDB.mongoTimeStempConverwithTime(isActiveDate);
//
//		log.info("Its mongoDB Time ===> " + mongoDBDate);
//		log.info("Its current Time ===> " + currentDateTime);
//
//		Boolean flag;
//		if (mongoDBDate.compareTo(currentDateTime) == 0) {
//			log.info("Both dates and Time are equal");
//			flag = true;
//
//		} else {
//			log.info("Both dates and Time are Not Equal");
//			flag = false;
//		}
//		assertTrue(flag);
//
//		String paths1 = "{$match:{'name':'" + WorkerNames + "'}}," + "{$unwind:'$instances'},"
//				+ "{$project : {'instances.workerUpSince':1}}";
//
//		ArrayList<String> value1 = MDB.particularDataFetchFromMongoDBUsingQuery("agents", paths1);
//		String workDir = MDB.mongoDBDataVerification2(value, "workspaceDir", 2);
//		String filePath = System.getProperty("user.dir") + "\\Data\\";
//
//		String wokrerPath = workDir.replace("\"", "").replace("\\", "");
//		String filePaths11 = filePath.replace("\"", "").replace("\\", "").replace(":", "");
//
//		Boolean flag1;
//		if (wokrerPath.equalsIgnoreCase(filePaths11)) {
//			log.info("path is same");
//			flag1 = true;
//		} else {
//			log.info("path is not same");
//			flag1 = false;
//		}
//		assertTrue(flag1);
//		wh.finalSteps(agentName, WorkerNames, "worker");
//		Thread.sleep(2000);
//	}

	@Test(enabled = true, priority = 11)
	public void To_Verify_Searchbar_Toggle_switch_implemented_Worker_Info_page_plat() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		String applicationName1 = "Platform_IDP";
		av.openPlatformConfiguration();
		av.NavigatePlatformWorker();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName1 + "_" + workerName;
		wh.PlatformWorkerAgentNameFinder();
		wh.addWorkerPlatformLevel(workerName);
		wh.PlatformDownloadWorker();
		String agentName = wh.agentNames();
		wh.agentRunning(WorkerNames);
		wh.platformWorkerPageRefresh();
		wh.instanceVerifycation("IdleInstance", WorkerNames, "0", "1");
		wh.clickOnDetailsButton(WorkerNames);
		String dirName = wh.verifyWorkerWorkSpaceDir();
		wh.verifyWorkerpopupSearch("Data");
		boolean flag = false;
		if (dirName.contains("Data")) {
			wh.verifyWorkerpopupSearch("skdfjlajfsdkjsfajsaflkfsj");
			try {
				wh.verifyWorkerWorkSpaceDirBoolean();
				flag = false;
			} catch (NoSuchElementException e) {
				log.info("Test Case pass");
				flag = true;
			}
		}
		assertTrue(flag);
		wh.clickOnClose();
		wh.finalSteps(agentName, WorkerNames, "worker");
	}

	@Test(enabled = true, priority = 12)
	public void To_verify_worker_able_trigger_workspaces_provided_shell_veriables_plat() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		String applicationName1 = "Platform_IDP";
		av.openPlatformConfiguration();
		av.NavigatePlatformWorker();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName1 + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		String agentFolder = applicationName + "_" + pipelineName;
		wh.PlatformWorkerAgentNameFinder();
		wh.addWorkerPlatformLevel(workerName);
		wh.PlatformDownloadWorker();
		String agentName = wh.agentNames();
		wh.agentRunningDiffernetPath(WorkerNames);
		wh.platformWorkerPageRefresh();
		wh.instanceVerifycation("IdleInstance", WorkerNames, "0", "1");
		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipeline("stage1", "qa", WorkerNames);
		ph.cmdPluglessDynamicTimeOut(70);
		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.PlatformworkerSelectionInPipelineTrigger(WorkerNames);
		ph.triggerPipelineUsingButton();
		ph.pipelineTriggerVerificaton();
		driver.navigate().refresh();
		String applicationNames = wh.excelRead(0);
		av.openPlatformConfiguration();
		av.NavigatePlatformWorker();
		wh.instanceVerifycation("ActiveInstance", WorkerNames, "1", "0");
		wh.agentStoper(agentName);
		wh.deleteWorker(WorkerNames);
		wh.delectFilescustom("\\Data", "agent");
		wh.delectFilescustom("\\Agent", "worker.lock");
		Thread.sleep(15000);
		boolean fileExits = wh.delectFilescustom("\\Agent", agentFolder);
		assertTrue(fileExits);
	}

	@Test(enabled = true, priority = 13)
	public void To_Verify_proper_data_getting_Working_Directory_multiple_worker_running_same_machine_plat()
			throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		String applicationName1 = "Platform_IDP";
		av.openPlatformConfiguration();
		av.NavigatePlatformWorker();
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName1 + "_" + workerName;
		wh.PlatformWorkerAgentNameFinder();
		wh.addWorkerPlatformLevel(workerName);
		wh.PlatformDownloadWorker();
		String agentName = wh.agentNames();
		Thread.sleep(5000);
		wh.moveAgenttoLoc(agentName);
		wh.PlatformDownloadWorker();
		String url = wh.agentRunning(WorkerNames);
		Thread.sleep(5000);
		wh.moveAndagentRunning(url);
		wh.platformWorkerPageRefresh();
		wh.platformWorkerPageRefresh();
		wh.instanceVerifycation("IdleInstance", WorkerNames, "0", "2");
		Thread.sleep(2000);
		wh.platformWorkerPageRefresh();
		wh.clickOnDetailsButton(WorkerNames);
		Thread.sleep(2000);
		String windownsDir = wh.verifyWorkerWorkSpaceDir();
		String windownsDir1 = wh.verifyWorkerWorkSpaceDirSecondRow();
		Thread.sleep(3000);
		wh.agentStoper(agentName);
		wh.delectFiles(agentName);
		wh.delectFiles("worker.lock");
		boolean flag;
		if (windownsDir.contains("Agent") && windownsDir1.contains("Data")) {
			log.info("Dri Details working fine");
			flag = true;
		} else if (windownsDir1.contains("Data") && windownsDir.contains("Agent")) {
			log.info("Dri Details working fine");
			flag = true;
		} else if (windownsDir.contains("Data") && windownsDir1.contains("Agent")) {
			log.info("Dri Details working fine");
			flag = true;
		} else {
			log.info("Dri Not working fine");
			flag = false;
		}
		wh.clickOnClose();
		assertTrue(flag);
		wh.finalSteps(agentName, WorkerNames, "worker");
		boolean fileExits = wh.delectFilescustom("\\Agent", agentName);
		boolean fileExits1 = wh.delectFilescustom("\\Agent", "worker.lock");
	}

	@Test(enabled = true, priority = 14)
	public void To_verify_invalid_customworkspace_provided_worker_able_create_workspace_first_plat() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		String applicationName1 = "Platform_IDP";
		av.openPlatformConfiguration();
		av.NavigatePlatformWorker();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName1 + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		String agentFolder = applicationName + "_" + pipelineName;
		wh.PlatformWorkerAgentNameFinder();
		wh.addWorkerPlatformLevel(workerName);
		wh.PlatformDownloadWorker();
		String agentName = wh.agentNames();
		wh.agentRunningCustomPath(WorkerNames, "Agent1");
		wh.platformWorkerPageRefresh();
		boolean value = wh.instanceVerifycation("IdleInstance", WorkerNames, "0", "1");
		assertTrue(value);
		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipeline("stage1", "qa", WorkerNames);
		ph.createStepeForlessTimeout();
		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.PlatformworkerSelectionInPipelineTrigger(WorkerNames);
		ph.triggerPipelineUsingButton();
		ph.pipelineTriggerVerificaton();
		ph.pipelineRunningVerifycation(pipelineName, WorkerNames);
		driver.navigate().refresh();
		String applicationNames = wh.excelRead(0);
		av.openPlatformConfiguration();
		av.NavigatePlatformWorker();
		wh.instanceVerifycation("ActiveInstance", WorkerNames, "1", "0");
		boolean fileExits = wh.delectFilescustom("\\Agent1", agentFolder);
		boolean fileExits1 = wh.delectFilescustom("", "Agent1");
		assertTrue(fileExits);
		wh.finalSteps(agentName, WorkerNames, agentFolder);
	}

	@Test(enabled = true, priority = 15)
	public void To_verify_worker_quit_when_not_able_to_launch_inaccessible_directory_plat() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		String applicationName1 = "Platform_IDP";
		av.openPlatformConfiguration();
		av.NavigatePlatformWorker();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName1 + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		String agentFolder = applicationName + "_" + pipelineName;
		wh.PlatformWorkerAgentNameFinder();
		wh.addWorkerPlatformLevel(workerName);
		wh.PlatformDownloadWorker();
		String agentName = wh.agentNames();
		wh.agentRunningCustomPath(WorkerNames, "ReadOnly");
		wh.platformWorkerPageRefresh();
		Boolean value = wh.instanceVerifycation("IdleInstance", WorkerNames, "0", "0");
		wh.agentStoper(agentName);
		wh.finalSteps(agentName, WorkerNames, "worker");
	}

	@Test(enabled = true, priority = 16)
	public void To_Verify_worker_should_not_delete_you_reject_delete_popup() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		String applicationName1 = "Platform_IDP";
		av.openPlatformConfiguration();
		av.NavigatePlatformWorker();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName1 + "_" + workerName;
		wh.PlatformWorkerAgentNameFinder();
		wh.addWorkerPlatformLevel(workerName);
		wh.PlatformDownloadWorker();
		wh.deleteWorkerCancelButton(WorkerNames);
		Thread.sleep(3000);
		wh.agentRunning(WorkerNames);
		wh.platformWorkerPageRefresh();
		boolean value = wh.instanceVerifycation("IdleInstance", WorkerNames, "0", "1");
		assertTrue(value);
		String agentName = wh.agentNames();
		wh.finalSteps(agentName, WorkerNames, "worker");
	}

	@Test(enabled = true, priority = 17)
	public void Validate_pipelineTriggeredPage_Up_Arrow_plat() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		String applicationName1 = "Platform_IDP";
		av.openPlatformConfiguration();
		av.NavigatePlatformWorker();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName1 + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		wh.PlatformWorkerAgentNameFinder();
		wh.addWorkerPlatformLevel(workerName);
		wh.PlatformDownloadWorker();
		wh.agentRunning(WorkerNames);
		wh.platformWorkerPageRefresh();
		wh.instanceVerifycation("IdleInstance", WorkerNames, "0", "1");
		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipeline("stage1", "qa", WorkerNames);
		ph.createStepeForlessTimeout();
		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		driver.navigate().refresh();
		String applicationNames = wh.excelRead(0);
		av.openPlatformConfiguration();
		av.NavigatePlatformWorker();
		String agentName = wh.agentNames();
		wh.finalSteps(agentName, WorkerNames, "worker");
	}

	@Test(enabled = true, priority = 18)
	public void Validate_pipelineTriggeredPage_Down_Arrow_plat() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		String applicationName1 = "Platform_IDP";
		av.openPlatformConfiguration();
		av.NavigatePlatformWorker();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName1 + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		wh.PlatformWorkerAgentNameFinder();
		wh.addWorkerPlatformLevel(workerName);
		wh.PlatformDownloadWorker();
		wh.platformWorkerPageRefresh();
		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipeline("stage1", "qa", WorkerNames);
		ph.createStepeForlessTimeout();
		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		driver.navigate().refresh();
		String applicationNames = wh.excelRead(0);
		av.openPlatformConfiguration();
		av.NavigatePlatformWorker();
		String agentName = wh.agentNames();
		wh.finalSteps(agentName, WorkerNames, "worker");
	}

	@Test(enabled = true, priority = 19)
	public void To_Verify_proper_data_getting_populate_WoringDirectory_when_multiple_worker_running_different_machine_plat()
			throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		String applicationName1 = "Platform_IDP";
		av.openPlatformConfiguration();
		av.NavigatePlatformWorker();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName1 + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		wh.PlatformWorkerAgentNameFinder();
		wh.addWorkerPlatformLevel(workerName);
		wh.deleteFileInLinuxMachine("agent");
		wh.deleteFileInLinuxMachine("worker.lock");
		wh.downloadLinuxWorker();
		Thread.sleep(2000);
		Boolean flag = wh.linuxAndWindowsAgentRunning(WorkerNames);
		wh.deleteFileInLinuxMachine("agent");
		wh.deleteFileInLinuxMachine("worker.lock");
		assertTrue(flag);
		wh.deleteLinuxWorker();
		wh.clickOnClose();
		String agentName = wh.agentNames();
		wh.finalSteps(agentName, WorkerNames, "worker");
	}

	@Test(enabled = true, priority = 20)
	public void To_Validate_that_woker_is_running_user_NoT_Able_to_RunAgain_same_worker_in_same_workspace_plat()
			throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		String applicationName1 = "Platform_IDP";
		av.openPlatformConfiguration();
		av.NavigatePlatformWorker();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName1 + "_" + workerName;
		wh.PlatformWorkerAgentNameFinder();
		wh.addWorkerPlatformLevel(workerName);
		wh.PlatformDownloadWorker();
		wh.agentRunning(WorkerNames);
		String workerName1 = wh.excelRead(1) + "a";
		String WorkerNames1 = applicationName1 + "_" + workerName1;
		wh.addWorkerPlatformLevel(workerName1);
		wh.agentRunning(WorkerNames1);
		wh.platformWorkerPageRefresh();
		boolean value = wh.instanceVerifycation("IdleInstance", WorkerNames, "0", "1");
		assertTrue(value);
		String agentName = wh.agentNames();
		wh.finalSteps(agentName, WorkerNames, "worker");
		wh.deleteWorker(WorkerNames1);
	}

	@Test(enabled = true, priority = 21)
	public void To_Validate_worker_running__userNot_able_to_trigger_another_worker_same_workspace_plat()
			throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		WorkerHelper.workerExcelWriter();
		String applicationName = "Platform_IDP";
		av.openPlatformConfiguration();
		av.NavigatePlatformWorker();
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		wh.PlatformWorkerAgentNameFinder();
		wh.addWorkerPlatformLevel(workerName);
		wh.PlatformDownloadWorker();
		String agentName = wh.agentNames();
		wh.agentRunning(WorkerNames);
		String newWorker = workerName + "abc1";
		String newWorkerNames = applicationName + "_" + newWorker;
		wh.addWorkerPlatformLevel(newWorker);
		wh.agentRunning(newWorkerNames);
		wh.platformWorkerPageRefresh();
		boolean value = wh.instanceVerifycation("IdleInstance", WorkerNames, "0", "1");
		assertTrue(value);
		boolean value1 = wh.instanceVerifycation("IdleInstance", newWorkerNames, "0", "0");
		assertTrue(value1);
		wh.finalSteps(agentName, WorkerNames, "wokrer");
		wh.deleteWorker(newWorkerNames);
	}
}