package com.company; //Created by Corpex, by the Grace of God, on 14/10/2015.

public class Cuenta {
    float saldo;

    public Cuenta(float saldo){
        this.saldo = saldo;
    }

    public synchronized void ingresarDinero(float cant){
        saldo = saldo + cant;
        consultaSaldo();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private  void consultaSaldo() {
        System.out.println("Saldo = "+saldo);
    }

    public synchronized  void gastarDinero(float cant){
        saldo = saldo - cant;
        consultaSaldo();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
