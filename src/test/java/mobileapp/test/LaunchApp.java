package mobileapp.test;

/**
 * @author froyo
 */

import io.appium.java_client.android.AndroidDriver;
import mobileapp.pageobject.LoginPage;
import mobileapp.utils.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;

public class LaunchApp {
    public AndroidDriver driver = null;
    private LoginPage loginPage;
    LoginPage loginFactory;
    XLSTestDataLoader testdataLoader = new XLSTestDataLoader();

    @Test(groups = {"sanity"},dataProvider="loginData")
    public void onboarding(String username, String password) throws InterruptedException
    {
        String platformVersion = TestController.getPlatformVersion(driver);
        Integer apiLevelFromVersion = AndroidVersionUtil.getApiLevelFromVersion(platformVersion);
        System.out.println("API level:" + apiLevelFromVersion);
        Thread.sleep(8000);
        System.out.println("welcome to my world");
        loginPage.loginAndMove(driver, username, password);
        System.out.println("second entry to my world");
        loginPage.menuItemNavigate(driver);
        Thread.sleep(8000);

    }


    @BeforeSuite
    public void startAppium() throws IOException, InterruptedException {
        AppiumController.stopAppium();
        driver = AppiumController.startAppium();
        loginPage = PageFactory.initElements(driver, LoginPage.class);

    }

    @AfterSuite

    public void stopAppium() throws IOException, InterruptedException {
        driver.quit();
        AppiumController.stopAppium();
    }


    @DataProvider(name = "loginData")
    public Object[][] getLoginData(Method m) {
        Object[][] retObjArr = null;
        String str = System.getProperty("user.dir");
        System.out.println("printing the location string---"+str);
        System.out.println(str + "/resources/TestData.xls -> "+"login -> "+ m.getName());
        try {
            retObjArr = testdataLoader.getTableArray(
                    System.getProperty("user.dir") + "/resources/TestData.xls","login", m.getName());
        } catch (Exception e) {
            System.out.println("Error while reading data from xls " + e);
        }
        return retObjArr;
    }


}
