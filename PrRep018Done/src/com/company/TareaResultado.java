package com.company; //Created by corpex, by the Grace of God, on 03/12/2015.

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

// Gestiona una tarea real. Ejecuta internamente el método
// done() cuando finaliza la ejecución de la tarea.
public class TareaResultado extends FutureTask<String> {

    private String nombre;

    // Constructor. Recibe la tarea real (en forma de Callable para que se le
    // pueda pasar al constructor de FutureTask.
    public TareaResultado(Callable<String> tarea) {
        // Llamo al constructor de FutureTask.
        super(tarea);
        this.nombre = ((Tarea) tarea).getNombre();
    }

    // Será llamado internamente al finalizar la ejecución de la tarea.
    @Override
    protected void done() {
        // Informo de si la tarea finalizó su ejecución normalmente o fue
        // cancelada.
        if (isCancelled()) {
            System.out.printf("%s: Ha sido cancelada\n", nombre);
        } else {
            System.out.printf("%s: Ha finalizado con normalidad\n", nombre);
        }
    }

}