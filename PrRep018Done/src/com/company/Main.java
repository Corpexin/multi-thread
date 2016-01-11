package com.company;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    /**
     * Ejecutar codigo una vez finalizada tarea en el ejecutor, para por ejemplo generar informar, enviar datos por email, etc. NO se podra cambiar el valor retornado por la tarea.
     El done es llamado internamente por la clase FutureTask cuando termina la tarea que este ejecutando y se automarque como isDone.
     Para usarlo, hacer extends de FutureTask y sobreescribir el done().
     Para acceder desde el done al resultado del callable, usamos get()

     * @param args
     */

    /**
     * En este proyecto enviaremos al ejecutor cinco tareas de una subclase de FutureTask, cada una de las cuales gestiona una tarea "real" y muestra un mensaje específico cuando ésta termina de ejecutarse. Además, una vez enviadas la tareas esperaremos 5 segundos antes de cancelarlas y mostrar sus resultados. La tarea "real" consistitirá en dormir durante un número de segundos aleatorio.
     * @param args
     */
    public static void main(String[] args) {
        // Creo el ejecutor
        ExecutorService ejecutor = (ExecutorService) Executors
                .newCachedThreadPool();
        // Para cada tarea real, creo una tarea de resultado que es la que envío
        // al ejecutor.
        TareaResultado tareasResultado[] = new TareaResultado[5];
        for (int i = 0; i < 5; i++) {
            tareasResultado[i] = new TareaResultado(new Tarea("Tarea " + i));
            ejecutor.submit(tareasResultado[i]);
        }
        // Espero 5 segundos.
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        // Cancelo todas las tareas. Si las tareas ya han finalizado su
        // ejecución, no les afecta.
        for (int i = 0; i < tareasResultado.length; i++) {
            tareasResultado[i].cancel(true);
        }
        // Escribo el resultado de las tareas que finalizaron su ejecución
        // antes de la cancelación.
        for (int i = 0; i < tareasResultado.length; i++) {
            try {
                if (!tareasResultado[i].isCancelled()) {
                    System.out.printf("%s\n", tareasResultado[i].get());
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        // Finalizo el ejecutor.
        ejecutor.shutdown();
    }

}