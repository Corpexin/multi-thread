package com.company;

public class Main {
    //En este proyecto vamos a realizar un programa que simule una cuenta bancaria en la que un hilo que realiza
    //una serie de abonos a la cuenta y otrol hilo realiza una serie de cargos. Debemos recordar que el orden de
    //ejecucion de los hilos no esta garantizado por la jvm, por lo que podrian intercalarse las operaciones de manera
    //que elsaldo no reflejara el valor correcto. Usaremos metodos syncrhinized para segurar que el saldo final es el
    //correcto incluso aunque se pretendieran realizar ambas operaciones simultaneamente

    //**Con bloque sincronizado: mismo enunciado pero sincronizando solo el bloque corresponiente a la seccion critica de
    //**metodo de pago

    public static void main(String[] args) {
        //Creo la clase cuenta bancaria con atributo saldo
        CuentaBancaria cuenta = new CuentaBancaria(500);//Cuenta bancaria de 500 euros de saldo
        //Creo el hilo abonador y el hilo cargador
        Thread hiloAbonador = new Thread(new Abonador(cuenta),"Abonador");
        Thread hiloCargador = new Thread(new Cargador(cuenta),"Cargador");
        //Inicio los hilos que realizaran sus tareas
        hiloAbonador.start();
        hiloCargador.start();
        //El hilo principal se espera a que finalicen
        try {
            hiloAbonador.join();
            hiloCargador.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Muestro el saldo final
        System.out.println("Saldo final: "+cuenta.saldo);

        //mas info en clase cuenta bancaria
        //Si un metodo se declara synchronized,  ningun otro hilo podra ejecutar dicho metodo sobre el objeto ni cualquier
        //otro metodo synchronized del mismo objeto. siendo suspendido hasta que se termine con el metodo.

        //Si el metodo synchronized ademas es estatico, un solo hilo podra ejecutar este metodo concreto, pero otro hilo
        //podra estar ejecutando cualquier otro metodo no estatico del objeto.
    }
}
