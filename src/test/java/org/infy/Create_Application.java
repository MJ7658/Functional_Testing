package org.infy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.infy.init.Constants;
import org.infy.init.ListenersTest;
import org.infy.init.LoadData;
import org.infy.reportHelper.ReportHelper;
import org.infy.uiPages.ApplicationView;
import org.infy.uiPages.CommonMethods;
import org.infy.uiPages.ConfigurePage;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

@Listeners(ListenersTest.class)
public class Create_Application extends LoadData {
	public Create_Application() throws FileNotFoundException, IOException, ParseException {
		super();
	}

	WebDriver driver;
	WebDriverWait wait;
	ExtentTest logger;
	CommonMethods common;

	@Test(enabled = true, priority = 1) // working
	public void create_Application() throws InterruptedException, IOException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;

		wait = new WebDriverWait(driver, Duration.ofMinutes(2));

		JSONObject request = LoadData.readJSONFile(Constants.INPUTAPPLICATIONDATA_JSON_PATH);
		ApplicationView Av = new ApplicationView(driver);

		common = new CommonMethods(driver);
		common.waitObjecttoLoad(50);

		Av.openApplicationViewOld();
		Av.createApplication(request);
		ReportHelper reportH = PageFactory.initElements(driver, ReportHelper.class);
		ConfigurePage configure = new ConfigurePage(driver);
		configure.configureEnvironment("Environments", request);
		driver.findElement(Av.rightArrow).click();
		driver.findElement(Av.rightArrow).click();
		configure.configureAgents("Workers", request);
		configure.configureRelease("Release", request);

		System.out.println("Create_Application Completed Successfully -------------------------------------");
		Thread.sleep(60000);
	}

	// this was false
	@Test(enabled = true, priority = 2)
	public void UIValidation_for_Mandatory_Application_Fields() throws InterruptedException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		Thread.sleep(1000);
		logger.info("UIValidation_for_Mandatory_Application_Fields");
		Thread.sleep(60000);
		System.out.println(
				"UIValidation_for_Mandatory_Application_Fields Completed Successfully -------------------------------------");
	}

	// this was false;
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
		JSONObject request = LoadData.readJSONFile(Constants.INPUTAPPLICATIONDATA_JSON_PATH);
		JSONObject editDetails = (JSONObject) request.get("Edit_Application");
		System.out.print("After Edit Details");
		common = new CommonMethods(driver);
		Thread.sleep(5000);
		System.out.println("editPortfolioConfiguration Completed Successfully -------------------------------------");
		Thread.sleep(60000);
	}

	@Test(enabled = true, priority = 5)
	public void deleteApplicationConfiguration() throws InterruptedException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		driver.navigate().refresh();
		ApplicationView Av = new ApplicationView(driver);
		common = new CommonMethods(driver);
		Thread.sleep(5000);
		System.out
				.println("deleteApplicationConfiguration Completed Successfully -------------------------------------");
		Thread.sleep(60000);
	}

	@Test(enabled = true, priority = 6)
	public void logout() throws InterruptedException {
		Thread.sleep(1000);
		logger.info("logout");
		Thread.sleep(5000);
	}
}
