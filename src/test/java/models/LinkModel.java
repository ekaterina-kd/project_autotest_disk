package models;

public class LinkModel {
    private static final String LINK_METHOD = "method";
    private static final String LINK_HREF = "href";
    private static final String LINK_TEMPLATED = "templated";
    private static final String LINK_OPERATION_ID = "operation_id";
    public static String getLinkMethod() {
        return LINK_METHOD;
    }

    public static String getLinkHref() {
        return LINK_HREF;
    }

    public static String getLinkTemplated() {
        return LINK_TEMPLATED;
    }

    public static String getLinkOperationId() {
        return LINK_OPERATION_ID;
    }
}
