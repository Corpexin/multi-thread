package com.company; //Created by corpex, by the Grace of God, on 03/12/2015.

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

// Simula una tarea que simplemente duerme durante un periodo aleatorio de tiempo
// y retorna Hola Mundo
public class Tarea implements Callable<String> {

    private String nombre;

    // Constructor.
    public Tarea(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String call() throws Exception {
        try {
            Long duracion = (long) (Math.random() * 10);
            System.out.printf("%s: Esperando %d segundos los resultados.\n",
                    this.nombre, duracion);
            TimeUnit.SECONDS.sleep(duracion);
        } catch (InterruptedException e) {
        }
        return "Hola Mundo. Soy " + nombre;
    }

    // Retorna el nombre de la tarea.
    public String getNombre() {
        return nombre;
    }
}