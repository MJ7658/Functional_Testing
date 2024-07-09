package org.infy;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.infy.init.Constants;
import org.infy.init.ListenersTest;
import org.infy.init.LoadData;
import org.infy.uiPages.ApplicationView;
import org.infy.uiPages.CommonMethods;
import org.infy.uiPages.Workflows;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

@Listeners(ListenersTest.class)
public class Application_WorkFlows_Test extends LoadData {

	public Application_WorkFlows_Test() throws FileNotFoundException, IOException, ParseException {
		super();
	}

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();

	@Test(enabled = true, priority = 1)
	public void create_Workflows_in_Application() throws InterruptedException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView ap = new ApplicationView(driver);
		Thread.sleep(2000);
		JSONObject request1 = LoadData.readJSONFileToCreatePipeline(Constants.INPUTPIPELINEDATA_JSON_PATH);
		Thread.sleep(2000);
		ap.openApplicationUsingManage(request1.get("applicationName").toString());
		Thread.sleep(60000);
	}

	@Test(enabled = true, priority = 2)
	public void trigger_And_Validate_Parallel_And_Sequential_Execution() throws InterruptedException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView ap = new ApplicationView(driver);
		JSONObject request1 = LoadData.readJSONFileToCreatePipeline(Constants.INPUTPIPELINEDATA_JSON_PATH);
		JSONObject request2 = LoadData.readJSONFile(Constants.APPLICATION_WORKFLOWS_DATA_JSON_PATH);
		ap.openApplicationUsingManage(request1.get("applicationName").toString());
		Thread.sleep(60000);
	}
}
