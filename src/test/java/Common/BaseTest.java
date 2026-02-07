package Common;

import Annotations.RecordVideo;
import helper.CaptureHelper;
import helper.ExcelHelper;
import helper.PropertiesHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import keywords.Common;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

public class BaseTest {
    public static WebDriver driver;
    protected ExcelHelper customerExcel;
    protected ExcelHelper contactExcel;
    protected ExcelHelper loginExcel;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeSuite
    public void setupBeforeSuite(){
        helper.PropertiesHelper.loadAllFiles();
    }

    @BeforeClass
    public void setupBeforeClass(){
        customerExcel = new ExcelHelper();
        customerExcel.setExcelFile("src/test/resources/DataTest/Customers.xlsx", "AddCustomer");

        contactExcel = new ExcelHelper();
        contactExcel.setExcelFile("src/test/resources/DataTest/Customers.xlsx", "Contact");

        loginExcel = new ExcelHelper();
        loginExcel.setExcelFile("src/test/resources/DataTest/Login.xlsx", "Login");
    }
    @BeforeMethod
    @Parameters({"browser"})
    public void createDriver(@Optional("chrome") String browser, Method method) {
        if(PropertiesHelper.getValue("browserName") != null){
            browser = PropertiesHelper.getValue("browserName");
        }else{
            browser = browser;
        }
       setupDriver(browser);
        if (method.isAnnotationPresent(RecordVideo.class)) {
            CaptureHelper.startRecord(method.getName());
        }
    }

    public WebDriver setupDriver(@Optional("chrome") String browserName){
        if(PropertiesHelper.getValue("browserName") != null){
            browserName = PropertiesHelper.getValue("browserName");
        }else{
            browserName = browserName;
        }

        switch (browserName.trim().toLowerCase()){
            case "chrome":
                driver = initChromeDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = initEdgeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = initFirefoxDriver();
                break;
            default:
                System.out.println("Trình duyệt không hỗ trợ, khởi tạo trình duyệt Chrome mặc định");
                driver = initChromeDriver();
                break;
        }
        return driver;
    }
    private WebDriver initChromeDriver(){
        System.out.println("Khởi tạo trình duyệt chrome");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }
    private WebDriver initEdgeDriver(){
        System.out.println("Khởi tạo trình duyệt edge");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        return driver;
    }
    private WebDriver initFirefoxDriver(){
        System.out.println("Khởi tạo trình duyệt Firefox");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    }

    @AfterMethod
    public void closeDriver(ITestResult testResult) throws IOException {
        Common common = new Common(driver);
        if(ITestResult.FAILURE == testResult.getStatus()){
            Common.takeScreenshot(testResult.getName());
        }
       // CaptureHelper.stopRecord();
        if(driver!=null){
            driver.quit();
        }
        System.out.println("Đóng trình duyệt");
    }
}
