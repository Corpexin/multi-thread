package com.company;

public class Main {
    //En este proyecto crearemos una aplicacion que ejecuta dos hilos, un hilo productor dedicado a producir elementos y almacenarlos
    //en un almacen y otro hilo consumidor dedicadoa  extraer elementos de dicho almacen y consumirlos. El problema radica
    //en que ambos pueden acceder simultaneamente a la misma estructura de datos para agregar o extraaer un elemento, lo
    //que implica que deberemos proveer mecanismos de sincronizacion sobre dichos accesos. Por otra parte un productor no
    //puede agregar un elemento a la estryctura de datos si el almacen esta lleno y un consumidor no puede extraer un elemento
    //del alamcen si este esta vacio. Estas condiciones centinela se ejecutan dentro del bloque sincronizado, por lo que deberemos
    //usar el metodo wait() para suspender los hilos mientras no pueden continuar y usar el metodo notifyAll() para reactivarlos
    //cuando deba volver a evaluarse la condicion centinela


    public static void main(String[] args) {
        //Creo el almacen
        Almacen alm = new Almacen();
        //Creo los hilos productor y consumidor
        Thread productor = new Thread(new Productor(alm), "Hilo Productor");
        Thread consumidor = new Thread(new Consumidor(alm), "Hilo Consumidor");
        //Inicio los hilos que realizaran tareas de consumir y producir
        productor.start();
        consumidor.start();

        //La clave aqui es identificar que es lo que hay que proteger y por que motivo
        //Hay que proteger meter y sacar elementos para evitar que accedan al mismo tiempo. Ejemplo,
        //el problema seria que fuese a meter un elemento en un hueco X que de repente ya no estuviera en esa posicion
        //porque otro hilo haya eliminado otro hueco o yoksetioxdxd
    }
}
