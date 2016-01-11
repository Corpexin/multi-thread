package com.company; //Created by corpex, by the Grace of God, on 03/12/2015.

import java.util.Date;

// Simula una tarea consistente en escribir un mensaje.
public class Tarea implements Runnable {

    private String nombre;

    // Constructor.
    public Tarea(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        // Muestra la fecha/hora actual
        System.out.printf("%s -> Ejecutada en: %s\n", nombre, new Date());
    }

}