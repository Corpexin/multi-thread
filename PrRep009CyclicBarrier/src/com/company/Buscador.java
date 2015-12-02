package com.company; //Created by corpex, by the Grace of God, on 01/12/2015.

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
// Busca las ocurrencias de un valor en una serie de fila de una matriz y almacena
// el resultado por fila en un objeto Resultado.
public class Buscador implements Runnable {

    // Propiedades.
    private int primeraFila;               // Primera fila en la que buscar.
    private int ultimaFila;                // Última fila en la que buscar.
    private Matriz matriz;                 // Matriz en la que buscar.
    private Resultado resultado;           // Objeto para almacenar el resultado.
    private int valor;                     // Valor a buscar.
    private final CyclicBarrier barrera;   // Barrera para sincronizar.

    // Constructor. Recibe los valores iniciales de las propiedades.
    public Buscador(int primeraFila, int ultimaFila, Matriz matriz,
                    Resultado resultado, int valor, CyclicBarrier barrera){
        // Inicializo las propiedades.
        this.primeraFila = primeraFila;
        this.ultimaFila = ultimaFila;
        this.matriz = matriz;
        this.resultado = resultado;
        this.valor = valor;
        this.barrera = barrera;
    }

    @Override
    public void run() {
        // Informo al usuario del inicio de la búsqueda.
        System.out.printf("%s: Buscando en las líneas %d-%d.\n",
                Thread.currentThread().getName(),
                primeraFila, ultimaFila);
        // Busco en cada fila de la matriz el número de ocurrencias de un valor y lo
        // almaceno en la fila correspondiente del objeto Resultado.
        int ocurrenciasFila;
        for (int i = primeraFila; i < ultimaFila; i++){
            // Obtengo la fila.
            int fila[] = matriz.getFila(i);
            // Reinicializo el contador.
            ocurrenciasFila=0;
            // Recorro las columnas de la fila e incremento el contador.
            for (int j = 0; j < fila.length; j++){
                if (fila[j] == valor){
                    ocurrenciasFila++;
                }
            }
            // Almacena el contador en la fila correspondiente del objeto Resultado.
            resultado.setOcurrencias(i, ocurrenciasFila);
        }
        // Informo al usuario de la finalización de la búsqueda en esas filas.
        System.out.printf("%s: Lineas %d-%d procesadas.\n",
                Thread.currentThread().getName(),
                primeraFila, ultimaFila);
        // Me sincronizo con el resto de buscadores, siendo suspendido hasta que todos
        // terminen la búsqueda parcial.
        try {
            barrera.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

}