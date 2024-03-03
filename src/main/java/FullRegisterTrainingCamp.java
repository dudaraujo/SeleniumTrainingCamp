import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.sql.Driver;
import java.util.List;

public class FullRegisterTrainingCamp {

    @Test
    public void shouldCompleteRegister() {

        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        driver.get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Maria Eduarda");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Araújo");
        driver.findElement(By.id("elementosForm:sexo:1")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();

        WebElement element =  driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
        combo.selectByVisibleText("Superior");

        WebElement element2 = driver.findElement(By.id("elementosForm:esportes"));
        Select combo2 = new Select(element2);
        combo2.selectByVisibleText("Natacao");
        combo2.selectByVisibleText("Corrida");
        combo2.selectByVisibleText("Karate");

        List<WebElement> AllSelectedOptions =  combo2.getAllSelectedOptions();

        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Essa é uma sugestão");

        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
        Assert.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("Maria Eduarda"));
        Assert.assertEquals("Sobrenome: Araújo", driver.findElement(By.id("descSobrenome")).getText());
        Assert.assertEquals("Sexo: Feminino", driver.findElement(By.id("descSexo")).getText());
        Assert.assertEquals("Comida: Pizza", driver.findElement(By.id("descComida")).getText());
        Assert.assertEquals("Escolaridade: superior", driver.findElement(By.id("descEscolaridade")).getText());
        Assert.assertEquals("Esportes: Natacao Corrida Karate", driver.findElement(By.id("descEsportes")).getText());
        Assert.assertEquals("Sugestoes: Essa é uma sugestão", driver.findElement(By.id("descSugestoes")).getText());

        driver.quit();

    }
}
