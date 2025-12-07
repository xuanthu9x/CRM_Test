package TestCase;

import Common.BaseTest;
import Pages.LoginPage;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test (priority = 1)
    @Parameters ({"email","password"})
    public void LoginSuccess(@Optional("admin@example.com") String email,@Optional("123456") String password) throws InterruptedException {
        System.out.println("Test case: Login with valid email and password");
        LoginPage login = new LoginPage(driver);
        LoginPage.LoginWithRememberMe(email, password);
        LoginPage.verifyLoginSuccess();
    }

    @Test (priority = 2)
    public void EmailEmpty(){
        System.out.println("Test case: Login with empty email");
        LoginPage login = new LoginPage(driver);
        LoginPage.LoginTest("", "123456");
        LoginPage.verifyEmailEmptyWarning();

    }

    @Test(priority = 3)
    public void PasswordEmpty(){
        System.out.println("Test case: Login with empty password");
        LoginPage login = new LoginPage(driver);
        LoginPage.LoginTest("admin@example.com", "");
        LoginPage.verifyPasswordEmptyWarning();
    }
    @Test (priority = 4)
    public void EmailWrong(){
        System.out.println("Test case: Login with wrong email");
        LoginPage login = new LoginPage(driver);
        LoginPage.LoginTest("admin1@exmample.com","123456");
        LoginPage.verifyEmailWrong();
    }

    @Test (priority = 5)
    public void PassWrong(){
        System.out.println("Test case: Login with wrong password");
        LoginPage login = new LoginPage(driver);
        LoginPage.LoginTest("admin@example.com", "abcdef");
        LoginPage.verifyPasswordWrong();
    }
    @Test (priority = 6)
    public void emailFormatWrong(){
        System.out.println("Test case: Login with wrong email format");
        LoginPage login = new LoginPage(driver);
        LoginPage.LoginTest("abc", "123456");
        LoginPage.verifyEmailFormat();
    }
}
