package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProfileResponse {

    @JsonProperty("userID")
    private String userID;
    private String username;
    private String firstName;
    private String lastName;
    private List<BookCollectionItem> books;
    private int statusCode;

    public UserProfileResponse() {}

    public String getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<BookCollectionItem> getBooks() {
        return books;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BookCollectionItem {
        private String isbn;
        private String title;

        public String getIsbn() {
            return isbn;
        }

        public String getTitle() {
            return title;
        }
    }
}

