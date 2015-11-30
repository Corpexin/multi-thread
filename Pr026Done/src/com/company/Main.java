package com.company;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
	    // Proyecto done - envio de tarea sumar los 20 primeros numeros y cuando este el resultado multiplique esa sume por 2
        ExecutorService ejecutor = (ExecutorService) Executors.newCachedThreadPool();
        TareaResultado tareaResultado = new TareaResultado(new Tarea()); //envoltura de tarea que tiene el metodo Done
        ejecutor.submit(tareaResultado); //le paso la tarea al ejecutor

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Suma = "+tareaResultado.get()); //coge el resultado por la face
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        ejecutor.shutdown();
    }
}
