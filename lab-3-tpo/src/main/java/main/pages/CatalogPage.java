package main.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CatalogPage extends Page {

    public CatalogPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void open() {
        driver.get("https://megamarket.ru/catalog/");
        wait.until(d -> ((JavascriptExecutor) d)
                .executeScript("return document.readyState").equals("complete"));
    }

    public boolean checkCategories() {
        try {
            return driver.findElement(By.xpath("//div[@class='inverted-catalog-category catalog-department__category-item']")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickCountryHouse() {
        driver.findElement(By.xpath("//h3[text()='Дача, сезонные товары']")).click();
    }

    public void clickFlowers() {
        driver.findElement(By.xpath("//h3[text()='Цветы с доставкой']")).click();
    }

    public void addFlowers() {
        driver.findElement(By.xpath("//button[@class='catalog-buy-button__button btn sm']")).click();
    }

    public void clickHighRating() {
        //span[text()='Цена']/../../
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[3]/div/main/div/div[1]/div[3]/div/div[2]/div/div[2]/div[1]/div/div/div/div[2]/nav/div[4]/div/div/div/div[1]/div/span")).click();
    }

    public boolean checkSeller() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'catalog-listing__container_loading')]")
        ));
        driver.findElement(By.xpath("//div[contains(text(),'Flawery.ru')]")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'catalog-listing__container_loading')]")
        ));
        WebElement e = driver.findElement(By.xpath("//span[@class='merchant-info__name']"));
        String name = e.getText();
        return name.contains("Flawery");
    }

    public boolean checkTypeFlowers() {
        driver.findElement(By.xpath("//div[contains(text(),'букет цветов')]")).click();
        WebElement e = driver.findElement(By.xpath("//a[@class='catalog-item-regular-desktop__title-link ddl_product_link']"));
        String title = e.getAttribute("title");
        return title.contains("букет");
    }

    public boolean checkMinPrice(int price) {
        driver.findElement(By.xpath("//span[text()='Цена']/../../../div[2]/div/div[1]/label[1]/input")).sendKeys(String.valueOf(price));
        String strPrice = driver.findElement(By.xpath("//div[@class='catalog-item-regular-desktop__price']")).getText();
        int intPrice = Integer.parseInt(strPrice.replaceAll("[^0-9]", "").trim());
        return intPrice >= price;
    }

}
