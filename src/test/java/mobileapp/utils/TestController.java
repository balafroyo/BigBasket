package mobileapp.utils;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.Augmenter;

import java.io.File;
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





    public static void takeScreenShot(AndroidDriver iosDriver, String testName){
        iosDriver =  (AndroidDriver) new Augmenter().augment(iosDriver);
        //Get the screenshot
        File scrFile = ((TakesScreenshot) iosDriver).getScreenshotAs(OutputType.FILE);
        System.out.println("Screenshot taken for "+testName);
        try {
            File testScreenShot = new File("./screenshots/"+testName+".jpg");
            //Copy the file to screenshots folder
            FileUtils.copyFile(scrFile, testScreenShot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
