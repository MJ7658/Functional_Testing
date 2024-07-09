package org.infy.reportHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.infy.commonHelpers.PipelineHelper;
import org.infy.init.ExcelReader;
import org.infy.uiPages.PipelineView;
import org.infy.uiPages.plugins.Plugins;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReportHelper {

	private WebDriver driver;
	private WebDriverWait wait;
	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(ReportHelper.class);
	public static String applicationNameToBeCreated;
	public static String applicationWorkflowNameToBeCreated;
	public static String applicationWorkflowNameCreated;
	public int count, flag;
	public static boolean statusVerify;
	static String Application;

	public ReportHelper(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(8000));
	}

	public By reportNav = By.xpath("//*[text()=' Reports ']");

	public By rightArrow = By.xpath("//mat-tab-group/mat-tab-header/div[3]");
	public By applicationViewLeftNav = By.xpath("//mat-list-item//span[text()=' Application ']");
	By applicationViewSideNav = By.xpath("//mat-sidenav-content//span[normalize-space(text())='Application list']");
	By createNewApplicationButon = By.xpath("//button[@mattooltip='Create an Application']");

	By applicationName = By.xpath("//input[@placeholder='Application Name']");
	By owners = By.xpath("//input[@placeholder='Owners']");
	By portfolioName = By.xpath("//mat-select[@placeholder='Portfolio Name']");
	By filterPipeline = By.xpath("//mat-label[text()='Filter']/parent::label/parent::span/preceding-sibling::input");
	By searchPipeline = By.xpath("//mat-label[text()='Search']/parent::label/parent::span/preceding-sibling::input");
	By applicationListTableRows = By
			.xpath("//div[text()='Application list']/parent::app-application-view/descendant::table/tbody/tr");
	By saveApplication = By.xpath("//*[text()=' Save ']");
	By editApplication = By.xpath("//table/tbody/tr[1]/td[3]/button/span/mat-icon");
	By add = By.xpath("//mat-icon[contains(text(),'add')]/ancestor::button");
	By back = By.xpath("//mat-icon[contains(text(),'arrow_back')]");
	By manage = By.xpath("//mat-button-toggle-group/mat-button-toggle[2]/button");

	By policy = By.xpath("//div[text()='Policies']");
	By addPolicy = By.xpath("//span[text()='Add Policy']");
	By selectPolicy = By.xpath("//mat-select[@placeholder='Select Policy']");
	By addButton = By.xpath("//*[text()=' Add ']");

	By resourceType = By.xpath("//*[text()='Assign Permissions']//following::span[80]");

	By resourceTypePortfolio = By.xpath("//*[text()='Assign Permissions']//following::span[58]");

	By permissionType = By.xpath("//*[text()='Assign Permissions']//following::span[83]");

	By permissionTypePortfolio = By.xpath("//*[text()='Assign Permissions']//following::span[61]");

	By okButton = By.xpath("//*[text()='OK']");
	By resourcePatten = By.xpath("//*[starts-with(@id,'mat-input')]//following::input[6]");

	By resourcePattenPortfolio = By.xpath("//*[starts-with(@id,'mat-input')]//following::input[4]");

	By addPermission = By.xpath("//*[text()=' Add Permission ']//following::button[12]");

	By addPermissionPortfolio = By.xpath("//*[text()=' Add Permission ']//following::button[8]");

	By Users = By.xpath("//*[text()='Permissions Added To Policy']//following::input[7]");

	By UsersPortfolio = By.xpath("//*[text()='Permissions Added To Policy']//following::input[5]");

	By policyName = By.xpath("//input[@placeholder='PolicyName']");

	By rightArrows = By.xpath("//*[@class='mat-focus-indicator arrow-right mat-mini-fab mat-button-base mat-primary']");

	By saveButton = By.xpath("//*[text()=' Save ']//following::button[12]");

	By saveButtonPortfolio = By.xpath("//*[text()=' Save ']//following::button[8]");

	By deleteButton = By.xpath("//*[text()=' Delete ']//following::button[12]");

	By deleteButtonportfolio = By.xpath("//*[text()=' Delete ']//following::button[8]");

	By yesButton = By.xpath("//*[text()=' Yes ']");

	By Environments = By.xpath("//div[text()='Environments']");

	By addEnvironment = By.xpath("//*[text()='Add Environment']");

	By environmentName = By.xpath("//*[@placeholder='Environment name']");

	By categorySelect = By.xpath("//*[text()=' Requires Approval ']//preceding::div[16]");

	By environmentSave = By.xpath("//*[text()=' Save ']");

	By variables = By.xpath("//div[text()='Variables']");

	By addVariables = By.xpath("//*[text()='Add Variable']");

	By variableName = By.xpath("//*[text()='Add Variable']//following::input[1]");
	By defaultValue = By.xpath("//*[text()='Add Variable']//following::input[3]");

	By variableSaveButton = By.xpath("//*[text()=' Save ']");

	By Releases = By.xpath("//div[text()='Release']");

	By addRelease = By.xpath("//*[text()='Add Release']");

	By releaseName = By.xpath("//*[@placeholder='Release Name']");

	By environmentSelect = By.xpath("//*[text()=' Cancel ']//preceding::div[45]");

	By okButtons = By.xpath("//*[text()='OK']");

	By startDate = By.xpath("//*[@placeholder='Start Date']");

	By endDate = By.xpath("//*[@placeholder='End Date']");

	By releaseOkButton = By.xpath("//*[text()=' Ok ']");

	By workers = By.xpath("//div[text()='Workers']");

	By addWorker = By.xpath("//span[text()='Add Worker']");
	By workerName = By.xpath("//*[starts-with(@id,'mat-input-')]");

	By pipelineName = By.cssSelector("input[placeholder= 'Pipeline Name']");

	By addStage = By.xpath("//*[@id='add-stage-chip']");
	By secondStage = By.id("cdk-step-label-0-1");
	By stepNameTitle = By.xpath("//mat-expansion-panel/mat-expansion-panel-header/span[1]/mat-panel-title/strong");

	public By addStepButton = By.xpath("//mat-chip[contains(@id,'add-step')]");
	By addStepButton_PipelineDetailsEditor = By.xpath("//span[normalize-space()='Add Step']/parent::button");

	By submitPipelineButton_PipelineDetailsEditor(String text) {
		return By.xpath("//span[normalize-space()='" + text + "']/parent::button");
	}

	By continueOnFailureBox = By.xpath("//*[contains(@name,'continueOnFailureInput')]");

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

	public By selectPlugin(String pluginName) {
		return By.xpath(String.format("//div[contains(@class,'mat-title')]//mat-title[text()='" + pluginName + "']",
				pluginName));
	}

	public By stageName = By.xpath("//*[@name='userInput']");
	public By environment = By.xpath(
			"//mat-label[normalize-space()='Environments']/parent::label/parent::span/preceding-sibling::mat-select");
	public By Worker = By
			.xpath("//mat-label[normalize-space()='Worker']/parent::label/parent::span/preceding-sibling::mat-select");
	By moreOptions = By.xpath("//table/tbody/tr[1]/td[3]/button[4]");
	public By editOptions = By.xpath("//table/tbody/tr/td[3]/button[5]");
	By cloneOptions = By.xpath("//tbody/tr/td[3]/button[6]");

	public By searchPluginTextBox = By.cssSelector("input[placeholder= 'Start searching plugins']");

	@FindBy(xpath = "//*[starts-with(@class,'mat-mdc-option')]")
	List<WebElement> lists;

	public void openReportPage(String ReportType, String Application, String pipelinename)
			throws InterruptedException, IOException {
		Thread.sleep(6000);
		wait.until(ExpectedConditions.elementToBeClickable(reportNav));
		WebElement reportNave = driver.findElement(reportNav);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", reportNave);
		String downloadView = driver.findElement(By.xpath("//*[text()='Download Report ']")).getText();
		if (downloadView.contains("Download")) {
			Thread.sleep(2000);
			log.info("Report Dashboard opened");
			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//*[contains(text(),'" + ReportType + "')]//following::button[1]")))
					.click();

			wait.until(ExpectedConditions
					.visibilityOf(driver.findElement(By.xpath("//*[text()='Application']//preceding::span[1]"))))
					.click();
			Thread.sleep(2000);
			clickOnElement(lists, Application);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='All']"))).click();
			Thread.sleep(2000);

			clickOnElement2(pipelinename);

			if (ReportType.equalsIgnoreCase("Execution Report")) {
				ExecutionReportHelper eh = new ExecutionReportHelper(driver);
				String workerName = eh.excelRead(9).replace(eh.excelRead(0) + "_", "");
				String pipelineName = "pipeline" + workerName;
				wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Pipeline']//preceding::span[1]")))
						.click();
				Thread.sleep(2000);
				clickOnElement(lists, pipelineName);
			} else if (ReportType.equalsIgnoreCase("Pipeline Configuration Report")) {
				Thread.sleep(2000);
				PipelineConfigResultHelper prh = new PipelineConfigResultHelper(driver);
				String pipelineName = prh.excelRead(0);
				ExecutionReportHelper eh = new ExecutionReportHelper(driver);
				wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Pipeline']//preceding::span[1]")))
						.click();
				Thread.sleep(2000);
				clickOnElement(lists, pipelineName);
			}
			Thread.sleep(2000);
			String dates = currentDatePicker();

			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(
					driver.findElement(By.xpath("//*[text()=' Download Report ']//preceding::button[2]")))).click();
			Thread.sleep(3000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='" + " " + dates + " " + "']")))
					.click();
			wait.until(ExpectedConditions.elementToBeClickable(
					driver.findElement(By.xpath("//*[text()=' Download Report ']//preceding::button[1]")))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='" + " " + dates + " " + "']")))
					.click();
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' Download Report ']"))).click();
			Thread.sleep(4000);
			waitForTheExcelFileToDownload(driver, "IDP_report");
			Thread.sleep(4000);
		}
	}

	public static ArrayList<String> workFlowCompexcelreadrRowtarget(String paths, int sheet) throws IOException {
		String path = System.getProperty("user.dir") + "//Data//" + paths;
		FileInputStream file = new FileInputStream(path);
		XSSFWorkbook wbook = new XSSFWorkbook(file);
		XSSFSheet sheet1 = wbook.getSheetAt(sheet);
		int row = sheet1.getLastRowNum();
		ArrayList<String> ls = new ArrayList<String>();
		ArrayList<String> ls1 = new ArrayList<String>();
		int noOfColumns = sheet1.getRow(row).getPhysicalNumberOfCells();
		for (int j = 0; j <= noOfColumns; j++) {
			DataFormatter df = new DataFormatter();
			String lastrow = df.formatCellValue(sheet1.getRow(row).getCell(j));
			String lastrowaboveone = df.formatCellValue(sheet1.getRow(row - 1).getCell(j));
			ls.add(lastrow);
			ls1.add(lastrowaboveone);
		}
		ls.remove(5);
		ls1.remove(5);
		ls.addAll(ls1);
		wbook.close();
		return ls;
	}

	public static String excelRead5(int value, String fileName, int num) throws IOException {

		String path = System.getProperty("user.dir") + "//Data//" + fileName;

		FileInputStream file = new FileInputStream(path);

		XSSFWorkbook wbook = new XSSFWorkbook(file);

		XSSFSheet sheet1 = wbook.getSheetAt(0);

		int row = sheet1.getLastRowNum();
		DataFormatter df = new DataFormatter();
		for (int i = 1; i <= row; i++) {
			Application = df.formatCellValue(sheet1.getRow(row + num).getCell(value));
		}
		wbook.close();
		return Application;
	}

	public static String currentDatePickers2() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		return date;
	}

	public static boolean excelCompa() throws IOException {
		boolean flag = true;
		for (int i = 0; i <= 11; i++) {

			if (excelRead5(i, "workflowComplieanceExpected.xlsx", 0)
					.equalsIgnoreCase(excelRead5(i, "IDPReport.xlsx", 0))) {
			} else if (currentDatePickers2().equalsIgnoreCase(excelRead5(i, "workflowComplieanceExpected.xlsx", 0))) {
			} else {
				log.info(excelRead5(i, "workflowComplieanceExpected.xlsx", 0));
				log.info(i);
				flag = false;
			}

			if (excelRead5(i, "workflowComplieanceExpected.xlsx", -1)
					.equalsIgnoreCase(excelRead5(i, "IDPReport.xlsx", -1))) {
			} else if (currentDatePickers2().equalsIgnoreCase(excelRead5(i, "workflowComplieanceExpected.xlsx", -1))) {
			} else {
				log.info(excelRead5(i, "workflowComplieanceExpected.xlsx", -1));
				log.info(i);
				flag = false;
			}

		}
		return flag;
	}

	public static void workflowComplieanceExpectedResultwriter() throws IOException, InterruptedException {

		String path = System.getProperty("user.dir") + "//Data//workflowComplieanceExpected.xlsx";
		Workbook wb = new XSSFWorkbook();
		FileOutputStream fs = new FileOutputStream(path);
		Sheet sheet1 = wb.createSheet("sheet1");

		String[] data = { "Portfolio", "Application", "Pipeline", "Workflow", "Status", "Execution Time",
				"Triggered By", "Worker", "Release No", "BuildId", "Rerun ID", "Stage1:cmdexec(cmdexec_0.0.1)" };

		Random ra = new Random();
		int n = ra.nextInt(9999);
		String Portfolio = "Automation_Functional_test";
		String applicationName = "WorkerAutomation";
		String workerName = "AutomationDemo" + n;
		String PipelineName = "pipeline" + workerName;
		String Workflow = applicationName + "_" + PipelineName;
		String Status = "Success";
		String ExecutionTime = currentDatePickers();
		String TriggeredBy = "satheshkumar.m@infosys.com";
		String workerNameAndAppname = applicationName + "_" + workerName;
		String ReleaseNo = "Release1";
		String buildID = applicationName + "-" + PipelineName + "-" + ReleaseNo + applicationName + PipelineName + "-2";
		String rerunID = applicationName + "-" + PipelineName + "-" + ReleaseNo + applicationName + PipelineName + "-1";

		String Stage = " Success";

		String[] data1 = { Portfolio, applicationName, PipelineName, Workflow, Status, ExecutionTime, TriggeredBy,
				workerNameAndAppname, ReleaseNo, buildID, rerunID, Stage };

		String[] data2 = { Portfolio, applicationName, PipelineName, Workflow, Status, ExecutionTime, TriggeredBy,
				workerNameAndAppname, ReleaseNo, rerunID, "", Stage };

		Row thirdrow = sheet1.createRow(0);
		for (int i = 0; i < data.length; i++) {
			thirdrow.createCell(i).setCellValue(data[i]);
		}
		Row thirdrow1 = sheet1.createRow(1);
		for (int i = 0; i < data1.length; i++) {
			thirdrow1.createCell(i).setCellValue(data1[i]);
		}

		Row thirdrow2 = sheet1.createRow(2);
		for (int i = 0; i < data2.length; i++) {
			thirdrow2.createCell(i).setCellValue(data2[i]);
		}

		wb.write(fs);
		wb.close();
		fs.close();
	}

	public static String currentDatePickers() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		return date;
	}

	public static String workflowExcelRead(int value) throws IOException {
		String path = System.getProperty("user.dir") + "//Data//workflowComplieanceExpected.xlsx";
		FileInputStream file = new FileInputStream(path);
		XSSFWorkbook wbook = new XSSFWorkbook(file);
		XSSFSheet sheet1 = wbook.getSheetAt(0);
		int row = sheet1.getLastRowNum() - sheet1.getFirstRowNum();
		for (int i = 1; i <= row; i++) {
			Application = sheet1.getRow(i).getCell(value).getStringCellValue();
		}
		wbook.close();
		return Application;
	}

	public static String workflowExcelReadwithRow(int value, int rowDetails) throws IOException {
		String path = System.getProperty("user.dir") + "//Data//workflowComplieanceExpected.xlsx";
		FileInputStream file = new FileInputStream(path);
		XSSFWorkbook wbook = new XSSFWorkbook(file);
		XSSFSheet sheet1 = wbook.getSheetAt(0);
		int row = sheet1.getLastRowNum() - sheet1.getFirstRowNum();
		for (int i = 1; i <= row; i++) {
			Application = sheet1.getRow(rowDetails).getCell(value).getStringCellValue();
		}
		wbook.close();
		return Application;
	}

	public static void waitForTheExcelFileToDownload(WebDriver driver, String fileName)
			throws IOException, InterruptedException {
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
			wait.until((x) -> {
				File[] file_Detail = new File(System.getProperty("user.dir") + "\\Data\\").listFiles();
				for (File file : file_Detail) {
					if (file.getName().contains(fileName)) {
						log.info("File Downloaded");
						return true;
					}
				}
				return false;
			});
		}
	}

	public static String currentDatePicker() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		return date;
	}

	public void clickOn(List<WebElement> locator, WebDriver driver, int timeout) {
		final WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(timeout));
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(locator)));

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
			Thread.sleep(1000);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
			Thread.sleep(1000);
			if (webElement.getText().equalsIgnoreCase(value)) {
				wait.until(ExpectedConditions.elementToBeClickable(webElement));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
				break;
			}
		}
	}

	@CacheLookup
	@FindBy(xpath = "//*[starts-with(@class,'mat-mdc-option')]")
	List<WebElement> policyListDetails;

	public void clickOnElement1(String value) throws InterruptedException {
		List<WebElement> list;
		try {
			list = driver.findElements(By.xpath("//*[starts-with(@class,'mat-mdc-option')]"));
			log.info("its try block");
		} catch (StaleElementReferenceException e) {
			list = driver.findElements(By.xpath("//*[starts-with(@class,'mat-mdc-option')]"));
			log.info("its catch block");
		}
		for (WebElement webElement : policyListDetails) {
			log.info("if condition ==> " + webElement.getText());
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
			if (webElement.getText().equalsIgnoreCase(value)) {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.elementToBeClickable(webElement));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
				log.info("its clicked ==> " + webElement.getText());
				Thread.sleep(2000);
				break;
			}
		}
	}

	public void clickOnElement12(String value) throws InterruptedException {
		List<WebElement> list = driver.findElements(By.xpath("//*[starts-with(@class,'mat-mdc-option')]"));
		for (WebElement webElement : list) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
			if (webElement.getText().equalsIgnoreCase(value)) {
				wait.until(ExpectedConditions.elementToBeClickable(webElement));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
				break;
			}
		}
	}

	public void searchAndClick(String value) throws InterruptedException {
		WebElement categroys = driver.findElement(By.xpath("//*[@placeholder='Search']"));
		categroys.click();
		categroys.clear();
		Thread.sleep(2000);
		categroys.sendKeys(value);
		Thread.sleep(2000);
		categroys.sendKeys(Keys.ENTER);
	}

//	@FindBy(xpath = "//*[starts-with(@class,'mat-mdc-option')]")
//	List<WebElement> categroys;

	public void clickOnElement11(String value) throws InterruptedException {
		List<WebElement> categroys = driver.findElements(By.xpath("//*[starts-with(@class,'mat-mdc-option')]"));
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

	public void clickOnElement2(String value) throws InterruptedException {
		driver.switchTo().activeElement().sendKeys(value);
		Thread.sleep(2000);
		List<WebElement> list = driver.findElements(By.xpath("//*[starts-with(@class,'mat-mdc-option')]"));
		for (WebElement webElement : lists) {
			if (webElement.getText().equalsIgnoreCase(value)) {
				wait.until(ExpectedConditions.elementToBeClickable(webElement));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
				break;
			}
		}
	}

	public void clickOnElement3(String value) throws InterruptedException {
		Thread.sleep(3000);
		driver.switchTo().activeElement().sendKeys(value);
		Thread.sleep(3000);
		List<WebElement> list = driver.findElements(By.xpath("//*[starts-with(@class,'mat-mdc-option')]"));
		for (WebElement webElement : list) {
			if (webElement.getText().equalsIgnoreCase(value)) {
				wait.until(ExpectedConditions.elementToBeClickable(webElement));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
				break;
			}
		}
	}

	public void createApplication(String value, String protfolioName, String OwnerName)
			throws InterruptedException, IOException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(applicationName)).click();

		driver.findElement(applicationName).sendKeys(value);
		driver.findElement(portfolioName).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[starts-with(@class,'mat-mdc-option')]")));
		List<WebElement> profoioName = driver.findElements(By.xpath("//*[starts-with(@class,'mat-mdc-option')]"));
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
				applicationNav();
			}
		}
		log.info("Application general details are saved");
	}

	public void applicationNav() throws InterruptedException, IOException {
		ExcelReader read = new ExcelReader();
		wait.until(ExpectedConditions.elementToBeClickable(applicationViewLeftNav)).click();
		wait.until(ExpectedConditions.elementToBeClickable(manage)).click();
		wait.until(ExpectedConditions.elementToBeClickable(createNewApplicationButon)).click();
		String AppName = read.excelRead(0);
		String protName = read.excelRead(1);
		String ownrName = read.excelRead(2);
		String OwnerName = ownrName.replace(";", "");
		Thread.sleep(2000);
		createApplication(AppName, protName, OwnerName);
	}

	public void newPolicyCreation(String policyNames) throws InterruptedException, IOException {

		ExcelReader read = new ExcelReader();
		wait.until(ExpectedConditions.elementToBeClickable(policy)).click();
		wait.until(ExpectedConditions.elementToBeClickable(addPolicy)).click();
		wait.until(ExpectedConditions.elementToBeClickable(selectPolicy)).click();

		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[starts-with(@class,'mat-mdc-option')]")));
		List<WebElement> policy = driver.findElements(By.xpath("//*[starts-with(@class,'mat-mdc-option')]"));
		clickOnElement(policy, "Other");
		wait.until(ExpectedConditions.elementToBeClickable(policyName)).sendKeys(policyNames);
		Thread.sleep(3000);
		if (driver.findElement(addButton).isEnabled() == true) {
			log.info("its enabled");
			wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
		} else {
			Thread.sleep(3000);
			wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
		}
		Thread.sleep(3000);
		WebElement element = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(3000);

		policySelect1(policyNames);
		String Permissionemail = read.excelRead(4);
		String policyPermissionemail = Permissionemail.replace(";", "");

		WebElement userClick1 = driver
				.findElement(By.xpath("//*[text()='" + " " + policyNames + " " + "']//following::div[98]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", userClick1);

		wait.until(ExpectedConditions.elementToBeClickable(Users)).click();
		driver.switchTo().activeElement().sendKeys(policyPermissionemail);

		wait.until(ExpectedConditions.elementToBeClickable(rightArrows)).click();
		wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
		Thread.sleep(2000);
		WebElement userTop = driver.findElement(By.xpath("//div[text()='Environments']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", userTop);
		Thread.sleep(2000);
	}

	public void newPolicyCreationPortfolio(String policyNames) throws InterruptedException, IOException {

		ExcelReader read = new ExcelReader();
		wait.until(ExpectedConditions.elementToBeClickable(policy)).click();
		wait.until(ExpectedConditions.elementToBeClickable(addPolicy)).click();
		wait.until(ExpectedConditions.elementToBeClickable(selectPolicy)).click();

		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[starts-with(@class,'mat-mdc-option')]")));
		List<WebElement> policy = driver.findElements(By.xpath("//*[starts-with(@class,'mat-mdc-option')]"));
		clickOnElement(policy, "Other");
		wait.until(ExpectedConditions.elementToBeClickable(policyName)).sendKeys(policyNames);
		Thread.sleep(3000);
		if (driver.findElement(addButton).isEnabled() == true) {
			log.info("its enabled");
			wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
		} else {
			Thread.sleep(3000);
			wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
		}
		Thread.sleep(3000);
		WebElement element = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(3000);

		policySelect1Portfolio(policyNames);
		String Permissionemail = read.excelRead(4);
		String policyPermissionemail = Permissionemail.replace(";", "");

		WebElement userClick1 = driver
				.findElement(By.xpath("//*[text()='" + " " + policyNames + " " + "']//following::div[70]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", userClick1);

		wait.until(ExpectedConditions.elementToBeClickable(UsersPortfolio)).click();
		driver.switchTo().activeElement().sendKeys(policyPermissionemail);

		wait.until(ExpectedConditions.elementToBeClickable(rightArrows)).click();
		wait.until(ExpectedConditions.elementToBeClickable(saveButtonPortfolio)).click();
		Thread.sleep(2000);
		WebElement userTop = driver.findElement(By.xpath("//div[text()='Environments']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", userTop);
		Thread.sleep(2000);
	}

	public void navPolicys() {
		wait.until(ExpectedConditions.elementToBeClickable(policy)).click();
	}

	public void navPolicys(WebDriver driver) {
		wait.until(ExpectedConditions.elementToBeClickable(policy)).click();
	}

	public void UpdatePolicys(String policyNames, String policyPermissionemail)
			throws InterruptedException, IOException {

		Thread.sleep(3000);
		WebElement element = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(3000);

		WebElement userClick1 = driver.findElement(By.xpath("//*[text()='" + " " + policyNames + " " + "']"));
		userClick1.click();
		Thread.sleep(3000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(Users)).click();
		driver.switchTo().activeElement().sendKeys(policyPermissionemail);

		wait.until(ExpectedConditions.elementToBeClickable(rightArrows)).click();
		wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
	}

	public void UpdatePolicysPortfolio(String policyNames, String policyPermissionemail)
			throws InterruptedException, IOException {

		Thread.sleep(3000);
		WebElement element = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(3000);

		WebElement userClick1 = driver.findElement(By.xpath("//*[text()='" + " " + policyNames + " " + "']"));
		userClick1.click();
		Thread.sleep(3000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(3000);

		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[normalize-space(text())='" + policyNames + "']//following::input[2]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[normalize-space(text())='" + policyNames + "']//following::input[2]")))
				.sendKeys(policyPermissionemail);

//		wait.until(ExpectedConditions.elementToBeClickable(UsersPortfolio)).click();
//		driver.switchTo().activeElement().sendKeys(policyPermissionemail);

		wait.until(ExpectedConditions.elementToBeClickable(rightArrows)).click();
	}

	By deleteButtonport = By.xpath("//*[text()=' Items per page: ']//preceding::button[1]");

	public void deletePolicys(String policyNames) throws InterruptedException, IOException {

		Thread.sleep(3000);
		WebElement element = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(3000);

		WebElement userClick1 = driver.findElement(By.xpath("//*[text()='" + " " + policyNames + " " + "']"));
		userClick1.click();
		Thread.sleep(3000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(deleteButtonport)).click();
		wait.until(ExpectedConditions.elementToBeClickable(yesButton)).click();
	}

	public void policyCreation(String policyNames) throws InterruptedException, IOException {

		ExcelReader read = new ExcelReader();
		wait.until(ExpectedConditions.elementToBeClickable(policy)).click();
		wait.until(ExpectedConditions.elementToBeClickable(addPolicy)).click();
		wait.until(ExpectedConditions.elementToBeClickable(selectPolicy)).click();

		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[starts-with(@class,'mat-mdc-option')]")));
		List<WebElement> policy = driver.findElements(By.xpath("//*[starts-with(@class,'mat-mdc-option')]"));
		clickOnElement(policy, "Other");

		wait.until(ExpectedConditions.elementToBeClickable(policyName)).sendKeys(policyNames);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
		Thread.sleep(3000);

		WebElement userClick = driver
				.findElement(By.xpath("//*[text()='" + " " + policyNames + " " + "']//following::div[59]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", userClick);
		Thread.sleep(3000);

		policySelect1(policyNames);
		String Permissionemail = read.excelRead(4);
		String policyPermissionemail = Permissionemail.replace(";", "");

		WebElement userClick1 = driver
				.findElement(By.xpath("//*[text()='" + " " + policyNames + " " + "']//following::div[98]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", userClick);

		wait.until(ExpectedConditions.elementToBeClickable(Users)).click();
		driver.switchTo().activeElement().sendKeys(policyPermissionemail);

		wait.until(ExpectedConditions.elementToBeClickable(rightArrows)).click();
		wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
		Thread.sleep(2000);
		WebElement userTop = driver.findElement(By.xpath("//div[text()='Environments']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", userTop);
		Thread.sleep(2000);
	}

	public void releaseManagerPolicy(String manager) throws IOException {

		ExcelReader read = new ExcelReader();
		WebElement addPolicys = driver.findElement(addPolicy);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addPolicys);
		addPolicys.click();

		wait.until(ExpectedConditions.elementToBeClickable(selectPolicy)).click();
		List<WebElement> policyss = driver.findElements(By.xpath("//*[starts-with(@class,'mat-mdc-option')]"));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[starts-with(@class,'mat-mdc-option')]")));
		clickOnElement(policyss, manager);
		wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();

		WebElement Policyname = driver.findElement(By.xpath("//*[text()='" + " " + manager + " " + "']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", manager);
		Policyname.click();

		String managers = read.excelRead(4);
		String managersEmail = managers.replace(";", "");
		wait.until(ExpectedConditions.elementToBeClickable(Users)).click();
		driver.switchTo().activeElement().sendKeys(managersEmail);

		WebElement us = driver.findElement(Users);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", us);

		wait.until(ExpectedConditions.elementToBeClickable(rightArrows)).click();
		wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", policy);

	}

	@FindBy(xpath = "//*[starts-with(@class,'mat-mdc-option')]")
	List<WebElement> list;

	public void policySelect1(String policyNames) throws InterruptedException {

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[text()='" + " " + policyNames + " " + "']"))).click();
		Thread.sleep(2000);
//		WebElement element = driver.findElement(
//				By.xpath("//*[starts-with(text(),'" + " " + policyNames + " " + "')]//following::div[59]"));
//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
//		Thread.sleep(2000);

		String[] policys = { "Application", "Bulk Notifications", "Developer Analytics", "Environment", "Pipeline",
				"Policy", "Release", "Stage", "Variables", "Worker", "Workflow(Application)" };

		String[] Application = { "EDIT", "EXECUTE", "VIEW" };

		String[] Bulk_Notifications = { "DELETE" };

		String[] Developer_Analytics = { "EXECUTIVE", "MANAGER", "USER" };

		String[] Environment = { "CREATE", "DELETE", "EDIT", "EXECUTE", "VIEW" };

		String[] Policy = { "CREATE", "EXECUTE", "EDIT", "VIEW" };

		String[] Release = { "CREATE", "EDIT", "EXECUTE", "VIEW" };

		String[] Worker = { "CREATE", "DELETE", "VIEW" };

		String[] Workflow = { "CREATE", "DELETE", "EDIT", "EXECUTE" };

		for (int i = 0; i < policys.length; i++) {
			wait.until(ExpectedConditions.elementToBeClickable(resourceType)).click();
			for (WebElement webElement : list) {
				log.info("if condition ==> " + webElement.getText());
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
				if (webElement.getText().equalsIgnoreCase(policys[i])) {
					log.info(" ==> " + webElement.getText());
					wait.until(ExpectedConditions.elementToBeClickable(webElement));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
					break;
				}
			}
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(permissionType)).click();

			if (policys[i].equalsIgnoreCase("Application")) {

				for (int j = 0; j < Application.length; j++) {
					Thread.sleep(1000);
					clickOnElement2(list, Application[j]);
					log.info(j + ":" + j + ":*;");
					if (j == 2) {
						wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();

						wait.until(ExpectedConditions.elementToBeClickable(resourcePatten)).click();

						driver.switchTo().activeElement().sendKeys("*");

						wait.until(ExpectedConditions.elementToBeClickable(addPermission)).click();

					}
				}
			} else if (policys[i].equalsIgnoreCase("Bulk Notifications")) {

				for (int j = 0; j < Bulk_Notifications.length; j++) {
					Thread.sleep(1000);
					clickOnElement2(list, Bulk_Notifications[j]);
					if (j == 0) {
						wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();

						wait.until(ExpectedConditions.elementToBeClickable(resourcePatten)).click();

						driver.switchTo().activeElement().sendKeys("*");

						wait.until(ExpectedConditions.elementToBeClickable(addPermission)).click();
					}
				}
			}

			else if (policys[i].equalsIgnoreCase("Developer Analytics")) {

				for (int j = 0; j < Developer_Analytics.length; j++) {
					Thread.sleep(1000);
					clickOnElement2(list, Developer_Analytics[j]);
					if (j == 2) {
						wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();

						wait.until(ExpectedConditions.elementToBeClickable(resourcePatten)).click();

						driver.switchTo().activeElement().sendKeys("*");

						wait.until(ExpectedConditions.elementToBeClickable(addPermission)).click();
					}
				}
			} else if (policys[i].equalsIgnoreCase("Environment") || policys[i].equalsIgnoreCase("Pipeline")) {

				for (int j = 0; j < Environment.length; j++) {
					Thread.sleep(1000);
					clickOnElement2(list, Environment[j]);
					if (j == 4) {
						wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();

						wait.until(ExpectedConditions.elementToBeClickable(resourcePatten)).click();

						driver.switchTo().activeElement().sendKeys("*");

						wait.until(ExpectedConditions.elementToBeClickable(addPermission)).click();
					}
				}
			} else if (policys[i].equalsIgnoreCase("Policy")) {

				for (int j = 0; j < Policy.length; j++) {
					Thread.sleep(1000);
					clickOnElement2(list, Policy[j]);
					if (j == 3) {
						wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();

						wait.until(ExpectedConditions.elementToBeClickable(resourcePatten)).click();

						driver.switchTo().activeElement().sendKeys("*");

						wait.until(ExpectedConditions.elementToBeClickable(addPermission)).click();
					}
				}
			}

			else if (policys[i].equalsIgnoreCase("Release") || policys[i].equalsIgnoreCase("Stage")
					|| policys[i].equalsIgnoreCase("Variables")) {
				for (int j = 0; j < Release.length; j++) {
					Thread.sleep(1000);
					clickOnElement2(list, Release[j]);
					if (j == 3) {
						wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();

						wait.until(ExpectedConditions.elementToBeClickable(resourcePatten)).click();

						driver.switchTo().activeElement().sendKeys("*");

						wait.until(ExpectedConditions.elementToBeClickable(addPermission)).click();
					}
				}
			} else if (policys[i].equalsIgnoreCase("Worker")) {
				for (int j = 0; j < Worker.length; j++) {
					Thread.sleep(1000);
					clickOnElement1(Worker[j]);
					if (j == 2) {
						wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();

						wait.until(ExpectedConditions.elementToBeClickable(resourcePatten)).click();

						driver.switchTo().activeElement().sendKeys("*");

						wait.until(ExpectedConditions.elementToBeClickable(addPermission)).click();
					}
				}
			} else if (policys[i].equalsIgnoreCase("Workflow(Application)")) {
				for (int j = 0; j < Workflow.length; j++) {
					Thread.sleep(1000);
					clickOnElement2(list, Workflow[j]);
					if (j == 3) {
						wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();

						wait.until(ExpectedConditions.elementToBeClickable(resourcePatten)).click();

						driver.switchTo().activeElement().sendKeys("*");

						wait.until(ExpectedConditions.elementToBeClickable(addPermission)).click();

					}
				}
			}
		}
	}

	public void policySelect1Portfolio(String policyNames) throws InterruptedException {

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[text()='" + " " + policyNames + " " + "']"))).click();
		Thread.sleep(2000);
//		WebElement element = driver.findElement(
//				By.xpath("//*[starts-with(text(),'" + " " + policyNames + " " + "')]//following::div[59]"));
//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
//		Thread.sleep(2000);

		String[] policys = { "Application", "Environment", "Pipeline", "Policy", "Release", "Reports", "Variables",
				"Worker" };

		String[] Application = { "EDIT", "EXECUTE", "VIEW" };

		String[] Bulk_Notifications = { "DELETE" };

		String[] Developer_Analytics = { "EXECUTIVE", "MANAGER", "USER" };

		String[] Environment = { "CREATE", "DELETE", "EDIT", "EXECUTE", "VIEW" };

		String[] Policy = { "CREATE", "EXECUTE", "EDIT", "VIEW" };

		String[] Release = { "CREATE", "EDIT", "EXECUTE", "VIEW" };

		String[] Worker = { "CREATE", "DELETE", "VIEW" };

		String[] Reports = { "VIEW" };

		String[] Workflow = { "CREATE", "DELETE", "EDIT", "EXECUTE" };

		for (int i = 0; i < policys.length; i++) {
			wait.until(ExpectedConditions.elementToBeClickable(resourceTypePortfolio)).click();
			for (WebElement webElement : list) {
				log.info("if condition ==> " + webElement.getText());
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
				if (webElement.getText().equalsIgnoreCase(policys[i])) {
					log.info(" ==> " + webElement.getText());
					wait.until(ExpectedConditions.elementToBeClickable(webElement));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
					break;
				}
			}
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(permissionTypePortfolio)).click();

			if (policys[i].equalsIgnoreCase("Application")) {

				for (int j = 0; j < Application.length; j++) {
					Thread.sleep(1000);
					clickOnElement2(list, Application[j]);
					log.info(j + ":" + j + ":*;");
					if (j == 2) {
						wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();

						wait.until(ExpectedConditions.elementToBeClickable(resourcePattenPortfolio)).click();

						driver.switchTo().activeElement().sendKeys("*");

						wait.until(ExpectedConditions.elementToBeClickable(addPermissionPortfolio)).click();

					}
				}
			} else if (policys[i].equalsIgnoreCase("Bulk Notifications")) {

				for (int j = 0; j < Bulk_Notifications.length; j++) {
					Thread.sleep(1000);
					clickOnElement2(list, Bulk_Notifications[j]);
					if (j == 0) {
						wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();

						wait.until(ExpectedConditions.elementToBeClickable(resourcePattenPortfolio)).click();

						driver.switchTo().activeElement().sendKeys("*");

						wait.until(ExpectedConditions.elementToBeClickable(addPermissionPortfolio)).click();
					}
				}
			}

			else if (policys[i].equalsIgnoreCase("Developer Analytics")) {

				for (int j = 0; j < Developer_Analytics.length; j++) {
					Thread.sleep(1000);
					clickOnElement2(list, Developer_Analytics[j]);
					if (j == 2) {
						wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();

						wait.until(ExpectedConditions.elementToBeClickable(resourcePattenPortfolio)).click();

						driver.switchTo().activeElement().sendKeys("*");

						wait.until(ExpectedConditions.elementToBeClickable(addPermissionPortfolio)).click();
					}
				}
			} else if (policys[i].equalsIgnoreCase("Environment") || policys[i].equalsIgnoreCase("Pipeline")) {

				for (int j = 0; j < Environment.length; j++) {
					Thread.sleep(1000);
					clickOnElement2(list, Environment[j]);
					if (j == 4) {
						wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();

						wait.until(ExpectedConditions.elementToBeClickable(resourcePattenPortfolio)).click();

						driver.switchTo().activeElement().sendKeys("*");

						wait.until(ExpectedConditions.elementToBeClickable(addPermissionPortfolio)).click();
					}
				}
			} else if (policys[i].equalsIgnoreCase("Policy")) {

				for (int j = 0; j < Policy.length; j++) {
					Thread.sleep(1000);
					clickOnElement2(list, Policy[j]);
					if (j == 3) {
						wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();

						wait.until(ExpectedConditions.elementToBeClickable(resourcePattenPortfolio)).click();

						driver.switchTo().activeElement().sendKeys("*");

						wait.until(ExpectedConditions.elementToBeClickable(addPermissionPortfolio)).click();
					}
				}
			}

			else if (policys[i].equalsIgnoreCase("Release") || policys[i].equalsIgnoreCase("Stage")
					|| policys[i].equalsIgnoreCase("Variables")) {
				for (int j = 0; j < Release.length; j++) {
					Thread.sleep(1000);
					clickOnElement2(list, Release[j]);
					if (j == 3) {
						wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();

						wait.until(ExpectedConditions.elementToBeClickable(resourcePattenPortfolio)).click();

						driver.switchTo().activeElement().sendKeys("*");

						wait.until(ExpectedConditions.elementToBeClickable(addPermissionPortfolio)).click();
					}
				}
			}

			else if (policys[i].equalsIgnoreCase("Reports")) {
				for (int j = 0; j < Reports.length; j++) {
					Thread.sleep(1000);
					clickOnElement2(list, Reports[j]);
					if (j == 0) {
						wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();

						wait.until(ExpectedConditions.elementToBeClickable(resourcePattenPortfolio)).click();

						driver.switchTo().activeElement().sendKeys("*");

						wait.until(ExpectedConditions.elementToBeClickable(addPermissionPortfolio)).click();
					}
				}
			} else if (policys[i].equalsIgnoreCase("Worker")) {
				for (int j = 0; j < Worker.length; j++) {
					Thread.sleep(1000);
					clickOnElement1(Worker[j]);
					if (j == 2) {
						wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();

						wait.until(ExpectedConditions.elementToBeClickable(resourcePattenPortfolio)).click();

						driver.switchTo().activeElement().sendKeys("*");

						wait.until(ExpectedConditions.elementToBeClickable(addPermissionPortfolio)).click();
					}
				}
			} else if (policys[i].equalsIgnoreCase("Workflow(Application)")) {
				for (int j = 0; j < Workflow.length; j++) {
					Thread.sleep(1000);
					clickOnElement2(list, Workflow[j]);
					if (j == 3) {
						wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();

						wait.until(ExpectedConditions.elementToBeClickable(resourcePattenPortfolio)).click();

						driver.switchTo().activeElement().sendKeys("*");

						wait.until(ExpectedConditions.elementToBeClickable(addPermissionPortfolio)).click();

					}
				}
			}
		}
	}

	public void environmentCreation() throws IOException, InterruptedException {

		ExcelReader read = new ExcelReader();
		wait.until(ExpectedConditions.elementToBeClickable(Environments));
		WebElement Environmentss = driver.findElement(Environments);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Environmentss);
		wait.until(ExpectedConditions.elementToBeClickable(Environments)).click();
		wait.until(ExpectedConditions.elementToBeClickable(addEnvironment)).click();

		String environmentValue = read.excelRead(6);
		String newValue = environmentValue.replace(",", "");
		String[] newenvironmentValue = newValue.split(":");
		String newvalues = newenvironmentValue[0];
		String newvalues1 = newenvironmentValue[1];
		String Envalue = newvalues.replace(" ", "");
		String Envalue1 = newvalues1.replace(" ", "");

		wait.until(ExpectedConditions.elementToBeClickable(environmentName)).sendKeys(Envalue);
		;
		WebElement categoryClick = driver.findElement(categorySelect);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", categoryClick);
		Thread.sleep(2000);
		log.info("Its Category " + Envalue1);
		clickOnElement11(Envalue1);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(environmentSave)).click();

	}

	public void variableCreation() throws IOException, InterruptedException {
		Thread.sleep(4000);
		ExcelReader read = new ExcelReader();
		wait.until(ExpectedConditions.elementToBeClickable(variables));
		WebElement Variable = driver.findElement(variables);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Variable);
		wait.until(ExpectedConditions.elementToBeClickable(variables)).click();
		wait.until(ExpectedConditions.elementToBeClickable(addVariables)).click();

		String variabless = read.excelRead(7);
		String newVari = variabless.replace("default=", "");
		String newVari1 = newVari.replace(",", "");
		String[] newVari2 = newVari1.split(":");
		String username = newVari2[0];
		String password = newVari2[1];

		wait.until(ExpectedConditions.elementToBeClickable(variableName)).sendKeys(username);
		wait.until(ExpectedConditions.elementToBeClickable(defaultValue)).sendKeys(password);

		wait.until(ExpectedConditions.elementToBeClickable(variableSaveButton)).click();
	}

	public void releaseCreation() throws IOException, InterruptedException {

		ExcelReader read = new ExcelReader();
		wait.until(ExpectedConditions.elementToBeClickable(Releases));
		WebElement Releasess = driver.findElement(Releases);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Releasess);
		wait.until(ExpectedConditions.elementToBeClickable(Releases)).click();
		wait.until(ExpectedConditions.elementToBeClickable(addRelease)).click();

		String releaseNames = read.excelRead(8);
		String NewreleaseName = releaseNames.replace(":Not Archived;", "");

		wait.until(ExpectedConditions.elementToBeClickable(releaseName)).sendKeys(NewreleaseName);

		wait.until(ExpectedConditions.elementToBeClickable(environmentSelect)).click();

		clickOnElement11(environmentName());

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

	}

	public void workerCreation() throws IOException {
		ExcelReader read = new ExcelReader();
		wait.until(ExpectedConditions.elementToBeClickable(workers));
		WebElement workerss = driver.findElement(workers);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", workerss);
		wait.until(ExpectedConditions.elementToBeClickable(workers)).click();
		wait.until(ExpectedConditions.elementToBeClickable(addWorker)).click();
		String workerValue = read.excelRead(9);
		String newValue = workerValue.replace(";", "");
		String[] newenvironmentValue = newValue.split("_");
		String workerNewName = newenvironmentValue[1];
		wait.until(ExpectedConditions.elementToBeClickable(workerName)).sendKeys(workerNewName);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Save ']")));
		driver.findElement(By.xpath("//*[text()=' Save ']")).click();
	}

	public void pipelineCreation() throws InterruptedException, IOException {
		PipelineView pv = new PipelineView(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		pv.navigateToVisualEditor();
		createPipelineName();
		ExcelReader read = new ExcelReader();
		String workerValue = read.excelRead(9);
		String newValue = workerValue.replace(";", "");
		ph.addStageInPipeline("stage1", "qa", newValue);
		// addStageInPipeline();
		createStepe();
		pv.submitPipeline();
		Thread.sleep(6000);
	}

	public void createPipelineName() throws InterruptedException, IOException {
		ExcelReader read = new ExcelReader();
		String appName = read.excelRead(9);
		String newValue = appName.replace(";", "");
		String[] appNames = newValue.split("_");
		String appFirstName = appNames[0];
		String appLastName = appNames[1];

		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//*[text()=' Select an application && pipeline to start ']"))));
		Thread.sleep(5000);
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//*[text()='Configuration']//following::div[15]"))));
		WebElement appli = driver.findElement(By.xpath("//*[text()='Configuration']//following::div[15]"));
		Actions action = new Actions(driver);
		action.moveToElement(appli).doubleClick().build().perform();
		clickOnElement3(appFirstName);
		String pipelineNameis = "pipeline" + appLastName;
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

	public void addStageInPipeline() throws InterruptedException, IOException {
		ExcelReader read = new ExcelReader();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' ADD STAGE ']"))).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(stageName))).sendKeys("Stage1");
		wait.until(ExpectedConditions.elementToBeClickable(environment)).click();
		clickOnElement1(environmentName());
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();
		WebElement element = driver.findElement(Worker);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
		String workerValue = read.excelRead(9);
		String newValue = workerValue.replace(";", "");
		clickOnElement12(newValue);
		wait.until(ExpectedConditions.elementToBeClickable(clickOnElement("Confirm"))).click();
	}

	public void addPlugin(JSONObject request, String plugin) throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));

		JSONArray steps = (JSONArray) request.get(plugin);
		for (Object obj : steps) {
			if (obj instanceof JSONObject) {
				JSONObject step = (JSONObject) obj;
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
						driver.findElement(addStepButton));
				driver.findElement(addStepButton).click();
				wait = new WebDriverWait(driver, Duration.ofMinutes(2));
				String pluginName = step.get("name").toString();
				new Plugins(driver).addPlugin(pluginName, step);
				count++;
			}
			Thread.sleep(3000);
			driver.findElement(By.xpath("//span[normalize-space()='Save']/parent::button")).click();
			log.info("plugin added successfully");

			driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));
		}
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

	public void createStepe() throws InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));
		wait.until(ExpectedConditions.elementToBeClickable(addStepButton)).click();
		cmdplug();
	}

	public void workfloCcreation() throws InterruptedException, IOException {
		Thread.sleep(10000);
		openApplicationDashboard();
		ExcelReader read = new ExcelReader();
		String appName = read.excelRead(9);
		String newValue = appName.replace(";", "");
		String[] appNames = newValue.split("_");
		String appFirstName = appNames[0];
		String appLastName = appNames[1];
		String value = appFirstName + "_pipeline" + appLastName;

		Thread.sleep(4000);
		WebElement workflowssss = driver.findElement(workeflows);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", workflowssss);
		wait.until(ExpectedConditions.elementToBeClickable(workeflows)).click();
		Thread.sleep(8000);
		wait.until(ExpectedConditions.elementToBeClickable(addworkflows)).click();
		wait.until(ExpectedConditions.elementToBeClickable(addStages)).click();
		wait.until(ExpectedConditions.elementToBeClickable(addSteps)).click();
		Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchWorkflow)).sendKeys(value);
		Thread.sleep(4000);
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//td[text()='" + " " + value + " " + "']//preceding::td[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(addWorkflows)).click();
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(submitButtons)).click();
		Thread.sleep(4000);
		String appNameworkFlow = read.excelRead(11);
		String workflowName = appNameworkFlow.replace(";", "");
		wait.until(ExpectedConditions.visibilityOfElementLocated(WorkflowNameCreation)).sendKeys(workflowName);
		wait.until(ExpectedConditions.elementToBeClickable(addConfirmButton)).click();
		Thread.sleep(6000);
	}

	public void openApplicationDashboard() throws InterruptedException, IOException {
		driver.navigate().refresh();
		Thread.sleep(8000);
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));
		ExcelReader read = new ExcelReader();
		String appnames = read.excelRead(0);
		wait.until(ExpectedConditions.visibilityOfElementLocated(applicationViewLeftNav)).click();
		String portfolioview = driver.findElement(By.xpath("//*[contains(text(),'Dashboard')]")).getText();
		if (portfolioview.equals("Dashboard")) {
			Thread.sleep(7000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@placeholder='Search']")))
					.sendKeys(appnames);
			log.info("Application Dashboard opened");
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[normalize-space(text())='" + appnames + "']//following::mat-icon[2]")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='DevSecOps Maturity']")));
			Thread.sleep(8000);
			driver.findElement(By.xpath("//*[normalize-space(text())='" + appnames + "']//following::mat-icon[2]"))
					.click();
		} else {
			log.info("Application Dashboard is not opened");
		}
	}

}