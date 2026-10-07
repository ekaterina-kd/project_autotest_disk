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

public class TestDisk extends BaseTest {

    @Test
    @DisplayName("GET - успешное получение метаинформации о диске пользователя")
    public void getMetadataAboutUserDisk_returnsOk() {
        given().spec(requestSpec)
                .when()
                .get(ApiPath.getRootDirectoryDisk())
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(matchesJsonSchemaInClasspath(SchemaPath.getSchemaMetadataUserDisk()));
    }

    @Test
    @DisplayName("GET - ошибка 400 в получении метаинформации о диске пользователя")
    public void getMetadataAboutUserDisk_returnsError400() {
                given().spec(requestSpec)
                        .body(ApiPath.getBrokenJson())
                        .when()
                        .get(ApiPath.getRootDirectoryDisk())
                        .then()
                        .statusCode(400)
                        .contentType(ContentType.JSON)
                        .spec(ResponseSpecs.errorResponse());

    }

    @Test
    @DisplayName("GET - ошибка 401 в получении метаинформации о диске пользователя")
    public void getMetadataAboutUserDisk_returnsError401() {
                given().spec(withoutAuthSpec)
                        .when()
                        .get(ApiPath.getRootDirectoryDisk())
                        .then()
                        .statusCode(401)
                        .contentType(ContentType.JSON)
                        .spec(ResponseSpecs.errorResponse());
    }
}
