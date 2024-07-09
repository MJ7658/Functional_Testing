package org.infy.uiPages;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	private WebDriver driver;
	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(HomePage.class);

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	By header = By.xpath("//app-header//mat-toolbar//h1[normalize-space()='IDP']");
	By userEmail = By.xpath(
			"//app-header//mat-toolbar//mat-icon[normalize-space()='keyboard_arrow_down']/parent::span/parent::button");
	By logoutButton = By.xpath("//div[@class='mat-menu-content']//mat-icon[text()='exit_to_app']/parent::button");
	By dialog = By.xpath("//mat-dialog-container");

	public void logOut() {
		if (!driver.findElements(dialog).isEmpty()) {
			driver.navigate().refresh();
		}
		driver.findElement(userEmail).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton));
		driver.findElement(logoutButton).click();
		log.info("Logout successful");

	}

}
