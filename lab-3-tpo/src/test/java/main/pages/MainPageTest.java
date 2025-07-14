package main.pages;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.stream.Stream;


public class MainPageTest {

    private MainPage mainPage;
    private WebDriver driver;

//    @BeforeEach
    public void initialization(WebDriver webDriver) {
        driver = webDriver;
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(52));
        mainPage = new MainPage(driver, wait);
    }

    static Stream<Arguments> browserProvider() {
        return Stream.of(
                Arguments.of(new ChromeDriver()),
                Arguments.of(new FirefoxDriver())
        );
    }


    @ParameterizedTest
    @MethodSource("browserProvider")
    public void testLogin(WebDriver webDriver) {
        initialization(webDriver);
        mainPage.open();
        mainPage.login();
        assertTrue(mainPage.checkInvitationLogin());
    }


    @ParameterizedTest
    @MethodSource("browserProvider")
    public void searchPhone (WebDriver webDriver) {
        initialization(webDriver);
        mainPage.open();
        mainPage.clickSearch();
        assertTrue(mainPage.searchRealme());
    }

    @ParameterizedTest
    @MethodSource("browserProvider")
    void openFavorites(WebDriver webDriver) {
        initialization(webDriver);
        mainPage.open();
        mainPage.openFavorites();
    }

    @ParameterizedTest
    @MethodSource("browserProvider")
    void openMulticart(WebDriver webDriver) {
        initialization(webDriver);
        mainPage.open();
        mainPage.openMulticart();
    }

    @ParameterizedTest
    @MethodSource("browserProvider")
    void addAdressViaMap(WebDriver webDriver) {
        initialization(webDriver);
        mainPage.open();
        mainPage.openAddress();
        mainPage.clickOnMap();
        mainPage.addAdress();
    }

    @ParameterizedTest
    @MethodSource("browserProvider")
    void addAdressViaInput(WebDriver webDriver) {
        initialization(webDriver);
        mainPage.open();
        mainPage.openAddress();
        mainPage.enterAddress("Санкт-Петербург, Вяземский переулок, 5-7");
        mainPage.addAdress();
    }

    @ParameterizedTest
    @MethodSource("browserProvider")
    void addProductToCart(WebDriver webDriver) {
        initialization(webDriver);
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
