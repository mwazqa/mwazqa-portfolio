package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddBooksResponse {

    @JsonProperty("userID")
    private String userID;
    private List<BookItem> books;
    private int statusCode;
    private String message;
    private String code;

    public AddBooksResponse() {}

    public String getUserID() {
        return userID;
    }

    public List<BookItem> getBooks() {
        return books;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BookItem {
        private String isbn;
        private String title;
        private String author;

        public String getIsbn() {
            return isbn;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }
    }
}

