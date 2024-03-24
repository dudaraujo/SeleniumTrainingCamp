package br.ce.dudaraujo.test;

import br.ce.dudaraujo.core.BaseTest;
import br.ce.dudaraujo.core.DSL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static br.ce.dudaraujo.core.DriverFactory.getDriver;

public class PrimeFaces extends BaseTest {

    private DSL dsl;


    @Before
    public void initialize() {
       getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml?jfwid=2f037");
        dsl = new DSL();

    }

    @Test
    public void shouldSelectComboOnPrimeFaces() {
        //VAI DAR ERRO POIS O SITE FOI ATUALIZADO
        dsl.clickXpath("//*[id='j_idt86:console_input']/..//span");
        dsl.clickXpath("//*[id='j_idt86:console_items']//li[.='PS4']");

    }
}
