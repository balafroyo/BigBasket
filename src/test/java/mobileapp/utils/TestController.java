package mobileapp.utils;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.Augmenter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class TestController {

    public static final String DEFAULT_JSON_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";



    public static Properties getProperties() {
        Properties properties = new Properties();
        File file = new File("./config/Config.properties");
        try {
            FileInputStream fileInput = new FileInputStream(file);
            properties.load(fileInput);
            fileInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }



    public static String getPlatformVersion(AndroidDriver driver) {
        return (String) driver.getCapabilities().getCapability(MobileCapabilityType.PLATFORM_VERSION);

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
