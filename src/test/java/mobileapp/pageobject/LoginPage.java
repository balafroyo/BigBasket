package mobileapp.pageobject;

/**
 * @author froyo
 */

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import io.appium.java_client.android.AndroidDriver;
import mobileapp.utils.AppConstants;
import mobileapp.utils.ExcelUtils;
import mobileapp.utils.Scroller;
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


    ExtentTest test;
    ExtentReports extent;

    public void loginAndMove(AndroidDriver driver, ExtentReports extent) throws InterruptedException {
        test = extent.startTest("method 1");
        sc = new Scroller();
        loginButton.click();
        Thread.sleep(2000);
        loginEmail.sendKeys("credr3.whizdm@gmail.com");
        Thread.sleep(2000);
        loginPassword.sendKeys("mangomist");
        Thread.sleep(2000);
        loginContinue.click();
        Thread.sleep(7000);
        // sc.scrollDown(driver);
        // Thread.sleep(7000);
        //sc.scrollDown(driver);
    }


    public void menuItemNavigate(AndroidDriver driver, ExtentReports extent) throws InterruptedException {

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



    @DataProvider(name = "loginData")
    public Object[][] getLoginData(Method m)  throws Exception{
        ExcelUtils.setExcelFile(AppConstants.DATAEXCELFILEPATH,"Sheet1");

        sTestCaseName = this.toString();

        // From above method we get long test case name including package and class name etc.

        // The below method will refine your test case name, exactly the name use have used

        sTestCaseName = ExcelUtils.getTestCaseName(this.toString());

        // Fetching the Test Case row number from the Test Data Sheet

        // Getting the Test Case name to get the TestCase row from the Test Data Excel sheet

        iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName,0);

        Object[][] testObjArray = ExcelUtils.getTableArray("D://ToolsQA//OnlineStore//src//testData//TestData.xlsx","Sheet1",iTestCaseRow);

        return (testObjArray);

    }

}
