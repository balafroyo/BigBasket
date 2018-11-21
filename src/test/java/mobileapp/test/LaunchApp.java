package mobileapp.test;

/**
 * @author froyo
 */
import com.relevantcodes.extentreports.ExtentReports;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;
import mobileapp.pageobject.LoginPage;
import mobileapp.utils.AndroidSetup;
import mobileapp.utils.AndroidVersionUtil;
import mobileapp.utils.ScreenShotPathProvider;
import mobileapp.utils.Util;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.IOException;

public class LaunchApp {
    ExtentReports extent;
    ExtentTest logger;
    public AndroidDriver driver = null;
    private LoginPage loginPage;



    @Test(priority = 1)
    public void onboarding() throws InterruptedException {

        logger = extent.startTest("LauncherActivity");
        String platformVersion = Util.getPlatformVersion(driver);
        Integer apiLevelFromVersion = AndroidVersionUtil.getApiLevelFromVersion(platformVersion);
        System.out.println("API level:" + apiLevelFromVersion);
        Thread.sleep(8000);
        System.out.println("welcome to my world");
        loginPage.loginAndMove(driver,extent);
        System.out.println("second entry to my world");






    }


    @BeforeTest
    public void startreport() {
        extent = new ExtentReports(System.getProperty("user.dir") + "test-output/NODatauser.html", true);
        extent
                .addSystemInfo("Host Name", "Bala")
                .addSystemInfo("Environment", "QA")
                .addSystemInfo("User Name", "Bala");
        extent.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));

    }

    @AfterMethod
    public void getResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
            logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
            //To capture screenshot path and store the path of the screenshot in the string "screenshotPath"
            //We do pass the path captured by this method in to the extent reports using "logger.addScreenCapture" method.

            if (StringUtils.isNotEmpty(ScreenShotPathProvider.getCurrentPath())) {
                String screenshotPath = AndroidSetup.getDestination(ScreenShotPathProvider.getCurrentPath());
                //To add it in the extent report
                logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));
            }
        } else if (result.getStatus() == ITestResult.SKIP) {
            logger.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
        }
        // ending test
        //endTest(logger) : It ends the current test and prepares to create HTML report
        extent.endTest(logger);
        extent.flush();
    }

    @BeforeSuite
    public void startAppium() throws IOException, InterruptedException {
        AndroidSetup.stopAppium();
        driver = AndroidSetup.startAppium();

        loginPage = PageFactory.initElements(driver, LoginPage.class);

        extent = new ExtentReports(System.getProperty("user.dir") + "test-output/datauser.html", true);
        extent
                .addSystemInfo("Host Name", "Froyo")
                .addSystemInfo("Environment", "MySpace")
                .addSystemInfo("User Name", "Froyo");
        extent.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
    }

    @AfterSuite

    public void stopAppium() throws IOException, InterruptedException {

        driver.quit();
        AndroidSetup.stopAppium();
    }


}
