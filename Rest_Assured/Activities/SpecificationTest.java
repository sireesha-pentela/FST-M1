package Examples;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import java.util.HashMap;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecificationTest {
	
	RequestSpecification requestSpec;
	ResponseSpecification responseSpec;
	int petId;
	
	// This method runs once before any test methods in the class
	@BeforeClass
	public void setup() {

	    // Setting up the request specification
	    // Base URI points to the Pet endpoint of Swagger Petstore API
	    requestSpec = new RequestSpecBuilder()
	            .setBaseUri("https://petstore.swagger.io/v2/pet") // Base URL for API requests
	            .addHeader("Content-Type", "application/json")     // Setting header to send JSON data
	            .build();                                          // Finalizing the request specification

	    // Setting up the response specification
	    responseSpec = new ResponseSpecBuilder()
	            .expectStatusCode(200)                            // Expecting HTTP status code 200 (OK)
	            .expectBody("status", equalTo("alive"))           // Expecting the 'status' field in response to be 'alive'
	            .expectResponseTime(lessThanOrEqualTo(5000L))     // Expecting response time ≤ 5000 milliseconds (L = Long type)
	            .build();                                         // Finalizing the response specification
	}
	
	//POST
	@Test(priority = 1)
	public void postRequestTest() {
	    // Creating a request body using a HashMap
	    // This will be converted to JSON and sent in the POST request
	    HashMap<String, Object> reqBoby = new HashMap<>();

	    // Adding key-value pairs to represent pet details
	    reqBoby.put("id", 12377);           // Unique ID for the pet
	    reqBoby.put("name", "puppy1");      // Name of the pet
	    reqBoby.put("status", "alive");     // Status of the pet

	    // Sending the POST request to the API using the request specification
	    Response response = 
	            given().spec(requestSpec)   // Using predefined request spec (base URI and headers)
	            .body(reqBoby)              // Attaching the request body
	            .when().post();             // Making the POST request

	    // Extracting the 'id' from the response and storing it in a class-level variable
	    this.petId = response.then().extract().path("id");

	    // Validating the response using the predefined response specification
	    // This checks status code, body content, and response time
	    response.then().spec(responseSpec);
	}
	
	//GET
	@Test(priority = 2)
	public void getRequestTest() {

	    // Sending a GET request to retrieve pet details using the pet ID
	    given()
	        .spec(requestSpec)                 // Using the predefined request specification (base URI and headers)
	        .pathParam("petID", this.petId)    // Setting the path parameter with the pet ID stored earlier in @BeforeClass section
	        .log().all()                       // Logging all request details (helps with debugging or understanding what's being sent)
	    .when()
	        .get("/{petID}")                   // Making the GET request to /{petID}
	    .then()
	        .spec(responseSpec)                // Validating the response using the predefined response spec
	        .body("name", equalTo("puppy1"));  // Asserting that the pet name in the response is "puppy1"
	}
	
	//DELETE
	@Test(priority = 3)
	public void delecteRequestTest() {

	    // Sending a Delete request to retrieve pet details using the pet ID
	    given()
	        .spec(requestSpec)                 // Using the predefined request specification (base URI and headers)
	        .pathParam("petID", this.petId)    // Setting the path parameter with the pet ID stored earlier
	    .when()
	        .delete("/{petID}")                // Making the delete request to /{petID}
	    .then()
	        .statusCode(200);                  // Validating the response code
	        
	}
	
}