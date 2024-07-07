package demo;

import java.time.Duration;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
        ChromeDriver driver;
        Wrappers wrappers;

        /*
         * TODO: Write your tests here with testng @Test annotation.
         * Follow `testCase01` `testCase02`... format or what is provided in
         * instructions
         */

        /*
         * Do not change the provided methods unless necessary, they will help in
         * automation and assessment
         */
        @BeforeTest
        public void startBrowser() {
                System.setProperty("java.util.logging.config.file", "logging.properties");

                // NOT NEEDED FOR SELENIUM MANAGER
                // WebDriverManager.chromedriver().timeout(30).setup();

                ChromeOptions options = new ChromeOptions();
                LoggingPreferences logs = new LoggingPreferences();

                logs.enable(LogType.BROWSER, Level.ALL);
                logs.enable(LogType.DRIVER, Level.ALL);
                options.setCapability("goog:loggingPrefs", logs);
                options.addArguments("--remote-allow-origins=*");

                System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

                driver = new ChromeDriver(options);
                wrappers = new Wrappers(driver);

                driver.manage().window().maximize();

        }

        @Test
        public void testCase01() throws InterruptedException {
                System.out.println("Start Test Case: Google Forms");
                // Navigate to Google Form
                try {
                        wrappers.openURL(
                                        "https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");
                        Assert.assertEquals(driver.getCurrentUrl(),
                                        "https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform",
                                        "Failed to navigate to Google Form");
                        Thread.sleep(3000);
                        System.out.println("update the Name");
                        // fill the name
                        String name = "Learner";
                        wrappers.inputText(driver,
                                        By.xpath("//span[normalize-space(text()) = 'Name']/following::input[@class='whsOnd zHQkBf']"),
                                        name);
                                        Thread.sleep(2000);
                        WebElement nameInput = driver.findElement(
                                        By.xpath("//span[normalize-space(text()) = 'Name']/following::input[@class='whsOnd zHQkBf']"));
                        String outputName = nameInput.getAttribute("data-initial-value").trim();
                        Thread.sleep(2000);
                        if (outputName.equals(name)) {
                                System.out.println("Input name is Matching:" +name);
                        } else {
                                System.out.println("name not matching:" +outputName);
                        }

                        System.out.println("Update the required fields");
                        String inputString = "To become a best QA Engineer";
                        Thread.sleep(2000);
                        wrappers.inputText(driver, By.xpath(
                                        "//span[normalize-space(text()) = 'Why are you practicing Automation?']/following::textarea[@class='KHxj8b tL9Q4c']"),
                                        inputString);
                                        Thread.sleep(2000);
                        WebElement phraseInput = driver.findElement(By.xpath(
                                        "//span[normalize-space(text()) = 'Why are you practicing Automation?']/following::textarea[@class='KHxj8b tL9Q4c']"));
                                        String outputPhrase= phraseInput.getAttribute("data-initial-value").trim();
                        // Assert.assertEquals(phraseInput.getAttribute("data-initial-value"),
                        //                 "To become a best QA Engineer",
                        //                 "Input phrase is not matching");
                        Thread.sleep(2000);
                        if(outputPhrase.equals(inputString)){
                                System.out.println("Input is correct:" +inputString);
                        }else{
                                System.out.println("Input is incorrect: "+phraseInput);
                        }

                        // Click experience
                        System.out.println("Select the experience");
                        wrappers.checkBoxSelection(driver, By.xpath(
                                        "//span[text()='How much experience do you have in Automation Testing?']/following::div[@class='AB7Lab Id5V1'][position()=2]"));
                        WebElement experienceCheckbox = driver.findElement(By.xpath(
                                        "//span[text()='How much experience do you have in Automation Testing?']/following::div[@class='AB7Lab Id5V1'][position()=2]/../.."));
                        Assert.assertTrue(experienceCheckbox.getAttribute("aria-checked").equals("true"),
                                        "Failed to select experience option");

                        // Tick the options
                        System.out.println("Tick the learned automation testing");
                        wrappers.checkBoxSelection(driver, By.xpath(
                                        "//span[normalize-space(text()) = 'Which of the following have you learned in Crio.Do for Automation Testing?']/following::div[@class='uHMk6b fsHoPb'][position() = 1]"));

                        wrappers.checkBoxSelection(driver, By.xpath(
                                        "//span[normalize-space(text()) = 'Which of the following have you learned in Crio.Do for Automation Testing?']/following::div[@class='uHMk6b fsHoPb'][position() = 4]"));

                        wrappers.checkBoxSelection(driver, By.xpath(
                                        "//span[normalize-space(text()) = 'Which of the following have you learned in Crio.Do for Automation Testing?']/following::div[@class='uHMk6b fsHoPb'][position() = 2]"));

                        System.out.println("Address as:");
                        wrappers.selectFromDropdown(driver, By.xpath(
                                        "//*[normalize-space(text()) = 'How should you be addressed?']/ancestor::div[4]//div[@class='MocG8c HZ3kWc mhLiyf LMgvRb KKjvXb DEh1R']"),
                                        "Mr");

                        // Date as per question
                        System.out.println("Update the current date - seven days(24*60*60*1000*7)");
                        Thread.sleep(2000);
                        wrappers.inputText(driver,
                                        By.xpath("//input[@class='whsOnd zHQkBf' and @aria-labelledby='i50']"),
                                        wrappers.dateSelection("dd/mm/yyyy", (long) 604800000));
                        // update the time as per epoch timing
                        System.out.println("update the current time(HH:MM)");
                        wrappers.inputText(driver, By.xpath(
                                        "(//*[normalize-space(text()) = 'What is the time right now?']//ancestor::div[4]//input[@class='whsOnd zHQkBf'])[1]"),
                                        wrappers.dateSelection("HH", 0));
                        wrappers.inputText(driver, By.xpath(
                                        "(//*[normalize-space(text()) = 'What is the time right now?']//ancestor::div[4]//input[@class='whsOnd zHQkBf'])[2]"),
                                        wrappers.dateSelection("MM", 0));

                        // Click sumbit button
                        System.out.println("Search the submit button and click");
                        driver.findElement(By.xpath("//*[normalize-space(text())='Submit']/ancestor::div[1]")).click();
                        Thread.sleep(2000);
                        System.out.println("Get and print the Success message");
                        //System.out.println(driver.findElement(By.xpath("//div[@role='heading']/../div[3]")).getText());
                        String successMessage = driver.findElement(By.xpath("//div[@role='heading']/../div[3]"))
                                        .getText();
                        System.out.println(successMessage);
                        Assert.assertNotNull(successMessage, "Failed to read the success message");

                        Thread.sleep(3000);

                } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Failure!");
                }
        }

        @AfterTest
        public void endTest() {
                driver.close();
                driver.quit();

        }
}