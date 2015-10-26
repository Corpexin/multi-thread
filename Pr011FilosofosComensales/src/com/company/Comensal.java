package com.company; //Created by Corpex, by the Grace of God, on 21/10/2015.

import java.util.Random;

public class Comensal implements Runnable{
    int idComensal;
    Palillo palilloDerecha;
    Palillo palilloIzquierda;
    Random rnd;
    final Object cerrojo = new Object();

    public Comensal(int idComensal, Palillo palilloDerecha, Palillo palilloIzquierda){
        this.idComensal =  idComensal;
        this.palilloDerecha = palilloDerecha; //asigno los palillos que le han pasado al comensal a cada comensal
        this.palilloIzquierda = palilloIzquierda;
        rnd = new Random();
    }

    @Override
    public void run() {
        while (true) {
            piensa();
            //Dependiendo si es par o impar coge un palillo u otro
            if(idComensal%2!=0){
                cogerPalilloDerecha();
                cogerPalilloIzquierda();
            }else{
                cogerPalilloIzquierda();
                cogerPalilloDerecha();
            }
            come();
            if(idComensal%2!=0){
                sueltaPalilloDerecha();
                sueltaPalilloIzquierda();
            }else{
                sueltaPalilloIzquierda();
                sueltaPalilloDerecha();
            }
        }

    }

    private void piensa() {
        System.out.println("Filosofo "+idComensal+" se pone a pensar");
        try {
            Thread.sleep(rnd.nextInt(5000)+3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Filosofo "+idComensal+" le entra hambre");
    }

    private void cogerPalilloDerecha() {
        synchronized (cerrojo){
            if(palilloDerecha.palilloDisponible) {
                palilloDerecha.palilloDisponible = false;
                System.out.println("Filosofo "+idComensal+" coge palillo derecha");
            }
            else{
                while (!palilloDerecha.palilloDisponible) {
                    System.out.println("Filosofo "+idComensal+" NO puede coger palillo derecha");
                    try {
                        cerrojo.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }



        }
    }

    private void cogerPalilloIzquierda() {
        synchronized (cerrojo){
            if(palilloIzquierda.palilloDisponible){
                palilloIzquierda.palilloDisponible = false;
                System.out.println("Filosofo "+idComensal+" coge palillo izquierda");
            }
            else{
                while (!palilloIzquierda.palilloDisponible) {
                    System.out.println("Filosofo "+idComensal+" NO puede coger palillo izquierda");
                    try {
                        cerrojo.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }



        }
    }

    private void sueltaPalilloDerecha() {
        synchronized (cerrojo){
            palilloDerecha.palilloDisponible = true;
            System.out.println("Filosofo "+idComensal+" deja palillo derecha");
            cerrojo.notifyAll();
        }
    }

    private void sueltaPalilloIzquierda() {
        synchronized (cerrojo){
            palilloIzquierda.palilloDisponible = true;
            System.out.println("Filosofo "+idComensal+" deja palillo izquierda");
            cerrojo.notifyAll();
        }
    }

    private void come() {
        System.out.println("Filosofo "+idComensal+" se pone a comer");
        try {
            Thread.sleep(rnd.nextInt(5000)+3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Filosofo "+idComensal+" deja de comer");
    }



}
