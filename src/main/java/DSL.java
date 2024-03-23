import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class DSL {

    private WebDriver driver;


    public DSL(WebDriver driver) {
        this.driver = driver;
    }

    public void write(String idField, String text) {

        driver.findElement(By.id(idField)).clear();
        driver.findElement(By.id(idField)).sendKeys(text);
    }

    public void writeByTagName(String tagField, String text) {

        driver.findElement(By.tagName(tagField)).clear();
        driver.findElement(By.tagName(tagField)).sendKeys(text);
    }

    public void click(String idField) {

        driver.findElement(By.id(idField)).click();
    }

    public void clickXpath(String idField) {

        driver.findElement(By.xpath(idField)).click();
    }

    public void clickOnLink(String linkFiled) {
        driver.findElement(By.linkText(linkFiled)).click();
    }

    public String getFieldValue(String idField) {
        return driver.findElement(By.id(idField)).getAttribute("value");
    }

    public String getFieldText(By by) {
        return driver.findElement(by).getText();
    }


    public boolean getFieldSelected(String idField) {
        return driver.findElement(By.id(idField)).isSelected();
    }

    public void getField(String idField) {
        driver.findElement(By.id(idField));
    }

    public void enterFrame(String frameName) {
        driver.switchTo().frame(frameName);
    }

    public void exitFrame() {
        driver.switchTo().defaultContent();
    }

    public void switchWindow(String id) {
        driver.switchTo().window(id);
    }



    public void selectCombo(String idField, String option) {

        WebElement element = driver.findElement(By.id(idField));
        Select combo = new Select(element);
        combo.selectByVisibleText(option);
    }

    public void deselectCombo(String idField, String sport) {
        WebElement element = driver.findElement(By.id(idField));
        Select combo = new Select(element);
        combo.deselectByVisibleText(sport);

    }

    public String  getFirstSelectedComboOption(String idField) {
        WebElement element = driver.findElement(By.id(idField));
        Select combo = new Select(element);
        return combo.getFirstSelectedOption().getText();
    }

    public void buttonClick(String idField) {
        WebElement button = driver.findElement(By.id(idField));
        button.click();
    }

    public int getNumberOptionsComboField(String idField) {
        WebElement element = driver.findElement(By.id(idField));

        Select combo = new Select(element);

        List<WebElement> options = combo.getOptions();

        return options.size();

    }

    public boolean getOptionComboField(String idField, String curso) {
        WebElement element = driver.findElement(By.id(idField));

        Select combo = new Select(element);

        List<WebElement> options = combo.getOptions();

        boolean finded = false;

        for(WebElement option: options){
            if(option.getText().equals(curso)) {
                finded = true;
                break;
            }
        }

        return finded;
    }

    public List getComboValues(String idField) {

        WebElement element = driver.findElement(By.id(idField));

        Select combo = new Select(element);

        List<WebElement> options = combo.getOptions();

        return combo.getAllSelectedOptions();

    }

    public String readAndAcceptAlert() {
        Alert alert = driver.switchTo().alert();

        String msg = alert.getText();

        alert.accept();

        return msg;
    }

    public String readAndRecuseAlert() {
        Alert alert = driver.switchTo().alert();

        String msg = alert.getText();

        alert.dismiss();

        return msg;
    }

    public String readAlert() {
        Alert alert = driver.switchTo().alert();

        String msg = alert.getText();

        return msg;

    }

    public void writeAlert(String text) {
        Alert alert = driver.switchTo().alert();

        alert.sendKeys(text);
        alert.accept();

    }

    public Object executarJs(String cmd, Object ... param) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript(cmd, param);

    }

    public void clickOnTableButton(String searchColumn, String value, String buttonColumn, String idTable) {
        //encontrar coluna do registro
        WebElement table = driver.findElement(By.xpath("//table[@id='elementosForm:tableUsuarios']"));
        int idColumn = getColumnIndice(searchColumn, table);

        //encontrar linha do registro
        int idLine = getLineIndice(value, table, idColumn);

        //procurar coluna do botão
        int idButtonColumn = getColumnIndice(buttonColumn, table);

        //clicar no botão da célula encontrada
        WebElement celula = table.findElement(By.xpath(".//tr["+idLine+"]/td["+idButtonColumn+"]"));
        celula.findElement(By.xpath(".//input")).click();
    }

    public int getColumnIndice( String searchColumn, WebElement table) {
        List<WebElement> columns = table.findElements(By.xpath(".//th"));
        int idColumn = -1;
        for (int i = 0; i < columns.size(); i++) {
            if (columns.get(i).getText().equals(searchColumn)) {
                idColumn = i + 1;
                break;
            }
        }
        return idColumn;

    }

    public int getLineIndice(String value, WebElement table, int idColumn) {
        List<WebElement> lines = table.findElements(By.xpath("./tbody/tr/td["+idColumn+"]"));
        int idLine = -1;
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).getText().equals(value)) {
                idLine = i + 1;
                break;
            }
        }
        return idLine;

    }

}
