package org.infy.mongodbRead;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bson.Document;
import org.infy.commonHelpers.WorkerHelper;
import org.json.simple.JSONArray;

import com.google.gson.Gson;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;


public class MongoReadData {
	static String Application;
	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(MongoReadData.class);

	public static void main(String[] args) throws IOException, InterruptedException {

		String duration = "{$unwind:'$edata.data'}" + "{$match :{'object.identifiers.pipelinename':'MongoTest'}}" + ","
				+ "{$match :{'object.identifiers.applicationname':'WorkerAutomation'}}"
				+ ",{$project : {'edata.data.duration':1}}";

		String applicationName = WorkerHelper.excelRead(0);
		String workerName = excelRead(1);
		String WorkerNames = applicationName + "_" + workerName;

		String pathstatus = "{$unwind:'$object.identifiers'},{$match :{'object.identifiers.pipelinename':'MongoTest'}},{$match :{'object.identifiers.applicationname':'WorkerAutomation'}},{$project : {'eid':'PIPELINE_EXECUTION_STATUS'}}";

		String paths = "{$match:{'name':'" + WorkerNames + "'" + "$unwind:'$instances'},"
				+ "{$project : {'instances.lsActive':1}}";

		MongoReadData MDB = new MongoReadData();
		ArrayList<String> value = MDB.particularDataFetchFromMongoDBUsingQuery("platform_telemetry", paths);

		for (String string : value) {
			log.info(string);
		}
	}

	public static Document docCreater(String value) {
		Document query1 = Document.parse(value);
		return query1;
	}

	public static String decode(String token) {
		byte[] decodedBytes = Base64.decodeBase64(token);
		String TokenDetails = new String(decodedBytes);
		return TokenDetails;
	}
	public static ArrayList<String> particularDataFetchFromMongoDBUsingQuery(String collectionName, String[] querys) {
		 
		String connectionString = "mongodb+srv://agtestadmin:Test%40gc0re%40221@devtestcluster-pl-0.sitxo.mongodb.net/?authMechanism=SCRAM-SHA-1";
		MongoClient client = MongoClients.create(connectionString);
		MongoDatabase db = client.getDatabase("agtest");
		MongoCollection<Document> dct = db.getCollection(collectionName);
		ArrayList<Document> doc = new ArrayList<Document>();
		for (String lisOfValue : querys) {
			doc.add(docCreater(lisOfValue));
		}
		AggregateIterable<Document> iterable = dct.aggregate(doc);
		ArrayList<String> lis = null;
		MongoCursor<Document> co = iterable.iterator();
		String Data = null;
		while (co.hasNext()) {
			String newDetails = co.next().toJson().toString();
			String[] splitNewOne = newDetails.split(",");
			lis = new ArrayList<String>();
			for (int i = 0; i < splitNewOne.length; i++) {
				Data = splitNewOne[i];
				lis.add(Data);
			}
			break;
		}
		return lis;
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
 
//	public static ArrayList<String> particularDataFetchFromMongoDBUsingQuery(String collectionName, String paths) {
//		String password = decode("aWRwYWRtaW5AMTIz");
//		MongoCredential cl = MongoCredential.createScramSha1Credential("idpadmin@infosys.com", "admin",
//				password.toCharArray());
//		MongoClient client = new MongoClient(new ServerAddress("10.66.155.104", 32647), Arrays.asList(cl));
//		MongoDatabase db = client.getDatabase("idp");
//		MongoCollection<Document> dct = db.getCollection(collectionName);
//		Document query = Document.parse(paths);
//		List<Document> setOfQuerys = Arrays.asList(query);
//		AggregateIterable<Document> iterable = dct.aggregate(setOfQuerys);
//		ArrayList<String> lis = null;
//		MongoCursor<Document> co = iterable.iterator();
//		String Data = null;
//		while (co.hasNext()) {
//			String newDetails = co.next().toJson().toString();
//			String[] splitNewOne = newDetails.split(",");
//			lis = new ArrayList<String>();
//			for (int i = 0; i < splitNewOne.length; i++) {
//				Data = splitNewOne[i];
//				lis.add(Data);
//			}
//			break;
//		}
//		return lis;
//	}
 
	public static ArrayList<String> particularDataFetchFromMongoDBUsingQuery(String collectionName, String paths)
			throws InterruptedException {
		Thread.sleep(1000);
		// Connection string for MongoDB Atlas
		String connectionString = "mongodb+srv://agtestadmin:Test%40gc0re%40221@devtestcluster-pl-0.sitxo.mongodb.net/?authMechanism=SCRAM-SHA-1";
		MongoClient client = MongoClients.create(connectionString);
		MongoDatabase db = client.getDatabase("agtest");
		MongoCollection<Document> dct = db.getCollection(collectionName);
		Document query = Document.parse(paths);
		List<Document> setOfQuerys = Arrays.asList(query);
		AggregateIterable<Document> iterable = dct.aggregate(setOfQuerys);
		ArrayList<String> lis = new ArrayList<>();
		MongoCursor<Document> co = iterable.iterator();
		while (co.hasNext()) {
			String newDetails = co.next().toJson();
			String[] splitNewOne = newDetails.split(",");
			for (String Data : splitNewOne) {
				lis.add(Data);
			}
			break; // This will only process the first document. Remove this if you want to process
					// all documents.
		}
		client.close(); // Don't forget to close the client
		return lis;
	}
 
	public static String mongoDBDataVerification(ArrayList<String> value, String keyValue, String verifyValue) {
		String data = null;
		String returnValues = null;
		for (String string : value) {
			String ne = string.replaceAll("[(){}]", "");
			String str1 = ne.replace("\"", "");
			String[] dat = ne.split(":");
			String k = dat[0];
			String v = dat[1];
			HashMap<String, String> kvvalue = new HashMap<String, String>();
			kvvalue.put(k, v);
			Set<String> nn = kvvalue.keySet();
			String newValue = nn.toString().replace("[", "").replace("]", "").replace("}", "").replace("\"", "").trim();
 
			if (newValue.equalsIgnoreCase(keyValue)) {
				data = kvvalue.get(k);
				String newValue1 = data.replace("\"", "").trim();
				log.info(newValue1);
 
				if (newValue1.equalsIgnoreCase(verifyValue)) {
					log.info("Yes! Data is there");
					log.info("Data is exits");
					returnValues = newValue1;
					break;
				}
			} else {
				log.info("sorry! Data not there");
 
			}
		}
		return returnValues;
	}
 
	public static String mongoTimeStempConver(String value) {
		long varLong = Long.parseLong(value.trim());
		Timestamp ts = new Timestamp(varLong);
		Date date = new Date(ts.getTime());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(date);
		return strDate;
	}
 
	public static String mongoTimeStempConverters(long value) {
		Timestamp ts = new Timestamp(value);
		Date date = new Date(ts.getTime());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyyHH:mm");
		String strDate = formatter.format(date);
		return strDate;
	}
 
	public static String mongoTimeStempConverwithTime(String value) {
		long varLong = Long.parseLong(value.trim());
		Timestamp ts = new Timestamp(varLong);
		Date date = new Date(ts.getTime());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyyHH:mm");
		String strDate = formatter.format(date);
		return strDate;
	}
 
	public static String currentDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String currentString = dateFormat.format(date);
		return currentString;
	}
 
	public static String mongoDBDataVerification1(ArrayList<String> value, String keyValue, int places) {
		String data = null;
		String returnValues = null;
		for (String string : value) {
			String ne = string.replaceAll("[(){}]", "");
			String str1 = ne.replace("\"", "");
			String[] dat = str1.split(":");
			String k = dat[0];
			String v = dat[1];
			HashMap<String, String> kvvalue = new HashMap<String, String>();
			kvvalue.put(k, v);
			Set<String> nn = kvvalue.keySet();
			String newValue = nn.toString().replace("[", "").replace("]", "").replace("}", "").trim();
 
			if (newValue.equalsIgnoreCase(keyValue)) {
				String nns = string.replace(":", "").replace("{", "").replace("}", "");
				log.info(nns);
 
				String[] inActive = nns.split(" ");
				data = inActive[places];
			}
		}
		return data;
	}
 
	public static String mongoDBDataVerification2(ArrayList<String> value, String keyValue, int places) {
		String data = null;
		String returnValues = null;
		for (String string : value) {
			String ne = string.replaceAll("[(){}]", "");
			String str1 = ne.replace("\"", "");
			String[] dat = str1.split(":");
			String k = dat[0];
			String v = dat[1];
			HashMap<String, String> kvvalue = new HashMap<String, String>();
			kvvalue.put(k, v);
			Set<String> nn = kvvalue.keySet();
			String newValue = nn.toString().replace("[", "").replace("]", "").replace("}", "").trim();
 
			if (newValue.equalsIgnoreCase(keyValue)) {
				String nns = string.replace(":", "").replace("{", "").replace("}", "");
				log.info(nns);
 
				String[] inActive = nns.replace("]", "").split(" ");
				data = inActive[places];
			}
		}
		return data;
	}
 
	public static String particularDataFetchFromMongoDB(String applicationNames, String pipelineNames,
			String stageNames, String stepNames) {
		String connectionString = "mongodb+srv://agtestadmin:Test%40gc0re%40221@devtestcluster-pl-0.sitxo.mongodb.net/?authMechanism=SCRAM-SHA-1";
		MongoClient client = MongoClients.create(connectionString);
		MongoDatabase db = client.getDatabase("agtest");
		MongoCollection<Document> dct = db.getCollection("applications");
		Document applicatoinName = Document.parse("{$match:{'name':'" + applicationNames + "'}}");
		Document pipelines = Document.parse("{$unwind:'$pipelines'}");
		Document pipelineName = Document.parse("{$match:{'pipelines.name':'" + pipelineNames + "'}}");
		Document stagess = Document.parse("{$unwind:'$pipelines.stages'}");
		Document stageName = Document.parse("{$match:{'pipelines.stages.name':'" + stageNames + "'}}");
		Document steps = Document.parse(" {$unwind:'$pipelines.stages.steps'}");
		Document stepName = Document.parse("{$match:{'pipelines.stages.steps.name':'" + stepNames + "'}}");
		Document finalStep = Document.parse("{ $project : { \"pipelines.stages.steps\": 1 }}");
 
		List<Document> pipeline = Arrays.asList(applicatoinName, pipelines, pipelineName, stagess, stageName, steps,
				finalStep);
		AggregateIterable<Document> iterable = dct.aggregate(pipeline);
 
		MongoCursor<Document> co = iterable.iterator();
		String Data = null;
		while (co.hasNext()) {
			String newDetails = co.next().toJson().toString();
 
			String[] splitNewOne = newDetails.split(",");
 
			for (int i = 0; i < splitNewOne.length; i++) {
				log.info(splitNewOne[i]);
				Data = splitNewOne[i];
			}
		}
		return Data;
	}
 
	public static void com(String Objectss) {
		JSONArray employeeList = new JSONArray();
		employeeList.add(Objectss);
 
		String path = System.getProperty("user.dir") + "\\Data\\companies.json";
 
		try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
			Gson gson = new Gson();
			String jsonString = gson.toJson(employeeList);
			out.write(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}}
