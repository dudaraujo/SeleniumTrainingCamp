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

    private TrainingCampPage page;

    @Before
    public void initialize() {
        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");
        dsl = new DSL(driver);
        page = new TrainingCampPage(driver);
    }
    @After
    public void quit() {
        driver.quit();
    }

    @Test
    public void shoulClickOnSimpleAlert() {

        page.setSimpleAlertButton();
        String text = dsl.readAndAcceptAlert();
        Assert.assertEquals("Alert Simples", text);

        page.setName(text);

    }

    @Test
    public void shouldClickInOkayOnConfirmeAlert() {

        page.setConfirmAlertButton();

        Assert.assertEquals("Confirm Simples", dsl.readAndAcceptAlert());
        Assert.assertEquals("Confirmado", dsl.readAndAcceptAlert());
    }

    @Test
    public void shouldClickInCancelOnConfirmeAlert() {

        page.setConfirmAlertButton();

        Assert.assertEquals("Confirm Simples", dsl.readAndRecuseAlert());

        Assert.assertEquals("Negado", dsl.readAndAcceptAlert());

    }

    @Test
    public void shouldClickAndWriteOnPromptAlert() {

        page.setPromptAlertButton();
        Assert.assertEquals("Digite um numero", dsl.readAlert());
        dsl.writeAlert("100");

        Assert.assertEquals("Era 100?", dsl.readAndAcceptAlert());

        Assert.assertEquals(":D", dsl.readAndAcceptAlert());

    }


}
