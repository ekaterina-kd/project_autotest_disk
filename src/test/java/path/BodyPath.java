package path;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BodyPath {
    private static final String BODY_PUBLIC_SETTING;

    static {
        try {
            BODY_PUBLIC_SETTING = Files.readString(Paths.get("src/test/resources/bodies/put_public_settings_body.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getBodyPublicSetting() {
        return BODY_PUBLIC_SETTING;
    }
}
