package com.company; //Created by Corpex, by the Grace of God, on 16/11/2015.

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Cliente implements Runnable {
    String nombre;

    public Cliente(String nombre){
        this.nombre = nombre;
    }

    @Override
    public void run() {
        Random rnd = new Random();
        try {
            int duracion = rnd.nextInt(5);
            TimeUnit.SECONDS.sleep(duracion);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(nombre+" sentado y listo.");
    }
}
