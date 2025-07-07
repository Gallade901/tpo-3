package main.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MulticartPage extends Page {

    public MulticartPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void open() {
        driver.get("https://megamarket.ru/multicart");
        wait.until(d -> ((JavascriptExecutor) d)
                .executeScript("return document.readyState").equals("complete"));

    }

    public void deleteProduct() {
        driver.findElement(By.xpath("//button[@class='good__remove']")).click();
    }

    public boolean checkEmptyCart() {
        try {
            return driver.findElement(By.xpath("//p[@class='cart-empty__description']")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickPlaceAnOrder() {
        driver.findElement(By.xpath("//button[@class='pui-button-element pui-button-element_variant_primary pui-button-element_size_md pui-button-element_fullwidth']")).click();
    }

}
