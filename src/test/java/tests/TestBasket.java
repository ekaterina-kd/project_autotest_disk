package tests;

import base.BaseTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import path.ApiPath;
import path.SchemaPath;
import specs.ResponseSpecs;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class TestBasket extends BaseTest {

    @Test
    @DisplayName("DELETE - успешное очищение корзины")
    public void deleteCleaningTheBasket_returnsOk() {
        given().spec(requestSpec)
                .when()
                .delete(ApiPath.getBasketDirectory())
                .then()
                .statusCode(204);
    }

    @Test
    @DisplayName("DELETE - ошибка 400 в очищении корзины")
    public void deleteCleaningTheBasket_returnsError400() {
                given().spec(requestSpec)
                        .body(ApiPath.getBrokenJson())
                        .when()
                        .get(ApiPath.getBasketDirectory())
                        .then()
                        .statusCode(400)
                        .contentType(ContentType.JSON)
                        .spec(ResponseSpecs.errorResponse());
    }

    @Test
    @DisplayName("DELETE - ошибка 400 в очищении корзины")
    public void deleteCleaningTheBasket_returnsError401() {
                given().spec(withoutAuthSpec)
                        .when()
                        .get(ApiPath.getBasketDirectory())
                        .then()
                        .statusCode(401)
                        .contentType(ContentType.JSON)
                        .spec(ResponseSpecs.errorResponse());
    }

    @Test
    @DisplayName("GET - успешное получение содержимого корзины")
    public void getContentsOfTheBasket_returnsOk() {
        given().spec(requestSpec)
                .when()
                .get(ApiPath.getBasketDirectory())
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(matchesJsonSchemaInClasspath(SchemaPath.getSchemaContentsOfTheBasket()));
    }

    @Test
    @DisplayName("GET - ошибка 400 в получении содержимого корзины")
    public void getContentsOfTheBasket_returnsError400() {
                given().spec(requestSpec)
                        .body(ApiPath.getBrokenJson())
                        .when()
                        .get(ApiPath.getBasketDirectory())
                        .then()
                        .statusCode(400)
                        .contentType(ContentType.JSON)
                        .spec(ResponseSpecs.errorResponse());
    }

    @Test
    @DisplayName("GET - ошибка 401 в получении содержимого корзины")
    public void getContentsOfTheBasket_returnsError401() {
                given().spec(withoutAuthSpec)
                        .when()
                        .get(ApiPath.getBasketDirectory())
                        .then()
                        .statusCode(401)
                        .contentType(ContentType.JSON)
                        .spec(ResponseSpecs.errorResponse());
    }
}
