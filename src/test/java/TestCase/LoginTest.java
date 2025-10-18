package TestCase;

import Common.BaseTest;
import Pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test
    public void LoginSuccess(){

        LoginPage login = new LoginPage(driver);
        LoginPage.LoginWithRememberMe("admin@example.com", "123456");

        LoginPage.verifyLoginSuccess();
    }

    @Test ()
    public void EmailEmpty(){
        LoginPage login = new LoginPage(driver);
        LoginPage.Login("", "123456");
        LoginPage.verifyEmailEmptyWarning();

    }

    @Test()
    public void PasswordEmpty(){
        LoginPage login = new LoginPage(driver);
        LoginPage.Login("admin@example.com", "");
        LoginPage.verifyPasswordEmptyWarning();
    }
    @Test ()
    public void EmailWrong(){
        LoginPage login = new LoginPage(driver);
        LoginPage.Login("admin1@exmample.com","123456");
        LoginPage.verifyEmailWrong();
    }

    @Test ()
    public void PassWrong(){
        LoginPage login = new LoginPage(driver);
        LoginPage.Login("admin@example.com", "abcdef");
        LoginPage.verifyPasswordWrong();
    }
    @Test ()
    public void emailFormatWrong(){
        LoginPage login = new LoginPage(driver);
        LoginPage.Login("abc", "123456");
        LoginPage.verifyEmailFormat();
    }
}
