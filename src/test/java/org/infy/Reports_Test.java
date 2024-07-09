package org.infy;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.infy.init.ListenersTest;
import org.infy.uiPages.CommonMethods;
import org.infy.uiPages.ReportsView;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

@Listeners(ListenersTest.class)
public class Reports_Test {

	public Reports_Test() throws FileNotFoundException, IOException, ParseException {
		super();
	}

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	String hr;

	@Test(enabled = true, priority = 1)
	public void generate_Reports_Validation() throws InterruptedException, IOException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		SoftAssert sa = new SoftAssert();
		ReportsView rv = new ReportsView(driver);
		Thread.sleep(1000);
		logger.info("generate_Reports_Validation");
		Thread.sleep(60000);
	}

	@Test(enabled = true, priority = 2)
	public void logout() throws InterruptedException {
		Thread.sleep(1000);
		logger.info("logout");
		Thread.sleep(5000);
	}
}
