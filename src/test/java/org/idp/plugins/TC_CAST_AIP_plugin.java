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

public class TC_CAST_AIP_plugin extends LoadData {

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();

	@Test(priority = 1)
	public void validate_CAST_AIP_Plugin() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);

		String applicationName = "Vigneshapp";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = "Manohar";
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + wh.currentDatePickers1();

		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipelineWithoutEnv("stage1", WorkerNames);

		ph.dyanamicStepAndPlugin("stage1", "gitscm");
		ph.addGitSCMInput("gitscm", "https://github.com/Infosys-AG-Core-Platform/dashboard.git",
				"Z2hwX1NmS0JmY2xHRGYwRjJJSXI5TUxUWVNFQTRTUGVnUjBiZXVjYw==");

		ph.dyanamicStepAndPlugin("stage1", "CAST-AIP");
		ph.addCAST_AIPpluginInput("CAST-AIP", "dashboard", "IDP",
				"D:\\Source code\\IDP_rescan_V5\\dashboard-dev (1)\\dashboard-dev", "idp_dashboard_svc_mngt",
				"idp_dashboard_svc", "http://blrhciqltyws346:8086/CAST-Health-Engineering_IDP/");

		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(WorkerNames);
		ph.triggerPipelineUsingButtonWithBranch("dev");
		driver.navigate().refresh();
		Thread.sleep(3000);
		ph.navigateToPipeline();
		Thread.sleep(5000);
		ph.filterPipelineAndNavToExeHistory(pipelineName);

		String foldername = applicationName + "_" + pipelineName;
		ph.navLogsDetails(foldername);
		String result = ph.getLog("INFO Application IDP not found in the portfolio");

		String[] filterValue = result.split("]");
		String[] fileter = filterValue[1].trim().split(" ");

		boolean flag;
		if (fileter[1].trim().equalsIgnoreCase("INFO Application IDP not found in the portfolio")) {
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
