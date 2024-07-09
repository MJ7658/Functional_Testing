package org.infy.uiPages;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.infy.uiPages.plugins.Plugins;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PipelineView {

	private WebDriver driver;
	private Wait<WebDriver> wait;
	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(PipelineView.class);
	public static String pipelineNameToBeCreated;
	public static String pipelineNameCreated;
	int count = 0;

	public PipelineView(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofMinutes(2));
	}

	public By pipelineViewLeftNav = By.xpath("//mat-list-item//span[text()=' Pipeline ']");
	By pipelineViewSideNav = By.xpath("//mat-nav-list/app-mat-menu-list[3]/mat-list-item/div");
	By listViewIcon = By.xpath("//button//mat-icon[normalize-space()='list']");
	By gridViewIcon = By.xpath("//button//mat-icon[normalize-space()='grid_view']");
	By filterPipeline = By.xpath("//mat-label[text()='Search']/parent::label/parent::span/preceding-sibling::input");
	By pipelineListTableRows = By
			.xpath("//*[text()='Pipeline list']/following-sibling::div/descendant::table/tbody/tr");

	public By createNewPipelineButon = By.xpath("//button[@mattooltip='Create new Pipeline']");
	By matPipelineContainer = By.xpath("//app-pipeline-editor-container");
	By matCardTitle = By.xpath("//mat-card-title");
	By pageTitle = By.xpath("//div[@class='mat-title']");
	By application = By.cssSelector("mat-select[name='applicationName']");
	By searchApplicationName = By.cssSelector("input[placeholder='Search Applications']");

	By selectFromDropdown(String appName) {

		return By.xpath(String.format(
				"//div[contains(@class,'mat-select-panel')]//mat-option/span[normalize-space()='" + appName + "']",
				appName));
	}

	By pipelineName = By.cssSelector("input[placeholder= 'Pipeline Name']");
	By addStageButton_PipelineDetailsEditor = By.xpath("//span[normalize-space()='Add Stage']/parent::button");
	By addStageButton_CreatePipelineEditor = By.xpath("//mat-chip[@id='add-stage-chip']");
	By stageNameDialog = By.xpath("//mat-dialog-container//h1[normalize-space()='Enter Stage Name']");
	public By footbartext = By.xpath(
			"//div[@class=\"editor-container\"]/mat-chip-list/div/div[2]/div[text()=\" Double click on stage to rename stage, add environments and worker, Click on a step to enter plugin input \"]");
	By stageName_PipelineDetailsEditor = By.cssSelector("input[placeholder= 'Stage Name']");
	By dialogConfirmButton = By.xpath("//app-user-input-dialog//span[normalize-space()='Confirm']/parent::button");
	public By addStepButton = By.xpath("//mat-chip[contains(@id,'add-step')]");
	By addStepButton_PipelineDetailsEditor = By.xpath("//span[normalize-space()='Add Step']/parent::button");

	By submitPipelineButton_PipelineDetailsEditor(String text) {
		return By.xpath("//span[normalize-space()='" + text + "']/parent::button");
	}

	By continueOnFailureBox = By.xpath("//*[contains(@name,'continueOnFailureInput')]");

	public By savePluginButton = By.xpath("//span[normalize-space()='Save']/parent::button");
	public By savePipelineButton = By.xpath("//mat-icon[normalize-space()='save']/parent::span/parent::button");
	By resetPipelineButton = By.xpath("//mat-icon[normalize-space()='refresh']/parent::span/parent::button");
	By choosePluginDialog = By.xpath("//mat-dialog-container//mat-toolbar-row[normalize-space()='Choose a plugin']");
	public By searchPluginTextBox = By.cssSelector("input[placeholder= 'Start searching plugins']");
	By defaultStageName = By.cssSelector("input[placeholder='Stage Name']");
	public By manage = By.xpath("//mat-button-toggle-group/mat-button-toggle[2]/button");

	public By stepName_existingplugin(String name) {
		return By.xpath("//span[normalize-space()='" + name + "']/parent::mat-chip/span");
	}

	public By stepName_existingplugin1() {
		return By.xpath("//span[normalize-space()='GITSCM']/parent::mat-chip/span");
	}

	// added by me
	By stageName_existing(String text) {
		return By.xpath("//div/mat-chip/span[text()='" + text + "']");
	}

	public By selectPlugin(String pluginName) {

		System.out.println(String.format("//div[contains(@class,'mat')]//*[text()='" + pluginName + "']", pluginName));
		return By.xpath(String.format(
//changed by me				"//div[contains(@class,'pipeline-manager-card')]//strong[text()='" + pluginName + "']",
				"//div[contains(@class,'mat')]//mat-title[text()='" + pluginName + "']", pluginName));
	}

	By deleteStageButton = By.xpath("//mat-icon[text()='delete']/parent::span/parent::button");
	By deleteStepButton = By.xpath("//span[normalize-space()='Delete']/parent::button");
	By switchEditor = By.cssSelector("button[mattooltip='Switch Editor']");
	By confirmSwitchEditor = By.xpath("//app-switch-editor-dialog//span[normalize-space()='Yes']/parent::button");
	By submitPipeline = By.xpath("//span[normalize-space()='Submit Pipeline']/parent::button");
	By Home = By.xpath("//mat-sidenav-content//a/span[normalize-space()='Home']");
	By GoBack = By.xpath("//button[@mattooltip='Go back']");

	By Pipeline = By.xpath("//*[text()=' Pipeline ']");

	By pipelineEditor = By.xpath("//app-pipeline-editor");
	By visualEditor = By.xpath("//app-visual-editor");
	By addStage = By.xpath("//*[@id='add-stage-chip']");
	By secondStage = By.id("cdk-step-label-0-1");
	By stepNameTitle = By.xpath("//mat-expansion-panel/mat-expansion-panel-header/span[1]/mat-panel-title/strong");
	public By stageName = By.xpath("//*[@name='userInput']");
	public By environment = By.xpath(
			"//mat-label[normalize-space()='Environments']/parent::label/parent::span/preceding-sibling::mat-select");
	public By Worker = By
			.xpath("//mat-label[normalize-space()='Worker']/parent::label/parent::span/preceding-sibling::mat-select");
	By moreOptions = By.xpath("//table/tbody/tr[1]/td[3]/button[4]");
	public By editOptions = By.xpath("//table/tbody/tr/td[3]/button[5]");
	By cloneOptions = By.xpath("//tbody/tr/td[3]/button[6]");

	By getPluginName(String field) {
		return By.xpath("//mat-chip/span[contains(text(),'" + field + "')]");

	}

	By clickOnExpandPlugin(String plugin) {
		return By.xpath("(//*[contains(text(),'" + plugin + "')])[1]");
		
	}

	By clickOnParticExpandPlugin(String stage, String plugin) {
		return By.xpath("//*[normalize-space(text())='" + stage + "']//following::div[1]//*[normalize-space(text())='"
				+ plugin + "']");
	}

	By clickOnExpandPlugin1(String plugin) {

		return By.xpath("//mat-chip/span[contains(text(),'" + plugin + "')]//following::span[19]");
	}

	public By clickOnElement(String fieldName) {
		return By.xpath("//*[contains(text(),'" + fieldName + "')]");
	}

	By pluginArea(String pluginName) {
		return By
				.xpath("//mat-expansion-panel-header//mat-panel-title/strong[contains(text(),': " + pluginName + "')]");
	}

	By collapsedPluginArea(String pluginName) {
		return By.xpath(
				"//mat-expansion-panel-header[@aria-expanded='false']//mat-panel-title/strong[contains(text(),': "
						+ pluginName + "')]");
	}

	public void openPipelineView() throws InterruptedException {
		Thread.sleep(8000);
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));
		new WebDriverWait(driver, Duration.ofMinutes(2))
				.until(ExpectedConditions.visibilityOf(driver.findElement(pipelineViewLeftNav))).click();
		Thread.sleep(4000);

		((JavascriptExecutor) driver).executeScript("arguments[0].click()", driver.findElement(manage));
		Thread.sleep(4000);
		log.info("Pipeline view opened");
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));

	}

	public void navigateToVisualEditor() throws InterruptedException {
		openPipelineView();
		Thread.sleep(6000);
		driver.findElement(createNewPipelineButon).click();
	}

	public void navigateToPipeLineEditor() throws InterruptedException {
		openPipelineView();
		Thread.sleep(8000);
		((JavascriptExecutor) driver).executeScript("arguments[0].click()",
				new WebDriverWait(driver, Duration.ofMinutes(2))
						.until(ExpectedConditions.elementToBeClickable(driver.findElement(createNewPipelineButon))));
		log.info("Navigated to Pipeline details page");
	}

	public void openExistingPipeline() throws InterruptedException {
		openPipelineView();
		new CommonMethods(driver).filterPipeline(PipelineView.getPipelineNameCreated());
		log.info("Pipeline is searched");
		Thread.sleep(1000);
		driver.findElement(editOptions).click();
		log.info("navigate to Pipeline page");
		Thread.sleep(1000);

	}

	public void editPipeline(String existingStepName, String stepname) throws InterruptedException {
		Thread.sleep(6000);

		new CommonMethods(driver).filterPipeline(PipelineView.getPipelineNameCreated());
		log.info("Pipeline is searched");
		Thread.sleep(5000);
		driver.findElement(editOptions).click();
		log.info("navigate to Pipeline page");
		Thread.sleep(5000);
		JavascriptExecutor je = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(stepName_existingplugin(existingStepName));
		je.executeScript("arguments[0].scrollIntoView(true);", element);

		Thread.sleep(4000);
		driver.findElement(stepName_existingplugin(existingStepName)).click();
		log.info("Navigate to Plugin Page");
		Thread.sleep(2000);

		driver.findElement(CommonMethods.stepName).clear();
		driver.findElement(CommonMethods.stepName).sendKeys(stepname);
		log.info("Step name is edited");
		driver.findElement(savePluginButton).click();
	}

	public void editPipeline(String stageName) throws InterruptedException {
		new CommonMethods(driver).filterPipeline(PipelineView.getPipelineNameCreated());
		log.info("Pipeline is searched");
		Thread.sleep(1000);
		// changed by me
		driver.findElement(By.xpath("//table/tbody/tr/td[3]/button[5]/span/mat-icon")).click();
		List<WebElement> Stages = driver.findElements(By.xpath("//div[@class=\"stage-chip-wrapper\"]/mat-chip/span"));
		log.info(Stages.size());
		for (WebElement s : Stages) {
			if (s.getText().trim().contentEquals(stageName)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
						driver.findElement(By.xpath("//div/mat-chip/span[contains(text(),'" + stageName + "')]")));
				WebElement element = driver
						.findElement(By.xpath("//div/mat-chip/span[contains(text(),'" + stageName + "')]"));
				element.click();
				Actions ac = new Actions(driver);
				ac.doubleClick(element).perform();
				Thread.sleep(1000);
				log.info("Navigated to Stage page");
			}
		}

	}

	// added by me
	public void editStageInPipeline(String stageName, String envnmnt, String worker) throws InterruptedException {
		driver.findElement(By.xpath("//mat-chip-list/div/mat-chip/mat-icon")).click();
		driver.findElement(environment).click();

		((JavascriptExecutor) driver).executeScript("arguments[0].click()",
				driver.findElement(selectFromDropdown(envnmnt)));

		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();
		WebElement element = driver.findElement(Worker);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
		driver.findElement(selectFromDropdown(worker)).click();
		driver.findElement(clickOnElement("Confirm")).click();

	}

	public void clonePipeline() {
		new CommonMethods(driver).filterPipeline(PipelineView.getPipelineNameCreated());
		log.info("Pipeline is searched");
		new WebDriverWait(driver, Duration.ofMinutes(2))
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(cloneOptions))).click();
		log.info("navigate to cloned Pipeline page");
	}

	public void createPipeline(JSONObject request) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));
		Thread.sleep(5000);
		Thread.sleep(4000);

		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//div[starts-with(@class,'mat-form-field-infix ng-tns-c')]"))));
		WebElement appli = driver.findElement(By.xpath("//div[starts-with(@class,'mat-form-field-infix ng-tns-c')]"));
		Actions action = new Actions(driver);
		action.moveToElement(appli).doubleClick().build().perform();

		String applicationName = request.containsKey("applicationName") ? (String) request.get("applicationName")
				: "App101";

		log.info(selectFromDropdown(applicationName));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",
				driver.findElement(selectFromDropdown(applicationName)));

		pipelineNameToBeCreated = request.get("name").toString().concat(CommonMethods.dateandTime());

		Thread.sleep(5000);
		driver.findElement(pipelineName).sendKeys(pipelineNameToBeCreated);
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));
	}

	public String getRepositoryPasswordType(String fieldName) {
		return driver.findElement(CommonMethods.inputFields(fieldName)).getAttribute("type");
	}

	public void addStageInPipeline(JSONObject stage) throws InterruptedException {
		//
		Thread.sleep(3000);
		driver.findElement(By.xpath("//mat-chip/child::span[text()=\" ADD STAGE \"]")).click();
		driver.findElement(stageName).sendKeys(stage.get("name").toString() + String.valueOf(count));
		log.info(stage.get("name").toString() + String.valueOf(count));

		driver.findElement(environment).click();
		((JavascriptExecutor) driver).executeScript("arguments[0].click()",
				driver.findElement(selectFromDropdown(stage.get("stage_env").toString())));
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();
		WebElement element = driver.findElement(Worker);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
		driver.findElement(selectFromDropdown(stage.get("worker").toString())).click();
		driver.findElement(clickOnElement("Confirm")).click();
	}

	public void addStageInPipelineDocker(JSONObject stage) throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.xpath("//mat-chip/child::span[text()=\" ADD STAGE \"]")).click();
		driver.findElement(stageName).sendKeys(stage.get("name").toString() + String.valueOf(count));
		log.info(stage.get("name").toString() + String.valueOf(count));
		driver.findElement(environment).click();
		((JavascriptExecutor) driver).executeScript("arguments[0].click()",
				driver.findElement(selectFromDropdown(stage.get("stage_env").toString())));
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();
		WebElement element = driver.findElement(Worker);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
		if (stage.get("name").toString() + String.valueOf(count) == "SCM2") {
			driver.findElement(selectFromDropdown(stage.get("worker2").toString())).click();
		} else {
			driver.findElement(selectFromDropdown(stage.get("worker1").toString())).click();
		}

		driver.findElement(clickOnElement("Confirm")).click();
	}

	public void addPlugin(JSONObject request, String[] plugins) throws InterruptedException {
		JSONArray stages = (JSONArray) request.get("stages");
		log.info("Before plugin");
		for (String plugin : plugins) {
			for (Object o : stages) {
				if (o instanceof JSONObject) {
					JSONObject stage = (JSONObject) o;

					addStageInPipeline(stage);

					JSONArray steps = (JSONArray) stage.get(plugin);
					for (Object obj : steps) {
						if (obj instanceof JSONObject) {
							JSONObject step = (JSONObject) obj;
							((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
									driver.findElement(By.id("add-step-chip-" + String.valueOf(count))));
							driver.findElement(By.id("add-step-chip-" + String.valueOf(count))).click();
							String pluginName = step.get("name").toString().toUpperCase();
							new Plugins(driver).addPlugin(pluginName, step);
							count++;
						}
						log.info("After plugin");
						if (driver.findElement(By.xpath(
								"//mat-dialog-container[contains(@id,\"mat-dialog-\")]/app-variables-directive-dialog/div[1]/div[1]/h2"))
								.isDisplayed()) {
							new WebDriverWait(driver, Duration.ofMinutes(2))
									.until(ExpectedConditions.elementToBeClickable(By.xpath(
											"//div/span/label/mat-label[text()=\"Search\"]/parent::label/parent::span/parent::div/input")));
							driver.findElement(By.xpath(
									"//div/span/label/mat-label[text()=\"Search\"]/parent::label/parent::span/parent::div/input"))
									.sendKeys("Git_token");
							driver.findElement(By.xpath("//table/tbody/tr/td[3]/button")).click();
						}
						Thread.sleep(4000);
						driver.findElement(savePluginButton).click();
						log.info("Plugin added");
					}
				}
			}
		}
	}

	public void addPluginDocker(JSONObject request, String[] plugins) throws InterruptedException {
		JSONArray stages = (JSONArray) request.get("stages");
		log.info("Before plugin");
		for (String plugin : plugins) {
			for (Object o : stages) {
				if (o instanceof JSONObject) {
					JSONObject stage = (JSONObject) o;

					addStageInPipelineDocker(stage);

					JSONArray steps = (JSONArray) stage.get(plugin);
					for (Object obj : steps) {
						if (obj instanceof JSONObject) {
							JSONObject step = (JSONObject) obj;
							((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
									driver.findElement(By.id("add-step-chip-" + String.valueOf(count))));
							driver.findElement(By.id("add-step-chip-" + String.valueOf(count))).click();
							String pluginName = step.get("name").toString().toUpperCase();
							new Plugins(driver).addPlugin(pluginName, step);
							count++;
						}
						log.info("After plugin");
						Thread.sleep(4000);
						driver.findElement(savePluginButton).click();
						log.info("Plugin added");
					}
				}
			}
		}
	}

	public void addPluginsWithPopup(JSONObject request, String[] plugins, String variableName)
			throws InterruptedException {
		JSONArray stages = (JSONArray) request.get("stages");
		for (String plugin : plugins) {
			for (Object o : stages) {
				if (o instanceof JSONObject) {
					JSONObject stage = (JSONObject) o;
					Thread.sleep(2000);
					addStageInPipeline(stage);
					JSONArray steps = (JSONArray) stage.get(plugin);
					for (Object obj : steps) {
						if (obj instanceof JSONObject) {

							JSONObject step = (JSONObject) obj;
							((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
									driver.findElement(By.id("add-step-chip-" + String.valueOf(count))));
							driver.findElement(By.id("add-step-chip-" + String.valueOf(count))).click();
							String pluginName = step.get("name").toString().toUpperCase();
							new Plugins(driver).addPlugin(pluginName, step);
							count++;

						}
						if (driver.findElement(By.xpath(
								"//mat-dialog-container[contains(@id,\"mat-dialog-\")]/app-variables-directive-dialog/div[1]/div[1]/h2"))
								.isDisplayed()) {
							new WebDriverWait(driver, Duration.ofMinutes(2))
									.until(ExpectedConditions.elementToBeClickable(By.xpath(
											"//div/span/label/mat-label[text()=\"Search\"]/parent::label/parent::span/parent::div/input")));
							Thread.sleep(3000);
							driver.findElement(By.xpath(
									"//div/span/label/mat-label[text()=\"Search\"]/parent::label/parent::span/parent::div/input"))
									.sendKeys(variableName);
							Thread.sleep(3000);
							driver.findElement(By.xpath("//table/tbody/tr/td[3]/button")).click();
							Thread.sleep(3000);
						}
						driver.findElement(
								By.xpath("//*[@id=\"mat-dialog-1\"]/app-plugin-input/div[3]/div[1]/button[1]")).click();
					}
				}
			}
		}
	}

	public JSONObject addStage(JSONObject request) throws InterruptedException {
		JSONArray stages = (JSONArray) request.get("stages");
		JSONObject stage;
		for (Object o : stages) {
			if (o instanceof JSONObject) {
				stage = (JSONObject) o;
				driver.findElement(addStage).click();
				driver.findElement(stageName).sendKeys(stage.get("name").toString());
				log.info(stage.get("name").toString());
				driver.findElement(environment).click();
				((JavascriptExecutor) driver).executeScript("arguments[0].click()",
						driver.findElement(selectFromDropdown(stage.get("env").toString())));
				Actions action = new Actions(driver);
				action.sendKeys(Keys.ESCAPE).build().perform();
				WebElement element = driver.findElement(Worker);
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
				element.click();
				driver.findElement(selectFromDropdown(stage.get("worker").toString())).click();

				driver.findElement(clickOnElement("Confirm")).click();
				return stage;

			}
		}
		return null;
	}

	// gitscm_maven","maven package
	public void addPlugin(JSONObject request, String plugin) throws InterruptedException {

		log.info("Plugin details " + plugin);

		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));

		JSONArray steps = (JSONArray) request.get(plugin);
		Thread.sleep(2000);
		for (Object obj : steps) {
			if (obj instanceof JSONObject) {
				JSONObject step = (JSONObject) obj;
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
						driver.findElement(addStepButton));
				driver.findElement(addStepButton).click();
				wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				String pluginName = step.get("name").toString();
				new Plugins(driver).addPlugin(pluginName, step);
				count++;
			}
			Thread.sleep(5000);
			driver.findElement(By.xpath("//span[normalize-space()='Save']/parent::button")).click();
			log.info("plugin added successfully");

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		}
	}

	public void viewPluginFields(JSONObject request, String plugin) throws InterruptedException {
		JSONArray stages = (JSONArray) request.get("stages");
		JSONObject stage = (JSONObject) stages.get(0);
		addStageInPipeline(stage);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(addStepButton));
		driver.findElement(addStepButton).click();
		wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		wait.until(ExpectedConditions.visibilityOfElementLocated(choosePluginDialog));
		driver.findElement(searchPluginTextBox).sendKeys(plugin);
		driver.findElement(selectPlugin(plugin)).click();
		expandPluginDetails(plugin);
	}

	public void savePluginDetails() {
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));
		driver.findElement(submitPipelineButton_PipelineDetailsEditor("save")).click();
		log.info("Plugin details saved");
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));
	}

	public void navigateToHomeScreenFromPipelineDetailsScreen() {
		driver.findElement(GoBack).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(listViewIcon));
		log.info("Navigated back to pipeline view home page successfully");
	}

	public boolean isVisualEditor() {
		if (driver.findElements(visualEditor).size() > 0) {
			log.info("Visual editor is displayed");
			return true;
		} else
			return false;
	}

	public boolean isPipelineEditor() {
		if (driver.findElements(pipelineEditor).size() > 0) {
			log.info("Pipeline editor is displayed");
			return true;
		} else
			return false;
	}

	public void switchEditor() {
		driver.findElement(switchEditor).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(confirmSwitchEditor));
		driver.findElement(confirmSwitchEditor).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(confirmSwitchEditor));
		log.info("Editor is switched successfully");
	}

	public void expandParticulrPluginDetails(String stage, String pluginName) {
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));
		log.info("Entered in expandPluginDetails method");
		driver.findElement(clickOnParticExpandPlugin(stage, pluginName)).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));
	}

	public void expandPluginDetails(String pluginName) {
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));
		log.info("Entered in expandPluginDetails method");
		driver.findElement(clickOnExpandPlugin(pluginName)).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));
	}

	public void editPlugin(String pluginName, String details) throws InterruptedException {
		executionHistoryEditButton();
		expandPluginDetails(pluginName);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@class='ng-star-inserted']//following::input[3]"))).clear();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@class='ng-star-inserted']//following::input[3]")))
				.sendKeys(details);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=' Save ']"))).click();
	}

	public void expandPluginDetails1(String pluginName) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));
		log.info("Entered in expandPluginDetails method");
		Thread.sleep(3000);
		driver.findElement(clickOnExpandPlugin1(pluginName)).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));
	}

	public boolean getStepName(String name) {
		log.info("getting Step name");
		if (driver.findElement(stepName_existingplugin(name)).getAttribute("innerText").contains(name))
			return true;
		return false;
	}

	public void executionHistoryEditButton() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(By.xpath("//*[@mattooltipclass='option-tooltip']//following::button[6]//mat-icon[1]"))))
				.click();
	}

	public void addclonedPipelineName() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));
		driver.navigate().refresh();
		Thread.sleep(8000);
		driver.switchTo().activeElement().sendKeys(CommonMethods.dateandTime());
		Thread.sleep(3000);
		WebElement element = driver.findElement(By.xpath("//span[text()='Submit']"));
		JavascriptExecutor execu = (JavascriptExecutor) driver;
		execu.executeScript("arguments[0].click();", element);
	}

	public void submitPipeline() throws InterruptedException {
		Thread.sleep(1000);

		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));
		JavascriptExecutor je = (JavascriptExecutor) driver;
		Thread.sleep(4000);
		WebElement element = driver.findElement(By.xpath("//span[normalize-space()='save']/parent::button"));
		je.executeScript("arguments[0].scrollIntoView(true);", element);

		Thread.sleep(4000);
		if (driver.findElement(submitPipelineButton_PipelineDetailsEditor("save")).isEnabled()) {

			Thread.sleep(4000);
			driver.findElement(submitPipelineButton_PipelineDetailsEditor("save")).click();
			Thread.sleep(3000);
			driver.findElement(submitPipelineButton_PipelineDetailsEditor("Yes")).click();
			log.info("Pipeline details submitted successfully");
		} else {
			new WebDriverWait(driver, Duration.ofMinutes(2)).until(ExpectedConditions
					.elementToBeClickable(driver.findElement(submitPipelineButton_PipelineDetailsEditor("save"))))
					.click();
			Thread.sleep(3000);
			driver.findElement(submitPipelineButton_PipelineDetailsEditor("Yes")).click();
			log.info("Pipeline details submitted successfully");
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));
	}

	public static String getPipelineNameCreated() {
		return pipelineNameCreated;
	}

	public void setPipelineNameCreated() {
		PipelineView.pipelineNameCreated = pipelineNameToBeCreated;
	}

	public String verifyPluginIsAdded(String plugin) {
		return driver.findElement(getPluginName(plugin)).getAttribute("innerText");
	}

	public boolean verifyPipelineIsClonned() {
		new CommonMethods(driver).filterPipeline(PipelineView.getPipelineNameCreated());
		return !driver.findElements(pipelineListTableRows).isEmpty();

	}

	public void navigateToPipeline() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(Pipeline));
		driver.findElement(Pipeline).click();
		log.info("Navigated to pipeline view home page successfully");
	}

}
