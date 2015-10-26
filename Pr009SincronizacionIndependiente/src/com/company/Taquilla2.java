package com.company; //Created by Corpex, by the Grace of God, on 19/10/2015.

public class Taquilla2 implements Runnable {
    Cine cine;

    public Taquilla2(Cine cine){
        this.cine = cine;
    }
    @Override
    public void run() {
        cine.vender(1,5);
        cine.vender(0,3);
        cine.devolver(0,2);
        cine.vender(1,3);
        cine.devolver(1,3);
    }
}
