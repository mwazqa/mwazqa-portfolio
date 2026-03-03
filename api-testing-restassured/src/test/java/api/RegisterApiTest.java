package api;

import static org.testng.Assert.*;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import base.BaseTest;
import models.RegisterRequest;
import models.RegisterResponse;
import utils.ConfigManager;

public class RegisterApiTest extends BaseTest {

    @Test(description = "Verify that registration with valid credentials returns a successful response")
    public void testValidRegister() {
        RegisterRequest request = new RegisterRequest(
                ConfigManager.getUserName(),
                ConfigManager.getPassword(),
                ConfigManager.getFirstName(),
                ConfigManager.getLastName()
        );
        RegisterResponse response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request)
                .when()
                .post("/Account/v1/User")
                .then()
                .log().all()
                .statusCode(201)
                .extract()
                .as(RegisterResponse.class);
        assertEquals(response.getUsername(), ConfigManager.getUserName(), "Username is matching");
        assertNotNull(response.getBooks(), "Empty array");
        assertNotNull(response.getUserID(), "Created userID");
        userIDToDelete = response.getUserID();
    }
}
