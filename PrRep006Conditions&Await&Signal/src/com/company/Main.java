package com.company;

public class Main {
    //Ejemplo del productor/consumidor pero usando los cerrojos y conditions
    //ENUNCIADO:
    //En este proyecto crearemos una aplicacion que ejecuta dos hilos, un hilo productor dedicado a producir elementos y almacenarlos
    //en un almacen y otro hilo consumidor dedicadoa  extraer elementos de dicho almacen y consumirlos. El problema radica
    //en que ambos pueden acceder simultaneamente a la misma estructura de datos para agregar o extraaer un elemento, lo
    //que implica que deberemos proveer mecanismos de sincronizacion sobre dichos accesos. Por otra parte un productor no
    //puede agregar un elemento a la estryctura de datos si el almacen esta lleno y un consumidor no puede extraer un elemento
    //del alamcen si este esta vacio.

    //CONDITIONS suspender a un hilo que haya obtenido el cerrojo que tenga asociado la condicion porque esta no se cumpla, liberandolo.
    //Realmente, es una mierda, porque lo unico que haces es hacer una condicion normal en un while (ejemplo while(lista>0)) y
    //dentro de ese while pone condition.await para que se suspenda mientras no se cumpla. Al final pones un notify y listitt.
    //Similar a wait/notify., pero estos pueden definir distinto cerrojos independientes con distintas colas de espera
    //Usamos newCondition(). Para suspender un hilo usamos await(). Reactivar signalAll()/signal(). await puede estar sobrecargado con ms

    public static void main(String[] args) {
        //Creo el almacen
        Almacen alm = new Almacen();
        //Creo los hilos productor y consumidor
        Thread productor = new Thread(new Productor(alm), "Hilo Productor");
        Thread consumidor = new Thread(new Consumidor(alm), "Hilo Consumidor");
        //Inicio los hilos que realizaran tareas de consumir y producir
        productor.start();
        consumidor.start();

        //El truco esta en identificar la condiciones para que se suspenda un hilo: ejemplo: "Si el almacen se queda sin elementos
        //y se intenta pedir uno se jode" o tambien "si el almacen esta lleno y se mete un elemento mas se jode"
    }
}