package tests;

import base.BaseTest;
import io.restassured.http.ContentType;
import models.LinkModel;
import models.StatusModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import path.ApiPath;
import specs.ResponseSpecs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestAsynchronousOperation extends BaseTest {

    @Test
    @DisplayName("GET - успешное получение статуса асинхронной операции")
    public void getStatusOfAnAsynchronousOperation_returnsOk() {
        try {
            filesClient.createFile(file)
                    .then()
                    .statusCode(201);
            filesClient.getInfoFile(file)
                    .then()
                    .statusCode(200);

            String uploadUrl = given().spec(requestSpec)
                    .queryParam(ApiPath.getFrom(), file)
                    .queryParam(ApiPath.getPath(), folder)
                    .when()
                    .post(ApiPath.getFilesAndFoldersDirectory() + ApiPath.getCopy())
                    .then()
                    .statusCode(201)
                    .contentType(ContentType.JSON)
                    .spec(ResponseSpecs.linkCreated())
                    .extract().path(LinkModel.getLinkHref());
            filesClient.createFolder(second_folder)
                    .then()
                    .statusCode(201);

            String asynchronousOperationUrl = given().spec(requestSpec)
                    .queryParam(ApiPath.getPath(), second_folder)
                    .queryParam(ApiPath.getUrl(), uploadUrl)
                    .when()
                    .post(ApiPath.getFilesAndFoldersDirectory() + ApiPath.getUpload())
                    .then()
                    .statusCode(202)
                    .contentType(ContentType.JSON)
                    .extract().path(LinkModel.getLinkHref());
            String operationId = given()
                    .when()
                    .get(asynchronousOperationUrl)
                    .then()
                    .extract().path(ApiPath.getId());

            given().spec(requestSpec)
                    .queryParam(ApiPath.getOperationId(), operationId)
                    .when()
                    .get(asynchronousOperationUrl)
                    .then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .body(StatusModel.getStatusOperation(), isOneOf(StatusModel.getOperationSuccessfullyCompleted(), StatusModel.getOperationFailed(), StatusModel.getOperationInProgress()));
        } finally {
            filesClient.deleteFolder(folder)
                    .then()
                    .statusCode(anyOf(is(204), is(404)));
            filesClient.deleteFolder(second_folder)
                    .then()
                    .statusCode(anyOf(is(204), is(404)));
            filesClient.deleteFile(file)
                    .then()
                    .statusCode(anyOf(is(204), is(404)));
        }
    }
        @Test
        @DisplayName("GET - ошибка 400 в получении статуса асинхронной операции")
        public void getStatusOfAnAsynchronousOperation_returnsError400() {
                    given().spec(requestSpec)
                            .body(ApiPath.getBrokenJson())
                            .when()
                            .get(ApiPath.getOperationsDirectory())
                            .then()
                            .statusCode(400)
                            .contentType(ContentType.JSON)
                            .spec(ResponseSpecs.errorResponse());
        }

        @Test
        @DisplayName("GET - ошибка 401 в получении статуса асинхронной операции")
        public void getStatusOfAnAsynchronousOperation_returnsError401() {
                    given().spec(withoutAuthSpec)
                            .when()
                            .get(ApiPath.getOperationsDirectory())
                            .then()
                            .statusCode(401)
                            .contentType(ContentType.JSON)
                            .spec(ResponseSpecs.errorResponse());
        }
    }
