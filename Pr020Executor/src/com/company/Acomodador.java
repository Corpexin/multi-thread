package com.company; //Created by Corpex, by the Grace of God, on 16/11/2015.

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Acomodador {
    //Ejecutor
    private ThreadPoolExecutor ejecutor;

    public Acomodador(){
        ejecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5); //ejecuta en 5 hilos max
        //ejecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    }

    public void acomodarCliente(Cliente cliente) {
        ejecutor.execute(cliente);
        System.out.println(cliente.nombre + " - Cliente acomodado");
        System.out.println("-Hilos activos: "+ejecutor.getActiveCount());
        System.out.println("-Tamaño de la piscina: "+ejecutor.getPoolSize());
    }

    public void cerrarTeatro() {
        ejecutor.shutdown();
    }
}
