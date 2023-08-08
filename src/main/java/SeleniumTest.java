import org.apache.log4j.*;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class SeleniumTest {
    SeleniumTest() {

        Properties prop = new Properties();
        prop.setProperty("log4j.rootLogger", "WARN");
        PropertyConfigurator.configure(prop);

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("https://harmonogram.mpo.krakow.pl/");

        WebElement sel_ulice = driver.findElement(By.id("sel_ulice"));

        //TODO

        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        //countryDropdown.selectByValue("2 Pułku Lotniczego");

        //new Select(new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.id("sel_ulice")))).selectByVisibleText("Akacjowa");

        //driver.quit();
    }
}
