package com.company; //Created by Corpex, by the Grace of God, on 26/10/2015.

public class Tarea implements Runnable{

    Impresora impresora;

    public Tarea(Impresora impresora){
        this.impresora = impresora;
    }

    @Override
    public void run() {
        impresora.imprimir(new Object());
    }
}
