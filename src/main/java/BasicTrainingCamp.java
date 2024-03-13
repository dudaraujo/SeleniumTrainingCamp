import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BasicTrainingCamp {

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
    public void shouldWriteInNameField() {

        dsl.write("elementosForm:nome", "Maria Eduarda");
        Assert.assertEquals("Maria Eduarda", dsl.getFieldValue("elementosForm:nome"));

    }

    @Test
    public void shouldWriteTwoTimesisNameField() {
        dsl.write("elementosForm:nome", "Maria Eduarda");
        Assert.assertEquals("Maria Eduarda", dsl.getFieldValue("elementosForm:nome"));
        dsl.write("elementosForm:nome", "Dudinha");
        Assert.assertEquals("Dudinha", dsl.getFieldValue("elementosForm:nome"));
    }
    @Test
    public void shouldWriteInTextArea() {

        dsl.write("elementosForm:sugestoes","Esse é um campo de área grande");
        Assert.assertEquals("Esse é um campo de área grande", dsl.getFieldValue("elementosForm:sugestoes"));

    }

    @Test
    public void shouldCheckRadioButton() {

        dsl.click("elementosForm:sexo:1");

        Assert.assertTrue(dsl.getFieldSelected("elementosForm:sexo:1"));

    }

    @Test
    public void shouldClickOnCheckButton() {

        dsl.click("elementosForm:comidaFavorita:2");

        Assert.assertTrue(dsl.getFieldSelected("elementosForm:comidaFavorita:2"));

    }

    @Test
    public  void shouldSelectComboField() {

        dsl.selectCombo("elementosForm:escolaridade", "Superior");

        Assert.assertEquals("Superior", dsl.getFirstSelectedComboOption("elementosForm:escolaridade"));

    }



    @Test
    public void shouldHasAllTheOptionsInComboField() {

        Assert.assertEquals(8, dsl.getNumberOptionsComboField("elementosForm:escolaridade"));
        Assert.assertTrue(dsl.getOptionComboField("elementosForm:escolaridade", "Mestrado"));

    }

    @Test
    public void shoulSelectSeveralOptions() {

        dsl.selectCombo("elementosForm:esportes", "Natacao");
        dsl.selectCombo("elementosForm:esportes", "Corrida");
        dsl.selectCombo("elementosForm:esportes", "Karate");


        List allSelectedOptions = dsl.getComboValues("elementosForm:esportes");

        Assert.assertEquals(3, allSelectedOptions.size());

        dsl.deselectCombo("elementosForm:esportes", "Karate");

        allSelectedOptions = dsl.getComboValues("elementosForm:esportes");

        Assert.assertEquals(2, allSelectedOptions.size());
        //Assert.assertTrue(allSelectedOptions.containsAll(Arrays.asList("Corrida", "Natacao")));


    }

    @Test
    public void shouldChangeButtonValue() {


        dsl.buttonClick("buttonSimple");

        Assert.assertEquals("Obrigado!", dsl.getFieldValue("buttonSimple"));

    }

    @Test
    public void shouldClickOnLink() {

        dsl.clickOnLink("Voltar");

        Assert.assertEquals("Voltou!", dsl.getFieldText(By.id("resultado")));

    }

    @Test
    public void shouldFindElementOnScreen() {

        Assert.assertEquals("Campo de Treinamento", dsl.getFieldText(By.tagName("h3")));

        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.getFieldText(By.className("facilAchar")));

    }

}
