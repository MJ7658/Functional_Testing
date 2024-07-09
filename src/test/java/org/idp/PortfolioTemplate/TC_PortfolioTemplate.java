package org.idp.PortfolioTemplate;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.infy.commonHelpers.PipelineHelper;
import org.infy.commonHelpers.WorkerHelper;
import org.infy.init.ListenersTest;
import org.infy.init.LoadData;
import org.infy.reportHelper.ExecutionReportHelper;
import org.infy.reportHelper.ReportCompare;
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
public class TC_PortfolioTemplate extends LoadData {

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();

	@Test(enabled = true, priority = 1)
	public void Validate_Portfolio_Template_Creation() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		ReportCompare comp = new ReportCompare();
		ExecutionReportHelper eh = new ExecutionReportHelper(driver);
		ReportHelper reportH = PageFactory.initElements(driver, ReportHelper.class);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		av.openApplicationDashboard(applicationName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipe" + workerName;
		String pipelineName1 = "pipeTe" + workerName;
		wh.workerAgentNameFinder();
		wh.addWorker(workerName);
		wh.downloadWorker();
		wh.agentRunning(WorkerNames);
		wh.workerPageRefresh();
		wh.instanceVerifycation("IdleInstance", WorkerNames, "0", "1");
		av.openPortfolioDashboardDetails("Automation_Functional_test");
		ph.navToPipelineTemplates();
		ph.createNewTemplate();
		ph.createPipelineName(applicationName, pipelineName);
		ph.addStageInPipeline("stage1", "qa", WorkerNames);
		ph.cmdPluglessTimeoutJavaVersion();
		String templateName = workerName + "tle";
		ph.saveTemplate(templateName);
		// ph.submitPipeline();
		driver.navigate().refresh();
		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName1);
		Thread.sleep(2000);
		ph.addPiplineTemplateWithName(templateName);
		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(WorkerNames);
		ph.triggerPipelineUsingButton();
		ph.pipelineTriggerVerificaton();
		ph.pipelineRunningVerifycation(pipelineName1, WorkerNames);
		Thread.sleep(2000);
		ph.navigateToPipelineExeHistory(pipelineName1);
		// boolean flag = ph.pipelineRunningSuccessVerifycation(pipelineName1,
		// WorkerNames);
		// assertTrue(flag);
		driver.navigate().refresh();
		Thread.sleep(2000);
		av.openApplicationDashboard(applicationName);
		String agentName = wh.agentNames();
		String foldername = applicationName + "_" + pipelineName1;
		wh.finalSteps(agentName, WorkerNames, foldername);
	}

	@Test(enabled = true, priority = 2)
	public void Validate_Portfolio_Template_PipelineUpdate_ShouldNot_Affect_PortfolioTemp() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		ReportCompare comp = new ReportCompare();
		ExecutionReportHelper eh = new ExecutionReportHelper(driver);
		ReportHelper reportH = PageFactory.initElements(driver, ReportHelper.class);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		av.openApplicationDashboard(applicationName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipe" + workerName;
		String pipelineName1 = "pipeTe" + workerName;
		String pipelineName2 = "pipeTem" + workerName;
		wh.workerAgentNameFinder();
		wh.addWorker(workerName);
		wh.downloadWorker();
		wh.agentRunning(WorkerNames);
		wh.workerPageRefresh();
		wh.instanceVerifycation("IdleInstance", WorkerNames, "0", "1");
		av.openPortfolioDashboardDetails("Automation_Functional_test");
		ph.navToPipelineTemplates();
		ph.createNewTemplate();

		// ph.createPipelineName(applicationName, pipelineName);
		String stageDetails = "Stage1";
		String pluginDetails = "cmdexec";
		String pluginParameter = "java -version";
		ph.addStageInPipeline(stageDetails, "qa", WorkerNames);
		ph.createDynamicStepsPluginParameter(stageDetails, pluginDetails, "");
		String templateName = workerName + "tle";
		ph.saveTemplate(templateName);
		// ph.submitPipeline();
		Thread.sleep(2000);
		driver.navigate().refresh();
		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName1);
		Thread.sleep(2000);
		ph.addPiplineTemplateWithName(templateName);
		ph.editPlugin(stageDetails, pluginDetails, pluginParameter);
		ph.submitPipeline();
		Thread.sleep(2000);
		driver.navigate().refresh();
		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName2);
		ph.addPiplineTemplateWithName(templateName);
		boolean flag = ph.verifyEditPluginParameter(stageDetails, pluginDetails, "");
		System.out.println(flag);
		assertTrue(flag);
		driver.navigate().refresh();
		Thread.sleep(2000);
		av.openApplicationDashboard(applicationName);
		String agentName = wh.agentNames();
		wh.finalSteps(agentName, WorkerNames, "worker");
	}
}
