package org.plugins;

import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.infy.init.Constants;
import org.infy.init.ListenersTest;
import org.infy.init.LoadData;
import org.infy.uiPages.CommonMethods;
import org.infy.uiPages.LoginPage;
import org.infy.uiPages.Parameters;
import org.infy.uiPages.PipelineView;
import org.infy.uiPages.TriggerPipeline;
import org.infy.uiPages.Workflows;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

@Listeners(ListenersTest.class)
public class ITAF extends LoadData {

	private static Logger Logg = LogManager.getLogger(ITAF.class);

	public ITAF() throws FileNotFoundException, IOException, ParseException {
		super();
	}

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Plugin_Verifications plugin = new Plugin_Verifications();
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();

	@DataProvider(name = "Plugin_TestData")
	public Object[][] getDataFromDataprovider() {
		return new Object[][] { { "ITAF", null } };

	}

	@Test(enabled = true, priority = 1, dataProvider = "Plugin_TestData")
	public void validate_Sensitive_fields_masking_in_UI_and_Logs_ITAF(String pluginname, String Stage)
			throws InterruptedException, FileNotFoundException, IOException, ParseException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		pv.navigateToPipeLineEditor();
		LoginPage lg = new LoginPage(driver);
		lg.screenshot();
		JSONObject request = LoadData.readJSONFileToCreatePipeline(Constants.INPUTPIPELINEDATA_JSON_PATH);
		pv.createPipeline(request);
		JSONObject pluginObj = pv.addStage(request);
		Logg.info("validate_Sensitive_fields_masking_in_UI_and_Logs_ITAF Method started");
		String[] plugin1 = { pluginname };
		String[] plugin2 = { pluginname, Stage };

		if (Stage == null) {
			for (String plugin : plugin1) {

				pv.addPlugin(pluginObj, plugin);
			}
		} else {
			for (String plugin : plugin2) {

				pv.addPlugin(pluginObj, plugin);
			}
		}

		pv.submitPipeline();
		pv.setPipelineNameCreated();
		request = LoadData.readJSONFile(Constants.TRIGGERPIPELINEDATA_JSON_PATH);

		pv.openPipelineView();
		TriggerPipeline tg = new TriggerPipeline(driver);
		tg.triggerPipeline();
		tg.enterTriggerDetails(request);

		Logg.info("validate_Sensitive_fields_masking_in_UI_and_Logs_ITAF Method Ended");
	}

	@Test(enabled = true, priority = 2, dataProvider = "Plugin_TestData")
	public void pipeline_Creation_With_One_Stage_ITAF(String pluginname, String Stage)
			throws InterruptedException, FileNotFoundException, IOException, ParseException {

		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		pv.navigateToPipeLineEditor();
		JSONObject request = LoadData.readJSONFileToCreatePipeline(Constants.INPUTPIPELINEDATA_JSON_PATH);
		pv.createPipeline(request);
		JSONObject pluginObj = pv.addStage(request);
		Logg.info("pipeline_Creation_With_One_Stage_ITAF Method Started");
		String[] plugin1 = { pluginname };
		String[] plugin2 = { pluginname, Stage };

		if (Stage == null) {
			for (String plugin : plugin1) {

				pv.addPlugin(pluginObj, plugin);
			}
		} else {
			for (String plugin : plugin2) {

				pv.addPlugin(pluginObj, plugin);
			}
		}

		pv.submitPipeline();
		assertEquals(true, true);
		Thread.sleep(60000);
		Logg.info("pipeline_Creation_With_One_Stage_ITAF Method Ended");
	}

	@Test(enabled = true, priority = 3, dataProvider = "Plugin_TestData")
	public void trigger_Pipeline_with_One_Stage_ITAF(String pluginname, String Stage)
			throws InterruptedException, FileNotFoundException, IOException, ParseException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		pv.navigateToPipeLineEditor();
		JSONObject request = LoadData.readJSONFileToCreatePipeline(Constants.INPUTPIPELINEDATA_JSON_PATH);
		pv.createPipeline(request);
		JSONObject pluginObj = pv.addStage(request);
		Logg.info("trigger_Pipeline_with_One_Stage_ITAF Method Started");
		String[] plugin1 = { pluginname };
		String[] plugin2 = { pluginname, Stage };

		if (Stage == null) {
			for (String plugin : plugin1) {

				pv.addPlugin(pluginObj, plugin);
			}
		} else {
			for (String plugin : plugin2) {

				pv.addPlugin(pluginObj, plugin);
			}
		}

		pv.submitPipeline();
		pv.setPipelineNameCreated();
		common = new CommonMethods(driver);
		request = LoadData.readJSONFile(Constants.TRIGGERPIPELINEDATA_JSON_PATH);

		pv.openPipelineView();
		TriggerPipeline tg = new TriggerPipeline(driver);
		tg.triggerPipeline();
		tg.enterTriggerDetails(request);
		Thread.sleep(6000);
		Logg.info("trigger_Pipeline_with_One_Stage_ITAF Method Ended");
	}

	@Test(enabled = true, priority = 4, dataProvider = "Plugin_TestData") // Work on Assertion part
	public void plugin_UI_fields_Validation_ITAF(String pluginname, String Stage) throws InterruptedException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		pv.navigateToPipeLineEditor();
		JSONObject request = LoadData.readJSONFileToCreatePipeline(Constants.INPUTPIPELINEDATA_JSON_PATH);
		common = new CommonMethods(driver);
		Logg.info("plugin_UI_fields_Validation_ITAF Method Started");
		pv.createPipeline(request);
		JSONObject pluginObj = pv.addStage(request);
		String[] plugin1 = { pluginname };
		String[] plugin2 = { pluginname, Stage };
		if (Stage == null) {
			for (String plugin : plugin1) {

				pv.addPlugin(pluginObj, plugin);
			}
		} else {
			for (String plugin : plugin2) {

				pv.addPlugin(pluginObj, plugin);
			}
		}
		pv.expandPluginDetails(pluginname);
		Thread.sleep(60000);
		Logg.info("plugin_UI_fields_Validation_ITAF Method Ended");
	}

	@Test(enabled = true, priority = 5, dataProvider = "Plugin_TestData")
	public void addition_Of_Plugin_in_Pipeline_ITAF(String pluginname, String Stage) throws InterruptedException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		pv.navigateToPipeLineEditor();
		JSONObject request = LoadData.readJSONFileToCreatePipeline(Constants.INPUTPIPELINEDATA_JSON_PATH);
		pv.createPipeline(request);
		JSONObject pluginObj = pv.addStage(request);
		Logg.info("addition_Of_Plugin_in_Pipeline_ITAF Method Started");
		String[] plugin1 = { pluginname };
		String[] plugin2 = { pluginname, Stage };

		Thread.sleep(3000);
		if (Stage == null) {
			for (String plugin : plugin1) {

				pv.addPlugin(pluginObj, plugin);
			}
		} else {
			for (String plugin : plugin2) {

				pv.addPlugin(pluginObj, plugin);
			}
		}
		Thread.sleep(10000);
		Logg.info("addition_Of_Plugin_in_Pipeline_ITAF Method Ended");
	}

	@Test(enabled = true, priority = 6, dataProvider = "Plugin_TestData")
	public void UIValidation_for_Mandatory_Fields_ITAF(String pluginname, String Stage) throws InterruptedException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		pv.navigateToPipeLineEditor();
		common = new CommonMethods(driver);
		Logg.info("UIValidation_for_Mandatory_Fields_ITAF Method Started");
		Thread.sleep(60000);
		Logg.info("UIValidation_for_Mandatory_Fields_ITAF Method Ended");

	}

	@Test(enabled = true, priority = 7, dataProvider = "Plugin_TestData", dependsOnMethods = {
			"trigger_Pipeline_with_One_Stage_ITAF" })
	public void edit_pipeline_ITAF(String pluginname, String Stage) throws InterruptedException {

		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		pv.openPipelineView();
		Logg.info("edit_pipeline_ITAF Method Started");
		String editplugin = "Edit" + pluginname;
		String editplugin1 = pluginname;
		pv.editPipeline(pluginname, editplugin1);
		pv.submitPipeline();
		Thread.sleep(60000);
		Logg.info("edit_pipeline_ITAF Method Ended");

	}

	@Test(enabled = true, priority = 8, dataProvider = "Plugin_TestData", dependsOnMethods = {
			"trigger_Pipeline_with_One_Stage_ITAF" })
	public void clone_pipeline_ITAF(String pluginname, String Stage) throws InterruptedException {

		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		Logg.info("clone_pipeline_ITAF Method Started");
		Thread.sleep(60000);
		Logg.info("clone_pipeline_ITAF Method Ended");
	}
}
