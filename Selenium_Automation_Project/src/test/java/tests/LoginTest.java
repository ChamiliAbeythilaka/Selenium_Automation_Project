package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test(priority = 1)
    public void validLogin() {
        LoginPage lp = new LoginPage(driver);
        boolean success = lp.typeUser("Admin")
                .typePass("admin123")
                .submitAndDetectResult();

        Assert.assertTrue(success, "Expected dashboard, but error banner showed.");
    }

    @Test(priority = 2)
    public void invalidLogin() {
        LoginPage lp = new LoginPage(driver);
        boolean success = lp.typeUser("Admin")
                .typePass("wrongPass")
                .submitAndDetectResult();

        Assert.assertFalse(success, "Expected error banner, but dashboard appeared.");
        Assert.assertTrue(lp.getErrorText().contains("Invalid credentials"));
    }
}
