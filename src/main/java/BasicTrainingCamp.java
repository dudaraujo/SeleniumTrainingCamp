import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class BasicTrainingCamp {

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
    public void shouldWriteInNameField() {

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Maria Eduarda");
        Assert.assertEquals("Maria Eduarda", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));

    }
    @Test
    public void shouldWriteInTextArea() {

        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Esse é um campo de área grande");

        Assert.assertEquals("Esse é um campo de área grande", dsl.getFieldValue("elementosForm:sugestoes"));

    }

    @Test
    public void shouldCheckRadioButton() {

        driver.findElement(By.id("elementosForm:sexo:1")).click();

        Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:1")).isSelected());

    }

    @Test
    public void shouldClickOnCheckButton() {

        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();

        Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());

    }

    @Test
    public  void shouldSelectComboField() {

        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));

        Select combo = new Select(element);

        combo.selectByVisibleText("Superior");

        Assert.assertEquals("Superior", combo.getFirstSelectedOption().getText());

    }

    @Test
    public void shouldHasAllTheOptionsInComboField() {

        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));

        Select combo = new Select(element);

        List<WebElement> options = combo.getOptions();

        Assert.assertEquals(8, options.size());


        boolean finded = false;

        for(WebElement option: options){
            if(option.getText().equals("Mestrado")) {
                finded = true;
                break;
            }
        }

        Assert.assertTrue(finded);

    }

    @Test
    public void shoulSelectSeveralOptions() {

        WebElement element = driver.findElement(By.id("elementosForm:esportes"));

        Select combo = new Select(element);

        combo.selectByVisibleText("Natacao");
        combo.selectByVisibleText("Corrida");
        combo.selectByVisibleText("Karate");

        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();

        Assert.assertEquals(3, allSelectedOptions.size());

        combo.deselectByVisibleText("Karate");

        allSelectedOptions = combo.getAllSelectedOptions();

        Assert.assertEquals(2, allSelectedOptions.size());

    }

    @Test
    public void shouldChangeButtonValue() {

        WebElement button = driver.findElement(By.id("buttonSimple"));
        button.click();

        Assert.assertEquals("Obrigado!", button.getAttribute("value"));

    }

    @Test
    public void shouldClickOnLink() {

        driver.findElement(By.linkText("Voltar")).click();

        Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());

    }

    @Test
    public void shouldFindElementOnScreen() {

        Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText());

        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", driver.findElement(By.className("facilAchar")).getText());

    }

}
