package org.idp.toolsintegration;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.infy.commonHelpers.PipelineHelper;
import org.infy.commonHelpers.ToolsIntegrationHelper;
import org.infy.commonHelpers.WorkerHelper;
import org.infy.init.ListenersTest;
import org.infy.init.LoadData;
import org.infy.init.gitTest;
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
	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(TC_Tools_Integration.class);

	@Test(enabled = true, priority = 1) //
	public void Validate_MSTEAMS_STATUS_Plugin() throws Exception {
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
		av.openApplicationDashboard(applicationName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		wh.workerAgentNameFinder();
		wh.addWorker(workerName);
		wh.downloadWorker();
		wh.agentRunning(WorkerNames);
		wh.workerPageRefresh();
		tIH.navTotoolIntergration();
		tIH.addPlugins("msteams_status", workerName,
				"https://infosystechnologies.webhook.office.com/webhookb2/d98f5e01-a710-4946-a84b-fe9117e57932@63ce7d59-2f3e-42cd-a8cc-be764cff5eb6/IncomingWebhook/861fdf2087e2425695f0429647096f5b/66b17976-8350-4522-9bb6-a2629decc4c9");
		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		String pipelineName = "pipeline" + workerName;
		String wrkname = applicationName + "-" + pipelineName;
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipeline("stage1", "qa", WorkerNames);
		ph.cmdPluglessTimeoutJavaVersion();
		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(WorkerNames);
		ph.triggerPipelineUsingButton();
		ph.pipelineRunningVerifycation(pipelineName, WorkerNames);
		Thread.sleep(10000);
		driver.navigate().refresh();
		String buildID = ph.getBuildID(WorkerNames);
		String TriggerID = wrkname + "-" + buildID;
		tIH.verifycationNotification("appInte", "TriggerID", TriggerID);
		driver.navigate().refresh();
		av.openApplicationDashboard(applicationName);
		String foldername = applicationName + "_" + pipelineName;
		String agentName = wh.agentNames();
		wh.finalSteps(agentName, WorkerNames, foldername);
		tIH.navTotoolIntergration();
		tIH.delectPlugin("msteams_status");
	}

	@Test(enabled = false, priority = 2) //
	public void Validate_Email_Plugin() throws Exception { // there is no SMTP Server for AGTest
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
		av.openApplicationDashboard(applicationName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		wh.workerAgentNameFinder();
		wh.addWorker(workerName);
		wh.downloadWorker();
		wh.agentRunning(WorkerNames);
		wh.workerPageRefresh();
		tIH.navTotoolIntergration();
		tIH.addEmailPlugins("Email", workerName, "satheshkumar.m@infosys.com");
		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		String pipelineName = "pipeline" + workerName;
		String wrkname = applicationName + "-" + pipelineName;
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipeline("stage1", "qa", WorkerNames);
		ph.createStepeForlessTimeout();
		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(WorkerNames);
		ph.triggerPipelineUsingButton();
		ph.pipelineTriggerVerificaton();
		ph.pipelineRunningVerifycation(pipelineName, WorkerNames);
		String buildID = ph.getBuildID(WorkerNames);
		String TriggerID = wrkname + "-" + buildID;
		tIH.emailVerifycation("");
	}

	@Test(enabled = false, priority = 3) //
	public void Validate_WebhookGithub_Plugin() throws Exception { // we are unable to find the api for the old github
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
		av.openApplicationDashboard(applicationName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		wh.workerAgentNameFinder();
		wh.addWorker(workerName);
		wh.downloadWorker();
		wh.agentRunning(WorkerNames);
		wh.workerPageRefresh();
		tIH.navTotoolIntergration();
		tIH.pluginSearch("webhook_github");
		tIH.clickOnSave();
		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		String pipelineName = "pipeline" + workerName;
		String wrkname = applicationName + "-" + pipelineName;
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipeline("stage1", "qa", WorkerNames);
		ph.createStepeForlessTimeout();
		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();

		String webhookDetails = ph.webHookDetailsFetch(WorkerNames);
		log.info(webhookDetails);

		int ids = gitTest.createWebHook("satheshkumar-m_infosys", "WebHookTest_Sk",
				"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", webhookDetails);
		String shaID = gitTest.createNewFile("satheshkumar-m_infosys", "WebHookTest_Sk",
				"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", "newfile5.txt", "first commit new file",
				"Testing file creation");

		// gitTest.deleteWebHook("satheshkumar-m_infosys","WebHookTest_Sk","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",webhookDetails,ids);

//			ph.workerSelectionInPipelineTrigger(WorkerNames);
//			ph.triggerPipelineUsingButton();
//			ph.pipelineTriggerVerificaton();
//			ph.pipelineRunningVerifycation(pipelineName, WorkerNames);
//			String buildID = ph.getBuildID(WorkerNames);
//			String TriggerID = wrkname+"-"+buildID;
//			tIH.emailVerifycation("");
//			driver.navigate().refresh();
//			av.openApplicationDashboard(applicationName);
//			String foldername = applicationName + "_" + pipelineName;
//			String agentName = wh.agentNames();
//			wh.finalSteps(agentName,WorkerNames,foldername);
//			tIH.navTotoolIntergration();
//			tIH.delectPlugin("msteams_status");

	}
}
