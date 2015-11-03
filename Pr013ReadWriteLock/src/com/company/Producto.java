package com.company; //Created by Corpex, by the Grace of God, on 26/10/2015.

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Producto {

    double precio;
    private ReadWriteLock cerrojoLE = new ReentrantReadWriteLock();

    public Producto(double precio){
        this.precio = precio;
    }

    public double getPrecio() {
        cerrojoLE.readLock().lock(); //echo cerrojo de lectura
        System.out.println(Thread.currentThread().getName()+" Consultando Precio...");
        try {//simulo la consulta
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("Precio: "+precio+"\n");//muestro el resultado
        cerrojoLE.readLock().unlock(); //quito cerrojo de lectura.
        return precio;
    }

    public void setPrecio(double precio) {
        cerrojoLE.writeLock().lock(); //echo cerrojo de escritura
        System.out.println(Thread.currentThread().getName()+" Modificando Precio...");//informo
        try {//simulo
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.precio = precio;
        System.out.print("Precio Modificado a: "+precio+"\n");

        cerrojoLE.writeLock().unlock();
    }

}
