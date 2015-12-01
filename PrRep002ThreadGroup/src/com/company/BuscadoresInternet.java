package com.company; //Created by corpex, by the Grace of God, on 30/11/2015.

import java.util.Random;

public class BuscadoresInternet implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" comienza la busqueda");
        Random rnd = new Random();
        //Simulamos la busqueda
        try {
            Thread.sleep(rnd.nextInt(5000)+5000);
            System.out.println("Busqueda terminada - "+Thread.currentThread().getName());
        } catch (InterruptedException e) {}

        //para que un hilo termine su ejecucion de forma chunga se puede usar return; asecas
    }
}
