package com.company; //Created by corpex, by the Grace of God, on 02/12/2015.

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class CalculadoraFactorial implements Callable<Integer> {

    // Variables miembro.
    private Integer numero;

    // Constructor.
    public CalculadoraFactorial(Integer numero) {
        this.numero = numero;
    }

    @Override
    public Integer call() throws Exception {
        // Calculo el factorial.
        int factorial = 1;
        if ((numero == 0) || (numero == 1)) {
            factorial = 1;
        } else {
            for (int i = 2; i <= numero; i++) {
                factorial *= i;
                // Para que tarde un poco más hacemos que duerma una milésimas.
                TimeUnit.MILLISECONDS.sleep(20);
            }
        }
        return factorial;
    }

}