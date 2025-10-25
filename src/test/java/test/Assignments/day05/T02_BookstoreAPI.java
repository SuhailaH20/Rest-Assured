package test.Assignments.day05;

import test.baseUrl.BookStore;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static test.utilities.ObjectMapper.getJsonNode;

public class T02_BookstoreAPI extends BookStore {
    public static String token;
    public static String userId;

    @Test(priority=3)
    void authzUser() {
        JsonNode payload = getJsonNode("T02_createUser");
        Response response=given(spec)
                .body(payload)
                .when().post("/Account/v1/Authorized");
        response.prettyPrint();
        response.then().statusCode(200);


    }
    @Test(priority=2)
    void generateToken() {
        JsonNode payload = getJsonNode("T02_createUser");
        Response response=given(spec)
                .body(payload)
                .when().post("/Account/v1/GenerateToken");
        response.prettyPrint();
       token=response.jsonPath().getString("token");
        System.out.println("token = " + token);

        response.then().statusCode(200).body("token",notNullValue(),"status",equalTo("Success"));
    }
    @Test(priority=1)
    void createUser() {
        //prepare payload
        JsonNode payload = getJsonNode("T02_createUser");
       //send request
        Response response=given(spec).body(payload).when().post("/Account/v1/User");
        response.prettyPrint();
        //do assertion
        response.then()
                .statusCode(201).body("username",equalTo(payload.get("userName").asText()));
         userId= response.jsonPath().getString("userID");
         System.out.println("id = " + userId);

    }

        @Test(priority =5,dependsOnMethods ="generateToken" )
        void AssignBook () {
        //prepare payload
            JsonNode payload = getJsonNode("T02_getBook");
            //send request
            Response response=given(spec).body(payload).when().post("/BookStore/v1/Books");
            response.prettyPrint();

        }

        @Test(priority = 6,dependsOnMethods ={"generateToken","createUser"})
        void getUser () {
            JsonNode payload = getJsonNode("T02_createUser");
             //send request
            Response response=given(spec).body(payload).when().get("/Account/v1/User/"+userId);
            response.prettyPrint();

            //do assertion
            response.then().statusCode(200);
            userId= response.jsonPath().getString("userId");
            response.then().body(("userId"),equalTo((userId)));
            response.then().body("username",equalTo(payload.get("userName").asText()));
        }

        @Test(priority = 4)
        void getAllBooks () {
            //send request
            Response response=given(spec).when().get("/BookStore/v1/Books");
            response.prettyPrint();
            String isbn=response.jsonPath().getString("books.isbn");
            //do assertion
            response.then()
                    .statusCode(200).
                    body("books[0].isbn",notNullValue());

        }
    }