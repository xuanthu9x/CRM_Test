package Pages;

import keywords.Common;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage extends BasePage{
    public static String url = "https://crm.anhtester.com/admin/authentication";
    private static By inputEmail = By.xpath("//input[@id='email']");
    private static By inputPassword = By.xpath("//input[@id='password']");
    private static By checkboxRememberMe = By.xpath("//label[normalize-space()='Remember me']");
    private static By buttonLogin = By.xpath("//button[normalize-space()='Login']");
    private  static By emailEmptyWarning = By.xpath("//div[normalize-space()='The Email Address field is required.']");
    private  static By passwordEmptyWarning = By.xpath("//div[normalize-space()='The Password field is required.']");
    private  static By email_pwWrongWarning = By.xpath("//div[@class='text-center alert alert-danger']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public static void LoginTest(String email, String password){
        System.out.println("Login");
        Common common = new Common(driver);
        Common.openUrl(url);
        Common.sendKey(inputEmail,email);
        Common.sendKey(inputPassword,password);
        Common.click(buttonLogin);
    }

    public BasePage Login(String email, String password){
        Common common = new Common(driver);
        Common.openUrl(url);
        Common.sendKey(inputEmail, email);
        Common.sendKey(inputPassword, password);
        Common.click(buttonLogin);
        return new BasePage(driver);
    }

    public static void LoginWithRememberMe(String email, String password){
        Common common = new Common(driver);
        Common.openUrl(url);
        Common.sendKey(inputEmail, email);
        Common.sendKey(inputPassword, password);
        boolean checkedRememberMe = driver.findElement(checkboxRememberMe).isSelected();
        if (!checkedRememberMe){
            Common.click(checkboxRememberMe);
        }
        Common.click(buttonLogin);
    }

    public static void verifyLoginSuccess(){
        Common common = new Common(driver);
        Common.waitForPageLoaded();
        Assert.assertTrue(driver.findElement(By.xpath("//span[normalize-space()='Dashboard']")).isDisplayed());
    }

    public static void verifyEmailEmptyWarning(){
        Common common = new Common(driver);
        String emailEmptyWarning = Common.getText(LoginPage.emailEmptyWarning);
        Common.softAssertEqual(emailEmptyWarning,"The Email Address field is required.");
    }

    public  static void verifyPasswordEmptyWarning(){
        Common common = new Common(driver);
        String passwordEmptyWarning = Common.getText(LoginPage.passwordEmptyWarning);
        Common.softAssertEqual(passwordEmptyWarning, "The Password field is required.");
    }

    public static void verifyEmailWrong(){
        Common common = new Common(driver);
        String emailWrongWarning = Common.getText(LoginPage.email_pwWrongWarning);
        Common.softAssertEqual(emailWrongWarning,"Invalid email or password");
    }

    public static void verifyPasswordWrong(){
        Common common = new Common(driver);
        String passwordWrongWarning = Common.getText(LoginPage.email_pwWrongWarning);
        Common.softAssertEqual(passwordWrongWarning,"Invalid email or password");
    }

    public static void verifyEmailFormat(){
        Common common = new Common(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String actualMessage = (String) js.executeScript(
                "return arguments[0].validationMessage;", driver.findElement(LoginPage.inputEmail));
        Common.softAssertEqual(actualMessage, "Please include an '@' in the email address. 'abc' is missing an '@'.");
    }
}
