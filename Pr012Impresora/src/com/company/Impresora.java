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
        if(cerrojo.tryLock()) { //intenta bloquear, si lo consigue, comienza la impresion, sino muestra que no se puede imprimir
            //cerrojo.lock();//bloqueo normal
            System.out.println(Thread.currentThread().getName() + " Comienza la impresion");
            try {
                TimeUnit.SECONDS.sleep(rnd.nextInt(3));//duermo 3 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + " Termina la impresion");
                cerrojo.unlock();//desbloqueo
            }
        }
        else{
            System.out.println(Thread.currentThread().getName()+" No puede imprimir");
        }
    }
}
