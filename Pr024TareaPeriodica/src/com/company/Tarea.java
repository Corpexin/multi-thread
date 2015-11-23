package com.company; //Created by Corpex, by the Grace of God, on 23/11/2015.

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tarea implements Runnable{
    String nombre;

    public Tarea(String nombre){
        this.nombre = nombre;
    }
    @Override
    public void run() {
        SimpleDateFormat formato = new SimpleDateFormat("hh:mm:ss");
        System.out.println("Tarea ejecutada en "+formato.format(new Date()));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
