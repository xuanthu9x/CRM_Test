package Pages;

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
        driver.get(url);
        sendKey(inputEmail,email);
        sendKey(inputPassword,password);
        click(buttonLogin);
    }

    public BasePage Login(String email, String password){
        driver.get(url);
        sendKey(inputEmail, email);
        sendKey(inputPassword, password);
        click(buttonLogin);
        return new BasePage(driver);
    }

    public static void LoginWithRememberMe(String email, String password){
        driver.get(url);
        sendKey(inputEmail, email);
        sendKey(inputPassword, password);
        boolean checkedRememberMe = driver.findElement(checkboxRememberMe).isSelected();
        if (!checkedRememberMe){
            click(checkboxRememberMe);
        }
        click(buttonLogin);
    }

    public static void verifyLoginSuccess(){
        Assert.assertTrue(driver.findElement(By.xpath("//span[normalize-space()='Dashboard']")).isDisplayed());
    }

    public static void verifyEmailEmptyWarning(){
        String emailEmptyWarning = getText(LoginPage.emailEmptyWarning);
        softAssertEqual(emailEmptyWarning,"The Email Address field is required.");
    }

    public  static void verifyPasswordEmptyWarning(){
        String passwordEmptyWarning = getText(LoginPage.passwordEmptyWarning);
        softAssertEqual(passwordEmptyWarning, "The Password field is required.");
    }

    public static void verifyEmailWrong(){
        String emailWrongWarning = getText(LoginPage.email_pwWrongWarning);
        softAssertEqual(emailWrongWarning,"Invalid email or password");
    }

    public static void verifyPasswordWrong(){
        String passwordWrongWarning = getText(LoginPage.email_pwWrongWarning);
        softAssertEqual(passwordWrongWarning,"Invalid email or password");
    }

    public static void verifyEmailFormat(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String actualMessage = (String) js.executeScript(
                "return arguments[0].validationMessage;", driver.findElement(LoginPage.inputEmail));
        softAssertEqual(actualMessage, "Please include an '@' in the email address. 'abc' is missing an '@'.");
    }
}
