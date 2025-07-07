package main.pages;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MulticartPageTest {

    private MulticartPage multicartPage;
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeEach
    public void initialization() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(52));
        multicartPage = new MulticartPage(driver, wait);
        mainPage = new MainPage(driver, wait);
        mainPage.open();
        mainPage.scrollDown();
        mainPage.addProductToCart();
        if (mainPage.mustChooseShop()) {
            if (mainPage.emptyShopList()) {
                mainPage.clickOnInputShop();
            }
            mainPage.chooseShop();
        }
        assertTrue(mainPage.checkProductInCart());

    }

    @Test
    public void testDeleteProduct() {
        multicartPage.open();
        multicartPage.deleteProduct();
        assertTrue(multicartPage.checkEmptyCart());
    }

    @Test
    public void placeAnOrder() {
        multicartPage.open();
        multicartPage.clickPlaceAnOrder();
        assertTrue(multicartPage.checkInvitationLogin());
    }

}
