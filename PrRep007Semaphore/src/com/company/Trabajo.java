package com.company; //Created by corpex, by the Grace of God, on 01/12/2015.

// Simula un trabajo que envía un documento a imprimir.
public class Trabajo implements Runnable {

    // Cola de impresión.
    private ColaImpresion colaImpresion;

    // Constructor.
    public Trabajo(ColaImpresion colaImpresion){
        this.colaImpresion = colaImpresion;
    }

    @Override
    public void run() {
        // Mando a imprimir un documento.
        colaImpresion.imprimir(new Object());
    }
}
