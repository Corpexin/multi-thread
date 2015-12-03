package com.company;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
    /**
     * En este proyecto enviaremos 5 tareas a un ejecutor ScheduledThreadPoolExecutor con uno sólo hilo, de manera que las tareas se ejecuten en orden y con un retardo de 1 segundo entre ellas. La tarea en sí será simulada con la escritura de un mensaje y retornará una cadena con un saludo.
     *
     */

    /**
     * -Schedule - Ejecutores planificados, con un delay inicial o si son repetitivas
     Se usa el ejecutor Planificado ScheduledThreadPoolExecutor = newScheduledThreadPool(numerodehilosdelEjeutor)
     Para enviar tareas al ejecutor se usa .schedule(tarea, tiempodeEsperaParaEjecutar)
     *Si queremos que la tarea se ejecute en un momento concreto, deberemos calcular la diferencia entre dicho momento y la fecha/hora actual y usarla como periodo de espera.
     **setExecuteExistingDelayedTasksAfterShutdownPolicy() del ejecutor. Si le pasamos como parámetro el valor false las tareas pendientes no serán ejecutadas al finalizar el ejecutor.

     * @param args
     */
    public static void main(String[] args) {
        // Creo un ejecutor para tareas planificadas con 1 hilo
        // (ScheduledThreadPoolExecutor)
        ScheduledThreadPoolExecutor ejecutor = (ScheduledThreadPoolExecutor) Executors
                .newScheduledThreadPool(1);
        // Envío cinco tareas al ejecutor para que sean ejecutadas con un
        // cierto retardo respecto a cuando son enviadas:
        // Tarea 0: 1 segundo, Tarea 1: 2 segundos, Tarea 2: 3 segundos, etc.
        for (int i = 0; i < 5; i++) {
            // Creo la tarea
            Tarea tarea = new Tarea("Tarea " + i);
            // Informo del envío de la tarea.
            System.out.printf("%s -> Enviada en: %s\n", "Tarea " + i,
                    new Date());
            // Envío la tarea planificando su retardo.
            ejecutor.schedule(tarea, i + 1, TimeUnit.SECONDS);
        }
        // Finalizo el ejecutor.
        ejecutor.shutdown();
        // Espero la finalización de las tareas una vez finalizado el ejecutor.
        try {
            ejecutor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Informo de que todas las tareas han finalizado
        System.out.printf("Todas las tareas finalizadas en: %s\n", new Date());
    }

}