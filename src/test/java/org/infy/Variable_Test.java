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
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

@Listeners(ListenersTest.class)
public class Variable_Test extends LoadData {

	public Variable_Test() throws FileNotFoundException, IOException, ParseException {
		super();
	}

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();

	@Test(enabled = true, priority = 1)
	public void create_Variable() throws InterruptedException, FileNotFoundException, IOException, ParseException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		logger.info("Reached here");
		PipelineView pv = new PipelineView(driver);
		logger.info("Reached here application");
		ApplicationView ap = new ApplicationView(driver);
		JSONObject request1 = LoadData.readJSONFile(Constants.INPUTAPPLICATIONDATA_JSON_PATH);
		SoftAssert sa = new SoftAssert();
		String varblNameAssigned = ap.add_Variable(request1);
		System.out.println(varblNameAssigned);
		driver.findElement(ap.application_Manage_Tab_Content("Policies")).click();
		driver.findElement(ap.application_Manage_Tab_Content("Variables")).click();
		Thread.sleep(60000);
	}

	@Test(enabled = true, priority = 2)

	public void logout() throws InterruptedException {
		Thread.sleep(1000);
		logger.info("logout");
		Thread.sleep(5000);
		// logout
		ApplicationView Av = new ApplicationView(driver);

	}
}
