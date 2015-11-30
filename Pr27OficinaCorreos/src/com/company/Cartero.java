package com.company; //Created by Corpex, by the Grace of God, on 25/11/2015.

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Cartero implements Runnable {
    CompletionService<String> servicio;
    private boolean finalizar;

    public Cartero(CompletionService<String> servicio) {
        this.servicio = servicio;
        finalizar = false;
    }

    @Override
    public void run() {
        while(!finalizar){
            try {
                Future<String> resultado = servicio.poll(20, TimeUnit.DAYS);
                String codigoCarta = resultado.get();
                System.out.println(codigoCarta+" enviada");
                System.out.println();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

    }

    void setFinalizar(boolean finalizar){
        this.finalizar = finalizar;
    }
}
