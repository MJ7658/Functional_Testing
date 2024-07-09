package org.infy.init;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.infy.uiPages.LoginPage;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
//import org.testng.asserts.SoftAssert;
import org.xml.sax.SAXException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenersTest implements ITestListener {

	public static WebDriver driver;
	public static ExtentSparkReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;
	private ThreadLocal<String> testName = new ThreadLocal<String>();
	public static boolean flag = true;

	public static String OSName = System.getProperty("os.name");
	public static String OSArchitecture = System.getProperty("os.arch");
	public static String OSVersion = System.getProperty("os.version");
	public static String OSBit = System.getProperty("sun.arch.data.model");
	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(ListenersTest.class);

	public void onTestStart(ITestResult result) {
		try {
			LoadData.setLoginDetails();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		driver.get(LoadData.getUrl());
		if (flag) {
			LoginPage lp = new LoginPage(driver);
			try {
				lp.login(LoadData.getUsername());
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			flag = false;
		}
		Object[] testData = result.getParameters();
		if (testData.length > 0) {
			testName.set(result.getName() + "_" + Arrays.toString(testData));
			result.setAttribute("testName", testName.get());
		} else {
			result.setAttribute("testName", result.getName());
		}

		logger = extent.createTest(result.getAttribute("testName").toString());
	}

	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println("Successed Mehod name is ======================>    " + result.getMethod());
		log.info("Successed Mehod name is ======================>    " + result.getMethod());
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		logger.pass(m);
		String testMethodName = result.getMethod().getMethodName();
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("Failed Mehod name is ======================>    " + result.getMethod());
		log.info(" Failed Mehod name is ======================>    " + result.getMethod());
		log.info("Exception Details ======================> " + result.getThrowable());
		String failureLogg = "TEST CASE FAILED";
		Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		logger.log(Status.FAIL, m);
		logger.fail(result.getThrowable());
		String testMethodName = result.getMethod().getMethodName();

		try {
			String dateTime = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String imagePath = Constants.ERROR_SCREENSHOTS_DIR + result.getName() + "-"
					+ Arrays.toString(result.getParameters()) + "_" + dateTime + ".png";
			FileUtils.copyFile(scrFile, new File(imagePath));
			logger.fail(result.getName() + "-" + Arrays.toString(result.getParameters()) + " method failed");
			logger.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath(imagePath).build());
		} catch (Exception e) {
		}
		try {
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (Exception e) {
		}
	}

	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		logger.skip(m);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		logger.log(Status.PASS, "*** Test failed but within percentage % " + result.getMethod().getMethodName());
		String FailedSuccessPre = "*** Test failed but within percentage % " + result.getMethod().getMethodName()
				+ "***";
		Markup m = MarkupHelper.createLabel(FailedSuccessPre, ExtentColor.INDIGO);
		logger.log(Status.INFO, m);
	}

	public void onStart(ITestContext context) {

		try {
			ReadConfigFile.readXML();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			driver = BrowserFactory.getDriver(ReadConfigFile.browser);
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);

		htmlReporter = new ExtentSparkReporter(Constants.EXTENT_REPORT_DIR + date + ".html");
		extent = new com.aventstack.extentreports.ExtentReports();
		extent.attachReporter(htmlReporter);
		PropertyConfigurator.configure("log4j.properties");
		htmlReporter.config().setReportName("IDP Results");
		htmlReporter.config().setDocumentTitle("Test Results");
		extent.setSystemInfo("Browser", "Chorme Browser");
		extent.setSystemInfo("OS", OSName);
		extent.setSystemInfo("OS Version", OSVersion);
		extent.setSystemInfo("OS Architecture", OSArchitecture);
		extent.setSystemInfo("OS Bit", OSBit);
		extent.setSystemInfo("JAVA Version", System.getProperty("java.version"));
		extent.setSystemInfo("OS Bit", OSBit);
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setProtocol(Protocol.HTTPS);
	}

	public void onFinish(ITestContext context) {
		String End = "*** TEST CASE ENDING ***";
		Markup m = MarkupHelper.createLabel(End, ExtentColor.CYAN);
		logger.log(Status.INFO, m);
		extent.flush();
	}

}
