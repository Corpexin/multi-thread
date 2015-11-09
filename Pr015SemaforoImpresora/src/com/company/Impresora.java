package com.company; //Created by Corpex, by the Grace of God, on 04/11/2015.

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Impresora {
    Semaphore semaforo = new Semaphore(1); //semaforo con contador de hilos que deja acceso a la vez

    public void imprimir(){
        Random rnd = new Random();
        try {
            //Adquiero semaforo
            semaforo.acquire();
            System.out.println(Thread.currentThread().getName() + " - Impresion Iniciada");
            //Simulo impresion
            Thread.sleep(rnd.nextInt(5000)+2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //Informo que finalizo impresion
            System.out.println(Thread.currentThread().getName() + " - Impresion Finalizada");
            //Libero Semaforo
            semaforo.release();
        }
    }
}
