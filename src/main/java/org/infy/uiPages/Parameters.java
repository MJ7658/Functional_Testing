package org.infy.uiPages;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.infy.init.BrowserFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Parameters extends BrowserFactory {

	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(Parameters.class);

	By parametersTab = By.xpath("//span[text()=' Parameters ']");
	By addParameter = By.xpath("//button[@mattooltip='Add New Parameter']");
	By enterParameterNameTextbox = By.xpath("//input[@data-placeholder = 'Enter Parameter Name']");
	By enterParameterValueTextbox = By.xpath("//input[@data-placeholder = 'Enter Value']");

	By getTriggerableToggleButton(int counter) {
		return By.xpath("//*[@id=\"mat-slide-toggle-" + counter * 2 + "\"]/label/div");
	}

	By getSaveButton(int counter) {
		return By.xpath(
				"//*[contains(@id, 'mat-tab-content')]/div/app-parameters/div/mat-card/mat-card-content/table/tbody/tr["
						+ counter + "]/td[4]/button[1]");
	}

	public void clickParametersTab() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(parametersTab));
		new WebDriverWait(driver, Duration.ofMinutes(2)).until(ExpectedConditions.elementToBeClickable(parametersTab))
				.click();
		log.info("Parameters tab is clicked.");
	}

	public void clickAddParameter() {
		new WebDriverWait(driver, Duration.ofMinutes(2)).until(ExpectedConditions.elementToBeClickable(addParameter))
				.click();
		log.info("Add Parameter button is clicked.");
	}

	public void addParameters(String jsonDataFilePath)
			throws FileNotFoundException, IOException, ParseException, InterruptedException {
		JSONParser jsonParser = new JSONParser();
		JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(jsonDataFilePath));
		int counter = 0;

		for (Object object : jsonArray) {

			counter++;

			clickAddParameter();

			JSONObject parameter = (JSONObject) object;

			String parameterName = (String) parameter.get("parameterName");

			new WebDriverWait(driver, Duration.ofMinutes(2))
					.until(ExpectedConditions.elementToBeClickable(enterParameterNameTextbox)).sendKeys(parameterName);

			String parameterValue = (String) parameter.get("parameterValue");

			new WebDriverWait(driver, Duration.ofMinutes(2))
					.until(ExpectedConditions.elementToBeClickable(enterParameterValueTextbox))
					.sendKeys(parameterValue);

			new WebDriverWait(driver, Duration.ofMinutes(2))
					.until(ExpectedConditions.elementToBeClickable(getTriggerableToggleButton(counter))).click();

			new WebDriverWait(driver, Duration.ofMinutes(2))
					.until(ExpectedConditions.elementToBeClickable(getSaveButton(counter))).click();

		}

	}

}
