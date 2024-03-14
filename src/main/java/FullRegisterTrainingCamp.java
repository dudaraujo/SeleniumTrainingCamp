import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.sql.Driver;
import java.util.List;

public class FullRegisterTrainingCamp {

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
    public void shouldCompleteRegister() {


        page.setName("Maria Eduarda");
        page.setFullName("Araújo");
        page.setFemale();
        page.setFavoriteFoodPizza();
        page.setGraduation("Superior");
        page.setSport("Natacao","Corrida", "Karate");
        page.setSuggestion("Essa é uma sugestão");

        page.setSubmitButton();

        Assert.assertTrue(page.getResult().startsWith("Cadastrado"));
        Assert.assertTrue(page.getResultName().endsWith("Maria Eduarda"));
        Assert.assertEquals("Sobrenome: Araújo", page.getResultFullName());
        Assert.assertEquals("Sexo: Feminino", page.getResultSex());
        Assert.assertEquals("Comida: Pizza", page.getResultFood());
        Assert.assertEquals("Escolaridade: superior", page.getResultGraduation());
        Assert.assertEquals("Esportes: Natacao Corrida Karate", page.getResultSport());
        Assert.assertEquals("Sugestoes: Essa é uma sugestão", page.getResultSuggestion());


    }
}
