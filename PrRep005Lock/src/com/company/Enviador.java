package com.company; //Created by corpex, by the Grace of God, on 01/12/2015.

public class Enviador implements Runnable {
    Impresora imp;

    public Enviador(Impresora imp) {
        this.imp = imp;
    }

    @Override
    public void run() {
        System.out.println("Envio de documento");
        Object documento = new Object();
        imp.imprimirDocumento(documento);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
