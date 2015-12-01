package com.company; //Created by corpex, by the Grace of God, on 01/12/2015.

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Almacen {
    ArrayList<String> elementos;
    final int LIMITE = 3;
    public Almacen(){
        elementos = new ArrayList<>(); //almacen de 10 elementos
    }
    ReentrantLock cerrojo = new ReentrantLock();
    Condition almacenNoEsteLleno = cerrojo.newCondition();
    Condition almacenNoEsteVacio = cerrojo.newCondition();

    public void almacenarElemento(String produccion) throws InterruptedException {
       cerrojo.lock();
            while(elementos.size()>=LIMITE) {
                almacenNoEsteLleno.await();
            }
            elementos.add(produccion);
            almacenNoEsteLleno.signalAll(); //se notifica que se ha a;adido elemento, por si hay esperando algun hilo
        cerrojo.unlock(); //si lo pones en un try catch, poner este en el finally
    }

    public String retirarElemento() throws InterruptedException {
        String resultado="";
        cerrojo.lock();
            while(elementos.isEmpty()) {
                almacenNoEsteVacio.await();
            }
            resultado = elementos.remove(elementos.size() - 1);
            almacenNoEsteVacio.signalAll();//se notifica que se ha eliminado
        cerrojo.unlock();
        return resultado;
    }
}
