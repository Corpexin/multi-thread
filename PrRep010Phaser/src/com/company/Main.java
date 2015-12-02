package com.company;

public class Main {

    /**
     * En este proyecto desarrollaremos una aplicación que simule la realización de un examen, en el que varios estudiantes
     * deberán realizar tres ejercicios. Todos los estudiantes deben finalizar un ejercicio antes de proceder con el
     * siguiente.
     */


    //Phaser - Varias fases, los hilos se sincronizan al final de cada fase
    //Cuando un hilo llega al final de una fase ejecuta arriveAndAwaitAdvance() que lo suspende
    //El numero de hilos por fases se define en el constructor del phaser con un entero
    //Cuando un hilo termina todas las fases, ejecuta arriveAndDeregister() que lo saca del Phaser

    /**
     * arrive(): Notifica al objeto Phaser que el hilo ha finalizado una fase, pero NO se suspende el hilo,
     * este hilo NO espera a que los demás concluyan la fase, sino que continua su ejecución.
     awaitAdvance(int phase): Suspende el hilo hasta que todos los hilos participantes han finalizado la fase actual,
     siempre y cuando el número de fase actual coincida con el valor recibido. Si no coincide, el método retorna inmediatamente y el hilo no es suspendido.
     awaitAdvanceInterruptibly(int phaser): Similar al método anterior, pero lanza la excepción InterruptedException si el hilo que está suspendido en este método es interrumpido.
     register(): Añade al hilo que lo ejecuta como participante de la sincronización por fases, y se considerará que el hilo no ha finalizado aún la fase actual.
     bulkRegister(int parties): Añade el número especificado de hilos como participantes de la sincronización por fases. Se considerará que estos hilos no han finalizado aún la fase actual.
     El único método de la clase Phaser para decrementar el número de hilos participantes en la sincronización por fases es el método arriveAndDeregister(), del que ya hemos hablado.
     forceTermination(): Hace que el objeto Phaser sea marcado como terminado independientemente del número de participantes que hayan ejecutado el método arriveAndDeregister(). Cuando un objeto Phaser entra en este estado de terminación, los métodos awaitAdvance() y arriveAndAwaitAdvance() retornan un valor negativo, en vez del valor positivo que retornan habitualmente, algo que deberemos comprobar cuando el objeto Phaser pueda ser terminado abruptamente.
     La clase Phaser define una serie de métodos informativos que nos permite hacer un seguimiento del estado del mismo:
     getPhase(): Retorna la fase en la que se encuentra el secuenciador.
     getRegisteredParties(): Retorna el número de participantes registrados en el secuenciador para sincronizarse por fases.
     getArrivedParties(): Retorna el número de participantes que han llegado al punto de sincronización al final de la fase actual (han finalizado la fase).
     getUnarrivedParties()
     *
     *
     */
    public static void main(String[] args) {
        // Se crea el array de hilos.
        Thread[] hilos = new Thread[5];
        // Se crea un objeto Secuenciador que sincronice cinco hilos.
        Secuenciador secuenciador = new Secuenciador(5);
        // Se crean cinco hilos y se inician.
        for (int i = 0; i < 5; i++) {
            hilos[i] = new Thread(new Estudiante(secuenciador), "Estudiante "
                    + (i + 1));
            hilos[i].start();
        }
        // Se espera la finalización de todos los hilos.
        for (int i = 0; i < 5; i++) {
            try {
                hilos[i].join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        // Se muestra si el secuenciador ha terminado.
        System.out.println("¿Secuenciador terminado? "
                + secuenciador.isTerminated());
    }

}