package demo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;

public class TestCases {
    ChromeDriver driver;
    public TestCases(){
        System.out.println("TestCases Start");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public void endTest(){
        System.out.println("TestCases End");
        driver.close();
        driver.quit();
    }

    public void testCase01() throws InterruptedException{
        System.out.println("Start Test Case: Google Forms");
        //Navigate to Google Form
        try{
            openURL("https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");
            Thread.sleep(3000);
            inputText(driver,By.xpath("//div[@class='Xb9hP']/input[@type='text']"),"Naveen");
            inputText(driver, By.xpath("//textarea[@class='KHxj8b tL9Q4c']"), "To become a best QA Engineer");
            checkBoxSelection(driver,By.xpath("//span[text()='How much experience do you have in Automation Testing?']/following::div[@class='AB7Lab Id5V1'][position()=2]"));
            checkBoxSelection(driver, By.xpath("//span[normalize-space(text()) = 'Which of the following have you learned in Crio.Do for Automation Testing?']/following::div[@class='uHMk6b fsHoPb'][position() = 1]"));
            checkBoxSelection(driver, By.xpath("//span[normalize-space(text()) = 'Which of the following have you learned in Crio.Do for Automation Testing?']/following::div[@class='uHMk6b fsHoPb'][position() = 4]"));
            checkBoxSelection(driver, By.xpath("//span[normalize-space(text()) = 'Which of the following have you learned in Crio.Do for Automation Testing?']/following::div[@class='uHMk6b fsHoPb'][position() = 2]"));
            selectFromDropdown(driver, By.xpath("//*[normalize-space(text()) = 'How should you be addressed?']/ancestor::div[4]//div[@class='MocG8c HZ3kWc mhLiyf LMgvRb KKjvXb DEh1R']"), "Mr");
            inputText(driver, By.xpath("//input[@class='whsOnd zHQkBf' and @aria-labelledby='i50']"),dateSelection("dd/mm/yyyy",(long)604800000));
            inputText(driver, By.xpath("(//*[normalize-space(text()) = 'What is the time right now?']//ancestor::div[4]//input[@class='whsOnd zHQkBf'])[1]"), dateSelection("HH", 0));
            inputText(driver, By.xpath("(//*[normalize-space(text()) = 'What is the time right now?']//ancestor::div[4]//input[@class='whsOnd zHQkBf'])[2]"), dateSelection("MM", 0));
            driver.findElement(By.xpath("//*[normalize-space(text())='Submit']/ancestor::div[1]")).click();
            System.out.println(driver.findElement(By.xpath("//div[@role='heading']/../div[3]")).getText());
            Thread.sleep(3000);

        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failure!");
        }
    }

    // public void Wrappers(WebDriver driver){
    //     this.driver = driver;
    //     this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    // }

    //Navigate to Google form
    private void openURL(String url){
        System.out.println("Open URL:"+url);
        driver.get(url);
        System.out.println("Success!");
    }

    //find the Element and send keys
    private static void inputText(WebDriver driver, By selector, String sendText) throws Exception {
        System.out.println("Trying to send text - "+sendText);
        // Find the element
        WebElement element = driver.findElement(selector);
        // Clear the element text, if already present and send the text
        element.clear();
        element.sendKeys(sendText);
        Thread.sleep(3000);
        System.out.println("Success!");
    }

    private static void checkBoxSelection(WebDriver driver, By selector) throws InterruptedException{
        System.out.println("Trying to click check box");
        WebElement element = driver.findElement(selector);
        //click
        element.click();
        Thread.sleep(2000);
        System.out.println("Succes!");
    }

    private static void selectFromDropdown(ChromeDriver driver, By dropDown, String selectText) throws InterruptedException{
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

    private static String dateSelection(String formatString, long offsetInMins) throws InterruptedException{
        System.out.println("Try to select the current date - 7:");
        LocalDateTime now = LocalDateTime.now();
        long seconds = offsetInMins / 1000;
        long nanos = (offsetInMins % 1000) * 1000000;
        LocalDateTime newDateTime = now.minus(Duration.ofSeconds(seconds, nanos));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatString);
        String formatteDateTime = newDateTime.format(formatter);
        return formatteDateTime;
    }

}
