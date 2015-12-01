package com.company;

public class Main {

    public static void main(String[] args) {
        //Mas potente y flexible que synchronized
        //Funcionalidades adicionales como trylock
        //oernute qye gata varios hilos lectores con readlock
        //mejor rendimiento

        //Los lock van el en pasivo, al igual que los synchro
        //si se usa try catch, meter el unlock en finally
        //getOwner() nombre del hilo que adquiere lock
        //getQueuedThreads() lista de hilos esperando
        //hasQueuedThread() si hay hilos esperando
        //isLocked() si algun hilo ha obtenido el lock
        //isFair() fair mode activado
        //getHoldCount numero de veces que se adquiere el lock
        //isHeldbyCurrentThread() si el lock ha sido adquirido por el hilo actual

        //REENTRANTREADWRITELOCK. Dos cerrojos, uno para lectura / uno para escritura
        //Cuando un hilo obtiene el cerrojo de lectura, otro puede obtener tambien el de lectura, pero no de escritura
        //Cuando un hilo obtiene el cerrojo de escritura, ningun puede obtener ni de lectura ni de escritura
        //ReentrarWriteLock cerrojoLE = new ReentrantReadWriteLock
        //Normalmente en dos metodos distintos:
        //cerrojoLE.readLock().lock // .unlock();
        //cerrojoLE.writeLock().lock //.unlock();

        //FAIR MODE. solo se usa con lock y unlock pero no con trylock. Da acceso primero al hilo que llevase mas tiempo esperando
        //Para activarlo, pasarle true por param al crear el lock, como  "new ReentrantLock(true)"

        //**Proyecto Lock - Programa que simula el envio de documentos a la cola de impresion
        //de una impresora, eniendo en cuenta que solo un docmumento puede esta imprimiendose en un momento dado.
        //Dada la sincronizacion usaremos un objeto de la clase ReentrantLock, que implementa la interfaz lock


        //Creamos la impresora
        Impresora imp = new Impresora();
        //Creamos los hilos de cola de impresion
        Thread enviador[] = new Thread[10];
        for(int i =0 ; i<enviador.length ; i++){
            enviador[i] = new Thread(new Enviador(imp),"Enviador"+i);
        }
        //Iniciamos los hilos
        for(Thread t: enviador){
            t.start();
        }
    }
}
