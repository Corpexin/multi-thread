package com.company;

import java.util.concurrent.*;

public class Main {

    /**
     * Este mecanismo tiene la limitación de que el hilo de procesamiento de resultados tan solo puede obtener los objetos Future de aquellas tareas que hayan finalizado su ejecución, dada las características del método poll(), por lo que únicamente pueden ser usados para obtener el resultado de las tareas y no para otras operaciones de control.
     * @param args
     */

    /**
     * La clase CompletionService proporciona distintos métodos para obtener los objetos Future correspondiente al resultado de la ejecución de las tareas:
     Future<V> poll(): Extrae de la cola interna el objeto Future correspondiente al resultado de la siguiente tarea completada o null si no hay ninguno.
     Future<V> poll(long timeout, TimeUnit unit): Extrae de la cola interna el objeto Future correspondiente al resultado de la siguiente tarea completada, esperando si es necesario el tiempo máximo pasado como argumento.
     Future<V> take():Extrae de la cola interna el objeto Future correspondiente al resultado de la siguiente tarea completada, bloqueando el hilo hasta que la cola tenga algún elemento.
     * @param args
     */

    /**
     * En este proyecto vamos a desarrollar una aplicación en la que varios solicitantes solicitan un informe, creando cada
     * uno de ellos un objeto generador de informes que envían a un servicio CompletionService. Otro objeto receptor
     * es el encargado de extraer del servicio los informes generados y mostrarlos por pantalla.
     * @param args
     */

    //No ejecuta el ejecutor en el main, es la novedad que le veo...

    public static void main(String[] args) {
        // Creo el ejecutor.
        ExecutorService ejecutor = (ExecutorService) Executors
                .newCachedThreadPool();
        // Creo el CompletionService y le paso al constructor el ejecutor.
        CompletionService<String> servicio = new ExecutorCompletionService<>(
                ejecutor);
        // Creo los dos hilos solicitantes de un informe.
        Thread sol1 = new Thread(new Solicitante("Baldomero", servicio));
        Thread sol2 = new Thread(new Solicitante("Genaro", servicio));
        // Creo un hilo para ejecutar el receptor de los informes.
        Receptor r = new Receptor(servicio);
        Thread receptor = new Thread(r);
        // Inicio los hilos.
        sol1.start();
        sol2.start();
        receptor.start();
        // Espero a la finalización de los hilos solicitantes de informes.
        try {
            sol1.join();
            sol2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Finalizo el ejecutor
        ejecutor.shutdown();
        // Espero la finalización de todas las tareas del ejecutor.
        try {
            ejecutor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Finalizo la ejecución del procesador de informes.
        r.setFinalizar(true);
    }

}