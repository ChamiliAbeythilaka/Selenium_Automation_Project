//package tests;
//
//import base.BaseTest;
//import org.openqa.selenium.By;
//import org.testng.annotations.Test;
//import pages.AdminPage;
//import pages.LoginPage;
//import org.testng.Assert;
//import util.WaitHelper;
//
//
//public class AddUserTest extends BaseTest {
//
//    @Test(priority = 1)
//    public void validLoginTest() {
//        LoginPage loginPage = new LoginPage(driver);
//        boolean success = loginPage
//                .typeUser("Admin")
//                .typePass("admin123")
//                .submitAndDetectResult();
//
//        Assert.assertTrue(success, "Login failed – dashboard not loaded.");
//    }
//
//
//    @Test(priority = 3, dependsOnMethods = "validLoginTest")
//    public void navigateToAddUserForm() {
//        AdminPage adminPage = new AdminPage(driver);
//
//        String empName = "Paul Collings";
//        String username = "testuser" + System.currentTimeMillis() % 1000;
//        String password = "Password123!";
//
//        adminPage.fillUserForm(empName, username, password);
//        adminPage.clickSave();
//    }
//
//        // You can add an assert here to confirm the user was added
//    }
//

//---------------------------------------------------------------------------
//package tests;
//
//import base.BaseTest;
//import org.testng.annotations.Test;
//import org.testng.Assert;
//import pages.AdminPage;
//import pages.LoginPage;
//
//public class AddUserTest extends BaseTest {
//
//    @Test(priority = 1)
//    public void validLoginTest() {
//        LoginPage loginPage = new LoginPage(driver);
//        boolean success = loginPage
//                .typeUser("Admin")
//                .typePass("admin123")
//                .submitAndDetectResult();
//
//        Assert.assertTrue(success, "Login failed – dashboard not loaded.");
//    }
//
//    @Test(priority = 3, dependsOnMethods = "validLoginTest")
//    public void navigateToAddUserForm() {
//        AdminPage adminPage = new AdminPage(driver);
//
//        // ✅ Navigate to Admin > Add User
//        adminPage.navigateToAddUserForm();
//
//        // Fill form
//        String empName = "Paul Collings";
//        String username = "testuser" + System.currentTimeMillis() % 1000;
//        String password = "Password123!";
//
//        adminPage.fillUserForm(empName, username, password);
//                adminPage.clickSave();
//                }
//                }


package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AdminPage;
import pages.LoginPage;
import util.WaitHelper;

public class AddUserTest extends BaseTest {

    @Test(priority = 1)
    public void validLoginTest() {
        boolean loggedIn = new LoginPage(driver)
                .typeUser("Admin")
                .typePass("admin123")
                .submitAndDetectResult();

        Assert.assertTrue(loggedIn, "Login failed – dashboard not loaded.");

        /* wait for any dashboard widgets to settle so Admin tab is clickable */
        WaitHelper.waitVisible(driver, By.xpath("//a[@href='/web/index.php/admin/viewAdminModule']"));
        System.out.println("✅ Logged in & dashboard ready.");
    }

    @Test(priority = 3, dependsOnMethods = "validLoginTest")
    public void addNewUserTest() {

        AdminPage admin = new AdminPage(driver);

        /* 1. open Add‑User form */
        admin.navigateToAddUserForm();

        /* 2. fill & save */
        String empName  = "Paul Collings";                       // existing employee
        String username = "testuser" + (System.currentTimeMillis() % 1000);
        String password = "Password123!";

        admin.fillUserForm(empName, username, password);
        admin.clickSave();

        /* 3. simple verification: toast or page source contains success */
        boolean saved = WaitHelper.waitVisible(driver,
                By.xpath("//div[contains(text(),'Successfully Saved')]")) != null;

        Assert.assertTrue(saved, "User not added successfully.");
        System.out.println("✅ User created: " + username);
    }
}
