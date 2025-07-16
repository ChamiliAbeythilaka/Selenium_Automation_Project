
package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import util.WaitHelper;

public class LoginPage {
    private final WebDriver driver;

    @FindBy(name = "username") private WebElement user;
    @FindBy(name = "password") private WebElement pass;
    @FindBy(css  = "button[type='submit']") private WebElement loginBtn;

    private static final By DASHBOARD_HEADER = By.cssSelector("h6.oxd-text--h6");
    private static final By ERROR_BANNER     = By.cssSelector(".oxd-alert-content-text");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LoginPage typeUser(String u) { user.clear(); user.sendKeys(u); return this; }
    public LoginPage typePass(String p) { pass.clear(); pass.sendKeys(p); return this; }

    /** click and return true = success, false = error */
    public boolean submitAndDetectResult() {
        loginBtn.click();
        try {                              // try dashboard first
            WaitHelper.waitVisible(driver, DASHBOARD_HEADER);
            return true;
        } catch (TimeoutException ignore) {     // fall back to error banner
            WaitHelper.waitVisible(driver, ERROR_BANNER);
            return false;
        }
    }

    public String getErrorText() {
        return driver.findElement(ERROR_BANNER).getText();
    }
}
