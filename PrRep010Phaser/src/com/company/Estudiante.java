package com.company; //Created by corpex, by the Grace of God, on 02/12/2015.

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Estudiante implements Runnable {

    Secuenciador secuenciador;
    SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");
    Random aleatorio = new Random();

    // Constructor.
    public Estudiante(Secuenciador secuenciador) {
        this.secuenciador = secuenciador;
    }

    @Override
    public void run() {
        // Fase 0: sentarse.
        secuenciador.arriveAndAwaitAdvance();
        // Fase 1: hacer ejercicio 1.
        hacerEjercicio(1);
        secuenciador.arriveAndAwaitAdvance();
        // Fase 2: hacer ejercicio 2.
        hacerEjercicio(2);
        secuenciador.arriveAndAwaitAdvance();
        // Fase 3: hacer ejercicio 3 finalizando el examen.
        hacerEjercicio(3);
        secuenciador.arriveAndAwaitAdvance();
    }

    private void hacerEjercicio(int i) {
        try {
            // Se informa de que el estudiante ha hecho el ejercicio.
            System.out.println(formateador.format(new Date()) + " --> "
                    + Thread.currentThread().getName()
                    + " inicia el ejercicio " + i);
            // Simulo la realización del ejercicio.
            TimeUnit.SECONDS.sleep(aleatorio.nextInt(6));
            // Se informa de que el estudiante ha hecho el ejercicio.
            System.out.println(formateador.format(new Date()) + " --> "
                    + Thread.currentThread().getName()
                    + " ha finalizado el ejercicio " + i);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}