package org.infy;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.infy.init.Constants;
import org.infy.init.ListenersTest;
import org.infy.init.LoadData;
import org.infy.uiPages.CommonMethods;
import org.infy.uiPages.Parameters;
import org.infy.uiPages.PipelineView;
import org.infy.uiPages.TriggerPipeline;
import org.infy.uiPages.Workflows;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

@Listeners(ListenersTest.class)
public class DotNet_Build extends LoadData {

	public DotNet_Build() throws FileNotFoundException, IOException, ParseException {
		super();
	}

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();

	@Test(enabled = true, priority = 1)
	public void validate_Sensitive_fields_masking_in_UI_and_Logs()
			throws InterruptedException, FileNotFoundException, IOException, ParseException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		pv.navigateToPipeLineEditor();
		JSONObject request = LoadData.readJSONFileToCreatePipeline(Constants.INPUTPIPELINEDATA_JSON_PATH);
		pv.createPipeline(request);
		String[] plugins = { "dotnet build" };
		pv.addPlugin(request, plugins);
		pv.submitPipeline();
		pv.setPipelineNameCreated();
		request = LoadData.readJSONFile(Constants.TRIGGERPIPELINEDATA_JSON_PATH);
		workflow.selectWorkflow();
		parameters.clickParametersTab();
		parameters.addParameters(Constants.INPUT_PARAMETERS_DATA_JSON_PATH);
		pv.openPipelineView();
		TriggerPipeline tg = new TriggerPipeline(driver);
		tg.triggerPipeline();
		tg.enterTriggerDetails(request);
		Thread.sleep(30000);
		String status = tg.verifyExecutionStatus();
		System.out.println("Execution Status: " + status);
		Thread.sleep(60000);
	}

	@Test(enabled = true, priority = 2)
	public void pipeline_Creation_With_One_Stage()
			throws InterruptedException, FileNotFoundException, IOException, ParseException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		JSONObject request = LoadData.readJSONFileToCreatePipeline(Constants.INPUTPIPELINEDATA_JSON_PATH);
		PipelineView pv = new PipelineView(driver);
		pv.navigateToPipeLineEditor();
		pv.createPipeline(request);
	}

	@Test(enabled = true, priority = 3)
	public void trigger_Pipeline_with_One_Stage()
			throws InterruptedException, FileNotFoundException, IOException, ParseException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		pv.navigateToPipeLineEditor();

		JSONObject request = LoadData.readJSONFileToCreatePipeline(Constants.INPUTPIPELINEDATA_JSON_PATH);
		pv.createPipeline(request);
		String[] plugins = { "dotnet build" };
		pv.addPlugin(request, plugins);
		pv.submitPipeline();
		pv.setPipelineNameCreated();
		common = new CommonMethods(driver);

		request = LoadData.readJSONFile(Constants.TRIGGERPIPELINEDATA_JSON_PATH);
		workflow.selectWorkflow();
		parameters.clickParametersTab();
		parameters.addParameters(Constants.INPUT_PARAMETERS_DATA_JSON_PATH);
		pv.openPipelineView();
		TriggerPipeline tg = new TriggerPipeline(driver);
		tg.triggerPipeline();
		tg.enterTriggerDetails(request);
	}

	@Test(enabled = true, priority = 4)
	public void plugin_UI_fields_Validation() throws InterruptedException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		pv.navigateToPipeLineEditor();
		JSONObject request = LoadData.readJSONFileToCreatePipeline(Constants.INPUTPIPELINEDATA_JSON_PATH);
		pv.createPipeline(request);
		String[] plugins = { "dotnet build" };
		pv.addPlugin(request, plugins);
		common = new CommonMethods(driver);
	}

	@Test(enabled = true, priority = 5)
	public void addition_Of_Plugin_in_Pipeline() throws InterruptedException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		pv.navigateToPipeLineEditor();
		JSONObject request = LoadData.readJSONFileToCreatePipeline(Constants.INPUTPIPELINEDATA_JSON_PATH);
		pv.createPipeline(request);
		String[] plugins = { "dotnet build" };
		pv.addPlugin(request, plugins);
		pv.savePluginDetails();
	}

	@Test(enabled = true, priority = 6)
	public void UIValidation_for_Mandatory_Fields() throws InterruptedException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		pv.navigateToPipeLineEditor();
		common = new CommonMethods(driver);
		JSONObject request = LoadData.readJSONFileToCreatePipeline(Constants.INPUTPIPELINEDATA_JSON_PATH);
		pv.createPipeline(request);
		String[] plugins = { "dotnet build" };
		pv.addPlugin(request, plugins);
		pv.expandPluginDetails("Dotnet-Build");
	}

	@Test(enabled = true, priority = 7, dependsOnMethods = { "trigger_Pipeline_with_One_Stage" })
	public void edit_pipeline() throws InterruptedException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		pv.openPipelineView();
		pv.editPipeline("Dotnet-Build", "edit_dotnet_build");
		pv.submitPipeline();
	}

	@Test(enabled = true, priority = 8, dependsOnMethods = { "trigger_Pipeline_with_One_Stage" })
	public void clone_pipeline() throws InterruptedException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		pv.openPipelineView();
		pv.clonePipeline();
		pv.submitPipeline();
	}
}
