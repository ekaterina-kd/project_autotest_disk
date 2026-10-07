package path;

public final class ApiPath {
    private static final String ROOT_DIRECTORY_DISK = "/disk";
    private static final String FILES_AND_FOLDERS_DIRECTORY = "/disk/resources";

    private static final String COPY = "/copy";
    private static final String DOWNLOAD = "/download";
    private static final String FILES = "/files";
    private static final String LAST_UPLOADED = "/last-uploaded";
    private static final String MOVE = "/move";
    private static final String PUBLIC = "/public";
    private static final String PUBLISH = "/publish";
    private static final String UNPUBLISH = "/unpublish";
    private static final String UPLOAD = "/upload";

    private static final String PUBLIC_DIRECTORY = "/disk/public/resources";
    private static final String BASKET_DIRECTORY = "/disk/trash/resources";
    private static final String OPERATIONS_DIRECTORY = "/disk/operations";

    private static final String ROOT = "/";
    private static final String PATH = "path";
    private static final String URL = "url";
    private static final String FROM = "from";
    private static final String FIELDS = "fields";
    private static final String OVERWRITE = "overwrite";
    private static final String BODY = "body";
    private static final String OPERATION_ID = "operation_id";
    private static final String PUBLIC_KEY = "public_key";

    private static final String APP = "app:/";
    private static final String DISK = "disk:/";
    private static final String BROKEN_JSON = "{ broken json";
    private static final String BOOLEAN_TRUE = "true";
    private static final String UNIVERSAL_LINK = "^https?://.+";
    private static final String ID = "id";

    public static String getRootDirectoryDisk() {
        return ROOT_DIRECTORY_DISK;
    }

    public static String getPublicDirectory() {
        return PUBLIC_DIRECTORY;
    }

    public static String getBasketDirectory() {
        return BASKET_DIRECTORY;
    }

    public static String getOperationsDirectory() {
        return OPERATIONS_DIRECTORY;
    }

    public static String getFilesAndFoldersDirectory() {
        return FILES_AND_FOLDERS_DIRECTORY;
    }

    public static String getApp() {
        return APP;
    }

    public static String getDisk() {
        return DISK;
    }

    public static String getCopy() {
        return COPY;
    }

    public static String getDownload() {
        return DOWNLOAD;
    }

    public static String getFiles() {
        return FILES;
    }

    public static String getLastUploaded() {
        return LAST_UPLOADED;
    }

    public static String getMove() {
        return MOVE;
    }

    public static String getPublic() {
        return PUBLIC;
    }

    public static String getPublish() {
        return PUBLISH;
    }

    public static String getUnpublish() {
        return UNPUBLISH;
    }

    public static String getUpload() {
        return UPLOAD;
    }

    public static String getPath() {
        return PATH;
    }

    public static String getUrl() {
        return URL;
    }

    public static String getFrom() {
        return FROM;
    }

    public static String getFields() {
        return FIELDS;
    }

    public static String getOverwrite() {
        return OVERWRITE;
    }

    public static String getBody() {
        return BODY;
    }

    public static String getOperationId() {
        return OPERATION_ID;
    }

    public static String getPublicKey() {
        return PUBLIC_KEY;
    }

    public static String getBooleanTrue() {
        return BOOLEAN_TRUE;
    }

    public static String getRoot() {
        return ROOT;
    }

    public static String getBrokenJson() {
        return BROKEN_JSON;
    }

    public static String getUniversalLink() {
        return UNIVERSAL_LINK;
    }

    public static String getId() {
        return ID;
    }
}
