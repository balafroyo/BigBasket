package mobileapp.utils;

import java.util.UUID;

/**
 * Util class to create a new path every time we need
 * to generate a path for screenshot via appium
 * <p>
 * For this we are using @{@link UUID} to generate
 * a unique name every time we do the testing
 */
public class ScreenShotPathProvider {
    private static String path = "";

    public static String generateNewPath() {
        path = UUID.randomUUID().toString();
        return path;
    }

    public static String getCurrentPath() {
        return path;
    }

    public static void makeCurrentPathEmpty() {
        path = "";
    }
}
