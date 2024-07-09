package org.infy.uiPages;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TriggerPipeline {

	private WebDriver driver;
	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(TriggerPipeline.class);

	public TriggerPipeline(WebDriver driver) {
		this.driver = driver;
	}

	public By filterPipeline = By
			.xpath("//mat-label[text()='Search']/parent::label/parent::span/preceding-sibling::input");
	public By triggerPipeline = By.xpath("//table/tbody/tr[1]/td[3]/button[1]");
	By triggerHistory = By.xpath("//table/tbody/tr[1]/td[3]/button[3]/span/mat-icon");
	By applicationName = By.cssSelector("input[placeholder= 'Application Name']");
	By pipelineName = By.cssSelector("input[placeholder='Pipeline Name']");
	By releaseNumber = By.cssSelector(
			"//mat-label[normalize-space()='Release Name']/parent::label/parent::span/preceding-sibling::mat-select");
	By workerName = By.xpath(
			"//mat-label[normalize-space()='Worker Name']/parent::label/parent::span/preceding-sibling::mat-select");
	By environmentName = By.cssSelector("mat-select[placeholder='Environment Name']");
	By triggerPipelineButton = By.xpath("//span[text()=' Trigger Pipeline ']/parent::button");

	By selectValueFromDropdown(String field) {
		return By.xpath("//span[contains(text(),'" + field + "')]");
	}

	By triggerHistoryMoreOption = By.cssSelector("mat-icon[mattooltip='More Options']");
	By triggerMatrix = By.xpath("//button/mat-icon[normalize-space()='list']");
	By logs = By.xpath("//mat-tab-group/mat-tab-header/div[2]/div/div/div[6]");
	By expandPlugin = By.cssSelector(".ng-trigger.ng-trigger-indicatorRotate.ng-star-inserted");
	By repositoryBranch = By.xpath("//*[@id='formly_45_string_branch_0']");
	public By executionStatus = By.xpath("//table/tbody/tr[1]/td[7]");
	By selectWorkflow = By.xpath(
			"//mat-label[normalize-space()='Select Workflow']/parent::label/parent::span/preceding-sibling::mat-select");

	public void triggerPipeline() throws InterruptedException {

		new WebDriverWait(driver, Duration.ofMinutes(2)).until(ExpectedConditions.elementToBeClickable(filterPipeline))
				.click();
		new WebDriverWait(driver, Duration.ofMinutes(2)).until(ExpectedConditions.elementToBeClickable(filterPipeline))
				.sendKeys(PipelineView.getPipelineNameCreated());
		Thread.sleep(5000);
		driver.findElement(triggerPipeline).click();
		log.info("moved to trigger page");

	}

	public void enterTriggerDetails(JSONObject request) throws InterruptedException {

		Thread.sleep(4000);
		new WebDriverWait(driver, Duration.ofMinutes(2)).until(ExpectedConditions
				.textToBe(By.xpath("//mat-card/mat-card-content[2]/div/app-workflow-diagram/div[1]"), "Pipeline Flow"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", driver.findElement(workerName));
		((JavascriptExecutor) driver).executeScript("arguments[0].click()",
				driver.findElement(selectValueFromDropdown(request.get("agentName1").toString())));
		Thread.sleep(1000);
		driver.findElement(triggerPipelineButton).click();
		log.info("pipeline is triggered");

		log.info("pipeline is triggered");
	}

	public void enterTriggerDetails2(JSONObject request) throws InterruptedException {
		new WebDriverWait(driver, Duration.ofMinutes(2)).until(ExpectedConditions
				.textToBe(By.xpath("//mat-card/mat-card-content[2]/div/app-workflow-diagram/div[1]"), "Pipeline Flow"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", driver.findElement(workerName));
		((JavascriptExecutor) driver).executeScript("arguments[0].click()",
				driver.findElement(selectValueFromDropdown(request.get("agentName2").toString())));
		Thread.sleep(1000);
		driver.findElement(triggerPipelineButton).click();
		log.info("pipeline is triggered");
	}

	public void navigateToTriggerHistoryLogs() {
		driver.findElement(triggerHistoryMoreOption).click();
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", driver.findElement(triggerMatrix));
		new WebDriverWait(driver, Duration.ofMinutes(2)).until(ExpectedConditions.elementToBeClickable(logs)).click();

	}

	public String verifyExecutionStatus() throws InterruptedException {

		driver.findElement(By.xpath("//*[@id=\"exec-history-refresh\"]")).click();
		Thread.sleep(30000);
		driver.findElement(By.xpath("//*[@id=\"exec-history-refresh\"]")).click();
		Thread.sleep(30000);
		driver.findElement(By.xpath("//*[@id=\"exec-history-refresh\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"exec-history-refresh\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"exec-history-refresh\"]")).click();
		Thread.sleep(3000);

		String status = driver.findElement(executionStatus).getAttribute("innerText");
		log.info("Execution Status: " + status);

		if (status.equals("Failed"))
			return "trigger pipeline Failed";
		else if (status.equals("In Progress"))
			return "trigger pipeline is In Progress";
		else if (status.equals("Pending"))
			return "trigger pipeline is pending";
		else
			return "trigger pipeline Success";
	}
}
