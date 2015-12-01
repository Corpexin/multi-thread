package com.company; //Created by corpex, by the Grace of God, on 01/12/2015.

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Impresora {
    Lock cerrojo = new ReentrantLock(true); //TRUE valor fair mode activado

    public void imprimirDocumento(Object documento){
        //Si quitase los locks, los hilos no se esperarian a que la impresion terminara, sino que entrarian
        //en medio del proceso para imprimir un nuevo documento. Con el cerrojo se espera a que otro hilo
        //suelte el lock.
        cerrojo.lock();
        System.out.println("Imprimiendo documento.");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cerrojo.unlock();
    }

}
