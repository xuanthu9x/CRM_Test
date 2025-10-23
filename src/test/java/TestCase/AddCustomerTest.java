package TestCase;

import Common.BaseTest;
import Pages.AddCustomerPage;
import Pages.BasePage;
import Pages.LoginPage;
import org.testng.annotations.Test;

public class AddCustomerTest extends BaseTest {
    LoginPage loginPage;
    BasePage basePage;
    AddCustomerPage addCustomerPage;
    @Test (priority = 1)
    public void AddCustomerWithOnlyRequiredField() throws InterruptedException {
        loginPage = new LoginPage(driver);
        basePage= loginPage.Login("admin@example.com","123456");
        addCustomerPage=basePage.CustomerPage();
        AddCustomerPage.InputAddCustomerWithOnlyRequireField("T_Test1_231025");
        AddCustomerPage.AddCustomerOnlySave();
        Thread.sleep(3000);
        AddCustomerPage.verifyAddCustomerSuccessfully("T_Test1_231025");
    }
    @Test(priority = 2)
    public void AddCustomerWithFullInfor() throws InterruptedException {
        loginPage = new LoginPage(driver);
        basePage= loginPage.Login("admin@example.com","123456");
        addCustomerPage=basePage.CustomerPage();

        AddCustomerPage.InputFullCustomerInfor("T_Test2_231025","VAT123","0336775288","google.com","java", "8414 Martin Luther King Jr Way South Seattle Washington 98118","Washington","Seattle","98118");
        AddCustomerPage.AddCustomerOnlySave();
        Thread.sleep(3000);
        AddCustomerPage.verifyAddCustomerSuccessfully("T_Test2_231025");
    }

    @Test(priority = 3)
    public void AddCustomerAndCreateContactWithOnlyRequiredField() throws InterruptedException {
        loginPage = new LoginPage(driver);
        basePage= loginPage.Login("admin@example.com","123456");
        addCustomerPage=basePage.CustomerPage();

        AddCustomerPage.InputAddCustomerWithOnlyRequireField("T_Test3_231025");
        AddCustomerPage.AddCustomerAndContact();
        Thread.sleep(2000);
        AddCustomerPage.verifyAddCustomerWithContact("T_Test3_231025");
    }
    @Test(priority = 4)
    public void AddCustomerAndCreateContactWithFullInfo() throws InterruptedException {
        loginPage = new LoginPage(driver);
        basePage= loginPage.Login("admin@example.com","123456");
        addCustomerPage=basePage.CustomerPage();

        AddCustomerPage.InputFullCustomerInfor("T_Test4_231025","VAT123","0336775288","google.com","java", "8414 Martin Luther King Jr Way South Seattle Washington 98118","Washington","Seattle","98118");
        AddCustomerPage.AddCustomerAndContact();
        Thread.sleep(3000);
        AddCustomerPage.verifyAddCustomerWithContact("T_Test4_231025");
    }

    @Test(priority = 5)
    public void ValidateRequireField(){
        loginPage = new LoginPage(driver);
        basePage= loginPage.Login("admin@example.com","123456");
        addCustomerPage=basePage.CustomerPage();

        AddCustomerPage.InputAddCustomerWithOnlyRequireField("");
        AddCustomerPage.AddCustomerOnlySave();

        AddCustomerPage.verifyRequireFieldWarning();
    }
    @Test(priority = 6)
    public void AddCustomerAndContacts() throws InterruptedException {
        loginPage = new LoginPage(driver);
        basePage= loginPage.Login("admin@example.com","123456");
        addCustomerPage=basePage.CustomerPage();

        AddCustomerPage.InputAddCustomerWithOnlyRequireField("T_Test8_231025");
        AddCustomerPage.AddCustomerAndContact();
        AddCustomerPage.addCustomerAndContacts("Test_Contact_231025","T_test","T_Test8@example.com","T_Test8");
        AddCustomerPage.verifyAddCustomerSuccessfully("T_Test8_231025");
        AddCustomerPage.verifyAddedContact("T_Test8@example.com");
    }
}
