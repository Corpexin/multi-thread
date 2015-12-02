package com.company;

public class Main {
    /**
     * En este proyecto simularemos una videoconferencia a la que deben asistir 10 participantes.
     * Para controlar que la videoconferencia no comienza hasta que no se hayan unido a la misma todos los
     * participantes haremos uso de un objeto CountDownLatch.
     */

    //CountDownLatch - hace a los hilos esperar a que un conjunto de operaciones terminen su ejecucion.
    //Tiene un contador que va disminuyendo con cada operacion terminada
    //pestillo.await() suspende el hilo hasta que el contador disminuya a 0
    //pestillo.countDown() disminuye en 1 el contador
    //sirve para sincronizar un hilo con la ejecucion de varias tareas
    //No se puede reusar, hay que crear un pestillo nuevo

    public static void main(String[] args) {
        //Crear videoconferencia, como hace accion, es un hilo
        VideoConferencia v = new VideoConferencia();
        Thread VideoConferencia  = new Thread(v);
        //Creo participantes
        Thread[] participantes = new Thread[10];

        for(int i =0 ; i<participantes.length ; i++){
            participantes[i] = new Thread(new Participante(v), "Participante"+i);
        }
        //Inicio los participantes
        for(Thread t : participantes){
            t.start();
        }

        VideoConferencia.start();
        //Inicio la videoconferencia y controlo que no empiece hasta que se abra el pestillo

    }
}
