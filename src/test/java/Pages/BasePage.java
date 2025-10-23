package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BasePage {
    public static WebDriver driver;
    private static By salesSubMenuList = By.xpath("//li[@class = 'menu-item-sales active']/ul/li");
    private static By listMenu = By.xpath("//ul[@id='side-menu']/li[contains(@class,'menu-item')]");
    private static By dashboardMenu = By.xpath("//span[normalize-space()='Dashboard']");
    private static By customersMenu = By.xpath("//span[normalize-space()='Customers']");
    private static By salesMenu = By.xpath("//span[normalize-space()='Sales']");
    private static By proposalSubMenu = By.xpath("//span[@class = 'sub-menu-text' and normalize-space()='Proposals']");
    private static By estimatesSubMenu = By.xpath("//span[@class = 'sub-menu-text' and normalize-space()='Estimates']");
    private static By invoicesSubMenu = By.xpath("//span[@class = 'sub-menu-text' and normalize-space()='Invoices']");
    private static By paymentsSubMenu = By.xpath("//span[@class = 'sub-menu-text' and normalize-space()='Payments']");
    private static By credit_notesSubMenu = By.xpath("//span[@class = 'sub-menu-text' and normalize-space()='Credit Notes']");
    private static By itemsSubMenu = By.xpath("//span[@class = 'sub-menu-text' and normalize-space()='Items']");
    private static By subscriptionsMenu = By.xpath("//span[@class = 'menu-text' and normalize-space()='Subscriptions']");
    private static By expensesMenu = By.xpath("//span[@class = 'menu-text' and normalize-space()='Expenses']");
    private static By contractsMenu = By.xpath("//span[@class = 'menu-text' and normalize-space()='Contracts']");
    private static By projectsMenu = By.xpath("//span[@class = 'menu-text' and normalize-space()='Projects']");
    private static By tasksMenu = By.xpath("//span[@class = 'menu-text' and normalize-space()='Tasks']");
    private static By supportMenu = By.xpath("//span[@class = 'menu-text' and normalize-space()='Support']");
    private static By leadsMenu = By.xpath("//span[@class = 'menu-text' and normalize-space()='Leads']");
    private static By estimate_requestMenu = By.xpath("//span[@class = 'menu-text' and normalize-space()='Estimate Request']");
    private static By knowledge_baseMenu = By.xpath("//span[@class = 'menu-text' and normalize-space()='Knowledge Base']");
    private static By utilitiesMenu = By.xpath("//span[@class = 'menu-text' and normalize-space()='Utilities']");
    private static By utilitiesMediaMenu = By.xpath("//span[@class = 'sub-menu-text' and normalize-space()='Media']");
    private static By utilitiesSubMenu = By.xpath("//li[contains(@class, 'menu-item-utilities')]/ul/li");
    private static By utilitiesBulk_pdf_exporterMenu = By.xpath("//span[@class = 'sub-menu-text' and normalize-space()='Bulk PDF Export']");
    private static By utilitiesCalendarMenu = By.xpath("//span[@class = 'sub-menu-text' and normalize-space()='Calendar']");
    private static By reportMenu = By.xpath("//span[@class = 'menu-text' and normalize-space()='Reports']");
    private static By reportsSubMenuList = By.xpath("//li[@class = 'menu-item-reports active']/ul/li");
    private static By reportsSalesMenu = By.xpath("//span[@class = 'sub-menu-text' and normalize-space()='Sales']");
    private static By reportsExpensesMenu = By.xpath("//span[@class = 'sub-menu-text' and normalize-space()='Expenses']");
    private static By reportsExpenses_vs_incomeMenu = By.xpath("//span[@class = 'sub-menu-text' and normalize-space()='Expenses vs Income']");
    private static By reportsLeadsMenu = By.xpath("//span[@class = 'sub-menu-text' and normalize-space()='Leads']");
    private static By reportsTimesheetsMenu = By.xpath("//span[@class = 'sub-menu-text' and normalize-space()='Timesheets overview']");
    private static By reportsKnowledge_base_articlesMenu = By.xpath("//span[@class = 'sub-menu-text' and normalize-space()='KB Articles']");
    private static List<String> expectedMenuList = Arrays.asList(
            "Dashboard",
            "Customers",
            "Sales",
            "Subscriptions",
            "Expenses",
            "Contracts",
            "Projects",
            "Tasks",
            "Support",
            "Leads",
            "Estimate Request",
            "Knowledge Base",
            "Utilities",
            "Reports"
    );
    private static List<String> expectedSalesSubMenuList = Arrays.asList(
            "Proposals",
            "Estimates",
            "Invoices",
            "Payments",
            "Credit Notes",
            "Items"
    );
    private static List<String> expectedUtilitesSubMenuList = Arrays.asList(
            "Media",
            "Bulk PDF Export",
            "Calendar"
    );
    private static List<String> expectedReportsSubMenuList = Arrays.asList(
            "Sales",
            "Expenses",
            "Expenses vs Income",
            "Leads",
            "Timesheets overview",
            "KB Articles"
    );

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    private static List<String> getMenuList(){
        List<WebElement> menuList = driver.findElements(listMenu);
        List<String> menuItems = new ArrayList<>();
        if(menuList.size()>0) {
            for(WebElement item : menuList){
                menuItems.add(item.getText());
            }
        }else{
            System.out.println("Can not get menu list");
        }
        return menuItems;
    }
    private static List<String> getSaleSubMenuList(){
        List<WebElement> menuList = driver.findElements(salesSubMenuList);
        List<String> subMenuList = new ArrayList<>();
        if(menuList.size()>0) {
            for(WebElement item : menuList){
                subMenuList.add(item.getText());
            }
        }else{
            System.out.println("Can not get menu list");
        }
        return subMenuList;
    }
    private static List<String> getUtilitiesSubMenuList(){
        List<WebElement> menuList = driver.findElements(utilitiesSubMenu);
        List<String> subMenuList = new ArrayList<>();
        if(menuList.size()>0) {
            for(WebElement item : menuList){
                subMenuList.add(item.getText());
            }
        }else{
            System.out.println("Can not get menu list");
        }
        return subMenuList;
    }
    private static List<String> getReportsSubMenuList(){
        List<WebElement> menuList = driver.findElements(reportsSubMenuList);
        List<String> subMenuList = new ArrayList<>();
        if(menuList.size()>0) {
            for(WebElement item : menuList){
                subMenuList.add(item.getText());
            }
        }else{
            System.out.println("Can not get menu list");
        }
        return subMenuList;
    }



    public static void checkMenuList() {
        List<WebElement> menuList = driver.findElements(listMenu);
        List<String> menuItemList = BasePage.getMenuList();
        Assert.assertEquals(menuItemList, expectedMenuList, " Menu list does not match!");


    }
    public static void checkSaleSubMenu() throws InterruptedException {
        click(salesMenu);
        Thread.sleep(1000);
        List<String> saleSubMenuList = BasePage.getSaleSubMenuList();
        Assert.assertEquals(saleSubMenuList, expectedSalesSubMenuList, " Sale sub menu list does not match!");
    }

    public static void checkUtilitiesSubMenu() throws InterruptedException {
        click(utilitiesMenu);
        Thread.sleep(1000);
        List<String> unitiliesSubMenuList = BasePage.getUtilitiesSubMenuList();
        Assert.assertEquals(unitiliesSubMenuList, expectedUtilitesSubMenuList, " Utilities sub menu list does not match!");
    }

    public static void checkReportsSubMenu() throws InterruptedException {
        click(reportMenu);
        Thread.sleep(1000);
        List<String> reportsSubMenuList = BasePage.getReportsSubMenuList();
        Assert.assertEquals(reportsSubMenuList, expectedReportsSubMenuList, " Reports sub menu list does not match!");
    }

    public static void navigateCustomerPage(){
        click(customersMenu);
    }
    public AddCustomerPage CustomerPage(){
        click(customersMenu);
        return new AddCustomerPage(driver);
    }
    public static void verifyNavigateCustomePage(){
        navigateCustomerPage();
        AddCustomerPage customerPage = new AddCustomerPage(driver);
        Assert.assertTrue(driver.findElement(AddCustomerPage.customerSummaryTitle).isDisplayed());
    }


//============================================================================================

    public static void sendKey(By by, String message){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        driver.findElement(by).sendKeys(message);
    }

    public static void click(By by){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).click();
    }

    public static void clearText(By by){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        driver.findElement(by).clear();
    }

    public static String getText(By by){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return driver.findElement(by).getText();
    }
    public  static void softAssertEqual(String actual, String expected){
        SoftAssert softassert = new SoftAssert();
        softassert.assertEquals(actual,expected);
    }

    public static void softAssertTrue(Boolean condition, String message){
        SoftAssert softassert = new SoftAssert();
        softassert.assertTrue(condition, message);
    }
    public static void moveToElement(By by){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }
}
