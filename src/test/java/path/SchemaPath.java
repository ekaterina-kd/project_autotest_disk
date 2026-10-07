package path;

public class SchemaPath {
    private static final String SCHEMA_METADATA_FILES = "schemas/get_metadata_files_response.json";
    private static final String SCHEMA_METADATA_USER_DISK = "schemas/get_metadata_user_disk_response.json";
    private static final String SCHEMA_METADATA_PUBLIC_FILES_OR_DIRECTORY = "schemas/get_metadata_public_files_or_directory_responce.json";
    private static final String SCHEMA_LIST_FILES_ORDERED_BY_UPLOAD_DATE = "schemas/get_list_of_files_ordered_by_upload_date_response.json";
    private static final String SCHEMA_LIST_FILES_ORDERED_BY_NAME = "schemas/get_list_of_files_ordered_by_name_response.json";
    private static final String SCHEMA_LIST_PUBLISHED_RESOURCES = "schemas/get_list_of_published_resources_response.json";
    private static final String SCHEMA_CONTENTS_OF_THE_BASKET = "schemas/get_contents_of_the_basket_response.json";
    public static String getSchemaMetadataFiles() {
        return SCHEMA_METADATA_FILES;
    }

    public static String getSchemaMetadataUserDisk() {
        return SCHEMA_METADATA_USER_DISK;
    }

    public static String getSchemaMetadataPublicFilesOrDirectory() {
        return SCHEMA_METADATA_PUBLIC_FILES_OR_DIRECTORY;
    }

    public static String getSchemaListFilesOrderedByUploadDate() {
        return SCHEMA_LIST_FILES_ORDERED_BY_UPLOAD_DATE;
    }

    public static String getSchemaListFilesOrderedByName() {
        return SCHEMA_LIST_FILES_ORDERED_BY_NAME;
    }

    public static String getSchemaListPublishedResources() {
        return SCHEMA_LIST_PUBLISHED_RESOURCES;
    }

    public static String getSchemaContentsOfTheBasket() {
        return SCHEMA_CONTENTS_OF_THE_BASKET;
    }
}
