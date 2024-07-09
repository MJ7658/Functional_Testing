package org.infy.commonHelpers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TelementryHelper {

	WebDriver driver;
	private WebDriverWait wait;

	public TelementryHelper(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(800));
	}

	@FindBy(xpath = "//*[text()=' Analytics ']")
	WebElement Analytics;

	@FindBy(xpath = "//*[text()=' Analytics ']//following::span[6]")
	WebElement Telementry;

	@FindBy(xpath = "//*[text()=' AI/ML Feature Usage']")
	WebElement featureUsage;

	public void navTelementry() {
		wait.until(ExpectedConditions.visibilityOf(Analytics)).click();
		wait.until(ExpectedConditions.visibilityOf(Telementry)).click();
	}

	public void navFeatureUsage() throws InterruptedException {
		Thread.sleep(4000);
		WebElement element = driver.findElement(By.xpath("//*[text()=' AI/ML Feature Usage']"));
		Thread.sleep(4000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(4000);
	}

	public String validate_Resolve_GPT() throws InterruptedException {
		Thread.sleep(6000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Object longValue = js.executeScript(
				"return document.getElementsByClassName('chartjs-render-monitor')[2].__ngContext__[36][0].data[0];");
		String stringValue = String.valueOf(longValue);
		return stringValue;
	}

	@FindBy(xpath = "//*[text()='Portfolios']//preceding::div[1]")
	WebElement portfoliosCount;

	@FindBy(xpath = "//*[text()='Applications']//preceding::div[1]")
	WebElement applicationsCount;

	@FindBy(xpath = "//*[text()='Pipelines']//preceding::div[1]")
	WebElement pipelinesCount;

	@FindBy(xpath = "//*[text()='Triggers']//preceding::div[1]")
	WebElement triggersCount;

	@FindBy(xpath = "//*[text()='Plugins']//preceding::div[1]")
	WebElement PluginsCount;

	@FindBy(xpath = "//*[text()='Unique Users']//preceding::div[1]")
	WebElement UniqueUserCount;

	public ArrayList<String> telementryCountCollect() {
		String portfoliocunt = wait.until(ExpectedConditions.visibilityOf(portfoliosCount)).getText();
		String applicationscunt = wait.until(ExpectedConditions.visibilityOf(applicationsCount)).getText();
		String pipelinescunt = wait.until(ExpectedConditions.visibilityOf(pipelinesCount)).getText();
		String triggerscunt = wait.until(ExpectedConditions.visibilityOf(triggersCount)).getText();
		String Pluginscunt = wait.until(ExpectedConditions.visibilityOf(PluginsCount)).getText();
		String UniqueUsercunt = wait.until(ExpectedConditions.visibilityOf(UniqueUserCount)).getText();

		ArrayList<String> list = new ArrayList<String>(Arrays.asList(portfoliocunt, applicationscunt, pipelinescunt,
				triggerscunt, Pluginscunt, UniqueUsercunt));
		return list;
	}

}
