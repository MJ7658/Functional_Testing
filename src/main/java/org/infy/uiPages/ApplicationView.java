package org.infy.uiPages;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.infy.commonHelpers.PipelineHelper;
import org.infy.init.BrowserFactory;
import org.infy.init.ExcelReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ApplicationView {

	private WebDriver driver;
	private WebDriverWait wait;
	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(ApplicationView.class);
	public static String applicationNameToBeCreated;
	public static String applicationWorkflowNameToBeCreated;
	public static String applicationWorkflowNameCreated;
	public int count, flag;
	public static boolean statusVerify;
	private String VisualTestingStatus;

	public ApplicationView(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		this.VisualTestingStatus = BrowserFactory.visualTestStatus();
	}

	By selectApplicationFromDropdown(String appName) {
		return By.xpath(String.format(
				"//div[contains(@class,'mat-select-panel')]//mat-option/span[normalize-space()='" + appName + "']",
				appName));
	}

	public By rightArrow = By.xpath("//mat-tab-group/mat-tab-header/div[3]");
	public By applicationViewLeftNav = By.xpath("//mat-list-item//span[text()=' Application ']");
	public By portfolioViewLeftNav = By.xpath("//mat-list-item//span[text()=' Portfolio ']");
	public By auditLogsNav = By.xpath("//mat-list-item//span[text()=' Audit Logs ']");
	public By platformConfigurationNav = By.xpath("//mat-list-item//span[text()=' Platform Configuration ']");
	By applicationViewSideNav = By.xpath("//mat-sidenav-content//span[normalize-space(text())='Application list']");
	By createNewApplicationButon = By.xpath("//button[@mattooltip='Create an Application']");
	By createNewPortfolioButon = By.xpath("//button[@mattooltip='Create a Portfolio']");

	By applicationName = By.xpath("//input[@placeholder='Application Name']");

	By portfoliosName = By.xpath("//input[@placeholder='Portfolio Name']");

	By owners = By.xpath("//input[@placeholder='Owners']");
	By portfolioName = By.xpath("//mat-select[@placeholder='Portfolio Name']");
	By filterPipeline = By.xpath("//mat-label[text()='Filter']/parent::label/parent::span/preceding-sibling::input");
	By searchPipeline = By.xpath("//mat-label[text()='Search']/parent::label/parent::span/preceding-sibling::input");
	By applicationListTableRows = By
			.xpath("//div[text()='Application list']/parent::app-application-view/descendant::table/tbody/tr");
	By saveApplication = By.xpath("//*[text()=' Save ']");

	By createPortfolio = By.xpath("//*[text()=' Create ']");

	By editApplication = By.xpath("//table/tbody/tr[1]/td[3]/button/span/mat-icon");
	By add = By.xpath("//mat-icon[contains(text(),'add')]/ancestor::button");
	By back = By.xpath("//mat-icon[contains(text(),'arrow_back')]");
	By manage = By.xpath("//mat-button-toggle-group/mat-button-toggle[2]/button");

	By settings = By.xpath(
			"//*[@class='mat-focus-indicator mat-tooltip-trigger edit-button mat-icon-button mat-button-base']//following::button[1]");

	By worker = By.xpath("//span[text()='Workers']");

	By Environments = By.xpath("//*[text()='Environments']");
	By Variables = By.xpath("//div[text()='Variables']");

	By Release = By.xpath("//span[text()='Release']");

	By Workerss = By.xpath("//span[text()='Workers']");

	By Tools_Compliance = By.xpath("//span[text()='Tools Compliance']");

	By addRelease = By.xpath("//*[text()='Add Release']");

	By releaseName = By.xpath("//*[@placeholder='Release Name']");

	By environmentSelect = By.xpath("//*[text()=' Cancel ']//preceding::div[45]");

	By environmentSelect1 = By.xpath("//*[text()=' Cancel ']//preceding::div[75]");

	By okButtons = By.xpath("//*[text()='OK']");

	By startDate = By.xpath("//*[@placeholder='Start Date']");

	By actualStartDate = By.xpath("//*[@placeholder='Actual Start Date']");

	By endDate = By.xpath("//*[@placeholder='End Date']");

	By actualEndDate = By.xpath("//*[@placeholder='Actual End Date']");

	By releaseOkButton = By.xpath("//*[text()=' Ok ']");

	By addVariables = By.xpath("//*[text()='Add Variable']");

	By variableName = By.xpath("//*[text()='Add Variable']//following::input[1]");

	By defaultValue = By.xpath("(//input[starts-with(@id,'mat-input-')])[3]");

	By variableEnvValue = By.xpath("//label[text()='Environment']//following::input[2]");

	By defaultValueSecure = By.xpath("//div[contains(@class,'mat-slide-toggle-bar mat-slide-toggle-bar-no-side-')]");

	By variableSaveButton = By.xpath("//*[text()=' Save ']");

	By toolCom = By.xpath("//span[text()='Tools Compliance']");

	By general = By.xpath("//div[text()='General']");

	By gating = By.xpath("//*[text()='Gating']");
	By addGating = By.xpath("//*[text()='Add gating criteria']");

	By addLockPlugin = By.xpath("//*[text()='Add gating criteria']");

	By lockPlugin = By.xpath("//*[contains(text(),'Lock Plugin')]");

	By toolMandate = By.xpath("//*[contains(text(),'Tools Mandate')]");

	By addlockPlugin = By.xpath("//*[text()='Lock Fields']");

	By piplineMatcher = By
			.xpath("//app-idp-pipeline-path-matcher-exp-input[@placeholder='Pipeline path match expression']");

	public By Dashboard = By.xpath("//mat-button-toggle-group/mat-button-toggle/button/span");
	By triggerPipelineButton = By
			.xpath("//div[@class=\"trigger-mat-forms\"]/button/span[text()=\" Trigger Pipeline \"]");
	By addVariableButton = By
			.xpath("//app-application-manager-view/div[3]/idp-variable-list/div/div/mat-toolbar/button/span[1]/span");
	By addButtonInVariable = By.xpath("//button/span[text()=\" add \"]");
	By saveButtonInVariable = By.xpath("//span[normalize-space()='Save']/parent::button");
	public By filterApplication = By.xpath("//mat-label[text()='Filter']");
	public By filterManageApplication = By.xpath("//input[@placeholder=\"Enter Keyword\"]");

	public By application_Manage_Tab_Content(String tabName) {
		return By.xpath("//mat-tab-group/mat-tab-header/div[2]/div/div/div/div[text()='" + tabName + "']");

	}

	By selectFromDropdown(String worker) {
		return By.xpath(
				"//div[contains(@class,'mat-select-panel')]/mat-option/span[text()='" + " " + worker + " " + "']");
	}

	public By selectFromDropdownInApplicationManageTab(String fieldName) {
		return By.xpath("//div[contains(@class,'mat-select-panel')]/mat-option/span[text()='" + fieldName + "']");
	}

	By apllication_Existing(String applicationName) {
		return By.xpath(
				"//app-application-summary/div/mat-accordion/mat-expansion-panel/mat-expansion-panel-header/span/div/div/div/div[text()='"
						+ applicationName + "']");
	}

	public By application_Tab_Content(String tabName) {
		return By.xpath("//div[@class=\"mat-tab-labels\"]/div/div[text()='" + tabName + "']");
	}

	public boolean verifySprintVelocityInApplication(String appName) throws InterruptedException {

		driver.findElement(application_Tab_Content("Maturity")).click();
		Thread.sleep(3000);
		driver.findElement(application_Tab_Content("Agile")).click();
		Thread.sleep(3000);
		Thread.sleep(1000);
		if (new WebDriverWait(driver, Duration.ofMinutes(2)).until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath("//*[@id=\"pluginRoot_fbe067d9-2340-4081-8692-a840a8cc3d4f\"]/div/div[1]/idp-dbrd-trendchart/mat-card/div[4]/canvas")))
				.isDisplayed()) {
			log.info("Sprint Velocity is updated");
			return true;
		}
		return false;
	}

	public void doubleClick(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).doubleClick().click().perform();
	}

	public void singleClick(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).click().build().perform();
	}

	public void addTool(String ToolName) throws IOException, InterruptedException {
		PipelineView pv = new PipelineView(driver);
		Thread.sleep(3000);
		WebElement element = driver.findElement(By.xpath("//*[text()=' Dashboard ']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Add Tools']"))).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(pv.searchPluginTextBox))).sendKeys(ToolName);
		wait.until(ExpectedConditions.elementToBeClickable(pv.selectPlugin(ToolName))).click();
	}

	public void editApplicationPortfolio(String newPortfolioName) throws IOException, InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Submit ']//preceding::div[5]")))
				.click();
		Thread.sleep(2000);
		WebElement elem = driver.findElement(By.xpath("//*[@placeholder='Search']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elem);
		Thread.sleep(2000);
		elem.click();
		Thread.sleep(2000);
		elem.sendKeys(newPortfolioName);
		Thread.sleep(2000);
		clickOnElement11(newPortfolioName);
		wait.until(ExpectedConditions.elementToBeClickable(submitButtons)).click();
		Thread.sleep(4000);
	}

	public void nav_AddRun_Frequency() throws IOException, InterruptedException {
		PipelineView pv = new PipelineView(driver);
		WebElement element = driver.findElement(By.xpath("//*[text()=' Dashboard ']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Run Frequency']"))).click();
	}

	public void addRun_Frequency(String Durations) throws IOException, InterruptedException {
		PipelineView pv = new PipelineView(driver);
		WebElement element = driver.findElement(By.xpath("//*[text()=' Dashboard ']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Add Run Frequency']"))).click();
		Thread.sleep(2000);
		WebElement el = driver.findElement(By.xpath("//*[@placeholder='Pipeline path match expression']"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", el);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@name='applicationName']"))).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@placeholder='Search']"))).click();
		Thread.sleep(3000);
		driver.switchTo().activeElement().sendKeys("*" + Keys.ENTER + Keys.TAB);
		Thread.sleep(3000);
		driver.switchTo().activeElement().sendKeys("*");
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@placeholder='Enter duration']"))).click();
		driver.switchTo().activeElement().sendKeys(Durations);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Cancel']//preceding::span[7]")))
				.click();
		WebElement elements = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Week ']")));
		Actions ac = new Actions(driver);
		ac.moveToElement(elements).click().build().perform();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Save ']"))).click();
	}

	public void addOverRide() throws IOException, InterruptedException {
		PipelineView pv = new PipelineView(driver);
		WebElement element = driver.findElement(By.xpath("//*[text()=' Dashboard ']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Add override']"))).click();
		Thread.sleep(2000);
		WebElement el = driver.findElement(By.xpath("//*[@placeholder='Pipeline path match expression']"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", el);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@name='applicationName']"))).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@placeholder='Search']"))).click();
		Thread.sleep(3000);
		driver.switchTo().activeElement().sendKeys("*" + Keys.ENTER + Keys.TAB);
		Thread.sleep(3000);
		driver.switchTo().activeElement().sendKeys("*");
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Add ']"))).click();
	}

	public void addLockFiledProperties() throws IOException, InterruptedException {
		PipelineView pv = new PipelineView(driver);
		WebElement element = driver.findElement(By.xpath("//*[text()=' Dashboard ']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(2000);
		WebElement el = driver.findElement(By.xpath("//*[@placeholder='Pipeline path match expression']"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", el);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@name='applicationName']"))).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@placeholder='Search']"))).click();
		Thread.sleep(3000);
		driver.switchTo().activeElement().sendKeys("*" + Keys.ENTER + Keys.TAB);
		Thread.sleep(3000);
		driver.switchTo().activeElement().sendKeys("*");
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Save ']"))).click();
	}

	public void addLockFiledDynamicProperties(String applicationName, String pipelineName)
			throws IOException, InterruptedException {
		PipelineView pv = new PipelineView(driver);

		Thread.sleep(2000);
		WebElement el = driver.findElement(By.xpath("//*[@placeholder='Pipeline path match expression']"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", el);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@name='applicationName']"))).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@placeholder='Search']"))).click();
		Thread.sleep(3000);
		driver.switchTo().activeElement().sendKeys(applicationName + Keys.ENTER + Keys.TAB);
		Thread.sleep(3000);
		driver.switchTo().activeElement().sendKeys(pipelineName);
		Thread.sleep(4000);
	}

	public void addGatingFiledDynamicProperties(String applicationName, String pipelineName)
			throws IOException, InterruptedException {
		PipelineView pv = new PipelineView(driver);

		Thread.sleep(2000);
		WebElement el = driver.findElement(By.xpath("//*[@placeholder='Pipeline path match expression']"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", el);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@name='applicationName']"))).click();
		Thread.sleep(2000);
		clickOnElement1(applicationName);
		driver.findElement(By.name("pattern")).sendKeys(applicationName);
		Thread.sleep(4000);
	}

	public void addLockFiledDynamicDetailsForCMDPlguin(String workDir, String cmd)
			throws IOException, InterruptedException {
		PipelineView pv = new PipelineView(driver);

		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[text()='Lock Plugin Properties']//following::input[2]")))
				.sendKeys(workDir);
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[text()='Lock Plugin Properties']//following::mat-icon[1]")))
				.click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[text()='Lock Plugin Properties']//following::input[3]")))
				.sendKeys(cmd);
		Thread.sleep(2000);
		WebElement element = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[text()='Lock Plugin Properties']//following::mat-icon[2]")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[text()='Lock Plugin Properties']//following::mat-icon[3]")))
				.click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Save ']"))).click();
	}

	public void addLockFiledInputData(String fieldNumber, String details) throws IOException, InterruptedException {

		Thread.sleep(3000);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[text()='Lock Plugin Properties']//following::input[" + fieldNumber + "]")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.sendKeys(details);
	}

	public void addLockFiledEnabled(String fieldNumber) throws IOException, InterruptedException {

		Thread.sleep(2000);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[text()='Lock Plugin Properties']//following::mat-icon[" + fieldNumber + "]")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
	}

	public void addLockFiledDynamicDetailsForGITSCMplguin(String workDir, String cmd)
			throws IOException, InterruptedException {
		PipelineView pv = new PipelineView(driver);

		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[text()='Lock Plugin Properties']//following::input[2]")))
				.sendKeys(workDir);
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[text()='Lock Plugin Properties']//following::mat-icon[1]")))
				.click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[text()='Lock Plugin Properties']//following::input[3]")))
				.sendKeys(cmd);
		Thread.sleep(2000);
		WebElement element = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[text()='Lock Plugin Properties']//following::mat-icon[2]")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[text()='Lock Plugin Properties']//following::mat-icon[3]")))
				.click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Save ']"))).click();
	}

	public void cancelButton() throws IOException, InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='cancel']"))).click();
	}

	public void saveButton() throws IOException, InterruptedException {
		Thread.sleep(3000);
		WebElement element = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(3000);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Save']"))).click();
	}

	public void freqDeleteButton() throws IOException, InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[text()=' Once in every 10-Week ']//following::button[2]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Yes ']"))).click();
	}

	public void deleteTool() throws IOException, InterruptedException {
		try {
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[text()='Add Tools']//following::button[1]")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Yes ']"))).click();
		} catch (Exception e) {
			log.info("tool not exists");
		}
	}

	public void verifyAndDeleteTool() throws IOException, InterruptedException {
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(3000);
				naviToTools_Compliance();
				Thread.sleep(3000);
				boolean elements = driver
						.findElement(By.xpath("//span[starts-with(@class,'mat-select-placeholder ng')]")).isDisplayed();
				if (elements) {
					break;
				}
			} catch (Exception e) {
				driver.findElement(By.xpath("//*[text()='Add Tools']//following::button[1]")).click();
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Yes ']"))).click();
				log.info("tool is exists and tool deleted");
				Thread.sleep(5000);
				driver.navigate().refresh();
			}
		}
	}

	public void deleteTools() throws IOException, InterruptedException {
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Yes ']"))).click();
		driver.findElement(By.xpath("//*[text()='Add Tools']//following::button[1]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Yes ']"))).click();
	}

	@FindBy(xpath = "//mat-list-item//span[text()=' Application ']")
	WebElement applicationViewLeftNavNew;

	@FindBy(xpath = "//*[contains(text(),'Dashboard')]")
	WebElement Dashboards;

	public void openApplicationDashboardNew(String appName) throws InterruptedException {
		Thread.sleep(6000);
		wait.until(ExpectedConditions.visibilityOf(applicationViewLeftNavNew)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='DevSecOps Maturity']")));
		String portfolioview = driver.findElement(By.xpath("//*[contains(text(),'Dashboard')]")).getText();
		if (Dashboards.getText().equals("Dashboard")) {
			String withoutSpace = appName.replace(" ", "");
			Thread.sleep(4000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='DevSecOps Maturity']")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search']")))
					.sendKeys(withoutSpace);
			Thread.sleep(4000);
			log.info("Application Dashboard opened");
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[normalize-space(text())='" + appName + "']//following::mat-icon[2]")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='DevSecOps Maturity']")));
			Thread.sleep(4000);
			driver.findElement(By.xpath("//*[normalize-space(text())='" + appName + "']//following::mat-icon[2]"))
					.click();
		} else {
			log.info("Application Dashboard is not opened");
		}
		try {
			Thread.sleep(3000);
			wait.until(ExpectedConditions
					.visibilityOf(driver.findElement(By.xpath("//*[text()='Application Details ']"))));
			Thread.sleep(6000);
//			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(
//					"//*[starts-with(@class,'mat-ripple mat-mdc-tab-header-pagination mat-mdc-tab-header-pagination-after')]"))));
//
//			WebElement element = driver.findElement(By.xpath(
//					"//*[starts-with(@class,'mat-ripple mat-mdc-tab-header-pagination mat-mdc-tab-header-pagination-after')]"));
//			Thread.sleep(2000);
//			JavascriptExecutor executor = (JavascriptExecutor) driver;
//			executor.executeScript("arguments[0].click();", element);
			wait.until(ExpectedConditions.visibilityOfElementLocated(worker)).click();
			log.info("Navigated to Application worker details page");
		} catch (Exception e) {
			Thread.sleep(3000);
			driver.navigate().refresh();
			Thread.sleep(5000);
			openApplicationDashboard(appName);
		}
	}

	public void openApplicationDashboard(String appName) throws InterruptedException {
		Thread.sleep(6000);
		WebElement applicat = wait.until(ExpectedConditions.visibilityOfElementLocated(applicationViewLeftNav));
		Actions action = new Actions(driver);
		action.moveToElement(applicat).doubleClick(applicat).build().perform();
		JavascriptExecutor execu = (JavascriptExecutor) driver;
		execu.executeScript("arguments[0].click();", applicat);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='DevSecOps Maturity']")));
		String portfolioview = driver.findElement(By.xpath("//*[contains(text(),'Dashboard')]")).getText();
		if (portfolioview.equals("Dashboard")) {
			String withoutSpace = appName.replace(" ", "");
			Thread.sleep(4000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='DevSecOps Maturity']")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search']")))
					.sendKeys(withoutSpace);
			Thread.sleep(8000);
			log.info("Application Dashboard opened");
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[normalize-space(text())='" + appName + "']//following::mat-icon[2]")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='DevSecOps Maturity']")));
			Thread.sleep(6000);
			WebElement el = wait.until(ExpectedConditions.elementToBeClickable(driver
					.findElement(By.xpath("//*[normalize-space(text())='" + appName + "']//following::mat-icon[2]"))));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", el);
			log.info("Application opened");
		} else {
			log.info("Application Dashboard is not opened");
		}
		try {
			Thread.sleep(6000);
			wait.until(ExpectedConditions
					.visibilityOf(driver.findElement(By.xpath("//*[text()='Application Details ']"))));
			log.info("Click on application details");
			Thread.sleep(6000);
//			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(
//					"//*[starts-with(@class,'mat-ripple mat-mdc-tab-header-pagination mat-mdc-tab-header-pagination-after')]"))));
//			log.info("click on right side arrow");
//			WebElement element = driver.findElement(By.xpath(
//					"//*[starts-with(@class,'mat-ripple mat-mdc-tab-header-pagination mat-mdc-tab-header-pagination-after')]"));
//			Thread.sleep(2000);
			log.info("Navigated to workertab");
//			JavascriptExecutor executor = (JavascriptExecutor) driver;
//			executor.executeScript("arguments[0].click();", element);
//			log.info("before click on worker");
//			JavascriptExecutor executor3 = (JavascriptExecutor) driver;
//			executor3.executeScript("arguments[0].click();", element);
//			JavascriptExecutor executor4 = (JavascriptExecutor) driver;
//			executor4.executeScript("arguments[0].click();", element);
			WebElement workertab = driver.findElement(By.xpath("//span[text()='Workers']"));
			JavascriptExecutor executor1 = (JavascriptExecutor) driver;
			executor1.executeScript("arguments[0].click();", workertab);
			log.info("Navigated to Application worker details page");
		} catch (Exception e) {
			Thread.sleep(3000);
			driver.navigate().refresh();
			Thread.sleep(5000);
			openApplicationDashboard(appName);
		}
	}

	By policy = By.xpath("//div[text()='Policies']");
	By addPolicy = By.xpath("//span[text()='Add Policy']");
	By selectPolicy = By.xpath("//mat-select[@placeholder='Select Policy']");
	By addButton = By.xpath("//*[text()=' Add ']");
	By policyName = By.xpath("//input[@placeholder='PolicyName']");

	public void policyCreation(String policyNames) throws InterruptedException {
		ExcelReader read = new ExcelReader();
		wait.until(ExpectedConditions.elementToBeClickable(policy)).click();
		wait.until(ExpectedConditions.elementToBeClickable(addPolicy)).click();
		wait.until(ExpectedConditions.elementToBeClickable(selectPolicy)).click();

		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@class='mat-option-text']")));
		List<WebElement> policy = driver.findElements(By.xpath("//*[@class='mat-option-text']"));
		clickOnElement(policy, "Other");
		wait.until(ExpectedConditions.elementToBeClickable(policyName)).sendKeys(policyNames);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();

		Thread.sleep(3000);
		WebElement element = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(3000);

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[text()='" + " " + policyNames + " " + "']"))).click();
	}

	By rightArrows = By.xpath("//*[@class='mat-focus-indicator arrow-right mat-mini-fab mat-button-base mat-primary']");

	public void addPolicy(String policyNames, String ResourceType, String[] permission, String ResourcePattern)
			throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[normalize-space(text())='" + policyNames + "']//following::span[2]"))).click();
		clickOnElement11(ResourceType);
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[normalize-space(text())='" + policyNames + "']//following::span[5]"))).click();
		for (String string : permission) {
			clickOnElement11(string);
		}
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='OK']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[normalize-space(text())='" + policyNames + "']//following::input[1]")))
				.sendKeys(ResourcePattern);
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[normalize-space(text())='" + policyNames + "']//following::button[1]"))).click();
	}

	public void addPolicyUser(String policyNames, String[] UserDetails) throws InterruptedException {
		Thread.sleep(3000);
		WebElement element = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(3000);
		for (String string : UserDetails) {
			wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//*[normalize-space(text())='" + policyNames + "']//following::input[2]"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//*[normalize-space(text())='" + policyNames + "']//following::input[2]")))
					.sendKeys(string);
			wait.until(ExpectedConditions.elementToBeClickable(rightArrows)).click();
		}
	}

	By saveButton = By.xpath("//*[text()=' Save ']//following::button[12]");

	public void clickOnSavePolicy() throws InterruptedException {
		Thread.sleep(3000);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
		JavascriptExecutor executor3 = (JavascriptExecutor) driver;
		executor3.executeScript("arguments[0].click();", element);
	}

	public void clickOnSavePolicy(String policyName) throws InterruptedException {
		Thread.sleep(3000);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[normalize-space(text())='" + policyName + "']//following::button[3]")));
		JavascriptExecutor executor3 = (JavascriptExecutor) driver;
		executor3.executeScript("arguments[0].click();", element);
	}

	public static WebDriver loginWithNewUser() throws InterruptedException {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		options.addArguments("--remote-allow-origins=*");
		Map<String, Object> prefs = new HashMap<String, Object>();
		String path = System.getProperty("user.dir") + "\\Data\\";
		prefs.put("download.default_directory", path);
		options.setExperimentalOption("prefs", prefs);
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		String username = "vignesh.mj";
		String password = "V29uZGVyTGEhITI1ODA=";
		String tokens = decode(password);
		String usernamePassword = username + ":" + tokens;
		driver.get("https://" + usernamePassword
				+ "@stsakaash.infosys.com/adfs/ls/wia?client-request-id=afb5a929-a521-48b8-a960-b1475e47e8a6&wa=wsignin1.0&wtrealm=urn%3afederation%3aMicrosoftOnline&wctx=LoginOptions%3D3%26estsredirect%3d2%26estsrequest%3drQQIARAApZE_aBNhGMbz9draBmuLgwhdItTBP5f78uWSu0YLxjS5XPOnbdr8HYzfffddckkud727NE1wc3ESZ0dBoRnUFgrFyUmwOHQUx6JQAoJjR1NEcBLB5eUdXp73-T3PTQb5YWQBQUioCkVWEBTE8ooAWVEQVHZRURQYIlogCMP2Ze_cjd2DN9deHiZ3H-5JD6TTVwOwUHddy4lwHK651HH9WPXrbc10eo6fmAanqxa2rEMAjgE4BWAwdrcCE_aGJHay-dzKZixgFpvWch4tpnKGWs_G3Z11FOdxsZLZ7BfkTKMiE2itZ1AgnZHiO1_GZlejHbeOzodp6336nJH_R_DOdZT4ZXG0DJj53zA1G1t1v6ET23RMzT1H2WcWwkFCBTW0yCItSFkeEZXFIiGsQoUwTzQtRJXwEZM0LdrWVZ9lm5reoj5qYL3lMzWtpbdpFRNCHcf3l0ecX6Ua7rTczww4Ya5s67U2dUY3jXt_BHs8DobjFyETmZryznmuenyes3HwYmJUEdjrPR4-erqy34jOfJqe9xxNcCTbLRX6JoeEenmNT9e45W6jnMUhe00sSWGIhVI-Vuw50lZSXoKRwLNJ8GMSPLngeTf9T-0OvLdEibbCdnoj17DkgG7l4ZYYqqKU3l7V-lm5asU6fDRjS6GEEX3rBe9nPGeXXg8-fvh28PV7cjh7u7jTrzV1tVPoIxztolRZlbNJo9UMYq3Bl4tNI7HdFGj-vtLkl34C0&cbcxt=&username=vignesh.mj%40infosys.com&mkt=&lc=");
		Thread.sleep(5000);
		return driver;
	}

	public static String decode(String token) {
		byte[] decodedBytes = Base64.decodeBase64(token);
		String TokenDetails = new String(decodedBytes);
		return TokenDetails;
	}

	public void naviToEnvir() throws InterruptedException {
		WebElement element = driver.findElement(By.xpath(
				"//*[starts-with(@class,'mat-ripple mat-mdc-tab-header-pagination mat-mdc-tab-header-pagination-before')]"));
		Thread.sleep(2000);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", element);
		JavascriptExecutor executor3 = (JavascriptExecutor) driver;
		executor3.executeScript("arguments[0].click();", element);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Environments)).click();
	}

	public boolean verifyButtons(String env) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(Environments));
		WebElement Environmentss = driver.findElement(Environments);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Environmentss);
		boolean element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + env + "']")))
				.isEnabled();

		boolean flag;
		if (element) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	public void naviToVariableManagement() throws InterruptedException {
		WebElement element = driver.findElement(By.xpath(
				"//*[starts-with(@class,'mat-ripple mat-mdc-tab-header-pagination mat-mdc-tab-header-pagination-before')]"));
		Thread.sleep(2000);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);

		WebElement Variables1 = driver.findElement(By.xpath("//div[text()='Variables']"));
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", Variables1);
		// wait.until(ExpectedConditions.visibilityOfElementLocated(Variables)).click();
	}

	public void naviToRelease() throws InterruptedException {

		try {
			WebElement element = driver.findElement(By.xpath(
					"//*[starts-with(@class,'mat-ripple mat-mdc-tab-header-pagination mat-mdc-tab-header-pagination-before')]"));
			Thread.sleep(2000);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			log.info("Arrow is not required");
		}

		wait.until(ExpectedConditions.visibilityOfElementLocated(Release)).click();
	}

	public void naviToWorker() throws InterruptedException {

		Thread.sleep(16000);
		WebElement element = driver.findElement(By.xpath(
				"//*[starts-with(@class,'mat-ripple mat-mdc-tab-header-pagination mat-mdc-tab-header-pagination-before')]"));
		Thread.sleep(3000);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(5000);
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", element);
		Thread.sleep(16000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Workerss)).click();
	}

	public void naviToTools_Compliance() throws InterruptedException {
		Thread.sleep(3000);
		WebElement element1 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=' Dashboard ']")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(3000);
//		WebElement element = driver.findElement(By.xpath(
//				"//*[starts-with(@class,'mat-ripple mat-mdc-tab-header-pagination mat-mdc-tab-header-pagination-after')]"));
//		Thread.sleep(2000);
//		JavascriptExecutor executor = (JavascriptExecutor) driver;
//		executor.executeScript("arguments[0].click();", element);
//		Thread.sleep(2000);
//		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
//		executor1.executeScript("arguments[0].click();", element);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Tools_Compliance)).click();
	}

	By addEnvironment = By.xpath("(//*[text()='Add Environment'])[1]");
	By environmentName =By.xpath("//*[@placeholder='Environment name']");
	By defultName = By.xpath("//*[@placeholder='Environment name']");
	By categorySelect = By.xpath("//*[text()=' Requires Approval ']//preceding::div[16]");
	By environmentSave = By.xpath("//*[text()=' Save ']");

	public void environmentCreation(String Envalue, String Category) throws IOException, InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(Environments));
		WebElement Environmentss = driver.findElement(Environments);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Environmentss);
		wait.until(ExpectedConditions.elementToBeClickable(Environments)).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(addEnvironment)).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(environmentName)).sendKeys(Envalue);
		WebElement categoryClick = driver.findElement(categorySelect);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", categoryClick);
		Thread.sleep(2000);
		clickOnElement11(Category);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(environmentSave)).click();
	}

	public void environmentCreationwithApproval(String Envalue, String Category, String email)
			throws IOException, InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(Environments));
		WebElement Environmentss = driver.findElement(Environments);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Environmentss);
		wait.until(ExpectedConditions.elementToBeClickable(Environments)).click();
		wait.until(ExpectedConditions.elementToBeClickable(addEnvironment)).click();
		wait.until(ExpectedConditions.elementToBeClickable(environmentName)).sendKeys(Envalue);
		WebElement categoryClick = driver.findElement(categorySelect);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", categoryClick);
		Thread.sleep(2000);
		clickOnElement11(Category);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='mat-slide-toggle-thumb']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id,'mat-chip-list-input')]")))
				.sendKeys(email);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Add']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(environmentSave)).click();
	}

	public void variableCreation(String username, String password, String envname, String value)
			throws IOException, InterruptedException {
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(Variables));
		WebElement Variable = driver.findElement(Variables);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Variable);
		wait.until(ExpectedConditions.elementToBeClickable(Variables)).click();
		wait.until(ExpectedConditions.elementToBeClickable(addVariables)).click();
		wait.until(ExpectedConditions.elementToBeClickable(variableName)).sendKeys(username);
		wait.until(ExpectedConditions.elementToBeClickable(defaultValue)).sendKeys(password);
		parameterEnvCreation(envname, value);
		wait.until(ExpectedConditions.elementToBeClickable(variableSaveButton)).click();
	}

	public void variableCreationWithSecure(String username, String password, String envname, String value)
			throws IOException, InterruptedException {
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(Variables));
		WebElement Variable = driver.findElement(Variables);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Variable);
		wait.until(ExpectedConditions.elementToBeClickable(Variables)).click();
		wait.until(ExpectedConditions.elementToBeClickable(addVariables)).click();
		wait.until(ExpectedConditions.elementToBeClickable(variableName)).sendKeys(username);
		wait.until(ExpectedConditions.elementToBeClickable(defaultValue)).sendKeys(password);
		wait.until(ExpectedConditions.elementToBeClickable(defaultValueSecure)).click();
		parameterEnvCreation(envname, value);
		wait.until(ExpectedConditions.elementToBeClickable(variableSaveButton)).click();
	}

	public boolean variableVerifySecure(String username) throws IOException, InterruptedException {
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[contains(text(),'" + username + "')]//following::td[3]//button[1]"))).click();
		String defaultvalue = wait.until(ExpectedConditions.elementToBeClickable(defaultValue)).getAttribute("value");
		String envvalue = wait.until(ExpectedConditions.elementToBeClickable(variableEnvValue)).getAttribute("value");
		boolean flag;
		if (defaultvalue.contains("SECRET:") && envvalue.contains("SECRET:")) {
			flag = true;
		} else {
			flag = false;
		}

		return flag;
	}

	public void cancelVariablePopup() throws IOException, InterruptedException {
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' Cancel ']"))).click();
		Thread.sleep(4000);
	}

	public void variableCreationWithoutParameter(String username, String password, String envname, String value)
			throws IOException, InterruptedException {
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(Variables));
		WebElement Variable = driver.findElement(Variables);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Variable);
		wait.until(ExpectedConditions.elementToBeClickable(Variables)).click();
		wait.until(ExpectedConditions.elementToBeClickable(addVariables)).click();
		wait.until(ExpectedConditions.elementToBeClickable(variableName)).sendKeys(username);
		wait.until(ExpectedConditions.elementToBeClickable(defaultValue)).sendKeys(password);
		wait.until(ExpectedConditions.elementToBeClickable(variableSaveButton)).click();
	}

	public static String currentDatePicker() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		return date;
	}

	public String currentDatePickers111() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddHHmms");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		return date;
	}

	public String currentDatePickersmins() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddHHmms");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		return date;
	}

	public String releaseCreation(String NewreleaseName, String environmentNames)
			throws IOException, InterruptedException, AWTException {

		wait.until(ExpectedConditions.elementToBeClickable(Release));
		WebElement Releasess = driver.findElement(Release);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Releasess);
		wait.until(ExpectedConditions.elementToBeClickable(Release)).click();
		wait.until(ExpectedConditions.elementToBeClickable(addRelease)).click();
		String dates = NewreleaseName + currentDatePickers111();
		wait.until(ExpectedConditions.elementToBeClickable(releaseName)).sendKeys(dates);

		wait.until(ExpectedConditions.elementToBeClickable(environmentSelect)).click();

		searchClickOnElement(environmentNames);

		WebElement okButt = wait.until(ExpectedConditions.elementToBeClickable(okButtons));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", okButt);
		wait.until(ExpectedConditions.elementToBeClickable(okButtons)).click();

		String currentDate = currentDatePicker();
		String endDates = currentDatePicker();

		wait.until(ExpectedConditions.elementToBeClickable(startDate)).click();

		WebElement cutnDate = driver.findElement(By.xpath("//*[text()='" + " " + currentDate + " " + "']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", cutnDate);

		wait.until(ExpectedConditions.elementToBeClickable(endDate)).click();

		WebElement ennDate = driver.findElement(By.xpath("//*[text()='" + " " + endDates + " " + "']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ennDate);

		wait.until(ExpectedConditions.elementToBeClickable(releaseOkButton));
		WebElement okbuton = driver.findElement(By.xpath("//*[text()=' Ok ']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", okbuton);
		return dates;
	}

	public void clickOnElement1(String value) throws InterruptedException {
		List<WebElement> list = driver.findElements(By.xpath("//*[@class='mat-option-text']"));
		for (WebElement webElement : list) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
			log.info(webElement.getText());
			if (webElement.getText().equalsIgnoreCase(value)) {
				wait.until(ExpectedConditions.elementToBeClickable(webElement));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
				break;
			}
		}
	}

	public void searchClickOnElement(String value) throws InterruptedException {

		WebElement element = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@placeholder='Search'])[2]")));
		element.click();
		Thread.sleep(2000);
		driver.switchTo().activeElement().sendKeys(value);
		Thread.sleep(4000);
		List<WebElement> list = driver.findElements(By.xpath("//*[@class='mat-option-text']"));
		for (WebElement webElement : list) {
			if (webElement.getText().equalsIgnoreCase(value)) {
				wait.until(ExpectedConditions.elementToBeClickable(webElement));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
				break;
			}
		}
	}

	public void parameterEnvCreation(String envname, String value) throws InterruptedException {
		Thread.sleep(3000);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[text()=' Add environment mapping ']//following::div[15]")));
		WebElement element = driver
				.findElement(By.xpath("//*[text()=' Add environment mapping ']//following::div[15]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		clickOnElement1(envname);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("(//*[text()=' add ']//preceding::input[1])[2]"))).click();
		Thread.sleep(2000);
		driver.switchTo().activeElement().sendKeys(value);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=' add ']"))).click();
	}

	public void variableSecretEncryptUpdate(String variableNames) throws IOException, InterruptedException {
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[text()='" + variableNames + "']//following::td//button[1]")))
				.click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//mat-label[text()='Secret']//following::div[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(variableSaveButton)).click();
		Thread.sleep(4000);
	}

	public boolean verifyAfterCreateEnv(String Envalue, String Category) throws IOException, InterruptedException {
	wait.until(ExpectedConditions
				.presenceOfAllElementsLocatedBy(By.xpath("//*[text()='" + Category + "']//following::td//span")));
		Thread.sleep(4000);
		List<WebElement> element = driver.findElements(By.xpath("//*[text()='" + Category + "']//following::td//span"));
		boolean flag = false;
		for (WebElement webElement : element) {
			Thread.sleep(2000);
			if (webElement.getText().equalsIgnoreCase(Envalue)) {
				flag = true;
				break;
			} else {
				flag = false;
			}
		}
		return flag;
	}

	public boolean verifyAfterCreateVariable(String variableNames) throws IOException, InterruptedException {
		Thread.sleep(3000);
		WebElement element = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(3000);
		String value = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='" + variableNames + "']")))
				.getText();
		Thread.sleep(2000);
		boolean flag = false;
		if (value.equalsIgnoreCase(variableNames)) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	public void scrolldown() throws InterruptedException {
		Thread.sleep(3000);
		WebElement element = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(3000);
	}

	public void scrollUp() throws InterruptedException {
		Thread.sleep(3000);
		WebElement element1 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=' Dashboard ']")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(3000);
	}

	public boolean verifyAfterCreateRelease(String releaseName, String env) throws IOException, InterruptedException {

		WebElement el = driver.findElement(By.xpath("//*[normalize-space(text())='" + releaseName
				+ "']//following::td[1]//mat-chip-list[1]//mat-chip[1]//span[1]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
		Thread.sleep(2000);
		String value = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[normalize-space(text())='"
				+ releaseName + "']//following::td[1]//mat-chip-list[1]//mat-chip[1]//span[1]"))).getText();
		Thread.sleep(2000);
		boolean flag = false;
		if (value.equalsIgnoreCase(env)) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	public boolean clickOnReleaseDetailsAndVerify(String releaseName, String Env)
			throws IOException, InterruptedException {

		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[normalize-space(text())='" + releaseName + "']//following::td[3]//button[1]"))).click();
		Thread.sleep(2000);
		boolean flag;
		try {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//mat-chip[normalize-space(text())='" + Env + "']")));
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	public void clickOnClose() throws IOException, InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=' Close ']"))).click();
		Thread.sleep(2000);
	}

	public void updateReleaseDetails(String ReleaseName, String updateEnvtName)
			throws IOException, InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[normalize-space(text())='" + ReleaseName + "']//following::button[1]"))).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(environmentSelect1)).click();
		searchClickOnElement(updateEnvtName);
		WebElement okButt = wait.until(ExpectedConditions.elementToBeClickable(okButtons));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", okButt);
		wait.until(ExpectedConditions.elementToBeClickable(okButtons)).click();
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(releaseOkButton));
		WebElement okbuton = driver.findElement(By.xpath("//*[text()=' Ok ']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", okbuton);
		Thread.sleep(3000);
	}

	public void archveReleaseDetails(String ReleaseName) throws IOException, InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[normalize-space(text())='" + ReleaseName + "']//following::button[2]"))).click();
		Thread.sleep(1000);

		String currentDate = currentDatePicker();
		String endDates = currentDatePicker();

		try {
			WebElement cutnDate = driver.findElement(By.xpath("//*[text()='" + " " + currentDate + " " + "']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", cutnDate);
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", actualStartDate);
			WebElement cutnDate = driver.findElement(By.xpath("//*[text()='" + " " + currentDate + " " + "']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", cutnDate);
		}

		wait.until(ExpectedConditions.elementToBeClickable(actualEndDate)).click();

		WebElement ennDate = driver.findElement(By.xpath("//*[text()='" + " " + endDates + " " + "']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ennDate);

		wait.until(ExpectedConditions.elementToBeClickable(releaseOkButton));
		WebElement okbuton = driver.findElement(By.xpath("//*[text()=' Ok ']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", okbuton);

		Thread.sleep(3000);
	}

	public void includeArchived() throws IOException, InterruptedException {
		Thread.sleep(3000);
		WebElement element1 = driver.findElement(By.xpath("//div[@class='mat-slide-toggle-thumb']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='mat-slide-toggle-thumb']")))
				.click();
	}

	public void updateEnv(String Envalue, String Category, String updateCategory)
			throws IOException, InterruptedException {
//		wait.until(ExpectedConditions
//				.visibilityOfAllElementsLocatedBy(By.xpath("//*[text()='" + Category + "']//following::td//span")));
		List<WebElement> element = driver.findElements(By.xpath("//*[text()='" + Category + "']//following::td//span"));
		for (WebElement webElement : element) {
			log.info(webElement.getText());
			if (webElement.getText().equalsIgnoreCase(Envalue)) {
				Thread.sleep(1000);
				log.info(webElement.getText());
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
				Thread.sleep(2000);
				WebElement categoryClick = driver.findElement(categorySelect);
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", categoryClick);
				clickOnElement11(updateCategory);
				Thread.sleep(2000);
				WebElement envSave = driver.findElement(environmentSave);
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", envSave);
				Thread.sleep(5000);
				break;
			} else {
				log.info("Env not Available");
			}
		}
	}

	public void updateVariable(String username, String updateDefaultName) throws IOException, InterruptedException {
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[text()='" + username + "']//following::td//button[1]")))
				.click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(defaultValue)).clear();
		wait.until(ExpectedConditions.elementToBeClickable(defaultValue)).sendKeys(updateDefaultName);
		wait.until(ExpectedConditions.elementToBeClickable(variableSaveButton)).click();
		Thread.sleep(4000);
	}

	public void deleteEnv(String Envalue) throws IOException, InterruptedException {
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[text()='" + Envalue + "']//following::mat-icon"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=' Yes ']"))).click();
		Thread.sleep(1000);
	}

	public void deleteEnvIfExits(String Envalue) throws IOException, InterruptedException {
		try {
			driver.findElement(By.xpath("//*[text()='" + Envalue + "']//following::mat-icon")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=' Yes ']"))).click();
			Thread.sleep(2000);
			log.info("Env available and deleted");
		} catch (Exception e) {
			log.info("Env Not available");
		}
	}

	public void deleteVariable(String username) throws IOException, InterruptedException {
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[text()='" + username + "']//following::td//button[2]")))
				.click();
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Cancel']//preceding::button[1]")))
				.click();
		Thread.sleep(1000);
	}

	public void deleteAllVariable(String username) throws IOException, InterruptedException {
		try {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[text()='" + username + "']//following::td//button[2]")))
					.click();
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[text()='Cancel']//preceding::button[1]"))).click();
			Thread.sleep(1000);
		} catch (Exception e) {
			log.info("variable not avaliable");
		}
	}

	public void clickOnElement11(String value) throws InterruptedException {
		List<WebElement> categroys = driver.findElements(By.xpath("//*[starts-with(@class,'mat-mdc-option')]"));
		for (WebElement webElement : categroys) {
			log.info(webElement.getText());
			if (webElement.getText().equalsIgnoreCase(value)) {
				log.info(" ==> " + webElement.getText());
				wait.until(ExpectedConditions.elementToBeClickable(webElement));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
				break;
			}
		}
	}

	public void clickOnElement13(List<WebElement> categroys, String value) throws InterruptedException {
		for (WebElement webElement : categroys) {
			log.info("if condition ==> " + webElement.getText());
			Thread.sleep(1000);
			if (webElement.getText().equalsIgnoreCase(value)) {
				Thread.sleep(1000);
				log.info(" ==> " + webElement.getText());
				wait.until(ExpectedConditions.elementToBeClickable(webElement));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
				break;
			}
		}
	}

	public void openApplicationDashboardAudi(String appName) throws InterruptedException {
		Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(applicationViewLeftNav)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='DevSecOps Maturity']")));
		String portfolioview = driver.findElement(By.xpath("//*[contains(text(),'Dashboard')]")).getText();
		if (portfolioview.equals("Dashboard")) {
			String withoutSpace = appName.replace(" ", "");
			Thread.sleep(4000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search']")))
					.sendKeys(withoutSpace);
			Thread.sleep(4000);
			log.info("Application Dashboard opened");
			log.info("//*[normalize-space(text())='" + appName + "']//following::span[13]");
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[normalize-space(text())='" + appName + "']//following::span[13]"))).click();
			Thread.sleep(4000);
			log.info("AuditLogs opened");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Audit']"))).click();
			Thread.sleep(4000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Telemetry']"))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=' DevSecOps Compliance ']")));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
					driver.findElement(By.xpath("//*[text()=' Audit Logs ']")));
		} else {
			log.info("Application Dashboard is not opened");
			openApplicationDashboardAudi(appName);
		}
		Thread.sleep(2000);
	}

	public void openApplicationDashboardAudi1(String appName) throws InterruptedException {
		Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(applicationViewLeftNav)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='DevSecOps Maturity']")));
		String portfolioview = driver.findElement(By.xpath("//*[contains(text(),'Dashboard')]")).getText();
		if (portfolioview.equals("Dashboard")) {
			String withoutSpace = appName.replace(" ", "");
			Thread.sleep(4000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search']")))
					.sendKeys(withoutSpace);
			Thread.sleep(7000);
			log.info("Application Dashboard opened");
			log.info("//*[normalize-space(text())='" + appName + "']//following::span[13]");
			Boolean element = wait
					.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//*[normalize-space(text())='" + appName + "']//following::span[13]")))
					.isDisplayed();
			assertTrue(element);
			log.info("its after assert");
			WebElement els = driver.findElement(By.xpath("//*[text()='Success Rate']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", els);
			Thread.sleep(2000);
			Thread.sleep(6000);
			log.info("AuditLogs opened");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Audit']"))).click();
			Thread.sleep(4000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Telemetry']"))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=' DevSecOps Compliance ']")));

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
					driver.findElement(By.xpath("//*[text()=' Audit Logs ']")));
		} else {
			log.info("Application Dashboard is not opened");
		}
		Thread.sleep(2000);
	}

	public void openPortfolioDashboardDetails(String portfolioName) throws InterruptedException {
		Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(portfolioViewLeftNav)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(portfolioViewLeftNav)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//span[contains(text(),'Dashboard')]"))));
		String portfolioview = driver.findElement(By.xpath("//span[contains(text(),'Dashboard')]")).getText();
		if (portfolioview.equals("Dashboard")) {
			String withoutSpace = portfolioName.replace(" ", "");
			Thread.sleep(4000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search']")))
					.sendKeys(withoutSpace);
			Thread.sleep(4000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='DevSecOps Maturity']")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[normalize-space(text())='" + portfolioName + "']//following::span[8]"))).click();
			Thread.sleep(4000);
			log.info("portfolio Dashboard opened");
		} else {
			log.info("portfolio Dashboard is not opened");
		}
		Thread.sleep(2000);
	}

	public void navToToolsComp() throws InterruptedException {
		Thread.sleep(4000);
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
//				"//*[starts-with(@class,'mat-ripple mat-mdc-tab-header-pagination mat-mdc-tab-header-pagination-after')]")))
//				.click();
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
//				"//*[starts-with(@class,'mat-ripple mat-mdc-tab-header-pagination mat-mdc-tab-header-pagination-after')]")))
//				.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(toolCom)).click();
		log.info("Navigated to Tools Compliance details page");
	}

	public void navToApplicationGeneral() throws InterruptedException {
		Thread.sleep(4000);
		WebElement element = driver.findElement(By.xpath(
				"//*[starts-with(@class,'mat-ripple mat-mdc-tab-header-pagination mat-mdc-tab-header-pagination-before')]"));
		element.click();
		element.click();
		element.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(general)).click();
		log.info("Navigated to Application General details page");
	}

	public void editApplication(String portfolio) throws InterruptedException {
		Thread.sleep(4000);
		driver.findElement(portfolioName).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@class='mat-option-text']")));
		List<WebElement> profoioName = driver.findElements(By.xpath("//*[@class='mat-option-text']"));
		clickOnElement(profoioName, portfolio);
		driver.findElement(submitButtons).click();
	}

	public void navToGating() throws InterruptedException {
		Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(gating)).click();
		log.info("Navigated to Gating details page");
	}

	public void navTolockPlugin() throws InterruptedException {
		Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(lockPlugin)).click();
		log.info("Navigated to LockPlugin details page");
	}

	public void navToToolMandate() throws InterruptedException {
		Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(toolMandate)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[starts-with(@id,'mat-checkbox')]")))
				.click();
		log.info("Navigated to ToolMandate");
	}

	public void navToAddGating() throws InterruptedException {
		Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(addGating)).click();
		log.info("Navigated to AddGating");
	}

	public void navToAddLockPlugin() throws InterruptedException {
		Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(addlockPlugin)).click();
		log.info("Click on Add lockPlugin");
	}

//	public void fileGateDetails() throws InterruptedException {
//		Thread.sleep(2000);
//		WebElement pipl = driver.findElement(piplineMatcher);
//		Actions action = new Actions(driver);
//		action.moveToElement(pipl).doubleClick().build().perform();
//		Thread.sleep(2000);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name='applicationName']"))).click();
//		Thread.sleep(2000);
//		wait.until(ExpectedConditions
//				.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search']//following::input[2]"))).click();
//		driver.switchTo().activeElement().sendKeys("*", Keys.ENTER, Keys.TAB);
//		Thread.sleep(2000);
//		driver.switchTo().activeElement().sendKeys("*", Keys.TAB);
//		Thread.sleep(2000);
//		driver.switchTo().activeElement().sendKeys("Test", Keys.TAB);
//		Thread.sleep(2000);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-select[@placeholder='Search']")))
//				.click();
//		wait.until(ExpectedConditions
//				.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search']//following::input[2]")))
//				.sendKeys("==", Keys.TAB);
//		Thread.sleep(2000);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@placeholder='Enter value']"))).click();
//		Thread.sleep(4000);
//		driver.switchTo().activeElement().sendKeys("Test", Keys.ENTER);
//		Thread.sleep(4000);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=' Save ']"))).click();
//	}
	public void fileGateDetails() throws InterruptedException {
		Thread.sleep(2000);
		WebElement pipl = driver.findElement(piplineMatcher);
		Actions action = new Actions(driver);
		action.moveToElement(pipl).doubleClick().build().perform();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name='applicationName']"))).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("(//input[@placeholder='Search']//following::input[2])[2]")))
				.click();
		driver.switchTo().activeElement().sendKeys("*", Keys.ENTER, Keys.TAB);
		Thread.sleep(2000);
		driver.switchTo().activeElement().sendKeys("*", Keys.TAB);
		Thread.sleep(2000);
		driver.switchTo().activeElement().sendKeys("Test", Keys.TAB);
		Thread.sleep(2000);
		driver.switchTo().activeElement().sendKeys("==");
//		wait.until(ExpectedConditions
//				.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search']//following::input[2]"))).click();
//		Thread.sleep(4000);
 
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@placeholder='Enter value']"))).click();
		Thread.sleep(4000);
		driver.switchTo().activeElement().sendKeys("Test", Keys.ENTER);
		Thread.sleep(4000);
 
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",
				driver.findElement(By.xpath("//*[text()=' Save ']")));
 
	}
	
	
	
	public void addGatingData(String selectMatric, String operator, String value) throws InterruptedException {
		Thread.sleep(2000);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//*[starts-with(@class,'mat-input-element mat-form-field-autofill-control mat-autocomplete-trigger ng')]")))
				.click();
		clickOnElement1(selectMatric);
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[starts-with(@class,'mat-select-placeholder ng-tns')]")))
				.click();

		clickOnElement1(operator);

		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@placeholder='Enter value']"))).click();
		Thread.sleep(4000);
		driver.switchTo().activeElement().sendKeys(value, Keys.ENTER);
		Thread.sleep(4000);
	}


	
	
	public void clickOnSave() throws InterruptedException {

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",

					driver.findElement(By.xpath("//*[text()='Save']")));

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Save']"))).click();

			log.info("Click On Save");

			Thread.sleep(4000);

		}
	 

	public void clickOnEdit() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@mattooltip='Edit']"))).click();
		log.info("Click On Edit");
		Thread.sleep(4000);
	}

	public void deleteParticularGating(String value) throws InterruptedException {
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[text()='" + value + "']//following::mat-icon[1]"))).click();
		log.info("Click On Delete");
		Thread.sleep(4000);
	}

	public void SaveLockPlugin() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=' Save ']"))).click();
		log.info("Click On plugin Save");
	}

	public void clickOnSaveLockPlugin() throws InterruptedException {

		Thread.sleep(4000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//*[text()='Save']")));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Save']")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		log.info("Click On Save");
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Yes']"))).click();
			Thread.sleep(5000);
		} catch (Exception e) {
			log.info("its catch block");
		}
	}

	public void clickOnSaveMandate() throws InterruptedException {

		Thread.sleep(4000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//*[text()='Save']")));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Save']")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		log.info("Click On Save");
	}

	public void clickOnDeleteLockPlugin() throws InterruptedException {
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=' false ']//following::button[2]")))
				.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=' Yes ']"))).click();
		log.info("Click On Delete");
		clickOnSave();
		Thread.sleep(4000);
	}

	public void clickOnDELETE() throws InterruptedException {
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[text()=' [ Test == Test ]  ']//following::button[2]")))
				.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=' Yes ']"))).click();
		log.info("Click On Delete");
		clickOnSave();
		Thread.sleep(4000);
	}

	public void itemPerpage() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[text()=' Items per page: ']//following::div[10]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=' 15 ']"))).click();
		log.info("Click On itemPerpage");
		Thread.sleep(4000);
	}

	public void itemPerpageDynamic(String pageNo) throws InterruptedException {
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//*[text()=' Items per page: ']//following::div[14]")));
		Boolean isDis = driver.findElement(By.xpath("//*[text()=' Items per page: ']//following::div[14]"))
				.isDisplayed();
		log.info(isDis);
		WebElement element = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[text()=' Items per page: ']//following::div[14]")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=' " + pageNo + " ']"))).click();
		log.info("Click On itemPerpage");
		Thread.sleep(4000);

		String label = driver.findElement(By.xpath("//div[@class='mat-paginator-range-label']")).getText();
		String[] value = label.split(" of ");
		int number = Integer.parseInt(value[1]);
		if (number > 100) {
			log.info("its more then 100");
			driver.findElement(By.xpath("//*[@aria-label='Next page']")).click();
		} else {
			log.info("its less then 100");
		}
	}

	public void openPortfolioDashboard(String portfolioName) throws InterruptedException {
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(portfolioViewLeftNav)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='DevSecOps Maturity']")));
		String portfolioview = driver.findElement(By.xpath("//*[contains(text(),'Dashboard')]")).getText();
		if (portfolioview.equals("Dashboard")) {
			String withoutSpace = portfolioName.replace(" ", "");
			Thread.sleep(4000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search']")))
					.sendKeys(withoutSpace);
			Thread.sleep(4000);
			log.info("portfolio Dashboard opened");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='DevSecOps Maturity']")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[normalize-space(text())='" + portfolioName + "']"))).click();
			Thread.sleep(4000);
			log.info("AuditLogs opened");
			driver.findElement(By.xpath("//*[text()='Audit']")).click();
			Thread.sleep(4000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=' DevSecOps Compliance ']")));
		} else {
			log.info("portfolio Dashboard is not opened");
		}
		Thread.sleep(2000);
	}

	public void openAuditLogs(String filterName, String name) throws InterruptedException {
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(auditLogsNav)).click();
		WebElement element = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Parameters']")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
		clickOnElement11(filterName);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@placeholder='Filter']"))).click();
		Thread.sleep(2000);
		driver.switchTo().activeElement().sendKeys(name);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=' Filter ']"))).click();
		Thread.sleep(2000);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=' Filter ']//following::mat-icon[1]"))).click();
//		Thread.sleep(2000);
	}

	public void openPlatformConfiguration() throws InterruptedException {
		Thread.sleep(8000);
		wait.until(ExpectedConditions.elementToBeClickable(platformConfigurationNav)).click();
		Thread.sleep(3000);
		try {
			driver.findElement(By.xpath("//span[text()=' Platform Configuration ']"));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(platformConfigurationNav)).click();
		}
	}

	public void navAuditLogs() throws InterruptedException {
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(auditLogsNav)).click();
	}

	public void navWorker() throws InterruptedException {
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(worker)).click();
	}

	public boolean delectPlugin(String plginName) throws IOException {
		try {
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
					driver.findElement(By.xpath("//*[text()='" + plginName + "']//following::td[2]//button[2]")));
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(
					driver.findElement(By.xpath("//*[text()='" + plginName + "']//following::td[2]//button[3]"))))
					.click();
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Yes ']"))))
					.click();
		} catch (Exception e) {
			log.info("Plugin Not Available We Can add it");
		}
		return true;
	}

	public void addPlugins(String plginName) throws IOException, InterruptedException {
		Thread.sleep(3000);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[text()='Add Plugin Configuration']")))).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//*[@placeholder='Search'])[2]"))))
				.sendKeys(plginName);
		Thread.sleep(5000);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//h3[text()='" + plginName + "']")))).click();
		Thread.sleep(3000);
	}

	public void inputDataforEnvironmentVar_IDP(String plginName, String DashboardUrl, String ProforlioUrl,
			String RegUrl, String BaseUrl, String PublicKey, String applicationName, String portfolioName,
			String WorkerName, String patternName, String LogoURL, String Hostname)
			throws IOException, InterruptedException {
		Thread.sleep(3000);

		String value = " Inputs for " + plginName + " ";
		System.out.println("//*[text()='" + value + "']//following::input[4]");
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[text()='" + value + "']//following::input[4]"))))
				.sendKeys(DashboardUrl);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//*[text()='" + value + "']//following::input[4]")));
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[text()='" + value + "']//following::input[5]"))))
				.sendKeys(ProforlioUrl);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[text()='" + value + "']//following::input[6]"))))
				.sendKeys(RegUrl);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//*[text()='" + value + "']//following::input[7]")));
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[text()='" + value + "']//following::input[7]"))))
				.sendKeys(BaseUrl);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[text()='" + value + "']//following::input[8]"))))
				.sendKeys(PublicKey);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//*[text()='" + value + "']//following::input[9]")));
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[text()='" + value + "']//following::input[9]"))))
				.sendKeys(applicationName);
		wait.until(ExpectedConditions.elementToBeClickable(
				driver.findElement(By.xpath("//*[text()='" + value + "']//following::input[10]"))))
				.sendKeys(portfolioName);
//		wait.until(ExpectedConditions.elementToBeClickable(
//				driver.findElement(By.xpath("//*[text()='" + value + "']//following::input[12]"))))
//				.sendKeys(WorkerName);
//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
//				driver.findElement(By.xpath("//*[text()='" + value + "']//following::input[11]")));
//		wait.until(ExpectedConditions.elementToBeClickable(
//				driver.findElement(By.xpath("//*[text()='" + value + "']//following::input[11]"))))
//				.sendKeys(patternName);
//
//		wait.until(ExpectedConditions.elementToBeClickable(
//				driver.findElement(By.xpath("//*[text()='" + value + "']//following::input[15]"))))
//				.sendKeys(patternName);
//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
//				driver.findElement(By.xpath("//*[text()='" + value + "']//following::input[16]")));
//		wait.until(ExpectedConditions.elementToBeClickable(
//				driver.findElement(By.xpath("//*[text()='" + value + "']//following::input[16]")))).sendKeys(LogoURL);
//
//		wait.until(ExpectedConditions.elementToBeClickable(
//				driver.findElement(By.xpath("//*[text()='" + value + "']//following::input[16]")))).sendKeys(Hostname);

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='Cancel']")))).click();

		Thread.sleep(2000);

	}

	public void inputDatafor_armorcode_sync_report(String plginName, String workerName, String pluginName, String token,
			String BaseUrl, String incationToken, String apiToken, String portfolioMap)
			throws IOException, InterruptedException {
		Thread.sleep(3000);

		String value = " Inputs for " + plginName + " ";

		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[text()='" + value + "']//following::input[2]"))))
				.sendKeys(workerName);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//*[text()='" + value + "']//following::input[4]")));

		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[text()='" + value + "']//following::input[4]"))))
				.click();
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[text()='" + value + "']//following::input[4]"))))
				.sendKeys(token);

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//*[text()='" + value + "']//following::input[2]")));

		wait.until(ExpectedConditions.elementToBeClickable(
				driver.findElement(By.xpath("//*[text()='" + value + "']//following::textarea[1]"))))
				.sendKeys(pluginName);

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//*[text()='" + value + "']//following::input[6]")));
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[text()='" + value + "']//following::input[6]"))))
				.sendKeys(BaseUrl);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[text()='" + value + "']//following::input[7]"))))
				.sendKeys(incationToken);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//*[text()='" + value + "']//following::input[7]")));
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[text()='" + value + "']//following::input[8]"))))
				.sendKeys(apiToken);
		wait.until(ExpectedConditions.elementToBeClickable(
				driver.findElement(By.xpath("//*[text()='" + value + "']//following::textarea[2]"))))
				.sendKeys(portfolioMap);

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='Cancel']")))).click();

		Thread.sleep(2000);

	}

	public void inputDataforEncryption(String plginName, String workerName, String encrption)
			throws IOException, InterruptedException {
		Thread.sleep(3000);

		String value = " Inputs for " + plginName + " ";
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[text()='" + value + "']//following::input[2]"))))
				.sendKeys(workerName);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[text()='" + value + "']//following::input[4]"))))
				.sendKeys(encrption);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='Cancel']")))).click();

		Thread.sleep(2000);

	}

	public boolean addPlugins(String plginName, String workerName, String url)
			throws IOException, InterruptedException {
		Thread.sleep(3000);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[text()='Add Plugin Configuration']")))).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//*[@placeholder='Search'])[2]"))))
				.sendKeys(plginName);
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//h3[text()='" + plginName + "']")))).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@placeholder='Worker Name']"))))
				.sendKeys(workerName + Keys.TAB);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()=' Save ']")))).click();
		Thread.sleep(5000);
		return true;
	}

	public boolean editPlugins(String plginName, String updateWorkerName) throws IOException, InterruptedException {
		Thread.sleep(2000);
		Thread.sleep(2000);

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//*[text()='" + plginName + "']//following::td[2]//button[2]")));
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(
				driver.findElement(By.xpath("//*[text()='" + plginName + "']//following::td[2]//button[2]")))).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[text()='Cancel']//preceding::input[3]"))))
				.clear();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@placeholder='Worker Name']"))))
				.sendKeys(updateWorkerName);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()=' Save ']")))).click();
		return true;
	}

	public void clickOnElement(List<WebElement> element, String value) {
		for (WebElement webElement : element) {
			if (webElement.getText().equalsIgnoreCase(value)) {
				wait.until(ExpectedConditions.elementToBeClickable(webElement));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
				break;
			}
		}
	}

	public void navCreateApplication(String appName, String protfolioName, String OwnerName)
			throws InterruptedException, IOException {
		ExcelReader read = new ExcelReader();
		wait.until(ExpectedConditions.elementToBeClickable(applicationViewLeftNav)).click();
		wait.until(ExpectedConditions.elementToBeClickable(manage)).click();
		wait.until(ExpectedConditions.elementToBeClickable(createNewApplicationButon)).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(applicationName)).click();

		driver.findElement(applicationName).sendKeys(appName);
		driver.findElement(portfolioName).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@class='mat-option-text']")));
		List<WebElement> profoioName = driver.findElements(By.xpath("//*[@class='mat-option-text']"));
		clickOnElement(profoioName, protfolioName);
		Thread.sleep(2000);
		driver.findElement(owners).sendKeys(OwnerName);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();

		for (int i = 0; i < 3; i++) {
			Thread.sleep(4000);
			if (driver.findElement(saveApplication).isEnabled() == true) {
				wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(saveApplication))).click();
				Thread.sleep(4000);
				break;
			} else {
				driver.navigate().refresh();
			}
		}
		log.info("Application Created");
	}

	public void navCreatePortfolio(String protfolioName, String OwnerName) throws InterruptedException, IOException {
		ExcelReader read = new ExcelReader();
		wait.until(ExpectedConditions.elementToBeClickable(portfolioViewLeftNav)).click();
		wait.until(ExpectedConditions.elementToBeClickable(manage)).click();
		wait.until(ExpectedConditions.elementToBeClickable(createNewPortfolioButon)).click();
		Thread.sleep(2000);
		driver.findElement(portfoliosName).sendKeys(protfolioName);
		driver.findElement(owners).sendKeys(OwnerName);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();

		for (int i = 0; i < 3; i++) {
			Thread.sleep(6000);
			if (driver.findElement(createPortfolio).isEnabled() == true) {
				Thread.sleep(4000);
				driver.findElement(createPortfolio).click();
				Thread.sleep(4000);
				break;
			} else {
				driver.navigate().refresh();
			}
		}
		log.info("portfolio Created");
	}

	public void openPortfolioDashboardAudit(String portfolioName) throws InterruptedException {
		Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(portfolioViewLeftNav)).click();
		String portfolioview = driver.findElement(By.xpath("//*[contains(text(),'Dashboard')]")).getText();
		if (portfolioview.equals("Dashboard")) {
			String withoutSpace = portfolioName.replace(" ", "");
			Thread.sleep(9000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search']")))
					.sendKeys(withoutSpace);
			Thread.sleep(4000);
			log.info("portfolio Dashboard opened");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='DevSecOps Maturity']")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[normalize-space(text())='" + portfolioName + "']//following::span[13]"))).click();
			Thread.sleep(4000);
			log.info("AuditLogs opened");
			driver.findElement(By.xpath("//*[text()='Audit']")).click();
			Thread.sleep(4000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=' DevSecOps Compliance ']")));
		} else {
			log.info("portfolio Dashboard is not opened");
		}
		Thread.sleep(2000);
	}

	By workeflows = By.xpath("//div[text()='Workflows']");

	By addworkflows = By.xpath("//span[text()='Create Workflow']");

	By addStages = By.xpath("//*[starts-with(text(),' ADD STAGE ')]");

	By addSteps = By.xpath("//*[starts-with(text(),' ADD STEP ')]");

	By searchWorkflow = By.xpath("//*[text()=' Choose a Workflow and a Worker ']//following::input[1]");

	By workerNamefind = By.xpath("//*[text()=' Choose a Workflow and a Worker ']//following::div[15]");

	By addWorkflows = By.xpath("//*[text()=' Add ']");

	By submitButtons = By.xpath("//*[text()=' Submit ']");

	By WorkflowNameCreation = By.xpath("//*[text()=' Workflow Name ']//following::input[1]");

	By addConfirmButton = By.xpath("//*[text()=' Confirm ']");

	public void workfloCcreation(String pipelineNamess, String workflowName) throws InterruptedException, IOException {
		Thread.sleep(10000);
		String value = pipelineNamess;
		Thread.sleep(4000);
		WebElement workflowssss = driver.findElement(workeflows);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", workflowssss);
		wait.until(ExpectedConditions.elementToBeClickable(workeflows)).click();
		Thread.sleep(8000);
		wait.until(ExpectedConditions.elementToBeClickable(addworkflows)).click();
		wait.until(ExpectedConditions.elementToBeClickable(addStages)).click();
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(addSteps)).click();
		Thread.sleep(8000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchWorkflow)).sendKeys(value);
		Thread.sleep(4000);
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//td[text()='" + " " + value + " " + "']//preceding::td[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(addWorkflows)).click();
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(submitButtons)).click();
		Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(WorkflowNameCreation)).sendKeys(workflowName);
		wait.until(ExpectedConditions.elementToBeClickable(addConfirmButton)).click();
		Thread.sleep(6000);
	}

	public void navToWorkflow() throws InterruptedException, IOException {
//		WebElement element = driver.findElement(By.xpath(
//				"//*[starts-with(@class,'mat-ripple mat-mdc-tab-header-pagination mat-mdc-tab-header-pagination-after')]"));
//		Thread.sleep(2000);
//		log.info("Navigated to workflow tab");
//		JavascriptExecutor executor = (JavascriptExecutor) driver;
//		executor.executeScript("arguments[0].click();", element);
//		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
//		executor1.executeScript("arguments[0].click();", element);
//		Thread.sleep(4000);
		WebElement workflowssss = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(workeflows)));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", workflowssss);
		wait.until(ExpectedConditions.elementToBeClickable(workeflows)).click();
	}

	public void searchAndAddWorkflow(String pipelineNamess, String workerName) throws InterruptedException {
		PipelineHelper ph = new PipelineHelper(driver);
		Thread.sleep(8000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchWorkflow)).sendKeys(pipelineNamess);
		Thread.sleep(4000);
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//td[text()='" + " " + pipelineNamess + " " + "']//preceding::td[1]")))
				.click();
		ph.workerNameSelectInWorkflow(workerName);
		wait.until(ExpectedConditions.elementToBeClickable(addWorkflows)).click();
		Thread.sleep(4000);
	}

	public void searchAndAddWorkflowWithoutWorker(String pipelineNamess) throws InterruptedException {
		PipelineHelper ph = new PipelineHelper(driver);
		Thread.sleep(8000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchWorkflow)).sendKeys(pipelineNamess);
		Thread.sleep(4000);
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//td[text()='" + " " + pipelineNamess + " " + "']//preceding::td[1]")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(addWorkflows)).click();
		Thread.sleep(4000);
	}

	public void addWorkFlowName(String workflowName) throws InterruptedException {
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(submitButtons)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(WorkflowNameCreation)).sendKeys(workflowName);
		wait.until(ExpectedConditions.elementToBeClickable(addConfirmButton)).click();
		Thread.sleep(6000);
	}

	public void triggerWorkflow(String workflowName) throws InterruptedException {
		Thread.sleep(8000);
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath("//*[starts-with(text(),' Workflow Name')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);

		WebElement element = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(driver
				.findElement(By.xpath("//*[normalize-space(text())='" + workflowName + "']//following::button[1]"))))
				.click();
		Thread.sleep(4000);
	}

	public boolean verifyWorkflowName(String workflowName) throws InterruptedException {
		Thread.sleep(4000);
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		boolean flag;
		try {
			WebElement element = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//*[normalize-space(text())='" + workflowName + "']")));
			if (element.getText().equalsIgnoreCase(workflowName)) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	public void addWorkFlow(String pipelineNamess, String workflowName) throws InterruptedException, IOException {
		String value = pipelineNamess;
		Thread.sleep(8000);
		wait.until(ExpectedConditions.elementToBeClickable(addworkflows)).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[starts-with(text(),' ADD STAGE ')]"))).click();
		Thread.sleep(4000);
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[starts-with(text(),' ADD STAGE ')]//following::"))).click();
		Thread.sleep(8000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchWorkflow)).sendKeys(value);
		Thread.sleep(4000);
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//td[text()='" + " " + value + " " + "']//preceding::td[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(addWorkflows)).click();
	}

	By workflowNamess = By.xpath("//*[text()=' workflowName ']//following::button[4]");

	By yesButtonss = By.xpath("//*[text()='Yes']");

	public void editWorkfloCreation(String workflowName) throws InterruptedException, IOException {
		Thread.sleep(8000);
		wait.until(ExpectedConditions.elementToBeClickable(driver
				.findElement(By.xpath("//*[normalize-space(text())='" + workflowName + "']//following::button[4]"))))
				.click();
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(submitButtons)).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(yesButtonss)).click();
		Thread.sleep(6000);
	}

	public void editWorkflowSteps(String workflowName, String deletedWorkflow, String stage, String workflowNames,
			String worker) throws InterruptedException, IOException {
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		ApplicationView av = new ApplicationView(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		Thread.sleep(8000);
		wait.until(ExpectedConditions.elementToBeClickable(driver
				.findElement(By.xpath("//*[normalize-space(text())='" + workflowName + "']//following::button[4]"))))
				.click();
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath(" //*[normalize-space(text())='" + deletedWorkflow + "']//following::mat-icon[1]"))).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(yesButtonss)).click();

		ph.dyanamicworkflowStepAndPlugin(stage);
		av.searchAndAddWorkflow(workflowNames, worker);

		Thread.sleep(6000);
		wait.until(ExpectedConditions.elementToBeClickable(submitButtons)).click();
		wait.until(ExpectedConditions.elementToBeClickable(yesButtonss)).click();
	}

	public boolean viewWorkflowSteps(String workflowName, String deletedWorkflow)
			throws InterruptedException, IOException {
		Thread.sleep(8000);
		wait.until(ExpectedConditions.elementToBeClickable(driver
				.findElement(By.xpath("//*[normalize-space(text())='" + workflowName + "']//following::button[2]"))))
				.click();
		Thread.sleep(4000);
		boolean flag;
		try {
			WebElement element = driver.findElement(By.xpath("//*[normalize-space(text())='" + deletedWorkflow + "']"));
			if (element.getText().equalsIgnoreCase(deletedWorkflow)) {
				WebElement element1 = driver.findElement(By.xpath("//*[text()=' Close ']"));
				element1.click();
				flag = false;
			} else {
				WebElement element1 = driver.findElement(By.xpath("//*[text()=' Close ']"));
				element1.click();
				flag = true;
			}
		} catch (Exception e) {
			WebElement element1 = driver.findElement(By.xpath("//*[text()=' Close ']"));
			element1.click();
			flag = true;
		}
		Thread.sleep(2000);
		return flag;
	}

	public void deleteWorkflow(String workflowName) throws InterruptedException, IOException {
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(8000);
		wait.until(ExpectedConditions.elementToBeClickable(driver
				.findElement(By.xpath("//*[normalize-space(text())='" + workflowName + "']//following::button[5]"))))
				.click();
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(yesButtonss)).click();
		Thread.sleep(6000);
	}

	public void backToeWorkflow() throws InterruptedException, IOException {
		Thread.sleep(4000);
		wait.until(
				ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Workflow List ']"))))
				.click();
	}

	public boolean verifyDefectTrendInApplication() throws InterruptedException {
		if (new WebDriverWait(driver, Duration.ofMinutes(2)).until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath("//*[@id=\"pluginRoot_fbe067d9-2340-4081-8692-a840a8cc3d4f\"]/div/div[2]/idp-dbrd-trendchart/mat-card/div[4]/canvas")))
				.isDisplayed()) {
			log.info("Defect Trend is updated");
			return true;
		}
		return false;
	}

	public boolean verifySprintEfficiencyInApplication() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(
				"//*[@id=\"pluginRoot_fbe067d9-2340-4081-8692-a840a8cc3d4f\"]/div/div[3]/idp-dbrd-barchart/mat-card/div[3]/canvas")));
		if (new WebDriverWait(driver, Duration.ofMinutes(2)).until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath("//*[@id=\"pluginRoot_fbe067d9-2340-4081-8692-a840a8cc3d4f\"]/div/div[3]/idp-dbrd-barchart/mat-card/div[3]/canvas")))
				.isDisplayed()) {
			log.info("Sprint Efficiency is updated");
			return true;
		}
		return false;
	}

	public boolean verifyVSMInApplication() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//mat-tab-group/mat-tab-header/div[3]")));
		driver.findElement(By.xpath("//mat-tab-group/mat-tab-header/div[3]")).click();
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(application_Tab_Content("VSM")));
		driver.findElement(By.xpath("//div[@class=\"mat-tab-labels\"]/div[8]/div[text()=\"VSM\"]")).click();
		if (!new WebDriverWait(driver, Duration.ofMinutes(2)).until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath("//div[@class=\"mat-tab-body-wrapper\"]/mat-tab-body[8]/div/app-value-stream-mapping/div/div[2]")))
				.getText().contentEquals(" Could not fetch progression details from Continuum! ")) {
			Thread.sleep(2000);
			log.info("VSM is updated");
			return true;
		}
		return false;
	}

	public void openApplicationUsingDashboard(String applicationName) throws InterruptedException {
		Thread.sleep(3000);
		new WebDriverWait(driver, Duration.ofMinutes(2))
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(applicationViewLeftNav))).click();
		driver.findElement(Dashboard).click();
		Thread.sleep(1000);
		log.info("Application view opened");
		wait.until(ExpectedConditions.visibilityOfElementLocated(filterPipeline)).sendKeys(applicationName);
		Thread.sleep(3000);
		new WebDriverWait(driver, Duration.ofMinutes(2)).until(
				ExpectedConditions.elementToBeClickable(driver.findElement(apllication_Existing(applicationName))))
				.click();
		log.info("Application expanded");
	}

	public void create_Workflows_In_Application(String workflowName, String worker, int stepNum)
			throws InterruptedException {
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//mat-chip-list/div/div[" + stepNum + "]/div/mat-chip/span")));
		driver.findElement(By.xpath("//mat-chip-list/div/div[" + stepNum + "]/div/mat-chip/span")).click();
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", new WebDriverWait(driver,
				Duration.ofMinutes(2))
				.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(
						"//mat-form-field/div/div/div/input[@placeholder=\"Search Pipeline or Workflow name\"]")))));
		driver.findElement(
				By.xpath("//mat-form-field/div/div/div/input[@placeholder=\"Search Pipeline or Workflow name\"]"))
				.sendKeys(workflowName);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By
				.xpath("//mat-dialog-container/app-select-pipeline-workflow/mat-toolbar/mat-toolbar-row/div/div[2]")));
		driver.findElement(
				By.xpath("//mat-dialog-container/app-select-pipeline-workflow/mat-toolbar/mat-toolbar-row/div/div[2]"))
				.click();
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",
				driver.findElement(selectFromDropdown(worker)));
		driver.findElement(By.xpath(
				"//mat-dialog-container/app-select-pipeline-workflow/div/perfect-scrollbar/div/div/div/div/div[@title='"
						+ workflowName + "']"))
				.click();
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", new WebDriverWait(driver,
				Duration.ofMinutes(2))
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(
						"//div[@class=\"cdk-overlay-container\"]/div[4]/div/mat-dialog-container/app-select-pipeline-workflow/div[2]/button[2]")))));
		driver.findElement(By.xpath(
				"//div[@class=\"cdk-overlay-container\"]/div[4]/div/mat-dialog-container/app-select-pipeline-workflow/div[2]/button[2]"))
				.click();
	}

	public void submitWorkFlowInApplication(JSONObject request) {
		driver.findElement(
				By.xpath("//mat-dialog-container/app-create-workflow-dialog/div[2]/button/span[text()=\" Submit \"]"))
				.click();
		applicationWorkflowNameToBeCreated = request.get("pipelineName").toString() + CommonMethods.getRandomNumber();
		driver.findElement(By.xpath("//input[@name=\"userInput\"]")).sendKeys(applicationWorkflowNameToBeCreated);
		driver.findElement(By.xpath("//form/div[2]/button/span[text()=\" Confirm \"]")).click();
	}

	public static String getapplicationNameCreated(String applicationNameCreated) {
		return applicationNameCreated;
	}

	public void setApplicationNameCreated() {
		ApplicationView.applicationWorkflowNameCreated = applicationWorkflowNameToBeCreated;
	}

	public void trigger_Workflows_In_Application(JSONObject request) throws InterruptedException {
		new WebDriverWait(driver, Duration.ofMinutes(2))
				.until(ExpectedConditions.textToBe(By.xpath("//table/tbody/tr[1]/td[1]"), "Thanu"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(
				"/html/body/app-root/app-app-shell/mat-sidenav-container/mat-sidenav-content/div/perfect-scrollbar/div/div[3]/div")));
		WebElement table = new WebDriverWait(driver, Duration.ofMinutes(2))
				.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//table/tbody"))));
		log.info(table);
		Thread.sleep(1000);
		log.info(driver.findElement(By.xpath("//table/tbody/tr[1]/td[1]")).getText());
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		log.info(rows);
		for (int i = 0; i < rows.size(); i++) {
			List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
			String Workflow = cols.get(0).getText();
			if (Workflow.contentEquals(applicationWorkflowNameCreated)) {
				driver.findElement(By.xpath("//tr[" + (i + 1) + "]/td[2]/button[1]")).click();
				Thread.sleep(10000);
				new WebDriverWait(driver, Duration.ofMinutes(2))
						.until(ExpectedConditions.elementToBeClickable(
								driver.findElement(By.xpath("//mat-card-content/div/div[2]/descendant::div/div"))))
						.click();
				((JavascriptExecutor) driver).executeScript("arguments[0].click();",
						driver.findElement(selectFromDropdown(request.get("workerName1").toString())));
				new WebDriverWait(driver, Duration.ofMinutes(2))
						.until(ExpectedConditions.elementToBeClickable(driver.findElement(triggerPipelineButton)))
						.click();
			}
		}
	}

	public boolean verifyParallelExecution(int stageSize1) throws InterruptedException {
		count = 0;
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(
				"/html/body/app-root/app-app-shell/mat-sidenav-container/mat-sidenav-content/div/perfect-scrollbar/div/div[3]/div")));
		WebElement table = new WebDriverWait(driver, Duration.ofMinutes(2))
				.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//table/tbody"))));
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		for (int i = 0; i < rows.size(); i++) {
			List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
			String Workflow = cols.get(0).getText();
			if (Workflow.contentEquals(applicationWorkflowNameCreated)) {
				Thread.sleep(2000);
				driver.findElement(By.xpath("//tr[" + (i + 1) + "]/td[2]/button[3]")).click();
				Actions ac = new Actions(driver);
				WebElement ele = new WebDriverWait(driver, Duration.ofMinutes(2))
						.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(
								"//app-trigger-history/idp-app-workflow-history/div/mat-icon[text()=\" refresh \"]"))));
				ac.doubleClick(ele).perform();
				Thread.sleep(4000);
				new WebDriverWait(driver, Duration.ofMinutes(2))
						.until(ExpectedConditions.visibilityOf(driver.findElement(
								By.xpath("//table/tbody/tr/td[4]/app-app-workflow-stages/div[" + stageSize1 + "]"))));
				List<WebElement> steps = driver
						.findElements(By.xpath("//table/tbody/tr/td[4]/app-app-workflow-stages/div"));
				log.info(steps.size());
				if (steps.size() == stageSize1) {
					for (int j = 0; j < stageSize1; j++) {
						log.info(driver
								.findElement(
										By.xpath("//table/tbody/tr/td[4]/app-app-workflow-stages/div[" + (j + 1) + "]"))
								.getCssValue("background-color"));
						String[] hexValue = driver
								.findElement(
										By.xpath("//table/tbody/tr/td[4]/app-app-workflow-stages/div[" + (j + 1) + "]"))
								.getCssValue("background-color").replace("rgba(", "").replace(")", "").split(",");
						hexValue[0] = hexValue[0].trim();
						int hexValue1 = Integer.parseInt(hexValue[0]);
						hexValue[1] = hexValue[1].trim();
						int hexValue2 = Integer.parseInt(hexValue[1]);
						hexValue[2] = hexValue[2].trim();
						int hexValue3 = Integer.parseInt(hexValue[2]);
						String actualColor = String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
						log.info(actualColor);
						if (actualColor.contentEquals("#f7941e")) {
							count++;
						}
					}
				}
			}
		}
		log.info(count);
		if (count == 3) {
			statusVerify = true;
		} else {
			statusVerify = false;
		}
		return statusVerify;
	}

	public boolean verifySequentialExecution(int stageSize1, int stageSize2, int stageSize3)
			throws InterruptedException {
		count = 0;
		Thread.sleep(20000);
		new WebDriverWait(driver, Duration.ofMinutes(2)).until(ExpectedConditions.visibilityOf(driver
				.findElement(By.xpath("//table/tbody/tr/td[4]/app-app-workflow-stages/div[" + (stageSize1) + "]"))));
		List<WebElement> steps = driver.findElements(By.xpath("//table/tbody/tr/td[4]/app-app-workflow-stages/div"));
		log.info(steps.size());
		for (int i = 0; i < stageSize1; i++) {
			log.info(driver.findElement(By.xpath("//table/tbody/tr/td[4]/app-app-workflow-stages/div[" + (i + 1) + "]"))
					.getCssValue("background-color"));
			String[] hexValue = driver
					.findElement(By.xpath("//table/tbody/tr/td[4]/app-app-workflow-stages/div[" + (i + 1) + "]"))
					.getCssValue("background-color").replace("rgba(", "").replace(")", "").split(",");
			hexValue[0] = hexValue[0].trim();
			int hexValue1 = Integer.parseInt(hexValue[0]);
			hexValue[1] = hexValue[1].trim();
			int hexValue2 = Integer.parseInt(hexValue[1]);
			hexValue[2] = hexValue[2].trim();
			int hexValue3 = Integer.parseInt(hexValue[2]);
			String actualColor = String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
			log.info(actualColor);
			if (actualColor.contentEquals("#0b8043")) {
				count++;
			}
		}
		log.info(count);
		if (count == stageSize1) {
			for (int i = stageSize1; i < (stageSize1 + stageSize2); i++) {
				log.info(driver
						.findElement(By.xpath("//table/tbody/tr/td[4]/app-app-workflow-stages/div[" + (i + 1) + "]"))
						.getCssValue("background-color"));
				String[] hexValue = driver
						.findElement(By.xpath("//table/tbody/tr/td[4]/app-app-workflow-stages/div[" + (i + 1) + "]"))
						.getCssValue("background-color").replace("rgba(", "").replace(")", "").split(",");
				hexValue[0] = hexValue[0].trim();
				int hexValue1 = Integer.parseInt(hexValue[0]);
				hexValue[1] = hexValue[1].trim();
				int hexValue2 = Integer.parseInt(hexValue[1]);
				hexValue[2] = hexValue[2].trim();
				int hexValue3 = Integer.parseInt(hexValue[2]);
				String actualColor = String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
				log.info(actualColor);
				if (actualColor.contentEquals("#f7941e") || actualColor.contentEquals("#ff3f3f")
						|| actualColor.contentEquals("#0b8043")) {
					count++;
				}
			}

		}
		log.info(count);
		if (count == (stageSize1 + stageSize2)) {
			statusVerify = true;
		} else {
			statusVerify = false;
		}
		return statusVerify;
	}

	public boolean validate_Failed_Execution(int stageSize1, int stageSize2, int stageSize3)
			throws InterruptedException {
		count = 0;
		flag = 0;
		Thread.sleep(40000);
		for (int i = 0; i < (stageSize1 + stageSize2 + stageSize3); i++) {
			log.info(driver.findElement(By.xpath("//table/tbody/tr/td[4]/app-app-workflow-stages/div[" + (i + 1) + "]"))
					.getCssValue("background-color"));
			String[] hexValue = driver
					.findElement(By.xpath("//table/tbody/tr/td[4]/app-app-workflow-stages/div[" + (i + 1) + "]"))
					.getCssValue("background-color").replace("rgba(", "").replace(")", "").split(",");
			hexValue[0] = hexValue[0].trim();
			int hexValue1 = Integer.parseInt(hexValue[0]);
			hexValue[1] = hexValue[1].trim();
			int hexValue2 = Integer.parseInt(hexValue[1]);
			hexValue[2] = hexValue[2].trim();
			int hexValue3 = Integer.parseInt(hexValue[2]);
			String actualColor = String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
			log.info(actualColor);
			if (actualColor.contentEquals("#0b8043")) {
				count++;
			} else if (actualColor.contentEquals("#ff3f3f")) {
				flag = 1;
				break;
			}
		}
		log.info(flag);
		log.info(count);
		if (flag == 1 && (count >= 0 && count < stageSize1)) {
			List<WebElement> steps = driver
					.findElements(By.xpath("//table/tbody/tr/td[4]/app-app-workflow-stages/div"));
			log.info("Workflow in stage1 is failing");
			if (steps.size() == stageSize1) {
				statusVerify = true;
			} else {
				statusVerify = false;
			}
		} else if (flag == 1 && (count >= stageSize1 && count < (stageSize1 + stageSize2))) {
			log.info("Workflow in stage2 is failing");
			List<WebElement> steps = driver
					.findElements(By.xpath("//table/tbody/tr/td[4]/app-app-workflow-stages/div"));
			if (steps.size() == (stageSize1 + stageSize2)) {
				statusVerify = true;
			} else {
				statusVerify = false;
			}
		} else if (flag == 1
				&& (count >= (stageSize1 + stageSize2) && count < (stageSize1 + stageSize2 + stageSize3))) {
			log.info("Workflow in stage2 is failing");
			List<WebElement> steps = driver
					.findElements(By.xpath("//table/tbody/tr/td[4]/app-app-workflow-stages/div"));
			if (steps.size() == (stageSize1 + stageSize2 + stageSize3)) {
				statusVerify = true;
			} else {
				statusVerify = false;
			}
		} else {
			statusVerify = false;
		}
		return statusVerify;
	}

	public String add_Variable(JSONObject request) throws InterruptedException {
		Thread.sleep(3000);
		new WebDriverWait(driver, Duration.ofMinutes(2))
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(applicationViewLeftNav))).click();
		Thread.sleep(10000);
		driver.findElement(filterPipeline).sendKeys(request.get("applicationName").toString());
		driver.findElement(
				By.xpath("//mat-expansion-panel-header[contains(@id,\"mat-expansion-panel-header-\")]/span[1]/div/div"))
				.click();
		Thread.sleep(1000);
		log.info("Application view opened");
		driver.findElement(By
				.xpath("//div/mat-accordion/mat-expansion-panel[1]/mat-expansion-panel-header/span/div/div/button[2]"))
				.click();
		driver.findElement(application_Manage_Tab_Content("Variables")).click();
		driver.findElement(addVariableButton).click();
		String varblName = request.get("Variable Name1").toString() + CommonMethods.getRandomNumber();
		driver.findElement(By.xpath(
				"//input[contains(@id,\"mat-input-\")]/following::span/label/mat-label[text()=\"Variable name\"]/parent::label/parent::span/parent::div/child::input"))
				.sendKeys(varblName);
		driver.findElement(By.xpath("//*[@id=\"mat-slide-toggle-1\"]/label/div/div/div[1]")).click();
		driver.findElement(By.xpath(
				"//input[contains(@id,\"mat-input-\")]/following::span/label/mat-label[text()=\"Default value\"]/parent::label/parent::span/parent::div/child::input"))
				.sendKeys(request.get("Default Value").toString());
		driver.findElement(saveButtonInVariable).click();
		return varblName;
	}

	public String getApplicationMessage() {
		return driver
				.findElement(By.xpath("//*[contains(@id, 'cdk-overlay-')]/snack-bar-container/simple-snack-bar/span"))
				.getAttribute("innerText");
	}

	public boolean openApplicationUsingManage(String applicationName) throws InterruptedException {
		new WebDriverWait(driver, Duration.ofMinutes(2))
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(applicationViewLeftNav))).click();
		new WebDriverWait(driver, Duration.ofMinutes(2)).until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//app-application-summary/div/mat-accordion/mat-expansion-panel[1]"))));
		new WebDriverWait(driver, Duration.ofMinutes(2))
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(manage))).click();
		Thread.sleep(5000);
		log.info("Application view opened");
		Thread.sleep(1000);
		driver.findElement(filterManageApplication).sendKeys(applicationName);
		Thread.sleep(5000);
		new WebDriverWait(driver, Duration.ofMinutes(2))
				.until(ExpectedConditions.elementToBeClickable(
						driver.findElement(By.xpath("//*[text()='" + applicationName + "']/following::td[2]/button"))))
				.click();
		return !driver.findElements(applicationListTableRows).isEmpty();
	}

	public void openApplicationViewOld() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(applicationViewLeftNav)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(manage)).click();
		String portfolioview = driver.findElement(By.xpath("//*[contains(text(),'Application list')]")).getText();
		if (portfolioview.equals("Application list")) {
			log.info("Application view opened");
		} else {
			driver.findElement(applicationViewLeftNav).click();
			Thread.sleep(8000);
			driver.findElement(manage).click();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(createNewApplicationButon)).click();
		log.info("Navigated to Application details page");
	}

	public void openApplicationView() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(applicationViewLeftNav)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(manage)).click();
		String portfolioview = driver.findElement(By.xpath("//*[contains(text(),'Application list')]")).getText();
		if (portfolioview.equals("Application list")) {
			log.info("Application view opened");
		} else {
			driver.findElement(applicationViewLeftNav).click();
			Thread.sleep(8000);
			driver.findElement(settings).click();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(worker)).click();
		log.info("Navigated to Application details page");
	}

	public void createApplication(JSONObject request) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(applicationName)).click();
		applicationNameToBeCreated = request.get("applicationName").toString() + CommonMethods.getRandomNumber();
		log.info(applicationNameToBeCreated);
		driver.findElement(applicationName).sendKeys(applicationNameToBeCreated);
		driver.findElement(portfolioName).click();
		((JavascriptExecutor) driver).executeScript("arguments[0].click()",
				driver.findElement(selectApplicationFromDropdown(request.get("portfolioName").toString())));
		JSONArray ownerslist = (JSONArray) request.get("owners");
		for (int i = 0; i < ownerslist.size(); i++) {
			JSONObject ownername = (JSONObject) ownerslist.get(i);
			driver.findElement(owners).sendKeys(ownername.get("ownerid").toString());
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ENTER).build().perform();
		}
		Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(saveApplication)));
		WebElement element = driver.findElement(saveApplication);
		Actions action = new Actions(driver);
		action.moveToElement(element).doubleClick().build().perform();
		log.info("Application general details are saved");
	}

	public boolean verifyApplicationCreation() throws InterruptedException {
		driver.findElement(back).click();
		Thread.sleep(1000);
		driver.findElement(searchPipeline).sendKeys(applicationNameToBeCreated);
		Thread.sleep(1000);
		return !driver.findElements(applicationListTableRows).isEmpty();
	}

	public void editApplication() throws InterruptedException {
		driver.findElement(applicationViewLeftNav).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(manage)).click();
		Thread.sleep(5000);
		log.info(applicationNameToBeCreated);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchPipeline)).sendKeys(applicationNameToBeCreated);
		Thread.sleep(3000);
		log.info("1");
		if (!driver.findElements(applicationListTableRows).isEmpty()) {
			log.info("2");
			wait.until(ExpectedConditions.visibilityOfElementLocated(editApplication)).click();
		}
	}

	public void logout() throws InterruptedException {
		driver.findElement(By.xpath("//*[contains(text(),'keyboard_arrow_down')]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[contains(text(),' Log out ')]")).click();
	}

	public String verifyApplicationGeneralMandatoryFields(JSONObject request) {
		if (!driver.findElement(saveApplication).isEnabled()) {
			driver.findElement(applicationName).sendKeys("MasterApp");

			if (!driver.findElement(saveApplication).isEnabled()) {
				return "Mandatory";
			} else {
				return "Portfolio Name is not a Mandatory Field";
			}
		} else {
			return "Application Name is not a Mandatory Field";
		}
	}

	public void NavigatePlatformWorker() throws InterruptedException {
		Thread.sleep(5000);
		// System.out.println("Worker");
		wait.until(ExpectedConditions.visibilityOfElementLocated(worker)).click();
	}

	public void platformWorkerPageRefresh() throws InterruptedException {
		Thread.sleep(8000);
		driver.navigate().refresh();
		log.info("After refresh");
		Thread.sleep(9000);
		log.info("Click on Arrow button");
		Thread.sleep(1000);
		log.info("Click on Worker tab");
		driver.findElement(By.xpath("//span[text()='Workers']"));
		doubleClick(driver.findElement(By.xpath("//span[text()='Workers']")));
		log.info("Double click on Worker Tab");
	}

	public void PlatformworkerSelectionInPipelineTrigger(String appAndWorkerName) throws InterruptedException {

		Thread.sleep(6000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text()='Worker Name']"))));
		Thread.sleep(4000);
		WebElement workerName = driver.findElement(By.xpath("//span[text()='Worker Name']"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", workerName);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[starts-with(@id,'mat-input')]")));
		Thread.sleep(4000);
		driver.switchTo().activeElement().sendKeys(appAndWorkerName);
		Thread.sleep(4000);
		WebElement ActiveInstance = driver.findElement(By.xpath("//*[text()='platform:']"));
		Actions action = new Actions(driver);
		action.moveToElement(ActiveInstance).click().build().perform();
		Thread.sleep(4000);
	}

	public void openPortfolioDashboardDetails1(String portfolioName) throws InterruptedException {
		Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(portfolioViewLeftNav)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(portfolioViewLeftNav)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//span[contains(text(),'Dashboard')]"))));
		String portfolioview = driver.findElement(By.xpath("//span[contains(text(),'Dashboard')]")).getText();
		if (portfolioview.equals("Dashboard")) {
			String withoutSpace = portfolioName.replace(" ", "");
			Thread.sleep(4000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search']")))
					.sendKeys(withoutSpace);
			Thread.sleep(4000);

			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[text()=' Automation_Functional_test ']"))).click();
//			wait.until(ExpectedConditions.visibilityOfElementLocated(
//					By.xpath("//*[normalize-space(text())='" + portfolioName + "']//following::span[4]"))).click();
			Thread.sleep(4000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Security']"))).click();

			log.info("portfolio Dashboard opened");
		} else {
			log.info("portfolio Dashboard is not opened");
		}
		Thread.sleep(2000);
	}

}
