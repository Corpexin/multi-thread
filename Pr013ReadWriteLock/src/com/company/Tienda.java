package com.company; //Created by Corpex, by the Grace of God, on 26/10/2015.

public class Tienda implements Runnable{
    Producto prod;

    public Tienda(Producto prod){
        this.prod = prod;
    }

    @Override
    public void run() {
        //Cambia el precio (setPrecio)
        prod.setPrecio(20);
    }
}
