package com;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import model.unknown.ResponseArray;
import model.user.Response;
import org.junit.jupiter.api.Test;

import static com.CustomSpec.spec;
import static io.restassured.RestAssured.*;

public class RestTest {

    @Test
    public void simpleTest() {
        given()
                .filter(new AllureRestAssured())
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    public void logTest() {
        given()
                .filter(new AllureRestAssured())
                .log().all()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().headers().log().body();
    }

    @Test
    public void sendBody() {
        String data = "{\n" +
                "\"name\": \"Bob\",\n" +
                "\"job\": \"worker\"" +
                "\n}";

        given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .log().uri()
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .log().body();
    }

    @Test
    public void getResponse() {
        io.restassured.response.Response response = RestAssured
                .given()
                .filter(new AllureRestAssured())
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .extract()
                .response();
        System.out.println(response.asString());
    }

    @Test
    public void getResponse2() {
        given()
                .baseUri("https://reqres.in")
                .log().uri()
                .filter(new AllureRestAssured())
                .when()
                .get("/api/users/2")
                .then()
                .statusCode(200)
                .log().body();

    }

    @Test
    public void getResponseWithSpec() {
        spec().request()
                .when()
                .get("/api/users/2")
                .then()
                .statusCode(200)
                .log().body().spec(spec().expectedResponseUser());
    }

    @Test
    public void getResponseWithSpecAndModel() {
        Response response = spec().request()
                .when()
                .get("/api/users/2")
                .then()
                .spec(spec().expectedResponseUser())
                .extract().as(Response.class);
        System.out.println(response.getData().getEmail());
    }

    @Test
    public void getResponseWithSpecAndModeArray() {
        ResponseArray responseArray = spec().request()
                .when()
                .get("/api/unknown")
                .then()
                .spec(spec().expectedResponseUser())
                .contentType(ContentType.JSON)
                .extract().as(ResponseArray.class);
        System.out.println(responseArray.getData().get(2).getPantoneValue());
    }


}
