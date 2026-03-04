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
        io.restassured.response.Response rawResponse = given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request)
                .when()
                .post("/Account/v1/User")
                .then()
                .log().all()
                .extract()
                .response();
        RegisterResponse response = rawResponse.as(RegisterResponse.class);
        response.setStatusCode(rawResponse.getStatusCode());
        assertEquals(response.getStatusCode(), 201, "Created");
        assertEquals(response.getUsername(), ConfigManager.getUserName(), "Username is matching");
        assertNotNull(response.getBooks(), "Empty array");
        assertNotNull(response.getUserID(), "Created userID");
    }

    @Test(description = "Verify that registration fails when trying to create a user that already exists")
    public void testAlreadyCreatedUser() {
        RegisterRequest request = new RegisterRequest(
                ConfigManager.getUserName(),
                ConfigManager.getPassword(),
                ConfigManager.getFirstName(),
                ConfigManager.getLastName()
        );
        io.restassured.response.Response rawResponse = given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request)
                .when()
                .post("/Account/v1/User")
                .then()
                .log().all()
                .extract()
                .response();
        RegisterResponse response = rawResponse.as(RegisterResponse.class);
        response.setStatusCode(rawResponse.getStatusCode());
        assertEquals(response.getStatusCode(), 406, "Not Acceptable");
        assertNotNull(response.getCode(), "Created code");
//        assertEquals(response.getCode(), "1204");
        assertEquals(response.getMessage(), "User exists!");
        userIDToDelete = response.getUserID(); // not working
    }

    @Test(description = "Verify that registration fails when an invalid password is provided")
    public void testInvalidRegister() {
        RegisterRequest request = new RegisterRequest(
                ConfigManager.getUserName(),
                ConfigManager.getRandomPassword(),
                ConfigManager.getFirstName(),
                ConfigManager.getLastName()
        );
        io.restassured.response.Response rawResponse = given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request)
                .when()
                .post("/Account/v1/User")
                .then()
                .log().all()
                .extract()
                .response();
        RegisterResponse response = rawResponse.as(RegisterResponse.class);
        response.setStatusCode(rawResponse.getStatusCode());
        assertEquals(response.getStatusCode(), 400, "Bad Request");
        assertEquals(response.getMessage(), "Passwords must have at least one non alphanumeric character, one digit ('0'-'9'), one uppercase ('A'-'Z'), one lowercase ('a'-'z'), one special character and Password must be eight characters or longer.");
//        assertEquals(response.getCode(), "1300");
        assertNotNull(response.getCode(), "Created code");
        userIDToDelete = response.getUserID(); // not working
    }
}
