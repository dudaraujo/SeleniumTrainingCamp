import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FrameAndWindowsTrainingCamp {

    private WebDriver driver;

    @Before
    public void initialize() {
        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");
    }
    @After
    public void quit() {
        driver.quit();
    }

    @Test
    public void shouldClickOnFrame() {

        driver.switchTo().frame("frame1");

        driver.findElement(By.id("frameButton")).click();

        Alert alert = driver.switchTo().alert();

        Assert.assertEquals("Frame OK!", alert.getText());

        String msg = alert.getText();

        alert.accept();

        driver.switchTo().defaultContent();

        driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);

    }

    @Test
    public void shouldClickOnNamedExternalWindow(){

        driver.findElement(By.id("buttonPopUpEasy")).click();

        driver.switchTo().window("Popup");

        driver.findElement(By.tagName("textarea")).sendKeys("Deu certo!");

        driver.close();

        driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);

        driver.findElement(By.tagName("textarea")).sendKeys("Deu certo!");

    }

    @Test
    public void shouldClickOnAdvancedExternalWindow() {

        driver.findElement(By.id("buttonPopUpHard")).click();

        System.out.println(driver.getWindowHandle());
        System.out.println(driver.getWindowHandles());

        driver.switchTo().window((String)driver.getWindowHandles().toArray()[1]);

        driver.findElement(By.tagName("textarea")).sendKeys("Deu certo a pop up dificil");

        driver.switchTo().window((String)driver.getWindowHandles().toArray()[0]);

        driver.findElement(By.tagName("textarea")).sendKeys("Deu certo a pop up dificil");

    }

}
