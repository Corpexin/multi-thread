package com.company; //Created by corpex, by the Grace of God, on 02/12/2015.

import java.util.concurrent.Callable;

public class Tarea implements Callable<String> {

    private SistemaValidacion sistema;
    private String usuario;
    private String password;

    // Constructor. Recibe el sistema, el usuario y la contraseña.
    public Tarea(SistemaValidacion sistema, String usuario, String password) {
        this.sistema = sistema;
        this.usuario = usuario;
        this.password = password;
    }

    // Retorna el nombre del sistema si se valida el usuario,
    // o lanza una excepción en caso contrario.
    @Override
    public String call() throws Exception {
        if (!sistema.validar(usuario, password)) {
            System.out.printf("%s -> Usuario incorrecto\n", sistema.getName());
            throw new Exception("Usuario incorrecto");
        }
        System.out.printf("%s -> Usuario validado\n", sistema.getName());
        return sistema.getName();
    }

}