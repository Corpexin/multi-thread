package com.company; //Created by Corpex, by the Grace of God, on 14/10/2015.

public class Gastador implements Runnable{

    Cuenta cuenta;

    public Gastador(Cuenta cuenta){
        this.cuenta = cuenta;
    }

    @Override
    public void run() {
        for(int i=0 ; i<3 ; i++){
            cuenta.gastarDinero(3);

        }

    }
}
