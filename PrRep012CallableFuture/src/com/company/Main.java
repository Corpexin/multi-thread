package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    //callables implementan el metodo call que debe retornar un valor
    //futures obtienen el resultado de los callables y gestionan su estado
    //Para enviar callables al ejecutor se usa submit(),(y no execute) que recibe un callable y retorna un future

    //Para gestionar el estado de una tarea con future se puede usar isDone() para comprobar si ha finalizado
    //Para obtener el resultado del metodo call se usa el metodo get() del future, que espera que el call termine
    //get() puede sobrecargarse con el tiempo que vaya a estar el future esperando la finalizacion de la tarea
    //get() tambien sirve en el future para sacar la informacion del mismo ejemplo String s = future.get()


    /**
     *En este proyecto desarrollaremos una aplicación que calcula el factorial de 10 números distintos.
     * Crearemos un tarea Callable que calcule el factorial de un número y un ejecutor con un grupo de
     * 2 hilos que lance las diez tareas y obtenga el resultado de cada una de ellas,
     * usando objetos Future, y lo almacene en una lista, para finalmente mostrar todos los resultados.
     */
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
        for (int i = 0; i < 10; i++) {
            numeros[i] = aleatorio.nextInt(10);
            CalculadoraFactorial factorial = new CalculadoraFactorial(numeros[i]);
            Future<Integer> futuroResultado = ejecutor.submit(factorial);
            listaResultados.add(futuroResultado);
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