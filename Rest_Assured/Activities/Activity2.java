package Activities;

import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import static org.hamcrest.CoreMatchers.equalTo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Activity2 {
	
	RequestSpecification requestSpec;
	ResponseSpecification responseSpec;
	
	int petID;
	
	@Test(priority = 1)
	public void addNewUserFromFile() throws IOException {
		
		FileInputStream jsonFile  = new FileInputStream("src/test/resources/userInfo.json");
		
		Response response = 
				given().
				    baseUri("https://petstore.swagger.io/v2/user").
				    headers("Content-Type", "application/json").
				    body(jsonFile).
				    log().all().
				when().
				    post();
		
		jsonFile.close();
		
		response.then().body("code", equalTo(200));
		response.then().body("message", equalTo("12344"));
	}
	
	@Test(priority = 2)
	public void getuser() throws IOException {
		
		File outputJSON = new File("src/test/resources/output.json");
		
		Response response =
				given().
					baseUri("https://petstore.swagger.io/v2/user").
					headers("Document-Type", "application/json").
					pathParam("username", "sireesha").
					log().all().
				when().
					get("/{username}");
				
		String resJson = response.getBody().asPrettyString();
		
		outputJSON.createNewFile();
		
		FileWriter writer = new FileWriter(outputJSON);
		
		writer.write(resJson);
		
		writer.close();
		
		// Assertion
		response.then().body("id", equalTo(12344));
		response.then().body("username", equalTo("sireesha"));
		response.then().body("firstName", equalTo("Justin"));
		response.then().body("lastName", equalTo("Case"));
		response.then().body("email", equalTo("justincase@mail.com"));
		response.then().body("password", equalTo("password123"));
		response.then().body("phone", equalTo("7095035263"));		
			
	}
		
		
	@Test(priority = 3)
	public void deleteUser() {
		
		Response response =
				given().
					baseUri("https://petstore.swagger.io/v2/user").
					headers("Content-Type", "application/json").
					pathParam("username", "sireesha").
				when().
					delete("/{username}");
		
		response.then().body("code", equalTo(200));
		response.then().body("message", equalTo("sireesha"));
	}
		


	
}
