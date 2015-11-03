package com.company; //Created by Corpex, by the Grace of God, on 26/10/2015.

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Tarea implements Runnable{

    Impresora impresora;

    public Tarea(Impresora impresora){
        this.impresora = impresora;
    }

    @Override
    public void run() {
        Random rnd = new Random();

        try {
            TimeUnit.SECONDS.sleep(rnd.nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        impresora.imprimir(new Object());
    }
}
