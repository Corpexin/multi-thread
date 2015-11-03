package com.company; //Created by Corpex, by the Grace of God, on 26/10/2015.

public class Cliente implements Runnable {
    Producto prod;

    public Cliente(Producto prod){
        this.prod = prod;
    }
    @Override
    public void run() {
        //Consulta el precio getPrecio
        prod.getPrecio();

    }
}
