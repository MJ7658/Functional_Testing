package org.idp.pipelineTrends;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class KeyboardAction {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
		WebDriver driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(12));
		Map<String, Object> asmap = ((EdgeDriver) driver).getCapabilities().asMap();
		asmap.forEach((key, value) -> {
			System.out.println("Keys========> " + key + "Value ===============> " + value);
		});
		KeyboardAction reg = new KeyboardAction();
		String name = "test";
		reg.keyboardClick(driver);
	}

	public void keyboardClick(WebDriver driver) throws InterruptedException {

		try {
			Robot robot = new Robot();
			Thread.sleep(3000);
			robot.keyPress(KeyEvent.VK_F2);
			robot.keyRelease(KeyEvent.VK_F2);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

}
