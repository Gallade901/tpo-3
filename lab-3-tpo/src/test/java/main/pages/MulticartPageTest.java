package main.pages;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MulticartPageTest {

    private MulticartPage multicartPage;
    private WebDriver driver;
    private MainPage mainPage;

//    @BeforeEach
    public void initialization(WebDriver webDriver) {
//        driver = new ChromeDriver();
        driver = webDriver;
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

    static Stream<Arguments> browserProvider() {
        return Stream.of(
                Arguments.of(new ChromeDriver()),
                Arguments.of(new FirefoxDriver())
        );
    }


    @ParameterizedTest
    @MethodSource("browserProvider")
    public void testDeleteProduct(WebDriver webDriver) {  // FIreFox проблема сайта
        initialization(webDriver);
        multicartPage.open();
        multicartPage.deleteProduct();
        assertTrue(multicartPage.checkEmptyCart());
    }

    @ParameterizedTest
    @MethodSource("browserProvider")
    public void placeAnOrder(WebDriver webDriver) {
        initialization(webDriver);
        multicartPage.open();
        multicartPage.clickPlaceAnOrder();
        assertTrue(multicartPage.checkInvitationLogin());
    }

}   
