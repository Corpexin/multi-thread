package com.company; //Created by corpex, by the Grace of God, on 02/12/2015.

import java.util.Random;

public class Cliente implements Runnable{
    Servidor serv;
    Tarea tarea;

    public Cliente(Servidor serv) {
        this.serv = serv;
    }

    @Override
    public void run() {
        Random rnd = new Random();
        System.out.println(Thread.currentThread().getName()+" generando Tarea...");
        tarea = new Tarea(rnd.nextInt(99999));
        try {
            Thread.sleep(rnd.nextInt(5000)+3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" - ¡Generada con id= "+tarea.id+"! y enviada al servidor\n");
        serv.gestionarTarea(tarea);
    }

    public class Tarea implements Runnable {
        int id;
        Tarea(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            //Simulamos ejecutar la tarea
            System.out.print("Ejecutando Tarea "+id);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(" ...Tarea Finalizada\n");
        }
    }
}
