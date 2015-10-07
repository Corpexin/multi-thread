package com.company; //Created by Corpex, by the Grace of God, on 07/10/2015.

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reloj implements Runnable{

    public void run(){
        while(true){
            System.out.println(hora());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Hilo Interrumpido "+e.getClass().getName());
                return;
            }

        }

    }

    private String hora() {
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(d);


    }
}
