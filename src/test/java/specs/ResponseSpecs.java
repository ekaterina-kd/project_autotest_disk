package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import models.ErrorModel;
import models.LinkModel;
import static org.hamcrest.Matchers.*;

public final class ResponseSpecs {
    private ResponseSpecs() {}

    public static ResponseSpecification linkCreated() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectBody(LinkModel.getLinkMethod(),    instanceOf(String.class))
                .expectBody(LinkModel.getLinkHref(),      instanceOf(String.class))
                .expectBody(LinkModel.getLinkTemplated(), instanceOf(Boolean.class))
                .build();
    }

    public static ResponseSpecification errorResponse() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectBody(ErrorModel.getErrorError(),       instanceOf(String.class))
                .expectBody(ErrorModel.getErrorDescription(), instanceOf(String.class))
                .expectBody(ErrorModel.getErrorMessage(),     instanceOf(String.class))
                .expectBody(ErrorModel.getErrorDetails(),     anyOf(nullValue(), instanceOf(Object.class)))
                .build();
    }
}
