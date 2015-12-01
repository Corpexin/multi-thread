package com.company; //Created by corpex, by the Grace of God, on 30/11/2015.

public class EstablecedorConexion implements Runnable {
    @Override
    public void run() {
        //Simulamos establecer conexion
        System.out.println("Estableciendo Conexion....");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
