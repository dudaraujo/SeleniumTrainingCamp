package br.ce.dudaraujo.test;

import br.ce.dudaraujo.core.BaseTest;
import br.ce.dudaraujo.core.DSL;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static br.ce.dudaraujo.core.DriverFactory.getDriver;
import static br.ce.dudaraujo.core.DriverFactory.killDriver;

public class AjaxTest extends BaseTest {

    private DSL dsl;


    @Before
    public void initialize() {
        getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml?jfwid=84ecf");
        dsl = new DSL();
    }

    @Test
    public void writeOnAjaxButtonWaitForVisibleText() {
        dsl.write("j_id_7w:name", "Teste com ajax");
        dsl.click("j_id_7w:j_id_82");
        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        wait.until(ExpectedConditions.textToBe(By.id("j_id_7w:display"), "Teste com ajax"));
        Assert.assertEquals("Teste com ajax", dsl.getFieldText(By.id("j_id_7w:display")));
    }

    @Ignore
    @Test
    public void writeOnAjaxButtonWaitForInvisibleLoading() {
        dsl.write("j_id_7w:name", "Teste com ajax");
        dsl.click("j_id_7w:j_id_82");
        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("")));
        Assert.assertEquals("Teste com ajax", dsl.getFieldText(By.id("j_id_7w:display")));
    }
}
