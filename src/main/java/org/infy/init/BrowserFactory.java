package org.infy.init;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.Parameters;

public class BrowserFactory {

	public static WebDriver driver;
	public static String Status;
	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(BrowserFactory.class);

	@Parameters("browserType")
	public static WebDriver getDriver(String browserType)
			throws AWTException, UnknownHostException, InterruptedException {
		if (driver == null) {
			if (browserType.equalsIgnoreCase("chrome")) {
				log.info("Browser Launched Successfully...");
				System.setProperty("webdriver.chrome.driver",
						"C:\\ChromeTest\\121.0.6167.85\\chromedriver-win64\\chromedriver.exe");
				log.info(browserType);
				ChromeOptions options = new ChromeOptions();
				options.setBinary("C:\\ChromeTest\\121.0.6167.85\\chrome-win64\\chrome.exe");
				options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
				options.addArguments("--remote-allow-origins=*");
				Map<String, Object> prefs = new HashMap<String, Object>();
				String path = System.getProperty("user.dir") + "\\Data\\";
				prefs.put("download.default_directory", path);
				options.setExperimentalOption("prefs", prefs);
				driver = new ChromeDriver(options);
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));

				Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
				int width = (int) size.getWidth();
				int height = (int) size.getHeight();
				log.info("Current Screen resolution : " + "width : " + width + " height : " + height);
			} else if (browserType.equalsIgnoreCase("Edge")) {
				log.info("Browser Launched Successfully...");
				System.setProperty("webdriver.edge.driver", "C:\\Edgedriver\\msedgedriver.exe");
				log.info(browserType);
				EdgeOptions options = new EdgeOptions();
				options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
				options.addArguments("--remote-allow-origins=*");
				Map<String, Object> prefs = new HashMap<String, Object>();
				String path = System.getProperty("user.dir") + "\\Data\\";
				prefs.put("download.default_directory", path);
				options.setExperimentalOption("prefs", prefs);
				driver = new EdgeDriver(options);
				driver.manage().window().maximize();
				driver.manage().window().setSize(new org.openqa.selenium.Dimension(1024,
				768));
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
				Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("document.body.style.zoom = '110%';");
				Thread.sleep(4000);
				int width = (int) size.getWidth();
				int height = (int) size.getHeight();
				log.info("Current Screen resolution : " + "width : " + width + " height : " + height);
				InetAddress ip = InetAddress.getLocalHost();
				System.out.println("Your current IP address : " + ip.getHostAddress());
			}
		}
		return driver;
	}

	public static String visualTestStatus() {
		return Status;
	}

}