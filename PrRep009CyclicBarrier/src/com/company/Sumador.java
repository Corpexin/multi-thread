package com.company; //Created by corpex, by the Grace of God, on 01/12/2015.

// Suma los valores almacenados en el objeto Resultado, correspondientes a un
// array con el número de ocurrencias por fila.
public class Sumador implements Runnable {

    // Resultados: Número de ocurrencias por fila.
    private Resultado resultado;

    // Constructor. Recibe el resultado.
    public Sumador(Resultado results){
        this.resultado=results;
    }

    @Override
    public void run() {
        // Informo al usuario de que se están procesando los resultados.
        System.out.printf("Procesando resultado final...\n");
        // Recorro el array de resultado sumando todos los valores.
        int suma = 0;
        int datos[] = resultado.getResultados();
        for (int valor:datos){
            suma += valor;
        }
        // Informo al usuario del resultado final.
        System.out.printf("Número total de ocurrencias: %d.\n", suma);
    }

}
