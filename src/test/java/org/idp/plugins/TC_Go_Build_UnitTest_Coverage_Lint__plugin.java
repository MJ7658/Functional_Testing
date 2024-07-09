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

public class TC_Go_Build_UnitTest_Coverage_Lint__plugin extends LoadData {

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();

	@Test(priority = 1)
	public void validate_Go_Build_UnitTest_Coverage_Lint_Plugin() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);

		String gitURL = WorkerHelper.readparticularRowCol(1, 14);
		String token = WorkerHelper.readparticularRowCol(2, 14);
		String path = WorkerHelper.readparticularRowCol(3, 14);

		String gitURL1 = WorkerHelper.readparticularRowCol(4, 14);
		String token1 = WorkerHelper.readparticularRowCol(5, 14);

		String binaryName = WorkerHelper.readparticularRowCol(6, 14);
		String matchingPattern = WorkerHelper.readparticularRowCol(7, 14);
		String repoToken = WorkerHelper.readparticularRowCol(8, 14);
		String repoUsername = WorkerHelper.readparticularRowCol(9, 14);
		String projectDir = WorkerHelper.readparticularRowCol(10, 14);

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

		ph.addGitSCMInput("gitscm", gitURL, token);

		ph.dyanamicStepAndPlugin("stage1", "Go Lint");
		ph.addGoLintluginInput("Go-Lint", path);

		ph.dyanamicStepAndPlugin("stage1", "Go Unit Test");
		ph.addGoUnitTestpluginInput("Go-Unit-Test", "github.com/Infosys-", path);

		ph.dyanamicStepAndPlugin("stage1", "Go Coverage");
		ph.addGoCoveragepluginInput("Go-Coverage", path);

		ph.addStageInPipelineWithoutEnv("stage2", BuildMachineWorker);
		Thread.sleep(3000);
		ph.dyanamicStepAndPlugin("stage2", "gitscm");
		ph.addGitSCMInputDynamicStage("stage2", "gitscm", gitURL1, token1);

		ph.dyanamicStepAndPlugin("stage2", "Go Build");
		ph.addGoBuildpluginInput("Go-Build", "-a -installsuffix cgo", binaryName, matchingPattern, repoToken,
				repoUsername, projectDir);

		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(BuildMachineWorker);
		ph.triggerPipelineUsingButtonWith2Branch("master", "dev");
		Thread.sleep(75000);
		driver.navigate().refresh();
		Thread.sleep(3000);
		ph.navigateToPipeline();
		Thread.sleep(10000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);

		String foldername = applicationName + "_" + pipelineName;
		ph.navLogsDetails(foldername);
		String result = ph.getLog("Building for Linux");
		boolean flag;
		if (result.contains("Building for Linux")) {
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
