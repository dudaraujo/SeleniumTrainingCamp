package br.ce.dudaraujo.core;

import org.junit.After;

import static br.ce.dudaraujo.core.DriverFactory.killDriver;

public class BaseTest {

    @After
    public void quit() {
        if(Properties.CLOSE_BROWSER) {
            killDriver();
        }
    }
}
