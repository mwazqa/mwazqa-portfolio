package api;

import static org.testng.Assert.*;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import base.BaseTest;
import utils.ConfigManager;
import models.*;
import java.util.Arrays;

public class UserProfileApiTest extends BaseTest {

    @Test(description = "Verify that an authenticated user can retrieve their profile information")
    public void testGetUserProfile() {
        // First, register and login to get token
        LoginRequest loginRequest = new LoginRequest(
                ConfigManager.getUserName(),
                ConfigManager.getPassword()
        );

        io.restassured.response.Response loginResponse = given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(loginRequest)
                .when()
                .post("/Account/v1/GenerateToken")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();

        LoginResponse login = loginResponse.as(LoginResponse.class);
        String token = login.getToken();

        assertNotNull(token, "Token should not be null");

        // Get user profile with token
        io.restassured.response.Response profileResponse = given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/Account/v1/User/ExampleUserName")
                .then()
                .log().all()
                .extract()
                .response();

        UserProfileResponse profile = profileResponse.as(UserProfileResponse.class);
        profile.setStatusCode(profileResponse.getStatusCode());

        assertEquals(profile.getStatusCode(), 200, "Status should be 200 OK");
        assertEquals(profile.getUsername(), ConfigManager.getUserName(), "Username should match");
        assertNotNull(profile.getUserID(), "User ID should not be null");
        assertNotNull(profile.getBooks(), "Books collection should be initialized");
    }

    @Test(description = "Verify that unauthenticated request to get user profile returns 401 Unauthorized")
    public void testGetUserProfileUnauthorized() {
        io.restassured.response.Response response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/Account/v1/User/ExampleUserName")
                .then()
                .log().all()
                .extract()
                .response();

        assertEquals(response.getStatusCode(), 401, "Should return 401 Unauthorized");
    }

    @Test(description = "Verify that a user can retrieve profile with valid Basic Auth credentials")
    public void testGetUserProfileWithBasicAuth() {
        io.restassured.response.Response response = given()
                .log().all()
                .auth().preemptive().basic(ConfigManager.getUserName(), ConfigManager.getPassword())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/Account/v1/User/ExampleUserName")
                .then()
                .log().all()
                .extract()
                .response();

        UserProfileResponse profile = response.as(UserProfileResponse.class);
        profile.setStatusCode(response.getStatusCode());

        assertEquals(profile.getStatusCode(), 200, "Should return 200 OK");
        assertEquals(profile.getUsername(), ConfigManager.getUserName(), "Username should match");
    }

    @Test(description = "Verify that requesting profile for non-existent user returns 400 Bad Request")
    public void testGetNonExistentUserProfile() {
        io.restassured.response.Response response = given()
                .log().all()
                .auth().preemptive().basic(ConfigManager.getUserName(), ConfigManager.getPassword())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/Account/v1/User/NonExistentUser12345")
                .then()
                .log().all()
                .extract()
                .response();

        int statusCode = response.getStatusCode();
        assertTrue(statusCode == 400 || statusCode == 401 || statusCode == 404,
                "Should return error status code (400, 401, or 404), got: " + statusCode);
    }
}

