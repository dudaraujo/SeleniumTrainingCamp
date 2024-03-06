import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AlertTrainingCamp {

    private WebDriver driver;

    @Before
    public void initialize() {
        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");
    }
    @After
    public void quit() {
        driver.quit();
    }

    @Test
    public void shoulClickOnSimpleAlert() {

        driver.findElement(By.id("alert")).click();
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();

        Assert.assertEquals("Alert Simples", text);

        alert.accept();
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Alert Simples");

    }

    @Test
    public void shouldClickInOkayOnConfirmeAlert() {

        driver.findElement(By.id("confirm")).click();
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();

        Assert.assertEquals("Confirm Simples", text);

        alert.accept();

        Assert.assertEquals("Confirmado", alert.getText());

        alert.accept();

    }

    @Test
    public void shouldClickInCancelOnConfirmeAlert() {

        driver.findElement(By.id("confirm")).click();
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();

        Assert.assertEquals("Confirm Simples", text);

        alert.dismiss();

        Assert.assertEquals("Negado", alert.getText());

        alert.accept();

    }

    @Test
    public void shouldClickAndWriteOnPromptAlert() {

        driver.findElement(By.id("prompt")).click();
        Alert alert = driver.switchTo().alert();

        Assert.assertEquals("Digite um numero", alert.getText());

        alert.sendKeys("100");

        alert.accept();

        Assert.assertEquals("Era 100?", alert.getText());

        alert.accept();

        Assert.assertEquals(":D", alert.getText());

    }


}
