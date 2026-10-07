package tests;

import base.BaseTest;
import io.restassured.http.ContentType;
import models.LinkModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import path.ApiPath;
import path.BodyPath;
import path.SchemaPath;
import specs.ResponseSpecs;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

public class TestPublicFilesAndFolders extends BaseTest {

//    @Test
//    @DisplayName("GET - успешное получение метаинформации о публичном файле или каталоге")
//    public void getMetadataAboutPublicFileOrDirectory_returnsOk() {
//     try {
//        filesClient.createFolder(folder)
//                .then()
//                .statusCode(201);
//        filesClient.getInfoFolder(folder)
//                .then()
//                .statusCode(200);
//        String public_link = given().spec(requestSpec)
//                .queryParam(ApiPath.getPath(), folder)
//                .contentType(ContentType.JSON)
//                .body(BodyPath.getBodyPublicSetting())
//                .when()
//                .put(ApiPath.getFilesAndFoldersDirectory() + ApiPath.getPublish())
//                .then()
//                .statusCode(200)
//                .contentType(ContentType.JSON)
//                .spec(ResponseSpecs.linkCreated())
//                .extract().path(LinkModel.getLinkHref());
//
//         given().spec(requestSpec)
//                 .queryParam(ApiPath.getPublicKey(), public_link)
//                 .when()
//                 .get(ApiPath.getPublicDirectory())
//                 .then()
//                 .statusCode(200)
//                 .contentType(ContentType.JSON)
//                 .body(matchesJsonSchemaInClasspath(SchemaPath.getSchemaMetadataPublicFilesOrDirectory()));
//    } finally {
//        filesClient.deleteFolder(folder)
//                .then()
//                .statusCode(anyOf(is(204), is(404)));
//    }}
}
