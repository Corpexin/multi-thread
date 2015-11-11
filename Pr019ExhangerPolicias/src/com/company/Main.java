package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class Main {

    //mismo ej que en wiki pero con policias
    public static void main(String[] args) {
        List<String> buffer1 = new ArrayList<>();
        List<String> buffer2 = new ArrayList<>();
        Exchanger<List<String>> intercambiador = new Exchanger<>();

        //Creo hilos
        Thread policia = new Thread(new Policia(buffer1, intercambiador), "Policia");
        Thread terrorista = new Thread(new Terrorista(buffer2, intercambiador), "Terrorista");
        //Inicio hilos
        terrorista.start();
        policia.start();
    }
}
