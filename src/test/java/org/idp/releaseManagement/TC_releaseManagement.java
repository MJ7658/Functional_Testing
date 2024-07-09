package org.idp.releaseManagement;

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
import org.infy.uiPages.PipelineView;
import org.infy.uiPages.Workflows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

@Listeners(ListenersTest.class)
public class TC_releaseManagement extends LoadData {
	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();

	@Test(enabled = true, priority = 1)
	public void To_validate_Release_Create_Update() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		av.openApplicationDashboard(applicationName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		av.naviToRelease();

		String date = av.currentDatePickersmins();

		String ReleaseName = "n";
		String envName = "QA";
		String updatedenvName = "Dev";

		String releaseName = av.releaseCreation(ReleaseName, envName);
		av.scrolldown();
		av.itemPerpageDynamic("100");
		Boolean value = av.verifyAfterCreateRelease(releaseName, envName);
		assertTrue(value);
		av.updateReleaseDetails(releaseName, updatedenvName);
		av.itemPerpageDynamic("100");
		Boolean value1 = av.verifyAfterCreateRelease(releaseName, updatedenvName);
		assertTrue(value1);
		Thread.sleep(3000);
		driver.navigate().refresh();
	}

	@Test(enabled = true, priority = 2)
	public void To_validate_Release_Archive() throws Exception {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper.workerExcelWriter();
		String applicationName = WorkerHelper.excelRead(0);
		av.openApplicationDashboard(applicationName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		String workerName = wh.excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;
		av.naviToRelease();

		String ReleaseName = "n";
		String envName = "QA";
		String updatedenvName = "Dev";

		String releaseName = av.releaseCreation(ReleaseName, envName);
		av.scrolldown();
		av.itemPerpageDynamic("100");
		av.scrolldown();
		Boolean value = av.verifyAfterCreateRelease(releaseName, envName);
		assertTrue(value);
		av.archveReleaseDetails(releaseName);
		av.includeArchived();
		av.scrolldown();
		av.itemPerpageDynamic("100");
		assertTrue(value);
		Boolean value2 = av.clickOnReleaseDetailsAndVerify(releaseName, envName);
		assertTrue(value2);
		av.clickOnClose();
	}
}