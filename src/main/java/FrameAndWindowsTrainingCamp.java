import br.ce.dudaraujo.core.DSL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static br.ce.dudaraujo.core.DriverFactory.getDriver;
import static br.ce.dudaraujo.core.DriverFactory.killDriver;

public class FrameAndWindowsTrainingCamp {

    private DSL dsl;

    @Before
    public void initialize() {
        getDriver().get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");
        dsl = new DSL();
    }
    @After
    public void quit() {
        killDriver();
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

        getDriver().close();

        dsl.switchWindow((String) getDriver().getWindowHandles().toArray()[0]);

        dsl.writeByTagName("textarea", "Deu certo!");


    }

    @Test
    public void shouldClickOnAdvancedExternalWindow() {

        dsl.click("buttonPopUpHard");

        System.out.println(getDriver().getWindowHandle());
        System.out.println(getDriver().getWindowHandles());

        getDriver().switchTo().window((String)getDriver().getWindowHandles().toArray()[1]);

        getDriver().findElement(By.tagName("textarea")).sendKeys("Deu certo a pop up dificil");

        getDriver().switchTo().window((String)getDriver().getWindowHandles().toArray()[0]);

        getDriver().findElement(By.tagName("textarea")).sendKeys("Deu certo a pop up dificil");

    }

    @Test
    public void shouldClickOnHiddenFrame(){
        WebElement frame = getDriver().findElement(By.id("frame2"));
        dsl.executarJs("window.scrollBy(0, arguments[0])", frame.getLocation().y);
        dsl.enterFrame("frame2");
        dsl.click("frameButton");
        String msg = dsl.readAndAcceptAlert();
        Assert.assertEquals("Frame OK!", msg);
    }

}
