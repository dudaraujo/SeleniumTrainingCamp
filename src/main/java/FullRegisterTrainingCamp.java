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
    public void shouldCompleteRegister() {

        dsl.write("elementosForm:nome", "Maria Eduarda");
        dsl.write("elementosForm:sobrenome", "Araújo");
        dsl.click("elementosForm:sexo:1");
        dsl.click("elementosForm:comidaFavorita:2");

        dsl.selectCombo("elementosForm:escolaridade", "Superior");


        dsl.selectCombo("elementosForm:esportes", "Natacao");
        dsl.selectCombo("elementosForm:esportes", "Corrida");
        dsl.selectCombo("elementosForm:esportes", "Karate");

        dsl.write("elementosForm:sugestoes", "Essa é uma sugestão");


        dsl.click("elementosForm:cadastrar");

        Assert.assertTrue(dsl.getFieldText(By.id("resultado")).startsWith("Cadastrado"));
        Assert.assertTrue(dsl.getFieldText(By.id("descNome")).endsWith("Maria Eduarda"));
        Assert.assertEquals("Sobrenome: Araújo", dsl.getFieldText(By.id("descSobrenome")));
        Assert.assertEquals("Sexo: Feminino", dsl.getFieldText(By.id("descSexo")));
        Assert.assertEquals("Comida: Pizza", dsl.getFieldText(By.id("descComida")));
        Assert.assertEquals("Escolaridade: superior", dsl.getFieldText(By.id("descEscolaridade")));
        Assert.assertEquals("Esportes: Natacao Corrida Karate", dsl.getFieldText(By.id("descEsportes")));
        Assert.assertEquals("Sugestoes: Essa é uma sugestão", dsl.getFieldText(By.id("descSugestoes")));


    }
}
