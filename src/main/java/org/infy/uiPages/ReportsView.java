package org.infy.uiPages;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReportsView {
	private WebDriver driver;
	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(ReportsView.class);

	public ReportsView(WebDriver driver) {
		this.driver = driver;
	}

	By pipelineDashboardText = By.xpath("//app-pipeline/div/div[text()=\" Pipeline Dashboard - Developer View\"]");
	By reportsLeftSideNav = By.xpath("//mat-nav-list/app-mat-menu-list[8]/mat-list-item/div");

	By selectFromDropdown(String fieldName) {
		return By.xpath("//div[@role=\"listbox\"]/mat-option/span[text()='" + " " + fieldName + " " + "']");
	}

	By dateInputFields(String date) {
		return By.xpath("//input[contains(@id,\"mat-input-\")]/following::span/label/mat-label[text()='" + date
				+ "']/parent::label/ancestor::span/preceding::input");
	}

	public void download_Report(String reportName, String startDate, String endDate, String appName,
			String pipelineName) throws InterruptedException {
		PipelineView pv = new PipelineView(driver);
		pv.navigateToPipeLineEditor();
		driver.findElement(reportsLeftSideNav).click();
		log.info("Entered the reports page");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//form/div[2]/mat-form-field/div/div[1]/div[3]")).click();
		driver.findElement(selectFromDropdown(reportName)).click();
		log.info("Clicked the report input");
		Thread.sleep(4000);
		new WebDriverWait(driver, Duration.ofMinutes(2)).until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//form/div[4]/mat-form-field/div/div/div[3]"))))
				.click();
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(selectFromDropdown(appName)));
		new WebDriverWait(driver, Duration.ofMinutes(2))
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(selectFromDropdown(appName))))
				.click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//form/div[5]/mat-form-field/div/div/div[3]")).click();
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(selectFromDropdown(pipelineName)));
		driver.findElement(selectFromDropdown(pipelineName)).click();
		new WebDriverWait(driver, Duration.ofMinutes(2))
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(
						"//input[contains(@id,\"mat-input-\")]/following::span/label/mat-label[text()=\"Start Date\"]/parent::label/ancestor::span/preceding::input/parent::div"))))
				.click();
		driver.findElement(dateInputFields("Start Date")).sendKeys(startDate);
		new WebDriverWait(driver, Duration.ofMinutes(2))
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(
						"//input[contains(@id,\"mat-input-\")]/following::span/label/mat-label[text()=\"End Date\"]/parent::label/ancestor::span/preceding::input[1]/parent::div"))))
				.click();
		driver.findElement(By.xpath(
				"//input[contains(@id,\"mat-input-\")]/following::span/label/mat-label[text()=\"End Date\"]/parent::label/ancestor::span/preceding::input[1]"))
				.sendKeys(endDate);
		new WebDriverWait(driver, Duration.ofMinutes(2))
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//form/div[8]/button"))))
				.click();

	}

}
