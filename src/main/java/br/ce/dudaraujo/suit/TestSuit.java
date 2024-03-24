package br.ce.dudaraujo.suit;

import br.ce.dudaraujo.core.DriverFactory;
import br.ce.dudaraujo.test.BusinessRuleTrainingCamp;
import br.ce.dudaraujo.test.FullRegisterTrainingCamp;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BusinessRuleTrainingCamp.class,
        FullRegisterTrainingCamp.class
})
public class TestSuit {

    @AfterClass
    public static void finalizar() {
        DriverFactory.killDriver();
    }
}
