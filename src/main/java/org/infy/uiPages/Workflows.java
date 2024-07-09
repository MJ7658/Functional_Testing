package org.infy.uiPages;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.infy.init.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Workflows extends BrowserFactory {

	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(Workflows.class);

	public By addWorkflow = By.xpath("//span[text()='Add Workflow']");
	By WorkFlowTab = By.xpath("//div/div/span[text()=\" Workflows \"]");

	By customWorkflow = By.xpath(
			"//div[@class=\"mat-tab-body-wrapper\"]/mat-tab-body[2]/div/app-workflow-editor/div/mat-card/mat-tab-group/mat-tab-header/div[2]/div/div/div[2]");
	By saveAs = By.xpath("//span[text()=' Save as ']");
	By workflowNameTextbox = By
			.xpath("//mat-label[text()='Enter name for workflow']/parent::label/parent::span/preceding-sibling::input");
	public By confirmWorkflow = By.xpath("//span[text()=' Confirm ']");
	public By editWorkflow = By.xpath("//button/span/span[text()=\"Edit\"]");

	public void clickWorkflowTab() {
		new WebDriverWait(driver, Duration.ofMinutes(2)).until(ExpectedConditions.elementToBeClickable(WorkFlowTab))
				.click();
		log.info("WorkFlow Tab is clicked.");

	}

	public void selectCustomWorkflow() {
		new WebDriverWait(driver, Duration.ofMinutes(2)).until(ExpectedConditions.elementToBeClickable(customWorkflow))
				.click();
		log.info("Custom WorkFlow Tab is clicked.");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(editWorkflow));

	}

	public void clickAddWorkflow() throws InterruptedException {
		List<WebElement> stages = driver.findElements(By.xpath("//mat-chip-list[@id=\"cdk-drop-list-0\"]/div/div"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(addWorkflow));
		new WebDriverWait(driver, Duration.ofMinutes(2)).until(ExpectedConditions.elementToBeClickable(addWorkflow))
				.click();
		log.info("Add workflow button is clicked.");
		if (stages.size() > 2) {
			new WebDriverWait(driver, Duration.ofMinutes(2))
					.until(ExpectedConditions.elementToBeClickable(
							driver.findElement(By.xpath("//div[3]/mat-chip/child::mat-icon[text()=\" cancel \"]"))))
					.click();
		}
	}

	public void clickSaveAs() {
		driver.findElement(By.xpath(
				"//div[@class=\"workflow-action-buttons ng-star-inserted\"]/button[2]/span[text()=\" Save As \"]"))
				.click();
		log.info("Save as button is clicked.");
	}

	public void enterWorkflowName() {
		new WebDriverWait(driver, Duration.ofMinutes(2))
				.until(ExpectedConditions.elementToBeClickable(workflowNameTextbox)).sendKeys("W" + LocalDate.now());
		log.info("Workflow name is entered.");
	}

	public void clickConfirm() {
		new WebDriverWait(driver, Duration.ofMinutes(2)).until(ExpectedConditions.elementToBeClickable(confirmWorkflow))
				.click();
		log.info("Confirm button is clicked.");
	}

	public void selectWorkflow() throws InterruptedException {
		clickAddWorkflow();
		clickSaveAs();
		enterWorkflowName();
		clickConfirm();
	}

}
