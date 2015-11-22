package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    //15 personas van a por un rotulador, se quiere saber quien devuelve el rotulador primero.
    public static void main(String[] args) {
        //Creamos el ejecutor
        ExecutorService ejecutor = (ExecutorService) Executors.newCachedThreadPool();
        //Creamos la lista de 15 personas
        List<Persona> listaPersonas = new ArrayList<>();
        //Añadimos las personas poniendoles un nombre a cada una
        for(int i=0 ; i<15 ; i++){
            listaPersonas.add(new Persona("Persona"+i));
        }
        //Esperamos al ganador y lo imprimimos
        try {
            System.out.println("Ganador: "+ejecutor.invokeAny(listaPersonas));
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Ninguno Gana.");
        }
        ejecutor.shutdown();
    }
}
