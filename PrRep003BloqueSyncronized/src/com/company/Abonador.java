package com.company; //Created by corpex, by the Grace of God, on 30/11/2015.

import java.util.Random;

public class Abonador implements Runnable{
    CuentaBancaria cuenta;

    public Abonador(CuentaBancaria cuenta){
        this.cuenta = cuenta;
    }

    @Override
    public void run() {
        //hacemos abonos a la cuenta (ingresamos) un numero aleatorio de veces
        Random rnd = new Random();
        int aleat = rnd.nextInt(5)+5;
        for(int i=0 ; i<aleat ; i++){
            cuenta.ingresarDinero(rnd.nextInt(1000));
            //simulamos tiempos de espera entre cada transaccion
            try {
                Thread.sleep(rnd.nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
