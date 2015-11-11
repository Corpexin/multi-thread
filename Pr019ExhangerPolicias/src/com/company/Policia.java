package com.company; //Created by Corpex, by the Grace of God, on 11/11/2015.

import java.util.List;
import java.util.concurrent.Exchanger;

public class Policia implements Runnable {
    List<String> buffer;
    private final Exchanger<List<String>> intercambiador;

    public Policia(List<String> buffer, Exchanger<List<String>> intercambiador) {
        this.buffer = buffer;
        this.intercambiador = intercambiador;
    }

    @Override
    public void run() {
        //10 ciclos y por cada ciclo se rellenan 10 rehenes
        for (int i = 0; i < 10; i++) {
            System.out.println("Policia - Ciclo " + i);
            try {
                //Se usa primero el exhanger para cambiar nada mas empezar
                System.out.println("Policia - Intercambio de Rehenes");
                buffer = intercambiador.exchange(buffer); //no olvidar igualar el buffer -.-
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Informo de como queda el buffer una vez intercambiado.
            System.out.printf("Policia -> Rehenes a intercambiar: %d\n", buffer.size());

            for (int j = 0; j < 10; j++) {
                String dato = buffer.get(0);
                System.out.println("Policia - " + dato + " sacado.");
                buffer.remove(0);
            }
        }
    }
}
