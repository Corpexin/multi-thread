package com.company; //Created by Corpex, by the Grace of God, on 22/11/2015.

import java.util.Random;
import java.util.concurrent.Callable;

public class Persona implements Callable<String>{
    String nombre;

    public Persona(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String call() throws Exception {
        cogerRotulador();
        return nombre;
    }

    private void cogerRotulador() throws InterruptedException {
        Random rnd = new Random();
        Thread.sleep(rnd.nextInt(5000));
    }
}
