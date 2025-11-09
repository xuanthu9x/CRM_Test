package TestCase;

import Common.BaseTest;
import Pages.AddCustomerPage;
import Pages.BasePage;
import Pages.LoginPage;
import keywords.Common;
import org.openqa.selenium.By;
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


        String companyName="T_Test_081125";
        String VATNumber="";
        String phoneNumber="";
        String website="";
        String group ="";
        String currency="";
        String language="";
        String address="";
        String state="";
        String city="";
        String zipCode="";


        //AddCustomerPage.InputAddCustomerWithOnlyRequireField(companyName);
        AddCustomerPage.InputFullCustomerInfor(companyName,VATNumber,phoneNumber,website,group,language, address,city,state,zipCode);
        AddCustomerPage.AddCustomerOnlySave();
        AddCustomerPage.verifyAlertMessage();
        Thread.sleep(3000);
        AddCustomerPage.verifyAddCustomerSuccessfully(companyName);
        AddCustomerPage.veriryCustomerDetail(companyName, VATNumber, phoneNumber, website, group,currency,language, address, city, state, zipCode);
    }
    @Test(priority = 2)
    public void AddCustomerWithFullInfor() throws InterruptedException {
        loginPage = new LoginPage(driver);
        basePage= loginPage.Login("admin@example.com","123456");
        addCustomerPage=basePage.CustomerPage();

        String companyName="T_Test2_081125";
        String VATNumber="VAT123";
        String phoneNumber="0444444444";
        String website="google.com";
        String group ="Gold";
        String currency="USD$";
        String language="English";
        String address="273 an duong vuong";
        String state="quan 5";
        String city="ho chi minh";
        String zipCode="11111";

        AddCustomerPage.InputFullCustomerInfor(companyName,VATNumber,phoneNumber,website,group,language, address,city,state,zipCode);
        AddCustomerPage.AddCustomerOnlySave();
        AddCustomerPage.verifyAlertMessage();
        Thread.sleep(3000);
        AddCustomerPage.verifyAddCustomerSuccessfully(companyName);
        AddCustomerPage.veriryCustomerDetail(companyName, VATNumber, phoneNumber, website, group,currency,language, address, city, state, zipCode);
    }

    @Test(priority = 3)
    public void AddCustomerAndCreateContactWithOnlyRequiredField() throws InterruptedException {
        loginPage = new LoginPage(driver);
        basePage= loginPage.Login("admin@example.com","123456");
        addCustomerPage=basePage.CustomerPage();

        String companyName="T_Test_081125";
        String VATNumber="";
        String phoneNumber="";
        String website="";
        String group ="";
        String currency="";
        String language="";
        String address="";
        String state="";
        String city="";
        String zipCode="";

        //AddCustomerPage.InputAddCustomerWithOnlyRequireField(companyName);
        AddCustomerPage.InputFullCustomerInfor(companyName,VATNumber,phoneNumber,website,group,language, address,city,state,zipCode);
        AddCustomerPage.AddCustomerAndContact();
        AddCustomerPage.verifyAlertMessage();
        Thread.sleep(2000);
        AddCustomerPage.verifyAddCustomerWithContact(companyName);
        AddCustomerPage.veriryCustomerDetail(companyName, VATNumber, phoneNumber, website, group,currency,language, address, city, state, zipCode);
    }
    @Test(priority = 4)
    public void AddCustomerAndCreateContactWithFullInfo() throws InterruptedException {
        loginPage = new LoginPage(driver);
        basePage= loginPage.Login("admin@example.com","123456");
        addCustomerPage=basePage.CustomerPage();

        String companyName="T_Test2_231025";
        String VATNumber="VAT123";
        String phoneNumber="0336775288";
        String website="google.com";
        String group ="Gold";
        String currency="USD$";
        String language="English";
        String address="273 an duong vuong";
        String state="quan 5";
        String city="ho chi minh";
        String zipCode="11111";

        AddCustomerPage.InputFullCustomerInfor(companyName,VATNumber,phoneNumber,website,group, language,address,city,state,zipCode);
        AddCustomerPage.AddCustomerAndContact();
        AddCustomerPage.verifyAlertMessage();
        Thread.sleep(3000);
        AddCustomerPage.verifyAddCustomerWithContact(companyName);
        AddCustomerPage.veriryCustomerDetail(companyName, VATNumber, phoneNumber, website, group,currency,language, address, city, state, zipCode);
    }

    @Test(priority = 5)
    public void ValidateRequireField(){
        loginPage = new LoginPage(driver);
        basePage= loginPage.Login("admin@example.com","123456");
        addCustomerPage=basePage.CustomerPage();

       // AddCustomerPage.InputAddCustomerWithOnlyRequireField("");
        AddCustomerPage.AddCustomerOnlySave();

        AddCustomerPage.verifyRequireFieldWarning();
    }
    @Test(priority = 6)
    public void AddCustomerAndContacts() throws InterruptedException {
        loginPage = new LoginPage(driver);
        basePage= loginPage.Login("admin@example.com","123456");
        addCustomerPage=basePage.CustomerPage();

        String companyName="T_Test6_081125";
        String VATNumber="VAT123";
        String phoneNumber="0336775288";
        String website="google.com";
        String group ="Gold";
        String currency="USD$";
        String language="English";
        String address="273 an duong vuong";
        String state="quan 5";
        String city="ho chi minh";
        String zipCode="11111";

        //AddCustomerPage.InputAddCustomerWithOnlyRequireField("T_Test8_231025");

        AddCustomerPage.InputFullCustomerInfor(companyName,VATNumber,phoneNumber,website,group, language,address,city,state,zipCode);
        AddCustomerPage.AddCustomerAndContact();

        String contactFirstName="binh";
        String contactLastName="an";
        String contactEmail="binhan@alert.com";
        String contactPW="123456";
        AddCustomerPage.addCustomerAndContacts(contactFirstName,contactLastName,contactEmail,contactPW);
        AddCustomerPage.verifyAddCustomerSuccessfully(companyName);
        AddCustomerPage.verifyContactDetail(contactFirstName,contactLastName,contactEmail);
        Common common = new Common(driver);
        Common.click(By.xpath("//li[contains(@class, 'customer_tab_profile')]"));
        AddCustomerPage.veriryCustomerDetail(companyName, VATNumber, phoneNumber, website, group,currency,language, address, city, state, zipCode);
    }
}
