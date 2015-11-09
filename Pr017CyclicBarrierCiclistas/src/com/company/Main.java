package com.company;

import java.util.concurrent.CyclicBarrier;

public class Main {

    public static void main(String[] args) {
        Thread[] ciclistas = new Thread[25];
        CyclicBarrier barrera = new CyclicBarrier(ciclistas.length);

        ////Creamos 25 hilos ciclistas
        for(int i=0 ; i<ciclistas.length ; i++){
            ciclistas[i] = new Thread(new Ciclista(barrera), "Ciclista"+i);
        }

        //Iniciamos los hilos
        for(Thread t : ciclistas){
            t.start();
        }
        //Cuando todos llegan a la meta el hilo principal dice que todos han terminado (un join para que espere al resto de hilos)//Creamos 25 hilos ciclistas
        for(int i = 0; i<ciclistas.length ; i++){
            try {
                ciclistas[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Todos los ciclistas han llegado a la meta");
    }
}
