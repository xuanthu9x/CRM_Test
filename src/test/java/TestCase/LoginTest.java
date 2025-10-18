package TestCase;

import Common.BaseTest;
import Pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test (priority = 1)
    public void LoginSuccess(){

        LoginPage login = new LoginPage(driver);
        LoginPage.LoginWithRememberMe("admin@example.com", "123456");

        LoginPage.verifyLoginSuccess();
    }

    @Test (priority = 2)
    public void EmailEmpty(){
        LoginPage login = new LoginPage(driver);
        LoginPage.Login("", "123456");
        LoginPage.verifyEmailEmptyWarning();

    }

    @Test(priority = 3)
    public void PasswordEmpty(){
        LoginPage login = new LoginPage(driver);
        LoginPage.Login("admin@example.com", "");
        LoginPage.verifyPasswordEmptyWarning();
    }
    @Test (priority = 4)
    public void EmailWrong(){
        LoginPage login = new LoginPage(driver);
        LoginPage.Login("admin1@exmample.com","123456");
        LoginPage.verifyEmailWrong();
    }

    @Test (priority = 5)
    public void PassWrong(){
        LoginPage login = new LoginPage(driver);
        LoginPage.Login("admin@example.com", "abcdef");
        LoginPage.verifyPasswordWrong();
    }
    @Test (priority = 6)
    public void emailFormatWrong(){
        LoginPage login = new LoginPage(driver);
        LoginPage.Login("abc", "123456");
        LoginPage.verifyEmailFormat();
    }
}
