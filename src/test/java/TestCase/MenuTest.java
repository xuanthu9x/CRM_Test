package TestCase;

import Common.BaseTest;
import Pages.BasePage;
import Pages.LoginPage;
import org.testng.annotations.Test;

public class MenuTest extends BaseTest {
    LoginPage login;
    BasePage basePage;
    @Test()
    public void checkMenuList() throws InterruptedException {
        login = new LoginPage(driver);
        basePage = login.Login("admin@example.com","123456");
        Thread.sleep(1000);
        BasePage.checkMenuList();
        BasePage.checkSaleSubMenu();
        BasePage.checkUtilitiesSubMenu();
        BasePage.checkReportsSubMenu();
    }
}
