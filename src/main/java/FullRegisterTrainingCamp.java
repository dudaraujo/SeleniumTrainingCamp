
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static br.ce.dudaraujo.core.DriverFactory.getDriver;
import static br.ce.dudaraujo.core.DriverFactory.killDriver;

public class FullRegisterTrainingCamp {

    private TrainingCampPage page;
    @Before
    public void initialize() {

        getDriver().get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");
        page = new TrainingCampPage();
    }
    @After
    public void quit() {
       killDriver();
    }

    @Test
    public void shouldCompleteRegister() {


        page.setName("Maria Eduarda");
        page.setFullName("Araújo");
        page.setFemale();
        page.setFavoriteFoodPizza();
        page.setGraduation("Superior");
        page.setSport("Natacao","Corrida", "Karate");
        page.setSuggestion("Essa é uma sugestão");

        page.setSubmitButton();

        Assert.assertTrue(page.getResult().startsWith("Cadastrado"));
        Assert.assertTrue(page.getResultName().endsWith("Maria Eduarda"));
        Assert.assertEquals("Sobrenome: Araújo", page.getResultFullName());
        Assert.assertEquals("Sexo: Feminino", page.getResultSex());
        Assert.assertEquals("Comida: Pizza", page.getResultFood());
        Assert.assertEquals("Escolaridade: superior", page.getResultGraduation());
        Assert.assertEquals("Esportes: Natacao Corrida Karate", page.getResultSport());
        Assert.assertEquals("Sugestoes: Essa é uma sugestão", page.getResultSuggestion());


    }
}
