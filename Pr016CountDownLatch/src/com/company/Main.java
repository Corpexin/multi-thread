package com.company;

import java.util.concurrent.CountDownLatch;

public class Main {

    public static void main(String[] args) {
        //creo el countdownlatch con contador a 10
        CountDownLatch pestillo = new CountDownLatch(11);

        //creo los 10 participantes
        Thread[] participantes = new Thread[10];

        for(int i=0 ; i<participantes.length ; i++){
            participantes[i] = new Thread(new Participante(pestillo), "Participante"+(i+1));
        }

        for(Thread t : participantes) {
            t.start();
        }

        //Resto 1 porque el principal esta tambien en el pestillo y lo pongo a esperar  a que el resto termine
        //para lanzar el mensaje de comienzo de videoconferencia
        pestillo.countDown();
        try {
            pestillo.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Comienza la VideoConferencia");
    }
}
