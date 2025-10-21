package test.Assignments.day03;



import test.baseUrl.CrudBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.pojos.postPojo;

import static io.restassured.RestAssured.given;

public class CrudOperation extends CrudBaseUrl
{

    //    Task: Write code that performs all CRUD operations on "activities"
    //    using the Fake REST API at https://fakerestapi.azurewebsites.net
    //    Requirements:
    //    1. Use POJO classes for all operations
    //    2. Implement CREATE (POST) - Add new activity
    //    3. Implement READ (GET) - Retrieve activity details
    //    4. Implement UPDATE (PUT) - Modify existing activity
    //    5. Implement DELETE - Remove activity
    //    6. Add appropriate assertions for each operation

    @Test
    void activityOperations() {

        // ========== CREATE (POST) ==========
        postPojo payload = new postPojo(
                0,
                "New Activity",
                "2024-12-31T00:00:00Z",
                true);
        Response createResponse  = given(spec)
                .body(payload)
                .when()
                .post("/api/v1/Activities");
        createResponse .prettyPrint();
        Assert.assertEquals(createResponse .statusCode(), 200);
        postPojo createdActivity = createResponse .as(postPojo.class);
        Assert.assertEquals(createdActivity.getDueDate(),payload.getDueDate());
        System.out.println("✅ CREATE Response: " + createResponse .asString());
        System.out.println("========================================");

        //2. READ (GET) - Retrieve activity details
        Response getResponse  = given(spec)
                .when()
                .get("/api/v1/Activities/7");
        getResponse .prettyPrint();
        Assert.assertEquals(getResponse .statusCode(), 200);
        postPojo getActivity= getResponse .as(postPojo.class);
        Assert.assertEquals(getActivity.getCompleted(),false);
        System.out.println("✅ READ Response: " + getResponse.asString());
        System.out.println("========================================");

        //3. UPDATE (PUT) - Modify existing activity
        getActivity.setCompleted(true);
     Response updateResponse  = given(spec)
                .body(getActivity)
                .when()
                .put("/api/v1/Activities/" +createdActivity.getId());
        updateResponse .prettyPrint();
        Assert.assertEquals(updateResponse .statusCode(), 200);
        postPojo updateActivity=updateResponse .as(postPojo.class);
        System.out.println("✅ UPDATE Response: " + updateResponse.asString());
        System.out.println("========================================");


        //4. DELETE - Remove activity
        Response deleteResponse  = given(spec)
                .when()
                .delete("/api/v1/Activities/" +  createdActivity.getId());
        deleteResponse .prettyPrint();
        Assert.assertEquals(deleteResponse .statusCode(), 200);

        given(spec).when().get("/api/v1/Activities/" +  createdActivity.getId())
                .then()
                .statusCode(404);
        System.out.println("✅ DELETE Response: " + deleteResponse.asString());

        System.out.println("========================================");

    }
}