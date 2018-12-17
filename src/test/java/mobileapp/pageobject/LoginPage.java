package mobileapp.pageobject;

/**
 * @author froyo
 */

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import io.appium.java_client.android.AndroidDriver;
import mobileapp.utils.AppConstants;
import mobileapp.utils.Scroller;
import mobileapp.utils.XLSTestDataLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class LoginPage {

    private String sTestCaseName;

    private int iTestCaseRow;



    Scroller sc = null;
    @FindBy(id = AppConstants.PACKAGE_NAME + AppConstants.LOGIN_BUTTON)
    private WebElement loginButton;
    @FindBy(id = AppConstants.PACKAGE_NAME + AppConstants.LOGIN_EMAIL_ID)
    private WebElement loginEmail;

    @FindBy(id = AppConstants.PACKAGE_NAME + AppConstants.LOGIN_PASSWORD)
    private WebElement loginPassword;
    @FindBy(id = AppConstants.PACKAGE_NAME + AppConstants.LOGIN_CONTINUE)
    private WebElement loginContinue;





    public void loginAndMove(AndroidDriver driver, String username, String password) throws InterruptedException {
       // test = extent.startTest("method 1");
        sc = new Scroller();
        loginButton.click();
        Thread.sleep(2000);
        loginEmail.sendKeys("credr3.whizdm@gmail.com");
       // loginEmail.sendKeys(username);
        Thread.sleep(2000);
       loginPassword.sendKeys("mangomist");
       //loginPassword.sendKeys(password);
        Thread.sleep(2000);
        loginContinue.click();
        Thread.sleep(7000);
        // sc.scrollDown(driver);
        // Thread.sleep(7000);
        //sc.scrollDown(driver);
    }


    public void menuItemNavigate(AndroidDriver driver) throws InterruptedException {

        WebElement homeMenu = driver.findElement(By.xpath("//android.widget.TextView[@text='Home']"));
        WebElement categoryMenu = driver.findElement(By.xpath("//android.widget.TextView[@text='Categories']"));
        WebElement searchMenu = driver.findElement(By.xpath("//android.widget.TextView[@text='Search']"));
        WebElement offersMenu = driver.findElement(By.xpath("//android.widget.TextView[@text='Offers']"));
        WebElement basketMenu = driver.findElement(By.xpath("//android.widget.TextView[@text='Basket']"));

        categoryMenu.click();
        Thread.sleep(3000);

       /* searchMenu.click();
        Thread.sleep(4000);

        offersMenu.click();
        Thread.sleep(4000);

        basketMenu.click();
        Thread.sleep(4000);

        homeMenu.click();
        Thread.sleep(4000);*/

    }


    public void categoryMenuItem(AndroidDriver driver, ExtentReports extent)throws Exception{


        sc.scrollDown(driver);

        Thread.sleep(4000);
        sc.scrollDown(driver);

        Thread.sleep(4000);

    }



}
