package com.company;

public class Main {

    public static void main(String[] args) {
        Thread hilo = new GeneradorPrimos();
        hilo.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        hilo.interrupt();
    }

}

