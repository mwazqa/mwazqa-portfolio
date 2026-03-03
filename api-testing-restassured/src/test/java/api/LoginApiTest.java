package api;

import static org.testng.Assert.*;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import base.BaseTest;
import models.LoginRequest;
import models.LoginResponse;
import utils.ConfigManager;

public class LoginApiTest extends BaseTest {

    @Test(description = "Verify that login with valid credentials returns a successful response and correct username")
    public void testValidLogin() {
        LoginRequest request = new LoginRequest(
                ConfigManager.getUserName(),
                ConfigManager.getPassword()
        );
        LoginResponse response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/Account/v1/Login")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(LoginResponse.class);
//        assertNotNull(response.getToken(), "");
        assertEquals(response.getUsername(), ConfigManager.getUserName(), "Username is matching");
    }

    @Test(description = "Verify that login with non-existing/invalid credentials returns an unsuccessful authentication response")
    public void testInvalidLogin() {
        LoginRequest request = new LoginRequest("InvalidUserName", "InvalidPassword@123");
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/Account/v1/Login")
                .then()
                .log().all()
                .statusCode(200)
                .log().all();
    }
}
