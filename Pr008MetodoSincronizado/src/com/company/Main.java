package com.company;

public class Main {

    public static void main(String[] args) {

        Cuenta c = new Cuenta(10);
        Ingresador i = new Ingresador(c);
        Gastador g = new Gastador(c);

        Thread ti = new Thread(i, "Hilo Ingresador");
        Thread tg = new Thread(g, "Hilo Gastador");

        ti.start();

        try {
            ti.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        tg.start();
        try {
            tg.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
