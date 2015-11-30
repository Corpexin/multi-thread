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
        Random rnd = new Random();
        int cont;
        //Creamos el ejecutor con max 2 hilos
        ThreadPoolExecutor ejecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        //Creamos la lista de tareas
        List<Tarea> listaTareas = new ArrayList<>();
        //Introducimos valores de numeros aleatorios
        for(int i=0 ; i<10 ; i++){
            listaTareas.add(new Tarea(rnd.nextInt(20)));
        }
        //Creamos la lista de future que recibiran los resultados de dichas tareas
        List<Future<Integer>> listaFutures = new ArrayList<>();
        //Ejecutamos la lista de tareas con el ejecutor
        for(int i=0 ; i<10 ; i++){
            listaFutures.add(ejecutor.submit(listaTareas.get(i))); //vamos guardando los resultados de las tareas
        }
        //Vamos preguntando cada cierto tiempo si ya ha terminado de recibir el resultado todos los futures
        System.out.print("Tareas Compleatas: ");
        do {
            cont =0;
            for(Future<Integer> f : listaFutures){
                if(f.isDone())
                    cont++;
            }
            System.out.print(cont+" ");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (cont!=10);
        System.out.println("Todas la tareas completadas. Resultados:");
        //Cuando termine muestro los resultados
        cont =0;
        for(Future<Integer> f : listaFutures){
            try {
                System.out.println("Tarea"+cont+" - Factorial de "+listaTareas.get(cont).num+":"+f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            cont++;
        }
        ejecutor.shutdown();
    }
}
