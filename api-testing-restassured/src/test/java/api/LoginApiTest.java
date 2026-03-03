package api;

import static org.testng.Assert.*;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import models.LoginRequest;
import models.LoginResponse;
import utils.ConfigManager;

import base.BaseTest;
public class LoginApiTest extends BaseTest {

    @Test(description = "Logowanie poprawnymi danymi")
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
//        assertNotNull(response.getToken(), "Token nie powinien być nullem");
        assertEquals(response.getUsername(), ConfigManager.getUserName(), "Username się nie zgadza");
    }

    @Test(description = "Logowanie nieistniejącym użytkownikiem")
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
