package com.company; //Created by Corpex, by the Grace of God, on 09/11/2015.

import java.util.concurrent.Phaser;

public class Fase extends Phaser {

    public Fase(int numEstudiantes) {
        super(numEstudiantes);
    }

    public boolean onAdvance(int phase, int registeredParties){
        switch(phase) {
            case 0:
                return estudiantesSentados();
            case 1:
                return ejfinaliz(1);
            case 2:
                return ejfinaliz(2);
            case 3:
                return ejfinaliz(3);
            case 4:
                return ejfinaliz(4);
            case 5:
                return ejfinaliz(5);
            case 6:
                return examfinaliz();
            default:
                return true;
        }

    }

    private boolean examfinaliz() {
        System.out.println("Fin del Examen");
        return false;
    }

    private boolean ejfinaliz(int i) {
        System.out.println("Comienza Ej"+i);
        return false;
    }

    private boolean estudiantesSentados() {
        System.out.println("Comienza el Examen");
        return false;
    }
}
