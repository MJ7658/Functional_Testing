package org.idp.telemetry;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;

import org.infy.commonHelpers.PipelineHelper;
import org.infy.commonHelpers.TelementryHelper;
import org.infy.commonHelpers.WorkerHelper;
import org.infy.init.ListenersTest;
import org.infy.reportHelper.ReportHelper;
import org.infy.uiPages.ApplicationView;
import org.infy.uiPages.CommonMethods;
import org.infy.uiPages.Parameters;
import org.infy.uiPages.PipelineView;
import org.infy.uiPages.Workflows;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

@Listeners(ListenersTest.class)
public class TC_Telemetry_All {

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();

	@Test(enabled = true, priority = 1) // QAUTOIDP-28647
	public void Validate_Telemetry_OverAllCount() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		TelementryHelper th = new TelementryHelper(driver);
		th.navTelementry();
		ArrayList<String> listcount = th.telementryCountCollect();

		for (String string : listcount) {
			System.out.println(string);
		}
	}

	@Test(enabled = true, priority = 2)
	public void to_Validate_Telemetry_AI_ML_Feature_Usage() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		TelementryHelper th = new TelementryHelper(driver);
		String pipelineName = "PipelineLevel_Trends";
		String BuildMachineWorker = "WorkerAutomation_BuildmachineWorker";
		th.navTelementry();
		driver.navigate().refresh();
		ph.navigateToPipeline();
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		ph.navLogsDetails(BuildMachineWorker);
		ph.navResolveGPTPage();
		ph.navPipelineDetailsPage();
		ph.navPipelineDetailsPage_SecurityTab();
		ph.navigateToPipeline();
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		ph.navLogsDetails(BuildMachineWorker);
		ph.navPipelineDetailsPage();
		ph.navPipelineDetailsPage_SecurityTab();
		ph.navigateToPipeline();
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		ph.navLogsDetails(BuildMachineWorker);
		ph.navPipelineDetailsPage();
		ph.navPipelineDetailsPage_SecurityTab();
		ph.navigateToPipeline();
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		ph.navLogsDetails(BuildMachineWorker);
		ph.navPipelineDetailsPage();
		ph.navPipelineDetailsPage_SecurityTab();
		driver.navigate().refresh();
		Thread.sleep(4000);
		ph.navigateToPipeline();
		th.navTelementry();
		Thread.sleep(8000);
		th.navFeatureUsage();
		driver.navigate().refresh();
		Thread.sleep(4000);
		th.navTelementry();
		th.navFeatureUsage();
		String value = th.validate_Resolve_GPT();
		System.out.println(value);
		driver.navigate().refresh();
		ph.navigateToPipeline();
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		ph.navLogsDetails(BuildMachineWorker);
		ph.navResolveGPTPage();
		ph.navPipelineDetailsPage();
		ph.navPipelineDetailsPage_SecurityTab();
		ph.navigateToPipeline();
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		ph.navLogsDetails(BuildMachineWorker);
		ph.navPipelineDetailsPage();
		ph.navPipelineDetailsPage_SecurityTab();
		ph.navigateToPipeline();
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		ph.navLogsDetails(BuildMachineWorker);
		ph.navPipelineDetailsPage();
		ph.navPipelineDetailsPage_SecurityTab();
		ph.navigateToPipeline();
		ph.filterPipelineAndNavToExeHistory(pipelineName);
		ph.navLogsDetails(BuildMachineWorker);
		ph.navPipelineDetailsPage();
		ph.navPipelineDetailsPage_SecurityTab();
		driver.navigate().refresh();
		Thread.sleep(4000);
		ph.navigateToPipeline();
		th.navTelementry();
		Thread.sleep(8000);
		th.navFeatureUsage();
		driver.navigate().refresh();
		Thread.sleep(4000);
		th.navTelementry();
		th.navFeatureUsage();
		String value1 = th.validate_Resolve_GPT();
		System.out.println(value1);
		assertEquals(value, value1);
	}
}
