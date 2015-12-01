package com.company; //Created by corpex, by the Grace of God, on 01/12/2015.

import java.util.Random;

public class Consumidor implements Runnable {
    Almacen alm;

    public Consumidor(Almacen alm){
        this.alm = alm;
    }

    @Override
    public void run() {
        Random rnd = new Random();
        for(int i=0 ; i<rnd.nextInt(5)+5 ; i++){
            try { //Simulamos recoger
                Thread.sleep(rnd.nextInt(5000)+3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("Consumidor - Elemento retirado " + alm.retirarElemento());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {//Simulamos almacenar
                Thread.sleep(rnd.nextInt(5000)+2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
