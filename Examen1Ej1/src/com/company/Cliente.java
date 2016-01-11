package com.company; //Created by corpex, by the Grace of God, on 04/12/2015.


public class Cliente implements Runnable {
    CajaAhorros caja;


    public Cliente(CajaAhorros caja) {
        this.caja = caja;
    }

    @Override
    public void run() {

        //Los clientes quieren realizar una gestion
        caja.llegarCaja();
    }
}
