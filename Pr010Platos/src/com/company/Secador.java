package com.company; //Created by Corpex, by the Grace of God, on 20/10/2015.

public class Secador implements Runnable {
    PilaPlatos platosLimpios;
    PilaPlatos platosSecos;

    public Secador(PilaPlatos platosLimpios, PilaPlatos platosSecos){
        this.platosLimpios = platosLimpios;
        this.platosSecos = platosSecos;
    }
    @Override
    public void run() {
        while (true) {
            try {
                platosLimpios.sacarPlatos(3);
                platosSecos.meterPlatos(4);
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
