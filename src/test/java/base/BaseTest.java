package base;

import clients.FilesAndFoldersClient;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import specs.RequestSpecs;
import java.util.UUID;

public abstract class BaseTest {
    protected static RequestSpecification requestSpec;
    protected static RequestSpecification withoutAuthSpec;
    protected static String folder;
    protected static String second_folder;
    protected static String file;
    protected FilesAndFoldersClient filesClient;

    @BeforeAll
    static void initSpec() {
        requestSpec = RequestSpecs.base();
        withoutAuthSpec = RequestSpecs.withoutAuth();
    }

    @BeforeEach
    void initClients() {
        filesClient = new FilesAndFoldersClient(requestSpec);
    }

    @BeforeEach
    void initNames() {
        folder = "test-" + UUID.randomUUID();
        second_folder = "new_test-" + UUID.randomUUID();
        file = UUID.randomUUID() + ".txt";
    }
}

