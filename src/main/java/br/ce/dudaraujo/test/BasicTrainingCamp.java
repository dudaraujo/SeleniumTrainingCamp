package br.ce.dudaraujo.test;

import br.ce.dudaraujo.core.BaseTest;
import br.ce.dudaraujo.core.DSL;
import br.ce.dudaraujo.page.TrainingCampPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

import static br.ce.dudaraujo.core.DriverFactory.getDriver;
import static br.ce.dudaraujo.core.DriverFactory.killDriver;


public class BasicTrainingCamp extends BaseTest {

    private DSL dsl;

    private TrainingCampPage page;

    @Before
    public void initialize() {
        getDriver().get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");
        dsl = new DSL();
        page = new TrainingCampPage();
    }


    @Test
    public void shouldWriteInNameField() {

        page.setName("Maria Eduarda");
        Assert.assertEquals("Maria Eduarda", page.getName());
    }

    @Test
    public void shouldWriteTwoTimesisNameField() {

        page.setName("Maria Eduarda");
        Assert.assertEquals("Maria Eduarda", page.getName());
        page.setName("Dudinha");
        Assert.assertEquals("Dudinha", page.getName());

    }
    @Test
    public void shouldWriteInTextArea() {

        page.setSuggestion("Esse é um campo de área grande");
        Assert.assertEquals("Esse é um campo de área grande", page.getSuggestion());
    }

    @Test
    public void shouldCheckRadioButton() {

        page.setFemale();
        Assert.assertTrue(page.getFemale());

    }

    @Test
    public void shouldClickOnCheckButton() {

        page.setFavoriteFoodPizza();
        Assert.assertTrue(page.getFavoriteFoodPizza());
    }

    @Test
    public  void shouldSelectComboField() {

        page.setGraduation("Superior");
        Assert.assertEquals("Superior", page.getGraduation());
    }



    @Test
    public void shouldHasAllTheOptionsInComboField() {

        Assert.assertEquals(8, dsl.getNumberOptionsComboField("elementosForm:escolaridade"));
        Assert.assertTrue(dsl.getOptionComboField("elementosForm:escolaridade", "Mestrado"));

    }

    @Test
    public void shoulSelectSeveralOptions() {

        page.setSport("Natacao", "Corrida", "Karate");


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

    @Test
    public void testJavaScript() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrita via JS'");
        //js.executeScript("alert('Testando JS')");


        WebElement element = getDriver().findElement(By.id("elementosForm:nome"));
        js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
    }

    @Test
    public void shouldClickOnTableButton() {
        dsl.clickOnTableButton("Nome", "Maria", "Botao",  "elementosForm:tableUsuarios");
    }

}
