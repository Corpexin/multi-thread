package com.company; //Created by Corpex, by the Grace of God, on 24/11/2015.

import java.util.concurrent.Callable;

public class Tarea implements Callable<Integer>{
    int num;

    public Tarea(int num){
        this.num = num;
    }

    @Override
    public Integer call() throws Exception {
        int numero = num;
        for(int i=num-1 ; i>0 ; i--){
            numero = numero * i;
        }
        return numero;
    }
}
