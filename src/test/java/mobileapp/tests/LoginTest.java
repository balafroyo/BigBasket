package mobileapp.tests;

/**
 * @author froyo
 */

import io.appium.java_client.android.AndroidDriver;
import mobileapp.pageobjects.LoginScreenFactory;
import mobileapp.utils.*;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

public class LoginTest {
    public AndroidDriver driver = null;
    private LoginScreenFactory loginScreenFactory;
    LoginScreenFactory loginFactory;
    XLSTestDataLoader testdataLoader = new XLSTestDataLoader();

    String testName;
    Logger log = Logger.getLogger("devpinoyLogger");

    {
        PropertyConfigurator.configure("./config/log4j.properties");
    }

    @Test(priority = 1)
    public void testLog4J() {
        System.out.println("inside the log4j method");
        log.debug("Debug Message");
        log.error("Error Message");
        log.fatal("Fatal Message");
        log.info("Info Message");
        System.out.println("log.isInfoEnabled() -> " + log.isInfoEnabled());
        System.out.println("log.isDebugEnabled() -> " + log.isDebugEnabled());
        System.out.println("log.isTraceEnabled() -> " + log.isTraceEnabled());
    }


    @Test(groups = {"sanity"}, dataProvider = "loginData", priority = 2,retryAnalyzer=Retry.class)
    public void onboarding(String username, String password) throws InterruptedException {
        String platformVersion = TestController.getPlatformVersion(driver);
        Integer apiLevelFromVersion = AndroidVersionUtil.getApiLevelFromVersion(platformVersion);
        log.debug("thank you 1");
        System.out.println("API level:" + apiLevelFromVersion);
        Thread.sleep(8000);
        log.debug("thank you 2");
        System.out.println("welcome to my world");
        loginScreenFactory.loginAndMove(driver, username, password);
        log.debug(
                "thank you 3");
        System.out.println("second entry to my world");
        loginScreenFactory.menuItemNavigate(driver);
        Thread.sleep(8000);

    }

    @BeforeSuite
    public void startAppium() throws IOException, InterruptedException {
        AppiumController.stopAppium();
        driver = AppiumController.startAppium();
        loginScreenFactory = PageFactory.initElements(driver, LoginScreenFactory.class);

    }

    @AfterSuite

    public void stopAppium() throws IOException, InterruptedException {
        driver.quit();
        AppiumController.stopAppium();
    }

    @BeforeMethod
    public void getMethodName(Method method) {

        testName = method.getName();
    }

    @AfterMethod
    public void verifyTest(ITestResult result) {
        //taking screen shot in failed case
        if (result.getStatus() == ITestResult.FAILURE) {
            TestController.takeScreenShot(driver, testName);
            log.info("Failed : " + testName + " method");
        } else {
            log.info("Passed : " + testName + " method");
        }
    }


    @DataProvider(name = "loginData")
    public Object[][] getLoginData(Method m) {
        Object[][] retObjArr = null;
        String str = System.getProperty("user.dir");
        System.out.println("printing the location string---" + str);
        System.out.println(str + "/resources/TestData.xls -> " + "login -> " + m.getName());
        try {
            retObjArr = testdataLoader.getTableArray(
                    System.getProperty("user.dir") + "/resources/TestData.xls", "login", m.getName());
        } catch (Exception e) {
            System.out.println("Error while reading data from xls " + e);
        }
        return retObjArr;
    }


}
