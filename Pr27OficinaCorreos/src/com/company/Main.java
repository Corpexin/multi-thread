package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        Random rnd = new Random();
        //Creo gestor de tareas rechazadas
        GestorTareasRechazadas gestor = new GestorTareasRechazadas();
	    //Creo Ejecutor
        ThreadPoolExecutor ejecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        //Asigno el ejecutor al gestor de tareas rechazadas
        ejecutor.setRejectedExecutionHandler(gestor);

        //Creo CompletionService
        CompletionService<String> servicio = new ExecutorCompletionService<>(ejecutor);
        //Creo los remitentes
        List<Thread> listaRemitentes = new ArrayList<>();
        int numeroRemitentes = rnd.nextInt(20); //generamos aleatoriamente el numero de remitentes
        for(int i=0 ; i<numeroRemitentes ; i++){
            listaRemitentes.add(new Thread(new Remitente("Remitente"+i, servicio)));
        }
        //Creo el cartero
        Cartero c = new Cartero(servicio);
        Thread cartero = new Thread(c);

        //Inicio hilos remitentes
        listaRemitentes.forEach(Thread::start);
        //Inicio hilos cartero
        cartero.start();

        //Espero finalizacion de los hilos
        for(Thread r : listaRemitentes){
            try {
                r.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Finalizo el ejecutor
        ejecutor.shutdown();

        //Espero a la finalizacion de todas las tareas
        try {
            ejecutor.awaitTermination(15, TimeUnit.SECONDS);//he cambiado esto porque si lo pongo a DAYS como estaba antes, no termina el codigo nunca
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Finalizo el cartero
        c.setFinalizar(true);

        //envio otra tarea que sera rechazada
        System.out.println("Enviando una ultima carta...");
        ejecutor.submit(new Thread(new Remitente("RemitenteUlt", servicio)));
    }
}
