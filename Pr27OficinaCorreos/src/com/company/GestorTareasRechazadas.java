package com.company; //Created by corpex, by the Grace of God, on 30/11/2015.

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class GestorTareasRechazadas implements RejectedExecutionHandler{

    //Muestro informacion sobre la tarea y ejecutor
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("Remitente rechazado");
    }
}
