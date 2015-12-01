package com.company; //Created by corpex, by the Grace of God, on 01/12/2015.

import java.util.Random;

public class Productor implements Runnable {
    Almacen alm;

    public Productor(Almacen alm){
        this.alm = alm;
    }
    @Override
    public void run() {
        Random rnd = new Random();
        for(int i=0 ; i<rnd.nextInt(5)+5 ; i++){
            String produccion = ""+rnd.nextInt(2000);
            try { //Simulamos producir
                Thread.sleep(rnd.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Productor - Elemento producido "+produccion);
            try {
                alm.almacenarElemento(produccion);//Ejecutamos el almacenamiento del elemento
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {//Simulamos almacenar
                Thread.sleep(rnd.nextInt(1000)+2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
