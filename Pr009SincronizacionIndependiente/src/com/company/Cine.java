package com.company; //Created by Corpex, by the Grace of God, on 19/10/2015.

public class Cine {
    int numButacas[];
    Object mutexSala[];

    public Cine(int numButacas[]){
        //le paso las butacas y creo los mutex con respecto al numero de butacas
        this.numButacas = numButacas;
        mutexSala = new Object[numButacas.length];

        for(int i =0 ; i<mutexSala.length ; i++){
            mutexSala[i] = new Object();
        }
    }

    public boolean vender(int numSala, int cantEntradas){
        if (numSala < 0 || numSala >= numButacas.length) {
            return false;
        }
        synchronized (mutexSala[numSala]) {
            if (cantEntradas <= numButacas[numSala]) {
                numButacas[numSala] -= cantEntradas;
                System.out.printf("Sala %d: %d entradas vendidas\n", numSala,
                        cantEntradas);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return true;
            } else {
                return false;
            }
        }
    }


    public boolean devolver(int numSala, int cantEntradas){
        if (numSala < 0 || numSala >= numButacas.length) {
            return false;
        }
        synchronized (mutexSala[numSala]) {
            numButacas[numSala] += cantEntradas;
            System.out.printf("Sala %d: %d entradas devueltas\n", numSala, cantEntradas);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return true;
        }
    }

    public int getLibres(int i) {
        return  numButacas[i];
    }
}
