package org.infy;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.infy.init.ListenersTest;
import org.infy.init.LoadData;
import org.infy.uiPages.CommonMethods;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

@Listeners(ListenersTest.class)
public class Pipeline_Template_Test extends LoadData {

	public Pipeline_Template_Test() throws FileNotFoundException, IOException, ParseException {
		super();
	}

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	int count = 0;

	@Test(enabled = true, priority = 1)
	public void create_Template() throws InterruptedException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		Thread.sleep(1000);
		logger.info("create_Template");
		Thread.sleep(3000);
		logger.info("Portfolio view opened");
		Thread.sleep(60000);
	}

	@Test(enabled = true, priority = 2)
	public void logout() throws InterruptedException {
		Thread.sleep(1000);
		logger.info("logout");
		Thread.sleep(5000);

	}
}
