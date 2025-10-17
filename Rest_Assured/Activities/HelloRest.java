package Examples;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class HelloRest {
	
	String Baseurl;
	
	@Test
	public void hellorest() {
	    // Set the base URL for the API
	    Baseurl = "https://petstore.swagger.io/v2/pet";
	    
	    // Send a GET request to fetch pets with status 'sold'
	    Response response = 
	        given().contentType(ContentType.JSON) // Set request content type to JSON; set headers, parameters
	        .when().get(Baseurl + "/findByStatus?status=sold"); // Perform GET request; https method (GET, POST, DELETE)
	    
	    // Print the response in a readable format
	 
	    response.prettyPrint();
	    
	    // Assertions to validate the response
	    
	    response.then().statusCode(200); // Check if the status code is 200 (OK)
	    
	    // Check if the first pet in the response has status 'sold'
	    response.then().body("[0].status", equalTo("sold"));	
	    
	 // Print the status code
		//		System.out.println(response.statusCode());
		//		System.out.println("===============================");
		//		// Print the response headers
		//		System.out.println(response.getHeaders().asList());
		//		System.out.println("===============================");
		//		// Print the response body
		//		System.out.println(response.getBody().asString());
		//		System.out.println("===============================");
		//		System.out.println(response.getBody().asPrettyString());
 
				
	}
}
