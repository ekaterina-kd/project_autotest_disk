package models;

public class ErrorModel {
    private static final String ERROR_ERROR = "error";
    private static final String ERROR_DESCRIPTION = "description";
    private static final String ERROR_MESSAGE = "message";
    private static final String ERROR_DETAILS = "details";

    public static String getErrorError() {
        return ERROR_ERROR;
    }

    public static String getErrorDescription() {
        return ERROR_DESCRIPTION;
    }

    public static String getErrorMessage() {
        return ERROR_MESSAGE;
    }

    public static String getErrorDetails() {
        return ERROR_DETAILS;
    }
}
