package api;

import base.BaseTest;

import io.restassured.http.ContentType;
import models.BookStoreResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class BookStoreApiTest extends BaseTest {

    @Test(description = "todo")
    public void testBookStore() {
        io.restassured.response.Response rawResponse = given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/BookStore/v1/Book?ISBN=9781449337711")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();
        BookStoreResponse response = rawResponse.as(BookStoreResponse.class);
        response.setStatusCode(rawResponse.getStatusCode());
        assertEquals(response.getStatusCode(), 200, "OK");
        assertNotNull(response.getIsbn(),"Recieved ISBN");
        assertNotNull(response.getTitle(),"Recieved Title");
        assertNotNull(response.getSubTitle(),"Recieved Subtitle");
        assertNotNull(response.getAuthor(),"Recieved Author");
        assertNotNull(response.getPublishDate(),"Recieved Publish Date");
        assertNotNull(response.getPublisher(),"Recieved Publisher");
        assertNotNull(response.getPages(),"Recieved Pages");
        assertNotNull(response.getDescription(),"Recieved Description");
        assertNotNull(response.getWebsite(),"Recieved Website");
    }
}
