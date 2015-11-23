package com.company;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Main {
    //-Proyecto Tarea Periódica - Cada 5 segundos que ejecute la tarea , que lo haga cada 5 segundos teniendo en cuenta la finalizacion de la tarea, usar simpledateformat para mostrar por pantalla

    public static void main(String[] args) {
        ScheduledExecutorService ejecutor = Executors.newScheduledThreadPool(1);
        Tarea tarea = new Tarea("Tarea1");
        //No espera a la finalizacion de la tarea sino que cada 5 segundos comienza
        //ScheduledFuture<?> resultado = ejecutor.scheduleAtFixedRate(tarea, 3, 5, TimeUnit.SECONDS);
        //Si la tarea aun no ha finalizado, espera que finalice para aplicar el delay
        ScheduledFuture<?> resultado = ejecutor.scheduleWithFixedDelay(tarea, 3, 5, TimeUnit.SECONDS);

        //comprobacion cada 50 ms
        for(int i =0 ; i<100 ; i++) {
            try { //hace falta dormir el hilo principal para que el otro hilo se ejecute
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ejecutor.shutdown();
    }
}
