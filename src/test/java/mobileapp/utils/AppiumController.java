package mobileapp.utils;
/**
 * @author froyo
 */

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AppiumController {


    private static Process process;
    private static String folderPath = "";
    private static String APPIUMSERVERSTART = "appium";

    public static AndroidDriver startAppium() throws IOException, InterruptedException {
        //creating new folder path for every time we start the test suite
        //the folder name will be created as per the time the test started
        //folder name: screenshots_dd-MM-yyyy_hhmmss
        Date currentTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_hhmmss");
        folderPath = sdf.format(currentTime);
        folderPath = "screenshots_" + folderPath;
        AndroidDriver driver = null;
        AppiumDriverLocalService driverLocalService;


        AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
        service.start();
        System.out.println("driverLocalService started successfully");



        String url = service.getUrl().toString();
        DesiredCapabilities capabilities = new DesiredCapabilities();
//com.bigbasket.mobileapp:id/daimajia_slider_image


        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0.1");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "NEXUS 5");
        capabilities.setCapability("appPackage", "com.bigbasket.mobileapp");
  //     capabilities.setCapability("appActivity", "com.whizdm.moneyview.loans.launcher.LauncherActivity");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/balakrishnan/Desktop/APK/bigbasketNovember.apk");
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

    public static String takeScreenShot(WebDriver wdriver, String screenshotName) throws IOException {
        wdriver = new Augmenter().augment(wdriver);

        //Get the screenshot
        File scrFile = ((TakesScreenshot) wdriver).getScreenshotAs(OutputType.FILE);
        System.out.println("Screenshot taken for " + screenshotName);

        //after execution, you could see a folder "FailedTestsScreenshots" under src folder
        String destination = getDestination(screenshotName);
        File finalDestination = new File(destination);

        FileUtils.copyFile(scrFile, finalDestination);
        //Returns the captured file path
        return destination;
    }

    public static String getDestination(String screenshotName) {
        String destination;
        if (StringUtils.isNotEmpty(folderPath))
            destination = Config.FAILED_SCREENSHOT_FOLDER_PATH + folderPath + "/" + screenshotName + ".png";
        else
            destination = Config.FAILED_SCREENSHOT_FOLDER_PATH + screenshotName + ".png";

        return destination;
    }
}


