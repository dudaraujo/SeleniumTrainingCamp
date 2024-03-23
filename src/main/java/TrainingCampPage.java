import br.ce.dudaraujo.core.DSL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TrainingCampPage {

    private DSL dsl;
    public TrainingCampPage() {
        dsl = new DSL();
    }


    public String getName() {

        return dsl.getFieldValue("elementosForm:nome");
    }
    public void setName(String name) {

        dsl.write("elementosForm:nome", name);
    }

    public void setFullName(String fullnName) {

        dsl.write("elementosForm:sobrenome", fullnName);
    }

    public boolean getFemale() {
        return dsl.getFieldSelected("elementosForm:sexo:1");
    }
    public void setFemale() {
        dsl.click("elementosForm:sexo:1");
    }

    public void  setMale() {
        dsl.click("elementosForm:sexo:0");
    }

    public boolean getFavoriteFoodPizza() {
        return dsl.getFieldSelected("elementosForm:comidaFavorita:2");
    }
    public void setFavoriteFoodPizza() {
        dsl.click("elementosForm:comidaFavorita:2");
    }
    public void setFavoriteFoodCarne() {
        dsl.click("elementosForm:comidaFavorita:0");
    }

    public void setFavoriteFoodVegetariano() {
        dsl.click("elementosForm:comidaFavorita:3");
    }

    public String getGraduation() {
        return dsl.getFirstSelectedComboOption("elementosForm:escolaridade");
    }
    public void setGraduation(String graduation) {
        dsl.selectCombo("elementosForm:escolaridade", graduation);
    }

    public void setSport(String... sports) {
        for(String sport: sports) {
            dsl.selectCombo("elementosForm:esportes", sport);
        }
    }


    public String getSuggestion() {
        return dsl.getFieldValue("elementosForm:sugestoes");
    }
    public void setSuggestion(String suggestion) {
        dsl.write("elementosForm:sugestoes", suggestion);
    }

    public void setSubmitButton() {
        dsl.click("elementosForm:cadastrar");
    }

    public void setSimpleAlertButton() {
        dsl.click("alert");
    }

    public void setConfirmAlertButton() {
        dsl.click("confirm");
    }

    public void setPromptAlertButton() {
        dsl.click("prompt");
    }

    public String getResult() {
        return dsl.getFieldText(By.id("resultado"));
    }

    public String getResultName() {
       return dsl.getFieldText(By.id("descNome"));
    }

    public String getResultFullName() {
        return dsl.getFieldText(By.id("descSobrenome"));
    }

    public String getResultSex() {
        return dsl.getFieldText(By.id("descSexo"));
    }

    public String getResultFood() {
        return dsl.getFieldText(By.id("descComida"));
    }

    public String getResultGraduation() {
        return dsl.getFieldText(By.id("descEscolaridade"));
    }

    public String getResultSport() {
        return dsl.getFieldText(By.id("descEsportes"));
    }

    public String getResultSuggestion() {
        return dsl.getFieldText(By.id("descSugestoes"));
    }




}
