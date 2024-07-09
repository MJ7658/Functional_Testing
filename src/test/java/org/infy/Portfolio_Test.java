package org.infy;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;
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
public class Portfolio_Test extends LoadData {

	public Portfolio_Test() throws FileNotFoundException, IOException, ParseException {
		super();
	}

	WebDriver driver;
	ExtentTest logger;
	CommonMethods common;
	private Logger log = Logger.getLogger(Portfolio_Test.class);

	@Test(enabled = true, priority = 1)
	public void create_Portfolio() throws InterruptedException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		Thread.sleep(1000);
		logger.info("create_Portfolio");
		Thread.sleep(60000);
	}

	@Test(enabled = true, priority = 2)
	public void UIValidation_for_Mandatory_Application_Fields() throws InterruptedException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		Thread.sleep(1000);
		logger.info("UIValidation_for_Mandatory_Application_Fields");
		Thread.sleep(60000);
	}

	@Test(enabled = true, priority = 3)
	public void UIValidation_for_AddButton_ToolTip() throws InterruptedException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		Thread.sleep(1000);
		logger.info("UIValidation_for_AddButton_ToolTip");
		Thread.sleep(60000);
	}

	@Test(enabled = true, priority = 4)
	public void editPortfolioConfiguration() throws InterruptedException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		Thread.sleep(1000);
		logger.info("editPortfolioConfiguration");
		Thread.sleep(60000);
	}

	@Test(enabled = true, priority = 5)
	public void deletePortfolioConfiguration() throws InterruptedException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		Thread.sleep(1000);
		logger.info("deletePortfolioConfiguration");
		Thread.sleep(60000);
	}

	@Test(enabled = true, priority = 6)
	public void logout() throws InterruptedException {
		Thread.sleep(1000);
		logger.info("logout");
		Thread.sleep(5000);
	}

}
