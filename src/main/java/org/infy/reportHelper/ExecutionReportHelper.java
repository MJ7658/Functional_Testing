package org.infy.reportHelper;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.infy.init.ExcelReader;
import org.infy.init.ListenersTest;
import org.infy.uiPages.ApplicationView;
import org.infy.uiPages.PipelineView;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

public class ExecutionReportHelper {
	static String Application;
	private WebDriver driver;
	static String applicationName = null;
	static String TriggeredBy = null;
	ExtentTest logger;
	private WebDriverWait wait;
	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(ExecutionReportHelper.class);
	public static String applicationNameToBeCreated;
	public static String applicationWorkflowNameToBeCreated;
	public static String applicationWorkflowNameCreated;
	public int count, flag;
	public static boolean statusVerify;

	public ExecutionReportHelper(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(8000));
	}

	By pipelineName = By.cssSelector("input[placeholder= 'Pipeline Name']");
	By Pipeline = By.xpath("//*[text()=' Pipeline ']");

	@Test(priority = 1)
	public void workerExectionChecking() throws InterruptedException, FileNotFoundException, IOException,
			ParseException, HeadlessException, UnsupportedFlavorException {
		driver = ListenersTest.driver;
		logger = ListenersTest.logger;
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		String appName = excelRead(0);
		String applicationName = appName;
		String protAndApplicationName = excelRead(9);
		String workerName1 = excelRead(1);
		String workerName = workerName1.replace("pipeline", "");
		av.openApplicationDashboard(applicationName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8000));
		Random ra = new Random();
		int n = ra.nextInt(50);
		String nameOftheApplication = workerName;

		driver.findElement(By.xpath("//span[text()='Add Worker']")).click();
		driver.findElement(By.xpath("//*[starts-with(@id,'mat-input-')]")).sendKeys(nameOftheApplication);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Save ']")));
		driver.findElement(By.xpath("//*[text()=' Save ']")).click();

		driver.findElement(By.xpath("//*[@class='mat-icon notranslate point pr-1 mr-1 mat-primary material-icons']"))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Download Worker ']")));
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[text()=' Download Worker ']")).click();

		driver.findElement(By.xpath(
				"//*[starts-with(text(),'https://idpapp.ad.infosys.com/registry/v1/qa/framework/windows')]//following::span"))
				.click();
		String myText = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.open('" + myText + "')");

		waitForTheExcelFileToDownload(driver, "agent");
		Thread.sleep(15000);

		wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Download Worker']//following::button")));
		driver.findElement(By.xpath("//*[text()='Download Worker']//following::button")).click();

		WebElement element = driver
				.findElement(By.xpath("//*[normalize-space(text())='" + protAndApplicationName + "']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();

		driver.findElement(
				By.xpath("//*[starts-with(text(),'" + " " + protAndApplicationName + " " + "')]//following::span[10]"))
				.click();
		String myAgentpath = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
		agentRunner(driver, myAgentpath, "agent");

	}

	public void triggerPipeline() throws InterruptedException, IOException {

		String workerName = excelRead(9).replace(excelRead(0) + "_", "");
		String appFirstName = excelRead(0);
		String pipelineName = "pipeline" + workerName;
		String newValue = appFirstName + "_" + workerName;

		PipelineView pv = new PipelineView(driver);
		pv.navigateToPipeline();
		Thread.sleep(4000);

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()=' Show Recent Builds ']"))));

		wait.until(
				ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[starts-with(@class,'mat-input')]"))))
				.sendKeys(pipelineName);
		wait.until(ExpectedConditions.elementToBeClickable(
				driver.findElement(By.xpath("//*[text()='" + pipelineName + "']//following::button[3]")))).click();

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text()='Worker Name']"))));
		Thread.sleep(4000);
		WebElement workerNames = driver.findElement(By.xpath("//span[text()='Worker Name']"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", workerNames);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[starts-with(@id,'mat-input')]")));
		driver.findElement(By.xpath("//*[starts-with(@id,'mat-input')]")).click();

		Thread.sleep(4000);
		driver.switchTo().activeElement().sendKeys(newValue);
		Thread.sleep(4000);
		WebElement ActiveInstance = driver.findElement(By.xpath("//*[text()='application:']"));
		Actions action = new Actions(driver);
		action.moveToElement(ActiveInstance).click().build().perform();

		Thread.sleep(3000);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Trigger Pipeline ']")));
		WebElement Trigger = driver.findElement(By.xpath("//*[text()=' Trigger Pipeline ']"));
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", Trigger);
	}

	public void backAndCheckHistory(String workerName) throws InterruptedException {
		String pipelineName = "pipeline" + workerName;
		String appFirstName = "WorkerAutomationTesting";
		String protAndApplicationName = appFirstName + "_" + workerName;
		for (int i = 0; i < 3; i++) {
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(
					By.xpath("//*[text()='" + " " + protAndApplicationName + " " + "']//following::td[4]"))));
			String status = driver
					.findElement(By.xpath("//*[text()='" + " " + protAndApplicationName + " " + "']//following::td[4]"))
					.getText();
			Thread.sleep(5000);
			log.info(status);
			;

			if (status.contains("Failed") || status.contains("Success")) {
				wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath("//*[text()='" + " " + protAndApplicationName + " " + "']//following::td[5]")))
						.click();
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Rerun Pipeline ']"))).click();
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Trigger Pipeline ']")));
				WebElement Trigger = driver.findElement(By.xpath("//*[text()=' Trigger Pipeline ']"));
				JavascriptExecutor executor1 = (JavascriptExecutor) driver;
				executor1.executeScript("arguments[0].click();", Trigger);
			} else {
				wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(
						"//button[@class='mat-focus-indicator mat-tooltip-trigger fab-button mat-mini-fab mat-button-base mat-primary']"))))
						.click();
				Thread.sleep(2000);
				wait.until(ExpectedConditions
						.visibilityOf(driver.findElement(By.xpath("//*[starts-with(@class,'mat-input')]"))))
						.sendKeys(pipelineName);
				wait.until(ExpectedConditions.visibilityOf(
						driver.findElement(By.xpath("//*[text()='" + pipelineName + "']//following::button[6]"))))
						.click();
			}
		}
	}

	public void reRun() throws InterruptedException, IOException {
		ExcelReader read = new ExcelReader();
		String pipelineName = read.excelRead(1);
		String appFirstName = read.excelRead(0);
		String workerName = excelRead(9).replace(excelRead(0) + "_", "");
		Thread.sleep(8000);
		driver.navigate().refresh();
		Thread.sleep(3000);
		String protAndApplicationName = appFirstName + "_" + workerName;
		driver.findElement(By.xpath("//tr[1]//td[8]")).click();
		log.info(driver.findElement(By.xpath("//tr[1]//td[8]")));
		Actions action = new Actions(driver);
		WebElement element1 = driver.findElement(By.xpath("//*[text()=' Rerun Pipeline ']"));
		action.moveToElement(element1).click().build().perform();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Trigger Pipeline ']")));
		WebElement Trigger = driver.findElement(By.xpath("//*[text()=' Trigger Pipeline ']"));
		Thread.sleep(3000);
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", Trigger);
		Thread.sleep(8000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()=' Filter ']"))));
		Thread.sleep(3000);
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

	public static void delectExcel() throws IOException, InterruptedException {
		File[] file_Details = new File(System.getProperty("user.dir") + "\\Data\\").listFiles();

		for (File file : file_Details) {

			log.info(file.getName());

			if (file.getName().contains("agent")) {
				log.info("File name is..." + file.getName());
				file.deleteOnExit();
				if (file.getName().contains("agent")) {
					log.info("File is successfully deleted!");
				} else {
					log.info("Sorry, the file was not deleted.");
				}
			}
		}
	}

	public static void agentRunner(WebDriver driver, String myAgentpath, String filename)
			throws IOException, InterruptedException {
		String path1 = System.getProperty("user.dir") + "\\Data\\";
		Runtime rt = Runtime.getRuntime();
		rt.exec("cmd.exe /c cd \"" + path1 + "\" & start cmd.exe /k \"" + myAgentpath + "\"");
	}

	public static void waitForTheExcelFileToDownload(WebDriver driver, String fileName)
			throws IOException, InterruptedException {
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8000));
			wait.until((x) -> {
				File[] file_Detail = new File(System.getProperty("user.dir") + "\\Data\\").listFiles();
				for (File file : file_Detail) {
					if (file.getName().contains(fileName)) {
						log.info("Agent Downloaded");
						return true;
					}
				}
				log.info("Agent not Downloaded");
				return false;
			});
		}
	}

	public void pipelineCreation() throws InterruptedException, IOException {
		String workerName = excelRead(9).replace(excelRead(0) + "_", "");
		String approver = excelRead(7);
		PipelineView pv = new PipelineView(driver);
		pv.navigateToVisualEditor();
		createPipelineName(workerName);
		addStageInPipeline(workerName);
		createStepe();
		createApproveStepe(approver);
		createStepe1();
		pv.submitPipeline();
		Thread.sleep(6000);
	}

	public void cmdplug() {
		PipelineView pv = new PipelineView(driver);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(pv.searchPluginTextBox))).sendKeys("cmdexec");
		wait.until(ExpectedConditions.elementToBeClickable(pv.selectPlugin("cmdexec"))).click();
		pv.expandPluginDetails("cmdexec");
		driver.findElement(By.xpath("//*[text()='Command']//following::input[1]")).sendKeys("java -version");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void cmdplug1() throws InterruptedException {
		PipelineView pv = new PipelineView(driver);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(pv.searchPluginTextBox))).sendKeys("cmdexec");
		wait.until(ExpectedConditions.elementToBeClickable(pv.selectPlugin("cmdexec"))).click();
		pv.expandPluginDetails1("cmdexec");
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//*[text()='Command']//preceding::input[1]"))))
				.sendKeys("NewCMD");
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//*[text()='Command']//following::input[1]"))))
				.sendKeys("java -version");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public void approvePlug(String approverName) throws InterruptedException {
		PipelineView pv = new PipelineView(driver);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(pv.searchPluginTextBox))).sendKeys("approval");
		wait.until(ExpectedConditions.elementToBeClickable(pv.selectPlugin("approval"))).click();
		pv.expandPluginDetails("approval");
		Thread.sleep(5000);
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//*[text()='Step Name']//following::input[1]"))))
				.sendKeys(approverName);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Save ']"))))
				.click();
	}

	public By addStepButton = By.xpath("//mat-chip[contains(@id,'add-step')]");

	public void createStepe() throws InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1));
		wait.until(ExpectedConditions.elementToBeClickable(addStepButton)).click();
		cmdplug();
	}

	public void createStepe1() throws InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1));
		wait.until(ExpectedConditions.elementToBeClickable(addStepButton)).click();
		cmdplug1();
	}

	public void createApproveStepe(String approverName) throws InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1));
		wait.until(ExpectedConditions.elementToBeClickable(addStepButton)).click();
		Thread.sleep(5000);
		approvePlug(approverName);
	}

	public void createPipelineName(String workerName) throws InterruptedException, IOException {

		String appFirstName = excelRead(0);

		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1));

		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//*[text()=' Select an application && pipeline to start ']"))));
		Thread.sleep(5000);
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//*[text()='Configuration']//following::div[15]"))));
		WebElement appli = driver.findElement(By.xpath("//*[text()='Configuration']//following::div[15]"));
		Actions action = new Actions(driver);
		action.moveToElement(appli).doubleClick().build().perform();

		clickOnElement2(appFirstName);

		String pipelineNameis = "pipeline" + workerName;

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(pipelineName))).sendKeys(pipelineNameis);

	}

	public String environmentName() throws IOException {
		ExcelReader read = new ExcelReader();
		String environmentValue = read.excelRead(6);
		String newValue = environmentValue.replace(",", "");
		String[] newenvironmentValue = newValue.split(":");
		String newvalues = newenvironmentValue[0];
		String newvalues1 = newenvironmentValue[1];
		String Envalue = newvalues.replace(" ", "");
		return Envalue;
	}

	public By clickOnElement(String fieldName) {
		return By.xpath("//*[contains(text(),'" + fieldName + "')]");
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

	public void clickOnElement2(List<WebElement> element, String value) throws InterruptedException {
		for (WebElement webElement : element) {
			if (webElement.getText().equalsIgnoreCase(value)) {
				wait.until(ExpectedConditions.elementToBeClickable(webElement));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
				break;
			}
		}
	}

	public void clickOnElement1(String value) {
		List<WebElement> list = driver.findElements(By.xpath("//*[@class='mat-option-text']"));
		for (WebElement webElement : list) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
			if (webElement.getText().equalsIgnoreCase(value)) {
				wait.until(ExpectedConditions.elementToBeClickable(webElement));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
				break;
			}
		}
	}

	public void clickOnElement2(String value) throws InterruptedException {
		Thread.sleep(2000);
		driver.switchTo().activeElement().sendKeys(value);
		Thread.sleep(2000);
		List<WebElement> list = driver.findElements(By.xpath("//*[@class='mat-option-text']"));
		for (WebElement webElement : list) {
			if (webElement.getText().equalsIgnoreCase(value)) {
				wait.until(ExpectedConditions.elementToBeClickable(webElement));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
				break;
			}
		}
	}

	public By stageName = By.xpath("//*[@name='userInput']");
	public By environment = By.xpath(
			"//mat-label[normalize-space()='Environments']/parent::label/parent::span/preceding-sibling::mat-select");
	public By Worker = By
			.xpath("//mat-label[normalize-space()='Worker']/parent::label/parent::span/preceding-sibling::mat-select");

	public void addStageInPipeline(String worker) throws InterruptedException, IOException {
		ExcelReader read = new ExcelReader();
		Thread.sleep(3000);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' ADD STAGE ']"))).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(stageName))).sendKeys("Stage1");

		WebElement element = driver.findElement(Worker);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
		String appFirstName = excelRead(0);
		String newValue = appFirstName + "_" + worker;

		log.info(newValue);

		clickOnElement1(newValue);
		wait.until(ExpectedConditions.elementToBeClickable(clickOnElement("Confirm"))).click();
	}

	public static String readRows(String path, int value) throws IOException {
		String particlurRows = null;
		FileInputStream file = new FileInputStream(path);
		XSSFWorkbook wbook = new XSSFWorkbook(file);
		XSSFSheet sheet1 = wbook.getSheetAt(0);

		int rows; // No of rows
		rows = sheet1.getPhysicalNumberOfRows();
		XSSFRow row;
		for (int i = 0; i < rows; i++) {
			row = sheet1.getRow(i);
			DataFormatter df = new DataFormatter();
			for (int j = 1; j <= 13; j++) {
				Application = df.formatCellValue(row.getCell(value));
			}
		}
		wbook.close();
		return Application;
	}

	public static String waitForTheExcelFileToDownload(String fileName) throws IOException, InterruptedException {
		File[] file_Detail = new File(System.getProperty("user.dir") + "\\Data\\").listFiles();
		for (File file : file_Detail) {
			if (file.getName().contains(fileName)) {
				log.info("File Found");
				String newo = file.getAbsolutePath();
				return newo;
			} else {

			}
		}
		return fileName;
	}

	public void navigateToPipeline() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(Pipeline));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(Pipeline))).click();
		log.info("Navigated to pipeline view home page successfully");
	}

	public String approverVerification()
			throws IOException, HeadlessException, UnsupportedFlavorException, InterruptedException {
		Thread.sleep(8000);
		driver.navigate().refresh();
		Thread.sleep(5000);
		String approver = excelRead(7);

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

	public static String[] currentDatePicker() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy,MMMM,d");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		String[] curntDate = date.split(",");
		return curntDate;
	}

	public static void executionReportExpectedResultwriter() throws IOException, InterruptedException {
		String path = System.getProperty("user.dir") + "//Data//ExecutionReportExpected.xlsx";
		Workbook wb = new XSSFWorkbook();
		FileOutputStream fs = new FileOutputStream(path);
		Sheet sheet1 = wb.createSheet("sheet1");

		String[] data = { "Application", "Pipeline", "Workflow", "Year", "Month", "Day", "Stages", "Triggered By",
				"Status", "Worker", "Release No." };

		Random ra = new Random();
		int n = ra.nextInt(9999);

		String[] newDate = currentDatePicker();

		TriggeredBy = "satheshkumar.m@infosys.com";
		String Approved = "satheshkumar.m@infosys.com";

		String applicationName = "WorkerAutomation";
		String workerName = "AutomationDemo" + n;
		String workerNameAndAppname = applicationName + "_" + workerName;
		String PipelineName = "pipeline" + workerName;
		String Workflow = applicationName + "_" + PipelineName;
		String year = newDate[0];
		String Month = newDate[1];
		String Date = newDate[2];
		String Stage = "Stage1";

		String[] data1 = { applicationName, PipelineName, Workflow, year, Month, Date, Stage, TriggeredBy, "Success",
				workerNameAndAppname, "WorkerTest",
				"Approved By: " + Approved + " (Stage1/cmdexec) -> (Stage1/cmdexecNew)" };
		Row thirdrow = sheet1.createRow(0);
		for (int i = 0; i < data.length; i++) {

			thirdrow.createCell(i).setCellValue(data[i]);
		}
		Row thirdrow1 = sheet1.createRow(1);
		for (int i = 0; i < data1.length; i++) {

			thirdrow1.createCell(i).setCellValue(data1[i]);
		}

		wb.write(fs);
		wb.close();
		fs.close();
	}

	public static String excelRead(int value) throws IOException {

		String path = System.getProperty("user.dir") + "//Data//ExecutionReportExpected.xlsx";

		FileInputStream file = new FileInputStream(path);

		XSSFWorkbook wbook = new XSSFWorkbook(file);

		XSSFSheet sheet1 = wbook.getSheetAt(0);

		int row = sheet1.getLastRowNum() - sheet1.getFirstRowNum();
		DataFormatter df = new DataFormatter();
		for (int i = 1; i <= row; i++) {
			Application = df.formatCellValue(sheet1.getRow(i).getCell(value));
		}
		wbook.close();
		return Application;
	}

	public void navigateToPipelineExeHistory() throws IOException, InterruptedException {
		PipelineView pv = new PipelineView(driver);
		pv.navigateToPipeline();
		String workerName = excelRead(9).replace(excelRead(0) + "_", "");
		String appFirstName = excelRead(0);
		String pipelineName = "pipeline" + workerName;
		Thread.sleep(3000);
		wait.until(
				ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[starts-with(@class,'mat-input')]"))))
				.sendKeys(pipelineName);
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//*[text()='" + pipelineName + "']//following::button[6]"))))
				.click();

	}

	public static void rebuildIDAndApproverComparsion(String[] IDs, String ApproveDetails)
			throws IOException, InterruptedException {

		String appFirstName = excelRead(0);
		String pipelineName = excelRead(1);

		String Firstname = appFirstName + "-" + pipelineName + "-";

		String BuildID = Firstname + IDs[0];
		String reRunID = Firstname + IDs[1];

		String result = waitForTheExcelFileToDownload("Execution_Report");

		String[] remainingReport = { ApproveDetails, BuildID, reRunID, };

		Boolean flag = true;
		int num = 0;
		for (int i = 11; i < 13; i++) {
			String results = readAndCompareRows(result, i);
			if (remainingReport[num].equalsIgnoreCase(results)) {
				flag = true;
				num++;
			} else {
				log.info("sorry !! there was a issue in the Column name ==> " + remainingReport[num]);
				flag = false;
				num++;
			}
		}
		if (flag == true) {
			log.info("Approver and BuildID and ReRun ID reports are working fine");
		} else {
			log.info("Approver and BuildID and ReRun ID reports are not working fine");
		}
	}

	public void ExcelCompartions() throws IOException, InterruptedException {

		String result = waitForTheExcelFileToDownload("Execution_Report");
		String Expected = System.getProperty("user.dir") + "//Data//ExecutionReportExpected.xlsx";

		Boolean flag = true;
		for (int i = 0; i < 10; i++) {
			String exepectedRe = readAndCompareRows(Expected, i);
			String results = readAndCompareRows(result, i);
			if (exepectedRe.equalsIgnoreCase(results)) {
				flag = true;
			} else {
				log.info("sorry !! there was a issue in the Column name ==> " + exepectedRe);
				flag = false;
			}

		}
		if (flag == true) {
			log.info("report is working fine");
		} else {
			log.info("reports are not working fine");
		}
	}

	public static String readAndCompareRows(String path, int value) throws IOException {
		String particlurRows = null;
		FileInputStream file = new FileInputStream(path);
		XSSFWorkbook wbook = new XSSFWorkbook(file);
		XSSFSheet sheet1 = wbook.getSheetAt(0);

		int LastRownum;
		LastRownum = sheet1.getLastRowNum();
		XSSFRow row;
		row = sheet1.getRow(LastRownum);
		for (int j = 1; j <= 13; j++) {
			DataFormatter df = new DataFormatter();
			Application = df.formatCellValue(row.getCell(value));
		}
		wbook.close();
		return Application;
	}
}
