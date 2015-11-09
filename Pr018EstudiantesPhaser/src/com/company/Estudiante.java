package com.company; //Created by Corpex, by the Grace of God, on 09/11/2015.

import java.util.Random;

public class Estudiante implements Runnable {
    Fase fase;

    public Estudiante(Fase fase) {
        this.fase = fase;
    }

    @Override
    public void run() {
        Random rnd = new Random();
        //Se espera a que todos se sienten para empezar a la vez
        fase.arriveAndAwaitAdvance();

        for(int i=0 ; i<5 ; i++){
            try {
                Thread.sleep(rnd.nextInt(5000)+5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            fase.arriveAndAwaitAdvance();
        }
        fase.arriveAndDeregister();
    }
}
