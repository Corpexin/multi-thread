package com.company; //Created by corpex, by the Grace of God, on 04/12/2015.

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CajaAhorros {

    //Constantes.
    final int NUM_EMPLEADOS = 4;
    private Lock cerrojo;//Cerrojo para controlar el acceso al array de disponibilidad.
    Random rnd;

    private Semaphore semaforo;// Semáforo, mira que clientes pueden pasar para ser atendidos por los empleados
    private boolean empleadoLibre[];// Array de booleanos para ver si esta libre un empleado

    public CajaAhorros(){
        //Inicio los componentes
        rnd = new Random();
        semaforo = new Semaphore(NUM_EMPLEADOS, true); //con el true activo el modo justo
        empleadoLibre = new boolean[NUM_EMPLEADOS];
        for (int i = 0; i < NUM_EMPLEADOS; i++){
            empleadoLibre[i] = true;
        }
        cerrojo = new ReentrantLock();
    }

    public void llegarCaja(){
        System.out.println(Thread.currentThread().getName()+" llega a la Caja");
        try {
            semaforo.acquire();//SEMAFORO ADQUIRIDO
            int empleadoUsado = obtenerEmpleado();//Obtiene al primer empleado disponible
            System.out.println("Empleado "+empleadoUsado+" atiende a "+Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(rnd.nextInt(4)+1);//tiempo de atencion al cliente de 1 a 4 segundos
            empleadoLibre[empleadoUsado] = true; //Empleado liberado del cliente
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+" sale de la caja.");
            semaforo.release();//SEMAFORO LIBERADO
        }

    }


    private int obtenerEmpleado() {
        int empleado = -1;
        try {
            cerrojo.lock(); //CERROJO ECHADO
            for (int i = 0; i < empleadoLibre.length; i++) { //recorro todos los empleados y obtiene el primero que este libre
                if (empleadoLibre[i]){
                    empleado = i;
                    empleadoLibre[i] = false;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrojo.unlock(); //CERROJO LIBRE
        }
        return empleado;
    }


}
