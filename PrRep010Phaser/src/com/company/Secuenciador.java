package com.company; //Created by corpex, by the Grace of God, on 02/12/2015.

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Phaser;

public class Secuenciador extends Phaser {

    SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");

    // Constructor.
    public Secuenciador(int participantes) {
        super(participantes);
    }

    // Al avanzar de fase.
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        // Dependiendo de la fase en la que se encuentre.
        switch (phase) {
            case 0:
                return estudiantesSentados();
            case 1:
                return finalizadoEjercicio(1);
            case 2:
                return finalizadoEjercicio(2);
            case 3:
                return finalizadoExamen();
            default:
                return true;
        }
    }

    private boolean estudiantesSentados() {
        // Se informa de que va a comenzar el examen.
        System.out.println(formateador.format(new Date())
                + " --> Comienza el examen");
        // Se retorna false para que el secueciador continue.
        return false;
    }

    private boolean finalizadoEjercicio(int i) {
        // Se informa de que todos los alumnos han finalizado el ejercicio.
        System.out.println(formateador.format(new Date())
                + " --> Finalizado ejercicio " + i + "\nComienza ejercicio "
                + (i + 1));
        // Se retorna false para que el secueciador continue.
        return false;
    }

    private boolean finalizadoExamen() {
        // Se informa de que todos los alumnos han finalizado el examen.
        System.out.println(formateador.format(new Date())
                + " --> Los estudiantes han finalizado el examen");
        // Se retorna true para que el secueciador entre en modo terminación.
        return true;
    }

}