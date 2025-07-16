//package base;
//
//import config.ConfigReader;
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.annotations.*;
//
//import java.time.Duration;
//
//public class BaseTest {
//    protected WebDriver driver;
//
//    @BeforeMethod
//    public void setup() {
//        String browser = ConfigReader.get("browser");
//
//        switch (browser.toLowerCase()) {
//            case "firefox" -> {
//                WebDriverManager.firefoxdriver().setup();
//                driver = new FirefoxDriver();
//            }
//            case "edge" -> {
//                WebDriverManager.edgedriver().setup();
//                driver = new EdgeDriver();
//            }
//            default -> {
//                WebDriverManager.chromedriver().setup();
//                driver = new ChromeDriver();
//            }
//        }
//
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
//                Long.parseLong(ConfigReader.get("implicitWait"))));
//        driver.manage().window().maximize();
//        driver.get(ConfigReader.get("baseUrl"));
//    }
//
////    @AfterMethod
////    public void tearDown() {
////        if (driver != null) driver.quit();
////    }
//
//    @AfterClass(alwaysRun = true)
//    public void quitDriver() {
//        if (driver != null && Boolean.parseBoolean(ConfigReader.get("closeBrowser"))) {
//            driver.quit();   // <- this is the critical line
//            driver = null;
//        }
//    }
//
//    }
//
//

package base;

import config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;

import java.time.Duration;

public abstract class BaseTest {

    protected static WebDriver driver;

    @BeforeClass
    public void setUp() {
        String browser = ConfigReader.get("browser");

        if (driver == null) {
            switch (browser.toLowerCase()) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
                    Long.parseLong(ConfigReader.get("implicitWait"))
            ));
        }
    }

    @BeforeMethod
    public void openHomePage() {
        driver.get(ConfigReader.get("baseUrl"));
    }

    @AfterMethod
    public void logoutIfLoggedIn() {
        try {
            if (driver.getCurrentUrl().contains("dashboard")) {
                driver.findElement(By.cssSelector("i.oxd-userdropdown-icon")).click();
                driver.findElement(By.linkText("Logout")).click();
            }
        } catch (Exception ignored) {
            // Already logged out or elements not found
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null && Boolean.parseBoolean(ConfigReader.get("closeBrowser"))) {
            driver.quit();
            driver = null;
        }
    }
}
