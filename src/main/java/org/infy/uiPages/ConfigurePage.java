package org.infy.uiPages;

import java.time.Duration;
import java.util.List;

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

public class ConfigurePage {

	private WebDriver driver;
	private WebDriverWait wait;
	public static String policyNameToBeCreated;
	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(ConfigurePage.class);

	public ConfigurePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofMinutes(2));
	}

	/* Click on Application/Portfolio Configure Fields */
	By configureElements(String fieldName) {
		return By.xpath("//*[contains(text(),'" + fieldName + "')]");
	}

	By selectEnvironment(String envname) {
		return By.xpath("//span[contains(text(),'" + envname + "')]/preceding-sibling::mat-pseudo-checkbox");
	}

	By expandPolicy(String policyName) {
		return By.xpath("//div[text()='" + " " + policyName + " " + "']/ancestor::mat-expansion-panel-header");
	}

	By submitButton(String text) {
		return By.xpath("//span[contains(text(),'" + text + "')]/parent::button");
	}

	By fieldName(String field) {
		return By.xpath("//input[@placeholder='" + field + "']");
	}

	By configInputFields(String fieldName) {
		return By.xpath("//mat-label[text()='" + fieldName + "']/parent::label/parent::span/preceding-sibling::input");
	}

	By selectApplicationFromDropdown(String appName) {
		return By.xpath(String.format(
				"//div[contains(@class,'mat-select-panel')]//mat-option/span[normalize-space()='" + appName + "']",
				appName));
	}

	By Environments = By
			.xpath("//mat-label[text()='Environments']/parent::label/parent::span/preceding-sibling::mat-select");
	By applicationViewLeftNav = By.xpath("//mat-list-item//span[text()=' Application ']");
	By applicationViewSideNav = By.xpath("//mat-sidenav-content//span[normalize-space(text())='Application list']");
	By createNewApplicationButon = By.xpath("//button[@mattooltip='Create an Application']");
	By applicationName = By.xpath("//input[@placeholder='Application Name']");
	By owners = By.xpath("//input[@placeholder='Owners']");

	By date(String text) {
		return By.xpath("//input[@placeholder='" + text + "']");
	}

	By selectDate(String date) {
		return By.xpath("//mat-month-view/table/tbody/descendant::td/child::div[text()=' " + date + " ']");
	}

	By addButtonInAddPolicy = By.xpath("//app-add-new-policy-dialog/mat-dialog-actions/button[1]");
	By portfolioName = By.xpath("//mat-select[@placeholder='Portfolio Name']");
	By filterPipeline = By.xpath("//mat-label[text()='Search']/parent::label/parent::span/preceding-sibling::input");
	By applicationListTableRows = By
			.xpath("//span[text()='Application list']/parent::mat-toolbar/following-sibling::table/tbody/tr");
	By saveApplication = By.xpath("//mat-dialog-actions/button[2]");
	By editApplication = By.xpath("//table/tbody/tr[1]/td[3]/button/span/mat-icon");
	By add = By.xpath("//mat-icon[contains(text(),'add')]/ancestor::button");
	By categoryName = By
			.xpath("//mat-label[text()='Category']/parent::label/parent::span/preceding-sibling::mat-select");
	By selectPolicy = By
			.xpath("//app-add-new-policy-dialog/div/div[1]/mat-form-field/div/div[1]/div/mat-select/div/div[1]");
	By saveEnvApp = By
			.xpath("//*[@id=\"mat-dialog-0\"]/app-environment-dialog/mat-dialog/mat-dialog-actions/button[1]");
	By saveEnv = By.xpath("//mat-dialog/mat-dialog-actions/button[2]");
	By saveEnv_config = By.xpath("//mat-dialog/mat-dialog-actions/button[1]");
	By back = By.xpath("//mat-icon[contains(text(),'arrow_back')]");
	By resourceType = By.xpath(
			"//mat-expansion-panel[contains(@class,'mat-expanded mat-expansion-panel-spacing')]/descendant::span[text()='Resource Type']/ancestor::mat-select");
	By resourcePermission = By.xpath(
			"//mat-expansion-panel[contains(@class,'mat-expanded mat-expansion-panel-spacing')]/descendant::span[text()='Permission']/ancestor::mat-select");
	By resourcePattern = By.xpath(
			"//mat-expansion-panel[contains(@class,'mat-expanded mat-expansion-panel-spacing')]/descendant::mat-label[text()='Resource Pattern']/parent::label/parent::span/preceding-sibling::input");
	By addPermission = By.xpath(
			"//mat-expansion-panel[contains(@class,'mat-expanded mat-expansion-panel-spacing')]/descendant::button/span[contains(text(),' Add Permission')]/parent::button");
	By usersName = By.xpath(
			"//mat-expansion-panel[contains(@class,'mat-expanded mat-expansion-panel-spacing')]/descendant::mat-label[text()='Add Users']/parent::label/parent::span/preceding-sibling::input");
	By addUsertoList = By.xpath(
			"//mat-expansion-panel[contains(@class,'mat-expanded mat-expansion-panel-spacing')]/descendant::mat-icon[text()='keyboard_arrow_right']/parent::span/parent::button");

	By savePolicies(int policiesSize) {
		return By.xpath("//mat-accordion/mat-expansion-panel[" + policiesSize
				+ "]/div/div/div/app-writepolicy/div/div/div[4]/button[1]");
	}

	By deletePolicy = By.xpath(
			"//mat-expansion-panel[contains(@class,'mat-expanded mat-expansion-panel-spacing')]/descendant::span[contains(text(),'Delete')]/parent::button");

	By tablerows(String conffield) {
		return By.xpath("//mat-sidenav-content//span[normalize-space(text()='" + conffield
				+ "')]/parent::mat-toolbar/following-sibling::table/tbody/tr");
	}

	By editConfigFieldsEntries(String text) {
		return By.xpath("//mat-icon[@mattooltip='" + text + "']/parent::span/parent::button");
	}

	By editEnv(String envName) {
		return By.xpath("//span[text()='" + envName + "']/parent::mat-chip");
	}

	By deleteEnv = By.xpath(
			"//mat-sidenav-content//span[normalize-space(text()='List of Environments')]/parent::mat-toolbar/following-sibling::table/tbody/tr/descendant::mat-icon[contains(@class,'remove')]");
	By acceptPopup = By.xpath("//span[text()=' Yes ']/parent::button");
	By deleteVariable = By.xpath(
			"//mat-sidenav-content//span[normalize-space(text()='List of Variables')]/parent::mat-toolbar/following-sibling::table/tbody/tr/descendant::mat-icon[text()='delete']");
	By deleteAgent = By.xpath(
			"//mat-sidenav-content//span[normalize-space(text()='Workers list')]/parent::mat-toolbar/following-sibling::table/tbody/tr[1]/descendant::mat-icon[contains(@class,'delete')]");
	By archiveRelease = By.xpath(
			"//mat-sidenav-content//span[normalize-space(text()='List of Releases')]/parent::mat-toolbar/following-sibling::table/tbody/tr[1]/descendant::mat-icon[@mattooltip='Archive Release']");

	/* Configuration of Environment tab */
	public void configureEnvironment(String configField, JSONObject request) throws InterruptedException {
		addConfiguration(configField);
		JSONArray EnvCategories = (JSONArray) request.get("EnvironmentsCategories");
		log.info("env");
		for (int i = 0; i < EnvCategories.size(); i++) {
			JSONObject category = (JSONObject) EnvCategories.get(i);
			log.info(EnvCategories.size());
			log.info("Reaching here 1");
			wait.until(ExpectedConditions.visibilityOfElementLocated(add)).click();
			log.info("Reaching here 2");
			wait.until(ExpectedConditions.visibilityOfElementLocated(fieldName("Environment name")))
					.sendKeys(category.get("EnvName").toString());
			log.info("Reaching here 3");
			wait.until(ExpectedConditions.elementToBeClickable(categoryName)).click();
			log.info("Reaching here 4");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",
					driver.findElement(selectApplicationFromDropdown(category.get("Env").toString())));

			log.info("Reaching here 5");

			Thread.sleep(3000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(saveEnv_config)).click();
			Thread.sleep(3000);
			log.info("Reaching here 6");
			log.info("Created environment " + category.get("EnvName").toString() + " successfully");
			log.info("This also done");
		}
		log.info("Environment is configured successfully");
	}

	/* Configuration of Release tab */
	public void configureRelease(String configField, JSONObject request) throws InterruptedException {
		Thread.sleep(1000);
		addConfiguration(configField);
		driver.findElement(add).click();
		driver.findElement(configInputFields("Release Name")).sendKeys(request.get("Release Name").toString());
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", driver.findElement(Environments));
		Thread.sleep(1000);
		driver.findElement(selectEnvironment(request.get("Environment").toString())).click();
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();
		driver.findElement(date("Start Date")).click();
		driver.findElement(selectDate(request.get("startDate").toString())).click();
		Thread.sleep(1000);
		driver.findElement(date("End Date")).click();
		log.info("End Date");
		driver.findElement(selectDate(request.get("endDate").toString())).click();
		Thread.sleep(1000);
		driver.findElement(submitButton("Ok")).click();
		log.info("Release is created successfully");
	}

	/* Configuration of Agents tab */
	public void configureAgents(String configField, JSONObject request) throws InterruptedException {
		Thread.sleep(1000);
		addConfiguration(configField);
		driver.findElement(add).click();
		driver.findElement(configInputFields("Worker Name")).sendKeys(request.get("Agent Name").toString());
		driver.findElement(submitButton("Save")).click();
	}

	/* Configuration of Variables tab */
	public void configureVariables(String configField, JSONObject request) throws InterruptedException {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath("//*[contains(text(),'Variables')]"));
		je.executeScript("arguments[0].scrollIntoView(true);", element);
		driver.findElement(By.xpath("//*[contains(text(),'Variables')]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(add)).click();
		log.info("Variable added successfully");
		wait.until(ExpectedConditions.visibilityOfElementLocated(configInputFields("Variable name")))
				.sendKeys(request.get("Variable Name").toString());
		log.info("Variable name added");
		wait.until(ExpectedConditions.visibilityOfElementLocated(configInputFields("Default value")))
				.sendKeys(request.get("Default Value1").toString());
		log.info("Variable value added");
		wait.until(ExpectedConditions.visibilityOfElementLocated(submitButton("Save"))).click();
		log.info("Variable saved");
	}

	/* Add Policies to Policy Tab */
	public JSONObject addPolicies(String configField, JSONObject request) throws InterruptedException {

		addConfiguration(configField);
		Thread.sleep(2000);
		driver.findElement(add).click();
		JSONObject policyDetails = (JSONObject) request.get("PolicyDetails");
		Thread.sleep(3000);
		driver.findElement(selectPolicy).click();
		((JavascriptExecutor) driver).executeScript("arguments[0].click()",
				driver.findElement(selectApplicationFromDropdown(policyDetails.get("selectPolicy").toString())));
		policyNameToBeCreated = policyDetails.get("policyName").toString() + CommonMethods.getRandomNumber();
		driver.findElement(configInputFields("Policy Name")).sendKeys(policyNameToBeCreated);
		Thread.sleep(1000);
		new WebDriverWait(driver, Duration.ofMinutes(2))
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(addButtonInAddPolicy))).click();
		log.info("policy details entered");
		return policyDetails;
	}

	/* Enter Details to Add Permission inside Policy */
	public JSONObject enterPolicyDetails(JSONObject policyDetails) throws InterruptedException {
		Thread.sleep(1000);
		JSONArray AssignPermissions = (JSONArray) policyDetails.get("AssignPermissions");
		for (Object o : AssignPermissions) {
			if (o instanceof JSONObject) {
				JSONObject resource = (JSONObject) o;
				JSONArray resourceDetails = (JSONArray) resource.get("Resources");
				Thread.sleep(1000);
				for (Object obj : resourceDetails) {

					if (obj instanceof JSONObject) {
						JSONObject object = (JSONObject) obj;
						WebElement element = driver.findElement(resourceType);
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
						element.click();
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", driver
								.findElement(selectApplicationFromDropdown(object.get("ResourceType").toString())));

						log.info("Resource type is selected");
						driver.findElement(resourcePermission).click();
						((JavascriptExecutor) driver).executeScript("arguments[0].click()",
								driver.findElement(selectApplicationFromDropdown(object.get("Permission").toString())));
						log.info("Resource Permission is selected");

						Actions action1 = new Actions(driver);
						action1.sendKeys(Keys.ESCAPE).build().perform();
						driver.findElement(resourcePattern).sendKeys(object.get("ResourcePattern").toString());
						action1.sendKeys(Keys.ENTER).build().perform();
						log.info("Add Permission need to be clicked....");
					}

					driver.findElement(addPermission).click();
					log.info("Add Permission is clicked....");
				}
			}
		}
		log.info("Permission is added");
		return policyDetails;
	}

	/* Add users to Policies */
	public void addUserPolicy(JSONObject policyDetails) throws InterruptedException {
		JSONArray users = (JSONArray) policyDetails.get("Users");
		for (int i = 0; i < users.size(); i++) {
			log.info("The size of add user policies is " + users.size());
			JSONObject username = (JSONObject) users.get(i);
			Thread.sleep(4000);

			WebElement element = driver.findElement(usersName);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			element.sendKeys(username.get("ownerid").toString());
			driver.findElement(addUsertoList).click();

			log.info("User name is added");
		}

	}

	/* save permission and users for the policies */
	public void savePolicies() throws InterruptedException {
		List<WebElement> policies = driver.findElements(By.xpath("//mat-accordion/mat-expansion-panel"));
		WebElement element = driver.findElement(savePolicies(policies.size()));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "");
		log.info("Policy details are saved successfully");

	}

	/* Expand and collapse policies tab */
	public void clickOnExpandPolcy() throws InterruptedException {

		WebElement element = driver.findElement(expandPolicy(policyNameToBeCreated));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(3000);
		element.click();
	}

	/* Configuration of policies */
	public void configurePolicies(String string, JSONObject request) throws InterruptedException {
		JSONObject policyDetails = addPolicies(string, request);
		clickOnExpandPolcy();
		JSONObject userDetails = enterPolicyDetails(policyDetails);
		log.info("Before ");
		addUserPolicy(userDetails);
		savePolicies();
		Thread.sleep(3000);

//		clickOnExpandPolcy();	
	}

	/* edit policies */
	public void editPoliciesDetails(String configField, JSONObject request) throws InterruptedException {
		addConfiguration(configField);
		log.info("Policies tab  is opened");
		JSONObject editDetails = (JSONObject) request.get("Edit_Policy");
		// JSONObject policyDetails =(JSONObject) editDetails.get("PolicyDetails");
		log.info("Before Expand");
		clickOnExpandPolcy();
		enterPolicyDetails(editDetails);
		log.info("Policies details entered");
		savePolicies();
		log.info("Policies details saved");
		clickOnExpandPolcy();
	}

	/* configure policies in create Application */
	public void configurepoliciesapplication(String configField, JSONObject request) throws InterruptedException {
		addConfiguration(configField);
		log.info("Policies tab  is opened");
		JSONObject editDetails = (JSONObject) request.get("Edit_Policy");
		// JSONObject policyDetails =(JSONObject) editDetails.get("PolicyDetails");
		log.info("Before Expand");
		clickOnExpandPolcy();
		enterPolicyDetails(editDetails);
		log.info("Policies details entered");
		savePolicies();
		// log.info("Policies details saved");
		// clickOnExpandPolcy();
	}

	/* click on particular configuration tab */
	public void addConfiguration(String fieldName) throws InterruptedException {
		log.info("Reached here");
		log.info(fieldName);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(configureElements(fieldName))).click();
		// driver.findElement(By.linkText(fieldName));
		log.info("Able to find this element " + fieldName);
	}

	/* Validating Tool Tip Title of Add Button */
	public String validateAddButtonToolTip(String fieldName) throws InterruptedException {
		driver.findElement(configureElements(fieldName)).click();
		log.info("getting Tooltip tiltle of " + fieldName);
		Thread.sleep(1000);
		return driver.findElement(add).getAttribute("mattooltip");
	}

	/* close dialog container */
	public void clickOnCancel() {
		driver.findElement(configureElements("Cancel")).click();
	}

	/* verify Environment Configuration mandatory fields */
	public String verifyEnvironmentConfigMandatoryFields(String configField) throws InterruptedException {
		log.info("validating Mandatory Fields of Environement");
		addConfiguration(configField);
		driver.findElement(add).click();
		Thread.sleep(1000);
		if (!driver.findElement(saveEnvApp).isEnabled()) {
			driver.findElement(fieldName("Environment name")).sendKeys("Dev");
			Thread.sleep(1000);
			if (!driver.findElement(saveEnvApp).isEnabled()) {
				return "Mandatory";
			} else {
				return "Category is a not Mandatory field";
			}

		} else {
			return "Environment Name is not a mandatory field";
		}
	}

	/* verify Release Configuration mandatory fields */
	public String verifyReleaseMandatoryFields(String configField) throws InterruptedException {
		log.info("validating Mandatory Fields of Release");
		addConfiguration(configField);
		driver.findElement(add).click();
		if (!driver.findElement(submitButton("Ok")).isEnabled()) {
			driver.findElement(configInputFields("Release Name")).sendKeys("Rel_0.0.1");
			if (!driver.findElement(submitButton("Ok")).isEnabled()) {
				return "Mandatory";
			} else {
				return "Environment is not a Mandatory Field";
			}
		} else {
			return "Release is not a Mandatory Field";
		}
	}

	/* verify Agents Configuration mandatory fields */
	public String verifyAgentsMandatoryFields(String configField) throws InterruptedException {
		log.info("validating Mandatory Fields of Agent");
		addConfiguration(configField);
		driver.findElement(add).click();
		if (!driver.findElement(submitButton("Save")).isEnabled())
			return "Mandatory";
		return "Agent name is not madatory field";
	}

	/* verify Policies Configuration mandatory fields */
	public String verifyPolicyMandatoryFields(String configField) throws InterruptedException {
		log.info("validating Mandatory Fields of Policies");
		addConfiguration(configField);
		driver.findElement(add).click();
		if (!driver.findElement(submitButton("Add")).isEnabled()) {
			driver.findElement(selectPolicy).click();
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",
					driver.findElement(selectApplicationFromDropdown("Other")));
			if (!driver.findElement(submitButton("Add")).isEnabled()) {
				log.info("Inside add");
				clickOnCancel();
				driver.findElement(expandPolicy(policyNameToBeCreated)).click();
				log.info("policy name created");
				if (!driver.findElement(addPermission).isEnabled()) {
					WebElement element = driver.findElement(resourceType);
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
					Thread.sleep(2000);
					element.click();
					((JavascriptExecutor) driver).executeScript("arguments[0].click()",
							driver.findElement(selectApplicationFromDropdown("Application")));
					if (!driver.findElement(addPermission).isEnabled()) {
						driver.findElement(resourcePermission).click();
						((JavascriptExecutor) driver).executeScript("arguments[0].click()",
								driver.findElement(selectApplicationFromDropdown("EDIT")));
						Thread.sleep(3000);//
						driver.findElement(By.xpath("//span[normalize-space()='OK']/parent::button")).click();

						if (!driver.findElement(addPermission).isEnabled()) {
							log.info("Inside if");
							Thread.sleep(4000);
							JavascriptExecutor je = (JavascriptExecutor) driver;
							WebElement policy = driver.findElement(expandPolicy(policyNameToBeCreated));
							je.executeScript("arguments[0].scrollIntoView(true);", policy);
							driver.findElement(expandPolicy(policyNameToBeCreated)).click();
							return "Mandatory";
						} else
							return "Resource Pattern is not mandatory field";
					} else
						return "Permission Type is not mandatory field";
				} else
					return "Resource Type is not mandatory field";

			} else
				return "Policy Name is not mandatory field";
		} else
			return "Select Policy is not mandatory field";
	}

	public String editVariables(String configField, JSONObject editDetails) throws InterruptedException {
		addConfiguration(configField);
		log.info("Variables tab is opened");
		if (driver.findElements(tablerows("List of Variables")).size() > 1) {
			log.info("Inside Variable");
			driver.findElement(By.xpath("//span[normalize-space()='edit']/parent::button")).click();

			WebElement element = driver.findElement(configInputFields("Variable name"));
			Thread.sleep(3000);
			element = driver.findElement(configInputFields("Default value"));
			element.clear();
			element.sendKeys(editDetails.get("Default Value").toString());
			driver.findElement(submitButton("Save")).click();
			return "Variables is successfully edited";
		} else {
			return "Variable list is empty";
		}
	}

	public String editReleaseDetails(String configField, JSONObject editDetails) throws InterruptedException {
		addConfiguration(configField);

		if (!driver.findElements(tablerows("List of Releases")).isEmpty()) {
			log.info("Inside Edit");
			driver.findElement(editConfigFieldsEntries("Edit Release")).click();
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", driver.findElement(Environments));
			Thread.sleep(1000);
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).build().perform();

			log.info("2");
			Thread.sleep(1000);
			driver.findElement(submitButton("Ok")).click();
			log.info("Release is created successfully");
			return "Release is successfully edited";
		} else
			return "Releases list is empty";

	}

	public String editEnvironment(String configField, JSONObject editDetails) throws InterruptedException {
		addConfiguration(configField);
		if (!driver.findElements(tablerows("List of Environments")).isEmpty()) {
			driver.findElement(editEnv(editDetails.get("editEnv").toString())).click();
			driver.findElement(categoryName).click();
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",
					driver.findElement(selectApplicationFromDropdown(editDetails.get("environmentValue").toString())));
			Thread.sleep(3000);

			((JavascriptExecutor) driver).executeScript("arguments[0].click()",

					driver.findElement(saveEnv_config));
			Thread.sleep(3000);
			return "Environment is successfull Edited";
		} else {
			return "Environment list is empty";
		}
	}

	public void deleteEnvironment(String configField) throws InterruptedException {
		addConfiguration(configField);
		if (!driver.findElements(tablerows("List of Environments")).isEmpty()) {
			driver.findElement(deleteEnv).click();
			log.info("Deleted env");
			driver.findElement(acceptPopup).click();
		}
	}

	public void deleteVariable(String configField) throws InterruptedException {
		addConfiguration(configField);
		if (driver.findElements(tablerows("List of Variables")).size() > 1) {
			driver.findElement(deleteVariable).click();
			driver.findElement(acceptPopup).click();
		}

	}

	public void deletePolicy(String configField) throws InterruptedException {
		addConfiguration(configField);
		Thread.sleep(1000);
		clickOnExpandPolcy();
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(deletePolicy));
		driver.findElement(deletePolicy).click();
		driver.findElement(acceptPopup).click();
	}

	public void deleteAgents(String configField) throws InterruptedException {
		addConfiguration(configField);
		if (driver.findElements(tablerows("Workers list")).size() > 1) {
			driver.findElement(deleteAgent).click();
		}
	}

	public void archiveRelease(String configField) throws InterruptedException {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath("//*[contains(text(),'Release')]"));
		je.executeScript("arguments[0].scrollIntoView(true);", element);
		driver.findElement(By.xpath("//*[contains(text(),'Release')]")).click();
		if (!driver.findElements(tablerows("List of Releases")).isEmpty()) {
			log.info("List not Empty");
			driver.findElement(archiveRelease).click();
			driver.findElement(date("Actual Start Date")).click();
			driver.findElement(selectDate("1")).click();
			Thread.sleep(1000);
			driver.findElement(date("Actual End Date")).click();
			driver.findElement(selectDate("1")).click();
			driver.findElement(submitButton("Ok")).click();
		}
	}

}
