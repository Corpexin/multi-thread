package com.company; //Created by Corpex, by the Grace of God, on 07/10/2015.

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Cargador implements Runnable{

    public Cargador(){

    }

    @Override
    public void run() {
        // Obtengo un formateado de hora.
        SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");
        // Informo del inicio de la carga.
        System.out.printf("%s -> Cargando datos... \n",
                formateador.format(new Date()));
        // Duermo durante 5 segundos.
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Informo de finalización de carga.
        System.out.printf("%s -> Carga de datos finalizada\n",
                formateador.format(new Date()));

    }
}
