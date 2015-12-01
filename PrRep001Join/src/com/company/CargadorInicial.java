package com.company; //Created by corpex, by the Grace of God, on 30/11/2015.

public class CargadorInicial implements Runnable {
    @Override
    public void run() {
        //simulamos  cargar los datos iniciales
        System.out.println("Cargando datos iniciales....");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
