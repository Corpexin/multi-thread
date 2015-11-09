package com.company;


public class Main {

    public static void main(String[] args) {
        Fase fase = new Fase(10);
        //Creo los estudiantes
        Thread[] estudiantes = new Thread[10];

        for(int i=0 ; i<estudiantes.length ; i++) {
            estudiantes[i] = new Thread(new Estudiante(fase), "Estudiante" + i);
        }
        //Inicio los estudiantes
        for(Thread t : estudiantes){
            t.start();
        }

        for(Thread t : estudiantes){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
