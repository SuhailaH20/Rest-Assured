package test.baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class CrudBaseUrl {

    protected RequestSpecification spec;

    @BeforeMethod
    public void setSpec(){
        spec = new RequestSpecBuilder()
                .setBaseUri("https://fakerestapi.azurewebsites.net")
                .setContentType(ContentType.JSON)
                .build();

    }
}