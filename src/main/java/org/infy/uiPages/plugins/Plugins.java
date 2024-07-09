package org.infy.uiPages.plugins;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.infy.uiPages.PipelineView;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Plugins {
	private WebDriver driver;
	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(Plugins.class);
	public static String pipelineNameToBeCreated;

	public Plugins(WebDriver driver) {
		this.driver = driver;
	}

	public void addPlugin(String pluginName, JSONObject step) throws InterruptedException {
		PipelineView pv = new PipelineView(driver);
		switch (pluginName) {
		case "BLAZEMETER":
			String[] BlazemeterInputParameter = { "blazemeterUrl:input", "blazemeterApiKey:input",
					"blazemeterApiSecret:input", "blazemeterTestType:input", "blazemeterTestId:input",
					"blazemeterProxyIp:input", "blazemeterProxyPort:input", "blazemeterProxyUsername:input",
					"blazemeterProxyPassword:input" };
			log.info("Plugin name: Blazemeter");
			driver.findElement(pv.searchPluginTextBox).sendKeys("Blazemeter");
			driver.findElement(pv.selectPlugin("Blazemeter")).click();
			log.info("Blazemeter plugin selected");
			pv.expandPluginDetails("Blazemeter");
			log.info("Plugin is expaneded");
			new InputPluginDetails(driver).enterStepDetails(step, BlazemeterInputParameter);
			log.info("Step details entered");
			break;

		case "DOCKER_DEPLOY":
			String[] dockerDeployInputParameter = { "dockerFilePath:input", "dockerPassword:input",
					"dockerRegistryUrl:input", "dockerRepo:input", "dockerUsername:input", "stackName:input",
					"tagName:input" };
			log.info("Plugin name: Docker_deploy");
			driver.findElement(pv.searchPluginTextBox).sendKeys("docker_deploy");
			driver.findElement(pv.selectPlugin("docker_deploy")).click();
			log.info("docker_deploy plugin selected");
			pv.expandPluginDetails("docker_deploy");
			log.info("Plugin is expaneded");
			new InputPluginDetails(driver).enterStepDetails(step, dockerDeployInputParameter);
			log.info("Step details entered");
			break;

		case "MAVEN_PACKAGE":
			String[] mavenInputParameter = { "pomFile:input" };
			log.info("Plugin name: Maven Package");
			driver.findElement(pv.searchPluginTextBox).sendKeys("Maven Package");
			driver.findElement(pv.selectPlugin("Maven Package")).click();
			log.info("Maven_package plugin selected");
			pv.expandPluginDetails("Maven-Package");
			log.info("Plugin is expaneded");
			driver.findElement(By.xpath("//input[@name='pluginDisplayNameInput']")).clear();
			driver.findElement(By.xpath("//input[@name='pluginDisplayNameInput']")).sendKeys("MAVEN_PACKAGE");
			new InputPluginDetails(driver).enterStepDetails(step, mavenInputParameter);
			log.info("Step details entered");
			break;

		case "TOMCAT_MAVEN":
			String[] tomcatmavenInputParameter = { "pomFile:input" };
			log.info("Plugin name: Maven Package");
			driver.findElement(pv.searchPluginTextBox).sendKeys("maven package");
			driver.findElement(pv.selectPlugin("Maven Package")).click();
			log.info("Maven_package plugin selected");
			pv.expandPluginDetails("Maven-Package");
			log.info("Plugin is expaneded");

			new InputPluginDetails(driver).enterStepDetails(step, tomcatmavenInputParameter);
			log.info("Step details entered");
			break;

		case "IFAST":
			String[] ifastInputParameter = { "iFastProjectName:input", "iFastURL:input", "iFastUsername:input",
					"iFastPassword_0:input" };
			log.info("Plugin name: IFast");
			driver.findElement(pv.searchPluginTextBox).sendKeys("iFast");
			driver.findElement(pv.selectPlugin("iFast")).click();
			log.info("IFast plugin selected");
			pv.expandPluginDetails("iFast");
			log.info("Plugin is expaneded");
			driver.findElement(By.xpath("//input[@name='pluginDisplayNameInput']")).clear();
			driver.findElement(By.xpath("//input[@name='pluginDisplayNameInput']")).sendKeys("IFAST");
			new InputPluginDetails(driver).enterStepDetails(step, ifastInputParameter);
			log.info("Step details entered");
			break;

		case "GITSCM":
			String[] gitscmInputParameter = { "Git Provider:dropdown", "gitUrl:input", "username:input",
					"password:input", "branchPattern:input" };
			log.info("Plugin name: GitSCM");
			driver.findElement(pv.searchPluginTextBox).sendKeys("gitscm");
			driver.findElement(pv.selectPlugin("gitscm")).click();
			log.info("gitscm plugin selected");
			pv.expandPluginDetails("gitscm");
			driver.findElement(By.xpath("//input[@name='pluginDisplayNameInput']")).clear();
			driver.findElement(By.xpath("//input[@name='pluginDisplayNameInput']")).sendKeys("GITSCM");
			log.info("Plugin is expaneded");
			new InputPluginDetails(driver).enterStepDetails(step, gitscmInputParameter);

			// JSONObject
			// InputPluginDataRequest=LoadData.readJSONFileToCreatePipeline(Constants.INPUTPIPELINEDATA_JSON_PATH);
			// String token=InputPluginDataRequest.get("GitTokenVariable").toString();
			Thread.sleep(3000);
			if (driver.findElement(By.xpath(
					"//mat-dialog-container[contains(@id,\"mat-dialog-\")]/app-variables-directive-dialog/div[1]/div[1]/h2"))
					.isDisplayed()) {
				new WebDriverWait(driver, Duration.ofMinutes(2)).until(ExpectedConditions.elementToBeClickable(By.xpath(
						"//div/span/label/mat-label[text()=\"Search\"]/parent::label/parent::span/parent::div/input")));
				Thread.sleep(3000);
				driver.findElement(By.xpath(
						"//div/span/label/mat-label[text()=\"Search\"]/parent::label/parent::span/parent::div/input"))
						.sendKeys("Git_token");
				Thread.sleep(5000);
				driver.findElement(By.xpath("//table/tbody/tr/td[3]/button")).click();
			}
			log.info("Step details entered");
			break;

		case "GITSCM_VAR":
			String[] gitscmvar = { "Git Provider:dropdown", "gitUrl:input", "username:input", "password:input",
					"branchPattern:input" };
			log.info("Plugin name: GitSCM");
			driver.findElement(pv.searchPluginTextBox).sendKeys("gitscm");
			driver.findElement(pv.selectPlugin("gitscm")).click();
			log.info("gitscm plugin selected");
			pv.expandPluginDetails("gitscm");
			log.info("Plugin is expaneded");
			new InputPluginDetails(driver).enterStepDetails(step, gitscmvar);
			log.info("Step details entered");
			break;

		case "GITSCM1":
			String[] gitscmInputParameter1 = { "browser:input", "browserUrl:input", "Git Provider:dropdown",
					"gitUrl:input", "username:input", "password:input", "branchPattern:input" };
			log.info("Plugin name: GitSCM");
			driver.findElement(pv.searchPluginTextBox).sendKeys("gitscm");
			driver.findElement(pv.selectPlugin("gitscm")).click();
			log.info("gitscm plugin selected");
			pv.expandPluginDetails("gitscm");
			log.info("Plugin is expaneded");
			new InputPluginDetails(driver).enterStepDetails(step, gitscmInputParameter1);
			if (driver.findElement(By.xpath(
					"//mat-dialog-container[contains(@id,\"mat-dialog-\")]/app-variables-directive-dialog/div[1]/div[1]/h2"))
					.isDisplayed()) {
				new WebDriverWait(driver, Duration.ofMinutes(2)).until(ExpectedConditions.elementToBeClickable(By.xpath(
						"//div/span/label/mat-label[text()=\"Search\"]/parent::label/parent::span/parent::div/input")));
				driver.findElement(By.xpath(
						"//div/span/label/mat-label[text()=\"Search\"]/parent::label/parent::span/parent::div/input"))
						.sendKeys("Git_token"); // token
				driver.findElement(By.xpath("//table/tbody/tr/td[3]/button")).click();
			}
			log.info("Step details entered");

			break;
		// gitscm_maven

		case "GITSCM_MAVEN":
			String[] gitscmInputParameter2 = { "Git Provider:dropdown", "gitUrl:input", "username:input",
					"password:input", "branchPattern:input" };
			log.info("Plugin name: GitSCM");
			driver.findElement(pv.searchPluginTextBox).sendKeys("gitscm");
			driver.findElement(pv.selectPlugin("gitscm")).click();
			log.info("gitscm plugin selected");
			pv.expandPluginDetails("gitscm");
			log.info("Plugin is expaneded");
			driver.findElement(By.xpath("//input[@name='pluginDisplayNameInput']")).clear();
			driver.findElement(By.xpath("//input[@name='pluginDisplayNameInput']")).sendKeys("GITSCM_MAVEN");
			log.info("Step-------------- " + step);
			new InputPluginDetails(driver).enterStepDetails(step, gitscmInputParameter2);
			if (driver.findElement(By.xpath(
					"//mat-dialog-container[contains(@id,\"mat-dialog-\")]/app-variables-directive-dialog/div[1]/div[1]/h2"))
					.isDisplayed()) {
				new WebDriverWait(driver, Duration.ofMinutes(2)).until(ExpectedConditions.elementToBeClickable(By.xpath(
						"//div/span/label/mat-label[text()=\"Search\"]/parent::label/parent::span/parent::div/input")));
				driver.findElement(By.xpath(
						"//div/span/label/mat-label[text()=\"Search\"]/parent::label/parent::span/parent::div/input"))
						.sendKeys("Git_token");
				Thread.sleep(5000);
				driver.findElement(By.xpath("//table/tbody/tr/td[3]/button")).click();
			}
			log.info("Step details entered");
			break;

		case "GITSCM_TOMCAT":
			String[] gitscmInputParameter3 = { "browser:input", "browserUrl:input", "Git Provider:dropdown",
					"gitUrl:input", "username:input", "password:input", "branchPattern:input" };
			log.info("Plugin name: GitSCM");
			driver.findElement(pv.searchPluginTextBox).sendKeys("gitscm");
			driver.findElement(pv.selectPlugin("gitscm")).click();
			log.info("gitscm plugin selected");
			pv.expandPluginDetails("gitscm");
			log.info("Plugin is expaneded");
			new InputPluginDetails(driver).enterStepDetails(step, gitscmInputParameter3);
			if (driver.findElement(By.xpath(
					"//mat-dialog-container[contains(@id,\"mat-dialog-\")]/app-variables-directive-dialog/div[1]/div[1]/h2"))
					.isDisplayed()) {
				new WebDriverWait(driver, Duration.ofMinutes(2)).until(ExpectedConditions.elementToBeClickable(By.xpath(
						"//div/span/label/mat-label[text()=\"Search\"]/parent::label/parent::span/parent::div/input")));
				driver.findElement(By.xpath(
						"//div/span/label/mat-label[text()=\"Search\"]/parent::label/parent::span/parent::div/input"))
						.sendKeys("gitscm");
				driver.findElement(By.xpath("//table/tbody/tr/td[3]/button")).click();
			}
			log.info("Step details entered");
			break;

		case "DOTNET_BUILD":
			String[] dotnetBuildInputParameters = { "solutionFile:input", "msbuildArguments:input" };
			log.info("Plugin name: dotnet_build");
			driver.findElement(pv.searchPluginTextBox).sendKeys("dotnet build");
			driver.findElement(pv.selectPlugin("Dotnet Build")).click();
			log.info("dotnet_build plugin selected");
			pv.expandPluginDetails("Dotnet-Build");
			log.info("Plugin is expaneded");
			driver.findElement(By.xpath("//input[@name='pluginDisplayNameInput']")).clear();
			driver.findElement(By.xpath("//input[@name='pluginDisplayNameInput']")).sendKeys("DOTNET_BUILD");
			new InputPluginDetails(driver).enterStepDetails(step, dotnetBuildInputParameters);

			log.info("Step details entered");
			break;

		case "ANGULAR_BUILD":
			String[] angularBuildInputParameters = { "compileArgument:input", "npmArgument:input",
					"workingDirectory:input" };
			log.info("Plugin name: angular build");
			driver.findElement(pv.searchPluginTextBox).sendKeys("angular build");
			driver.findElement(pv.selectPlugin("Angular Build")).click();
			log.info("angular_build plugin selected");
			pv.expandPluginDetails("Angular-Build");
			log.info("Plugin is expaneded");
			driver.findElement(By.xpath("//input[@name='pluginDisplayNameInput']")).clear();
			driver.findElement(By.xpath("//input[@name='pluginDisplayNameInput']")).sendKeys("ANGULAR_BUILD");
			new InputPluginDetails(driver).enterStepDetails(step, angularBuildInputParameters);
			log.info("Step details entered");
			break;

		case "TSLINT_CODE_ANALYSIS":
			String[] TslintCodeAnalysisInputParameters = { "workingDirectory:input" };
			log.info("Plugin name: Tslint Code Analysis");
			driver.findElement(pv.searchPluginTextBox).sendKeys("tslint code analysis");
			driver.findElement(pv.selectPlugin("Tslint Code Analysis")).click();
			log.info("Angular Codeanalysis Tslint-plugin selected");
			pv.expandPluginDetails("Tslint-Code-Analysis");
			log.info("Plugin is expaneded");
			driver.findElement(By.xpath("//input[@name='pluginDisplayNameInput']")).clear();
			driver.findElement(By.xpath("//input[@name='pluginDisplayNameInput']")).sendKeys("TSLINT_CODE_ANALYSIS");
			new InputPluginDetails(driver).enterStepDetails(step, TslintCodeAnalysisInputParameters);
			log.info("Step details entered");
			break;

		case "SonarQube":
			String[] sonarInputParameters = { "serverUrl:input", "userToken:input", "propertiesFilePath:input",
					"projectKey:input", "password:input" };
			log.info("Plugin name: SonarQube");
			driver.findElement(pv.searchPluginTextBox).sendKeys("SonarQube");
			driver.findElement(pv.selectPlugin("SonarQube")).click();
			log.info("Sonar plugin selected");
			pv.expandPluginDetails("SonarQube");
			log.info("Plugin is expaneded");
			new InputPluginDetails(driver).enterStepDetails(step, sonarInputParameters);
			driver.findElement(By.xpath(
					"//div/span/label/mat-label[text()=\"Search\"]/parent::label/parent::span/parent::div/input"))
					.sendKeys("Git_token");
			Thread.sleep(5000);
			driver.findElement(By.xpath("//table/tbody/tr/td[3]/button")).click();
			log.info("Step details entered");
			break;

		case "DOCKER_BUILD_&_PUSH":
			String[] dockerBuildInputParameter = { "dockerFilePath:input", "dockerPassword:input",
					"dockerRegistryUrl:input", "dockerRepo:input", "dockerUsername:input", "tagName:input" };
			log.info("Plugin name: docker_build_&_push");
			driver.findElement(pv.searchPluginTextBox).sendKeys("docker build & push");
			driver.findElement(pv.selectPlugin("Docker Build & Push")).click();
			log.info("docker_build plugin selected");
			pv.expandPluginDetails("Docker-Build-&-Push");
			log.info("Plugin is expaneded");
			new InputPluginDetails(driver).enterStepDetails(step, dockerBuildInputParameter);
			if (driver.findElement(By.xpath(
					"//mat-dialog-container[contains(@id,\"mat-dialog-\")]/app-variables-directive-dialog/div[1]/div[1]/h2"))
					.isDisplayed()) {
				new WebDriverWait(driver, Duration.ofMinutes(2)).until(ExpectedConditions.elementToBeClickable(By.xpath(
						"//div/span/label/mat-label[text()=\"Search\"]/parent::label/parent::span/parent::div/input")));
				driver.findElement(By.xpath(
						"//div/span/label/mat-label[text()=\"Search\"]/parent::label/parent::span/parent::div/input"))
						.sendKeys("docker_password");
				driver.findElement(By.xpath("//table/tbody/tr/td[3]/button")).click();
			}
			log.info("Step details entered");
			break;

		case "ANDROID_LINT":
			String[] androidLintInputParameter = { "workingDirectory:input" };
			log.info("Plugin name: Android Lint");
			driver.findElement(pv.searchPluginTextBox).sendKeys("Android lint");
			driver.findElement(pv.selectPlugin("Android lint")).click();
			log.info("android_lint plugin selected");
			pv.expandPluginDetails("Android-lint");
			driver.findElement(By.xpath("//input[@name='pluginDisplayNameInput']")).clear();
			driver.findElement(By.xpath("//input[@name='pluginDisplayNameInput']")).sendKeys("ANDROID_LINT");
			log.info("Plugin is expaneded");
			new InputPluginDetails(driver).enterStepDetails(step, androidLintInputParameter);
			log.info("Step details entered");
			break;

		case "TOMCAT":
			String[] tomcatInputParameter = { "contextPath:input", "serverManagerUrl:input", "tomcatPassword:input",
					"tomcatUsername:input", "warPath:input" };
			log.info("Plugin name: TOMCAT");
			driver.findElement(pv.searchPluginTextBox).sendKeys("Tomcat");
			driver.findElement(pv.selectPlugin("Tomcat")).click();
			log.info("tomcat plugin selected");
			pv.expandPluginDetails("Tomcat");
			log.info("Plugin is expaneded");
			new InputPluginDetails(driver).enterStepDetails(step, tomcatInputParameter);
			log.info("Step details entered");
			break;

		case "PYTHON_BUILD":
			String[] pythonBuildInputParameter = { "requirementFileDirectory:input", "setupFileDirectory:input",
					" Packaging type :dropdown" };
			log.info("Plugin name: PYTHON BUILD");
			driver.findElement(pv.searchPluginTextBox).sendKeys("Python Build");
			driver.findElement(pv.selectPlugin("Python Build")).click();
			log.info("python_build plugin selected");
			pv.expandPluginDetails("Python-Build");
			log.info("Plugin is expaneded");
			driver.findElement(By.xpath("//input[@name='pluginDisplayNameInput']")).clear();
			driver.findElement(By.xpath("//input[@name='pluginDisplayNameInput']")).sendKeys("PYTHON_BUILD");
			new InputPluginDetails(driver).enterStepDetails(step, pythonBuildInputParameter);
			log.info("Step details entered");
			break;

		case "JMETER":
			String[] jmeterInputParameter = { "jmxFile:input" };
			log.info("Plugin name: Jmeter");
			driver.findElement(pv.searchPluginTextBox).sendKeys("Jmeter");
			driver.findElement(pv.selectPlugin("Jmeter")).click();
			log.info("jmeter plugin selected");
			pv.expandPluginDetails("Jmeter");
			log.info("Plugin is expaneded");
			driver.findElement(By.xpath("//input[@name='pluginDisplayNameInput']")).clear();
			driver.findElement(By.xpath("//input[@name='pluginDisplayNameInput']")).sendKeys("JMETER");
			new InputPluginDetails(driver).enterStepDetails(step, jmeterInputParameter);
			log.info("Step details entered");
			break;

		case "ITAF":
			String[] itafInputParameter = { "iTafUrl:input", "iTafUsername:input", "iTafAgent:input",
					"iTafProject:input", "browser:input", "testSuites:input" };
			log.info("Plugin name: ITAF");
			driver.findElement(pv.searchPluginTextBox).sendKeys("ITAF");
			driver.findElement(pv.selectPlugin("ITAF")).click();
			log.info("iTAF plugin selected");
			pv.expandPluginDetails("ITAF");
			log.info("Plugin is expaneded");
			new InputPluginDetails(driver).enterStepDetails(step, itafInputParameter);
			log.info("Step details entered");
			break;

		case "PYTHON LINT":
			String[] pythonLintInputParameter = { "pythonRootDirectory:input" };
			log.info("pluginName: Python Lint");
			driver.findElement(pv.searchPluginTextBox).sendKeys("Python Lint");
			driver.findElement(pv.selectPlugin("Python Lint")).click();
			log.info("Python Lint plugin selected");
			pv.expandPluginDetails("Python-Lint");
			log.info("Plugin is expanded");
			new InputPluginDetails(driver).enterStepDetails(step, pythonLintInputParameter);
			log.info("Step details entered");
			break;

		case "DOCKER SWARM DEPLOY":
			String[] dockerSwarmDeployLintInputParameter = { "dockerFilePath:input", "dockerRegistryUrl:input",
					"dockerRepo:input", "dockerUsername:input", "dockerPassword:input", "stackName:input",
					"tagName:input" };
			log.info("pluginName : Docker Swarm Deploy");
			driver.findElement(pv.searchPluginTextBox).sendKeys("Docker Swarm Deploy");
			driver.findElement(pv.selectPlugin("Docker Swarm Deploy")).click();
			log.info("Docker Swarm Deploy plugin selected");
			pv.expandPluginDetails("Docker-Swarm-Deploy");
			log.info("Plugin is expanded");
			new InputPluginDetails(driver).enterStepDetails(step, dockerSwarmDeployLintInputParameter);
			log.info("Step details entered");
			break;

		case "SELENIUM":
			String[] seleniumInputParameter = { "pomFilePath:input", "testNgFiles:input" };
			log.info("pluginName : Selenium");
			driver.findElement(pv.searchPluginTextBox).sendKeys("Selenium");
			driver.findElement(pv.selectPlugin("Selenium")).click();
			log.info("Python Lint plugin selected");
			pv.expandPluginDetails("Selenium");
			log.info("Plugin is expanded");
			new InputPluginDetails(driver).enterStepDetails(step, seleniumInputParameter);
			log.info("Step details entered");
			break;

		case "AGILEDASHBOARDJIRAREPORT":
			String[] jiraReportInputParameter = { "jiraurl:input", "jiraprojectname:input", "jiraUsername:input",
					"jirapassword:input" };
			log.info("pluginName : agileDashboardJiraReport");
			driver.findElement(pv.searchPluginTextBox).sendKeys("agileDashboardJiraReport");
			driver.findElement(pv.selectPlugin("agileDashboardJiraReport")).click();
			log.info("Python Lint plugin selected");
			pv.expandPluginDetails("agileDashboardJiraReport");
			log.info("Plugin is expanded");
			new InputPluginDetails(driver).enterStepDetails(step, jiraReportInputParameter);
			log.info("Step details entered");
			break;

		case "CONTINUUM":
			String[] continuumInputParameter = { "url:input", "token:input", "want:input", "package:input",
					"progression:input", "version:input" };
			log.info("pluginName : continuum");
			driver.findElement(pv.searchPluginTextBox).sendKeys("continuum");
			driver.findElement(pv.selectPlugin("continuum")).click();
			log.info("Python Lint plugin selected");
			pv.expandPluginDetails("continuum");
			log.info("Plugin is expanded");
			new InputPluginDetails(driver).enterStepDetails(step, continuumInputParameter);
			log.info("Step details entered");
			break;

		case "IISDEPLOY":
			String[] IIsdeployInputParameter = { "projectPath:input", "machineName:input", "userName:input",
					"password:input" };
			log.info("pluginName : IIsDeploy");
			driver.findElement(pv.searchPluginTextBox).sendKeys("iisdeploy");
			driver.findElement(pv.selectPlugin("iisdeploy")).click();
			log.info("Python Lint plugin selected");
			pv.expandPluginDetails("iisdeploy");
			log.info("Plugin is expanded");
			new InputPluginDetails(driver).enterStepDetails(step, IIsdeployInputParameter);
			log.info("Step details entered");
			break;

		case "SOAPUI":
			String[] soapUIInputParameter = { "projectPath:input" };
			log.info("pluginName : SoapUI");
			driver.findElement(pv.searchPluginTextBox).sendKeys("SoapUI");
			driver.findElement(pv.selectPlugin("SoapUI")).click();
			log.info("Python Lint plugin selected");
			pv.expandPluginDetails("SoapUI");
			log.info("Plugin is expanded");
			new InputPluginDetails(driver).enterStepDetails(step, soapUIInputParameter);
			log.info("Step details entered");
			break;
		}

	}

}
