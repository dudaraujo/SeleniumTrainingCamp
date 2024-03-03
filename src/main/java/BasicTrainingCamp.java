import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class BasicTrainingCamp {

    @Test
    public void shouldWriteInNameField() {

        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        driver.get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Maria Eduarda");
        Assert.assertEquals("Maria Eduarda", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));

        driver.quit();
    }
    @Test
    public void shouldWriteInTextArea() {

        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        driver.get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");

        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Esse é um campo de área grande");

        Assert.assertEquals("Esse é um campo de área grande", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));

        driver.quit();
    }

    @Test
    public void shouldCheckRadioButton() {

        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        driver.get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");

        driver.findElement(By.id("elementosForm:sexo:1")).click();

        Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:1")).isSelected());

        driver.quit();

    }

    @Test
    public void shouldClickOnCheckButton() {

        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        driver.get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");

        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();

        Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());

        driver.quit();
    }

    @Test
    public  void shouldSelectComboField() {

        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        driver.get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");

        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));

        Select combo = new Select(element);

        combo.selectByVisibleText("Superior");

        Assert.assertEquals("Superior", combo.getFirstSelectedOption().getText());

        driver.quit();

    }

    @Test
    public void shouldHasAllTheOptionsInComboField() {

        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        driver.get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");

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

        driver.quit();

    }

    @Test
    public void shoulSelectSeveralOptions() {

        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        driver.get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");

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

        driver.quit();

    }

    @Test
    public void shouldChangeButtonValue() {

        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        driver.get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");

        WebElement button = driver.findElement(By.id("buttonSimple"));
        button.click();

        Assert.assertEquals("Obrigado!", button.getAttribute("value"));

        driver.quit();
    }

    @Test
    public void shouldClickOnLink() {

        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        driver.get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");

        driver.findElement(By.linkText("Voltar")).click();

        Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());

        driver.quit();
    }

    @Test
    public void shouldFindElementOnScreen() {

        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        driver.get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");

        Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText());

        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", driver.findElement(By.className("facilAchar")).getText());

        driver.quit();
    }

}
