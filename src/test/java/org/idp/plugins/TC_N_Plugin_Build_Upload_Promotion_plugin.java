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

public class TC_N_Plugin_Build_Upload_Promotion_plugin extends LoadData {

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();

	@Test(priority = 1)
	public void validate_N_Plugin_Build_Upload_Promotion_Plugin() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();

		String github = WorkerHelper.readparticularRowCol(1, 26);
		String Token = WorkerHelper.readparticularRowCol(2, 26);
		String disributionList = WorkerHelper.readparticularRowCol(1, 27);
		String pluginDir = WorkerHelper.readparticularRowCol(2, 27);

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
		ph.addGitSCMInput("gitscm", github, Token);

		ph.dyanamicStepAndPlugin("stage1", "nplugin_build");
		ph.addnplugin_buildInput("nplugin_build", pluginDir, disributionList);
		ph.submitPipeline();

		ph.addParameterWithTrigger("version", "");
		ph.navToParametersAndClickONAdd();
		ph.paramterVariableNameCreation("Registry_url");
		ph.parameterEnvCreation("test", "https://agtest.ad.infosys.com/registry/v1/prod");
		ph.parameterSaveButtonClick();
		ph.parametertoConfigurationNav();
		Thread.sleep(2000);
		ph.addnplugin_parameter("nplugin_build", "//span[text()=' Inputs for NPLUGIN_BUILD ']//following::input[2]",
				"${version}");

//		ph.dyanamicStepAndPlugin("stage1", "Plugin-Upload");
//		ph.addpluginUploadPluginInput("Plugin-Upload", "${Registry_url}", "${version}", "sonar");

//
//		ph.dyanamicStepAndPlugin("stage1", "nplugin_promotion");
//		ph.addnplugin_promotionPluginInput("nplugin_promotion", "linux/ppc64le,windows/arm64", "sonar");

		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(BuildMachineWorker);
		ph.triggerPipelineUsingButtonWith2Branch("master", "test");
		driver.navigate().refresh();
		Thread.sleep(90000);
		ph.navigateToPipeline();
		Thread.sleep(25000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		String foldername = applicationName + "_" + pipelineName;
		ph.navLogsDetails(foldername);
		String result = ph.getLog("Manifest saved successfully");
		boolean flag;
		if (result.contains("Manifest saved successfully")) {
			flag = true;
		} else {
			flag = false;
		}
		assertTrue(flag);
		Thread.sleep(2000);
		driver.navigate().refresh();
	}
}
