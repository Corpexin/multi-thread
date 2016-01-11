package com.company; //Created by corpex, by the Grace of God, on 30/11/2015.

import java.util.Random;

public class Cargador implements Runnable{
    CuentaBancaria cuenta;
    public Cargador(CuentaBancaria cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public void run() {
        //Hacemos cargos a la cuenta (sacamos)
        Random rnd = new Random();
        int aleat = rnd.nextInt(5)+5;
        for(int i=0 ; i<aleat ; i++){
            if(cuenta.saldo>=1000)
                cuenta.sacarDinero(rnd.nextInt(1000));
            //simulamos tizempos de espera entre cada transaccion
            try {
                Thread.sleep(rnd.nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
