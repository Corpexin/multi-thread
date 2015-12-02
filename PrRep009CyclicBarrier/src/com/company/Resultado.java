package com.company; //Created by corpex, by the Grace of God, on 01/12/2015.


// Se usa para almacenar en un array el número de ocurrencias del número
// buscado en cada una de las filas de la matriz.
public class Resultado {

    // Array de ocurrencias en las filas.
    private int ocurrenciasFila[];

    // Constructor. Recibe el número de filas de la matriz.
    public Resultado(int filas) {
        ocurrenciasFila = new int[filas];
    }

    // Establece el número de ocurrencias de una fila.
    public void  setOcurrencias(int fila, int valor){
        ocurrenciasFila[fila] = valor;
    }

    // Retorna los resultados.
    public int[] getResultados(){
        return ocurrenciasFila;
    }
}