package org.infy;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.infy.init.ListenersTest;
import org.infy.uiPages.ApplicationView;
import org.infy.uiPages.CommonMethods;
import org.infy.uiPages.Parameters;
import org.infy.uiPages.PipelineView;
import org.infy.uiPages.Workflows;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

@Listeners(ListenersTest.class)
public class agileDashboardJiraReport {

	public agileDashboardJiraReport() throws FileNotFoundException, IOException, ParseException {
		super();
	}

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	Workflows workflow = new Workflows();
	Parameters parameters = new Parameters();

	@Test
	public void createPipeline_Jira() throws InterruptedException, FileNotFoundException, IOException, ParseException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView ap = new ApplicationView(driver);
		PipelineView pvw = new PipelineView(driver);
		Thread.sleep(1000);
		logger.info("createPipeline_Jira");
		Thread.sleep(5000);
		Thread.sleep(60000);
	}
}