package com.company; //Created by Corpex, by the Grace of God, on 04/11/2015.

public class Trabajo implements Runnable {

    private Impresora imp;

    public Trabajo(Impresora imp){
        this.imp = imp;
    }
    @Override
    public void run() {
        imp.imprimir();
    }
}
