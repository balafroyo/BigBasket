package mobileapp.utils;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.lang3.StringUtils;
import java.io.IOException;


public class TestController {

    public static final String DEFAULT_JSON_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    public static void hidekeyboard(AndroidDriver driver) {
        try {
            driver.hideKeyboard();
        } catch (Exception e) {
            /*e.printStackTrace();*/
        }
    }


    public static String shaveNonNumeric(String s) {
        return s.replaceAll("[^\\d.]", "");

    }


    public static String shaveQuotes(String res) {
//        return res.substring(1,res.length()-1);
        return res.replaceAll("^\"|\"$", "");
    }



    public static String getPlatformVersion(AndroidDriver driver) {
        return (String) driver.getCapabilities().getCapability(MobileCapabilityType.PLATFORM_VERSION);

    }



    public static double getDouble(String value, double defaultValue) {
        if (StringUtils.isEmpty(value))
            return defaultValue;

        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return defaultValue;
        }
    }





    public static void takeScreenShot(AndroidDriver driver) {
        try {
            AppiumController.takeScreenShot(driver, ScreenShotPathProvider.generateNewPath());
        } catch (IOException e) {
            e.printStackTrace();
            ScreenShotPathProvider.makeCurrentPathEmpty();
        }
    }
}
