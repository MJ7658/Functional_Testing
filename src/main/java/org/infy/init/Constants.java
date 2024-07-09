package org.infy.init;

public class Constants {
	public static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
	public static final String CHROME_DRIVER_PATH = CURRENT_DIRECTORY + "/WebDrivers/chromedriver.exe";
	public static final String IE_DRIVER_PATH = CURRENT_DIRECTORY + "/WebDrivers/IEDriverServer.exe";
	public static final String GECKO_DRIVER_PATH = CURRENT_DIRECTORY + "/WebDrivers/geckodriver.exe";
	public static final String MSEDGE_DRIVER_PATH = CURRENT_DIRECTORY + "/WebDrivers/msedgedriver.exe";
	public static final String ERROR_SCREENSHOTS_DIR = CURRENT_DIRECTORY + "/test-output/ErrorScreenshots/";
	public static final String EXTENT_REPORT_DIR = CURRENT_DIRECTORY + "/test-output/ExtentReport/";
	public static final String CONFIG_FILE = CURRENT_DIRECTORY + "/config.xml";
	public static final String LOGINDETAILS_JSON_PATH = CURRENT_DIRECTORY + "/InputData/LoginDetails.json";
	public static final String INPUTPIPELINEDATA_JSON_PATH = CURRENT_DIRECTORY + "/InputData/InputPluginData.json";
	public static final String INPUTAPPLICATIONDATA_JSON_PATH = CURRENT_DIRECTORY + "/InputData/ApplicationData.json";
	public static final String TRIGGERPIPELINEDATA_JSON_PATH = CURRENT_DIRECTORY + "/InputData/triggerDetails.json";
	public static final String PORTFOLIODATA_JSON_PATH = CURRENT_DIRECTORY + "/InputData/PortfolioData.json";
	public static final String INPUT_PARAMETERS_DATA_JSON_PATH = CURRENT_DIRECTORY + "/InputData/ParametersData.json";
	public static final String APPLICATION_WORKFLOWS_DATA_JSON_PATH = CURRENT_DIRECTORY
			+ "/InputData/ApplicationWorkflowsData.json";
	public static final String REPORT_DOWNLOADS_PATH = "C:\\Users\\nagarajan.palavesam\\Downloads";

}
