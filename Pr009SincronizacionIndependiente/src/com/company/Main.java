package com.company;

public class Main {

    public static void main(String[] args) {
        int salas[] = new int[2];
        //Creo las salas del cine con sus butacas disponibles y se las paso al Cine
        salas[0] = 20;
        salas[1] = 20;
        Cine cine = new Cine(salas);

        //Creo los 2 hilos de taquillas y les paso el cine
        Thread hiloTaquilla1 = new Thread(new Taquilla1(cine), "Taq1");
        Thread hiloTaquilla2 = new Thread(new Taquilla2(cine), "Taq2");

        //Inicio las 2 taquillas
        hiloTaquilla1.start();
        hiloTaquilla2.start();

        //Espero a que ambas taquillas terminen de vender
        try {
            hiloTaquilla1.join();
            hiloTaquilla2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Muestro las butacas libres de cada sala.
        int numSalas = salas.length;
        for (int i = 0; i < numSalas; i++) {
            System.out.printf("Sala %d: %d butacas libres\n", i + 1, cine.getLibres(i));
         }

    }
}
