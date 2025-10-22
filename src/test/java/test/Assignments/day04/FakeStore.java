package test.Assignments.day04;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.utilities.ObjectMapper;



import static io.restassured.RestAssured.given;

public class FakeStore extends test.baseUrl.FakeStore {


// Objective: Create a new shopping cart using the Fake Store API with JsonNode for dynamic payload handling.
//Requirements:
//Reference the API documentation at https://fakestoreapi.com/docs
//Use JsonNode to create the request payload dynamically
//Create a cart with properties like:
//userId
//date
//products (array of product objects with productId and quantity)
//Modify the JsonNode to add additional fields as needed
//Send a POST request to the create cart endpoint
//Assert that the response status code indicates success
//Assert that the returned cart contains the expected data

    @Test
    void addCart() {

        JsonNode payload =  ObjectMapper.getJsonNode("fakeData");
        ObjectNode objectNode = (ObjectNode) payload;

        objectNode.put("id", 11);
        objectNode.put("userId", 9);
        objectNode.put("date", "2025-10-21");

        ObjectNode products=(ObjectNode) objectNode.get("products").get(0);
        products.put("title","science fiction book");
        products.put("price",14.99);
        products.put("productId",1);
        products.put("quantity",3);
        System.out.println("payload = " + payload);

        Response response= given(spec).body(payload).when().post("/carts");
        response.prettyPrint();

        Assert.assertEquals(response.statusCode(),201);
        Assert.assertEquals(objectNode.get("id").asInt(), payload.get("id").asInt());

    }}
