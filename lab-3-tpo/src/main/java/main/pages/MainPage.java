package main.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends Page {

    public MainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void open() {
        driver.get("https://megamarket.ru/");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(), 'Войти')]")));
        wait.until(d -> ((JavascriptExecutor) d)
                .executeScript("return document.readyState").equals("complete"));
    }

    public void login () {
        driver.findElement(By.xpath("//button[contains(text(), 'Войти')]")).click();
    }

    public void clickSearch() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(), 'Найти товары')] ")));
        driver.findElement(By.xpath("//div[contains(text(), 'Найти товары')] ")).click();
    }

    public boolean searchRealme() {
        driver.findElement(By.xpath("//textarea")).sendKeys("realme gt 6", Keys.ENTER);
        wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
        return driver.findElements(By.xpath("//a[contains(text(), 'Смартфон Realme GT 6')]")).size() > 0;
    }

    public void openFavorites() {
        driver.findElement(By.xpath("//a[@class='desktop-navigation-tabs__item']")).click();
        driver.findElement(By.xpath("//h1[contains(text, Избранное)]"));
    }

    public void openMulticart() {
        driver.findElement(By.xpath("//a[@class='multicart-tab desktop-navigation-tabs__multicart-button']")).click();
        driver.findElement(By.xpath("//h1[contains(text,Корзина)]"));
    }

    public void openAddress() {
        driver.findElement(By.xpath("//button[@class='address-button']")).click();
    }

    public void enterAddress(String address) {
        driver.findElement(By.xpath("//input[@class='search-field-input sm']")).sendKeys(address);
        driver.findElement(By.xpath("//mark[contains(text(), 'Санкт-Петербург, Вяземский переулок, 5-7')]")).click();
    }

    public void addAdress() {
        driver.findElement(By.xpath("//button[@class='pui-button-element pui-button-element_variant_primary pui-button-element_size_md']")).click();
    }

    public void clickOnMap() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//canvas")));
        driver.findElement(By.xpath("//div[@class='geo-selector-map set-address-modal__map']")).click();
    }

    public void addProductToCart() {
        driver.findElement(By.xpath("//button[@class='catalog-buy-button__button product-card__buy-button-circle xs']")).click();

    }

    public void clickOnInputShop() {
        driver.findElement(By.xpath("//input[@class='search-field-input md']")).sendKeys("Москва", Keys.ENTER);
        driver.findElement(By.xpath("//mark[text()='Москва']")).click();
    }

    public void chooseShop() {
        driver.findElement(By.xpath("//div[@class='store-list__list-item store-item']")).click();
        driver.findElement(By.xpath("//button[@class='c-button c-button_theme_primary c-button_size_medium c-button_fullwidth']")).click();
    }

    public boolean emptyShopList() {
        try {
            return driver.findElement(By.xpath("//div[@class='store-list__empty']")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean mustChooseShop() {
        try {
            return driver.findElement(By.xpath("//div[text()='Выберите магазин']")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }




}
