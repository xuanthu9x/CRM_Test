package Pages;

import Common.CommonAction;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage {
    private static WebDriver driver;
    public static String url = "https://crm.anhtester.com/admin/authentication";
    private static By inputEmail = By.xpath("//input[@id='email']");
    private static By inputPassword = By.xpath("//input[@id='password']");
    private static By checkboxRememberMe = By.xpath("//label[normalize-space()='Remember me']");
    private static By buttonLogin = By.xpath("//button[normalize-space()='Login']");
    private  static By emailEmptyWarning = By.xpath("//div[normalize-space()='The Email Address field is required.']");
    private  static By passwordEmptyWarning = By.xpath("//div[normalize-space()='The Password field is required.']");
    private  static By email_pwWrongWarning = By.xpath("//div[@class='text-center alert alert-danger']");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }
    public static void Login(String email, String password){
        driver.get(url);
        CommonAction common = new CommonAction(driver);
        CommonAction.sendKey(inputEmail,email);
        CommonAction.sendKey(inputPassword, password);
        CommonAction.click(buttonLogin);
    }

    public static void LoginWithRememberMe(String email, String password){
        driver.get(url);
        CommonAction common = new CommonAction(driver);
        CommonAction.sendKey(inputEmail, email);
        CommonAction.sendKey(inputPassword, password);
        boolean checkedRememberMe = driver.findElement(checkboxRememberMe).isSelected();
        if (!checkedRememberMe){
            CommonAction.click(checkboxRememberMe);
        }
        CommonAction.click(buttonLogin);
    }

    public static void verifyLoginSuccess(){
        Assert.assertTrue(driver.findElement(By.xpath("//span[normalize-space()='Dashboard']")).isDisplayed());
    }

    public static void verifyEmailEmptyWarning(){
        String emailEmptyWarning = CommonAction.getText(LoginPage.emailEmptyWarning);
        CommonAction.softAssertEqual(emailEmptyWarning,"The Email Address field is required.");
    }

    public  static void verifyPasswordEmptyWarning(){
        String passwordEmptyWarning = CommonAction.getText(LoginPage.passwordEmptyWarning);
        CommonAction.softAssertEqual(passwordEmptyWarning, "The Password field is required.");
    }

    public static void verifyEmailWrong(){
        String emailWrongWarning = CommonAction.getText(LoginPage.email_pwWrongWarning);
        CommonAction.softAssertEqual(emailWrongWarning,"Invalid email or password");
    }

    public static void verifyPasswordWrong(){
        String passwordWrongWarning = CommonAction.getText(LoginPage.email_pwWrongWarning);
        CommonAction.softAssertEqual(passwordWrongWarning,"Invalid email or password");
    }

    public static void verifyEmailFormat(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String actualMessage = (String) js.executeScript(
                "return arguments[0].validationMessage;", driver.findElement(LoginPage.inputEmail));
        CommonAction.softAssertEqual(actualMessage, "Please include an '@' in the email address. 'abc' is missing an '@'.");
    }
}
