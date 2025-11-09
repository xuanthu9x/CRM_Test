package keywords;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class Common {
    private static WebDriver driver;
    public Common (WebDriver driver){
        this.driver = driver;
    }
    public static void sendKey(By by, String message){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        element.sendKeys(message);
        // driver.findElement(by).sendKeys(message);
    }

    public static void click(By by){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
        element.click();
        //driver.findElement(by).click();
    }

    public static void clearText(By by){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        element.clear();
        //driver.findElement(by).clear();
    }

    public static String getText(By by){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return element.getText();
        //return driver.findElement(by).getText();
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

    public static boolean isDisplay (By by, int timeoutInSeconds){
        boolean isDisplayed;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            isDisplayed = element.isDisplayed();
        } catch (Exception e) {
            isDisplayed = false;
        }
        return isDisplayed;
    }

    public static String getContribute(By by, String contribute){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return element.getAttribute(contribute);
    }
}
