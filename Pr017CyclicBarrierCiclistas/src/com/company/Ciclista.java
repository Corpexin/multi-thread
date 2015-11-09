package com.company; //Created by Corpex, by the Grace of God, on 09/11/2015.

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Ciclista implements Runnable{
    CyclicBarrier barrera;

    public Ciclista(CyclicBarrier barrera){
        this.barrera = barrera;
    }

    @Override
    public void run() {
        Random rnd = new Random();

        //Comienza a correr hasta la salida
        try {
            Thread.sleep(rnd.nextInt(5000)+5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " - ha llegado a la linea de salida.");
        //LLega a la salida y se espera
        try {
            barrera.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+ " - comienza la carrera.");

        //Sigue Corriendo
        try {
            Thread.sleep(rnd.nextInt(5000) + 5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " - llega a la meta");

        //JOIN?
    }
}
