package com.example.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;

public class SampleSeleniumTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
    }

    @Test
    public void testGreetingFunctionality() {
        try {
            // Load local HTML file (convert to file:// URL)
            File htmlFile = new File("index.html");
            URL pageUrl = htmlFile.toURI().toURL();
            driver.get(pageUrl.toString());

            // Verify title
            Assert.assertEquals(driver.getTitle(), "Sample Test Page");

            // Type name and click button
            WebElement input = driver.findElement(By.id("nameInput"));
            WebElement button = driver.findElement(By.id("greetBtn"));
            WebElement result = driver.findElement(By.id("result"));

            input.sendKeys("Abhishek");
            button.click();

            // Verify greeting
            String output = result.getText().trim();
            Assert.assertEquals(output, "Hello, Abhishek!");
        } catch (Exception e) {
            Assert.fail("Test failed: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
