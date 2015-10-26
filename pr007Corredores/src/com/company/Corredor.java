package com.company; //Created by Corpex, by the Grace of God, on 12/10/2015.

import java.util.Random;

public class Corredor implements Runnable {
    int aleat;

    @Override
    public void run() {
        Random rnd = new Random();
        System.out.println(Thread.currentThread().getName()+" empieza a correr");
        aleat = rnd.nextInt(10000) +5000;
        //Lo pongo a dormir segun el numero de segundos que le haya tocado
        try {
            Thread.sleep(aleat);
            System.out.println(Thread.currentThread().getName()+" Tiempo "+aleat);
            if(!Thread.currentThread().isInterrupted()) { //Si no ha sido interrupido (es el primero en ganar) muestra el mensaje
                System.out.println(Thread.currentThread().getName() + " gana.");
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+" Interrumpido");
            return;
        }


    }
}
