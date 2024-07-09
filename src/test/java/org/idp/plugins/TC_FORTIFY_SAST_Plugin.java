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
public class TC_FORTIFY_SAST_Plugin extends LoadData {

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();

	@Test(enabled = true, priority = 1)
	public void Validate_FortifySAST_Security_Details() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;

		String BuildMachineWorker = "WorkerAutomation_BuildmachineWorker";
		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", BuildMachineWorker);
		ph.dyanamicStepAndPlugin("stage1", "gitscm");
		ph.addGitSCMInput("gitscm", "https://github.com/Infosys-idp/ui-notification-manager.git",
				"Z2hwX1NmS0JmY2xHRGYwRjJJSXI5TUxUWVNFQTRTUGVnUjBiZXVjYw==");
		ph.dyanamicStepAndPlugin("stage1", "Fortify-SAST");

		ph.addSASTPlugin("Fortify-SAST", "ui-notification-manager", "node_modules",
				"MWU0ZDQ3OTEtOWZiMC00NWY5LTliNjEtMDIyOWNlNjYxNDUy");
		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(BuildMachineWorker);
		ph.triggerPipelineUsingButtonWithBranch("dev");
		driver.navigate().refresh();
		Thread.sleep(3000);
		ph.navigateToPipeline();
		Thread.sleep(5000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);

		String foldername = applicationName + "_" + pipelineName;
		Thread.sleep(15000);
		ph.navLogsDetails(foldername);
		String result = ph.getLog("INFO Report generated successfully");

		boolean flag;
		if (result.contains("INFO Report generated successfully")) {
			flag = true;
		} else {
			flag = false;
		}
		assertTrue(flag);
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);

	}

}
