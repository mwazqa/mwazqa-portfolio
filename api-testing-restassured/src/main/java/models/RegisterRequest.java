package models;

public class RegisterRequest {

    private String userName;
    private String password;
    private String firstname;
    private String lastname;

    public RegisterRequest(String userName, String password, String firstname, String lastname) {
        this.userName = userName;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }
}
