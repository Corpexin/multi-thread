package com.company; //Created by corpex, by the Grace of God, on 03/12/2015.

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

// Simula el objeto receptor que procesa los informes generados.
public class Receptor implements Runnable {

    // Servicio para ejecutar el generador de informe.
    private CompletionService<String> servicio;
    // Indicador de si se debe finalizar el Receptor.
    private boolean finalizar;

    // Constructor. Recibe el servicio donde ejecutar el generador.
    public Receptor(CompletionService<String> servicio) {
        this.servicio = servicio;
        finalizar = false;
    }

    @Override
    public void run() {
        // Mientras no haya que finalizar el Receptor.
        while (!finalizar) {
            try {
                // Obtengo del servicio el objeto Future resultado.
                Future<String> resultado = servicio.poll(20, TimeUnit.SECONDS);
                if (resultado != null) {
                    // Obtengo del objeto Future el informe.
                    String informe = resultado.get();
                    System.out.printf("Receptor -> Recibido %s\n", informe);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Receptor -> Finalizando...\n");
    }

    // Establece el indicador de finalización del receptor.
    public void setFinalizar(boolean finalizar) {
        this.finalizar = finalizar;
    }

}