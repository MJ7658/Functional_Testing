package org.infy.uiPages;

import java.time.Duration;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Portfolio {

	private WebDriver driver;
	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(Portfolio.class);
	public static String portfolioNameToBeCreated;
	public static String pipelineNameToBeCreated;
	public static String pipelineTemplateNameToBeCreated;
	public static String pipelineTemplateNameCreated;
	int count = 0;

	public Portfolio(WebDriver driver) {
		this.driver = driver;
	}

	public By savePluginButton = By.xpath("//span[normalize-space()='Save']/parent::button");
	public By stageName = By.xpath("//*[@name='userInput']");
	public By Dashboard = By.xpath("//mat-button-toggle-group/mat-button-toggle/button/span");
	public By portfolioLeftNav = By.xpath("//mat-list-item//span[text()=' Portfolio ']");
	public By manage = By.xpath("//mat-button-toggle-group/mat-button-toggle[2]/button");
	public By portfolioViewSideNav = By.xpath("//mat-sidenav-content//span[normalize-space(text())='Portfolio List']");
	By createNewPortfolioButon = By.xpath("//button[@mattooltip='Create a Portfolio']");
	By portfolioSettings = By.xpath("//button[@mattooltip='Settings']");
	public By createNewTemplateButton = By.xpath("//button[@mattooltip='Create New Template']");
	By portfolioNamed = By
			.xpath("//span[normalize-space()='Portfolio Name']/ancestor::mat-form-field/descendant::input");
	By owners = By.xpath("//input[@placeholder='Owners']");
	By createPortfolio = By.xpath("//mat-dialog-actions/button[2]");
	By createnewPortfolio = By.xpath("//span[normalize-space()='Create']/parent::button");
	By back = By.xpath("//mat-icon[contains(text(),'arrow_back')]");
	public By filterPipeline = By.xpath("//input[@placeholder=\"Search\"]");
	By searchPipeline = By.xpath("//mat-label[text()='Search']/parent::label/parent::span/preceding-sibling::input");
	public By filterPortfolio = By.xpath("//*[@id=\"mat-input-0\"]");
	By portfolioListTableRows = By
			.xpath("//span[text()='Portfolio List']/parent::mat-toolbar/following-sibling::table/tbody/tr");
	By editPortfolio = By.xpath(
			"//mat-sidenav-content//span[normalize-space(text()='List of Releases')]/parent::mat-toolbar/following-sibling::table/tbody/tr[1]/descendant::mat-icon[@mattooltip='Edit Portfolio']");
	public By searchPluginTextBox = By.cssSelector("input[placeholder= 'Start searching plugins']");
	public By rightArrow = By.xpath("//mat-tab-group/mat-tab-header/div[3]");
	By application = By.cssSelector("mat-select[name='applicationName']");
	By pipelineName = By.cssSelector("input[placeholder= 'Pipeline Name']");

	By submitPipelineButton_PipelineDetailsEditor(String text) {
		return By.xpath("//span[normalize-space()='" + text + "']/parent::button");
	}

	By submitPipelineButton_PipelineDetailsEditors(String text) {
		return By.xpath("//span[normalize-space()=" + text + "]/parent::button");
	}

	public By clickOnElement(String fieldName) {
		return By.xpath("//*[contains(text(),'" + fieldName + "')]");
	}

	By selectFromDropdown(String appName) {
		return By.xpath(String.format(
				"//div[contains(@class,'mat-select-panel')]//mat-option/span[normalize-space()='" + appName + "']",
				appName));
	}

	By selectPlugin(String pluginName) {
		return By.xpath(String.format(
//changed by me				"//div[contains(@class,'pipeline-manager-card')]//strong[text()='" + pluginName + "']",
				"//div[contains(@class,'mat-title')]//mat-title[text()='" + pluginName + "']", pluginName));
	}

	By clickOnExpandPlugin(String plugin) {
		return By.xpath("//mat-chip/span[contains(text(),'" + plugin + "')]");
	}

	public void expandPluginDetails(String pluginName) {
		log.info("Entered in expandPluginDetails method");
		driver.findElement(clickOnExpandPlugin(pluginName)).click();
	}

	public By portfolio_Manage_Tab_Content(String tabName) {
		return By.xpath("//mat-tab-group/mat-tab-header/div[2]/div/div/div/div[text()='" + tabName + "']");
	}

	public void openPortfolioView() throws InterruptedException {
		driver.findElement(portfolioLeftNav).click();
		Thread.sleep(5000);
		driver.findElement(manage).click();
		String portfolioview = driver.findElement(By.xpath("//*[contains(text(),'Portfolio List')]")).getText();
		if (portfolioview.equals("Portfolio List")) {
			log.info("Portfolio view opened");
		} else {
			driver.findElement(portfolioLeftNav).click();
			Thread.sleep(5000);
			driver.findElement(manage).click();
		}

	}

	public void createPipelineTemplate(JSONObject request) throws InterruptedException {
		new WebDriverWait(driver, Duration.ofMinutes(2)).until(
				ExpectedConditions.textToBe(By.xpath("//mat-chip-list[contains(@id,\"mat-chip-list-\")]/div/div/div"),
						"Select an application && pipeline to start"));
		Thread.sleep(3000);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",
				new WebDriverWait(driver, Duration.ofMinutes(2))
						.until(ExpectedConditions.elementToBeClickable(driver.findElement(application))));
		String applicationName = request.containsKey("applicationName") ? (String) request.get("applicationName")
				: "App101";
		Thread.sleep(3000);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",
				driver.findElement(selectFromDropdown(applicationName)));
		pipelineNameToBeCreated = request.get("name").toString() + CommonMethods.getRandomNumber();
		Thread.sleep(1000);
		driver.findElement(pipelineName).sendKeys(pipelineNameToBeCreated);
	}

	public void addStageInPipelineTemplateWithoutConfiguration(JSONObject request) throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.xpath("//mat-chip/child::span[text()=\" ADD STAGE \"]")).click();
		driver.findElement(stageName).sendKeys(request.get("stageName").toString() + String.valueOf(count));
		log.info(request.get("stageName").toString() + String.valueOf(count));
		driver.findElement(clickOnElement("Confirm")).click();
	}

	public static int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

	public void addPlugin(JSONObject request, String[] plugins) throws InterruptedException {
		for (String pl : plugins) {
			addStageInPipelineTemplateWithoutConfiguration(request);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
					driver.findElement(By.id("add-step-chip-" + String.valueOf(count))));
			driver.findElement(By.id("add-step-chip-" + String.valueOf(count))).click();
			driver.findElement(searchPluginTextBox).sendKeys(pl);
			driver.findElement(selectPlugin(pl)).click();
			log.info("gitscm plugin selected");
			expandPluginDetails(pl);
			log.info("Plugin is expaneded");
			driver.findElement(savePluginButton).click();
			count++;
		}
	}

	public void submitPipelineTemplate(JSONObject request) throws InterruptedException {
		Thread.sleep(4000);

		if (driver.findElement(By.xpath("//span[normalize-space()='queue']/parent::button")).isEnabled()) {
			log.info("Able to find save Template");
			Thread.sleep(4000);
			JavascriptExecutor je = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.xpath("//span[normalize-space()='queue']/parent::button"));
			je.executeScript("arguments[0].scrollIntoView(true);", element);
			driver.findElement(By.xpath("//span[normalize-space()='queue']/parent::button")).click();
			log.info("clicked");
			// driver.findElement(By.xpath("//span[normalize-space()='queue']/parent::button")).click();
			pipelineTemplateNameToBeCreated = request.get("pipelineName").toString()
					+ String.valueOf(getRandomNumber());
			log.info(pipelineTemplateNameToBeCreated);
			Thread.sleep(1000);
			driver.findElement(
					By.xpath("//mat-label[text()='Template Name']/parent::label/parent::span/preceding-sibling::input"))
					.sendKeys(pipelineTemplateNameToBeCreated);
			new WebDriverWait(driver, Duration.ofMinutes(2))
					.until(ExpectedConditions.elementToBeClickable(driver
							.findElement(By.xpath("//app-save-as-template-dialog/form/mat-dialog-actions/button[1]"))))
					.click();
			log.info("Pipeline Template created successfully");
		} else {
			new WebDriverWait(driver, Duration.ofMinutes(2)).until(ExpectedConditions
					.elementToBeClickable(driver.findElement(submitPipelineButton_PipelineDetailsEditor("queue"))))
					.click();
			pipelineTemplateNameToBeCreated = request.get("pipelineName").toString()
					+ String.valueOf(getRandomNumber());
			driver.findElement(
					By.xpath("//mat-label[text()='Template Name']/parent::label/parent::span/preceding-sibling::input"))
					.sendKeys(pipelineTemplateNameToBeCreated);
			driver.findElement(By.xpath("//app-save-as-template-dialog/form/mat-dialog-actions/button[1]")).click();
			log.info("Pipeline Template created successfully");
		}
	}

	public void createPortfolio(JSONObject request) throws InterruptedException {
		driver.findElement(createNewPortfolioButon).click();
		log.info("Navigated to Portfolio details page");
		portfolioNameToBeCreated = request.get("portfolioName").toString() + CommonMethods.getRandomNumber();
		driver.findElement(portfolioNamed).sendKeys(portfolioNameToBeCreated);
		log.info("portfolio name is enetered" + portfolioNameToBeCreated);
		Thread.sleep(1000);
		JSONArray ownerslist = (JSONArray) request.get("owners");
		for (int i = 0; i < ownerslist.size(); i++) {
			JSONObject ownername = (JSONObject) ownerslist.get(i);
			Thread.sleep(1000);
			driver.findElement(owners).sendKeys(ownername.get("ownerid").toString());
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ENTER).build().perform();
		}
		log.info("ownerlist is added");
		driver.findElement(createnewPortfolio).click();
		Thread.sleep(2000);
		log.info("Portfolio general details are saved");

	}

	public boolean verifyPortfolioCreated() throws InterruptedException {
		driver.findElement(back).click();
		Thread.sleep(1000);
		driver.findElement(searchPipeline).sendKeys(portfolioNameToBeCreated);
		return !driver.findElements(portfolioListTableRows).isEmpty();
	}

	public void editPortfolio() throws InterruptedException {

		Thread.sleep(5000);
		driver.findElement(manage).click();
		Thread.sleep(4000);
		driver.findElement(searchPipeline).sendKeys(portfolioNameToBeCreated);
		if (!driver.findElements(By.xpath("//table/tbody/tr")).isEmpty()) {
			Thread.sleep(2000);
			driver.findElement(editPortfolio).click();
		}

	}

	public static String getPipelineTemplateNameCreated() {
		return pipelineTemplateNameCreated;
	}

	public void setPipelineTemplateNameCreated() {
		Portfolio.pipelineTemplateNameCreated = pipelineTemplateNameToBeCreated;
	}

	public boolean verifyPortfolioNameIsMandatory() {
		return !driver.findElement(createPortfolio).isEnabled();
	}
}
