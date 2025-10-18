package Common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class CommonAction {
    private static WebDriver driver;

    public CommonAction (WebDriver driver){
        this.driver = driver;
    }

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
}
