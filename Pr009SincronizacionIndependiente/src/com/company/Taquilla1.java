package com.company; //Created by Corpex, by the Grace of God, on 19/10/2015.

public class Taquilla1 implements Runnable {

    Cine cine;

    public Taquilla1(Cine cine){
        this.cine = cine;
    }

    @Override
    public void run() {
        cine.vender(1,3);
        cine.vender(0,2);
        cine.devolver(0,1);
        cine.vender(1,1);
        cine.devolver(1,1);
    }
}
