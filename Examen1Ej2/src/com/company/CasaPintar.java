package com.company; //Created by corpex, by the Grace of God, on 04/12/2015.


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class CasaPintar implements Callable<String>{
    Random rnd;
    int numCasa;

    public CasaPintar(int numCasa){
        rnd = new Random();
        this.numCasa = numCasa;
    }

    @Override
    public String call() {
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
        try {
            TimeUnit.SECONDS.sleep(rnd.nextInt(3)+2); //tiempo en ser pintada 2-5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Numero de casa: "+numCasa +" - Hora Finalizacion: "+ formato.format(new Date());
    }
}
