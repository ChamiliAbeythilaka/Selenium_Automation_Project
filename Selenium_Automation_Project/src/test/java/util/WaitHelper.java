
package util;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

/** Reusable explicit‑wait helpers */
public final class WaitHelper {
    private WaitHelper() { }

    /** Wait until element is visible, then return it */
    public static WebElement waitVisible(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }



}
