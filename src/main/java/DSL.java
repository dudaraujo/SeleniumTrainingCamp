import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DSL {

    private WebDriver driver;


    public DSL(WebDriver driver) {
        this.driver = driver;
    }

    public void write(String idField, String text) {

        driver.findElement(By.id(idField)).sendKeys(text);
    }

    public void click(String idField) {

        driver.findElement(By.id(idField)).click();
    }

    public String getFieldValue(String idField) {
        return driver.findElement(By.id(idField)).getAttribute("value");
    }
}
