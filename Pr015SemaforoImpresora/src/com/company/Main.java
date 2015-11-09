package com.company;

public class Main {

    public static void main(String[] args) {
        //Creo la impresora
        Impresora imp = new Impresora();
        //Creo los trabajos
        Thread[] trab = new Thread[5];
        //Asigno los trabajos
        for(int i = 0; i<trab.length ; i++){
            trab[i] = new Thread(new Trabajo(imp), "Trabajo"+i);
        }
        //Inicio los trabajos
        for(Thread t : trab){
            t.start();
        }
    }
}
