package com.company;

public class Main {
    //En este proyecto crearemos 10 hilos que perteneceran al mismo grupo de hilos.La tarea de estos hilos sera simular
    //que se realiza una busqueda en internet, por lo que generaremos aleatoriamente el numero de segundos que tarda en
    //realizarse la busqeuda(y que el hilo pasara durmiendo en la simulacion). Cuando cualquiera de los hilos finaliza
    //la busqueda, almacena el resultado en un objeto creado especifincamente para ello (en en nuestra simulacion contendra el
    //nombre del hilo que ha finalizado la busqeda). Cuando detectemos que uno de los hilos del grupo ha finalizado
    //interrumpiremos a todos los hilos del grupo, ya que no tiene sentido que sigan ejecutandose una vez encontrado
    //el resultado.

    public static void main(String[] args) {
        //Creamos el ThreadGroup
        ThreadGroup grupoHilos = new ThreadGroup("Grupo Buscadores"); //siempre hay que pasarle nombre por parametro
	    //Creamos los 10 hilos
        Thread[] hilos = new Thread[10];
        //Asignamos los 10 hilos al threadgroup
        for(int i =0 ; i<hilos.length ; i++){
            hilos[i] = new Thread(grupoHilos, new BuscadoresInternet(), "Buscador"+i);
        }
        //Iniciamos los hilos.
        for(Thread t:hilos){
            t.start();
        }
        //Comprobamos cada x tiempo si alguno de los hilos ha finalizado
        while(grupoHilos.activeCount()==10){
            //dormimos cada 50 ms el principal para que el resto siga procesando
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //Interrrumpimos a todos los hilos del grupo
        grupoHilos.interrupt();

        //activeCount() numero de hilos del grupo activos(en ejecucion)
        //list() muestra por salida estandar informacion sobre los hilos del grupo(?)
        //enumerate(Thread[] lista)  que almacena en un array pasado como argumento el conjunto de hilos activos del grupo

        //Los ThreadGroup se usan principalmente cuando queremos realizar una misma accion con todos los hilos, especialmente interrumpirlos
    }
}
