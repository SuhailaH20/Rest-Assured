package test.baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

import static test.Assignments.day05.T02_BookstoreAPI.token;

public class BookStore {

    protected RequestSpecification spec;


    @BeforeMethod
    public void setSpec(){
        spec = new RequestSpecBuilder()
                .setBaseUri("https://bookstore.demoqa.com")
                .addHeader("Authorization","Bearer "+token)
                .setContentType(ContentType.JSON)
                .build();
    }

}