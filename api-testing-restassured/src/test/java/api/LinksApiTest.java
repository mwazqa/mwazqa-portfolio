package api;

import static org.testng.Assert.*;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import base.BaseTest;
import models.LinksResponse;

public class LinksApiTest extends BaseTest {

    @Test(description = "/created")
    public void testLinksCreated() {
        io.restassured.response.Response rawResponse = given()
                .log().all()
                .when()
                .get("/created")
                .then()
                .log().all()
                .extract()
                .response();
        LinksResponse response = new LinksResponse();
        response.setStatusCode(rawResponse.getStatusCode());
        assertEquals(response.getStatusCode(), 201, "Created");
    }

    @Test(description = "/no-content")
    public void testLinksNoContent() {
        io.restassured.response.Response rawResponse = given()
                .log().all()
                .when()
                .get("/no-content")
                .then()
                .log().all()
                .extract()
                .response();
        LinksResponse response = new LinksResponse();
        response.setStatusCode(rawResponse.getStatusCode());
        assertEquals(response.getStatusCode(), 204, "No Content");
    }

    @Test(description = "/moved")
    public void testLinksMoved() {
        io.restassured.response.Response rawResponse = given()
                .log().all()
                .when()
                .get("/moved")
                .then()
                .log().all()
                .extract()
                .response();
        LinksResponse response = new LinksResponse();
        response.setStatusCode(rawResponse.getStatusCode());;
        String actualLocation = rawResponse.getHeader("Location");
        response.setURL(actualLocation);
        assertEquals(response.getStatusCode(), 301, "Moved Permanently");
//        assertEquals(response.getURL(), "demoqa.com", "Location header URL mismatch!"); // no URL in Location header
    }

    @Test(description = "/bad-request")
    public void testLinksBadRequest() {
        io.restassured.response.Response rawResponse = given()
                .log().all()
                .when()
                .get("/bad-request")
                .then()
                .log().all()
                .extract()
                .response();
        LinksResponse response = new LinksResponse();
        response.setStatusCode(rawResponse.getStatusCode());
        assertEquals(response.getStatusCode(), 400, "Bad Request");
    }

    @Test(description = "/unauthorized")
    public void testLinksUnauthorized() {
        io.restassured.response.Response rawResponse = given()
                .log().all()
                .when()
                .get("/unauthorized")
                .then()
                .log().all()
                .extract()
                .response();
        LinksResponse response = new LinksResponse();
        response.setStatusCode(rawResponse.getStatusCode());
        assertEquals(response.getStatusCode(), 401, "Unauthorized");
    }

    @Test(description = "/forbidden")
    public void testLinksForbidden() {
        io.restassured.response.Response rawResponse = given()
                .log().all()
                .when()
                .get("/forbidden")
                .then()
                .log().all()
                .extract()
                .response();
        LinksResponse response = new LinksResponse();
        response.setStatusCode(rawResponse.getStatusCode());
        assertEquals(response.getStatusCode(), 403, "Forbidden");
    }

    @Test(description = "/invalid-url")
    public void testLinksNotFound() {
        io.restassured.response.Response rawResponse = given()
                .log().all()
                .when()
                .get("/invalid-url")
                .then()
                .log().all()
                .extract()
                .response();
        LinksResponse response = new LinksResponse();
        response.setStatusCode(rawResponse.getStatusCode());
        assertEquals(response.getStatusCode(), 404, "Not Found");
    }

    @Test(description = "/500")
    public void testLinksInternalServerError() {
        io.restassured.response.Response rawResponse = given()
                .log().all()
                .when()
                .get("https://the-internet.herokuapp.com/status_codes/500")
                .then()
                .log().all()
                .extract()
                .response();
        LinksResponse response = new LinksResponse();
        response.setStatusCode(rawResponse.getStatusCode());
        assertEquals(response.getStatusCode(), 500, "Internal Server Error");
    }
}
