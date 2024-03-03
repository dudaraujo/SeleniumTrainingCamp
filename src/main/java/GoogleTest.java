import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GoogleTest {
@Test
    public void Test() {

        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        //System.setProperty("webdriver.chrome.driver", "drivers/chromedrivers.exe");
        WebDriver driver = new FirefoxDriver();
        //WebDriver driver = new ChromeDriver();

        driver.get("https://www.google.com");
        driver.manage().window().setSize(new Dimension(1200, 765));
        System.out.println(driver.getTitle());

        Assert.assertEquals("Google", driver.getTitle());

        driver.quit();
    }
}
