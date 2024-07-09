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
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

@Listeners(ListenersTest.class)
public class Tomcat {

	public Tomcat() throws FileNotFoundException, IOException, ParseException {
		super();
	}

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();

	@Test(enabled = true, priority = 3)
	public void validate_Sensitive_fields_masking_in_UI_and_Logs()
			throws InterruptedException, FileNotFoundException, IOException, ParseException {

		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		SoftAssert sa = new SoftAssert();
		pv.navigateToPipeLineEditor();
		JSONObject request = LoadData.readJSONFileToCreatePipeline(Constants.INPUTPIPELINEDATA_JSON_PATH);
		pv.createPipeline(request);
		JSONObject pluginObj = pv.addStage(request);
		String[] plugins = { "gitscm_tomcat", "tomcat_maven", "tomcat" };
		for (String plugin : plugins) {
			pv.addPlugin(pluginObj, plugin);
			if (plugin.equals("gitscm")) {
				sa.assertEquals(pv.getRepositoryPasswordType("password"), "password", "Password field is not masked");
			}
		}
		pv.submitPipeline();
		pv.setPipelineNameCreated();
		request = LoadData.readJSONFile(Constants.TRIGGERPIPELINEDATA_JSON_PATH);
		workflow.selectWorkflow();
		parameters.clickParametersTab();
		parameters.addParameters(Constants.INPUT_PARAMETERS_DATA_JSON_PATH);
		pv.openPipelineView();
		TriggerPipeline tg = new TriggerPipeline(driver);
		tg.triggerPipeline();
		new WebDriverWait(driver, Duration.ofMinutes(2)).until(
				ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//app-formly-field-mat-input/input"))))
				.clear();
		driver.findElement(By.xpath("//app-formly-field-mat-input/input")).sendKeys("master");
		tg.enterTriggerDetails(request);
		String status = tg.verifyExecutionStatus();
		System.out.println("Execution Status: " + status);
		sa.assertEquals(status, "trigger pipeline Success", "trigger pipeline is not successful"); // tg.navigateToTriggerHistoryLogs();
		sa.assertAll();
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
		String[] plugins = { "gitscm_tomcat", "tomcat_maven", "tomcat" };
		for (String plugin : plugins) {
			pv.addPlugin(pluginObj, plugin);
		}

		pv.submitPipeline();
		pv.setPipelineNameCreated();
		sa.assertEquals(new CommonMethods(driver).getPipelineSubmissionMessage(), "Pipeline created successfully!",
				"Pipeline creation failed");
		workflow.selectWorkflow();
		sa.assertEquals(new CommonMethods(driver).getPipelineSubmissionMessage(), "Worflow Created",
				"Workflow creation failed");
		parameters.clickParametersTab();
		parameters.addParameters(Constants.INPUT_PARAMETERS_DATA_JSON_PATH);

		sa.assertAll();
	}

	@Test(enabled = true, priority = 1)
	public void trigger_Pipeline_with_One_Stage()
			throws InterruptedException, FileNotFoundException, IOException, ParseException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		pv.navigateToPipeLineEditor();
		JSONObject request = LoadData.readJSONFileToCreatePipeline(Constants.INPUTPIPELINEDATA_JSON_PATH);
		pv.createPipeline(request);
		JSONObject pluginObj = pv.addStage(request);
		String[] plugins = { "gitscm_tomcat", "tomcat_maven", "tomcat" };
		for (String plugin : plugins) {
			pv.addPlugin(pluginObj, plugin);
		}
		pv.submitPipeline();
		pv.setPipelineNameCreated();
		common = new CommonMethods(driver);
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(common.getPipelineSubmissionMessage(), "Pipeline created successfully!",
				"Pipeline creation failed");
		request = LoadData.readJSONFile(Constants.TRIGGERPIPELINEDATA_JSON_PATH);
		workflow.selectWorkflow();
		parameters.clickParametersTab();
		parameters.addParameters(Constants.INPUT_PARAMETERS_DATA_JSON_PATH);
		pv.openPipelineView();
		TriggerPipeline tg = new TriggerPipeline(driver);
		tg.triggerPipeline();
		driver.findElement(By.xpath("//app-formly-field-mat-input/input")).clear();
		driver.findElement(By.xpath("//app-formly-field-mat-input/input")).sendKeys("master");
		tg.enterTriggerDetails(request);
		sa.assertEquals(common.getPipelineSubmissionMessage(), "Pipeline triggered successfully!",
				"Pipeline triggering failed");
		sa.assertAll();
	}

	@Test(enabled = true, priority = 4)
	public void plugin_UI_fields_Validation() throws InterruptedException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		pv.navigateToPipeLineEditor();
		JSONObject request = LoadData.readJSONFileToCreatePipeline(Constants.INPUTPIPELINEDATA_JSON_PATH);
		pv.createPipeline(request);
		JSONObject pluginObj = pv.addStage(request);
		String[] plugins = { "gitscm_tomcat", "tomcat_maven", "tomcat" };
		for (String plugin : plugins) {
			pv.addPlugin(pluginObj, plugin);
		}
		common = new CommonMethods(driver);
		SoftAssert sa = new SoftAssert();
		pv.expandPluginDetails("Tomcat");
		sa.assertEquals(common.getFieldsHeaderName("Step Name"), "Step Name", "Step Name is not matched");
		sa.assertTrue(
				common.getFieldType("contextPath").equals("text")
						&& common.getFieldText("contextPath").equals("Context Path *"),
				"Context path is not a text Field or Name not Matched");
		sa.assertTrue(
				common.getFieldType("serverManagerUrl").equals("text")
						&& common.getFieldText("serverManagerUrl").equals("Server Manager URL *"),
				"serverManagerUrl is not a text Field or Name not Matched");
		sa.assertTrue(
				common.getFieldType("tomcatPassword").equals("password")
						&& common.getFieldText("tomcatPassword").equals("Tomcat password *"),
				"tomcatPassword is not a password Field or Name not Matched");
		sa.assertTrue(
				common.getFieldType("tomcatUsername").equals("text")
						&& common.getFieldText("tomcatUsername").equals("Tomcat username *"),
				"tomcatUsername is not a text Field or Name not Matched");
		sa.assertTrue(
				common.getFieldType("warPath").equals("text")
						&& common.getFieldText("warPath").equals("War file path to be deployed *"),
				"warPath is not a text Field or Name not Matched");
		sa.assertAll();
	}

	@Test(enabled = true, priority = 5)
	public void addition_Of_Plugin_in_Pipeline() throws InterruptedException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		pv.navigateToPipeLineEditor();
		JSONObject request = LoadData.readJSONFileToCreatePipeline(Constants.INPUTPIPELINEDATA_JSON_PATH);
		pv.createPipeline(request);
		JSONObject pluginObj = pv.addStage(request);
		String[] plugins = { "gitscm_tomcat", "tomcat_maven", "tomcat" };
		for (String plugin : plugins) {
			pv.addPlugin(pluginObj, plugin);

		}
		pv.submitPipeline();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(pv.verifyPluginIsAdded("Tomcat"), "Tomcat", "Plugin is not added");
		sa.assertAll();
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
		JSONObject pluginObj = pv.addStage(request);
		String[] plugins = { "tomcat" };
		for (String plugin : plugins) {
			pv.addPlugin(pluginObj, plugin);
		}
		pv.expandPluginDetails("Tomcat");
		sa.assertEquals(common.verifypluginMandatoryFields("contextPath"), "This field is required",
				"Context Path is not a mandatory field");
		sa.assertEquals(common.verifypluginMandatoryFields("serverManagerUrl"), "This field is required",
				"Server Manager URL is not a mandatory field");
		sa.assertEquals(common.verifypluginMandatoryFields("tomcatPassword"), "This field is required",
				"tomcat password is not a mandatory field");
		sa.assertEquals(common.verifypluginMandatoryFields("tomcatUsername"), "This field is required",
				"tomcat username is not a mandatory field");
		sa.assertEquals(common.verifypluginMandatoryFields("warPath"), "This field is required",
				"War file path is not a mandatory field");
		sa.assertAll();
	}

	@Test(enabled = true, priority = 7, dependsOnMethods = { "trigger_Pipeline_with_One_Stage" })
	public void edit_pipeline() throws InterruptedException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		SoftAssert sa = new SoftAssert();
		pv.openPipelineView();
		pv.editPipeline("Tomcat", "edit_tomcat");
		pv.submitPipeline();
		sa.assertTrue(pv.getStepName("edit_tomcat"));

		sa.assertEquals(new CommonMethods(driver).getPipelineSubmissionMessage(), "Pipeline updated successfully!",
				"Pipeline updation failed");
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
		Thread.sleep(1000);
		sa.assertEquals(new CommonMethods(driver).getPipelineSubmissionMessage(), "Pipeline created successfully!",
				"Pipeline creation failed");
		pv.openPipelineView();
		sa.assertTrue(pv.verifyPipelineIsClonned());
		sa.assertAll();
	}

}
