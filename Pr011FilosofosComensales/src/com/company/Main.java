package com.company;

import java.util.ArrayList;

public class Main {

    private static final int NUM_COMENSALES = 5;

    public static void main(String[] args) {
        ArrayList<Palillo> listaPalillos = new ArrayList<>();
        ArrayList<Thread> listaComensales = new ArrayList<>();

        //Creacion de palillos
        for(int i =0 ; i<NUM_COMENSALES ; i++){
            listaPalillos.add(new Palillo(i));
        }

        //Creacion de comensales
        for(int i =0 ; i<NUM_COMENSALES ; i++){
            Palillo palilloDerecha = listaPalillos.get(i);
            Palillo palilloIzquierda;
            if(i!=NUM_COMENSALES-1)
                palilloIzquierda = listaPalillos.get(i+1);
            else
                palilloIzquierda = listaPalillos.get(0);

            listaComensales.add(i, new Thread(new Comensal(i, palilloDerecha, palilloIzquierda)));
            listaComensales.get(i).start();
        }

    }
}
