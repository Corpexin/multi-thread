package com.company;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        /**
         * Caja de ahorros - clase pasiva que contiene un semaforo para controlar los clientes que entran
         * Empleados - numero de hilos que va a gestionar el semaforo
         * Clientes - hilos que van a realizar las tareas
         */
        //Creo la caja de ahorros
        CajaAhorros caja = new CajaAhorros();

        Random rnd = new Random();

        //Creo los hilos
        Thread clientes[] = new Thread[50];
        for (int i = 0; i < clientes.length; i++){
            clientes[i] = new Thread(new Cliente(caja), "Cliente " + i);
        }
        // Inicio los hilos.
        for (Thread cliente : clientes) {
            cliente.start();
            try {
                TimeUnit.SECONDS.sleep(rnd.nextInt(3));//tiempo aleatorio de llegada de 0-2 segundos.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
