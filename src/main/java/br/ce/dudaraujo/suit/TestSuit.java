package br.ce.dudaraujo.suit;

import br.ce.dudaraujo.test.BusinessRuleTrainingCamp;
import br.ce.dudaraujo.test.FrameAndWindowsTrainingCamp;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BusinessRuleTrainingCamp.class,
        FrameAndWindowsTrainingCamp.class
})
public class TestSuit {
}
