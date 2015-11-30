package com.company; //Created by Corpex, by the Grace of God, on 25/11/2015.

import java.util.concurrent.CompletionService;

public class Remitente implements Runnable{
    static int cont=0;
    String codigoCarta;
    String nombre;
    CompletionService<String> servicio;

    public Remitente(String nombre, CompletionService<String> servicio){
        this.nombre = nombre;
        this.servicio = servicio;
        codigoCarta = "Carta"+ cont++;
    }

    @Override
    public void run() {
        //callable retorna codigo que despues imprimira el cartero "se ha mandado la carta Carta1."
        Oficina generador = new Oficina(codigoCarta); //no seguro que sea asi. enviamos a la oficina la carta
        System.out.println("Remitente envia "+codigoCarta);
        servicio.submit(generador);
    }
}
