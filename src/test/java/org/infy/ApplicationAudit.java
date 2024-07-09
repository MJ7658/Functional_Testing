package org.infy;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.infy.init.Constants;
import org.infy.init.ListenersTest;
import org.infy.init.LoadData;
import org.infy.uiPages.ApplicationView;
import org.infy.uiPages.CommonMethods;
import org.infy.uiPages.Parameters;
import org.infy.uiPages.PipelineView;
import org.infy.uiPages.Workflows;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

@Listeners(ListenersTest.class)
public class ApplicationAudit extends LoadData {

	public ApplicationAudit() throws FileNotFoundException, IOException, ParseException {
		super();
	}

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();
	String dateOfPipelineCreation;
	String timeOfPipelineCreation;
	String dateOfWorkFlowCreation;
	String timeOfWorkFlowCreation;

	@Test(enabled = true, priority = 1)
	public void create_Pipeline_For_ApplicationAudit()
			throws InterruptedException, FileNotFoundException, IOException, ParseException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pvw = new PipelineView(driver);
		Thread.sleep(1000);
		logger.info("create_Pipeline_For_ApplicationAudit");
		Thread.sleep(60000);
	}

	@Test(enabled = false, priority = 2)
	public void update_Pipeline_For_ApplicationAudit()
			throws InterruptedException, FileNotFoundException, IOException, ParseException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		PipelineView pvw = new PipelineView(driver);
		ApplicationView ap = new ApplicationView(driver);
		JSONObject request = LoadData.readJSONFileToCreatePipeline(Constants.INPUTPIPELINEDATA_JSON_PATH);
		Thread.sleep(1000);
		pvw.openPipelineView();
		Thread.sleep(2000);
		pvw.editPipeline("gitscm", "edit_gitSCM");
		pvw.submitPipeline();
		Thread.sleep(60000);
	}

	@Test(enabled = true, priority = 3)
	public void logout() throws InterruptedException {
		Thread.sleep(1000);
		logger.info("logout");
		Thread.sleep(5000);
	}
}
