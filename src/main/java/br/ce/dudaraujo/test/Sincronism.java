package br.ce.dudaraujo.test;

import br.ce.dudaraujo.core.BaseTest;
import br.ce.dudaraujo.core.DSL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static br.ce.dudaraujo.core.DriverFactory.getDriver;

public class Sincronism extends BaseTest {

    private DSL dsl;


    @Before
    public void initialize() {
        getDriver().get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");
        dsl = new DSL();
    }

    @Test
    public void shoulClickOnDelayButtonFixWait() throws InterruptedException {

        dsl.click("buttonDelay");

        //espera fixa
        Thread.sleep(5000);
        dsl.write("novoCampo", "escevendo no campo delay");
    }

    @Test
    public void shoulClickOnDelayButtonImplicitWait() throws InterruptedException {
        //espera implicita
        //é comum ser usado no before dos testes
        getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        dsl.click("buttonDelay");
        dsl.write("novoCampo", "escevendo no campo delay");
        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    @Test
    public void shoulClickOnDelayButtonExplicitWait() throws InterruptedException {

        dsl.click("buttonDelay");

        //espera explicita -> melhor estratégia
        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));

        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }
}
