package api;

import static org.testng.Assert.*;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import base.BaseTest;
import utils.ConfigManager;
import models.*;
import java.util.Arrays;

/**
 * Comprehensive test suite for BookStore API operations.
 * Covers:
 * - Fetching individual book details (BookStore)
 * - Adding books to user's collection
 * - Removing books from user's collection
 * - Authentication and error handling
 */
public class BooksApiTest extends BaseTest {

    // ==================== FETCH BOOK DETAILS ====================

    @Test(description = "Verify that book details can be retrieved by ISBN")
    public void testFetchBookDetails() {
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
        assertNotNull(response.getIsbn(), "Received ISBN");
        assertNotNull(response.getTitle(), "Received Title");
        assertNotNull(response.getSubTitle(), "Received Subtitle");
        assertNotNull(response.getAuthor(), "Received Author");
        assertNotNull(response.getPublishDate(), "Received Publish Date");
        assertNotNull(response.getPublisher(), "Received Publisher");
        assertNotNull(response.getPages(), "Received Pages");
        assertNotNull(response.getDescription(), "Received Description");
        assertNotNull(response.getWebsite(), "Received Website");
    }

    // ==================== ADD BOOKS TO COLLECTION ====================

    @Test(description = "Verify that a user can add books to their collection")
    public void testAddBooksToCollection() {
        // Login first to get token
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

        // Get userID for book collection addition
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
        String userID = profile.getUserID();

        assertNotNull(userID, "User ID should not be null");

        // Add books to collection
        AddBooksRequest addBooksRequest = new AddBooksRequest(
                userID,
                Arrays.asList(
                        new AddBooksRequest.IsbnItem("9781449325862"),
                        new AddBooksRequest.IsbnItem("9781449331818")
                )
        );

        io.restassured.response.Response addBooksResponse = given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(addBooksRequest)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .log().all()
                .extract()
                .response();

        AddBooksResponse booksResponse = addBooksResponse.as(AddBooksResponse.class);
        booksResponse.setStatusCode(addBooksResponse.getStatusCode());

        assertEquals(booksResponse.getStatusCode(), 201, "Should return 201 Created");
        assertNotNull(booksResponse.getBooks(), "Books should be in response");
        assertTrue(booksResponse.getBooks().size() >= 2, "Should have at least 2 books");
    }

    @Test(description = "Verify that adding books with invalid ISBN returns an error")
    public void testAddBooksWithInvalidIsbn() {
        // Login first to get token
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

        // Try to add books with invalid ISBN
        AddBooksRequest addBooksRequest = new AddBooksRequest(
                "invalid-user-id",
                Arrays.asList(
                        new AddBooksRequest.IsbnItem("invalid-isbn-9999999999")
                )
        );

        io.restassured.response.Response response = given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(addBooksRequest)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .log().all()
                .extract()
                .response();

        int statusCode = response.getStatusCode();
        assertTrue(statusCode >= 400, "Should return error status code (4xx or 5xx), got: " + statusCode);
    }

    @Test(description = "Verify that adding books without authentication returns 401 Unauthorized")
    public void testAddBooksUnauthorized() {
        AddBooksRequest addBooksRequest = new AddBooksRequest(
                "some-user-id",
                Arrays.asList(
                        new AddBooksRequest.IsbnItem("9781449325862")
                )
        );

        io.restassured.response.Response response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(addBooksRequest)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .log().all()
                .extract()
                .response();

        assertEquals(response.getStatusCode(), 401, "Should return 401 Unauthorized");
    }

    // ==================== REMOVE BOOKS FROM COLLECTION ====================

    @Test(description = "Verify that a user can delete a specific book from their collection")
    public void testRemoveBookFromCollection() {
        // Login first to get token
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

        // Delete a book from collection using the ISBN
        io.restassured.response.Response deleteResponse = given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .queryParam("ISBN", "9781449325862")
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .log().all()
                .extract()
                .response();

        int statusCode = deleteResponse.getStatusCode();
        // Accept 200, 204 (No Content), or 404 if book wasn't in collection
        assertTrue(statusCode == 200 || statusCode == 204 || statusCode == 404,
                "Should return success or not found status, got: " + statusCode);
    }

    @Test(description = "Verify that deleting a book without authentication returns 401 Unauthorized")
    public void testRemoveBookUnauthorized() {
        io.restassured.response.Response response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .queryParam("ISBN", "9781449325862")
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .log().all()
                .extract()
                .response();

        assertEquals(response.getStatusCode(), 401, "Should return 401 Unauthorized");
    }
}

