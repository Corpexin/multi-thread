package com.company; //Created by corpex, by the Grace of God, on 02/12/2015.


import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Servidor {
    ThreadPoolExecutor ejecutor;

    public Servidor(){
        //ejecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        ejecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
    }

    public void gestionarTarea(Cliente.Tarea tarea) {
        ejecutor.execute(tarea);
    }

    public void apagarServer(){
        ejecutor.shutdown();
    }
}
