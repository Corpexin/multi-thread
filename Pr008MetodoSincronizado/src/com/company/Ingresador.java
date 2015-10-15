package com.company; //Created by Corpex, by the Grace of God, on 14/10/2015.

public class Ingresador implements Runnable {

    Cuenta cuenta;

    public Ingresador(Cuenta cuenta){
        this.cuenta = cuenta;
    }

    @Override
    public void run() {
        cuenta.ingresarDinero(20);
        cuenta.ingresarDinero(15);
        cuenta.ingresarDinero(5);
    }
}
