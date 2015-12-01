package com.company; //Created by corpex, by the Grace of God, on 01/12/2015.

public class Impresor implements Runnable {
    Impresora imp;

    public Impresor(Impresora imp) {
        this.imp = imp;
    }

    @Override
    public void run() {
        imp.imprimir();
    }
}
