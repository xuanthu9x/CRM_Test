package Common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    @BeforeMethod
    @Parameters({"browser"})
    public void createDriver( String browser){
       setupDriver(browser);
    }
    public WebDriver setupDriver(@Optional("edge") String browserName){
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
    public void closeDriver(){
        if(driver!=null){
            driver.quit();
        }
        System.out.println("Đóng trình duyệt");
    }
}
