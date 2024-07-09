package org.infy.uiPages;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.infy.init.ListenersTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class CommonMethods {

	private static WebDriver driver;
	private WebDriverWait wait;
	private ExtentTest reportLogger;
	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(CommonMethods.class);

	public CommonMethods(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		this.reportLogger = ListenersTest.logger;
		// this.softAssert = ListenersTest.softAssert;
	}

	public static By stepName = By
			.xpath("//mat-label[text()='Step Name']/parent::label/parent::span/preceding-sibling::input");

	static By inputFields(String fieldName) {
		log.info("-------------//input[contains(@id,'" + fieldName + "')]");
		return By.xpath("//input[contains(@id,'" + fieldName + "')]");
	}

	/* it is for Pipeline Name, Application Name, Stage Name and Step Name */
	By mandatoryFields(String fieldName) {
		return By.xpath("//span[normalize-space()='" + fieldName
				+ "']/parent::div/parent::div/parent::div/div[3]/div/mat-error");
	}

	By field(String fieldName) {
		return By.xpath("//span[normalize-space()='" + fieldName + "']/parent::div");
	}

	/* it is for Pipeline Name, Application Name, Stage Name and Step Name */
	By getPipelinesFieldstext(String fieldName) {
		return By.xpath("//span[normalize-space()='" + fieldName + "']/parent::div/child::span//mat-label");
	}

	By requiredField(String fieldName) {
		return By.xpath("//input[contains(@id,'" + fieldName
				+ "')]/ancestor::mat-form-field/descendant::formly-validation-message");
	}

	By dropDownrequiredField(String fieldName) {
		return By.xpath("//mat-select[contains(@id,'" + fieldName
				+ "')]/ancestor::mat-form-field/descendant::formly-validation-message");
	}

	By fieldText(String fieldName) {
		return By.xpath("//input[contains(@id,'" + fieldName
				+ "')]/parent::app-formly-field-mat-input/following-sibling::span//mat-label");
	}

	By summitPipelineMessage = By.xpath("//*[contains(@class, 'mat-simple-snackbar')]/span");

	By filterPipeline = By.xpath("//mat-label[text()='Search']/parent::label/parent::span/preceding-sibling::input");

	By selectApplicationFromDropdown(String appName) {
		return By.xpath(String.format(
				"//div[contains(@class,'mat-select-panel')]//mat-option/span[normalize-space()='" + appName + "']",
				appName));
	}

	static By selectfromDropDown(String fieldName) {
		return By.xpath("//mat-label[text()='" + fieldName + "']/parent::label/preceding::mat-select[1]");
	}

	By clickOnExpandPlugin(String plugin) {
		return By.xpath("//mat-chip/span[contains(text(),'" + plugin + "')]");
	}

	public boolean isRequiredField(String fieldName) {
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(0));
			boolean isRequired = isElementPresent(requiredField(fieldName));
			String msg = isRequired ? fieldName + " : is a required field" : fieldName + " : is an optional field";
			log.info(msg);
			return isRequired;
		} catch (Exception e) {
			return false;
		} finally {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		}
	}

	public void refreshMyApplciation(int n) {
		for (int i = 0; i <= n; i++) {
			driver.navigate().refresh();
		}
	}

	public void waitObjecttoLoad(int sec) {
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(sec));
	}

	public void enterFieldDetails(String fieldName, String fieldValue) {
		fieldValue = fieldValue != null ? fieldValue : "";
		WebElement element = driver.findElement(inputFields(fieldName));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.clear();
		boolean isRequired = isRequiredField(fieldName) && fieldValue.isEmpty();
		if (isRequired) {
			reportLogger.log(Status.FAIL, "Pipeline cannot be created as field value is empty for field: " + fieldName);
		}
		element.sendKeys(fieldValue);
	}

	public static boolean isElementPresent(By by) {
		return !driver.findElements(by).isEmpty();
	}

	public static void sendText(By by, String text) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(by));
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(text);
	}

	public static String dateandTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String datetime = dtf.format(now).toString().replace("/", "_").replace(":", "_").replace(" ", "_");
		log.info(datetime);
		return datetime;
	}

	public static int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(100000);
	}

	/* get fieldType for Plugin's Fields */
	public String getFieldType(String fieldname) {
		WebElement element = driver.findElement(inputFields(fieldname));
		log.info(element.getAttribute("Type"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		return element.getAttribute("Type");
	}

	/* get fieldText for Plugin's Fields */
	public String getFieldText(String fieldname) {
		WebElement element = driver.findElement(fieldText(fieldname));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		return element.getText();
	}

	/*
	 * getting fields name for Application Name, Pipeline Name, Stage Name and Step
	 * Name
	 */
	public String getFieldsHeaderName(String fieldName) {
		return driver.findElement(getPipelinesFieldstext(fieldName)).getAttribute("innerText");

	}

	/* checking for mandatory fields */
	public String verifyMandatoryFields(String fieldname) {
		WebElement element = driver.findElement(field(fieldname));
		element.click();
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();
		action.sendKeys(Keys.TAB).build().perform();
		log.info(driver.findElement(mandatoryFields(fieldname)).getAttribute("innerText"));
		return driver.findElement(mandatoryFields(fieldname)).getText();
	}

	public String verifyStepNameIsMandatory(String fieldname) throws InterruptedException {
		Thread.sleep(1000);
		WebElement element = driver.findElement(stepName);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.clear();
		element.sendKeys("c");
		new Actions(driver).sendKeys(Keys.BACK_SPACE).build().perform();
		Thread.sleep(2000);
		return driver.findElement(mandatoryFields(fieldname)).getAttribute("innerText");
	}

	/* checking for mandatory fields for plugin's Fields */
	public String verifypluginMandatoryFields(String fieldname) throws InterruptedException {
		WebElement element = driver.findElement(inputFields(fieldname));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.clear();
		element.sendKeys("c");
		new Actions(driver).sendKeys(Keys.BACK_SPACE).build().perform();
		Thread.sleep(1000);
		return driver.findElement(requiredField(fieldname)).getAttribute("innerText");
	}

	/*
	 * getting Toast Messages after submission of pipeline and trigger the pipeline
	 */
	public String getPipelineSubmissionMessage() {
		// return driver.findElement(summitPipelineMessage).getAttribute("innerText");
		log.info("get Pipeline submission 1");
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.pollingEvery(Duration.ofMinutes(2));
		WebElement x = fluentWait.until(ExpectedConditions.visibilityOfElementLocated(summitPipelineMessage));
		log.info("able to fetch web element");
		return x.getAttribute("innerText");
	}

	public void filterPipeline(String pipelineName) {
		new WebDriverWait(driver, Duration.ofMinutes(2)).until(ExpectedConditions.elementToBeClickable(filterPipeline))
				.sendKeys(pipelineName);
	}

	public void selectFieldfromDropDown(String fieldName, String fieldValue) {
		// TODO Auto-generated method stub
		fieldValue = fieldValue != null ? fieldValue : "";
		WebElement element = driver.findElement(selectfromDropDown(fieldName));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		boolean isRequired = isRequiredField(fieldName) && fieldValue.isEmpty();

		if (isRequired) {
			reportLogger.log(Status.FAIL, "Pipeline cannot be created as field value is empty for field: " + fieldName);
		}
		element.click();
		driver.findElement(selectApplicationFromDropdown(fieldValue)).click();
	}

	public String verifypluginMandatoryFieldsDropDown(String fieldname, String PluginName) throws InterruptedException {
		WebElement element = driver.findElement(inputFields(fieldname));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		new Actions(driver).sendKeys(Keys.ESCAPE).build().perform();
		driver.findElement(clickOnExpandPlugin(PluginName)).click();
		driver.findElement(selectfromDropDown(fieldname)).click();
		new Actions(driver).sendKeys(Keys.ESCAPE).build().perform();
		Thread.sleep(1000);
		return driver.findElement(dropDownrequiredField(fieldname)).getAttribute("innerText");

	}

}
