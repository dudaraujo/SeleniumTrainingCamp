import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AlertTrainingCamp {

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
    public void shoulClickOnSimpleAlert() {

        dsl.click("alert");
        String text = dsl.readAndAcceptAlert();
        Assert.assertEquals("Alert Simples", text);

        dsl.write("elementosForm:nome", text);

    }

    @Test
    public void shouldClickInOkayOnConfirmeAlert() {

        dsl.click("confirm");

        Assert.assertEquals("Confirm Simples", dsl.readAndAcceptAlert());
        Assert.assertEquals("Confirmado", dsl.readAndAcceptAlert());

    }

    @Test
    public void shouldClickInCancelOnConfirmeAlert() {

        dsl.click("confirm");

        Assert.assertEquals("Confirm Simples", dsl.readAndRecuseAlert());

        Assert.assertEquals("Negado", dsl.readAndAcceptAlert());

    }

    @Test
    public void shouldClickAndWriteOnPromptAlert() {

        dsl.click("prompt");
        Assert.assertEquals("Digite um numero", dsl.readAlert());
        dsl.writeAlert("100");

        Assert.assertEquals("Era 100?", dsl.readAndAcceptAlert());

        Assert.assertEquals(":D", dsl.readAndAcceptAlert());

    }


}
