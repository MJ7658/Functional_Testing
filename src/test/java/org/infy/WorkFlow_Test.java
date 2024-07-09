package org.infy;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.infy.init.Constants;
import org.infy.init.ListenersTest;
import org.infy.init.LoadData;
import org.infy.uiPages.CommonMethods;
import org.infy.uiPages.Parameters;
import org.infy.uiPages.PipelineView;
import org.infy.uiPages.Workflows;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

@Listeners(ListenersTest.class)
public class WorkFlow_Test extends LoadData {

	public WorkFlow_Test() throws FileNotFoundException, IOException, ParseException {
		super();
	}

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();

	@Test(enabled = true, priority = 1)
	public void create_Pipeline_In_Pipeline_Workflows()
			throws InterruptedException, FileNotFoundException, IOException, ParseException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pvw = new PipelineView(driver);
		Thread.sleep(60000);

	}

	@Test(enabled = true, priority = 2)
	public void validate_Worker_And_Environment_in_Custom_Workflow()
			throws InterruptedException, FileNotFoundException, IOException, ParseException {
		PipelineView pvw = new PipelineView(driver);
		JSONObject request = LoadData.readJSONFileToCreatePipeline(Constants.INPUTPIPELINEDATA_JSON_PATH);
		JSONArray stages = (JSONArray) request.get("stages");
		JSONObject stage = (JSONObject) stages.get(0);
		Thread.sleep(60000);
	}
}
