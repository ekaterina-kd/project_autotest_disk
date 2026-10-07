package models;

public class StatusModel {
    private static final String STATUS_OPERATION = "status";
    private static final String OPERATION_SUCCESSFULLY_COMPLETED = "success";
    private static final String OPERATION_FAILED = "failed";
    private static final String OPERATION_IN_PROGRESS = "in-progress";

    public static String getStatusOperation() {
        return STATUS_OPERATION;
    }

    public static String getOperationSuccessfullyCompleted() {
        return OPERATION_SUCCESSFULLY_COMPLETED;
    }

    public static String getOperationFailed() {
        return OPERATION_FAILED;
    }

    public static String getOperationInProgress() {
        return OPERATION_IN_PROGRESS;
    }
}
