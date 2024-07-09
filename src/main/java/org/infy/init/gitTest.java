package org.infy.init;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.util.Base64;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class gitTest {

	public static void main(String[] args) throws InterruptedException {

		String url = "https://agtest.ad.infosys.com/portfoliosvc/webhook/Thanu_Application/SFDCIncremetalUpdated/general/Thanu_Application_SFDCIncremetalUpdated?worker=Thanu_Application_WebHooks&release=application:release001";
		String Updateurl = "https://agtest.ad.infosys.com/MS/webhook/Thanu_Application/SFDCIncremetalUpdated/general/Thanu_Application_SFDCIncremetalUpdated?worker=Thanu_Application_WebHooks&release=application:release001";

		String URLN = "https://agtest.ad.infosys.com/svc/webhook/WorkerAutomation/pipelineWorker0526125156/general/WorkerAutomation_pipelineWorker0526125156?worker=WorkerAutomation_Worker0526125156&release=Release Name *";
		createWebHook("satheshkumar-m_infosys", "WebHookTest_Sk", "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", URLN);
	}

	public static int createWebHook(String ownerName, String ProjectName, String token, String url)
			throws InterruptedException {
		String body = "{\r\n" + "    \"name\":\"web\",\r\n" + "    \"active\":true,\r\n"
				+ "    \"events\":[\"push\",\"pull_request\"],\r\n" + "    \"config\":{\r\n" + "        \"url\":\""
				+ url + "\",\r\n" + "        \"content_type\":\"json\",\r\n" + "        \"insecure_ssl\":1}\r\n"
				+ "        }";

		baseURI = "https://api.github.com/repos/" + ownerName + "/" + ProjectName + "/hooks";
		Response response = given().headers("Authorization", "Bearer " + token, "Accept", "application/vnd.github+json")
				.body(body).post("").then().extract().response();
		String str = response.asPrettyString();

		JsonPath js = new JsonPath(str);
		int idDetails = js.getInt("id");
		return idDetails;
	}

	public static void updateWebHook(String ownerName, String ProjectName, String token, String url, int id)
			throws InterruptedException {
		String body = "{\r\n" + "    \"name\":\"web\",\r\n" + "    \"active\":true,\r\n"
				+ "    \"events\":[\"push\",\"pull_request\"],\r\n" + "    \"config\":{\r\n" + "        \"url\":\""
				+ url + "\",\r\n" + "        \"content_type\":\"json\",\r\n" + "        \"insecure_ssl\":1}\r\n"
				+ "        }";

		baseURI = "https://api.github.com/repos/" + ownerName + "/" + ProjectName + "/hooks/" + id;

		Response response = given().headers("Authorization", "Bearer " + token, "Accept", "application/vnd.github+json")
				.body(body).post("").then().extract().response();
		String str = response.asPrettyString();
	}

	public static void deleteWebHook(String ownerName, String ProjectName, String token, String url, int id)
			throws InterruptedException {

		baseURI = "https://api.github.com/repos/" + ownerName + "/" + ProjectName + "/hooks/" + id;
		Response response = given().headers("Authorization", "Bearer " + token, "Accept", "application/vnd.github+json")
				.delete("").then().extract().response();
		String str = response.asPrettyString();
		int staus = response.getStatusCode();
	}

	public static String createNewFile(String ownerName, String ProjectName, String token, String fileNameWithExe,
			String commitMsg, String TextDetails) throws InterruptedException {

		String encodedString = Base64.getEncoder().encodeToString(TextDetails.getBytes());
		String body = "{\r\n" + "    \"message\": \"" + commitMsg + "\",\r\n" + "    \"content\": \"" + encodedString
				+ "\"\r\n" + " }";
		baseURI = "https://api.github.com/repos/" + ownerName + "/" + ProjectName + "/contents/" + fileNameWithExe + "";
		Response response = given().headers("Authorization", "Bearer " + token, "Accept", "application/vnd.github+json")
				.body(body).put("").then().extract().response();
		String str = response.asPrettyString();
		JsonPath js = new JsonPath(str);
		String shaIdDetails = js.getString("content.sha");
		return shaIdDetails;
	}

	public static void UpdateNewFile(String ownerName, String ProjectName, String token, String fileNameWithExe,
			String commitMsg, String TextDetails, String shaID) throws InterruptedException {

		String encodedString = Base64.getEncoder().encodeToString(TextDetails.getBytes());

		String body = "{\r\n" + "    \"message\": \"" + commitMsg + "\",\r\n" + "    \"content\": \"" + encodedString
				+ "\",\r\n" + "     \"sha\": \"" + shaID + "\"\r\n" + " }";
		baseURI = "https://api.github.com/repos/" + ownerName + "/" + ProjectName + "/contents/" + fileNameWithExe + "";

		Response response = given().headers("Authorization", "Bearer " + token, "Accept", "application/vnd.github+json")
				.body(body).put("").then().extract().response();

		String str = response.asPrettyString();
	}

	public static void DeleteFile(String ownerName, String ProjectName, String token, String DeletefileNameWithExe,
			String commitMsg, String shaID) throws InterruptedException {
		String body = "{\r\n" + "      \"message\": \"" + commitMsg + "\",\r\n" + "     \"sha\": \"" + shaID + "\"\r\n"
				+ "}";
		baseURI = "https://api.github.com/repos/" + ownerName + "/" + ProjectName + "/contents/" + DeletefileNameWithExe
				+ "";
		Response response = given().headers("Authorization", "Bearer " + token, "Accept", "application/vnd.github+json")
				.body(body).delete("").then().extract().response();
		String str = response.asPrettyString();
	}

}
