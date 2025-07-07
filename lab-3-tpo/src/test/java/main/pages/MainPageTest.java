package main.pages;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;


public class MainPageTest {

    private MainPage mainPage;
    private WebDriver driver;

    @BeforeEach
    public void initialization() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(52));
        mainPage = new MainPage(driver, wait);
    }

    @Test
    public void testLogin() {
        mainPage.open();
        mainPage.login();
        assertTrue(mainPage.checkInvitationLogin());
    }

    @Test
    public void searchPhone () {
        mainPage.open();
        mainPage.clickSearch();
        assertTrue(mainPage.searchRealme());
    }

    @Test
    void openFavorites() {
        mainPage.open();
        mainPage.openFavorites();
    }

    @Test
    void openMulticart() {
        mainPage.open();
        mainPage.openMulticart();
    }

    @Test
    void addAdressViaMap() {
        mainPage.open();
        mainPage.openAddress();
        mainPage.clickOnMap();
        mainPage.addAdress();
    }

    @Test
    void addAdressViaInput() {
        mainPage.open();
        mainPage.openAddress();
        mainPage.enterAddress("Санкт-Петербург, Вяземский переулок, 5-7");
        mainPage.addAdress();
    }

    @Test
    void addProductToCart() {
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


    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
