package test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;


public class APIResponseValidation {

    //API Endpoint: https://fakerestapi.azurewebsites.net/api/v1/Users
    //Objective: Validate API response headers and status
    //Test Requirements:
    //Send GET request to the endpoint
    //Validate response status code
    //Validate response headers
    //Verify server information
    //Expected Assertions:
    //Status code is 200
    //Content-Type is "application/json"
    //Server header contains "Kestrel"
    //Transfer-Encoding is "chunked"

@Test
    void Responsevalidation() {
        // Send GET request
        Response response = RestAssured.get("https://fakerestapi.azurewebsites.net/api/v1/Users");

        // Print status code
        int statusCode = response.statusCode();
        System.out.println("Status Code: " + statusCode);

        // Print all headers
        System.out.println("Headers:");
        response.getHeaders().forEach(header ->
                System.out.println(header.getName() + ": " + header.getValue())
        );

        // Print response body
        System.out.println("\nResponse Body:");
        response.prettyPrint();

        // Assertions
        response.then().statusCode(200); // status code check
        response.then().assertThat().header("Content-Type", containsString("application/json"));
        response.then().assertThat().header("Server", containsString("Kestrel"));
        response.then().assertThat().header("Transfer-Encoding", equalTo("chunked"));
    }
}
