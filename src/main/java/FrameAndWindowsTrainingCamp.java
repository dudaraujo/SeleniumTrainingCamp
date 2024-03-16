import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FrameAndWindowsTrainingCamp {

    private WebDriver driver;
    private DSL dsl;

    @Before
    public void initialize() {
        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");
        dsl = new DSL(driver);
    }
    @After
    public void quit() {
        driver.quit();
    }

    @Test
    public void shouldClickOnFrame() {


        dsl.enterFrame("frame1");
        dsl.click("frameButton");
        String msg = dsl.readAndAcceptAlert();
        Assert.assertEquals("Frame OK!", msg);
        dsl.exitFrame();
        dsl.write("elementosForm:nome", msg);

    }

    @Test
    public void shouldClickOnNamedExternalWindow(){

        dsl.click("buttonPopUpEasy");

        dsl.switchWindow("Popup");

        dsl.writeByTagName("textarea", "Deu certo!");

        driver.close();

        dsl.switchWindow((String) driver.getWindowHandles().toArray()[0]);

        dsl.writeByTagName("textarea", "Deu certo!");


    }

    @Test
    public void shouldClickOnAdvancedExternalWindow() {

        dsl.click("buttonPopUpHard");

        System.out.println(driver.getWindowHandle());
        System.out.println(driver.getWindowHandles());

        driver.switchTo().window((String)driver.getWindowHandles().toArray()[1]);

        driver.findElement(By.tagName("textarea")).sendKeys("Deu certo a pop up dificil");

        driver.switchTo().window((String)driver.getWindowHandles().toArray()[0]);

        driver.findElement(By.tagName("textarea")).sendKeys("Deu certo a pop up dificil");

    }

    @Test
    public void shouldClickOnHiddenFrame(){
        WebElement frame = driver.findElement(By.id("frame2"));
        dsl.executarJs("window.scrollBy(0, arguments[0])", frame.getLocation().y);
        dsl.enterFrame("frame2");
        dsl.click("frameButton");
        String msg = dsl.readAndAcceptAlert();
        Assert.assertEquals("Frame OK!", msg);
    }

}
