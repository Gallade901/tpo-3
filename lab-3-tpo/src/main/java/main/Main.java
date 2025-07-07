package main;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class Main {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        driver.get("https://megamarket.ru/");
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
//        WebElement input = driver.findElement(By.xpath("input"));
//        driver.getTitle();
//
//        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
//
//        WebElement textBox = driver.findElement(By.name("my-text"));
//        WebElement submitButton = driver.findElement(By.cssSelector("button"));
//
//        textBox.sendKeys("Selenium");
//        submitButton.click();
//
//        WebElement message = driver.findElement(By.id("message"));
//        message.getText();
//
//        driver.quit();
    }
}