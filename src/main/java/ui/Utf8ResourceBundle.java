package ui;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

final class Utf8ResourceBundle {

    private Utf8ResourceBundle() {
        throw new UnsupportedOperationException("Utility class");
    }

    static ResourceBundle load(Locale locale) {
        try {
            return ResourceBundle.getBundle("labels", locale, new Utf8Control());
        } catch (MissingResourceException e) {
            return ResourceBundle.getBundle("labels", Locale.US, new Utf8Control());
        }
    }

    private static final class Utf8Control extends ResourceBundle.Control {

        @Override
        public ResourceBundle newBundle(String baseName, Locale locale, String format,
                ClassLoader loader, boolean reload) throws IOException {
            String bundleName = toBundleName(baseName, locale);
            String resourceName = toResourceName(bundleName, "properties");
            InputStream stream = loader.getResourceAsStream(resourceName);
            if (stream == null) {
                return null;
            }
            try (InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
                return new PropertyResourceBundle(reader);
            }
        }
    }
}
