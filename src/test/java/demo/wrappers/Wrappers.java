package demo.wrappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
    ChromeDriver driver;
    public Wrappers(ChromeDriver driver) {
        this.driver = driver;
        System.out.println(this.driver);
    }
    //Navigate to Google form
    public void openURL(String url){
        System.out.println("Open URL:"+url);
        driver.get(url);
        System.out.println("Success!");
    }

    //find the Element and send keys
    public void inputText(WebDriver driver, By selector, String sendText) throws Exception {
        System.out.println("Trying to send text - "+sendText);
        // Initialize the webdriver wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // wait until selector is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        // Find the element
        WebElement element = driver.findElement(selector);
        // Clear the element text, if already present and send the text
        element.clear();
        element.sendKeys(sendText);
        Thread.sleep(3000);
        System.out.println("Success!");
    }

    public void checkBoxSelection(WebDriver driver, By selector) throws InterruptedException{
        System.out.println("Trying to click check box");
        WebElement element = driver.findElement(selector);
        //click
        element.click();
        Thread.sleep(2000);
        System.out.println("Succes!");
    }

    public void selectFromDropdown(ChromeDriver driver, By dropDown, String selectText) throws InterruptedException{
        System.out.println("Try to select the dropdown-"+selectText);
        //open dropdown
        WebElement dropDownElement = driver.findElement(dropDown);
        dropDownElement.click();
        Thread.sleep(2000);
        //visible text in options
        driver.findElement(By.xpath("(//div[@data-value='" + selectText + "'])[2]")).click();
        Thread.sleep(2000);
        System.out.println("Success!");

    }

    public String dateSelection(String formatString, long offsetInMins) throws InterruptedException{
        System.out.println("Try to select the current date or Time");
        LocalDateTime now = LocalDateTime.now();
        long seconds = offsetInMins / 1000;
        long nanos = (offsetInMins % 1000) * 1000000;
        LocalDateTime newDateTime = now.minus(Duration.ofSeconds(seconds, nanos));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatString);
        String formatteDateTime = newDateTime.format(formatter);
        return formatteDateTime;
    }

//     public static String calculateEpochTimeToString(int offsetInMins) {
//         // Get the current date and time as an Instant
//         Instant now = Instant.now();

//         // Apply the offset in milliseconds to the current instant
//         Instant newInstant = now.plusMillis(offsetInMins);

//         // Convert the Instant to epoch time in milliseconds
//         long epochMilli = newInstant.toEpochMilli();

//         // Return the epoch time as a string
//         return String.valueOf(epochMilli);
//     }
}
