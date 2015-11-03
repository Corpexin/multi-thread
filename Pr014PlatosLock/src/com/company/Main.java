package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> listaP = new ArrayList<>();
	    PilaPlatos platosLimpios = new PilaPlatos(listaP);
        PilaPlatos platosSecos = new PilaPlatos(listaP);

        Thread hLimpiador = new Thread(new Limpiador(platosLimpios), "Limpiador");
        Thread hSecador  = new Thread(new Secador(platosLimpios, platosSecos), "Secador");
        Thread hPonedor = new Thread(new Ponedor(platosSecos), "Ponedor");

        hLimpiador.start();
        hSecador.start();
        hPonedor.start();


    }
}
