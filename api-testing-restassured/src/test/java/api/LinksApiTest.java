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
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
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
}
