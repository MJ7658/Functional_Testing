package org.infy.uiPages.plugins;

import org.apache.logging.log4j.LogManager;
import org.infy.uiPages.CommonMethods;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;

public class InputPluginDetails {
	private WebDriver driver;
	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(InputPluginDetails.class);

	public InputPluginDetails(WebDriver driver) {
		this.driver = driver;
	}

	public void enterStepDetails(JSONObject object, String[] inputParameter) {
		CommonMethods cmon = new CommonMethods(driver);
		JSONObject fields = (JSONObject) object.get("pluginInput");
		log.info("===== Started entering Step details for plugin =========");
		for (int i = 0; i < inputParameter.length; i++) {
			String[] inputfields = inputParameter[i].split(":");
			log.info(inputfields[0] + " " + inputfields[1]);
			if (inputfields[1].equals("input")) {
				cmon.enterFieldDetails(inputfields[0], fields.get(inputfields[0]).toString());
			} else if (inputfields[1].equals("dropdown")) {
				cmon.selectFieldfromDropDown(inputfields[0], fields.get(inputfields[0]).toString());
			}
			log.info("****** Step details for plugin has been entered *******");
		}

	}
}
