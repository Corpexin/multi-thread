package com.company; //Created by corpex, by the Grace of God, on 03/12/2015.

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class GestorTareasRechazadas implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        // Muestro información sobre la tarea y el ejecutor.
        System.out.printf("%s rechazada por %s\n", r.toString(),
                executor.toString());
        System.out
                .printf("Ejecutor terminando? %s\n", executor.isTerminating());
        System.out.printf("Ejecutor finalizado? %s\n", executor.isTerminated());
    }

}