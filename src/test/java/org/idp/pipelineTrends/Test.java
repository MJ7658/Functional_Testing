package org.idp.pipelineTrends;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
		WebDriver driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(12));
		Map<String, Object> asmap = ((EdgeDriver) driver).getCapabilities().asMap();
		asmap.forEach((key, value) -> {
			System.out.println("Keys========> " + key + "Value ===============> " + value);
		});
		Test reg = new Test();
		String name = "test";
		reg.userRegistration(driver, name);
	}

	public void userRegistration(WebDriver driver, String text) throws InterruptedException {
		System.out.println("Its string parameter ============================================> : " + text);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddHHmmss");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		int randomNumber = Integer.parseInt(date);
		// int randomNumber = rand.nextInt(2000) + 1;
		System.out.println("Random number: " + randomNumber);
		// Create New Mail ID ---> TestCase 1
		driver.get("https://temporarymail.com/");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		String Mail_old = driver.findElement(By.xpath("(//input)[1]")).getText(); // Store to variable
		// ----------------------- Split ------------------------
		String[] Getdomain = Mail_old.split("@");
		String MyDomain = Getdomain[1];
		driver.findElement(By.xpath("(//button/i)[3]")).click(); // Change Address
		driver.findElement(By.xpath("//p[contains(text(),'Username')]/following::input")).clear(); // Clear previous one
		driver.findElement(By.xpath("//p[contains(text(),'Username')]/following::input"))
				.sendKeys("quality" + randomNumber); // QA+Random
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@type='submit']")).click(); // Change Mail iD
		Thread.sleep(10000);
		// String Mail_new=driver.findElement(By.xpath("(//input)[1]")).getText();
		// System.out.println("Mail_ID " +Mail);
		// Parent Window
		String parent = driver.getWindowHandle();

		// ------------------ Switch Tab----------------------------

		JavascriptExecutor executor2 = (JavascriptExecutor) driver;
		executor2.executeScript(
				"window.open('https://laborforcesolution--laborsit.sandbox.my.site.com/EmployerPortal/s/login/?language=en_US&ec=302&startURL=%2FEmployerPortal%2Fs%2F')");
		Set<String> s = driver.getWindowHandles();
		Iterator<String> I1 = s.iterator();
		while (I1.hasNext()) {
			String child_window = I1.next();
			if (!parent.equals(child_window)) {
				driver.switchTo().window(child_window);
				System.out.println(driver.switchTo().window(child_window).getTitle());
			}
		}
		// ---------------------------------------------------------
		// Create new account
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		WebElement objectToLoad = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Create Account')]")));
		objectToLoad.click();
		// driver.findElement(By.xpath("//a[contains(text(),'Create
		// Account')]")).click();

		Random rand = new Random();
		// Generate a random number between 100000000 (inclusive) and 1000000000
		// (exclusive)
		int nineDigitNumber = rand.nextInt(900000000) + 100000000;
		System.out.println(nineDigitNumber);

		String stringValue = String.valueOf(nineDigitNumber);
		Thread.sleep(5000);
		// Enter Employer Identification Number
		driver.findElement(By.xpath("(//label/following::input)[1]")).sendKeys(stringValue);
		// Re-Enter Employer Identification Number
		driver.findElement(By.xpath("(//label/following::input)[2]")).sendKeys(stringValue);
		// Accept user Agreement
		driver.findElement(By.xpath("//span[@class='slds-checkbox_faux']")).click();
//		driver.findElement(By.xpath(
//				"//span[contains(text(),'I understand that committing Unemployment Insurance fraud is a felony.')]"))
//				.click();
//		driver.findElement(
//				By.xpath("//span[contains(text(),'I understand that all information provided will be verified.')]"))
//				.click();

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//span[contains(text(),'Next')]")));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();

		// driver.manage().window().maximize();
		// driver.navigate().refresh();
		Thread.sleep(10000);
		// User Signup on labour force
		driver.findElement(By.xpath("//input[@placeholder=\"First Name\"]")).sendKeys("qatest_" + randomNumber);
		driver.findElement(By.xpath("//input[@placeholder=\"Last Name\"]")).sendKeys("quality" + randomNumber);
		driver.findElement(By.xpath("//input[@placeholder=\"Email\"]"))
				.sendKeys("quality" + randomNumber + "@" + MyDomain);
		Thread.sleep(10000);
		driver.findElement(By.xpath("//input[@placeholder=\"Phone\"]")).sendKeys("86723975" + randomNumber);
		driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
		Thread.sleep(5000);
		driver.switchTo().window(parent);
		Thread.sleep(5000);
		driver.navigate().refresh();
		// Test Case 3: -> Authenticate mail and Open the link
		// Click just Now
		Thread.sleep(30000);
		driver.findElement(By.xpath("//div[contains(text(),'Sandbox: Welcome to LaborForceTax')]")).click();
		WebElement iframe = driver.findElement(By.xpath("//iframe[@id='messageIframe']"));
		driver.switchTo().frame(iframe);
		System.out.println("iframe Dedected!");
		Thread.sleep(5000);
		String MailBody = driver.findElement(By.xpath("//*[contains(text(),'Hi qatest')]")).getText();
		System.out.println("-------->   " + MailBody + " <---------");
		// ---------------------- Grip Sentence ----------------------------------------

		String[] ids = MailBody.split(" go to ");
		String name = ids[1];
		String[] newname = name.trim().split("Username:");
		String url = newname[0].replace("hxxps", "https");
		System.out.println(url);
		driver.get(url);
		driver.switchTo().alert().accept();
	}

}
