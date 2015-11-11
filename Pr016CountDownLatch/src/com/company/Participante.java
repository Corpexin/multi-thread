package com.company; //Created by Corpex, by the Grace of God, on 11/11/2015.

import java.util.concurrent.CountDownLatch;

public class Participante implements Runnable{
    CountDownLatch pestillo;

    public Participante(CountDownLatch pestillo){
        this.pestillo = pestillo;
    }

    @Override
    public void run() {
        //Los participantes se conectan
        conectar();
        pestillo.countDown();
        try {
            pestillo.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //por cada conexion se resta 1 al countdown
        //cuando se conecta un participante se pone a dormir hasta que todos se conectan
        //cuando se conectan todos, comienza la videoconferencia.

    }

    private void conectar() {
        System.out.println(Thread.currentThread().getName()+" se ha conectado");
    }
}
