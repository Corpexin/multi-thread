package com.company; //Created by Corpex, by the Grace of God, on 07/10/2015.

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Conector implements Runnable{

    @Override
    public void run() {
        // Obtengo un formateado de hora.
        SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");
        // Informo del inicio de la conexión.
        System.out.printf("%s -> Conectando...\n",
                formateador.format(new Date()));
        // Duermo durante 8 segundos.
        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Informo del inicio de la conexión.
        System.out.printf("%s -> Conexión establecida\n",
                formateador.format(new Date()));

    }

}
