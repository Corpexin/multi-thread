package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {

    public static void main(String[] args) {
        // Creo un FixedThreadPool de dos hilos.
        ThreadPoolExecutor ejecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        // Creo una lista en la que almacenar los objetos Future con el resultado
        // de los hilos.
        List<Future<Integer>> listaResultados = new ArrayList<>();
        // Creo 10 tareas consistente en el cálculo del factorial de números
        // aleatorios menores que 10.
        Random aleatorio = new Random();
        Integer[] numeros = new Integer[10];

        List<CalculadoraFact> listaTareas = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            numeros[i] = aleatorio.nextInt(10);
            CalculadoraFact factorial = new CalculadoraFact(numeros[i]);
            listaTareas.add(factorial);
            //Future<Integer> futuroResultado = ejecutor.submit(factorial);
            //listaResultados.add(futuroResultado);
        }

        try {
            listaResultados = ejecutor.invokeAll(listaTareas);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Espero la finalización de las diez tareas.
        do {
            // Mientras espero muestro los objetos Future disponibles.
            System.out.printf("Completas -> %d. Tareas:", ejecutor.getCompletedTaskCount());
            for (int i = 0; i < listaResultados.size(); i++) {
                if (listaResultados.get(i).isDone()) {
                    System.out.printf(" %d", i);
                }
            }
            System.out.printf("\n");
            // Duermo durante unas milésimas para volver a comprobar.
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (ejecutor.getCompletedTaskCount() < listaResultados.size());
        // Muestro los resultados
        System.out.printf("Resultados:\n");
        Integer valor = null;
        for (int i = 0; i < listaResultados.size(); i++) {
            // Obtengo el objeto future de la lista.
            Future<Integer> objetoResultado = listaResultados.get(i);
            try {
                valor = objetoResultado.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            System.out.printf("Tarea %d -> Factorial de %d = %d\n", i, numeros[i], valor);
        }
        // Finalizo el ejecutor.
        ejecutor.shutdown();
    }

}