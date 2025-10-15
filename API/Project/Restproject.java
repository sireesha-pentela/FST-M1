package project;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import org.testng.Reporter;
import static org.hamcrest.CoreMatchers.equalTo;
import java.util.HashMap;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Restproject {
	
	RequestSpecification requestSpec;
	ResponseSpecification responseSpec;
	int Id;
	String SSH = "ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAIPwW7aRMR7K0JkEFp7tz3JoBOdERZgKdsl1GpWojrvVK" ;
	
	
	// This method runs once before any test methods in the class
	@BeforeClass
	public void setup() {

	    // Setting up the request specification
	    // Base URI points to the Pet endpoint of Swagger Petstore API
	    requestSpec = new RequestSpecBuilder()
	            .setBaseUri("https://api.github.com/user/keys") // Base URL for API requests
	            .addHeader("Content-Type", "application/json")     // Setting header to send JSON data
	            .addHeader("Authorization", "Bearer <YOUR-TOKEN>")
	            .addHeader("X-GitHub-Api-Version", "2022-11-28")
	            .build();                                          // Finalizing the request specification

	    // Setting up the response specification
	    responseSpec = new ResponseSpecBuilder()
	            .expectBody("title", equalTo("TestKey"))        
	            .expectBody("key", equalTo(SSH))
	            .build();                                       
	}
	
	//POST
	@Test(priority = 1)
	public void postRequestTest() {
	    // Creating a request body using a HashMap
	    // This will be converted to JSON and sent in the POST request
	    HashMap<String, Object> reqBoby = new HashMap<>();

	    // Adding key-value pairs to represent pet details
	    reqBoby.put("title", "TestKey");           
	    reqBoby.put("key", SSH); 
	    
	    // Sending the POST request to the API using the request specification
	    Response response = 
	            given().spec(requestSpec)   // Using predefined request spec (base URI and headers)
	            .body(reqBoby)              // Attaching the request body
	            .when().post();             // Making the POST request

	    // Extracting the 'id' from the response and storing it in a class-level variable
	    this.Id = response.then().extract().path("id");

	    // Validating the response using the predefined response specification
	    // This checks status code, body content, and response time
	    response.then().spec(responseSpec);
	}
	
	//GET
	@Test(priority = 2)
	public void getRequestTest() {

	    Response response = 
	        given()
	            .spec(requestSpec)
	            .pathParam("keyId", this.Id)
	        .when()
	            .get("/{keyId}");

	    // Validate response
	    response.then()
	            .spec(responseSpec)
	            .statusCode(200);

	    // Log response body to TestNG report
	    Reporter.log("GET Response Body: " + response.getBody().asString());
	    
	    System.out.println("GET Response Body: " + response.getBody().asPrettyString());
	}
	
	//DELETE
	@Test(priority = 3)
	public void deleteRequestTest() {

	    Response response = 
	        given()
	            .spec(requestSpec)
	            .pathParam("keyId", this.Id)
	        .when()
	            .delete("/{keyId}");

	    // Validate status code
	    response.then().statusCode(204);

	    // Log response to TestNG report
	    Reporter.log("DELETE Response Body: " + response.getBody().asString());
	    
	    
	}
	
}
