package com.company;

public class Main {
    //En este proyecto crearemos un programa que lanza varios hilos que llevan a cabo tareas de inicializacion del programa
    //Una vez iniciados estos holos, el hilo principal del programa debera esperar que concluya la ejecucion de los mismos
    //Para poder continuar con su funcion, ya que es estrictamente necesario que se haya llevado a cabo completamente
    //La inicializacion antes de seguir con otras tareas. Es evidente que ene ste caso el hilo principal debe esperar la
    //Finalizacion de los otros hilos.

    public static void main(String[] args) {
        //Creamos los hilos
        Thread cargadorDatosIniciales = new Thread(new CargadorInicial()); //Tambien puede ser new Thread(new Runnable, "Nombre de hilo")
        Thread establecedorDeConexion = new Thread(new EstablecedorConexion());
        //Iniciamos los hilos
        cargadorDatosIniciales.start();
        establecedorDeConexion.start();
        //Esperamos a que finalicen los hilos.
        //El hilo principal no avanzara hasta que ambos hilos terminen su ejecucion
        try {
            cargadorDatosIniciales.join(); //tambien puede ser join(long ms), que si el hilo llamado no finaliza su
            // ejecucion en el numero de ms, el hilos llamador(main) continua su ejecucion
            establecedorDeConexion.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Realizar otras tareas en el principal (simulamos)
        System.out.println("Ejecutando tareas...");
        try {
            Thread.sleep(5000); //Tambien sirve TimeUnit.SECONDS.sleep(5); que duerme durante 5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();//Si el hilo esta durmiendo y se interrumpe, da esta excepcion
        }
        //Fin del programa
        System.out.println("Fin del programa");

    }
}
