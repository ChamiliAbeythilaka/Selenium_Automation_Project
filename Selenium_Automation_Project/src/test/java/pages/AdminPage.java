
package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.WaitHelper;

public class AdminPage {
    WebDriver driver;

    public AdminPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Elements
    @FindBy(xpath = "//label[text()='User Role']/../following-sibling::div//div[@class='oxd-select-text']")
    private WebElement userRoleDropdown;

    @FindBy(xpath = "//div[@role='option']//span[text()='ESS']")
    private WebElement essOption;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    private WebElement employeeNameField;

    @FindBy(xpath = "//input[@autocomplete='off'][@type='text']")
    private WebElement usernameField;

    @FindBy(xpath = "//label[text()='Status']/../following-sibling::div//div[@class='oxd-select-text']")
    private WebElement statusDropdown;

    @FindBy(xpath = "//div[@role='option']//span[text()='Enabled']")
    private WebElement enabledOption;

    @FindBy(xpath = "//input[@type='password'][1]")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@type='password'][2]")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    private WebElement saveButton;

    // Locators for elements needing waits
    private By adminMenuLocator = By.xpath("//span[text()='Admin']");
    private By addButtonLocator = By.xpath("//button[normalize-space()='Add']");

    // Actions
    public void navigateToAddUserForm() {
        WaitHelper.waitVisible(driver, adminMenuLocator).click();
        WaitHelper.waitVisible(driver, addButtonLocator).click();
    }

    public void fillUserForm(String empName, String username, String password) {
        userRoleDropdown.click();
        essOption.click();

        employeeNameField.sendKeys(empName);
        usernameField.sendKeys(username);

        statusDropdown.click();
        enabledOption.click();

        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(password);
    }

    public void clickSave() {
        saveButton.click();
    }
}



