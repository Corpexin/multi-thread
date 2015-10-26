package com.company;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        ThreadGroup grupoCorredores  = new ThreadGroup("Buscadores");
        Thread[] array = new Thread[10];

        //Cargo todos los hilos en un arrray
        for(int i=0 ; i<array.length ; i++) {
            Corredor corredor = new Corredor();
            Thread hiloCorredor = new Thread(grupoCorredores, corredor, "Corredor "+i);
            array[i] = hiloCorredor;
        }

        //Inicio todos los hilos a la vez
        for (Thread anArray : array) {
            anArray.start();
        }

        //Espero a que uno de los hilos finalice
        while (grupoCorredores.activeCount() > 9) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        grupoCorredores.interrupt(); //interrumpo al resto


    }
}
