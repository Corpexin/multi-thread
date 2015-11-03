package com.company;

public class Main {

    public static void main(String[] args) {

        //Creamos la clase producto
        Producto prod = new Producto(100);

        //Creamos los hilos de clientes asignandole el producto
        Thread[] clientes = new Thread[3];
        for(int i =0 ; i<clientes.length ; i++){
            clientes[i] = new Thread(new Cliente(prod), "Cliente"+i);
        }
        //Creamos el hilo de la tienda asignandole el producto
        Thread tienda = new Thread(new Tienda(prod), "Tienda");

        //Vamos lanzando poco a poco
        clientes[0].start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clientes[1].start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tienda.start();
        clientes[2].start();



    }
}
