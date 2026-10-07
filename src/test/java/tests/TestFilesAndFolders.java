package tests;

import base.BaseTest;
import io.restassured.http.ContentType;
import models.LinkModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import path.ApiPath;
import path.BodyPath;
import path.SchemaPath;
import specs.ResponseSpecs;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestFilesAndFolders extends BaseTest {

    @Test
    @DisplayName("DELETE - успешное удаление файла или папки")
    public void deleteFileOrFolder_returnsOk() {
        boolean created = false;
        try {
            filesClient.createFolder(folder)
                    .then()
                    .statusCode(201);
            created = true;

            filesClient.getInfoFolder(folder)
                    .then()
                    .statusCode(200);

            filesClient.deleteFolder(folder)
                    .then()
                    .statusCode(204);

            filesClient.getInfoFolder(folder)
                    .then()
                    .statusCode(404);
            created = false;
        } finally {
            if (created) {
                filesClient.deleteFolder(folder)
                        .then()
                        .statusCode(anyOf(is(204), is(404)));
            }}}

    @Test
    @DisplayName("GET - успешное получение метаинформации о файле или каталоге")
    public void getMetadataAboutFiles_returnsOk() {
        given().spec(requestSpec)
                .queryParam(ApiPath.getPath(), ApiPath.getDisk())
                .when()
                .get(ApiPath.getFilesAndFoldersDirectory())
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(matchesJsonSchemaInClasspath(SchemaPath.getSchemaMetadataFiles()));
    }

    @Test
    @DisplayName("GET - ошибка 400 в получении метаинформации о файле или каталоге")
    public void getMetadataAboutFiles_returnsError400() {
        given().spec(requestSpec)
                .when()
                .get(ApiPath.getFilesAndFoldersDirectory())
                .then()
                .statusCode(400)
                .contentType(ContentType.JSON)
                .spec(ResponseSpecs.errorResponse());
    }

    @Test
    @DisplayName("GET - ошибка 401 в получении метаинформации о файле или каталоге")
    public void getMetadataAboutFiles_returnsError401() {
        given().spec(withoutAuthSpec)
                .when()
                .get(ApiPath.getFilesAndFoldersDirectory())
                .then()
                .statusCode(401)
                .contentType(ContentType.JSON)
                .spec(ResponseSpecs.errorResponse());
    }

    @Test
    @DisplayName("PUT - успешное создание каталога")
    public void putCreateCatalog_returnsOk() {
        try { filesClient.createFolder(folder)
                .then()
                .statusCode(201)
                .spec(ResponseSpecs.linkCreated());
            filesClient.getInfoFolder(folder)
                    .then()
                    .statusCode(200);
        } finally {
            filesClient.deleteFolder(folder)
                    .then()
                    .statusCode(anyOf(is(204), is(404)));
        }}

    @Test
    @DisplayName("PUT - ошибка 400 в создании каталога")
    public void putCreateCatalog_returnsError400() {
                given().spec(requestSpec)
                        .when()
                        .get(ApiPath.getFilesAndFoldersDirectory())
                        .then()
                        .statusCode(400)
                        .contentType(ContentType.JSON)
                        .spec(ResponseSpecs.errorResponse());
    }

    @Test
    @DisplayName("PUT - ошибка 401 в создании каталога")
    public void putCreateCatalog_returnsError401() {
                given().spec(withoutAuthSpec)
                        .when()
                        .get(ApiPath.getFilesAndFoldersDirectory())
                        .then()
                        .statusCode(401)
                        .contentType(ContentType.JSON)
                        .spec(ResponseSpecs.errorResponse());
    }

    @Test
    @DisplayName("POST - создание копии файла или папки")
    public void postCreateCopyFilesOrFolders_returnsOk() {
        try {
            filesClient.createFolder(folder)
                    .then()
                    .statusCode(201);
            filesClient.getInfoFolder(folder)
                    .then()
                    .statusCode(200);
        given().spec(requestSpec)
                .queryParam(ApiPath.getFrom(), folder)
                .queryParam(ApiPath.getPath(), second_folder)
                .when()
                .post(ApiPath.getFilesAndFoldersDirectory() + ApiPath.getCopy())
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .spec(ResponseSpecs.linkCreated())
                .body(LinkModel.getLinkHref(), matchesPattern(ApiPath.getUniversalLink()));
            filesClient.getInfoFolder(second_folder)
                    .then()
                    .statusCode(200);
    } finally {
            filesClient.deleteFolder(folder)
                    .then()
                    .statusCode(anyOf(is(204), is(404)));
            filesClient.deleteFolder(second_folder)
                    .then()
                    .statusCode(anyOf(is(204), is(404)));
        }}

    @Test
    @DisplayName("POST - ошибка 400 в создании копии файла или папки")
    public void postCreateCopyFilesOrFolders_returnsError400() {
        try {
            filesClient.createFolder(folder)
                    .then()
                    .statusCode(201);
            filesClient.getInfoFolder(folder)
                    .then()
                    .statusCode(200);
            given().spec(requestSpec)
                    .post(ApiPath.getFilesAndFoldersDirectory() + ApiPath.getCopy())
                    .then()
                    .statusCode(400)
                    .spec(ResponseSpecs.errorResponse());
        } finally {
            filesClient.deleteFolder(folder)
                    .then()
                    .statusCode(anyOf(is(204), is(404)));
        }}

    @Test
    @DisplayName("POST - ошибка 401 в создании копии файла или папки")
    public void postCreateCopyFilesOrFolders_returnsError401() {
        try {
            filesClient.createFolder(folder)
                    .then()
                    .statusCode(201);
            filesClient.getInfoFolder(folder)
                    .then()
                    .statusCode(200);
            given().spec(withoutAuthSpec)
                    .queryParam(ApiPath.getFrom(), folder)
                    .queryParam(ApiPath.getPath(), second_folder)
                    .when()
                    .post(ApiPath.getFilesAndFoldersDirectory() + ApiPath.getCopy())
                    .then()
                    .statusCode(401)
                    .contentType(ContentType.JSON)
                    .spec(ResponseSpecs.errorResponse());
        } finally {
            filesClient.deleteFolder(folder)
                    .then()
                    .statusCode(anyOf(is(204), is(404)));
        }}

    @Test
    @DisplayName("GET - успешное получение ссылки на скачивание файла")
    public void getLinkFile_returnsOk() {
        try {
            filesClient.createFolder(folder);
            given().spec(requestSpec)
                    .queryParam(ApiPath.getPath(), folder)
                    .when()
                    .get(ApiPath.getFilesAndFoldersDirectory() + ApiPath.getDownload())
                    .then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .spec(ResponseSpecs.linkCreated())
                    .body(LinkModel.getLinkHref(), matchesPattern(ApiPath.getUniversalLink()))
                    .body(LinkModel.getLinkHref(), containsString(folder));
        } finally {
            filesClient.deleteFolder(folder);
        }
    }

    @Test
    @DisplayName("GET - ошибка 400 в получении ссылки на скачивание файла")
    public void getLinkFile_returnsError400() {
        given().spec(requestSpec)
                .when()
                .get(ApiPath.getFilesAndFoldersDirectory() + ApiPath.getDownload())
                .then()
                .statusCode(400)
                .contentType(ContentType.JSON)
                .spec(ResponseSpecs.errorResponse());
    }

    @Test
    @DisplayName("GET - ошибка 401 в получении ссылки на скачивание файла")
    public void getLinkFile_returnsError401() {
        given().spec(withoutAuthSpec)
                .when()
                .get(ApiPath.getFilesAndFoldersDirectory() + ApiPath.getDownload())
                .then()
                .statusCode(401)
                .contentType(ContentType.JSON)
                .spec(ResponseSpecs.errorResponse());
    }

    @Test
    @DisplayName("GET - успешное получение списка файлов упорядоченный по имени")
    public void getListOfFilesOrderedByName_returnsOk() {
        given().spec(requestSpec)
                .when()
                .get(ApiPath.getFilesAndFoldersDirectory() + ApiPath.getFiles())
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(matchesJsonSchemaInClasspath(SchemaPath.getSchemaListFilesOrderedByName()));
    }

    @Test
    @DisplayName("GET - ошибка 400 в получении списка файлов упорядоченный по имени")
    public void getListOfFilesOrderedByName_returnsError400() {
        given().spec(requestSpec)
                .body(ApiPath.getBrokenJson())
                .when()
                .get(ApiPath.getFilesAndFoldersDirectory() + ApiPath.getFiles())
                .then()
                .statusCode(400)
                .contentType(ContentType.JSON)
                .spec(ResponseSpecs.errorResponse());
    }

    @Test
    @DisplayName("GET - ошибка 401 в получении списка файлов упорядоченный по имени")
    public void getListOfFilesOrderedByName_returnsError401() {
                given().spec(withoutAuthSpec)
                        .when()
                        .get(ApiPath.getFilesAndFoldersDirectory() + ApiPath.getFiles())
                        .then()
                        .statusCode(401)
                        .contentType(ContentType.JSON)
                        .spec(ResponseSpecs.errorResponse());
    }

    @Test
    @DisplayName("GET - успешное получение списка файлов упорядоченный по дате загрузки")
    public void getListOfFilesOrderedByUploadDate_returnsOk() {
        given().spec(requestSpec)
                .when()
                .get(ApiPath.getFilesAndFoldersDirectory() + ApiPath.getLastUploaded())
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(matchesJsonSchemaInClasspath(SchemaPath.getSchemaListFilesOrderedByUploadDate()));
    }

    @Test
    @DisplayName("GET - ошибка 400 в получении списка файлов упорядоченный по дате загрузки")
    public void getListOfFilesOrderedByUploadDate_returnsError400() {
                given().spec(requestSpec)
                        .body(ApiPath.getBrokenJson())
                        .when()
                        .get(ApiPath.getFilesAndFoldersDirectory() + ApiPath.getLastUploaded())
                        .then()
                        .statusCode(400)
                        .contentType(ContentType.JSON)
                        .spec(ResponseSpecs.errorResponse());
    }

    @Test
    @DisplayName("GET - ошибка 401 в получении списка файлов упорядоченный по дате загрузки")
    public void getListOfFilesOrderedByUploadDate_returnsError401() {
                given().spec(withoutAuthSpec)
                        .when()
                        .get(ApiPath.getFilesAndFoldersDirectory() + ApiPath.getLastUploaded())
                        .then()
                        .statusCode(401)
                        .contentType(ContentType.JSON)
                        .spec(ResponseSpecs.errorResponse());
    }

    @Test
    @DisplayName("POST - перемещение файла или папки")
    public void postMovingFilesOrFolders_returnsOk() {
        try {
            filesClient.createFolder(folder)
                    .then()
                    .statusCode(201);
            filesClient.getInfoFolder(folder)
                    .then()
                    .statusCode(200);
            given().spec(requestSpec)
                    .queryParam(ApiPath.getFrom(), folder)
                    .queryParam(ApiPath.getPath(), second_folder)
                    .when()
                    .post(ApiPath.getFilesAndFoldersDirectory() + ApiPath.getMove())
                    .then()
                    .statusCode(201)
                    .contentType(ContentType.JSON)
                    .spec(ResponseSpecs.linkCreated())
                    .body(LinkModel.getLinkHref(), matchesPattern(ApiPath.getUniversalLink()));
            filesClient.getInfoFolder(second_folder)
                    .then()
                    .statusCode(200);
            filesClient.getInfoFolder(folder)
                    .then()
                    .statusCode(404);
        } finally {
            filesClient.deleteFolder(folder)
                    .then()
                    .statusCode(anyOf(is(204), is(404)));
            filesClient.deleteFolder(second_folder)
                    .then()
                    .statusCode(anyOf(is(204), is(404)));
        }}

    @Test
    @DisplayName("POST - ошибка 400 в перемещении файла или папки")
    public void postMovingFilesOrFolders_returnsError400() {
        try {
            filesClient.createFolder(folder)
                    .then()
                    .statusCode(201);
            filesClient.getInfoFolder(folder)
                    .then()
                    .statusCode(200);
            given().spec(requestSpec)
                    .post(ApiPath.getFilesAndFoldersDirectory() + ApiPath.getCopy())
                    .then()
                    .statusCode(400)
                    .spec(ResponseSpecs.errorResponse());
        } finally {
            filesClient.deleteFolder(folder)
                    .then()
                    .statusCode(anyOf(is(204), is(404)));
        }}

    @Test
    @DisplayName("POST - ошибка 401 в перемещении файла или папки")
    public void postMovingFilesOrFolders_returnsError401() {
        try {
            filesClient.createFolder(folder)
                    .then()
                    .statusCode(201);
            filesClient.getInfoFolder(folder)
                    .then()
                    .statusCode(200);
            given().spec(withoutAuthSpec)
                    .queryParam(ApiPath.getFrom(), folder)
                    .queryParam(ApiPath.getPath(), second_folder)
                    .when()
                    .post(ApiPath.getFilesAndFoldersDirectory() + ApiPath.getCopy())
                    .then()
                    .statusCode(401)
                    .contentType(ContentType.JSON)
                    .spec(ResponseSpecs.errorResponse());
        } finally {
            filesClient.deleteFolder(folder)
                    .then()
                    .statusCode(anyOf(is(204), is(404)));
        }}

    @Test
    @DisplayName("GET - успешное получение списка опубликованных ресурсов")
    public void getListOfPublishedResources_returnsOk() {
        given().spec(requestSpec)
                .when()
                .get(ApiPath.getFilesAndFoldersDirectory() + ApiPath.getPublic())
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(matchesJsonSchemaInClasspath(SchemaPath.getSchemaListPublishedResources()));
    }

    @Test
    @DisplayName("GET - ошибка 400 в получении списка опубликованных ресурсов")
    public void getListOfPublishedResources_returnsError400() {
                given().spec(requestSpec)
                        .body(ApiPath.getBrokenJson())
                        .when()
                        .get(ApiPath.getFilesAndFoldersDirectory() + ApiPath.getPublic())
                        .then()
                        .statusCode(400)
                        .contentType(ContentType.JSON)
                        .spec(ResponseSpecs.errorResponse());
    }

    @Test
    @DisplayName("GET - ошибка 401 в получении списка опубликованных ресурсов")
    public void getListOfPublishedResources_returnsError401() {
                given().spec(withoutAuthSpec)
                        .when()
                        .get(ApiPath.getFilesAndFoldersDirectory() + ApiPath.getPublic())
                        .then()
                        .statusCode(401)
                        .contentType(ContentType.JSON)
                        .spec(ResponseSpecs.errorResponse());
    }

    @Test
    @DisplayName("PUT - успешное опубликование ресурса")
    public void putPublicationResource_returnsOk() {
        try {
            filesClient.createFolder(folder)
                .then()
                .statusCode(201);
            filesClient.getInfoFolder(folder)
                    .then()
                    .statusCode(200);
            given().spec(requestSpec)
                    .queryParam(ApiPath.getPath(), folder)
                    .contentType(ContentType.JSON)
                    .body(BodyPath.getBodyPublicSetting())
                    .when()
                    .put(ApiPath.getFilesAndFoldersDirectory() + ApiPath.getPublish())
                    .then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .spec(ResponseSpecs.linkCreated())
                    .body(LinkModel.getLinkHref(), matchesPattern(ApiPath.getUniversalLink()));
        } finally {
            filesClient.deleteFolder(folder)
                    .then()
                    .statusCode(anyOf(is(204), is(404)));
        }}

    @Test
    @DisplayName("PUT - ошибка 400 в опубликовании ресурса")
    public void putPublicationResource_returns400() {
        try { filesClient.createFolder(folder)
                .then()
                .statusCode(201);
            filesClient.getInfoFolder(folder)
                    .then()
                    .statusCode(200);
            given().spec(requestSpec)
                    .when()
                    .put(ApiPath.getFilesAndFoldersDirectory() + ApiPath.getPublish())
                    .then()
                    .statusCode(400)
                    .contentType(ContentType.JSON)
                    .spec(ResponseSpecs.errorResponse());
        } finally {
            filesClient.deleteFolder(folder)
                    .then()
                    .statusCode(anyOf(is(204), is(404)));
        }}

    @Test
    @DisplayName("PUT - ошибка 401 в опубликовании ресурса")
    public void putPublicationResource_returns401() {
        try { filesClient.createFolder(folder)
                .then()
                .statusCode(201);
            filesClient.getInfoFolder(folder)
                    .then()
                    .statusCode(200);
            given().spec(withoutAuthSpec)
                    .when()
                    .put(ApiPath.getFilesAndFoldersDirectory() + ApiPath.getPublish())
                    .then()
                    .statusCode(401)
                    .contentType(ContentType.JSON)
                    .spec(ResponseSpecs.errorResponse());
        } finally {
            filesClient.deleteFolder(folder)
                    .then()
                    .statusCode(anyOf(is(204), is(404)));
        }}

    @Test
    @DisplayName("GET - успешное получение ссылки для загрузки файлов")
    public void getLinkToDownloadTheFiles_returnsOk() {
        given().spec(requestSpec)
                .queryParam(ApiPath.getPath(), folder)
                .when()
                .get(ApiPath.getFilesAndFoldersDirectory() + ApiPath.getUpload())
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .spec(ResponseSpecs.linkCreated())
                .body(LinkModel.getLinkOperationId(), instanceOf(String.class))
                .body(LinkModel.getLinkHref(), matchesPattern(ApiPath.getUniversalLink()));
    }

    @Test
    @DisplayName("GET - ошибка 400 в получении ссылки для загрузки файлов")
    public void getLinkToDownloadTheFiles_returnsError400() {
        given().spec(requestSpec)
                .when()
                .get(ApiPath.getFilesAndFoldersDirectory() + ApiPath.getUpload())
                .then()
                .statusCode(400)
                .contentType(ContentType.JSON)
                .spec(ResponseSpecs.errorResponse());
    }
    @Test
    @DisplayName("GET - ошибка 401 в получении ссылки для загрузки файлов")
    public void getLinkToDownloadTheFiles_returnsError401() {
                given().spec(withoutAuthSpec)
                        .when()
                        .get(ApiPath.getFilesAndFoldersDirectory() + ApiPath.getUpload())
                        .then()
                        .statusCode(401)
                        .contentType(ContentType.JSON)
                        .spec(ResponseSpecs.errorResponse());
    }

    @Test
    @DisplayName("POST - загрузка файла в Диск по URL")
    public void postUploadingFileToDiskViaURL_returnsOk() {
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
            given().spec(requestSpec)
                    .queryParam(ApiPath.getPath(), second_folder)
                    .queryParam(ApiPath.getUrl(), uploadUrl)
                    .when()
                    .post(ApiPath.getFilesAndFoldersDirectory() + ApiPath.getUpload())
                    .then()
                    .statusCode(202)
                    .contentType(ContentType.JSON)
                    .spec(ResponseSpecs.linkCreated());
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
        }}
}
