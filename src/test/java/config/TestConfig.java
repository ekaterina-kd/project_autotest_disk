package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class TestConfig {
    private static final Properties PROPS = new Properties();

    static {
        try (InputStream is = TestConfig.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (is == null) {
                throw new IllegalStateException(
                        "config.properties не найден");
            }
            PROPS.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось прочитать config.properties", e);
        }
    }
    private TestConfig() {}

    public static String baseUrl() {
        return require("base.url");
    }

    public static String version() {
        return require("version");
    }

    public static String token() {
        return require("oauth.token");
    }

    private static String require(String key) {
        String value = PROPS.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Не задан параметр '" + key + "' в config.properties");
        }
        return value;
    }
}
