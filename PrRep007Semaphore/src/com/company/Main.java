package com.company;

public class Main {
    /**En este proyecto vamos a simular una cola de impresion unica que recibe los documentos a imprimir por parte de los
     * distintos hilos y que los imprime en alguna de las impresoras disponibles. Para gestionar el acceso a las impresoras
     * usaremos un semaforo. Para comprobar la disponibilidad de las impresoras usaremos un array protegido con un objeto
     * ReentrantLock
     *
     */

    //Semaphore: Permite/prohibe acceso a hilos. Permite pasar mientras  su contador es 0. Cada hilo que deja pasar resta 1 al contador.
    //Cuando llega a 0 no deja pasar a mas hasta que algun hilo salga.
    //Cuando llega a 0 el semaforo suspende el resto de hilos que intentan entrar.
    //Cuando un hilo intenta entrar hace un acquire(). para salir un release(). (tambien tryAcquire). Se puede hacer aquire(n)/release(n) para pillar + de un recurso
    //Metodos: availablePermits(): retorna el contador
    //hasQueuedThreads() si hay hilos esperando
    //getQueueLength() numero de hilos esperando
    //isFair() si esta activado el modo justo

    public static void main (String args[]) {
        // Creo la cola de impresión.
        ColaImpresion colaImpresion = new ColaImpresion();
        // Creo doce hilos para los trabajos de impresión.
        Thread hilos[] = new Thread[12];
        for (int i = 0; i < 12; i++){
            hilos[i] = new Thread(new Trabajo(colaImpresion), "Trabajo " + i);
        }
        // Inicio los hilos.
        for (int i = 0; i < 12; i++){
            hilos[i].start();
        }
    }

}