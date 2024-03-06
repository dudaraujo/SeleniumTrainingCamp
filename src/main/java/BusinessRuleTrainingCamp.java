import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class BusinessRuleTrainingCamp {

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
    public void shouldValiateRequiredName() {

        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();

        String msg = alert.getText();

        Assert.assertEquals("Nome eh obrigatorio", msg);

    }

    @Test
    public void shouldValidateSurnameRequired() {

        dsl.write("elementosForm:nome", "Maria Eduarda");

        dsl.click("elementosForm:cadastrar");

        Alert alert = driver.switchTo().alert();

        String msg = alert.getText();

        Assert.assertEquals("Sobrenome eh obrigatorio", msg);

    }

    @Test
    public void shouldValidateSexField() {

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Maria Eduarda");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Araujo");

        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();

        String msg = alert.getText();

        Assert.assertEquals("Sexo eh obrigatorio", msg);

    }

    @Test
    public void shouldValidateFavoriteFoodRule() {

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Maria Eduarda");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Araujo");
        driver.findElement(By.id("elementosForm:sexo:1")).click();

        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();


        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();

        String msg = alert.getText();

        Assert.assertEquals("Tem certeza que voce eh vegetariano?", msg);

    }

    @Test
    public void shouldValidateSportsRule() {

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Maria Eduarda");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Araujo");
        driver.findElement(By.id("elementosForm:sexo:1")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();

        WebElement element = driver.findElement(By.id("elementosForm:esportes"));

        Select combo = new Select(element);

        combo.selectByVisibleText("Corrida");
        combo.selectByVisibleText("O que eh esporte?");

        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();

        String msg = alert.getText();

        Assert.assertEquals("Voce faz esporte ou nao?", msg);

    }


}
