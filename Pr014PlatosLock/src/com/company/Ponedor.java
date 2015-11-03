package com.company; //Created by Corpex, by the Grace of God, on 20/10/2015.

public class Ponedor implements Runnable {
    PilaPlatos platosSecos;

    public Ponedor(PilaPlatos platosSecos){
        this.platosSecos = platosSecos;
    }
    @Override
    public void run() {
        while (true) {
            try {
                platosSecos.sacarPlatos(3);
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
