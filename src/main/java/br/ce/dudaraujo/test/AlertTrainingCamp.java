package br.ce.dudaraujo.test;

import br.ce.dudaraujo.core.BaseTest;
import br.ce.dudaraujo.core.DSL;
import br.ce.dudaraujo.page.TrainingCampPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static br.ce.dudaraujo.core.DriverFactory.getDriver;
import static br.ce.dudaraujo.core.DriverFactory.killDriver;

public class AlertTrainingCamp extends BaseTest {
    private DSL dsl;

    private TrainingCampPage page;

    @Before
    public void initialize() {
        getDriver().get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");
        dsl = new DSL();
        page = new TrainingCampPage();
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
