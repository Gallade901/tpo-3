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

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CatalogPageTest {

    private CatalogPage catalogPage;
    private WebDriver driver;

//    @BeforeEach
    public void initialization(WebDriver webDriver) {
        driver = webDriver;
//        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(52));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(52));
        catalogPage = new CatalogPage(driver, wait);
    }

    static Stream<Arguments> browserProvider() {
        return Stream.of(
                Arguments.of(new ChromeDriver()),
                Arguments.of(new FirefoxDriver())
        );
    }

    @ParameterizedTest
    @MethodSource("browserProvider")
    public void buyFlower(WebDriver webDriver) {
        initialization(webDriver);
        catalogPage.open();
        catalogPage.checkCategories();
        catalogPage.clickCountryHouse();
        catalogPage.clickFlowers();
        catalogPage.addFlowers();
        assertTrue(catalogPage.checkProductInCart());
    }

    @ParameterizedTest
    @MethodSource("browserProvider")
    public void checkFilters(WebDriver webDriver) {
        initialization(webDriver);
        catalogPage.open();
        catalogPage.checkCategories();
        catalogPage.clickCountryHouse();
        catalogPage.clickFlowers();
        catalogPage.clickHighRating();
        catalogPage.scrollDown();
        assertTrue(catalogPage.checkSeller());
        assertTrue(catalogPage.checkMinPrice(3000));
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
