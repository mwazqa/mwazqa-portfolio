package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterResponse {

    @JsonProperty("userID")
    private String userID;
    private String username;
    private List<Object> books;

    public RegisterResponse() {}

    public String getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public List<Object> getBooks() {
        return books;
    }

}
