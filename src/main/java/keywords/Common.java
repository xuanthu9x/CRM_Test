package keywords;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class Common {
    private static WebDriver driver;
    private static int TIMEOUT = 5;
    public Common (WebDriver driver){
        this.driver = driver;
    }
    public static void openUrl(String url){
        driver.get(url);
        System.out.println("Open URL: " + url);
        waitForPageLoaded();
    }

    public static void logConsole(String message) {
        // Có thể thêm timestamp hoặc cấp độ log ở đây trước khi in
        System.out.println(message);
        // Sau này, bạn có thể thêm logic ghi vào report ở đây
    }

    public static WebElement waitForElementVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            return  wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            logConsole("Timeout waiting for the element visible. " + by.toString());
            throw new TimeoutException("Element not visible after 5 seconds: " + by.toString(), error);
        }
    }

    public static WebElement waitForElementVisible(By by, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            return  wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            logConsole("Timeout waiting for the element visible. " + by.toString());
            throw new TimeoutException("Element not visible after 5 seconds: " + by.toString(), error);
        }
    }

    public static WebElement waitForElementClickable(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            return wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Throwable error) {
            logConsole("Timeout waiting for the element ready to click. " + by.toString());
            throw new TimeoutException("Element not visible after 5 seconds: " + by.toString(), error);
        }
    }

    public static void sendKey(By by, String message) {
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        //WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        waitForElementVisible(by).sendKeys(message);
        System.out.println("Input text '" + message + "' into element: " + by.toString());
    }

    public static void click(By by){
        waitForElementClickable(by).click();
        System.out.println("Click on element: " + by.toString());
    }

    public static void clearText(By by){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        element.clear();
        System.out.println("Clear text in element: " + by.toString());
        //driver.findElement(by).clear();
    }

    public static String getText(By by){
        System.out.println("Get text from element: " + by.toString());
        System.out.println("Text value: " + waitForElementVisible(by).getText());
        return waitForElementVisible(by).getText();
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
        WebElement element = waitForElementVisible(by);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        System.out.println("Move to element: " + by.toString());
    }

    public static boolean isDisplay (By by, int timeoutInSeconds){
        boolean isDisplayed;
        try {
            isDisplayed = waitForElementVisible(by, timeoutInSeconds).isDisplayed();
        } catch (Exception e) {
            isDisplayed = false;
        }
        System.out.println("Element " + by.toString() + " is displayed: " + isDisplayed);
        return isDisplayed;
    }

    public static String getContribute(By by, String contribute){
        System.out.println("Get contribute '" + contribute + "' from element: " + by.toString());
        System.out.println("Contribute value: " + waitForElementVisible(by).getAttribute(contribute));
        return waitForElementVisible(by).getAttribute(contribute);
    }

    //Chờ đợi trang load xong mới thao tác
    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //Wait for Javascript to load
        ExpectedCondition< Boolean > jsLoad = new ExpectedCondition < Boolean > () {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        //Check JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            //System.out.println("Javascript is NOT Ready.");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                error.printStackTrace();
                Assert.fail("FAILED. Timeout waiting for page load.");
            }
        }
    }
}
