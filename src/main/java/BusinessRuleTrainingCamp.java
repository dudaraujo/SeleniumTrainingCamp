import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class BusinessRuleTrainingCamp {

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
    public void shouldValiateRequiredName() {

        page.setSubmitButton();
        Assert.assertEquals("Nome eh obrigatorio", dsl.readAndAcceptAlert());
    }

    @Test
    public void shouldValidateSurnameRequired() {

        page.setName("Maria Eduarda");
        page.setSubmitButton();

        Assert.assertEquals("Sobrenome eh obrigatorio", dsl.readAndAcceptAlert());
    }

    @Test
    public void shouldValidateSexField() {

        page.setName("Maria Eduarda");
        page.setFullName("Araújo");
        page.setSubmitButton();

        Assert.assertEquals("Sexo eh obrigatorio", dsl.readAndAcceptAlert());
    }

    @Test
    public void shouldValidateFavoriteFoodRule() {

        page.setName("Maria Eduarda");
        page.setFullName("Araújo");
        page.setFemale();
        page.setFavoriteFoodCarne();
        page.setFavoriteFoodVegetariano();

        page.setSubmitButton();

        Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.readAndAcceptAlert());
    }

    @Test
    public void shouldValidateSportsRule() {

        page.setName("Maria Eduarda");
        page.setFullName("Araújo");
        page.setFemale();
        page.setFavoriteFoodCarne();
        page.setSport("Corrida", "O que eh esporte?");

        page.setSubmitButton();

        Assert.assertEquals("Voce faz esporte ou nao?", dsl.readAndAcceptAlert());
    }



}
