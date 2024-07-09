package org.infy.commonHelpers;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.infy.init.BrowserFactory;
import org.infy.reportHelper.ReportHelper;
import org.infy.uiPages.ApplicationView;
import org.infy.uiPages.PipelineView;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;

public class WorkerHelper {
	static String Application;
	private WebDriver driver;
	private WebDriverWait wait;
	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(WorkerHelper.class);
	public static String applicationNameToBeCreated;
	public static String applicationWorkflowNameToBeCreated;
	public static String applicationWorkflowNameCreated;
	public int count, flag;
	public static boolean statusVerify;
	public static String agentNameis;
	private String VisualTestingStatus;

	public WorkerHelper(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(800));
		this.VisualTestingStatus = BrowserFactory.visualTestStatus();
	}

	public void addWorker(String name) throws IOException {
		WebElement element = driver.findElement(By.xpath("//*[text()=' Dashboard ']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Add Worker']"))).click();
		driver.findElement(By.xpath("(//*[starts-with(@id,'mat-input-')])[2]")).sendKeys(name);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Save ']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Save ']"))).click();
		log.info("worker added successfully");
	}

	public void platformLeveladdWorker(String name) throws IOException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Add Worker']"))).click();
		driver.findElement(By.xpath("(//*[starts-with(@id,'mat-input-')])[2]")).sendKeys(name);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Save ']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Save ']"))).click();
		log.info("worker added successfully");
	}

	public void addWorkerPlatformLevel(String name) throws IOException {
		// WebElement element = driver.findElement(By.xpath("//*[text()='Dashboard']"));
		// ((JavascriptExecutor)
		// driver).executeScript("arguments[0].scrollIntoView(true);", element);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Add Worker']"))).click();
		driver.findElement(By.xpath("//*[text()='Add Worker']//following::input[1]")).sendKeys(name);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Save ']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Save ']"))).click();
	}

	public void deleteLinuxWorker() throws IOException {
		String agentName = linux("ls Satheesh").substring(0, 11);

		linux("cd Satheesh && rm " + agentName + "&& rm worker.lock");
		linux("cd Satheesh && rm worker.lock");
	}

	public static String decode(String token) {
		byte[] decodedBytes = Base64.decodeBase64(token);
		String TokenDetails = new String(decodedBytes);
		return TokenDetails;
	}

	public static String linux(String command1) {
		String host = "10.208.80.34";
		String users = "cHJvamFkbWlu";
		String passwords = "SWRwQDIwMjQ=";

		String user = decode(users);
		String password = decode(passwords);

		String value = null;
		try {
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			JSch jsch = new JSch();
			com.jcraft.jsch.Session session = jsch.getSession(user, host, 22);
			session.setPassword(password);
			session.setConfig(config);
			session.connect();
			log.info("Connected");
			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(command1);
			channel.setInputStream(null);
			((ChannelExec) channel).setErrStream(System.err);
			InputStream in = channel.getInputStream();
			channel.connect();

			byte[] tmp = new byte[1024];
			while (true) {
				while (in.available() > 0) {
					int i = in.read(tmp, 0, 1024);
					if (i < 0)
						break;
					// System.out.print(new String(tmp, 0, i));
					value = new String(tmp, 0, i);
					return value;
				}
				if (channel.isClosed()) {
					log.info("exit-status: " + channel.getExitStatus());
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (Exception ee) {
				}
			}
			channel.disconnect();
			session.disconnect();
			log.info("DONE");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	public static void linuxAgentRunner(WebDriver drivers, String command1, String WorkerNames) throws Exception {
		String host = "10.208.80.34";
		String users = "cHJvamFkbWlu";
		String passwords = "SWRwQDIwMjQ=";

		String user = decode(users);
		String password = decode(passwords);

		String value = null;

		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		JSch jsch = new JSch();
		com.jcraft.jsch.Session session = jsch.getSession(user, host, 22);
		session.setPassword(password);
		session.setConfig(config);
		session.connect();
		log.info("Connected");
		Channel channel = session.openChannel("exec");
		((ChannelExec) channel).setCommand(command1);
		channel.setInputStream(null);
		((ChannelExec) channel).setErrStream(System.err);
		InputStream in = channel.getInputStream();
		channel.connect();
		WorkerHelper wh = new WorkerHelper(drivers);
		wh.workerPageRefresh();
		boolean va = wh.instanceVerifycation("IdleInstance", WorkerNames, "0", "1");
		assertTrue(va);
		wh.tokenRefresh(WorkerNames);
		channel.disconnect();
		session.disconnect();
		log.info("DONE");
	}

	public static boolean linuxAndWindownsAgentRunner(WebDriver drivers, String command1, String WorkerNames)
			throws Exception {

		String host = "10.208.80.34";
		String users = "cHJvamFkbWlu";
		String passwords = "SWRwQDIwMjQ=";

		String user = decode(users);
		String password = decode(passwords);
		String value = null;

		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		JSch jsch = new JSch();
		com.jcraft.jsch.Session session = jsch.getSession(user, host, 22);
		session.setPassword(password);
		session.setConfig(config);
		session.connect();
		log.info("Linux machine Connected");
		Channel channel = session.openChannel("exec");
		((ChannelExec) channel).setCommand(command1);
		channel.setInputStream(null);
		((ChannelExec) channel).setErrStream(System.err);
		InputStream in = channel.getInputStream();
		channel.connect();
		WorkerHelper wh = new WorkerHelper(drivers);
		wh.downloadWorker();
		String agentName = wh.agentNames();
		Thread.sleep(3000);
		wh.scrollIntoViewOn(WorkerNames);
		wh.agentRunning(WorkerNames);
		wh.workerPageRefresh();
		wh.workerPageRefresh();
		Thread.sleep(3000);
		wh.instanceVerifycation("IdleInstance", WorkerNames, "0", "2");
		wh.clickOnDetailsButton(WorkerNames);
		wh.clickONRefresh();
		wh.clickONRefresh();
		wh.workerPageRefresh();
		wh.clickOnDetailsButton(WorkerNames);
		String linuxDirname = wh.verifyWorkerWorkSpaceDir();
		String windownsDir = wh.verifyWorkerWorkSpaceDirSecondRow();
		Thread.sleep(3000);
		wh.agentStoper(agentName);
		wh.delectFiles(agentName);
		wh.delectFiles("worker.lock");
		boolean flag;
		if (linuxDirname.contains("Satheesh") && windownsDir.contains("Data")) {
			log.info("Dri Details working fine");
			flag = true;
		} else if (windownsDir.contains("Satheesh") && linuxDirname.contains("Data")) {
			log.info("Dir Not working fine");
			flag = true;
		} else {
			log.info("Dir Not working fine");
			flag = false;
		}
		channel.disconnect();
		session.disconnect();
		log.info("linux And Windowns AgentRunner DONE");
		return flag;
	}

	public void windowTOlinux(String windowsPath, String LinuxPath, String FileName) throws Exception {

		String host = "10.208.80.34";
		String users = "cHJvamFkbWlu";
		String passwords = "SWRwQDIwMjQ=";

		String user = decode(users);
		String password = decode(passwords);
		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		JSch jsch = new JSch();
		com.jcraft.jsch.Session session = jsch.getSession(user, host, 22);
		session.setPassword(password);
		session.setConfig(config);
		session.connect();
		ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
		channelSftp.connect();
		String localFile = windowsPath;
		String remoteDir = LinuxPath;
		channelSftp.put(localFile, remoteDir + FileName);
		channelSftp.exit();
		session.disconnect();
	}

	public void downloadLinuxWorker()
			throws InterruptedException, IOException, HeadlessException, UnsupportedFlavorException {
		Thread.sleep(6000);
		WebElement element = driver.findElement(By.xpath("//*[text()=' Dashboard ']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
//		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(
//				By.xpath("//*[@class='mat-icon notranslate point pr-1 mr-1 mat-primary material-icons']")))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Download Worker ']")));
		Thread.sleep(6000);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Download Worker ']")))).click();
		WebElement copy = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(
				"//*[contains(text(),'framework/linux')]//following::span[@class='mat-mdc-button-persistent-ripple mdc-icon-button__ripple']"))));
		Thread.sleep(5000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", copy);
		Thread.sleep(5000);
		Actions actions = new Actions(driver);
		actions.moveToElement(copy).doubleClick().build().perform();

		String myText = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);

		Thread.sleep(6000);
		String myText1 = "wget " + myText + " --no-check-certificate";
		linux("cd Satheesh && " + myText1 + " ");
		Thread.sleep(3000);
		if (linux("ls Satheesh").contains("agent")) {
			log.info("Agent Downloaded");
		} else {
			log.info("Agent Not Downloaded");
		}
		wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Download Worker']//following::button")));
		driver.findElement(By.xpath("//*[text()='Download Worker']//following::button")).click();
	}

	public void downloadWorker()
			throws InterruptedException, IOException, HeadlessException, UnsupportedFlavorException, AWTException {
		WebElement element = driver.findElement(By.xpath("//*[text()=' Dashboard ']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
//		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(
//				By.xpath("//*[@class='mat-icon notranslate point pr-1 mr-1 mat-primary material-icons']")))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Download Worker ']")));
		log.info("Download worker Popup Opened");
		Thread.sleep(4000);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Download Worker ']")))).click();
		Thread.sleep(5000);
		WebElement copy = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(
				"//*[contains(text(),'framework/windows')]//following::span[@class='mat-mdc-button-persistent-ripple mdc-icon-button__ripple']"))));
		Thread.sleep(5000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", copy);
		Thread.sleep(5000);
		Actions actions = new Actions(driver);
		actions.moveToElement(copy).doubleClick().build().perform();

		String myText = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
		String[] agentName = myText.split("/");
		agentNameis = agentName[8];
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.open('" + myText + "')");
		waitForTheExcelFileToDownload(driver, "agent");
		Thread.sleep(15000);
		wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Download Worker']//following::button")));
		driver.findElement(By.xpath("//*[text()='Download Worker']//following::button")).click();
		log.info("Agent downloaded successfully");
	}

	public void workerAgentNameFinder()
			throws InterruptedException, IOException, HeadlessException, UnsupportedFlavorException, AWTException {
		WebElement element = driver.findElement(By.xpath("//*[text()=' Dashboard ']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
//		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(
//				By.xpath("//*[@class='mat-icon notranslate point pr-1 mr-1 mat-primary material-icons']")))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Download Worker ']")));
		Thread.sleep(4000);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Download Worker ']")))).click();
		Thread.sleep(5000);
		WebElement copy = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(
				"//*[contains(text(),'framework/windows')]//following::span[@class='mat-mdc-button-persistent-ripple mdc-icon-button__ripple']"))));
		log.info("Download Agent Popup Opened");
		Thread.sleep(5000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", copy);
		Thread.sleep(5000);

		WebElement copyButton = driver.findElement(By.xpath(
				"//*[contains(text(),'framework/windows')]//following::span[@class='mat-mdc-button-persistent-ripple mdc-icon-button__ripple']"));
		Thread.sleep(3000);
		Actions actions = new Actions(driver);
		actions.moveToElement(copyButton).doubleClick().build().perform();

		String myText = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
		String[] agentName = myText.split("/");
		agentNameis = agentName[8];
		startingSteps(agentNameis);
		wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Download Worker']//following::button")));
		driver.findElement(By.xpath("//*[text()='Download Worker']//following::button")).click();
	}

	public String agentNames() {
		return agentNameis;
	}

	public void scrollIntoViewOn(String WorkerNames) throws InterruptedException {
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath("//*[normalize-space(text())='" + WorkerNames + "']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
	}

	public void linuxAgentRunning(String WorkerNames) throws Exception {
		scrollIntoViewOn(WorkerNames);
		Thread.sleep(3000);
		WebElement element = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(3000);
		WebElement copyWorker = driver
				.findElement(By.xpath("//*[normalize-space(text())='" + WorkerNames + "']//following::span[18]"));

		Actions actions = new Actions(driver);
		actions.moveToElement(copyWorker).doubleClick().build().perform();

		String myAgentpath = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
		String[] ne = myAgentpath.split(" --");
		String agentName = ne[0].replace("./", "");
		String pa = "./";
		String path = "cd Satheesh && chmod 777 " + agentName + " && " + pa + myAgentpath + " -tlsSkipVerify";
		log.info(path);
		Thread.sleep(3000);
		linuxAgentRunner(driver,
				"cd Satheesh && chmod 777 " + agentName + " && " + pa + myAgentpath + " -tlsSkipVerify=true",
				WorkerNames);
	}

	public void linuxAgentRunningAsService(String WorkerNames, String ServiceName, String service_file_name,
			String binary_path, String workspace_path, String fileNames) throws Exception {
		scrollIntoViewOn(WorkerNames);
		Thread.sleep(3000);
		WebElement element = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[normalize-space(text())='" + WorkerNames + "']//following::mat-icon[5]"))
				.click();

		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//input[@name='serviceFileName']"))))
				.sendKeys(ServiceName);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//input[@placeholder='Enter the agent Path']"))))
				.sendKeys(service_file_name);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//input[@placeholder='Enter the workspace Path']"))))
				.sendKeys(binary_path);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@name='Username']"))))
				.sendKeys(workspace_path);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Download Service File ']")))).click();
		String localFile = System.getProperty("user.dir") + "//Data//" + fileNames;
		String remoteDir = "/home/projadmin/Satheesh/";
		String fileName = fileNames;
		windowTOlinux(localFile, remoteDir, fileName);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(
				"//button[@class='mat-focus-indicator mat-tooltip-trigger cancel mat-icon-button mat-button-base']"))))
				.click();
	}

	public Boolean linuxAndWindowsAgentRunning(String WorkerNames) throws Exception {
		scrollIntoViewOn(WorkerNames);
		Thread.sleep(3000);
		WebElement element = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(3000);
		WebElement copyWorker = driver
				.findElement(By.xpath("//*[normalize-space(text())='" + WorkerNames + "']//following::span[18]"));

		Actions actions = new Actions(driver);
		actions.moveToElement(copyWorker).doubleClick().build().perform();

		String myAgentpath = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
		String[] ne = myAgentpath.split(" --");
		String agentName = ne[0].replace("./", "");
		String pa = "./";
		String path = "cd Satheesh && chmod 777 " + agentName + " && " + pa + myAgentpath + " -tlsSkipVerify";
		log.info(path);
		Thread.sleep(3000);
		Boolean flag = linuxAndWindownsAgentRunner(driver,
				"cd Satheesh && chmod 777 " + agentName + " && " + pa + myAgentpath + " -tlsSkipVerify=true",
				WorkerNames);
		return flag;
	}

	public String agentRunning(String WorkerNames)
			throws HeadlessException, UnsupportedFlavorException, IOException, InterruptedException, AWTException {
		scrollIntoViewOn(WorkerNames);
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(2000);
		driver.manage().window().maximize();
		WebElement copyWorker = driver
				.findElement(By.xpath("//*[normalize-space(text())='" + WorkerNames + "']//following::span[12]"));
		Thread.sleep(3000);
		Actions actions = new Actions(driver);
		actions.moveToElement(copyWorker).doubleClick().build().perform();

		String myAgentpath = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
		agentRunner(driver, myAgentpath, "agent");
		log.info("Agent is running");
		return myAgentpath;
	}

	public void agentRunningDiffernetPath(String WorkerNames)
			throws HeadlessException, UnsupportedFlavorException, IOException, InterruptedException, AWTException {
		scrollIntoViewOn(WorkerNames);
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(2000);
		WebElement copyWorker = driver
				.findElement(By.xpath("//*[normalize-space(text())='" + WorkerNames + "']//following::span[18]"));

		Actions actions = new Actions(driver);
		actions.moveToElement(copyWorker).doubleClick().build().perform();
		String myAgentpath = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
		String path1 = System.getProperty("user.dir") + "\\Agent";
		String shellCMD = " --workspace=" + "\"" + path1 + "\"";
		String newAgentDirPath = myAgentpath + shellCMD;
		agentRunner(driver, newAgentDirPath, "agent");
	}

	public void agentRunningCustomPath(String WorkerNames, String customPath)
			throws HeadlessException, UnsupportedFlavorException, IOException, InterruptedException, AWTException {
		scrollIntoViewOn(WorkerNames);
		WebElement copyWorker = driver
				.findElement(By.xpath("//*[normalize-space(text())='" + WorkerNames + "']//following::span[18]"));

		Actions actions = new Actions(driver);
		actions.moveToElement(copyWorker).doubleClick().build().perform();
		String myAgentpath = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
		String path1 = System.getProperty("user.dir") + "\\" + customPath + "";
		String shellCMD = " --workspace=" + "\"" + path1 + "\"";
		String newAgentDirPath = myAgentpath + shellCMD;
		agentRunner(driver, newAgentDirPath, "agent");
	}

	public void openAgent(String WorkerNames) throws InterruptedException {
		scrollIntoViewOn(WorkerNames);
		WebElement copyWorker = driver
				.findElement(By.xpath("//*[normalize-space(text())='" + WorkerNames + "']//following::span[18]"));

		Actions actions = new Actions(driver);
		actions.moveToElement(copyWorker).doubleClick().build().perform();
	}

	public void moveAgent(String agentName) throws IOException, InterruptedException {
		String path1 = System.getProperty("user.dir") + "\\Data\\" + agentName;
		String path2 = System.getProperty("user.dir") + "\\Agent\\" + agentName;
		log.info("FileMoved = " + moveFile(path1, path2));
	}

	public static void moveAgentToPaticulrLoc(String agentName) throws IOException, InterruptedException {
		String par = agentName.substring(0, 5);
		String fileLocaiton = locationFinder(par);
		String[] ne = fileLocaiton.split("\\\\");
		String path2 = System.getProperty("user.dir") + "\\Agent\\" + ne[4];
		log.info("FileMoved = " + moveFile(fileLocaiton, path2));
	}

	public static void moveAgenttoLoc(String agentName) throws IOException, InterruptedException {
		String par = agentName.substring(0, 5);
		String fileLocaiton = locationFinder(par);
		String[] ne = fileLocaiton.split("\\\\");
		String fil = System.getProperty("user.dir") + "\\Agent\\" + agentName;
		String path2 = System.getProperty("user.dir") + "\\Agent\\" + ne[4];
		rename(fileLocaiton, fil);
	}

	public static void ExcelRenames(String reportName) throws IOException, InterruptedException {
		String fileName = locationFinder("IDP_report");
		File ITE_FileName = new File(fileName);
		String ITE_fname = reportName;
		File ITE_rename = new File(System.getProperty("user.dir") + "\\Data\\", ITE_fname);
		ITE_FileName.renameTo(ITE_rename);
		String path_ite = System.getProperty("user.dir") + "\\Data\\" + ITE_fname + "";
	}

	public static void rename(String path, String path2) {
		Path oldFile = Paths.get(path);
		try {
			Files.move(oldFile, oldFile.resolveSibling(path2));
			log.info("File Successfully Rename");
		} catch (IOException e) {
			log.info("operation failed");
		}
	}

	public static String locationFinder(String fileName) throws IOException, InterruptedException {
		File[] file_Detail = new File(System.getProperty("user.dir") + "\\Data\\").listFiles();
		for (File file : file_Detail) {
			if (file.getName().contains(fileName)) {
				log.info("File Found");
				String newo = file.getAbsolutePath();
				return newo;
			} else {

			}
		}
		return fileName;
	}

	public static boolean moveFile(String sourcePath, String targetPath) {
		File fileToMove = new File(sourcePath);
		return fileToMove.renameTo(new File(targetPath));
	}

	public void moveAndagentRunning(String WorkerNames)
			throws HeadlessException, UnsupportedFlavorException, IOException, InterruptedException, AWTException {
		Thread.sleep(2000);
		agentRunnerAgentFolder(driver, WorkerNames);
	}

	public void tokenRefresh(String WorkerNames)
			throws HeadlessException, UnsupportedFlavorException, IOException, InterruptedException, AWTException {
		scrollIntoViewOn(WorkerNames);
		Thread.sleep(3000);
		WebElement element = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[normalize-space(text())='" + WorkerNames + "']//following::span[25]")));
		doubleClick(driver
				.findElement(By.xpath("//*[normalize-space(text())='" + WorkerNames + "']//following::span[25]")));
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Yes ']"))).click();
	}

	public void doubleClick(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).doubleClick().click().perform();
	}

	public void workerPageRefresh() throws InterruptedException {
		Thread.sleep(8000);
		driver.navigate().refresh();
		log.info("After refresh");
//		Thread.sleep(9000);
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(
//				"//*[starts-with(@class,'mat-ripple mat-tab-header-pagination mat-tab-header-pagination-after mat-elevation')]"))));
//		Thread.sleep(3000);
//		log.info("Click on Arrow button");
//		WebElement element = driver.findElement(By.xpath(
//				"//*[starts-with(@class,'mat-ripple mat-tab-header-pagination mat-tab-header-pagination-after mat-elevation')]"));
//		JavascriptExecutor executor = (JavascriptExecutor) driver;
//		executor.executeScript("arguments[0].click();", element);
//		doubleClick(element);
		Thread.sleep(4000);
		log.info("Click on Worker tab");
		driver.findElement(By.xpath("//*[text()='Workers']"));
		doubleClick(driver.findElement(By.xpath("//*[text()='Workers']")));
		log.info("Double click on Worker Tab");
	}

	public boolean workflowTriggererifycation(String workflowName, String appAndWorkerName)
			throws InterruptedException {
		Thread.sleep(12000);
		WebElement element = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(driver
				.findElement(By.xpath("//*[normalize-space(text())='" + workflowName + "']//following::button[3]"))))
				.click();
		boolean flag;
		try {
			wait.until(ExpectedConditions.visibilityOf(
					driver.findElement(By.xpath("//*[normalize-space(text())='" + appAndWorkerName + "']"))));
			WebElement Status1 = driver
					.findElement(By.xpath("//*[normalize-space(text())='" + appAndWorkerName + "']"));
			Thread.sleep(4000);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	public void workerPageRefresh1() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' refresh ']")));
		WebElement element = driver.findElement(By.xpath("//*[text()=' refresh ']"));
		Actions action = new Actions(driver);
		action.moveToElement(element).doubleClick().doubleClick().build().perform();
	}

	public boolean instanceVerifycation(String instance, String WorkerNames, String ActiveInstance,
			String IdleInstancescout) throws InterruptedException {
		Thread.sleep(4000);
		WebElement element = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(4000);

		WebElement ActiveInstances = driver
				.findElement(By.xpath("//*[normalize-space(text())='" + WorkerNames + "']//following::td[2]"));
		WebElement IdleInstances = driver
				.findElement(By.xpath("//*[normalize-space(text())='" + WorkerNames + "']//following::td[3]"));
		Thread.sleep(5000);

		boolean flag = false;
		if (instance.equalsIgnoreCase("IdleInstance")) {
			if (ActiveInstances.getText().equalsIgnoreCase(ActiveInstance)
					&& IdleInstances.getText().equalsIgnoreCase(IdleInstancescout)) {
				Thread.sleep(2000);
				log.info("The IdleInstances are working fine");
				flag = true;
			} else {
				Thread.sleep(2000);
				log.info("The IdleInstances are NOT working fine");
				flag = false;
			}
		} else {
			if (ActiveInstances.getText().equalsIgnoreCase(ActiveInstance)
					&& IdleInstances.getText().equalsIgnoreCase(IdleInstancescout)) {
				Thread.sleep(2000);
				log.info("The ActiveInstance are working fine");
				flag = true;
			} else {
				Thread.sleep(2000);
				log.info("The ActiveInstances are NOT working fine");
				flag = false;
			}
		}
		return flag;
	}

	public void clickOnDetailsButton(String appAndWorkerName) throws InterruptedException {
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(
				By.xpath("//*[normalize-space(text())='" + appAndWorkerName + "']//following::button[1]")))).click();
	}

	public void clickONRefresh() throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//*[text()='Terminated Instances']//preceding::mat-icon[1]")))).click();
	}

	public Boolean verifyWorker_Up_Since() throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//*[text()=' CPU Cores ']//following::td[4]"))));
		Boolean text = driver.findElement(By.xpath("//*[text()=' CPU Cores ']//following::td[4]")).isDisplayed();
		return text;
	}

	public String verifyWorkerWorkSpaceDir1() throws InterruptedException {
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//*[text()=' CPU Cores ']//following::td[8]"))));
		String text = driver.findElement(By.xpath("//*[text()=' CPU Cores ']//following::td[8]")).getText();
		log.info("We got the text=======>     " + text);
		return text;
	}

	public String verifyWorkerWorkSpaceDir() throws InterruptedException {
		Thread.sleep(3000);
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//*[text()=' CPU Cores ']//following::td[3]"))));
		String text = driver.findElement(By.xpath("//*[text()=' CPU Cores ']//following::td[3]")).getText();
		return text;
	}

	public Boolean verifyWorkerWorkSpaceDirBoolean() throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//*[text()=' CPU Cores ']//following::td[3]"))));
		Boolean text = driver.findElement(By.xpath("//*[text()=' CPU Cores ']//following::td[3]")).isDisplayed();
		return text;
	}

	public void verifyWorkerpopupSearch(String values) throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//*[text()='Terminated Instances']//following::input"))));
		driver.findElement(By.xpath("//*[text()='Terminated Instances']//following::input")).sendKeys(values);

	}

	public String verifyWorkerWorkSpaceDirSecondRow() throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//*[text()=' CPU Cores ']//following::td[15]"))));
		String text = driver.findElement(By.xpath("//*[text()=' CPU Cores ']//following::td[15]")).getText();
		return text;
	}

	public Boolean verifyWorkerInstanceCount() throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//*[text()=' CPU Cores ']//following::td[8]"))));
		Boolean text = driver.findElement(By.xpath("//*[text()=' CPU Cores ']//following::td[8]")).isDisplayed();
		return text;
	}

	// *[text()='Feedback']
	public void clickOnTerminatedInstances() throws Exception {
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//*[text()='Terminated Instances']//preceding::div[1]"))))
				.click();
		Thread.sleep(2000);
	}

	public void clickOnClose() throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()=' Close ']")))).click();
	}

	public void deleteWorker(String appAndWorkerName) throws InterruptedException {
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(
				By.xpath("//*[normalize-space(text())='" + appAndWorkerName + "']//following::button[2]")))).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()=' Yes ']")))).click();
	}

	public void deleteWorkerCancelButton(String appAndWorkerName) throws InterruptedException {
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath("//*[starts-with(text(),' Copyright ')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(
				By.xpath("//*[normalize-space(text())='" + appAndWorkerName + "']//following::button[2]")))).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='No']")))).click();
	}

	public void workerlchAndCountChecking()
			throws IOException, InterruptedException, HeadlessException, UnsupportedFlavorException, AWTException {
		ApplicationView av = new ApplicationView(driver);
		PipelineView pv = new PipelineView(driver);
		ReportHelper rp = new ReportHelper(driver);
		PipelineHelper ph = new PipelineHelper(driver);
		WorkerHelper wh = new WorkerHelper(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8000));
		String workerName = excelRead(1);
		String applicationName = excelRead(0);
		String WorkerNames = applicationName + "_" + workerName;
		String pipelineName = "pipeline" + workerName;
		addWorker(workerName);
		downloadWorker();
		agentRunning(WorkerNames);
		workerPageRefresh();
		instanceVerifycation("IdleInstance", WorkerNames, "0", "1");
		ph.navigateToPipeline();
		ph.navigateToVisualEditor();
		ph.createPipelineName(applicationName, pipelineName);
		Thread.sleep(2000);
		ph.addStageInPipeline("stage1", "qa", WorkerNames);
		ph.createStepeForTimeout();
		ph.submitPipeline();
		ph.triggerPipelineUsingIcon();
		ph.workerSelectionInPipelineTrigger(WorkerNames);
		ph.triggerPipelineUsingButton();
		ph.pipelineTriggerVerificaton();
		ph.pipelineRunningVerifycation(pipelineName, WorkerNames);
		driver.navigate().refresh();
		String applicationNames = wh.excelRead(0);
		av.openApplicationDashboard(applicationName);
		instanceVerifycation("ActiveInstance", WorkerNames, "1", "0");
		deleteWorker(WorkerNames);
		delectFiles("agent");
		delectFiles(WorkerNames);
		delectFiles("worker.lock");
	}

	public static void delectFiles(String fileName) throws IOException, InterruptedException {
		File[] file_Details = new File(System.getProperty("user.dir") + "\\Data\\").listFiles();
		for (File file : file_Details) {
			if (file.getName().contains(fileName)) {
				file.deleteOnExit();
				if (file.getName().contains("agent")) {
					log.info("File is successfully deleted!");
				} else {
					log.info("Sorry, the file was not deleted.");
				}
			}
		}
	}

	public static boolean delectFilesDataFolder(String fileName) throws IOException, InterruptedException {
		File[] file_Details = new File(System.getProperty("user.dir") + "\\Agent\\").listFiles();
		boolean flag = false;
		for (File file : file_Details) {
			if (file.getName().contains(fileName)) {
				Thread.sleep(2000);
				FileUtils.forceDelete(file);
				Thread.sleep(2000);
				log.info("File is successfully deleted!");
				flag = true;
				File[] file_Details1 = new File(System.getProperty("user.dir") + "\\Agent\\").listFiles();
				for (File file2 : file_Details1) {
					if (file2.getName().contains(fileName)) {
						log.info("Sorry, the file was not deleted.");
						flag = false;
					} else {
						log.info("Verified the File is successfully deleted!");
					}
				}
			}
		}
		return flag;
	}

	public static boolean delectFilesAgentFolder(String fileName) throws IOException, InterruptedException {
		File[] file_Details = new File(System.getProperty("user.dir") + "\\" + fileName + "").listFiles();
		boolean flag = false;
		for (File file : file_Details) {
			if (file.getName().contains(fileName)) {
				Thread.sleep(10000);

				FileUtils.forceDeleteOnExit(file);
				log.info("File is successfully deleted!");
				flag = true;
				File[] file_Details1 = new File(System.getProperty("user.dir") + "\\" + fileName + "").listFiles();
				for (File file2 : file_Details1) {
					if (file2.getName().contains(fileName)) {
						log.info("Sorry, the file was not deleted.");
						flag = false;
					} else {
						log.info("Verified the File is successfully deleted!");
					}
				}
			}
		}
		return flag;
	}

	public static boolean delectFilescustom(String filepath, String fileName) throws IOException, InterruptedException {
		File[] file_Details = new File(System.getProperty("user.dir") + filepath).listFiles();
		boolean flag = false;
		for (File file : file_Details) {
			String names = System.getProperty("user.dir");
			String rem = names.replace(":\\", "").replace("\\", "");
			String[] name = file.getName().split(rem);
			if (name[0].contains(fileName)) {
				FileUtils.forceDelete(file);
				File[] file_Details1 = new File(System.getProperty("user.dir") + "\\" + filepath).listFiles();
				for (File file2 : file_Details1) {
					String[] name2 = file2.getName().split(rem);
					if (name2[0].contains(fileName)) {
						log.info("Sorry, the file was not deleted.");
						flag = false;
					} else {
						log.info("Verified the File is successfully deleted!");
						flag = true;
						break;
					}
				}
			}
		}
		return flag;
	}

	public static void deleteFileInLinuxMachine(String fileNameStartwith) throws Exception {

		ArrayList<String> agentName = linuxArraylistFilereturn("ls Satheesh");
		String path = System.getProperty("user.dir") + "//Data//testout.txt";
		String agentNames = null;
		for (String string : agentName) {
			agentNames = string;
		}
		WriteNote(path, agentNames);
		ArrayList<String> values = notepadReader(path, fileNameStartwith);
		for (String string : values) {
			log.info(string);
			linux("cd Satheesh && rm " + string + "");
		}
		Thread.sleep(5000);
		delectFilescustom("\\Data", "testout.txt");
	}

	public static ArrayList<String> linuxArraylistFilereturn(String command1) {
		String host = "VQAUPNASZ02";
		String users = "cHJvamFkbWlu";
		String passwords = "SWRwQDIwMjA=";

		String user = decode(users);
		String password = decode(passwords);
		String value = null;
		ArrayList<String> ls = new ArrayList<String>();
		try {
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			JSch jsch = new JSch();
			com.jcraft.jsch.Session session = jsch.getSession(user, host, 22);
			session.setPassword(password);
			session.setConfig(config);
			session.connect();
			log.info("Connected");
			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(command1);
			channel.setInputStream(null);
			((ChannelExec) channel).setErrStream(System.err);
			InputStream in = channel.getInputStream();
			channel.connect();

			byte[] tmp = new byte[1024];
			while (true) {
				while (in.available() > 0) {
					int i = in.read(tmp, 0, 1024);
					if (i < 0)
						break;
					value = new String(tmp, 0, i);
					ls.add(value);
				}
				if (channel.isClosed()) {
					log.info("exit-status: " + channel.getExitStatus());
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (Exception ee) {
				}
			}
			channel.disconnect();
			session.disconnect();
			log.info("DONE");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ls;
	}

	public static ArrayList<String> notepadReader(String filePath, String startswith) throws Exception {
		File file = new File(filePath);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		ArrayList<String> agentList = new ArrayList<String>();
		while ((st = br.readLine()) != null) {
			if (st.startsWith(startswith)) {
				agentList.add(st);
				log.info("Yes! Data is there");
				log.info("Data is exits");
			}
		}
		br.close();
		return agentList;
	}

	public static void WriteNote(String path, String value) throws InterruptedException, IOException {

		FileWriter fw = new FileWriter(path);
		fw.write(value);
		fw.close();
	}

	public static boolean delectFilecustomsAgentFolder(String fileName, String folderName)
			throws IOException, InterruptedException {
		File[] file_Details = new File(System.getProperty("user.dir") + "\\Agent\\").listFiles();
		boolean flag = false;
		for (File file : file_Details) {
			if (file.getName().contains(fileName)) {
				Thread.sleep(2000);
				FileUtils.forceDeleteOnExit(file);
				Thread.sleep(2000);
				log.info("File is successfully deleted!");
				flag = true;
				File[] file_Details1 = new File(System.getProperty("user.dir") + "\\Agent\\").listFiles();
				for (File file2 : file_Details1) {
					if (file2.getName().contains(fileName)) {
						log.info("Sorry, the file was not deleted.");
						flag = false;
					} else {
						log.info("Verified the File is successfully deleted!");
					}
				}
			}
		}
		return flag;
	}

	public static Boolean verifyfileExitsOrNot(String fileName) throws IOException, InterruptedException {

		log.info("This is the file name ==> " + fileName);
		Boolean flag = false;
		File[] file_Details = new File(System.getProperty("user.dir") + "\\Data\\").listFiles();
		for (File file : file_Details) {
			if (file.getName().contains(fileName)) {
				log.info("File is Exits");
				flag = true;
				break;
			} else {
				log.info("Sorry,File is not Exits");
				flag = false;
			}
		}
		return flag;
	}

	public static boolean delectFilesFromAgentFolder(String fileName) throws IOException, InterruptedException {
		File[] file_Details = new File(System.getProperty("user.dir") + "\\Agent\\").listFiles();
		boolean flag = false;
		for (File file : file_Details) {
			if (file.getName().contains(fileName)) {
				flag = true;
				file.deleteOnExit();
				if (file.getName().contains("agent")) {
					log.info("File is successfully deleted!");
				} else {
					log.info("Sorry, the file was not deleted.");
				}
			} else {
				flag = false;
			}
		}
		return flag;
	}

	public static String currentDatePickers() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		return date;
	}

	public static String currentDatePickers1() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddHHmms");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		return date;
	}

	public static void workerExcelWriter() throws IOException {
		String path = System.getProperty("user.dir") + "//Data//WorkerExpected.xlsx";
		Workbook wb = new XSSFWorkbook();
		FileOutputStream fs = new FileOutputStream(path);
		Sheet sheet1 = wb.createSheet("sheet1");

		String[] data = { "Application", "worker", "pipeline", "portfolioName" };

		Random ra = new Random();
		int n = ra.nextInt(5000);

		String applicationName = "WorkerAutomation";
		String workerName = "Worker" + currentDatePickers1();
		String PipelineName = "pipeline" + workerName;
		String portfolioName = "Automation_Functional_test";

		String[] data1 = { applicationName, workerName, PipelineName, portfolioName };

		Row thirdrow = sheet1.createRow(0);
		for (int i = 0; i < data.length; i++) {
			thirdrow.createCell(i).setCellValue(data[i]);
		}
		Row thirdrow1 = sheet1.createRow(1);
		for (int i = 0; i < data1.length; i++) {
			thirdrow1.createCell(i).setCellValue(data1[i]);
		}

		wb.write(fs);
		wb.close();
		fs.close();
	}

	public static String excelRead(int value) throws IOException {

		String path = System.getProperty("user.dir") + "//Data//WorkerExpected.xlsx";

		FileInputStream file = new FileInputStream(path);

		XSSFWorkbook wbook = new XSSFWorkbook(file);

		XSSFSheet sheet1 = wbook.getSheetAt(0);

		int row = sheet1.getLastRowNum() - sheet1.getFirstRowNum();

		for (int i = 1; i <= row; i++) {
			Application = sheet1.getRow(i).getCell(value).getStringCellValue();
		}
		wbook.close();
		return Application;
	}

	public static String readparticularRowCol(int rownumber, int col) throws IOException {

		String path = System.getProperty("user.dir") + "//Data//PluginData.xlsx";

		FileInputStream file = new FileInputStream(path);

		XSSFWorkbook wbook = new XSSFWorkbook(file);

		XSSFSheet sheet1 = wbook.getSheetAt(0);
		DataFormatter df = new DataFormatter();
		Application = df.formatCellValue(sheet1.getRow(rownumber).getCell(col));

		wbook.close();
		return Application;
	}

	public static void agentStoper(String agentname) throws IOException, InterruptedException, AWTException {
		Runtime rt = Runtime.getRuntime();
		rt.exec("taskkill /f /im " + agentname + "");
	}

	public static void textKill() throws IOException, InterruptedException, AWTException {
		Runtime rt = Runtime.getRuntime();
		rt.exec("taskkill /f /im testout.txt");
	}

	public void finalSteps(String agentName, String WorkerNames, String appAndPipName)
			throws IOException, InterruptedException, AWTException {
		WorkerHelper wh = new WorkerHelper(driver);
		wh.agentStoper(agentName);
		wh.deleteWorker(WorkerNames);
		wh.delectFilescustom("\\Data", "agent");
		wh.delectFilescustom("\\Data", "worker.lock");
		try {
			wh.delectFilescustom("\\Data", appAndPipName);
		} catch (IOException e) {
			assertTrue(true);
		}
	}

	public void startingSteps(String agentName) throws IOException, InterruptedException, AWTException {
		WorkerHelper wh = new WorkerHelper(driver);
		try {
			wh.agentStoper2(agentName);
			wh.delectFilescustom("\\Data", "agent");
			wh.delectFilescustom("\\Data", "worker.lock");
		} catch (Exception e) {
			try {
				Thread.sleep(20000);
				wh.agentStoper2(agentName);
				wh.delectFilescustom("\\Data", "agent");
				wh.delectFilescustom("\\Data", "worker.lock");
			} catch (Exception es) {
				log.info("its IO Exception");
			}
		}
	}

	public static void agentStoper3(String agentname) throws IOException, InterruptedException, AWTException {

		ArrayList<String> list = new ArrayList<String>();
		ProcessHandle.allProcesses().forEach(processHandle -> {
			list.add(processHandle.pid() + " " + processHandle.info().toString());
		});

		for (String string : list) {
			if (string.contains(System.getProperty("user.dir") + "\\Data\\" + agentname)) {
				log.info("its working");
				log.info("its working =======> " + string);
				String[] nn = string.split(",");
				String stringNew = nn[1].replace(" cmd: ", "");
				if (stringNew.equalsIgnoreCase(System.getProperty("user.dir") + "\\Data\\" + agentname)) {
					String[] nw = string.split(" ");
					log.info("its PID =======> " + nw[0]);
					Runtime rt = Runtime.getRuntime();
					rt.exec("taskkill /F /PID " + nw[0]);
					rt.exec("taskkill /F /PID " + nw[0]);
					if (string.contains("C:\\Windows\\System32\\cmd.exe")) {
						log.info(string);
						String[] nw1 = string.split(" ");
						if (nw1.equals(nw)) {
							log.info("its PID =======> " + nw[0]);
							rt.exec("taskkill /F /PID " + nw[0]);
							rt.exec("taskkill /F /PID " + nw[0]);
						}
					}
				}
			} else if (string.contains(System.getProperty("user.dir") + "\\Agent\\" + agentname)) {
				log.info("its working");
				log.info("its working =======> " + string);
				String[] nn = string.split(",");
				String stringNew = nn[1].replace(" cmd: ", "");
				if (stringNew.equalsIgnoreCase(System.getProperty("user.dir") + "\\Agent\\" + agentname)) {
					String[] nw = string.split(" ");
					log.info("its PID =======> " + nw[0]);
					Runtime rt = Runtime.getRuntime();
					rt.exec("taskkill /F /PID " + nw[0]);
					rt.exec("taskkill /F /PID " + nw[0]);
					if (string.contains("C:\\Windows\\System32\\cmd.exe")) {
						log.info(string);
						String[] nw1 = string.split(" ");
						if (nw1.equals(nw)) {
							log.info("its PID =======> " + nw[0]);
							rt.exec("taskkill /F /PID " + nw[0]);
							rt.exec("taskkill /F /PID " + nw[0]);
						}
					}
				}
			}
			if (string.contains("C:\\Windows\\System32\\cmd.exe")) {
				log.info("its working");
				log.info("its working =======> " + string);
				log.info(string);
				String[] nw = string.split(" ");
				log.info("its PID =======> " + nw[0]);

				Runtime rt = Runtime.getRuntime();
				rt.exec("taskkill /F /PID " + nw[0]);
				rt.exec("taskkill /F /PID " + nw[0]);
			}
		}
	}

	public static void agentStoper1(String agentname) throws IOException, InterruptedException, AWTException {

		ArrayList<String> list = new ArrayList<String>();
		ProcessHandle.allProcesses().forEach(processHandle -> {
			list.add(processHandle.pid() + " " + processHandle.info().toString());
		});

		for (String string : list) {
			if (string.contains(System.getProperty("user.dir") + "\\Data\\" + agentname)) {
				String[] nw = string.split(" ");
				Runtime rt = Runtime.getRuntime();
				rt.exec("taskkill /F /PID " + nw[0]);
				rt.exec("taskkill /F /PID " + nw[0]);
				log.info("Data folder Agent Stoped");
			} else if (string.contains(System.getProperty("user.dir") + "\\Agent\\" + agentname)) {
				String[] nw = string.split(" ");
				Runtime rt = Runtime.getRuntime();
				rt.exec("taskkill /F /PID " + nw[0]);
				rt.exec("taskkill /F /PID " + nw[0]);
				log.info("Agent folder Agent Stop Stoped");
			}
			if (string.contains("C:\\Windows\\System32\\cmd.exe")) {
				String[] nw = string.split(" ");
				Runtime rt = Runtime.getRuntime();
				rt.exec("taskkill /F /PID " + nw[0]);
				rt.exec("taskkill /F /PID " + nw[0]);
				log.info("CMD Closed");
			}
		}
	}

	public static void agentStoper2(String agentname) throws IOException, InterruptedException, AWTException {

		ArrayList<String> list = new ArrayList<String>();
		ProcessHandle.allProcesses().forEach(processHandle -> {
			list.add(processHandle.pid() + " " + processHandle.info().toString());
		});

		for (String string : list) {
			if (string.contains(System.getProperty("user.dir") + "\\Data\\" + agentname)) {
				String[] nn = string.split(",");
				String stringNew = nn[1].replace(" cmd: ", "");
				if (stringNew.equalsIgnoreCase(System.getProperty("user.dir") + "\\Data\\" + agentname)) {
					String[] nw = string.split(" ");
					Runtime rt = Runtime.getRuntime();
					rt.exec("taskkill /F /PID " + nw[0]);
					log.info("Data folder Agent Stoped");
				}
			} else if (string.contains(System.getProperty("user.dir") + "\\Agent\\" + agentname)) {
				String[] nn = string.split(",");
				String stringNew = nn[1].replace(" cmd: ", "");
				if (stringNew.equalsIgnoreCase(System.getProperty("user.dir") + "\\Agent\\" + agentname)) {
					String[] nw = string.split(" ");
					Runtime rt = Runtime.getRuntime();
					rt.exec("taskkill /F /PID " + nw[0]);
					// rt.exec("taskkill /F /PID " + nw[0]);
					log.info("Agent folder Agent Stop Stoped");
				}
			}

		}
	}

	public static void agentRunner(WebDriver driver, String myAgentpath, String filename)
			throws IOException, InterruptedException, AWTException {
		String path1 = System.getProperty("user.dir") + "\\Data\\";
		Runtime rt = Runtime.getRuntime();
		rt.exec("cmd.exe /c cd \"" + path1 + "\" & start cmd.exe /k \"" + myAgentpath + "\"");
	}

	public static void agentRunnerITAFFolder(String myAgentpath)
			throws IOException, InterruptedException, AWTException {
		String path1 = System.getProperty("user.dir") + "\\ITAF\\";
		Runtime rt = Runtime.getRuntime();
		rt.exec("cmd.exe /c cd \"" + path1 + "\" & start cmd.exe /k \"" + myAgentpath + "\"");
	}

	public static void agentRunnerAgentFolder(WebDriver driver, String myAgentpath)
			throws IOException, InterruptedException, AWTException {
		Thread.sleep(2000);
		String path1 = System.getProperty("user.dir") + "\\Agent\\";
		Runtime rt = Runtime.getRuntime();
		rt.exec("cmd.exe /c cd \"" + path1 + "\" & start cmd.exe /k \"" + myAgentpath + "\"");
	}

	public static void waitForTheExcelFileToDownload(WebDriver driver, String fileName)
			throws IOException, InterruptedException {
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8000));
			wait.until((x) -> {
				File[] file_Detail = new File(System.getProperty("user.dir") + "\\Data\\").listFiles();
				for (File file : file_Detail) {
					if (file.getName().contains(fileName)) {
						log.info("Agent Downloaded");
						return true;
					}
				}
				log.info("Agent not Downloaded");
				return false;
			});
		}
	}

	public void PlatformWorkerAgentNameFinder()
			throws InterruptedException, IOException, HeadlessException, UnsupportedFlavorException, AWTException {

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Download Worker ']")));
		Thread.sleep(4000);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Download Worker ']")))).click();
		Thread.sleep(5000);
		WebElement copy = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(
				"//*[contains(text(),'framework/windows')]//following::span[@class='mat-mdc-button-persistent-ripple mdc-icon-button__ripple']"))));
		log.info("Download Agent Popup Opened");
		Thread.sleep(5000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", copy);
		Thread.sleep(5000);
		Actions actions = new Actions(driver);
		actions.moveToElement(copy).doubleClick().build().perform();

		String myText = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
		String[] agentName = myText.split("/");
		agentNameis = agentName[8];
		startingSteps(agentNameis);
		wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Download Worker']//following::button")));
		driver.findElement(By.xpath("//*[text()='Download Worker']//following::button")).click();
	}

	public void platformWorkerPageRefresh() throws InterruptedException {
		Thread.sleep(8000);
		driver.navigate().refresh();
		log.info("After refresh");
		Thread.sleep(9000);
		log.info("Click on Arrow button");
		Thread.sleep(1000);
		log.info("Click on Worker tab");
		driver.findElement(By.xpath("//*[text()='Workers']"));
		doubleClick(driver.findElement(By.xpath("//*[text()='Workers']")));

		try {
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text()='Workers list']"))));
		} catch (NoSuchElementException e) {
			driver.navigate().refresh();
			Thread.sleep(9000);
			doubleClick(driver.findElement(By.xpath("//*[text()='Workers']")));
		}
		log.info("Double click on Worker Tab");
	}

	public void PlatformDownloadWorker()
			throws InterruptedException, IOException, HeadlessException, UnsupportedFlavorException, AWTException {

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=' Download Worker ']")));
		log.info("Download worker Popup Opened");
		Thread.sleep(4000);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Download Worker ']")))).click();
		Thread.sleep(5000);
		WebElement copy = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(
				"//*[contains(text(),'framework/windows')]//following::span[@class='mat-mdc-button-persistent-ripple mdc-icon-button__ripple']"))));
		Thread.sleep(5000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", copy);
		Thread.sleep(5000);
		Actions actions = new Actions(driver);
		actions.moveToElement(copy).doubleClick().build().perform();

		String myText = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
		String[] agentName = myText.split("/");
		agentNameis = agentName[8];
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.open('" + myText + "')");
		waitForTheExcelFileToDownload(driver, "agent");
		Thread.sleep(15000);
		wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Download Worker']//following::button")));
		driver.findElement(By.xpath("//*[text()='Download Worker']//following::button")).click();
		log.info("Agent downloaded successfully");
	}

}
