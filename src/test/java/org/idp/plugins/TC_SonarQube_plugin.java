package org.idp.plugins;

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
import org.infy.uiPages.Workflows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

@Listeners(ListenersTest.class)

public class TC_SonarQube_plugin extends LoadData {

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();

	@Test(priority = 1)
	public void validate_SonarQube_Plugin() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);

		String applicationName = WorkerHelper.excelRead(0);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = "WorkerAutomation_BuildmachineWorker";
		String pipelineName = "pipeline" + workerName;

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", WorkerNames);

		ph.dyanamicStepAndPlugin("stage1", "gitscm");
		ph.addGitSCMInput("gitscm", "https://github.com/Infosys-idp/ui-core.git",
				"Z2hwX3BnVnhHSjlwc2NOSGxOMkdVWnNJSE1RTmpFUXFISjJCVXFGVw==");

		ph.dyanamicStepAndPlugin("stage1", "SonarQube");
		ph.addSonarQubepluginInput("SonarQube", "dksljjfdfsjlk", "QLTY_QAUTO_nplugin_jfrogXray",
				"Jfrog-xray/sonar-project.properties", "https://infysonar.ad.infosys.com",
				"1edb5d6c0b66d32a4102c2a463f453be9d283d17");

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

		String[] filterValue = result.split("]");
		String[] fileter = filterValue[1].trim().split(" ");

		boolean flag;
		if (fileter[1].trim().equalsIgnoreCase("Uploading report")) {
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
