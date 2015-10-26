package com.company; //Created by Corpex, by the Grace of God, on 26/10/2015.

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Impresora {
    //private final Lock cerrojo = new ReentrantLock(); //sin modo seguro
    private final Lock cerrojo = new ReentrantLock(true); //modo seguro

    public void imprimir(Object o) {
        Random rnd = new Random();
        cerrojo.lock();//bloqueo
        System.out.println(Thread.currentThread().getName()+" Comienza la impresion");
        try {
            TimeUnit.SECONDS.sleep(rnd.nextInt(5));//duermo 5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println(Thread.currentThread().getName()+" Termina la impresion");
            cerrojo.unlock();//desbloqueo
        }

    }
}
