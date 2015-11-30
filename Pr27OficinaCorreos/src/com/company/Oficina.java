package com.company; //Created by corpex, by the Grace of God, on 30/11/2015.

import java.util.Random;
import java.util.concurrent.Callable;

public class Oficina implements Callable<String> {
    String codigoCarta;

    public Oficina(String codigoCarta) {
        this.codigoCarta = codigoCarta;
    }

    @Override
    public String call() throws Exception {
        Random rnd = new Random();
        System.out.println(codigoCarta+" gestionada por oficina y enviada al cartero");
        //Simulamos la gestion de la carta
        Thread.sleep(rnd.nextInt(5000));

        return codigoCarta;
    }
}
