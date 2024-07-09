package org.idp.plugins;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.idp.pipelineeditor.TC_Workflow;
import org.infy.commonHelpers.PipelineHelper;
import org.infy.commonHelpers.WorkerHelper;
import org.infy.init.ListenersTest;
import org.infy.init.LoadData;
import org.infy.reportHelper.ReportHelper;
import org.infy.uiPages.ApplicationView;
import org.infy.uiPages.CommonMethods;
import org.infy.uiPages.Parameters;
import org.infy.uiPages.Workflows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

@Listeners(ListenersTest.class)

public class TC_ANGULAR_UNIT_TEST_KARMA_JASMINE_plugin extends LoadData {

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();
	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(TC_Workflow.class);

	@Test(priority = 1)
	public void validate_ANGULAR_UNIT_TEST_KARMA_JASMINE_Plugin() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);

		String gitRepo = WorkerHelper.readparticularRowCol(1, 0);
		String Token = WorkerHelper.readparticularRowCol(2, 0);
		String flagDetails = WorkerHelper.readparticularRowCol(1, 1);
		String nameOFTheproject = WorkerHelper.readparticularRowCol(2, 1);
		String nameOFdir = WorkerHelper.readparticularRowCol(3, 1);
		String applicationName = WorkerHelper.excelRead(0);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", WorkerNames);

		ph.dyanamicStepAndPlugin("stage1", "gitscm");
		ph.addGitSCMInput("gitscm", gitRepo, Token);

		ph.dyanamicStepAndPlugin("stage1", "Angular Unit Test with Karma-Jasmine");
		ph.addKarma_JasminepluginInput("Angular-Unit-Test-with-Karma-Jasmine", "karmaJasmine", flagDetails,
				nameOFTheproject, nameOFdir);

		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(WorkerNames);
		ph.triggerPipelineUsingButtonWithBranch("main");
		driver.navigate().refresh();
		Thread.sleep(3000);
		ph.navigateToPipeline();
		Thread.sleep(5000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		boolean value1 = ph.pipelineRunningSuccessVerifycation(pipelineName, WorkerNames);
		assertTrue(value1);
		String foldername = applicationName + "_" + pipelineName;
		ph.navLogsDetails(foldername);
		String result = ph.getLog("Uploading report");
		log.info(result);
		boolean flag;
		if (result.contains("Uploading report")) {
			flag = true;
		} else {
			flag = false;
		}
		assertTrue(flag);
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		av.openApplicationDashboard(applicationName);
		String agentName = wh.agentNames();
		String foldernames = applicationName + "_" + pipelineName;
		wh.finalSteps(agentName, WorkerNames, foldernames);
	}
}
