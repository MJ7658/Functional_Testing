package org.infy.uiPages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.infy.init.ReadConfigFile;
//import org.infy.init.EncryptionAndDecryption;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	private WebDriver driver;
	private Wait<WebDriver> wait;
	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(LoginPage.class);

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	By LoginScreen = By.className("login-pf-page");
	By userName = By.id("username");
	By password = By.id("password");
	By loginButton = By.id("kc-login");
	By loginError = By.xpath("//div[contains(@class,'alert-error')]");
	By advanced = By.id("details-button");
	By proceedToUnsafe = By.id("proceed-link");
	By nextButton = By.id("idSIButton9");
	By emailaddress = By.id("i0116");



	public void login(String id) throws Exception {

		if (ReadConfigFile.browser.equalsIgnoreCase("chrome")) {
			driver.findElement(emailaddress).sendKeys(id);
			wait = new WebDriverWait(driver, Duration.ofMinutes(2));
			wait.until(ExpectedConditions.elementToBeClickable(nextButton));
			driver.findElement(nextButton).click();
			log.info("Login successful");
		} else if (ReadConfigFile.browser.equalsIgnoreCase("Edge")) {
			Thread.sleep(10000);
			
			screenshot();
			driver.findElement(emailaddress).sendKeys(id);
			wait = new WebDriverWait(driver, Duration.ofMinutes(1));
			screenshot();
			wait.until(ExpectedConditions.elementToBeClickable(nextButton));
			screenshot();
			driver.findElement(nextButton).click();
			screenshot();
			System.out.println("Login successful");
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
			Thread.sleep(2000);
			screenshot();
		}
	}

//	public void login(String id) throws Exception {
//
//		if (ReadConfigFile.browser.equalsIgnoreCase("chrome")) {
	//{
	
	

//			driver.findElement(emailaddress).sendKeys(id);
//			wait = new WebDriverWait(driver, Duration.ofMinutes(2));

//			wait.until(ExpectedConditions.elementToBeClickable(nextButton));
//			screenshot();
//			driver.findElement(nextButton).click();
//	log.info("Login successful");
//	Thread.sleep(30000);
//} else if (ReadConfigFile.browser.equalsIgnoreCase("Edge")) {
//	Thread.sleep(10000);
////	screenshot();
////	driver.findElement(emailaddress).sendKeys(id);
////	wait = new WebDriverWait(driver, Duration.ofMinutes(2));
////	screenshot();
////	wait.until(ExpectedConditions.elementToBeClickable(nextButton));
////	screenshot();
////	driver.findElement(nextButton).click();
//	System.out.println("Login successful");
//}
//}
	
	public void screenshot() throws IOException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		String path = System.getProperty("user.dir") + "//ExecutionScreentshot//" + date + ".png";
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File(path));
	}
}
