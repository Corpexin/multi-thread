package com.company; //Created by Corpex, by the Grace of God, on 07/10/2015.

public class GeneradorPrimos extends Thread{

    public void run(){
        int cont = 0;

        while (true) {
            if (esPrimo(cont))
                System.out.println(cont + " es primo");
            else
                System.out.println(cont + " no es primo");
            cont++;

            if(isInterrupted()){
                System.out.println("Se ha interrumpido el hilo");
                return;
            }

        }
    }

    public boolean esPrimo(int num){
        boolean esP=true;
        int cont =2;

        while(cont<num){
            if(num%cont==0)
                esP = false;

            cont++;
        }
        return esP;
    }
}

