package br.ce.dudaraujo.test;

import br.ce.dudaraujo.core.DSL;
import br.ce.dudaraujo.page.TrainingCampPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static br.ce.dudaraujo.core.DriverFactory.getDriver;
import static br.ce.dudaraujo.core.DriverFactory.killDriver;

@RunWith(Parameterized.class)
public class Parametros {


        private DSL dsl;
        private TrainingCampPage page;

        @Parameterized.Parameter
        public String nome;
        @Parameterized.Parameter(value=1)
        public String sobrenome;
        @Parameterized.Parameter(value=2)
        public String sexo;
        @Parameterized.Parameter(value=3)
        public List<String> comidas;
        @Parameterized.Parameter(value=4)
        public String[] esportes;
        @Parameterized.Parameter(value=5)
        public String msg;


        @Before
        public void inicializa(){
            getDriver().get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");
            dsl = new DSL();
            page = new TrainingCampPage();
        }

        @After
        public void finaliza(){
           killDriver();
        }

        @Parameterized.Parameters
        public static Collection<Object[]> getCollection(){
            return Arrays.asList(new Object[][] {
                    {"", "", "", Arrays.asList(), new String[]{}, "Nome eh obrigatorio"},
                    {"Maria Eduarda", "", "", Arrays.asList(), new String[]{}, "Sobrenome eh obrigatorio"},
                    {"Maria Eduarda", "Araujo", "", Arrays.asList(), new String[]{}, "Sexo eh obrigatorio"},
                    {"Maria Eduarda", "Araujo", "Feminino", Arrays.asList("Carne", "Vegetariano"), new String[]{}, "Tem certeza que voce eh vegetariano?"},
                    {"Maria Eduarda", "Araujo", "Feminino", Arrays.asList("Carne"), new String[]{"Karate", "O que eh esporte?"}, "Voce faz esporte ou nao?"}
            });
        }

        @Test
        public void deveValidarRegras(){
            page.setName(nome);
            page.setFullName(sobrenome);
            if(sexo.equals("Masculino")) {
                page.setMale();
            }
            if(sexo.equals("Feminino")) {
                page.setFemale();
            }
            if(comidas.contains("Carne")) page.setFavoriteFoodCarne();
            if(comidas.contains("Pizza")) page.setFavoriteFoodPizza();
            if(comidas.contains("Vegetariano")) page.setFavoriteFoodVegetariano();
            page.setSport(esportes);
            page.setSubmitButton();
            System.out.println(msg);
            Assert.assertEquals(msg, dsl.readAndAcceptAlert());
        }
    }


