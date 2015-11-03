package com.company;

public class Main {

    public static void main(String[] args) {

        //Creamos la clase producto
        Producto[] prod = new Producto[3];
        //Creamos los hilos de clientes asignandole el producto
        Thread[] clientes = new Thread[3];
        for(int i =0 ; i<clientes.length ; i++){
            clientes[i] = new Thread(new Cliente(prod[i]));
        }
        //Creamos el hilo de la tienda asignandole el producto
        Thread tienda = new Thread(new Tienda(prod[0]));
        //Lanzamos los hilos. Los clientes se lanzan de poco en poco
       for(Thread t : clientes){
           t.start();
       }
        tienda.start();


    }
}
