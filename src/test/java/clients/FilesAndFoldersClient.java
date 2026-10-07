package clients;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import path.ApiPath;
import static io.restassured.RestAssured.given;


public class FilesAndFoldersClient {
    private final RequestSpecification spec;

    public FilesAndFoldersClient(RequestSpecification spec) {
        this.spec = spec;
    }

    public Response createFolder(String folder) {
        return given().spec(spec)
                .queryParam(ApiPath.getPath(), folder)
                .when()
                .put(ApiPath.getFilesAndFoldersDirectory());
    }

    public Response createFile(String file) {
        return given().spec(spec)
                .queryParam(ApiPath.getPath(), file)
                .when()
                .put(ApiPath.getFilesAndFoldersDirectory());
    }

    public Response getInfoFolder(String folder) {
        return given().spec(spec)
                .queryParam(ApiPath.getPath(), folder)
                .when()
                .get(ApiPath.getFilesAndFoldersDirectory());
    }

    public Response getInfoFile(String file) {
        return given().spec(spec)
                .queryParam(ApiPath.getPath(), file)
                .when()
                .get(ApiPath.getFilesAndFoldersDirectory());
    }

    public Response deleteFolder(String folder) {
        return given().spec(spec)
                .queryParam(ApiPath.getPath(), folder)
                .when()
                .delete(ApiPath.getFilesAndFoldersDirectory());
    }

    public Response deleteFile(String file) {
        return given().spec(spec)
                .queryParam(ApiPath.getPath(), file)
                .when()
                .delete(ApiPath.getFilesAndFoldersDirectory());
    }
}

