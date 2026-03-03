package base;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.annotations.BeforeClass;
import utils.ConfigManager;

public class BaseTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ConfigManager.getBaseUrl();
        RestAssured.defaultParser = Parser.JSON;
    }
}
