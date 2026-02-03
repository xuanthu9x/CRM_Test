package TestCase;

import Common.BaseTest;
import Pages.LoginPage;
import helper.CaptureHelper;
import helper.SystemHelper;
import keywords.Common;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.DriverManager;
import java.util.Hashtable;

public class LoginTest extends BaseTest {
    @Test (priority = 1)
    @Parameters ({"email","password"})
    public void LoginSuccess(@Optional("admin@example.com") String email,@Optional("123456") String password) throws InterruptedException {
        System.out.println("Test case: Login with valid email and password");
        LoginPage login = new LoginPage(driver);
        //LoginPage.LoginWithRememberMe(email, password);
        LoginPage.LoginTest(); // gọi hàm dùng email, password từ file properties
        LoginPage.verifyLoginSuccess();
    }

    @Test (priority = 2)
    public void EmailEmpty(){
        System.out.println("Test case: Login with empty email");
        LoginPage login = new LoginPage(driver);
        LoginPage.LoginTest("", "123456");
        //LoginPage.LoginTest();
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
    @Test (priority = 7)
    public void DemoLoginWithDataInExcel(){
        LoginPage login = new LoginPage(driver);
        String email = loginExcel.getCellData("Email",1);
        String password = loginExcel.getCellData("Password",1);
        LoginPage.LoginTest(email,password);
        Cookie session = driver.manage().getCookieNamed("sp_session");
        System.out.println("Cookie name: " + session.getName() + ", Cookie value: " + session.getValue());
        loginExcel.setExcelFile("src/test/resources/DataTest/~$Login.xlsx","Login");
        loginExcel.setCellData(session.getName(), "SessionName",1);
        loginExcel.setCellData(session.getValue(), "SessionValue",1);

    }
//==================================================================================================
    @DataProvider(name = "data_provider_01")
    public Object[][] dataLogin() {
        return new Object[][]{{"admin@example.com", "123456"}, {"user1@example.com", "123456"}};
    }
    @Test(dataProvider = "data_provider_01")
    public void LoginSuccessDemoDataProvideSameClass(String email, String password) throws InterruptedException {
        System.out.println("Test case: Login with valid email and password");
        LoginPage login = new LoginPage(driver);
        LoginPage.LoginWithRememberMe(email, password);
        //LoginPage.LoginTest(); // gọi hàm dùng email, password từ file properties
        LoginPage.verifyLoginSuccess();
    }

    @Test(dataProvider = "data_provider_login", dataProviderClass = DataProviderLogin.class)
    public void LoginSuccessDemoDataProvideNotSameClass(String email, String password) throws InterruptedException {
        //System.out.println("Test case: Login with valid email and password");
        LoginPage login = new LoginPage(driver);
        LoginPage.LoginWithRememberMe(email, password);
        //LoginPage.LoginTest(); // gọi hàm dùng email, password từ file properties
        LoginPage.verifyLoginSuccess();
    }

    @Test(dataProvider = "data_provider_login_excel", dataProviderClass = DataProviderLogin.class)
    public void testLoginFromDataProviderExcel(String email, String password) {
        LoginPage login = new LoginPage(driver);
        LoginPage.LoginWithRememberMe(email, password);
        //LoginPage.LoginTest(); // gọi hàm dùng email, password từ file properties
        LoginPage.verifyLoginSuccess();
    }

    @Test(priority = 1, dataProvider = "data_provider_login_excel_hashtable", dataProviderClass = DataProviderLogin.class)
    public void testLoginFromDataProviderExcelHashtable(Hashtable< String, String > data) {
        LoginPage login = new LoginPage(driver);
        LoginPage.LoginWithRememberMe(data.get("Email"), data.get("Password"));
        //LoginPage.LoginTest(); // gọi hàm dùng email, password từ file properties
        LoginPage.verifyLoginSuccess();
    }

    // Sử dụng DataProvider với các dòng cụ thể (1, 3, 4)
    @Test(dataProvider = "data_provider_login_excel_specific_rows", dataProviderClass = DataProviderLogin.class)
    public void testLoginWithSpecificRows(String email, String password) {
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);

        LoginPage login = new LoginPage(driver);
        LoginPage.LoginWithRememberMe(email,password);
        //LoginPage.LoginTest(); // gọi hàm dùng email, password từ file properties
        LoginPage.verifyLoginSuccess();
    }

    // Sử dụng DataProvider với các dòng cụ thể dạng Hashtable
    @Test(dataProvider = "data_provider_login_excel_specific_rows_hashtable", dataProviderClass = DataProviderLogin.class)
    public void testLoginWithSpecificRowsHashtable(Hashtable < String, String > data) {
        String email = data.get("Email");
        String password = data.get("Password");

        System.out.println("Email: " + email);
        System.out.println("Password: " + password);

        LoginPage login = new LoginPage(driver);
        LoginPage.LoginWithRememberMe(email,password);
        //LoginPage.LoginTest(); // gọi hàm dùng email, password từ file properties
        LoginPage.verifyLoginSuccess();
    }
    // ==================================================================================================
    @Test (priority = 2)
    public void takeScreenshotLoginPage(Method method){
        //System.out.println("Test case: Login with empty email");
        LoginPage login = new LoginPage(driver);
        LoginPage.LoginTest("11admin@example.com", "123456");
        LoginPage.verifyLoginSuccess();

    }

    @Test (priority = 2)
    public void demoCaptureScreenLoginPage(Method method){
        CaptureHelper.startRecord(method.getName());
        //System.out.println("Test case: Login with empty email");
        LoginPage login = new LoginPage(driver);
        LoginPage.LoginTest("admin@example.com", "123456");
        LoginPage.verifyLoginSuccess();

    }

}
