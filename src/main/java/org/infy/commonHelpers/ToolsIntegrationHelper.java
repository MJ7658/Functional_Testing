package org.infy.commonHelpers;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ToolsIntegrationHelper {

	static String Application;
	private WebDriver driver;
	private WebDriverWait wait;
	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(ToolsIntegrationHelper.class);
	public static String applicationNameToBeCreated;
	public static String applicationWorkflowNameToBeCreated;
	public static String applicationWorkflowNameCreated;
	public int count, flag;
	public static boolean statusVerify;
	public static String agentNameis;

	public ToolsIntegrationHelper(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(800));
	}

	public void navTotoolIntergration() throws IOException, InterruptedException {
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath("//*[starts-with(text(),' Dashboard')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		log.info("Navigate to integration page");
		driver.findElement(By.id("mat-tab-label-0-7")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("mat-tab-label-0-7")).click();
		Thread.sleep(2000);
		log.info("Click on Tools integration page");
	}

	public void delectPlugin(String plginName) throws IOException {
		try {
			Thread.sleep(2000);
			log.info("delete the old plugin if anything exits");
			WebElement element1 = driver
					.findElement(By.xpath("//*[text()='" + plginName + "']//following::td[2]//button[3]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
			Thread.sleep(3000);
			log.info("scrollToElement");
			driver.findElement(By.xpath("//*[text()='" + plginName + "']//following::td[2]//button[3]")).click();
			log.info("click on Delete");
			driver.findElement(By.xpath("//*[text()=' Yes ']")).click();
			log.info("click on Delete Yes");
		} catch (Exception e) {
			log.info("Plugin Not Available We Can add it");
		}
	}

	public void addPlugins(String plginName, String workerName, String url) throws IOException, InterruptedException {
		log.info("its Add plugin method");
		Thread.sleep(3000);
		delectPlugin(plginName);
		Thread.sleep(3000);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[text()='Add Plugin Configuration'])[1]"))))
				.click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//*[@placeholder='Search'])[2]"))))
				.sendKeys(plginName);
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//h3[text()='" + plginName + "']")))).click();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@placeholder='Worker Name']"))))
				.sendKeys(workerName + Keys.TAB);
		Thread.sleep(2000);
		driver.switchTo().activeElement().sendKeys(url);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()=' Save ']")))).click();
	}

	public void editPlugins(String plginName, String updateWorkerName) throws IOException, InterruptedException {
		Thread.sleep(2000);
		WebElement element1 = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[text()='" + plginName + "']//following::td[2]//button[2]")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(
				driver.findElement(By.xpath("//*[text()='" + plginName + "']//following::td[2]//button[2]")))).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[text()='Cancel']//preceding::input[4]"))))
				.clear();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@placeholder='Worker Name']"))))
				.sendKeys(updateWorkerName);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()=' Save ']")))).click();
	}

	public void addEmailPlugins(String plginName, String workerName, String mailRec)
			throws IOException, InterruptedException {
		log.info("Click on Tools integration page");
		Thread.sleep(3000);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[text()='Add Plugin Configuration']")))).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@placeholder='Search']"))))
				.sendKeys(plginName);
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//h3[text()='" + plginName + "']")))).click();
		Thread.sleep(3000);
		log.info("Click on Email plugin");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@placeholder='Worker Name']"))))
				.sendKeys(workerName + Keys.TAB);
		Thread.sleep(2000);
		log.info("Select workerName");
		driver.switchTo().activeElement().sendKeys(mailRec);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[starts-with(@id,'mat-select-value')]"));
		WebElement el = driver.findElement(By.xpath("//div[starts-with(@id,'mat-select-value')]"));
		Actions action = new Actions(driver);
		action.moveToElement(el).doubleClick().build().perform();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()=' Save ']")))).click();
	}

	public void pluginSearch(String plginName) throws IOException, InterruptedException {
		Thread.sleep(3000);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[text()='Add Plugin Configuration']")))).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@placeholder='Search']"))))
				.sendKeys(plginName);
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//h3[text()='" + plginName + "']")))).click();
	}

	public void clickOnSave() {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()=' Save ']")))).click();
	}

	public ArrayList<String> teamsVerifycation(String TeamChennelName) throws IOException, InterruptedException {
		WebDriver edgedriver = edgeBrowserOpen();
		edgedriver.get("https://teams.microsoft.com/");
		edgedriver.navigate().refresh();
		Thread.sleep(8000);
		edgedriver.navigate().refresh();
		WebDriverWait edgeWait = new WebDriverWait(edgedriver, Duration.ofSeconds(800));
		edgeWait.until(
				ExpectedConditions.elementToBeClickable(edgedriver.findElement(By.xpath("//span[text()='Teams']"))))
				.click();
		Thread.sleep(3000);
		edgeWait.until(ExpectedConditions.elementToBeClickable(
				edgedriver.findElement(By.xpath("//button[@data-tid='channel-list-filter-btn']")))).click();
		Thread.sleep(3000);
		edgeWait.until(ExpectedConditions
				.visibilityOf(edgedriver.findElement(By.xpath("//input[@placeholder='Filter by team or channel']"))))
				.sendKeys(TeamChennelName);
		Thread.sleep(3000);
		edgeWait.until(ExpectedConditions.visibilityOf(edgedriver.findElement(By.xpath("//span[text()='General']"))))
				.click();
		Thread.sleep(2000);
		List<WebElement> listofResult = edgedriver
				.findElements(By.xpath("//*[text()='New conversation']//preceding::table[1]//tr"));
		Thread.sleep(2000);
		ArrayList<String> value = new ArrayList<String>();
		for (WebElement webElement : listofResult) {
			value.add(webElement.getText());
			log.info(webElement.getText());
		}
		edgedriver.close();
		return value;
	}

	public ArrayList<String> emailVerifycation(String emailName) throws IOException, InterruptedException {
		WebDriver edgedriver = edgeBrowserOpen();
		edgedriver.get("hhttps://outlook.office365.com/");
		edgedriver.navigate().refresh();
		Thread.sleep(8000);
		edgedriver.navigate().refresh();
		WebDriverWait edgeWait = new WebDriverWait(edgedriver, Duration.ofSeconds(800));

		edgeWait.until(ExpectedConditions.visibilityOf(edgedriver.findElement(By.xpath("//*[@placeholder='Search']"))))
				.sendKeys(emailName);
		ArrayList<String> value = new ArrayList<String>();
		return value;
	}

	public void verifycationNotification(String toolName, String key, String value)
			throws IOException, InterruptedException {
		ArrayList<String> ll = teamsVerifycation(toolName);
		for (String string : ll) {
			String val = string.trim();
			String[] ne = val.split(":");

			String k = ne[0].trim();
			String v = ne[1].trim();
			HashMap<String, String> kvvalue = new HashMap<String, String>();
			kvvalue.put(k, v);
			Set<String> nn = kvvalue.keySet();
			String name = nn.toString().replace("[", "").replace("]", "");

			if (name.equalsIgnoreCase(key)) {
				String na = kvvalue.get(k);
				String name1 = na.toString().replace("[", "").replace("]", "");
				log.info(name1);
				if (name1.toString().equalsIgnoreCase(value)) {
					log.info("Yes! We got the Notification");
					log.info("Data is exits");
					break;
				}
			} else {
				log.info("sorry! Data not there");
			}
		}
	}

	public void emailVerifycationNotification(String toolName, String key, String value)
			throws IOException, InterruptedException {
		ArrayList<String> ll = emailVerifycation(toolName);
		for (String string : ll) {
			String val = string.trim();
			String[] ne = val.split(":");

			String k = ne[0].trim();
			String v = ne[1].trim();
			HashMap<String, String> kvvalue = new HashMap<String, String>();
			kvvalue.put(k, v);
			Set<String> nn = kvvalue.keySet();
			String name = nn.toString().replace("[", "").replace("]", "");

			if (name.equalsIgnoreCase(key)) {
				String na = kvvalue.get(k);
				String name1 = na.toString().replace("[", "").replace("]", "");
				if (name1.toString().equalsIgnoreCase(value)) {
					log.info("Yes! We got the Notification");
					log.info("Data is exits");
					break;
				}
			} else {
				log.info("sorry! Data not there");
			}
		}
	}

	public WebDriver edgeBrowserOpen() {
		System.setProperty("webdriver.edge.driver", "C:\\Edgedriver1\\msedgedriver.exe");
		WebDriver edgeDriver = new EdgeDriver();
		edgeDriver.manage().window().maximize();
		edgeDriver.manage().deleteAllCookies();
		edgeDriver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));
		edgeDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		return edgeDriver;
	}

}
