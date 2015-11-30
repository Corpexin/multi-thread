package com.company;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {

    public static void main(String[] args) {
        //Creamos el ejecutor y future
        ThreadPoolExecutor ejecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        Future<String> resultado = ejecutor.submit(new Tarea());

        //Dormimos
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Cancelamos
        resultado.cancel(true); //Si le paso el true, termina subitamente, si le paso false se espera a finalizar la tarea

        //Verificamos
        System.out.println("Cancelada? "+resultado.isCancelled());
    }
}
