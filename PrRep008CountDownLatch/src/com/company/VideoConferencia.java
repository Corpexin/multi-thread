package com.company; //Created by corpex, by the Grace of God, on 01/12/2015.

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class VideoConferencia implements Runnable{
    CountDownLatch pestillo = new CountDownLatch(10); //10 participantes y la conferencia
    @Override
    public void run() {
        try {
            pestillo.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Conexion Establecida. VideoConferencia Iniciada");

    }

    public void conectar(String nombre) throws InterruptedException {
        //Simulo la conexion
        Random rnd = new Random();
        try {
            Thread.sleep(rnd.nextInt(5000)+3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(nombre+" conectado");
        pestillo.countDown();
        pestillo.await();
    }
}
