package mobileapp.utils;

public interface Config {
    String APP_ACTIVITY = "com.whizdm.moneyview.loans.launcher.LauncherActivity";

    String PLATFORM_NAME="Android";

    String APK_PATH="/Users/avantigada/Desktop/appium-loans-automation/apps/app-debug-qav49.apk";

    String FAILED_SCREENSHOT_FOLDER_PATH = System.getProperty("user.dir") + "/FailedTestsScreenshots/";

    String ENVIRONMENT_ENDPOINT = "http://lstaging2.whizdm.com";
   // String ENVIRONMENT_ENDPOINT = "http://10.20.0.139:8080";

}
