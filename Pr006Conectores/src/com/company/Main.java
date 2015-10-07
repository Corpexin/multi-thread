package com.company;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        // Inicio un hilo que cargue los datos.
        Cargador cargador = new Cargador();
        Thread hiloCargador = new Thread(cargador,"Hilo del Cargador");
        hiloCargador.start();
        // Inicio un hilo que establezca la conexi�n.
        Conector conector = new Conector();
        Thread hiloConexion = new Thread(conector,"Hilo de Conexi�n");
        hiloConexion.start();
        // Para poder continuar debo esperar que a finalicen tanto el hilo del
        // cargador de datos como el hilo de establecimiento de la conexi�n.
        try {
            hiloCargador.join();
            hiloConexion.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Informo al usuario.
        System.out.printf("%s -> Carga y conexi�n realizadas, continuando...\n",
                new SimpleDateFormat("HH:mm:ss").format(new Date()));

    }

}
