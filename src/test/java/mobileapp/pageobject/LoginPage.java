package mobileapp.pageobject;

/**
 * @author froyo
 */

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import io.appium.java_client.android.AndroidDriver;
import mobileapp.utils.AppConstants;
import mobileapp.utils.Scroller;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

        Scroller sc = null;
        @FindBy(id = AppConstants.PACKAGE_NAME + AppConstants.LOGIN_BUTTON)
        private WebElement loginButton;
    @FindBy(id = AppConstants.PACKAGE_NAME + AppConstants.LOGIN_EMAIL_ID)
    private WebElement loginEmail;

    @FindBy(id = AppConstants.PACKAGE_NAME + AppConstants.LOGIN_PASSWORD)
    private WebElement loginPassword;
    @FindBy(id = AppConstants.PACKAGE_NAME + AppConstants.LOGIN_CONTINUE)
    private WebElement loginContinue;


    ExtentTest test;
    ExtentReports extent;

        public void loginAndMove(AndroidDriver driver, ExtentReports extent) throws InterruptedException {
            test = extent.startTest(" Get Loan offer - No Data User");
            sc = new Scroller();
            loginButton.click();
            Thread.sleep(2000);
            loginEmail.sendKeys("credr3.whizdm@gmail.com");
            Thread.sleep(2000);
            loginPassword.sendKeys("mangomist");
            Thread.sleep(2000);
            loginContinue.click();
            Thread.sleep(7000);
            sc.scrollDown(driver);
            Thread.sleep(7000);

            sc.scrollDown(driver);
        }
}
