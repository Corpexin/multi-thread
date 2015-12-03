package com.company; //Created by corpex, by the Grace of God, on 02/12/2015.
import java.util.Random;
import java.util.concurrent.TimeUnit;

// Simula un sistema de validación
public class SistemaValidacion {

    private String nombreSistema;

    // Constructor.
    public SistemaValidacion(String nombreSistema) {
        this.nombreSistema = nombreSistema;
    }

    // Simula la validación del usuario.
    // Recibe el nombre de usuario y la contraseña.
    public boolean validar(String name, String password) {
        // Simulo la duración de la validación.
        Random aleatorio = new Random();
        try {
            int duracion = aleatorio.nextInt(5);
            System.out.printf("%s -> Duración: %d segundos\n", nombreSistema, duracion);
            TimeUnit.SECONDS.sleep(duracion);
        } catch (InterruptedException e) {
            return false;
        }
        // Simulo el resultado de la validación.
        return aleatorio.nextBoolean();
    }

    // Retorna el nombre del sistema de validación.
    public String getName() {
        return nombreSistema;
    }

}