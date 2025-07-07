package main.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {
    public WebDriver driver;
    public WebDriverWait wait;
    public Page (WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0, 500)");
//        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//        Actions actions = new Actions(driver);
//        actions.sendKeys(Keys.PAGE_DOWN).build().perform();
    }

    public boolean checkInvitationLogin() {
        try {
            return driver.findElement(By.xpath("//span[@class='auth-main__title']")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


}
