package br.ce.dudaraujo.test;

import br.ce.dudaraujo.core.BaseTest;
import br.ce.dudaraujo.core.DSL;
import br.ce.dudaraujo.page.TrainingCampPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static br.ce.dudaraujo.core.DriverFactory.getDriver;


public class BusinessRuleTrainingCamp extends BaseTest {

    private DSL dsl;

    private TrainingCampPage page;

    @Before
    public void initialize() {
        getDriver().get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");
        dsl = new DSL();
        page = new TrainingCampPage();
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
