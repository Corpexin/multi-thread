package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        /**
         * Jefe de obra = main
         * Baldomero = ejecutor
         * Pintores = hilos
         * Pintar casa = tareas
         */

        ThreadPoolExecutor baldomero = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);//3 pintores
        List<Future<String>> listaResultados1 = new ArrayList<>();

        System.out.println("Jefe de obra envia PRIMERA REMESA a Baldomero");
        // Creo 10 tareas
        for (int i = 0; i < 10; i++) {
            CasaPintar casa = new CasaPintar(i);
            Future<String> resultado = baldomero.submit(casa);
            listaResultados1.add(resultado);
        }

        //Espero 5 segundos
        try {
            System.out.println("5 segundos de descanso...");
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Jefe de obra envia SEGUNDA REMESA a Baldomero");
        //Creo otras 15 tareas
        for (int i = 10; i < 25; i++) {
            CasaPintar casa = new CasaPintar(i);
            Future<String> resultado = baldomero.submit(casa);
            listaResultados1.add(resultado);
        }

        System.out.println("Esperando a que los pintores terminen. Por favor, sea paciente...");
        //Espero a que termine
        do {
            try {
                Thread.sleep(50);//Compruebo de nuevo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (baldomero.getCompletedTaskCount() < listaResultados1.size());


        //Muesto resultados
        System.out.println("Resultados:");
        String result = null;
        for (Future<String> objetoResultado : listaResultados1) {
            try {
                result = objetoResultado.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println(result);
        }

        baldomero.shutdown(); //Finalizo el ejecutor.
    }
}
