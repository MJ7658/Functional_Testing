package org.plugins;

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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

@Listeners(ListenersTest.class)
public class JMETER extends LoadData {

	public JMETER() throws FileNotFoundException, IOException, ParseException {
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
		return new Object[][] {

				{ "GITSCM", "JMETER" }

		};

	}

	@Test(enabled = true, priority = 1, dataProvider = "Plugin_TestData")
	public void validate_Sensitive_fields_masking_in_UI_and_Logs_JMETER(String pluginname, String Stage)
			throws InterruptedException, FileNotFoundException, IOException, ParseException {

		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		Thread.sleep(10000);
		logger.info("validate_Sensitive_fields_masking_in_UI_and_Logs_JMETER");
		Thread.sleep(60000);
	}

	@Test(enabled = true, priority = 2, dataProvider = "Plugin_TestData")
	public void pipeline_Creation_With_One_Stage_JMETER(String pluginname, String Stage)
			throws InterruptedException, FileNotFoundException, IOException, ParseException {

		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		pv.navigateToPipeLineEditor();
		JSONObject request = LoadData.readJSONFileToCreatePipeline(Constants.INPUTPIPELINEDATA_JSON_PATH);
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

		pv.submitPipeline();
		Thread.sleep(60000);

	}

	@Test(enabled = true, priority = 3, dataProvider = "Plugin_TestData")
	public void trigger_Pipeline_with_One_Stage_JMETER(String pluginname, String Stage)
			throws InterruptedException, FileNotFoundException, IOException, ParseException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		pv.navigateToPipeLineEditor();
		JSONObject request = LoadData.readJSONFileToCreatePipeline(Constants.INPUTPIPELINEDATA_JSON_PATH);
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

		pv.submitPipeline();
		pv.setPipelineNameCreated();
		common = new CommonMethods(driver);

		request = LoadData.readJSONFile(Constants.TRIGGERPIPELINEDATA_JSON_PATH);

		pv.openPipelineView();
		TriggerPipeline tg = new TriggerPipeline(driver);
		tg.triggerPipeline();
		tg.enterTriggerDetails(request);
		Thread.sleep(6000);

	}

	@Test(enabled = true, priority = 4, dataProvider = "Plugin_TestData") // Work on Assertion part
	public void plugin_UI_fields_Validation_JMETER(String pluginname, String Stage) throws InterruptedException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		pv.navigateToPipeLineEditor();
		JSONObject request = LoadData.readJSONFileToCreatePipeline(Constants.INPUTPIPELINEDATA_JSON_PATH);
		common = new CommonMethods(driver);
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
	}

	@Test(enabled = true, priority = 5, dataProvider = "Plugin_TestData")
	public void addition_Of_Plugin_in_Pipeline_JMETER(String pluginname, String Stage) throws InterruptedException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		pv.navigateToPipeLineEditor();
		JSONObject request = LoadData.readJSONFileToCreatePipeline(Constants.INPUTPIPELINEDATA_JSON_PATH);
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

		pv.savePluginDetails();
	}

	@Test(enabled = true, priority = 6, dataProvider = "Plugin_TestData")
	public void UIValidation_for_Mandatory_Fields_JMETER(String pluginname, String Stage) throws InterruptedException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		Thread.sleep(60000);
		logger.info("UIValidation_for_Mandatory_Fields_JMETER");
	}

	@Test(enabled = true, priority = 7, dataProvider = "Plugin_TestData", dependsOnMethods = {
			"trigger_Pipeline_with_One_Stage_JMETER" })
	public void edit_pipeline_JMETER(String pluginname, String Stage) throws InterruptedException {

		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		pv.openPipelineView();
		String editplugin = pluginname;
		pv.editPipeline(pluginname, editplugin);
		pv.submitPipeline();
		Thread.sleep(10000);

	}

	@Test(enabled = true, priority = 8, dataProvider = "Plugin_TestData", dependsOnMethods = {
			"trigger_Pipeline_with_One_Stage_JMETER" })
	public void clone_pipeline_JMETER(String pluginname, String Stage) throws InterruptedException {

		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pv = new PipelineView(driver);
		pv.openPipelineView();
		pv.clonePipeline();
		pv.addclonedPipelineName();
		pv.submitPipeline();
		pv.openPipelineView();
		Thread.sleep(10000);
	}
}
