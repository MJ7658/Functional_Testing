package org.idp.workermanagement;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.apache.commons.codec.binary.Base64;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class WorkerDelete {

	public static void main(String[] args) {

		createIssue();
		// uploadImage();
	}

	public static String decode(String token) {
		byte[] decodedBytes = Base64.decodeBase64(token);
		String TokenDetails = new String(decodedBytes);
		return TokenDetails;
	}

	public static void createIssue() {

		String tokens = decode(
				"ZXlKaGJHY2lPaUpTVXpVeE1pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SmxiV0ZwYkNJNkluWnBaMjVsYzJndWJXcEFhVzVtYjNONWN5NWpiMjBpTENKbGVIQWlPakUzTWpFeE5UUTJNREFzSW1saGRDSTZNVGN4T0RZeU16RTNNeXdpYm1GdFpTSTZJa1oxYm1OMGFXOXVJbjAubXV6YjlFQzR6TkhGZ214blVnS1pheWJmdGwzMXVKd1dQbDljY2toMXVQcEFHSElpOGVRbjQ2ZmhzczVSSkU5U1pnWmZUOTBvRS1IQVZVOVA1cVhjTTlRQk5pMEFkaWM4TFlseWs1UlhNLTFQS3cwZEU4cm5jUUVST04zUWJzU3FTcF9BOFZ4ektzR0hkNkpwOXdhOGlpSFdteXVORHloVFR1SDlzanRGLW9n");
		String path = "{\"query\":\"{\\n              agentInfo(level:\\\"application\\\",resource:\\\"WorkerAutomation\\\"){\\n                  name,\\n                  level,\\n                  resource,\\n                  token\\n                  tokenGeneratedAt\\n                  instances{\\n                    instanceid,\\n                    lsActive,\\n                    noOfAvailableExecutor,\\n                    noOfActiveJobs,\\n                    prodMode,\\n                    workspaceDir,\\n                    workerUpSince,\\n                    systemInfo{\\n                      os,\\n                      memoryUsage,\\n                      cpuUsage,\\n                      diskUsage,\\n                      hostname,\\n                      cpuCores\\n                    }\\n                  }\\n              }\\n       }\\n  \"}";
		baseURI = "https://agtest-tools.infosysapps.com/portfoliosvc";
		Response response = given().relaxedHTTPSValidation().headers("Content-Type", ContentType.JSON)
				.headers("Authorization", tokens).body(path).post("/graphql").then().extract().response();
		int status = response.statusCode();
		System.out.println(status);
		String value = response.asPrettyString();
		JsonPath js = new JsonPath(value);

		for (int i = 0; i >= 0; i++) {
			String testCycle = js.get("data.agentInfo[" + i + "].name");
			if (testCycle == null) {
				break;
			} else if (testCycle.startsWith("WorkerAutomation_Worker")) {
				System.out.println("WorkerName ====> " + testCycle);

				String Deletepath = "{\"query\":\"\\n       mutation {\\n        agent(operation:\\\"delete\\\",name:\\\""
						+ testCycle
						+ "\\\", resource:\\\"WorkerAutomation\\\",level:\\\"application\\\"){\\n           name\\n        }\\n      }\\n    \"}";
				baseURI = "https://agtest-tools.infosysapps.com/portfoliosvc";
				Response response1 = given().relaxedHTTPSValidation().headers("Content-Type", ContentType.JSON)
						.headers("Authorization", tokens).body(Deletepath).post("/graphql").then().extract().response();
			}
		}
	}
}
