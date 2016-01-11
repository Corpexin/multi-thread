package com.company; //Created by corpex, by the Grace of God, on 01/12/2015.

import java.util.ArrayList;

public class Almacen {
    ArrayList<String> elementos;
    private Object cerrojo = new Object();//aqui no tendria sentido crear un cerrojo aparte, pudiendo usar this. Solo se crea cuando haya mas de 1
    //varios mutex se suele usar cuando los thread actuan sobre mas de un elemento, y que sean independientes entre si.
    final int LIMITE = 3;
    public Almacen(){
        elementos = new ArrayList<>(); //almacen de 10 elementos
    }

    public void almacenarElemento(String produccion) throws InterruptedException {
        synchronized (cerrojo) {
            while(elementos.size()>=LIMITE) {
                cerrojo.wait(); //se suspende el hilo si se llena el almacen
                System.out.println("Lista llena");
            }
            elementos.add(produccion);
            cerrojo.notifyAll(); //se notifica que se ha a;adido elemento, por si hay esperando algun hilo
        }
    }

    public String retirarElemento() throws InterruptedException {
        String resultado="";
        synchronized (cerrojo) {
            while(elementos.isEmpty()) {
                cerrojo.wait();
                System.out.println("Lista vacia");
            }
            resultado = elementos.remove(elementos.size() - 1);
            cerrojo.notifyAll();//se notifica que se ha eliminado
        }
        return resultado;
    }
}
