package com.company;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
    /**
     * El comportamiento de este método puede ser diferente dependiendo del valor de su parámetro y del propio estado de la tarea:
     Si la tarea ha finalizado su ejecución, ha sido cancelada con anterioridad o no puede ser cancelado por cualquier otra razón, el método retornará el valor false y la tarea no será cancelada.
     Si la tarea está esperando en el ejecutor a que se le conceda un objeto Thread (un hilo) en la que ejecutarse, la tarea es cancelada y no llegará a ser ejecutada nunca.
     Si la tarea ya se está ejecutando, el comportamiento dependerá del parámetro que recibe el método cancel(). Si recibe true, la tarea será cancelada. Si recibe false la tarea NO será cancelada.
     * @param args
     */

    /**
     * En este proyecto vamos a desarrollar una aplicación que envía una tarea a un ejecutor, espera dos segundos y después la cancela.
     * @param args
     */


    public static void main(String[] args) {
        // Creo un ejecutor.
        ThreadPoolExecutor ejecutor = (ThreadPoolExecutor) Executors
                .newCachedThreadPool();
        // Envío una tarea al ejecutor.
        Future<String> resultado = ejecutor.submit(new Tarea());
        // Duermo durante dos segundos.
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Cancelo la tarea.
        System.out.printf("Cancelando la tarea...\n");
        resultado.cancel(true);
        // Verifico que la tarea ha sido cancelada
        System.out.printf("Tarea cancelada? %s\n", resultado.isCancelled());
        System.out.printf("Tarea terminada? %s\n", resultado.isDone());
        // Finalizo el ejecutor.
        ejecutor.shutdown();
    }

}

