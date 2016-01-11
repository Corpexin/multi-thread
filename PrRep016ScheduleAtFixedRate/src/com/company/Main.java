package com.company;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        /**
         * En este proyecto enviaremos una tareas periódica a un ejecutor ScheduledThreadPoolExecutor con uno sólo hilo,
         * que se ejecutará cada dos segundos. La tarea en sí será simulada con la escritura de un mensaje.
         */

        // Creo un servicio de ejecución ScheduledExecutorService con un hilo.
        ScheduledExecutorService ejecutor = Executors.newScheduledThreadPool(1);
        // Creo una nueva tarea y la envío al ejecutar para que se ejecute con
        // un retardo inicial de 1 segundo y posteriormente se ejecute cada 2
        // segundos.
        // Como resultado obtengo un objeto SheduledFuture que me permite
        // controlar ciertos aspectos de la planificación.
        // Como la tarea implementa Runnable y no Callable y por tanto no
        // retorna nada, el parámetro de ScheduledFuture tiene que ser ?
        Tarea tarea = new Tarea("Tarea");
        ScheduledFuture<?> resultado = ejecutor.scheduleAtFixedRate(tarea, 1,
                2, TimeUnit.SECONDS);
        // Cada cierto tiempo compruebo cuánto queda para la próxima ejecución
        // de la tarea.
        for (int i = 0; i < 10; i++) {
            System.out.printf("Próxima ejecución dentro de %d milisegundos\n",
                    resultado.getDelay(TimeUnit.MILLISECONDS));
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Finalizo el ejecutor.
        ejecutor.shutdown();
    }

}

