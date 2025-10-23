package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class AddCustomerPage extends BasePage{
    public static By customerSummaryTitle = By.xpath("//span[normalize-space()='Customers Summary']");
    public AddCustomerPage(WebDriver driver){
        super(driver);
    }
    public static By menuCustomer = By.xpath("//span[normalize-space()='Customers']");
    public static By btnNewCustomer = By.xpath("//div[@class='_buttons']/descendant::a[normalize-space()='New Customer']");
    private static By inputCompany = By.xpath("//input[@id = 'company']");
    private static By companyEmptyWarning = By.xpath("//p[@id='company-error']");
    private static By inputVAT = By.xpath("//input[@id = 'vat']");
    private static By inputPhone = By.xpath("//input[@id = 'phonenumber']");
    private static By inputWebsite = By.xpath("//input[@id = 'website']");
    private static By groupDropdown = By.xpath("//button[@data-id='groups_in[]']");
    private static By inputSearchGroup = By.xpath("//button[@data-id='groups_in[]']/following::input[@aria-controls='bs-select-1']");
    private static By btnSelectAllGroup = By.xpath("//button[@data-id='groups_in[]']/following::button[normalize-space()='Select All']");
    private static By btDeselectAllGroup = By.xpath("//button[@data-id='groups_in[]']/following::button[normalize-space()='Deselect All']");
    private static By optionGroup = By.xpath("//div[@id='bs-select-1']/descendant::span[contains(normalize-space(),'java')]");
    private static By currencyDropdown = By.xpath("//button[@data-id='default_currency']");
    private static By inputSearchCurrency=By.xpath("//input[@aria-controls='bs-select-2']");
    private static By currencyOptionUSD=By.xpath("//div[@id='bs-select-2']/descendant::span[contains(text(),'USD')]");
    private static By defaultLanguageDropdown = By.xpath("//button[@data-id='default_language']");
    private static By defaultLanguageEnglish = By.xpath("//div[@id = 'bs-select-3']/descendant::span[contains(text(),'English')]");
    private static By addressTextArea = By.xpath("//textarea[@id = 'address']");
    private static By inputCity = By.xpath("//input[@id = 'city']");
    private static By inputState = By.xpath("//input[@id = 'state']");
    private static By inputZipCode = By.xpath("//input[@id = 'zip']");
    private static By countryDropdown = By.xpath("//button[@data-id='country']");
    private static By inputSearchCountry = By.xpath("//button[@data-id='country']/following::input[@aria-controls='bs-select-4']");
    private static By countryOption = By.xpath("//div[@id='bs-select-4']/descendant::span[contains(text(),'United States')]");
    private static By btnSaveAndCreateContract = By.xpath("//button[contains(@class,'save-and-add-contact')]");
    private static By btnOnlySave = By.xpath("//button[contains(@class,'only-save')]");

    private static By firstNameContact = By.xpath("//input[@id='firstname']");
    private static By lastNameContact = By.xpath("//input[@id='lastname']");
    private static By contactEmail = By.xpath("//input[@id='email']");
    private static By contactPW = By.xpath("//input[@name='password']");
    private static By contactSaveBtn = By.xpath("//button[normalize-space()='Save']");



    public static void verifyAddCustomerSuccessfully(String companyName){
        Boolean companyNameDisplay = driver.findElement(By.xpath("//span[contains(text(),'"+companyName+"')]")).isDisplayed();
        Assert.assertTrue(companyNameDisplay,"Add customer not successfully");
    }

    public static void InputAddCustomerWithOnlyRequireField(String companyName1){
        click(btnNewCustomer);
        click(menuCustomer);
        click(btnNewCustomer);
        String companyName = companyName1;
        sendKey(inputCompany,companyName);
    }

    public static void InputFullCustomerInfor(String companyName, String VAT, String sdt, String webSite, String group, String address, String city, String state, String zipCode) throws InterruptedException {

        click(btnNewCustomer);
        sendKey(inputCompany,companyName);
        sendKey(inputVAT,VAT);
        sendKey(inputPhone,sdt);
        sendKey(inputWebsite,webSite);
        click(groupDropdown);
        sendKey(inputSearchGroup,group);
        Thread.sleep(1000);
        click(By.xpath("//div[@id='bs-select-1']/descendant::span[contains(normalize-space(),'"+group+"')]"));
        driver.findElement(By.xpath("//body")).click();

        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(countryDropdown)).perform();

        click(currencyDropdown);
        click(currencyOptionUSD);
        click(defaultLanguageDropdown);
        click(defaultLanguageEnglish);
        sendKey(addressTextArea,address);
        sendKey(inputCity,city);
        sendKey(inputState,state);
        sendKey(inputZipCode,zipCode);
        click(countryDropdown);
        click(countryOption);
    }
    public static void AddCustomerOnlySave()
    {
        moveToElement(btnOnlySave);
        click(btnOnlySave);
    }
    public static void AddCustomerAndContact(){
        moveToElement(btnSaveAndCreateContract);
        click(btnSaveAndCreateContract);
    }
    public static void verifyAddCustomerWithContact(String companyName){
        Boolean newContactPopup = driver.findElement(By.xpath("//div[@id='contact']")).isDisplayed();
        Assert.assertTrue(newContactPopup,"Add new contact pop-up is not shown");
        click(By.xpath("//button[normalize-space() = 'Close']"));
        Boolean companyNameDisplay = driver.findElement(By.xpath("//span[contains(text(),'"+companyName+"')]")).isDisplayed();
        Assert.assertTrue(companyNameDisplay,"Add customer not successfully");
    }
    public static void verifyRequireFieldWarning(){
        Boolean warningDisplay = driver.findElement(companyEmptyWarning).isDisplayed();
        Assert.assertTrue(warningDisplay,"Validate company empty is not shown");
        String warningMessage = driver.findElement(companyEmptyWarning).getText();
        softAssertEqual(warningMessage,"This field is required.");
    }
    public static void addCustomerAndContacts(String contactFirstName, String contactLastName, String email, String password) throws InterruptedException {
        sendKey(firstNameContact,contactFirstName);
        sendKey(lastNameContact,contactLastName);
        sendKey(contactEmail,email);
        sendKey(contactPW,password);
        click(contactSaveBtn);
        Thread.sleep(1000);
    }
    public static void verifyAddedContact(String email)
    {
        String actualContactEmail = getText(By.xpath("//a[contains(@href,'mailto')]"));
        Assert.assertEquals(actualContactEmail,email);
    }

}
