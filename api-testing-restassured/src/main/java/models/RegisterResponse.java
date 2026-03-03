package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterResponse {

    @JsonProperty("userID")
    private String userID;
    private String username;
    private String token;
    private String expires;
    private String message;

    public RegisterResponse() {}

    public String getUserId() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public String getExpires() {
        return expires;
    }

    public String getMessage() {
        return message;
    }
}
