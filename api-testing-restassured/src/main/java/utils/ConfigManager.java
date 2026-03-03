package utils;

public class ConfigManager {

    private static final String BASE_URL = "https://demoqa.com";

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getUserName() {
        return "ExampleUserName";
    }

    public static String getPassword() {
        return "ExamplePassword@123";
    }

    public static String getFirstName() {
        return "Example First Name";
    }

    public static String getLastName() {
        return "Example Last Name";
    }
}
