package org.infy;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.infy.init.Constants;
import org.infy.init.ListenersTest;
import org.infy.init.LoadData;
import org.infy.uiPages.ApplicationView;
import org.infy.uiPages.CommonMethods;
import org.infy.uiPages.ConfigurePage;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

@Listeners(ListenersTest.class)
public class PoliciesTest {

	public PoliciesTest() throws FileNotFoundException, IOException, ParseException {
		super();
	}

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;

	@Test(enabled = true, priority = 1)
	public void create_Policies() throws InterruptedException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView ap = new ApplicationView(driver);
		JSONObject request = LoadData.readJSONFile(Constants.INPUTAPPLICATIONDATA_JSON_PATH);
		ConfigurePage configure = new ConfigurePage(driver);
		Thread.sleep(1000);
		logger.info("create_Policies");
		Thread.sleep(60000);

	}

	@Test(enabled = true, priority = 2)
	public void logout() throws InterruptedException {
		Thread.sleep(1000);
		logger.info("logout");

	}

}
