package TestCase;

import Common.BaseTest;
import Pages.AddCustomerPage;
import Pages.BasePage;
import Pages.LoginPage;
import keywords.Common;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class AddCustomerTest extends BaseTest {
    LoginPage loginPage;
    BasePage basePage;
    AddCustomerPage addCustomerPage;
    private String companyName="";
    private String VATNumber="";
    private String phoneNumber="";
    private String website="";
    private String group ="";
    private String currency="";
    private String language="";
    private String address="";
    private String state="";
    private String city="";
    private String zipCode="";
    private String country="United States";
    @Test (priority = 1)
    public void AddCustomerWithOnlyRequiredField() throws InterruptedException {

        loginPage = new LoginPage(driver);
        basePage= loginPage.Login("admin@example.com","123456");
        addCustomerPage=basePage.CustomerPage();


         companyName= customerExcel.getCellData("Company",1);
         VATNumber = customerExcel.getCellData("VAT_Number",1);
         phoneNumber = customerExcel.getCellData("Phone",1);
         website = customerExcel.getCellData("Website",1);
         group = "Gold";
         currency="USD$";
         language="English";
         address= customerExcel.getCellData("Address",1);
         state="";
         city = customerExcel.getCellData("City",1);
         zipCode = customerExcel.getCellData("ZipCode",1);
         country = customerExcel.getCellData("Country",1);

        //AddCustomerPage.InputAddCustomerWithOnlyRequireField(companyName);
        AddCustomerPage.InputFullCustomerInfor(companyName,VATNumber,phoneNumber,website,group,language, address,city,state,zipCode,country);
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

         companyName= customerExcel.getCellData("Company",2);
         VATNumber= customerExcel.getCellData("VAT_Number",2);
         phoneNumber= customerExcel.getCellData("Phone",2);
         website= customerExcel.getCellData("Website",2);
         group= customerExcel.getCellData("Group",2);
         currency= customerExcel.getCellData("Currency",2);
         language= customerExcel.getCellData("DefaultLanguage",2);
         address= customerExcel.getCellData("Address",2);
         city= customerExcel.getCellData("City",2);
         state= customerExcel.getCellData("State",2);
         zipCode= customerExcel.getCellData("ZipCode",2);
         country= customerExcel.getCellData("Country",2);

        AddCustomerPage.InputFullCustomerInfor(companyName,VATNumber,phoneNumber,website,group,language, address,city,state,zipCode,country);
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

         companyName= customerExcel.getCellData("Company",3);

        AddCustomerPage.InputAddCustomerWithOnlyRequireField(companyName);
        //AddCustomerPage.InputFullCustomerInfor(companyName,VATNumber,phoneNumber,website,group,language, address,city,state,zipCode,country);
        AddCustomerPage.AddCustomerAndContact();
        AddCustomerPage.verifyAlertMessage();
        Thread.sleep(5000);
        AddCustomerPage.verifyAddCustomerWithContact(companyName);
        Common.click(By.xpath("//li[@class = 'customer_tab_profile']/a[@data-group = 'profile']"));
        AddCustomerPage.veriryCustomerDetail(companyName, VATNumber, phoneNumber, website, group,currency,language, address, city, state, zipCode);
    }
    @Test(priority = 4)
    public void AddCustomerAndCreateContactWithFullInfo() throws InterruptedException {
        loginPage = new LoginPage(driver);
        basePage= loginPage.Login("admin@example.com","123456");
        addCustomerPage=basePage.CustomerPage();

         companyName= customerExcel.getCellData("Company",4);
         VATNumber= customerExcel.getCellData("VAT_Number",4);
         phoneNumber= customerExcel.getCellData("Phone",4);
         website= customerExcel.getCellData("Website",4);
         group = customerExcel.getCellData("Group",4);
         currency= customerExcel.getCellData("Currency",4);
         language= customerExcel.getCellData("DefaultLanguage",4);
         address= customerExcel.getCellData("Address",4);
         state= customerExcel.getCellData("State",4);
         city= customerExcel.getCellData("City",4);
         zipCode= customerExcel.getCellData("ZipCode",4);
         country= customerExcel.getCellData("Country",4);

        AddCustomerPage.InputFullCustomerInfor(companyName,VATNumber,phoneNumber,website,group, language,address,city,state,zipCode,country);
        AddCustomerPage.AddCustomerAndContact();
        AddCustomerPage.verifyAlertMessage();
        Thread.sleep(3000);
        AddCustomerPage.verifyAddCustomerWithContact(companyName);
        Common.click(By.xpath("//li[@class = 'customer_tab_profile']/a[@data-group = 'profile']"));
        AddCustomerPage.veriryCustomerDetail(companyName, VATNumber, phoneNumber, website, group,currency,language, address, city, state, zipCode);
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

         companyName= customerExcel.getCellData("Company",5);
         VATNumber= customerExcel.getCellData("VAT_Number",5);
         phoneNumber= customerExcel.getCellData("Phone",5);
         website= customerExcel.getCellData("Website",5);
         group= customerExcel.getCellData("Group",5);
         currency= customerExcel.getCellData("Currency",5);
         language= customerExcel.getCellData("DefaultLanguage",5);
         address= customerExcel.getCellData("Address",5);
         city= customerExcel.getCellData("City",5);
         state= customerExcel.getCellData("State",5);
         zipCode= customerExcel.getCellData("ZipCode",5);
         country = customerExcel.getCellData("Country",5);

        //AddCustomerPage.InputAddCustomerWithOnlyRequireField("T_Test8_231025");
        AddCustomerPage.InputFullCustomerInfor(companyName,VATNumber,phoneNumber,website,group, language,address,city,state,zipCode,country);
        AddCustomerPage.AddCustomerAndContact();

        String contactFirstName= contactExcel.getCellData("FirstName",1);
        String contactLastName= contactExcel.getCellData("LastName",1);
        String contactEmail= contactExcel.getCellData("Email",1);
        String contactPW= contactExcel.getCellData("Password",1);
        AddCustomerPage.addCustomerAndContacts(contactFirstName,contactLastName,contactEmail,contactPW);
        AddCustomerPage.verifyAddCustomerSuccessfully(companyName);
        AddCustomerPage.verifyContactDetail(contactFirstName,contactLastName,contactEmail);
        Thread.sleep(3000);
        Common common = new Common(driver);
        Common.click(By.xpath("//li[contains(@class, 'customer_tab_profile')]"));
        AddCustomerPage.veriryCustomerDetail(companyName, VATNumber, phoneNumber, website, group,currency,language, address, city, state, zipCode);
    }

    @Test(dataProvider = "data_provider_customer", dataProviderClass = DataProviderFactory.class)
    public void AddCustomerWithFullInforDataProvider(String companyName,String VATNumber, String phoneNumber,String website,String group, String currency, String language, String address,String city, String state, String zipCode, String country) throws InterruptedException {
        loginPage = new LoginPage(driver);
        basePage= loginPage.Login("admin@example.com","123456");
        addCustomerPage=basePage.CustomerPage();
        AddCustomerPage.InputFullCustomerInfor(companyName,VATNumber,phoneNumber,website,group,language, address,city,state,zipCode,country);
        AddCustomerPage.AddCustomerOnlySave();
        AddCustomerPage.verifyAlertMessage();
        Thread.sleep(3000);
        AddCustomerPage.verifyAddCustomerSuccessfully(companyName);
        AddCustomerPage.veriryCustomerDetail(companyName, VATNumber, phoneNumber, website, group,currency,language, address, city, state, zipCode);
    }

}
