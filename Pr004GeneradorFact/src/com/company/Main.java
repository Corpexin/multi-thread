package com.company;

public class Main {

    public static void main(String[] args) {
        Thread hilo = new GeneradorFact();
        hilo.start();

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        hilo.interrupt();
    }

}
