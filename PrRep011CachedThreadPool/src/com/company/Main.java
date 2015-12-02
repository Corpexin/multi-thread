package com.company;

public class Main {

    //Ejecutores
    //Para trabajar con ejecutores se usa un objeto de la clase ThreadPoolExecutor
    //Creamos el ejecutor y le pasamos los Runnable/Callable con execute()
    /**
     * getPoolSize(): Retorna el número real de hilos en el grupo gestionado por el ejecutor.
     getActiveCount(): Retorna el número de hilos que están ejecutando tareas en el ejecutor.
     getCompletedTaskCount(): Retorna el número de tareas completadas por el ejecutor.
     getTaskCount(): Retorna el número de tareas que han sido enviadas al ejecutor.
     getLargestPoolSize(): Retorna el número máximo de hilos que han sido utilizados a la vez por el ejecutor en un momento dado.
     getCorePoolSize(): Retorna el número mínimo de hilos que utilizará internamente el ejecutor si no está ejecutando ninguna tarea.
     */
    //Se cierra el ejecutor con shutdown()
    /**
     * shutdownNow(): Finaliza la ejecución del ejecutor inmediatamente, por lo que no ejecuta las tareas pendientes,
     * retorna una lista con éstas. Las tareas que están en ejecución en el momento en el que se llama al método continúan
     * su ejecución, pero el método no espera a que finalicen.
     isTerminating(): Retorna true si el ejecutor está realizando la operación de finalizar su ejecución, pero no ha concluido aún.
     isTerminated(): Retorna true si se ha llamado al método shutdown() o shutdownNow() del ejecutor y todas las
     tareas han finalizado su ejecución.
     isShutdown(): Retorna true si se ha llamado al método shutdown() del ejecutor.
     awaitTermination(long timeout, TimeUnit unit): Bloquea el hilo en el que se ejecuta hasta que las tareas del
     ejecutar hayan terminado o transcurre el tiempo máximo de espera (timeout). La unidad (unit) puede ser una de las
     constantes TimeUnit.DAYS, TimeUnit.HOURS, TimeUnit.MINUTES, TimeUnit.SECONDS, etc. Si queremos esperar a la
     finalización de las tareas independientemente de su duración deberemos especificar un tiempo máximo muy alto,
     como por ejemplo, días.
     */

    /**
     * En este proyecto simularemos el funcionamiento de un servidor web que procesa peticiones de varios clientes.
     * Para ello haremos uso de la clase Executors y de su método newCachedThreadPool() para obtener un objeto
     * ExecutorService que convertiremos explícitamente a ThreadPoolExecutor.
     */
    //CacheThreadPool - newCachedThreadPool() Reutiliza hilos  para realizar tareas, o si no puede, crea uno nuevo
    //FixedThreadPool - newFixedThreadPool(n) Reutiliza un numero de mismos hilos que se pasa por parametro, se suele
    // usar cuando el numero de tareas es alto, para no sobrecargar el sistema.

    public static void main(String[] args) {

        //Creamos el servidor (gestiona tod el tema de los ejecutores)
        Servidor serv = new Servidor();
        //Creamos hilos clientes
        Thread[] clientes = new Thread[50];
        //Inicializamos los clientes
        for(int i=0 ; i<clientes.length ; i++){
            clientes[i] = new Thread(new Cliente(serv), "Cliente"+i);
            clientes[i].start();
        }
        //Una vez finalizados todos los clientes se cierra el ejecutor del servidor
        try { //dejo 10 segundos para que se termine el programa ya que todavia no se han dado los callable/future
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        serv.apagarServer();
    }
}
