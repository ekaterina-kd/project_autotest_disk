package specs;

import config.TestConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public final class RequestSpecs {
    private RequestSpecs() {}

    public static RequestSpecification base() {
        return new RequestSpecBuilder()
                .setBaseUri(TestConfig.baseUrl())
                .setBasePath("/" + TestConfig.version())
                .addHeader("Authorization", "QAuth " + TestConfig.token())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }

    public static RequestSpecification withoutAuth() {
        return new RequestSpecBuilder()
                .setBaseUri(TestConfig.baseUrl())
                .setBasePath("/" + TestConfig.version())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }
}
