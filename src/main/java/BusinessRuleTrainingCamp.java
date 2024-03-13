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
    public void shouldValiateRequiredName() {

        dsl.click("elementosForm:cadastrar");

        Assert.assertEquals("Nome eh obrigatorio", dsl.readAndAcceptAlert());

    }

    @Test
    public void shouldValidateSurnameRequired() {

        dsl.write("elementosForm:nome", "Maria Eduarda");
        dsl.click("elementosForm:cadastrar");

        Assert.assertEquals("Sobrenome eh obrigatorio", dsl.readAndAcceptAlert());

    }

    @Test
    public void shouldValidateSexField() {

        dsl.write("elementosForm:nome", "Maria Eduarda");
        dsl.write("elementosForm:sobrenome", "Araújo");
        dsl.click("elementosForm:cadastrar");

        Assert.assertEquals("Sexo eh obrigatorio", dsl.readAndAcceptAlert());

    }

    @Test
    public void shouldValidateFavoriteFoodRule() {

        dsl.write("elementosForm:nome", "Maria Eduarda");
        dsl.write("elementosForm:sobrenome", "Araújo");
        dsl.click("elementosForm:sexo:1");

        dsl.click("elementosForm:comidaFavorita:0");
        dsl.click("elementosForm:comidaFavorita:3");

        dsl.click("elementosForm:cadastrar");

        Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.readAndAcceptAlert());

    }

    @Test
    public void shouldValidateSportsRule() {

        dsl.write("elementosForm:nome", "Maria Eduarda");
        dsl.write("elementosForm:sobrenome", "Araújo");
        dsl.click("elementosForm:sexo:1");
        dsl.click("elementosForm:comidaFavorita:0");

        dsl.selectCombo("elementosForm:esportes", "Corrida");
        dsl.selectCombo("elementosForm:esportes", "O que eh esporte?");


        dsl.click("elementosForm:cadastrar");


        Assert.assertEquals("Voce faz esporte ou nao?", dsl.readAndAcceptAlert());

    }



}
