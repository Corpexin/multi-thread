package com.company;

public class Main {

    public static void main(String[] args) {
        Impresora impresora = new Impresora();
        Thread[] hilos = new Thread[10];

        //creo los hilos
        for(int i = 0 ; i<hilos.length ; i++){
            hilos[i] = new Thread(new Tarea(impresora), "Tarea"+i);
        }

        //lanzo los hilos
        for (Thread hilo : hilos) {
            hilo.start();
        }
    }
}
