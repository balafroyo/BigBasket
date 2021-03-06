package mobileapp.tests;

/**
 * @author froyo
 */

import io.appium.java_client.android.AndroidDriver;
import mobileapp.pageobjects.LoginScreenFactory;
import mobileapp.reporting.ExtentManager;
import mobileapp.reporting.TestLog;
import mobileapp.utils.AppiumController;
import mobileapp.utils.Retry;
import mobileapp.utils.TestController;
import mobileapp.utils.XLSTestDataLoader;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.IOException;
import java.lang.reflect.Method;

public class LoginTest {
    public AndroidDriver driver = null;
    private LoginScreenFactory loginScreenFactory;
    LoginScreenFactory loginFactory;
    XLSTestDataLoader testdataLoader = new XLSTestDataLoader();
   public  String testName;




    @Test(groups = {"sanity"}, dataProvider = "loginData", priority = 2,retryAnalyzer=Retry.class)
    public void onboarding(String username, String password) throws InterruptedException {
        TestLog.testStart(" "+testName, "onboarding method");
        TestLog.stepInfo("welcome");
        String platformVersion = TestController.getPlatformVersion(driver);
        Integer apiLevelFromVersion = TestController.getApiLevelFromVersion(platformVersion);
        TestLog.stepInfo("thank you 1");
        System.out.println("API level:" + apiLevelFromVersion);
        Thread.sleep(8000);
        TestLog.stepInfo("thank you 2");
        loginScreenFactory.loginAndMove(driver, username, password);
        TestLog.stepInfo("thank you 3");
        loginScreenFactory.menuItemNavigate(driver);
        Thread.sleep(8000);

        System.out.println("empty" );
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
        ExtentManager.getReporter().flush();
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
            TestLog.stepInfo("Failed : " + testName + " method");
        } else {
            TestLog.stepInfo("Passed : " + testName + " method");
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
