package com.company; //Created by Corpex, by the Grace of God, on 12/10/2015.

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Corredor implements Runnable {
    int aleat;

    @Override
    public void run() {
        Random rnd = new Random();
        System.out.println(Thread.currentThread().getName()+" empieza a correr");
        aleat = rnd.nextInt(10) +5;
        //Lo pongo a dormir segun el numero de segundos que le haya tocado
        try {
            TimeUnit.SECONDS.sleep(aleat);
            if(!Thread.currentThread().isInterrupted()) { //Si no ha sido interrupido (es el primero en ganar) muestra el mensaje
                System.out.println(Thread.currentThread().getName() + " gana.");
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+" Interrumpido");
        }


    }
}
