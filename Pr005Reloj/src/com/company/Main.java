package com.company;

public class Main {

    public static void main(String[] args) {
        Reloj r = new Reloj();
        Thread hilo = new Thread(r);
        hilo.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        hilo.interrupt();

    }

}
