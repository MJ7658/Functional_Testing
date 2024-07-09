package org.infy.commonHelpers;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.infy.init.ExcelReader;
import org.infy.uiPages.ApplicationView;
import org.infy.uiPages.PipelineView;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PipelineHelper {

	private WebDriver driver;
	private WebDriverWait wait;
	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(PipelineHelper.class);
	public static String applicationNameToBeCreated;
	public static String applicationWorkflowNameToBeCreated;
	public static String applicationWorkflowNameCreated;
	public int count, flag;
	public static boolean statusVerify;

	public PipelineHelper(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(800));
	}

	By pipelineName = By.cssSelector("input[placeholder= 'Pipeline Name']");
	public By stageName = By.xpath("//*[@name='userInput']");
	public By environment = By.xpath("//*[normalize-space()='Enter Stage configuration']//following::div[16]");
	public By Worker = By.xpath("//*[normalize-space()='Enter Stage configuration']//following::div[26]");
	By moreOptions = By.xpath("//table/tbody/tr[1]/td[3]/button[4]");
	public By editOptions = By.xpath("//table/tbody/tr/td[3]/button[5]");
	By cloneOptions = By.xpath("//tbody/tr/td[3]/button[6]");

	public By searchPluginTextBox = By.cssSelector("input[placeholder= 'Start searching plugins']");

	public By clickOnElement(String fieldName) {
		return By.xpath("//*[contains(text(),'" + fieldName + "')]");
	}

	public By createNewPipelineButon = By.xpath("//button[@mattooltip='Create new Pipeline']");
	public By addStepButton = By.xpath("//span[text()='ADD STEP ']");
	public By tiggerPipelines = By.xpath("//*[normalize-space()='touch_app']");

	By addStepButton_PipelineDetailsEditor = By.xpath("//span[normalize-space()='Add Step']/parent::button");

	By submitPipelineButton_PipelineDetailsEditor(String text) {
		return By.xpath("//*[normalize-space()='" + text + "']/parent::button");
	}

	public By pipelineViewLeftNav = By.xpath("//mat-list-item//span[text()=' Pipeline ']");
	By pipelineViewSideNav = By.xpath("//mat-nav-list/app-mat-menu-list[3]/mat-list-item/div");
	By listViewIcon = By.xpath("//button//mat-icon[normalize-space()='list']");
	By gridViewIcon = By.xpath("//button//mat-icon[normalize-space()='grid_view']");
	By filterPipeline = By.xpath("//mat-label[text()='Search']/parent::label/parent::span/preceding-sibling::input");
	By pipelineListTableRows = By
			.xpath("//*[text()='Pipeline list']/following-sibling::div/descendant::table/tbody/tr");
	By matPipelineContainer = By.xpath("//app-pipeline-editor-container");
	By matCardTitle = By.xpath("//mat-card-title");
	By pageTitle = By.xpath("//div[@class='mat-title']");
	By application = By.cssSelector("mat-select[name='applicationName']");
	By searchApplicationName = By.cssSelector("input[placeholder='Search Applications']");
	public By manage = By.xpath("//mat-button-toggle-group/mat-button-toggle[2]/button");
	By Pipeline = By.xpath("//*[text()=' Pipeline ']");
	By PlatformConfig = By.xpath("//*[text()=' Platform Configuration ']");
	By PlatformTrends = By.xpath("//div[text()='Trends']");
	By closePlatformTrends = By
			.xpath("//button[@class='mat-focus-indicator mat-tooltip-trigger cancel mat-icon-button mat-button-base']");

	By PlatformConigDashboard = By.xpath("//*[text()='Dashboard']");

	By selectFromDropdown(String appName) {
		return By.xpath(String.format(
				"//div[contains(@class,'mat-select-panel')]//mat-option/span[normalize-space()='" + appName + "']",
				appName));
	}

	public void createPipelineName(String appFirstName, String pipelnName) throws InterruptedException, IOException {
		log.info("Checking the user can open the application selection dropdown");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//*[text()=' Select an application && pipeline to start ']"))));
		Thread.sleep(5000);
		log.info("before Select the Applicaiton Name");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='Application Name']"))));
		WebElement appli = driver.findElement(By.xpath("//*[text()='Application Name']"));
		Actions action = new Actions(driver);
		action.moveToElement(appli).doubleClick().build().perform();
		clickOnElement2(appFirstName);
		log.info("before Select the Applicaiton Name");
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(pipelineName))).sendKeys(pipelnName);
	}

	public void editPipelineName(String pipelnName) throws InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
		Thread.sleep(5000);
		driver.switchTo().activeElement().sendKeys(pipelnName);
		Thread.sleep(3000);
		WebElement element = driver.findElement(By.xpath("//span[text()='Submit']"));
		JavascriptExecutor execu = (JavascriptExecutor) driver;
		execu.executeScript("arguments[0].click();", element);
	}

	public String approverVerification()
			throws IOException, HeadlessException, UnsupportedFlavorException, InterruptedException {
		Thread.sleep(8000);
		driver.navigate().refresh();
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("notificationIcon")));

		WebElement Notifications = driver.findElement(By.id("notificationIcon"));
		Actions action = new Actions(driver);
		action.moveToElement(Notifications).doubleClick().build().perform();
		Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//mat-row[1]//mat-cell[3]"))));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//mat-row[1]//mat-cell[4]"))));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//mat-row[1]//mat-cell[5]"))));

		String TriggeredBy = driver.findElement(By.xpath("//mat-row[1]//mat-cell[3]")).getText();
		String FromStage = driver.findElement(By.xpath("//mat-row[1]//mat-cell[4]")).getText();
		String ToStage = driver.findElement(By.xpath("//mat-row[1]//mat-cell[5]")).getText();

		String Approver = "Approved By: " + TriggeredBy + " (" + FromStage + ") -> (" + ToStage + ")";

		wait.until(
				ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//mat-row[1]//mat-cell[6]//mat-icon"))));
		WebElement approveButton = driver.findElement(By.xpath("//mat-row[1]//mat-cell[6]//mat-icon"));
		action.moveToElement(approveButton).doubleClick().build().perform();

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()=' content_copy ']"))))
				.click();
		String stageApproveDetails = (String) Toolkit.getDefaultToolkit().getSystemClipboard()
				.getData(DataFlavor.stringFlavor);

		wait.until(
				ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@placeholder='Enter comment here']"))))
				.sendKeys("Approved");

		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//*[@placeholder='<To Stage> (<To Env>) / <To Step>']"))))
				.sendKeys(stageApproveDetails);

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()=' Approve ']")))).click();

		return Approver;
	}

	public String approverVerification(String request)
			throws IOException, HeadlessException, UnsupportedFlavorException, InterruptedException {
		Thread.sleep(8000);
		driver.navigate().refresh();
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("notificationIcon")));

		WebElement Notifications = driver.findElement(By.id("notificationIcon"));
		Actions action = new Actions(driver);
		action.moveToElement(Notifications).doubleClick().build().perform();
		Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//mat-row[1]//mat-cell[3]"))));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//mat-row[1]//mat-cell[4]"))));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//mat-row[1]//mat-cell[5]"))));

		String TriggeredBy = driver.findElement(By.xpath("//mat-row[1]//mat-cell[3]")).getText();
		String FromStage = driver.findElement(By.xpath("//mat-row[1]//mat-cell[4]")).getText();
		String ToStage = driver.findElement(By.xpath("//mat-row[1]//mat-cell[5]")).getText();

		String Approver = "Approved By: " + TriggeredBy + " (" + FromStage + ") -> (" + ToStage + ")";

		wait.until(
				ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//mat-row[1]//mat-cell[6]//mat-icon"))));
		WebElement approveButton = driver.findElement(By.xpath("//mat-row[1]//mat-cell[6]//mat-icon"));
		action.moveToElement(approveButton).doubleClick().build().perform();

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()=' content_copy ']"))))
				.click();
		String stageApproveDetails = (String) Toolkit.getDefaultToolkit().getSystemClipboard()
				.getData(DataFlavor.stringFlavor);

		wait.until(
				ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@placeholder='Enter comment here']"))))
				.sendKeys(request);

		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//*[@placeholder='<To Stage> (<To Env>) / <To Step>']"))))
				.sendKeys(stageApproveDetails);
		String name = "Approved";
		if (name.equalsIgnoreCase(request))
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()=' Approve ']"))))
					.click();
		else {
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()=' Reject ']")))).click();
		}
		return Approver;
	}
	

	public void clickOnElement1(String value) throws InterruptedException {
		List<WebElement> list = driver.findElements(By.xpath("//*[@class='mdc-list-item__primary-text']"));
		for (WebElement webElement : list) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
			if (webElement.getText().equalsIgnoreCase(value)) {
				wait.until(ExpectedConditions.elementToBeClickable(webElement));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
				break;
			}
		}
	}

	public void clickOnElement11(String value) throws InterruptedException {
		List<WebElement> list = driver.findElements(By.xpath("//*[@class='mdc-list-item__primary-text']"));
		for (WebElement webElement : list) {
			if (webElement.getText().equalsIgnoreCase(value)) {
				wait.until(ExpectedConditions.elementToBeClickable(webElement));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
				break;
			}
		}
	}

	public void createParameter(String variableName, String defaultName) throws InterruptedException {
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath("//*[starts-with(text(),' Dashboard')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Parameters ']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Add Parameter']"))).click();
		Thread.sleep(2000);
		driver.switchTo().activeElement().sendKeys(variableName + Keys.TAB + Keys.TAB + Keys.TAB);
		Thread.sleep(2000);
		driver.switchTo().activeElement().sendKeys(defaultName);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Save ']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Configuration']"))).click();
	}

	public void navToParametersAndClickONAdd() throws InterruptedException {
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath("//*[starts-with(text(),' Dashboard')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Parameters ']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Add Parameter']"))).click();
	}

	public void clickOnAddParameter() throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Add Parameter']"))).click();
	}

	public void addProxyParameterfor480VM() throws InterruptedException {
		String token = "aHR0cDovL3NhdGhlc2hrdW1hci5tOjA5QFRhc2tvdmVyQDEwLjY4LjI0OC4zOTo4MA==";
		String Defaultname = "http://blrproxy.ad.infosys.com:443";
		String Defaultname1 = ".ad.infosys.com";
		String variableName = "HTTP_PROXY";
		navToParametersAndClickONAdd();
		paramterVariableNameCreation(variableName);
		paramterDefaultNameCreation(Defaultname);
		clickOnSecureButton();
		parameterSaveButtonClick();

		String variableName1 = "HTTPS_PROXY";
		clickOnAddParameter();
		paramterVariableNameCreation(variableName1);
		paramterDefaultNameCreation(Defaultname);
		clickOnSecureButton();
		parameterSaveButtonClick();

		String variableName2 = "http_proxy";
		clickOnAddParameter();
		paramterVariableNameCreation(variableName2);
		paramterDefaultNameCreation(Defaultname);
		clickOnSecureButton();
		parameterSaveButtonClick();

		String variableName3 = "https_proxy";
		clickOnAddParameter();
		paramterVariableNameCreation(variableName3);
		paramterDefaultNameCreation(Defaultname);
		clickOnSecureButton();
		parameterSaveButtonClick();

		String Default = ".ad.infosys.com,.infosys.com,localhost,127.0.0.1,0.0.0.0,10.0.0.0/8,192.168.10.0/24";
		String variableName4 = "no_proxy";
		clickOnAddParameter();
		paramterVariableNameCreation(variableName4);
		paramterDefaultNameCreation(Default);
		parameterSaveButtonClick();
		parametertoConfigurationNav();
	}

	public void addParameter(String variableName, String Defaultname) throws InterruptedException {
		navToParametersAndClickONAdd();
		paramterVariableNameCreation(variableName);
		paramterDefaultNameCreation(Defaultname);
		parameterSaveButtonClick();
		parametertoConfigurationNav();
	}

	public void addParameterWithTrigger(String variableName, String Defaultname) throws InterruptedException {
		Thread.sleep(2000);
		navToParametersAndClickONAdd();
		paramterVariableNameCreation(variableName);
		triggerableClick();
		paramterDefaultNameCreation(Defaultname);
		parameterSaveButtonClick();
		parametertoConfigurationNav();
	}

	public void triggerableClick() throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//*[text()='Triggerable']//following::div[1][@class='mdc-form-field']")))
				.click();
	}

	public void MulitiChoiceeClick() throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//*[text()='Multiple choice']//following::div[1][@class='mdc-form-field']")))
				.click();
	}

	public void paramterVariableNameCreation(String VariableName) throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[text()='Add Variable']//following::div[5]"))).click();
		driver.switchTo().activeElement().sendKeys(VariableName);
	}

	public void paramterDefaultNameCreation(String DefaultName) throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[text()='Add Variable']//following::input[2]"))).click();
		driver.switchTo().activeElement().sendKeys(DefaultName);
	}

	public void parameterEnvCreation(String envname, String value) throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[text()='Environment']//following::mat-select[1]"))).click();
		clickOnElement1(envname);

		WebElement element = driver.findElement(By.xpath("//*[text()=' add ']//preceding::input[1]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);

		Thread.sleep(5000);
		driver.switchTo().activeElement().sendKeys(value);

		WebElement element1 = driver.findElement(By.xpath("//span[text()=' add ']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", element1);
	}

	public void parameterSaveButtonClick() throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Save ']"))).click();
	}

	public boolean triggerButtonVerifyCation() throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[@aria-checked='false']")));
		WebElement element = driver.findElement(By.xpath("//*[text()='Secret']//following::button[2]"));
		boolean flag;
		if (element.isEnabled() == false) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	public boolean verifyAddedParameters(String username, String verfiyValues) throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[normalize-space(text())='" + username + "']//following::td[1]")));
		String value = driver.findElement(By.xpath("//*[normalize-space(text())='" + username + "']//following::td[1]"))
				.getText();
		boolean flag;
		if (value.equalsIgnoreCase(verfiyValues)) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	public boolean triggerPageParameterVerfiy(String verfiyValues)
			throws InterruptedException, AWTException, HeadlessException, UnsupportedFlavorException, IOException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[text()='Pipeline Parameters']//following::input"))).click();
		WebElement value = driver.findElement(By.xpath("//*[text()='Pipeline Parameters']//following::input"));
		Actions action = new Actions(driver);
		action.doubleClick(value).build().perform();
		value.sendKeys(Keys.CONTROL, "c");
		Thread.sleep(2000);
		String myAgentpath = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
		log.info(myAgentpath);
		boolean flag;
		if (myAgentpath.equalsIgnoreCase(verfiyValues)) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	public void deleteParameter(String username) throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[normalize-space(text())='" + username + "']//following::td[3]//button[2]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Yes']"))).click();
	}

	public void editParameter(String username) throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[normalize-space(text())='" + username + "']//following::td[3]//button[1]"))).click();
	}

	public boolean verifyPipelineParameter() throws InterruptedException {
		Thread.sleep(2000);
		String defaultvalue = wait
				.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//*[text()='Update Variable']//following::input[5]")))
				.getAttribute("value");

		String envValue = wait
				.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//th[text()='Environment']//following::input[1]")))
				.getAttribute("value");
		boolean flag;
		if (defaultvalue.contains("SECRET:") && envValue.contains("SECRET:")) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;

	}

	public Boolean parameterEditpopupDefaultVerfy() throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//input[starts-with(@id,'mat-input')]//following::input[2]")));
		String enType = driver.findElement(By.xpath("//input[starts-with(@id,'mat-input')]//following::input[2]"))
				.getAttribute("type");

		Boolean flag;
		if (enType.equalsIgnoreCase("password")) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	public Boolean parameterEditpopupEnveVerfy() throws InterruptedException, AWTException {
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		Thread.sleep(3000);

		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//input[starts-with(@id,'mat-input')]//following::input[2]")));
		String enType = driver.findElement(By.xpath("//input[starts-with(@id,'mat-input')]//following::input[2]"))
				.getAttribute("type");

		Boolean flag;
		if (enType.equalsIgnoreCase("password")) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	public void parametertoConfigurationNav() throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Configuration']"))).click();
	}

	public void clickOnSecureButton() throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//*[text()='Secret']//following::div[1][@class='mdc-form-field']")))
				.click();
	}

	public void clickOnTriggerButton() throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//*[text()='Triggerable']//following::span[1][@class='mat-slide-toggle-bar mat-slide-toggle-bar-no-side-margin']")))
				.click();
	}

	public void editParameter(String variableName, String defaultName) throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Parameters ']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[normalize-space(text())='" + variableName + "']//following::td[3]//button[1]"))).click();
		Thread.sleep(2000);
		driver.switchTo().activeElement().sendKeys(Keys.TAB, Keys.TAB, Keys.BACK_SPACE);
		Thread.sleep(2000);
		driver.switchTo().activeElement().sendKeys(defaultName);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Save ']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Configuration']"))).click();
	}

	public void openPipelineView() throws InterruptedException {
		Thread.sleep(4000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
		new WebDriverWait(driver, Duration.ofSeconds(3))
				.until(ExpectedConditions.visibilityOf(driver.findElement(pipelineViewLeftNav))).click();
		Thread.sleep(4000);

		((JavascriptExecutor) driver).executeScript("arguments[0].click()", driver.findElement(manage));
		Thread.sleep(4000);
		log.info("Pipeline view opened");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));

	}

	public void navigateToPipeline() throws InterruptedException {
		log.info("Checking pipelinepage visible or not");
		wait.until(ExpectedConditions.visibilityOfElementLocated(Pipeline));
		log.info("before click pipeline page");
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", driver.findElement(Pipeline));
		log.info("Navigated to pipeline view home page successfully");
	}

	public void navigateToPlatformConfig() throws InterruptedException {
		log.info("navigateToPlatformConfig");
		wait.until(ExpectedConditions.visibilityOfElementLocated(PlatformConfig));
		log.info("navigateToPlatformConfig");
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", driver.findElement(PlatformConfig));
		log.info("Navigated to navigateToPlatformConfig successfully");
	}

	public void navigateToPlatformTrends() throws InterruptedException {
		log.info("navigateToPlatformTrends");
		wait.until(ExpectedConditions.visibilityOfElementLocated(PlatformTrends));
		log.info("navigateToPlatformTrends");
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", driver.findElement(PlatformTrends));
		log.info("Navigated to navigateToPlatformTrends successfully");
	}

	public void closePlatformTrends() throws InterruptedException {
		log.info("closePlatformTrends");
		wait.until(ExpectedConditions.visibilityOfElementLocated(closePlatformTrends));
		log.info("closePlatformTrends");
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", driver.findElement(closePlatformTrends));
	}

	public Boolean verify_infoIcon_Details(String vulnerabililtyName, String expectedText) throws InterruptedException {
		System.out.println("verify_infoIcon_Details ====> " + vulnerabililtyName);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[normalize-space(text())='" + vulnerabililtyName + "']//following::mat-icon[1]")));
		Thread.sleep(5000);
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		Thread.sleep(5000);
		WebElement tooltip = driver.findElement(By.className("widget-tooltip"));
		String text = tooltip.getText();
		Boolean flag;
		if (text.contains(expectedText)) {
			flag = true;
		} else {
			flag = false;
			System.out.println("Unable to fetch_infoIcon_Details of Actual ====> " + text);
			System.out.println("Unable to fetch_infoIcon_Details of Expected ====> " + expectedText);
		}
		Thread.sleep(7000);
		return flag;
	}

	public Boolean verify_Treads_infoIcon_Details(String vulnerabililtyName, String expectedText)
			throws InterruptedException {
		System.out.println("verify_infoIcon_Details ====> " + vulnerabililtyName);

		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[normalize-space(text())='" + vulnerabililtyName + "']//following::mat-icon[1]")));
		Thread.sleep(4000);
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		Thread.sleep(5000);
		WebElement tooltip = driver.findElement(By.className("widget-tooltip"));
		String text = tooltip.getText();

		Boolean flag;
		if (text.contains(expectedText)) {
			flag = true;
		} else {
			flag = false;
			System.out.println("Unable to fetch_infoIcon_Details of Actual ====> " + text);
			System.out.println("Unable to fetch_infoIcon_Details of Expected ====> " + expectedText);
		}
		Thread.sleep(4000);
		return flag;
	}

	public void navigateToPlatformConfig_Dashboard() throws InterruptedException {
		log.info("navigateToPlatformConfig_Dashboard");
		wait.until(ExpectedConditions.visibilityOfElementLocated(PlatformConigDashboard));
		log.info("navigateToPlatformConfig_Dashboard");
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", driver.findElement(PlatformConigDashboard));
		log.info("Navigated to navigateToPlatformConfig successfully");
		Thread.sleep(5000);
	}

	public void navigateToPlatformConfig_Graph_Click(String nameofGraph) throws InterruptedException {
		log.info("navigateToPlatformConfig_Graph_Click");
		Thread.sleep(10000);
		WebElement ele = driver.findElement(By.xpath("//*[normalize-space(text())='" + nameofGraph + "']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		driver.findElement(By.xpath("//*[normalize-space(text())='" + nameofGraph + "']")).click();
	}

	public Boolean navigateToPlatformVerifycation() throws InterruptedException {
		Boolean flag;
		try {
			wait.until(ExpectedConditions
					.visibilityOf(driver.findElement(By.xpath("//*[normalize-space(text())='Permission Denied']"))));
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	@FindBy(xpath = "//*[text()=' Portfolio ']'")
	WebElement portfolioNav;

	public void navigateToPortfolio() {
		wait.until(ExpectedConditions.visibilityOf(portfolioNav));
		wait.until(ExpectedConditions.elementToBeClickable(portfolioNav)).click();
		log.info("Navigated to Portfolio view home page successfully");
	}

	@FindBy(xpath = "//*[starts-with(@class,'mat-ripple mat-tab-header-pagination mat-tab-header-pagination-after mat-elevation')]")
	WebElement arrowButton;

	@FindBy(xpath = "//*[text()='Pipeline Templates']")
	WebElement pipelineTemplatesTab;

	public void navToPipelineTemplates() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(arrowButton));
		wait.until(ExpectedConditions.elementToBeClickable(arrowButton)).click();
		Thread.sleep(2000);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", arrowButton);
		wait.until(ExpectedConditions.visibilityOf(pipelineTemplatesTab)).click();
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", pipelineTemplatesTab);
		log.info("Navigated to pipelineTemplates details page");
	}

	public void editPipelineTemplates(String templateName, String StageName) throws InterruptedException {
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[text()='" + templateName + "']//following::button[2]")))
				.click();
		Thread.sleep(4000);
		WebElement el = driver
				.findElement(By.xpath("//*[normalize-space(text())='" + StageName + "']//following::div[3]"));
		Actions action = new Actions(driver);
		action.moveToElement(el).doubleClick().build().perform();
		cmdPluglessTimeoutja1(StageName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='save']"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=' Update ']"))).click();
	}

	@FindBy(xpath = "//*[text()='Create New Template']")
	WebElement addnewTemplate;

	public void createNewTemplate() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(addnewTemplate));
		wait.until(ExpectedConditions.elementToBeClickable(addnewTemplate)).click();
		Thread.sleep(2000);
	}

	@FindBy(xpath = "//span[normalize-space()='queue']/parent::button")
	WebElement saveTemplate;

	@FindBy(xpath = "//*[@name='templateName']")
	WebElement templateNames;

	@FindBy(xpath = "//*[text()=' Save as portfolio level Template ']")
	WebElement savePortfoliolevlTemp;

	@FindBy(xpath = "//*[text()=' Confirm ']")
	WebElement confirmButton;

	public void saveTemplate(String TemaplateName) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(saveTemplate)).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(savePortfoliolevlTemp)).click();
		wait.until(ExpectedConditions.visibilityOf(templateNames)).sendKeys(TemaplateName);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(confirmButton)).click();
		Thread.sleep(8000);
	}

	public void addPiplineTemplate() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@class='mat-focus-indicator mat-mini-fab mat-button-base mat-accent ng-star-inserted']")))
				.click();
		log.info("Navigated to Template page");
		Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Portfolio Templates']")))
				.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@placeholder='Search']"))).click();
		Thread.sleep(2000);
		driver.switchTo().activeElement().sendKeys("PIPELINETEMPLETE");
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='pipelineTemplete']"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=' Select ']"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Yes']"))).click();
		log.info("Pipeline Template added");
	}

	public void addPiplineTemplateWithName(String TemplateName) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@class='mat-focus-indicator mat-mini-fab mat-button-base mat-accent ng-star-inserted']")))
				.click();
		log.info("Navigated to Template page");
		Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Portfolio Templates']")))
				.click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[text()='Portfolio Templates']//following::input[1]")))
				.sendKeys(TemplateName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='" + TemplateName + "']")))
				.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=' Select ']"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Yes']"))).click();
		log.info("Pipeline Template added");
	}

	public void navigateToVisualEditor() throws InterruptedException {
		openPipelineView();
		Thread.sleep(4000);
		log.info("before click createNewPipelineButon");
		driver.findElement(createNewPipelineButon).click();
		log.info("createNewPipeline page");
	}

	public void editPlugin(String StageName, String plugin, String parameter) throws InterruptedException {
		PipelineView pv = new PipelineView(driver);
		pv.expandParticulrPluginDetails(StageName, plugin);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[text()='Command']//following::input[1]")).sendKeys(parameter);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public boolean verifyEditPluginParameter(String StageName, String plugin, String parameter)
			throws InterruptedException {
		Thread.sleep(2000);
		PipelineView pv = new PipelineView(driver);
		pv.expandParticulrPluginDetails(StageName, plugin);
		Thread.sleep(3000);
		WebElement element = driver.findElement(By.xpath("//*[text()='Command']//following::input[1]"));
		boolean flag;
		if (element.getText() == parameter) {
			flag = true;
		} else {
			flag = false;
		}
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
		return flag;
	}

	public static void setDropDownValue(WebDriver driver, WebElement Elementtobeselected)
			throws InterruptedException, IOException {
		Actions keyDown = new Actions(driver);
		keyDown.sendKeys(Keys.chord(Keys.DOWN, Keys.DOWN)).perform();
		Elementtobeselected.click();
	}

	public void addStageInPipeline(String stage, String EnvName, String WorkerName)
			throws InterruptedException, IOException {
		log.info("add StageName Page");
		ExcelReader read = new ExcelReader();
		Thread.sleep(3000);
		WebElement element1 = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(3000);
		WebElement element2 = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' ADD STAGE ']")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element2);
		element2.click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(stageName))).sendKeys(stage);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(environment)).click();
		clickOnElement1(EnvName);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(Worker));
		WebElement element = driver.findElement(Worker);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
		clickOnElement1(WorkerName);
		wait.until(ExpectedConditions.elementToBeClickable(clickOnElement("Confirm"))).click();
		log.info("after adding the stage");
	}

	public void addStageInPipeline1(String stage, String EnvName) throws InterruptedException, IOException {
		ExcelReader read = new ExcelReader();
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' ADD STAGE ']"))).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(stageName))).sendKeys(stage);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(environment)).click();
		clickOnElement1(EnvName);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(clickOnElement("Confirm"))).click();
	}

	public void addStageInPipelineWithoutEnv(String stage, String WorkerName) throws InterruptedException, IOException {
		ExcelReader read = new ExcelReader();
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' ADD STAGE ']"))).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(stageName))).sendKeys(stage);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(environment)).click();
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(Worker));
		WebElement element = driver.findElement(Worker);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
		clickOnElement1(WorkerName);
		wait.until(ExpectedConditions.elementToBeClickable(clickOnElement("Confirm"))).click();
	}

	public void addStageInPipelineWithoutEnv_Worker(String stage) throws InterruptedException, IOException {
		ExcelReader read = new ExcelReader();
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' ADD STAGE ']"))).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(stageName))).sendKeys(stage);
		wait.until(ExpectedConditions.elementToBeClickable(clickOnElement("Confirm"))).click();
	}

	public void cmdplug() {
		PipelineView pv = new PipelineView(driver);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(pv.searchPluginTextBox))).sendKeys("cmdexec");
		wait.until(ExpectedConditions.elementToBeClickable(pv.selectPlugin("cmdexec"))).click();
		pv.expandPluginDetails("cmdexec");
		driver.findElement(By.xpath("//*[text()='Command']//following::input[1]")).sendKeys("java-version");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void navworkflow() throws InterruptedException {
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath("//*[starts-with(text(),' Dashboard')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Workflows ']"))).click();
	}

	public void addworkFlow() throws InterruptedException {
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath("//*[starts-with(text(),' Dashboard')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Add Workflow']"))).click();
	}

	public void clickOnSaveAs(String workflowName) throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Save As ']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@name='userInput']"))).sendKeys(workflowName);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[normalize-space(text())='Confirm']"))).click();
	}

	public void editWorkflow(String workflowName) throws InterruptedException {
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[text()='Workflow List']//following::div[3]"))).click();
		clickOnElement1(workflowName);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Edit']"))).click();
		Thread.sleep(3000);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Save ']"))).click();
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Yes']"))).click();

	}

	public void deleteWorkflow() throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Delete']"))).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Yes']"))).click();

	}

	public void navConfiguration() throws InterruptedException {
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath("//*[starts-with(text(),' Dashboard')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Configuration']"))).click();
	}

	public boolean workflowVerifyCation(List<String> workflowList) throws InterruptedException {
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		List<WebElement> element = driver
				.findElements(By.xpath("//*[@class='mat-mdc-tooltip-trigger mat-caption cursor-pointer stage-rect-text']"));
		ArrayList<String> firstlist = new ArrayList<String>();
		ArrayList<String> secondList = new ArrayList<String>();
		secondList.addAll(workflowList);
		for (WebElement webElement : element) {
			String tex = webElement.getText();
			firstlist.add(tex);
		}
		boolean boolval = firstlist.equals(secondList);

		return boolval;
	}

	@FindBy(xpath = "//*[@class='mat-tooltip-trigger cursor-pointer stage-rect-text ng-star-inserted'")
	List<WebElement> removeStage;

	public void removeStageDetailsInWorkflow() throws InterruptedException {
		WebElement element1 = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		List<WebElement> element = driver.findElements(
				By.xpath("//*[@class='mat-mdc-tooltip-trigger cursor-pointer stage-rect-text ng-star-inserted']"));
		int num = 0;
		for (int i = 0; i < element.size(); i++) {
			if (i <= 0) {
				element.get(i).click();
			}
		}
	}

	public void addParticularStage(String StageDetails) throws InterruptedException {
		Thread.sleep(2000);

		WebElement element1 = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		log.info("//*[normalize-space(text())='" + StageDetails + "']//following::mat-icon[1]");

		wait.until(ExpectedConditions.elementToBeClickable(driver
				.findElement(By.xpath("//*[normalize-space(text())='" + StageDetails + "']//following::mat-icon[1]"))))
				.click();
	}

	public int zoomINandZoomOut(int height) throws InterruptedException {
		Thread.sleep(2000);
		WebElement slider = driver.findElement(By.xpath("//*[@class='mdc-slider__track']"));
		Actions action = new Actions(driver);
		action.dragAndDropBy(slider, 0, height);
		action.release().build().perform();
		WebElement cssValue = driver
				.findElement(By.xpath("//*[@class='mat-mdc-tooltip-trigger cursor-pointer stage-rect-text ng-star-inserted']"));
		Dimension dim = cssValue.getSize();
		log.info(dim.height);
		System.out.println("Zoomed element height: " + dim.height);
		return dim.height;
	}

	public void addasStageRadioButton() throws InterruptedException {
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Add as First Stage ']")))).click();
	}

	public boolean stagePositionPOPUP1(String StageDetails) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(
				driver.findElement(By.xpath("//*[text()=' Select Stage Position in workflow ']//following::div[13]"))))
				.click();
		Thread.sleep(2000);
		boolean flag;
		try {
			log.info("//*[normalize-space(text())='" + StageDetails + "'] //following::mat-option");
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(
					By.xpath("//*[normalize-space(text())='" + StageDetails + "'] //following::mat-option"))));
			WebElement drop = driver.findElement(
					By.xpath("//*[normalize-space(text())='" + StageDetails + "']//following::mat-option"));
			Actions action = new Actions(driver);
			action.moveToElement(drop).click().build().perform();
			flag = true;
		} catch (NoSuchElementException e) {
			flag = false;
		}
		return flag;
	}

	public boolean stagePositionPOPUP(String StageDetails) throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(
				driver.findElement(By.xpath("//*[text()=' Select Stage Position in workflow ']//following::div[13]"))))
				.click();
		Thread.sleep(2000);
		boolean flag;

		try {
			Boolean value = driver
					.findElement(
							By.xpath("//*[normalize-space(text())='" + StageDetails + "'] //following::mat-option"))
					.isDisplayed();
			driver.findElement(By.xpath("//*[normalize-space(text())='" + StageDetails + "']//following::mat-option"))
					.click();
			flag = true;
		} catch (NoSuchElementException e) {
			flag = false;
		}
		return flag;
	}

	public boolean stagePositionPOPUPVerifyTrue(String StageDetails) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(
				driver.findElement(By.xpath("//*[text()=' Select Stage Position in workflow ']//following::span[2]"))))
				.click();
		Thread.sleep(2000);
		boolean flag;
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//*[@class='mdc-list-item__primary-text']"))));
		String value = driver.findElement(By.xpath("//*[@class='mdc-list-item__primary-text']")).getText();
		log.info(value);
		if (value.equalsIgnoreCase(StageDetails)) {
			driver.findElement(By.xpath("//*[@class='mdc-list-item__primary-text']")).click();
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	public boolean stagePositionPOPUPVerifyTrueMultiple(String StageDetails) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(
				driver.findElement(By.xpath("//*[text()=' Select Stage Position in workflow ']//following::span[2]"))))
				.click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.visibilityOfAllElements(driver.findElements(By.xpath("//*[@class='mdc-list-item__primary-text']"))));
		boolean flag = false;
		List<WebElement> el = driver.findElements(By.xpath("//*[@class='mdc-list-item__primary-text']"));
		for (WebElement webElement : el) {

			log.info(webElement.getText());
			if (webElement.getText().equalsIgnoreCase(StageDetails)) {
				webElement.click();
				flag = true;
			} else {
				flag = false;
			}
		}

		return flag;
	}

	public void addSequenceRadioButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' In Sequence ']"))))
				.click();
	}

	public void addParallelRadioButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' In Parallel ']"))))
				.click();
	}

	public boolean stagePositionPOPUPVerifyFalse(String StageDetails) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(
				driver.findElement(By.xpath("//*[text()=' Select Stage Position in workflow ']//following::span[2]"))))
				.click();

		boolean flag;
		log.info("//*[normalize-space(text())='" + StageDetails + "'] //following::mat-option");
		String value = driver.findElement(By.xpath("//*[@class='mdc-list-item__primary-text']")).getText();
		if (value.equalsIgnoreCase(StageDetails)) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	public void addStagePopupe() throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Add Stage ']"))))
				.click();
	}

	public void escopePopup() throws InterruptedException, AWTException {
		Thread.sleep(2000);
		Robot ro = new Robot();
		ro.keyPress(KeyEvent.VK_ESCAPE);
		ro.keyRelease(KeyEvent.VK_ESCAPE);
		ro.keyPress(KeyEvent.VK_ESCAPE);
		ro.keyRelease(KeyEvent.VK_ESCAPE);
	}

	public void cancelButton() throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='Cancel']"))));
		WebElement cancelButton = driver.findElement(By.xpath("//*[text()='Cancel']"));
		Actions action = new Actions(driver);
		action.moveToElement(cancelButton).doubleClick().build().perform();
	}

	public void cmdPlugTimeout() throws InterruptedException {
		PipelineView pv = new PipelineView(driver);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(pv.searchPluginTextBox))).sendKeys("cmdexec");
		wait.until(ExpectedConditions.elementToBeClickable(pv.selectPlugin("cmdexec"))).click();
		pv.expandPluginDetails("cmdexec");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[text()='Command']//following::input[1]"))
				.sendKeys("waitfor SomethingThatIsNeverHappening /t 100");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void cmdPluglessTimeout() throws InterruptedException {
		PipelineView pv = new PipelineView(driver);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(pv.searchPluginTextBox))).sendKeys("cmdexec");
		wait.until(ExpectedConditions.elementToBeClickable(pv.selectPlugin("cmdexec"))).click();
		pv.expandPluginDetails("cmdexec");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[text()='Command']//following::input[1]"))
				.sendKeys("waitfor SomethingThatIsNeverHappening /t 40");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void cmdPluglessDynamicTimeOut(int timeDetails) throws InterruptedException {
		PipelineView pv = new PipelineView(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(addStepButton)).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(pv.searchPluginTextBox))).sendKeys("cmdexec");
		wait.until(ExpectedConditions.elementToBeClickable(pv.selectPlugin("cmdexec"))).click();
		pv.expandPluginDetails("cmdexec");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[text()='Command']//following::input[1]"))
				.sendKeys("waitfor SomethingThatIsNeverHappening /t " + timeDetails + "");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void cmdPluglessTimeout1() throws InterruptedException {
		PipelineView pv = new PipelineView(driver);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(pv.searchPluginTextBox))).sendKeys("cmdexec");
		wait.until(ExpectedConditions.elementToBeClickable(pv.selectPlugin("cmdexec"))).click();
		pv.expandPluginDetails("cmdexec");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[text()='Command']//following::input[1]"))
				.sendKeys("waitfor SomethingThatIsNeverHappening /t 45");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void cmdPlugfaildPluginlessTimeout() throws InterruptedException {
		PipelineView pv = new PipelineView(driver);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(pv.searchPluginTextBox))).sendKeys("cmdexec");
		wait.until(ExpectedConditions.elementToBeClickable(pv.selectPlugin("cmdexec"))).click();
		pv.expandPluginDetails("cmdexec");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[text()='Command']//following::input[1]"))
				.sendKeys("waitfor SomethingThatIsNeverHappening /t 8");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void removeStage() throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' stage1 ']//following::mat-icon[1]"))));
		WebElement element = driver.findElement(By.xpath("//*[text()=' stage1 ']//following::mat-icon[1]"));
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		Thread.sleep(4000);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' stage1 ']//following::mat-icon[1]"))))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()='Yes']")))).click();
	}

	public void paraMeterTestStep() throws InterruptedException, AWTException {
		PipelineView pv = new PipelineView(driver);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(pv.searchPluginTextBox))).sendKeys("cmdexec");
		wait.until(ExpectedConditions.elementToBeClickable(pv.selectPlugin("cmdexec"))).click();
		Thread.sleep(2000);
		pv.expandPluginDetails("cmdexec");
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//*[text()='Command']//following::input[1]"))));
		WebElement inputbox = driver.findElement(By.xpath("//*[text()='Command']//following::input[1]"));
		inputbox.sendKeys("${");
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//td[text()='Username1']//following::button[1]")))).click();
		Thread.sleep(2000);
		inputbox.click();
		Robot ro = new Robot();
		for (int i = 0; i <= 11; i++) {
			Thread.sleep(1000);
			inputbox.sendKeys(Keys.LEFT);
		}
		Thread.sleep(4000);
		driver.switchTo().activeElement().sendKeys("cmd /c echo ");
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void dynamicParameterTestStep() throws InterruptedException, AWTException {
		PipelineView pv = new PipelineView(driver);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(pv.searchPluginTextBox))).sendKeys("cmdexec");
		wait.until(ExpectedConditions.elementToBeClickable(pv.selectPlugin("cmdexec"))).click();
		Thread.sleep(2000);
		pv.expandPluginDetails("cmdexec");
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//*[text()='Command']//following::input[1]"))));
		WebElement inputbox = driver.findElement(By.xpath("//*[text()='Command']//following::input[1]"));
		inputbox.sendKeys("${");
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//td[text()='Username1']//following::button[1]")))).click();
		Thread.sleep(2000);
		inputbox.click();
		Robot ro = new Robot();
		for (int i = 0; i <= 11; i++) {
			Thread.sleep(1000);
			inputbox.sendKeys(Keys.LEFT);
		}
		Thread.sleep(4000);
		driver.switchTo().activeElement().sendKeys("cmd /c echo ");
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void cmdPluglessTimeoutja(String Stage) throws InterruptedException {
		PipelineView pv = new PipelineView(driver);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(pv.searchPluginTextBox))).sendKeys("cmdexec");
		wait.until(ExpectedConditions.elementToBeClickable(pv.selectPlugin("cmdexec"))).click();
		pv.expandParticulrPluginDetails(Stage, "cmdexec");
		Thread.sleep(3000);
		String details = "java --version";
		driver.findElement(By.xpath("//*[text()='Command']//following::input[1]")).sendKeys(details);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void cmdPluglessTimeoutja1(String Stage) throws InterruptedException {
		PipelineView pv = new PipelineView(driver);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(pv.searchPluginTextBox))).sendKeys("cmdexec");
		wait.until(ExpectedConditions.elementToBeClickable(pv.selectPlugin("cmdexec"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[normalize-space(text())='" + Stage
				+ "']//following::div[3]//*[normalize-space(text())='cmdexec']"))).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[text()='Command to execute']//preceding::input[1]"))).clear();
		Thread.sleep(3000);
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[text()='Command to execute']//preceding::input[1]")))
				.sendKeys("cmd1");
		Thread.sleep(3000);
		String details = "java -version";
		driver.findElement(By.xpath("//*[text()='Command']//following::input[1]")).sendKeys(details);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void dyanamicStepAndPlugin1(String Stage, String plugin) throws InterruptedException {
		PipelineView pv = new PipelineView(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(addStepButton)).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(pv.searchPluginTextBox))).sendKeys(plugin);
		wait.until(ExpectedConditions.elementToBeClickable(pv.selectPlugin(plugin))).click();
		Thread.sleep(3000);
	}

	public void dyanamicStepAndPlugin(String Stage, String plugin) throws InterruptedException {
		PipelineView pv = new PipelineView(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//*[normalize-space(text()) = '" + Stage + "']//following::*[normalize-space(text()) = 'ADD STEP']")))
				.click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(pv.searchPluginTextBox))).sendKeys(plugin);
		wait.until(ExpectedConditions.elementToBeClickable(pv.selectPlugin(plugin))).click();
		Thread.sleep(3000);
	}

	public void clickOnCreateWorkflowButton() throws InterruptedException {
		Thread.sleep(3000);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//mat-icon[contains(@class,'mat-icon notranslate leds-add-button mat-elevation')]")));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void clickOnAddStage() throws InterruptedException {

		Thread.sleep(3000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//*[starts-with(text(),' ADD STAGE ')]")));
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[starts-with(text(),' ADD STAGE ')]"))).click();
		Thread.sleep(3000);
	}

	public void dyanamicworkflowStepAndPlugin(String Stage) throws InterruptedException {
		PipelineView pv = new PipelineView(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//*[normalize-space(text()) = '" + Stage
						+ "']//following::span[normalize-space(text()) = 'ADD STEP']")));
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[normalize-space(text()) = '" + Stage
				+ "']//following::span[normalize-space(text()) = 'ADD STEP']"))).click();
		Thread.sleep(4000);
	}

	public void cmdPluglessTimeoutja() throws InterruptedException {
		PipelineView pv = new PipelineView(driver);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(pv.searchPluginTextBox))).sendKeys("cmdexec");
		wait.until(ExpectedConditions.elementToBeClickable(pv.selectPlugin("cmdexec"))).click();
		pv.expandPluginDetails("cmdexec");
		Thread.sleep(3000);
		String details = "java -version";
		driver.findElement(By.xpath("//*[text()='Command']//following::input[1]")).sendKeys(details);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public String decode(String token) {
		byte[] decodedBytes = Base64.decodeBase64(token);
		String TokenDetails = new String(decodedBytes);
		return TokenDetails;
	}

	public void addGitSCMInput(String plugin, String repoName, String Token) throws InterruptedException {

		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);
		String tokens = decode(Token);
		WebElement element = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for GITSCM ']//following::input[2]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.sendKeys(repoName);

		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for GITSCM ']//following::input[4]"))))
				.sendKeys(tokens);

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void addCMDPluginInput(String plugin, String cmd) throws InterruptedException {
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);
		WebElement element = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for CMDEXEC ']//following::input[2]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.sendKeys(cmd);

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void addSonarInput(String plugin, String url, String username, String password) throws InterruptedException {
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);
		WebElement element = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for SONARPLUGIN ']//following::input[2]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.sendKeys(url);

		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for SONARPLUGIN ']//following::input[3]"))))
				.sendKeys(username);

		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for SONARPLUGIN ']//following::input[4]"))))
				.sendKeys(password);

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void addSonarQubeInput(String plugin, String propertie, String password, String prjectKey,
			String releativePATHS, String URLs, String username) throws InterruptedException {

		String tokens = decode(username);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);
		WebElement properties = driver
				.findElement(By.xpath("//span[text()=' Inputs for SONARQUBE ']//following::input[2]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", properties);
		properties.sendKeys(propertie);

		WebElement passwords = driver
				.findElement(By.xpath("//span[text()=' Inputs for SONARQUBE ']//following::input[3]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", passwords);
		passwords.sendKeys(password);

		WebElement projectKey = driver
				.findElement(By.xpath("//span[text()=' Inputs for SONARQUBE ']//following::input[4]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", projectKey);
		projectKey.sendKeys(prjectKey);

		WebElement releativePath = driver
				.findElement(By.xpath("//span[text()=' Inputs for SONARQUBE ']//following::input[5]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", releativePath);
		releativePath.sendKeys(releativePATHS);

		WebElement Url = driver.findElement(By.xpath("//span[text()=' Inputs for SONARQUBE ']//following::input[6]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Url);
		Url.sendKeys(URLs);

		driver.findElement(By.xpath("//span[text()=' Inputs for SONARQUBE ']//following::input[7]")).sendKeys(tokens);

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public boolean addLockVerifycation(String plugin, int sizeOftheLock) throws InterruptedException {
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);

		List<WebElement> element = driver.findElements(By.xpath("//*[@data-mat-icon-name='leds-lock']"));
		log.info(element.size());
		boolean flag = false;
		if (element.size() == sizeOftheLock) {
			flag = true;
		} else {
			flag = false;
		}
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()='Cancel']"))))
				.click();
		return flag;

	}

	public boolean addOpenLockVerifycation(String plugin, int sizeOftheLock) throws InterruptedException {
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);

		List<WebElement> element = driver.findElements(By.xpath("//*[@data-mat-icon-name='leds-lock-opened']"));
		boolean flag = false;
		if (element.size() == sizeOftheLock) {
			flag = true;
		} else {
			flag = false;
		}
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()='Cancel']"))))
				.click();
		return flag;

	}

	public boolean addLockVerifycationAndEdit(String plugin, int sizeOftheLock, String username)
			throws InterruptedException {
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);

		List<WebElement> element = driver.findElements(By.xpath("//*[@data-mat-icon-name='leds-lock']"));
		boolean flag = false;
		if (element.size() == sizeOftheLock) {
			flag = true;
		} else {
			flag = false;
		}
		Thread.sleep(2000);
		WebElement elements = driver.findElement(By.xpath("//span[text()=' Inputs for GITSCM ']//following::input[3]"));
		elements.clear();
		elements.sendKeys(username);

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
		return flag;

	}

	public void addSleepInput(String plugin, String sleepTimeinSec) throws InterruptedException {
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);
		WebElement element = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for SLEEP ']//following::input[2]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.sendKeys(sleepTimeinSec);

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void addGitSCMInputDynamicStage(String stage, String plugin, String repoName, String Token)
			throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(
				"//*[normalize-space(text()) = '" + stage + "']//following::span[contains(text(),'" + plugin + "')]"))))
				.click();
		Thread.sleep(3000);
		String tokens = decode(Token);
		WebElement element = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for GITSCM ']//following::input[2]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.sendKeys(repoName);

		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for GITSCM ']//following::input[4]"))))
				.sendKeys(tokens);

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void addSoapUIpluginInput(String plugin, String projectPath, String TestSuite) throws InterruptedException {
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for SOAPUI ']//following::input[2]"))))
				.sendKeys(projectPath);
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for SOAPUI ']//following::input[3]"))))
				.sendKeys(TestSuite);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void addNewmanpluginInput(String plugin, String collectionJSON, String collectionJsonPath, String Envpath)
			throws InterruptedException {
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for NEWMAN POSTMAN ']//following::input[2]"))))
				.sendKeys(collectionJSON);
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for NEWMAN POSTMAN ']//following::input[3]"))))
				.sendKeys(collectionJsonPath);
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for NEWMAN POSTMAN ']//following::input[4]"))))
				.sendKeys(Envpath);

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void addPython_BuildpluginInput(String plugin, String fileDir, String setupFileDir)
			throws InterruptedException {
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for PYTHON BUILD ']//following::input[2]"))))
				.sendKeys(fileDir);
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for PYTHON BUILD ']//following::input[3]"))))
				.sendKeys(setupFileDir);

		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for PYTHON BUILD ']//following::div[35]"))))
				.click();

		WebElement element3 = wait.until(
				ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='mat-option-text']"))));
		Thread.sleep(3000);
		Actions action = new Actions(driver);
		action.moveToElement(element3).build().perform();
		action.click(element3).build().perform();

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void addSeleniumpluginInput(String plugin, String filePath, String TestNGDetails)
			throws InterruptedException {
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for SELENIUM ']//following::input[2]"))))
				.sendKeys(filePath);
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for SELENIUM ']//following::input[3]"))))
				.sendKeys(TestNGDetails);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void addSeleniumpTestNGluginInput(String plugin, String filePath, String xmlFileDetails)
			throws InterruptedException {
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for SELENIUM_TESTNG ']//following::input[2]"))))
				.sendKeys(filePath);
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for SELENIUM_TESTNG ']//following::input[3]"))))
				.sendKeys(xmlFileDetails);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void addJmeterpluginInput(String plugin, String JMXFilepath) throws InterruptedException {
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for JMETER ']//following::input[2]"))))
				.sendKeys(JMXFilepath);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void addCodeQLpluginInput(String plugin, String gitRepoName, String projectName, String token,
			String scriptLanguage, String buildCommands) throws InterruptedException {
		String tokens = decode(token);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for CODEQL ']//following::input[2]"))))
				.sendKeys(gitRepoName);

		WebElement ele = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for CODEQL ']//following::input[3]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		ele.sendKeys(projectName);

		WebElement element = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for CODEQL ']//following::input[4]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.sendKeys(tokens);

		WebElement element1 = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for CODEQL ']//following::span[21]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(3000);
		element1.click();
		clickOnElement1(scriptLanguage);

		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for CODEQL ']//following::input[5]"))))
				.sendKeys(buildCommands);

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void addITAFExecutionpluginInput(String plugin, String urlOFITAF, String projectName, String testCase,
			String worker, String portfolioName, String emailID, String itafToken) throws InterruptedException {
		String tokens = decode(itafToken);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for ITAF-EXECUTION ']//following::input[2]"))))
				.sendKeys(urlOFITAF);

		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for ITAF-EXECUTION ']//following::input[3]"))))
				.sendKeys(projectName);

		WebElement testcases = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for ITAF-EXECUTION ']//following::input[4]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", testcases);
		testcases.sendKeys(testCase);

		WebElement workers = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for ITAF-EXECUTION ']//following::input[5]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", workers);
		workers.sendKeys(worker);

		WebElement ele1 = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for ITAF-EXECUTION ']//following::input[6]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele1);
		Thread.sleep(3000);
		ele1.sendKeys(portfolioName);

		WebElement email = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for ITAF-EXECUTION ']//following::input[7]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", email);
		Thread.sleep(3000);
		email.sendKeys(emailID);

		WebElement tokns = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for ITAF-EXECUTION ']//following::input[8]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tokns);
		Thread.sleep(3000);
		tokns.sendKeys(tokens);

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void addCodeQLAnalyzepluginInput(String plugin, String gitProjectName, String projectPath, String token,
			String scriptLanguage) throws InterruptedException {
		String tokens = decode(token);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for CODEQL-ANALYZE ']//following::input[2]"))))
				.sendKeys(gitProjectName);
		WebElement ele = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for CODEQL-ANALYZE ']//following::input[3]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		ele.sendKeys(projectPath);
		WebElement element = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for CODEQL-ANALYZE ']//following::input[4]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.sendKeys(tokens);
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for CODEQL-ANALYZE ']//following::input[5]"))))
				.sendKeys(scriptLanguage);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void clickOnPlugin(String plugin) throws InterruptedException {
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);
	}

	public Boolean validateSaveButton(String plugin, int num) throws InterruptedException {
		Thread.sleep(3000);
		Boolean value = driver
				.findElement(
						By.xpath("//span[normalize-space(text())='" + plugin + "']//following::button[" + num + "]"))
				.isEnabled();
		return value;
	}

	public Boolean verify_Plugin_Input_Fields(String plugin, int number) throws InterruptedException {

		WebElement ele = wait.until(ExpectedConditions.visibilityOf(driver.findElement(
				By.xpath("//span[normalize-space(text())='" + plugin + "']//following::input[" + number + "]"))));
		ele.clear();
		ele.sendKeys("a");
		ele.sendKeys(Keys.BACK_SPACE);
		ele.click();
		ele.sendKeys(Keys.TAB);
		log.info("//span[normalize-space(text())='" + plugin + "']//following::input[" + number + "]");

		Thread.sleep(4000);

		log.info("//span[normalize-space(text())='" + plugin + "']//following::input[" + number + "]//following::input["
				+ number + "]//following::mat-error[1]");

		WebElement element = driver.findElement(By.xpath("//span[normalize-space(text())='" + plugin
				+ "']//following::input[" + number + "]//following::mat-error[1]"));

		Boolean flag;

		if (element.getText().equalsIgnoreCase("This field is required")) {
			flag = true;
		} else {
			flag = false;
		}

		return flag;
	}

	public void scrollToElement(String plugin, int number) throws InterruptedException {

		Thread.sleep(2000);
		WebElement ele = driver.findElement(
				By.xpath("//span[normalize-space(text())='" + plugin + "']//following::input[" + number + "]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		Thread.sleep(2000);
	}

	public void scrollToView(String element) throws InterruptedException {
		Thread.sleep(2000);
		WebElement ele = driver.findElement(By.xpath("//*[normalize-space(text())='" + element + "']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		Thread.sleep(2000);
	}

	public void addGoBuildpluginInput(String plugin, String arg, String binaryName, String matcingPattern, String token,
			String repoUser, String projectDir) throws InterruptedException {
		String tokens = decode(token);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);

		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for GO BUILD ']//following::input[2]"))))
				.sendKeys(arg);

		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for GO BUILD ']//following::input[3]"))))
				.sendKeys(binaryName);
		WebElement ele = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for GO BUILD ']//following::input[4]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		WebElement element = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for GO BUILD ']//following::input[5]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.sendKeys(tokens);

		WebElement element1 = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for GO BUILD ']//following::input[6]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		element1.sendKeys(tokens);

		WebElement elemen = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for GO BUILD ']//following::input[7]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elemen);
		elemen.sendKeys(projectDir);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void addGoUnitTestpluginInput(String plugin, String matchingPattern, String projectDir)
			throws InterruptedException {
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for GO UNIT TEST ']//following::input[2]"))))
				.sendKeys(matchingPattern);

		WebElement ele = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for GO UNIT TEST ']//following::input[4]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);

		WebElement element = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for GO UNIT TEST ']//following::input[5]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.sendKeys(projectDir);

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void addGoCoveragepluginInput(String plugin, String projectDir) throws InterruptedException {
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for GO COVERAGE ']//following::input[2]"))))
				.sendKeys(projectDir);

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void addGoLintluginInput(String plugin, String projectDir) throws InterruptedException {
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for GO LINT ']//following::input[2]"))))
				.sendKeys(projectDir);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void addpluginInput(String plugin, String URL, String repoName, String userName, String token,
			String oldTagName, String newTagName) throws InterruptedException {
		String tokens = decode(token);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for DOCKER TAGGER ']//following::input[2]"))))
				.sendKeys(URL);

		WebElement ele = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for DOCKER TAGGER ']//following::input[3]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		ele.sendKeys(repoName);

		WebElement element = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for DOCKER TAGGER ']//following::input[4]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.sendKeys(userName);

		WebElement element1 = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for DOCKER TAGGER ']//following::input[5]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		element1.sendKeys(tokens);

		WebElement elemen = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for DOCKER TAGGER ']//following::input[6]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elemen);
		elemen.sendKeys(oldTagName);

		WebElement elemen3 = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for DOCKER TAGGER ']//following::input[7]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elemen3);
		elemen3.sendKeys(newTagName);

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void addk8s_CustomDeploypluginInput(String plugin, String configPath, String configFileName,
			String yamlFileName, String kuberNetName, String validationTimeOut) throws InterruptedException {

		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for K8S_CUSTOMDEPLOY ']//following::input[2]"))))
				.sendKeys(configPath);
		Thread.sleep(3000);
		WebElement ele = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for K8S_CUSTOMDEPLOY ']//following::input[3]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		ele.sendKeys(configFileName);

		WebElement element = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for K8S_CUSTOMDEPLOY ']//following::input[4]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.sendKeys(yamlFileName);

		WebElement element1 = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for K8S_CUSTOMDEPLOY ']//following::input[5]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		element1.sendKeys(kuberNetName);

		WebElement elemen4 = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for K8S_CUSTOMDEPLOY ']//following::input[7]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elemen4);
		elemen4.sendKeys(validationTimeOut);

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void addnplugin_buildInput(String plugin, String disributionList, String pluginDir)
			throws InterruptedException {

		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for NPLUGIN_BUILD ']//following::input[3]"))))
				.sendKeys(disributionList);

		WebElement ele = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for NPLUGIN_BUILD ']//following::input[4]"))));
		ele.sendKeys(pluginDir);

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void addnplugin_parameter(String plugin, String xpath, String Status) throws InterruptedException {

		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);
		WebElement element = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpath))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.sendKeys(Status);

		String value = Status.replace("${", "").replace("}", "");
		log.info(value);
		Thread.sleep(3000);
		driver.switchTo().activeElement().sendKeys(value);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text()=' Add ']")))).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void addnplugin_promotionPluginInput(String plugin, String registyURL, String pluginDir)
			throws InterruptedException {

		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);

		WebElement element = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for NPLUGIN_PROMOTION ']//following::input[3]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for NPLUGIN_PROMOTION ']//following::input[4]"))))
				.sendKeys(registyURL);

		WebElement ele = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for NPLUGIN_PROMOTION ']//following::input[6]"))));
		ele.sendKeys(pluginDir);

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void addpluginUploadPluginInput(String plugin, String regURL, String version, String pluginDir)
			throws InterruptedException {

		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);

		wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(By.xpath("//span[text()=' Inputs for PLUGIN-UPLOAD ']//following::mat-checkbox[2]"))))
				.click();
		WebElement element = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for PLUGIN-UPLOAD ']//following::input[4]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for PLUGIN-UPLOAD ']//following::input[5]"))))
				.sendKeys(regURL);

		String value = regURL.replace("${", "").replace("}", "");
		log.info(value);
		Thread.sleep(3000);
		driver.switchTo().activeElement().sendKeys(value);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text()=' Add ']")))).click();

		WebElement element1 = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for PLUGIN-UPLOAD ']//following::input[6]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		element1.sendKeys(version);

		String value1 = version.replace("${", "").replace("}", "");
		log.info(value1);
		Thread.sleep(3000);
		driver.switchTo().activeElement().sendKeys(value1);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text()=' Add ']")))).click();
		WebElement element2 = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for PLUGIN-UPLOAD ']//following::input[7]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element2);
		element2.sendKeys(pluginDir);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();

	}

	public void addDockerTaggerpluginInput(String plugin, String URL, String repoName, String userName, String token,
			String oldTagName, String newTagName) throws InterruptedException {
		String tokens = decode(token);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for DOCKER TAGGER ']//following::input[2]"))))
				.sendKeys(URL);

		WebElement ele = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for DOCKER TAGGER ']//following::input[3]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		ele.sendKeys(repoName);

		WebElement element = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for DOCKER TAGGER ']//following::input[4]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.sendKeys(userName);

		WebElement element1 = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for DOCKER TAGGER ']//following::input[5]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		element1.sendKeys(tokens);

		WebElement elemen = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for DOCKER TAGGER ']//following::input[6]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elemen);
		elemen.sendKeys(oldTagName);

		WebElement elemen3 = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for DOCKER TAGGER ']//following::input[7]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elemen3);
		elemen3.sendKeys(newTagName);

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void addCAST_AIPpluginInput(String plugin, String applicationName, String url, String profile,
			String projectName, String schemaName, String codePath) throws InterruptedException {
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for CAST-AIP ']//following::input[2]"))))
				.sendKeys(applicationName);

		WebElement ele = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for CAST-AIP ']//following::input[3]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		ele.sendKeys(url);

		WebElement element = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for CAST-AIP ']//following::input[4]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.sendKeys(profile);

		WebElement element1 = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for CAST-AIP ']//following::input[5]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		element1.sendKeys(projectName);

		WebElement elemen = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for CAST-AIP ']//following::input[6]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elemen);
		elemen.sendKeys(schemaName);

		WebElement elemen3 = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for CAST-AIP ']//following::input[7]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elemen3);
		elemen3.sendKeys(codePath);

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void addKarma_JasminepluginInput(String plugin, String name, String flagDetails, String nameOFTheproject,
			String nameOFdir) throws InterruptedException {

		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);
		WebElement ele1 = wait.until(ExpectedConditions.visibilityOf(driver.findElement(
				By.xpath("//span[text()=' Inputs for ANGULAR UNIT TEST WITH KARMA-JASMINE ']//following::input[1]"))));
		ele1.clear();
		Thread.sleep(3000);
		ele1.sendKeys(name);

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(
				By.xpath("//span[text()=' Inputs for ANGULAR UNIT TEST WITH KARMA-JASMINE ']//following::input[2]"))))
				.sendKeys(flagDetails);

		WebElement ele = wait.until(ExpectedConditions.visibilityOf(driver.findElement(
				By.xpath("//span[text()=' Inputs for ANGULAR UNIT TEST WITH KARMA-JASMINE ']//following::input[3]"))));
		ele.sendKeys(nameOFTheproject);

		WebElement element = wait.until(ExpectedConditions.visibilityOf(driver.findElement(
				By.xpath("//span[text()=' Inputs for ANGULAR UNIT TEST WITH KARMA-JASMINE ']//following::input[4]"))));
		element.sendKeys(nameOFdir);

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void addBlazemeterpluginInput(String plugin, String URL, String keyID, String secrKey, String testType,
			String testID) throws InterruptedException {

		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for BLAZEMETER ']//following::input[2]"))))
				.sendKeys(URL);

		WebElement ele = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for BLAZEMETER ']//following::input[3]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		ele.sendKeys(keyID);

		WebElement element = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for BLAZEMETER ']//following::input[4]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.sendKeys(secrKey);

		WebElement element1 = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for BLAZEMETER ']//following::input[5]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		element1.sendKeys(testType);

		WebElement elemen = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for BLAZEMETER ']//following::input[6]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elemen);
		elemen.sendKeys(testID);

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void addTslintCodeAnalysispluginInput(String plugin, String additonalArg, String projectPath)
			throws InterruptedException {
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(By.xpath("//span[text()=' Inputs for TSLINT CODE ANALYSIS ']//following::input[2]"))))
				.sendKeys(additonalArg);

		WebElement ele = wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(By.xpath("//span[text()=' Inputs for TSLINT CODE ANALYSIS ']//following::input[3]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		ele.sendKeys(projectPath);

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void addAngularBuildpluginInput(String plugin, String npmArg, String projectPath)
			throws InterruptedException {
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);
		WebElement element = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for ANGULAR BUILD ']//following::input[3]"))));
		element.clear();
		element.sendKeys(npmArg);

		WebElement ele = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for ANGULAR BUILD ']//following::input[5]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		ele.sendKeys(projectPath);

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void addSonarQubepluginInput(String plugin, String password, String productkey, String relativePath,
			String sonarQubeURL, String securityToken) throws InterruptedException {
		String passwords = decode(password);
		String tokens = decode(securityToken);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for SONARQUBE ']//following::input[3]"))))
				.sendKeys(passwords);

		WebElement ele = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for SONARQUBE ']//following::input[4]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		ele.sendKeys(productkey);

		WebElement element = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for SONARQUBE ']//following::input[5]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.sendKeys(relativePath);

		WebElement element1 = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for SONARQUBE ']//following::input[6]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		element1.sendKeys(sonarQubeURL);

		WebElement elemen = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for SONARQUBE ']//following::input[7]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elemen);
		elemen.sendKeys(securityToken);

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void addenvironmentVar_BuildId_FormatterpluginInput(String plugin, String stepName)
			throws InterruptedException {
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);
		WebElement element = wait.until(ExpectedConditions.visibilityOf(driver.findElement(
				By.xpath("//span[text()=' Inputs for ENVIRONMENTVAR_BUILDID_FORMATTER ']//following::input[1]"))));
		element.clear();
		element.sendKeys(stepName);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void addDockerBuildAndPushpluginInput(String plugin, String addtionalArg, String dockerFilePath,
			String token, String regURL, String repoName, String userName, String tagName) throws InterruptedException {
		String tokens = decode(token);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for DOCKER BUILD & PUSH ']//following::input[2]"))))
				.sendKeys(addtionalArg);

		WebElement scrollDown = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for DOCKER BUILD & PUSH ']//following::div[35]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scrollDown);

		WebElement ele = wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(By.xpath("//span[text()=' Inputs for DOCKER BUILD & PUSH ']//following::input[4]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		ele.sendKeys(dockerFilePath);

		WebElement element = wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(By.xpath("//span[text()=' Inputs for DOCKER BUILD & PUSH ']//following::input[5]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.sendKeys(tokens);

		WebElement element1 = wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(By.xpath("//span[text()=' Inputs for DOCKER BUILD & PUSH ']//following::input[6]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		element1.sendKeys(regURL);

		WebElement elemen = wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(By.xpath("//span[text()=' Inputs for DOCKER BUILD & PUSH ']//following::input[7]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elemen);
		elemen.sendKeys(repoName);

		WebElement elemen3 = wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(By.xpath("//span[text()=' Inputs for DOCKER BUILD & PUSH ']//following::input[8]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elemen3);
		elemen3.sendKeys(userName);

		WebElement eleme4 = wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(By.xpath("//span[text()=' Inputs for DOCKER BUILD & PUSH ']//following::input[9]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", eleme4);
		eleme4.sendKeys(tagName);

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void addBlockDuckpluginInput(String plugin, String urlofBlackDock, String projectName, String projectVersion,
			String projectPath, String token) throws InterruptedException {
		String tokens = decode(token);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);

		WebElement ele = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for BLACKDUCK ']//following::input[2]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		ele.sendKeys(urlofBlackDock);

		WebElement element = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for BLACKDUCK ']//following::input[3]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.sendKeys(projectName);

		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for BLACKDUCK ']//following::input[4]"))))
				.sendKeys(projectVersion);

		WebElement element1 = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for BLACKDUCK ']//following::input[5]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		element1.sendKeys(projectPath);

		WebElement elemen = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for BLACKDUCK ']//following::input[6]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elemen);
		elemen.sendKeys(tokens);

		Thread.sleep(3000);

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void addJiraUpdatepluginInput(String plugin, String password, String url, String UserName)
			throws InterruptedException {

		String tokens = decode(password);

		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for JIRA-STATUS-UPDATE ']//following::input[2]"))))
				.sendKeys(tokens);
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for JIRA-STATUS-UPDATE ']//following::input[3]"))))
				.sendKeys(url);
		WebElement element = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for JIRA-STATUS-UPDATE ']//following::input[4]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.sendKeys(UserName);

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void addJiraUpdatepluginInputAddStatus(String plugin, String Status) throws InterruptedException {

		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);
		WebElement element = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for JIRA-STATUS-UPDATE ']//following::input[4]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//*[text()=' Inputs for JIRA-STATUS-UPDATE ']//following::div[44]"))))
				.click();
		clickOnElement1(Status);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void addSASTPlugin(String plugin, String projectPath, String executeList, String token)
			throws InterruptedException {
		PipelineView pv = new PipelineView(driver);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);
		WebElement elemens = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for FORTIFY-SAST ']//following::input[2]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elemens);
		elemens.sendKeys(projectPath);
		Thread.sleep(3000);
		WebElement elemen = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for FORTIFY-SAST ']//following::input[3]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elemen);
		elemen.click();
		Thread.sleep(3000);
		WebElement element1s = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for FORTIFY-SAST ']//following::div[49]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1s);
		element1s.click();
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//span[@class='mat-option-text']//following::span[1]"))))
				.click();
		Thread.sleep(3000);
		WebElement element = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for FORTIFY-SAST ']//following::input[5]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.sendKeys(executeList);
		Thread.sleep(3000);

		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for FORTIFY-SAST ']//following::input[6]"))))
				.sendKeys(token);

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void addDASTPlugin(String plugin, String fortyRL, String userName, String token, String templatePath)
			throws InterruptedException {
		String tokens = decode(token);
		PipelineView pv = new PipelineView(driver);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		Thread.sleep(3000);

		WebElement element = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for FORTIFY DAST ']//following::input[2]"))));
		element.sendKeys(fortyRL);

		WebElement element3 = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for FORTIFY DAST ']//following::input[3]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element3);
		element3.sendKeys(userName);

		WebElement element2 = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for FORTIFY DAST ']//following::input[4]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element2);
		element2.sendKeys(tokens);

		WebElement element1 = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[text()=' Inputs for FORTIFY DAST ']//following::input[5]"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		element1.sendKeys(templatePath);

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void createStepe() throws InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(addStepButton)).click();
		cmdplug();
	}

	public void createStepeForTimeout() throws InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(addStepButton)).click();
		cmdPlugTimeout();
	}

	public void createStepeForlessTimeout() throws InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(addStepButton)).click();
		cmdPluglessTimeout();
	}

	public void createDynamicSteps(String StageName) throws InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
		Thread.sleep(2000);
		WebElement el = driver.findElement(
				By.xpath("(//*[normalize-space(text())='" + StageName + "']//following::mat-chip-option/span[1])[1]"));
		Actions action = new Actions(driver);
		action.moveToElement(el).click().build().perform();
		cmdPluglessTimeoutja(StageName);
	}

	public void createDynamicStepsPluginParameter(String StageName, String plugin, String parameter)
			throws InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
		WebElement el = driver
				.findElement(By.xpath("//*[normalize-space(text())='" + StageName + "']//following::div[1]"));
		Actions action = new Actions(driver);
		action.moveToElement(el).doubleClick().build().perform();
		PipelineView pv = new PipelineView(driver);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(pv.searchPluginTextBox))).sendKeys(plugin);
		wait.until(ExpectedConditions.elementToBeClickable(pv.selectPlugin(plugin))).click();
		pv.expandParticulrPluginDetails(StageName, plugin);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[text()='Command']//following::input[1]")).sendKeys(parameter);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void createStepeForlessTimeout1() throws InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(addStepButton)).click();
		cmdPluglessTimeout1();
	}

	public void createFaildStepeForlessTimeout() throws InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(addStepButton)).click();
		cmdPlugfaildPluginlessTimeout();
	}
	
	public void createFaildStepeForlessTimeoutDummy() throws InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(addStepButton)).click();
		cmdPlugfaildPluginlessTimeout();
		
	}

	public void CreateDynamicParameterTestStep(String StageName)
			throws InterruptedException, IOException, AWTException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
		Thread.sleep(2000);
		WebElement el = driver.findElement(
				By.xpath("(//*[normalize-space(text())='" + StageName + "']//following::mat-chip-option/span[1])[1]"));
		Actions action = new Actions(driver);
		action.moveToElement(el).click().build().perform();
		paraMeterTestStep();
	}

	public void cmdPluglessTimeoutJavaVersion() throws InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(addStepButton)).click();
		cmdPluglessTimeoutja();
	}

	public void scrollUP() throws InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
		WebElement element = driver.findElement(By.xpath("//*[text()=' Dashboard ']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=' Dashboard ']")));
	}

	public void clickOnElement2(String value) throws InterruptedException {
		Thread.sleep(3000);
		driver.switchTo().activeElement().sendKeys(value);
		Thread.sleep(3000);
		List<WebElement> list = driver.findElements(By.xpath("//*[@class='mdc-list-item__primary-text']"));
		for (WebElement webElement : list) {
			if (webElement.getText().equalsIgnoreCase(value)) {
				wait.until(ExpectedConditions.elementToBeClickable(webElement));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
				break;
			}
		}
	}

	public void submitPipeline() throws InterruptedException {
		Thread.sleep(1000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
		JavascriptExecutor je = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath("//*[normalize-space()='save']/parent::button"));
		je.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(3000);
		if (driver.findElement(submitPipelineButton_PipelineDetailsEditor("save")).isEnabled()) {
			Thread.sleep(4000);
			driver.findElement(submitPipelineButton_PipelineDetailsEditor("save")).click();
			Thread.sleep(3000);
			driver.findElement(submitPipelineButton_PipelineDetailsEditor("Yes")).click();
			log.info("Pipeline details submitted successfully");
		} else {
			new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions
					.elementToBeClickable(driver.findElement(submitPipelineButton_PipelineDetailsEditor("save"))))
					.click();
			Thread.sleep(3000);
			driver.findElement(submitPipelineButton_PipelineDetailsEditor("Yes")).click();
			log.info("Pipeline details submitted successfully");
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
	}

	public void submitCancelPipeline() throws InterruptedException {
		Thread.sleep(1000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
		JavascriptExecutor je = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath("//*[normalize-space()='save']/parent::button"));
		je.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(3000);
		if (driver.findElement(submitPipelineButton_PipelineDetailsEditor("save")).isEnabled()) {
			Thread.sleep(4000);
			driver.findElement(submitPipelineButton_PipelineDetailsEditor("save")).click();
			Thread.sleep(3000);
			driver.findElement(submitPipelineButton_PipelineDetailsEditor("No")).click();
			log.info("Pipeline details Cancelled successfully");
		} else {
			new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions
					.elementToBeClickable(driver.findElement(submitPipelineButton_PipelineDetailsEditor("save"))))
					.click();
			Thread.sleep(3000);
			driver.findElement(submitPipelineButton_PipelineDetailsEditor("No")).click();
			log.info("Pipeline details Cancelled successfully");
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
	}

	@CacheLookup
	@FindBy(xpath = "//*[normalize-space()='touch_app']")
	WebElement tiggerPipelines1;

	public void triggerPipelineUsingIcon() throws InterruptedException, IOException {
		try {
			Thread.sleep(5000);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
			log.info("Trigger page");
			wait.until(ExpectedConditions.elementToBeClickable(tiggerPipelines1));
			Actions action = new Actions(driver);
			action.moveToElement(tiggerPipelines1).doubleClick().build().perform();
			log.info("Pipeline execution log page");
		} catch (org.openqa.selenium.StaleElementReferenceException e) {
			log.info("Trigger page Catch Block");
			wait.until(ExpectedConditions.elementToBeClickable(tiggerPipelines));
			WebElement IconTrigger = driver.findElement(tiggerPipelines);
			Actions action = new Actions(driver);
			action.moveToElement(IconTrigger).doubleClick().build().perform();
			log.info("Pipeline execution log page");
		}
	}

	public void ReleaseNameSelect(String value) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(
				driver.findElement(By.xpath("//*[text()='Release Name']//following::div[4]"))));
		Thread.sleep(3000);
		WebElement releaseName = driver.findElement(By.xpath("//*[text()='Release Name']//following::div[4]"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", releaseName);
		Thread.sleep(3000);
		List<WebElement> list = driver.findElements(By.xpath("//span[@class='mdc-list-item__primary-text']"));
		for (WebElement webElement : list) {
			if (webElement.getText().equalsIgnoreCase(value)) {
				wait.until(ExpectedConditions.elementToBeClickable(webElement));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
				break;
			}
		}
	}

	public void workerNameSelectInWorkflow(String value) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(
				driver.findElement(By.xpath("(//*[starts-with(@class,'mat-form-field-infix ng-tns-c')])[2]")))).click();
		Thread.sleep(3000);
		List<WebElement> list = driver.findElements(By.xpath("//span[@class='mat-option-text']"));
		for (WebElement webElement : list) {
			if (webElement.getText().equalsIgnoreCase("None")) {
				log.info("its none");
			} else {
				log.info(webElement.getText());
				String[] workerName = webElement.getText().split("_");
				log.info(workerName[1]);
				if (workerName[1].contains(value)) {
					wait.until(ExpectedConditions.elementToBeClickable(webElement));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
					break;
				}
			}
		}
	}

	public void triggerPageWorkflowSelection(String value) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(
				driver.findElement(By.xpath("(//*[starts-with(@class,'mat-form-field-infix ng-tns-c')])[1]")))).click();
		Thread.sleep(3000);
		List<WebElement> list = driver.findElements(By.xpath("//span[@class='mat-option-text']"));
		for (WebElement webElement : list) {
			if (webElement.getText().equalsIgnoreCase("None")) {
				log.info("its none");
			} else {
				log.info(webElement.getText());
				if (webElement.getText().contains(value)) {
					wait.until(ExpectedConditions.elementToBeClickable(webElement));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
					break;
				}
			}
		}
	}

	public void workerSelectionInPipelineTrigger(String appAndWorkerName) throws InterruptedException {

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
		WebElement ActiveInstance = driver.findElement(By.xpath("//*[text()='application:']"));
		Actions action = new Actions(driver);
		action.moveToElement(ActiveInstance).click().build().perform();
		Thread.sleep(4000);
	}

	public void workerSelectionInWorkflowTrigger(String appAndWorkerName) throws InterruptedException {

		Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//mat-label[text()='Worker Name']"))));
		Thread.sleep(4000);
		WebElement workerName = driver.findElement(By.xpath("//mat-label[text()='Worker Name']"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", workerName);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[starts-with(@id,'mat-input')]")));
		Thread.sleep(4000);
		driver.switchTo().activeElement().sendKeys(appAndWorkerName);
		Thread.sleep(4000);
		PipelineHelper ph = new PipelineHelper(driver);
		String value = "application:" + appAndWorkerName;
		ph.clickOnElement1(value);
		Thread.sleep(4000);
	}

	public boolean workerSelectionInPipelineTriggerArrowVerify(String appAndWorkerName, Boolean value)
			throws InterruptedException {
		Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text()='Worker Name']"))));
		Thread.sleep(4000);
		WebElement workerName = driver.findElement(By.xpath("//span[text()='Worker Name']"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", workerName);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[starts-with(@id,'mat-input')]")));
		driver.findElement(By.xpath("//*[starts-with(@id,'mat-input')]")).click();

		Thread.sleep(4000);
		driver.switchTo().activeElement().sendKeys(appAndWorkerName);
		Thread.sleep(4000);
		boolean flag;
		boolean workerrunning = true;
		if (workerrunning == value) {
			boolean arrowUpward = driver.findElement(By.xpath("//*[text()='arrow_upward']")).isDisplayed();
			flag = true;
		} else {
			boolean arrowUpward = driver.findElement(By.xpath("//*[text()='arrow_downward']")).isDisplayed();
			flag = true;
		}
		Thread.sleep(4000);
		WebElement ActiveInstance = driver.findElement(By.xpath("//*[text()='application:']"));
		Actions action = new Actions(driver);
		action.moveToElement(ActiveInstance).click().build().perform();
		return flag;
	}

	public String webHookDetailsFetch(String appAndWorkerName) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[starts-with(@class,'ng-tns')]//following::span[49]"))));
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[starts-with(@class,'ng-tns')]//following::span[45]"))));
		String releaseName = driver.findElement(By.xpath("//span[starts-with(@class,'ng-tns')]//following::span[49]"))
				.getText();
		String workflowName = driver.findElement(By.xpath("//span[starts-with(@class,'ng-tns')]//following::span[45]"))
				.getText();

		String appAndPipName = driver.findElement(By.xpath("//*[@class='mat-card-header-text']")).getText();

		String appname = appAndPipName.replace(" ", "");
		String AppNames = appname.replace("/", "_").replace(" ", "");

		String webHookurl = "https://agtest.ad.infosys.com/svc/webhook/" + appname + "/general/" + AppNames + "?worker="
				+ appAndWorkerName + "&release=" + releaseName + "";

		return webHookurl;
	}

	public void triggerPipelineUsingButton() throws InterruptedException, IOException {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()=' Trigger Pipeline ']"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//*[text()=' Trigger Pipeline ']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Trigger Pipeline ']")));
		WebElement Trigger = driver.findElement(By.xpath("//*[text()=' Trigger Pipeline ']"));
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", Trigger);
		log.info("Pipeline Tiggered");
	}

	public void triggerPipelineUsingButtonWithBranch(String branchDetails) throws InterruptedException, IOException {
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//div[text()='Pipeline Flow']//following::input[1]"))))
				.clear();
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//div[text()='Pipeline Flow']//following::input[1]"))))
				.sendKeys(branchDetails);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()=' Trigger Pipeline ']"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//*[text()=' Trigger Pipeline ']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Trigger Pipeline ']")));
		WebElement Trigger = driver.findElement(By.xpath("//*[text()=' Trigger Pipeline ']"));
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", Trigger);
		log.info("Pipeline Tiggered");
		Thread.sleep(15000);
	}

	public void triggerPipelineUsingButtonWithMultichoiceParameter(String parameterDetails)
			throws InterruptedException, IOException {
		Thread.sleep(4000);
		WebElement element = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(4000);
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//div[text()='Pipeline Parameters']//following::span[2]"))))
				.click();
		clickOnElement1(parameterDetails);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()=' Trigger Pipeline ']"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//*[text()=' Trigger Pipeline ']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Trigger Pipeline ']")));
		WebElement Trigger = driver.findElement(By.xpath("//*[text()=' Trigger Pipeline ']"));
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", Trigger);
		log.info("Pipeline Tiggered");
		Thread.sleep(15000);
	}

	public Boolean triggerPipelinePageTriggerableVerifycationParameter() throws InterruptedException, IOException {
		Thread.sleep(4000);
		WebElement element = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(4000);
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//div[text()='Pipeline Parameters']//following::input[1]"))))
				.clear();
		Thread.sleep(4000);
		WebElement paramete = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//div[text()='Pipeline Parameters']//following::input[1]"))));
		paramete.sendKeys("a");
		Thread.sleep(4000);
		paramete.sendKeys(Keys.BACK_SPACE);
		Thread.sleep(2000);
		paramete.sendKeys(Keys.BACK_SPACE);

		String text = wait
				.until(ExpectedConditions.visibilityOf(driver.findElement(
						By.xpath("//mat-error[text()='This Parameter is Mandatory for Selected Workflow']"))))
				.getText();

		Boolean flag = false;
		if (text.equalsIgnoreCase("This Parameter is Mandatory for Selected Workflow")) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	public void triggerPipelinePageEnterTriggerablenParameter(String TriggerableValue)
			throws InterruptedException, IOException {
		Thread.sleep(4000);
		WebElement element = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(4000);
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//div[text()='Pipeline Parameters']//following::input[1]"))))
				.sendKeys(TriggerableValue);
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath("//*[starts-with(text(),' Dashboard')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
	}

	public void scrollUp() throws InterruptedException, IOException {
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath("//*[starts-with(text(),' Dashboard')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
	}

	public void triggerPageBranchSelection(String branchDetails, int inputbox)
			throws InterruptedException, IOException {
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//div[text()='Pipeline Flow']//following::input[" + inputbox + "]"))))
				.clear();
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//div[text()='Pipeline Flow']//following::input[" + inputbox + "]"))))
				.sendKeys(branchDetails);
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
	}

	public void triggerPipelineUsingButtonWith2Branch(String branchDetails, String branchDetails1)
			throws InterruptedException, IOException {
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//div[text()='Pipeline Flow']//following::input[1]"))))
				.clear();
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//div[text()='Pipeline Flow']//following::input[1]"))))
				.sendKeys(branchDetails);
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		WebElement element2 = wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//div[text()='Pipeline Flow']//following::input[2]"))));
		element2.clear();
		element2.sendKeys(branchDetails1);
		Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()=' Trigger Pipeline ']"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//*[text()=' Trigger Pipeline ']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Trigger Pipeline ']")));
		WebElement Trigger = driver.findElement(By.xpath("//*[text()=' Trigger Pipeline ']"));
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", Trigger);
		log.info("Pipeline Tiggered");
		Thread.sleep(15000);
	}

	public void triggerPipelineUsingButtonWithJiraDetails(String issueDetails, String Status)
			throws InterruptedException, IOException {
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//div[text()='Pipeline Flow']//following::input[1]"))))
				.sendKeys(issueDetails);
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//div[text()='Pipeline Flow']//following::input[2]"))))
				.sendKeys(Status);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()=' Trigger Pipeline ']"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//*[text()=' Trigger Pipeline ']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Trigger Pipeline ']")));
		WebElement Trigger = driver.findElement(By.xpath("//*[text()=' Trigger Pipeline ']"));
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", Trigger);
		log.info("Pipeline Tiggered");
		Thread.sleep(15000);
	}

	public void pipelineTriggerVerificaton() throws InterruptedException, IOException {
		log.info("pipelineTriggerVerificaton method started");
		try {
			wait.until(ExpectedConditions
					.visibilityOf(driver.findElement(By.xpath("//*[text()='Pipeline triggered successfully!']"))));
			WebElement triggeredMesg = driver.findElement(By.xpath("//*[text()='Pipeline triggered successfully!']"));
			Thread.sleep(4000);

			if (triggeredMesg.isDisplayed() == true) {
				log.info("Pipeline triggered successfully!");
				Thread.sleep(4000);
				driver.navigate().refresh();
			} else {
				log.info("Pipeline NOT triggered!!!!!");
			}
		} catch (Exception e) {
			log.info("Pipeline triggered successfully popup verifycation");
		}
		log.info("pipelineTriggerVerificaton");
	}

	public void filterPipelineAndNavToExeHistory(String pipelineNames) throws InterruptedException {
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[starts-with(text(),' Show Recent Builds ')]"));
		driver.findElement(By.xpath("//*[starts-with(@class,'mat-mdc-input')]")).sendKeys(pipelineNames);
		WebElement element = driver.findElement(By.xpath("//*[text()='" + pipelineNames + "']//following::button[6]"));
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", element);
		log.info("open the pipeline execution history");
	}

	public void filterPipelineAndclickOnEdit(String pipelineNames) throws InterruptedException {
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[starts-with(text(),' Show Recent Builds ')]"));
		driver.findElement(By.xpath("//*[starts-with(@class,'mat-mdc-input')]")).sendKeys(pipelineNames);
		WebElement element = driver.findElement(By.xpath("//*[text()='" + pipelineNames + "']//following::button[7]"));
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", element);
		log.info("open the pipeline edit page");
	}

	public boolean openPipelinePluginVerifyEncrpt(String plugin) {
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[contains(text(),'" + plugin + "')])[1]"))))
				.click();
		WebElement textField = driver.findElement(By.xpath("//input[contains(@id,'string_password_')]"));
		String encryptedText = textField.getAttribute("value");
		boolean flag;
		if (encryptedText.contains("${SECRET")) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	public void filterPipelineAndClickClonePiepline(String pipelineNames) throws InterruptedException {
		Thread.sleep(4000);
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//*[starts-with(text(),' Show Recent Builds ')]"))));

		driver.findElement(By.xpath("//*[starts-with(@class,'mat-mdc-input')]")).sendKeys(pipelineNames);
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//*[text()='" + pipelineNames + "']//following::button[8]")))).click();
	}

	public void filterPipelineAndClickViewSchedulee(String pipelineNames) throws InterruptedException {
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[starts-with(text(),' Show Recent Builds ')]"));

		driver.findElement(By.xpath("//*[starts-with(@class,'mat-mdc-input')]")).sendKeys(pipelineNames);

		WebElement element = driver.findElement(By.xpath("//*[text()='" + pipelineNames + "']//following::button[4]"));
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", element);
	}

	public void schedulePipeline(String scheduleName, String WorkerNames, String minDetails)
			throws InterruptedException {
		PipelineHelper ph = new PipelineHelper(driver);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text()='New Schedule']"))))
				.click();
		ph.workerSelectionInPipelineTrigger(WorkerNames);
		wait.until(
				ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text()=' Schedule Pipeline ']"))))
				.click();

		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//*[starts-with(@class,'mat-mdc-input')]"))))
				.sendKeys(scheduleName);

		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//*[text()='Every ']//following::div[4]")))).click();

		clickOnElement1(minDetails);

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()=' Confirm ']")))).click();
	}

	public void updateSchedulePipeline(String scheduleName, String WorkerNames, String minDetails)
			throws InterruptedException {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("document.body.style.zoom = '80%';");
		Thread.sleep(4000);

		PipelineHelper ph = new PipelineHelper(driver);
		System.out.println("//td[normalize-space(text())='" + scheduleName + "']//following::mat-icon[1]");
		
		WebElement element22 = driver.findElement(By.xpath("(//*[text()='Action']//following::span[1])"));
		JavascriptExecutor executor111 = (JavascriptExecutor) driver;
		executor111.executeScript("arguments[0].click();", element22);

		Thread.sleep(4000);
		
		driver.navigate().refresh();
		
		WebElement update = wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Update Schedule ']"))));
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", update);
		

		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[text()='Every ']//following::div[4]")))).click();

		clickOnElement1(minDetails);

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Update ']"))))
				.click();
	}

	
	public void deleteSchedulePipeline(String scheduleName) throws InterruptedException {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("document.body.style.zoom = '80%';");
		Thread.sleep(4000);
		
		System.out.println("//td[normalize-space(text())='" + scheduleName + "']//following::mat-icon[2]");
		
		driver.navigate().refresh();
		
		WebElement update = wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[text()='Action']//following::span[5])"))));
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", update);
		
//		wait.until(ExpectedConditions.visibilityOf(driver
//				.findElement(By.xpath("//td[normalize-space(text())='" + scheduleName + "']//following::mat-icon[2]"))))
//				.click();
		
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()=' Delete ']")))).click();
	}

	public boolean verifyDeleteSchedulePipeline(String scheduleName) throws InterruptedException {
		boolean flag = false;
		try {
			Boolean element = driver.findElement(By.xpath("//td[normalize-space(text())='" + scheduleName + "']"))
					.isDisplayed();
			flag = false;
		} catch (NoSuchElementException e) {
			flag = true;
		}
		return flag;
	}

	@FindBy(xpath = "//button[@mattooltipclass='option-tooltip']")
	WebElement Arrowsba;

	public void pipelineRunningVerifycation(String pipeLineNames, String appAndWorkerName) throws InterruptedException {
		log.info("pipelineRunningVerifycation method open");
		Thread.sleep(2000);
		Arrowsba.click();
		filterPipelineAndNavToExeHistory(pipeLineNames);
		driver.findElement(By.xpath("//*[normalize-space(text())='" + appAndWorkerName + "']//following::td[3]"));
		WebElement Status1 = driver
				.findElement(By.xpath("//*[normalize-space(text())='" + appAndWorkerName + "']//following::td[3]"));
		Thread.sleep(4000);
		if (Status1.getText().equalsIgnoreCase("In Progress")) {
			log.info("Status is In Progress");
			Thread.sleep(3000);
		} else {
			log.info("Status is Not In Progress");
		}
		log.info("pipelineRunningVerifycation method closed");
	}

	public String[] buildAndReRunIdStore() throws InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(3000);
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//tr[1]//td[5]//following::div[1]//div"))));
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//tr[1]//td[3]//following::div[1]//div"))));
		String RerunID = driver.findElement(By.xpath("//tr[1]//td[5]//following::div[1]//div")).getText();
		String buildID = driver.findElement(By.xpath("//tr[1]//td[3]//following::div[1]//div")).getText();
		String[] BuildAndRerunIDs = { buildID, RerunID };
		return BuildAndRerunIDs;
	}

	public void navigateToPipelineExeHistory(String piplineName) throws IOException, InterruptedException {
		navigateToPipeline();
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//*[starts-with(@class,'mat-mdc-input')]"))))
				.sendKeys(piplineName);
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//*[text()='" + piplineName + "']//following::button[6]"))))
				.click();
	}

	public void reRun1() throws InterruptedException, IOException {
		Thread.sleep(8000);
		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//tr[1]//td[8]")))).click();
		Actions action = new Actions(driver);
		WebElement element1 = driver.findElement(By.xpath("//*[text()=' Rerun Pipeline ']"));
		action.moveToElement(element1).click().build().perform();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Trigger Pipeline ']")));
		WebElement Trigger = driver.findElement(By.xpath("//*[text()=' Trigger Pipeline ']"));
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		Thread.sleep(10000);
	}

	public String getBuildID(String appAndWorkerName) throws InterruptedException {
		log.info("getBuildID method open");
		driver.findElement(By.xpath("//*[normalize-space(text())='" + appAndWorkerName + "']//following::td[1]"));
		log.info("checking the build details");
		WebElement Status1 = driver
				.findElement(By.xpath("//*[normalize-space(text())='" + appAndWorkerName + "']//following::td[1]"));
		Thread.sleep(4000);
		String buildDetails = Status1.getText();
		log.info("build details ==> " + buildDetails);
		return buildDetails;
	}

	public boolean pipelineRunningSuccessVerifycation(String pipeLineNames, String appAndWorkerName)
			throws InterruptedException {
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(800));
		log.info("pipelineRunningSuccessVerifycation method open");
		driver.findElement(By.xpath("//button[@mattooltipclass='option-tooltip']")).click();
		log.info("after click on tool tip");
		filterPipelineAndNavToExeHistory(pipeLineNames);
		log.info("after filterpipeline and nav to execution History");
		WebElement Status1 = driver
				.findElement(By.xpath("//*[normalize-space(text())='" + appAndWorkerName + "']//following::td[3]"));
		Thread.sleep(4000);
		boolean flag = true;
		if (Status1.getText().equalsIgnoreCase("Success")) {
			log.info("Status is  Success");
			flag = true;
			Thread.sleep(4000);
		} else {
			flag = false;

			log.info("Status is Not  Success ");
		}
		return flag;
	}

	public void changingJiraStatus(Boolean status, String issueUpdatedstatus) throws InterruptedException, IOException {

		if (status == false) {
			reRun1(issueUpdatedstatus);
			Thread.sleep(4000);
		} else {

		}
	}

	public void reRun() throws InterruptedException, IOException {
		Thread.sleep(8000);
		Thread.sleep(4000);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("document.body.style.zoom = '80%';");
		Thread.sleep(4000);
		log.info("ZoomOut Done");
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath("//*[@mattooltip='More Options']"));

		Thread.sleep(4000);
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
		WebElement reRun = driver.findElement(By.xpath("//*[text()=' Rerun Pipeline ']"));
		JavascriptExecutor executor3 = (JavascriptExecutor) driver;
		executor3.executeScript("arguments[0].click();", reRun);

		Thread.sleep(7000);
		WebElement Trigger = driver.findElement(By.xpath("//*[text()=' Trigger Pipeline ']"));
		JavascriptExecutor executor4 = (JavascriptExecutor) driver;
		executor4.executeScript("arguments[0].click();", Trigger);
		Thread.sleep(10000);
	}

	public void reRun1(String Status) throws InterruptedException, IOException {

		Thread.sleep(8000);
		driver.navigate().refresh();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[1]//td[8]")).click();
		Actions action = new Actions(driver);
		WebElement element1 = driver.findElement(By.xpath("//*[text()=' Rerun Pipeline ']"));
		action.moveToElement(element1).click().build().perform();
		Thread.sleep(5000);
		WebElement ele = wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//div[text()='Pipeline Flow']//following::input[2]"))));
		ele.clear();
		ele.sendKeys(Status);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Trigger Pipeline ']")));
		WebElement Trigger = driver.findElement(By.xpath("//*[text()=' Trigger Pipeline ']"));
		Thread.sleep(3000);
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", Trigger);
		Thread.sleep(8000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()=' Filter ']"))));
		Thread.sleep(3000);
	}

	public boolean verifyRejectLog(String comment) throws InterruptedException {
		log.info("verifyRejectLog");
		boolean flag = true;
		try {
			Boolean rejectcmt = driver.findElement(By.xpath("//*[text()=' Rejection Comment : " + comment + " ']"))
					.isDisplayed();
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		log.info("verify Reject Log completed");
		return flag;
	}

	public String getLog(String comment) throws InterruptedException {
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		WebElement element2 = driver.findElement(By.xpath("//*[contains(text(),'" + comment + "')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element2);
		Thread.sleep(2000);
		String log = driver.findElement(By.xpath("//*[contains(text(),'" + comment + "')]")).getText();
		return log;
	}

	public String getLogResult() throws InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//*[starts-with(@class,'logLine leds-caption WARN')]"))));
		String log = driver.findElement(By.xpath("//*[starts-with(@class,'logLine leds-caption WARN')]")).getText();
		return log;
	}

	public String filterLog(String log, String keys) throws InterruptedException {
		String removeActual = log.replace("[GatingFailed] Plugin execution marked as failed for the below reasons", "");
		String remove1 = removeActual.replace("Actual result:{", "");
		String[] value = remove1.split(",");
		HashMap<String, String> map = new HashMap<String, String>();

		for (String string : value) {
			String[] value1 = string.split(":");
			String key = value1[0];
			String val = value1[1];
			map.put(key, val);
		}
		String keyValue = map.get(keys);
		return keyValue;
	}

	public boolean verifycationFlag(String value, String expected) throws InterruptedException {

		boolean flag;
		if (value.equalsIgnoreCase(expected)) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	public void navLogsDetails(String pipeLineNames) throws InterruptedException {
		log.info("navLogsDetails page");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("document.body.style.zoom = '80%';");
		Thread.sleep(4000);
		log.info("ZoomOut Done");

		WebElement element = driver.findElement(By.xpath("//*[normalize-space(text())='" + pipeLineNames
				+ "']//following::mat-icon[@mattooltip='More Options']"));
		Thread.sleep(4000);
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
		WebElement logs = driver.findElement(By.xpath("//*[text()=' Logs ']"));
		JavascriptExecutor executor3 = (JavascriptExecutor) driver;
		executor3.executeScript("arguments[0].click();", logs);
		log.info("Pipeline Log page");
	}

	public void navResolveGPTPage() throws InterruptedException {
		log.info("navResolveGPT page");
		WebElement element = driver.findElement(By.xpath("//div[text()='ResolveGPT']"));
		Thread.sleep(4000);
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
	}

	public void navPipelineDetailsPage() throws InterruptedException {
		log.info("nav Pipeline Details page");
		Thread.sleep(2000);
		WebElement element1 = driver
				.findElement(By.xpath("//*[@class='mat-icon notranslate leds-icons leds-pipeline mat-icon-no-color']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		WebElement element = driver
				.findElement(By.xpath("//*[@class='mat-icon notranslate leds-icons leds-pipeline mat-icon-no-color']"));
		Thread.sleep(4000);
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
	}

	public ArrayList<String> getSevDataFromSonar() throws InterruptedException {

		String value = decode("MDExQFRhc2tvdmVy");
		driver.findElement(By.id("login")).sendKeys("satheshkumar.m");
		driver.findElement(By.id("password")).sendKeys(value);
		Thread.sleep(4000);
		driver.findElement(By.xpath("//button[@class='button']")).click();
		driver.findElement(By.xpath("//a[text()='MF-Metrics']")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("(//a[text()='Issues'])[2]")).click();
		Thread.sleep(4000);
		List<WebElement> element = driver
				.findElements(By.xpath("//*[text()='Severity']//following::span[@class='facet-stat']"));

		HashMap<Integer, String> map = new HashMap<Integer, String>();

		for (int i = 0; i < element.size(); i++) {
			map.put(i, element.get(i).getText());
		}

		ArrayList<String> list = new ArrayList<String>();

		list.add(map.get(2));
		list.add(map.get(4));
		list.add(map.get(1));

		Thread.sleep(2000);
		return list;
	}

	public void navToNewTab(String URL) throws InterruptedException {
		// Open a new tab
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.open('" + URL + "')");

		// Switch to the new tab
		for (String tab : driver.getWindowHandles()) {
			driver.switchTo().window(tab);
		}
	}

	public void switchToWindow() throws InterruptedException {
		// Open a new tab
		// Switch to the new tab
		for (String tab : driver.getWindowHandles()) {
			driver.switchTo().window(tab);
		}
	}

	public String getParentWindowSessionID() throws InterruptedException {
		String originalHandle = driver.getWindowHandle();
		System.out.println(originalHandle);
		return originalHandle;
	}

	public void navToParentTab() throws InterruptedException {
		PipelineHelper he = new PipelineHelper(driver);
		Set<String> windowHandles = driver.getWindowHandles();
		String parentWindowHandle = he.getParentWindowSessionID(); // Store parent window
		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(parentWindowHandle)) {
				driver.switchTo().window(windowHandle);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindowHandle);
	}

	public void navPipelineDetailsPage_SecurityTab() throws InterruptedException {
		log.info("nav Pipeline Details SecurityTab page");
		WebElement element = driver.findElement(By.xpath("//div[text()='Security']"));
		Thread.sleep(4000);
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", element);
		Thread.sleep(7000);
	}

	public void navPipelineDetailsPage_MaturityTab() throws InterruptedException {
		log.info("nav Pipeline Details SecurityTab page");
		WebElement element = driver.findElement(By.xpath("//div[text()='Maturity']"));
		Thread.sleep(4000);
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
	}

	public boolean verifyTwoArray(ArrayList<String> ArrayList1, ArrayList<String> ArrayList2)
			throws InterruptedException {
		log.info("verifyTwoArray");
		boolean flag;
		if (ArrayList1.equals(ArrayList2) == true) {
			log.info(" Array List are equal");
			flag = true;
		} else {
			log.info(" Array List are not equal");
			flag = false;
		}
		return flag;
	}

	public void navPipelineQualityTab() throws InterruptedException {
		log.info("nav PipelineQuvalityTab");
		WebElement element = driver.findElement(By.xpath("//*[text()='Quality']"));
		Thread.sleep(4000);
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
	}

	public ArrayList<String> fetch_Sonoar_Report_Quality()
			throws InterruptedException, HeadlessException, UnsupportedFlavorException, IOException {
		log.info("nav Pipeline Details page");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("document.body.style.zoom = '100%';");
		Thread.sleep(4000);
		log.info("ZoomOut Done");
		WebElement element = driver.findElement(By.xpath("//*[text()='Quality']"));
		element.click();
		ArrayList<String> listOfReport = new ArrayList<String>();
		for (int i = 2; i <= 4; i++) {
			String report = driver.findElement(By.xpath("(//*[@class='first-row'])[" + i + "]//span[1]")).getText();
			listOfReport.add(report);
		}
		return listOfReport;
	}

	public ArrayList<String> fetch_Fortity_SAST_Vulnerabilities()
			throws InterruptedException, HeadlessException, UnsupportedFlavorException, IOException {
		log.info("nav Pipeline Details page");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("document.body.style.zoom = '100%';");
		Thread.sleep(4000);
		log.info("ZoomOut Done");
		WebElement element = driver.findElement(By.xpath("//*[text()='Security']"));
		element.click();
		Thread.sleep(5000);
		ArrayList<String> listOfReport = new ArrayList<String>();
		for (int i = 0; i <= 4; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			try {
				String chartData = (String) js.executeScript("return chart[0].__ngContext__[33][" + i + "];");
				String[] value = chartData.split(" ");
				listOfReport.add(value[1]);
			} catch (Exception e) {
				System.out.println("its exception");
			}

		}
		return listOfReport;
	}

	public ArrayList<String> fetch_CodeQL_Analyze_Vulnerabilities()
			throws InterruptedException, HeadlessException, UnsupportedFlavorException, IOException {
		log.info("nav Pipeline Details page");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("document.body.style.zoom = '100%';");
		Thread.sleep(6000);
		log.info("ZoomOut Done");
		WebElement element = driver.findElement(By.xpath("//*[text()='Security']"));
		element.click();
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath("//*[text()=' - CodeQL-Analyze']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		ArrayList<String> listOfReport = new ArrayList<String>();
		for (int i = 0; i <= 4; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			try {
				String chartData = (String) js.executeScript("return chart[1].__ngContext__[33][" + i + "];");
				String[] value = chartData.split(" ");
				listOfReport.add(value[1]);
			} catch (Exception e) {
				System.out.println("its exception");
			}

		}
		return listOfReport;
	}

	public ArrayList<String> fetch_Fortify_Dast_Vulnerabilities()
			throws InterruptedException, HeadlessException, UnsupportedFlavorException, IOException {
		log.info("nav Pipeline Details page");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("document.body.style.zoom = '100%';");
		Thread.sleep(6000);
		log.info("ZoomOut Done");
		WebElement element = driver.findElement(By.xpath("//*[text()='Security']"));
		element.click();
		Thread.sleep(6000);
		WebElement element1 = driver.findElement(By.xpath("//*[text()=' - Fortify-Dast']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		ArrayList<String> listOfReport = new ArrayList<String>();
		for (int i = 0; i <= 4; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			try {
				String chartData = (String) js.executeScript("return chart[2].__ngContext__[33][" + i + "];");
				String[] value = chartData.split(" ");
				listOfReport.add(value[1]);
			} catch (Exception e) {
				System.out.println("its exception");
			}

		}
		return listOfReport;
	}

	public ArrayList<String> fetch_Trivy_Vulnerabilities()
			throws InterruptedException, HeadlessException, UnsupportedFlavorException, IOException {
		log.info("nav Pipeline Details page");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("document.body.style.zoom = '100%';");
		Thread.sleep(6000);
		log.info("ZoomOut Done");
		WebElement element = driver.findElement(By.xpath("//*[text()='Security']"));
		element.click();
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath("//*[text()=' - Trivy-Vulnerability-Scanner']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		ArrayList<String> listOfReport = new ArrayList<String>();
		for (int i = 0; i <= 4; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			try {
				String chartData = (String) js.executeScript("return chart[3].__ngContext__[33][" + i + "];");
				String[] value = chartData.split(" ");
				listOfReport.add(value[1]);
			} catch (Exception e) {
				log.info("its exception");
			}
		}
		return listOfReport;
	}

	public ArrayList<String> fetch_Jfrog_Vulnerabilities(int datacount)
			throws InterruptedException, HeadlessException, UnsupportedFlavorException, IOException {
		log.info("nav Pipeline Details page");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("document.body.style.zoom = '100%';");
		Thread.sleep(6000);
		log.info("ZoomOut Done");
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath("//*[text()=' - jfrog_xray']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(3000);
		ArrayList<String> listOfReport = new ArrayList<String>();
		for (int i = 0; i <= datacount; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			try {
				String chartData = (String) js.executeScript("return chart[7].__ngContext__[33][" + i + "];");
				String[] value = chartData.split(" ");
				listOfReport.add(value[1]);
			} catch (Exception e) {
				log.info("its exception");
			}
		}
		return listOfReport;
	}

	public ArrayList<String> fetch_Vulnerabilities(int datacount, String pluginName, int chartNumber)
			throws InterruptedException, HeadlessException, UnsupportedFlavorException, IOException {
		log.info("nav Pipeline Details page");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("document.body.style.zoom = '100%';");
		Thread.sleep(6000);
		log.info("ZoomOut Done");
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath("//*[text()='" + pluginName + "']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(3000);
		ArrayList<String> listOfReport = new ArrayList<String>();
		for (int i = 0; i <= datacount; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			try {
				String chartData = (String) js
						.executeScript("return chart[" + chartNumber + "].__ngContext__[33][" + i + "];");
				String[] value = chartData.split(" ");
				listOfReport.add(value[1]);
			} catch (Exception e) {
				log.info("its exception");
			}
		}
		Collections.sort(listOfReport);
		return listOfReport;
	}

	public ArrayList<String> fetch_blackduck_Vulnerabilities(int datacount, int chartDetails)
			throws InterruptedException, HeadlessException, UnsupportedFlavorException, IOException {
		log.info("nav Pipeline Details page");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("document.body.style.zoom = '100%';");
		Thread.sleep(6000);
		log.info("ZoomOut Done");
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath("//*[text()=' Operational Risks ']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(3000);
		ArrayList<String> listOfReport = new ArrayList<String>();
		for (int i = 0; i <= datacount; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			try {
				String chartData = (String) js
						.executeScript("return chart[" + chartDetails + "].__ngContext__[33][" + i + "];");
				String[] value = chartData.split(" ");
				listOfReport.add(value[1]);
			} catch (Exception e) {
				log.info("its exception");
			}
		}
		return listOfReport;
	}

	public ArrayList<String> fetch_Trend_Code_violation_trend() throws InterruptedException {
		Thread.sleep(5000);
		log.info("nav Trend_Code_violation_trend page");
		ArrayList<String> listOfReport = new ArrayList<String>();
		for (int i = 0; i <= 2; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String chartData = (String) js.executeScript(
					"return document.getElementsByClassName('chartjs-render-monitor')[1].__ngContext__[43][" + i
							+ "].data[1];");
			listOfReport.add(chartData);
		}
		return listOfReport;
	}

	public ArrayList<String> fetch_Security_SonarQube_Vulnerabilities_Details() throws InterruptedException {
		log.info("nav Trend_Code_violation_trend page");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("document.body.style.zoom = '100%';");
		Thread.sleep(6000);
		ArrayList<String> listOfReport = new ArrayList<String>();
		for (int i = 1; i <= 3; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String chartData = (String) js.executeScript("return chart[7].__ngContext__[33][" + i + "];");
			String[] newTest = chartData.split(" ");
			listOfReport.add(newTest[1]);
		}
		return listOfReport;
	}

	public ArrayList<String> fetch_FortifySAST_Vulnerabilities_Details() throws InterruptedException {
		Thread.sleep(5000);
		log.info("nav FortifySAST_Vulnerabilities_Details page");
		ArrayList<String> listOfReport = new ArrayList<String>();
		for (int i = 0; i <= 4; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String chartData = (String) js.executeScript("return chart[2].__ngContext__[33][" + i + "];");
			String[] newTest = chartData.split(" ");
			listOfReport.add(newTest[1]);
		}
		return listOfReport;
	}

	public ArrayList<String> fetch_FortifySAST_Vulnerabilities_Details_FromPlatfromlevel(String nameOfthePipeline)
			throws InterruptedException {
		Thread.sleep(5000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//span[normalize-space(text())='" + nameOfthePipeline + "']")));
		Thread.sleep(3000);
		log.info("nav FortifySAST_Vulnerabilities_Details page");
		ArrayList<String> listOfReport = new ArrayList<String>();
		for (int i = 1; i <= 5; i++) {
			WebElement elements = driver.findElement(By
					.xpath("//span[normalize-space(text())='" + nameOfthePipeline + "']//following::span[" + i + "]"));
			listOfReport.add(elements.getText());
		}
		return listOfReport;
	}

	public ArrayList<String> fetch_Vulnerabilities_Details_FromPlatfromlevel_Trends(String trendsName)
			throws InterruptedException {
		Thread.sleep(5000);
		log.info("fetch_Vulnerabilities_Details_FromPlatfromlevel_Trends");
		ArrayList<String> listOfReport = new ArrayList<String>();
		for (int i = 0; i <= 4; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String chartData = (String) js.executeScript("return document.evaluate(\"//div[normalize-space(text())='"
					+ trendsName
					+ "']//following::canvas[1]\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.__ngContext__[43]["
					+ i + "].data[0];");
			listOfReport.add(chartData);
		}
		return listOfReport;
	}

	public ArrayList<String> fetch_Sonar_Vulnerabilities_Details_FromPlatfromlevel(String nameOfthePipeline)
			throws InterruptedException {
		Thread.sleep(5000);
		log.info("nav FortifySAST_Vulnerabilities_Details page");
		ArrayList<String> listOfReport = new ArrayList<String>();
		for (int i = 2; i <= 4; i++) {
			WebElement elements = driver.findElement(By
					.xpath("//span[normalize-space(text())='" + nameOfthePipeline + "']//following::span[" + i + "]"));
			listOfReport.add(elements.getText());
		}
		return listOfReport;
	}

	public ArrayList<String> fetch_Jfrog_Details_FromPlatfromlevel(String nameOfthePipeline)
			throws InterruptedException {
		Thread.sleep(5000);
		log.info("nav _Jfrog_Vulnerabilities_Details page");
		ArrayList<String> listOfReport = new ArrayList<String>();
		for (int i = 1; i <= 5; i++) {
			WebElement elements = driver.findElement(By
					.xpath("//span[normalize-space(text())='" + nameOfthePipeline + "']//following::span[" + i + "]"));
			listOfReport.add(elements.getText());
		}
		return listOfReport;
	}

	public ArrayList<String> fetch_chart_Details_FromPlatfromlevel(String nameOfthePipeline, int startcount, int count)
			throws InterruptedException {
		Thread.sleep(5000);
		log.info("nav _Jfrog_Vulnerabilities_Details page");
		ArrayList<String> listOfReport = new ArrayList<String>();
		for (int i = startcount; i <= count; i++) {
			WebElement elements = driver.findElement(By
					.xpath("//span[normalize-space(text())='" + nameOfthePipeline + "']//following::span[" + i + "]"));
			listOfReport.add(elements.getText());
		}
		Collections.sort(listOfReport);
		return listOfReport;
	}

	public ArrayList<String> fetch_Blockduck_Vulnerabilities_Details_FromPlatfromlevel(String nameOfthePipeline,
			int count) throws InterruptedException {
		Thread.sleep(5000);
		log.info("nav fetch_Blockduck_Vulnerabilities_Details_FromPlatfromlevel page");
		ArrayList<String> listOfReport = new ArrayList<String>();
		for (int i = 2; i <= count; i++) {
			WebElement elements = driver.findElement(By
					.xpath("//span[normalize-space(text())='" + nameOfthePipeline + "']//following::span[" + i + "]"));
			listOfReport.add(elements.getText());
		}
		return listOfReport;
	}

	public ArrayList<String> fetch_Codeql_Vulnerabilities_Details() throws InterruptedException {
		log.info("nav FortifySAST_Vulnerabilities_Details page");
		Thread.sleep(5000);
		ArrayList<String> listOfReport = new ArrayList<String>();
		for (int i = 0; i <= 4; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String chartData = (String) js.executeScript("return chart[8].__ngContext__[33][" + i + "];");
			String[] newTest = chartData.split(" ");
			listOfReport.add(newTest[1]);
		}
		return listOfReport;
	}

	public ArrayList<String> fetch_Fortify_Dast_Vulnerabilities_Details() throws InterruptedException {
		log.info("nav FortifySAST_Vulnerabilities_Details page");
		Thread.sleep(6000);
		ArrayList<String> listOfReport = new ArrayList<String>();
		for (int i = 0; i <= 4; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String chartData = (String) js.executeScript("return chart[3].__ngContext__[33][" + i + "];");
			String[] newTest = chartData.split(" ");
			listOfReport.add(newTest[1]);
		}
		return listOfReport;
	}

	public ArrayList<String> fetch_Trivy_Vulnerability_Details() throws InterruptedException {
		log.info("nav Trivy_Vulnerabilities_Details page");
		Thread.sleep(6000);
		ArrayList<String> listOfReport = new ArrayList<String>();
		for (int i = 0; i <= 4; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String chartData = (String) js.executeScript("return chart[5].__ngContext__[33][" + i + "];");
			String[] newTest = chartData.split(" ");
			listOfReport.add(newTest[1]);
		}
		return listOfReport;
	}

	public ArrayList<String> fetch_Blackduck_Vulnerability_Details(int count, int chartCount)
			throws InterruptedException {
		log.info("nav fetch_Blackduck_Vulnerability_Details page");
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath("//*[text()=' Blackduck Operational Risks ']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(6000);
		ArrayList<String> listOfReport = new ArrayList<String>();
		for (int i = 0; i <= count; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String chartData = (String) js
					.executeScript("return chart[" + chartCount + "].__ngContext__[33][" + i + "];");
			String[] newTest = chartData.split(" ");
			listOfReport.add(newTest[1]);
		}
		return listOfReport;
	}

	public ArrayList<String> fetch_jfrog_Vulnerability_Details(int count) throws InterruptedException {
		log.info("nav fetch_Blackduck_Vulnerability_Details page");
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath("//*[text()=' Jfrog_xray vulnerabilities ']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(6000);
		ArrayList<String> listOfReport = new ArrayList<String>();
		for (int i = 0; i <= count; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String chartData = (String) js.executeScript("return chart[9].__ngContext__[33][" + i + "];");
			String[] newTest = chartData.split(" ");
			listOfReport.add(newTest[1]);
		}
		return listOfReport;
	}

	public ArrayList<String> fetch_Vulnerability_Details(int count, String pluginName, int chartCount)
			throws InterruptedException {
		log.info("nav fetch_Blackduck_Vulnerability_Details page");
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath("//*[text()='" + pluginName + "']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(6000);
		ArrayList<String> listOfReport = new ArrayList<String>();
		for (int i = 0; i <= count; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String chartData = (String) js
					.executeScript("return chart[" + chartCount + "].__ngContext__[33][" + i + "];");
			String[] newTest = chartData.split(" ");
			listOfReport.add(newTest[1]);
		}
		Collections.sort(listOfReport);
		return listOfReport;
	}

	public ArrayList<String> fetch_PDM_Vulnerability_Details() throws InterruptedException {
		log.info("nav Trivy_Vulnerabilities_Details page");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("document.body.style.zoom = '100%';");
		Thread.sleep(8000);
		ArrayList<String> listOfReport = new ArrayList<String>();
		for (int i = 0; i <= 4; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String chartData = (String) js.executeScript("return chart[11].__ngContext__[33][" + i + "];");
			String[] newTest = chartData.split(" ");
			listOfReport.add(newTest[1]);
		}
		return listOfReport;
	}

	public ArrayList<String> fetch_Platformlevel_blackduck_Vulnerability_Details(int count, int chartcount)
			throws InterruptedException {
		log.info("nav fetch_Platformlevel_blackduck_Vulnerability_Details page");
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath("//*[text()=' Blackduck License Risks ']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(6000);
		ArrayList<String> listOfReport = new ArrayList<String>();
		for (int i = 0; i <= count; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String chartData = (String) js
					.executeScript("return chart[" + chartcount + "].__ngContext__[33][" + i + "];");
			String[] newTest = chartData.split(" ");
			listOfReport.add(newTest[1]);
		}
		return listOfReport;
	}

	public String fetch_Maturity_Details(String value) throws InterruptedException {
		log.info("nav DevSecOps_Maturity_Details page");
		Thread.sleep(6000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Object longValue = js.executeScript("return " + value);
		String stringValue = String.valueOf(longValue);
		return stringValue;
	}

	public String fetch_Maturity_MM_Details(String value) throws InterruptedException {
		log.info("nav Maturity_Details page");
		Thread.sleep(6000);
		WebElement element = driver.findElement(By.xpath("//*[text()=' " + value + " ']//following::div[5]"));
		return element.getText();
	}

	public String fetch_Environment_Utilisation_Details() throws InterruptedException {
		log.info("nav Environment_Utilisation_Details page");
		Thread.sleep(6000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Object longValue = js.executeScript(
				"return document.getElementsByClassName('chartjs-render-monitor')[0].__ngContext__[36][0].data[1];");
		String stringValue = String.valueOf(longValue);
		return stringValue;
	}

	public int scheduleVerfycation() throws InterruptedException {
		driver.navigate().refresh();
		
		List<WebElement> element = wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath(
				"//td[@class='mat-mdc-cell mdc-data-table__cell cdk-cell cdk-column-workflow mat-column-workflow ng-star-inserted']"))));
		return element.size();
	}

	public void trminatePipeline(String pipeLineNames) throws InterruptedException {

		log.info("trminatePipeline page");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("document.body.style.zoom = '80%';");
		Thread.sleep(4000);
		log.info("ZoomOut Done");

		WebElement element1 = driver.findElement(By.xpath("//*[normalize-space(text())='" + pipeLineNames
				+ "']//following::mat-icon[@mattooltip='More Options']"));
		Thread.sleep(4000);
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", element1);
		Thread.sleep(2000);
		WebElement element2 = driver.findElement(By.xpath("//*[text()=' Terminate ']"));
		Thread.sleep(4000);
		JavascriptExecutor executor2 = (JavascriptExecutor) driver;
		executor2.executeScript("arguments[0].click();", element2);
		Thread.sleep(5000);
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()=' Terminate ']")))).click();
//		 Thread.sleep(5000);
		WebElement ell = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()=' Terminate ']"))));
		JavascriptExecutor executor4 = (JavascriptExecutor) driver;
		executor4.executeScript("arguments[0].click();", ell);
		Thread.sleep(8000);
	}

	
	public void pipelineRunningstatusVerifycation(String pipeLineNames, String appAndWorkerName)
			throws InterruptedException {
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//button[@mattooltipclass='option-tooltip']"))))
				.click();
		filterPipelineAndNavToExeHistory(pipeLineNames);
		wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(By.xpath("//*[normalize-space(text())='" + appAndWorkerName + "']//following::td[3]"))));
		WebElement Status1 = driver
				.findElement(By.xpath("//*[normalize-space(text())='" + appAndWorkerName + "']//following::td[3]"));
		Thread.sleep(4000);
		if (Status1.getText().equalsIgnoreCase("Aborted")) {
			log.info("Status is Aborted");
			Thread.sleep(4000);
		} else {
			log.info("Status is Not Aborted");
		}
	}

	public String logsVerifycationDetails(String StageDetails) throws InterruptedException {
		WebElement logslist = driver.findElement(By.xpath("//*[starts-with(text(),'" + StageDetails + "')]"));
		String[] logs = logslist.getText().split(":");
		return logs[0].trim();
	}

	public boolean verfiyMethods(List workflowList, List workflowList1) {

		ArrayList<String> firstlist = new ArrayList<String>();
		ArrayList<String> secondList = new ArrayList<String>();

		ArrayList<String> sorceList = new ArrayList<String>();
		ArrayList<String> targetList = new ArrayList<String>();

		firstlist.addAll(workflowList);
		secondList.addAll(workflowList1);

		for (String webElement : firstlist) {
			sorceList.add(webElement.trim());
		}
		for (String webElement1 : secondList) {
			targetList.add(webElement1.replace("(", "").replace(")", "").trim());
		}
		boolean boolval = sorceList.equals(targetList);
		log.info(boolval);
		return boolval;

	}

	public void naviPipelineDetails() {
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//*[text()='Logs']//preceding::button[11]")))).click();
	}

	public void pipelineAuditPageNav(String pipeLineNames) throws InterruptedException {
		navLogsDetails(pipeLineNames);
		naviPipelineDetails();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='Audit']")))).click();
	}

	public boolean VerifyActionRows(String row, String actionName) throws InterruptedException {
		Thread.sleep(2000);
		ApplicationView av = new ApplicationView(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//div[text()=' Audit Logs ' or text()=' Audit Logs - Admin View ']")));
		av.itemPerpage();
		String action = wait
				.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//tr[" + row + "]//td[3]"))))
				.getText();
		log.info(action);
		boolean flag = true;
		if (action.equalsIgnoreCase(actionName)) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	public boolean VerifyActionRowsWithoutItemperPage(String row, String actionName) throws InterruptedException {
		Thread.sleep(1000);
		ApplicationView av = new ApplicationView(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//div[text()=' Audit Logs ' or text()=' Audit Logs - Admin View ']")));
		String action = wait
				.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//tr[" + row + "]//td[3]"))))
				.getText();
		log.info(action);
		boolean flag = true;
		if (action.equalsIgnoreCase(actionName)) {
			flag = true;
		} else {
			flag = false;
		}

		return flag;
	}

	public boolean VerifyActionRowsWithoutItemperPagePlatformLevel(String accoutDetails, String actionName)
			throws InterruptedException {
		Thread.sleep(1000);
		List<WebElement> elementssss = driver
				.findElements(By.xpath("//*[normalize-space(text())='" + accoutDetails + "']//following::td[2]"));
		boolean flag = true;
		for (WebElement webElement : elementssss) {
			log.info(webElement.getText());
			if (webElement.getText().equalsIgnoreCase(actionName)) {
				flag = true;
				break;
			} else {
				flag = false;
			}
		}

		return flag;
	}

	public void scrollDownToAutilogs() throws InterruptedException {
		ApplicationView av = new ApplicationView(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//div[text()=' Audit Logs ']")));
		av.itemPerpage();
	}

	public boolean VerifyActionRowsForAll(String row, String actionName) throws InterruptedException {
		String action = wait
				.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//tr[" + row + "]//td[3]"))))
				.getText();

		boolean flag = true;
		if (action.equalsIgnoreCase(actionName)) {
			flag = true;
		} else {
			flag = false;
		}

		return flag;
	}

	public boolean VerifyActionRowsWithoutAddPipline(String row, String piplineName) {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//div[text()=' Audit Logs ']")));

		String action = wait
				.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//tr[" + row + "]//td[4]"))))
				.getText();

		String vl = piplineName.replace("_", "/");

		boolean flag = true;
		if (action.equalsIgnoreCase(vl)) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	public String VerifyView(String Actions, String pipeLineNames) throws Exception {

		String vl = pipeLineNames.replace("_", "/");
		String[] verifyvalue = pipeLineNames.split("/");

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[normalize-space(text())='" + Actions
				+ "']//following::td[normalize-space(text())='" + vl + "']//following::span[1]")))).click();

		String viewValues = wait.until(
				ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[starts-with(@id,'mat-dialog')]"))))
				.getText();

		return viewValues;
	}

	public String VerifyDynamicRowView(int row, String Actions, String pipeLineNames) throws Exception {

		String vl = pipeLineNames.replace("_", "/");
		String[] verifyvalue = pipeLineNames.split("/");

		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//tr[" + row + "]//td[normalize-space(text())='" + Actions
						+ "']//following::td[normalize-space(text())='" + vl + "']//following::span[1]"))))
				.click();

		String viewValues = wait.until(
				ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[starts-with(@class,'mat-mdc-dialog-surface mdc-dialog__surface')]"))))
				.getText();

		return viewValues;
	}

	public String VerifyDynamicRowViewPortfolio(int row, String Actions, String pipeLineNames) throws Exception {

		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//tr[" + row + "]//td[normalize-space(text())='" + Actions
						+ "']//following::td[normalize-space(text())='" + pipeLineNames + "']//following::span[1]"))))
				.click();
		String viewValues = wait.until(
				ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[starts-with(@id,'mat-dialog')]"))))
				.getText();
		return viewValues;
	}

	public void WriteNote(String path, String value) throws InterruptedException, IOException {

		FileWriter fw = new FileWriter(path);
		fw.write(value);
		fw.close();
	}

	public void VerifyViews(int rowNum, String status, String target, String key, String value) throws Exception {
		WorkerHelper wh = new WorkerHelper(driver);
		String path = System.getProperty("user.dir") + "//Data//testout.txt";
		String viewValues2 = VerifyDynamicRowView(rowNum, status, target);
		WriteNote(path, viewValues2);
		newone(path, key, value);
		clickOnOK();
		wh.delectFilescustom("\\Data", "testout.txt");
	}

	public void applicationVerifyViews(int rowNum, String status, String target, String key, String value)
			throws Exception {
		WorkerHelper wh = new WorkerHelper(driver);
		String path = System.getProperty("user.dir") + "//Data//testout.txt";
		String viewValues2 = VerifyDynamicRowView(rowNum, status, target);
		WriteNote(path, viewValues2);
		newoneWithout(path, key, value);
		clickOnOK();
		wh.delectFilescustom("\\Data", "testout.txt");
	}

	public static Boolean newone(String filePath, String key, String mapValue) throws Exception {
		Boolean flag = false;
		File file = new File(filePath);

		BufferedReader br = new BufferedReader(new FileReader(file));

		String st;

		while ((st = br.readLine()) != null)

			if (st.trim().contains("+")) {
				String kv = st.replace("\"", "").replace("+ ", "").trim();

				try {
					String[] ne = kv.split(":");
					String k = ne[0].trim();
					String v = ne[1].trim();
					HashMap<String, String> kvvalue = new HashMap<String, String>();
					kvvalue.put(k, v);
					Set<String> nn = kvvalue.keySet();
					String name = nn.toString().replace("[", "").replace("]", "");

					if (name.equalsIgnoreCase(key)) {
						String na = kvvalue.get(k);
						String newValue1 = na.toString().replace(",", "");
						if (newValue1.equalsIgnoreCase(mapValue)) {
							log.info("Yes! Data is there");
							log.info("Data is exits");
							br.close();
							flag = true;
							break;
						}
					} else {
						log.info("sorry! Data not there");
					}

				} catch (ArrayIndexOutOfBoundsException e) {
					log.info("The value is empty");
				}
			}
		return flag;
	}

	public Boolean newoneWithout(String filePath, String key, String mapValue) throws Exception {
		Boolean flag = false;
		File file = new File(filePath);

		BufferedReader br = new BufferedReader(new FileReader(file));

		String st;

		while ((st = br.readLine()) != null)

			try {
				String kv = st.replace("\"", "").replace("+ ", "").trim();
				String[] ne = kv.split(":");
				String k = ne[0].trim();
				String v = ne[1].trim();
				HashMap<String, String> kvvalue = new HashMap<String, String>();
				kvvalue.put(k, v);
				Set<String> nn = kvvalue.keySet();
				String name = nn.toString().replace("[", "").replace("]", "");

				if (name.equalsIgnoreCase(key)) {
					String na = kvvalue.get(k);
					String newValue1 = na.toString().replace(",", "");
					if (newValue1.equalsIgnoreCase(mapValue)) {
						log.info("Yes! Data is there");
						log.info("Data is exits");
						br.close();
						flag = true;
						break;
					}
				} else {
					log.info("sorry! Data not there");
				}

			} catch (ArrayIndexOutOfBoundsException e) {
				log.info("The value is empty");
			}
		return flag;
	}

	public void clickOnOK() {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='OK']")))).click();
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
}
