package mobileapp.utils;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;

import java.time.Duration;

/**
 * @author froyo
 */
public class Scroller {

    public void scrollDown(AndroidDriver driver) throws InterruptedException {
        Dimension size;

        size = driver.manage().window().getSize();

        //Find swipe start and end point from screen's width and height.
        //Find startx point which is at bottom  of screen.
        int starty = (int) (size.height * 0.90);

        //Find endx point which is at top  of screen.
        int endy = (int) (size.height * 0.30);

        //Find horizontal point where you wants to swipe. It is in middle of screen height.
        int startx = size.width / 4;

        //Swipe from bottom to top.

        (new TouchAction(driver))
                .press(PointOption.point(startx, starty))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(startx, endy))
                .release()
                .perform();
        // driver.swipe(startx, starty, startx, endy, 1000);
        Thread.sleep(2000);
    }
}
