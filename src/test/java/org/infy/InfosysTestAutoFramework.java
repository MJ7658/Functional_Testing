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
import org.testng.asserts.SoftAssert;

//import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.ExtentTest;

@Listeners(ListenersTest.class)
public class InfosysTestAutoFramework extends LoadData {

	public InfosysTestAutoFramework() throws FileNotFoundException, IOException, ParseException {
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
		Thread.sleep(60000);
	}

	@Test(enabled = false, priority = 2)
	public void pipeline_Creation_With_One_Stage()
			throws InterruptedException, FileNotFoundException, IOException, ParseException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		JSONObject request = LoadData.readJSONFileToCreatePipeline(Constants.INPUTPIPELINEDATA_JSON_PATH);
		PipelineView pv = new PipelineView(driver);
		SoftAssert sa = new SoftAssert();
		pv.navigateToPipeLineEditor();
		pv.createPipeline(request);
		String[] plugins = { "iTAF" };
		pv.addPlugin(request, plugins);
		pv.submitPipeline();
		pv.setPipelineNameCreated();
		sa.assertEquals(new CommonMethods(driver).getPipelineSubmissionMessage(), "Pipeline created successfully!",
				"Pipeline creation failed");
		workflow.selectWorkflow();
		parameters.clickParametersTab();
		parameters.addParameters(Constants.INPUT_PARAMETERS_DATA_JSON_PATH);
		sa.assertEquals(new CommonMethods(driver).getPipelineSubmissionMessage(), "Worflow Created",
				"Workflow creation failed");
		sa.assertAll();
	}

	@Test(enabled = false, priority = 3)
	public void trigger_Pipeline_with_One_Stage()
			throws InterruptedException, FileNotFoundException, IOException, ParseException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		pv.navigateToPipeLineEditor();
		SoftAssert sa = new SoftAssert();
		JSONObject request = LoadData.readJSONFileToCreatePipeline(Constants.INPUTPIPELINEDATA_JSON_PATH);
		pv.createPipeline(request);
		String[] plugins = { "iTAF" };
		pv.addPlugin(request, plugins);
		pv.submitPipeline();
		pv.setPipelineNameCreated();
		common = new CommonMethods(driver);
		sa.assertEquals(common.getPipelineSubmissionMessage(), "Pipeline created successfully!",
				"Pipeline creation failed");
		request = LoadData.readJSONFile(Constants.TRIGGERPIPELINEDATA_JSON_PATH);
		workflow.selectWorkflow();
		parameters.clickParametersTab();
		parameters.addParameters(Constants.INPUT_PARAMETERS_DATA_JSON_PATH);
		pv.openPipelineView();
		TriggerPipeline tg = new TriggerPipeline(driver);
		tg.triggerPipeline();
		tg.enterTriggerDetails(request);
		sa.assertEquals(common.getPipelineSubmissionMessage(), "Pipeline triggered successfully!",
				"Pipeline creation failed");
		sa.assertAll();
	}

	@Test(enabled = false, priority = 4)
	public void plugin_UI_fields_Validation() throws InterruptedException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		pv.navigateToPipeLineEditor();
		JSONObject request = LoadData.readJSONFileToCreatePipeline(Constants.INPUTPIPELINEDATA_JSON_PATH);
		pv.createPipeline(request);
		String[] plugins = { "iTAF" };
		pv.addPlugin(request, plugins);
		common = new CommonMethods(driver);
		SoftAssert sa = new SoftAssert();
		pv.expandPluginDetails("ITAF");
		sa.assertEquals(common.getFieldsHeaderName("Step Name"), "Step Name", "Step Name is not matched");
		sa.assertTrue(
				(common.getFieldType("iTafUrl").equals("text") && common.getFieldText("iTafUrl").equals("ITAF Url *")),
				"iTAF Url is not a text Field or Name not Matched");
		sa.assertTrue(
				(common.getFieldType("iTafUsername").equals("text")
						&& common.getFieldText("iTafUsername").equals("Itaf Username *")),
				"Itaf Username is not a text Field or Name not Matched");
		sa.assertTrue(
				(common.getFieldType("iTafAgent").equals("text")
						&& common.getFieldText("iTafAgent").equals("Itaf Agent *")),
				"Itaf Agent is not a text Field or Name not Matched");
		sa.assertTrue(
				(common.getFieldType("iTafProject").equals("text")
						&& common.getFieldText("iTafProject").equals("Itaf ProjectName *")),
				"Itaf ProjectName is not a text Field or Name not Matched");
		sa.assertTrue(
				(common.getFieldType("browser").equals("text") && common.getFieldText("browser").equals("Browser *")),
				"Browser is not a text Field or Name not Matched");
		sa.assertTrue(
				(common.getFieldType("testSuites").equals("text")
						&& common.getFieldText("testSuites").equals("testSuites *")),
				"TestSuites is not a text Field or Name not Matched");
		sa.assertAll();
	}

	@Test(enabled = false, priority = 5)
	public void addition_Of_Plugin_in_Pipeline() throws InterruptedException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		SoftAssert sa = new SoftAssert();
		pv.navigateToPipeLineEditor();
		JSONObject request = LoadData.readJSONFileToCreatePipeline(Constants.INPUTPIPELINEDATA_JSON_PATH);
		pv.createPipeline(request);
		String[] plugins = { "iTAF" };
		pv.addPlugin(request, plugins);
		pv.savePluginDetails();
		sa.assertEquals(pv.verifyPluginIsAdded("ITAF"), "ITAF", "Plugin is not added");
		sa.assertAll();
	}

	@Test(enabled = false, priority = 6)
	public void UIValidation_for_Mandatory_Fields() throws InterruptedException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		pv.navigateToPipeLineEditor();
		SoftAssert sa = new SoftAssert();
		common = new CommonMethods(driver);
		sa.assertEquals(common.verifyMandatoryFields("Application Name"), "Select Application",
				"Application Name is not a mandatory field");
		sa.assertEquals(common.verifyMandatoryFields("Pipeline Name"), "Enter Pipeline Name",
				"Pipeline Name is not a mandatory field");
		JSONObject request = LoadData.readJSONFileToCreatePipeline(Constants.INPUTPIPELINEDATA_JSON_PATH);
		pv.createPipeline(request);
		String[] plugins = { "iTAF" };
		pv.addPlugin(request, plugins);
		pv.expandPluginDetails("ITAF");
		sa.assertEquals(common.verifyStepNameIsMandatory("Step Name"), "Please enter step name",
				"Step Name is not a mandatory field");
		sa.assertEquals(common.verifypluginMandatoryFields("iTafUrl"), "This field is required",
				"Compile argument is not a mandatory field");
		sa.assertEquals(common.verifypluginMandatoryFields("iTafUsername"), "This field is required",
				"NPM arguments is not a mandatory field");
		sa.assertEquals(common.verifypluginMandatoryFields("iTafAgent"), "This field is required",
				"Working directory is not a mandatory field");
		sa.assertEquals(common.verifypluginMandatoryFields("iTafProject"), "This field is required",
				"Compile argument is not a mandatory field");
		sa.assertEquals(common.verifypluginMandatoryFields("browser"), "This field is required",
				"NPM arguments is not a mandatory field");
		sa.assertEquals(common.verifypluginMandatoryFields("testSuites"), "This field is required",
				"Working directory is not a mandatory field");
		sa.assertAll();
	}

	@Test(enabled = false, priority = 7, dependsOnMethods = { "trigger_Pipeline_with_One_Stage" })
	public void edit_pipeline() throws InterruptedException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		SoftAssert sa = new SoftAssert();
		pv.openPipelineView();
		pv.editPipeline("ITAF", "edit_iTAF");
		pv.submitPipeline();
		sa.assertTrue(pv.getStepName("edit_iTAF"));
		sa.assertEquals(new CommonMethods(driver).getPipelineSubmissionMessage(), "Pipeline updated successfully!",
				"Pipeline creation failed");
		sa.assertAll();
	}

	@Test(enabled = false, priority = 8, dependsOnMethods = { "trigger_Pipeline_with_One_Stage" })
	public void clone_pipeline() throws InterruptedException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		SoftAssert sa = new SoftAssert();
		pv.openPipelineView();
		pv.clonePipeline();
		pv.submitPipeline();
		sa.assertEquals(new CommonMethods(driver).getPipelineSubmissionMessage(), "Pipeline created successfully!",
				"Pipeline creation failed");
		pv.openPipelineView();
		sa.assertTrue(pv.verifyPipelineIsClonned());
		sa.assertAll();
	}
}
