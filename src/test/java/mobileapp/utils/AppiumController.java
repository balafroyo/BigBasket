package mobileapp.utils;
/**
 * @author froyo
 */

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class AppiumController {


    // private static Process process;
    private static String folderPath = "";
    //  private static String APPIUMSERVERSTART = "appium";
   private String PATH =System.getProperty("user.dir") + "/resources/bigbasketNovember.apk";

    public static AndroidDriver startAppium() throws IOException, InterruptedException {


        //creating new folder path for every time we start the tests suite
        //the folder name will be created as per the time the tests started
        //folder name: screenshots_dd-MM-yyyy_hhmmss
        Date currentTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_hhmmss");
        folderPath = sdf.format(currentTime);
        folderPath = "screenshots_" + folderPath;
        AndroidDriver driver = null;
        AppiumDriverLocalService driverLocalService;
        Properties properties = TestController.getProperties();

        AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
        service.start();
        System.out.println("driverLocalService started successfully");


        String url = service.getUrl().toString();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");


        capabilities.setCapability(MobileCapabilityType.APP, "/Users/balakrishnan/Documents/workspace/BigBasket/resources/bigbasketNovember.apk");
        capabilities.setCapability("platformName", properties.getProperty("PLATFORM_NAME"));
        capabilities.setCapability("platformVersion", properties.getProperty("PLATFORM_VERSION"));
        capabilities.setCapability("deviceName", properties.getProperty("DEVICE_NAME"));
        capabilities.setCapability("appPackage", properties.getProperty("APP_PACKAGE"));
        capabilities.setCapability("autoGrantPermission", "true");
        capabilities.setCapability("unicodeKeyboard", true);
        capabilities.setCapability("resetKeyboard", true);
        try {
            driver = new AndroidDriver(new URL(url), capabilities);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            return driver;
        } catch (MalformedURLException e) {
            System.out.println("Exception in initialisation of androidDriver");
            e.printStackTrace();
            return null;
        }
    }

    public static void stopAppium() {
        List<String> list = new ArrayList<String>();
        list.add("killall");
        list.add("node");
        ProcessBuilder pb = new ProcessBuilder(list);
        try {
            pb.start();
            System.out.println("Running session of appium got killed");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


}


