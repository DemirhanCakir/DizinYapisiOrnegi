package tests;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestInvalidLogin {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // ChromeDriver yolun tanımlıysa bu satır gerekmez
        // System.setProperty("webdriver.chrome.driver", "C:/path/to/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testInvalidLogin() {
        driver.get("https://the-internet.herokuapp.com/login");

        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

        usernameInput.sendKeys("wronguser");
        passwordInput.sendKeys("wrongpass");
        loginButton.click();

        WebElement errorMessage = driver.findElement(By.id("flash"));
        String messageText = errorMessage.getText();

        System.out.println("Hata mesajı: " + messageText);

        assertTrue(messageText.contains("Your username is invalid!"),
                "Beklenen hata mesajı görünmedi.");
    }

    @AfterEach
    public void tearDown() {
        // Ekran görüntüsü almak istersen:
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File src = screenshot.getScreenshotAs(OutputType.FILE);
        File dest = new File("test-sonucu.png");
        src.renameTo(dest);

        driver.quit();
    }
}
