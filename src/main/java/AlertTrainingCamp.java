import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AlertTrainingCamp {

    @Test
    public void shoulClickOnSimpleAlert() {

        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        driver.get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");

        driver.findElement(By.id("alert")).click();
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();

        Assert.assertEquals("Alert Simples", text);

        alert.accept();
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Alert Simples");

        driver.quit();

    }

    @Test
    public void shouldClickInOkayOnConfirmeAlert() {

        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        driver.get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");

        driver.findElement(By.id("confirm")).click();
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();

        Assert.assertEquals("Confirm Simples", text);

        alert.accept();

        Assert.assertEquals("Confirmado", alert.getText());

        alert.accept();

        driver.quit();
    }

    @Test
    public void shouldClickInCancelOnConfirmeAlert() {

        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        driver.get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");

        driver.findElement(By.id("confirm")).click();
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();

        Assert.assertEquals("Confirm Simples", text);

        alert.dismiss();

        Assert.assertEquals("Negado", alert.getText());

        alert.accept();

        driver.quit();

    }

    @Test
    public void shouldClickAndWriteOnPromptAlert() {

        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        driver.get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");

        driver.findElement(By.id("prompt")).click();
        Alert alert = driver.switchTo().alert();

        Assert.assertEquals("Digite um numero", alert.getText());

        alert.sendKeys("100");

        alert.accept();

        Assert.assertEquals("Era 100?", alert.getText());

        alert.accept();

        Assert.assertEquals(":D", alert.getText());
        alert.accept();


    }


}
