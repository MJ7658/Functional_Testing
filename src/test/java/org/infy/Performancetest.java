package org.infy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

@Listeners(ListenersTest.class)
public class Performancetest extends LoadData {

	public Performancetest() throws FileNotFoundException, IOException, ParseException {
		super();
	}

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();

	@Test(enabled = false, priority = 1)
	public void validate_Sensitive_fields_masking_in_UI_and_Logs()
			throws InterruptedException, FileNotFoundException, IOException, ParseException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		Thread.sleep(60000);
	}

	@Test(enabled = true, priority = 2)
	public void pipeline_Creation_With_One_Stage()
			throws InterruptedException, FileNotFoundException, IOException, ParseException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		JSONObject request = LoadData.readJSONFileToCreatePipeline(Constants.INPUTPIPELINEDATA_JSON_PATH);
		PipelineView pv = new PipelineView(driver);
		SoftAssert sa = new SoftAssert();

		pv.navigateToPipeLineEditor();
		pv.createPipeline(request);
		JSONObject pluginObj = pv.addStage(request);
		String[] plugins = { "gitscm", "Jmeter" };
		for (String plugin : plugins) {
			pv.addPlugin(pluginObj, plugin);
			if (plugin.equals("gitscm")) {
				sa.assertEquals(pv.getRepositoryPasswordType("password"), "password", "Password field is not masked");
			}
		}
		pv.submitPipeline();
		pv.setPipelineNameCreated();
		sa.assertEquals(new CommonMethods(driver).getPipelineSubmissionMessage(), "Pipeline created successfully!",
				"Pipeline creation failed");
		sa.assertAll();
		Thread.sleep(60000);
	}

	@Test(enabled = true, priority = 3)
	public void trigger_Pipeline_with_One_Stage()
			throws InterruptedException, FileNotFoundException, IOException, ParseException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		SoftAssert sa = new SoftAssert();
		pv.navigateToPipeLineEditor();
		JSONObject request = LoadData.readJSONFileToCreatePipeline(Constants.INPUTPIPELINEDATA_JSON_PATH);
		pv.createPipeline(request);
		JSONObject pluginObj = pv.addStage(request);
		String[] plugins = { "gitscm", "Jmeter" };
		for (String plugin : plugins) {
			pv.addPlugin(pluginObj, plugin);
			if (plugin.equals("gitscm")) {
				sa.assertEquals(pv.getRepositoryPasswordType("password"), "password", "Password field is not masked");
			}
		}
		pv.submitPipeline();
		pv.setPipelineNameCreated();
		common = new CommonMethods(driver);
		sa.assertEquals(common.getPipelineSubmissionMessage(), "Pipeline created successfully!",
				"Pipeline creation failed");
		request = LoadData.readJSONFile(Constants.TRIGGERPIPELINEDATA_JSON_PATH);
		pv.openPipelineView();
		TriggerPipeline tg = new TriggerPipeline(driver);
		tg.triggerPipeline();
		new WebDriverWait(driver, Duration.ofMinutes(2)).until(
				ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//app-formly-field-mat-input/input"))))
				.clear();
		driver.findElement(By.xpath("//app-formly-field-mat-input/input")).sendKeys("main");
		tg.enterTriggerDetails2(request);
		sa.assertEquals(common.getPipelineSubmissionMessage(), "Pipeline triggered successfully!",
				"Pipeline triggering failed");
		sa.assertAll();
		Thread.sleep(60000);
	}

	@Test(enabled = true, priority = 4)
	public void plugin_UI_fields_Validation() throws InterruptedException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		pv.navigateToPipeLineEditor();
		JSONObject request = LoadData.readJSONFileToCreatePipeline(Constants.INPUTPIPELINEDATA_JSON_PATH);
		pv.createPipeline(request);
		String plugin = "Jmeter";
		JSONObject pluginObj = pv.addStage(request);
		pv.addPlugin(pluginObj, plugin);
		common = new CommonMethods(driver);
		SoftAssert sa = new SoftAssert();
		pv.expandPluginDetails("Jmeter");

		sa.assertEquals(common.getFieldsHeaderName("Step Name"), "Step Name", "Step Name is not matched");
		sa.assertTrue(
				(common.getFieldType("jmxFile").equals("text") && common.getFieldText("jmxFile").equals("JMX File *")),
				"JMX File is not a text Field or Name not Matched");

		sa.assertAll();
		Thread.sleep(60000);
	}

	@Test(enabled = true, priority = 5)
	public void addition_Of_Plugin_in_Pipeline() throws InterruptedException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		SoftAssert sa = new SoftAssert();
		pv.navigateToPipeLineEditor();
		JSONObject request = LoadData.readJSONFileToCreatePipeline(Constants.INPUTPIPELINEDATA_JSON_PATH);
		pv.createPipeline(request);
		JSONObject pluginObj = pv.addStage(request);
		String[] plugins = { "gitscm", "Jmeter" };
		for (String plugin : plugins) {
			pv.addPlugin(pluginObj, plugin);

			if (plugin.equals("gitscm")) {
				sa.assertEquals(pv.getRepositoryPasswordType("password"), "password", "Password field is not masked");
			}
		}
		pv.submitPipeline(); // ?
		sa.assertEquals(pv.verifyPluginIsAdded("Jmeter"), "Jmeter", "Plugin is not added");
		sa.assertAll();
		Thread.sleep(60000);
	}

	@Test(enabled = true, priority = 6)
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
		String plugin = "Jmeter";
		JSONObject pluginObj = pv.addStage(request);
		pv.addPlugin(pluginObj, plugin);
		pv.expandPluginDetails("Jmeter");
		sa.assertEquals(common.verifyStepNameIsMandatory("Step Name"), "Please enter step name",
				"Step Name is not a mandatory field");
		sa.assertEquals(common.verifypluginMandatoryFields("jmxFile"), "This field is required",
				"jmx File is not a mandatory field");
		sa.assertAll();
	}

	@Test(enabled = true, priority = 7, dependsOnMethods = { "trigger_Pipeline_with_One_Stage" })
	public void edit_pipeline() throws InterruptedException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		SoftAssert sa = new SoftAssert();
		pv.openPipelineView();
		pv.editPipeline("Jmeter", "editjmeter");
		pv.submitPipeline();
		sa.assertTrue(pv.getStepName("editjmeter"));
		sa.assertEquals(new CommonMethods(driver).getPipelineSubmissionMessage(), "Pipeline updated successfully!",
				"Pipeline creation failed");
		sa.assertAll();
	}

	@Test(enabled = true, priority = 8, dependsOnMethods = { "trigger_Pipeline_with_One_Stage" })
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
