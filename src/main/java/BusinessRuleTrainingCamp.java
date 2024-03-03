import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class BusinessRuleTrainingCamp {

    @Test
    public void shouldValiateRequiredName() {
        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        driver.get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");

        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();

        String msg = alert.getText();

        Assert.assertEquals("Nome eh obrigatorio", msg);

        driver.quit();
    }

    @Test
    public void shouldValidateSurnameRequired() {

        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        driver.get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Maria Eduarda");

        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();

        String msg = alert.getText();

        Assert.assertEquals("Sobrenome eh obrigatorio", msg);

        driver.quit();

    }

    @Test
    public void shouldValidateSexField() {

        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        driver.get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Maria Eduarda");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Araujo");

        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();

        String msg = alert.getText();

        Assert.assertEquals("Sexo eh obrigatorio", msg);

        driver.quit();

    }

    @Test
    public void shouldValidateFavoriteFoodRule() {
        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        driver.get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Maria Eduarda");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Araujo");
        driver.findElement(By.id("elementosForm:sexo:1")).click();

        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();


        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();

        String msg = alert.getText();

        Assert.assertEquals("Tem certeza que voce eh vegetariano?", msg);

        driver.quit();

    }

    @Test
    public void shouldValidateSportsRule() {

        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        driver.get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");

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

        driver.quit();

    }



}
