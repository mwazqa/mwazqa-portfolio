package utils;

public class ConfigManager {

    private static final String BASE_URL = "https://demoqa.com";

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getUserName() {
        return "ExampleUsername";
    }

    public static String getPassword() {
        return "ExamplePassword@123";
    }

}
