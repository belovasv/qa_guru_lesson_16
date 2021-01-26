package restTest;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class CustomSpec {
    private final RequestSpecification request = given()
            .baseUri("https://reqres.in")
            .log().uri()
            .filter(new AllureRestAssured())
            .when();

    private final ResponseSpecification response = expect()
            .body("data", notNullValue())
            .body("support", notNullValue())
            .statusCode(200);

    public static CustomSpec spec() {
        return new CustomSpec();
    }

    public RequestSpecification request() {
        return request;
    }

    public ResponseSpecification expectedResponseUser() {
        return response;
    }
}
