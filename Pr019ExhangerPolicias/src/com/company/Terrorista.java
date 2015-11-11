package com.company; //Created by Corpex, by the Grace of God, on 11/11/2015.

import java.util.List;
import java.util.concurrent.Exchanger;

public class Terrorista implements Runnable {
    List<String> buffer;
    private final Exchanger<List<String>> intercambiador;

    public Terrorista(List<String> buffer , Exchanger<List<String>> intercambiador){
        this.buffer = buffer;
        this.intercambiador = intercambiador;
    }

    @Override
    public void run() {
        //10 ciclos y por cada ciclo se rellenan 10 rehenes
        for(int i = 0 ; i<10 ; i++){
            System.out.println("Terrorista - Ciclo "+i);
            for(int j = 0 ; j<10 ; j++){
                buffer.add("Rehen"+j);
                System.out.println("Terrorista - Rehen"+j+" introducido.");
            }

            try {
                //Una vez terminado se usa el exhanger
                System.out.println("Terrorista - Intercambio de Rehenes");
                buffer = intercambiador.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Informo de como queda el buffer una vez intercambiado.
            System.out.printf("Terrorista -> Rehenes a intercambiar: %d\n", buffer.size());
        }
    }
}
