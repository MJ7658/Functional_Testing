package org.infy.init;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class ReadConfigFile {

	public static String env = "";
	public static String browser = "";
	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(ReadConfigFile.class);

	public static void readXML() throws ParserConfigurationException, SAXException, IOException {
		File file = new File(Constants.CONFIG_FILE);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(file);
		doc.getDocumentElement().normalize();
		log.info("Root element: " + doc.getDocumentElement().getNodeName());
		env = doc.getElementsByTagName("env").item(0).getTextContent();
		browser = doc.getElementsByTagName("browser").item(0).getTextContent();
		log.info(env + " " + browser);
	}

}
