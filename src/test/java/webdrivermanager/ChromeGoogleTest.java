package webdrivermanager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class ChromeGoogleTest {
    private WebDriver driver;

    private static final String searchRequest = "SpaceX crew dragon";

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setupTest() {
        driver = new ChromeDriver();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.get("https://www.google.com/");
        By searchInput = By.name("q");
        wait.until(presenceOfElementLocated(searchInput));
        driver.findElement(searchInput).sendKeys(searchRequest);
        By searchButton = By.name("btnK");
        wait.until(elementToBeClickable(searchButton));
        driver.findElement(searchButton).click();

        By resultStats = By.id("result-stats");
        wait.until(presenceOfElementLocated(resultStats));

        WebElement resultStatsElement = driver.findElement(resultStats);
        System.out.println("По запиту: " + searchRequest);
        System.out.println(resultStatsElement.getText());
    }
}
