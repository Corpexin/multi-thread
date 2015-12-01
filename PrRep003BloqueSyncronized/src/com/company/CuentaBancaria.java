package com.company; //Created by corpex, by the Grace of God, on 30/11/2015.

public class CuentaBancaria {
    double saldo;

    public CuentaBancaria(double saldo){
        this.saldo = saldo;
    }

    public synchronized void ingresarDinero(double ingreso){ //se pone synchronized para que un hilo no pueda ingresar mientras
        //otro esta sacando dinero, o hace una cosa o la otra
        saldo = saldo + ingreso;
        System.out.println("Se ingresa "+ingreso);

        //synchronized (this){//Si solo hay un cerrojo, se usa this.
        //    saldo = saldo + ingreso;
        //    System.out.println("Se ingresa "+ingreso);
        //}


        //Recordar solo poner dentro del bloque lo que sea importante de modificar
    }

    public synchronized void sacarDinero(double retirada){
        saldo = saldo - retirada;
        System.out.println("Se retira "+retirada);

        //synchronized (this){
        //    saldo = saldo - retirada;
        //    System.out.println("Se retira "+retirada);
        //}
    }
}
