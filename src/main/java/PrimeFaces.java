import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PrimeFaces {

    private WebDriver driver;
    private DSL dsl;


    @Before
    public void initialize() {
        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml?jfwid=2f037");
        dsl = new DSL(driver);

    }
    @After
    public void quit() {
        driver.quit();
    }

    @Test
    public void shouldSelectComboOnPrimeFaces() {
        //VAI DAR ERRO POIS O SITE FOI ATUALIZADO
        dsl.clickXpath("//*[id='j_idt86:console_input']/..//span");
        dsl.clickXpath("//*[id='j_idt86:console_items']//li[.='PS4']");

    }
}
