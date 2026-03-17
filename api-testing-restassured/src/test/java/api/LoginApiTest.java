package api;

import static org.testng.Assert.*;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import base.BaseTest;
import utils.ConfigManager;
import models.LoginRequest;
import models.LoginResponse;

public class LoginApiTest extends BaseTest {

    @Test(description = "Verify that login with valid credentials returns a successful response and correct username")
    public void testValidLogin() {
        LoginRequest request = new LoginRequest(
                ConfigManager.getUserName(),
                ConfigManager.getPassword()
        );
        io.restassured.response.Response rawResponse = given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request)
                .when()
                .post("/Account/v1/GenerateToken")
                .then()
                .log().all()
                .extract()
                .response();
        LoginResponse response = rawResponse.as(LoginResponse.class);
        response.setStatusCode(rawResponse.getStatusCode());
        assertEquals(response.getStatusCode(), 200, "OK");
        assertNotNull(response.getToken(),"Created token");
//        assertEquals(response.getExpires(), "make function of one day later date");
        assertEquals(response.getStatus(), "Success");
        assertNotNull(response.getResult(), "User authorized successfully.");
    }

    @Test(description = "Verify that login with non-existing/invalid credentials returns an unsuccessful authentication response")
    public void testInvalidLogin() {
        LoginRequest request = new LoginRequest(
                ConfigManager.getRandomUserName(),
                ConfigManager.getRandomPassword()
        );
        io.restassured.response.Response rawResponse = given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request)
                .when()
                .post("/Account/v1/GenerateToken")
                .then()
                .log().all()
                .extract()
                .response();
        LoginResponse response = rawResponse.as(LoginResponse.class);
        response.setStatusCode(rawResponse.getStatusCode());
        assertEquals(response.getStatusCode(), 200, "OK");
        assertNull(response.getToken(),"Null token");
        assertNull(response.getExpires(), "Null expires");
        assertEquals(response.getStatus(), "Failed");
        assertNotNull(response.getResult(), "User authorization failed.");
    }
}
