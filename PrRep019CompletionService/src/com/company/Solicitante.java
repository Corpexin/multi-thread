package com.company; //Created by corpex, by the Grace of God, on 03/12/2015.


import java.util.concurrent.CompletionService;

// Simula un solicitante de un informe
public class Solicitante implements Runnable {

    private String nombre;

    // CompletionService usado para la ejecución de las tareas de
    // generación de informes
    private CompletionService<String> servicio;

    // Constructor. Recibe el nombre del solicitante y el servicio.
    public Solicitante(String nombre, CompletionService<String> servicio) {
        this.nombre = nombre;
        this.servicio = servicio;
    }

    @Override
    public void run() {
        // Creo el generador de informes y lo envío al servicio.
        String titulo = "Historial";
        Generador generador = new Generador(nombre, titulo);
        System.out.printf("Solicitante -> %s solicita %s\n", nombre, titulo);
        servicio.submit(generador);
    }

}