package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) {
        /**
         * En este proyecto usaremos el método invokeAll() del ejecutor para ejecutar tres tareas, esperar a su finalización y mostrar sus resultados. Crearemos una clase Resultado que encapsule el nombre de la tarea que proporciona el resultado y el valor (entero) resultante de ejecutar la tarea (que simularemos mediante un valor aleatorio).
         */
        //-InvokeAll - Espera el Retornado de todos los resultados de las tareas lanzadas. Es mas eficiente que usar Future.isdone().
        //Ej: Future = ejecutor.invokeAll(Callable)

        // Creo el ejecutor.
        ExecutorService ejecutor = (ExecutorService) Executors
                .newCachedThreadPool();
        // Creo una lista con tres tareas.
        List<Tarea> listaTareas = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            listaTareas.add(new Tarea("Tarea-" + i));
        }
        // Creo una lista de objetos Future de resultados donde
        // las tareas almacenarán sus resultados.
        List<Future<Resultado>> listaResultados = null;
        // Lanzo las tres tareas esperando a que éstas finalicen y
        // almacenen los resultados en la lista de objetos Future.
        try {
            listaResultados = ejecutor.invokeAll(listaTareas);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Finalizo el ejecutor.
        ejecutor.shutdown();
        // Muestro los resultados de las tareas.
        System.out.printf("Resultados:\n");
        for (int i = 0; i < listaResultados.size(); i++) {
            Future<Resultado> retornoTarea = listaResultados.get(i);
            try {
                Resultado resultado = retornoTarea.get();
                System.out.printf("%s -> Resultado: %s\n",
                        resultado.getNombre(), resultado.getValor());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

}

