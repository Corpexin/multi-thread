package com.company; //Created by Corpex, by the Grace of God, on 07/10/2015.

public class GeneradorFact extends Thread{

    public void run(){
        int cont = 0;

        while (true) {
            try {
                System.out.println("Factorial de "+cont+" = "+factorial(cont));
            } catch (InterruptedException e) {
                System.out.printf("%s: Se ha interrumpido el hilo de búsqueda",
                        Thread.currentThread().getName());
            }
            cont++;

            if(isInterrupted()){
                System.out.println("Se ha interrumpido el hilo");
                return;
            }

        }
    }

    public double factorial(double num) throws InterruptedException{
        double result=0;

        if(num>0)
            result = num * factorial(num-1);
        else
            result = 1;

        if (Thread.interrupted())
            throw new InterruptedException();

        return result;
    }
}