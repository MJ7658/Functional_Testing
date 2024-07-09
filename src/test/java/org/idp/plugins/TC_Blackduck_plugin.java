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

public class TC_Blackduck_plugin extends LoadData {

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();

	@Test(priority = 1)
	public void validate_Blackduck_Plugin() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);

		String applicationName = WorkerHelper.excelRead(0);
		String workerName11 = "Test" + wh.currentDatePickers1();
		String workerName = "BuildmachineWorker";
		String pipelineName = "pipeline" + workerName11;

		String WorkerNames = applicationName + "_" + workerName;
		String WorkerNames1 = applicationName + "_" + workerName;

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv_Worker("stage1");
		ph.dyanamicStepAndPlugin("stage1", "gitscm");

		ph.addGitSCMInput("gitscm", "https://github.com/Infosys-AG-Core-Platform/portfolio.git",
				"Z2hwX1NmS0JmY2xHRGYwRjJJSXI5TUxUWVNFQTRTUGVnUjBiZXVjYw==");

		ph.dyanamicStepAndPlugin("stage1", "Blackduck");
		ph.addBlockDuckpluginInput("Blackduck", "https://osscompliancehub.ad.infosys.com", "IDP_svc_portfolio", "4.8.0",
				"portfolio",
				"TXpNNE5tSXdNek10TlRJNFlTMDBOamt6TFRnd1ltRXROMkZrTUdZeVpqQm1OVGMxT2pOaVlqY3laV1JrTFRka1lqZ3RORGN3TmkwNU5HTTJMVEE0TTJZMk5HVTRZemt3WXc9PQ==");

		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(WorkerNames);
		ph.triggerPipelineUsingButtonWithBranch("dev");
		driver.navigate().refresh();
		Thread.sleep(13000);
		ph.navigateToPipeline();
		Thread.sleep(150000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		String foldername = applicationName + "_" + pipelineName;
		ph.navLogsDetails(foldername);
		String result = ph.getLog("INFO Report uploaded successfully");
		boolean flag;
		if (result.contains("INFO Report uploaded successfully")) {
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
