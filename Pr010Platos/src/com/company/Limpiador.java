package com.company; //Created by Corpex, by the Grace of God, on 19/10/2015.

public class Limpiador implements Runnable {

    PilaPlatos platosLimpios;

    public Limpiador(PilaPlatos platosLimpios){
        this.platosLimpios = platosLimpios;
    }
    @Override
    public void run() {
        while (true) {
            try {
                platosLimpios.meterPlatos(3);
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
